
package com.dataSwitch.ds.commons.datatyperules;
import java.util.ArrayList;
import java.util.List;

import com.dataSwitch.ds.commons.datatyperules.DataTypeRule;

public class DataTypeMapRules {
	
	public enum dsDataType {
		
		CHAR_CHAR,
		CHAR_VARCHAR,
		CHAR_CLOB,
		UNICODE_CHAR,
		UNICODE_VARCHAR,
		UNICODE_CLOB,
		BIN_CHAR,
		BIN_VARCHAR,
		BIN_BLOB,
		NUM_BIT,
		NUM_TINYINT,
		NUM_SMALLINT,
		NUM_USMALLINT,
		NUM_INT,
		NUM_UINT,
		NUM_BIGINT,
		NUM_UBIGINT,
		NUM_SMALLMONEY,
		NUM_MONEY,
		NUM_DECIMAL,
		NUM_DOUBLE,
		NUM_FLOAT,
		NUM_REAL,
		DATE_DATE,
		DATE_TIME,
		DATE_DATETIME,
		DATE_TIMEZONE,
		DATE_SMALLDATETIME,
		DATE_INTERVAL,
		TYPE_VAR,
		TYPE_UNKNOWN,
		TYPE_VOID,
		TYPE_STRUCT,
		CHAR_DEPRECATED,
		BIN_DEPRECATED,
		NUM_DEPRECATED,
	    DATE_DEPRECATED,
	    UNICODE_DEPRECATED,
	    NUM_DECIMAL_DEPRECATED
	    
	}
	
	public static dsDataType GEN_CHAR_CHAR = dsDataType.CHAR_CHAR;
	public final static String GEN_CHAR_DEPRICATED = "CHAR_DEPRICATED";

