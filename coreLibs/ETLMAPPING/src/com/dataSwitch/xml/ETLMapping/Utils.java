
package com.dataSwitch.xml.ETLMapping;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.dataSwitch.ds.commons.datatyperules.DataTypeConvertor;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.coreLib.util.Console;
import com.dataSwitch.ds.functionTypeRules.RuleMapping;
import com.dataSwitch.ds.functionparser.Lexer;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.xml.ETLMapDTO.AggInfo;
import com.dataSwitch.xml.ETLMapDTO.ChecksumInfo;
import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.ColumnStruct;
import com.dataSwitch.xml.ETLMapDTO.ExprInfo;
import com.dataSwitch.xml.ETLMapDTO.InRecDTO;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.ReportLogInfo;
import com.dataSwitch.xml.ETLMapDTO.SeqGenInfo;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Lookup.LookupSource;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Multijoiner.DetailComponent;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Multijoiner.DetailComponent.JoinCondition;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Joiner;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Lookup;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.SourceDataset;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.TargetDataset;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Joiner.JoinCondition.DetailDataElement;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Joiner.JoinCondition.MasterDataElement;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.TargetDataset.TargetDataLoadOptions;
import com.dataSwitch.xml.connectorspecs.Connector.ProcessFlows.ProcessFlow;
import com.dataSwitch.xml.connectorspecs.Connector.ProcessFlows.ProcessFlow.Steps.Step;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity.RelationalDataElements.DataElement;
import com.dataSwitch.xml.connectorspecs.InputDataElementType;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType.TransformationRule;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType.TransformationRule.Parameter;


public class Utils
{


	public static final int JobType(Connector connector)
	{
		for(int dtmpng = 0; dtmpng < connector.getDataMappings().getDataMapping().size(); dtmpng++)
		{
			DataMapping datamapping = connector.getDataMappings().getDataMapping().get(dtmpng);
			if(datamapping.getAdditionalProperties().getDatastage().getJobType().equalsIgnoreCase("Parallel Job"))
				return 3;
		}
		return 1;
	}

	public static final String getStageType(String StageType, boolean lookup)
	{
		if(StageType.equalsIgnoreCase("Relational - ODBC"))
			return "ODBCConnectorPX";

		if(StageType.equalsIgnoreCase("HashedFileStage"))
			return "HashedFileStage";

		if(StageType.equalsIgnoreCase("Relational - DB2 LUW"))
			return "DB2ConnectorPX" ;

		if (StageType.equalsIgnoreCase("Relational - Netezza"))
			return "NetezzaConnectorPX";

		if  (StageType.equalsIgnoreCase("Relational - Oracle"))
			return "OracleConnectorPX";

		if(StageType.equalsIgnoreCase("Relational - Teradata"))
			return "TeradataConnectorPX";

		if(StageType.equalsIgnoreCase("Relational - SQL Server"))
			return "Pxsqlsvr";

		if(StageType.equalsIgnoreCase("Relational - Amazon Redshift"))
			return "ODBCConnectorPX";

		if(StageType.equalsIgnoreCase("Relational - Snowflake DW"))
			return "SnowFlakeConnectorPX";

		if(StageType.equalsIgnoreCase("Snowflake - Cloud"))
			return "SnowflakeCloudConnectorPX";

		if(StageType.equalsIgnoreCase("Salesforce - DB"))
			return "SalesforceConnectorPX";

		if(StageType.equalsIgnoreCase("BigQuery"))
			return "BigQueryConnectorPX";

		if(StageType.equalsIgnoreCase("Hadoop - HDFS"))
			return "HadoopHDFSPX";

		if(StageType.equalsIgnoreCase("Hadoop - Hive")) 
			return "HadoopHivePX";

		if(StageType.equalsIgnoreCase("Impala")) 
			return "HadoopImpalaPX";

		if(StageType.equalsIgnoreCase("Relational - Azure SQL Data Warehouse"))
			return "AzureDataWarehousePX";

		if(StageType.equalsIgnoreCase("Relational - Azure SQL DB"))
			return "AzureSQLDBPX";
		
		if(StageType.equalsIgnoreCase("Relational - Sybase"))
			return "SybaseConnectorPX";
		if(StageType.equalsIgnoreCase("Unknown DBtype"))
			return "UnknownDBtypeConnectorPX";

		if (StageType.equalsIgnoreCase("Files - Delimited"))
		{
			if(lookup)
				return "PxLookupFileSet";
			else
				return "PxSequentialFile";
		}
		if (StageType.equalsIgnoreCase("Files - Fixed Width"))
		{
			if(lookup)
				return "PxLookupFileSet";
			else
				return "PxSequentialFile";
		}
		if (StageType.equalsIgnoreCase("Files - Avro"))
		{
			if(lookup)
				return "PxLookupFileSet";
			else
				return "PxSequentialFile";
		}
		if (StageType.equalsIgnoreCase("Files - Parquet"))
		{
			if(lookup)
				return "PxLookupFileSet";
			else
				return "PxSequentialFile";
		}
		else
			return null;
	}

	public final static String getSqlTypeForDataType (String dataType )
	{
		if (dataType.equalsIgnoreCase("LongNVarChar"))
			return "-10";

		if (dataType.equalsIgnoreCase("NVarChar"))
			return "-9";

		if (dataType.equalsIgnoreCase("NChar"))
			return "-8";

		if (dataType.equalsIgnoreCase("Bit"))
			return "-7";

		if (dataType.equalsIgnoreCase("TinyInt"))
			return "-6";

		if (dataType.equalsIgnoreCase("BigInt"))
			return "-5";

		if (dataType.equalsIgnoreCase("LongVarBinary"))
			return "-4";

		if (dataType.equalsIgnoreCase("VarBinary"))
			return "-3";

		if (dataType.equalsIgnoreCase("Binary"))
			return "-2";

		if (dataType.equalsIgnoreCase("LongVarChar"))
			return "-1";

		if (dataType.equalsIgnoreCase("Unknown"))
			return "-1";

		if (dataType.equalsIgnoreCase("Char"))
			return "1";

		if (dataType.equalsIgnoreCase("Numeric"))
			return "2";

		if (dataType.equalsIgnoreCase("Decimal"))
			return "3";

		if (dataType.equalsIgnoreCase("Integer"))
			return "4";

		if (dataType.equalsIgnoreCase("SmallInt"))
			return "5";

		if (dataType.equalsIgnoreCase("Float"))
			return "6";

		if (dataType.equalsIgnoreCase("Real"))
			return "7";

		if (dataType.equalsIgnoreCase("Double"))
			return "8";

		if (dataType.equalsIgnoreCase("Date"))
			return "9";

		if (dataType.equalsIgnoreCase("Time"))
			return "10";

		if (dataType.equalsIgnoreCase("Timestamp"))
			return "11";

		if (dataType.equalsIgnoreCase("VarChar"))
			return "12";

		return "12";
	}

	public final static String getKeyType (String keyType)
	{
		if (keyType.equalsIgnoreCase("none"))
			return "0";

		if (keyType.equalsIgnoreCase("primary"))
			return "1";

		if (keyType.equalsIgnoreCase("primary-foreign"))
			return "2";

		if (keyType.equalsIgnoreCase("foreign"))
			return "3";

		return "0";
	}

	public static final List<com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.TargetDataset> getTargetdataSet(Connector connector)
	{
		List<com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.TargetDataset> tgtDataSet = new ArrayList<com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.TargetDataset>();
		for(int dtmpng = 0; dtmpng < connector.getDataMappings().getDataMapping().size(); dtmpng++)
		{
			DataMapping datamapping = connector.getDataMappings().getDataMapping().get(dtmpng);
			for(int dtpipe = 0; dtpipe < datamapping.getDataTransformations().getDataTransformation().size(); dtpipe ++)
			{
				DataTransformation datapipeline = datamapping.getDataTransformations().getDataTransformation().get(dtpipe);
				//				for(int src = 0; src < datapipeline.getTargetDataset().size(); src++)
				//				{
				com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.TargetDataset datasettgt = datapipeline.getTargetDataset();//.get(src);
				tgtDataSet.add(datasettgt);
				//				}
			}
		}
		return tgtDataSet;
	}
	public final static boolean checkForDB(String szStageType)
	{
		boolean szType;
		szType = checkIfDB(szStageType);
		if (szType)
		{
			return true;
		}
		else
			return false;
	}

	public static  boolean  checkIfDB(String StageType)
	{
		if((StageType.equalsIgnoreCase("Relational - ODBC")) || (StageType.equalsIgnoreCase("HashedFileStage")) ||
				(StageType.equalsIgnoreCase("Relational - DB2 LUW")) || (StageType.equalsIgnoreCase("Relational - Netezza"))
				|| (StageType.equalsIgnoreCase("Relational - Oracle"))|| (StageType.equalsIgnoreCase("Relational - Teradata"))
				|| (StageType.equalsIgnoreCase("Relational - Azure SQL DB ")) || StageType.equalsIgnoreCase("Relational - Amazon Redshift") 
				||(StageType.equalsIgnoreCase("Unknown DBtype"))||(StageType.equalsIgnoreCase("Relational - Snowflake DW")) ||(StageType.equalsIgnoreCase("Relational - Sybase"))||
				StageType.equalsIgnoreCase("MongoDb")||StageType.equalsIgnoreCase("BigQuery")||(StageType.equalsIgnoreCase("Salesforce - DB")) 
				|| (StageType.equalsIgnoreCase("Relational - SQL Server"))|| (StageType.equalsIgnoreCase("Snowflake - Cloud")) 
				|| (StageType.equalsIgnoreCase("Hadoop - HDFS")) || 
				(StageType.equalsIgnoreCase("Hadoop - Hive")) || (StageType.equalsIgnoreCase("Relational - Azure SQL Data Warehouse"))|| (StageType.equalsIgnoreCase("Relational - Azure SQL DB")) || 
				(StageType.equalsIgnoreCase("Impala") || (StageType.equalsIgnoreCase("Relational - MySQL"))))
		{
			return true;
		}
		return false;
	}


