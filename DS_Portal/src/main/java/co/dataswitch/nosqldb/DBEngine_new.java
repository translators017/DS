
package co.dataswitch.nosqldb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



//import org.apache.log4j.Logger;

public class DBEngine_new {

    public static final int CONCURRENCY_TYPE_SEQUENTIAL = 1;
    public static final int CONCURRENCY_TYPE_PARALLEL = 2;

    private static final int TRANS_TYPE_INSERT = 1;
    private static final int TRANS_TYPE_UPDATE = 2;
    private static final int TRANS_TYPE_DELETE = 3;

     //private static Logger logger = Logger.getLogger(DBEngine.class);

    //private static String dataStoreDir = System.getProperty("user.dir") + "\\NoSQLDBDataStore\\";
    
   // private static String dataStoreDir = Common.getNoSQLDBPath();
    
    private static String tableNameStoreDir = System.getProperty("user.dir") + File.separator+"NoSQLDBTblNameStore"+File.separator;
    private static String keyStoreDir = System.getProperty("user.dir") + File.separator+"NoSQLDBKeyStore"+File.separator;

    private static int concurrencyType = CONCURRENCY_TYPE_PARALLEL;

    public static boolean setConcurrencyStrategy (int type)
    {
    	if ((type == CONCURRENCY_TYPE_SEQUENTIAL) || (type == CONCURRENCY_TYPE_PARALLEL))
    	{
    		concurrencyType = type;
    		return true;
    	}

    	return false;
    }

    public static DBObject_new getData(String tableDocName, ArrayList<String> pkCompositeValues,String dataStoreDir) throws Exception
    {
    	String pkValue = getNoDBCompositeKeyValue(pkCompositeValues);

    	return getData(tableDocName, pkValue,dataStoreDir);
    }
   

    public static boolean insertData(String tableDocName, ArrayList<String> pkCompositeValues, DBObject_new dataObj,String dataStoreDir) throws Exception
    {
    	String pkValue = getNoDBCompositeKeyValue(pkCompositeValues);

        return insertData(tableDocName, pkValue, dataObj,dataStoreDir);
    }

    public static boolean updateData (String tableDocName, ArrayList<String> pkCompositeValues, DBObject_new dataObj,String dataStoreDir) throws Exception
    {
    	String pkValue = getNoDBCompositeKeyValue(pkCompositeValues);

    	return updateData(tableDocName, pkValue, dataObj,dataStoreDir);
	}

    public static boolean deleteData (String tableDocName, ArrayList<String> pkCompositeValues,String dataStoreDir) throws Exception
    {
    	String pkValue = getNoDBCompositeKeyValue(pkCompositeValues);

    	return deleteData(tableDocName, pkValue,dataStoreDir);
	}

    public static DBObject_new getData(String tableDocName, String pkValue,String dataStoreDir) throws Exception
    {
    	File dataFile = getDataFile (tableDocName,pkValue,dataStoreDir);
    	if(tableDocName.contains("/"))
    		tableDocName = tableDocName.substring(0,tableDocName.indexOf("/"));
    	DBObject_new dbObj = getDataObj (tableDocName);
    	if (dataFile == null)
    		return null;

		
        try
        {
        	FileReader reader = new FileReader(dataFile);
        	BufferedReader br = new BufferedReader(reader);
        	String jsonString = br.readLine();
        	JSONParser parser = new JSONParser();
        	JSONObject json = (JSONObject) parser.parse(jsonString);
        	dbObj.setJsonData(json);
        	reader.close();
        	br.close();
        	
        }
        catch (Exception ex)
        {
           
        }
        return dbObj;
    }
    
    ///////////////Added for Data Switch - Code starts here////////////////
    //read the pretty json file
    public static DBObject_new getDataPrettyJson(String tableDocName, String pkValue,String dataStoreDir) throws Exception
    {
    	File dataFile = getDataFile (tableDocName,pkValue,dataStoreDir);
    	System.out.println("dataFile: "+dataFile);
    	if(tableDocName.contains("/"))
    		tableDocName = tableDocName.substring(0,tableDocName.indexOf("/"));
    	DBObject_new dbObj = getDataObj (tableDocName);
    	System.out.println("dbObj: "+dbObj);
    	if (dataFile == null)
    		return null;
		
        try
        {
        	FileReader reader = new FileReader(dataFile);
        	//BufferedReader br = new BufferedReader(reader);
        	//String jsonString = br.readLine();
        	System.out.println("reader: "+reader);
        	JSONParser parser = new JSONParser();
        	JSONObject json = (JSONObject) parser.parse(reader);
        	System.out.println("json: "+json);
        	dbObj.setJsonData(json);
        	reader.close();
        	//br.close();        	
        }
        catch (Exception ex)
        {
           
        }
        return dbObj;
    }
    
    
    private static File getDataPrettyFile (String tableDocName, String pkValue,String dataStoreDir) throws Exception
    {
    	
    	if (!isValidNoDBTableName (tableDocName))
			throw (new Exception("Invalid Table Doc Name"));

    	if (!isValidNoDBKeyValue(pkValue))
			throw (new Exception("Invalid Key Value"));

        try
        {
    		File file = new File(dataStoreDir + "/" + tableDocName + "/" + pkValue + ".bin");
            if (!file.isDirectory() && file.exists())
                return file;
        }
        catch (Exception ex)
        {
        	return null;
        }
        finally
        {
        }

        return null;
    }
    ///////////////Added for Data Switch - Code ends here////////////////
    