	private static ArrayList<DataTypeRule> dsDataTypeRules = null;
	private static ArrayList<DataTypeRule> oraDataTypeRules = null;
	private static ArrayList<DataTypeRule> db2DataTypeRules = null;
	private static ArrayList<DataTypeRule> teradataTypeRules = null;
	private static ArrayList<DataTypeRule> netezzaTypeRules = null;  
   	private static ArrayList<DataTypeRule> sqlserverTypeRules = null;
   	private static ArrayList<DataTypeRule> sybaseDataTypeRules = null;
   	private static ArrayList<DataTypeRule> azureDataFactoryDataTypRules = null;
	private static ArrayList<DataTypeRule> azureDataFactoryDataFlowRules = null;
    private static ArrayList<DataTypeRule> hadoopHdfsDataTypRules = null;
	private static ArrayList<DataTypeRule> redshiftDataTypeRules = null;
	private static ArrayList<DataTypeRule> bigqueryDataTypeRules = null;
	private static ArrayList<DataTypeRule> azuresqlDataTypeRules = null;
	private static ArrayList<DataTypeRule> snowflakeDataTypeRules = null;
 	private static ArrayList<DataTypeRule> auroraDataTypeRules = null;
 	private static ArrayList<DataTypeRule> datastageDataTypeRules = null;
 	private static ArrayList<DataTypeRule> ssisDataTypeRules = null;
 	private static ArrayList<DataTypeRule> informaticaBDMDataTypeRules = null;
 	private static ArrayList<DataTypeRule> informaticaPCDataTypeRules = null;
 	private static ArrayList<DataTypeRule> apacheBeamDataTypeRules = null;
 	private static ArrayList<DataTypeRule> javaDataTypeRules = null;
 	private static ArrayList<DataTypeRule> flatFileDataTypeRules = null;
 	private static ArrayList<DataTypeRule> odbcDataTypeRules = null;
 	private static ArrayList<DataTypeRule> talendDataTypeRules = null;
 	private static ArrayList<DataTypeRule> mongoDataTypeRules = null;
 	private static ArrayList<DataTypeRule> amazonS3DataTypeRules = null;
 	private static ArrayList<DataTypeRule> sqlDwDataTypeRules = null;
 	private static ArrayList<DataTypeRule> snowflakeCloudDataTypeRules = null;
 	private static ArrayList<DataTypeRule> greenPlumDataTypeRules = null;
 	private static ArrayList<DataTypeRule> avroTypeRules = null;
    private static ArrayList<DataTypeRule> hadoopHiveDataTypRules = null;
    private static ArrayList<DataTypeRule> salesForceDataTypeRules = null;
    private static ArrayList<DataTypeRule> impalaDataTypeRules = null;
    private static ArrayList<DataTypeRule> pwxDataTypeRules = null;
    private static ArrayList<DataTypeRule> pentahoDataTypeRules = null;
    private static ArrayList<DataTypeRule> postgreDataTypeRules = null;
    private static ArrayList<DataTypeRule> monetdbDataTypeRules = null;
    private static ArrayList<DataTypeRule> parquetDataTypeRules = null;
    private static ArrayList<DataTypeRule> verticaDataTypeRules = null;
    private static ArrayList<DataTypeRule> pythonDataTypeRules = null;
    private static ArrayList<DataTypeRule> pySparkDataTypeRules = null;
    private static ArrayList<DataTypeRule> xmlSchemaDataTypeRules = null;
    private static ArrayList<DataTypeRule> mySQLDataTypeRules = null;


 	
	public static List<DataTypeRule> getDSToolRules()
	{
		if(dsDataTypeRules!=null)
			return dsDataTypeRules;

		dsDataTypeRules = new ArrayList<DataTypeRule>();

//		DataTypeRule(maxPrecision, minScale, maxScale, maxDataLength,  - minValue, maxValue, defaultSupport, defaultValue, sizeInBytes, defaultSize, notNullSupport);
//		new OtherRule(minValue, maxValue, defaultSupport, defaultValue, sizeInBytes, notNullSupport)
//		after 4th Param replace "," with "), new OtherRule("
// 		Remove Penultimate Param at the end
//		Add a ")" before ";"
// 		Add "Y/N" string as 1st param for SizeRule 
		
		
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"char","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"string","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"text","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"nchar","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"nstring","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"ntext","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"binary","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"binstring","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"bintext","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"bit","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"tinyint","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"smallint","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"usmallint","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"number","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"unumber","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"long","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"ulong","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"smallmoney","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"money","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"decimal","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"double","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"float","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"real","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"date","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"time","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"datetime","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"date_tz","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"smalldatetime","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"INTERVAL","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"variableType","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"UnknownType","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VOID,"void","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","N","NULL","NULL","Y")));
		dsDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_STRUCT,"struct","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","N","NULL","NULL","Y")));
		
		return dsDataTypeRules;
	}
	
	public static List<DataTypeRule> getOracleRules()
	{
		if(oraDataTypeRules!=null)
			return oraDataTypeRules;

		oraDataTypeRules = new ArrayList<DataTypeRule>();

		//String isSizeApplicable, String maxLengthPrecision, String maxScale, String defaultLengthPrecision, String defaultScale
		
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('P',"2000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR2","NULL","B"), new SizeRule('P',"32767","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"CLOB","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"NCHAR","NULL","B"), new SizeRule('P',"2000","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"NVARCHAR2","NULL","B"), new SizeRule('P',"32767","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"NCLOB","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"RAW","NULL","B"), new SizeRule('P',"32767","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"LONG RAW","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"BLOB","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"NUMBER","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"NUMBER","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"NUMBER","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"NUMBER","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"NUMBER","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("-2147483648","2147483647","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"NUMBER","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"LONG","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"NUMBER","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"NUMBER","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"NUMBER","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"NUMBER","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"NUMBER","NULL","B"), new SizeRule('S',"38","","15","0"), new OtherRule("2.22507485850720E-308","2.22507485850720E-308","Y","NULL","9","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('P', "126","","","0"), new OtherRule("1.17549E-38F","3.40282E+38F","Y","NULL","5","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"FLOAT","NULL","T"), new SizeRule('P', "126","","",""), new OtherRule("1.17549E-38F","3.40282E+38F","Y","NULL","5","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","19","0"), new OtherRule("01 januarY  4712 BC","31 december 9999 AD","Y","NULL","7","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIMESTAMP","NULL","T"), new SizeRule('P', "9","","26","6","6",""), new OtherRule("NULL","NULL","Y","NULL","0","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('P', "9","","26","6","6",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 23.59.61.99999","Y","NULL","10","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP WITH TIMEZONE","NULL","B"), new SizeRule('P', "9","","26","6","6",""), new OtherRule("1 JanuarY 0001 00.00.00.000000000 -12.59","31 December 9999, 23.59.61.99999   +14.00","Y","NULL","12","N")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('P', "9","","26","6","6",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 23.59.61.99999","Y","NULL","10","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"TIMESTAMP","NULL","T"), new SizeRule('P', "9","","26","6","6",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"VARCHAR",dsDataType.CHAR_VARCHAR.toString().toString().toString(),"S"), new SizeRule('P', "32767","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
	    oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"XMLTYPE",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
	    oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"LONG",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
	    //oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMBER",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
	    oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"BINARY_DOUBLE",dsDataType.NUM_DOUBLE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
	    oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMBER(P,S)",dsDataType.NUM_DOUBLE.toString(),"S"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
	    oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
	    oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_DEPRECATED,"NVARCHAR",dsDataType.UNICODE_VARCHAR.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
	    oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"BFILE",dsDataType.BIN_BLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","0","Y")));
		//oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"LONG RAW",dsDataType.BIN_VARCHAR.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"TIMESTAMP WITH LOCAL TIMEZONE",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('P', "9","","26","6"), new OtherRule("00:00:00.000000  -12.59","23:59:61.999999  +14.00","Y","NULL","8","Y")));
	    oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR2","NULL","T"), new SizeRule('P',"32767","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		oraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR2","NULL","T"), new SizeRule('P',"32767","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		
		return oraDataTypeRules;
	}

	public static List<DataTypeRule> getHadoopHDFSDataTypRules()
	{
		if(hadoopHdfsDataTypRules!=null)
			return hadoopHdfsDataTypRules;

		hadoopHdfsDataTypRules = new ArrayList<DataTypeRule>();

		/*hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule("CHAR_STRING","STRING","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule("UNICODE_STRING","NSTRING","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DOUBLE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("2.22507485850720E-308","2.22507485850720E-308","Y","NULL","9","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","1","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"NUMBER","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"DATETIME","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"STRING","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"STRING","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
	*/	
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"string","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"nstring","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"int","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"int","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"int","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"int","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"int","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"int","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"int","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"int","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"number","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"number","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"number","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"double","NULL","B"),new SizeRule('N', "","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"number","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"double","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"datetime","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"datetime","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"datetime","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"datetime","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"datetime","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"datetime","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"character",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"binary",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"text",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"long",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"long",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"integer",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"smallint",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"bigint",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"decimal",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"real",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"date",dsDataType.DATE_DATETIME.toString().toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("00:00:00.000000  -12.59","23:59:61.999999  +14.00","Y","NULL","8","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"time",dsDataType.DATE_DATETIME.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("00:00:00.000000  -12.59","23:59:61.999999  +14.00","Y","NULL","8","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		hadoopHdfsDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		
		return hadoopHdfsDataTypRules;
	}
	
	
	public static List<DataTypeRule> getDB2Rules()
	{
		if(db2DataTypeRules!=null)
			return db2DataTypeRules;

		db2DataTypeRules = new ArrayList<DataTypeRule>();


		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('P', "255","","1",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('P', "32704","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"CLOB","NULL","B"), new SizeRule('P', "2,147,483,647","","1,048,576",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"GRAPHIC","NULL","B"), new SizeRule('P', "128","","1",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"VARGRAPHIC","NULL","B"), new SizeRule('P', "16352","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"DBCLOB","NULL","B"), new SizeRule('P', "1,073,741,824","","1,048,576",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"CHAR FOR BIT DATA","NULL","B"), new SizeRule('P', "255","","1",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARCHAR FOR BIT DATA","NULL","B"), new SizeRule('P', "32704","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"BLOB","NULL","B"),  new SizeRule('P', "2,147,483,647","","1,048,576",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"SMALLINT","NULL","T"), new SizeRule('P', "15","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"SMALLINT","NULL","T"), new SizeRule('P', "15","","5","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('P', "15","","5","0"), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"SMALLINT","NULL","T"), new SizeRule('P', "15","","5","0"), new OtherRule("-32768","32767","Y","NULL","4","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INTEGER","NULL","B"), new SizeRule('P', "31","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INTEGER","NULL","T"), new SizeRule('P', "31","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('P', "63","","19","0"), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"BIGINT","NULL","T"), new SizeRule('P', "63","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"DECIMAL","NULL","T"), new SizeRule('S', "31","","10","4"), new OtherRule("1 - 10³¹","10³¹ - 1","Y","NULL","4","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"DECIMAL","NULL","T"), new SizeRule('S', "31","","19","4"), new OtherRule("1 - 10³¹","10³¹ - 1","Y","NULL","4","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","B"), new SizeRule('S', "31","","",""), new OtherRule("1 - 10³¹","10³¹ - 1","Y","NULL","4","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DOUBLE","NULL","B"), new SizeRule('P', "64","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"DOUBLE","NULL","T"), new SizeRule('P', "64","","15","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"DECIMAL","NULL","T"), new SizeRule('S', "31","","",""), new OtherRule("1 - 10³¹","10³¹ - 1","Y","NULL","4","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","10","0"), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIME","NULL","B"), new SizeRule('N', "","","8","0"), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('N', "","","26","6"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","26","6"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"CHARACTER",dsDataType.CHAR_CHAR.toString(),"S"), new SizeRule('P', "255","","1",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"LONG VARCHAR",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('P', "32,700","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"LONG VARGRAPHIC",dsDataType.UNICODE_VARCHAR.toString(),"S"), new SizeRule('P', "16,350","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"INT",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMERIC",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('S', "31","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"DECFLOAT",dsDataType.NUM_FLOAT.toString(),"S"), new SizeRule('P', "34","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","8","Y")));
		db2DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"DOUBLE",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('P', "64","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","8","Y")));
		

		return db2DataTypeRules;
	}
	public static List<DataTypeRule> getTeradataRules()
	{
		if(teradataTypeRules!=null)
			return teradataTypeRules;

		teradataTypeRules = new ArrayList<DataTypeRule>();
		
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('N', "64000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"CHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"BYTE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARBYTE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
	   	teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"VARCHAR","NULL","T"), new SizeRule('P', "2097088000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BYTEINT","NULL","T"), new SizeRule('N', "","","3","0"), new OtherRule("-128","127","Y","NULL","1","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"BYTEINT","NULL","B"), new SizeRule('N', "","","3","0"), new OtherRule("-32768","32767","Y","NULL","4","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('P', "32767","","5","0"), new OtherRule("-32768","32767","Y","NULL","2","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"SMALLINT","NULL","T"), new SizeRule('N', "","","5","0"), new OtherRule("-32768","32767","Y","NULL","4","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INTEGER","NULL","B"), new SizeRule('P', "2147483647","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INTEGER","NULL","T"), new SizeRule('N', "","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","8","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('N', "","","19","0"), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","8","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"BIGINT","NULL","T"), new SizeRule('N', "","","19","0"), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","16","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"DECIMAL","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"DECIMAL","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","16","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","8","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL_DEPRECATED,"DEC",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","8","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DECIMAL","NULL","T"), new SizeRule('S', "38","127","15","0"), new OtherRule("NULL","NULL","Y","NULL","8","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('S', "38","127","15","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"FLOAT","NULL","T"), new SizeRule('S', "38","127","15","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","10","0"), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIME","NULL","B"), new SizeRule('N', "","","8","0"), new OtherRule("00:00:00","24:00:00","Y","NULL","6","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('N', "","","26","6"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 23.59.61.99999","Y","NULL","10","Y"))); 		
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","26","6"), new OtherRule("1 JanuarY 0001 00.00.00.000000000 -12.59","31 December 9999, 23.59.61.99999   +14.00","Y","NULL","12","N")));
	    teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","26","6"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 23.59.61.99999","Y","NULL","10","Y")));
	    teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"INTERVAL","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
	    teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"CLOB",dsDataType.UNICODE_VARCHAR.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
	    teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"BLOB",dsDataType.UNICODE_VARCHAR.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMERIC",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","5","Y")));
	    teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"DOUBLE PRECISION",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","5","Y")));
	    teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMBER",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","5","Y")));
	    teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"REAL",dsDataType.NUM_REAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","5","Y")));
	    teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"TIMESTAMP WITH TIMEZONE",dsDataType.DATE_DATETIME.toString(),"S"), new SizeRule('N', "","","26","6"), new OtherRule("-10^38+1","10^38-1","Y","NULL","5","Y")));
	    teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
	    teradataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
	
	
		return teradataTypeRules;
	}
	
	public static List<DataTypeRule> getNetezzaRules()
	{
		if(netezzaTypeRules!=null)
			return netezzaTypeRules;

		netezzaTypeRules = new ArrayList<DataTypeRule>();


		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('P', "64000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('P', "64000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"VARCHAR","NULL","T"), new SizeRule('P', "64000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"NCHAR","NULL","B"), new SizeRule('P', "16000","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"NVARCHAR","NULL","B"), new SizeRule('P', "16000","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"NVARCHAR","NULL","T"), new SizeRule('P', "16000","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"CHAR","NULL","T"), new SizeRule('P', "64000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARCHAR","NULL","T"), new SizeRule('P', "64000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"VARCHAR","NULL","T"), new SizeRule('P', "64000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","1","Y","NULL","1","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"BYTEINT","NULL","B"), new SizeRule('S', "","","3","0"), new OtherRule("0","255","Y","NULL","1","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('S', "","","5","0"), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"SMALLINT","NULL","T"), new SizeRule('S', "","","5","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INTEGER","NULL","B"), new SizeRule('S', "","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INTEGER","NULL","T"), new SizeRule('S', "","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('S', "","","19","0"), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","8","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"BIGINT","NULL","T"), new SizeRule('S', "","","19","0"),new OtherRule("NULL","NULL","Y","NULL","13","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"DECIMAL","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"DECIMAL","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DOUBLE","NULL","B"), new SizeRule('S', "","","15","0"), new OtherRule("-1.79E + 308","1.79E + 308","Y","53","0","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-3.40E + 38","3.40E + 38","Y","NULL","4","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"REAL","NULL","B"), new SizeRule('S', "","","6","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","10","0"), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIME","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","23:59:59.999999","Y","NULL","8","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('S', "","","29","6"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 23:59:59.999999","Y","NULL","12","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIME WITH TIME ZONE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 23:59:59.999999","Y","NULL","12","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 23:59:59.999999","Y","NULL","12","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"CHARACTER",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"NVARCHAR(n)",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMERIC",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"INT2",dsDataType.NUM_SMALLINT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"DOUBLE PRECISION",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"INT1",dsDataType.NUM_TINYINT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"INT",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"INT4",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"INT8",dsDataType.NUM_BIGINT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"DOUBLE",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT8",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT4",dsDataType.NUM_FLOAT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('P', "64000","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		netezzaTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('P', "64000","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
	
		return netezzaTypeRules;
	}
	
	public static List<DataTypeRule> getSqlServerRules()
	{
		if(sqlserverTypeRules!=null)
			return sqlserverTypeRules;

		sqlserverTypeRules = new ArrayList<DataTypeRule>();


		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('P', "8000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('P', "8000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"TEXT","NULL","B"), new SizeRule('P', "2,147,483,647","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"NCHAR","NULL","B"), new SizeRule('P', "4000","","",""),new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"NVARCHAR","NULL","B"), new SizeRule('P', "4000","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"NTEXT","NULL","B"), new SizeRule('P', "1,073,741,823 ","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"VARBINARY","NULL","T"), new SizeRule('P', "8000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARBINARY","NULL","B"), new SizeRule('P', "8000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"IMAGE","NULL","B"), new SizeRule('P', "2,147,483,647","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BIT","NULL","B"), new SizeRule('P', "1","","1","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"TINYINT","NULL","B"), new SizeRule('P', "3","","3","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('P', "5","","5","0"), new OtherRule("-32768","32767","Y","NULL","2","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"SMALLINT","NULL","T"), new SizeRule('P', "5","","5","0"), new OtherRule("-2147483648","2147483647","Y","NULL","5","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INT","NULL","B"), new SizeRule('P', "10","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INT","NULL","T"), new SizeRule('P', "10","","10","0"),new OtherRule("NULL","NULL","Y","NULL","9","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('P', "19","","19","0"), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","8","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"BIGINT","NULL","T"), new SizeRule('P', "19","","19","0"), new OtherRule("NULL","NULL","Y","NULL","13","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"SMALLMONEY","NULL","B"), new SizeRule('S', "10","","10","4"), new OtherRule("-214748.3648","214748.3647","Y","NULL","4","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"MONEY","NULL","B"), new SizeRule('S', "19","","19","4"), new OtherRule("-922,337,203,685,477.5808","922,337,203,685,477.5807","Y","NULL","8","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","B"), new SizeRule('S', "38","","38","0"), new OtherRule("-10^38+1","10^38-1","Y","NULL","5","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DECIMAL","NULL","T"), new SizeRule('S', "38","","38","0"), new OtherRule("-1.79E + 308","1.79E + 308","Y","53","0","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('P', "15","","15","0"), new OtherRule("-3.40E + 38","3.40E + 38","Y","NULL","4","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"REAL","NULL","B"), new SizeRule('P', "7","","7","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATETIME","NULL","B"), new SizeRule('N', "","","23","3"), new OtherRule("01/01/0001","31/12/9999","Y","NULL","3","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"DATETIME","NULL","T"), new SizeRule('N', "","","23","3"), new OtherRule("00:00:00","23:59:59.999999","Y","NULL","5","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"DATETIME2","NULL","B"), new SizeRule('P', "7","","27","7"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 23:59:59.999999","Y","NULL","8","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"DATETIMEOFFSET","NULL","B"), new SizeRule('P', "7","","",""), new OtherRule("1 JanuarY 0001 0001  00:00:00.0000000  -14:00","31 December 9999, 23:59:59.999999","Y","NULL","10","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"SMALLDATETIME","NULL","B"), new SizeRule('N', "","","19","0"), new OtherRule("1 JanuarY 1990, 00.00.00.000000000","6 JUNE 2079, 23:59:59.999999","Y","NULL","4","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"SMALLDATETIME","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"BINARY",dsDataType.BIN_CHAR.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"SYSNAME",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMERIC",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('S', "38","","38","0"), new OtherRule("-10^38+1","10^38-1","Y","NULL","5","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMERIC IDENTITY",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","5","Y")));
		//sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"DATETIME",dsDataType.DATE_DATETIME.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","5","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"TIMESTAMP",dsDataType.DATE_DATETIME.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","5","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('P', "8000","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		sqlserverTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('P', "8000","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));

		return sqlserverTypeRules;
	}
	
	public static List<DataTypeRule> getSybaseRules()
	{
		if(sybaseDataTypeRules!=null)
			return sybaseDataTypeRules;

		sybaseDataTypeRules = new ArrayList<DataTypeRule>();

		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('P', "255","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('P', "255","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"TEXT","NULL","B"), new SizeRule('P', "32767","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"NCHAR","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","NULL","Y","NULL","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"NVARCHAR","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"TEXT","NULL","T"), new SizeRule('P', "32767","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"BINARY","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARBINARY","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"IMAGE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BIT","NULL","B"), new SizeRule('P', "1","","1","0"), new OtherRule("-128","127","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"TINYINT","NULL","B"), new SizeRule('P', "3","","3","0"), new OtherRule("255","","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('P', "5","","5","0"), new OtherRule("-32768","32767","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"SMALLINT","NULL","T"), new SizeRule('P', "5","","5","0"), new OtherRule("0","65535","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INT","NULL","B"), new SizeRule('P', "10","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INT","NULL","T"), new SizeRule('P', "10","","10","0"), new OtherRule("0"," 4294967295","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('P', "19","","19","0"), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"UNSIGNEDBIGINT","NULL","B"), new SizeRule('P', "19","","19","0"), new OtherRule("0","18446744073709551615","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"SMALLMONEY","NULL","B"), new SizeRule('S', "10","","10","4"), new OtherRule("-214748.3648","214748.3647","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"MONEY","NULL","B"), new SizeRule('S', "19","","19","4"), new OtherRule("–922337203685477.5808 ","–922337203685477.5808 ","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","B"), new SizeRule('S', "38","","18","0"), new OtherRule("-10^38+1","10^38-1","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"FLOAT","NULL","T"), new SizeRule('P', "15","","15","0"), new OtherRule("1.175494351e-308","1.175494351e+308","Y","NULL","9","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('P', "15","","15","0"), new OtherRule("3.402823466e-38","3.402823466e+38","Y","NULL","5","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"REAL","NULL","B"), new SizeRule('P', "7","","7","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATETIME","NULL","T"), new SizeRule('S', "23","","23","3"), new OtherRule("01 januarY 0001","31 december 9999 ","Y","NULL","7","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"DATETIME","NULL","T"), new SizeRule('S', "23","","23","3"), new OtherRule("01 januarY 0001 '00:00:00.000","31 december 9999 23:59:59.990","Y","NULL","0","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"DATETIME","NULL","B"), new SizeRule('S', "23","","23","3"), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP","NULL","B"), new SizeRule('P', "8","","8","0"), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"SMALLDATETIME","NULL","B"), new SizeRule('P', "19","","19","0"), new OtherRule("1 JanuarY 1990, 00.00.00.000000000","6 JUNE 2079, 23:59:59.999999","Y","NULL","4","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"SMALLDATETIME","NULL","T"), new SizeRule('P', "19","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_DEPRECATED,"NCHAR(N)",dsDataType.UNICODE_CHAR.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_DEPRECATED,"NVARCHAR(N)",dsDataType.UNICODE_VARCHAR.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"INTEGER",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMERIC",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"UNSIGNEDINT",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"UNSIGNED SMALLINT",dsDataType.NUM_SMALLINT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMERIC",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMERIC IDENTITY",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"TIME",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"UNITEXT",dsDataType.UNICODE_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"UNICHAR(N)",dsDataType.UNICODE_CHAR.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"UNIVARCHAR(N)",dsDataType.UNICODE_VARCHAR.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"VARCHAR(N)",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"DATE",dsDataType.DATE_DATETIME.toString(),"B"), new SizeRule('N', "","","",""), new OtherRule("01 januarY 0001","31 december 9999 ","Y","NULL","7","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"TIME",dsDataType.DATE_DATETIME.toString(),"T"), new SizeRule('N', "","","",""), new OtherRule("01 januarY 0001 '00:00:00.000","31 december 9999 23:59:59.990","Y","NULL","0","Y")));
		sybaseDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"BIGDATETIME",dsDataType.DATE_DATETIME.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
		
		
		return sybaseDataTypeRules;
	}
	


	public static List<DataTypeRule> getRedshiftRules()
	{
		if(redshiftDataTypeRules!=null)
			return redshiftDataTypeRules;

		 redshiftDataTypeRules = new ArrayList<DataTypeRule>();

		/*redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('N',"0","0","16384",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('N',"0","0","16384",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"VARCHAR(64000)","NULL","T"), new SizeRule('N',"0","0","16384",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"CHAR","NULL","T"), new SizeRule('N',"0","0",""), new OtherRule("NULL","NULL","NULL","Y","NULL","2","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"VARCHAR","NULL","T"), new SizeRule('N',"0","0","16384",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"VARCHAR","NULL","T"), new SizeRule('N',"0","0","16384",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"VARCHAR(64000)","NULL","T"), new SizeRule('N',"0","0","16384",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARCHAR(64000)","NULL","T"), new SizeRule('N',"0","0","16384",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"VARCHAR(64000)","NULL","B"), new SizeRule('N',"0","0","16384",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BOOLEAN","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"SMALLINT","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("255","","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32768","32767","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"INTEGER","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("0","65535","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INTEGER","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INTEGER","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("0"," 4294967295","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"BIGINT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("0","18446744073709551615","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"DECIMAL(10,4)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-214748.3648","214748.3647","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"DECIMAL(19,4)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("–922337203685477.5808 ","–922337203685477.5808 ","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","B"), new SizeRule('N',"-84","127","0","-10^38+1","10^38-1","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DOUBLE PRECISION","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1.175494351e-308","1.175494351e+308","Y","NULL","9","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("3.402823466e-38","3.402823466e+38","Y","NULL","5","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"REAL","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01 januarY 0001","31 december 9999 ","Y","NULL","7","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("01 januarY 0001 '00:00:00.000","31 december 9999 23:59:59.990","Y","NULL","0","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 1990, 00.00.00.000000000","6 JUNE 2079, 23:59:59.999999","Y","NULL","4","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMPTZ","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"CHARACTER",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N',"0","0","255",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"INT",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N',"0","0",""), new OtherRule("NULL","NULL","","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMERIC",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT4",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("1.175494351e-308","1.175494351e+308","Y","NULL","9","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT8",dsDataType.NUM_FLOAT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("3.402823466e-38","3.402823466e+38","Y","NULL","5","Y")));
		//redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT",dsDataType.NUM_FLOAT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("3.402823466e-38","3.402823466e+38","Y","NULL","5","Y")));
		//redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"NCHAR",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N',"0","0","16384",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"BPCHAR",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N',"0","0","16384",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		//redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"NVARCHAR",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"0","0","16384",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		//redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"TEXT",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"0","0","16384",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));  
		*/
		
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"VARCHAR","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","65535","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","65535","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"VARCHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","65535","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"VARCHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","65535","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"VARCHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","65535","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"VARCHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","65535","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"VARCHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","65535","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARCHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","65535","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"VARCHAR","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","65535","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BOOL","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"INT2","NULL","T"), new SizeRule('N', "","","5","0"), new OtherRule("-32768","32767","Y","NULL","2","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"INT2","NULL","B"), new SizeRule('N', "","","5","0"), new OtherRule("-32768","32767","Y","NULL","2","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"INT2","NULL","T"), new SizeRule('N', "","","5","0"), new OtherRule("-32768","32767","Y","NULL","2","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INT8","NULL","T"), new SizeRule('N', "","","19","0"), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INT8","NULL","T"), new SizeRule('N', "","","19","0"), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"INT8","NULL","B"), new SizeRule('N', "","","19","0"), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"INT8","NULL","T"), new SizeRule('N', "","","19","0"), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"NUMERIC","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-9223372036854775807","9223372036854775807","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"NUMERIC","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-9223372036854775807","9223372036854775807","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"NUMERIC","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-9223372036854775807","9223372036854775807","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"NUMERIC","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-9223372036854775807","9223372036854775807","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"NUMERIC","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-9223372036854775807","9223372036854775807","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"NUMERIC","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-9223372036854775807","9223372036854775807","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"TIMESTAMP","NULL","B"), new SizeRule('N', "","","29","6"), new OtherRule("4713 BC","294276 AD","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","29","6"), new OtherRule("4713 BC","294276 AD","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","29","6"), new OtherRule("4713 BC","294276 AD","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","29","6"), new OtherRule("4713 BC","294276 AD","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("4713 BC","294276 AD","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","29","6"), new OtherRule("4713 BC","294276 AD","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","65535","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","65535","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"CHARACTER",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4096","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"INT",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","2","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT4",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('S', "","","",""), new OtherRule("-9223372036854775807","9223372036854775807","Y","NULL","4","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT8",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('S', "","","",""), new OtherRule("-9223372036854775807","9223372036854775807","Y","NULL","8","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"NCHAR",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4096","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"BPCHAR",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","256","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"BPCHAR",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","256","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"CHARACTER",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4096","Y")));
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"TIMESTAMPTZ",dsDataType.DATE_SMALLDATETIME.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","8","Y")));  
		redshiftDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"INT4",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));

		
		return redshiftDataTypeRules;
	}
	
	public static List<DataTypeRule> getBigqueryRules()
	{
		if(bigqueryDataTypeRules!=null)
			return bigqueryDataTypeRules;

		 bigqueryDataTypeRules = new ArrayList<DataTypeRule>();

		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"STRING","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"STRING","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"STRING","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"STRING","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"STRING","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"STRING","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"BYTES","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"BYTES","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"BYTES","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BOOL","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"INT64","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"INT64","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"INT64","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INT64","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INT64","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"INT64","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"INT64","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"NUMERIC","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"NUMERIC","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"NUMERIC","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"FLOAT64","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT64","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"NUMERIC","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIME","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"STRING","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"STRING","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"STRUCT",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"ARRAY",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"GEOGRAPHY",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
				
/*
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"CLOB","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"VARGRAPHIC","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		//bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"GRAPHIC",dsDataType.UNICODE_CHAR.toString(),"S"), new SizeRule('N',"0","0","128",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"STRING","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"STRING","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		//bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"BINARY",dsDataType.BIN_CHAR,"S"), new SizeRule('N',"0","0","255",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"BYTES","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"BYTES","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BOOL","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"INT64","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"INT64","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"INT64","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INT64","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INT64","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"INT64","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"INT64","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"NUMERIC","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"NUMERIC","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"NUMERIC","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"FLOAT64","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT64","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		//bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"INTEGER",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		//bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMERIC",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		//bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"DOUBLE",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","8","Y")));
		//bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"REAL",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"DATETIME","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"BYTES","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		//bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"real","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		bigqueryDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
 */


		  
		return bigqueryDataTypeRules;
	}

	public static List<DataTypeRule> getAzuresqlRules()
	{
		if(azuresqlDataTypeRules!=null)
			return azuresqlDataTypeRules;

		 azuresqlDataTypeRules = new ArrayList<DataTypeRule>();

		/*azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR(n)","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"NVARCHAR(n)","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"NCHAR(n)","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"NVARCHAR(n)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"NVARCHAR(n)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"BINARY(n)",dsDataType.BIN_VARCHAR.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARBINARY(n)","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"NVARCHAR(n)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BIT","NULL","B"), new SizeRule('N', "","","1","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"TINYINT","NULL","B"), new SizeRule('N', "","","3","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32768","32767","Y","NULL","2","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"SMALLINT","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","5","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INT","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","9","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('N', "","","19","0"), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","8","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"BIGINT","NULL","T"), new SizeRule('N', "","","19","0"), new OtherRule("NULL","NULL","Y","NULL","13","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"SMALLMONEY","NULL","B"), new SizeRule('N', "","","10","4"), new OtherRule("-214748.3648","214748.3647","Y","NULL","4","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"MONEY","NULL","B"), new SizeRule('N', "","","19","4"), new OtherRule("-922,337,203,685,477.5808","922,337,203,685,477.5807","Y","NULL","8","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","B"), new SizeRule('N',"0","38","0",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","5","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"REAL","NULL","B"), new SizeRule('N', "","","7","0"), new OtherRule("-1.79E + 308","1.79E + 308","Y","53","0","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT(n)","NULL","B"), new SizeRule('N', "","","15","0"), new OtherRule("-3.40E + 38","3.40E + 38","Y","NULL","4","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"REAL","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		//azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMERIC",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N',"0","38","0","-10^38+1","10^38-1","Y","NULL","5","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","23","3"), new OtherRule("01/01/0001","31/12/9999","Y","NULL","3","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIME","NULL","B"), new SizeRule('N', "","","8","0"), new OtherRule("00:00:00","23:59:59.999999","Y","NULL","5","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"DATETIME2","NULL","B"), new SizeRule('N', "","","27","7"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 23:59:59.999999","Y","NULL","8","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"DATETIMEOFFSET","NULL","B"), new SizeRule('N', "","","27","7"), new OtherRule("1 JanuarY 0001 0001  00:00:00.0000000  -14:00","31 December 9999, 23:59:59.999999","Y","NULL","10","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"SMALLDATETIME","NULL","B"), new SizeRule('N', "","","19","0"), new OtherRule("1 JanuarY 1990, 00.00.00.000000000","6 JUNE 2079, 23:59:59.999999","Y","NULL","4","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"BINARY(n)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR(n)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR(n)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
*/
		//String isSizeApplicable, String maxLengthPrecision, String maxScale, String defaultLengthPrecision, String defaultScale
			
		
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('P', "8000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('P', "8000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"NVARCHAR","NULL","B"), new SizeRule('P', "4000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"NCHAR","NULL","B"), new SizeRule('P', "4000","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"NVARCHAR","NULL","T"), new SizeRule('P', "4000","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"NVARCHAR","NULL","T"), new SizeRule('P', "4000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"BINARY","NULL","B"), new SizeRule('P', "8000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARBINARY","NULL","B"), new SizeRule('P', "8000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"NVARCHAR","NULL","T"), new SizeRule('P', "4000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BIT","NULL","B"), new SizeRule('S', "1","0","1","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"TINYINT","NULL","B"), new SizeRule('S', "3","0","3","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('S', "5","0","5","0"), new OtherRule("-32768","32767","Y","NULL","2","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"SMALLINT","NULL","T"), new SizeRule('S', "5","0","5","0"), new OtherRule("-2147483648","2147483647","Y","NULL","5","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INT","NULL","B"), new SizeRule('S', "10","0","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INT","NULL","T"), new SizeRule('S', "10","0","10","0"), new OtherRule("NULL","NULL","Y","NULL","9","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('S', "19","0","19","0"), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","8","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"BIGINT","NULL","T"), new SizeRule('S', "19","0","19","0"), new OtherRule("NULL","NULL","Y","NULL","13","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"SMALLMONEY","NULL","B"), new SizeRule('S', "10","4","10","4"), new OtherRule("-214748.3648","214748.3647","Y","NULL","4","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"MONEY","NULL","B"), new SizeRule('S', "19","4","19","4"), new OtherRule("-922,337,203,685,477.5808","922,337,203,685,477.5807","Y","NULL","8","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","B"), new SizeRule('S',"28","5","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","5","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"REAL","NULL","T"), new SizeRule('S', "28","5","7","0"), new OtherRule("-1.79E + 308","1.79E + 308","Y","53","0","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('S', "28","5","15","0"), new OtherRule("-3.40E + 38","3.40E + 38","Y","NULL","4","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"REAL","NULL","B"), new SizeRule('S', "28","5","7","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","23","3"), new OtherRule("01/01/0001","31/12/9999","Y","NULL","3","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIME","NULL","B"), new SizeRule('N', "","","8","0"), new OtherRule("00:00:00","23:59:59.999999","Y","NULL","5","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"DATETIME2","NULL","B"), new SizeRule('N', "","","27","7"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 23:59:59.999999","Y","NULL","8","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"DATETIMEOFFSET","NULL","B"), new SizeRule('N', "","","27","7"), new OtherRule("1 JanuarY 0001 0001  00:00:00.0000000  -14:00","31 December 9999, 23:59:59.999999","Y","NULL","10","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"SMALLDATETIME","NULL","B"), new SizeRule('N', "","","19","0"), new OtherRule("1 JanuarY 1990, 00.00.00.000000000","6 JUNE 2079, 23:59:59.999999","Y","NULL","4","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('P', "8000","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('P', "8000","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"sysname",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","2147483647",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"uniqueidentifier",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","2147483647",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azuresqlDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"DATETIME",dsDataType.DATE_DATETIME.toString(),"S"), new SizeRule('N',"","","2147483647",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));

		
		return azuresqlDataTypeRules;
	}

	public static List<DataTypeRule> getSnowflakeRules()
	{
		if(snowflakeDataTypeRules!=null)
			return snowflakeDataTypeRules;

		 snowflakeDataTypeRules = new ArrayList<DataTypeRule>();

		/* bala
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"TEXT","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARBINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"BINARY","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BOOLEAN","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"NUMBER(3)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"NUMBER(5)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"NUMBER(6)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"NUMBER(10)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"NUMBER(11)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"NUMBER(19)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"NUMBER(20)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"NUMBER(10,4)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"NUMBER(19,4)","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"NUMBER","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DOUBLE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"REAL","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"DATETIME","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP_TZ","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		//snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"CHAR",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N',"0","0","255",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		//snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"TEXT",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"0","0","128",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		//snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"VARBINARY",dsDataType.BIN_CHAR,"S"), new SizeRule('N',"0","0","255",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"INTEGER",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMERIC",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT4",dsDataType.NUM_FLOAT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"DOUBLE PRECISION ",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","8","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT8 ",dsDataType.NUM_FLOAT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		//snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"DOUBLE",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","8","Y")));
		//snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"REAL",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"TIMESTAMP_LTZ",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
		snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"TIMESTAMP_NTZ",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
		*/
		 
		 	snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('P',"16777216","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('P',"16777216","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"TEXT","NULL","B"), new SizeRule('P',"16777216","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"VARCHAR","NULL","T"), new SizeRule('P', "16777216","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"VARCHAR","NULL","T"), new SizeRule('P',"16777216","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"VARCHAR","NULL","T"), new SizeRule('P',"16777216","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"BINARY","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"BINARY","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"BINARY","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BOOLEAN","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"BYTEINT","NULL","B"), new SizeRule('S', "","","3","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('S', "","","5","0"), new OtherRule("-32768","32767","Y","NULL","0","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"SMALLINT","NULL","T"), new SizeRule('S', "","","5","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INT","NULL","B"), new SizeRule('S', "","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INT","NULL","T"), new SizeRule('S', "","","10","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"BIGINT","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"NUMERIC","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"NUMERIC","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","B"), new SizeRule('S', "","","",""),new OtherRule("NULL","NULL","Y","NULL","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DOUBLE","NULL","B"), new SizeRule('S', "","","15","0"), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"REAL","NULL","B"), new SizeRule('S', "","","7","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","19","0"), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIME","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('N', "","","26","6"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP_TZ","NULL","B"), new SizeRule('N', "","","15","0"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"INTERVAL","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"STRING",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('P',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"CHARACTER","CHAR_VARCHAR ","S"), new SizeRule('P',"","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"VARBINARY",dsDataType.BIN_CHAR.toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMBER",dsDataType.NUM_INT.toString(),"S"), new SizeRule('S', "38","37","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"INTEGER",dsDataType.NUM_INT.toString(),"S"), new SizeRule('S', "38","0","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT4",dsDataType.NUM_FLOAT.toString(),"S"), new SizeRule('S', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"DOUBLE PRECISION",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('S', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","8","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT8",dsDataType.NUM_FLOAT.toString(),"S"), new SizeRule('S', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"BIGDECIMAL",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"TIMESTAMP_LTZ",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"DATETIME",dsDataType.DATE_DATETIME.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
			snowflakeDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"TIMESTAMP_NTZ",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
			
		return snowflakeDataTypeRules;
	}
	
	public static List<DataTypeRule> getDatastageRules()
	{
		if(datastageDataTypeRules!=null)
			return datastageDataTypeRules;
		datastageDataTypeRules = new ArrayList<DataTypeRule>();
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"Char","NULL","B"), new SizeRule('P', "255","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VarChar","NULL","B"), new SizeRule('P', "2000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"LongVarChar","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"NChar","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"NVarChar","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"LongVarChar","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"Binary","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));	  
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"NVarChar","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"LongVarBinary","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"Bit","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"TinyInt","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SmallInt","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"SmallInt","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"Integer","NULL","B"), new SizeRule('S', "38","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"Integer","NULL","T"), new SizeRule('S', "38","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BigInt","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"BigInt","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"Decimal","NULL","T"), new SizeRule('S', "38","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"Decimal","NULL","T"), new SizeRule('S', "38","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"Decimal","NULL","B"), new SizeRule('S', "38","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"Double","NULL","B"), new SizeRule('S', "38","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"Float","NULL","B"), new SizeRule('S', "38","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"Real","NULL","B"), new SizeRule('S', "38","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"Date","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"Time","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"Timestamp","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"Timestamp","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"Timestamp","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"Timestamp","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL_DEPRECATED,"Numeric",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('S', "38","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"Encrypted",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"PathName",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"List",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"VarBinary",dsDataType.BIN_VARCHAR.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"String",dsDataType.BIN_VARCHAR.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VarChar","NULL","T"), new SizeRule('P', "2000","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		datastageDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VarChar","NULL","T"), new SizeRule('P', "2000","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		
		return datastageDataTypeRules;
	}
	
	public static List<DataTypeRule> getSsisRules()
    {
                    if(ssisDataTypeRules!=null)
                                    return ssisDataTypeRules;

                    ssisDataTypeRules = new ArrayList<DataTypeRule>();


                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"[DT_STR].str","NULL","T"), new SizeRule('P', "8000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"[DT_STR].str","NULL","B"), new SizeRule('P', "8000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"[DT_TEXT].text","NULL","B"), new SizeRule('P', "2147483647","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"[DT_WSTR].wstr","NULL","T"), new SizeRule('P', "4000","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"[DT_WSTR].wstr","NULL","B"), new SizeRule('P', "4000","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"[DT_NTEXT].nText","NULL","B"), new SizeRule('P', "1073741823","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"[DT_BYTES].bytes","NULL","T"), new SizeRule('P', "8000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"[DT_BYTES].bytes","NULL","B"), new SizeRule('P', "8000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"[DT_IMAGE].image","NULL","B"), new SizeRule('P', "2147483647","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"[DT_BOOL].bool","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"[DT_I1].i1","NULL","B"), new SizeRule('S', "1","","10","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"[DT_I2].i2","NULL","B"), new SizeRule('S', "2","","10","0"), new OtherRule("-32768","32767","Y","NULL","0","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"[DT_UI2].ui2","NULL","B"), new SizeRule('S', "2","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"[DT_I4].i4","NULL","B"), new SizeRule('S', "4","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"[DT_UI4].ui4","NULL","B"), new SizeRule('S', "4","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"[DT_I8].i8","NULL","B"), new SizeRule('S', "8","","19","0"), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"[DT_UI8].ui8","NULL","B"), new SizeRule('S', "8","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"[DT_CY].cy","NULL","T"), new SizeRule('S', "19","4","28","28"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"[DT_CY].cy","NULL","B"), new SizeRule('S', "19","4","28","28"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"[DT_R8].r8","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"[DT_R8].r8","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"[DT_R4].r4","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"[DT_R8].r8","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"[DT_DATE].date","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"[DT_DBTIME].dbTime","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"[DT_DBTIMESTAMP].dbTimeStamp","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"[DT_DBTIMESTAMPOFFSET].dbTimeStampOffset","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"[DT_DBTIMESTAMP].dbTimeStamp","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"[DT_DBTIMESTAMP].dbTimeStamp","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"[DT_DECIMAL].decimal",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('S', "29","28","38","38"), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"[DT_NUMERIC].numeric",dsDataType.NUM_INT.toString(),"S"), new SizeRule('S', "38","38","38","38"), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"[DT_UI1].ui1",dsDataType.NUM_TINYINT.toString(),"S"), new SizeRule('S', "1","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"[DT_DBTIME2].dbTime2",dsDataType.DATE_TIME.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"[DT_DBTIMESTAMP2].dbTimeStamp2",dsDataType.DATE_DATETIME.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"[DT_DBDATE].dbDate",dsDataType.DATE_DATE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"[DT_STR].str","NULL","T"), new SizeRule('P', "8000","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
                    ssisDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"[DT_STR].str","NULL","T"), new SizeRule('P', "8000","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));


                    return ssisDataTypeRules;
    }
	
	
	public static List<DataTypeRule> getInformaticaBDMRules()
    {
                    if(informaticaBDMDataTypeRules!=null)
                                    return informaticaBDMDataTypeRules;

                    informaticaBDMDataTypeRules = new ArrayList<DataTypeRule>();


                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"string","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"text","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"nstring","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"nstring","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"ntext","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"binary","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"binary","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"binary","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"small integer","NULL","T"), new SizeRule('S', "","","5","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"small integer","NULL","T"), new SizeRule('S', "","","5","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"small integer","NULL","B"), new SizeRule('S', "","","5","0"), new OtherRule("-32768","32767","Y","NULL","0","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"small integer","NULL","T"), new SizeRule('S', "","","5","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"integer","NULL","B"), new SizeRule('S', "","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"integer","NULL","T"), new SizeRule('S', "","","10","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"bigint","NULL","B"), new SizeRule('S', "","","19","0"), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"bigint","NULL","T"), new SizeRule('S', "","","19","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"decimal","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"decimal","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"decimal","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"double","NULL","B"), new SizeRule('S', "","","15","0"), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"double","NULL","T"), new SizeRule('S', "","","15","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"real","NULL","B"), new SizeRule('S', "","","7","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"date/time","NULL","T"), new SizeRule('S', "","","29","9"), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"date/time","NULL","T"), new SizeRule('S', "","","29","9"), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"date/time","NULL","B"), new SizeRule('S', "","","29","9"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"date/time","NULL","T"), new SizeRule('S', "","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"date/time","NULL","T"), new SizeRule('S', "","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"date/time","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    informaticaBDMDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    
                    

                    return informaticaBDMDataTypeRules;
    }
	
	
	
	public static List<DataTypeRule> getInformaticaPCRules()
    {
                    if(informaticaPCDataTypeRules!=null)
                                    return informaticaPCDataTypeRules;

                    informaticaPCDataTypeRules = new ArrayList<DataTypeRule>();


       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"string","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"text","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"nstring","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"nstring","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"ntext","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"binary","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"binary","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"binary","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"small integer","NULL","T"), new SizeRule('S', "","","5","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"small integer","NULL","T"), new SizeRule('S', "","","5","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"small integer","NULL","B"), new SizeRule('S', "","","5","0"), new OtherRule("-32768","32767","Y","NULL","0","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"small integer","NULL","T"), new SizeRule('S', "","","5","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"integer","NULL","B"), new SizeRule('S', "","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"integer","NULL","T"), new SizeRule('S', "","","10","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"bigint","NULL","B"), new SizeRule('S', "","","19","0"), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"bigint","NULL","T"), new SizeRule('S', "","","19","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"decimal","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"decimal","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"decimal","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"double","NULL","B"), new SizeRule('S', "","","15","0"), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"double","NULL","T"), new SizeRule('S', "","","15","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"real","NULL","B"), new SizeRule('S', "","","7","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"date/time","NULL","T"), new SizeRule('S', "","","29","9"), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"date/time","NULL","T"), new SizeRule('S', "","","29","9"), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"date/time","NULL","B"), new SizeRule('S', "","","29","9"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"date/time","NULL","T"), new SizeRule('S', "","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"date/time","NULL","T"), new SizeRule('S', "","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"date/time","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
       informaticaPCDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                   
       return informaticaPCDataTypeRules;
    }

	public static List<DataTypeRule> getApacheBeamRules()
	{
		if(apacheBeamDataTypeRules!=null)
			return apacheBeamDataTypeRules;

		apacheBeamDataTypeRules = new ArrayList<DataTypeRule>();


		//apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"CHARACTER",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N',"0","0","255",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		//apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"GRAPHIC","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		//apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"VARGRAPHIC","NULL","B"), new SizeRule('N',"0","0","16352",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		//apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"DBCLOB","NULL","B"), new SizeRule('N',"0","0","1",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		//apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"BINARY","NULL","B"), new SizeRule('N',"0","0","255",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		//apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARBINARY","NULL","B"), new SizeRule('N',"0","0","32704",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		//apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"BLOB","NULL","B"), new SizeRule('N',"0","0","2",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		//apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"SMALLINT","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"TINYINT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"DECIMAL","NULL","T"), new SizeRule('S', "5","2","38","38"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INTEGER","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"DECIMAL","NULL","T"), new SizeRule('S', "5","2","38","38"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"INTEGER","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"DECIMAL","NULL","T"), new SizeRule('S', "5","2","38","38"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"DECIMAL","NULL","T"), new SizeRule('S', "5","2","38","38"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"DECIMAL","NULL","T"), new SizeRule('S', "5","2","38","38"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","B"), new SizeRule('S', "5","2","38","38"), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DOUBLE","NULL","B"), new SizeRule('P', "15","","15","0"), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('P', "6","","15","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		//apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"INTEGER",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		//apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMERIC",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		//apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"DOUBLE",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","8","Y")));
		//apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"REAL",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		//apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"real","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		apacheBeamDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"NULL",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		

		return apacheBeamDataTypeRules;
	}
	
	

	public static List<DataTypeRule> getJavaRules()
	{
		if(javaDataTypeRules!=null)
			return javaDataTypeRules;

		javaDataTypeRules = new ArrayList<DataTypeRule>();


		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"char","NULL","B"), new SizeRule('N', "65535","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"String","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"String","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"char","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"String","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"String","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"byte","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"byte[]","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"byte[]","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"boolean","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"short","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"short","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"short","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"int","NULL","B"), new SizeRule('P', "2147483647","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"int","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"long","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"long","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"double","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"double","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"double","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"double","NULL","B"), new SizeRule('S', "","","15","0"), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"float","NULL","B"), new SizeRule('S', "","","15","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"double","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
        javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"Date","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"Date","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"Date","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"Date","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"Date","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"Date","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"String","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"String","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		javaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"NULL",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		
		return javaDataTypeRules;
	}
	
	public static List<DataTypeRule> getAzureDataFactoryRules()
	{
		if(azureDataFactoryDataTypRules!=null)
			return azureDataFactoryDataTypRules;

		azureDataFactoryDataTypRules = new ArrayList<DataTypeRule>();


		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"String","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"Byte","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"Byte","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"Byte","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"Boolean","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"Int16","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"Int16","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"Int16","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"Int32","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"Int32","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"Int64","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"Int64","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"Decimal","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"Decimal","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"Decimal","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"Double","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"Single","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"Double","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DateTime","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"DateTime","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"DateTime","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"DateTimeOffset","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"DateTime","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"DateTime","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		azureDataFactoryDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		
		
		return azureDataFactoryDataTypRules;
	}
	public static List<DataTypeRule> getAzureDataFactoryDataFlowRules()
	{
		if(azureDataFactoryDataFlowRules!=null)
			return azureDataFactoryDataFlowRules;

		azureDataFactoryDataFlowRules = new ArrayList<DataTypeRule>();


		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"string","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"string","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"string","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"string","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"string","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"string","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"string","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"string","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"boolean","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"short","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"short","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"short","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"integer","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"integer","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"integer","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"long","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"float","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"float","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"decimal","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"double","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"float","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"float","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"date","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"date","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"date","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"date","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"timestamp","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"timestamp","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		azureDataFactoryDataFlowRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		
		
		return azureDataFactoryDataFlowRules;
	}
	
	public static List<DataTypeRule> getTalendRules()
	{
		if(talendDataTypeRules!=null)
			return talendDataTypeRules;

		talendDataTypeRules = new ArrayList<DataTypeRule>();


		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"id_Character","NULL","B"), new SizeRule('P', "65535","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"id_String","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"id_String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"id_Character","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"id_String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"id_String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"id_byte[]","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"id_byte[]","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"id_byte[]","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"id_Boolean","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"id_Byte","NULL","B"), new SizeRule('S', "3","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"id_Short","NULL","B"), new SizeRule('S', "5","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"id_Short","NULL","T"), new SizeRule('S', "5","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"id_Integer","NULL","B"), new SizeRule('S', "10","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"id_Integer","NULL","T"), new SizeRule('S', "10","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"id_Long","NULL","B"), new SizeRule('S', "19","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"id_Long","NULL","T"), new SizeRule('S', "19","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"id_BigDecimal","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"id_BigDecimal","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"id_BigDecimal","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"id_Double","NULL","B"), new SizeRule('S', "19","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"id_Float","NULL","B"), new SizeRule('S', "10","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"id_Double","NULL","T"), new SizeRule('S', "19","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"id_Date","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"id_Date","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"id_Date","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"id_Date","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"id_Date","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"id_Date","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"id_String","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		talendDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"id_String","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		
		
		return talendDataTypeRules;
	}
	
	public static List<DataTypeRule> getFlatFileRules()
	{
		if(flatFileDataTypeRules!=null)
			return flatFileDataTypeRules;

		flatFileDataTypeRules = new ArrayList<DataTypeRule>();


		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"string","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"nstring","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"int","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"short","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"short","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"short","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"int","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"int","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"bigint","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"bigint","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"number","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"number","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"number","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"double","NULL","B"),new SizeRule('S', "","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"number","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"double","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"datetime","NULL","T"), new SizeRule('S', "","","29","9"), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"datetime","NULL","T"), new SizeRule('S', "","","29","9"), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"datetime","NULL","B"), new SizeRule('S', "","","29","9"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"datetime","NULL","T"), new SizeRule('S', "","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"datetime","NULL","T"), new SizeRule('S', "","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"datetime","NULL","T"), new SizeRule('S', "","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"character",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"binary",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"text",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"long",dsDataType.NUM_INT.toString(),"S"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"long",dsDataType.NUM_INT.toString(),"S"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"integer",dsDataType.NUM_INT.toString(),"S"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"smallint",dsDataType.NUM_INT.toString(),"S"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"bigint",dsDataType.NUM_INT.toString(),"S"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"decimal",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"real",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"date",dsDataType.DATE_DATETIME.toString(),"S"), new SizeRule('S', "","","29","9"), new OtherRule("00:00:00.000000  -12.59","23:59:61.999999  +14.00","Y","NULL","8","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"time",dsDataType.DATE_DATETIME.toString(),"S"), new SizeRule('S', "","","29","9"), new OtherRule("00:00:00.000000  -12.59","23:59:61.999999  +14.00","Y","NULL","8","Y")));
	    flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		flatFileDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		
		return flatFileDataTypeRules;
	}
	
	
	
	public static List<DataTypeRule> getOdbcRules()
    {
                    if(odbcDataTypeRules!=null)
                                    return odbcDataTypeRules;

                    odbcDataTypeRules = new ArrayList<DataTypeRule>();
                   
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('P', "2000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('P', "32767","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"TEXT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"NCHAR","NULL","B"), new SizeRule('P', "2000","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"NVARCHAR","NULL","B"), new SizeRule('P', "32767","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"NTEXT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"BINARY","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARBINARY","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"LONGVARBINARY","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BIT","NULL","B"),new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"TINYINT","NULL","B"), new SizeRule('S', "","","3","0"), new OtherRule("0","255","Y","NULL","1","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('S', "","","5","0"), new OtherRule("-32768","32767","Y","NULL","0","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"SMALLINT","NULL","T"), new SizeRule('S', "","","5","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INTEGER","NULL","B"), new SizeRule('S', "","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INTEGER","NULL","T"), new SizeRule('S', "","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('S', "","","19","0"), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","8","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"BIGINT","NULL","T"), new SizeRule('S', "","","19","0"), new OtherRule("NULL","NULL","Y","NULL","13","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"DECIMAL","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"DECIMAL","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DOUBLE","NULL","B"), new SizeRule('S',"","","15","0"), new OtherRule("-1.79E + 308","1.79E + 308","Y","53","0","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('S',"","","15","0"), new OtherRule("-3.40E + 38","3.40E + 38","Y","NULL","4","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"REAL","NULL","B"), new SizeRule('S',"","","7","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N',"","","10","0"), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIME","NULL","B"), new SizeRule('S',"","","8","0"), new OtherRule("00:00:00","23:59:59.999999","Y","NULL","8","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('S',"","","29","9"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 23:59:59.999999","Y","NULL","12","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP","NULL","T"), new SizeRule('S',"","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 23:59:59.999999","Y","NULL","12","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('S',"","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 23:59:59.999999","Y","NULL","12","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMERIC",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","0","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('P', "2000","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
                    odbcDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('P', "32767","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));

                    return odbcDataTypeRules;
    }


	public static List<DataTypeRule> getMongoRules()
    {
           if(mongoDataTypeRules!=null)
                  return mongoDataTypeRules;

           mongoDataTypeRules = new ArrayList<DataTypeRule>();

           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"STRING","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"STRING","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"STRING","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"STRING","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"STRING","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"STRING","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"BINARY DATA","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"BINARY DATA","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"BINARY DATA","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BOOLEAN","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"INTEGER","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"INTEGER","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"INTEGER","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INTEGER","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","1","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INTEGER","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"INTEGER","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"INTEGER","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"INTEGER","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"INTEGER","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"INTEGER","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DOUBLE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("2.22507485850720E-308","2.22507485850720E-308","Y","NULL","9","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"DOUBLE","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1.17549E-38F","3.40282E+38F","Y","NULL","5","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"DOUBLE","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01 januarY  4712 BC","31 december 9999 AD","Y","NULL","7","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"DATE","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","0","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 23.59.61.99999","Y","NULL","10","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001 00.00.00.000000000 -12.59","31 December 9999, 23.59.61.99999   +14.00","Y","NULL","12","N")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 23.59.61.99999","Y","NULL","10","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"STRING","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
           mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"STRING","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
          
           //mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT",dsDataType.NUM_FLOAT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","0","Y")));
           //mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"BFILE",dsDataType.BIN_BLOB,"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","0","Y")));
           //mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"LONG RAW",dsDataType.BIN_BLOB,"S"), new SizeRule('N',"0","0","2147483647",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           //mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"LONG",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N',"0","0","2147483647",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           //mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"NCHAR","NULL","B"), new SizeRule('N',"0","0","2000",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
           //mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"NVARCHAR2","NULL","B"), new SizeRule('N',"0","0","4000",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
           //mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"NCLOB","NULL","B"), new SizeRule('N',"0","0","2147483647",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
           //mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"VARCHAR",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"0","0","4000",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           //mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"TIME WITH LOCAL TIMEZONE",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("00:00:00.000000  -12.59","23:59:61.999999  +14.00","Y","NULL","8","Y")));
           //mongoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"RAW","NULL","B"), new SizeRule('N',"0","0","2000",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           //mongoDataTypeRules.add(new DataTypeRule(new TypeRule("XML_XML","XMLTYPE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
           
           
           return mongoDataTypeRules;
    }

	
	public static List<DataTypeRule> getAuroraRules()
	{
		if(auroraDataTypeRules!=null)
			return auroraDataTypeRules;

		 auroraDataTypeRules = new ArrayList<DataTypeRule>();

		auroraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('P',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		auroraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		auroraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		auroraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INTEGER","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		auroraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		auroraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		auroraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		auroraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"REAL","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		auroraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		auroraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIME","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		auroraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		auroraDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMPZ","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		  
		return auroraDataTypeRules;
	}

	public static List<DataTypeRule> getAmazonS3()
	{
		if(amazonS3DataTypeRules!=null)
			return amazonS3DataTypeRules;

		amazonS3DataTypeRules = new ArrayList<DataTypeRule>();

		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"STRING","NULL","B"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("-2147483648","2147483647","Y","NULL","1","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("2.22507485850720E-308","2.22507485850720E-308","Y","NULL","9","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("1.17549E-38F","3.40282E+38F","Y","NULL","5","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("1.17549E-38F","3.40282E+38F","Y","NULL","5","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("01 januarY  4712 BC","31 december 9999 AD","Y","NULL","7","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("NULL","NULL","Y","NULL","0","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 23.59.61.99999","Y","NULL","10","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("1 JanuarY 0001 00.00.00.000000000 -12.59","31 December 9999, 23.59.61.99999   +14.00","Y","NULL","12","N")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 23.59.61.99999","Y","NULL","10","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"STRING","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		amazonS3DataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"STRING","NULL","T"), new SizeRule('N', "","","256","0"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		
		return amazonS3DataTypeRules;
	}

	public static List<DataTypeRule> getSQLDw()
	{
		if(sqlDwDataTypeRules!=null)
			return sqlDwDataTypeRules;

		sqlDwDataTypeRules = new ArrayList<DataTypeRule>();


		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('P', "8000","","8000",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('P', "8000","","8000",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"TEXT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"NCHAR","NULL","T"), new SizeRule('P', "4000","","4000",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"NVARCHAR","NULL","B"), new SizeRule('P', "4000","","4000",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"NTEXT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"BINARY","NULL","B"), new SizeRule('P', "8000","","8000",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARBINARY","NULL","B"), new SizeRule('P', "8000","","8000",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"IMAGE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BIT","NULL","B"), new SizeRule('N', "3","","3",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"TINYINT","NULL","B"), new SizeRule('N', "3","","3",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('N', "5","","5",""), new OtherRule("-32768","32767","Y","NULL","2","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"SMALLINT","NULL","T"), new SizeRule('N', "5","","5",""), new OtherRule("-2147483648","2147483647","Y","NULL","5","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INT","NULL","B"), new SizeRule('N', "10","","10",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INT","NULL","T"), new SizeRule('N', "10","","10",""), new OtherRule("NULL","NULL","Y","NULL","9","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('N', "19","","19",""), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","8","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"BIGINT","NULL","T"), new SizeRule('N', "19","","19",""), new OtherRule("NULL","NULL","Y","NULL","13","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"SMALLMONEY","NULL","B"), new SizeRule('N', "10","","10",""), new OtherRule("-214748.3648","214748.3647","Y","NULL","4","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"MONEY","NULL","B"), new SizeRule('N', "19","","19",""), new OtherRule("-922,337,203,685,477.5808","922,337,203,685,477.5807","Y","NULL","8","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","B"), new SizeRule('S', "38","","28","5"), new OtherRule("-10^38+1","10^38-1","Y","NULL","5","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DECIMAL","NULL","T"), new SizeRule('S', "38","","28","5"), new OtherRule("-1.79E + 308","1.79E + 308","Y","53","0","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('S', "15","","28","5"), new OtherRule("-3.40E + 38","3.40E + 38","Y","NULL","4","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"REAL","NULL","B"), new SizeRule('S', "7","","28","5"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "10","","",""), new OtherRule("01/01/0001","31/12/9999","Y","NULL","3","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIME","NULL","B"), new SizeRule('S', "27","7","13",""), new OtherRule("00:00:00","23:59:59.999999","Y","NULL","5","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"DATETIME","NULL","B"), new SizeRule('N', "23","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 23:59:59.999999","Y","NULL","8","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"DATETIMEOFFSET","NULL","B"), new SizeRule('S', "27","7","",""), new OtherRule("1 JanuarY 0001 0001  00:00:00.0000000  -14:00","31 December 9999, 23:59:59.999999","Y","NULL","10","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"SMALLDATETIME","NULL","B"), new SizeRule('N', "10","","",""), new OtherRule("1 JanuarY 1990, 00.00.00.000000000","6 JUNE 2079, 23:59:59.999999","Y","NULL","4","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"SMALLDATETIME","NULL","T"), new SizeRule('N', "10","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"BINARY",dsDataType.BIN_CHAR.toString(),"S"), new SizeRule('P', "8000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMERIC",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('S', "38","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","5","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"DateTime",dsDataType.DATE_DATETIME.toString(),"S"), new SizeRule('N', "23","","",""), new OtherRule("-10^38+1","10^38-1","Y","NULL","5","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('P', "8000","","8000",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		sqlDwDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('P', "8000","","8000",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));

		return sqlDwDataTypeRules;
}

	public static List<DataTypeRule> getSnowflakeCloud() 
	{
		if(snowflakeCloudDataTypeRules!=null)
			return snowflakeCloudDataTypeRules;

		snowflakeCloudDataTypeRules = new ArrayList<DataTypeRule>();

		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"VARCHAR","NULL","T"), new SizeRule('P',"16777216","","16777216",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('P',"16777216","","16777216",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"VARCHAR","NULL","T"), new SizeRule('P',"16777216","","16777216",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"VARCHAR","NULL","T"), new SizeRule('P', "16777216","","16777216",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"VARCHAR","NULL","T"), new SizeRule('P',"16777216","","16777216",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"VARCHAR","NULL","T"), new SizeRule('P',"16777216","","16777216",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"VARCHAR","NULL","T"), new SizeRule('P',"16777216","","16777216",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARBINARY","NULL","B"), new SizeRule('N',"8388608","","838860",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"VARCHAR","NULL","T"), new SizeRule('P',"16777216","","16777216",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BOOLEAN","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"NUMBER","NULL","T"), new SizeRule('S', "38","0","38","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"NUMBER","NULL","T"), new SizeRule('S', "38","0","38","0"), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"NUMBER","NULL","T"), new SizeRule('S', "38","0","38","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"NUMBER","NULL","T"), new SizeRule('S', "38","0","38","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"NUMBER","NULL","T"), new SizeRule('S', "38","0","38","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"NUMBER","NULL","T"), new SizeRule('S', "38","0","38","0"), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"NUMBER","NULL","T"), new SizeRule('S', "38","0","38","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"NUMBER","NULL","T"), new SizeRule('S', "38","0","38","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"NUMBER","NULL","T"), new SizeRule('S', "38","0","38","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"NUMBER","NULL","B"), new SizeRule('S', "38","0","38","0"), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DOUBLE","NULL","B"), new SizeRule('N',"19","","52","0"), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"DOUBLE","NULL","T"), new SizeRule('N',"19","","52","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"DOUBLE","NULL","T"), new SizeRule('N',"19","","52","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N',"9","","38","10"), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"DATE","NULL","T"), new SizeRule('N',"9","","38","10"), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMPNTZ","NULL","T"), new SizeRule('N',"9","","38","9"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMPNTZ","NULL","B"), new SizeRule('N',"9","","38","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMPNTZ","NULL","T"), new SizeRule('N', "9","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"INTERVAL","NULL","B"), new SizeRule('N', "9","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('P', "16777216","","16777216",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('P', "16777216","","16777216",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"INTEGER",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('S', "38","0","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMERIC",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('S', "38","0","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT4",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N',"19","","52","0"), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"DOUBLE PRECISION ",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N',"19","","52","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","8","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT",dsDataType.NUM_FLOAT.toString(),"S"), new SizeRule('N',"19","","52","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","8","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT8",dsDataType.NUM_FLOAT.toString(),"S"), new SizeRule('N', "19","","52","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"TIMESTAMP_LTZ",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N',"9","","38","9"), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"TIMESTAMP",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N',"9","","38","9"), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
		snowflakeCloudDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"DATETIME",dsDataType.DATE_DATE.toString(),"S"), new SizeRule('N', "9","","",""), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
		
		return snowflakeCloudDataTypeRules;

	}

	public static List<DataTypeRule> getGreenPlum()
	{
		if(greenPlumDataTypeRules!=null)
			return greenPlumDataTypeRules;

		greenPlumDataTypeRules = new ArrayList<DataTypeRule>();

		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR(n)","NULL","T"), new SizeRule('P',"","","16384",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR(n)","NULL","B"), new SizeRule('P',"","","32704",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"CHAR(n)","NULL","B"), new SizeRule('P',"","","2147483647",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"CHAR(n)","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"LONG VARCHAR(n)","NULL","T"), new SizeRule('P',"","","16352",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"LONG VARCHAR(n)","NULL","T"), new SizeRule('P',"","","1",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"CHAR(n)","NULL","T"), new SizeRule('P',"","","16384",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"LONG VARCHAR(n)","NULL","T"), new SizeRule('P',"","","32704",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"LONG VARCHAR(n)","NULL","T"), new SizeRule('P',"","","2",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BIT","NULL","B"), new SizeRule('S', "1","0","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"BIT","NULL","T"), new SizeRule('S', "1","0","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('S', "5","0","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"SMALLINT","NULL","T"), new SizeRule('S', "5","0","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INTEGER","NULL","B"), new SizeRule('S', "10","0","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INTEGER","NULL","T"), new SizeRule('S', "10","0","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('S', "19","0","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"BIGINT","NULL","T"), new SizeRule('S', "19","0","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"MONEY","NULL","B"), new SizeRule('S', "19","2","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"MONEY","NULL","T"), new SizeRule('S', "19","2","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","B"), new SizeRule('S',"","","0",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DOUBLE PRECISION","NULL","B"), new SizeRule('P', "19","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('P', "19","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"REAL","NULL","B"), new SizeRule('P', "10","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('P', "10","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIME","NULL","B"), new SizeRule('P', "19","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('P', "19","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP","NULL","T"), new SizeRule('P', "19","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('P', "19","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"TIMESTAMP","NULL","T"), new SizeRule('P', "19","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR(n)","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		greenPlumDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR(n)","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		
		
		return greenPlumDataTypeRules;

	}

	public static List<DataTypeRule> getAvro() 
	{
		if(avroTypeRules!=null)
			return avroTypeRules;

		avroTypeRules = new ArrayList<DataTypeRule>();


		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"union","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"string","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"string","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"bytes","NULL","B"), new SizeRule('N', "4000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"bytes","NULL","T"), new SizeRule('N', "4000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"bytes","NULL","T"), new SizeRule('N', "4000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"boolean","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"int","NULL","T"), new SizeRule('S', "10","0","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"int","NULL","T"), new SizeRule('S', "10","0","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"int","NULL","T"), new SizeRule('S', "10","0","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"int","NULL","B"), new SizeRule('S', "10","0","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"int","NULL","T"), new SizeRule('S', "10","0","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"long","NULL","T"), new SizeRule('S', "19","0","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"long","NULL","T"), new SizeRule('S', "19","0","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"double","NULL","B"), new SizeRule('S', "15","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"double","NULL","T"), new SizeRule('S', "15 	","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"double","NULL","T"), new SizeRule('S', "15","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"double","NULL","T"),new SizeRule('S', "15","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"float","NULL","B"), new SizeRule('S', "15","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"double","NULL","B"), new SizeRule('S', "15","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"date","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"time","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"timestamp","NULL","B"), new SizeRule('N', "","","29","9"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"timestamp","NULL","T"), new SizeRule('N', "","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"timestamp","NULL","T"), new SizeRule('N', "","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"timestamp","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"character",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"binary",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"text",dsDataType.CHAR_VARCHAR.toString().toString(),"P"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"integer",dsDataType.NUM_INT.toString(),"S"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"smallint",dsDataType.NUM_INT.toString(),"S"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"bigint",dsDataType.NUM_INT.toString(),"S"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"decimal",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('S', "38","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"real",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"duration",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("00:00:00.000000  -12.59","23:59:61.999999  +14.00","Y","NULL","8","Y")));
	    avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"string","NULL","T"), new SizeRule('P', "32767","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		avroTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"string","NULL","T"), new SizeRule('P', "32767","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		
		return avroTypeRules;
	}

	public static List<DataTypeRule> getHadoopHIVEDataTypRules()
	{
		if(hadoopHiveDataTypRules!=null)
			return hadoopHiveDataTypRules;

		hadoopHiveDataTypRules = new ArrayList<DataTypeRule>();
		
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"char","NULL","T"), new SizeRule('P', "255","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"varchar","NULL","B"), new SizeRule('P', "65535","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"array","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"char","NULL","T"), new SizeRule('P', "255","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"varchar","NULL","T"), new SizeRule('P', "65535","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"string","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"binary","NULL","B"), new SizeRule('N', "","","9",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"boolean","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"tinyint","NULL","B"), new SizeRule('N', "3","","5","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"smallint","NULL","B"), new SizeRule('N', "5","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"smallint","NULL","T"), new SizeRule('N', "5","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"int","NULL","B"), new SizeRule('N', "10","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"int","NULL","T"), new SizeRule('N', "10","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"bigint","NULL","B"), new SizeRule('N', "19","","19","0"), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"bigint","NULL","T"), new SizeRule('N', "19","","19","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"decimal","NULL","B"), new SizeRule('S', "38","","38","38"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"decimal","NULL","T"), new SizeRule('S', "38","","38","38"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"decimal","NULL","T"), new SizeRule('S', "38","","38","38"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"double","NULL","B"),new SizeRule('S', "19","","15",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"float","NULL","T"), new SizeRule('S', "10","","15",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"double","NULL","T"), new SizeRule('S', "19","","15",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"date","NULL","T"), new SizeRule('N', "","","10","0"), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"timestamp","NULL","T"), new SizeRule('N', "","","29","9"), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"timestamp","NULL","B"), new SizeRule('N', "","","29","9"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"timestamp","NULL","T"), new SizeRule('N', "","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"datetime","NULL","T"), new SizeRule('N', "","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"datetime","NULL","T"), new SizeRule('N', "","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"struct",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"varchar","NULL","T"), new SizeRule('P', "65535","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		hadoopHiveDataTypRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"varchar","NULL","T"), new SizeRule('P', "65535","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		
		return hadoopHiveDataTypRules;
	}

	public static List<DataTypeRule> getSalesForceDataTypRules()
	{
		if(salesForceDataTypeRules!=null)
            return salesForceDataTypeRules;

			salesForceDataTypeRules = new ArrayList<DataTypeRule>();
			
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"anyType","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"reference","NULL","B"), new SizeRule('N', "18","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"textarea","NULL","B"), new SizeRule('P', "255","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"anyType","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"reference","NULL","T"), new SizeRule('N', "18","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"textarea","NULL","B"), new SizeRule('P', "255","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"anyType","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"anyType","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"anyType","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"boolean","NULL","B"),new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"double","NULL","T"), new SizeRule('S', "","","3","0"), new OtherRule("0","255","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"double","NULL","T"), new SizeRule('S', "","","5","0"), new OtherRule("-32768","32767","Y","NULL","0","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"double","NULL","T"), new SizeRule('S', "","","5","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"int","NULL","B"), new SizeRule('S', "","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"int","NULL","T"), new SizeRule('S', "","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"double","NULL","B"), new SizeRule('S', "","","19","0"), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","8","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"double","NULL","T"), new SizeRule('S', "","","19","0"), new OtherRule("NULL","NULL","Y","NULL","13","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"currency","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"currency","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"double","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"double","NULL","B"), new SizeRule('S',"","","15","0"), new OtherRule("-1.79E + 308","1.79E + 308","Y","53","0","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"double","NULL","T"), new SizeRule('S',"","","15","0"), new OtherRule("-3.40E + 38","3.40E + 38","Y","NULL","4","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"double","NULL","T"), new SizeRule('S',"","","7","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"Date","NULL","B"), new SizeRule('N',"","","10","0"), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"Time","NULL","B"), new SizeRule('N',"","","8","0"), new OtherRule("00:00:00","23:59:59.999999","Y","NULL","8","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"Date/Time","NULL","B"), new SizeRule('N',"","","29","9"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 23:59:59.999999","Y","NULL","12","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"Date/Time","NULL","T"), new SizeRule('N',"","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 23:59:59.999999","Y","NULL","12","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"Date/Time","NULL","T"), new SizeRule('N',"","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 23:59:59.999999","Y","NULL","12","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"Date/Time","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"AnyType","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"AnyType","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"Auto Number",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"string",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"Combobox",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"Text Area",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('P', "255","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"Text Area (Rich)",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('P', "255","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"Text Area (Long)",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('P', "255","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"Text (Encrypted)",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('P', "255","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"URL",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('P', "255","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"Email",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"Id",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('S', "18","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"base64",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"Email",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"DateCategoryGroupReference",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"Lookup Relationship",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"Multi-Select Picklist",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"base64",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"percent",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"Checkbox",dsDataType.NUM_BIT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"Picklist",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
			salesForceDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"Phone",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));

			return salesForceDataTypeRules;
	}

	public static List<DataTypeRule> getImpalaDataTypRules() 
	{
		if(impalaDataTypeRules!=null)
			return impalaDataTypeRules;

		impalaDataTypeRules = new ArrayList<DataTypeRule>();
		
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('P',"255","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('P',"65535","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"VARCHAR","NULL","T"), new SizeRule('P',"65535","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"CHAR","NULL","T"), new SizeRule('P',"255","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"VARCHAR","NULL","T"), new SizeRule('P',"65535","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"VARCHAR","NULL","T"), new SizeRule('P',"65535","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"VARCHAR","NULL","B"), new SizeRule('P',"65535","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARCHAR","NULL","T"), new SizeRule('P',"65535","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"VARCHAR","NULL","B"), new SizeRule('P',"65535","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BOOLEAN","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"TINYINT","NULL","B"), new SizeRule('N', "3","","3","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('N', "5","","5","0"), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"SMALLINT","NULL","T"), new SizeRule('N', "5","","5","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INT","NULL","B"), new SizeRule('N', "10","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INT","NULL","T"), new SizeRule('N', "10","","10","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('N', "19","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"BIGINT","NULL","T"), new SizeRule('N', "19","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"DOUBLE","NULL","T"), new SizeRule('P', "17","","15","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"DOUBLE","NULL","T"), new SizeRule('P', "17","","15","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","B"), new SizeRule('S', "38","","",""),new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DOUBLE","NULL","B"), new SizeRule('P', "17","","15","0"), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('P', "9","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"REAL","NULL","B"), new SizeRule('N', "","","7","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"TIMESTAMP","NULL","T"), new SizeRule('N', "8","","19","0"), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "8","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('N', "8","","26","6"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP","NULL","T"), new SizeRule('N', "8","","15","0"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "8","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"TIMESTAMP","NULL","T"), new SizeRule('N', "8","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('P', "65535","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('P', "65535","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"STRING",dsDataType.CHAR_VARCHAR.toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"STRUCT",dsDataType.CHAR_VARCHAR.toString(),"S"), new SizeRule('S',"4000","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"MAP",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('S',"4000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		impalaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"ARRAY",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('S', "4000","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));

		return impalaDataTypeRules;
	}

	
	public static List<DataTypeRule> getPythonDataTypRules() 
	{
		if(pythonDataTypeRules!=null)
			return pythonDataTypeRules;

		pythonDataTypeRules = new ArrayList<DataTypeRule>();
		
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"str","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"str","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"str","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"str","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"str","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"str","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"str","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"str","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"str","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"int","NULL","T"), new SizeRule('P',"","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"int","NULL","T"), new SizeRule('P',"","","5","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"int","NULL","T"), new SizeRule('P',"","","5","0"), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"int","NULL","T"), new SizeRule('P', "","","5","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"int","NULL","B"), new SizeRule('P', "","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"int","NULL","T"), new SizeRule('P',"","","10","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"float","NULL","T"), new SizeRule('S',"","","10","0"), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"float","NULL","T"), new SizeRule('S',"","","19","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"float","NULL","T"), new SizeRule('S',"","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"float","NULL","T"), new SizeRule('S',"","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"float","NULL","T"), new SizeRule('S',"","","",""),new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"float","NULL","T"), new SizeRule('S',"","","15","0"), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"float","NULL","B"), new SizeRule('S',"","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"float","NULL","T"), new SizeRule('S',"","","7","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"datetime","NULL","T"), new SizeRule('N',"","","29","9"), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"datetime","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"datetime","NULL","B"), new SizeRule('N',"","","29","9"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"datetime","NULL","T"), new SizeRule('N',"","","29","9"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"datetime","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"datetime","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"str","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pythonDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"str","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		
		return pythonDataTypeRules;
	}
	/*public static List<DataTypeRule> getPWXDatatypeRules()
	{
		if(pwxDataTypeRules!=null)
			return pwxDataTypeRules;
	
		pwxDataTypeRules = new ArrayList<DataTypeRule>();
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"VARCHAR","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"CHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"VARCHAR","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"VARCHAR","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"VARCHAR","NULL","T"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARBIN","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"BIN","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"NUM8","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"NUM8","NULL","B"), new SizeRule('N', "","","3","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"NUM16","NULL","B"), new SizeRule('N', "","","5","0"), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"NUM8U","NULL","B"), new SizeRule('N', "","","5","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"NUM32","NULL","B"), new SizeRule('N', "","","10","0"), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"NUM16U","NULL","B"), new SizeRule('N', "","","10","0"), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"NUM64U","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"NUM64U","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"DOUBLE","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"DOUBLE","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"PACKED","NULL","B"), new SizeRule('N', "","","",""),new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DOUBLE","NULL","B"), new SizeRule('N', "","","15","0"), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"NUM64U","NULL","T"), new SizeRule('N', "","","7","0"), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","19","0"), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIME","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('N', "","","26","6"), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","15","0"), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"UZONED",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"ZONED",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pwxDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"UPACKED",dsDataType.NUM_DECIMAL.toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));	
		
		return pwxDataTypeRules;	
			
	}*/

	public static List<DataTypeRule> getPentahoDatatypeRules() {
		
		if(pentahoDataTypeRules!=null)
			return pentahoDataTypeRules;
		
		pentahoDataTypeRules = new ArrayList<DataTypeRule>();


		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"String","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"Binary","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"Binary","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"Binary","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"Binary","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"Integer","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"Integer","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"Integer","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"Integer","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"Integer","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"Integer","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"Integer","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"Number","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"Number","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"Number","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"BigNumber","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"Number","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"BigNumber","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"Date","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"Date","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"Timestamp","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"Timestamp","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"Timestamp","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"Timestamp","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pentahoDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"String","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		
		
		
		return pentahoDataTypeRules;
	}

	public static List<DataTypeRule> getPostgreDatatypeRules() {
		
		if(postgreDataTypeRules!=null)
			return postgreDataTypeRules;
		
		postgreDataTypeRules = new ArrayList<DataTypeRule>();


		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"TEXT","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"CHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"VARCHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"CHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"BOOLEAN","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"BOOLEAN","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"BOOLEAN","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BOOLEAN","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"SMALLINT","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"SMALLINT","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INTEGER","NULL","B"), new SizeRule('S', "","0","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INTEGER","NULL","T"), new SizeRule('S', "","0","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('S', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"BIGINT","NULL","T"), new SizeRule('S', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"REAL","NULL","T"), new SizeRule('N', "6","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"REAL","NULL","T"), new SizeRule('N', "6","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"FLOAT","NULL","T"), new SizeRule('N', "15","","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"FLOAT","NULL","B"), new SizeRule('N', "15","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","T"), new SizeRule('N', "15","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"REAL","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIME","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMPZ","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"DATE","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"DATE","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"CHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		postgreDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"CHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));

		
		return postgreDataTypeRules;
		
	}

	public static List<DataTypeRule> getMonetDbDatatypeRules() {
		
		if(monetdbDataTypeRules!=null)
			return monetdbDataTypeRules;
		
		monetdbDataTypeRules = new ArrayList<DataTypeRule>();

		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"CLOB","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"CHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"VARCHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"CLOB","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"BOOLEAN","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"BOOLEAN","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"BLOB","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BOOLEAN","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"TINYINT","NULL","B"), new SizeRule('S', "3","0","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('S', "5","0","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"SMALLINT","NULL","T"), new SizeRule('S', "5","0","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INTEGER","NULL","B"), new SizeRule('S', "10","0","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INTEGER","NULL","T"), new SizeRule('S', "10","0","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('S', "19","0","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"BIGINT","NULL","T"), new SizeRule('S', "19","0","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"REAL","NULL","T"), new SizeRule('P', "10","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"REAL","NULL","T"), new SizeRule('P', "10","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","B"), new SizeRule('S', "18","3","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DOUBLE","NULL","B"), new SizeRule('P', "19","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('P', "19","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"REAL","NULL","B"), new SizeRule('P', "10","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIME","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TIMESTAMP","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"DATE","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"DATE","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"CHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		monetdbDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"CHAR","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));

		
		return monetdbDataTypeRules;
		
	}

	public static List<DataTypeRule> getParquetDatatypeRules() {
		if(parquetDataTypeRules!=null)
			return parquetDataTypeRules;
		
		parquetDataTypeRules = new ArrayList<DataTypeRule>();

		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1","104857600","Y","NULL","1","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1","104857600","Y","NULL","1","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1","104857600","Y","NULL","1","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1","104857600","Y","NULL","2","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1","104857600","Y","NULL","2","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1","104857600","Y","NULL","1","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1","104857600","Y","NULL","1","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1","104857600","Y","NULL","1","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"BINARY","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1","104857600","Y","NULL","1","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BOOLEAN","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","2","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"INT32","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","2","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"INT64","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","0","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"INT64","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","4","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INT96","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","4","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INT96","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","4","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"INT96","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","8","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"INT96","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775808","9223372036854775807","Y","NULL","4","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"DOUBLE","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"DOUBLE","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"FLOAT","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DOUBLE","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('P', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"DOUBLE","NULL","T"), new SizeRule('P', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","NULL","4","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","NULL","10","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","NULL","10","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","NULL","10","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","NULL","10","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","NULL","10","Y")));
		parquetDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"BINARY","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","NULL","10","Y")));

		
		return parquetDataTypeRules;
	}

	public static List<DataTypeRule> getVerticaDatatypeRules() {


		if(verticaDataTypeRules!=null)
			return verticaDataTypeRules;
		
		verticaDataTypeRules = new ArrayList<DataTypeRule>();

		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('P', "65000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('P', "65000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"BINARY","NULL","T"), new SizeRule('P', "1","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"CHAR","NULL","T"), new SizeRule('P', "65000","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"VARCHAR","NULL","T"), new SizeRule('P', "65000","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"BINARY","NULL","T"), new SizeRule('P', "1","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"CHAR","NULL","T"), new SizeRule('P', "65000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"VARCHAR","NULL","T"), new SizeRule('P', "65000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"BINARY","NULL","B"), new SizeRule('P', "1","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BOOLEAN","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"TINYINT","NULL","B"), new SizeRule('P', "19","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","B"), new SizeRule('P', "19","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"SMALLINT","NULL","T"), new SizeRule('P', "19","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INT","NULL","B"), new SizeRule('P', "19","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"INT","NULL","T"), new SizeRule('P', "19","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('P', "19","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"BIGINT","NULL","T"), new SizeRule('P', "19","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"MONEY","NULL","T"), new SizeRule('S', "18","4","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"MONEY","NULL","B"), new SizeRule('S', "18","4","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","B"), new SizeRule('S', "37","15","",""), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"NUMERIC","NULL","B"), new SizeRule('S', "37","15","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"REAL","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIME","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"DATETIME","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP WITH TIMEZONE","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"SMALLDATETIME","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"SMALLDATETIME","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR","NULL","T"), new SizeRule('P', "65000","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR","NULL","T"), new SizeRule('P', "65000","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"VARCHAR",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('P', "65000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"LONG VARCHAR",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('P', "65000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"GEOMETRY",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('P', "65000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"GEOGRAPHY",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('P', "65000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"UUID",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('P', "65000","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"VARBINARY",dsDataType.BIN_VARCHAR.toString(),"S"), new SizeRule('P', "65000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"LONG VARBINARY",dsDataType.BIN_VARCHAR.toString(),"S"), new SizeRule('P', "65000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"BYTEA",dsDataType.BIN_VARCHAR.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"RAW",dsDataType.BIN_VARCHAR.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"DOUBLE PRECISION",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"INT8",dsDataType.NUM_INT.toString(),"S"), new SizeRule('P', "19","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT8",dsDataType.CHAR_CLOB.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"FLOAT(n)",dsDataType.NUM_FLOAT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"NUMBER",dsDataType.NUM_INT.toString(),"S"), new SizeRule('P', "19","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"INTEGER",dsDataType.NUM_INT.toString(),"S"), new SizeRule('P', "19","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"TIME WITH TIMEZONE",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"INTERVAL",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"TIMESTAMP",dsDataType.DATE_DATETIME.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"INTERVAL DAY TO SECOND",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
		verticaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"INTERVAL YEAR TO MONTH",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","7","Y")));
		
		return verticaDataTypeRules;
	}
	
	public static List<DataTypeRule> getPySparkDataTypeRules()
	{
		if(pySparkDataTypeRules!=null)
			return pySparkDataTypeRules;

		pySparkDataTypeRules = new ArrayList<DataTypeRule>();

//		DataTypeRule(maxPrecision, minScale, maxScale, maxDataLength,  - minValue, maxValue, defaultSupport, defaultValue, sizeInBytes, defaultSize, notNullSupport);
//		new OtherRule(minValue, maxValue, defaultSupport, defaultValue, sizeInBytes, notNullSupport)
//		after 4th Param replace "," with "), new OtherRule("
// 		Remove Penultimate Param at the end
//		Add a ")" before ";"
// 		Add "Y/N" string as 1st param for SizeRule 
		
		
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"StringType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"StringType()","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"StringType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"StringType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"StringType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"StringType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"BinaryType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"BinaryType()","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"BinaryType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"ByteType()","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"ShortType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"ShortType()","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-32768","32767","Y","NULL","0","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"ShortType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"IntegerType()","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-2147483648","2147483647","Y","NULL","4","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"IntegerType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"LongType()","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-9223372036854775807","9223372036854775808","Y","NULL","8","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"LongType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"LongType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"LongType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","Y","NULL","4","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DecimalType()","NULL","B"), new SizeRule('S', "38","18","38","38"), new OtherRule("-1031 - 1","1031 - 1","Y","NULL","4","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DoubleType()","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("- 10-383","10+384","Y","NULL","4","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FloatType()","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"LongType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("-7.2E+75","7.2E+75","Y","NULL","4","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DateType()","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001","31/12/9999","Y","currrent date","4","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TimestampType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("00:00:00","24:00:00","Y","NULL","4","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"TimestampType()","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TimestampType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TimestampType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"TimestampType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"StringType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		pySparkDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"StringType()","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		
		return pySparkDataTypeRules;
	}
	
	public static List<DataTypeRule> getXMLSchemaRules()
	{
		if(xmlSchemaDataTypeRules!=null)
			return xmlSchemaDataTypeRules;

		xmlSchemaDataTypeRules = new ArrayList<DataTypeRule>();
		
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"xs:string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"xs:string","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"xs:string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"xs:string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"xs:string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"xs:string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"xs:hexBinary","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"xs:hexBinary","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"xs:hexBinary","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"xs:int","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"xs:byte","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"xs:short","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"xs:unsignedShort","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"xs:integer","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"xs:unsignedInt","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"xs:long","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"xs:unsignedLong","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"xs:double","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"xs:double","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"xs:decimal","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"xs:double","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"xs:float","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"xs:double","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"xs:date","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"xs:time","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"xs:datetime","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"xs:string","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"xs:datetime","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"xs:datetime","NULL","T"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"xs:string","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"xs:string","NULL","B"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"xs:anyURI",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"xs:QName",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"xs:NOTATION",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"xs:normalizedString",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"xs:token",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"xs:language",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"xs:NMToken",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"xs:NMTokens",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"xs:Name",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"xs:NCName",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"xs:ID",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"xs:IDREF",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"xs:IDREFS",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"xs:ENTITY",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_DEPRECATED,"xs:ENTITIES",dsDataType.CHAR_VARCHAR.toString().toString(),"S"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_DEPRECATED,"xs:base64Binary",dsDataType.BIN_VARCHAR.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"xs:nonPositiveInteger",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"xs:int",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"xs:nonNegativeInteger",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"xs:positiveInteger",dsDataType.NUM_INT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DEPRECATED,"xs:unsignedByte",dsDataType.NUM_TINYINT.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"xs:duration",dsDataType.DATE_DATETIME.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"xs:gYearMonth",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"xs:gYear",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"xs:gMonthDay",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"xs:gMonth",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		xmlSchemaDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"xs:gDay",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('N', "","","",""), new OtherRule("NULL","NULL","","NULL","","Y")));
		
		return xmlSchemaDataTypeRules;
	}
	
	public static List<DataTypeRule> getmySQLRules()
	{
		if(mySQLDataTypeRules!=null)
			return mySQLDataTypeRules;

		mySQLDataTypeRules = new ArrayList<DataTypeRule>();

		//String isSizeApplicable, String maxLengthPrecision, String maxScale, String defaultLengthPrecision, String defaultScale
		
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CHAR,"CHAR","NULL","B"), new SizeRule('P',"2000","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_VARCHAR,"VARCHAR","NULL","B"), new SizeRule('P',"32767","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.CHAR_CLOB,"CLOB","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CHAR,"NCHAR","NULL","B"), new SizeRule('P',"2000","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_VARCHAR,"NVARCHAR2","NULL","B"), new SizeRule('P',"32767","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.UNICODE_CLOB,"NCLOB","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","2","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_CHAR,"RAW","NULL","B"), new SizeRule('P',"32767","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_VARCHAR,"LONG RAW","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.BIN_BLOB,"BLOB","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIT,"BIT","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("-32767","32767","Y","NULL","2","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_TINYINT,"TINYINT","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLINT,"SMALLINT","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_USMALLINT,"","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_INT,"INT","NULL","B"), new SizeRule('S',"38","","15","0"), new OtherRule("-2147483648","2147483647","Y","NULL","1","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UINT,"NUMBER","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_BIGINT,"BIGINT","NULL","B"), new SizeRule('N',"","","",""), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_UBIGINT,"NUMBER","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_SMALLMONEY,"NUMBER","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_MONEY,"NUMBER","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DECIMAL,"DECIMAL","NULL","T"), new SizeRule('S',"38","","15","0"), new OtherRule("NULL","NULL","Y","NULL","1","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_DOUBLE,"DOUBLE","NULL","B"), new SizeRule('S',"38","","15","0"), new OtherRule("2.22507485850720E-308","2.22507485850720E-308","Y","NULL","9","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_FLOAT,"FLOAT","NULL","B"), new SizeRule('P', "126","","","0"), new OtherRule("1.17549E-38F","3.40282E+38F","Y","NULL","5","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.NUM_REAL,"FLOAT","NULL","T"), new SizeRule('P', "126","","",""), new OtherRule("1.17549E-38F","3.40282E+38F","Y","NULL","5","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATE,"DATE","NULL","B"), new SizeRule('N', "","","19","0"), new OtherRule("01 januarY  4712 BC","31 december 9999 AD","Y","NULL","7","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIME,"TIME","NULL","T"), new SizeRule('P', "9","","26","6","6",""), new OtherRule("NULL","NULL","Y","NULL","0","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DATETIME,"DATETIME","NULL","B"), new SizeRule('P', "9","","26","6","6",""), new OtherRule("01/01/0001, 00.00.00.000000000","31/12/9999, 23.59.61.99999","Y","NULL","10","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_TIMEZONE,"TIMESTAMP WITH TIMEZONE","NULL","B"), new SizeRule('P', "9","","26","6","6",""), new OtherRule("1 JanuarY 0001 00.00.00.000000000 -12.59","31 December 9999, 23.59.61.99999   +14.00","Y","NULL","12","N")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_SMALLDATETIME,"TIMESTAMP","NULL","T"), new SizeRule('P', "9","","26","6","6",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 23.59.61.99999","Y","NULL","10","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_INTERVAL,"TIMESTAMP","NULL","T"), new SizeRule('P', "9","","26","6","6",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
	    mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.DATE_DEPRECATED,"TIMESTAMP WITH LOCAL TIMEZONE",dsDataType.DATE_TIMEZONE.toString(),"S"), new SizeRule('P', "9","","26","6"), new OtherRule("00:00:00.000000  -12.59","23:59:61.999999  +14.00","Y","NULL","8","Y")));
	    mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_VAR,"VARCHAR2","NULL","T"), new SizeRule('P',"32767","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		mySQLDataTypeRules.add(new DataTypeRule(new TypeRule(dsDataType.TYPE_UNKNOWN,"VARCHAR2","NULL","T"), new SizeRule('P',"32767","","",""), new OtherRule("1 JanuarY 0001, 00.00.00.000000000","31 December 9999, 24.00.00.000000000","Y","NULL","10","Y")));
		
		return mySQLDataTypeRules;
	}
}


	