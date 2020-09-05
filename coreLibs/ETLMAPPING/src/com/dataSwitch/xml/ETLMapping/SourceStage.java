
package com.dataSwitch.xml.ETLMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.ColumnStruct;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Lookup.LookupSource;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.OtherTransformation;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.OtherTransformation.OutputDataElements.OutputDataElement;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.SourceDataset;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity.RelationalDataElements.DataElement;

public class SourceStage
{

	public final static void fillSourceCustomStage (Connector connector, DataMapping dataMapping, List<TransRecExtDTO> recsList)
	{
		if( dataMapping.getDataTransformations()  == null)
			return;
		if( dataMapping.getDataTransformations().getDataTransformation() == null)
			return;

		List<DataTransformation> dtTransList = dataMapping.getDataTransformations().getDataTransformation();
		Map<String, SourceDataset> srcDataSetList = new HashMap<String, SourceDataset>();
		Map<String, OtherTransformation> otherTransList = new HashMap<String, OtherTransformation>();
		Map<String,String> srcDecList = new HashMap<String,String>();
		
		Map<String,ArrayList<String>> specProp = new HashMap<String,ArrayList<String>>();

		
		List<SystemCatalog> srcSysCatalogList = connector.getSystemCatalogs().getSystemCatalog();
		SystemEntity  sysEntity = null;
		String dbConnectionName = null;
		
		for (int i = 0; i < dtTransList.size(); i++)
		{
			DataTransformation dtTrans = dataMapping.getDataTransformations().getDataTransformation().get(i);
			
										
			if (dtTrans.getTransformationType().equalsIgnoreCase("Source Definition"))
			{
				srcDataSetList.put(dtTrans.getName(), dtTrans.getSourceDataset());
				if(dtTrans.getSpecialProperties() != null) 
				{
					Utils.readingSpecialProp(specProp,dtTrans);
				}
				
				if(dtTrans.getDescription() != null && dtTrans.getDescription() != "")
				{
					srcDecList.put(dtTrans.getName(),dtTrans.getDescription());
				}
			}
				
			
			else if (dtTrans.getTransformationType().equalsIgnoreCase("Other Transformation"))
			{
				String orgname = dtTrans.getName().replace("original_", "");
				otherTransList.put(orgname, dtTrans.getOtherTransformation());
			}
		}

		ArrayList<String> lkpsrcCatalogRefList = new ArrayList<String>();
		ArrayList<String> lkpsrcFeedRefList = new ArrayList<String>();
		ArrayList<String> lkpQueryList = new ArrayList<String>();
		String lkpsrcCatalogRef = null, lkpsrcFeedRef = null, primarySystemEntityRefRef = null, joinCondition = null;
		// Identify and add Lookup Sources
		DataTransformation  dtTran;
		for(int dtTrans = 0; dtTrans < dtTransList.size(); dtTrans ++)
		{
			dtTran = dtTransList.get(dtTrans);
			if (dtTran.getLookup() == null)
				continue;
			
			for(LookupSource lkpSrc : dtTran.getLookup().getLookupSource()){
			lkpsrcCatalogRef = lkpSrc.getSystemCatalogRef();
			//lkpsrcFeedRef = dtTran.getLookup().getLookupSource().get(0).getSourceDatasetRef();
			lkpsrcFeedRef = lkpSrc.getLkpSourceComponentRef() != null ? lkpSrc.getLkpSourceComponentRef() : lkpSrc.getSourceDatasetRef();
			if ((lkpsrcFeedRef == null) || (lkpsrcFeedRef.length() <= 0))
				continue;

			String sqlQuery = null, filterQuery = null ;

			sqlQuery = lkpSrc.getLookupOverrideSQL();
			filterQuery = lkpSrc.getLookupSourceFilterSQL();
			if (((sqlQuery == null) || (sqlQuery.trim().length() <= 0)) && (filterQuery != null))
			{
				if (filterQuery.trim().length() > 0)
					sqlQuery = filterQuery;
			}

			lkpsrcCatalogRefList.add(lkpsrcCatalogRef);
			lkpsrcFeedRefList.add(lkpsrcFeedRef);
			lkpQueryList.add(sqlQuery);
		}
		}
		for (Entry<String, SourceDataset> entry : srcDataSetList.entrySet())
		{
			SourceDataset srcDataSet = entry.getValue();

			if(srcDataSet == null)
				continue;

			if (srcDataSet.getSystemCatalogRef().equalsIgnoreCase("Other Transformation"))
			{
				fillOtherTransDetails (otherTransList.get(entry.getKey()), recsList, entry.getKey());
				//fillOtherTransDetails (otherTransList.get(entry.getKey()), recsList, srcDataSet.getSystemEntityRef());
				continue;
			}

			for(int j = 0; j < srcSysCatalogList.size(); j++)
			{
				SystemCatalog sysCat = srcSysCatalogList.get(j);

				if (!srcDataSet.getSystemCatalogRef().equalsIgnoreCase(sysCat.getName()))
					continue;

				String catalogName = sysCat.getName();
				for(int k = 0; k < srcSysCatalogList.get(j).getSystemEntities().getSystemEntity().size(); k++ )
				{
					sysEntity = srcSysCatalogList.get(j).getSystemEntities().getSystemEntity().get(k);
					if (!srcDataSet.getSystemEntityRef().equalsIgnoreCase(sysEntity.getName()))
						continue;

					dbConnectionName = "";primarySystemEntityRefRef = null; joinCondition = null;
					if(srcSysCatalogList.get(j).getSystemConnection() != null)
						dbConnectionName = srcSysCatalogList.get(j).getSystemConnection().getName();

					String sqlQuery = "";
					String filterQuery = null ;
					String preSQl = null;
					String postSQL = null;
					String source_Des = null;
					
					
					boolean bLookupSrc = false;
					if (lkpsrcFeedRefList.contains(entry.getKey()))
					{
						bLookupSrc = true;

					}
					if (srcDataSet.getSqlQuery() != null)
						sqlQuery  = srcDataSet.getSqlQuery();
					if (srcDataSet.getPrimarySystemEntityRef() != null)
						primarySystemEntityRefRef = srcDataSet.getPrimarySystemEntityRef();
					
					if (srcDataSet.getFilterCondition() != null)
						filterQuery =srcDataSet.getFilterCondition().getValue();

					if (srcDataSet.getSourceFeedJoin() != null)
						joinCondition = srcDataSet.getSourceFeedJoin().getJoinCondition().getValue();
					
					if(srcDataSet.getPreSQL() != null)
						preSQl = srcDataSet.getPreSQL();
					
					if(srcDataSet.getPostSQL() != null)
						postSQL = srcDataSet.getPostSQL();
					for (Entry<String, String> entry_Des : srcDecList.entrySet())
					{
						if(entry_Des.getKey().equalsIgnoreCase(entry.getKey()))
							source_Des = entry_Des.getValue();
					}
					

					fillSourceDetails(sysEntity, recsList, bLookupSrc, sqlQuery, dbConnectionName, entry.getKey(), primarySystemEntityRefRef, joinCondition, filterQuery,preSQl,postSQL,source_Des,catalogName,specProp);
						

				}
			}
		}

	}


