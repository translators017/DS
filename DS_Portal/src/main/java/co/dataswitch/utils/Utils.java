package co.dataswitch.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;

import co.dataswitch.config.bean.Configurations;
import co.dataswitch.config.bean.Configurations.Configuration;
import co.dataswitch.config.bean.Configurations.Configuration.Properties.Property;
import co.dataswitch.nosqldb.DBEngine_new;
import co.dataswitch.nosqldbDTO.Catalogs;
import co.dataswitch.nosqldbDTO.EntityDesignDTO;
import co.dataswitch.nosqldbDTO.Mapping;
import co.dataswitch.nosqldbDTO.ProcessGroup;
import co.dataswitch.nosqldbDTO.SchemaMigrator;


@Controller
public class Utils {
	
	private static final Logger dslogger = Logger.getLogger(Utils.class);

	public static String getNoSQLDBPath(HttpServletRequest request){		
		String nosqlpath = "";
		Configurations ds = UnmarshallerController.unmarshallDataSwitchConfigXML(request);
		if(ds != null)
		{
			for (int i = 0; i < ds.getConfiguration().size(); i++) {
				if(ds.getConfiguration().get(i).getName().equalsIgnoreCase("Common")){
					for(Property prop : ds.getConfiguration().get(i).getProperties().getProperty()){
						if(prop.getName().equalsIgnoreCase("noSqlPath")){
							nosqlpath = prop.getValue();
						}
					}
				}
			}
		}

		return nosqlpath;
	}
	
	public static Catalogs getCatalogObject(HttpServletRequest request,String catalogName){
		String dataStoreDir = getNoSQLDBPath(request);
		String username = (String) request.getSession().getAttribute("username");
		Catalogs catalog = null;
		try {
			catalog = (Catalogs) DBEngine_new.getData("Catalogs", catalogName, dataStoreDir+"/"+username);
		} catch (Exception e) {
			dslogger.error("Unable to get catalog object for catalog - "+catalogName);
		}
		return catalog;
	}
	
	public static Mapping getMappingObject(HttpServletRequest request,String mappingName,String selectedSystem,String dataStoreDir,String username){
		Mapping mapping = null;
		try {
			mapping = (Mapping) DBEngine_new.getData("mapping", mappingName, dataStoreDir+"/"+username+"/VisualMapper/"+selectedSystem+"/");
		} catch (Exception e) {
			dslogger.error("Unable to get mapping group object - "+mappingName);
		}
		return mapping;
	}
	
	public static ProcessGroup getProcessGroupObj(String groupName, String dataStoreDir,String username) {
		ProcessGroup group = new ProcessGroup();
		
		try {
			group = (ProcessGroup) DBEngine_new.getData("ProcessGroups", groupName, dataStoreDir+ File.separator +username);
		} catch (Exception e) {
			dslogger.error("Unable to get process group object - "+groupName);
		}
		return group;
	}
	
	public static SchemaMigrator getSchemaDesignObject(HttpServletRequest request,String projectName){
		String dataStoreDir = getNoSQLDBPath(request);
		String username = (String) request.getSession().getAttribute("username");
		SchemaMigrator schemaDesign = null;
		try {
			schemaDesign = (SchemaMigrator) DBEngine_new.getData("SchemaDesigner", projectName, dataStoreDir+"/"+username);
		} catch (Exception e) {
			dslogger.error("Unable to get schema design object for project - "+projectName);
		}
		return schemaDesign;
	}
	
	public static List<SchemaMigrator> getAllSchemaMigrator(HttpServletRequest request) {
		dslogger.info("Entering method to get all schema design from db");
		ArrayList<String> data = null;
		String tableDocName = "SchemaDesigner";
		SchemaMigrator catalogData = null;
		String dataStoreDir = Utils.getNoSQLDBPath(request);
		String username = (String) request.getSession().getAttribute("username");
		try {
			data = DBEngine_new.getKeyValuesList(tableDocName,dataStoreDir + File.separator + username);
		} catch (Exception e) {
			dslogger.error("Error in getting values from db - "+e.getMessage());
		}
		List<SchemaMigrator> catalogList = new ArrayList<>();
		if(data != null)
		{
			for(int i = 0; i < data.size(); i++)
			{
				try {
					//System.out.println("Individual Design Data: "+data.get(i));
					catalogData = (SchemaMigrator) DBEngine_new.getData("SchemaDesigner",data.get(i),dataStoreDir+File.separator+username);
					//System.out.println("Final Schema Data: "+catalogData);
					catalogList.add(catalogData);
				}catch (Exception e) {
					dslogger.error("Error in getting values from db - "+e.getMessage());
				}
			}
		}else{
			dslogger.error("Unable to fetch schema design data from db");
		}
		return catalogList;
	}
	