	public final static RuleMapping getTransformationRuleMappingObj (List<RuleMapping> codeGenRuleList, String ruleName)
	{
		RuleMapping ruleMapping = null;

		for (int i = 0; i < codeGenRuleList.size(); i++)
		{
			ruleMapping = codeGenRuleList.get(i);
			if(codeGenRuleList.get(i).getTransformationRule().getRuleName().equalsIgnoreCase(ruleName))
			{
				return ruleMapping;
			}
		}
		return null;
	}

	public final static String getTransformationRuleType(List<RuleMapping> codeGenRuleList, String ruleName)
	{
		String transType = null;
		for (int i = 0; i < codeGenRuleList.size(); i++)
		{
			RuleMapping ruleMapping = codeGenRuleList.get(i);
			if(ruleMapping.getTransformationRule().getRuleName().equalsIgnoreCase(ruleName))
			{
				transType = ruleMapping.getTransformationType();
				if (transType.equalsIgnoreCase("Expression"))
				{
					if (ruleMapping.getTransformationRule().getRuleName().equalsIgnoreCase("Encoding - MD5 Checksum"))
						transType = "MD5";
					else if (ruleMapping.getTransformationRule().getRuleName().equalsIgnoreCase("Encoding - CRC32"))
						transType = "CRC32";
					else if (ruleMapping.getTransformationRule().getRuleName().equalsIgnoreCase("Encoding - AES Encryption"))
						transType = "AESEncryption";
					else if (ruleMapping.getTransformationRule().getRuleName().equalsIgnoreCase("Encoding - AES Decryption"))
						transType = "AESDecryption";
				}
				if (ruleMapping.getTransformationType().equalsIgnoreCase("Data Transformation"))
					transType = ruleMapping.getExpression();
			}
		}
		return transType;
	}



	public static final boolean checkForTaget(TransRecExtDTO record)
	{
		if(record.transType.equalsIgnoreCase("Target Stage"))
			return true;
		else
			return false;
	}

	public static final ColumnDTO getColumnClone(ColumnDTO curObj, OutRecDTO parentOutRec1)
	{
		ColumnDTO obj = new ColumnDTO();

		obj.colStruct = curObj.colStruct;
		obj.isBeingUsed = curObj.isBeingUsed;
		obj.aliasName = curObj.aliasName;
		obj.parentOutRec = parentOutRec1;

		return obj;
	}

	public static final ColumnDTO getColumnDeepClone (ColumnDTO curObj)
	{
		ColumnDTO obj = new ColumnDTO();

		obj.colStruct = new ColumnStruct();

		obj.colStruct.name = curObj.colStruct.name;
		obj.colStruct.dataType = curObj.colStruct.dataType;
		obj.colStruct.sqlType = curObj.colStruct.sqlType;
		obj.colStruct.keyType = curObj.colStruct.keyType;
		obj.colStruct.length = curObj.colStruct.length;
		obj.colStruct.precision = curObj.colStruct.precision;
		obj.colStruct.Scale = curObj.colStruct.Scale;
		obj.colStruct.isNullable= curObj.colStruct.isNullable;
		obj.colStruct.dateFormat = curObj.colStruct.dateFormat;
				
		obj.isBeingUsed = curObj.isBeingUsed;
		obj.aliasName = curObj.aliasName;
		obj.parentOutRec = curObj.parentOutRec;

		return obj;
	}

	public final static TransRecExtDTO getTransRecDTOByName (List<TransRecExtDTO> list, String szCompName)
	{
		if (szCompName == null)
			return null;

		TransRecExtDTO obj = null;
		for (int i = 0; i < list.size(); i++)
		{
			obj = list.get(i);

			if (szCompName.equalsIgnoreCase(obj.compName))
				return obj;
		}

		return null;
	}


	public final static ColumnDTO getColumnDTOByNameInTransRec (TransRecExtDTO transRec, String compName, String columnName)
	{
		if(transRec.compName.equalsIgnoreCase("CP_UNC_LP"))
			System.out.println("Test");
		if (compName == null)
			return null;
		if (columnName == null)
			return null;
		String szCompColumnName = compName + "." + columnName;

		return (getColumnDTOByNameInTransRec (transRec, szCompColumnName));
	}

	public final static ColumnDTO getColumnDTOByNameInSrcTgt (TransRecExtDTO transRec, String compName, String columnName)
	{
		if (compName == null)
			return null;
		if (columnName == null)
			return null;
		String szCompColumnName = compName + "." + columnName;

		return (getColumnDTOByNameInTransRec (transRec, szCompColumnName));
	}

	public final static ColumnDTO getColumnDTOByNameInTransRec (TransRecExtDTO transRec, String szCompColumnName)
	{
		ColumnDTO colDTO;

		if (szCompColumnName == null)
			return null;
		if(transRec.compName.equalsIgnoreCase("CP_UNC_LP"))
			System.out.println("Test");
		//		TransRecExtDTO transRecExt = new TransRecExtDTO();
		//		transRecExt = (TransRecExtDTO)transRec;
		for (int i = 0; i < transRec.transColsList.size(); i++)
		{
			colDTO = transRec.transColsList.get(i);
			if (colDTO.colStruct.name.equalsIgnoreCase(szCompColumnName))
				return colDTO;
		}

		return null;
	}

	public final static ColumnDTO getColumnDTOByNameInTransRecSort (TransRecExtDTO transRec, String columnName)
	{
		ColumnDTO colDTO;

		if (columnName == null)
			return null;

		//		TransRecDTO transRec = new TransRecDTO();
		//		transRec = (TransRecDTO)transRecExt;

		SrcTgtInfo transRec1 = new SrcTgtInfo();
		transRec1 = (SrcTgtInfo)transRec.transInfo;
		for (int i = 0; i < transRec1.srcTgtColsList.size(); i++)
		{
			colDTO = transRec1.srcTgtColsList.get(i);

			for (int k = 0; k < transRec.inRecsList.size(); k++)
			{
				InRecDTO inRec = transRec.inRecsList.get(k);
				for (int l = 0; l < inRec.prevOutRec.columnList.size(); l++)
				{
					ColumnDTO inColDTO=inRec.prevOutRec.columnList.get(l);
					ColumnStruct colStruct1=new ColumnStruct();
					String srcCol = inColDTO.colStruct.name.substring(inColDTO.colStruct.name.indexOf(".")+1,inColDTO.colStruct.name.length());
					//					if (inColDTO.srcColumn != null)
					{
						if (colDTO.colStruct.name.equalsIgnoreCase(srcCol))
						{
							colStruct1.name = inColDTO.srcColumn.colStruct.name;
							colStruct1.dataType = inColDTO.colStruct.dataType;
							colStruct1.sqlType = inColDTO.colStruct.sqlType;
							colStruct1.length = inColDTO.colStruct.length;
							colStruct1.precision = inColDTO.colStruct.precision;

							colStruct1.dateFormat = inColDTO.colStruct.dateFormat;
							
							colStruct1.isNullable = inColDTO.colStruct.isNullable;

							colStruct1.Scale = inColDTO.colStruct.Scale;
							colStruct1.description = inColDTO.colStruct.description;
							colStruct1.intialValue = inColDTO.colStruct.intialValue;
							colStruct1.keyType = inColDTO.colStruct.keyType;
							//						colDTO = col;
							inColDTO.colStruct = colStruct1;
							return inColDTO;
						}
					}
				}
			}
		}

		return null;
	}

	public final static ColumnDTO getColumnDTOByNameOutRec (TransRecExtDTO transRecDTO, String szOutColumnName)
	{
		ColumnDTO colDTO;
		OutRecDTO outRec;

		if (szOutColumnName == null)
			return null;
		for (int i = 0; i < transRecDTO.outRecsList.size(); i++)
		{
			outRec = transRecDTO.outRecsList.get(i);
			for(int o = 0; o < outRec.columnList.size(); o++)
			{
				colDTO = outRec.columnList.get(o);
				if (colDTO.colStruct.name.equalsIgnoreCase(szOutColumnName))
					return colDTO;
			}
		}

		return null;
	}

	public final static ColumnDTO getSrcTgtColumnDTOByName (SrcTgtInfo srcTgtRec, String szTgtColumn)
	{
		ColumnDTO colDTO;

		if (srcTgtRec == null)
			return null;
		if (szTgtColumn == null)
			return null;
		if (szTgtColumn.trim().length() <= 0)
			return null;

		for(int o = 0; o < srcTgtRec.srcTgtColsList.size(); o++)
		{
			colDTO = srcTgtRec.srcTgtColsList.get(o);
			if (colDTO.colStruct.name.equalsIgnoreCase(szTgtColumn))
				return colDTO;
		}

		return null;
	}

	public static final ColumnDTO getColumnDTOByName (ArrayList<ColumnDTO> columnList, String szTgtColumn)
	{
		ColumnDTO colDTO;

		if (columnList == null || columnList.size() < 1)
			return null;
		if (szTgtColumn == null)
			return null;
		if (szTgtColumn.trim().length() <= 0)
			return null;

		for(int o = 0; o < columnList.size(); o++)
		{
			colDTO = columnList.get(o);
			if (colDTO.colStruct.name.equalsIgnoreCase(szTgtColumn))
				return colDTO;
		}

		return null;
	}	


	public static final ColumnDTO getExprColumnDTOByName (ExprInfo expRec, String szTgtColumn)
	{
		ColumnDTO colDTO;

		if (expRec == null)
			return null;
		if (szTgtColumn == null)
			return null;
		if (szTgtColumn.trim().length() <= 0)
			return null;

		for(int o = 0; o < expRec.colDTOList.size(); o++)
		{
			colDTO = expRec.colDTOList.get(o);
			if (colDTO.colStruct.name.equalsIgnoreCase(szTgtColumn))
				return colDTO;
		}

		return null;
	}

	public static final ColumnDTO getChecksumColumnDTOByName (ChecksumInfo chkRec, String szTgtColumn)
	{
		ColumnDTO colDTO;

		if (chkRec == null)
			return null;
		if (szTgtColumn == null)
			return null;
		if (szTgtColumn.trim().length() <= 0)
			return null;

		for(int o = 0; o < chkRec.colDTOList.size(); o++)
		{
			colDTO = chkRec.colDTOList.get(o);

			if (colDTO.colStruct.name.equalsIgnoreCase(szTgtColumn))
				return colDTO;
		}

		return null;
	}

