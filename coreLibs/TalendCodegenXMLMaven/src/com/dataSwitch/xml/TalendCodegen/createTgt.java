
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.InRecDTO;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.ElementParameter;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata.Column;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.ds.commons.datatyperules.*;/*
import com.dataSwitch.ds.functionTypeRules.TestTypeConv;
import com.dataSwitch.ds.functionparser.Token;*/
public class createTgt {

	public static void fillPropsforFlatTgt(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb, List<String> cntxtParamsList)
	{
		if(!srctgtdb.fileExtension.equalsIgnoreCase("xml"))
		{
		
			String[] fields = new String[]{"TEXT","CHECK","TEXT","FILE","TEXT","CHECK","OPENED_LIST","TEXT","CHECK","CHECK","CHECK","CHECK","TEXT","TEXT",
					"CHECK","TEXT","TEXT","CHECK","CHECK","TEXT","CHECK","TEXT","CHECK","ENCODING_TYPE","TECHNICAL","CHECK","CHECK","TEXT","TEXT"};
			
			String[] names = new String[]{"UNIQUE_NAME","USESTREAM","STREAMNAME","FILENAME","ROWSEPARATOR","OS_LINE_SEPARATOR_AS_ROW_SEPARATOR","CSVROWSEPARATOR","FIELDSEPARATOR","APPEND","INCLUDEHEADER",
					"COMPRESS","ADVANCED_SEPARATOR","THOUSANDS_SEPARATOR","DECIMAL_SEPARATOR","CSV_OPTION","ESCAPE_CHAR","TEXT_ENCLOSURE","CREATE","SPLIT","SPLIT_EVERY","FLUSHONROW","FLUSHONROW_NUM","ROW_MODE",
					"ENCODING","ENCODING:ENCODING_TYPE","DELETE_EMPTYFILE","FILE_EXIST_EXCEPTION","SCHEMA_OPT_NUM","CONNECTION_FORMAT"};
			
			String[] values = new String[]{"get from reclist","false","outputStream","get from reclist","\"\\n\"","true","\"\\n\"","get from reclist","true","true","false","false",
					"\",\"","\".\"","false","\"\"\"","\"\"\"","true","false","1000","false","1","false","\"ISO-8859-15\"",
					"\"ISO-8859-15\"","false","true","90","row"};
			
			String[] shows = new String[]{"false","null","false","null","null","false","false","null","null","null","false","null","false","false","null","false","false","null","null","false","null","false","null","null",
					"null","null","false","false","null"};
			
			for(int i=0;i<fields.length;i++){
				TalendfileProcessType.Node.ElementParameter elemParam = new TalendfileProcessType.Node.ElementParameter();
				elemParam.setField(fields[i]);
				elemParam.setName(names[i]);
				if(shows[i]!="null"){
					elemParam.setShow(shows[i]);
				}
				if(values[i]=="get from reclist"){
					if(names[i].equalsIgnoreCase("UNIQUE_NAME")){
						elemParam.setValue(recordperPipe.compName);
					}
					else if(names[i].equalsIgnoreCase("FILENAME")){
						//List<Token> tokens = TestTypeConv.getTokens(srctgtdb.directoryPath);
						String path = Utils.getParamValWithContext(srctgtdb.directoryPath, docspecs, cntxtParamsList);
						elemParam.setValue(path);
					}
					else if(names[i].equalsIgnoreCase("FIELDSEPARATOR")){
						String delim = srctgtdb.delimiter;
						if(delim==null){
							elemParam.setValue("\"\"");
						}
						else{
							elemParam.setValue("\""+delim+"\"");
						}
					}
					
				}
				else{
					elemParam.setValue(values[i]);
				}
				compNode.getElementParameter().add(elemParam);
			}
		}
		else{
			
			ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
			
			String directory = srctgtdb.directoryPath;
			if(directory.contains("@")){
				directory = directory.substring(0,srctgtdb.directoryPath.indexOf('@'));
			}
			
			elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT", "UNIQUE_NAME", recordperPipe.compName,"false"));
			elemParam.add(Utils.getPropDtlsforExp("CHECK", "USESTREAM", "false"));
			elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT", "STREAMNAME", "outputStream", "false"));
			elemParam.add(Utils.getPropDtlsforExp("FILE","FILENAME",directory));			
			
			int orderIndex = 0;
			
			String tempNs = srctgtdb.directoryPath.substring(srctgtdb.directoryPath.indexOf('@'),srctgtdb.directoryPath.length());
			String[] namespaces = tempNs.split("@xmlns:");
			
			String description = srctgtdb.srcTgtColsList.get(0).colStruct.description;
			String root3 = description.substring(0,description.indexOf("/Facility"));
			String root2 = root3.substring(0,root3.lastIndexOf('/'));
			String root1 = root2.substring(0,root2.lastIndexOf('/'));
					
			ElementParameter elemparamTgtXml = new ElementParameter();
			elemparamTgtXml.setField("TABLE");
			elemparamTgtXml.setName("ROOT");
			elemparamTgtXml.setShow("false");
			List<ElementParameter.ElementValue> elemValueList = new ArrayList<ElementParameter.ElementValue>();
			orderIndex++;
			elemValueList.add(Utils.getPropElemforTrans("PATH", root1));
			elemValueList.add(Utils.getPropElemforTrans("COLUMN", ""));
			elemValueList.add(Utils.getPropElemforTrans("VALUE", ""));
			elemValueList.add(Utils.getPropElemforTrans("ATTRIBUTE", "main"));
			elemValueList.add(Utils.getPropElemforTrans("ORDER", String.valueOf(orderIndex)));
			elemparamTgtXml.getElementValue().addAll(elemValueList);
			for(int i = 1 ; i < namespaces.length; i++)
			{
				List<ElementParameter.ElementValue> elemValueList1 = new ArrayList<ElementParameter.ElementValue>();
				
				String namespace = namespaces[i];
				String path = namespace.substring(0,namespace.indexOf('='));
				String value = namespace.substring(namespace.indexOf('=')+1,namespace.length());
				
				orderIndex++;
				elemValueList1.add(Utils.getPropElemforTrans("PATH", path));
				elemValueList1.add(Utils.getPropElemforTrans("COLUMN", ""));
				elemValueList1.add(Utils.getPropElemforTrans("VALUE", value));
				elemValueList1.add(Utils.getPropElemforTrans("ATTRIBUTE", "ns"));
				elemValueList1.add(Utils.getPropElemforTrans("ORDER", String.valueOf(orderIndex)));
				elemparamTgtXml.getElementValue().addAll(elemValueList1);
			}
			List<ElementParameter.ElementValue> elemValueList2 = new ArrayList<ElementParameter.ElementValue>();
			orderIndex++;
			elemValueList2.add(Utils.getPropElemforTrans("PATH", root2));
			elemValueList2.add(Utils.getPropElemforTrans("COLUMN", ""));
			elemValueList2.add(Utils.getPropElemforTrans("VALUE", ""));
			elemValueList2.add(Utils.getPropElemforTrans("ATTRIBUTE", "main"));
			elemValueList2.add(Utils.getPropElemforTrans("ORDER", String.valueOf(orderIndex)));
			elemparamTgtXml.getElementValue().addAll(elemValueList2);
			List<ElementParameter.ElementValue> elemValueList3 = new ArrayList<ElementParameter.ElementValue>();
			orderIndex++;
			elemValueList3.add(Utils.getPropElemforTrans("PATH", root3));
			elemValueList3.add(Utils.getPropElemforTrans("COLUMN", ""));
			elemValueList3.add(Utils.getPropElemforTrans("VALUE", ""));
			elemValueList3.add(Utils.getPropElemforTrans("ATTRIBUTE", "main"));
			elemValueList3.add(Utils.getPropElemforTrans("ORDER", String.valueOf(orderIndex)));
			elemparamTgtXml.getElementValue().addAll(elemValueList3);
			
			ElementParameter elemParam1 = new ElementParameter();
			elemParam1.setField("TABLE");
			elemParam1.setName("GROUP");
			elemParam1.setShow("false");
			
			ElementParameter elemparam2 = new ElementParameter();
			elemparam2.setField("TABLE");
			elemparam2.setName("LOOP");
			elemparam2.setShow("false");
			List<ElementParameter.ElementValue> elemValueList4 = new ArrayList<ElementParameter.ElementValue>();
			orderIndex++;
			elemValueList4.add(Utils.getPropElemforTrans("PATH", description.substring(0,description.lastIndexOf('/'))));
			elemValueList4.add(Utils.getPropElemforTrans("COLUMN", ""));
			elemValueList4.add(Utils.getPropElemforTrans("VALUE", ""));
			elemValueList4.add(Utils.getPropElemforTrans("ATTRIBUTE", "main"));
			elemValueList4.add(Utils.getPropElemforTrans("ORDER", String.valueOf(orderIndex)));
			elemparam2.getElementValue().addAll(elemValueList4);
			for(int i = 0 ; i < srctgtdb.srcTgtColsList.size(); i++)
			{
				ColumnDTO tgtCols = srctgtdb.srcTgtColsList.get(i);
				String colsName = tgtCols.colStruct.name;
				String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				
				List<ElementParameter.ElementValue> elemValueList5 = new ArrayList<ElementParameter.ElementValue>();
				orderIndex++;
				elemValueList5.add(Utils.getPropElemforTrans("PATH", tgtCols.colStruct.description));
				elemValueList5.add(Utils.getPropElemforTrans("COLUMN", colName));
				elemValueList5.add(Utils.getPropElemforTrans("VALUE", ""));
				elemValueList5.add(Utils.getPropElemforTrans("ATTRIBUTE", "branch"));
				elemValueList5.add(Utils.getPropElemforTrans("ORDER", String.valueOf(orderIndex)));
				elemparam2.getElementValue().addAll(elemValueList5);
			}
			
			ArrayList<ElementParameter> elemParam3 = new ArrayList<ElementParameter>();
			
			elemParam3.add(Utils.getPropDtlsforExp("EXTERNAL", "MAP", ""));
			elemParam3.add(Utils.getPropDtlsforExp("CHECK", "MERGE", "false"));
			elemParam3.add(Utils.getPropDtlsforExp("CHECK", "PRETTY_COMPACT", "false"));
			elemParam3.add(Utils.getPropDtlsforExp("CHECK", "FILE_VALID", "false"));
			elemParam3.add(Utils.getPropDtlsWithShwforExp("RADIO", "DTD_VALID", "true", "false"));
			elemParam3.add(Utils.getPropDtlsWithShwforExp("TEXT", "DTD_NAME", "\"Root\"", "false"));
			elemParam3.add(Utils.getPropDtlsWithShwforExp("TEXT", "DTD_SYSTEMID", "\"Talend.dtd\"", "false"));
			elemParam3.add(Utils.getPropDtlsWithShwforExp("RADIO", "XSL_VALID", "false", "false"));
			elemParam3.add(Utils.getPropDtlsWithShwforExp("TEXT", "XSL_TYPE", "\"text/xsl\"", "false"));
			elemParam3.add(Utils.getPropDtlsWithShwforExp("TEXT", "XSL_HREF", "\"Talend.xsl\"", "false"));
			elemParam3.add(Utils.getPropDtlsforExp("CHECK", "SPLIT", "false"));
			elemParam3.add(Utils.getPropDtlsWithShwforExp("TEXT", "SPLIT_EVERY", "1000", "false"));
			elemParam3.add(Utils.getPropDtlsforExp("CHECK", "TRIM", "false"));
			elemParam3.add(Utils.getPropDtlsforExp("CHECK", "CREATE", "true"));
			elemParam3.add(Utils.getPropDtlsforExp("CHECK", "CREATE_EMPTY_ELEMENT", "true"));
			elemParam3.add(Utils.getPropDtlsforExp("CHECK", "ADD_EMPTY_ATTRIBUTE", "true"));
			elemParam3.add(Utils.getPropDtlsforExp("CHECK", "ADD_UNMAPPED_ATTRIBUTE", "false"));
			elemParam3.add(Utils.getPropDtlsforExp("CHECK", "ADD_DOCUMENT_AS_NODE", "false"));
			elemParam3.add(Utils.getPropDtlsforExp("CHECK", "OUTPUT_AS_XSD", "false"));
			elemParam3.add(Utils.getPropDtlsforExp("CHECK", "ADVANCED_SEPARATOR", "false"));
			elemParam3.add(Utils.getPropDtlsWithShwforExp("TEXT", "THOUSANDS_SEPARATOR", "\",\"", "false"));
			elemParam3.add(Utils.getPropDtlsWithShwforExp("TEXT", "DECIMAL_SEPARATOR", "\".\"", "false"));
			elemParam3.add(Utils.getPropDtlsforExp("CLOSED_LIST", "GENERATION_MODE", "Dom4j"));
			elemParam3.add(Utils.getPropDtlsforExp("ENCODING_TYPE", "ENCODING", "\"ISO-8859-15\""));
			elemParam3.add(Utils.getPropDtlsforExp("TECHNICAL", "ENCODING:ENCODING_TYPE", "ISO-8859-15"));
			elemParam3.add(Utils.getPropDtlsforExp("CHECK", "DELETE_EMPTYFILE", "false"));
			elemParam3.add(Utils.getPropDtlsWithShwforExp("TEXT", "SCHEMA_OPT_NUM", "100", "false"));
			elemParam3.add(Utils.getPropDtlsWithShwforExp("TEXT", "XMLNODE_OPT_NUM", "100", "false"));
			elemParam3.add(Utils.getPropDtlsforExp("TEXT", "CONNECTION_FORMAT", "row"));
			
			compNode.getElementParameter().addAll(elemParam);
			compNode.getElementParameter().add(elemparamTgtXml);
			compNode.getElementParameter().add(elemParam1);
			compNode.getElementParameter().add(elemparam2);
			compNode.getElementParameter().addAll(elemParam3);
		}
		/*else
		{
			ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
			
			
			elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT", "UNIQUE_NAME", recordperPipe.compName,"false"));
			elemParam.add(Utils.getPropDtlsforExp("FILE","FILENAME",srctgtdb.directoryPath));
			elemParam.add(Utils.getPropDtlsforExp("CHECK", "INPUT_IS_DOCUMENT", "false"));
			//doubt
			elemParam.add(Utils.getPropDtlsWithShwforExp("COLUMN_LIST", "DOCUMENT_COL", "Emp_ID", "false"));
			elemParam.add(Utils.getPropDtlsforExp("TEXT", "ROW_TAG", "\"DATA\""));
			//dount end
			elemParam.add(Utils.getPropDtlsforExp("CHECK", "SPLIT", "false"));
			elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT", "SPLIT_EVERY", "1000", "false"));
			elemParam.add(Utils.getPropDtlsforExp("CHECK", "CREATE", "true"));
			ElementParameter elemparamTgtXml1 = new ElementParameter();
			elemparamTgtXml1.setField("TABLE");
			elemparamTgtXml1.setName("ROOT_TAGS");
			ElementParameter elemparamTgtXml = new ElementParameter();
			elemparamTgtXml.setField("TABLE");
			elemparamTgtXml.setName("Mapping");
			for(int i = 0 ; i < srctgtdb.srcTgtColsList.size(); i++)
			{
				ColumnDTO tgtCols = srctgtdb.srcTgtColsList.get(i);
				String colsName = tgtCols.colStruct.name;
				String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				List<ElementParameter.ElementValue> elemValueList = new ArrayList<ElementParameter.ElementValue>();
		    	elemValueList.add(Utils.getPropElemforTrans("SCHEMA_COLUMN", colName));
		    	elemValueList.add(Utils.getPropElemforTrans("AS_ATTRIBUTE", "false"));
		    	elemValueList.add(Utils.getPropElemforTrans("SCHEMA_COLUMN_NAME", "true"));
		    	elemValueList.add(Utils.getPropElemforTrans("LABEL", "\"label\""));
		    	elemparamTgtXml.getElementValue().addAll(elemValueList);
			}
			
			ArrayList<ElementParameter> elemParam1 = new ArrayList<ElementParameter>();
			elemParam1.add(Utils.getPropDtlsforExp("CHECK", "USE_DYNAMIC_GROUPING", "false"));
			elemParam1.add(Utils.getPropDtlsforExp("TABLE", "GROUP_BY", "false"));
			elemParam1.add(Utils.getPropDtlsforExp("CHECK", "FLUSHONROW", "false"));
			elemParam1.add(Utils.getPropDtlsWithShwforExp("TEXT", "FLUSHONROW_NUM", "1", "false"));
			elemParam1.add(Utils.getPropDtlsforExp("CHECK", "ADVANCED_SEPARATOR", "false"));
			elemParam1.add(Utils.getPropDtlsWithShwforExp("TEXT", "THOUSANDS_SEPARATOR", "\",\"", "false"));
			elemParam1.add(Utils.getPropDtlsWithShwforExp("TEXT", "DECIMAL_SEPARATOR", "\".\"", "false"));
			elemParam1.add(Utils.getPropDtlsforExp("ENCODING_TYPE", "ENCODING", "\"ISO-8859-15\""));
			elemParam1.add(Utils.getPropDtlsforExp("TECHNICAL", "ENCODING:ENCODING_TYPE", "ISO-8859-15"));
			elemParam1.add(Utils.getPropDtlsforExp("CHECK", "DELETE_EMPTYFILE", "false"));
			elemParam1.add(Utils.getPropDtlsWithShwforExp("CHECK", "TRIM", "false","false"));
			elemParam1.add(Utils.getPropDtlsforExp("TEXT", "CONNECTION_FORMAT", "row"));
			
			compNode.getElementParameter().addAll(elemParam);
			compNode.getElementParameter().add(elemparamTgtXml);
			compNode.getElementParameter().add(elemparamTgtXml1);
			compNode.getElementParameter().addAll(elemParam1);		
		}*/
		
		
		
	}

