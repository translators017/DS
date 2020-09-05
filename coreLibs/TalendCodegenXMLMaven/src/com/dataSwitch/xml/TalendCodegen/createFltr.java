
package com.dataSwitch.xml.TalendCodegen;

import java.util.List;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.FilterInfo;
import com.dataSwitch.xml.ETLMapDTO.InRecDTO;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata;
import com.dataSwitch.xml.connectorspecs.Connector;

public class createFltr {
	
	public static void fillFltrTrans(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb,String szoutputLinkedServiceFilePath,boolean checkCDChasInsrtDelete)
	{
		  String dateFlag = "false";
		  String Rtrtranstype = "tMap";
		  Double compVersion = 2.1;
		  String compName = recordperPipe.compName;
		  List<String> prevComp = Utils.getPrevCompName(recordperPipe);
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
		  FilterInfo rtr = (FilterInfo)recordperPipe.transInfo;
		  List<String> nextComp = Utils.getNextCompName(recordperPipe,checkCDChasInsrtDelete,srctgtdb);
		  Node.NodeData.OutPutTables outTbl = null;
		  
		  String suffForCond = recordperPipe.inRecsList.get(0).name;
		  char c = suffForCond.charAt(suffForCond.length()-1);
		  suffForCond="";
		  if(Character.isDigit(c)){
			  suffForCond = Character.toString(c);
		  }
		  
		  for(int i = 0 ; i < recordperPipe.outRecsList.size() ; i++)
		  {
			  OutRecDTO outRecs = recordperPipe.outRecsList.get(i);
			  
			  if(outRecs.columnList.size()<=0 && outRecs.nextInRec.parentTransRec.inRecsList.size()>1)
					 continue;
			  
			  String condcompName = nextComp.get(i);
			  if(condcompName.contains("/Reject/"))
			  {
				  condcompName = condcompName.substring(0, condcompName.indexOf("/Reject/"));
			  }
			  outTbl = new Node.NodeData.OutPutTables();
			  outTbl.setsizeState("INTERMEDIATE");
			  outTbl.setname("Case_"+condcompName);
			  String condName = rtr.filterCond;
			  String condition = condName.substring(condName.indexOf(".")+1, condName.length());
			  
			  condition = condition.replaceAll(" (?i)AND ", " && ");
			  
			  condition = condition.replaceAll(" (?i)OR ", " || ");
			  
			  condition = condition.replace("=", "==").replaceAll(" = "," == ").replaceAll("MappingVariable", "Context");
			  
			 
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
			  
			  
			  //outTbl.setexpressionFilter("rw_"+prevCompName+"."+condition);
			  outTbl.setactivateExpressionFilter("true");
			  Node.Metadata mtData = new Node.Metadata();
			  mtData.setConnector("FLOW");
			  mtData.setName("Case_"+condcompName);
			  
			  if(outRecs.columnList.size()==0 && outRecs.nextInRec.parentTransRec.inRecsList.size()==1)
			  {
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
				  mapTbl.setexpression("dummy value");
				  outTbl.getMapperTableEntries().add(mapTbl);
			  }
		      else{  
					 for(int j = 0 ; j < outRecs.columnList.size() ; j++)
					 {
						 Metadata.Column colm = new Metadata.Column();
						 ColumnDTO cols = outRecs.columnList.get(j);
						 String ColsName = cols.colStruct.name;
						 String srcCols = cols.srcColumn.colStruct.name;
						 String dataType = cols.colStruct.dataType;
						 int length = cols.colStruct.length;
						 int precision = cols.colStruct.precision;
						 String colName = ColsName.substring(ColsName.indexOf(".")+1, ColsName.length());
						 String srcColsName = srcCols.substring(srcCols.indexOf(".")+1, srcCols.length());
						 String prevOutrec = cols.srcColumn.parentOutRec.name;
						 String prevOutrecName = prevOutrec.substring(0, prevOutrec.indexOf("Out"));
						 colm.setName(colName);
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
		//				 colm.setPrecision(precision);
						 colm.setType(dataType);
						 if(dataType!=null){
							 if(dataType.equalsIgnoreCase("id_Date") ){
								 dateFlag = "true";
							 }
						 }
						 
						 colm.setUsefulColumn("true");
						 mtData.getColumn().add(colm);
						 Node.NodeData.OutPutTables.MapperTableEntries mapTbl = new Node.NodeData.OutPutTables.MapperTableEntries ();
						 mapTbl.setName(colName);
						 mapTbl.setName(colName);
						 mapTbl.setType(dataType);
						 mapTbl.setNullable(nullable);
						 //mapTbl.setexpression("rw_"+prevCompName+"."+srcColsName);
						 if(prevCompNametrans.equalsIgnoreCase("Filter Transformation") || prevCompNametrans.equalsIgnoreCase("Conditional Grouping"))
						 {
						   mapTbl.setexpression("Case_"+compName+"."+srcColsName);
						 }
						 else
						 {
							 boolean suffix = true;
							 int suf = -1;
							 String tmpOutRecName = cols.srcColumn.parentOutRec.name;
							 try{
								 int check = Integer.parseInt(tmpOutRecName.substring(tmpOutRecName.length()-1,tmpOutRecName.length()));
								 suf = check;
							 }catch(Exception e){
								 suffix = false;
							 }		
							 if(suffix && suf>0){
								 prevOutrecName = prevOutrecName + Integer.toString(suf);
							 }
							 else{
								 
							 }
							  /*for(int i1=0;i1<recordperPipe.inRecsList.size();i1++){
								  if(recordperPipe.inRecsList.get(i1).prevOutRec.parentTransRec.transType.equalsIgnoreCase("Expression")){
									  if(recordperPipe.inRecsList.get(i1).prevOutRec.parentTransRec.outRecsList.size()>1){
										  TransRecExtDTO prevRecord = (TransRecExtDTO) recordperPipe.inRecsList.get(i1).prevOutRec.parentTransRec;
										  for(int j1 = 0; j1<prevRecord.outRecsList.size()  ;j1++){
											  if(prevRecord.outRecsList.get(j1).nextInRec.parentTransRec.compName.equalsIgnoreCase(recordperPipe.compName)){
												  String tmpOutRecName = prevRecord.outRecsList.get(j1).name;
												  try{
													 int check = Integer.parseInt(tmpOutRecName.substring(tmpOutRecName.length()-1,tmpOutRecName.length()));
													 suf = check;
												  }catch(Exception e){
													  suffix = false;
												  }
											  }
										  }
									  }
								  }  
							  }
							  if(suffix && suf>0){
								  
								  prevOutrecName = prevOutrecName + Integer.toString(suf);
							  }*/
							 
							 
							 mapTbl.setexpression("rw_"+prevOutrecName+"."+srcColsName);
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
		  compNode.setNodeData(ndData);
		  
		  if(dateFlag.equalsIgnoreCase("true")){
			  createReportLog.addToLog("==> tMap: Might require manual handling of Date Pattern in schema."+System.lineSeparator(),szoutputLinkedServiceFilePath);
		  }
	}

}