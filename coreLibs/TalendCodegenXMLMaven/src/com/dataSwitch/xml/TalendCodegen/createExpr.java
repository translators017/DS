
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.functionTypeRules.FnConverter;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.ExprInfo;
import com.dataSwitch.xml.ETLMapDTO.InRecDTO;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.ReportLogInfo;
//import com.dataSwitch.xml.ETLMapDTO.ReportLogInfo;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.NodeData.OutPutTables;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.NodeData.VarTables;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;

public class createExpr {
	
	public static void fillExpTrans(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb,String szoutputLinkedServiceFilePath, List<String> cntxtParamsList,DataMapping dataMappingObj, LogHelper logHelperObj, ReportLogInfo reportObj)
	{
		  
		  //createReportLog.addToLog("==> tMap/"+recordperPipe.compName+":::Might require manual handling of Date Pattern in schema."+System.lineSeparator(),szoutputLinkedServiceFilePath);
		  reportObj.transTypeList.put(recordperPipe.compName, "Transformer Component(tMap)");
		  
		  String Exptranstype = "tMap";
		  Double compVersion = 2.1;
		  String compName = recordperPipe.compName;
		  ArrayList<String> manualChangesList = new ArrayList<String>();
		  
		  if(compName.equalsIgnoreCase("xfm_fct_dm")){
			  String debug = "bjbj";
		  }
		  
		  String xPos = recordperPipe.xLevelPos;
		  String yPos = recordperPipe.yLevelPos;
		  List<String> prevComp = Utils.getPrevCompName(recordperPipe);
		  List<String> inputColsList = new ArrayList<String>();
		  String prevcompName = prevComp.toString().replace("[", "").replace("]", "");
		  String prevCompName =  prevcompName.substring(0,prevcompName.indexOf("/"));
		  String prevCompNametrans = prevcompName.substring(prevcompName.indexOf("/")+1 , prevcompName.length());
		  Utils.getcompDetails(compNode,Exptranstype, compVersion,xPos,yPos);
		  Utils.fillPropforExp_tMap(recordperPipe,docspecs,compNode,srctgtdb);
		  ExprInfo exp = (ExprInfo) recordperPipe.transInfo;
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
		  List <String> columnList = new ArrayList<String>();
		  List <String> columnList1 = new ArrayList<String>();
		  Node.NodeData.OutPutTables outTbl = null;
		  List<String> VarcolsList = new ArrayList<String>();
		  List<String> genExpVarsColsList = new ArrayList<String>();
		  
		  for(int i = 0 ; i < recordperPipe.outRecsList.size() ; i++)
		  {
			  OutRecDTO outRecs = recordperPipe.outRecsList.get(i);
			  if(outRecs.columnList.size()<=0)
					 continue;  
			 for(int j = 0 ; j < outRecs.columnList.size() ; j++)
			 {
				 ColumnDTO cols = outRecs.columnList.get(j);
				 //String ColsName = cols.colStruct.name;
				 //String colName = ColsName.substring(ColsName.indexOf(".")+1, ColsName.length());
				 String colName = cols.aliasName;
				 columnList1.add(colName);
			 }
		  }
		  
		  if(exp != null)
		  {  
			  for(int k = 0 ; k < exp.colDTOList.size(); k++)
			  {
				  ColumnDTO expCol =  exp.colDTOList.get(k);
				  
//				  String colsName = expCol.colStruct.name;
//				  String colName = colsName.substring(colsName.indexOf(".")+1,colsName.length());
				  String colName = expCol.aliasName;
                  if(!expCol.transRule.transRuleName.equalsIgnoreCase("General - Expression"))
                  {
                	  if(!columnList1.contains(colName))
            	      {
   						  VarcolsList.add(colName);  
                	  }	
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("General - Expression"))
                  {
                	  String paramVal = expCol.transRule.paramValuesList.get(0);
                	  List<String> SrcCols = Utils.getSrcColsListforParam(recordperPipe, expCol);
					  String SrcColName = SrcCols.toString().replace("[", "").replace("]", "");
					  List<String> SrcColsWithComp = Utils.getSrcColsList(recordperPipe, expCol);
					  String SrcColNameWComp = SrcColsWithComp.toString().replace("[", "").replace("]", "");
					  paramVal = stringManipulator(paramVal,SrcColName,SrcColNameWComp,inputColsList,prevCompNametrans,prevCompName,compName);
                	  if(!columnList1.contains(colName))
                	  {
						  VarcolsList.add(colName);
						  genExpVarsColsList.add(colName);
                	  }
                  }
                  
			  }
		  }
		   
		  for(int i = 0 ; i < recordperPipe.outRecsList.size() ; i++)
		  {
			  OutRecDTO outRecs = recordperPipe.outRecsList.get(i);
			  if(outRecs.columnList.size()<=0)
					 continue;
			 
			  outTbl = new Node.NodeData.OutPutTables();
			  outTbl.setsizeState("INTERMEDIATE");
			  Node.Metadata mtData = new Node.Metadata();
			  	boolean checkRcp = Utils.getBooleanForRcp(dataMappingObj);
				if(checkRcp == true)
				{
					mtData.getColumn().add(Utils.createExtraColForRCP());
					outTbl.getMapperTableEntries().add(Utils.createOpMapTblEnteries(prevCompName,compName,prevCompNametrans));
				}
				
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
				 //String ColsName = cols.colStruct.name;
				 String colName = cols.aliasName;
				 String dataType = cols.colStruct.dataType;
				 int length = cols.colStruct.length;
				 int precision = cols.colStruct.precision;
				 String srcComp = cols.srcColumn.colStruct.name;
				 String srcCompName = srcComp.substring(0, srcComp.indexOf("."));
				// String colName = ColsName.substring(ColsName.indexOf(".")+1, ColsName.length());
				 String srcCols = cols.srcColumn.colStruct.name;
				 //String srcColsName = srcCols.substring(srcCols.indexOf(".")+1, srcCols.length());
				 String srcColsName = cols.srcColumn.aliasName;
				 columnList.add(colName); 
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
				 
			
			   if(cols.colStruct.dateFormat != null)
			   {
				   if(cols.colStruct.dateFormat.length() > 0)
				   {
					   colm.setPattern("\"" + cols.colStruct.dateFormat + "\"");
				   }
			   }
			   else
				   colm.setPattern("");
			   
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
				
//				 colm.setPrecision(precision);
				 colm.setType(dataType);
				 colm.setUsefulColumn("true");
				 mtData.getColumn().add(colm);
				
				 if(!srcCompName.equalsIgnoreCase(compName))
				 {
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
					 //System.out.println("compname====>"+compName+">>>colname>>>"+colName+"<<<<<<<prevoutrc>>>>"+prevOutrecName);
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
				 else if(VarcolsList.contains(srcColsName))
				 {
					 Node.NodeData.OutPutTables.MapperTableEntries mapTbl = new Node.NodeData.OutPutTables.MapperTableEntries ();
					 mapTbl.setName(colName);
					 mapTbl.setType(dataType);
					 mapTbl.setNullable(nullable);
                     mapTbl.setexpression("Var.var_"+srcColsName);
					 outTbl.getMapperTableEntries().add(mapTbl);
				 } 
			 }
			 
			 getExpColDtls(exp,outTbl,compName,prevCompName ,prevCompNametrans,recordperPipe,columnList,szoutputLinkedServiceFilePath, 
                           cntxtParamsList,dataMappingObj,ndVars,inputColsList,genExpVarsColsList,logHelperObj,manualChangesList);
			 
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
				  String nullable = Boolean.toString(colsList.colStruct.isNullable);
				 // String colsName = colsList.colStruct.name;
				  String colName = colsList.aliasName;
				  String dataType = colsList.colStruct.dataType;
				 // String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				  inputColsList.add(colName);
				  mapTbl.setName(colName);
				  mapTbl.setType(dataType);
				  mapTbl.setNullable(nullable);
				  inTbl.getMapperTableEntries().add(mapTbl);
			  }
			  ndData.getInPutTables().add(inTbl);
		  }
		  