	public static final ColumnDTO getAggColumnDTOByName (AggInfo aggRec, String szTgtColumn)
	{
		ColumnDTO colDTO;

		if (aggRec == null)
			return null;
		if (szTgtColumn == null)
			return null;
		if (szTgtColumn.trim().length() <= 0)
			return null;

		for(int o = 0; o < aggRec.colDTOList.size(); o++)
		{
			colDTO = aggRec.colDTOList.get(o);

			if (colDTO.colStruct.name.equalsIgnoreCase(szTgtColumn))
				return colDTO;
		}

		return null;
	}

	public static final ColumnDTO getSeqGenColumnDTOByName (SeqGenInfo seqGenRec, String szTgtColumn)
	{
		ColumnDTO colDTO;

		if (seqGenRec == null)
			return null;
		if (szTgtColumn == null)
			return null;
		if (szTgtColumn.trim().length() <= 0)
			return null;

		for(int o = 0; o < seqGenRec.colDTOList.size(); o++)
		{
			colDTO = seqGenRec.colDTOList.get(o);

			if (colDTO.colStruct.name.equalsIgnoreCase(szTgtColumn))
				return colDTO;
		}

		return null;
	}

	public static final ColumnDTO getInputSourceColumnsObj (String columnName, TransRecExtDTO transRec,
			TargetDataElementType tgtDataElem)
	{
		String srcFeedRef = null, srcDataElemRef = null;
		for (int j = 0; j < tgtDataElem.getTransformationRule().size(); j++)
		{
			TransformationRule rule = tgtDataElem.getTransformationRule().get(j);
			int i;

			List<String> inputDataElements = getInputDataElements(rule);
			for (i = 0; i < inputDataElements.size(); i++)
			{
				String srcDataElem = inputDataElements.get(i);
				if (columnName.equalsIgnoreCase (getElemNamefromVar(srcDataElem)))
				{
					srcFeedRef = getCompNamefromVar(srcDataElem);
					srcDataElemRef =getElemNamefromVar(srcDataElem);
					break;
				}
			}
			if (i < inputDataElements.size())
				break;
		}

		ColumnDTO inColDTO = Utils.getColumnDTOByNameInTransRec (transRec, srcFeedRef, srcDataElemRef);
		if (inColDTO == null)
			return null;

		return inColDTO;
	}

	public static final ColumnDTO getInputSourceColumnObj (String columnName, TransRecExtDTO transRec,
			TransformationRule rule, TargetDataElementType tgtDataElem)
	{
		String srcFeedRef = null, srcDataElemRef = null;

		List<String> inputDataElements = getInputDataElements(rule);

		for (int j = 0; j < inputDataElements.size(); j++)
		{
			String srcDataElem = inputDataElements.get(j);
			if (columnName.equalsIgnoreCase (getElemNamefromVar(srcDataElem)))
			{
				srcFeedRef = getCompNamefromVar(srcDataElem);
				srcDataElemRef = getElemNamefromVar(srcDataElem);
				break;
			}
		}

		if (srcFeedRef == null)
		{
			for (int j = 0; j < inputDataElements.size(); j++)
			{
				String srcDataElem = inputDataElements.get(j);
				if (columnName.equalsIgnoreCase (getElemNamefromVar(srcDataElem)))
				{
					srcFeedRef = getCompNamefromVar(srcDataElem);
					srcDataElemRef = getElemNamefromVar(srcDataElem);
					break;
				}
			}
		}

		ColumnDTO inColDTO = Utils.getColumnDTOByNameInTransRec (transRec, srcFeedRef, srcDataElemRef);
		if (inColDTO == null)
		{
			System.out.println("ERROR: ColumnName not found - CompName: " + transRec.compName +
					" TgtElemName:" + tgtDataElem.getTargetDataElementRef() +
					" RuleName:" + rule.getRuleName() + " colName:" + columnName);
			//			lkp_krishna TgtElemName:salary RuleName:Special - Lookup colName:emp_Id
		}

		return inColDTO;
	}

	public static final ColumnDTO getVariablePortColumnFromTransInfo (TransRecExtDTO transRec, TransformationRule rule, TargetDataElementType tgtDataElem)
	{
		String szColName = null;
		ArrayList<ColumnDTO> colDTOList = null;
		//sweta
		if(transRec.transType.equalsIgnoreCase("MD5") && (rule.getRuleName().equalsIgnoreCase("Encoding - MD5 Checksum")))
		{
			colDTOList = ((ChecksumInfo) transRec.transInfo).colDTOList;
		}
		if(transRec.transType.equalsIgnoreCase("Expression") && (!(rule.getRuleName().equalsIgnoreCase("Encoding - MD5 Checksum"))))
		{
			colDTOList = ((ExprInfo) transRec.transInfo).colDTOList;
		}
		if(transRec.transType.equalsIgnoreCase("Aggregator"))
		{
			colDTOList = ((AggInfo) transRec.transInfo).colDTOList;
		}
		if(transRec.transType.equalsIgnoreCase("Sequence Generator"))
		{
			colDTOList = ((SeqGenInfo) transRec.transInfo).colDTOList;
		}
		/*if(transRec.transType.equalsIgnoreCase("Sorter"))
		{
			colDTOList = ((SorterInfo) transRec.transInfo).colDTOList;
		}
		if(transRec.transType.equalsIgnoreCase("Duplicates"))
		{
			colDTOList = ((RemoveDupInfo) transRec.transInfo).colDTOList;
		}*/

		if (colDTOList == null)
			return null;

		//SeqGenInfo transInfo = (SeqGenInfo) transRec.transInfo;
		szColName = tgtDataElem.getTargetDataElementRef();
		//		if (rule.getInterimOutDataElement().size() > 0)
		//			szColName = rule.getInterimOutDataElement().get(0).getName();
		for (int i = 0; i < colDTOList.size(); i++)
		{
			String name = (colDTOList.get(i).colStruct.name);

			String x[] = name.split("\\.");

			if (szColName.equalsIgnoreCase(x[x.length-1]))
				return colDTOList.get(i);
		}

		return null;
	}

	public final static ColumnDTO setIsBeingUsed (TransRecExtDTO transRec, ColumnDTO tgtColDTO)
	{
		for (int k = 0; k < transRec.inRecsList.size(); k++)
		{
			InRecDTO inRec = transRec.inRecsList.get(k);
			for (int l = 0; l < inRec.prevOutRec.columnList.size(); l++)
			{
				ColumnDTO colDTO=inRec.prevOutRec.columnList.get(l);
				if((colDTO.colStruct.name.substring(colDTO.colStruct.name.indexOf(".")+1)).equalsIgnoreCase(tgtColDTO.colStruct.name))
					tgtColDTO.isBeingUsed = 'N';
				else
					tgtColDTO.isBeingUsed = 'Y';
			}
		}
		return tgtColDTO;
	}

	public final static ColumnDTO getColDetails (Connector connector, String srcColName, String srcRefName, TargetDataElementType tgtDataElem, ColumnDTO colDTO)
	{
		List<SystemCatalog> srcSysCatalogList = connector.getSystemCatalogs().getSystemCatalog();
		SystemEntity  sysEntity = null;

		for(int j = 0; j < srcSysCatalogList.size(); j++)
		{
			for(int k = 0; k < srcSysCatalogList.get(j).getSystemEntities().getSystemEntity().size(); k++ )
			{
				sysEntity = srcSysCatalogList.get(j).getSystemEntities().getSystemEntity().get(k);
				if (sysEntity.getName().equalsIgnoreCase(srcRefName))
				{
					for(int i = 0; i < sysEntity.getRelationalDataElements().getDataElement().size(); i++)
					{
						DataElement dataelement = sysEntity.getRelationalDataElements().getDataElement().get(i);

						if (!srcColName.equalsIgnoreCase(dataelement.getName()))
							continue;

						if (srcColName.equalsIgnoreCase(dataelement.getName()))
						{
							if(dataelement.getDatatype() !=null)
								colDTO.colStruct.dataType = dataelement.getDatatype();

							if(dataelement.getLength() !=null)
								colDTO.colStruct.length = dataelement.getLength();

							if(dataelement.getPrecision() !=null)
								colDTO.colStruct.precision = dataelement.getPrecision();

							if(dataelement.getScale() !=null)
								colDTO.colStruct.Scale = dataelement.getScale();
							
							if(dataelement.getDataFormat() !=null)
								colDTO.colStruct.dateFormat = dataelement.getDataFormat();

							return colDTO;

						}

					}
				}
				else
				{
					if(tgtDataElem.getDataType() != null)
						colDTO.colStruct.dataType = tgtDataElem.getDataType();

					//					if(dataelement.getLength() !=null)
					//						colDTO.colStruct.length = tgtDataElem.get

					if(tgtDataElem.getPrecision() !=null)
						colDTO.colStruct.precision = tgtDataElem.getPrecision();

					if(tgtDataElem.getScale() !=null)
						colDTO.colStruct.Scale = tgtDataElem.getScale();

					return colDTO;
				}
			}
		}

		return colDTO;
	}

	public static final ColumnDTO getInputSourceColumnObjAgg (String columnName, TransRecExtDTO transRec,
			TransformationRule rule, TargetDataElementType tgtDataElem)
	{
		String srcFeedRef = null, srcDataElemRef = null;

		List<String> inputDataElements = getInputDataElements(rule);
		for (int j = 0; j < inputDataElements.size(); j++)
		{
			String srcDataElem = inputDataElements.get(j);
			if (columnName.equalsIgnoreCase (getElemNamefromVar(srcDataElem)))
			{
				srcFeedRef = getCompNamefromVar(srcDataElem);
				srcDataElemRef = getElemNamefromVar(srcDataElem);
				break;
			}
		}

		ColumnDTO inColDTO = null;
		for (int i = 0; i < transRec.transColsList.size(); i++)
		{
			inColDTO = transRec.transColsList.get(i);
			if ((inColDTO.colStruct.name.substring(inColDTO.colStruct.name.indexOf(".")+1, inColDTO.colStruct.name.length()).equalsIgnoreCase(srcDataElemRef)))
			{
				return inColDTO;
			}
		}

		if (inColDTO == null)
		{
			System.out.println("ERROR: ColumnName not found - CompName: " + transRec.compName +
					" TgtElemName:" + tgtDataElem.getTargetDataElementRef() +
					" RuleName:" + rule.getRuleName() + " colName:" + columnName);
		}

		return inColDTO;
	}

