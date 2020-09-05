
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dataSwitch.xml.ETLMapDTO.ChecksumInfo;
import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.InRecDTO;
import com.dataSwitch.xml.ETLMapDTO.LookupInfo;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.NodeData.VarTables;
import com.dataSwitch.xml.connectorspecs.Connector;

public class createLkpAstMap 
{

	public static void fillLookupAstMapTrans(TransRecExtDTO recordperPipe, Connector docspecs, Node compNode,
			SrcTgtInfo srctgtdb) {
		
		String Exptranstype = "tMap";
		  Double compVersion = 2.1;
		  String compName = recordperPipe.compName;
		  
		  
		  String xPos = recordperPipe.xLevelPos;
		  String yPos = recordperPipe.yLevelPos;
		  Utils.getcompDetails(compNode,Exptranstype, compVersion,xPos,yPos);
		  Utils.fillPropforExp_tMap(recordperPipe,docspecs,compNode,srctgtdb);
		  LookupInfo lkp = (LookupInfo) recordperPipe.transInfo;
		  String mstrcompName = Utils.getMstrCompForLkp(lkp, compName);
		  Node.NodeData ndData = new Node.NodeData();
		  ndData.setXsiType("TalendMapper:MapperData");
		  Node.NodeData.UIProperties ndUI = new Node.NodeData.UIProperties();
		  ndUI.setshellMaximized("true");
		  ndData.setUIProperties(ndUI);
		  Node.NodeData.VarTables ndVars = new Node.NodeData.VarTables();
		  ndVars.setname("Var");
		  ndVars.setminimized("true");
		  ndVars.setsizeState("INTERMEDIATE");
		  
		  HashMap<String,String> keyColsMap = new  HashMap<String,String>();
		  for(int j = 0 ; j < lkp.masterCols.size(); j++)
		  {
			  	if(j >= lkp.lookUpCols.size())
			  		continue;
				ColumnDTO jnrMstrName1 = lkp.masterCols.get(j);
				String jnrMstrName = jnrMstrName1.colStruct.name;
				ColumnDTO jnrDtlsName1 = lkp.lookUpCols.get(j);
				String jnrDtlName = jnrDtlsName1.colStruct.name;
				String mstrColName = jnrMstrName.substring(jnrMstrName.indexOf(".")+1, jnrMstrName.length());	
	        	String dtlColName = jnrDtlName.substring(jnrDtlName.indexOf(".")+1, jnrDtlName.length());
				
//				String mstrColName = jnrMstrName1.aliasName;
//				String dtlColName = jnrDtlsName1.aliasName;
	        	
				keyColsMap.put(dtlColName, mstrColName);
		  }
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
			  
			 for(int j = 0 ; j < outRecs.columnList.size() ; j++)
			 {
				 Metadata.Column colm = new Metadata.Column();
				 ColumnDTO cols = outRecs.columnList.get(j);
				 String dataType = cols.colStruct.dataType;
				 int length = cols.colStruct.length;
				 int precision = cols.colStruct.precision;
				 String srcComp = cols.srcColumn.colStruct.name;
				 String srcCompName = srcComp.substring(0, srcComp.indexOf("."));
				 String colName = cols.aliasName;
				 //String srcCols = cols.srcColumn.colStruct.name;
				 //String srcColsName = srcCols.substring(srcCols.indexOf(".")+1, srcCols.length());
				 String srcColsName = cols.srcColumn.aliasName;
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
				 
				 String prevOutrec = cols.srcColumn.parentOutRec.name;
				 String parentTransType = cols.srcColumn.parentOutRec.parentTransRec.transType;
				 String suffixForMultipleInput = "";
				 if(prevOutrec.substring(prevOutrec.lastIndexOf("t")+1) != null){
					 suffixForMultipleInput = prevOutrec.substring(prevOutrec.lastIndexOf("t")+1);
				 }
				 String prevOutrecName = prevOutrec.substring(0, prevOutrec.indexOf("Out"));
				 Node.NodeData.OutPutTables.MapperTableEntries mapTbl = new Node.NodeData.OutPutTables.MapperTableEntries ();
				 mapTbl.setName(colName);
				 mapTbl.setType(dataType);
				 mapTbl.setNullable(nullable); 
				 if(parentTransType.equalsIgnoreCase("Filter Transformation") || parentTransType.equalsIgnoreCase("Conditional Grouping"))
				 {
				   mapTbl.setexpression("Case_"+compName+"."+srcColsName);
				 }
				 else
				 {
                    mapTbl.setexpression("rw_"+prevOutrecName+suffixForMultipleInput+"."+srcColsName);
				 }
				outTbl.getMapperTableEntries().add(mapTbl);
				 
			 }
			 
			 ndData.getOutPutTables().add(outTbl);
			 compNode.getMetadata().add(mtData);
		  }
		  
