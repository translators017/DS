
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.xml.ETLMapDTO.BaseTransInfo;
import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.ExprInfo;
import com.dataSwitch.xml.ETLMapDTO.InRecDTO;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.ReportLogInfo;
import com.dataSwitch.xml.ETLMapDTO.RouterInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.NodeData;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.NodeData.OutPutTables;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.NodeData.VarTables;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;

public class MergedCompDtls 
{

	/*not yet copied for input and output*/
	
	public static ArrayList<HashMap<String,String>> getMapForDerivedCols(TransRecExtDTO recordperPipe, List<String> cntxtParamsList, DataMapping dataMappingObj, String szoutputLinkedServiceFilePath, LogHelper logHelperObj, ReportLogInfo reportObj) 
	{
		
		 ArrayList<HashMap<String,String>> mapList = new ArrayList<HashMap<String,String>>();
		 //remove this report log afterwards 
		 //createReportLog.addToLog("==> tMap/"+recordperPipe.compName+":::Might require manual handling of Date Pattern in schema."+System.lineSeparator(),szoutputLinkedServiceFilePath);
		  reportObj.transTypeList.put(recordperPipe.compName, "Transformer Component(tMap)");
		  ArrayList<String> manualChangesList = new ArrayList<String>();
		  
		  HashMap<String,String> colExpMap = new HashMap<String,String>();
		  HashMap<String,String> varExpMap = new HashMap<String,String>();
		  
		  ExprInfo exp = (ExprInfo) recordperPipe.transInfo;
		  String compName = recordperPipe.compName;
		  List<String> prevComp = Utils.getPrevCompName(recordperPipe);
		  List<String> inputColsList = new ArrayList<String>();
		  String prevcompName = prevComp.toString().replace("[", "").replace("]", "");
		  String prevCompName =  prevcompName.substring(0,prevcompName.indexOf("/"));
		  String prevCompNametrans = prevcompName.substring(prevcompName.indexOf("/")+1 , prevcompName.length());
		  List <String> columnList = new ArrayList<String>();
		  List <String> columnList1 = new ArrayList<String>();
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
					  paramVal = createExpr.stringManipulator(paramVal,SrcColName,SrcColNameWComp,inputColsList,prevCompNametrans,prevCompName,compName);
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
			  
			 for(int j = 0 ; j < outRecs.columnList.size() ; j++)
			 {
				 Metadata.Column colm = new Metadata.Column();
				 ColumnDTO cols = outRecs.columnList.get(j);
				 String colName = cols.aliasName;
				 String srcComp = cols.srcColumn.colStruct.name;
				 String srcCompName = srcComp.substring(0, srcComp.indexOf("."));
				 String srcCols = cols.srcColumn.colStruct.name;
				 //String srcColsName = srcCols.substring(srcCols.indexOf(".")+1, srcCols.length());
				 String srcColsName = cols.srcColumn.aliasName;
				 columnList.add(colName); 
				
				
				 if(!srcCompName.equalsIgnoreCase(compName))
				 {
					 String prevOutrec = cols.srcColumn.parentOutRec.name;
					 String parentTransType = cols.srcColumn.parentOutRec.parentTransRec.transType;
					 String suffixForMultipleInput = "";
					 if(prevOutrec.substring(prevOutrec.lastIndexOf("t")+1) != null){
						 suffixForMultipleInput = prevOutrec.substring(prevOutrec.lastIndexOf("t")+1);
					 }
					 String prevOutrecName = prevOutrec.substring(0, prevOutrec.indexOf("Out"));
					 if(parentTransType.equalsIgnoreCase("Filter Transformation") || parentTransType.equalsIgnoreCase("Conditional Grouping"))
					 {
					
					   //mapTbl.setexpression("Case_"+compName+"."+srcColsName); hashm,
						 colExpMap.put(colName, "Case_"+compName+"."+srcColsName);
					 }
					 else
					 {
                        //mapTbl.setexpression("rw_"+prevOutrecName+suffixForMultipleInput+"."+srcColsName);hashm,
						 colExpMap.put(colName, "rw_"+prevOutrecName+suffixForMultipleInput+"."+srcColsName);
					 }
				 }
				 else if(VarcolsList.contains(srcColsName))
				 {
                    // mapTbl.setexpression("Var.var_"+srcColsName);hashm,
					 colExpMap.put(colName, "Var.var_"+srcColsName);
				 } 
			 }
		  }
			 