		  if(exp != null)
		  {  
			  for(int k = 0 ; k < exp.colDTOList.size(); k++)
			  {
				  ColumnDTO expCol =  exp.colDTOList.get(k);
				  
				  String origExp = expCol.transRule.originalExpression;
				  origExp = "/*Original Expression ==> "+ origExp + "*/";
				  
				  
				  //String colsName = expCol.colStruct.name;
				  String dataType = expCol.colStruct.dataType;
				  String colName = expCol.aliasName;
				 // String colName = colsName.substring(colsName.indexOf(".")+1,colsName.length());

                  if(expCol.transRule.transRuleName.equalsIgnoreCase("General - Expression"))
                  {
                	  
                	  String paramVal = expCol.transRule.paramValuesList.get(0);
                	  List<String> SrcCols = Utils.getSrcColsListforParam(recordperPipe, expCol);
					  String SrcColName = SrcCols.toString().replace("[", "").replace("]", "");
					  List<String> SrcColsWithComp = Utils.getSrcColsList(recordperPipe, expCol);
					  String SrcColNameWComp = SrcColsWithComp.toString().replace("[", "").replace("]", "");
					  paramVal = stringManipulator(paramVal,SrcColName,SrcColNameWComp,inputColsList,prevCompNametrans,prevCompName,compName);
					  paramVal = FunctionTypeConv.getCurrentFunctions(paramVal, ToolTypeConstants.DS_TOOL,ToolTypeConstants.TALEND );
					  
					  if(paramVal.contains("ds") || paramVal.contains("dsError_")){
      					 manualChangesList.add(colName+":"+"require manual handling in case of component variables ==> " + paramVal);
      				  }
					  
					  if(!columnList.contains(colName))  
                	  {
						  VarTables.MapperTableEntries varmapTbl =  new VarTables.MapperTableEntries();
						  varmapTbl.setName("var_"+colName);
						  //createReportLog.addToLog("==> tMap/"+compName+" (Component Variable) - "+colName+": Component/stage variable might require manual handling in case of nested expression.."+System.lineSeparator(), szoutputLinkedServiceFilePath);
						  varmapTbl.setType(dataType);
						  String nullable = Boolean.toString(expCol.colStruct.isNullable);
						  varmapTbl.setNullable(nullable);
						  if(cntxtParamsList.contains(paramVal)){
							  varmapTbl.setexpression("context."+paramVal);
						  }
						  else{
							  varmapTbl.setexpression(paramVal + origExp);
						  }
						  ndVars.getMapperTableEntries().add(varmapTbl); 
                	  }
                  }
                  
			  }
		  }
		  
