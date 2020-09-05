
package com.dataSwitch.ds.functionTypeRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.functionTypeRules.FnMapRules.dsFuunctionType;



public class FnConverter 
{
	public FnConverter() {
		// TODO Auto-generated constructor stub
	}	
    public final static List<FnRule> getOutputFnMapRule (int tooltype)
	{

		List<FnRule> fnMapRules = new ArrayList<FnRule>();
		switch(tooltype)
		{
			case ToolTypeConstants.DS_TOOL:
				fnMapRules = FnMapRules.getDSToolRules();
				break;
				
			case ToolTypeConstants.ORACLE:
				fnMapRules = FnMapRules.getOracleRules();
				break;
				
			case ToolTypeConstants.DB2:
				fnMapRules = FnMapRules.getDB2Rules();
				break;	
	
			case ToolTypeConstants.TERADATA:
				fnMapRules = FnMapRules.getTeradataRules();  
				break;	
	
			case ToolTypeConstants.NETEZZA:
				fnMapRules = FnMapRules.getNetezzaRules();  
				break;
	
			case ToolTypeConstants.SQLSERVER:
				fnMapRules = FnMapRules.getSqlServerRules();  
				break;	
	
			case ToolTypeConstants.SYBASE:
				fnMapRules = FnMapRules. getSybaseRules();  
				break;	
			
			case ToolTypeConstants.REDSHIFT:
				fnMapRules = FnMapRules.getRedshiftRules();
				break;	
	
			case ToolTypeConstants.BIGQUERY:
				fnMapRules = FnMapRules.getBigqueryRules();  
				break;	
	
			case ToolTypeConstants.AZURESQL:
				fnMapRules = FnMapRules.getAzuresqlRules();  
				break;
	
			case ToolTypeConstants.SNOWFLAKE:
				fnMapRules = FnMapRules. getSnowflakeRules();  
				break;	
	
			case ToolTypeConstants.AURORA:
				fnMapRules = FnMapRules. getAuroraRules();  
				break;	
				
			case ToolTypeConstants.DATASTAGE:
				fnMapRules = FnMapRules. getDatastageRules();  
				break;
				
			case ToolTypeConstants.SSIS:
				fnMapRules = FnMapRules. getSsisRules(); 
				break;
			
			case ToolTypeConstants.INFORMATICA_BDM:
				fnMapRules = FnMapRules.getInformaticaBDMRules();
				break;
			
			case ToolTypeConstants.INFORMATICA_PC:
				fnMapRules = FnMapRules.getInformaticaPCRules();
				break;
				
			case ToolTypeConstants.APACHE_BEAM:
				fnMapRules = FnMapRules.getApacheBeamRules();
				break;
		
			case ToolTypeConstants.JAVA:
				fnMapRules = FnMapRules.getJavaRules();
				break;
				
			case ToolTypeConstants.ODBC:
				fnMapRules = FnMapRules.getOdbcRules();
				break;
				
			case ToolTypeConstants.PYSPARK:
                fnMapRules = FnMapRules.getPySparkRules();
                break;
                
			case ToolTypeConstants.TALEND:
                fnMapRules = FnMapRules.getTalendRules();
                break;
                
			case ToolTypeConstants.AZUREDATAFACTORY:
                fnMapRules = FnMapRules.getAzureDataFactoryRules();
                break;
                
			case ToolTypeConstants.ADFDATAFLOW:
                fnMapRules = FnMapRules.getAzureDataFactoryDataFlowRules();
                break;
                
			case ToolTypeConstants.MONGODB:
                fnMapRules = FnMapRules.getMongoRules();
                break; 
                
			case ToolTypeConstants.POSTGRESQL:
                fnMapRules = FnMapRules.getPostgreSQL();
                break;
            
			case ToolTypeConstants.GREENPLUM:
                fnMapRules = FnMapRules.getGreenPlum();
                break; 
                
			case ToolTypeConstants.IMPALA:
                fnMapRules = FnMapRules.getImpalaRules();
                break; 
                
			case ToolTypeConstants.VERTICA:
                fnMapRules = FnMapRules.getVerticaRules();
                break;
                
			case ToolTypeConstants.APACHE_BEAM_PY:
                fnMapRules = FnMapRules.getApacheBeamPythonRules();
                break; 
                
			case ToolTypeConstants.SCALA_SPARK:
                fnMapRules = FnMapRules.getSparkScalaRules();
                break;           
                
			case ToolTypeConstants.HADOOPHIVE:
                fnMapRules = FnMapRules.getHiveRules();
                break;
                
			case ToolTypeConstants.PYTHON:
				fnMapRules = FnMapRules.getPythonRules();
			
			case ToolTypeConstants.PANDASSQL:
				fnMapRules = FnMapRules.pandasSqlRules();

		}
		
		return fnMapRules;
	}