	private final static void fillOtherTransDetails (OtherTransformation otherTrans, List<TransRecExtDTO> recsList, String compName)
	{

		TransRecExtDTO srcRecExt = new TransRecExtDTO();

		SrcTgtInfo transRec = (SrcTgtInfo) srcRecExt.transInfo;

		if (transRec == null)
		{
			transRec = new SrcTgtInfo();
			srcRecExt.transInfo = transRec;
		}

		srcRecExt.compName = compName;//sysEntity.getName();
		srcRecExt.transType = "Source";

		transRec.primarySystemEntityRefRef = null;

		transRec.joinCondition = null;

		srcRecExt.seqNo = 0;
		transRec.overrideSQL = "";

		transRec.fileType = otherTrans.getTransformationType();

		transRec.directoryPath = null;
		transRec.schemaName = null;

		transRec.connectionName = "";

		transRec.tableName =otherTrans.getName();
		
		if(otherTrans.getAdditionalProperties() != null)
		{
			Utils.getOtherTransPropDtls(transRec,otherTrans.getAdditionalProperties());
		}
		
		
		transRec.dbType = "Other Transformation";

		for(int j = 0; j < otherTrans.getOutputDataElements().getOutputDataElement().size(); j ++)
		{
			OutputDataElement dataelement = otherTrans.getOutputDataElements().getOutputDataElement().get(j);

			ColumnDTO colDTO = new ColumnDTO();

			colDTO.colStruct = new ColumnStruct();


			colDTO.colStruct.name = dataelement.getName();
			String dataType = "string";

			if(dataelement.getDatatype() != null)
			{
				dataType = dataelement.getDatatype();
			}

			colDTO.colStruct.dataType = dataType;


			colDTO.colStruct.precision = dataelement.getPrecision();
			colDTO.colStruct.Scale= dataelement.getScale();

			String keyType = "none";
			colDTO.colStruct.keyType = Utils.getKeyType(keyType);

			colDTO.colStruct.isNullable= false;

			transRec.srcTgtColsList.add(colDTO);//System.out.println("fillSourceDetails===="+colDTO.colStruct.name+"==="+colDTO.colStruct.Scale);
		}


		srcRecExt.transInfo = transRec;

		recsList.add(srcRecExt);
	}


