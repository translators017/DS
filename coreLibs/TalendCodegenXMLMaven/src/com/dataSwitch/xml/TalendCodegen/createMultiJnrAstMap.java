
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.HashMap;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.DetailElementDTO;
import com.dataSwitch.xml.ETLMapDTO.InRecDTO;
import com.dataSwitch.xml.ETLMapDTO.JoinerInfo;
import com.dataSwitch.xml.ETLMapDTO.MultiJoinerInfo;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata;
import com.dataSwitch.xml.connectorspecs.Connector;

public class createMultiJnrAstMap {

	public static void fillMultiJoinerAstMapTrans(TransRecExtDTO recordperPipe, Connector docspecs, Node compNode,
			SrcTgtInfo srctgtdb) {
		
		
		String Exptranstype = "tMap";
		  Double compVersion = 2.1;
		  String compName = recordperPipe.compName;
		  
		  if(compName.equalsIgnoreCase("lkp_whse")){
			  String debug ="";
		  }
		  
		  String xPos = recordperPipe.xLevelPos;
		  String yPos = recordperPipe.yLevelPos;
		  Utils.getcompDetails(compNode,Exptranstype, compVersion,xPos,yPos);
		  Utils.fillPropforExp_tMap(recordperPipe,docspecs,compNode,srctgtdb);
		  MultiJoinerInfo jnr = (MultiJoinerInfo) recordperPipe.transInfo;
		  String mstrcompName = Utils.getMstrCompForMultiJnr(jnr,compName);
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
		  for(int j=0; j<jnr.detailElements.size(); j++) {
			  DetailElementDTO dtlElem = jnr.detailElements.get(j);
			  if(dtlElem.detailCols.size()==dtlElem.masterCols.size())
			  {
			  for(int k = 0; k<dtlElem.detailCols.size(); k++) {
				  ColumnDTO dtlCol = dtlElem.detailCols.get(k);
				  String dtlColName = dtlCol.aliasName;
				  String dtlColName2 = dtlCol.colStruct.name.substring(dtlCol.colStruct.name.indexOf('.')+1);
				  ColumnDTO mstrCol = dtlElem.masterCols.get(k);
				  //String mstrColName = mstrCol.aliasName;
				  String mstrColName = mstrCol.colStruct.name.substring(mstrCol.colStruct.name.indexOf('.')+1);
				  keyColsMap.put(dtlColName, mstrColName);
				  keyColsMap.put(dtlColName2, mstrColName);
			  }
			  }
			  else
			  {
				  for(int k = 0; k<dtlElem.detailCols.size(); k++) {
					  ColumnDTO dtlCol = dtlElem.detailCols.get(k);
					  String dtlColName = dtlCol.aliasName;
					  String dtlColName2 = dtlCol.colStruct.name.substring(dtlCol.colStruct.name.indexOf('.')+1);
					  String mstrColelem = dtlElem.masterElemList.get(k);
					  //String mstrColName = mstrCol.aliasName;
					  String mstrColelemName = mstrColelem.substring(mstrColelem.indexOf('.')+1);
					  keyColsMap.put(dtlColName, mstrColelemName);
					  keyColsMap.put(dtlColName2, mstrColelemName);
				  }
			  }
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
			  
			  if(i>0) {
				  outTbl.setname("rw_"+compName+i);
				  mtData.setName("rw_"+compName+i);
			  }
			  
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
				 String srcCols = cols.srcColumn.colStruct.name;
				 String srcColsName = srcCols.substring(srcCols.indexOf(".")+1, srcCols.length());
				 //String srcColsName = cols.srcColumn.aliasName;
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
		
				 if(cols.colStruct.dateFormat != null)
				   {
					   if(cols.colStruct.dateFormat.length() > 0)
					   {
						   colm.setPattern("\"" + cols.colStruct.dateFormat + "\"");
					   }
				   }
				  else
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
		  
		  
		  
		/*  ArrayList<String> masterInputColList = new ArrayList<String>();
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
					  //String colName = colsList.colStruct.name;
					  //colName = colName.substring(colName.indexOf('.')+1);
					  masterInputColList.add(colName);
				  } 
				  
			  }
		  }*/
		  
		  
		  HashMap<String,ArrayList<String>> inputColmMap = new HashMap<>();
		  
		  
		  
		  for(int j = 0 ; j < recordperPipe.inRecsList.size() ; j++)
		  {
			  
			  ArrayList<String> inputColList = new ArrayList<>();
			  
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
				/*
				 * if(jnr.joinType.equalsIgnoreCase("Inner Join") ||
				 * jnr.joinType.equalsIgnoreCase("Inner")) { inTbl.setinnerJoin("true"); }
				 *
				 */
				  if(jnr.detailElements.get(0).joinType.equalsIgnoreCase("Inner") || jnr.detailElements.get(0).joinType.equalsIgnoreCase("Inner Join") ) {
					  inTbl.setinnerJoin("true");
				  }
				  checkDtl = true;
			  }
			  inTbl.setname(prevoutRec);
			  
			  boolean isPrevCompDirectSource = false;
			  if(inrec.prevOutRec.parentTransRec.transType.equalsIgnoreCase("Source")) {
				  isPrevCompDirectSource = true;
			  }
			  
			  boolean oneCondSet = false;
			  
			  for( int j1 = 0 ; j1 < inrec.prevOutRec.columnList.size(); j1++)
			  {
				  Node.NodeData.InPutTables.MapperTableEntries mapTbl = new Node.NodeData.InPutTables.MapperTableEntries();
				  ColumnDTO colsList = inrec.prevOutRec.columnList.get(j1);
				  String colsName = colsList.colStruct.name;
				  String dataType = colsList.colStruct.dataType;
				  String colName = "";
				  if(isPrevCompDirectSource==true) {
					  colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				  }
				  else {
					  colName = colsList.aliasName;
				  }
				  //String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				  //String colName = colsList.aliasName;
				  mapTbl.setName(colName);
				  
				  inputColList.add(colName);
				  
				  mapTbl.setType(dataType);
				  if(checkDtl == true)
				  {
					  if(keyColsMap.containsKey(colName))
					  {
						  String mstrKeyCol = keyColsMap.get(colName);
						  DetailElementDTO dtlElm = jnr.detailElements.get(j-1);
						  String temp = dtlElm.masterCols.get(0).parentOutRec.name;
						  temp = temp.substring(0,temp.indexOf("Out"));
						  mstrcompName = "rw_"+temp;
						  //mapTbl.setexpression("rw_"+ mstrcompName+"."+mstrKeyCol);
						  
						  /*String tempMstrKeyCol = mstrKeyCol+"1";
						  if(!masterInputColList.contains(mstrKeyCol))
						  {
							  System.out.println(colName + ">>>>>>>>" + mstrKeyCol);
							  if(masterInputColList.contains(tempMstrKeyCol))
							  {
								  mstrKeyCol = "";
								  mstrKeyCol = tempMstrKeyCol;
							  }
						  }*/
						  //if(oneCondSet==false) {
							  mapTbl.setexpression(mstrcompName+"."+mstrKeyCol);
						  //}
						  oneCondSet= true;
						  mapTbl.setoperator("=");
					  }
				  }
				  String nullable = Boolean.toString(colsList.colStruct.isNullable);
				  mapTbl.setNullable(nullable);
				  inTbl.getMapperTableEntries().add(mapTbl);
			  }
			  
			  inputColmMap.put(prevoutRec, inputColList);
			  
			  ndData.getInPutTables().add(inTbl);
		  }
		  ndData.setVarTables(ndVars);
		  compNode.setNodeData(ndData);
	
		
	}

}