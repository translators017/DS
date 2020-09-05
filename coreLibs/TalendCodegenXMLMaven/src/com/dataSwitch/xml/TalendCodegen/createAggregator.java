
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.xml.ETLMapDTO.AggInfo;
import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.InRecDTO;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.ReportLogInfo;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.ElementParameter;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata;
import com.dataSwitch.xml.connectorspecs.Connector;

public class createAggregator {
	
	
public static HashMap<String,String> fillPropsforAgg(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb, TalendfileProcessType pipeline, ReportLogInfo reportObj)
{
		String[] fields = new String[]{"TEXT","TABLE","TABLE","TEXT","CHECK","CHECK","CHECK","TEXT"};
		String[] names = new String[]{"UNIQUE_NAME","GROUPBYS","OPERATIONS","LIST_DELIMITER","USE_FINANCIAL_PRECISION","CHECK_TYPE_OVERFLOW",
				"CHECK_ULP","CONNECTION_FORMAT"};
		String[] shows = new String[]{"false","","","","","","",""};
		String[] values = new String[]{"get from reclist","","","&quot;,&quot;","true","false","false","row"};
		
		HashMap<String,String> colToDsColMap = new HashMap<String, String>();
		HashMap<String,String> dsColToAggExpMap = new HashMap<String, String>();
		
		for(int i=0;i<fields.length;i++){
			
			TalendfileProcessType.Node.ElementParameter elemParam = new TalendfileProcessType.Node.ElementParameter();
			
			elemParam.setField(fields[i]);
			elemParam.setName(names[i]);
			if(shows[i]!=""){
				elemParam.setShow(shows[i]);
			}
			if(values[i].equalsIgnoreCase("get from reclist")){
				elemParam.setValue(recordperPipe.compName);
			}else{
				elemParam.setValue(values[i]);
			}
			if(recordperPipe.transInfo != null)
			{
			AggInfo agg = (AggInfo) recordperPipe.transInfo;
			
			if(fields[i].equalsIgnoreCase("table")){
				
				if(names[i].equalsIgnoreCase("groupbys")){
					
//					for(int j=0;j<agg.colDTOList.size();j++)
//					{
						ColumnDTO cols= agg.colDTOList.get(0);
						if(cols.isBeingUsed==89)
						{
							int m=-1;
							//System.out.println(cols.transRule.grpByColNamesList.size());
							for (int l = 0; l < 2*cols.transRule.grpByColNamesList.size(); l++) 
							{
								//System.out.println(m);
								if(l%2==0){
									m++;
								}
								String grpCols = cols.transRule.grpByColNamesList.get(m);
								
								
								String grpName = grpCols.substring(grpCols.indexOf(".")+1, grpCols.length());
								TalendfileProcessType.Node.ElementParameter.ElementValue elemval = new TalendfileProcessType.Node.ElementParameter.ElementValue();
								String elementRef;
								if(l%2==0){
									elementRef = "OUTPUT_COLUMN";									
								}else{
									elementRef = "INPUT_COLUMN";									
								}
								
								String value = grpName;
								
								elemval.setElementRef(elementRef);
								elemval.setValue(value);
								
								elemParam.getElementValue().add(elemval);
							}
						}
					//}
				}
				else if(names[i].equalsIgnoreCase("operations"))
				{

					boolean isAggExptMapMade = false;
					boolean hasAggExp = false;
					
					for(int k=0;k<agg.colDTOList.size();k++)
					{
						String dscol = "dscol";
						
						ColumnDTO aggCols = agg.colDTOList.get(k);
						if(aggCols.isBeingUsed == 89)
						{
							String colsName = aggCols.colStruct.name;
							String cols = colsName.substring(colsName.indexOf(".")+1, colsName.length());
							
							String ruleName = aggCols.transRule.transRuleName;
							
							if(ruleName.equalsIgnoreCase("Aggregator - Expression")) {
								dscol += k;
								colToDsColMap.put(cols, dscol);
								String aggexp = aggCols.transRule.paramValuesList.get(0);
								aggexp = aggexp.substring(aggexp.indexOf('('),aggexp.lastIndexOf(')'));
								aggexp = FunctionTypeConv.getCurrentFunctions(aggexp, ToolTypeConstants.DS_TOOL, ToolTypeConstants.TALEND);
								dsColToAggExpMap.put(dscol, aggexp);
							}
						}
					}
					
					for(int k=0;k<agg.colDTOList.size();k++)
					{
						ColumnDTO aggCols = agg.colDTOList.get(k);
						if(aggCols.isBeingUsed == 89)
						{
							String colsName = aggCols.colStruct.name;
							String cols = colsName.substring(colsName.indexOf(".")+1, colsName.length());
							
							String ruleName = aggCols.transRule.transRuleName;
							String srccols = "";
							
							if(colToDsColMap.containsKey(cols)) {
								srccols = colToDsColMap.get(cols);
							}
							else {
								String srcColName = aggCols.transRule.srcColNamesList.get(0);
								srccols = srcColName.substring(srcColName.indexOf(".")+1, srcColName.length());
							}
							
							String transRule = "";
							if(aggCols.transRule.transRuleName.equalsIgnoreCase("Aggregate - Sum"))
							{
								transRule = "sum";
							}
							else if(aggCols.transRule.transRuleName.equalsIgnoreCase("Aggregate - Count"))
							{
								transRule = "count";
							}
							else if(aggCols.transRule.transRuleName.equalsIgnoreCase("Aggregate - First"))
							{
								transRule = "first";
							}
							else if(aggCols.transRule.transRuleName.equalsIgnoreCase("Aggregate - Last"))
							{
								transRule = "last";
							}
							else if(aggCols.transRule.transRuleName.equalsIgnoreCase("Aggregate - Average"))
							{
								transRule = "avg";
							}
							else if(aggCols.transRule.transRuleName.equalsIgnoreCase("Aggregate - Maximum"))
							{
								transRule = "max";
							}
							else if(aggCols.transRule.transRuleName.equalsIgnoreCase("Aggregate - Minimum"))
							{
								transRule = "min";
							}
							else if(aggCols.transRule.transRuleName.equalsIgnoreCase("Aggregator - Expression")) {
								hasAggExp = true;
								transRule = "max";
							}
							
							List<ElementParameter.ElementValue> elemValuelist = new ArrayList<ElementParameter.ElementValue>();
							elemValuelist.add(Utils.getPropElemforTrans("OUTPUT_COLUMN",cols));
							elemValuelist.add(Utils.getPropElemforTrans("FUNCTION",transRule));
							elemValuelist.add(Utils.getPropElemforTrans("INPUT_COLUMN",srccols));
							elemValuelist.add(Utils.getPropElemforTrans("IGNORE_NULL","false"));
							elemParam.getElementValue().addAll(elemValuelist);
						}
					}
					
					if(hasAggExp == true && isAggExptMapMade == false ) {
						createAggExpTMap(pipeline,agg,recordperPipe,dsColToAggExpMap,reportObj);
						isAggExptMapMade = true;
					}
				}
				
			}
		}
			
			compNode.getElementParameter().add(elemParam);
		}
		
		
		return dsColToAggExpMap;
   }

