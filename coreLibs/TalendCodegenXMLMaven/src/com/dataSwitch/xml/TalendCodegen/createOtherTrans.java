
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.List;

import com.dataSwitch.ds.commons.datatyperules.DataTypeConvertor;
import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.InRecDTO;
import com.dataSwitch.xml.ETLMapDTO.OtherTransInfo;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.ReportLogInfo;
import com.dataSwitch.xml.ETLMapDTO.SorterInfo;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.ElementParameter;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;

public class createOtherTrans {
	public static void checkTransType(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb,String szoutputLinkedServiceFilePath, List<String> cntxtParamsList, ReportLogInfo reportObj, DataMapping dataMappingObj, boolean checkCDChasInsrtDelete, TalendfileProcessType pipeline)
	{
		if(srctgtdb.fileType.equalsIgnoreCase("Normalizer") || srctgtdb.fileType.equalsIgnoreCase("Pivot Stage"))
		{
			createOtherTrans.fillTransfortNormalize(recordperPipe, docspecs, compNode, srctgtdb);
			//createReportLog.addToLog("==> tPivotToColumnsDelimited (Normalizer Component): Manual handling for properties required." + System.lineSeparator(),szoutputLinkedServiceFilePath);
			reportObj.transTypeList.put(recordperPipe.compName, "Pivot Component(tMom)");
		}
		else if(srctgtdb.fileType.equalsIgnoreCase("Expression") )
		{
			createOtherTrans.fillTransforReusableExpastMap(recordperPipe, docspecs, compNode, srctgtdb);
			//createReportLog.addToLog("==> tMap (Expression Component): Manual handling for properties required." + System.lineSeparator(),szoutputLinkedServiceFilePath);
			reportObj.transTypeList.put(recordperPipe.compName, "Reusable Expression");
		}
		else if(srctgtdb.fileType.equalsIgnoreCase("WebSphereMQConnector Stage"))
		{
			createOtherTrans.fillTransforMessageQueue(recordperPipe, docspecs, compNode, srctgtdb,cntxtParamsList);
			//createReportLog.addToLog("==> tMomInput (Messaging Component): Manual handling for properties required." + System.lineSeparator(),szoutputLinkedServiceFilePath);
			reportObj.transTypeList.put(recordperPipe.compName, "WebSphereMQ Component(tMom)");
		}
		else if(srctgtdb.fileType.equalsIgnoreCase("Change Data Capture"))
		{
			createCDC.fillTransforCDC(recordperPipe, docspecs, compNode, srctgtdb,cntxtParamsList,dataMappingObj,checkCDChasInsrtDelete);
			
			if(checkCDChasInsrtDelete == true && recordperPipe.compName.contains("_dsCDC"))
				createCDC.fillUnionCompTrans(recordperPipe, docspecs, pipeline, srctgtdb,cntxtParamsList,dataMappingObj,checkCDChasInsrtDelete);
			
		}
	}



