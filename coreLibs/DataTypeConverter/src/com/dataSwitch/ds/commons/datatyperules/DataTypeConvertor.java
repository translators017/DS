
package com.dataSwitch.ds.commons.datatyperules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.dataSwitch.ds.commons.datatyperules.DataTypeRule;


public class DataTypeConvertor 
{
	public final static List<DataTypeRule> getOutputToolDataTypeMapRule (int tooltype)
	{

		List<DataTypeRule> dataTypeMapRules = new ArrayList<DataTypeRule>();
		switch(tooltype)
		{
		
			case ToolTypeConstants.DS_TOOL:
				dataTypeMapRules = DataTypeMapRules.getDSToolRules();
				break;
				
			case ToolTypeConstants.ORACLE:
				dataTypeMapRules = DataTypeMapRules.getOracleRules();
				break;
				
			case ToolTypeConstants.DB2:
				dataTypeMapRules = DataTypeMapRules.getDB2Rules();
				break;	
				
			case ToolTypeConstants.TERADATA:
				dataTypeMapRules = DataTypeMapRules.getTeradataRules();  
				break;	
				
			case ToolTypeConstants.NETEZZA:
				dataTypeMapRules = DataTypeMapRules.getNetezzaRules();  
				break;
				
			case ToolTypeConstants.SQLSERVER:
				dataTypeMapRules = DataTypeMapRules.getSqlServerRules();  
				break;	
				
			case ToolTypeConstants.SYBASE:
				dataTypeMapRules = DataTypeMapRules. getSybaseRules();  
				break;	
				
			case ToolTypeConstants.REDSHIFT:
				dataTypeMapRules = DataTypeMapRules.getRedshiftRules();
				break;	
				
			case ToolTypeConstants.BIGQUERY:
				dataTypeMapRules = DataTypeMapRules.getBigqueryRules();  
				break;	
				
			case ToolTypeConstants.AZURESQL:
				dataTypeMapRules = DataTypeMapRules.getAzuresqlRules();  
				break;
				
			case ToolTypeConstants.SNOWFLAKE:
				dataTypeMapRules = DataTypeMapRules. getSnowflakeRules();  
				break;	
				
			case ToolTypeConstants.AURORA:
				dataTypeMapRules = DataTypeMapRules. getAuroraRules();  
				break;	
				
			case ToolTypeConstants.DATASTAGE:
				dataTypeMapRules = DataTypeMapRules. getDatastageRules();  
				break;
				
			case ToolTypeConstants.SSIS:
				dataTypeMapRules = DataTypeMapRules. getSsisRules(); 
				break;
				
			case ToolTypeConstants.INFORMATICA_BDM:
				dataTypeMapRules = DataTypeMapRules.getInformaticaBDMRules();
				break;
				
			case ToolTypeConstants.INFORMATICA_PC:
				dataTypeMapRules = DataTypeMapRules.getInformaticaPCRules();
				break;
				
			case ToolTypeConstants.APACHE_BEAM:
				dataTypeMapRules = DataTypeMapRules.getApacheBeamRules();
				break;
				
			case ToolTypeConstants.JAVA:
				dataTypeMapRules = DataTypeMapRules.getJavaRules();
				break;
				
			case ToolTypeConstants.ODBC:
				dataTypeMapRules = DataTypeMapRules.getOdbcRules();
				break;		
				
			case ToolTypeConstants.PYSPARK:
                dataTypeMapRules = DataTypeMapRules.getPySparkDataTypeRules();
                break;	
                
			case ToolTypeConstants.TALEND:
                dataTypeMapRules = DataTypeMapRules.getTalendRules();
                break;    
                
			case ToolTypeConstants.AZUREDATAFACTORY:
                dataTypeMapRules = DataTypeMapRules.getAzureDataFactoryRules();
                break;
                
			case ToolTypeConstants.ADFDATAFLOW:
                dataTypeMapRules = DataTypeMapRules.getAzureDataFactoryDataFlowRules();
                break;
                
			case ToolTypeConstants.MONGODB:
                dataTypeMapRules = DataTypeMapRules.getMongoRules();
                break;       
                
            case ToolTypeConstants.POSTGRESQL:
                dataTypeMapRules = DataTypeMapRules.getPostgreDatatypeRules();
                break; 
                
            case ToolTypeConstants.GREENPLUM:
                dataTypeMapRules = DataTypeMapRules.getGreenPlum();
                break;  
                
            case ToolTypeConstants.IMPALA:
                dataTypeMapRules = DataTypeMapRules.getImpalaDataTypRules();
                break;          
                
            case ToolTypeConstants.PENTAHO:
                dataTypeMapRules = DataTypeMapRules.getPentahoDatatypeRules();
                break;      
                
            case ToolTypeConstants.VERTICA:
                dataTypeMapRules = DataTypeMapRules.getVerticaDatatypeRules();
                break;  
                
			case ToolTypeConstants.AMAZONS3:
                dataTypeMapRules = DataTypeMapRules.getAmazonS3();
                break;
                
			case ToolTypeConstants.SQLDW:
                dataTypeMapRules = DataTypeMapRules.getSQLDw();
                break;
                
            case ToolTypeConstants.SNOWFLAKECLOUD:
                dataTypeMapRules = DataTypeMapRules.getSnowflakeCloud();
                break;
                
            case ToolTypeConstants.AVRO:
                dataTypeMapRules = DataTypeMapRules.getAvro();
                break;
                
            case ToolTypeConstants.HADOOPHDFS:
                dataTypeMapRules = DataTypeMapRules.getHadoopHDFSDataTypRules();
                break;
                
            case ToolTypeConstants.HADOOPHIVE:
                dataTypeMapRules = DataTypeMapRules.getHadoopHIVEDataTypRules();
                break;
                
            case ToolTypeConstants.SALESFORCE:
                dataTypeMapRules = DataTypeMapRules.getSalesForceDataTypRules();
                break;
                
                
            case ToolTypeConstants.MONETDB:
                dataTypeMapRules = DataTypeMapRules.getMonetDbDatatypeRules();
                break; 
                
            case ToolTypeConstants.PARQUET:
                dataTypeMapRules = DataTypeMapRules.getParquetDatatypeRules();
                break;
                
			case ToolTypeConstants.FLATFILE:
				dataTypeMapRules = DataTypeMapRules.getFlatFileRules();
				break;	
				
			case ToolTypeConstants.PYTHON:
				dataTypeMapRules = DataTypeMapRules.getPythonDataTypRules();
				break;
				
			case ToolTypeConstants.XMLSCHEMA:
				dataTypeMapRules = DataTypeMapRules.getXMLSchemaRules();
	            break; 
	            
			case ToolTypeConstants.MYSQL:
				dataTypeMapRules = DataTypeMapRules.getmySQLRules();
	            break;

		}
		return dataTypeMapRules;
	}