		  compNode.setNodeData(ndData);
		  reportObj.manualChanges.put(compName, manualChangesList);
	}
	
	private static void getExpColDtls(ExprInfo exp, OutPutTables outTbl, String compName, String prevCompName ,String prevCompNametrans,TransRecExtDTO recordperPipe,List <String> columnList,String szoutputLinkedServiceFilePath, 
			                           List<String> cntxtParamsList,DataMapping dataMappingObj, Node.NodeData.VarTables ndVars,List<String> inputColsList, List<String> genExpVarsColsList, LogHelper logHelperObj, ArrayList<String> manualChangesList)
	  
	{
		  if(exp != null)
		  {  
			  for(int k = 0 ; k < exp.colDTOList.size(); k++)
			  {
				  ColumnDTO expCol =  exp.colDTOList.get(k);
				  
				  String origExp = expCol.transRule.originalExpression;
				  origExp = "/*Original Expression ==> "+ origExp + "*/";
				  
				 // String colsName = expCol.colStruct.name;
				  String dataType = expCol.colStruct.dataType;
				  //String colName = colsName.substring(colsName.indexOf(".")+1,colsName.length());
				  String colName = expCol.aliasName;
				  Node.NodeData.OutPutTables.MapperTableEntries mapTbl = new Node.NodeData.OutPutTables.MapperTableEntries ();
				  mapTbl.setName(colName);
				  mapTbl.setType(dataType);
				  String nullable = Boolean.toString(expCol.colStruct.isNullable);
				  mapTbl.setNullable(nullable);
				  String transRule = "";
				  
				  String srcColAdd = "";
				  for(int j = 0 ; j < expCol.transRule.srcColNamesList.size(); j++)
				  {
					String srcColName = expCol.transRule.srcColNamesList.get(j);
					String srcCol = srcColName.substring(srcColName.indexOf(".")+1,srcColName.length());
					if(prevCompNametrans.equalsIgnoreCase("Conditional Grouping")||prevCompNametrans.equalsIgnoreCase("Filter Transformation"))
					{
					   srcColAdd += "Case_"+compName+"."+srcCol + ",";
					}
					else
					{
					   srcColAdd += "rw_"+prevCompName+"."+srcCol + ",";
				    }
				  }
				  
				  if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Convert to Uppercase"))
                  {
                         transRule = "StringHandling.UPCASE";
                  }
				  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - String to Integer"))
				  {
					  transRule = "Integer.parseInt";
				  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Convert to Lowercase")){
                         transRule = "StringHandling.DOWNCASE";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Length")){
                         transRule = "StringHandling.LEN";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Trim Leading Spaces")){
                         transRule = "StringHandling.FTRIM";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Trim Trailing Spaces")){
                         transRule = "StringHandling.TRIM";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Trim Leading and Trailing Spaces")){
                         transRule = "StringHandling.TRIM";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Replace String")){
                         transRule = "StringHandling.EREPLACE";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Numeric - Absolute")){
                         transRule = "Mathematical.ABS";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Numeric - Ceil")){
                         transRule = "Math.ceil";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Numeric - Floor")){
                         transRule = "Math.floor";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Numeric - Round")){
                         transRule = "Math.round";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Date/Time - Add to Date")){
                      transRule = "TalendDate.addDate";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Date/Time - Difference between Dates")){
                      transRule = "TalendDate.compareDate";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Date/Time - Get Date/Time Part")){
                      transRule = "TalendDate.getCurrentDate";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Date/Time - Truncate")){
                      transRule = "TalendDate.getFirstDayOfMonth";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - String to Date")){
                      transRule = "TalendDate.formatDate";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - String to Integer")){
                      transRule = "Integer.parseInt";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - String to Decimal")){
                      transRule = "Numeric.convertImpliedDecimalFormat";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - String to Float")){
                      transRule = "Integer.valueOf";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Check - NULL Values")){
                      transRule = "Relational.ISNULL";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Audit / Generated - System Date")){
                      transRule = "TalendDate.getCurrentDate";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Audit / Generated - System Timestamp")){
                      transRule = "TalendDate.formatDateInUTC";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Left Padding")){
                      transRule = "StringHandling.LEFT";
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Right Padding")){
                      transRule = "StringHandling.RIGHT";
                  }
				  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Check - Date Values")){
                      transRule = "TalendDate.isDate";
                  } 
				  else if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Return ASCII Code")){
				      transRule = "DSUtils.getasciiCode";
                  }
 				  else if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Return ASCII Character")){
                      transRule = "DSUtils.getasciiCharacter";
                  }
 				  else if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Convert to InitCaps")){
                      transRule = "DSUtils.initCaps";
                  }
 				  else if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Search String")){
                      transRule = "DSUtils.searchString";
                  }
 				  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - Date to String")){
 					 transRule = "toString()";
 				  }
				  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - Numeric to String")){
	 					 transRule = "toString()";
				  }
				  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - Date/Time to String")){
	 					 transRule = "toString()";
				  }
				  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - Date/Time to Integer")){
	 					 transRule = "Integer.parseInt";
				  }
				  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Numeric - Truncate")){
	 					 transRule = "Math.round";
				  }
				  else if(expCol.transRule.transRuleName.equalsIgnoreCase("Date/Time - Round")){
	 					 transRule = "TalendDate.getFirstDayOfMonth";
				  }
				  
				  
                  if(expCol.transRule.srcColNamesList != null)
				  {
                	  if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Concatenation"))
    				  { 
						   List<String> SrcCols = Utils.getSrcColsList(recordperPipe, expCol);
						   String SrcColName = SrcCols.toString().replace("[", "").replace("]", "");
						   StringTokenizer tokenizer = new StringTokenizer(SrcColName, ",");
						   String SrcColumn = "";
						   while(tokenizer.hasMoreTokens())
						   {
							   String srcCol = tokenizer.nextToken();
							   String ExpCompCheck = srcCol.substring(0, srcCol.indexOf("."));
							   String ExpCol = srcCol.substring(srcCol.indexOf(".")+1, srcCol.length());
							   if(!ExpCompCheck.equalsIgnoreCase(compName))
							   {
								   srcCol = "rw_"+prevCompName+"."+ExpCol;
							   }
							   else
							   {
								   srcCol = ExpCol;
							   }
							   SrcColumn += srcCol+" + ";
						   }
						   SrcColumn = SrcColumn.substring(0, SrcColumn.length()-2);
						   mapTbl.setexpression(SrcColumn + origExp);
						   outTbl.getMapperTableEntries().add(mapTbl);
					 }
                	  else if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Substring"))
                	  {
                		  /*List<String> SrcCols = Utils.getSrcColsList(recordperPipe, expCol);
						  String srcCols = SrcCols.get(0);
						  String srcColName = srcCols.substring(srcCols.indexOf(".")+1, srcCols.length());
						  String paramVal1 = expCol.transRule.paramValuesList.get(0);
						  String paramVal2 = expCol.transRule.paramValuesList.get(1);
						  if(prevCompNametrans.equalsIgnoreCase("Filter Tansformation") || prevCompNametrans.equalsIgnoreCase("Conditional Grouping"))
						  {
							  transRule = "StringHandling.SUBSTR("+"Case_"+compName+"."+srcColName+","+paramVal1+","+paramVal2+")";
						  }
						  else
						  {
						     transRule = "StringHandling.SUBSTR("+"rw_"+prevCompName+"."+srcColName+","+paramVal1+","+paramVal2+")";
						  }*/
                		  List<String> SrcCols = Utils.getSrcColsList(recordperPipe, expCol);
						  String srcCols = SrcCols.get(0);
						  String srcColName = srcCols.substring(srcCols.indexOf(".")+1, srcCols.length());
						  
						  String paramVal1 = expCol.transRule.paramValuesList.get(1);
						  String paramVal2 = expCol.transRule.paramValuesList.get(2);						  
						  
						boolean suffix = true;
						  int suf = -1;
						  for(int i=0;i<recordperPipe.inRecsList.size();i++){
							  if(recordperPipe.inRecsList.get(i).prevOutRec.parentTransRec.outRecsList.size()>1){
								  TransRecExtDTO tmpRecord = (TransRecExtDTO) recordperPipe.inRecsList.get(i).prevOutRec.parentTransRec;
								  for(int j = 0; j<tmpRecord.outRecsList.size() && tmpRecord.outRecsList.size()>1 ;j++){
									  if(tmpRecord.outRecsList.get(j).nextInRec.parentTransRec.compName.equalsIgnoreCase(recordperPipe.compName)){
										  String tmpOutRecName = tmpRecord.outRecsList.get(j).name;
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
						  if(suffix && suf>0){
							  prevCompName = prevCompName + Integer.toString(suf);
						  }
						  if(prevCompNametrans.equalsIgnoreCase("Filter Transformation") || prevCompNametrans.equalsIgnoreCase("Conditional Grouping"))
						  {
							  transRule = "StringHandling.SUBSTR("+"Case_"+compName+"."+srcColName+","+paramVal1+","+paramVal2+")";
						  }
						  else
						  {
						     transRule = "StringHandling.SUBSTR("+"rw_"+prevCompName+"."+srcColName+","+paramVal1+","+paramVal2+")";
						  }
						  mapTbl.setexpression(transRule + origExp);
                	  }
				   }
				  
                  if(!expCol.transRule.transRuleName.equalsIgnoreCase("General - Expression") && !expCol.transRule.transRuleName.equalsIgnoreCase("String - Concatenation"))
                  {
                	    
                	  	//System.out.println("compName>>>>"+compName);
                	  	if(!srcColAdd.equalsIgnoreCase("")){
                	  		srcColAdd = srcColAdd.substring(0, srcColAdd.length()-1);
                	  	}
            	    	
            	    	if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Left Padding") ||
								expCol.transRule.transRuleName.equalsIgnoreCase("String - Right Padding"))
            	    	{
							srcColAdd = srcColAdd+","+expCol.transRule.paramValuesList.get(1);
							mapTbl.setexpression(transRule + "("+srcColAdd+")" + origExp);
						}
            	    	else if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Replace String"))
            	    	{
            	    		srcColAdd = srcColAdd+","+"\""+expCol.transRule.paramValuesList.get(1)+"\""+","+"\""+expCol.transRule.paramValuesList.get(2)+"\"";
            	    		mapTbl.setexpression(transRule + "("+srcColAdd+")" + origExp);
            	    	}
            	    	else if(expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - String to Date")||
            	    			expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - Date/Time to Integer")||
            	    			expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - String to Decimal"))
            	    	{
            	    		srcColAdd =  srcColAdd+","+expCol.transRule.paramValuesList.get(1);
            	    		mapTbl.setexpression(transRule + "("+srcColAdd+")" + origExp);
            	    	}
            	    	else if(expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - Date to String")||
								expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - Date/Time to String"))
						{
            	    		mapTbl.setexpression(srcColAdd+"."+transRule + origExp);
						}
            	    	
						else if(expCol.transRule.transRuleName.equalsIgnoreCase("Check - Condition"))
						{
							//srcColAdd = srcColAdd+","+expCol.transRule.paramValuesList.get(0)+","+expCol.transRule.paramValuesList.get(1)+","+expCol.transRule.paramValuesList.get(2);
							// SRC_TYPE_CD="ORGPRM" Then "W" Else "F"
							// src_type_cd.equals("ORGPRM") ? "W" : "F"
							if(expCol.transRule.paramValuesList.get(1).contains("=")){
								int index = expCol.transRule.paramValuesList.get(1).indexOf("=");
								String temp = expCol.transRule.paramValuesList.get(1).substring(index);
								String temp2 = ".equals("+temp+")";
								srcColAdd = srcColAdd + temp2 + "?" + "\"" +expCol.transRule.paramValuesList.get(2)+"\"" + ":" + "\""+expCol.transRule.paramValuesList.get(3)+"\"";
								mapTbl.setexpression(srcColAdd + origExp);
							}
						}
            	    	
            	    	
            	    	/*if(prevCompNametrans.equalsIgnoreCase("Filter Transformation") || prevCompNametrans.equalsIgnoreCase("Conditional Grouping"))
            	    	{
            	    		mapTbl.setexpression(transRule+"("+"Case_"+compName+"."+colName+")");
            	    	}*/
            	    	else if(!columnList.contains(colName))
            	    	{
            	    	  List<String> SrcCols = Utils.getSrcColsList(recordperPipe, expCol);
       					  String srcCols = SrcCols.get(0);
       					  String srcColName = srcCols.substring(srcCols.indexOf(".")+1, srcCols.length());
            	    	  VarTables.MapperTableEntries varmapTbl =  new VarTables.MapperTableEntries();
   						  varmapTbl.setName("var_"+colName);
   						  //createReportLog.addToLog("==> tMap/"+compName+" (Component Variable) - "+colName+": Component/stage variable might require manual handling in case of nested expression."+System.lineSeparator(), szoutputLinkedServiceFilePath);
   						  varmapTbl.setType(dataType);
   						  varmapTbl.setNullable(nullable);
   						  varmapTbl.setexpression(transRule+"("+"rw_"+prevCompName+"."+srcColName+")");
   						  ndVars.getMapperTableEntries().add(varmapTbl);
   						  
                	    }
            	    	else
            	    	{
            	    		mapTbl.setexpression(transRule + "("+srcColAdd+")" + origExp);
            	    	}
            	    	/*else
            	    	{
            	    		mapTbl.setexpression(transRule+"("+"rw_"+prevCompName+"."+colName+")");
            	    	}*/

            	    	outTbl.getMapperTableEntries().add(mapTbl);
                  }
                  else if(expCol.transRule.transRuleName.equalsIgnoreCase("General - Expression"))
                  {
                	  
                	  String paramVal = expCol.transRule.paramValuesList.get(0);
                	  List<String> SrcCols = Utils.getSrcColsListforParam(recordperPipe, expCol);
					  String SrcColName = SrcCols.toString().replace("[", "").replace("]", "");
					  List<String> SrcColsWithComp = Utils.getSrcColsList(recordperPipe, expCol);
					  String SrcColNameWComp = SrcColsWithComp.toString().replace("[", "").replace("]", "");
					  paramVal = stringManipulator(paramVal,SrcColName,SrcColNameWComp,inputColsList,prevCompNametrans,prevCompName,compName);
					  paramVal = FunctionTypeConv.getCurrentFunctions(paramVal, ToolTypeConstants.DS_TOOL,ToolTypeConstants.TALEND );
                	  if(columnList.contains(colName))  
                	  {  
                		 
                		 if(cntxtParamsList.contains(paramVal))
                		 {
                			 mapTbl.setexpression("context."+paramVal);
                		 }
                		/* else if(paramVal.contains("."))
                		 {
                			 String paramsetName = paramVal.substring(0,paramVal.indexOf('.'));
                			 if(cntxtParamsList.contains(paramsetName)){
                				 paramVal = paramVal.replace('.', '@');
                				 mapTbl.setexpression("Contexts."+paramVal);
                			 }
                		 }*/
                	     else if(!paramVal.contains("'") && !paramVal.contains("(") && !paramVal.equalsIgnoreCase("DSJobName") && !paramVal.contains("\"") && !paramVal.contains("."))
                		 {
                			 //createReportLog.addToLog("==> tMap/"+compName+" (General Expression) - "+colName+": Might require manual handling in case of nested expressions."+ System.lineSeparator(), szoutputLinkedServiceFilePath);
                			 
                	    	 mapTbl.setexpression("Var."+"var_"+paramVal);
                			 if(genExpVarsColsList.contains(paramVal)){
                				 logHelperObj.genExpVarsInCols++;
                			 }
                			 else{
                				 //createReportLog.addToLog("==> tMap/"+compName+" (General Expression) - "+colName+": Might require manual handling in case of nested expressions."+ System.lineSeparator(), szoutputLinkedServiceFilePath);
                				 /*if(paramVal.contains("DS_")){
                					 manualChangesList.add(colName+":"+"require manual handling in case of nested expressions ==> " + "\""+"Var."+"var_"+paramVal+"\"");
                				 }*/
                			 }
                			 
                		 }
                		 else if(paramVal.equalsIgnoreCase("DSJobName"))
                		 {
                			 //createReportLog.addToLog("==> tMap/"+compName+" (General Expression) - "+colName+": Might require manual handling in case of DSJobName is wrong."+ System.lineSeparator(), szoutputLinkedServiceFilePath);
//                			 mapTbl.setexpression("\""+docspecs.getName()+"\"");
                			 mapTbl.setexpression("\""+dataMappingObj.getName()+"\"");
                		 }
                		 else if(paramVal.equalsIgnoreCase("currentTimestamp ()")){
                			
                			//String fns = FunctionTypeConv.getCurrentFunctions(12, 20, paramVal);
                			mapTbl.setexpression(paramVal + origExp);
                		 }
                		 else
                		 {   
                			 String paramsetName ="";
                			 if(paramVal.contains("."))
                			 {
	                			 paramsetName = paramVal.substring(0,paramVal.indexOf('.'));
	                			 
                			 }
                			 if(cntxtParamsList.contains(paramsetName)){
                				 paramVal = paramVal.replace('.', '_');
                				 mapTbl.setexpression("context."+paramVal);
                			 }
                			 else{
                			 
	                			 char singleQuote = '\'';
	                			 char doubleQuote = '\"';
	                			 int countSingleQuote = 0;
	                			 int countDoubleQuote = 0;
	                			 
	                			 for(int i=0;i<paramVal.length();i++){
	                				 if(paramVal.charAt(i)==singleQuote){
	                					 countSingleQuote++;
	                				 }
	                				 if(paramVal.charAt(i)==doubleQuote){
	                					 countDoubleQuote++;
	                				 }
	                			 }
	                			 
	                			 /*if>>>#
	               			  	else>>>@
	               			  	then>>>>%*/
	                			 
	                			 int countIf = 0;
	                			 int countElse = 0;
	                			 int countThen = 0;
	                			 
	                			 String temp =  paramVal.replaceAll("(?i)IIF ", "#").replaceAll(" (?i)IIF ", "#");
	                			 temp =  temp.replaceAll("(?i)If ", "#").replaceAll(" (?i)if ", "#");
	                			 temp =  temp.replaceAll(" (?i)Then ", "%");
	                			 temp =  temp.replaceAll(" (?i)Else ", "@");
	                			 
	                			 for(int l=0;l<temp.length();l++){
	                				 char c = temp.charAt(l);
	                				 if(c=='#'){
	                					 countIf++;
	                				 }
	                				 else if(c=='@'){
	                					 countElse++;
	                				 }
	                				 else if(c=='%'){
	                					 countThen++;
	                				 }
	                			 }
	                			 
	                			 String ifCondition = "";
	                			 String thenStatement = "";
	                			 String elseStatement = "";
	                			 
	                			 if(countIf==1 && countThen==1 && countElse==1){
	                				 ifCondition = temp.substring(temp.indexOf('#')+1,temp.indexOf('%'));
	                    			 thenStatement = temp.substring(temp.indexOf('%')+1,temp.indexOf('@'));
	                    			 elseStatement = temp.substring(temp.indexOf('@')+1);
	                			 }
	                			 
	                			 
	                			 //System.out.println(countIf+""+countThen+""+countElse+""+paramVal + ">>>>>>>>>>>>>" + temp);
	                			 
	                			 if(countSingleQuote==2 && paramVal.charAt(0)==singleQuote && paramVal.charAt(paramVal.length()-1)==singleQuote){
	                				 paramVal = paramVal.replace("\'", "\"");
	                			 }
	                			 else if(countDoubleQuote==2 && paramVal.charAt(0)==singleQuote && paramVal.charAt(paramVal.length()-1)==singleQuote){
	                				 
	                			 }
	                			 else if(countIf==1 && countThen==1 && countElse==1 && !thenStatement.contains("(") && !ifCondition.contains("(") && !elseStatement.contains("(")){
	             
	                			 }
	                			 else{
	                				 //createReportLog.addToLog("==> tMap/"+compName+" (General Expression) - "+colName+": Might require manual handling in case of nested expressions."+ System.lineSeparator(), szoutputLinkedServiceFilePath);
	                				 
	                				 if(paramVal.contains("ds") || paramVal.contains(" dsError_")){
	                					 manualChangesList.add(colName+":"+"require manual handling in case of nested expressions ==> " + "\""+paramVal+"\"");
	                				 }
	                				
	                			 }
	                			 
	                			 
	                			 
	                			 paramVal =  paramVal.replaceAll("(?i)IIF ", "").replaceAll(" (?i)IIF ", "");
	                			 
	                			 paramVal =  paramVal.replaceAll("(?i)If ", " ").replaceAll(" (?i)if ", " ");
	
	                			 paramVal =  paramVal.replaceAll(" (?i)Then ", " ? ");
	                			 
	                			 paramVal =  paramVal.replaceAll(" (?i)Else ", " : ");
	                			 
	                			 paramVal = paramVal.replaceAll(" (?i)Or ", " || ");
	                			 
	                			 paramVal = paramVal.replaceAll(" (?i)And ", " && ");
	                			
	                			 if(paramVal.contains("=") && paramVal.contains("?") && paramVal.contains(":")) {
	                				 
	                				 int index = paramVal.indexOf("=");
	                				 String temp1 = paramVal.substring(0,index);
	                				 String temp2 = paramVal.substring(index, paramVal.length());
	                				 int index2 = temp2.indexOf("?");
	                				 String condition = "";
	                				 if(temp2.substring(1,index2)!=null){
	                					 condition = temp2.substring(1,index2);
	                				 }
	                				 String temp3 = temp2.substring(index2,temp2.length());
	                				 paramVal = temp1 + ".equals("+condition+")"+temp3;
	                				 paramVal = paramVal.replaceAll(" ","");
	                			 }
	                			 
	                			 if(!paramVal.contains("?")){
	                				paramVal =  paramVal.replaceAll(" : ", " + ");
	                			 }
	                			 
	//                			 if(!paramVal.contains("':'")){
	//                				 paramVal =  paramVal.replaceAll(":", " + ");
	//                			 }
	                			 paramVal =  paramVal.replaceAll("MappingVariable", "context");
	                			 mapTbl.setexpression(paramVal + origExp);
                			 } 
                		 }
                		 outTbl.getMapperTableEntries().add(mapTbl);
                	  }
                	  else
                	  {
                		
						  VarTables.MapperTableEntries varmapTbl =  new VarTables.MapperTableEntries();
						  varmapTbl.setName("var_"+colName);
						  //createReportLog.addToLog("==> tMap/"+compName+" (Component Variable) - "+colName+": Component/stage variable might require manual handling in case of nested expression."+System.lineSeparator(), szoutputLinkedServiceFilePath);
						 /* if(paramVal.contains(" DS_")){
         					 manualChangesList.add(colName+":"+"require manual handling in case of component variables ==> " + "\""+"Var."+"var_"+paramVal+"\"");
         				  }*/
						  
						  varmapTbl.setType(dataType);
						  varmapTbl.setNullable(nullable);
						  if(cntxtParamsList.contains(paramVal)){
							  varmapTbl.setexpression("context."+paramVal);
						  }
						  else{
							  
							  	 paramVal =  paramVal.replaceAll("(?i)IIF ", "").replaceAll(" (?i)IIF ", "");
	                			 
		            			 paramVal =  paramVal.replaceAll("(?i)If ", " ").replaceAll(" (?i)if ", " ");
		
		            			 paramVal =  paramVal.replaceAll(" (?i)Then ", " ? ");
		            			 
		            			 paramVal =  paramVal.replaceAll(" (?i)Else ", " : ");
		            			 
		            			 paramVal = paramVal.replaceAll(" (?i)Or ", " || ");
		            			 
		            			 paramVal = paramVal.replaceAll(" (?i)And ", " && ");
            			
	                			 if(paramVal.contains("=") && paramVal.contains("?") && paramVal.contains(":")) {
	                				 
	                				 int index = paramVal.indexOf("=");
	                				 String temp1 = paramVal.substring(0,index);
	                				 String temp2 = paramVal.substring(index, paramVal.length());
	                				 int index2 = temp2.indexOf("?");
	                				 String condition = "";
	                				 if(temp2.substring(1,index2)!=null){
	                					 condition = temp2.substring(1,index2);
	                				 }
	                				 String temp3 = temp2.substring(index2,temp2.length());
	                				 paramVal = temp1 + ".equals("+condition+")"+temp3;
	                				 paramVal = paramVal.replaceAll(" ","");
	                			 }
	                			 
	                			 
	                			 if(!paramVal.contains("?")){
	                				paramVal =  paramVal.replaceAll(" : ", " + ");
	                			 }
	                			 
//	                			 if(!paramVal.contains("':'")){
//	                				 paramVal =  paramVal.replaceAll(":", " + ");
//	                			 }
//							  
							  varmapTbl.setexpression(paramVal + origExp);
						  }
						  //ndVars.getMapperTableEntries().add(varmapTbl); 
                	  }
                  }
                  
			  }
		  }
		
	}

	public static final  String stringManipulator(String paramVal ,String SrcColName ,String SrcColNameWComp,List<String>inputColsList,
			                                      String prevCompNametrans,String prevCompName,String compName)
	{
		String srcCol = "";
		StringTokenizer tokenizer = new StringTokenizer(SrcColName, ",");
        while (tokenizer.hasMoreTokens())
        {
        	srcCol = tokenizer.nextToken();
        	break;
        }
        
        if(paramVal.toLowerCase().contains(srcCol.toLowerCase()) && !srcCol.equalsIgnoreCase("")) 
		{
      	  String tmp3[] = SrcColNameWComp.split(",");
      	  for (int i = 0; i < tmp3.length; i++) 
      	  {
      		 if(prevCompNametrans.equalsIgnoreCase("Filter Transformation") || prevCompNametrans.equalsIgnoreCase("Conditional Grouping"))
			 {  
      		    paramVal = paramVal.replaceAll(tmp3[i].trim(),"Case_"+compName+"."+tmp3[i].trim().substring(tmp3[i].trim().indexOf(".")+1, tmp3[i].trim().length()));
			 }
      		 else
      		 {
      			paramVal = paramVal.replaceAll(tmp3[i].trim(),"rw_"+prevCompName+"."+tmp3[i].trim().substring(tmp3[i].trim().indexOf(".")+1, tmp3[i].trim().length()));
      		 }
      	  }	   
		}
        
		return paramVal;	
	}
	
}