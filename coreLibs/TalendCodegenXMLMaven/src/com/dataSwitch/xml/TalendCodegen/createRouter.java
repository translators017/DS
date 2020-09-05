
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.ExprInfo;
import com.dataSwitch.xml.ETLMapDTO.InRecDTO;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.ReportLogInfo;
import com.dataSwitch.xml.ETLMapDTO.RouterInfo;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;

public class createRouter {
	
	public static void fillRouterTrans(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb, DataMapping dataMappingObj, List<String> cntxtParamsList, String szoutputLinkedServiceFilePath, LogHelper logHelperObj, ReportLogInfo reportObj, boolean checkCDChasInsrtDelete)
	{
		
	      boolean isMerged = false;
		  if(recordperPipe.mergerdTransRecs.size() > 0)
		  {
			  isMerged = true;
		  }
		  
		  
		  String Rtrtranstype = "tMap";
		  Double compVersion = 2.1;
		  List<String> prevComp = Utils.getPrevCompName(recordperPipe);
		  String compName = recordperPipe.compName;
		  
		  if(compName.equalsIgnoreCase("cpy_mdbyer_ref_data")){
			  String debug = "";
		  }
		  
		  reportObj.transTypeList.put(compName, "Router Component(tMap)");
		  ArrayList<String> manualChangesList = new ArrayList<String>();
		  
		  String xPos = recordperPipe.xLevelPos;
		  String yPos = recordperPipe.yLevelPos;
		  //String prevCompName = prevComp.toString().replace("[", "").replace("]", "");
		  String prevcompName = prevComp.toString().replace("[", "").replace("]", "");
		  String prevCompName =  prevcompName.substring(0,prevcompName.indexOf("/"));
		  String prevCompNametrans = prevcompName.substring(prevcompName.indexOf("/")+1 , prevcompName.length());
		  Utils.getcompDetails(compNode,Rtrtranstype, compVersion,xPos,yPos);
		  Utils.fillPropforExp_tMap(recordperPipe,docspecs,compNode,srctgtdb);
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
		  RouterInfo rtr = (RouterInfo)recordperPipe.transInfo;
		  List<String> nextComp = Utils.getNextCompName(recordperPipe,checkCDChasInsrtDelete,srctgtdb);
		  Node.NodeData.OutPutTables outTbl = null;
		  HashMap<String,String> colMap = null;
		  HashMap<String,String> varMap = null;
		  
		  String suffForCond = recordperPipe.inRecsList.get(0).name;
		  char c = suffForCond.charAt(suffForCond.length()-1);
		  suffForCond="";
		  if(Character.isDigit(c)){
			  suffForCond = Character.toString(c);
		  }
		  
		  if(isMerged == true)
		  {
			  
			 TransRecExtDTO expTrans  = (TransRecExtDTO) recordperPipe.mergerdTransRecs.get(0);
			 List<String> prevComp1 = Utils.getPrevCompName(expTrans);
			  //String prevCompName = prevComp.toString().replace("[", "").replace("]", "");
			  String prevcompName1 = prevComp1.toString().replace("[", "").replace("]", "");
			  String prevCompName1 =  prevcompName1.substring(0,prevcompName1.indexOf("/"));
			  String prevCompNametrans1 = prevcompName1.substring(prevcompName1.indexOf("/")+1 , prevcompName1.length());
			  ArrayList<HashMap<String,String>> mapList = MergedCompDtls.getMapForDerivedCols(expTrans,cntxtParamsList,dataMappingObj,szoutputLinkedServiceFilePath,logHelperObj,reportObj);
			  colMap = mapList.get(0);
			  varMap = mapList.get(1);
			  MergedCompDtls.setOutInVarTables(colMap,varMap,recordperPipe,nextComp,compNode,outTbl,rtr,
					 prevCompName1,suffForCond,prevCompNametrans1,compName,dataMappingObj,ndData,expTrans,ndVars);
		  }
		  else
		  {
			  for(int i = 0 ; i < recordperPipe.outRecsList.size() ; i++)
			  {
				  
				  OutRecDTO outRecs = recordperPipe.outRecsList.get(i);
				 /* if(outRecs.columnList.size() <=0  && outRecs.nextInRec.parentTransRec.inRecsList.size()>1)
						 continue;*/
				  String condcompName = nextComp.get(i);
				 
				  boolean rejectCheck = false;
				  if(condcompName.contains("/Reject/"))
				  {
					  condcompName = condcompName.substring(0, condcompName.indexOf("/Reject/"));
					  rejectCheck = true;
				  }
				  outTbl = new Node.NodeData.OutPutTables();
				  outTbl.setsizeState("INTERMEDIATE");
				  outTbl.setname("Case_"+condcompName);
				  //activateCondensedTool="true" columnNameFilter="" reject="true"
				  if(rejectCheck == true)
				  {
					  outTbl.setactivateCondensedTool("true");
					  outTbl.setcolumnNameFilter("");
					  outTbl.setreject("true");
				  }
				  String condName="";
				  String condition="";
	//			  if(rtr.routerCondList.size()<(i+1)){
	//				  condition = "true";
	//			  }
				  
				  
				  for(Entry<String, List<OutRecDTO>> entry: rtr.grpOutRecList.entrySet()){
					  List<OutRecDTO> outRecDTO = entry.getValue();
					  for(int i1 = 0;i1 < outRecDTO.size();i1++){
						  if(outRecDTO.get(i1).name.equalsIgnoreCase(outRecs.name)){
							  for(int i2 = 0;i2 < rtr.grpNamesList.size(); i2++){
								  if(rtr.grpNamesList.get(i2).equalsIgnoreCase(entry.getKey())){
									  if(rtr.routerCondList.get(i2)!=null){
										  condName = rtr.routerCondList.get(i2);
										  condition = condName.substring(condName.indexOf(".")+1, condName.length());
										  
										  condition = condition.replaceAll(" (?i)AND ", " && ");
				    						  
										  condition = condition.replaceAll(" (?i)OR ", " || ");
										  
										  condition = condition.replaceAll("=", "==").replaceAll(" = "," == ").replaceAll(" = "," == ").replaceAll("MappingVariable", "Context");;
										  
										  break;
									  }	
									  else{
										  condition = "true";
										  break;
									  }
								  }
							  }
						  }
					  }
				  }
				  
				  
	//			  if(rtr.routerCondList.get(i)!=null){
	//				  condName = rtr.routerCondList.get(i);
	//				  condition = condName.substring(condName.indexOf(".")+1, condName.length());
	//				  
	//				  condition = condition.replaceAll(" (?i)AND ", " && ");
	//				  
	//				  condition = condition.replaceAll(" (?i)OR ", " || ");
	//				  
	//				  condition = condition.replaceAll("=", "==").replaceAll(" = "," == ");
	//				  
	//			  }
	//			  else{
	//				  condition = "true";
	//			  }
				  
				  if(!(prevCompNametrans.equalsIgnoreCase("Conditional Grouping")||prevCompNametrans.equalsIgnoreCase("Filter Transformation")))
				  {
					  if(!(condition.equalsIgnoreCase("true") || condition.equalsIgnoreCase("false")))
					  {
					      outTbl.setexpressionFilter("rw_"+prevCompName+suffForCond+"."+condition);
					  }
					  else
					  {
						  outTbl.setexpressionFilter(condition);
					  }
				  }
				  else
				  {
					  if(!(condition.equalsIgnoreCase("true") || condition.equalsIgnoreCase("false")))
					  {
					      outTbl.setexpressionFilter("Case_"+compName+"."+condition);
					  }
					  else
					  {
						  outTbl.setexpressionFilter(condition);
					  }
				  }
				  outTbl.setactivateExpressionFilter("true");
				  Node.Metadata mtData = new Node.Metadata();
				  mtData.setConnector("FLOW");
				  mtData.setName("Case_"+condcompName);
				  
				  boolean checkRcp = Utils.getBooleanForRcp(dataMappingObj);
					if(checkRcp == true)
					{
						mtData.getColumn().add(Utils.createExtraColForRCP());
						outTbl.getMapperTableEntries().add(Utils.createOpMapTblEnteries(prevCompName,compName,prevCompNametrans));
					}
				  
				  if(outRecs.columnList.size()==0 && outRecs.nextInRec.parentTransRec.inRecsList.size()>=1)
				  {
					  
					  manualChangesList.add("dummy column : require manual handling in case of missing columns" );
					  reportObj.manualChanges.put(compName, manualChangesList);
					  
					  Metadata.Column colm = new Metadata.Column();
					  colm.setName("dummy_column");
					  colm.setComment("");
					  colm.setKey("false");
	
					  colm.setLength(20);
					  colm.setPrecision(0);
					  colm.setNullable("true");
					  colm.setPattern("");
					  colm.setType("id_String");
					  colm.setUsefulColumn("true");
					  mtData.getColumn().add(colm);
					  
					  Node.NodeData.OutPutTables.MapperTableEntries mapTbl = new Node.NodeData.OutPutTables.MapperTableEntries ();
					  mapTbl.setName("dummy_column");
					  mapTbl.setType("id_String");
					  mapTbl.setNullable("true");
					  mapTbl.setexpression("\"dummy value\"");
					  outTbl.getMapperTableEntries().add(mapTbl);
				  }
				  else
				  {
					 for(int j = 0 ; j < outRecs.columnList.size() ; j++)
					 {
						 Metadata.Column colm = new Metadata.Column();
						 ColumnDTO cols = outRecs.columnList.get(j);
						// String ColsName = cols.colStruct.name;
						 String colName = cols.aliasName;
						 String srcColsName = cols.srcColumn.colStruct.name;
						 String dataType = cols.colStruct.dataType;
						 int length = cols.colStruct.length;
						 int precision = cols.colStruct.precision;
						 //String colName = ColsName.substring(ColsName.indexOf(".")+1, ColsName.length());
						 //String srccolName = srcColsName.substring(srcColsName.indexOf(".")+1, srcColsName.length());
						 String srccolName = cols.srcColumn.aliasName;
						 String prevOutrec = cols.srcColumn.parentOutRec.name;
						 String prevOutrecName = prevOutrec.substring(0, prevOutrec.indexOf("Out"));
						 colm.setName(colName);
						 
						 boolean isNullable = cols.colStruct.isNullable;
						 String nullStatus = String.valueOf(isNullable);
						 String comment = cols.colStruct.description;
						 
						 if(comment!=null){
						 	 colm.setComment(comment);
						 }
						 else{
							 colm.setComment("");
						 }
						 
						 colm.setKey("false");
		
		//				 colm.setLength(length);
						 
						 if(cols.colStruct.length==0){
							 colm.setLength(cols.colStruct.precision);
							 colm.setPrecision(cols.colStruct.Scale);
						}
						else{
							colm.setLength(cols.colStruct.length);
							colm.setPrecision(cols.colStruct.Scale);
						}
						 
						 if(nullStatus!=null){
							 colm.setNullable(nullStatus);
						 }else{
							 colm.setNullable("true");
						 }
						 
						 colm.setPattern("");
		//				 colm.setPrecision(precision);
						 colm.setType(dataType);
						 colm.setUsefulColumn("true");
						 mtData.getColumn().add(colm);
						 Node.NodeData.OutPutTables.MapperTableEntries mapTbl = new Node.NodeData.OutPutTables.MapperTableEntries ();
						 mapTbl.setName(colName);
						 mapTbl.setName(colName);
						 mapTbl.setType(dataType);
						 mapTbl.setNullable(nullStatus);
						 
						 String suffixForMultipleInput = "";
						 if(prevOutrec.substring(prevOutrec.lastIndexOf("t")+1) != null){
							 suffixForMultipleInput = prevOutrec.substring(prevOutrec.lastIndexOf("t")+1);
						 }
						 
						 if(prevCompNametrans.equalsIgnoreCase("Filter Transformation") || prevCompNametrans.equalsIgnoreCase("Conditional Grouping"))
						 {
						   mapTbl.setexpression("Case_"+compName+"."+srccolName);
						 }
						 else
						 {
		                    //mapTbl.setexpression("rw_"+prevCompName+"."+srccolName);
							 mapTbl.setexpression("rw_"+prevOutrecName+suffixForMultipleInput+"."+srccolName);
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
			  
				     boolean checkRcp = Utils.getBooleanForRcp(dataMappingObj);
					 if(checkRcp == true)
					 {
						inTbl.getMapperTableEntries().add(Utils.createIpMapTblEnteries());
					 }
				  for( int j1 = 0 ; j1 < inrec.prevOutRec.columnList.size(); j1++)
				  {
					  Node.NodeData.InPutTables.MapperTableEntries mapTbl = new Node.NodeData.InPutTables.MapperTableEntries();
					  ColumnDTO colsList = inrec.prevOutRec.columnList.get(j1);
					  String colsName = colsList.colStruct.name;
					  String dataType = colsList.colStruct.dataType;
					  
					  boolean isNullable = colsList.colStruct.isNullable;
					  String nullStatus = String.valueOf(isNullable);
					 //String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
					  String colName = colsList.aliasName;
					  mapTbl.setName(colName);
					  mapTbl.setType(dataType);
					  mapTbl.setNullable(nullStatus);
					  inTbl.getMapperTableEntries().add(mapTbl);
				  }
				  ndData.getInPutTables().add(inTbl);
			  }
		  }
		  compNode.setNodeData(ndData);
	}
	

}