		  ArrayList<String> masterInputColList = new ArrayList<String>();
		  for(int j = 0 ; j < recordperPipe.inRecsList.size() ; j++)
		  {
			  InRecDTO inrec = recordperPipe.inRecsList.get(j);
			  
			  for( int j1 = 0 ; j1 < inrec.prevOutRec.columnList.size(); j1++)
			  {
				  ColumnDTO colsList = inrec.prevOutRec.columnList.get(j1);
				  String prevoutRec = inrec.prevOutRec.name.substring(0, inrec.prevOutRec.name.indexOf("Out"));
				  if(inrec.prevOutRec.parentTransRec.transType.equalsIgnoreCase("Conditional Grouping"))
				  {
					  prevoutRec = "Case_"+compName;
				  }
				  else
				  {
					  prevoutRec = "rw_"+prevoutRec;
				  }
				  if(prevoutRec.equalsIgnoreCase(mstrcompName))
				  {
					  String colName = colsList.aliasName;
					  masterInputColList.add(colName);
				  } 
				  
			  }
		  }
		  
		  
		  for(int j = 0 ; j < recordperPipe.inRecsList.size() ; j++)
		  {
			  InRecDTO inrec = recordperPipe.inRecsList.get(j);
			  Node.NodeData.InPutTables inTbl = new Node.NodeData.InPutTables();
			  inTbl.setsizeState("INTERMEDIATE");
			  inTbl.setlookupMode("LOAD_ONCE");
			  inTbl.setmatchingMode("UNIQUE_MATCH");
			  String prevoutRec = inrec.prevOutRec.name.substring(0, inrec.prevOutRec.name.indexOf("Out"));
			  
			  if(inrec.prevOutRec.parentTransRec.transType.equalsIgnoreCase("Conditional Grouping") || inrec.prevOutRec.parentTransRec.transType.equalsIgnoreCase("Filter Transformation"))
			  {
				  prevoutRec = "Case_"+compName;
			  }
			  else
			  {
				  prevoutRec = "rw_"+prevoutRec;
			  }
			  boolean checkDtl = false;
			  if(!prevoutRec.equalsIgnoreCase(mstrcompName))
			  {
				  
				  inTbl.setactivateCondensedTool("true");
				  inTbl.setinnerJoin("true");
				  checkDtl = true;
			  }
			  inTbl.setname(prevoutRec);
			  
			  
			  for( int j1 = 0 ; j1 < inrec.prevOutRec.columnList.size(); j1++)
			  {
				  Node.NodeData.InPutTables.MapperTableEntries mapTbl = new Node.NodeData.InPutTables.MapperTableEntries();
				  ColumnDTO colsList = inrec.prevOutRec.columnList.get(j1);
				  String colsName = colsList.colStruct.name;
				  String dataType = colsList.colStruct.dataType;
				  String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				  //String colName = colsList.aliasName;
				  mapTbl.setName(colName);
				  mapTbl.setType(dataType);
				  if(checkDtl == true)
				  {
					  if(keyColsMap.containsKey(colName))
					  {
						  String mstrKeyCol = keyColsMap.get(colName);
						  //mapTbl.setexpression("rw_"+ mstrcompName+"."+mstrKeyCol);
						  
						  String tempMstrKeyCol = mstrKeyCol+"1";
						  if(!masterInputColList.contains(mstrKeyCol))
						  {
							  if(masterInputColList.contains(tempMstrKeyCol))
							  {
								  mstrKeyCol = "";
								  mstrKeyCol = tempMstrKeyCol;
							  }
						  }
						  
						  
						  mapTbl.setexpression(mstrcompName+"."+mstrKeyCol);
						  mapTbl.setoperator("=");
					  }
				  }
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