	public static String getNewComponentName(String compName, ArrayList<TransRecExtDTO> recsList)
	{
		int i = 1;
		String szNewCompName = compName + i;
		TransRecExtDTO transRec = null;

		while (true)
		{
			transRec = Utils.getTransRecDTOByName(recsList, szNewCompName);
			if (transRec == null)
				break;
			i++;
			szNewCompName = compName + i;
		}

		return szNewCompName;
	}


	public static final List<DataTransformation> getOtherTransList (DataMapping dataMapping)
	{
		List<DataTransformation> otherTransList = new ArrayList<DataTransformation>();

		for (DataTransformation dt : dataMapping.getDataTransformations().getDataTransformation())
		{
			if(dt.getTransformationType().equalsIgnoreCase("Other Transformation"))
			{
				otherTransList.add(dt);
			}
		}

		return otherTransList;
	}


	public static final void updateOtherTransInDataMapping (DataMapping dataMapping, List<DataTransformation> otherTransList)
	{
		if (otherTransList.size() <= 0)
			return;

		List<String> otherTransNames = new ArrayList<String>();

		for (int i = 0; i < otherTransList.size(); i++)
		{
			otherTransNames.add(otherTransList.get(i).getName().toLowerCase());
		}

		for (int i = 0; i < dataMapping.getTargetDataElements().getTargetDataElement().size(); i++)
		{
			if (otherTransNames.contains(dataMapping.getTargetDataElements().getTargetDataElement().get(i).getTargetDatsetRef().toLowerCase()))
			{
				String name = "tgtInput_"+dataMapping.getTargetDataElements().getTargetDataElement().get(i).getTargetDatsetRef();
				dataMapping.getTargetDataElements().getTargetDataElement().get(i).setTargetDatsetRef(name);
			}
		}

		List<DataTransformation> mapTrans = dataMapping.getDataTransformations().getDataTransformation();
		List<DataTransformation> dtList = new ArrayList<DataTransformation>();
		for (int i = 0; i < otherTransList.size(); i++)
		{
			DataTransformation dt = otherTransList.get(i);

			String orgName = dt.getName();
			String updname = "original_"+ dt.getName();
			String tgtname = "tgtInput_"+ orgName;

			if (dt.getOtherTransformation().getOutputDataElements() == null)
				continue;

			if (dt.getOtherTransformation().getOutputDataElements().getOutputDataElement().size() > 0)
			{
				DataTransformation dtnew1 = new DataTransformation();
				dtnew1.setName(orgName);
				dtnew1.setTransformationType("Source Definition");
				if(dt.getNextComponentRef() != null)
					dtnew1.setNextComponentRef(dt.getNextComponentRef());

				SourceDataset sd = new SourceDataset();
				sd.setSystemCatalogRef("Other Transformation");
				sd.setSystemEntityRef(updname);

				dtnew1.setSourceDataset(sd);

				mapTrans.add(dtnew1);
			}

			DataTransformation dtnew2 = new DataTransformation();
			dtnew2.setName(tgtname);
			dtnew2.setTransformationType("Target Definition");
			if(dt.getPrevComponentRef() != null)
			{
				dtnew2.setPrevComponentRef(dt.getPrevComponentRef());
				
				String [] str = dt.getPrevComponentRef().split(";");
				
				DataTransformation dt_prev = null;
				String nextComponet = "";
				String nextOutComponet = "";
				String value = "";
				for (int j = 0; j < str.length; j++)
				{
					value = str[j].trim();
					if (value.contains("."))
						value = value.split("\\.")[0].trim();
					
					if (value.equalsIgnoreCase(""))
						continue;
					
					for (int j2 = 0; j2 < dataMapping.getDataTransformations().getDataTransformation().size(); j2++)
					{
						dt_prev = dataMapping.getDataTransformations().getDataTransformation().get(j2);
						
						if (!dt_prev.getName().equalsIgnoreCase(value))
							continue;
						
						if (dt_prev.getNextComponentRef() == null)
							continue;
						
						nextComponet = dt_prev.getNextComponentRef().replaceAll("(?i)\\b"+orgName+"\\b", tgtname);
						dt_prev.setNextComponentRef(nextComponet);
						
						if (dt_prev.getTransformationType().equalsIgnoreCase("Conditional Grouping"))
						{
							for (int k = 0; k < dt_prev.getConditionalGrouping().getOutputGroup().size(); k++)
							{
								if (dt_prev.getConditionalGrouping().getOutputGroup().get(k).getNextComponentRef() == null)
									continue;
								nextOutComponet = dt_prev.getConditionalGrouping().getOutputGroup().get(k).getNextComponentRef().replaceAll("(?i)\\b"+orgName+"\\b", tgtname);
								dt_prev.getConditionalGrouping().getOutputGroup().get(k).setNextComponentRef(nextOutComponet);
								
							}
						}
					}
				}
			}
				

			TargetDataset td = new TargetDataset();
			td.setSystemCatalogRef("Other Transformation");
			td.setSystemEntityRef(updname);

			dtnew2.setTargetDataset(td);
			if(dt.getRejectOption() != null)
			{
				dtnew2.setRejectOption(dt.getRejectOption());
			}
			mapTrans.add(dtnew2);

			dt.setName(updname);
		}
	}

	public static final void removeDataMappingUpdates (DataMapping dataMapping, List<DataTransformation> otherTransList)
	{
		for (int i = 0; i < dataMapping.getTargetDataElements().getTargetDataElement().size(); i++)
		{
			if(dataMapping.getTargetDataElements().getTargetDataElement().get(i).getTargetDatsetRef().startsWith("tgtInput_"))
			{
				String name = dataMapping.getTargetDataElements().getTargetDataElement().get(i).getTargetDatsetRef().replace("tgtInput_", "");
				dataMapping.getTargetDataElements().getTargetDataElement().get(i).setTargetDatsetRef(name);
			}
		}

		for (int i = 0; i < dataMapping.getDataTransformations().getDataTransformation().size(); i++)
		{
			DataTransformation dt = dataMapping.getDataTransformations().getDataTransformation().get(i);

			if(dt.getTransformationType().equalsIgnoreCase("Source Definition"))
			{
				if (dt.getSourceDataset().getSystemCatalogRef().equalsIgnoreCase("Other Transformation"))
				{
					dataMapping.getDataTransformations().getDataTransformation().remove(i);
					i--;
				}
			}
			else if (dt.getTransformationType().equalsIgnoreCase("Target Definition"))
			{
				if (dt.getTargetDataset().getSystemCatalogRef().equalsIgnoreCase("Other Transformation"))
				{
					dataMapping.getDataTransformations().getDataTransformation().remove(i);
					i--;
				}
			}
			else if (dt.getTransformationType().equalsIgnoreCase("Other Transformation"))
			{
				String name = dt.getName().replace("original_", "");
				dt.setName(name);
			}
		}
	}


	public static final void combineTransRecObj (List<TransRecExtDTO> recsList)
	{

		ArrayList<String> rejectList = new ArrayList<String>();

		TransRecExtDTO origTransRec = null;

		for (int i = 0; i < recsList.size(); i++)
		{
			origTransRec = recsList.get(i);

			if (!(origTransRec.transType.equalsIgnoreCase("Other Transformation") && origTransRec.compName.startsWith("original_")))
				continue;


			String compName = origTransRec.compName.replace("original_", "");
			TransRecExtDTO transRec = Utils.getTransRecDTOByName(recsList, compName);

			boolean check = false;
			if (transRec == null)
			{
				transRec = new TransRecExtDTO();
				transRec.compName = compName;

				check = true;

			}

			String tgtcompName = "tgtInput_"+compName;
			TransRecExtDTO inRec = Utils.getTransRecDTOByName(recsList, tgtcompName);

			if (inRec != null)
			{
				transRec.inRecsList = inRec.inRecsList;
				transRec.prevCompList = inRec.prevCompList;
				transRec.previousComp = inRec.previousComp;
				transRec.transColsList = inRec.transColsList;
				if (check)
					transRec.transInfo = inRec.transInfo;
			}


			transRec.transType = origTransRec.transType;
			//transRec.transInfo = origTransRec.transInfo;
			transRec.identifier = origTransRec.identifier;
			transRec.seqNo = origTransRec.seqNo;

			recsList.remove(i); // Remove Original Record
			i--;

			for (int j = 0; j < transRec.inRecsList.size(); j++)
			{
				transRec.inRecsList.get(j).parentTransRec = transRec;
			}

			for (int j = 0; j < transRec.prevCompList.size(); j++)
			{
				List<TransRecExtDTO> nextList = transRec.prevCompList.get(j).nextCompList;
				boolean bRemoved = false;
				for (int k = 0; k < nextList.size(); k++)
				{
					if (inRec.compName.equalsIgnoreCase (nextList.get(k).compName))
					{
						nextList.remove(k);
						k--;
						bRemoved = true;
					}
				}
				if (bRemoved)
					nextList.add(transRec);
			}

			// Remove the inRec from RecsList

			if (inRec != null)
			{
				for (int j = 0; j < recsList.size(); j++)
				{
					if (inRec.compName.equalsIgnoreCase (recsList.get(j).compName))
					{
						recsList.remove(j); // Remove inRec Record
						j--;
						i--;
						break;
					}
				}
			}

			if (check)
			{
				recsList.add(transRec);
				i++;
			}
		}
	}