	public static Map<String,FnRule> getSourceFnTypeRule(int toolType)
	{

		Map<String,FnRule> getSrcDtRule = new HashMap<String,FnRule>();
		for(FnRule dtr : getOutputFnMapRule(toolType)){
			if(dtr.getApplicability()== "S" || dtr.getApplicability()=="B"){
				String key = dtr.getToolFnType();
				getSrcDtRule.put(key, dtr);
			}
		}
		
		return getSrcDtRule;
	}

	public static Map<String,FnRule> getTargetFnTypeRule(int toolType){
		Map<String,FnRule> getTgtDtRule = new HashMap<String,FnRule>();
		for(FnRule dtr : getOutputFnMapRule(toolType)){
			if(dtr.getApplicability()== "T" || dtr.getApplicability()=="B"){
				String key = dtr.getGenFnType().toString();
				getTgtDtRule.put(key, dtr);
			}
		}
		return getTgtDtRule;
	}

	public static HashMap<String, String> getSourceTargetTypeMaps (int srcToolType, int tgttooltype)
	{
		
		//HashMap<String, String> srcTgtTypesMap = new HashMap<>();
		HashMap<String, String> srcTgtTypesMap = new HashMapCaseInsensitive();
		Map<String,FnRule> srcRuleMap = FnConverter.getSourceFnTypeRule(srcToolType);
		Map<String,FnRule> tgtRuleMap = FnConverter.getTargetFnTypeRule(tgttooltype);

		dsFuunctionType tgtGenDataType;
		String tgtDataType = "";
		FnRule srcRule, tgtRule;

		String srcDataType;
		dsFuunctionType srcGenDataType;
		dsFuunctionType srcRecTgtType;

		for (Map.Entry<String, FnRule> srcEntry : srcRuleMap.entrySet()) 
		{
			srcRule = srcEntry.getValue();
			srcDataType = srcEntry.getKey();
			tgtDataType = ""; 
			srcRecTgtType = srcRule.getRecommendedType();
			
			srcGenDataType = srcRule.getGenFnType();
			
			if ((srcRecTgtType.toString() != "NULL") && (srcRecTgtType.toString().trim().length() > 0)) 
				tgtGenDataType = srcRecTgtType;
			else
				tgtGenDataType = srcGenDataType;

			if ((tgtGenDataType.toString() != "NULL") && (tgtGenDataType.toString().trim().length() > 0))
			{
				for (Map.Entry<String, FnRule> tgtEntry : tgtRuleMap.entrySet()) 
				{
					tgtRule = tgtEntry.getValue();
					if(tgtRule.getGenFnType() == tgtGenDataType)
					{ 
						tgtDataType = tgtRule.getToolFnType();
						break;
					}	
				}
			}
			//System.out.println( " srcGenDataType:::: " + srcEntry.getValue().getDbDataType()+ " tgtDataType:::: " + tgtDataType);
			
			if ((tgtDataType == null) || (tgtDataType.trim().length() <= 0))
			{
				tgtDataType ="NA";
				//System.out.println("ERROR: SrcToolId: " + srcToolType + " SrcDataType:" + srcDataType + "TgtToolId:" + tgttooltype + "TgtDataType: ***NA***" );
			}

			srcTgtTypesMap.put(srcDataType, tgtDataType);
		}
		
		return srcTgtTypesMap;
	}
	
	public static String getTargetFnType (int srctoolType, int tgttooltype, String srcFntype)
	{
		HashMap<String, String> srcTgtTypesMap = new HashMapCaseInsensitive();
		srcTgtTypesMap = getSourceTargetTypeMaps (srctoolType, tgttooltype);
		String tgtFntype = srcTgtTypesMap.get(srcFntype);
		return tgtFntype;
	}


}