	public static DataTypeRule getSourceDataTypeRule(int toolType, String szDataType)
	{

		for (DataTypeRule dtr : getOutputToolDataTypeMapRule(toolType))
		{
			if (dtr.getApplicability()== "S" || dtr.getApplicability()=="B")
			{
				if (dtr.getDbDataType().equalsIgnoreCase(szDataType))
					return  dtr;
			}
		}
		
		return null;
	}

	public static DataTypeRule getTargetDataTypeRule(int toolType, String szGenDataType)
	{

		for (DataTypeRule dtr : getOutputToolDataTypeMapRule(toolType))
		{
			if (dtr.getApplicability() == "T" || dtr.getApplicability()=="B")
			{
				if (dtr.getGenDataType().toString().equalsIgnoreCase(szGenDataType))
					return  dtr;
			}
		}
		
		return null;
	}

	/*public static Map<String,DataTypeRule> getSourceDataTypeRules(int toolType)
	{

		Map<String,DataTypeRule> getSrcDtRule = new HashMap<String,DataTypeRule>();
		for(DataTypeRule dtr : getOutputToolDataTypeMapRule(toolType)){
			if(dtr.getApplicability()== "S" || dtr.getApplicability()=="B"){
				String key = dtr.getDbDataType();
				getSrcDtRule.put(key, dtr);
			}
		}
		
		return getSrcDtRule;
	}

	public static Map<String,DataTypeRule> getTargetDataTypeRules(int toolType){
		Map<String,DataTypeRule> getTgtDtRule = new HashMap<String,DataTypeRule>();
		for(DataTypeRule dtr : getOutputToolDataTypeMapRule(toolType)){
			if(dtr.getApplicability()== "T" || dtr.getApplicability()=="B"){
				String key = dtr.getGenDataType();
				getTgtDtRule.put(key, dtr);
			}
		}
		return getTgtDtRule;
	}*/
	public static Map<String,DataTypeRule> getSourceDataTypeRule(int toolType)
	{

		Map<String,DataTypeRule> getSrcDtRule = new HashMap<String,DataTypeRule>();
		for(DataTypeRule dtr : getOutputToolDataTypeMapRule(toolType)){
			if(dtr.getApplicability()== "S" || dtr.getApplicability()=="B"){
				String key = dtr.getDbDataType();
				getSrcDtRule.put(key, dtr);
			}
		}
		
		return getSrcDtRule;
	}

