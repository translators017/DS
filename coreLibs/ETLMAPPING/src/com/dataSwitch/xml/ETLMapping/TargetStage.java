
package com.dataSwitch.xml.ETLMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.ColumnStruct;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.OtherTransformation;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.OtherTransformation.InputDataElements.InputDataElement;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.TargetDataset;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity.RelationalDataElements.DataElement;


public class TargetStage
{

	int count = 0;

	public final static void fillTargetCustomStage (Connector connector, DataMapping datamapping, List<TransRecExtDTO> recsList)
	{
		List<DataTransformation> dtTransList = datamapping.getDataTransformations().getDataTransformation();
		//		List<TargetDataset> tgtDataSetList = new ArrayList<TargetDataset>();
		Map<String, TargetDataset> tgtDataSetList = new HashMap<String, TargetDataset>();
		Map<String, OtherTransformation> otherTransList = new HashMap<String, OtherTransformation>();
		Map<String,String> srcDecList = new HashMap<String,String>();

		Map<String,ArrayList<String>> specProp = new HashMap<String,ArrayList<String>>();

		List<SystemCatalog> tgtCatalogList = connector.getSystemCatalogs().getSystemCatalog();
		SystemEntities.SystemEntity  sysEntity = null;
		String dbConnectionName = null; 

		for (int i = 0; i < dtTransList.size(); i++)
		{
			DataTransformation dtTrans = datamapping.getDataTransformations().getDataTransformation().get(i);
			if (dtTrans.getTransformationType().equalsIgnoreCase("Target Definition")){
				//System.out.println("yyy---"+dtTrans.getName()+"."+dtTrans.getRejectOption());
				if(dtTrans.getDescription() != null && dtTrans.getDescription() != "")
				{
					srcDecList.put(dtTrans.getName(),dtTrans.getDescription());
				}	
				if(dtTrans.getSpecialProperties() != null) 
				{
					Utils.readingSpecialProp(specProp,dtTrans);
				}
				if(dtTrans.getRejectOption().length() > 0)
					tgtDataSetList.put(dtTrans.getName()+"."+dtTrans.getRejectOption(), dtTrans.getTargetDataset());
				else
					tgtDataSetList.put(dtTrans.getName(), dtTrans.getTargetDataset());
			}else if (dtTrans.getTransformationType().equalsIgnoreCase("Other Transformation"))
			{
				if(dtTrans.getRejectOption().length() > 0)
				{
					String orgname = "tgtInput_"+ dtTrans.getName().replace("original_", "")+"."+dtTrans.getRejectOption();
					otherTransList.put(orgname, dtTrans.getOtherTransformation());
				}
				else
				{
					String orgname = "tgtInput_"+ dtTrans.getName().replace("original_", "");
					otherTransList.put(orgname, dtTrans.getOtherTransformation());
				}
			}
		}
		for (Entry<String, TargetDataset> entry : tgtDataSetList.entrySet())
		{
			TargetDataset tgtDataSet = entry.getValue();

			if(tgtDataSet == null)
				continue;

			if (tgtDataSet.getSystemCatalogRef().equalsIgnoreCase("Other Transformation"))
			{
				fillOtherTargetDetails(otherTransList.get(entry.getKey()), recsList, entry.getKey());
				continue;
			}

			for(int j = 0; j < tgtCatalogList.size(); j++)
			{
				SystemCatalog sysCat = tgtCatalogList.get(j);

				if (!tgtDataSet.getSystemCatalogRef().equalsIgnoreCase(sysCat.getName()))
					continue;

				String catalogName = sysCat.getName();
				for(int k = 0; k < tgtCatalogList.get(j).getSystemEntities().getSystemEntity().size(); k++ )
				{
					sysEntity = tgtCatalogList.get(j).getSystemEntities().getSystemEntity().get(k);
					if (!tgtDataSet.getSystemEntityRef().equalsIgnoreCase(sysEntity.getName()))
						continue;

					dbConnectionName = "";
					if(tgtCatalogList.get(j).getSystemConnection() !=null)
						dbConnectionName = tgtCatalogList.get(j).getSystemConnection().getName();
					String target_Des = null;
					for (Entry<String, String> entry_Des : srcDecList.entrySet())
					{
						if(entry_Des.getKey().equalsIgnoreCase(entry.getKey()))
							target_Des = entry_Des.getValue();
					}
					fillTargetDetails(sysEntity, recsList, false, tgtDataSet, dbConnectionName, entry.getKey(),target_Des,catalogName,specProp);
				}
			}
		}
	}