    public static boolean insertData(String tableDocName, String pkValue, DBObject_new dataObj, String dataStoreDir) throws Exception
    {
    	if (concurrencyType == CONCURRENCY_TYPE_SEQUENTIAL)
    		return syncDMLTransactions (TRANS_TYPE_INSERT, tableDocName, pkValue, dataObj,dataStoreDir);
    	else
    		return insertDataSync (tableDocName, pkValue, dataObj, dataStoreDir);
    }

    public static boolean updateData(String tableDocName, String pkValue, DBObject_new dataObj,String dataStoreDir) throws Exception
    {
    	if (concurrencyType == CONCURRENCY_TYPE_SEQUENTIAL)
    		return syncDMLTransactions (TRANS_TYPE_UPDATE, tableDocName, pkValue, dataObj,dataStoreDir);
    	else
    		return updateDataSync(tableDocName, pkValue, dataObj,dataStoreDir);
    }
    
    public static boolean deleteData(String tableDocName, String pkValue,String dataStoreDir) throws Exception
    {
    	if (concurrencyType == CONCURRENCY_TYPE_SEQUENTIAL)
    		return syncDMLTransactions (TRANS_TYPE_DELETE, tableDocName, pkValue, null,dataStoreDir);
    	else
    		return deleteDataSync(tableDocName, pkValue,dataStoreDir);
    }

    private synchronized static boolean syncDMLTransactions (int transType, String tableDocName, String pkValue, DBObject_new dataObj,String dataStoreDir) throws Exception
    {
    	switch (transType)
    	{
    		case TRANS_TYPE_INSERT:
        		return insertDataSync (tableDocName, pkValue, dataObj, dataStoreDir);
    		case TRANS_TYPE_UPDATE:
        		return updateDataSync (tableDocName, pkValue, dataObj, dataStoreDir);
    		case TRANS_TYPE_DELETE:
        		return deleteDataSync (tableDocName, pkValue, dataStoreDir);
    	}

    	return false;
    }

    private static boolean insertDataSync(String tableDocName, String pkValue, DBObject_new dataObj,String dataStoreDir) throws Exception
    {
    	File dataFile = getDataFile (tableDocName, pkValue,dataStoreDir);
    	if (dataFile != null)
			throw (new Exception("Key already exists - Cannot insert this record"));

        try
        {
        	
            File file = new File(dataStoreDir +File.separator+ tableDocName);
            file.mkdirs();
            
            File dtfile = new File(file + File.separator + pkValue + ".bin" );
            FileWriter fw = new FileWriter(dtfile);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(dataObj.getJsonData().toString());
            bw.flush();
            fw.flush();
            bw.close();
            fw.close();
          
        }
        catch (Exception ex)
        {
//            logger.error("UserID:" + keyInfo.getUserId() + " ProjectID:" + tableDocName + " Error Message:" + ex.getMessage(), ex);
            //throw ex;
        	return false;
        }

        return true;
    }