	public static Map<String,DataTypeRule> getTargetDataTypeRule(int toolType){
		Map<String,DataTypeRule> getTgtDtRule = new HashMap<String,DataTypeRule>();
		for(DataTypeRule dtr : getOutputToolDataTypeMapRule(toolType)){
			if(dtr.getApplicability()== "T" || dtr.getApplicability()=="B"){
				String key = dtr.getGenDataType().toString();
				getTgtDtRule.put(key, dtr);
			}
		}
		return getTgtDtRule;
	}
	public static HashMap<String, String> getSourceTargetTypeMaps (int srcToolType, int tgttooltype)
	{
		
		//HashMap<String, String> srcTgtTypesMap = new HashMap<>();
		HashMap<String, String> srcTgtTypesMap = new HashMapCaseInsensitive();
		Map<String,DataTypeRule> srcRuleMap = DataTypeConvertor.getSourceDataTypeRule(srcToolType);
		Map<String,DataTypeRule> tgtRuleMap = DataTypeConvertor.getTargetDataTypeRule(tgttooltype);

		String tgtGenDataType;
		String tgtDataType = "";
		DataTypeRule srcRule, tgtRule;

		String srcDataType, srcRecTgtType, srcGenDataType;

		for (Map.Entry<String, DataTypeRule> srcEntry : srcRuleMap.entrySet()) 
		{
			srcRule = srcEntry.getValue();
			srcDataType = srcEntry.getKey();

			tgtDataType = ""; 
			srcRecTgtType = srcRule.getRecommendedType().toString();
			
			srcGenDataType = srcRule.getGenDataType().toString();
			
			if ((srcRecTgtType != "NULL") && (srcRecTgtType.trim().length() > 0)) 
				tgtGenDataType = srcRecTgtType;
			else
				tgtGenDataType = srcGenDataType;

			if ((tgtGenDataType != "NULL") && (tgtGenDataType.trim().length() > 0))
			{
				for (Map.Entry<String, DataTypeRule> tgtEntry : tgtRuleMap.entrySet()) 
				{
					tgtRule = tgtEntry.getValue();
					if(tgtRule.getGenDataType().toString() == tgtGenDataType)
					{ 
						tgtDataType = tgtRule.getDbDataType();
						break;
					}	
				}
			}
			//System.out.println( " srcGenDataType:::: " + srcEntry.getValue().getDbDataType()+ " tgtDataType:::: " + tgtDataType);
			
			if ((tgtDataType == null) || (tgtDataType.trim().length() <= 0))
			{
				tgtDataType ="NA";
				System.out.println("ERROR: SrcToolId: " + srcToolType + " SrcDataType:" + srcDataType + "TgtToolId:" + tgttooltype + "TgtDataType: ***NA***" );
			}

			srcTgtTypesMap.put(srcDataType, tgtDataType);
		}
		
		return srcTgtTypesMap;
	}
	
	public static String getTargetDataType (String srcDtype, int srctoolType, int tgttooltype)
	{
		HashMap<String, String> srcTgtTypesMap = new HashMapCaseInsensitive();
		 srcTgtTypesMap = getSourceTargetTypeMaps (srctoolType, tgttooltype);
		
		
		String tgtDtype = srcTgtTypesMap.get(srcDtype);

		return tgtDtype;
	}
	
	public static DataTypeObject getTargetDataType (int srcToolType, int tgtToolType, String srcDataType, int lengthOrPrecision, int scale)
	{
		//System.out.println( " original datatype prec sclae " + srcDataType + " :" +lengthOrPrecision + " : " +scale );
		DataTypeRule srcRule = getSourceDataTypeRule(srcToolType, srcDataType);
		if (srcRule == null)
			return null;

		String recTgtType = srcRule.getRecommendedType().toString();
		String genDataType = srcRule.getGenDataType().toString();
		if ((recTgtType != "NULL") && (recTgtType.trim().length() > 0)) 
			genDataType = recTgtType;

		if ((genDataType == "NULL") || (genDataType.trim().length() <= 0))
			return null;
		if (genDataType.equalsIgnoreCase("NUM_DECIMAL"))
		{
			if (scale == 0)
				genDataType = getGenericTypeForPrecision(lengthOrPrecision,scale);
		}
			

		DataTypeRule tgtRule = getTargetDataTypeRule(tgtToolType, genDataType);
		if (tgtRule == null)
			return null;

		DataTypeObject tgtDataTypeObj = new DataTypeObject();

		tgtDataTypeObj.dataType = tgtRule.getDbDataType();
		if (tgtRule.getPrecisionScaleIndicator() == 'Y')
		{
			tgtDataTypeObj.precision = tgtRule.getDefaultLengthPrecision();
			//tgtDataTypeObj.scale = Integer.toString(scale);
			tgtDataTypeObj.scale = tgtRule.getDefaultScale();
		}
		
		PrecisonScaleMapRules.getPrecisionScale(tgtToolType, tgtDataTypeObj, tgtRule);

		return tgtDataTypeObj;
	}


	private static String getGenericTypeForPrecision(int lengthOrPrecision, int scale) {
		
		if (lengthOrPrecision <= 1)
			return "NUM_BIT"; 

		else if (lengthOrPrecision <= 4)
			return "NUM_SMALLINT"; 
		
		else if (lengthOrPrecision <= 18)
			return "NUM_BIGINT"; 
		
		else if (lengthOrPrecision >= 19)
			return "NUM_BIGINT"; 
				
	return "NUM_DECIMAL";
	}
	
		public List<String> getAllDataType(int toolType) {
				String DataType = null;
				List<String> dataTypes = new ArrayList();
				for (DataTypeRule dtr : getOutputToolDataTypeMapRule(toolType)) {
					if (dtr.getDbDataType().contains("("))
						DataType = dtr.getDbDataType().substring(0, dtr.getDbDataType().indexOf('('));
					else
						DataType = dtr.getDbDataType();
					if (!dataTypes.contains(DataType))
						dataTypes.add(DataType);
				}
				return dataTypes;
			}

}