	public static TreeMap<String, List<String>> getCatalogTree(HttpServletRequest request) {
		String noSqlpath = getNoSQLDBPath(request);
		String username = (String) request.getSession().getAttribute("username");
		File path = new File(noSqlpath+"/"+username+"/Catalogs");
		TreeMap<String, List<String>> treeMap = new TreeMap<String, List<String>>();
		File [] binfiles = path.listFiles();
		String fileName = "";
		for (int i = 0; i < binfiles.length; i++)
		{
			fileName = binfiles[i].getName();
			if (binfiles[i].isFile() && !binfiles[i].getName().equalsIgnoreCase("dummyCatalog.bin")){ 
				fileName = fileName.substring(0,fileName.lastIndexOf("."));
				Catalogs catalogtree = getCatalogObject(request, fileName);
				List<String>entityNames = new ArrayList<String>();
				if(catalogtree.getEntityLst()!=null){
					for(int j=0;j<catalogtree.getEntityLst().size();j++){
						entityNames.add(catalogtree.getEntityLst().get(j).getPhysicalName());
					}
				}
				treeMap.put(catalogtree.getCatalogName(), entityNames);
			}
		}
		return treeMap;
	}
	
	public static String getUpdatedDbType(String dbType, Configurations ds) {
		for(Configuration conf : ds.getConfiguration()){
			if(conf.getName().equalsIgnoreCase("Cloud DBs")){
				for(Property prop : conf.getProperties().getProperty()){
					if(prop.getValue().equals(dbType))
						return prop.getName();
				}
			}
			if(conf.getName().equalsIgnoreCase("NoSQL DBs")){
				for(Property prop : conf.getProperties().getProperty()){
					if(prop.getValue().equals(dbType))
						return prop.getName();
				}
			}
			if(conf.getName().equalsIgnoreCase("Bigdata")){
				for(Property prop : conf.getProperties().getProperty()){
					if(prop.getValue().equals(dbType))
						return prop.getName();
				}
			}
			if(conf.getName().equalsIgnoreCase("Relational DBs")){
				for(Property prop : conf.getProperties().getProperty()){
					if(prop.getValue().equals(dbType))
						return prop.getName();
				}
			}
			if(conf.getName().equalsIgnoreCase("Files")){
				for(Property prop : conf.getProperties().getProperty()){
					if(prop.getValue().equals(dbType))
						return prop.getName();
				}
			}
		}
		return null;
	}
	