		  if(exp != null)
		  {  
			  
			  
			  for(int k = 0 ; k < exp.colDTOList.size(); k++)
			  {
				  ColumnDTO expCol =  exp.colDTOList.get(k);
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
					  paramVal = createExpr.stringManipulator(paramVal,SrcColName,SrcColNameWComp,inputColsList,prevCompNametrans,prevCompName,compName);
					  
					  paramVal = FunctionTypeConv.getCurrentFunctions(paramVal,ToolTypeConstants.DS_TOOL,ToolTypeConstants.TALEND);
					  
					  if(paramVal.contains(" ds") || paramVal.contains(" dsErrorCheckHoliday")){
	      					 manualChangesList.add(colName+":"+"require manual handling in case of component variables ==> " + paramVal);
	      			  }
					  
					  if(!columnList.contains(colName))  
                	  {
						  VarTables.MapperTableEntries varmapTbl =  new VarTables.MapperTableEntries();
						  varmapTbl.setName("var_"+colName);
						  //createReportLog.addToLog("==> tMap/"+compName+" (Component Variable) - "+colName+": Component/stage variable might require manual handling in case of nested expression.."+System.lineSeparator(), szoutputLinkedServiceFilePath);
						  varmapTbl.setType(dataType);
						  varmapTbl.setNullable("true");
						  if(cntxtParamsList.contains(paramVal)){
							  //varmapTbl.setexpression("Contexts."+paramVal);
							  varExpMap.put(colName+";"+dataType, "context."+paramVal);
						  }
						  else{
							  //varmapTbl.setexpression(paramVal);
							  varExpMap.put(colName+";"+dataType, paramVal);
						  }
                	  }
                  }
                  
			  }
			  
			  
			  
