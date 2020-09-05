
package com.dataSwitch.ds.commons.datatyperules;

import java.util.HashMap;

public class ToolTypeConstants {

	public static final int DS_TOOL = 0;
	public static final int ORACLE = 1;
	public static final int DB2 = 2;
	public static final int TERADATA = 3;
	public static final int NETEZZA = 4;
	public static final int SQLSERVER = 5;
	public static final int SYBASE = 6;
	public static final int REDSHIFT = 7;
	public static final int BIGQUERY = 8;
	public static final int AZURESQL = 9;
	public static final int SNOWFLAKE = 10;
	public static final int AURORA = 11;
	public static final int DATASTAGE = 12;
	public static final int SSIS = 13;
	public static final int INFORMATICA_BDM = 14;
	public static final int INFORMATICA_PC = 15;
	public static final int APACHE_BEAM = 16;
	public static final int JAVA = 17;	
	public static final int ODBC = 18;
	public static final int PYSPARK = 19;
	public static final int TALEND = 20;
	public static final int AZUREDATAFACTORY = 21;
	public static final int ADFDATAFLOW = 22;
	public static final int MONGODB = 23;	
    public static final int POSTGRESQL = 24;
    public static final int GREENPLUM = 25;
    public static final int IMPALA = 26;
    public static final int PENTAHO = 27;
    public static final int VERTICA = 28;
    public static final int APACHE_BEAM_PY = 29;
	public static final int SCALA_SPARK = 30;	
    public static final int AMAZONS3 = 31;
	public static final int SQLDW = 32;
	public static final int SNOWFLAKECLOUD = 33;	
	public static final int AVRO = 34;
	public static final int HADOOPHDFS = 35;
	public static final int HADOOPHIVE = 36;
	public static final int SALESFORCE = 37;	
	public static final int PWX = 38;	
	public static final int MONETDB = 39;
	public static final int PARQUET = 40;  
	public static final int FLATFILE = 41;
	public static final int PYTHON = 42;
	public static final int PANDASSQL = 43;
	public static final int XMLSCHEMA = 44;
	public static final int MYSQL = 45;
	
	
	public static HashMap<String, Integer> getMap() {
		HashMap<String, Integer> dbTool = new HashMap<String, Integer>();
		
		dbTool.put("DS", 0);
		dbTool.put("ORACLE", 1);
		dbTool.put("DB2", 2);
		dbTool.put("TERADATA", 3);
		dbTool.put("NETEZZA", 4);
		dbTool.put("SQLSERVER", 5);
		dbTool.put("SYBASE", 6);
		dbTool.put("REDSHIFT", 7);
		dbTool.put("BIGQUERY", 8);
		dbTool.put("AZURESQL", 9);
		dbTool.put("SNOWFLAKE", 10);
		dbTool.put("AURORA", 11);
		dbTool.put("DATASTAGE", 12);
		dbTool.put("SSIS", 13);
		dbTool.put("INFORMATICA_BDM", 14);
		dbTool.put("INFORMATICA_PC", 15);
		dbTool.put("APACHEBEAM", 16);
		dbTool.put("JAVA", 17);
		dbTool.put("ODBC", 18);
		dbTool.put("PYSPARK", 19);
		dbTool.put("TALEND", 20);
		dbTool.put("AZUREDATAFACTORY", 21);
		dbTool.put("ADFDATAFLOW", 22);
		dbTool.put("MONGODB", 23);
        dbTool.put("POSTGRESQL", 24);
        dbTool.put("GREENPLUM", 25);
        dbTool.put("IMPALA", 26);
        dbTool.put("PENTAHO", 27);
        dbTool.put("VERTICA", 28);
        dbTool.put("APACHE_BEAM_PY", 29);
		dbTool.put("SCALA_SPARK", 30);	
		dbTool.put("AMAZONS3", 31);
		dbTool.put("SQLDW", 32);
		dbTool.put("SNOWFLAKECLOUD", 33);		
		dbTool.put("AVRO", 34);
		dbTool.put("HADOOPHDFS", 35);
		dbTool.put("HADOOPHIVE", 36);
		dbTool.put("SALESFORCE", 37);		
		dbTool.put("PWX", 38);		
		dbTool.put("MONETDB", 39);
		dbTool.put("PARQUET", 40);		
        dbTool.put("FLATFILE", 41);
        dbTool.put("PYTHON",42);
        dbTool.put("PANDASSQL",43);
        dbTool.put("XMLSCHEMA", 44);
        dbTool.put("MYSQL", 45);

		return dbTool;

	}

}