	public static int toolNo(String toolName)
	{
		System.out.println("toolName--->"+toolName);
		int toolNumber=0;
		if(toolName.toUpperCase().contains("ORACLE"))
			toolNumber=ToolTypeConstants.ORACLE;
		else if(toolName.toUpperCase().contains("DB2"))
			toolNumber=ToolTypeConstants.DB2;
		else if(toolName.toUpperCase().contains("TERADATA"))
			toolNumber=ToolTypeConstants.TERADATA;
		else if(toolName.toUpperCase().contains("NETEZZA"))
			toolNumber=ToolTypeConstants.NETEZZA;
		else if(toolName.toUpperCase().contains("SQL SERVER"))
			toolNumber=ToolTypeConstants.SQLSERVER;
		else if(toolName.toUpperCase().contains("MYSQL")  || toolName.toUpperCase().equalsIgnoreCase("Relational - MySQL"))
			toolNumber=ToolTypeConstants.MYSQL;
		else if(toolName.toUpperCase().contains("SYBASE"))
			toolNumber=ToolTypeConstants.SYBASE;
		else if(toolName.toUpperCase().contains("REDSHIFT"))
			toolNumber=ToolTypeConstants.REDSHIFT;
		else if(toolName.toUpperCase().contains("BIGQUERY"))
			toolNumber=ToolTypeConstants.BIGQUERY;
		else if(toolName.toUpperCase().equalsIgnoreCase("SNOWFLAKE DW") || toolName.toUpperCase().equalsIgnoreCase("SNOWFLAKE"))
			toolNumber=ToolTypeConstants.SNOWFLAKE;
		else if(toolName.toUpperCase().equalsIgnoreCase("Snowflake - Cloud"))
			toolNumber=ToolTypeConstants.SNOWFLAKECLOUD;
		else if((toolName.toUpperCase().equalsIgnoreCase("AZURESQL")) || (toolName.toUpperCase().equalsIgnoreCase("AZURE SQL DW")) || (toolName.toUpperCase().contains("AZURE")) || (toolName.toUpperCase().contains("Azure SQL DW (Synapse)")))
			toolNumber=ToolTypeConstants.AZURESQL;		
		else if(toolName.toUpperCase().contains("AURORA"))
			toolNumber=ToolTypeConstants.AURORA;
		else if(toolName.toUpperCase().contains("DATASTAGE"))
			toolNumber=ToolTypeConstants.DATASTAGE;
		else if(toolName.toUpperCase().contains("SSIS"))
			toolNumber=ToolTypeConstants.SSIS;
		else if(toolName.toUpperCase().contains("INFORMATICA"))
			toolNumber=ToolTypeConstants.INFORMATICA_PC;
		else if(toolName.toUpperCase().contains("APACHEBEAM"))
			toolNumber=ToolTypeConstants.APACHE_BEAM;
		else if(toolName.toUpperCase().equalsIgnoreCase("JAVA"))
			toolNumber=ToolTypeConstants.JAVA;
		else if(toolName.toUpperCase().contains("FILE") && !toolName.equalsIgnoreCase("Files - Parquet") && !toolName.equalsIgnoreCase("Files - Avro") && !toolName.equalsIgnoreCase("Files - XML"))
			toolNumber=ToolTypeConstants.FLATFILE;
		else if(toolName.toUpperCase().contains("ODBC"))
			toolNumber=ToolTypeConstants.ODBC;
		else if(toolName.toUpperCase().contains("PYSPARK"))
			toolNumber=ToolTypeConstants.PYSPARK;
		else if(toolName.toUpperCase().contains("TALEND"))
			toolNumber=ToolTypeConstants.TALEND;
		else if(toolName.toUpperCase().contains("AZUREDATAFACTORY"))
			toolNumber=ToolTypeConstants.AZUREDATAFACTORY;
		else if(toolName.toUpperCase().contains("ADFDATAFLOW"))
			toolNumber=ToolTypeConstants.ADFDATAFLOW;
		else if(toolName.toUpperCase().contains("MONGODB"))
			toolNumber=ToolTypeConstants.MONGODB;
		else if(toolName.toUpperCase().contains("AMAZONS3"))
			toolNumber=ToolTypeConstants.AMAZONS3;
		else if(toolName.toUpperCase().contains("SQLDW"))
			toolNumber=ToolTypeConstants.SQLDW;
		else if(toolName.toUpperCase().contains("SNOWFLAKE CLOUD DW"))
			toolNumber=ToolTypeConstants.SNOWFLAKECLOUD;
		else if(toolName.toUpperCase().contains("GREENPLUM"))
			toolNumber=ToolTypeConstants.GREENPLUM;
		else if(toolName.toUpperCase().contains("AVRO"))
			toolNumber=ToolTypeConstants.AVRO;
		else if(toolName.toUpperCase().contains("HADOOPHDFS") || toolName.toUpperCase().equalsIgnoreCase("Hadoop - HDFS"))
			toolNumber=ToolTypeConstants.HADOOPHDFS;
		else if(toolName.toUpperCase().contains("HADOOPHIVE") || toolName.toUpperCase().equalsIgnoreCase("Hadoop - Hive"))
			toolNumber=ToolTypeConstants.HADOOPHIVE;
		else if(toolName.toUpperCase().contains("SALESFORCE"))
			toolNumber=ToolTypeConstants.SALESFORCE;
		else if(toolName.toUpperCase().contains("IMPALA"))
			toolNumber=ToolTypeConstants.IMPALA;
		else if(toolName.toUpperCase().contains("PWX"))
			toolNumber=ToolTypeConstants.PWX;
		else if(toolName.toUpperCase().contains("PENTAHO"))
			toolNumber=ToolTypeConstants.PENTAHO;
		else if(toolName.toUpperCase().contains("POSTGRESQL"))
			toolNumber=ToolTypeConstants.POSTGRESQL;
		else if(toolName.toUpperCase().contains("MONETDB"))
			toolNumber=ToolTypeConstants.MONETDB;
		else if(toolName.toUpperCase().contains("PARQUET"))
			toolNumber=ToolTypeConstants.PARQUET;
		else if(toolName.toUpperCase().contains("VERTICA"))
			toolNumber=ToolTypeConstants.VERTICA;
		else if(toolName.toUpperCase().contains("XML"))
			toolNumber=ToolTypeConstants.XMLSCHEMA;
		else
			toolNumber=ToolTypeConstants.DS_TOOL;
		
		return toolNumber;
	}
	
	public static void cleanUpAndDeleteFolder(File remediatedPathDir) 
	{
		if(remediatedPathDir.isFile())
		{
			remediatedPathDir.delete();
			return;
		}

		File[] files = remediatedPathDir.listFiles();

		if(files != null)
		{
			for (File file : files) 
			{
				if (file.isDirectory()) 
				{
					File cleanUpDir = new File(remediatedPathDir.getPath() + File.separator + file.getName());
					cleanUpAndDeleteFolder(cleanUpDir);
				}
				else
					file.delete();
			}

			remediatedPathDir.delete();
		}

	}

	
}