    private static boolean updateDataSync(String tableDocName, String pkValue, DBObject_new dataObj,String dataStoreDir) throws Exception
    {
    	File dataFile = getDataFile (tableDocName, pkValue,dataStoreDir);
    	if (dataFile == null)
    		return false;

        try
        {
        	//dataFile.delete();
        	FileWriter fw = new FileWriter(dataFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(dataObj.getJsonData().toString());
            bw.flush();
            fw.flush();
            bw.close();
            fw.close();
            
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        	return false;
        }

        return true;
    }

    private static boolean deleteDataSync(String tableDocName, String pkValue,String dataStoreDir) throws Exception
    {
    	File dataFile = getDataFile (tableDocName, pkValue,dataStoreDir);
    	if (dataFile == null)
    		return false;

        try
        {
        	return dataFile.delete();
        }
        catch (Exception ex)
        {
          //  logger.error("UserID:" + keyInfo.getUserId() + " ProjectID:" + tableDocName + " Error Message:" + ex.getMessage(), ex);
            return false;
        }
    }

    public static ArrayList<String> getKeyValuesList(String tableDocName,String dataStoreDir) throws Exception
    {    	
    	
    	if (!isValidNoDBTableName (tableDocName))
			throw (new Exception("Invalid Table Name"));

        ArrayList<String> lst = new ArrayList<String>();
        //lst.add("");
        //System.out.println("dataStoreDir---"+dataStoreDir);
        try
        {
            File file = new File(dataStoreDir +File.separator+ tableDocName +File.separator);
            if (file.exists()){
            	//return lst;

            String fname[] = file.list();
            for (int i = 0; i < fname.length; i++)
            {
            	//System.out.println("filename---->"+fname[i]);
            	if(fname[i].contains(".bin")){
            		lst.add(fname[i].substring(0, fname[i].lastIndexOf(".bin")));
            	}
            }
            }
        }
        catch (Exception ex)
        {
           // logger.error("UserID:" + keyInfo.getUserId() + " ProjectID:" + tableDocName + " Error Message:" + ex.getMessage(), ex);
            throw ex;
        }

        return lst;
    }

    private static File getDataFile (String tableDocName, String pkValue,String dataStoreDir) throws Exception
    {
    	
    	if (!isValidNoDBTableName (tableDocName))
			throw (new Exception("Invalid Table Doc Name"));

    	if (!isValidNoDBKeyValue(pkValue))
			throw (new Exception("Invalid Key Value"));

        try
        {
    		File file = new File(dataStoreDir + File.separator + tableDocName + File.separator + pkValue + ".bin");
            if (!file.isDirectory() && file.exists())
                return file;
        }
        catch (Exception ex)
        {
        	return null;
        }
        finally
        {
        }

        return null;
    }
       
    private static DBObject_new getDataObj (String tableDocName) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
    	DBObject_new dbObj = null;
    	switch(tableDocName) {
	    	case "Catalogs":
				dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.Catalogs").newInstance();
				break;
	    	case "SchemaDesigner": //Added for Data Switch (Schema Migrator)
				dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.SchemaMigrator").newInstance();
				break;
	    	case "schemaDesign": //Added for Data Switch (Schema Design DTO)
				dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.SchemaDesignDTO").newInstance();
				break;
	    	case "schemaEntities": //Added for Data Switch (Entity Design DTO)
				dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.EntityDesignDTO").newInstance();
				break;
	    	case "VisualMapper":
    			dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.SystemMapping").newInstance();
    			break;
    		case "mapping":
    			dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.Mapping").newInstance();
    			break;
    		case "CatalogingTools":
    			dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.Sync").newInstance();
    			break;
    		case "DBMigrationTools":
    			dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.Workgroups").newInstance();
    			break;
    		case "JobDetails":
    			dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.JobDetails").newInstance();
    			break;
    		case "ArchitecureStudio":
    			dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.PASProjectConfig").newInstance();
    			break;
    		case "DataDomain":
    			dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.DataDomain").newInstance();
    			break;
    		case "ModelDerivator":
    			dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.ModelDerivator").newInstance();
    			break;
    		case "ProjectSetup":
    			dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.DataMicroServices").newInstance();
    			break;
    		case "ServiceSetup":
    			dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.DataServiceSetModel").newInstance();
    			break;
    		case "VisualDesigner":
    			dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.SystemMapping").newInstance();
    			break;
    		case "MappingDocConf":
    			dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.MappingConfig").newInstance();
    			break;
    		case "ToolConvDet":
    			dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.ToolConvDet").newInstance();
    			break;
    		case "ScriptRem":
    			dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.ScriptRem").newInstance();
    			break;
    		case "BIMigrator":
    			dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.BIMigrationMetadata").newInstance();
    			break;
    		case "ProcessGroups":
    			dbObj = (DBObject_new) Class.forName("co.dataswitch.nosqldbDTO.ProcessGroup").newInstance();
    			break;
    	}
    	return dbObj;
    }

    private static String getNoDBCompositeKeyValue(ArrayList<String> pkCompositeValues) throws Exception
    {
    	String pkValue = "";

    	if (pkCompositeValues.size() <= 0)
			throw (new Exception("Key not found in Key Values List"));

    	for (int i = 0; i < pkCompositeValues.size(); i++)
    	{
    		if (!isValidNoDBKeyValue(pkCompositeValues.get(i)))
    			throw (new Exception("Invalid Key Value"));

    		if (i > 0)
    			pkValue += ("_" + pkCompositeValues.get(i));
    		else
    			pkValue = pkCompositeValues.get(i);
    	}

    	return pkValue;
    }

    private static boolean isValidNoDBKeyValue (String keyValue) throws Exception
    {
    	if (keyValue == null)
    		throw (new Exception("Key Value cannot be NULL"));

    	if (keyValue.trim().length() <= 0)
    		throw (new Exception("Key Value cannot be empty string"));

    	return true;
    }

    private static boolean isValidNoDBTableName (String tableName) throws Exception
    {
    	if (tableName == null)
    		throw (new Exception("Table Name cannot be NULL"));

    	if (tableName.trim().length() <= 0)
    		throw (new Exception("Table Name cannot be empty string"));

    	return true;
    }
}