	private final static void fillOtherTargetDetails(OtherTransformation otherTrans, List<TransRecExtDTO> recsList, String CompName)
	{
		TransRecExtDTO tgtRecExt = new TransRecExtDTO();

		SrcTgtInfo transRec = (SrcTgtInfo) tgtRecExt.transInfo;

		if (transRec == null)
		{
			transRec = new SrcTgtInfo();
			tgtRecExt.transInfo = transRec;
		}

		//take the columns from the targetCatalog
		String rejCompName[] = CompName.split("\\.");
		if (rejCompName.length > 1)
		{
			tgtRecExt.compName = rejCompName[0];
			tgtRecExt.rejectOption = rejCompName[1];
		}
		else
		{
			tgtRecExt.compName = CompName;//tgtfeed.getName();

		}

		//	tgtRecExt.transType = "Other Transformation";
		tgtRecExt.transType = "Target";
		transRec.loadStrategy = "";
		transRec.overrideSQL = "";
		transRec.insertSQL = "";
		transRec.updateSQL = "";

		transRec.connectionName = "";

		transRec.tableName = otherTrans.getName();

		transRec.fileType = otherTrans.getTransformationType();

		transRec.srcTgtColsList = new ArrayList<ColumnDTO>();

		if(otherTrans.getAdditionalProperties() != null)
		{
			Utils.getOtherTransPropDtls(transRec,otherTrans.getAdditionalProperties());
		}
		
		if (otherTrans.getInputDataElements() != null)
		{


			for(int j = 0; j < otherTrans.getInputDataElements().getInputDataElement().size(); j ++)
			{
				InputDataElement dataelement = otherTrans.getInputDataElements().getInputDataElement().get(j);
				ColumnDTO colDTO = new ColumnDTO();

				colDTO.colStruct = new ColumnStruct();


				colDTO.colStruct.name = dataelement.getName();

				String keyType = "none";
				colDTO.colStruct.keyType =  Utils.getKeyType(keyType);

				colDTO.colStruct.length = 0;


				String dataType= "string";
				if(dataelement.getDatatype() != null)
				{
					dataType= dataelement.getDatatype();
				}


				colDTO.colStruct.dataType = dataType;


				colDTO.colStruct.precision = dataelement.getPrecision();

				colDTO.colStruct.Scale = dataelement.getScale();


				colDTO.colStruct.isNullable= false;
				transRec.srcTgtColsList.add(colDTO);//System.out.println("fillTargetDetails===="+colDTO.colStruct.name+"===="+colDTO.colStruct.Scale);
			}
		}
		System.out.println("fillTargetDetails===="+tgtRecExt.compName+"     "+tgtRecExt.transType);

		transRec.dbType = "Other Transformation";
		//transRec.dbType = otherTrans.getTransformationType();

		recsList.add(tgtRecExt);
	}



