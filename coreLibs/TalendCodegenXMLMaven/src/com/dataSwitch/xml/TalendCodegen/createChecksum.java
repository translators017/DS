
package com.dataSwitch.xml.TalendCodegen;

import java.util.List;

import com.dataSwitch.xml.ETLMapDTO.ChecksumInfo;
import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.InRecDTO;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.SeqGenInfo;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.NodeData.VarTables;
import com.dataSwitch.xml.connectorspecs.Connector;

public class createChecksum {

	public static void fillCheckSumTrans(TransRecExtDTO recordperPipe, Connector docspecs, Node compNode,
			SrcTgtInfo srctgtdb) {
		
		String Exptranstype = "tMap";
		  Double compVersion = 2.1;
		  String compName = recordperPipe.compName;
		  List<String> prevComp = Utils.getPrevCompName(recordperPipe);
		  String xPos = recordperPipe.xLevelPos;
		  String yPos = recordperPipe.yLevelPos;
		  //String prevCompName = prevComp.toString().replace("[", "").replace("]", "");
		  String prevcompName = prevComp.toString().replace("[", "").replace("]", "");
		  String prevCompName =  prevcompName.substring(0,prevcompName.indexOf("/"));
		  String prevCompNametrans = prevcompName.substring(prevcompName.indexOf("/")+1 , prevcompName.length());
		  Utils.getcompDetails(compNode,Exptranstype, compVersion,xPos,yPos);
		  Utils.fillPropforExp_tMap(recordperPipe,docspecs,compNode,srctgtdb);
		  ChecksumInfo seqGen = (ChecksumInfo) recordperPipe.transInfo;
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
			  outTbl.setname("rw_"+compName);
			  Node.Metadata mtData = new Node.Metadata();
			  mtData.setConnector("FLOW");
			  mtData.setName("rw_"+compName);
			  mtData.setLabel("rw_"+compName);
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
				 colm.setLength(length);
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
				 colm.setPrecision(precision);
				 colm.setType(dataType);
				 colm.setUsefulColumn("true");
				 mtData.getColumn().add(colm);
				 
				 if(!srcCompName.equalsIgnoreCase(compName))
				 {
					 Node.NodeData.OutPutTables.MapperTableEntries mapTbl = new Node.NodeData.OutPutTables.MapperTableEntries ();
					 mapTbl.setName(colName);
					 mapTbl.setName(colName);
					 mapTbl.setType(dataType);
					 mapTbl.setNullable(nullable);
					 //mapTbl.setexpression("rw_"+prevCompName+"."+colName);
					 if(prevCompNametrans.equalsIgnoreCase("Filter Transformation") || prevCompNametrans.equalsIgnoreCase("Conditional Grouping"))
					 {
					   mapTbl.setexpression("Case_"+compName+"."+srcColsName);
					 }
					 else
					 {
	                    mapTbl.setexpression("rw_"+prevCompName+"."+srcColsName);
					 }
					 outTbl.getMapperTableEntries().add(mapTbl);
				 } 
			 }
			 
			  for(int i1 = 0 ; i1 < seqGen.colDTOList.size() ; i1++)
			  {
				  ColumnDTO SeqCols = seqGen.colDTOList.get(i1);
				  String dataType = SeqCols.colStruct.dataType;
				  String colsName = SeqCols.colStruct.name;
				  String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				  if(SeqCols.transRule.transRuleName.equalsIgnoreCase("Encoding - MD5 Checksum"))
				  {
					  String paramVal = SeqCols.transRule.paramValuesList.get(0);
					  VarTables.MapperTableEntries mapTbl =  new VarTables.MapperTableEntries();
					  mapTbl.setName("var_"+colName);
					  mapTbl.setType(dataType);
					  mapTbl.setexpression("DataMasking.createMD5(&quot;s1&quot;,"+paramVal+",1)");
					  Node.NodeData.OutPutTables.MapperTableEntries OutmapTbl = new Node.NodeData.OutPutTables.MapperTableEntries ();
					  OutmapTbl.setName(colName);
					  OutmapTbl.setType(dataType);
					  String nullable = Boolean.toString(SeqCols.colStruct.isNullable);
					  OutmapTbl.setNullable(nullable);
					  OutmapTbl.setexpression("Var."+"var_"+colName);
					  ndVars.getMapperTableEntries().add(mapTbl);
					  outTbl.getMapperTableEntries().add(OutmapTbl);
				  }
			  }
			 ndData.getOutPutTables().add(outTbl);
			 compNode.getMetadata().add(mtData);
		  }
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
				  Node.NodeData.InPutTables.MapperTableEntries mapTbl = new Node.NodeData.InPutTables.MapperTableEntries();
				  ColumnDTO colsList = inrec.prevOutRec.columnList.get(j1);
				  String colsName = colsList.colStruct.name;
				  String dataType = colsList.colStruct.dataType;
				  String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				  mapTbl.setName(colName);
				  mapTbl.setType(dataType);
				  String nullable = Boolean.toString(colsList.colStruct.isNullable);
				  mapTbl.setNullable(nullable);
				  inTbl.getMapperTableEntries().add(mapTbl);
			  }
			  ndData.getInPutTables().add(inTbl);
		  }
		  ndData.setVarTables(ndVars);
		  compNode.setNodeData(ndData);
	}

}