	public static void fillTransforMessageQueue(TransRecExtDTO recordperPipe, Connector docspecs, Node compNode,
			SrcTgtInfo srctgtdb, List<String> cntxtParamsList) 
	{
		Double compVersion = 0.101;
		String sorttranstype = "tMomInput";
		String compName = recordperPipe.compName;
		String xPos = recordperPipe.xLevelPos;
		String yPos = recordperPipe.yLevelPos;
		Utils.getcompDetails(compNode,sorttranstype, compVersion,xPos,yPos);
		fillPropsforMessageQueue(recordperPipe,docspecs,compNode,srctgtdb,cntxtParamsList);
		Node.Metadata mtdata = new Node.Metadata();
		mtdata.setConnector("FLOW");
		mtdata.setLabel("rw_"+compName);
		mtdata.setName(compName);
		for(int i = 0 ; i < recordperPipe.outRecsList.size(); i++)
		{
			OutRecDTO outRecs = recordperPipe.outRecsList.get(i);
			for (int j = 0; j < outRecs.columnList.size(); j++) 
			{
				ColumnDTO cols = outRecs.columnList.get(j);
				Node.Metadata.Column colmn = new Node.Metadata.Column();
				String colsName = cols.colStruct.name;
				String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				String dataType = cols.colStruct.dataType;
				int length = cols.colStruct.length;
				int precs = cols.colStruct.precision;
				colmn.setKey("false");

//				colmn.setLength(length);
				
				
				if(cols.colStruct.length==0){
					 colmn.setLength(cols.colStruct.precision);
					 colmn.setPrecision(cols.colStruct.Scale);
				}
				else{
					colmn.setLength(cols.colStruct.length);
					colmn.setPrecision(cols.colStruct.Scale);
				}
				
				colmn.setName(colName);

				 String nullable = Boolean.toString(cols.colStruct.isNullable);
				 colmn.setNullable(nullable);
					if(cols.colStruct.description != null)
					{
						String comment = cols.colStruct.description;
						colmn.setComment(comment);
					}
					else
					{
						colmn.setComment("");
					}
				colmn.setPattern("");
//				colmn.setPrecision(precs);
				colmn.setType(dataType);
				colmn.setUsefulColumn("true");
				mtdata.getColumn().add(colmn);
			}
		}
		compNode.getMetadata().add(mtdata);	
		
	}
	
	
	public static void fillPropsforMessageQueue(TransRecExtDTO recordperPipe, Connector docspecs, Node compNode,
			SrcTgtInfo srctgtdb, List<String> cntxtParamsList) 
	{
		
		String compName = recordperPipe.compName;
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","KEEPLISTENING","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","STARTSERVER","false","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","USEMAX","false","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","USEMAX_WITH_TIMEOUT","false","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","USE_CONNECTION","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("COMPONENT_LIST","CONNECTION","","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","FAILOVER","","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","STATIC","false","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","CONNECTION_PARAM_FAILOVER","\"?randomize=false\"","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","CONNECTION_PARAM_STATIC","\"?transport.maxReconnectDelay=5000&amp;transport.useExponentialBackOff=false\"","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","TIMEOUT","1","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","MAXMSG","1000","false"));
		elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","SERVER","WebSphere"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","USE_SSL","false","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("LABEL","USE_SSL_NOTE","Notice: You should use tSetKeysore or JVM arguments to specify keystore or truststore","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","SERVERADDRESS","\"localhost\""));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","SERVERPORT","\"1414\""));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","SERVERS","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","USER","\"\""));
		elemParam.add(Utils.getPropDtlsforExp("PASSWORD","PASS","0RMsyjmybrE="));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","FROM","\"\"","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","MSGTYPE","Topic","false"));
		if(srctgtdb.transDtls.size() > 0)
		{
			for(int i = 0 ; i < srctgtdb.transDtls.size() ; i++)
			{
				String propNames = srctgtdb.transDtls.get(i);
				String propName= propNames.substring(0,propNames.indexOf(":")).trim();
				String propValue = propNames.substring(propNames.indexOf(":")+1).trim();
				propValue = Utils.getParamValWithContext(propValue, docspecs, cntxtParamsList);
				if(propName.equalsIgnoreCase("Channel name"))
				{
					elemParam.add(Utils.getPropDtlsforExp("TEXT","CHANNEL",propValue));
				}
				else if(propName.equalsIgnoreCase("Queue manager"))
				{
					elemParam.add(Utils.getPropDtlsforExp("TEXT","QM",propValue));
				}
				else if(propName.equalsIgnoreCase("Queue name"))
				{
					elemParam.add(Utils.getPropDtlsforExp("TEXT","QUEUE",propValue));
				}
				
			}
		}
		else
		{
			elemParam.add(Utils.getPropDtlsforExp("TEXT","CHANNEL","\"DC.SVRCONN\""));
			elemParam.add(Utils.getPropDtlsforExp("TEXT","QM","\"\""));
			elemParam.add(Utils.getPropDtlsforExp("TEXT","QUEUE","\"\""));
		}
		elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","MESSAGE_BODY_TYPE","Text"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","IS_TRANSACTED","false","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","IS_USE_MESSAGE_ID","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","MSG_ID","\"\"","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","COMMIT","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","ROLLBACK","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","AUTO_BACKOUT","false"));
		elemParam.add(Utils.getPropDtlsforExp("TABLE","ADDITIONAL_OPTIONS","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","BROWSE","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CLOSED_LIST","ACKNOWLEDGMENT_MODE","AUTO_ACKNOWLEDGE","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","GET_JMS_HEADER","false","false"));
		elemParam.add(Utils.getPropDtlsforExp("TABLE","JMS_HEADERS","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","GET_JMS_PROPERTIES","false","false"));
		elemParam.add(Utils.getPropDtlsforExp("TABLE","JMS_PROPERTIES","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","GET_MQMD_FIELDS","false","false"));
		elemParam.add(Utils.getPropDtlsforExp("TABLE","MQMD_FIELDS","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","USE_MQ_HEADER","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","USE_FIX_MQRFH2","false","false"));
		elemParam.add(Utils.getPropDtlsforExp("TABLE","MQRFH2_FIXED_FIELD","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","USE_MQRFH2_MCD","false","false"));
		elemParam.add(Utils.getPropDtlsforExp("TABLE","MQRFH2_MCD_FIELD","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","USE_MQRFH2_JMS","false","false"));
		elemParam.add(Utils.getPropDtlsforExp("TABLE","MQRFH2_JMS_FIELD","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","USE_MQRFH2_USR","false","false"));
		elemParam.add(Utils.getPropDtlsforExp("TABLE","MQRFH2_USR_FIELD","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","SET_MQ_SSL_CIPHER","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CLOSED_LIST","MQ_SSL_CIPHER","SSL_RSA_WITH_NULL_MD5","false"));
		compNode.getElementParameter().addAll(elemParam);
		
	
	}
	
	public static void fillPropfortNormalize(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb)
	{
		String compName = recordperPipe.compName;
		String normalizeCol = srctgtdb.srcTgtColsList.get(0).aliasName;
		
		
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("PREV_COLUMN_LIST","NORMALIZE_COLUMN",normalizeCol));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","ITEMSEPARATOR","\",\""));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","DEDUPLICATE","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","CSV_OPTION","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CLOSED_LIST","ESCAPE_CHAR","ESCAPE_MODE_DOUBLED","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","TEXT_ENCLOSURE","\"\"\"","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","DISCARD_TRAILING_EMPTY_STR","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","TRIM","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
    	
		compNode.getElementParameter().addAll(elemParam);
		
	}
	
	public static void fillTransfortNormalize(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb)
	{
		Double compVersion = 0.101;
		String sorttranstype = "tNormalize";
		String compName = recordperPipe.compName;
		String xPos = recordperPipe.xLevelPos;
		String yPos = recordperPipe.yLevelPos;
		Utils.getcompDetails(compNode,sorttranstype, compVersion,xPos,yPos);
		fillPropfortNormalize(recordperPipe,docspecs,compNode,srctgtdb);
		Node.Metadata mtdata = new Node.Metadata();
		mtdata.setConnector("FLOW");
		mtdata.setLabel("rw_"+compName);
		mtdata.setName(compName);
		for(int i = 0 ; i < recordperPipe.outRecsList.size(); i++)
		{
			OutRecDTO outRecs = recordperPipe.outRecsList.get(i);
			for (int j = 0; j < outRecs.columnList.size(); j++) 
			{
				ColumnDTO cols = outRecs.columnList.get(j);
				Node.Metadata.Column colmn = new Node.Metadata.Column();
				String colsName = cols.colStruct.name;
				String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				String dataType = cols.colStruct.dataType;
				int length = cols.colStruct.length;
				int precs = cols.colStruct.precision;
				colmn.setKey("false");

//				colmn.setLength(length);
				
				
				if(cols.colStruct.length==0){
					 colmn.setLength(cols.colStruct.precision);
					 colmn.setPrecision(cols.colStruct.Scale);
				}
				else{
					colmn.setLength(cols.colStruct.length);
					colmn.setPrecision(cols.colStruct.Scale);
				}
				
				colmn.setName(colName);

				 String nullable = Boolean.toString(cols.colStruct.isNullable);
				 colmn.setNullable(nullable);
					if(cols.colStruct.description != null)
					{
						String comment = cols.colStruct.description;
						colmn.setComment(comment);
					}
					else
					{
						colmn.setComment("");
					}
				colmn.setPattern("");
//				colmn.setPrecision(precs);
				colmn.setType(dataType);
				colmn.setUsefulColumn("true");
				mtdata.getColumn().add(colmn);
			}
		}
		compNode.getMetadata().add(mtdata);	
	}
	
	public static void fillTransforReusableExpastMap(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb)
	{
			Double compVersion = 0.101;
			String sorttranstype = "tMap";
			String compName = recordperPipe.compName;
			String xPos = recordperPipe.xLevelPos;
			String yPos = recordperPipe.yLevelPos;
			Utils.getcompDetails(compNode,sorttranstype, compVersion,xPos,yPos);
			Utils.fillPropforExp_tMap(recordperPipe, docspecs, compNode, srctgtdb);
			List<String> prevComp = Utils.getPrevCompName(recordperPipe);
			String prevcompName = prevComp.toString().replace("[", "").replace("]", "");
			String prevCompName =  prevcompName.substring(0,prevcompName.indexOf("/"));
			String prevCompNametrans = prevcompName.substring(prevcompName.indexOf("/")+1 , prevcompName.length());
			Node.Metadata mtdata = new Node.Metadata();
			mtdata.setConnector("FLOW");
			mtdata.setLabel("rw_"+compName);
			mtdata.setName(compName);
		    Node.NodeData ndData = new Node.NodeData();
		    ndData.setXsiType("TalendMapper:MapperData");
		    Node.NodeData.UIProperties ndUI = new Node.NodeData.UIProperties();
		    ndUI.setshellMaximized("true");
		    ndData.setUIProperties(ndUI);
		    Node.NodeData.VarTables ndVars = new Node.NodeData.VarTables();
		    ndVars.setname("Var");
		    ndVars.setminimized("true");
		    ndVars.setsizeState("INTERMEDIATE");
		    Node.NodeData.OutPutTables outTbl = null;
		    for(int i = 0 ; i < recordperPipe.outRecsList.size() ; i++)
			  {
				  OutRecDTO outRecs = recordperPipe.outRecsList.get(i);
				  outTbl = new Node.NodeData.OutPutTables();
				  outTbl.setsizeState("INTERMEDIATE");
				  Node.Metadata mtData = new Node.Metadata();
				  if(i!=0){
					  outTbl.setname("rw_"+compName+i);
					  mtData.setConnector("FLOW");
					  mtData.setName("rw_"+compName+i);
					  mtData.setLabel("rw_"+compName+i);
				  }else{
					  outTbl.setname("rw_"+compName);
					  mtData.setConnector("FLOW");
					  mtData.setName("rw_"+compName);
					  mtData.setLabel("rw_"+compName);
				  }
				 for(int j = 0 ; j < outRecs.columnList.size() ; j++)
				 {
					 Metadata.Column colm = new Metadata.Column();
					 ColumnDTO cols = outRecs.columnList.get(j);
					 String ColsName = cols.colStruct.name;
					 String dataType = cols.colStruct.dataType;
					 int length = cols.colStruct.length;
					 int precision = cols.colStruct.precision;
					 String srcComp = cols.srcColumn.colStruct.name;
					 String srcCompName = srcComp.substring(0, srcComp.indexOf("."));
					 String colName = ColsName.substring(ColsName.indexOf(".")+1, ColsName.length());
					 String srcCols = cols.srcColumn.colStruct.name;
					 String srcColsName = srcCols.substring(srcCols.indexOf(".")+1, srcCols.length());
					 colm.setName(colName);
					 colm.setKey("false");

//					 colm.setLength(length);
					 
					 
					 if(cols.colStruct.length==0){
						 colm.setLength(cols.colStruct.precision);
						 colm.setPrecision(cols.colStruct.Scale);
					}
					else{
						colm.setLength(cols.colStruct.length);
						colm.setPrecision(cols.colStruct.Scale);
					}

					 String nullable = Boolean.toString(cols.colStruct.isNullable);
					 colm.setNullable(nullable);
						if(cols.colStruct.description != null)
						{
							String comment = cols.colStruct.description;
							colm.setComment(comment);
						}
						else
						{
							colm.setComment("");
						}
					 colm.setPattern("");
//					 colm.setPrecision(precision);
					 colm.setType(dataType);
					 colm.setUsefulColumn("true");
					 mtData.getColumn().add(colm);
					 if(!srcCompName.equalsIgnoreCase(compName))
					 {
						 String prevOutrec = cols.srcColumn.parentOutRec.name;
						 String prevOutrecName = prevOutrec.substring(0, prevOutrec.indexOf("Out"));
						 Node.NodeData.OutPutTables.MapperTableEntries mapTbl = new Node.NodeData.OutPutTables.MapperTableEntries ();
						 mapTbl.setName(colName);
						 mapTbl.setType(dataType);
						 mapTbl.setNullable(nullable);
						 if(prevCompNametrans.equalsIgnoreCase("Filter Transformation") || prevCompNametrans.equalsIgnoreCase("Conditional Grouping"))
						 {
						   mapTbl.setexpression("Case_"+compName+"."+srcColsName);
						 }
						 else
						 {
	                        mapTbl.setexpression("rw_"+prevOutrecName+"."+srcColsName);
						 }
						 outTbl.getMapperTableEntries().add(mapTbl);
					 }
					 else
					 {
						 Node.NodeData.OutPutTables.MapperTableEntries mapTbl = new Node.NodeData.OutPutTables.MapperTableEntries ();
						 mapTbl.setName(colName);
						 mapTbl.setType(dataType);
						 mapTbl.setNullable(nullable);
						 if(prevCompNametrans.equalsIgnoreCase("Filter Transformation") || prevCompNametrans.equalsIgnoreCase("Conditional Grouping"))
						 {
						   mapTbl.setexpression("Case_"+compName+"."+srcColsName);
						 }
						 else
						 {
	                        mapTbl.setexpression("rw_"+compName+"."+srcColsName);
						 }
						 outTbl.getMapperTableEntries().add(mapTbl);
					 }
				 }
				 ndData.getOutPutTables().add(outTbl);
				 compNode.getMetadata().add(mtData);
			  }
		    for(int j = 0 ; j < recordperPipe.inRecsList.size() ; j++)
			{
				  InRecDTO inrec = recordperPipe.inRecsList.get(j);
				  String inReCmp = inrec.name.substring(0, inrec.name.indexOf("Out"));
				  Node.NodeData.InPutTables inTbl = new Node.NodeData.InPutTables();
				  inTbl.setsizeState("INTERMEDIATE");
				  inTbl.setlookupMode("LOAD_ONCE");
				  inTbl.setmatchingMode("UNIQUE_MATCH");
				  inTbl.setname("rw_"+inReCmp);
				  for( int j1 = 0 ; j1 < inrec.prevOutRec.columnList.size(); j1++)
				  {
					  Node.NodeData.InPutTables.MapperTableEntries mapTbl = new Node.NodeData.InPutTables.MapperTableEntries();
					  ColumnDTO colsList = inrec.prevOutRec.columnList.get(j1);
					  String colsName = colsList.colStruct.name;
					  String dataType = colsList.colStruct.dataType;
					  String nullable = Boolean.toString(colsList.colStruct.isNullable);	
					  String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
					  mapTbl.setName(colName);
					  mapTbl.setType(dataType);
					  mapTbl.setNullable(nullable);
					  inTbl.getMapperTableEntries().add(mapTbl);
				  }
				  ndData.getInPutTables().add(inTbl);
			 }
			 ndData.setVarTables(ndVars);
			 compNode.setNodeData(ndData);
	}
	
}