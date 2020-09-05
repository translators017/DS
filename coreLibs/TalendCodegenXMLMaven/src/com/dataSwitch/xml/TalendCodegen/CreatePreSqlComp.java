
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dataSwitch.ds.commons.datatyperules.DataTypeConvertor;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Connection;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.ElementParameter;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;

public class CreatePreSqlComp {

	public static void createSrcForPreSQL(TalendfileProcessType pipeline, TransRecExtDTO recordperPipe, Connector docspecs, Node compNode, String preSQL, SrcTgtInfo srctgtdb, String szoutputLinkedServiceFilePath, DataMapping dataMappingObj, List<String> cntxtParamsList) {
	
		String tlndTranstype = "";
		int DataBaseType = 0 ;
		if(srctgtdb.fileExtension != null)
		{
			if(srctgtdb.fileExtension.equalsIgnoreCase(".xlsx"))
			{
				tlndTranstype = "tFileInputExcel";
				DataBaseType = 18;
			}
			else if(srctgtdb.fileExtension.equalsIgnoreCase(".txt"))
			{
				tlndTranstype = "tFileInputDelimited";
				DataBaseType = 18;
			}
			else
			{
				tlndTranstype = "tFileInputDelimited";
				DataBaseType = 18;
			}
		}
		else
		{
			if(srctgtdb.fileType.equalsIgnoreCase("Relational - Netezza"))
			{
				tlndTranstype = "tNetezzaInput";
				DataBaseType = 4;
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - Teradata"))
			{
				tlndTranstype = "tTeradataInput";
				DataBaseType = 3;
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - DB2 LUW"))
			{
				tlndTranstype = "tDB2Input";
				DataBaseType = 2;
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - Oracle"))
			{
				tlndTranstype = "tOracleInput";
				DataBaseType = 1;
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - ODBC"))
			{
				tlndTranstype = "tOracleInput";
				DataBaseType = 1;
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - SQL Server"))
			{
				tlndTranstype = "tMSSqlInput";
				DataBaseType = 5;
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - Snowflake DW"))
			{
				tlndTranstype = "tSnowflakeInput";
				DataBaseType = 10;
			}
		}
		
	    Double compVersion = null;
		String compName = recordperPipe.compName;
		compName = "Pre_"+compName;
		
		if(srctgtdb.fileExtension != null)
		{
			if(srctgtdb.fileExtension.equalsIgnoreCase(".xlsx") && (srctgtdb.fileType.equalsIgnoreCase("Files - Fixed Width") || srctgtdb.fileType.equalsIgnoreCase("Files - Delimited")))
			{
				compVersion = 0.102;
				fillPropforflatSrc(recordperPipe, docspecs, compNode, srctgtdb,szoutputLinkedServiceFilePath);
			}
			else
			{
				compVersion = 0.102;
				fillPropforflatTxtSrc(recordperPipe, docspecs, compNode, srctgtdb,szoutputLinkedServiceFilePath,cntxtParamsList);
			}
		}
		else
		{
			if(srctgtdb.fileType.equalsIgnoreCase("Relational - Netezza")||srctgtdb.fileType.equalsIgnoreCase("Relational - Teradata")||
					srctgtdb.fileType.equalsIgnoreCase("Relational - DB2 LUW") || srctgtdb.fileType.equalsIgnoreCase("Relational - Oracle") || 
					srctgtdb.fileType.equalsIgnoreCase("Relational - ODBC") || srctgtdb.fileType.equalsIgnoreCase("Relational - SQL Server")) 
			{
				compVersion = 0.102;
				fillPropforDBSrc(recordperPipe, docspecs, compNode, srctgtdb,szoutputLinkedServiceFilePath, cntxtParamsList,preSQL,compName);
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - Snowflake DW"))
			{
				compVersion = 0.102;
				fillPropforDBSnowFlakeSrc(recordperPipe, docspecs, compNode, srctgtdb,szoutputLinkedServiceFilePath,cntxtParamsList,dataMappingObj,preSQL,compName);
			}
		}
		String xPos = recordperPipe.xLevelPos;
		String yPos = recordperPipe.yLevelPos;
		Utils.getcompDetails(compNode,tlndTranstype, compVersion,xPos,yPos);
		TalendfileProcessType.Node.Metadata metadata = new TalendfileProcessType.Node.Metadata();
		metadata.setConnector("FLOW");
		metadata.setLabel("rw_"+compName);
		metadata.setName(compName);
		
		compNode.getMetadata().add(metadata);
		
		pipeline.getNode().add(compNode);
		
		
		Connection conn = new Connection();
		conn.setconnectorName("SUBJOB_OK");
		conn.setlabel("OnSubjobOk");
		conn.setlineStyle("1");
		conn.setmetaname(compName);
		conn.setoffsetLabelX("0");
		conn.setoffsetLabelY("0");
		conn.setsource(compName);
		conn.settarget(recordperPipe.compName);
		
		TalendfileProcessType.Connection.ElementParameter elem = new TalendfileProcessType.Connection.ElementParameter();
		elem.setName("UNIQUE_NAME");
		elem.setfield("TEXT");
		elem.setshow("false");
		elem.setvalue("rw_"+compName);
		
		conn.getElementParameter().add(elem);
		pipeline.getConnection().add(conn);
	}
	
	
	public static void fillPropforflatSrc(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb,String szoutputLinkedServiceFilePath)
	{
		//createReportLog.addToLog("==> tFileInputDelimited (source) / "+recordperPipe.compName+" : Might require manual handling for setting date patterns if date related fields are present in schema." + System.lineSeparator(),szoutputLinkedServiceFilePath);
	    String tlndTranstype = "";
		String compName = recordperPipe.compName;
		if(srctgtdb.fileExtension != null)
		{
			tlndTranstype = "tFileInputExcel";
		}
		String[] fields = new String[]{"TEXT","CHECK","FILE","CHECK","TABLE","TEXT","TEXT","TEXT","CHECK",
				"TEXT","TEXT","CHECK","TECHNICAL","TECHNICAL","CHECK", "TEXT", "TEXT","CHECK","TABLE","CHECK","TABLE","ENCODING_TYPE",
				"TECHNICAL","CHECK","CHECK","CHECK","CHECK","CLOSED_LIST","TEXT","TEXT"};
		
		String[] names = new String[]{"UNIQUE_NAME","VERSION_2007","FILENAME","ALL_SHEETS","SHEETLIST","HEADER","FOOTER","LIMIT","AFFECT_EACH_SHEET",
				"FIRST_COLUMN", "LAST_COLUMN","DIE_ON_ERROR","SCHEMA_REJECT:SCHEMA_TYPE","SCHEMA_REJECT:REPOSITORY_SCHEMA_TYPE","ADVANCED_SEPARATOR", "THOUSANDS_SEPARATOR",
				"DECIMAL_SEPARATOR","TRIMALL","TRIMSELECT","CONVERTDATETOSTRING","DATESELECT","ENCODING","ENCODING:ENCODING_TYPE", "READ_REAL_VALUE", "STOPREAD_ON_EMPTYROW","NOVALIDATE_ON_CELL",
				"SUPPRESS_WARN","GENERATION_MODE","LABEL","CONNECTION_FORMAT"};
		
		String[] values = new String[]{"get from reclist","true","&quot;D:/talend practice 2/file1.xlsx&quot;","true","null","1","0","","false","1","",
				 "false","REPOSITORY","_b0i9wD-BEemk_L7oDS4Ktw - get from reclist","false","','","'.'","false","null","false","null","&quot;UTF-8&quot;","UTF-8",
				 "false","false","false","false","USER_MODE","get from reclist","row"};
		
		String[] shows = new String[]{"false","null","null","null","false","null","null","null","null","null","null","null","false","false","null","false","false","null",
				"null","null","false","null","null","false","null","false","false","null","null","null"};
		
		
		for(int i=0;i<fields.length;i++){
			TalendfileProcessType.Node.ElementParameter elemParam = new TalendfileProcessType.Node.ElementParameter();
			
			elemParam.setField(fields[i]);
			elemParam.setName(names[i]);
			if(values[i]!="null"){
				if(values[i].contains("get from reclist")){
					String s=values[i].replaceAll("get from reclist", recordperPipe.compName);
					elemParam.setValue(s);
				}
				else{
					elemParam.setValue(values[i]);
				}	
			}
			
			if(shows[i]!="null"){
				elemParam.setShow(shows[i]);
			}
			
			if(fields[i].equalsIgnoreCase("TABLE")){
				
				if(names[i].equalsIgnoreCase("SHEETLIST")){
//					List<ColumnDTO> colDTO = srctgtdb.srcTgtColsList;
//					colDTO.get(0).colStruct.name;
					TalendfileProcessType.Node.ElementParameter.ElementValue elemValue1 = new TalendfileProcessType.Node.ElementParameter.ElementValue();
					elemValue1.setElementRef("SHEETNAME");
					elemValue1.setValue("&quot;Sheet1&quot;");
					elemParam.getElementValue().add(elemValue1);
					TalendfileProcessType.Node.ElementParameter.ElementValue elemValue2 = new TalendfileProcessType.Node.ElementParameter.ElementValue();
					elemValue2.setElementRef("USE_REGEX");
					elemValue2.setValue("");
					elemParam.getElementValue().add(elemValue2);
				}
				
				else if(names[i].equalsIgnoreCase("TRIMSELECT")){
					List<ColumnDTO> colDTO = srctgtdb.srcTgtColsList;
					int numCol = colDTO.size();
					int n = 2*numCol;
					//System.out.println("n="+n);
					int k=0;
					for(int j=0;j<n;j++){
						String value;
						String elemRef;
						
						if(j%2==0){
							value = colDTO.get(k).colStruct.name;
							elemRef = "SCHEMA_COLUMN";
							k++;
						}
						else{
							value = "false";
							elemRef = "TRIM";	
						}
						
						TalendfileProcessType.Node.ElementParameter.ElementValue elemValue = new TalendfileProcessType.Node.ElementParameter.ElementValue();
						elemValue.setValue(value);
						elemValue.setElementRef(elemRef);
						elemParam.getElementValue().add(elemValue);
						
					}
				}
				
				
				else if(names[i].equalsIgnoreCase("DATESELECT")){
					List<ColumnDTO> colDTO = srctgtdb.srcTgtColsList;
					int numCol = colDTO.size();
					int n = 3*numCol;
					//System.out.println("n="+n);
					int k=1;
					int colIndex=0;
					for(int j=0;j<n;j++){
						String value = null;
						String elemRef = null;
						
						if(k==1){
							value = colDTO.get(colIndex).colStruct.name;
							elemRef = "SCHEMA_COLUMN";
							k++;
							colIndex++;
						}
						else if(k==2){
							value = "false";
							elemRef="CONVERTDATE";
							k++;
						}
						else if(k==3){
							value = "";
							elemRef = "PATTERN";
							k=1;
						}
						
						TalendfileProcessType.Node.ElementParameter.ElementValue elemValue = new TalendfileProcessType.Node.ElementParameter.ElementValue();
						elemValue.setValue(value);
						elemValue.setElementRef(elemRef);
						elemParam.getElementValue().add(elemValue);
						
					}
				}
				
			}
			
			compNode.getElementParameter().add(elemParam);
		}
		
	}
	
	
	
	
	public static void fillPropforflatTxtSrc(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb,String szoutputLinkedServiceFilePath, List<String> cntxtParamsList)
	{
		//createReportLog.addToLog("==> tFileInputDelimited (source) / "+recordperPipe.compName+": Might require manual handling for setting date patterns if date related fields are present in schema." + System.lineSeparator(),szoutputLinkedServiceFilePath);
		String tlndTranstype = "tFileInputDelimited";
		String path = Utils.getParamValWithContext(srctgtdb.directoryPath, docspecs, cntxtParamsList);
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",recordperPipe.compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("TECHNICAL", "PROPERTY:PROPERTY_TYPE", "REPOSITORY"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TECHNICAL","PROPERTY:REPOSITORY_PROPERTY_TYPE","_b0i9wD-BEemk_L7oDS4Ktw","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TECHNICAL","SCHEMA:SCHEMA_TYPE","REPOSITORY","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TECHNICAL","SCHEMA:REPOSITORY_SCHEMA_TYPE","_b0i9wD-BEemk_L7oDS4Ktw - "+recordperPipe.compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("LABEL", "FILENAMETEXT", "\"When the input source is a stream or a zip file,footer and random shouldn't be bigger than 0.\""));
		elemParam.add(Utils.getPropDtlsforExp("FILE", "FILENAME", path/*+srctgtdb.fileExtension.replace("#", "")*/));
		elemParam.add(Utils.getPropDtlsforExp("CHECK", "CSV_OPTION", "false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT", "ROWSEPARATOR", "\"\\n\""));
		elemParam.add(Utils.getPropDtlsWithShwforExp("OPENED_LIST","CSVROWSEPARATOR","\"\\n\"","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT", "FIELDSEPARATOR", "\",\""));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","ESCAPE_CHAR","\"\"\"","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","TEXT_ENCLOSURE","\"\"\"","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","HEADER","1"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","FOOTER","0"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","LIMIT",""));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","REMOVE_EMPTY_ROW","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","UNCOMPRESS","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","DIE_ON_ERROR","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TECHNICAL","SCHEMA_REJECT:SCHEMA_TYPE","REPOSITORY","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TECHNICAL","SCHEMA_REJECT:REPOSITORY_SCHEMA_TYPE","_b0i9wD-BEemk_L7oDS4Ktw - "+recordperPipe.compName,"false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("DIRECTORY","TEMP_DIR","\"C:/Users/unameit/workspace\"","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","ADVANCED_SEPARATOR","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","THOUSANDS_SEPARATOR","\",\"","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","DECIMAL_SEPARATOR","\".\"","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","RANDOM","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","NB_RANDOM","10","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","TRIMALL","false"));
		ElementParameter elemparamTgtDb = new ElementParameter();
		elemparamTgtDb.setField("TABLE");
		elemparamTgtDb.setName("TRIMSELECT");
		ElementParameter elemparamTgtDbdecode = new ElementParameter();
		elemparamTgtDbdecode.setField("TABLE");
		elemparamTgtDbdecode.setName("DECODE_COLS");
		elemparamTgtDbdecode.setShow("false");
		for(int i = 0 ; i < srctgtdb.srcTgtColsList.size(); i++)
		{
			ColumnDTO tgtCols = srctgtdb.srcTgtColsList.get(i);
			String colsName = tgtCols.colStruct.name;
			String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
			List<ElementParameter.ElementValue> elemValueList = new ArrayList<ElementParameter.ElementValue>();
	    	elemValueList.add(Utils.getPropElemforTrans("SCHEMA_COLUMN", colName));
	    	elemValueList.add(Utils.getPropElemforTrans("TRIM", "false"));
	    	elemparamTgtDb.getElementValue().addAll(elemValueList);
	    	List<ElementParameter.ElementValue> elemValueList1 = new ArrayList<ElementParameter.ElementValue>();
	    	elemValueList1.add(Utils.getPropElemforTrans("SCHEMA_COLUMN", colName));
	    	elemValueList1.add(Utils.getPropElemforTrans("DECODE", "false"));
	    	elemparamTgtDbdecode.getElementValue().addAll(elemValueList1);
		}
		elemParam.add(Utils.getPropDtlsforExp("CHECK","CHECK_FIELDS_NUM","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","CHECK_DATE","false"));
		elemParam.add(Utils.getPropDtlsforExp("ENCODING_TYPE","ENCODING","\"UTF-8\""));
		elemParam.add(Utils.getPropDtlsforExp("TECHNICAL","ENCODING:ENCODING_TYPE","UTF-8"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","SPLITRECORD","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","ENABLE_DECODE","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","DESTINATION","","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","SCHEMA_OPT_NUM","100","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","LABEL",recordperPipe.compName));
		
		compNode.getElementParameter().addAll(elemParam);
		compNode.getElementParameter().add(elemparamTgtDbdecode);
		compNode.getElementParameter().add(elemparamTgtDb);
	}
	
	
	public static void fillPropforDBSrc(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb,String szoutputLinkedServiceFilePath, List<String> cntxtParamsList, String preSQL, String compName)
	{
		    //createReportLog.addToLog("==> tDBInput (source) / "+recordperPipe.compName+": Might require manual handling for setting date patterns if date related fields are present in schema." + System.lineSeparator(),szoutputLinkedServiceFilePath);
		    String tlndTranstype = "";
			if(srctgtdb.fileType.equalsIgnoreCase("Relational - Netezza"))
			{
				tlndTranstype = "Netezza";
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - Teradata")){
				tlndTranstype = "Teradata";
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - DB2 LUW"))
			{
				tlndTranstype = "DB2";
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - Oracle"))
			{
				tlndTranstype = "Oracle";
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - ODBC"))
			{
				tlndTranstype = "Oracle";
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - SQL Server"))
			{
				tlndTranstype = "MSSQL";
			}
		
			
		HashMap<String,String> uNamePwd = Utils.getUnamePwd(docspecs, srctgtdb);	
		
		String[] fields = new String[]{"TEXT","LABEL","CHECK","COMPONENT_LIST","TEXT","TEXT","TEXT","TEXT","TEXT","PASSWORD","DBTABLE","QUERYSTORE_TYPE",
				"TECHNICAL","TECHNICAL","GUESS_SCHEMA","MEMO_SQL","MAPPING_TYPE","ENCODING_TYPE","TECHNICAL","CHECK","TEXT","CHECK","TABLE"};
		
		String[] names = new String[]{"UNIQUE_NAME","NOTE","USE_EXISTING_CONNECTION","CONNECTION","HOST","TYPE","PORT","DBNAME","USER","PASS","TABLE",
				"QUERYSTORE","QUERYSTORE:REPOSITORY_QUERYSTORE_TYPE","QUERYSTORE:QUERYSTORE_TYPE","GUESS_SCHEMA","QUERY","MAPPING","ENCODING","ENCODING:ENCODING_TYPE",
				"USE_CURSOR","CURSOR_SIZE","TRIM_ALL_COLUMN","TRIM_COLUMN"};
		
//		String[] values = new String[]{compName,"(To use this component, you need first to add nzjdbc.jar in the Modules view)","false","null","&quot;&quot;",tlndTranstype,"&quot;5480&quot;","&quot;&quot;","&quot;&quot;","0RMsyjmybrE=","&quot;&quot;","&quot;&quot;","","BUILT_IN",
//				"&quot;&quot;","get from reclist",tlndTranstype.toLowerCase()+"_id","&quot;ISO-8859-15&quot;","ISO-8859-15","false","1000","false",""};
		
		String[] values = new String[]{compName,"(To use this component, you need first to add nzjdbc.jar in the Modules view)","false","null","\"\"",tlndTranstype,"\"\"","get from reclist","read from docspecs","read from docspecs",recordperPipe.compName,"\"\"","","BUILT_IN",
				"\"\"","get from reclist",tlndTranstype.toLowerCase()+"_id","\"ISO-8859-15\"","ISO-8859-15","false","1000","false",""};
		
		String[] shows = new String[]{"false","","","false","","false","","","","","","","false","","","","false","false","","","false","",""};
		
		
		
		
		for(int i=0;i<fields.length;i++){
			TalendfileProcessType.Node.ElementParameter elemParam = new TalendfileProcessType.Node.ElementParameter();
			elemParam.setField(fields[i]);
			elemParam.setName(names[i]);
			if(values[i]!=""){
				if(values[i].equalsIgnoreCase("get from reclist")){
					if(names[i].equalsIgnoreCase("QUERY")){
						//String query = srctgtdb.overrideSQL;
						elemParam.setValue("\""+preSQL+"\"");
					}
					if(names[i].equalsIgnoreCase("DBNAME")){
						String dbName = srctgtdb.connectionName;
						dbName = Utils.getParamValWithContext(dbName, docspecs, cntxtParamsList);
						elemParam.setValue(dbName);
					}
				}
				else if(values[i].equalsIgnoreCase(tlndTranstype.toLowerCase()+"_id") && tlndTranstype.equalsIgnoreCase("MSSQL")){
					elemParam.setValue("id_"+tlndTranstype);
				}
				else if(values[i].equalsIgnoreCase("read from docspecs")){
					String userName = "";
					String pwd = "";
					if(names[i].equalsIgnoreCase("USER")){
						if(uNamePwd.get("username")!=null){
							userName = uNamePwd.get("username");
							userName = Utils.getParamValWithContext(userName, docspecs, cntxtParamsList);
						}
						elemParam.setValue(userName);
					}
					if(names[i].equalsIgnoreCase("PASS")){
						if(uNamePwd.get("pwd")!=null){
							pwd = uNamePwd.get("pwd");
							pwd = Utils.getParamValWithContext(pwd, docspecs, cntxtParamsList);
						}
						elemParam.setValue(pwd);
					}
				}
				else if(values[i]=="null"){
					elemParam.setValue("");
				}
				else{
					elemParam.setValue(values[i]);
				}
			}
			if(shows[i]!=""){
				elemParam.setShow(shows[i]);
			}
			
			if(fields[i].equalsIgnoreCase("table"))
			{
				/*for(int j = 0; j < srctgtdb.srcTgtColsList.size(); j++)
				{
						ColumnDTO cols = srctgtdb.srcTgtColsList.get(j);
						String colsname = cols.colStruct.name;
						String ColName = colsname.substring(colsname.indexOf(".")+1, colsname.length());
						ElementParameter.ElementValue elemval = new ElementParameter.ElementValue();
						elemval.setElementRef("SCHEMA_COLUMN");
						elemval.setValue(ColName);
						elemParam.getElementValue().add(elemval);
						ElementParameter.ElementValue elemval1 = new ElementParameter.ElementValue();
						elemval1.setElementRef("TRIM");
						elemval1.setValue("false");
						elemParam.getElementValue().add(elemval1);
				}	*/
			}
			
			compNode.getElementParameter().add(elemParam);
			
		}

	}
	
	public static void fillPropforDBSnowFlakeSrc(TransRecExtDTO recordperPipe, Connector docspecs, Node compNode,
			SrcTgtInfo srctgtdb, String szoutputLinkedServiceFilePath, List<String> cntxtParamsList, DataMapping dataMappingObj, String preSQL, String compName) 
	{
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("QUERYSTORE_TYPE","QUERYSTORE","","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TECHNICAL","QUERYSTORE:REPOSITORY_QUERYSTORE_TYPE","","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT", "CONNECTION_FORMAT", "row"));
		ElementParameter elemProps = new ElementParameter();
		elemProps.setName("PROPERTIES");
		String srcColsAdd = "";
		for(int i = 0 ; i < srctgtdb.srcTgtColsList.size() ; i++)
		{
			ColumnDTO srcTgtCols = srctgtdb.srcTgtColsList.get(i);
			String colName = srcTgtCols.colStruct.name;
			String datatype = srcTgtCols.colStruct.dataType;
			DataTypeConvertor dt = new DataTypeConvertor();
			String newDataTyp = dt.getTargetDataType(datatype, ToolTypeConstants.SNOWFLAKE, ToolTypeConstants.TALEND);
			boolean isNullable = srcTgtCols.colStruct.isNullable;
			String length = "";
			String precision = "";
			String comment = srcTgtCols.colStruct.description;
			if(comment!=null){
				comment = comment.replace("\"", "").replace("(", "").replace(")", "").replace(",", "").replace("\nSearchable? N\\20", "").replace("\\", "").replace("-", "").replace("\n", "");
			}else{
				comment="";
			}
			
			
			if(srcTgtCols.colStruct.length==0){
				length = Integer.toString(srcTgtCols.colStruct.precision);
				precision = Integer.toString(srcTgtCols.colStruct.Scale);
			}
			else{
				length = Integer.toString(srcTgtCols.colStruct.length);
				precision = Integer.toString(srcTgtCols.colStruct.Scale);
			}
			
			String dataType =  newDataTyp.substring(newDataTyp.indexOf("id_")+3);
			
			dataType = Utils.getUpdatedDataTypeforSnowflakes(dataType);
			String depricatedDataType = "";
			String updatedDataType = "";
			if(dataType.contains(";"))
			{
				updatedDataType = dataType.substring(0, dataType.indexOf(";"));
				depricatedDataType = dataType.substring(dataType.indexOf(";")+1,dataType.length());
			}
			else
			{
				updatedDataType = dataType;
				depricatedDataType = "null";
			}
			
			String DBType = "";
			if(depricatedDataType.equalsIgnoreCase("null"))
			{
				DBType = "\\\"type\\\":[\\\""+updatedDataType+"\\\",\\\""+"null"+"\\\"]";
			}
			else
			{
				if(newDataTyp.equalsIgnoreCase("id_Date"))
				{
					DBType = "\\\"type\\\":[{\\\"type\\\":\\\""+depricatedDataType+"\\\",\\\"java-class\\\":\\\"java.util.Date\\\"},\\\""+"null"+"\\\"]";
				}
				else if(newDataTyp.equalsIgnoreCase("id_BigDecimal"))
				{
					DBType = "\\\"type\\\":[{\\\"type\\\":\\\""+depricatedDataType+"\\\",\\\"java-class\\\":\\\"java.math.BigDecimal\\\"},\\\""+"null"+"\\\"]";
				}
				else if(newDataTyp.equalsIgnoreCase("id_Short"))
				{
					DBType = "\\\"type\\\":[{\\\"type\\\":\\\""+depricatedDataType+"\\\",\\\"java-class\\\":\\\"java.lang.Short\\\"},\\\""+"null"+"\\\"]";
				}
			}
			//srcColsAdd += "{\\\"name\\\":\\\""+colName+"\\\",\\\"type\\\":[\\\"string\\\",\\\"null\\\"],\\\"di.table.comment\\\":\\\"\\\",\\\"talend.field.dbType\\\":\\\"\\\",\\\"talend.field.dbColumnName\\\":\\\""+colName+"\\\",\\\"di.column.talendType\\\":\\\""+newDataTyp+"\\\",\\\"di.column.isNullable\\\":\\\"false\\\",\\\"talend.field.pattern\\\":\\\"\\\",\\\"di.column.relationshipType\\\":\\\"\\\",\\\"di.table.label\\\":\\\""+colName+"\\\",\\\"di.column.relatedEntity\\\":\\\"\\\"},";
				srcColsAdd += "{\\\"name\\\":\\\""+colName+"\\\","+DBType+",\\\"AVRO_TECHNICAL_KEY\\\":\\\""+colName+"\\\",\\\"talend.field.dbType\\\":\\\"\\\",\\\"di.column.talendType\\\":\\\""+newDataTyp+"\\\",\\\"talend.field.pattern\\\":\\\"\\\",\\\"di.table.label\\\":\\\""+colName+"\\\",\\\"talend.field.precision\\\":\\\""+precision+"\\\",\\\"di.table.comment\\\":\\\""+comment+"\\\",\\\"talend.field.dbColumnName\\\":\\\""+colName+"\\\",\\\"di.column.isNullable\\\":\\\""+isNullable+"\\\",\\\"talend.field.length\\\":\\\""+length+"\\\",\\\"di.column.relationshipType\\\":\\\"\\\",\\\"di.column.originalLength\\\":\\\""+length+"\\\",\\\"di.column.relatedEntity\\\":\\\"\\\"},";
			
		}
		
		boolean checkRcp = Utils.getBooleanForRcp(dataMappingObj);
		if(checkRcp == true)
		{
			srcColsAdd += "{\\\"name\\\":\\\""+"Dynamic_Column"+"\\\","+"\\\"type\\\":[\\\""+"string"+"\\\",\\\""+"null"+"\\\"]"+",\\\"AVRO_TECHNICAL_KEY\\\":\\\""+"Dynamic_Column"+"\\\",\\\"talend.field.dbType\\\":\\\"\\\",\\\"di.column.talendType\\\":\\\""+"id_Dynamic"+"\\\",\\\"talend.field.pattern\\\":\\\"\\\",\\\"di.table.label\\\":\\\""+"Dynamic_Column"+"\\\",\\\"talend.field.precision\\\":\\\""+"0"+"\\\",\\\"di.table.comment\\\":\\\""+""+"\\\",\\\"talend.field.dbColumnName\\\":\\\""+"Dynamic_Column"+"\\\",\\\"di.column.isNullable\\\":\\\""+"true"+"\\\",\\\"talend.field.length\\\":\\\""+"255"+"\\\",\\\"di.column.relationshipType\\\":\\\"\\\",\\\"di.column.originalLength\\\":\\\""+"255"+"\\\",\\\"di.column.relatedEntity\\\":\\\"\\\"},";
		}
		
		srcColsAdd = srcColsAdd.substring(0, srcColsAdd.lastIndexOf(","));
		
		String updatedSchema = Utils.getParamValWithContext(srctgtdb.schemaName, docspecs, cntxtParamsList);
		String updatedConnection = Utils.getParamValWithContext(srctgtdb.connectionName, docspecs, cntxtParamsList);
		String updatedTable = Utils.getParamValWithContext(srctgtdb.tableName, docspecs, cntxtParamsList);
		//String updatedQuery = Utils.getUpdatedQuery(srctgtdb.overrideSQL, docspecs, cntxtParamsList);
		String updatedQuery = srctgtdb.overrideSQL;
		String account = "tjx.test.azure";
		String userid = "context.TJXASVC_EU_AGILONE_SHARE_USER";
		cntxtParamsList.add("TJXASVC_EU_AGILONE_SHARE_USER");
		String password = "";
		String dataWareHouse = "context.DEMO_WH";
		cntxtParamsList.add("DEMO_WH");
		
		elemProps.setValue("{\"@id\":3,\"@type\":\"org.talend.components.snowflake.tsnowflakeinput.TSnowflakeInputProperties\",\"condition\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":{\"@type\":\"java.util.RegularEnumSet\",\"@items\":[{\"@type\":\"org.talend.daikon.properties.property.Property$Flags\",\"name\":\"HIDDEN\"}]},\"storedValue\":\"\\\"\\\"\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"condition\",\"tags\":null},"
				+ "\"manualQuery\":{\"flags\":null,\"storedValue\":{\"@type\":\"boolean\",\"value\":true},\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":false,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,"
				+ "\"currentType\":\"java.lang.Boolean\",\"name\":\"manualQuery\",\"tags\":null},"
				+ "\"query\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\","
				+ "\"possibleValues2\":null,\"flags\":{\"@type\":\"java.util.RegularEnumSet\"},"
				+ "\"storedValue\":\"\\\""+preSQL+"\\\"\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false,\"LINE_SEPARATOR_REPLACED_TO\":\" \"},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"query\",\"tags\":null},\"convertColumnsAndTableToUppercase\":{\"flags\":null,\"storedValue\":{\"@type\":\"boolean\",\"value\":true},\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":false,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.Boolean\",\"name\":\"convertColumnsAndTableToUppercase\",\"tags\":null},\"connection\":{\"@id\":4,\"__version\":1,\"name\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,\"storedValue\":null,\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\"},\"size\":-1,\"occurMinTimes\":1,\"occurMaxTimes\":1,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"name\",\"tags\":null},\"loginTimeout\":{\"@id\":7,\"flags\":null,\"storedValue\":{\"@type\":\"int\",\"value\":15},\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.Integer\",\"name\":\"loginTimeout\",\"tags\":null},\"account\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,"
						+ "\"storedValue\":\"\\\""+account+"\\\"\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":1,\"occurMaxTimes\":1,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"account\",\"tags\":null},\"userPassword\":{\"useAuth\":{\"flags\":null,\"storedValue\":{\"@type\":\"boolean\",\"value\":false},\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\"},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.Boolean\",\"name\":\"useAuth\",\"tags\":null},"
						+ "\"userId\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,\"storedValue\":\""+userid+"\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":1,\"occurMaxTimes\":1,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"userId\",\"tags\":null},"
								+ "\"password\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":{\"@type\":\"java.util.RegularEnumSet\",\"@items\":[{\"@type\":\"org.talend.daikon.properties.property.Property$Flags\",\"name\":\"ENCRYPT\"},{\"@type\":\"org.talend.daikon.properties.property.Property$Flags\",\"name\":\"SUPPRESS_LOGGING\"}]},\"storedValue\":\"0RMsyjmybrE=\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":1,\"occurMaxTimes\":1,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"password\",\"tags\":null},\"needSwitch\":false,\"name\":\"userPassword\",\"validationResult\":null,\"tags\":null},"
						+ "\"warehouse\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,\"storedValue\":\""+dataWareHouse+"\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"warehouse\",\"tags\":null},\"db\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,"
						+ "\"storedValue\":\""+updatedConnection+"\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":1,\"occurMaxTimes\":1,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"db\",\"tags\":null},\"schemaName\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\","
								+ "\"possibleValues2\":null,\"flags\":null,\"storedValue\":\""+updatedSchema+"\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":1,\"occurMaxTimes\":1,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"schemaName\",\"tags\":null},\"role\":{\"@id\":6,\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,\"storedValue\":\"\\\"\\\"\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"role\",\"tags\":null},\"tracing\":{\"@id\":5,\"@type\":\"org.talend.daikon.properties.property.EnumProperty\",\"flags\":null,\"storedValue\":{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"OFF\",\"name\":\"OFF\"},\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":{\"@type\":\"java.util.Arrays$ArrayList\",\"@items\":[{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"OFF\",\"name\":\"OFF\"},{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"SEVERE\",\"name\":\"SEVERE\"},{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"WARNING\",\"name\":\"WARNING\"},{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"INFO\",\"name\":\"INFO\"},{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"CONFIG\",\"name\":\"CONFIG\"},{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"FINE\",\"name\":\"FINE\"},{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"FINER\",\"name\":\"FINER\"},{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"FINEST\",\"name\":\"FINEST\"},{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"ALL\",\"name\":\"ALL\"}]},\"currentType\":\"org.talend.components.snowflake.SnowflakeConnectionProperties.Tracing\",\"name\":\"tracing\",\"tags\":null},\"testConnection\":{\"formtoShow\":null,\"name\":\"testConnection\",\"tags\":null},\"advanced\":{\"formtoShow\":{\"subtitle\":null,\"properties\":{\"@ref\":4},\"widgetMap\":{\"loginTimeout\":{\"@type\":\"org.talend.daikon.properties.presentation.Widget\",\"row\":1,\"order\":1,\"hidden\":false,\"widgetType\":\"widget.type.default\",\"longRunning\":false,\"deemphasize\":false,\"callBeforeActivate\":false,\"callBeforePresent\":false,\"callValidate\":false,\"callAfter\":false,\"content\":{\"@ref\":7},\"configurationValues\":{\"@type\":\"java.util.HashMap\"}},\"tracing\":{\"@type\":\"org.talend.daikon.properties.presentation.Widget\",\"row\":2,\"order\":1,\"hidden\":false,\"widgetType\":\"widget.type.enumeration\",\"longRunning\":false,\"deemphasize\":false,\"callBeforeActivate\":false,\"callBeforePresent\":false,\"callValidate\":false,\"callAfter\":false,\"content\":{\"@ref\":5},\"configurationValues\":{\"@type\":\"java.util.HashMap\"}},\"role\":{\"@type\":\"org.talend.daikon.properties.presentation.Widget\",\"row\":3,\"order\":1,\"hidden\":false,\"widgetType\":\"widget.type.default\",\"longRunning\":false,\"deemphasize\":false,\"callBeforeActivate\":false,\"callBeforePresent\":false,\"callValidate\":false,\"callAfter\":false,\"content\":{\"@ref\":6},\"configurationValues\":{\"@type\":\"java.util.HashMap\"}}},\"cancelable\":false,\"originalValues\":null,\"callBeforeFormPresent\":false,\"callAfterFormBack\":false,\"callAfterFormNext\":false,\"callAfterFormFinish\":false,\"allowBack\":false,\"allowForward\":false,\"allowFinish\":false,\"refreshUI\":true,\"name\":\"Advanced\",\"tags\":null},\"name\":\"advanced\",\"tags\":null},\"referencedComponent\":{\"referenceType\":{\"@type\":\"org.talend.daikon.properties.property.EnumProperty\",\"flags\":null,\"storedValue\":null,\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\"},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":{\"@type\":\"java.util.Arrays$ArrayList\",\"@items\":[{\"@type\":\"org.talend.components.api.properties.ComponentReferenceProperties$ReferenceType\",\"name\":\"THIS_COMPONENT\"},{\"@type\":\"org.talend.components.api.properties.ComponentReferenceProperties$ReferenceType\",\"name\":\"COMPONENT_TYPE\"},{\"@type\":\"org.talend.components.api.properties.ComponentReferenceProperties$ReferenceType\",\"name\":\"COMPONENT_INSTANCE\"}]},\"currentType\":\"org.talend.components.api.properties.ComponentReferenceProperties&lt;P extends org.talend.daikon.properties.Properties>.ReferenceType\",\"name\":\"referenceType\",\"tags\":null},\"componentInstanceId\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,\"storedValue\":null,\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\"},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"componentInstanceId\",\"tags\":null},\"referenceDefinitionName\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,\"storedValue\":\"tSnowflakeConnection\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\"},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"referenceDefinitionName\",\"tags\":null},\"reference\":null,\"name\":\"referencedComponent\",\"validationResult\":null,\"tags\":null},\"org.talend.daikon.properties.PropertiesImpl.name\":\"connection\",\"validationResult\":null,\"tags\":null},\"table\":{\"@id\":2,\"connection\":{\"@ref\":4},"
										+ "\"tableName\":{\"possibleValues2\":null,\"flags\":null,\"storedValue\":\"\\\""+updatedTable+"\\\"\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"tableName\",\"tags\":null},\"schemaListener\":{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionTableProperties$1\",\"this$0\":{\"@ref\":3}},\"main\":{\"@type\":\"org.talend.components.snowflake.SnowflakeTableProperties$1\",\"this$0\":{\"@ref\":2},\"schema\":{\"@type\":\"org.talend.daikon.properties.property.SchemaProperty\",\"flags\":null,\"storedValue\":\"{\\\"type\\\":\\\"record\\\",\\\"name\\\":\\\"MAIN\\\","
												+ "\\\"fields\\\":["
												+  srcColsAdd
												+ "],"
												+ "\\\"di.table.name\\\":\\\"MAIN\\\",\\\"di.table.label\\\":\\\"MAIN\\\"}\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":false,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"org.apache.avro.Schema\",\"name\":\"schema\",\"tags\":null},\"name\":\"main\",\"validationResult\":{\"@id\":1,\"status\":{\"name\":\"OK\"},\"message\":null,\"number\":0},\"tags\":null},\"name\":\"table\",\"validationResult\":{\"@type\":\"org.talend.daikon.properties.ValidationResultMutable\",\"status\":{\"name\":\"ERROR\"},\"message\":\"java.lang.IllegalArgumentException: Missing account\",\"number\":0},\"tags\":null},\"name\":\"root\",\"validationResult\":{\"@ref\":1},\"tags\":null}");
		compNode.getElementParameter().addAll(elemParam);
		compNode.getElementParameter().add(elemProps);
	
	}

}