	public static void createAggExpTMap(TalendfileProcessType pipeline, AggInfo agg, TransRecExtDTO recordperPipe, HashMap<String, String> dsColToAggExpMap, ReportLogInfo reportObj) {
	
		 TalendfileProcessType.Node compNode = new TalendfileProcessType.Node();
		  String Exptranstype = "tMap";
		  Double compVersion = 2.1;
		  String compName = "aggexp_"+recordperPipe.compName;
		  
		  reportObj.transTypeList.put(compName, "Transformer Component(tMap)");
		  ArrayList<String> manualChangesList = new ArrayList<String>();
		  
		  String xPos = recordperPipe.xLevelPos;
		  int xPos1 = Integer.parseInt(xPos) - 100;
		  String xpos2 = Integer.toString(xPos1);
		  String yPos = recordperPipe.yLevelPos;
		  List<String> prevComp = Utils.getPrevCompName(recordperPipe);
		  String prevcompName = prevComp.toString().replace("[", "").replace("]", "");
		  String prevCompName =  prevcompName.substring(0,prevcompName.indexOf("/"));
		  String prevCompNametrans = prevcompName.substring(prevcompName.indexOf("/")+1 , prevcompName.length());
		  Utils.getcompDetails(compNode,Exptranstype, compVersion,xpos2,yPos);
			ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
			elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
			elemParam.add(Utils.getPropDtlsforExp("EXTERNAL","MAP",""));
			elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","LINK_STYLE","AUTO"));
			elemParam.add(Utils.getPropDtlsforExp("DIRECTORY","TEMPORARY_DATA_DIRECTORY",""));
			elemParam.add(Utils.getPropDtlsforExp("IMAGE","PREVIEW","_CL1KkC-QEemZ8oa6e99oEg-"+compName+"-PREVIEW.bmp"));
			elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","DIE_ON_ERROR","true","false"));
			elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","LKUP_PARALLELIZE","false","false"));
			elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","ENABLE_AUTO_CONVERT_TYPE","false","false"));
			elemParam.add(Utils.getPropDtlsforExp("TEXT","ROWS_BUFFER_SIZE","2000000"));
			elemParam.add(Utils.getPropDtlsforExp("CHECK","CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL","false"));
			elemParam.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
			compNode.getElementParameter().addAll(elemParam);
			
		  Node.NodeData ndData = new Node.NodeData();
		  ndData.setXsiType("TalendMapper:MapperData");
		  Node.NodeData.UIProperties ndUI = new Node.NodeData.UIProperties();
		  ndUI.setshellMaximized("true");
		  ndData.setUIProperties(ndUI);
		  Node.NodeData.VarTables ndVars = new Node.NodeData.VarTables();
		  ndVars.setname("Var");
		  ndVars.setminimized("true");
		  ndVars.setsizeState("INTERMEDIATE");
		  ndData.setVarTables(ndVars);
		  Node.NodeData.OutPutTables outTbl = null;
		  outTbl = new Node.NodeData.OutPutTables();
		  outTbl.setsizeState("INTERMEDIATE");
		  outTbl.setname("rw_"+compName);
		  Node.Metadata mtData = new Node.Metadata();
		  mtData.setConnector("FLOW");
		  mtData.setName("rw_"+compName);
		  mtData.setLabel("rw_"+compName);
		  
		  for(int j = 0 ; j < recordperPipe.inRecsList.size() ; j++)
		  {
			  InRecDTO inrec = recordperPipe.inRecsList.get(j);
			  Node.NodeData.InPutTables inTbl = new Node.NodeData.InPutTables();
			  inTbl.setsizeState("INTERMEDIATE");
			  inTbl.setlookupMode("LOAD_ONCE");
			  inTbl.setmatchingMode("UNIQUE_MATCH");
			  inTbl.setname("rw_"+prevCompName);
			  for( int j1 = 0 ; j1 < inrec.prevOutRec.columnList.size(); j1++)
			  {
				  Node.NodeData.InPutTables.MapperTableEntries inmapTbl = new Node.NodeData.InPutTables.MapperTableEntries();
				  Node.NodeData.OutPutTables.MapperTableEntries outmapTbl = new Node.NodeData.OutPutTables.MapperTableEntries();
				  Metadata.Column colm = new Metadata.Column();
				  
				  ColumnDTO colsList = inrec.prevOutRec.columnList.get(j1);
				  String colsName = colsList.colStruct.name;
				  String dataType = colsList.colStruct.dataType;
				  String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				  inmapTbl.setName(colName);
				  outmapTbl.setName(colName);
				  colm.setName(colName);
				  
				  inmapTbl.setType(dataType);
				  outmapTbl.setType(dataType);
				  colm.setType(dataType);
				  colm.setComment("");
				  colm.setKey("false");
				  if(colsList.colStruct.length==0){
					 colm.setLength(colsList.colStruct.precision);
					 colm.setPrecision(colsList.colStruct.Scale);
				  }
				  else{
					 colm.setLength(colsList.colStruct.length);
					 colm.setPrecision(colsList.colStruct.Scale);
				  }
				  
				  boolean isNullable = colsList.colStruct.isNullable;
				  String nullStatus = String.valueOf(isNullable);
				  
				  inmapTbl.setNullable(nullStatus);
				  outmapTbl.setNullable(nullStatus);
				  colm.setNullable(nullStatus);
				  colm.setPattern("");
				  colm.setUsefulColumn("true");
				  
				  outmapTbl.setexpression(inTbl.getname()+"."+colName);
				  outTbl.getMapperTableEntries().add(outmapTbl);
				  mtData.getColumn().add(colm);
				  
				  inTbl.getMapperTableEntries().add(inmapTbl);
			  }
			  ndData.getInPutTables().add(inTbl);
		  }
		  
		  for (HashMap.Entry entry : dsColToAggExpMap.entrySet()) {
			  Node.NodeData.OutPutTables.MapperTableEntries outmapTbl = new Node.NodeData.OutPutTables.MapperTableEntries();
			  Metadata.Column colm = new Metadata.Column();
			  String colName = (String) entry.getKey();
			  String value = (String) entry.getValue();
			  value = value.replaceAll("MappingVariable", "context");
			  outmapTbl.setName(colName);
			  colm.setName(colName);
			  outmapTbl.setType("id_Integer");
			  colm.setType("id_Integer");
			  colm.setComment("");
			  colm.setKey("false");
			  colm.setLength(10);
			  colm.setPrecision(5);
			  outmapTbl.setNullable("true");
			  colm.setNullable("true");
			  colm.setPattern("");
			  colm.setUsefulColumn("true");
			  outmapTbl.setexpression(value);
			  
			  String xp = (String) entry.getValue();
			  if(xp.contains("ds") || xp.contains("dsError")) {
				  manualChangesList.add(colName+":"+"require manual handling in case of nested expressions ==> " + "\""+xp+"\"");
			  }
			  
			  outTbl.getMapperTableEntries().add(outmapTbl);
			  mtData.getColumn().add(colm);
		  }
		  
		  ndData.getOutPutTables().add(outTbl);
		  compNode.getMetadata().add(mtData);
		  
		  compNode.setNodeData(ndData);
		  pipeline.getNode().add(compNode);
		  
		  reportObj.manualChanges.put(compName, manualChangesList);
		  
		  	TalendfileProcessType.Connection conn = new TalendfileProcessType.Connection();
			conn.setconnectorName("FLOW");
			conn.setlabel(outTbl.getname());
			conn.setsource(compName);
			conn.settarget(recordperPipe.compName);
			conn.setlineStyle("0"); 
			conn.setmetaname(compName);
			conn.setoffsetLabelX("0");
			conn.setoffsetLabelY("0");
			TalendfileProcessType.Connection.ElementParameter elem = new TalendfileProcessType.Connection.ElementParameter();
			elem.setName("UNIQUE_NAME");
			elem.setfield("TEXT");
			elem.setshow("false");
			elem.setvalue(outTbl.getname());
			TalendfileProcessType.Connection.ElementParameter elem1 = new TalendfileProcessType.Connection.ElementParameter();
			elem1.setName("MONITOR_CONNECTION");
			elem1.setfield("CHECK");
			elem1.setvalue("false");
			conn.getElementParameter().add(elem1);
			conn.getElementParameter().add(elem);
			pipeline.getConnection().add(conn);
	
	}