	public static final void updateLookupTransInDataMapping (DataMapping dataMapping)
	{
		for (int i = 0; i < dataMapping.getTargetDataElements().getTargetDataElement().size(); i++)
		{
			TargetDataElementType tde = dataMapping.getTargetDataElements().getTargetDataElement().get(i);
			if (tde.getTransformationRule().size() > 0)
			{
				for (int j = 0; j < tde.getTransformationRule().size(); j++)
				{
					if (tde.getTransformationRule().get(j).getRuleName().equalsIgnoreCase("Special - Lookup"))
					{
						String input = "";
						List<String> inputDataElements = getInputDataElements(tde.getTransformationRule().get(j));

						for (int j2 = 0; j2 < inputDataElements.size(); j2++)
						{
							if (j2 > 0)
							{
								input = input+","+inputDataElements.get(j2);
							}
							else
							{
								input = inputDataElements.get(j2);
							}
						}

						String value = ":LKP."+tde.getTransformationRule().get(j).getParameter().get(0).getValue()+"("+input+")";
						Parameter param = new Parameter ();
						param.setName("Expression");
						param.setValue(value);
						param.setType("Exp");
						tde.getTransformationRule().get(j).getParameter().clear();
						tde.getTransformationRule().get(j).getParameter().add(param);
						tde.getTransformationRule().get(j).setRuleName("General - Expression");
					}
				}
			}
		}
	}

	public static final int getToolTypeConverterToolID (String szDBToolType)
	{
		//System.out.println(" szDBToolType   :: " + szDBToolType);
		if (szDBToolType.equalsIgnoreCase("Relational - Oracle"))
			return ToolTypeConstants.ORACLE;
		if (szDBToolType.equalsIgnoreCase("Relational - SQL Server"))
			return ToolTypeConstants.SQLSERVER;
		if (szDBToolType.equalsIgnoreCase("Relational - Netezza"))
			return ToolTypeConstants.NETEZZA;
		if (szDBToolType.equalsIgnoreCase("Relational - Teradata"))
			return ToolTypeConstants.TERADATA;
		if (szDBToolType.equalsIgnoreCase("Relational - Amazon Redshift"))
			return ToolTypeConstants.REDSHIFT;
		if (szDBToolType.equalsIgnoreCase("Relational - ODBC"))
			return ToolTypeConstants.ODBC;
		if (szDBToolType.equalsIgnoreCase("Files - Delimited"))
			return ToolTypeConstants.FLATFILE;
		if (szDBToolType.equalsIgnoreCase("Files - Fixed Width"))
			return ToolTypeConstants.FLATFILE;
		if (szDBToolType.equalsIgnoreCase("Relational - DB2 LUW"))
			return ToolTypeConstants.DB2;
		if (szDBToolType.equalsIgnoreCase("Relational - Snowflake DW"))
			return ToolTypeConstants.SNOWFLAKE;		
		if (szDBToolType.equalsIgnoreCase("Snowflake - Cloud"))
			return ToolTypeConstants.SNOWFLAKECLOUD;		
		if (szDBToolType.equalsIgnoreCase("Salesforce - DB"))
			return ToolTypeConstants.SALESFORCE;		
		if (szDBToolType.equalsIgnoreCase("BigQuery"))
			return ToolTypeConstants.BIGQUERY;
		if (szDBToolType.equalsIgnoreCase("Hadoop - HDFS"))
			return ToolTypeConstants.HADOOPHDFS;		
		if (szDBToolType.equalsIgnoreCase("Hadoop - Hive"))
			return ToolTypeConstants.HADOOPHIVE;		
		if (szDBToolType.equalsIgnoreCase("Impala"))
			return ToolTypeConstants.IMPALA;		
		if (szDBToolType.equalsIgnoreCase("Relational - Sybase"))
			return ToolTypeConstants.SYBASE;
		if (szDBToolType.equalsIgnoreCase("Relational - MySQL"))
			return ToolTypeConstants.MYSQL;
		if (szDBToolType.equalsIgnoreCase("MongoDb"))
			return ToolTypeConstants.MONGODB;
		if (szDBToolType.equalsIgnoreCase("Relational - Azure SQL DB") || szDBToolType.equalsIgnoreCase("Relational - Azure SQL Data Warehouse"))
			return ToolTypeConstants.AZURESQL;
		if (szDBToolType.equalsIgnoreCase(""))
			return ToolTypeConstants.AURORA;
		if (szDBToolType.equalsIgnoreCase(""))
			return ToolTypeConstants.JAVA ;
		if (szDBToolType.equalsIgnoreCase("Other Transformation"))
			return ToolTypeConstants.DS_TOOL;
		if (szDBToolType.equalsIgnoreCase("DS_TOOL"))
			return ToolTypeConstants.DS_TOOL;


		// ToDO Utham - For all other db type constants

		return -1;
	}

	public static final void changeToToolSpecificDataType (int codeGenToolId, String srcDBType, ColumnDTO column)
	{
		int srcToolID = Utils.getToolTypeConverterToolID (srcDBType);
		if (srcToolID < 0)
			srcToolID = codeGenToolId;
		HashMap<String, String> hm = new HashMapCaseInsensitive();
		hm = DataTypeConvertor.getSourceTargetTypeMaps (srcToolID, codeGenToolId);
		column.colStruct.dataType = hm.get(column.colStruct.dataType);

	}

	public static final void changeToToolSpecificDataType (int codeGenToolId, String srcDBType,
			ArrayList<ColumnDTO> colsList)
	{
		int srcToolID = Utils.getToolTypeConverterToolID (srcDBType);
		if (srcToolID < 0)
			srcToolID = codeGenToolId;
		HashMap<String, String> hm = new HashMapCaseInsensitive();
		hm = DataTypeConvertor.getSourceTargetTypeMaps (srcToolID, codeGenToolId);

		hm = DataTypeConvertor.getSourceTargetTypeMaps (srcToolID, codeGenToolId);

		for (int i = 0; i < colsList.size(); i++)
		{
			ColumnDTO col = colsList.get(i);
			col.colStruct.dataType = hm.get(col.colStruct.dataType);

		}
	}