	public static void fillPropsforDBTgt(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb, DataMapping dataMappingObj, List<String> cntxtParamsList)
	{
		String tlndTranstype = "";
		int dataBaseId = 0 ;
		if(srctgtdb.fileType.equalsIgnoreCase("Relational - Netezza"))
		{
			tlndTranstype = "Netezza";
			dataBaseId = ToolTypeConstants.NETEZZA;
		}
		else if(srctgtdb.fileType.equalsIgnoreCase("Relational - Teradata")){
			tlndTranstype = "Teradata";
			dataBaseId = ToolTypeConstants.TERADATA;
		}
		else if(srctgtdb.fileType.equalsIgnoreCase("Relational - DB2 LUW"))
		{
			tlndTranstype = "tDB2Output";
			dataBaseId = ToolTypeConstants.DB2;
		}
		else if(srctgtdb.fileType.equalsIgnoreCase("Relational - Oracle"))
		{
			tlndTranstype = "Oracle";
			dataBaseId = ToolTypeConstants.ORACLE;
		}
		else if(srctgtdb.fileType.equalsIgnoreCase("Relational - ODBC"))
		{
			tlndTranstype = "Oracle";
			dataBaseId = ToolTypeConstants.ORACLE;
		}
		else if(srctgtdb.fileType.equalsIgnoreCase("Relational - Amazon Redshift"))
		{
			tlndTranstype = "Redshift";
			dataBaseId = ToolTypeConstants.REDSHIFT;
		}
		else if(srctgtdb.fileType.equalsIgnoreCase("Relational - SQL Server") || srctgtdb.fileType.equalsIgnoreCase("Relational - Azure SQL DB") || srctgtdb.fileType.equalsIgnoreCase("Relational - Azure SQL Data Warehouse"))
		{
			tlndTranstype = "MSSQL";
			dataBaseId = ToolTypeConstants.SQLSERVER;
		}
		else if(srctgtdb.fileType.equalsIgnoreCase("MongoDb"))
		{
			tlndTranstype = "tMongoDBOutput";
			dataBaseId = ToolTypeConstants.MONGODB;
		}
		else if(srctgtdb.fileType.equalsIgnoreCase("Relational - Snowflake DW"))
		{
			tlndTranstype = "tSnowflakeOutput";
			dataBaseId = ToolTypeConstants.SNOWFLAKE;
		}
		
		String compName = recordperPipe.compName;;
		String dbName = srctgtdb.connectionName;
		String tblName = srctgtdb.tableName;
		String dataAction ="Insert";
		String tableAction = "NONE";
		if(srctgtdb.loadStrategy.equalsIgnoreCase("insert"))
		{
		  dataAction = "Insert";
		}
		if(srctgtdb.loadStrategy.equalsIgnoreCase("truncate") || srctgtdb.loadStrategy.equalsIgnoreCase("truncate and load") )
		{
		    tableAction = "Truncate table";
		}
		else if(srctgtdb.loadStrategy.equalsIgnoreCase("delete"))
		{
			dataAction = "Delete";
		}
		else if(srctgtdb.loadStrategy.equalsIgnoreCase("update"))
		{
			dataAction = "Update";
		}
		else if(srctgtdb.loadStrategy.equalsIgnoreCase("insert or update"))
		{
			dataAction = "Insert of update";
		}
		
		
		
		HashMap<String,String> uNamePwd = Utils.getUnamePwd(docspecs, srctgtdb);
		
		String uName = "";
		String pwd = "";
		
		if(uNamePwd.get("username")!=null){
			uName = uNamePwd.get("username");
		}
		else{
			uName = "";
		}
		
		
		if(uNamePwd.get("pwd")!=null){
			pwd = uNamePwd.get("pwd");
		}
		else{
			pwd = "";
		}
		
		
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
	
		if(srctgtdb.fileType.equalsIgnoreCase("Relational - Snowflake DW"))
		{
			elemParam.add(Utils.getPropDtlsWithShwforExp("QUERYSTORE_TYPE","QUERYSTORE","","false"));
			elemParam.add(Utils.getPropDtlsWithShwforExp("TECHNICAL","QUERYSTORE:REPOSITORY_QUERYSTORE_TYPE","","false"));
			elemParam.add(Utils.getPropDtlsforExp("TECHNICAL","QUERYSTORE:QUERYSTORE_TYPE","BUILT_IN"));
			elemParam.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
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
				String comment = srcTgtCols.colStruct.description;

				if(comment!=null){
					comment = comment.replace("\"", "").replace("(", "").replace(")", "").replace(",", "").replace("\nSearchable? N\\20", "").replace("\\", "").replace("-", "").replace("\n", "");
				}else{
					comment="";
				}
				
				String length = "";
				String precision = "";
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
				if(srcTgtCols.srcColumn != null)
				{
					//srcColsAdd += "{\\\"name\\\":\\\""+colName+"\\\",\\\"type\\\":[\\\"string\\\",\\\"null\\\"],\\\"di.table.comment\\\":\\\"\\\",\\\"AVRO_TECHNICAL_KEY\\\":\\\""+colName+"\\\",\\\"talend.field.dbType\\\":\\\"\\\",\\\"talend.field.dbColumnName\\\":\\\""+colName+"\\\",\\\"di.column.talendType\\\":\\\""+newDataTyp+"\\\",\\\"di.column.isNullable\\\":\\\"true\\\",\\\"talend.field.pattern\\\":\\\"\\\",\\\"di.column.relationshipType\\\":\\\"\\\",\\\"di.table.label\\\":\\\""+colName+"\\\",\\\"di.column.relatedEntity\\\":\\\"\\\"},";
					srcColsAdd += "{\\\"name\\\":\\\""+colName+"\\\","+DBType+",\\\"AVRO_TECHNICAL_KEY\\\":\\\""+colName+"\\\",\\\"talend.field.dbType\\\":\\\"\\\",\\\"di.column.talendType\\\":\\\""+newDataTyp+"\\\",\\\"talend.field.pattern\\\":\\\"\\\",\\\"di.table.label\\\":\\\""+colName+"\\\",\\\"talend.field.precision\\\":\\\""+precision+"\\\",\\\"di.table.comment\\\":\\\""+comment+"\\\",\\\"talend.field.dbColumnName\\\":\\\""+colName+"\\\",\\\"di.column.isNullable\\\":\\\""+isNullable+"\\\",\\\"talend.field.length\\\":\\\""+length+"\\\",\\\"di.column.relationshipType\\\":\\\"\\\",\\\"di.column.originalLength\\\":\\\""+length+"\\\",\\\"di.column.relatedEntity\\\":\\\"\\\"},";
				}
				
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
			String account = "tjx.test.azure";
			String userid = "context.TJXASVC_EU_AGILONE_SHARE_USER";
			cntxtParamsList.add("TJXASVC_EU_AGILONE_SHARE_USER");
			String password = "";
			String dataWareHouse = "context.DEMO_WH";
			cntxtParamsList.add("DEMO_WH");
			
			elemProps.setValue("{\"@id\":3,\"@type\":\"org.talend.components.snowflake.tsnowflakeoutput.TSnowflakeOutputProperties\",\"__version\":1,\"outputAction\":{\"@type\":\"org.talend.daikon.properties.property.EnumProperty\",\"flags\":null,\"storedValue\":{\"@type\":\"org.talend.components.snowflake.tsnowflakeoutput.TSnowflakeOutputProperties$OutputAction\",\"name\":\"INSERT\"},\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":{\"@type\":\"java.util.Arrays$ArrayList\",\"@items\":[{\"@type\":\"org.talend.components.snowflake.tsnowflakeoutput.TSnowflakeOutputProperties$OutputAction\",\"name\":\"INSERT\"},{\"@type\":\"org.talend.components.snowflake.tsnowflakeoutput.TSnowflakeOutputProperties$OutputAction\",\"name\":\"UPDATE\"},{\"@type\":\"org.talend.components.snowflake.tsnowflakeoutput.TSnowflakeOutputProperties$OutputAction\",\"name\":\"UPSERT\"},{\"@type\":\"org.talend.components.snowflake.tsnowflakeoutput.TSnowflakeOutputProperties$OutputAction\",\"name\":\"DELETE\"}]},\"currentType\":\"org.talend.components.snowflake.tsnowflakeoutput.TSnowflakeOutputProperties.OutputAction\",\"name\":\"outputAction\",\"tags\":null},\"upsertKeyColumn\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":{\"@type\":\"java.util.RegularEnumSet\",\"@items\":[{\"@type\":\"org.talend.daikon.properties.property.Property$Flags\",\"name\":\"HIDDEN\"}]},\"storedValue\":null,\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":{\"@type\":\"java.util.ArrayList\",\"@items\":[\"newColumn\",\"newColumn1\"]},\"currentType\":\"java.lang.String\",\"name\":\"upsertKeyColumn\",\"tags\":null},\"schemaReject\":{\"schema\":{\"@type\":\"org.talend.daikon.properties.property.SchemaProperty\",\"flags\":null,\"storedValue\":\"{\\\"type\\\":\\\"record\\\",\\\"name\\\":\\\"rejectOutput\\\",\\\"fields\\\":[{\\\"name\\\":\\\"columnName\\\",\\\"type\\\":\\\"string\\\",\\\"talend.isLocked\\\":\\\"false\\\",\\\"talend.field.generated\\\":\\\"true\\\",\\\"talend.field.length\\\":\\\"255\\\"},{\\\"name\\\":\\\"rowNumber\\\",\\\"type\\\":\\\"string\\\",\\\"talend.isLocked\\\":\\\"false\\\",\\\"talend.field.generated\\\":\\\"true\\\",\\\"talend.field.length\\\":\\\"255\\\"},{\\\"name\\\":\\\"category\\\",\\\"type\\\":\\\"string\\\",\\\"talend.isLocked\\\":\\\"false\\\",\\\"talend.field.generated\\\":\\\"true\\\",\\\"talend.field.length\\\":\\\"255\\\"},{\\\"name\\\":\\\"character\\\",\\\"type\\\":\\\"string\\\",\\\"talend.isLocked\\\":\\\"false\\\",\\\"talend.field.generated\\\":\\\"true\\\",\\\"talend.field.length\\\":\\\"255\\\"},{\\\"name\\\":\\\"errorMessage\\\",\\\"type\\\":\\\"string\\\",\\\"talend.isLocked\\\":\\\"false\\\",\\\"talend.field.generated\\\":\\\"true\\\",\\\"talend.field.length\\\":\\\"255\\\"},{\\\"name\\\":\\\"byteOffset\\\",\\\"type\\\":\\\"string\\\",\\\"talend.isLocked\\\":\\\"false\\\",\\\"talend.field.generated\\\":\\\"true\\\",\\\"talend.field.length\\\":\\\"255\\\"},{\\\"name\\\":\\\"line\\\",\\\"type\\\":\\\"string\\\",\\\"talend.isLocked\\\":\\\"false\\\",\\\"talend.field.generated\\\":\\\"true\\\",\\\"talend.field.length\\\":\\\"255\\\"},{\\\"name\\\":\\\"sqlState\\\",\\\"type\\\":\\\"string\\\",\\\"talend.isLocked\\\":\\\"false\\\",\\\"talend.field.generated\\\":\\\"true\\\",\\\"talend.field.length\\\":\\\"255\\\"},{\\\"name\\\":\\\"code\\\",\\\"type\\\":\\\"string\\\",\\\"talend.isLocked\\\":\\\"false\\\",\\\"talend.field.generated\\\":\\\"true\\\",\\\"talend.field.length\\\":\\\"255\\\"}]}\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\"},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"org.apache.avro.Schema\",\"name\":\"schema\",\"tags\":null},\"name\":\"schemaReject\",\"validationResult\":null,\"tags\":null},\"convertColumnsAndTableToUppercase\":{\"flags\":null,\"storedValue\":{\"@type\":\"boolean\",\"value\":true},\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":false,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.Boolean\",\"name\":\"convertColumnsAndTableToUppercase\",\"tags\":null},\"connection\":{\"@id\":4,\"__version\":1,\"name\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,\"storedValue\":null,\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\"},\"size\":-1,\"occurMinTimes\":1,\"occurMaxTimes\":1,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"name\",\"tags\":null},\"loginTimeout\":{\"@id\":7,\"flags\":null,\"storedValue\":{\"@type\":\"int\",\"value\":15},\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.Integer\",\"name\":\"loginTimeout\",\"tags\":null},\"account\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,"
					+ "\"storedValue\":\"\\\""+account+"\\\"\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":1,\"occurMaxTimes\":1,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"account\",\"tags\":null},\"userPassword\":{\"useAuth\":{\"flags\":null,\"storedValue\":{\"@type\":\"boolean\",\"value\":false},\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\"},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.Boolean\",\"name\":\"useAuth\",\"tags\":null},\"userId\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,"
							+ "\"storedValue\":\""+userid+"\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":1,\"occurMaxTimes\":1,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"userId\",\"tags\":null},\"password\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":{\"@type\":\"java.util.RegularEnumSet\",\"@items\":[{\"@type\":\"org.talend.daikon.properties.property.Property$Flags\",\"name\":\"ENCRYPT\"},{\"@type\":\"org.talend.daikon.properties.property.Property$Flags\",\"name\":\"SUPPRESS_LOGGING\"}]},\"storedValue\":\"0RMsyjmybrE=\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":1,\"occurMaxTimes\":1,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"password\",\"tags\":null},\"needSwitch\":false,\"name\":\"userPassword\",\"validationResult\":null,\"tags\":null},\"warehouse\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,"
									+ "\"storedValue\":\""+dataWareHouse+"\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"warehouse\",\"tags\":null},\"db\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,"
											+ "\"storedValue\":\""+updatedConnection+"\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":1,\"occurMaxTimes\":1,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"db\",\"tags\":null},\"schemaName\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,"
											+ "\"storedValue\":\""+updatedSchema+"\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":1,\"occurMaxTimes\":1,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"schemaName\",\"tags\":null},\"role\":{\"@id\":6,\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,\"storedValue\":\"\\\"\\\"\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"role\",\"tags\":null},\"tracing\":{\"@id\":5,\"@type\":\"org.talend.daikon.properties.property.EnumProperty\",\"flags\":null,\"storedValue\":{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"OFF\",\"name\":\"OFF\"},\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":{\"@type\":\"java.util.Arrays$ArrayList\",\"@items\":[{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"OFF\",\"name\":\"OFF\"},{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"SEVERE\",\"name\":\"SEVERE\"},{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"WARNING\",\"name\":\"WARNING\"},{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"INFO\",\"name\":\"INFO\"},{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"CONFIG\",\"name\":\"CONFIG\"},{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"FINE\",\"name\":\"FINE\"},{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"FINER\",\"name\":\"FINER\"},{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"FINEST\",\"name\":\"FINEST\"},{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionProperties$Tracing\",\"value\":\"ALL\",\"name\":\"ALL\"}]},\"currentType\":\"org.talend.components.snowflake.SnowflakeConnectionProperties.Tracing\",\"name\":\"tracing\",\"tags\":null},\"testConnection\":{\"formtoShow\":null,\"name\":\"testConnection\",\"tags\":null},\"advanced\":{\"formtoShow\":{\"subtitle\":null,\"properties\":{\"@ref\":4},\"widgetMap\":{\"loginTimeout\":{\"@type\":\"org.talend.daikon.properties.presentation.Widget\",\"row\":1,\"order\":1,\"hidden\":false,\"widgetType\":\"widget.type.default\",\"longRunning\":false,\"deemphasize\":false,\"callBeforeActivate\":false,\"callBeforePresent\":false,\"callValidate\":false,\"callAfter\":false,\"content\":{\"@ref\":7},\"configurationValues\":{\"@type\":\"java.util.HashMap\"}},\"tracing\":{\"@type\":\"org.talend.daikon.properties.presentation.Widget\",\"row\":2,\"order\":1,\"hidden\":false,\"widgetType\":\"widget.type.enumeration\",\"longRunning\":false,\"deemphasize\":false,\"callBeforeActivate\":false,\"callBeforePresent\":false,\"callValidate\":false,\"callAfter\":false,\"content\":{\"@ref\":5},\"configurationValues\":{\"@type\":\"java.util.HashMap\"}},\"role\":{\"@type\":\"org.talend.daikon.properties.presentation.Widget\",\"row\":3,\"order\":1,\"hidden\":false,\"widgetType\":\"widget.type.default\",\"longRunning\":false,\"deemphasize\":false,\"callBeforeActivate\":false,\"callBeforePresent\":false,\"callValidate\":false,\"callAfter\":false,\"content\":{\"@ref\":6},\"configurationValues\":{\"@type\":\"java.util.HashMap\"}}},\"cancelable\":false,\"originalValues\":null,\"callBeforeFormPresent\":false,\"callAfterFormBack\":false,\"callAfterFormNext\":false,\"callAfterFormFinish\":false,\"allowBack\":false,\"allowForward\":false,\"allowFinish\":false,\"refreshUI\":true,\"name\":\"Advanced\",\"tags\":null},\"name\":\"advanced\",\"tags\":null},\"referencedComponent\":{\"referenceType\":{\"@type\":\"org.talend.daikon.properties.property.EnumProperty\",\"flags\":null,\"storedValue\":null,\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\"},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":{\"@type\":\"java.util.Arrays$ArrayList\",\"@items\":[{\"@type\":\"org.talend.components.api.properties.ComponentReferenceProperties$ReferenceType\",\"name\":\"THIS_COMPONENT\"},{\"@type\":\"org.talend.components.api.properties.ComponentReferenceProperties$ReferenceType\",\"name\":\"COMPONENT_TYPE\"},{\"@type\":\"org.talend.components.api.properties.ComponentReferenceProperties$ReferenceType\",\"name\":\"COMPONENT_INSTANCE\"}]},\"currentType\":\"org.talend.components.api.properties.ComponentReferenceProperties&lt;P extends org.talend.daikon.properties.Properties>.ReferenceType\",\"name\":\"referenceType\",\"tags\":null},\"componentInstanceId\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,\"storedValue\":null,\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\"},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"componentInstanceId\",\"tags\":null},\"referenceDefinitionName\":{\"@type\":\"org.talend.daikon.properties.property.StringProperty\",\"possibleValues2\":null,\"flags\":null,\"storedValue\":\"tSnowflakeConnection\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\"},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"referenceDefinitionName\",\"tags\":null},\"reference\":null,\"name\":\"referencedComponent\",\"validationResult\":null,\"tags\":null},\"org.talend.daikon.properties.PropertiesImpl.name\":\"connection\",\"validationResult\":null,\"tags\":null},\"table\":{\"@id\":2,\"@type\":\"org.talend.components.snowflake.tsnowflakeoutput.TSnowflakeOutputProperties$TableSubclass\",\"this$0\":{\"@ref\":3},\"connection\":{\"@ref\":4},\"tableName\":{\"possibleValues2\":null,\"flags\":null,"
													+ "\"storedValue\":\"\\\""+updatedTable+"\\\"\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":true,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"java.lang.String\",\"name\":\"tableName\",\"tags\":null},\"schemaListener\":{\"@type\":\"org.talend.components.snowflake.SnowflakeConnectionTableProperties$1\",\"this$0\":{\"@ref\":3}},\"main\":{\"@type\":\"org.talend.components.snowflake.SnowflakeTableProperties$1\",\"this$0\":{\"@ref\":2},\"schema\":{\"@type\":\"org.talend.daikon.properties.property.SchemaProperty\",\"flags\":null,\"storedValue\":\"{\\\"type\\\":\\\"record\\\",\\\"name\\\":\\\"MAIN\\\",\\\"fields\\\":["
															+ srcColsAdd
															+ "]"
															+ ",\\\"di.table.name\\\":\\\"tDBOutput_1\\\",\\\"di.table.label\\\":\\\"MAIN\\\"}\",\"storedDefaultValue\":null,\"children\":{\"@type\":\"java.util.ArrayList\"},\"taggedValues\":{\"@type\":\"java.util.HashMap\",\"SUPPORT_CONTEXT\":false,\"IS_DYNAMIC\":false},\"size\":-1,\"occurMinTimes\":0,\"occurMaxTimes\":0,\"precision\":0,\"pattern\":null,\"nullable\":false,\"possibleValues\":null,\"currentType\":\"org.apache.avro.Schema\",\"name\":\"schema\",\"tags\":null},\"name\":\"main\",\"validationResult\":{\"@id\":1,\"status\":{\"name\":\"OK\"},\"message\":null,\"number\":0},\"tags\":null},\"name\":\"table\",\"validationResult\":{\"@type\":\"org.talend.daikon.properties.ValidationResultMutable\",\"status\":{\"name\":\"ERROR\"},\"message\":\"java.lang.IllegalArgumentException: Missing account\",\"number\":0},\"tags\":null},\"name\":\"root\",\"validationResult\":{\"@ref\":1},\"tags\":null}");
		
			getRejectDtlsForDBs(compNode);
			compNode.getElementParameter().addAll(elemParam);
			compNode.getElementParameter().add(elemProps);
		}
		else
		{
		   
			elemParam.add(Utils.getPropDtlsforExp("CHECK","USE_EXISTING_CONNECTION","false"));
			elemParam.add(Utils.getPropDtlsWithShwforExp("COMPONENT_LIST","CONNECTION","","false"));
			
			if(srctgtdb.fileType.equalsIgnoreCase("MongoDb"))
			{	
				elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","DB_VERSION","MONGODB_3_5_X"));
				elemParam.add(Utils.getPropDtlsforExp("CHECK","USE_REPLICA_SET","false"));
				elemParam.add(Utils.getPropDtlsforExp("TABLE","REPLICA_SET","false"));
				elemParam.add(Utils.getPropDtlsforExp("TEXT","HOST","\"localhost\""));
				elemParam.add(Utils.getPropDtlsforExp("TEXT","PORT","27017"));
				elemParam.add(Utils.getPropDtlsforExp("TEXT","DATABASE","\"local\""));
				elemParam.add(Utils.getPropDtlsforExp("CHECK","USE_SSL","false"));
				elemParam.add(Utils.getPropDtlsforExp("CHECK","SET_WRITE_CONCERN","false"));
				elemParam.add(Utils.getPropDtlsWithShwforExp("CLOSED_LIST","WRITE_CONCERN","ACKNOWLEDGED","false"));
				elemParam.add(Utils.getPropDtlsforExp("CHECK","SET_BULK_WRITE","false"));
				elemParam.add(Utils.getPropDtlsWithShwforExp("CLOSED_LIST","BULK_WRITE_TYPE","Unordered","false"));
				elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","BULK_WRITE_SIZE","\"1000\"","false"));
				elemParam.add(Utils.getPropDtlsforExp("CHECK","REQUIRED_AUTHENTICATION","false"));
				elemParam.add(Utils.getPropDtlsWithShwforExp("CLOSED_LIST","AUTHENTICATION_MECHANISM","NEGOTIATE_MEC","false"));
				elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","SET_AUTHENTICATION_DATABASE","false","false"));
				elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","AUTHENTICATION_DATABASE","\"\"","false"));
				elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","USERNAME","\"\"","false"));
				elemParam.add(Utils.getPropDtlsWithShwforExp("PASSWORD","PASSWORD","0RMsyjmybrE=","false"));
				elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","KRB_USER_PRINCIPAL","\"mongouser@EXAMPLE.COM\"","false"));
				elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","KRB_REALM","\"EXAMPLE.COM\"","false"));
				elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","KRB_KDC","\"kdc.example.com\"","false"));
				elemParam.add(Utils.getPropDtlsforExp("TEXT","COLLECTION","\""+srctgtdb.tableName+"\""));
				elemParam.add(Utils.getPropDtlsforExp("CHECK","DROP_COLLECTION_CREATE","false"));
				elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","DATA_ACTION","INSERT"));
				elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","UPDATE_ALL","false","false"));
				
				ElementParameter elemparamTgtDb = new ElementParameter();
				elemparamTgtDb.setField("TABLE");
				elemparamTgtDb.setName("MAPPING");
				
				for(int i = 0 ; i < srctgtdb.srcTgtColsList.size(); i++)
				{
					ColumnDTO tgtCols = srctgtdb.srcTgtColsList.get(i);
					String colsName = tgtCols.colStruct.name;
					String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
					List<ElementParameter.ElementValue> elemValueList = new ArrayList<ElementParameter.ElementValue>();
			    	elemValueList.add(Utils.getPropElemforTrans("SCHEMA_COLUMN", colName));
			    	elemValueList.add(Utils.getPropElemforTrans("PARENT_NODE_PATH", "\"\""));
			    	elemValueList.add(Utils.getPropElemforTrans("REMOVE_NULL_FIELD", "false"));
			    	elemparamTgtDb.getElementValue().addAll(elemValueList);
				}
				
				
				ArrayList<ElementParameter> elemParam1 = new ArrayList<ElementParameter>();
				elemParam1.add(Utils.getPropDtlsforExp("CHECK","DIE_ON_ERROR","false"));
				elemParam1.add(Utils.getPropDtlsforExp("CHECK","QUERYOPTION_NOTIMEOUT","false"));
				
				compNode.getElementParameter().addAll(elemParam);
				
			    if(srctgtdb.recordMarkerList.size() > 0)
			    {
			    	elemParam1.add(Utils.getPropDtlsforExp("CHECK","IS_VIRTUAL_COMPONENT","true"));
			    
					String getRootPath = getRootPathFromColsStruct(srctgtdb);
					
					getRootPath = getUpdatedXPath(getRootPath);
					
					String rootPathVal = "";
					if(getRootPath.contains("/"))
					{
						String[] val = getRootPath.split("/");
						
						rootPathVal = val[1];
					}
					else
						rootPathVal = getRootPath;
					
					LinkedHashMap<String, ArrayList<String>> groupHashVal = new LinkedHashMap<String, ArrayList<String>>();
					
					getTreeStructVal(getRootPath, srctgtdb, groupHashVal);
					
				    ArrayList<String> rootVals = groupHashVal.get("Root");
				    
				    
				    ElementParameter elemParamRoot = new ElementParameter();
			    	elemParamRoot.setField("TABLE");
			    	elemParamRoot.setName("ROOT");
			    	elemParamRoot.setShow("false");
			    	
			    	List<ElementParameter.ElementValue> elemValueList = new ArrayList<ElementParameter.ElementValue>();
			    	elemValueList.add(Utils.getPropElemforTrans("PATH", "/"+rootPathVal));
			    	elemValueList.add(Utils.getPropElemforTrans("COLUMN", ""));
			    	elemValueList.add(Utils.getPropElemforTrans("VALUE", ""));
			    	elemValueList.add(Utils.getPropElemforTrans("ATTRIBUTE", "main"));
			    	elemValueList.add(Utils.getPropElemforTrans("ORDER", "1"));
			    	

			    	elemParamRoot.getElementValue().addAll(elemValueList);
			    	
			    	ElementParameter elemGroupBy = new ElementParameter();
			    	elemGroupBy.setField("TABLE");
			      	elemGroupBy.setName("GROUPBYS");
			    	
				    int orderKey  = 2;
				    int endOrder = 0;
				    
				    int rootOrder = fillElemParamList(orderKey, rootVals, "Root", elemGroupBy, elemParamRoot, endOrder);
				    
				    //ArrayList<String> GroupVals = groupHashVal.get("Group");
				 
				    //ArrayList<ArrayList<String>> getGroupElemnts = getOjectElementGroups(GroupVals);
				    
				    ArrayList<String> LoopVals = groupHashVal.get("Loop");
				    
				    ElementParameter elemParamLoop = new ElementParameter();
				    elemParamLoop.setField("TABLE");
				    elemParamLoop.setName("LOOP");
				    elemParamLoop.setShow("false");
				    
				    String loopPath = srctgtdb.recordMarkerList.get(0);
					
					loopPath = getUpdatedXPath(loopPath);
			    	
			    	List<ElementParameter.ElementValue> elemLoopValList = new ArrayList<ElementParameter.ElementValue>();
			    	elemLoopValList.add(Utils.getPropElemforTrans("PATH", "/"+loopPath));
			    	elemLoopValList.add(Utils.getPropElemforTrans("COLUMN", ""));
			    	elemLoopValList.add(Utils.getPropElemforTrans("VALUE", ""));
			    	elemLoopValList.add(Utils.getPropElemforTrans("ATTRIBUTE", "main"));
			    	elemLoopValList.add(Utils.getPropElemforTrans("ORDER", ""+rootOrder+1));
			    	

			    	elemParamLoop.getElementValue().addAll(elemLoopValList);
			    	
				    int LoopOrder = fillElemParamList(rootOrder+2, LoopVals, "Loop", elemGroupBy, elemParamLoop, endOrder);
				 
				    compNode.getElementParameter().add(elemParamRoot);
				    compNode.getElementParameter().add(elemParamLoop);
				    compNode.getElementParameter().add(elemGroupBy);
					
			    }
			    else
			    {
			    	elemParam1.add(Utils.getPropDtlsforExp("CHECK","IS_VIRTUAL_COMPONENT","false"));
					elemParam1.add(Utils.getPropDtlsWithShwforExp("TABLE","ROOT","","false"));
					elemParam1.add(Utils.getPropDtlsWithShwforExp("TABLE","GROUP","","false"));
					elemParam1.add(Utils.getPropDtlsWithShwforExp("TABLE","LOOP","","false"));
					elemParam1.add(Utils.getPropDtlsWithShwforExp("TABLE","GROUPBYS","","false"));
			    }
			    
				elemParam1.add(Utils.getPropDtlsWithShwforExp("EXTERNAL","MAP","","false"));
				elemParam1.add(Utils.getPropDtlsWithShwforExp("CHECK","CREATE_EMPTY_ELEMENT","true","false"));
				elemParam1.add(Utils.getPropDtlsWithShwforExp("CHECK","REMOVE_ROOT","true","false"));
				elemParam1.add(Utils.getPropDtlsWithShwforExp("TEXT","DATA_NODE","\"\"","false"));
				elemParam1.add(Utils.getPropDtlsWithShwforExp("TEXT","QUERY_NODE","\"\"","false"));
				elemParam1.add(Utils.getPropDtlsWithShwforExp("CHECK","COMPACT_FORMAT","true","false"));
				elemParam1.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
				
				
				compNode.getElementParameter().add(elemparamTgtDb);
				compNode.getElementParameter().addAll(elemParam1);
			}
			else{
				
				if(srctgtdb.fileType.equalsIgnoreCase("Relational - Oracle"))
				{
					elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","CONNECTION_TYPE","ORACLE_SID"));
					elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","DB_VERSION","ORACLE_12"));
					elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","RAC_URL","\"\"","false"));
					elemParam.add(Utils.getPropDtlsforExp("TEXT","HOST","\"\""));
					elemParam.add(Utils.getPropDtlsforExp("TEXT","PORT","\"1521\""));
					elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","LOCAL_SERVICE_NAME","\"\"","false"));
					elemParam.add(Utils.getPropDtlsforExp("TEXT","TABLESCHEMA","\"\""));
					elemParam.add(Utils.getPropDtlsWithShwforExp("LABEL","NOTE","Warning : this component configuration will automatically generate a commit before data insert/update/delete","false"));
					elemParam.add(Utils.getPropDtlsforExp("LABEL","NOTE","This option only applies when deploying and running in the Talend Runtime"));
					elemParam.add(Utils.getPropDtlsforExp("CHECK","SPECIFY_DATASOURCE_ALIAS","false"));
					elemParam.add(Utils.getPropDtlsforExp("TEXT","DATASOURCE_ALIAS","\"\""));
					elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","JDBC_URL","\"jdbc:oracle:oci:/@talend\"","false"));
					elemParam.add(Utils.getPropDtlsforExp("LABEL","NOTE","*Note: Example for Additional JDBC Parameters: \"parameterName1=value1&amp;&amp;parameterName2=value2\""));
					elemParam.add(Utils.getPropDtlsforExp("CHECK","USE_HINT_OPTIONS","false"));
					elemParam.add(Utils.getPropDtlsforExp("TABLE","HINT_OPTIONS","false"));
					elemParam.add(Utils.getPropDtlsforExp("CHECK","ENABLE_DEBUG_MODE","false"));
					elemParam.add(Utils.getPropDtlsforExp("CHECK","CONVERT_COLUMN_TABLE_TO_UPPERCASE","false"));
					elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","USE_BATCH_AND_USE_CONN","false","false"));
					elemParam.add(Utils.getPropDtlsforExp("CHECK","USE_TIMESTAMP_FOR_DATE_TYPE","true"));
					elemParam.add(Utils.getPropDtlsforExp("CHECK","TRIM_CHAR","true"));
				}
				else
				{
					elemParam.add(Utils.getPropDtlsforExp("TEXT","HOST","\"\""));
					elemParam.add(Utils.getPropDtlsforExp("TEXT","PORT","\"5480\""));
					
					if(srctgtdb.fileType.equalsIgnoreCase("Relational - SQL Server") || srctgtdb.fileType.equalsIgnoreCase("Relational - Azure SQL DB") || srctgtdb.fileType.equalsIgnoreCase("Relational - Azure SQL Data Warehouse")){
						elemParam.add(Utils.getPropDtlsforExp("LABEL", "NOTE", "NOTE: batch size must be less than or equal to total number of parameter markers divided by number of columns"));
						if(srctgtdb.fileType.equalsIgnoreCase("Relational - SQL Server")){
							elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","DRIVER","JTDS"));
						}
						else{
							elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","DRIVER","MSSQL_PROP"));
						}
					}
					else{
						elemParam.add(Utils.getPropDtlsforExp("LABEL", "NOTE", "(To use this component, you need first to add nzjdbc.jar in the Modules view)"));
					}
				}
				
				String updatedCompDb = Utils.getParamValWithContext(dbName, docspecs, cntxtParamsList);
				String updatedUserName = Utils.getParamValWithContext(uName, docspecs, cntxtParamsList);
				elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","TYPE",tlndTranstype,"false"));
				elemParam.add(Utils.getPropDtlsforExp("TEXT","DBNAME",updatedCompDb));
				elemParam.add(Utils.getPropDtlsforExp("TEXT","USER",updatedUserName));
				elemParam.add(Utils.getPropDtlsforExp("PASSWORD","PASS",pwd));
				elemParam.add(Utils.getPropDtlsforExp("DBTABLE","TABLE",tblName));
				elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","TABLE_ACTION",tableAction));
				elemParam.add(Utils.getPropDtlsforExp("CLOSED_LIST","DATA_ACTION",dataAction));
				elemParam.add(Utils.getPropDtlsforExp("CHECK","DIE_ON_ERROR","false"));
				if(tlndTranstype.equalsIgnoreCase("MSSQL")){
					elemParam.add(Utils.getPropDtlsWithShwforExp("MAPPING_TYPE","MAPPING","id_"+tlndTranstype,"false"));
				}
				else{
					elemParam.add(Utils.getPropDtlsWithShwforExp("MAPPING_TYPE","MAPPING",tlndTranstype.toLowerCase()+"_id","false"));
				}
				
				elemParam.add(Utils.getPropDtlsforExp("TEXT","PROPERTIES","\"noDatetimeStringSync=true\""));
				elemParam.add(Utils.getPropDtlsforExp("CHECK","USE_BATCH_SIZE","true"));
				elemParam.add(Utils.getPropDtlsforExp("TEXT","BATCH_SIZE","10000"));
				elemParam.add(Utils.getPropDtlsWithShwforExp("ENCODING_TYPE","ENCODING","\"ISO-8859-15\"","false"));
				elemParam.add(Utils.getPropDtlsforExp("TECHNICAL","ENCODING:ENCODING_TYPE","ISO-8859-15"));
				elemParam.add(Utils.getPropDtlsforExp("TEXT","COMMIT_EVERY","10000"));
				
				ElementParameter elemparam1 = new ElementParameter();
				elemparam1.setField("TABLE");
				elemparam1.setName("ADD_COLS");
				
				elemParam.add(Utils.getPropDtlsforExp("CHECK","USE_FIELD_OPTIONS","false"));
				
				ElementParameter elemparamTgtDb = new ElementParameter();
				elemparamTgtDb.setField("TABLE");
				elemparamTgtDb.setName("CRITERIA");
				
				for(int i = 0 ; i < srctgtdb.srcTgtColsList.size(); i++)
				{
					ColumnDTO tgtCols = srctgtdb.srcTgtColsList.get(i);
					String colsName = tgtCols.colStruct.name;
					String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
					List<ElementParameter.ElementValue> elemValueList = new ArrayList<ElementParameter.ElementValue>();
			    	elemValueList.add(Utils.getPropElemforTrans("SCHEMA_COLUMN", colName));
			    	elemValueList.add(Utils.getPropElemforTrans("UPDATE_KEY", "false"));
			    	elemValueList.add(Utils.getPropElemforTrans("DELETE_KEY", "false"));
			    	elemValueList.add(Utils.getPropElemforTrans("UPDATABLE", "true"));
			    	elemValueList.add(Utils.getPropElemforTrans("INSERTABLE", "true"));
			    	elemparamTgtDb.getElementValue().addAll(elemValueList);
				}
				elemParam.add(Utils.getPropDtlsforExp("CHECK","SUPPORT_NULL_WHERE","false"));
				compNode.getElementParameter().addAll(elemParam);
				compNode.getElementParameter().add(elemparam1);
				compNode.getElementParameter().add(elemparamTgtDb);	
			}
		
			if(!srctgtdb.fileType.equalsIgnoreCase("MongoDb"))
			{
				TalendfileProcessType.Node.Metadata metadata = new TalendfileProcessType.Node.Metadata();
				metadata.setConnector("REJECT");
				metadata.setName("REJECT");
				
				boolean checkRcp = Utils.getBooleanForRcp(dataMappingObj);
				if(checkRcp == true)
				{
				   metadata.getColumn().add(Utils.createExtraColForRCP());
				   
				}
				for (int i = 0; i < recordperPipe.inRecsList.size(); i++) 
				{
					InRecDTO inRec =  recordperPipe.inRecsList.get(i);
					for (int j = 0; j <  inRec.prevOutRec.columnList.size(); j++) 
					{
						ColumnDTO colsDetails = inRec.prevOutRec.columnList.get(j);
						String ColsName = colsDetails.colStruct.name;
						String colName = ColsName.substring(ColsName.indexOf(".")+1, ColsName.length());
						TalendfileProcessType.Node.Metadata.Column col  = new TalendfileProcessType.Node.Metadata.Column();
						col.setComment("");
						String key = colsDetails.colStruct.keyType;
						col.setKey("false");
		
		//				col.setLength(colsDetails.colStruct.length);
						
						
						if(colsDetails.colStruct.length==0){
							col.setLength(colsDetails.colStruct.precision);
							col.setPrecision(colsDetails.colStruct.Scale);
						}
						else{
							col.setLength(colsDetails.colStruct.length);
							col.setPrecision(colsDetails.colStruct.Scale);
						}
						
						if(colsDetails.colStruct.dateFormat != null)
						{
						   if(colsDetails.colStruct.dateFormat.length() > 0)
							   col.setPattern("\"" + colsDetails.colStruct.dateFormat + "\"");
						}
						else
							col.setPattern("");
						
						col.setName(colName);
						col.setNullable("true");
		//				col.setPrecision(colsDetails.colStruct.precision);
						String dataType = colsDetails.colStruct.dataType;
						if(colsDetails.colStruct.dataType != null)
						{
							String tgtDataType = Utils.getTalendDataType(21,dataBaseId,dataType);
							if(tgtDataType.equalsIgnoreCase("Number(p,s)"))
							{
								tgtDataType = "NUMBER";
							}
							col.setsourceType(tgtDataType);
						}
		
						
						col.setType(dataType);
						col.setUsefulColumn("true");
						metadata.getColumn().add(col);
					}
					TalendfileProcessType.Node.Metadata.Column colRjt1 = new TalendfileProcessType.Node.Metadata.Column();
					colRjt1.setComment("");
					colRjt1.setKey("false");
					colRjt1.setLength(255);
					colRjt1.setName("errorCode");
					colRjt1.setNullable("true");
					colRjt1.setPattern("");
					colRjt1.setPrecision(0);
					colRjt1.setsourceType("VARCHAR");
					colRjt1.setType("id_String");
					colRjt1.setoriginalLength("-1");
					colRjt1.setUsefulColumn("true");
					TalendfileProcessType.Node.Metadata.Column colRjt2 = new TalendfileProcessType.Node.Metadata.Column();
					colRjt2.setComment("");
					colRjt2.setKey("false");
					colRjt2.setLength(255);
					colRjt2.setName("errorCode");
					colRjt2.setNullable("true");
					colRjt2.setPattern("");
					colRjt2.setPrecision(0);
					colRjt2.setsourceType("VARCHAR");
					colRjt2.setType("id_String");
					colRjt2.setoriginalLength("-1");
					colRjt1.setUsefulColumn("true");
					metadata.getColumn().add(colRjt1);
					metadata.getColumn().add(colRjt2);
					compNode.getMetadata().add(metadata);
				}
			}
		}
		
	}
	
	private static ArrayList<ArrayList<String>> getOjectElementGroups(ArrayList<String> groupVals) 
	{
		ArrayList<ArrayList<String>> valsList = new ArrayList<ArrayList<String>>();
		 
		boolean checkGroupPath = false;
		String checkPath = "";
		
		for(int i = 0 ; i < groupVals.size() ; i++)
		{
			String path = groupVals.get(i).substring(0, groupVals.get(i).indexOf(";")).replace("[Value]", "");
	    	String colVal = groupVals.get(i).substring(groupVals.get(i).indexOf(";")+1, groupVals.get(i).length()).replace("[Value]", "");
	    	
	    	if(path.length() > 0)
	    	{
	    		checkPath = path;
	    		if(checkPath.equalsIgnoreCase(path))
                {
	    			checkGroupPath = true;
                }
	    		
	    		if(checkGroupPath)
	    		{
	    			
	    		}
	    	}
	        
		}
		 
		return null;
	}

	private static Integer fillElemParamList(int orderKey, ArrayList<String> rootVals, String keyType, ElementParameter elemGroupBy, ElementParameter elemParamRoot, int endOrder) 
	{
		for(int i = 0 ; i  < rootVals.size(); i++)
	    {
	    	String path = rootVals.get(i).substring(0, rootVals.get(i).indexOf(";")).replace("[Value]", "");
	    	String colVal = rootVals.get(i).substring(rootVals.get(i).indexOf(";")+1, rootVals.get(i).length()).replace("[Value]", "");
	    	
	    	List<ElementParameter.ElementValue> elemRootValueList = new ArrayList<ElementParameter.ElementValue>();
	    	
	    	elemRootValueList.add(Utils.getPropElemforTrans("PATH", "/"+path));
	    	elemRootValueList.add(Utils.getPropElemforTrans("COLUMN", colVal));
	    	elemRootValueList.add(Utils.getPropElemforTrans("VALUE", ""));
	    	elemRootValueList.add(Utils.getPropElemforTrans("ATTRIBUTE", "branch"));
	    	elemRootValueList.add(Utils.getPropElemforTrans("ORDER", ""+orderKey));
	    	
	    	if(keyType.equalsIgnoreCase("Root"))
	    	{
	    		if(elemGroupBy != null)
	    		{
			    	List<ElementParameter.ElementValue> elemGroupList = new ArrayList<ElementParameter.ElementValue>();
			    	elemGroupList.add(Utils.getPropElemforTrans("INPUT_COLUMN", colVal));
			    	elemGroupBy.getElementValue().addAll(elemGroupList);
	    		}
	    	}
	    	
	    	endOrder = orderKey++;
	    	
	    	elemParamRoot.getElementValue().addAll(elemRootValueList);		
	    }	
		
		return endOrder;
	}

	private static void getTreeStructVal(String rootPathVal, SrcTgtInfo srctgtdb,
			LinkedHashMap<String, ArrayList<String>> groupHashVal) 
	{
		ArrayList<String> rootValList = new ArrayList<String>();
		ArrayList<String> LoopValList = new ArrayList<String>();
		ArrayList<String> GroupList = new ArrayList<String>();
		
		String loopPath = srctgtdb.recordMarkerList.get(0);
		
		loopPath = getUpdatedXPath(loopPath);
		
		for(int i = 0 ; i < srctgtdb.srcTgtColsList.size(); i++)
		{
			String XPathVal = srctgtdb.srcTgtColsList.get(i).colStruct.xPath;
			
			XPathVal = getUpdatedXPath(XPathVal);
			
			if(XPathVal != null)
			{
				String rootVal = XPathVal.substring(XPathVal.lastIndexOf("/")+1, XPathVal.length());
				String xPath = XPathVal.substring(0, XPathVal.lastIndexOf("/"));
				
			    if(rootVal.contains("[Value]"))
			    {
			    	if(XPathVal.substring(0, XPathVal.lastIndexOf("/")).equals(rootPathVal))
			    	{
			    		if(!rootValList.contains(XPathVal + ";"+ rootVal))
			    			rootValList.add(XPathVal + ";"+ rootVal);
			    	}
			    	else
			    	{
			    		if(xPath.equals(loopPath))
			    		{
			    			if(!LoopValList.contains(XPathVal + ";"+ rootVal))
			    				LoopValList.add(XPathVal + ";"+ rootVal);
			    		}
			    		else
			    		{
			    			if(!GroupList.contains(XPathVal + ";" + rootVal))
			    				GroupList.add(XPathVal + ";" + rootVal);
			    		}
			    	}
			    	
			    }
			  
			}
	   
		}
		
		groupHashVal.put("Root", rootValList);
		groupHashVal.put("Loop", LoopValList);
		groupHashVal.put("Group", GroupList);
		
		
		
	}

	private static String getUpdatedXPath(String xPathVal) 
	{
		String[] xVal =  xPathVal.split("/");
		
		String updatedVal = "";
		for(int i = 0 ; i < xVal.length; i++)
		{	
			if(i == 0)
				continue;
			
			String updVal = xVal[i];
			
			if(updVal.contains("@"))
				updVal = updVal.substring(0, updVal.lastIndexOf("@"));
		
			if(i == xVal.length-1)
				updatedVal += updVal;
			else
				updatedVal += updVal + "/";
		}
		
		
		
		return updatedVal;
	}

	private static String getRootPathFromColsStruct(SrcTgtInfo srctgtdb) 
	{
		for(int i = 0 ; i < srctgtdb.srcTgtColsList.size(); i++)
		{
			String XPathVal = srctgtdb.srcTgtColsList.get(i).colStruct.xPath;
			
			if(XPathVal != null)
			{
				String rootVal = XPathVal.substring(XPathVal.lastIndexOf("/")+1, XPathVal.length());
				
			    if(rootVal.contains("[Value]"))
			    {
			    	return  XPathVal.substring(0, XPathVal.lastIndexOf("/"));
			    }
			}
		}
		return null;
	}

	private static void getRejectDtlsForDBs(Node compNode) 
	{
		TalendfileProcessType.Node.Metadata metadata = new TalendfileProcessType.Node.Metadata();
		metadata.setConnector("REJECT");
		metadata.setName("REJECT");
		metadata.setLabel("MAIN");
		ArrayList<TalendfileProcessType.Node.Metadata.Column> rjtCols = new ArrayList<TalendfileProcessType.Node.Metadata.Column>();
		rjtCols.add(getMetaDataDtlsForRjt("", "false", 255, "columnName", "false", "", -1, "id_String", "-1", "true"));
		rjtCols.add(getMetaDataDtlsForRjt("", "false", 255, "rowNumber", "false", "", -1, "id_String", "-1", "true"));
		rjtCols.add(getMetaDataDtlsForRjt("", "false", 255, "category", "false", "", -1, "id_String", "-1", "true"));
		rjtCols.add(getMetaDataDtlsForRjt("", "false", 255, "character", "false", "", -1, "id_String", "-1", "true"));
		rjtCols.add(getMetaDataDtlsForRjt("", "false", 255, "errorMessage", "false", "", -1, "id_String", "-1", "true"));
		rjtCols.add(getMetaDataDtlsForRjt("", "false", 255, "byteOffset", "false", "", -1, "id_String", "-1", "true"));
		rjtCols.add(getMetaDataDtlsForRjt("", "false", 255, "line", "false", "", -1, "id_String", "-1", "true"));
		rjtCols.add(getMetaDataDtlsForRjt("", "false", 255, "sqlState", "false", "", -1, "id_String", "-1", "true"));
		rjtCols.add(getMetaDataDtlsForRjt("", "false", 255, "code", "false", "", -1, "id_String", "-1", "true"));
		metadata.getColumn().addAll(rjtCols);
		compNode.getMetadata().add(metadata);
	}
	
	private static Column getMetaDataDtlsForRjt(String comment,String key,int length,String name,String nullable,String pattern,int precision,String type,String originalLength,String usefulColumn) 
	{
		
		TalendfileProcessType.Node.Metadata.Column rjtCol = new TalendfileProcessType.Node.Metadata.Column();
		rjtCol.setName(name);
		rjtCol.setComment(comment);
		rjtCol.setKey(key);
		rjtCol.setLength(length);
		rjtCol.setNullable(nullable);
		rjtCol.setPattern(pattern);
		rjtCol.setPrecision(precision);
		rjtCol.setUsefulColumn(usefulColumn);
		rjtCol.setoriginalLength(originalLength);
		rjtCol.setType(type);
		Column.AdditionalField addfiled =  new Column.AdditionalField();
		addfiled.setkey("AVRO_TECHNICAL_KEY");
		addfiled.setvalue(name);
		rjtCol.setAdditionalField(addfiled);
		return rjtCol;
		
	}

	public static void fillInputColsforTgt(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb,TalendfileProcessType pipeline, boolean checkBoolean, DataMapping dataMappingObj, List<String> cntxtParamsList)
	{
		String tlndTranstype = "";
		int DataBaseType = 0 ;
		if(srctgtdb.fileExtension != null && !srctgtdb.fileExtension.equalsIgnoreCase("xml"))
		{
			tlndTranstype = "tFileOutputDelimited";
		}
		else if(srctgtdb.fileExtension != null && srctgtdb.fileExtension.equalsIgnoreCase("xml"))
		{
			tlndTranstype = "tAdvancedFileOutputXML";
		}
		else
		{
			if(srctgtdb.fileType.equalsIgnoreCase("Relational - Netezza"))
			{
				tlndTranstype = "tNetezzaOutput";
				DataBaseType = 4;
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - Teradata")){
				tlndTranstype = "tTeradataOutput";
				DataBaseType = 3;
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - DB2 LUW"))
			{
				tlndTranstype = "tDB2Output";
				DataBaseType = 2;
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - Oracle"))
			{
				tlndTranstype = "tOracleOutput";
				DataBaseType = 1;
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - ODBC"))
			{
				tlndTranstype = "tOracleOutput";
				DataBaseType = 1;
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - Amazon Redshift"))
			{
				tlndTranstype = "tRedshiftOutput";
				DataBaseType = 7;
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - SQL Server"))
			{
				tlndTranstype = "tMSSqlOutput";
				DataBaseType = 5;
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - Azure SQL DB") || srctgtdb.fileType.equalsIgnoreCase("Relational - Azure SQL Data Warehouse")){
				tlndTranstype = "tSQLDWHOutput";
				DataBaseType = 5;
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("MongoDb"))
			{
				tlndTranstype = "tMongoDBOutput";
				DataBaseType = 24;
			}
			else if(srctgtdb.fileType.equalsIgnoreCase("Relational - Snowflake DW"))
			{
				tlndTranstype = "tSnowflakeOutput";
				DataBaseType = 10;
			}
		}
		
		String xPos = recordperPipe.xLevelPos;
		String yPos = recordperPipe.yLevelPos;
		String compName = recordperPipe.compName;
		TalendfileProcessType.Node.Metadata metadata = new TalendfileProcessType.Node.Metadata();
		metadata.setConnector("FLOW");
		metadata.setLabel("rw_"+compName);
		metadata.setName(compName);
		
		boolean checkRcp = Utils.getBooleanForRcp(dataMappingObj);
		if(checkRcp == true)
		{
		   metadata.getColumn().add(Utils.createExtraColForRCP());
		}
	    Double compVersion = null;
		if(srctgtdb.fileExtension != null)
		{
			compVersion = 0.101;
			createTgt.fillPropsforFlatTgt(recordperPipe, docspecs, compNode, srctgtdb, cntxtParamsList);
			boolean checkOrderCols = compareLists(recordperPipe, srctgtdb);
			boolean checkRejectOption = getBooleanRejectOption(recordperPipe,srctgtdb);
			if(checkOrderCols == false && checkBoolean == false && checkRejectOption != true)
			{
		        Utils.fillExpTransforColumnSort(recordperPipe, docspecs,srctgtdb,pipeline);
		        Utils.getcompDetails(compNode,tlndTranstype, compVersion,xPos,yPos);
		        
					for (int j = 0; j < srctgtdb.srcTgtColsList.size(); j++) 
					{
						ColumnDTO colsDetails = srctgtdb.srcTgtColsList.get(j);
						if(colsDetails.srcColumn == null)
						continue;
						String ColsName = colsDetails.colStruct.name;
						String colName = ColsName.substring(ColsName.indexOf(".")+1, ColsName.length());
						TalendfileProcessType.Node.Metadata.Column col = new TalendfileProcessType.Node.Metadata.Column();
						
						boolean isNullable = colsDetails.colStruct.isNullable;
						String nullStatus = String.valueOf(isNullable);
						String comment = colsDetails.colStruct.description;
						
						if(comment!=null){
							col.setComment(comment);
						}
						else{
							col.setComment("");
						}
						
						String key = colsDetails.colStruct.keyType;
						col.setKey("false");

						
//						col.setLength(colsDetails.colStruct.length);
						
						
						if(colsDetails.colStruct.length==0){
							col.setLength(colsDetails.colStruct.precision);
							col.setPrecision(colsDetails.colStruct.Scale);
						}
						else{
							col.setLength(colsDetails.colStruct.length);
							col.setPrecision(colsDetails.colStruct.Scale);
						}
						
						col.setName(colName);
						
						if(nullStatus!=null){
							col.setNullable(nullStatus);
						}
						else{
							col.setNullable("true");
						}
						

						 if(colsDetails.colStruct.dateFormat != null)
						   {
							   if(colsDetails.colStruct.dateFormat.length() > 0)
							   {
								   col.setPattern("\"" + colsDetails.colStruct.dateFormat + "\"");
							   }
						   }
						  else
							  col.setPattern("");
//						col.setPrecision(colsDetails.colStruct.precision);
						String dataType = colsDetails.colStruct.dataType;
						DataTypeConvertor dtc = new DataTypeConvertor();
			    		dataType = dtc.getTargetDataType(dataType,ToolTypeConstants.ODBC,ToolTypeConstants.TALEND);
			    		if(dataType!=null){
			    			col.setType(dataType);
			    		}else{
			    			col.setType("id_String");
			    		}
						
						col.setUsefulColumn("true");
						metadata.getColumn().add(col);
					}
					compNode.getMetadata().add(metadata);
			}
			else
			{
			   Utils.getcompDetails(compNode,tlndTranstype, compVersion,xPos,yPos);
			   for (int i = 0; i < recordperPipe.inRecsList.size(); i++) 
				{
					InRecDTO inRec =  recordperPipe.inRecsList.get(i);
					for (int j = 0; j <  inRec.prevOutRec.columnList.size(); j++) 
					{
						ColumnDTO colsDetails = inRec.prevOutRec.columnList.get(j);
						String ColsName = colsDetails.colStruct.name;
						String colName = ColsName.substring(ColsName.indexOf(".")+1, ColsName.length());
						TalendfileProcessType.Node.Metadata.Column col = new TalendfileProcessType.Node.Metadata.Column();
						
						boolean isNullable = colsDetails.colStruct.isNullable;
						String nullStatus = String.valueOf(isNullable);
						String comment = colsDetails.colStruct.description;
						
						if(comment!=null){
							col.setComment(comment);
						}
						else{
							col.setComment("");
						}
						
						String key = colsDetails.colStruct.keyType;
						col.setKey("false");
						if(colsDetails.colStruct.length==0){
							col.setLength(colsDetails.colStruct.precision);
							col.setPrecision(colsDetails.colStruct.Scale);
						}
						else{
							col.setLength(colsDetails.colStruct.length);
							col.setPrecision(colsDetails.colStruct.Scale);
						}
						
						col.setName(colName);
						
						if(nullStatus!=null){
							col.setNullable(nullStatus);
						}
						else{
							col.setNullable("true");
						}
						

						 if(colsDetails.colStruct.dateFormat != null)
						   {
							   if(colsDetails.colStruct.dateFormat.length() > 0)
							   {
								   col.setPattern("\"" + colsDetails.colStruct.dateFormat + "\"");
							   }
						   }
						  else
							  col.setPattern("");
						
						
						String dataType = colsDetails.colStruct.dataType;
						if(dataType != null)
						{	
						  if(srctgtdb.fileExtension == null)
						  {
								String tgtDataType = Utils.getTalendDataType(21,DataBaseType,dataType);
								if(tgtDataType.equalsIgnoreCase("Number(p,s)"))
								{
									tgtDataType = "NUMBER";
								}
								col.setsourceType(tgtDataType);
						   }
						}
						col.setType(dataType);
						col.setUsefulColumn("true");
						metadata.getColumn().add(col);
					}
					compNode.getMetadata().add(metadata);	
				}
			}
			
		}
		else
		{
			compVersion = 0.102;
			createTgt.fillPropsforDBTgt(recordperPipe, docspecs, compNode, srctgtdb,dataMappingObj,cntxtParamsList);
			Utils.getcompDetails(compNode,tlndTranstype, compVersion,xPos,yPos);
			for (int i = 0; i < recordperPipe.inRecsList.size(); i++) 
			{
				InRecDTO inRec =  recordperPipe.inRecsList.get(i);
				for (int j = 0; j <  inRec.prevOutRec.columnList.size(); j++) 
				{
					ColumnDTO colsDetails = inRec.prevOutRec.columnList.get(j);
					String ColsName = colsDetails.colStruct.name;
					String colName = ColsName.substring(ColsName.indexOf(".")+1, ColsName.length());
					TalendfileProcessType.Node.Metadata.Column col = new TalendfileProcessType.Node.Metadata.Column();
					
					boolean isNullable = colsDetails.colStruct.isNullable;
					String nullStatus = String.valueOf(isNullable);
					String comment = colsDetails.colStruct.description;
					
					if(srctgtdb.fileType.equalsIgnoreCase("Relational - Snowflake DW"))
					{
						Column.AdditionalField addfiled = new Column.AdditionalField();
						addfiled.setkey("AVRO_TECHNICAL_KEY");
						addfiled.setvalue(colName);
						col.setAdditionalField(addfiled);
					}
					
					if(comment!=null){
						col.setComment(comment);
					}
					else{
						col.setComment("");
					}
					
					String key = colsDetails.colStruct.keyType;
					col.setKey("false");
					
					if(colsDetails.colStruct.length == 0){
						col.setLength(colsDetails.colStruct.precision);
						col.setPrecision(colsDetails.colStruct.Scale);
					}
					else{
						col.setLength(colsDetails.colStruct.length);
						col.setPrecision(colsDetails.colStruct.Scale);
					}
					
					col.setName(colName);
					
					if(nullStatus!=null){
						col.setNullable(nullStatus);
					}
					else{
						col.setNullable("true");
					}
						
					
					 if(colsDetails.colStruct.dateFormat != null)
					   {
						   if(colsDetails.colStruct.dateFormat.length() > 0)
						   {
							   col.setPattern("\"" + colsDetails.colStruct.dateFormat + "\"");
						   }
					   }
					  else
						  col.setPattern("");
					
					String dataType = colsDetails.colStruct.dataType;
					if(dataType == null){
						dataType = "id_String";
					}
					
					if(dataType != null)
					{	
					  if(srctgtdb.fileExtension == null)
					  {
							String tgtDataType = Utils.getTalendDataType(21,DataBaseType,dataType);
							if(tgtDataType.equalsIgnoreCase("Number(p,s)"))
							{
								tgtDataType = "NUMBER";
							}
							col.setsourceType(tgtDataType);
					   }
					}
					
					
					col.setType(dataType);
					col.setUsefulColumn("true");
					metadata.getColumn().add(col);
				}
				compNode.getMetadata().add(metadata);	
			}
		}			
}
		
		
	
	
	private static boolean getBooleanRejectOption(TransRecExtDTO recordperPipe, SrcTgtInfo srctgtdb) 
	{
	    boolean checkSrcBoolean = false;
		for(int i = 0 ; i < recordperPipe.prevCompList.size(); i++)
		{
			if(recordperPipe.prevCompList.get(i).transType.equalsIgnoreCase("Source") && recordperPipe.rejectOption.equalsIgnoreCase("Capture - Bad Data File"))
			{
				checkSrcBoolean = true;
			}
		}
		return checkSrcBoolean;
	}

	public static boolean compareLists(TransRecExtDTO recordperPipe, SrcTgtInfo srctgtdb) 
	{
		
		List<String> CheckSrcOrderList = new ArrayList<String>();
		for (int i1 = 0; i1 < srctgtdb.srcTgtColsList.size(); i1++) 
		{
		     ColumnDTO srcCols =  srctgtdb.srcTgtColsList.get(i1);
		     if(srcCols.srcColumn == null)
		    	continue; 
		     String srcColsName = srcCols.colStruct.name;
		     CheckSrcOrderList.add(srcColsName);
		}
		
		List<String> PrevOrderColsList = new ArrayList<String>();
		for (int i = 0; i < recordperPipe.inRecsList.size(); i++) 
		{
			InRecDTO inRec =  recordperPipe.inRecsList.get(i);
			for (int j = 0; j <  inRec.prevOutRec.columnList.size(); j++) 
			{
				ColumnDTO colsDetails = inRec.prevOutRec.columnList.get(j);
				String ColsName = colsDetails.colStruct.name;
				String colName = ColsName.substring(ColsName.indexOf(".")+1, ColsName.length());
				PrevOrderColsList.add(colName);
			}
		}
		if(PrevOrderColsList.size() == CheckSrcOrderList.size())
		{
			int count=0;
			int size=PrevOrderColsList.size();
			for(int i=0;i<size;i++)
			{
				if(PrevOrderColsList.get(i).equals(CheckSrcOrderList.get(i))){
					count++;
				}
			}
			if(count==size){
				return true;
			}else{
				return false;
			}
		}
	
		return false;
	}
	
}