	public static void fillColsforAgg(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb, TalendfileProcessType pipeline, ReportLogInfo reportObj)
	{
		String xPos = recordperPipe.xLevelPos;
		String yPos = recordperPipe.yLevelPos;
		Utils.getcompDetails(compNode,"tAggregateRow", 0.102,xPos,yPos);
		
		HashMap<String,String> dsColToAggExpMap = createAggregator.fillPropsforAgg(recordperPipe, docspecs, compNode, srctgtdb, pipeline, reportObj);
		
		TalendfileProcessType.Node.Metadata metadata = new TalendfileProcessType.Node.Metadata();
		metadata.setConnector("FLOW");
		metadata.setLabel("metadata");
		metadata.setName(recordperPipe.compName);
		
		ArrayList<String> metadataColsList = new ArrayList<String>();
		
		for (int i = 0; i < recordperPipe.outRecsList.size(); i++){
			
			OutRecDTO outRec =  recordperPipe.outRecsList.get(i);
			
			for (int j = 0; j < outRec.columnList.size(); j++){
				
				ColumnDTO colsDetails = outRec.columnList.get(j);
				String ColsName = colsDetails.colStruct.name;
				String name = ColsName.substring(ColsName.indexOf(".")+1, ColsName.length());
				String key = "false";
				int length = colsDetails.colStruct.length;
				String pattern = "";
				int precision = colsDetails.colStruct.precision;
				String usefulColumn = "true";
				String dataType = colsDetails.colStruct.dataType;
				TalendfileProcessType.Node.Metadata.Column column = new TalendfileProcessType.Node.Metadata.Column();
				column.setKey(key);
				
				 if(colsDetails.colStruct.length==0){
					 column.setLength(colsDetails.colStruct.precision);
					 column.setPrecision(colsDetails.colStruct.Scale);
				}
				else{
					column.setLength(colsDetails.colStruct.length);
					column.setPrecision(colsDetails.colStruct.Scale);
				}
				 
				column.setName(name);
				metadataColsList.add(name);
				
				String nullable = Boolean.toString(colsDetails.colStruct.isNullable);
				if(colsDetails.colStruct.description != null)
				{
					String comment = colsDetails.colStruct.description;
					column.setComment(comment);
				}
				else
				{
					column.setComment("");
				}
				column.setNullable(nullable);
				column.setPattern(pattern);
				column.setType(dataType);
				column.setUsefulColumn(usefulColumn);
				metadata.getColumn().add(column);
				
			}
			
			AggInfo agg = (AggInfo) recordperPipe.transInfo;
			ColumnDTO cols= agg.colDTOList.get(0);
			
			for(int j=0 ; j<cols.transRule.grpByColumns.size(); j++) {
				
				if(cols.transRule.grpByColumns.get(j)==null) {
					continue;
				}
				
				ColumnDTO colsDetails = cols.transRule.grpByColumns.get(j);
				String ColsName = colsDetails.colStruct.name;
				String name = ColsName.substring(ColsName.indexOf(".")+1, ColsName.length());
				
				if(!metadataColsList.contains(name)) {
					String key = "false";
					int length = colsDetails.colStruct.length;
					String pattern = "";
					int precision = colsDetails.colStruct.precision;
					String usefulColumn = "true";
					String dataType = colsDetails.colStruct.dataType;
					TalendfileProcessType.Node.Metadata.Column column = new TalendfileProcessType.Node.Metadata.Column();
					column.setKey(key);
					
					 if(colsDetails.colStruct.length==0){
						 column.setLength(colsDetails.colStruct.precision);
						 column.setPrecision(colsDetails.colStruct.Scale);
					}
					else{
						column.setLength(colsDetails.colStruct.length);
						column.setPrecision(colsDetails.colStruct.Scale);
					}
					 
					column.setName(name);
					metadataColsList.add(name);
					
					String nullable = Boolean.toString(colsDetails.colStruct.isNullable);
					if(colsDetails.colStruct.description != null)
					{
						String comment = colsDetails.colStruct.description;
						column.setComment(comment);
					}
					else
					{
						column.setComment("");
					}
					column.setNullable(nullable);
					column.setPattern(pattern);
					column.setType(dataType);
					column.setUsefulColumn(usefulColumn);
					metadata.getColumn().add(column);
				}
				
			}
			
			
			for (HashMap.Entry entry : dsColToAggExpMap.entrySet()) {
				  Metadata.Column colm = new Metadata.Column();
				  String colName = (String) entry.getKey();
				  colm.setName(colName);
				  colm.setType("id_Integer");
				  colm.setComment("");
				  colm.setKey("false");
				  colm.setLength(10);
				  colm.setPrecision(5);
				  colm.setNullable("true");
				  colm.setPattern("");
				  colm.setUsefulColumn("true");
				  metadata.getColumn().add(colm);
			  }
			
			
			compNode.getMetadata().add(metadata);
		}
		
	}

	
}