			  for(int k = 0 ; k < exp.colDTOList.size(); k++)
			  {
				  ColumnDTO expCol =  exp.colDTOList.get(k);
				  String dataType = expCol.colStruct.dataType;
				  String colName = expCol.aliasName;
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
						  // mapTbl.setexpression(SrcColumn);hashm,
						   colExpMap.put(colName, SrcColumn);
					 }
              	  else if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Substring"))
              	  {
              		 
              		  List<String> SrcCols = Utils.getSrcColsList(recordperPipe, expCol);
						  String srcCols = SrcCols.get(0);
						  String srcColName = srcCols.substring(srcCols.indexOf(".")+1, srcCols.length());
						  String paramVal1 = expCol.transRule.paramValuesList.get(0);
						  String paramVal2 = expCol.transRule.paramValuesList.get(1);
						boolean suffix = true;
						  int suf = -1;
						  for(int i1=0;i1<recordperPipe.inRecsList.size();i1++){
							  if(recordperPipe.inRecsList.get(i1).prevOutRec.parentTransRec.outRecsList.size()>1){
								  TransRecExtDTO tmpRecord = (TransRecExtDTO) recordperPipe.inRecsList.get(i1).prevOutRec.parentTransRec;
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
						 // mapTbl.setexpression(transRule);hashm,
						  colExpMap.put(colName, transRule);
              	  }
				   }
				  
                if(!expCol.transRule.transRuleName.equalsIgnoreCase("General - Expression") && !expCol.transRule.transRuleName.equalsIgnoreCase("String - Concatenation"))
                {
              	    
          	    	srcColAdd = srcColAdd.substring(0, srcColAdd.length()-1);
          	    	if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Left Padding") ||
								expCol.transRule.transRuleName.equalsIgnoreCase("String - Right Padding"))
          	    	{
							srcColAdd = srcColAdd+","+expCol.transRule.paramValuesList.get(0);
							//mapTbl.setexpression(transRule + "("+srcColAdd+")");
							colExpMap.put(colName, transRule + "("+srcColAdd+")");
						}
          	    	else if(expCol.transRule.transRuleName.equalsIgnoreCase("String - Replace String"))
          	    	{
          	    		srcColAdd = srcColAdd+","+expCol.transRule.paramValuesList.get(0)+","+expCol.transRule.paramValuesList.get(1);
          	    		//mapTbl.setexpression(transRule + "("+srcColAdd+")");
          	    		colExpMap.put(colName, transRule + "("+srcColAdd+")");
          	    	}
          	    	else if(expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - String to Date")||
          	    			expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - Date/Time to Integer")||
          	    			expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - String to Decimal"))
          	    	{
          	    		srcColAdd =  srcColAdd+","+expCol.transRule.paramValuesList.get(0);
          	    		//mapTbl.setexpression(transRule + "("+srcColAdd+")");
          	    		colExpMap.put(colName, transRule + "("+srcColAdd+")");
          	    	}
          	    	else if(expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - Date to String")||
								expCol.transRule.transRuleName.equalsIgnoreCase("Conversion - Date/Time to String"))
						{
	          	    	 //	mapTbl.setexpression(srcColAdd+"."+transRule);
	          	    		colExpMap.put(colName, srcColAdd+"."+transRule);
						}
          	    	
						else if(expCol.transRule.transRuleName.equalsIgnoreCase("Check - Condition"))
						{
							if(expCol.transRule.paramValuesList.get(0).contains("=")){
								int index = expCol.transRule.paramValuesList.get(0).indexOf("=");
								String temp = expCol.transRule.paramValuesList.get(0).substring(index);
								String temp2 = ".equals("+temp+")";
								srcColAdd = srcColAdd + temp2 + "?" + expCol.transRule.paramValuesList.get(1) + ":" + expCol.transRule.paramValuesList.get(2);
								//mapTbl.setexpression(srcColAdd);
								colExpMap.put(colName,srcColAdd);
							}
						}
          	    	
          	    	
          	    	else if(!columnList.contains(colName))
          	    	{
          	    	  List<String> SrcCols = Utils.getSrcColsList(recordperPipe, expCol);
     					  String srcCols = SrcCols.get(0);
     					  String srcColName = srcCols.substring(srcCols.indexOf(".")+1, srcCols.length());
 						  
 						 // varmapTbl.setexpression(transRule+"("+"rw_"+prevCompName+"."+srcColName+")");
 						  
              	    }
          	    	else
          	    	{
          	    		//mapTbl.setexpression(transRule + "("+srcColAdd+")");
          	    		colExpMap.put(colName, transRule + "("+srcColAdd+")");
          	    	}

                }
                else if(expCol.transRule.transRuleName.equalsIgnoreCase("General - Expression"))
                {
              	  
                	
              	  String paramVal = expCol.transRule.paramValuesList.get(0);
              	  List<String> SrcCols = Utils.getSrcColsListforParam(recordperPipe, expCol);
					  String SrcColName = SrcCols.toString().replace("[", "").replace("]", "");
					  List<String> SrcColsWithComp = Utils.getSrcColsList(recordperPipe, expCol);
					  String SrcColNameWComp = SrcColsWithComp.toString().replace("[", "").replace("]", "");
					  paramVal = createExpr.stringManipulator(paramVal,SrcColName,SrcColNameWComp,inputColsList,prevCompNametrans,prevCompName,compName);
					  paramVal = FunctionTypeConv.getCurrentFunctions(paramVal,ToolTypeConstants.DS_TOOL,ToolTypeConstants.TALEND);
					  
					  
				 if(columnList.contains(colName))  
              	  {  
              		 
              		/* if(cntxtParamsList.contains(paramVal))
              		 {
              			 //mapTbl.setexpression("Contexts."+paramVal);
              			colExpMap.put(colName, "Contexts."+paramVal);
              		 }*/
              		
              	     if(!paramVal.contains("'") && !paramVal.contains("(") && !paramVal.equalsIgnoreCase("DSJobName") && !paramVal.contains("\""))
              		 {
              			 
              	    	 //mapTbl.setexpression("Var."+"var_"+paramVal);
              	    	 if(paramVal.contains("."))
              	    	 {
              	    		 String paramSetName = paramVal.substring(0, paramVal.indexOf("."));
              	    		 String paramName = paramVal.substring(paramVal.indexOf(".")+1);
              	    		 
              	    		
              	    		 
              	    		 String param = paramSetName + "_" + paramName;
              	    		 
              	    		 if(cntxtParamsList.contains(paramSetName))
                     		 {
                     			 //mapTbl.setexpression("Contexts."+paramVal);
              	    			 colExpMap.put(colName, "context."+paramSetName+"_"+paramName);
                     			
                     			if(!cntxtParamsList.contains(param)){
                     				cntxtParamsList.add(param + "@" + dataType);
                     			}
                     			
                     		 }
              	    		else
                 	    	 {
                 	    		colExpMap.put(colName, "Var."+"var_"+paramVal);
                 	    		varExpMap.put(colName+";"+dataType, paramVal);
                 	    	 }
              	    	 }
              	    	 else
              	    	 {
              	    		colExpMap.put(colName,paramVal);
              	    	 }
              	    	 
   
              	    	if(paramVal.contains(" ds") || paramVal.contains(" dsErrorCheckHoliday")){
	       					 //manualChangesList.add(colName+":"+"require manual handling in case of nested expressions ==> " + "\""+"Var."+"var_"+paramVal+"\"");
	           			}
              	    	 
              	    	
              		 }
              		 else if(paramVal.equalsIgnoreCase("DSJobName"))
              		 {
              			 //mapTbl.setexpression("\""+dataMappingObj.getName()+"\"");
              			colExpMap.put(colName, "\""+dataMappingObj.getName()+"\"");
              		 }
              		 else if(paramVal.equalsIgnoreCase("currentTimestamp ()")){
              			
              			String fns = FunctionTypeConv.getCurrentFunctions(paramVal,ToolTypeConstants.DATASTAGE,ToolTypeConstants.TALEND);
              			//mapTbl.setexpression(fns);
              			colExpMap.put(colName,fns);
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
              				 //mapTbl.setexpression("Contexts."+paramVal);
              				colExpMap.put(colName, "context."+paramVal);
              			 }
              			 else{
              			 
	                			 char singleQuote = '\'';
	                			 char doubleQuote = '\"';
	                			 int countSingleQuote = 0;
	                			 int countDoubleQuote = 0;
	                			 
	                			 for(int i1=0;i1<paramVal.length();i1++){
	                				 if(paramVal.charAt(i1)==singleQuote){
	                					 countSingleQuote++;
	                				 }
	                				 if(paramVal.charAt(i1)==doubleQuote){
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
	                			 
	                			 
	                			
	                			 
	                			 if(countSingleQuote==2 && paramVal.charAt(0)==singleQuote && paramVal.charAt(paramVal.length()-1)==singleQuote){
	                				 paramVal = paramVal.replace("\'", "\"");
	                			 }
	                			 else if(countDoubleQuote==2 && paramVal.charAt(0)==singleQuote && paramVal.charAt(paramVal.length()-1)==singleQuote){
	                				 
	                			 }
	                			 else if(countIf==1 && countThen==1 && countElse==1 && !thenStatement.contains("(") && !ifCondition.contains("(") && !elseStatement.contains("(")){
	             
	                			 }
	                			 else{
	                				 //createReportLog.addToLog("==> tMap/"+compName+" (General Expression) - "+colName+": Might require manual handling in case of nested expressions."+ System.lineSeparator(), szoutputLinkedServiceFilePath);
	                				 if(paramVal.contains("DS_")){
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
	                			 
	//                			 
	                			 
	                			// mapTbl.setexpression(paramVal);
	                			 colExpMap.put(colName, paramVal);
              			 } 
              		 }
              	  }
              	  else
              	  {
              		
						 
					/*	 //createReportLog.addToLog("==> tMap/"+compName+" (Component Variable) - "+colName+": Component/stage variable might require manual handling in case of nested expression."+System.lineSeparator(), szoutputLinkedServiceFilePath);
	              		if(paramVal.contains(" DS_")){
	    					 manualChangesList.add(colName+":"+"require manual handling in case of component variables ==> " + "\""+"Var."+"var_"+paramVal+"\"");
	    				 }*/
						  if(cntxtParamsList.contains(paramVal)){
							  //varmapTbl.setexpression("Contexts."+paramVal);
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
	                			 
//							  
							 // varmapTbl.setexpression(paramVal);
						  }
              	  }
                }
                
			  }
		  }
		
		  mapList.add(colExpMap);
		  mapList.add(varExpMap);
		
		  reportObj.manualChanges.put(compName, manualChangesList);
		  
		return mapList;
	}

	
	public static void setOutInVarTables(HashMap<String, String> derivedColMap, HashMap<String, String> varMap, TransRecExtDTO recordperPipe, List<String> nextComp, Node compNode, OutPutTables outTbl, RouterInfo rtr, String prevCompName, String suffForCond, 
			String prevCompNametrans, String compName, DataMapping dataMappingObj, NodeData ndData, TransRecExtDTO expTransRecordperPipe, VarTables ndVars) {
		
		ArrayList<String> varColsList = new ArrayList<String>();
		
		for (HashMap.Entry<String,String> entry : varMap.entrySet()){
			VarTables.MapperTableEntries varmapTbl =  new VarTables.MapperTableEntries();
			 varmapTbl.setName("var_"+entry.getKey().substring(0,entry.getKey().lastIndexOf(';')));
			 varColsList.add(entry.getKey().substring(0,entry.getKey().lastIndexOf(';')));
			 varmapTbl.setType(entry.getKey().substring(entry.getKey().indexOf(';')+1));
			 varmapTbl.setNullable("true");
			 varmapTbl.setexpression(entry.getValue());
			 ndVars.getMapperTableEntries().add(varmapTbl);
		}

		if(compName.equalsIgnoreCase("XFM_UNC_ONLY_EXP")){
			String debug = "";
		}
		
		
		  for(int i = 0 ; i < recordperPipe.outRecsList.size() ; i++)
		  {
			  OutRecDTO outRecs = recordperPipe.outRecsList.get(i);
			  if(outRecs.columnList.size() <=0  && outRecs.nextInRec.parentTransRec.inRecsList.size()>1)
					 continue;
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
				  if(outRecDTO.size() > 0){
					  for(int i1 = 0;i1 < outRecDTO.size();i1++){
						  if(outRecDTO.get(i1).name.equalsIgnoreCase(outRecs.name)){
							  for(int i2 = 0;i2 < rtr.grpNamesList.size(); i2++){
								  if(rtr.grpNamesList.get(i2).equalsIgnoreCase(entry.getKey())){
									  if(rtr.routerCondList.get(i2)!=null){
										  condName = rtr.routerCondList.get(i2);
										  condition = condName.substring(condName.indexOf(".")+1, condName.length());
										  
										  condition = condition.replaceAll(" (?i)AND ", " && ");
				    						  
										  condition = condition.replaceAll(" (?i)OR ", " || ");
										  
										  condition = condition.replaceAll("=", "==").replaceAll(" = "," == ");
										  
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
				  else{
					  condition = rtr.routerCondList.get(0).substring(rtr.routerCondList.get(0).indexOf('.')+1);
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
			  String updatedCondition = "";
			  boolean containsVar = false;
			  if(!(prevCompNametrans.equalsIgnoreCase("Conditional Grouping")||prevCompNametrans.equalsIgnoreCase("Filter Transformation")))
			  {
				  if(!(condition.equalsIgnoreCase("true") || condition.equalsIgnoreCase("false")))
				  {
					  List<Token> tk = FunctionTypeConv.getTokens(condition);
					  for(Token token : tk)
					  {
						 
						  if(varColsList.contains( token.data)){
							  updatedCondition += "Var.var_"+ token.data;
							  containsVar = true;
						  }
						  else{
							  updatedCondition +=  token.data;
						  }
					  }
				      if(containsVar==true){
				    	  
				    	  outTbl.setexpressionFilter(updatedCondition);
				      }
				      else{
				    	  outTbl.setexpressionFilter("rw_"+prevCompName+suffForCond+"."+condition);
				      }
				  }
				  else
				  {
					  if(containsVar==true){
				    	  outTbl.setexpressionFilter(updatedCondition);
				      }
				      else{
				    	  outTbl.setexpressionFilter(condition);
				      }
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
					 
					 
					  
					 colm.setNullable(nullStatus);
					 colm.setPattern("");
	//				 colm.setPrecision(precision);
					 colm.setType(dataType);
					 colm.setUsefulColumn("true");
					 mtData.getColumn().add(colm);
					 Node.NodeData.OutPutTables.MapperTableEntries mapTbl = new Node.NodeData.OutPutTables.MapperTableEntries ();
					 mapTbl.setName(colName);
					 mapTbl.setType(dataType);
					 mapTbl.setNullable(nullStatus);
					 
					 String suffixForMultipleInput = "";
					 if(prevOutrec.substring(prevOutrec.lastIndexOf("t")+1) != null){
						 suffixForMultipleInput = prevOutrec.substring(prevOutrec.lastIndexOf("t")+1);
					 }
					 
					 if(prevCompNametrans.equalsIgnoreCase("Filter Transformation") || prevCompNametrans.equalsIgnoreCase("Conditional Grouping"))
					 {
					   //mapTbl.setexpression("Case_"+compName+"."+srccolName);
						 if(derivedColMap.get(colName)!=null)
						 {
							 mapTbl.setexpression(derivedColMap.get(colName));
							// System.out.println("DerivedCol " + colName);
						 }
						 else
						 {
							 mapTbl.setexpression(derivedColMap.get(srccolName));
						 }
					 }
					 else
					 {
	                    //mapTbl.setexpression("rw_"+prevCompName+"."+srccolName);
						 //mapTbl.setexpression("rw_"+prevOutrecName+suffixForMultipleInput+"."+srccolName);
						 //mapTbl.setexpression(derivedColMap.get(srccolName));
						 if(derivedColMap.get(srccolName)!=null)
						 {
							// System.out.println(colName +"<<Value>>>"+derivedColMap.get(colName) + "<<<derivedColName>>>");
							 mapTbl.setexpression(derivedColMap.get(srccolName));
						 }
						/* else
						 {
							 //System.out.println(derivedColMap.get(srccolName)+ "<<<<SrcColName>>>>");
							 mapTbl.setexpression(derivedColMap.get(srccolName));
						 }*/
						 
					 }
					 outTbl.getMapperTableEntries().add(mapTbl);
				 }
			  }
			 ndData.getOutPutTables().add(outTbl);
			 compNode.getMetadata().add(mtData);
		  }
		  
		  
		  
		  for(int j = 0 ; j < expTransRecordperPipe.inRecsList.size() ; j++)
		  {
			  InRecDTO inrec = expTransRecordperPipe.inRecsList.get(j);
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
				 //String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				  String colName = colsList.aliasName;
				  mapTbl.setName(colName);
				  mapTbl.setType(dataType);
				  
				  boolean isNullable = colsList.colStruct.isNullable;
				  String nullStatus = String.valueOf(isNullable);
				  
				  mapTbl.setNullable(nullStatus);
				  inTbl.getMapperTableEntries().add(mapTbl);
			  }
			  ndData.getInPutTables().add(inTbl);
		  }
	  
		
	}

	
	
	}