	private final static void fillSourceDetails (SystemEntity sysEntity, List<TransRecExtDTO> recsList, boolean lookup, String sqlQuery, String connectionName, 
			String compName, String primarySystemEntityRefRef, String joinCondition, String filterQuery, String preSQl, String postSQL, String source_dec, String catalogName, Map<String, ArrayList<String>> specProp)
	{
		TransRecExtDTO srcRecExt = new TransRecExtDTO();

		SrcTgtInfo transRec = (SrcTgtInfo) srcRecExt.transInfo;

		if (transRec == null)
		{
			transRec = new SrcTgtInfo();
			srcRecExt.transInfo = transRec;
		}

		boolean dbcheck;

		srcRecExt.compName = compName;//sysEntity.getName();
		if (lookup)
			srcRecExt.transType = "LookupSource";
		else
			srcRecExt.transType = "Source";

		if (primarySystemEntityRefRef != null)
			transRec.primarySystemEntityRefRef = primarySystemEntityRefRef;

		if (joinCondition != null)
			transRec.joinCondition = joinCondition;
		
		if (filterQuery != null)
			transRec.filterSQL = filterQuery;
		
		if(preSQl != "" && preSQl != null)
			transRec.preSQL = preSQl;
		
		if(postSQL != "" && postSQL != null)
			transRec.postSQL = postSQL;

		srcRecExt.seqNo = 0;
		transRec.overrideSQL = sqlQuery;

		transRec.fileType = sysEntity.getSystemEntityType();

		transRec.directoryPath = null;
		transRec.schemaName = null;

		transRec.connectionName = connectionName;
		transRec.catalogName = catalogName;

		transRec.tableName = sysEntity.getName();
		transRec.source_Desc = source_dec;
		 for (Map.Entry mapElement : specProp.entrySet()) 
		 {
			 if(mapElement.getKey().toString().equalsIgnoreCase(compName)) 
			 {
				 transRec.specialProp = (ArrayList<String>) mapElement.getValue();
			 }
		 }
		
		

		//check if fileType or DB Type
		dbcheck = Utils.checkForDB(sysEntity.getSystemEntityType());
		if(dbcheck)
		{
			transRec.schemaName=  sysEntity.getPhysicalProperties().getRelationalDatabaseProperties().getSchemaName();
		}
		else
		{
			transRec.directoryPath = sysEntity.getPhysicalProperties().getFlatFileProperties().getDirectoryPath();
			transRec.fileExtension = sysEntity.getPhysicalProperties().getFlatFileProperties().getFileExtension();
			if(sysEntity.getPhysicalProperties().getFlatFileProperties().getDelimitedFileProperties() != null)
			{
				transRec.delimiter = sysEntity.getPhysicalProperties().getFlatFileProperties().getDelimitedFileProperties().getColumnDelimiter();
			}
		}

		String stageType = Utils.getStageType(sysEntity.getSystemEntityType(), lookup);
		transRec.dbType = stageType;

		for(int j = 0; j < sysEntity.getRelationalDataElements().getDataElement().size(); j ++)
		{
			DataElement dataelement = sysEntity.getRelationalDataElements().getDataElement().get(j);

			ColumnDTO colDTO = new ColumnDTO();

			colDTO.colStruct = new ColumnStruct();

			colDTO.colStruct.name = dataelement.getName();
			String dataType = dataelement.getDatatype();
			colDTO.colStruct.sqlType = Utils.getSqlTypeForDataType(dataType);

			colDTO.colStruct.dataType = dataType;

			String keyType = dataelement.getKeyType();
			colDTO.colStruct.keyType = Utils.getKeyType(keyType);

			if(dataelement.getLength() != null)
				colDTO.colStruct.length = dataelement.getLength();

			if(dataelement.getPrecision() != null)
				colDTO.colStruct.precision = dataelement.getPrecision();

			if(dataelement.getScale() != null)
				colDTO.colStruct.Scale= dataelement.getScale();
			
			if(dataelement.getDataFormat() != null)
				colDTO.colStruct.dateFormat= dataelement.getDataFormat();

			if(dataelement.isIsNullable())
				colDTO.colStruct.isNullable= dataelement.isIsNullable();
			
			if(dataelement.getDescription() != null)
				colDTO.colStruct.description = dataelement.getDescription();
			
			transRec.srcTgtColsList.add(colDTO);//System.out.println("fillSourceDetails===="+colDTO.colStruct.name+"==="+colDTO.colStruct.Scale);
		}
		
		srcRecExt.transInfo = transRec;

		recsList.add(srcRecExt);
	}

}