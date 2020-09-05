
package co.dataswitch.nosqldb;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


//import org.apache.log4j.Logger;

public class DBServices 
{

    public static DBObject_new getData(String tableDocName, ArrayList<String> pkCompositeValues, String dataStoreDir) throws Exception {
        return DBEngine_new.getData (tableDocName, pkCompositeValues,dataStoreDir);
    }

    public static DBObject_new getData(String tableDocName, String pkValue, String dataStoreDir) throws Exception {
        return DBEngine_new.getData (tableDocName, pkValue,dataStoreDir);
    }

    public static boolean insertData(String tableDocName, ArrayList<String> pkCompositeValues, DBObject_new dataObj, String dataStoreDir) throws Exception {
        return DBEngine_new.insertData(tableDocName, pkCompositeValues, dataObj,dataStoreDir);
    }

    public static boolean insertData(String tableDocName, String pkValue, DBObject_new dataObj, String dataStoreDir) throws Exception {
        return DBEngine_new.insertData(tableDocName, pkValue, dataObj,dataStoreDir);
    }

    public static boolean updateData(String tableDocName, ArrayList<String> pkCompositeValues, DBObject_new dataObj, String dataStoreDir) throws Exception {
        return DBEngine_new.updateData(tableDocName, pkCompositeValues, dataObj,dataStoreDir);
    }

    public static boolean updateData(String tableDocName, String pkValue, DBObject_new dataObj, String dataStoreDir) throws Exception {
        return DBEngine_new.updateData(tableDocName, pkValue, dataObj,dataStoreDir);
    }

    public static boolean deleteData(String tableDocName, ArrayList<String> pkCompositeValues, String dataStoreDir) throws Exception {
        return DBEngine_new.deleteData(tableDocName, pkCompositeValues,dataStoreDir);
    }

    public static boolean deleteData(String tableDocName, String pkValue, String dataStoreDir) throws Exception {
        return DBEngine_new.deleteData(tableDocName, pkValue,dataStoreDir);
    }

    public static ArrayList<String> getKeyValuesList(String tableDocName, String dataStoreDir) throws Exception {
        return DBEngine_new.getKeyValuesList(tableDocName,dataStoreDir);
    }
}