	public static void getOtherTransPropDtls(SrcTgtInfo transRec, String additionalProperties) 
	{
		try 
		{
			JSONObject transProps = (JSONObject) OtherTransParser.getTransDtlsForOtherTrans(additionalProperties);
			Set<String> keys = transProps.keySet();
			Iterator<String> iterator = keys.iterator();
			while(iterator.hasNext())
			{
				String key = iterator.next();
				String value = (String) transProps.get(key);
				String addProps =  key +" : " +value;
				transRec.transDtls.add(addProps);
			}

		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
	}

	public static void fillReportLog(DataMapping dataMappingObj,ReportLogInfo reportObj)
	{

		reportObj.jobName = dataMappingObj.getName();
		reportObj.jobType = "Mapping";
		int totalEntityTransCount = 0;
		totalEntityTransCount = dataMappingObj.getDataTransformations().getDataTransformation().size();
		int totalAttributeTransCount = 0;

		HashMap<String, String> sqlMap = new HashMap<String,String>();

		for (int inner_Loop = 0; inner_Loop < dataMappingObj.getDataTransformations().getDataTransformation().size(); inner_Loop++) 
		{
			DataTransformation dataTransformations = dataMappingObj.getDataTransformations().getDataTransformation().get(inner_Loop);
			if(dataTransformations.getTransformationType().equalsIgnoreCase("Source Definition") || dataTransformations.getTransformationType().equalsIgnoreCase("Target Definition") 
					|| dataTransformations.getTransformationType().equalsIgnoreCase("Lookup"))
			{

				addsqlTranformations(dataTransformations,sqlMap);


			}
		}

		reportObj.totalsqlTranCount = sqlMap.size();
		reportObj.totalEntityTransCount = totalEntityTransCount;

		for (int inner_Loop = 0; inner_Loop < dataMappingObj.getTargetDataElements().getTargetDataElement().size(); inner_Loop++) 
		{
			TargetDataElementType targetDataElementObj = dataMappingObj.getTargetDataElements().getTargetDataElement().get(inner_Loop);
			if (targetDataElementObj.getTransformationRule() != null && !targetDataElementObj.getTransformationRule().isEmpty()) 
			{
				if(!targetDataElementObj.getTransformationRule().get(0).getRuleName().equalsIgnoreCase("Special - Union") && !targetDataElementObj.getTransformationRule().get(0).getRuleName().equalsIgnoreCase("General  - Constant"))
				totalAttributeTransCount=totalAttributeTransCount+targetDataElementObj.getTransformationRule().size();
			}
		}

		reportObj.totalAttributeTransCount = totalAttributeTransCount;

		int totalTransformations = (totalEntityTransCount+totalAttributeTransCount+sqlMap.size());

		reportObj.totalTransCount = totalTransformations;
		String type_job = "";

		if (totalTransformations <= 5) 
			type_job = "Very Simple";

		else if (totalTransformations > 5 && totalTransformations <= 10) 
			type_job = "Simple";

		else if (totalTransformations > 10 && totalTransformations <= 25) 
			type_job = "Medium";

		else if (totalTransformations > 25 && totalTransformations <= 50) 
			type_job = "Complex";

		else 
			type_job = "Very Complex";

		reportObj.complexityType=type_job;

	}

	private static void addsqlTranformations(DataTransformation dataTransformations,
			HashMap<String, String> sqlMap) 
	{
		// TODO Auto-generated method stub

		if(dataTransformations.getTransformationType().equalsIgnoreCase("Source Definition"))
		{
			SourceDataset sdt = dataTransformations.getSourceDataset();
			String key = sdt.getSystemEntityRef();

			if(sdt.getSqlQuery() != null && sdt.getSqlQuery() != "")
			{
				if(sdt.getSqlQuery().contains("ds"))
					sqlMap.put(key+"_SQLQuery", sdt.getSqlQuery());
			}

			if(sdt.getFilterCondition() != null)
			{
				if(sdt.getFilterCondition().getValue().contains("ds"))
					sqlMap.put(key+"_FilterCondition", sdt.getFilterCondition().getValue());
			}

			if(sdt.getPreSQL() != null && sdt.getPreSQL() != "")
			{
				if(sdt.getPreSQL().contains("ds"))
					sqlMap.put(key+"_PreSQL", sdt.getPreSQL());
			}
			if(sdt.getPostSQL() != null && sdt.getPostSQL() != "")
			{
				if(sdt.getPostSQL().contains("ds"))
					sqlMap.put(key+"_PostSQL", sdt.getPostSQL());

			}

			if(sdt.getSourceFeedJoin() != null)
			{
				if(sdt.getSourceFeedJoin().getJoinCondition().getValue().contains("ds"))
					sqlMap.put(key+"_JoinCondition", sdt.getSourceFeedJoin().getJoinCondition().getValue());
			}
		}
		if(dataTransformations.getTransformationType().equalsIgnoreCase("Target Definition"))
		{
			TargetDataLoadOptions tgtloadOptions = dataTransformations.getTargetDataset().getTargetDataLoadOptions();

			String key = dataTransformations.getTargetDataset().getName();

			if(tgtloadOptions.getUpdateOverrideSQL() != null && tgtloadOptions.getUpdateOverrideSQL() != "")
			{
				if(tgtloadOptions.getUpdateOverrideSQL().contains("ds"))

					sqlMap.put(key+"_UpdateOverrideSQL", tgtloadOptions.getUpdateOverrideSQL());
			}
			if(tgtloadOptions.getPostSQL() != null && tgtloadOptions.getPostSQL() != "")
			{
				if( tgtloadOptions.getPostSQL().contains("ds"))
					sqlMap.put(key+"_PostSQL", tgtloadOptions.getPostSQL());

			}
			if(tgtloadOptions.getPreSQL() != null && tgtloadOptions.getPreSQL() != "")
			{
				if( tgtloadOptions.getPreSQL().contains("ds"))
					sqlMap.put(key+"_PreSQL", tgtloadOptions.getPreSQL());

			}
		}

		if(dataTransformations.getTransformationType().equalsIgnoreCase("Lookup"))
		{
			for(LookupSource ls : dataTransformations.getLookup().getLookupSource()){
				String key = ls.getLkpSourceComponentRef();

				if(ls.getLookupOverrideSQL() != null && ls.getLookupOverrideSQL() != "")
				{
					if(ls.getLookupOverrideSQL().contains("ds"))
						sqlMap.put(key+"_SQLQuery", ls.getLookupOverrideSQL());
				}

				if(ls.getLookupSourceFilterSQL() != null)
				{
					if(ls.getLookupSourceFilterSQL().contains("ds"))
						sqlMap.put(key+"_FilterCondition", ls.getLookupSourceFilterSQL());
				}
			}
		}
	}

	public static void fillReportLogProcessflow(ProcessFlow pf,ReportLogInfo reportObj)
	{

		reportObj.jobName=pf.getName();
		reportObj.jobType="Workflow";
		int taskCount = 0;
		if (pf.getSteps() != null)
		{
			taskCount = pf.getSteps().getStep().size();
			int filterCount = 0;
			for (Step step : pf.getSteps().getStep()) 
			{
				if (step.getStepType().equalsIgnoreCase("Filter Task"))
					filterCount++;
			}

			taskCount = taskCount - filterCount;
		}
		reportObj.totalTransCount=taskCount;

		String type_job = "";
		if (taskCount <= 5) 
			type_job = "Very Simple";
		else if (taskCount > 5 && taskCount <= 10) 
			type_job = "Simple";
		else if (taskCount > 10 && taskCount <= 15)
			type_job = "Medium";
		else if (taskCount > 15 && taskCount <= 25) 
			type_job = "Complex";
		else 
			type_job = "Very Complex";
		reportObj.complexityType=type_job;
	}

	public static void printReportLog(ReportLogInfo reportObj,Connector docspecs,String outputPath,Console console) 
	{

		FileWriter fw = null;
		String content = "";
		try {

			fw = new FileWriter(outputPath+"Report_Log.txt",true);	
			if(reportObj.otherReportDtls != null && reportObj.otherReportDtls !="")
			{
				fw.write(reportObj.otherReportDtls+"\n");
			}

			content="---------------------------------------------------\nJob Summary: "+reportObj.jobName+"\n---------------------------------------------------";			
			fw.write(content+"\n");
			content=reportObj.jobName;			
			console.put("Message", "Mapping Name - " + content);
			fw.write("Mapping Name - " +content+"\n");

			content= Integer.toString(reportObj.totalEntityTransCount);
			console.put("Message", "Number of Entity Transformation - " + content);			
			fw.write( "Number of Entity Transformation - " +content+"\n");

			content= Integer.toString(reportObj.totalAttributeTransCount);
			console.put("Message", "Number of  Attribute Transformation Rules - " + content);
			fw.write("Number of  Attribute Transformation Rules - "+content+"\n");

			content= Integer.toString(reportObj.totalsqlTranCount);
			console.put("Message", "Total Number Of SQL Queries to be changed manually - " + content);
			fw.write("Total Number Of SQL Queries to be changed manually - "+content+"\n");

			content= Integer.toString(reportObj.totalTransCount);
			console.put("Message", "Total Number Of Transformations - " + content);
			fw.write("Total Number Of Transformations - "+content+"\n");

			content= reportObj.complexityType;
			console.put("Message", "Complexity Type - " + content);
			fw.write("Complexity Type - "+content+"\n");



			HashMap<String, String> sqlMap = new HashMap<String,String>();

			for (int i = 0; i < docspecs.getDataMappings().getDataMapping().size(); i++)
			{
				DataMapping dataMappingObj = docspecs.getDataMappings().getDataMapping().get(i);

				if(reportObj.jobName.equalsIgnoreCase(dataMappingObj.getName()))
				{
					for (int inner_Loop = 0; inner_Loop < dataMappingObj.getDataTransformations().getDataTransformation().size(); inner_Loop++) 
					{
						DataTransformation dataTransformations = dataMappingObj.getDataTransformations().getDataTransformation().get(inner_Loop);
						if(dataTransformations.getTransformationType().equalsIgnoreCase("Source Definition") || dataTransformations.getTransformationType().equalsIgnoreCase("Target Definition") 
								|| dataTransformations.getTransformationType().equalsIgnoreCase("Lookup"))						
						{
							addsqlTranformations(dataTransformations,sqlMap);
						}
					}

				}
			}




			int manualCount=0;

			/*	for (Entry<String, String> entry : reportObj.transTypeList.entrySet()) 
			{
				if(!entry.getValue().equalsIgnoreCase("Expression"))
				{
					manualCount=manualCount+1;
				}

			}*/
			for (Entry<String, ArrayList<String>> entry : reportObj.manualChanges.entrySet()) 
			{	            
				ArrayList<String> values = entry.getValue();
				if(values.size()>0)
				{
					manualCount=manualCount+values.size();
					/*for(int i=0;i<values.size();i++)
					{
						//if(values.get(i).contains(":"))				
							manualCount=manualCount+1;
					}*/
				}
			}

			manualCount = manualCount+ sqlMap.size();

			float automationPercentage=(((reportObj.totalTransCount - manualCount) * 100) / reportObj.totalTransCount);
			content= String.valueOf(automationPercentage);
			console.put("Message", "Automation Percentage - " + content);
			fw.write("Automation Percentage - " +content+"\n");
			content= Integer.toString(manualCount);
			console.put("Message", "Number Of Transformations to be changed manually - " + content);
			fw.write("Number Of Transformations to be changed manually - "+content+"\n");			
			if(automationPercentage !=100)
			{
				content="---------------------------------------------------\nTransformations Summary\n---------------------------------------------------";			
				fw.write(content+"\n");
				int loop=1;
				for (Entry<String, ArrayList<String>> manualChangesList : reportObj.manualChanges.entrySet()) 
				{
					String transName = manualChangesList.getKey();
					String transType = gettranstype(reportObj,transName);

					ArrayList<String> values = manualChangesList.getValue();
					if(values.size() > 0)
					{
						content=loop+". Transformation Name : "+transName+"\t"+"Transformation Type :"+transType;
						fw.write(content+"\n");
					}

					int innerloop=0;
					for(int i=0;i<values.size();i++)
					{
						innerloop=(i+1);
						if(values.get(i).contains(":"))
						{
							String colName = values.get(i).substring(0, values.get(i).indexOf(":"));
							String colRule = values.get(i).substring(values.get(i).indexOf(":")+1);
							content="\t"+loop+"."+innerloop+". Variable Name : "+colName+"\t Rule : "+colRule;
							fw.write(content+"\n");
						}

					}
					if(values.size()>0)
					{
						loop++;
					}

				}
			}

			if(sqlMap.size() > 0)
			{
				content="---------------------------------------------------\nSQL Query Transformations Which needs Manual Update\n---------------------------------------------------";			
				fw.write(content+"\n");
				for (String keyvalue : sqlMap.keySet()) 
				{
					content="\t"+sqlMap.get(keyvalue);
					fw.write(content+"\n");
				}
			}

			if(reportObj.codesnippet.size()>0)
			{
				content="---------------------------------------------------\nCode Snippet Which needs Manual Update\n---------------------------------------------------";			
				fw.write(content+"\n");
				for(int i=0;i<reportObj.codesnippet.size();i++)
				{
					content="\t"+reportObj.codesnippet.get(i);
					fw.write(content+"\n");

				}
			}

			content="---------------------------------------------------**************End of Mapping**************---------------------------------------------------";	
			fw.write(content+"\n"); 
		}
		catch (IOException e) {

			e.printStackTrace();

		} finally {

			try 
			{

				if (fw != null)
					fw.close();

			} catch (IOException ex) 
			{

				ex.printStackTrace();

			}
		}
	}

	public static void printReportLogWorkflow(ReportLogInfo reportObj,String outputPath,Console console) 
	{

		FileWriter fw = null;
		String content = "";
		try {

			fw = new FileWriter(outputPath+"Report_Log.txt",true);	
			if(reportObj.otherReportDtls != null && reportObj.otherReportDtls !="")
			{
				fw.write(reportObj.otherReportDtls+"\n");
			}

			content="---------------------------------------------------\nJob Summary: "+reportObj.jobName+"\n---------------------------------------------------";			
			fw.write(content+"\n");
			content=reportObj.jobName;

			console.put("Message", "Workflow Name - " + content);
			fw.write("Workflow Name - " +content+"\n");
			content= Integer.toString(reportObj.totalTransCount);
			console.put("Message", "Total Number of Tasks - " + content);
			fw.write( "Total Number of Tasks - " +content+"\n");

			content= reportObj.complexityType;
			console.put("Message", "Complexity Type - " + content);
			fw.write("Complexity Type - "+content+"\n");
			int manualCount=0;


			for (Entry<String, ArrayList<String>> entry : reportObj.manualChanges.entrySet()) 
			{	            
				ArrayList<String> values = entry.getValue();
				if(values.size()>0)
				{
					manualCount=manualCount+values.size();

				}
			}

			float automationPercentage=(((reportObj.totalTransCount - manualCount) * 100) / reportObj.totalTransCount);
			content= String.valueOf(automationPercentage);
			console.put("Message", "Automation Percentage - " + content);
			fw.write("Automation Percentage - " +content+"\n");

			content= Integer.toString(manualCount);
			console.put("Message", "Number Of Tasks to be changed manually - " + content);
			fw.write("Number Of Tasks to be changed manually - "+content+"\n");			
			if(manualCount > 0)
			{
				content="---------------------------------------------------\nTasks Summary\n---------------------------------------------------";			
				fw.write(content+"\n");
				int loop=1;
				for (Entry<String, ArrayList<String>> manualChangesList : reportObj.manualChanges.entrySet()) 
				{
					String transName = manualChangesList.getKey();
					String transType = gettranstype(reportObj,transName);
					ArrayList<String> values = manualChangesList.getValue();
					if(values.size()>0)
					{
						content=loop+". Task Name : "+transName+"\t"+"Task Type : "+transType;
						fw.write(content+"\n");
						loop++;
					}
				}
			}
			content="---------------------------------------------------**************End of Workflow**************---------------------------------------------------";	
			fw.write(content+"\n"); 
		}
		catch (IOException e) {

			e.printStackTrace();

		} finally {

			try 
			{

				if (fw != null)
					fw.close();

			} catch (IOException ex) 
			{

				ex.printStackTrace();

			}
		}
	}


	private static String gettranstype(ReportLogInfo reportObj, String transName) 
	{
		// TODO Auto-generated method stub
		for (Entry<String, String> list : reportObj.transTypeList.entrySet()) 
		{
			if(list.getKey().equalsIgnoreCase(transName))
				return list.getValue();
		}
		return null;
	}

	public static void readingSpecialProp(Map<String, ArrayList<String>> specProp, DataTransformation dtTrans) 
	{
		// TODO Auto-generated method stub



		try 
		{
			ArrayList<String> specialPropList = new ArrayList<String>();

			String dtname = dtTrans.getName();


			JSONObject transProps = (JSONObject) OtherTransParser.getTransDtlsForOtherTrans(dtTrans.getSpecialProperties());

			Set<String> keys = transProps.keySet();
			Iterator<String> iterator = keys.iterator();
			while(iterator.hasNext())
			{
				String key = iterator.next();
				String value = (String) transProps.get(key);
				String addProps =  key +" : " +value;
				specialPropList.add(addProps);
			}
			specProp.put(dtname, specialPropList);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
	}

	public static List<String> getInputDataElements(TransformationRule rule){
		List<String> inputDataElements = new ArrayList<String>();

		for(Parameter param : rule.getParameter()){
			if(param.getType().equalsIgnoreCase("DE") || param.getType().equalsIgnoreCase("Exp")){
				try{
					ArrayList<Token> alltokens = Lexer.interpret(param.getValue());

					for (int i = 0; i < alltokens.size(); i++) {
						if(alltokens.get(i).type.name().equalsIgnoreCase("VARWITHDOT")){
							inputDataElements.add(alltokens.get(i).data.toString());
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return inputDataElements;
	}

	public static String getCompNamefromVar(String var){

		StringTokenizer str = new StringTokenizer(var,".");
		return str.nextToken();
	}
	public static String getElemNamefromVar(String var){

		StringTokenizer str = new StringTokenizer(var,".");
		str.nextToken();
		return str.nextToken();
	}
	
	
	public static void splitMultiJoinerTransformation (Connector docspecs)
	{

		for (DataMapping dm : docspecs.getDataMappings().getDataMapping())
		{
			if (dm.getDataTransformations() == null)
				continue;

			List<DataTransformation> datatransList = new ArrayList<DataTransformation>();

			for (DataTransformation dt : dm.getDataTransformations().getDataTransformation())
			{
				if (!dt.getTransformationType().equalsIgnoreCase("Multi Joiner Transformation"))
					continue;

				List<String> detailCompList = new ArrayList<String>();
				Set<String> masterCompList_unq = new HashSet<String>();
				masterCompList_unq.add(dt.getMultijoiner().getMasterComponentRef());

				for (DetailComponent dc : dt.getMultijoiner().getDetailComponent()) 
				{
					for (JoinCondition jc : dc.getJoinCondition())
					{
						String masterComp = jc.getMasterDataElement().getComponentRef();
						boolean masterCheck = false;

						if (dt.getMultijoiner().getMasterComponentRef().equalsIgnoreCase(masterComp))
							continue;

						for (DetailComponent dc1 : dt.getMultijoiner().getDetailComponent())
						{
							if (dc.getComponentRef().equalsIgnoreCase(dc1.getComponentRef()))
								continue;

							for (JoinCondition jc1 : dc1.getJoinCondition())
							{
								if (jc1.getDetailDataElement().getComponentRef().equalsIgnoreCase(masterComp))
								{
									masterCheck = true;
								}
							}
						}
						if (!masterCheck)
						{
							masterCompList_unq.add(masterComp);
						}
					}
				}//end of for

				String [] prevComp = dt.getPrevComponentRef().split(";");
				List<String> prevList = new ArrayList<String>();

				for (int j = 0; j < prevComp.length; j++) 
				{
					prevList.add(prevComp[j]);

					for (DetailComponent dc : dt.getMultijoiner().getDetailComponent()) 
					{
						if (prevComp[j].equalsIgnoreCase(dc.getComponentRef()))
						{
							prevList.remove(prevComp[j]);
						}
					}
				}

				String prevCompNames = prevList.toString().replace("[", "").replace("]", "").replace(", ", ";");

				for (DetailComponent dc : dt.getMultijoiner().getDetailComponent()) 
				{
					prevCompNames = prevCompNames+";"+dc.getComponentRef();
				}
				dt.setPrevComponentRef(prevCompNames);

				DataTransformation dt_new = new DataTransformation();
				String prevComponentRef = dt.getPrevComponentRef().split(";")[0];

				int dc_count = 1;
				List<String> masterCompList = new ArrayList<String>(masterCompList_unq);
				for (int i = 0; i < masterCompList.size(); i++)
				{
					String masterComponent = masterCompList.get(i);
					int pre_count = 0;
					//int dc_count = 0;
					for (DetailComponent dc : dt.getMultijoiner().getDetailComponent()) 
					{
						pre_count++;
						if (detailCompList.contains(dc.getComponentRef()))
							continue;

						boolean check = false;

						//dc_count++;
						dt_new = new DataTransformation();
						String joinerName = "jnr_"+dc.getComponentRef()+"1";

						if (dc_count == dt.getMultijoiner().getDetailComponent().size())
							joinerName = dt.getName();

						dt_new.setName(joinerName);
						dt_new.setTransformationType("Joiner Transformation");
						//String new_prevComp = prevComponentRef+";"+dt.getPrevComponentRef().split(";")[dc_count];
						String new_prevComp = prevComponentRef+";"+dt.getPrevComponentRef().split(";")[pre_count];
						dt_new.setPrevComponentRef(new_prevComp);

						Joiner join = new Joiner();
						join.setJoinType(dc.getJoinType());
						join.setUseSortedInput(dc.isUseSortedInput());

						dt_new.setJoiner(join);
						List<String> detailList = new ArrayList<String>();
						for (JoinCondition jc : dc.getJoinCondition())
						{
							if (jc.getMasterDataElement().getComponentRef().equalsIgnoreCase(masterComponent))
							{
								check = true;
							}
							detailList.add(jc.getDetailDataElement().getComponentRef());

							com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Joiner.JoinCondition joinCondition = 
									new com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Joiner.JoinCondition();

							MasterDataElement mde = new MasterDataElement();
							mde.setComponentRef(jc.getMasterDataElement().getComponentRef());
							mde.setDataElementName(jc.getMasterDataElement().getDataElementName());
							joinCondition.setMasterDataElement(mde);

							DetailDataElement dde = new DetailDataElement();
							dde.setComponentRef(jc.getDetailDataElement().getComponentRef());
							dde.setDataElementName(jc.getDetailDataElement().getDataElementName());
							joinCondition.setDetailDataElement(dde);
							join.getJoinCondition().add(joinCondition);
						}
						if (check)
						{
							dc_count++;
							datatransList.add(dt_new);
							prevComponentRef = joinerName;
							detailCompList.add(dc.getComponentRef());
							for (int j = 0; j < detailList.size(); j++) 
							{
								if (masterCompList_unq.add(detailList.get(j)))
								{
									masterCompList.add(detailList.get(j));
								}	
							}
						}
					}
					masterCompList.remove(masterComponent);
					i--;
				}
			}

			for (int j = 0; j < dm.getDataTransformations().getDataTransformation().size(); j++) 
			{
				DataTransformation dt = dm.getDataTransformations().getDataTransformation().get(j);

				if (dt.getTransformationType().equalsIgnoreCase("Multi Joiner Transformation"))
				{
					dm.getDataTransformations().getDataTransformation().remove(j);
					j--;
				}
			}
			dm.getDataTransformations().getDataTransformation().addAll(datatransList);
		}
	}
	
	public static void splitMultiLookupTransformation (Connector docspecs)
	{
		for (DataMapping dm : docspecs.getDataMappings().getDataMapping())
		{
			if (dm.getDataTransformations() == null)
				continue;

			List<DataTransformation> datatransList = new ArrayList<DataTransformation>();

			List<String> removeList = new ArrayList<String>();

			for (DataTransformation dt : dm.getDataTransformations().getDataTransformation())
			{
				if (!dt.getTransformationType().equalsIgnoreCase("Lookup"))
					continue;

				if(dt.getLookup().getLookupSource().size()  < 2)
					continue;

				removeList.add(dt.getName());

				List<String> newlkpList = new ArrayList<String>();

				int lkpCount = 0;
				String [] prevComp = dt.getPrevComponentRef().split(";");
				List<String> prevList = new ArrayList<String>();

				for (int j = 0; j < prevComp.length; j++) 
				{
					prevList.add(prevComp[j]);

					for (LookupSource ls : dt.getLookup().getLookupSource()) 
					{
						if (prevComp[j].equalsIgnoreCase(ls.getLkpSourceComponentRef()))
						{
							prevList.remove(prevComp[j]);
						}
					}
				}

				String prevCompNames = prevList.toString().replace("[", "").replace("]", "").replace(", ", ";");

				for (LookupSource ls : dt.getLookup().getLookupSource()) 
				{
					lkpCount++;

					DataTransformation dt_new = new DataTransformation();

					String lkpName = "lkp_"+ls.getLkpSourceComponentRef()+"_1";
					//String lkpName = "lkp_"+ls.getLkpSourceComponentRef()+"_"+lkpCount;


					if (prevList.size() > 1)
					{
						if (lkpCount == dt.getLookup().getLookupSource().size())
							lkpName = dt.getName();
					}


					dt_new.setName(lkpName);
					dt_new.setTransformationType("Lookup");
					dt_new.setPrevComponentRef(prevCompNames);

					Lookup lkp = new Lookup();
					lkp.setLookupType(dt.getLookup().getLookupType());
					lkp.setMultipleMatchPolicy(dt.getLookup().getMultipleMatchPolicy());

					lkp.getLookupSource().add(ls);
					dt_new.setLookup(lkp);

					if (prevList.size() > 1)
					{
						prevCompNames = lkpName;
					}

					if (!lkpName.equalsIgnoreCase(dt.getName()))
						newlkpList.add(lkpName);

					datatransList.add(dt_new);
				}

				if (prevList.size() == 1)
				{
					for (int j = 0; j < dm.getDataTransformations().getDataTransformation().size(); j++) 
					{
						DataTransformation dtp = dm.getDataTransformations().getDataTransformation().get(j);

						if (dtp.getPrevComponentRef() == null)
							continue;
						if (dtp.getPrevComponentRef().equalsIgnoreCase(""))
							continue;

						if(dtp.getPrevComponentRef().toLowerCase().matches(".*\\b"+dt.getName().toLowerCase()+"\\b.*"))
						{
							String val1 = ";"+dt.getName();
							String val2 = dt.getName()+";";
							String val3 = dt.getName();
							
							String previous_name = dtp.getPrevComponentRef().replace(val1, "").replace(val2, "").replace(val3, "");
							
							String prev_comp_upd = "";
							
							if (!previous_name.equalsIgnoreCase(""))
							{
								prev_comp_upd = previous_name+";"+newlkpList.toString().replace("[", "").replace("]", "").replace(", ", ";");
							}
							else
							{
								prev_comp_upd = newlkpList.toString().replace("[", "").replace("]", "").replace(", ", ";");
							}
							dtp.setPrevComponentRef(prev_comp_upd);
						}
					}
					for (int j = 0; j < dm.getDataTransformations().getDataTransformation().size(); j++) 
					{
						DataTransformation dtp = dm.getDataTransformations().getDataTransformation().get(j);

						if (dtp.getNextComponentRef() == null)
							continue;
						if (dtp.getNextComponentRef().equalsIgnoreCase(""))
							continue;

						if(dtp.getNextComponentRef().toLowerCase().matches(".*\\b"+dt.getName().toLowerCase()+"\\b.*"))
						{
							String val1 = ";"+dt.getName();
							String val2 = dt.getName()+";";
							String val3 = dt.getName();
							
							String next_name = dtp.getNextComponentRef().replace(val1, "").replace(val2, "").replace(val3, "");
							
							String next_comp_upd = "";
							
							if (!next_name.equalsIgnoreCase(""))
							{
								next_comp_upd = next_name+";"+newlkpList.toString().replace("[", "").replace("]", "").replace(", ", ";");
							}
							else
							{
								next_comp_upd = newlkpList.toString().replace("[", "").replace("]", "").replace(", ", ";");
							}
							
							dtp.setNextComponentRef(next_comp_upd);
							
						}
					}
				}
			}

			for (int j = 0; j < dm.getDataTransformations().getDataTransformation().size(); j++) 
			{
				DataTransformation dt = dm.getDataTransformations().getDataTransformation().get(j);

				if (dt.getTransformationType().equalsIgnoreCase("Lookup"))
				{
					if (removeList.contains(dt.getName()))
					{
						dm.getDataTransformations().getDataTransformation().remove(j);
						j--;
					}
				}
			}
			dm.getDataTransformations().getDataTransformation().addAll(datatransList);
		}
	}
	
	
	
	public static void splitMultiLookupTransformationIntoSerial (Connector docspecs)
	{
		for (DataMapping dm : docspecs.getDataMappings().getDataMapping())
		{
			if (dm.getDataTransformations() == null)
				continue;

			List<DataTransformation> datatransList = new ArrayList<DataTransformation>();

			List<String> removeList = new ArrayList<String>();

			for (DataTransformation dt : dm.getDataTransformations().getDataTransformation())
			{
				if (!dt.getTransformationType().equalsIgnoreCase("Lookup"))
					continue;

				if(dt.getLookup().getLookupSource().size()  < 2)
					continue;

				removeList.add(dt.getName());

				List<String> newlkpList = new ArrayList<String>();

				int lkpCount = 0;
				String [] prevComp = dt.getPrevComponentRef().split(";");
				List<String> prevList = new ArrayList<String>();

				for (int j = 0; j < prevComp.length; j++) 
				{
					prevList.add(prevComp[j]);

					for (LookupSource ls : dt.getLookup().getLookupSource()) 
					{
						if (prevComp[j].equalsIgnoreCase(ls.getLkpSourceComponentRef()))
						{
							prevList.remove(prevComp[j]);
						}
					}
				}

				String prevCompNames = prevList.toString().replace("[", "").replace("]", "").replace(", ", ";");
				String firstLookupName ="";
				for (LookupSource ls : dt.getLookup().getLookupSource()) 
				{
					lkpCount++;

					DataTransformation dt_new = new DataTransformation();

					String lkpName = "lkp_"+ls.getLkpSourceComponentRef()+"_1";
					//String lkpName = "lkp_"+ls.getLkpSourceComponentRef()+"_"+lkpCount;


					if (prevList.size() > 1)
					{
						if (lkpCount == dt.getLookup().getLookupSource().size())
							lkpName = dt.getName();
					}


					dt_new.setName(lkpName);
					dt_new.setTransformationType("Lookup");
					dt_new.setPrevComponentRef(prevCompNames);
					
					if(firstLookupName.isEmpty())
						firstLookupName = lkpName;

					Lookup lkp = new Lookup();
					lkp.setLookupType(dt.getLookup().getLookupType());
					lkp.setMultipleMatchPolicy(dt.getLookup().getMultipleMatchPolicy());

					lkp.getLookupSource().add(ls);
					dt_new.setLookup(lkp);

					prevCompNames = lkpName;


					if (!lkpName.equalsIgnoreCase(dt.getName()))
						newlkpList.add(lkpName);

					datatransList.add(dt_new);
				}

				if (prevList.size() == 1)
				{
					for (int j = 0; j < dm.getDataTransformations().getDataTransformation().size(); j++) 
					{
						DataTransformation dtp = dm.getDataTransformations().getDataTransformation().get(j);

						if (dtp.getPrevComponentRef() == null)
							continue;
						if (dtp.getPrevComponentRef().equalsIgnoreCase(""))
							continue;

						if(dtp.getPrevComponentRef().toLowerCase().matches(".*\\b"+dt.getName().toLowerCase()+"\\b.*"))
						{
							String prev_comp_upd = prevCompNames;
							dtp.setPrevComponentRef(prev_comp_upd);
						}
						
					}
					for (int j = 0; j < dm.getDataTransformations().getDataTransformation().size(); j++) 
					{
						DataTransformation dtp = dm.getDataTransformations().getDataTransformation().get(j);

						if (dtp.getNextComponentRef() == null)
							continue;
						if (dtp.getNextComponentRef().equalsIgnoreCase(""))
							continue;

						if(dtp.getNextComponentRef().toLowerCase().matches(".*\\b"+dt.getName().toLowerCase()+"\\b.*"))
						{
							String next_comp_upd = firstLookupName;
							dtp.setPrevComponentRef(next_comp_upd);
						}
					}
				}
			}

			for (int j = 0; j < dm.getDataTransformations().getDataTransformation().size(); j++) 
			{
				DataTransformation dt = dm.getDataTransformations().getDataTransformation().get(j);

				if (dt.getTransformationType().equalsIgnoreCase("Lookup"))
				{
					if (removeList.contains(dt.getName()))
					{
						dm.getDataTransformations().getDataTransformation().remove(j);
						j--;
					}
				}
			}
			dm.getDataTransformations().getDataTransformation().addAll(datatransList);
			
			
		}
	}

	public static void addPassThroughExpresssion(Connector docspecs, DataMapping dataMapping,
			ArrayList<TransRecExtDTO> recsList) 
	{
		// TODO Auto-generated method stub
		//boolean othertrans = false;
		//ArrayList<DataTransformation> otherTransLst = new ArrayList<DataTransformation>();
		for (DataMapping dm : docspecs.getDataMappings().getDataMapping())
		{
			//Adding Previous Comp
			if(dm.getName().equalsIgnoreCase(dataMapping.getName())) 
			{
				String srcComp = "";
				for(int i = 0; i < recsList.size(); i++) 
				{
					if(recsList.get(i).transType.equalsIgnoreCase("Source")) 
					{
						DataTransformation tempExp = new DataTransformation();
						tempExp.setName("ds_Exp");
						tempExp.setTransformationType("Expression");
						tempExp.setPrevComponentRef(recsList.get(i).compName);
						dm.getDataTransformations().getDataTransformation().add(tempExp);
					}
				}
				
				
				for (int j = 0; j < dm.getDataTransformations().getDataTransformation().size(); j++) 
				{
					DataTransformation dt = dm.getDataTransformations().getDataTransformation().get(j);
					if(dt.getTransformationType().equalsIgnoreCase("Source Definition")) 
					{
						dt.setNextComponentRef("ds_Exp");
					}
					if(dt.getTransformationType().equalsIgnoreCase("Target Definition"))
						dt.setPrevComponentRef("ds_Exp");					
																	
				}
			}
			
		}
		
	}

}