	private final static void fillTargetDetails(SystemEntities.SystemEntity tgtfeed, List<TransRecExtDTO> recsList, boolean lookup, TargetDataset tgtDataSet, String ConnectionName, String CompName, String target_Des, String catalogName, Map<String, ArrayList<String>> specProp)
	{

		TransRecExtDTO tgtRecExt = new TransRecExtDTO();

		SrcTgtInfo transRec = (SrcTgtInfo) tgtRecExt.transInfo;

		if (transRec == null)
		{
			transRec = new SrcTgtInfo();
			tgtRecExt.transInfo = transRec;
		}

		//take the columns from the targetCatalog
		String tempComp = ""; 
		String rejCompName[] = CompName.split("\\.");
		if (rejCompName.length > 1)
		{
			tgtRecExt.compName = rejCompName[0];
			tgtRecExt.rejectOption = rejCompName[1];
			tempComp =  rejCompName[0];
		}
		else
		{
			tgtRecExt.compName = CompName;
			tempComp = CompName;
			//tgtfeed.getName();
		}

		//tgtRecExt.compName = CompName;//tgtfeed.getName();
		tgtRecExt.transType = "Target";
		

		transRec.loadStrategy = tgtDataSet.getTargetDataLoadOptions().getLoadStrategy();
		if(tgtDataSet.getTargetDataLoadOptions().getLoadStrategy().equalsIgnoreCase("update")) 
		{
			String[] cols = tgtDataSet.getTargetDataLoadOptions().getUpdateColumns().split(",");
			for(int i = 0; i < cols.length; i++) 
			{
				transRec.updateCols.add(cols[i]);
			}
			String[] keyCols = tgtDataSet.getTargetDataLoadOptions().getKeyColumns().split(",");
			for(int i = 0; i < keyCols.length; i++) 
			{
				transRec.updateKeyCols.add(keyCols[i]);
			}
		}
		transRec.postSQL = tgtDataSet.getTargetDataLoadOptions().getPostSQL();
		transRec.preSQL = tgtDataSet.getTargetDataLoadOptions().getPreSQL();
		transRec.updateSQL = tgtDataSet.getTargetDataLoadOptions().getUpdateOverrideSQL();
		transRec.source_Desc = target_Des;
		transRec.connectionName = ConnectionName;

		transRec.tableName = tgtfeed.getName();
		transRec.catalogName = catalogName;
		for (Map.Entry mapElement : specProp.entrySet()) 
		 {
			 if(mapElement.getKey().toString().equalsIgnoreCase(tempComp)) 
			 {
				 transRec.specialProp = (ArrayList<String>) mapElement.getValue();
			 }
		 }
		transRec.fileType = tgtfeed.getSystemEntityType();
		
		if(tgtfeed.getPhysicalProperties() != null)
		{
			if(tgtfeed.getPhysicalProperties().getSemiStructuredFileProperties() != null)
			{
				if(tgtfeed.getPhysicalProperties().getSemiStructuredFileProperties().getNewRecordMarkers() != null)
					transRec.recordMarkerList = (ArrayList<String>) tgtfeed.getPhysicalProperties().getSemiStructuredFileProperties().getNewRecordMarkers().getNewRecordMarker();
			}
		}

		boolean dbcheck = Utils.checkForDB(tgtfeed.getSystemEntityType());
		if(dbcheck)
		{
			transRec.schemaName =  tgtfeed.getPhysicalProperties().getRelationalDatabaseProperties().getSchemaName();
		}
		else
		{
			if(!tgtfeed.getSystemEntityType().equalsIgnoreCase("Unknown DBtype"))
			{
				transRec.directoryPath = tgtfeed.getPhysicalProperties().getFlatFileProperties().getDirectoryPath();
				transRec.fileExtension = tgtfeed.getPhysicalProperties().getFlatFileProperties().getFileExtension();
			}
			if(tgtfeed.getPhysicalProperties().getFlatFileProperties().getDelimitedFileProperties() != null)
			{
				transRec.delimiter = tgtfeed.getPhysicalProperties().getFlatFileProperties().getDelimitedFileProperties().getColumnDelimiter();
			}
		}

		transRec.srcTgtColsList = new ArrayList<ColumnDTO>();

		for(int j = 0; j < tgtfeed.getRelationalDataElements().getDataElement().size(); j ++)
		{
			DataElement dataelement = tgtfeed.getRelationalDataElements().getDataElement().get(j);
			ColumnDTO colDTO = new ColumnDTO();

			colDTO.colStruct = new ColumnStruct();

			colDTO.colStruct.name= dataelement.getName();
			String dataType= dataelement.getDatatype();
			colDTO.colStruct.sqlType = Utils.getSqlTypeForDataType(dataType);

			colDTO.colStruct.dataType = dataType;

			String keyType = dataelement.getKeyType();
			colDTO.colStruct.keyType =  Utils.getKeyType(keyType);

			if(dataelement.getLength() != null)
				colDTO.colStruct.length = dataelement.getLength();

			if(dataelement.getPrecision() != null)
				colDTO.colStruct.precision = dataelement.getPrecision();

			if(dataelement.getScale() != null)
				colDTO.colStruct.Scale = dataelement.getScale();
			
			if(dataelement.getDataFormat() != null)
				colDTO.colStruct.dateFormat = dataelement.getDataFormat();

			if(dataelement.isIsNullable())
				colDTO.colStruct.isNullable= dataelement.isIsNullable();
			
			 if(dataelement.getDescription() != null)
					colDTO.colStruct.description = dataelement.getDescription();
			 
			 if(dataelement.getXPath() != null)
				 colDTO.colStruct.xPath = dataelement.getXPath();
			 
			//How to take the source column of the Column  as previously sourceColumn was there now not there
			//coloumnDTO.sourceColumn= dataelement.getName();
			transRec.srcTgtColsList.add(colDTO);//System.out.println("fillTargetDetails===="+colDTO.colStruct.name+"===="+colDTO.colStruct.Scale);
		}
		System.out.println("fillTargetDetails===="+tgtRecExt.compName+"     "+tgtRecExt.transType);

		String stageType = Utils.getStageType(tgtfeed.getSystemEntityType(), lookup);
		transRec.dbType = stageType;


		recsList.add(tgtRecExt);
	}

}