
package com.dataSwitch.ds.sql.SQLQueryRevEngg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.functionTypeRules.FnMapRules;
import com.dataSwitch.ds.functionTypeRules.FnRule;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.sql.SQLGenerator.QueryGenerator;
import com.dataSwitch.ds.sql.SQLGenerator.ReportLogInfo;
import com.dataSwitch.ds.sql.SQLGenerator.SQLValue;
import com.dataSwitch.ds.sql.SQLObjeds.SQLElement;
import com.dataSwitch.ds.sql.SQLObjeds.SQLExpr;
import com.dataSwitch.ds.sql.SQLObjeds.SQLJoin;
import com.dataSwitch.ds.sql.SQLObjeds.SQLJoinCond;
import com.dataSwitch.ds.sql.SQLObjeds.SQLObject;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;
import com.dataSwitch.ds.sql.SQLObjeds.SQLUnion;
import com.dataSwitch.ds.sql.SQLRevEnggDTO.sqlDTO;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Filter;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Multijoiner;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Multijoiner.DetailComponent;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Multijoiner.DetailComponent.JoinCondition;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Multijoiner.DetailComponent.JoinCondition.DetailDataElement;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Multijoiner.DetailComponent.JoinCondition.MasterDataElement;
//import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.DetailComponent;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.SourceDataset;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.TargetDataElements;
import com.dataSwitch.xml.connectorspecs.Connector.SystemCatalogs.SystemCatalog.SystemEntities.SystemEntity;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType;
//import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.DetailComponent.JoinCondition;
//import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.DetailComponent.JoinCondition.DetailDataElement;
//import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.DetailComponent.JoinCondition.MasterDataElement;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.SetOperation;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.SetOperation.InputGroups;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.SetOperation.InputGroups.InputGroup;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Sorter;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.Sorter.SorterKey;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType.TransformationRule;
import com.dataSwitch.xml.connectorspecs.TargetDataElementType.TransformationRule.Parameter;

public class processSubQuery 
{

	public static void fillTransFormationDtls(DataTransformations dataTrans, SQLQuery sqlQuery,
			ArrayList<String> tables, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap, String getCatalogName,
			ArrayList<String> uniqueCompNamesList, TargetDataElements tgtDataElems, boolean checkSubQuery, LinkedHashMap<String, sqlDTO> sqlDtoList, LinkedHashMap<String, SystemEntity> sysEntityMap, int currentDbId, boolean checkJoinerSubQuery) 
	{
		
		//Source  -> Filter -> Joiner -> filter -> RemoveDuplicate -> Expression -> Aggregator -> filter-> Sorter -> Union -> Target
		//                                                                                                             ^
		//Source  -> Filter -> Joiner -> filter -> RemoveDuplicate -> Expression -> Aggregator -> filter-> Sorter ->   |
		
		ArrayList<sqlDTO> sqlDto = new ArrayList<sqlDTO>();
		
		if(sqlQuery.tableList.size() > 0)
			createSourceTrans(sqlQuery, sqlQuery.tableList,sqlQuery.colsList, dataTrans, tables, uniqueCompNamesList, tgtDataElemnsMap, getCatalogName, 
					sqlDto, tgtDataElems, checkSubQuery, sqlDtoList, sysEntityMap, currentDbId, checkJoinerSubQuery);
		 
	    if(sqlQuery.joinCond != null)
			createJoinerTrans(sqlQuery, sqlQuery.joinCond, dataTrans, tables, uniqueCompNamesList, sqlDto, tgtDataElemnsMap, getCatalogName, tgtDataElems,
					checkSubQuery, sqlDtoList, sysEntityMap, currentDbId, checkJoinerSubQuery);
		
	    if(sqlQuery.whereCond != null)
			createFilterTrans(sqlQuery, sqlQuery.whereCond, dataTrans, tables, uniqueCompNamesList, 
					                        sqlDto, tgtDataElemnsMap, sqlDtoList, sqlQuery.colsList, currentDbId, checkJoinerSubQuery);
		
	    if(sqlQuery.colsList.size() > 0)
	    	createExprTrans(sqlQuery, sqlQuery.colsList, dataTrans, tables, uniqueCompNamesList, sqlDto, tgtDataElems, tgtDataElemnsMap, sqlDtoList);
		
	    if(sqlQuery.groupByCols.size() > 0)
			createAggrTrans(sqlQuery, sqlQuery.colsList,sqlQuery.groupByCols, dataTrans, tables, uniqueCompNamesList, tgtDataElems, sqlDto, 
					tgtDataElemnsMap, sqlDtoList);
	    
	    if(sqlQuery.havingCond != null)
	    	createFltrTransForHavingClause(sqlQuery, sqlQuery.havingCond, dataTrans, tables, uniqueCompNamesList, sqlDto, tgtDataElemnsMap, sqlDtoList, sqlQuery.colsList);
	    
	    if(sqlQuery.OrderBy.size() > 0)	
	        createSorterTrans(sqlQuery, sqlQuery.OrderBy, dataTrans, tables, uniqueCompNamesList, sqlQuery.colsList, 
	        		sqlDto, tgtDataElemnsMap, getCatalogName);
		
	    if(sqlQuery.predicate != null)
			createRemoveDuplicateTrans(sqlQuery, sqlQuery.colsList, dataTrans, tables, uniqueCompNamesList);
		
	    if(sqlQuery.setOperation != null)
			createUnionTrans(sqlQuery, sqlQuery.setOperation, dataTrans, tables, uniqueCompNamesList, 
						tgtDataElems, sqlDto, tgtDataElemnsMap, checkSubQuery, sqlDtoList, sysEntityMap, getCatalogName, currentDbId, checkJoinerSubQuery);
	    
	    fillNextCompNameForFltrTrans(dataTrans, sqlDto);
	    
	    if(sqlDto.size() > 0)
		{
			addColsListForMainSource(sqlDto, sqlQuery.colsList);
		}
	    
	    sqlDTO sqlObjt = new sqlDTO();
	    if(checkSubQuery)
		{
	    	getCompNameForSubQuery(sqlDto, sqlObjt);
	    	checkSubQuery = false;
		}
	    
	    fillTgtDataElems(sqlObjt, sqlDto, tgtDataElemnsMap);
	    
		sqlDto.clear();
		
		if(sqlObjt.transType != null)
		{
			sqlObjt.subQuery = true;
			sqlDtoList.put(sqlObjt.compName, sqlObjt);
		}
		
		
	}
	
    private static void fillTgtDataElems(sqlDTO sqlObjt, ArrayList<sqlDTO> sqlDto,
			LinkedHashMap<String, sqlDTO> tgtDataElemnsMap) 
    {
		
		if(sqlObjt.transType != null && sqlObjt.subSQLType.equalsIgnoreCase("SubQueryPrevComp"))
			tgtDataElemnsMap.put(sqlObjt.compName, sqlObjt);
		else
		{
			for(int i = 0 ; i < sqlDto.size() ;i++)
			{
				if(sqlDto.get(i).transType.equalsIgnoreCase("Expression"))
					tgtDataElemnsMap.put(sqlDto.get(i).compName, sqlDto.get(i));
				if(sqlDto.get(i).transType.equalsIgnoreCase("Aggregator"))
					tgtDataElemnsMap.put(sqlDto.get(i).compName, sqlDto.get(i));
				if(sqlDto.get(i).transType.equalsIgnoreCase("MainSource"))
				{
					tgtDataElemnsMap.put(sqlDto.get(i).compName, sqlDto.get(i));
				}
			}
		}
	}

	private static sqlDTO getCompNameForSubQuery(ArrayList<sqlDTO> sqlDto, sqlDTO sqlObjt) 
    {
		LinkedList<sqlDTO> jnrCompLst = new LinkedList<sqlDTO>();
		
		for(int i = 0 ; i < sqlDto.size(); i++)
		{
			if(sqlDto.get(i).transType.equalsIgnoreCase("Union"))
				getsqlDtoProps(sqlDto.get(i), sqlObjt);
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Sorter"))
				getsqlDtoProps(sqlDto.get(i), sqlObjt);
			else if(sqlDto.get(i).transType.equalsIgnoreCase("HavingFilter"))
				getsqlDtoProps(sqlDto.get(i), sqlObjt);
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Aggregator"))
				getsqlDtoProps(sqlDto.get(i), sqlObjt);
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Expression"))
				getsqlDtoProps(sqlDto.get(i), sqlObjt);
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Filter"))
				getsqlDtoProps(sqlDto.get(i), sqlObjt);
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Joiner"))
				getsqlDtoProps(sqlDto.get(i), sqlObjt);
			else if(sqlDto.get(i).transType.equalsIgnoreCase("MainSource"))
				getsqlDtoProps(sqlDto.get(i), sqlObjt);
		}
		
		if(sqlObjt != null)
		{
			if(sqlObjt.transType == null)
			{
				if(jnrCompLst.size() > 0)
					getsqlDtoProps(jnrCompLst.get(jnrCompLst.size()-1), sqlObjt);
			}
		}
		
		
		for(int i = 0; i < sqlDto.size() ; i++)
		{
		    if(sqlDto.get(i).transType.equalsIgnoreCase("MainSource"))
				sqlObjt.sqlDtoList.add(sqlDto.get(i));
		    
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Expression"))
				sqlObjt.sqlDtoList.add(sqlDto.get(i));
			
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Aggregator"))
				sqlObjt.sqlDtoList.add(sqlDto.get(i));
			
		}
		
		return sqlObjt;
    }

	private static void getsqlDtoProps(sqlDTO sqlDTO, sqlDTO sqlObjt) 
	{
		
		sqlObjt.aliasName = sqlDTO.aliasName;
		sqlObjt.compName = sqlDTO.compName;
		sqlObjt.nextCompName = sqlDTO.nextCompName;
		sqlObjt.originalTableName = sqlDTO.originalTableName;
		sqlObjt.subSQLType = "SubQueryPrevComp";
		sqlObjt.transType = sqlDTO.transType;
		sqlObjt.subQuery = sqlDTO.subQuery;
		
		
	}

	private static void createFltrTransForHavingClause(SQLQuery sqlQuery, SQLExpr havingCond,
		DataTransformations dataTrans, ArrayList<String> tables, ArrayList<String> uniqueCompNamesList,
		ArrayList<sqlDTO> sqlDto, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap, LinkedHashMap<String, sqlDTO> sqlDtoList, ArrayList<SQLObject> colsList) 
    {
    	
		if(havingCond != null)
		{
			DataTransformation dataTran = new DataTransformation();
			String compName = getCompNameForTrans(sqlDto, "fltr_", tgtDataElemnsMap, sqlQuery);
			compName = Utils.getUniqueCompName(compName, uniqueCompNamesList);
			dataTran.setName(compName);
			havingCond.compName = compName;
			String prevCompName = getprevCompNameForHavingFltr(sqlDto, compName);
			dataTran.setTransformationType("Filter Transformation");
			dataTran.setPrevComponentRef(prevCompName);
			dataTran.setNextComponentRef("");
			
			getSrcDTOObjeds(sqlDto, "HavingFilter", compName, compName , compName);
			
			if(havingCond.args.size() > 1)
			{
				Filter fltr = new Filter();
				String fltrCondVal = "";
				for(int i = 0 ; i < havingCond.args.size(); i++)
				{
					SQLExpr sqlExpr = new SQLExpr();
					if(sqlQuery.whereCond.args.get(i) instanceof SQLExpr)
						sqlExpr = (SQLExpr) sqlQuery.whereCond.args.get(i);
					
					// As of now I'm handling for sqlExprVal we have to do the changes for subqueries 
					
					if(sqlExpr.value != null)
					{
						if(i == 0)
							fltrCondVal += " "+getUpdatedCondVal(sqlExpr.value, sqlDto, colsList);
						else
							fltrCondVal += " "+sqlExpr.type + " "+getUpdatedCondVal(sqlExpr.value, sqlDto, colsList);
					}
				}
				
				fltr.setFilterCondition(fltrCondVal);
				fltr.setFilterPosition("none");
				
				dataTran.setFilter(fltr);
			}
			
			dataTrans.getDataTransformation().add(dataTran);
		}
	
 	}

	private static String getprevCompNameForHavingFltr(ArrayList<sqlDTO> sqlDto, String compName) 
	{
		String prevCompName = "";
		String transType = "";
		LinkedList<String> jnrCompLst = new LinkedList<String>();
		for(int i = 0 ; i < sqlDto.size(); i++)
		{
			if(sqlDto.get(i).subSQLType != null)
			{
				if(sqlDto.get(i).subSQLType.equalsIgnoreCase("SubQueryPrevComp") && sqlDto.get(i).subQuery)
				{
					prevCompName = sqlDto.get(i).compName;
					transType =  sqlDto.get(i).transType;
					sqlDto.get(i).subQuery = false;
				}
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Aggregator"))
			{
				prevCompName = sqlDto.get(i).compName;
				transType =  sqlDto.get(i).transType;
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Expression"))
			{
				prevCompName = sqlDto.get(i).compName;
				transType =  sqlDto.get(i).transType;
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Filter"))
			{
				prevCompName = sqlDto.get(i).compName;
				transType =  sqlDto.get(i).transType;
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Joiner"))
				jnrCompLst.add(sqlDto.get(i).compName);
			else if(sqlDto.get(i).transType.equalsIgnoreCase("MainSource"))
				prevCompName = sqlDto.get(i).compName;
		}
		if(prevCompName != null)
		{
			if(prevCompName.equalsIgnoreCase(""))
			{
				if(jnrCompLst.size() > 0)
					prevCompName = jnrCompLst.get(jnrCompLst.size()-1);
			}
		}
		
		if(transType.equalsIgnoreCase("Filter"))
		{
			for(int i = 0 ; i < sqlDto.size(); i++)
			{
				if(sqlDto.get(i).transType.equalsIgnoreCase("Filter"))
				{
					sqlDto.get(i).nextCompName = compName;
				}
			}
		}
		return prevCompName;
	}

	private static void fillNextCompNameForFltrTrans(DataTransformations dataTrans, ArrayList<sqlDTO> sqlDto)
	{
		if( dataTrans.getDataTransformation() != null)
		{
			for(int i = 0 ; i < dataTrans.getDataTransformation().size() ; i++)
			{
				DataTransformation dataTran = dataTrans.getDataTransformation().get(i);
				
				if(dataTran.getTransformationType() != null)
				{
					if(dataTran.getTransformationType().equalsIgnoreCase("Filter TransFormation"))
					{
						String compName = dataTran.getName();
						for(int  i1 = 0 ; i1 < sqlDto.size() ; i1++)
						{
							if(sqlDto.get(i1).compName != null)
							{
								if(sqlDto.get(i1).transType.equalsIgnoreCase("Filter") && sqlDto.get(i1).compName.equalsIgnoreCase(compName))
								{
									if(sqlDto.get(i1).nextCompName != null)
										dataTran.setNextComponentRef(sqlDto.get(i1).nextCompName);
								}
								else if(sqlDto.get(i1).transType.equalsIgnoreCase("HavingFilter") && sqlDto.get(i1).compName.equalsIgnoreCase(compName))
								{
									if(sqlDto.get(i1).nextCompName != null)
										dataTran.setNextComponentRef(sqlDto.get(i1).nextCompName);
								}
							}
						}
					}
				}
			}
		}
	}

	private static void createAggrTrans(SQLQuery sqlQuery, ArrayList<SQLObject> colsList,
			ArrayList<SQLObject> groupByCols, DataTransformations dataTrans, ArrayList<String> tables,
			ArrayList<String> uniqueCompNamesList, TargetDataElements tgtDataElems, ArrayList<sqlDTO> sqlDto, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap, LinkedHashMap<String, sqlDTO> sqlDtoList) 
	{
		boolean checkExpr = checkExprAggrTrans(colsList, sqlDto);
		if(checkExpr)
		{
			DataTransformation dataTran = new DataTransformation();
			String compName = getCompNameForTrans(sqlDto, "aggr_", sqlDtoList, sqlQuery);
			
			if(compName == null)
				compName = "aggr_"+sqlQuery.compName;
			
			compName = Utils.getUniqueCompName(compName, uniqueCompNamesList);
			sqlQuery.aggrCompName = compName;
			
			dataTran.setName(compName);
			String prevCompName = getprevCompNameForAggr(sqlDto, compName);
			dataTran.setTransformationType("Aggregator");
			dataTran.setPrevComponentRef(prevCompName);
			
			getSrcDTOObjeds(sqlDto, "Aggregator", compName, compName , compName);
			
			fillTgtDataElemsForAggr(colsList, sqlDto, tgtDataElems, compName, groupByCols); 
			
			dataTrans.getDataTransformation().add(dataTran);
		}
		
	}

	
	private static String getprevCompNameForAggr(ArrayList<sqlDTO> sqlDto, String compName)
	{
		String prevCompName = "";
		String transType = "";
		LinkedList<String> jnrCompLst = new LinkedList<String>();
		for(int i = 0 ; i < sqlDto.size(); i++)
		{
			if(sqlDto.get(i).subSQLType != null)
			{
				if(sqlDto.get(i).subSQLType.equalsIgnoreCase("SubQueryPrevComp") && sqlDto.get(i).subQuery)
				{
					prevCompName = sqlDto.get(i).compName;
					transType = sqlDto.get(i).transType;
					sqlDto.get(i).subQuery = false;
				}
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Expression"))
			{
				prevCompName = sqlDto.get(i).compName;
				transType = sqlDto.get(i).transType;
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Filter"))
			{
				prevCompName = sqlDto.get(i).compName;
				transType =  sqlDto.get(i).transType;
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Joiner"))
				jnrCompLst.add(sqlDto.get(i).compName);
			else if(sqlDto.get(i).transType.equalsIgnoreCase("MainSource"))
				prevCompName = sqlDto.get(i).compName;
		}
		if(prevCompName != null)
		{
			if(prevCompName.equalsIgnoreCase(""))
			{
				if(jnrCompLst.size() > 0)
					prevCompName = jnrCompLst.get(jnrCompLst.size()-1);
			}
		}
		
		if(transType.equalsIgnoreCase("Filter"))
		{
			for(int i = 0 ; i < sqlDto.size(); i++)
			{
				if(sqlDto.get(i).transType.equalsIgnoreCase("Filter"))
				{
					sqlDto.get(i).nextCompName = compName;
				}
			}
		}
		return prevCompName;
	}

	private static void fillTgtDataElemsForAggr(ArrayList<SQLObject> colsList, ArrayList<sqlDTO> sqlDto,
			TargetDataElements tgtDataElems, String compName, ArrayList<SQLObject> groupByCols) 
	{
		int count = 1;
		for(int i = 0 ; i < colsList.size() ; i++)
		{
			SQLElement sqlElem = new SQLElement();
			if(colsList.get(i) instanceof SQLElement)
				sqlElem = (SQLElement) colsList.get(i);
			
			if(sqlElem.name != null)
			{
				if(sqlElem.name.contains("*"))
					sqlElem.name = sqlElem.name.replace("*", " * ");
				
				ArrayList<Token> tknVal = FunctionTypeConv.getTokens(sqlElem.name);
				getUpdatedGrpbyOrdrbyCols(sqlElem, groupByCols, count++, tknVal);
				
			    if(tknVal.size() > 1)
			    {
			    	boolean checkForAggr = checkForAggrTrans(tknVal.get(0));
			    	if(checkForAggr)
			    	{
			    		createTargetDataElemsForAggr(sqlElem.aliasName, tgtDataElems, sqlDto, tknVal, sqlElem.name, compName, groupByCols, colsList);
			    		for(int i2 = 0 ; i2 < sqlDto.size() ; i2++ )
			    		{
			    			if(sqlDto.get(i2).transType.equalsIgnoreCase("Aggregator") && 
			    					sqlDto.get(i2).compName.equalsIgnoreCase(compName))
			    			{
			    				if(!sqlDto.get(i2).sqlElems.contains(sqlElem))
			    					sqlDto.get(i2).sqlElems.add(sqlElem);
			    			}
			    		}
			    	}
			    }
			}
		}
	}

	private static void getUpdatedGrpbyOrdrbyCols(SQLElement sqlElem, ArrayList<SQLObject> colsList, int count, ArrayList<Token> tknVal)
	{
		for(int i = 0 ; i < colsList.size(); i++)
		{
			SQLElement sqlGrpByElem = new SQLElement();
			
			if(colsList.get(i) instanceof SQLElement)
				sqlGrpByElem = (SQLElement) colsList.get(i); 
			if(sqlGrpByElem.name != null)
			{
				String cnt = Integer.toString(count);
				if(sqlGrpByElem.name.equalsIgnoreCase(cnt))
				{
					sqlGrpByElem.name = sqlElem.name;
					sqlGrpByElem.aliasName = sqlElem.aliasName;
				}
			}
		}
		
	}

	private static void createTargetDataElemsForAggr(String aliasName, TargetDataElements tgtDataElems,
			ArrayList<sqlDTO> sqlDto, ArrayList<Token> tknVal, String name, String compName, ArrayList<SQLObject> groupByCols, ArrayList<SQLObject> colsList) 
	{
		
		TargetDataElementType tgtElemType = new TargetDataElementType();
		tgtElemType.setTargetDatsetRef(compName);
		if(aliasName != null)
			tgtElemType.setTargetDataElementRef(aliasName);
		
		 String updatedVal = getUpdatedCondVal(name, sqlDto, colsList);
		 
		 TransformationRule transRule = new TransformationRule();
		 transRule.setComponentName(compName);
		 transRule.setOriginalExpression(name);
		 if(tknVal.size() == 4)
			 if(getAggrTransRule(tknVal.get(0)) != null)
				 transRule.setRuleName(getAggrTransRule(tknVal.get(0)));
			 else
				 transRule.setRuleName("Aggregator - Expression");
		 else
			 transRule.setRuleName("Aggregator - Expression");
		 
		 if(transRule.getRuleName() != null) 
		 {
			 Parameter param = new Parameter();
			 if(transRule.getRuleName().equalsIgnoreCase("Aggregator - Expression"))
			 {
				 param.setName("Expression");
				 param.setType("Exp");
				 ArrayList<Token> condtkn = FunctionTypeConv.getTokens(updatedVal);
				 boolean checkDataElem = checkUpdatedColsHasDataElem(condtkn);
				
				 if(!checkDataElem)
					updatedVal = getUpdatedCondVals(updatedVal, sqlDto, condtkn);
				 
				 updatedVal = Utils.getStructuredVal(updatedVal);
				 param.setValue(updatedVal);
			 }
			 else
			 {
				 param.setName("var");
				 param.setType("DE");
				 ArrayList<Token> updatedValTkn = FunctionTypeConv.getTokens(updatedVal);
				 
				 String dataElem = getDataElemName(updatedValTkn, sqlDto);
				 
				 param.setValue(dataElem);
			 }
			  
			 transRule.getParameter().add(param);
			 getGroupByCols(transRule,groupByCols,sqlDto, compName, colsList);
		 }
		
		 tgtElemType.getTransformationRule().add(transRule);
		 
		 tgtDataElems.getTargetDataElement().add(tgtElemType);
	}

	private static String getDataElemName(ArrayList<Token> updatedValTkn, ArrayList<sqlDTO> sqlDto)
	{
		String dataElem = "";
		 for(int i = 0 ; i < updatedValTkn.size() ; i++)
		 {
			 if(updatedValTkn.get(i).type.name().equalsIgnoreCase("varwithdot"))
				 dataElem = updatedValTkn.get(i).data;
		 }
		 if(dataElem.length() < 0 || dataElem.equalsIgnoreCase(""))
		 {
			 dataElem = getSrcColsFromSubQuery(sqlDto, updatedValTkn, dataElem);
			 
			 if(dataElem == null)
				 dataElem = "";
			 
			if(dataElem.length() < 0 || dataElem.equalsIgnoreCase(""))
			{
				for(int i = 0 ; i < sqlDto.size() ; i++)
				{
					sqlDTO srcSqlDto = sqlDto.get(i);
					for(int j1 = 0 ; j1 < updatedValTkn.size() ; j1++)
					{
						if(updatedValTkn.get(j1).type.name().equalsIgnoreCase("VAR"))
						{
							if(srcSqlDto.transType.equalsIgnoreCase("MainSource"))
							{
								if(!updatedValTkn.get(j1).data.startsWith("ds"))
									dataElem = srcSqlDto.compName+"."+updatedValTkn.get(j1).data;
							}
						}
					}
					
				}
			}
		 }
		 
		return dataElem;
	}
	

	public static String getSrcColsFromSubQuery(ArrayList<sqlDTO> sqlDto, ArrayList<Token> tkn, String dataElem) 
	{
		for(int i = 0 ; i < sqlDto.size() ; i++)
		{
			if(sqlDto.get(i).subSQLType != null)
			{
				if(sqlDto.get(i).subSQLType.equalsIgnoreCase("SubQueryPrevComp"))
				{
					for(int j = 0 ; j < sqlDto.get(i).sqlDtoList.size() ; j++)
					{
						sqlDTO srcSqlDto = sqlDto.get(i).sqlDtoList.get(j);
						for(int j1 = 0 ; j1 < tkn.size() ; j1++)
						{
						
							if(tkn.get(j1).type.name().equalsIgnoreCase("VAR") || tkn.get(j1).type.name().equalsIgnoreCase("VARWITHDOT"))
							{
								if(srcSqlDto.transType.equalsIgnoreCase("MainSource") || srcSqlDto.transType.equalsIgnoreCase("Expression") ||
										srcSqlDto.transType.equalsIgnoreCase("Aggregator"))
								{
									dataElem = getSrcCompName(srcSqlDto, tkn.get(j1).data);
									if(dataElem.contains("."))
										return dataElem;
								}
								
							}
						}
					}
				}
			}
		}
		return null;
	}

	private static String getSrcCompName(sqlDTO srcSqlDto, String data)
	{
		if(srcSqlDto.sqlElems.size() > 0)
		{
			for (int i = 0; i < srcSqlDto.sqlElems.size(); i++) 
			{
				SQLElement sqlElem = new SQLElement();
				
				if(srcSqlDto.sqlElems.get(i) instanceof SQLElement)
					sqlElem = (SQLElement) srcSqlDto.sqlElems.get(i);
				
				if(sqlElem.aliasName != null)
				{
					if(data.contains("."))
						data = data.substring(data.lastIndexOf(".")+1);
					
					if(data.equalsIgnoreCase(sqlElem.aliasName))
					{
						String srcCompName = "";
						if(srcSqlDto.transType.equalsIgnoreCase("MainSource"))
						{
							if(sqlElem.name.contains("."))
							   srcCompName = sqlElem.name.substring(0,sqlElem.name.indexOf("."));
							else
							   srcCompName = sqlElem.name;
						}
						else
						{
							srcCompName = srcSqlDto.compName;
						}
						
						data = srcCompName + "." + data;
						break;
					}
				}
			}
		}
		return data;
	}

	private static void getGroupByCols(TransformationRule transRule, ArrayList<SQLObject> groupByCols,
			ArrayList<sqlDTO> sqlDto, String compName, ArrayList<SQLObject> colsList) 
	{
	
	   for(int i = 0 ; i < groupByCols.size() ; i++)
	   {
		   Parameter param = new Parameter();
		   param.setName("Group By");
		   param.setType("DE");
		   
		   SQLElement sqlElem = new SQLElement();
		   
		   if(groupByCols.get(i) instanceof SQLElement)
			   sqlElem = (SQLElement) groupByCols.get(i);
		   
		   ArrayList<Token> tknVal = FunctionTypeConv.getTokens(sqlElem.name);
		   
		   if(tknVal.size() > 1)
		   {
			   if(sqlElem.aliasName != null);
			   {
				   if(compName != null)
				   {
					   boolean checkAggr = checkForAggrTrans(tknVal.get(0));
					   String srcCompName = compName.substring(compName.indexOf("aggr_")+5, compName.length());
					  
					   if(!checkAggr)
						   param.setValue("expr_"+srcCompName+"."+sqlElem.aliasName);
					   else
						   param.setValue("aggr_"+srcCompName+"."+sqlElem.aliasName);
					   
				   }
			   }
		   }
		   else
		   {
			   String dataElem = getDataElemName(tknVal, sqlDto);
			   dataElem =  getUpdatedCondVal(dataElem, sqlDto, colsList);
			   param.setValue(dataElem);
		   }
		   
		   transRule.getParameter().add(param);
	   }
	}

	private static void createExprTrans(SQLQuery sqlQuery, ArrayList<SQLObject> colsList, DataTransformations dataTrans,
			ArrayList<String> tables, ArrayList<String> uniqueCompNamesList, ArrayList<sqlDTO> sqlDto, TargetDataElements tgtDataElems, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap, LinkedHashMap<String, sqlDTO> sqlDtoList) 
	{
		boolean checkExpr = checkExprAggrTrans(colsList, sqlDto);
		
		if(checkExpr)
		{
			
			DataTransformation dataTran = new DataTransformation();
			String compName = getCompNameForTrans(sqlDto, "expr_", sqlDtoList, sqlQuery);
			
			if(compName == null)
				compName = "expr_"+sqlQuery.compName;
			
			compName = Utils.getUniqueCompName(compName, uniqueCompNamesList);
	   
			sqlQuery.exprCompName = compName;
			dataTran.setName(compName);
			String prevCompName = getprevCompNameForExpr(sqlDto, compName);
			dataTran.setTransformationType("Expression");
			dataTran.setPrevComponentRef(prevCompName);
			
			getSrcDTOObjeds(sqlDto, "Expression", compName, compName , compName);
			
			fillTgtDataElemsForExpr(colsList, sqlDto, tgtDataElems, compName); 
						
			dataTrans.getDataTransformation().add(dataTran);
		}
	    
		
	}

	private static void fillTgtDataElemsForExpr(ArrayList<SQLObject> colsList, ArrayList<sqlDTO> sqlDto,
			TargetDataElements tgtDataElems, String compName) 
	{
		
		for(int i = 0 ; i < colsList.size() ; i++)
		{
			SQLElement sqlElem = new SQLElement();
			if(colsList.get(i) instanceof SQLElement)
				sqlElem = (SQLElement) colsList.get(i);
			
			if(sqlElem.name != null)
			{
				if(sqlElem.name.contains("*"))
					sqlElem.name = sqlElem.name.replace("*", " * ");
				
				ArrayList<Token> tknVal = FunctionTypeConv.getTokens(sqlElem.name);
				
			    if(tknVal.size() > 1)
			    {
			    	boolean checkForAggr = checkForAggrTrans(tknVal.get(0));
			    	if(!checkForAggr)
			    	{
			    		crateTargetDataElemsForExpr(sqlElem.aliasName, tgtDataElems, sqlDto, tknVal, sqlElem.name, compName, colsList);
			    		for(int i2 = 0 ; i2 < sqlDto.size() ; i2++ )
			    		{
			    			if(sqlDto.get(i2).compName != null) // check Null
			    			{
				    			if(sqlDto.get(i2).transType.equalsIgnoreCase("Expression") && 
				    					sqlDto.get(i2).compName.equalsIgnoreCase(compName))
				    			{
				    				if(!sqlDto.get(i2).sqlElems.contains(sqlElem))
				    					sqlDto.get(i2).sqlElems.add(sqlElem);
				    			}
			    			}
			    		}
			    	}
			    }
			    else
			    {
			    	for(int i2 = 0 ; i2 < sqlDto.size() ; i2++ )
		    		{
		    			if(sqlDto.get(i2).transType.equalsIgnoreCase("MainSource") || sqlDto.get(i2).transType.equalsIgnoreCase("Source"))
		    			{
		    				if(compName != null) // check Null
		    				{
			    				String srcCompName = compName.substring(compName.indexOf("expr_")+5, compName.length());
			    				if(sqlDto.get(i2).compName.equalsIgnoreCase(srcCompName))
			    				{
				    				if(!sqlDto.get(i2).sqlElems.contains(sqlElem))
				    				{
				    					sqlDto.get(i2).sqlElems.add(sqlElem);
				    				}
			    				}
		    				}
		    			}
		    		}
			    }
			}
		}
		
	}

	private static void crateTargetDataElemsForExpr(String aliasName, TargetDataElements tgtDataElems,
			ArrayList<sqlDTO> sqlDto, ArrayList<Token> tknVal, String name, String compName, ArrayList<SQLObject> colsList) 
	{
		TargetDataElementType tgtElemType = new TargetDataElementType();
		tgtElemType.setTargetDatsetRef(compName);
		if(aliasName != null)
			tgtElemType.setTargetDataElementRef(aliasName);
		
		 tgtElemType.setDataType("string");
		 tgtElemType.setPrecision(50);
		 tgtElemType.setScale(0);
		 String updatedVal = getUpdatedCondVal(name, sqlDto, colsList);
		 TransformationRule transRule = new TransformationRule();
		 transRule.setComponentName(compName);
		 transRule.setOriginalExpression(name);
		 transRule.setRuleName("General - Expression");
		 Parameter param = new Parameter();
		 param.setName("Expression");
		 param.setType("Exp");
		 param.setValue(updatedVal);
		 transRule.getParameter().add(param);
		 tgtElemType.getTransformationRule().add(transRule);
		 
		 tgtDataElems.getTargetDataElement().add(tgtElemType);
		 
	}

	private static boolean checkForAggrTrans(Token token) 
	{
		for(int i = 0 ; i < FnMapRules.getDSToolRules().size(); i++)
		{
			FnRule fnRule = FnMapRules.getDSToolRules().get(i);
			if(fnRule.getGenFnType().name().contains("AGG_") && fnRule.getToolFnType().equalsIgnoreCase(token.data))
				return true;
		} 
		return false;
	}
	
	private static String getAggrTransRule(Token token) 
	{
		for(int i = 0 ; i < FnMapRules.getDSToolRules().size(); i++)
		{
			FnRule fnRule = FnMapRules.getDSToolRules().get(i);
			if(fnRule.getGenFnType().name().contains("AGG_") && fnRule.getToolFnType().equalsIgnoreCase(token.data))
				return getProperTransRuleForAggr(fnRule.getToolFnType());
		} 
		return null;
	}

	private static String getProperTransRuleForAggr(String toolFnType) 
	{
	    if(toolFnType.equalsIgnoreCase("dsMin"))
	    	toolFnType = "Aggregate - Minimum";
	    else if(toolFnType.equalsIgnoreCase("dsAvg"))
	    	toolFnType = "Aggregate - Average";
	    else if(toolFnType.equalsIgnoreCase("dsMax"))
	    	toolFnType = "Aggregate - Maximum";
	    else if(toolFnType.equalsIgnoreCase("dsSum"))
	    	toolFnType = "Aggregate - Sum";
	    else if(toolFnType.equalsIgnoreCase("dsCount"))
	    	toolFnType = "Aggregate - Count";
	    else if(toolFnType.equalsIgnoreCase("dsFirstValue"))
	    	toolFnType = "Aggregate - First";
	    else if(toolFnType.equalsIgnoreCase("dsLast"))
	    	toolFnType = "Aggregate - Last";
	    else if(toolFnType.equalsIgnoreCase("dsVariance"))
	    	toolFnType = "Aggregate - Variance";
	    else
	    	toolFnType = "Aggregator - Expression";
	    
		return toolFnType;
	}

	private static String getprevCompNameForExpr(ArrayList<sqlDTO> sqlDto, String compName) 
	{
		String prevCompName = "";
		String transType = "";
		LinkedList<String> jnrCompLst = new LinkedList<String>();
		for(int i = 0 ; i < sqlDto.size(); i++)
		{

			if(sqlDto.get(i).subSQLType != null)
			{
				if(sqlDto.get(i).subSQLType.equalsIgnoreCase("SubQueryPrevComp") && sqlDto.get(i).subQuery)
				{
					prevCompName = sqlDto.get(i).compName;
					sqlDto.get(i).subQuery = false;
				}
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Filter"))
		    {
				prevCompName = sqlDto.get(i).compName;
				transType =  sqlDto.get(i).transType;
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Joiner"))
				jnrCompLst.add(sqlDto.get(i).compName);
			else if(sqlDto.get(i).transType.equalsIgnoreCase("MainSource"))
				prevCompName = sqlDto.get(i).compName;
		}
		if(prevCompName.equalsIgnoreCase(""))
		{
			if(jnrCompLst.size() > 0)
				prevCompName = jnrCompLst.get(jnrCompLst.size()-1);
		}
		if(transType.equalsIgnoreCase("Filter"))
		{
			for(int i = 0 ; i < sqlDto.size(); i++)
			{
				if(sqlDto.get(i).transType.equalsIgnoreCase("Filter"))
				{
					sqlDto.get(i).nextCompName = compName;
				}
			}
		}
		return prevCompName;
		
	}

	private static boolean checkExprAggrTrans(ArrayList<SQLObject> colsList, ArrayList<sqlDTO> sqlDto) 
	{
		if(colsList.size() > 0)
		{
			for(int i = 0 ; i <  colsList.size(); i++)
			{
				SQLElement sqlElem = new SQLElement();
				
				if(colsList.get(i) instanceof SQLElement)
					sqlElem = (SQLElement) colsList.get(i);
				
				if(sqlElem.name != null)
				{
					if(sqlElem.name.contains("*"))
					{
						sqlElem.name = sqlElem.name.replace("*", " * ");
					}
					ArrayList<Token> tknVal = FunctionTypeConv.getTokens(sqlElem.name);
					
					if(tknVal.size() > 1)
						return true;
				}
			}
		}
		
		return false;
	}

	private static void createRemoveDuplicateTrans(SQLQuery sqlQuery, ArrayList<SQLObject> colsList,
			DataTransformations dataTrans, ArrayList<String> tables, ArrayList<String> uniqueCompNamesList) 
	{
		
		
	}

	private static void createUnionTrans(SQLQuery sqlQuery, SQLUnion setOperation, DataTransformations dataTrans,
			ArrayList<String> tables, ArrayList<String> uniqueCompNamesList, TargetDataElements tgtDataElems, ArrayList<sqlDTO> sqlDto, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap, boolean checkSubQuery, LinkedHashMap<String, sqlDTO> sqlDtoList, LinkedHashMap<String, SystemEntity> sysEntityMap, String getCatalogName, int currentDbId, boolean checkJoinerSubQuery) 
	{
	    if(setOperation != null)
	    {
	    	DataTransformation dataTran = new DataTransformation();
	    	String compName = getCompNameForTrans(sqlDto, "Union_", tgtDataElemnsMap, sqlQuery);
			
			if(compName == null)
				compName = "Union_"+sqlQuery.compName;
			
			compName = Utils.getUniqueCompName(compName, uniqueCompNamesList);
			dataTran.setName(compName);
			String prevCompName = getprevCompNameForUnion(sqlDto, compName);
			dataTran.setTransformationType("Union Transformation");
			dataTran.setPrevComponentRef(prevCompName);
			
			getSrcDTOObjeds(sqlDto, "Union", compName, compName , compName);
			
			if(setOperation.dmlQuery != null)
			{
				SQLQuery unionSqlQuery = new SQLQuery();
				
				if(setOperation.dmlQuery instanceof SQLQuery)
					unionSqlQuery = (SQLQuery) setOperation.dmlQuery;
				
				fillTransFormationDtls(dataTrans, unionSqlQuery, tables, tgtDataElemnsMap, 
						getCatalogName, uniqueCompNamesList, tgtDataElems, checkSubQuery, sqlDtoList, sysEntityMap, currentDbId, checkJoinerSubQuery);
				
			}
			
			
			dataTrans.getDataTransformation().add(dataTran);
			 
			String prevComp = updatePrevCompNameForUnionTrans(dataTrans, compName);
			
			fillUnionTransDtls(dataTran, setOperation, prevComp);
			
	 	   
	    }
		
	}

	private static void fillUnionTransDtls(DataTransformation dataTran, SQLUnion setOperation, String prevComp)
	{
		String unionTyp = setOperation.type;
		
		SetOperation setOpr = new SetOperation();
		setOpr.setOperation(unionTyp);
		
		InputGroups ipGrps = new InputGroups();
		
		String[] tempArray = prevComp.split(";");
		
		for (int i = 0; i < tempArray.length; i++) 
		{
			String compName = tempArray[i];
			
			InputGroup ipGrp = new InputGroup();
			
			ipGrp.setName("Group"+i);
			ipGrp.setOrder(i);
			ipGrp.setPrevComponentRef(compName);
			
			ipGrps.getInputGroup().add(ipGrp);
			
		}
		setOpr.setInputGroups(ipGrps);
		
		dataTran.setSetOperation(setOpr);
	}

	private static String updatePrevCompNameForUnionTrans(DataTransformations dataTrans, String compName) 
	{
		if(dataTrans.getDataTransformation() != null)
		{
			for(int i = 0 ; i < dataTrans.getDataTransformation().size() ; i++)
			{
				if(dataTrans.getDataTransformation().get(i).getName() != null)
				{
					if(dataTrans.getDataTransformation().get(i).getTransformationType() != null)
					{
						if(dataTrans.getDataTransformation().get(i).getTransformationType().equalsIgnoreCase("Union Transformation") && 
								dataTrans.getDataTransformation().get(i).getName().equalsIgnoreCase(compName))
						{
							String orgPrevComp = dataTrans.getDataTransformation().get(i).getPrevComponentRef();
							String prevCompName = dataTrans.getDataTransformation().get(i-1).getName();
							
							dataTrans.getDataTransformation().get(i).setPrevComponentRef(orgPrevComp+ ";" +prevCompName);
							
							String prevCompNames = orgPrevComp+ ";" +prevCompName;
							return prevCompNames;
						}
					}
				}
			}
		}
		return null;
		
	}

	private static String getprevCompNameForUnion(ArrayList<sqlDTO> sqlDto, String compName) 
	{
		String prevCompName = "";
		String transType = "";
		LinkedList<String> jnrCompLst = new LinkedList<String>();
		for(int i = 0 ; i < sqlDto.size(); i++)
		{
			if(sqlDto.get(i).subSQLType != null)
			{
				if(sqlDto.get(i).subSQLType.equalsIgnoreCase("SubQueryPrevComp") && sqlDto.get(i).subQuery)
				{
					prevCompName = sqlDto.get(i).compName;
					transType =  sqlDto.get(i).transType;
					sqlDto.get(i).subQuery = false;
				}
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Sorter"))
			{
				prevCompName = sqlDto.get(i).compName;
				transType =  sqlDto.get(i).transType;
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("HavingFilter"))
			{
				prevCompName = sqlDto.get(i).compName;
				transType =  sqlDto.get(i).transType;
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Aggregator"))
			{
				prevCompName = sqlDto.get(i).compName;
				transType =  sqlDto.get(i).transType;
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Expression"))
			{
				prevCompName = sqlDto.get(i).compName;
				transType =  sqlDto.get(i).transType;
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Filter"))
			{
				prevCompName = sqlDto.get(i).compName;
				transType =  sqlDto.get(i).transType;
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Joiner"))
				jnrCompLst.add(sqlDto.get(i).compName);
			else if(sqlDto.get(i).transType.equalsIgnoreCase("MainSource"))
				prevCompName = sqlDto.get(i).compName;
		}
		if(prevCompName != null)
		{
			if(prevCompName.equalsIgnoreCase(""))
			{
				if(jnrCompLst.size() > 0)
					prevCompName = jnrCompLst.get(jnrCompLst.size()-1);
			}
		}
		
		if(transType.equalsIgnoreCase("Filter"))
		{
			for(int i = 0 ; i < sqlDto.size(); i++)
			{
				if(sqlDto.get(i).transType.equalsIgnoreCase("Filter"))
				{
					sqlDto.get(i).nextCompName = compName;
				}
			}
		}
		else if(transType.equalsIgnoreCase("HavingFilter"))
		{
			for(int i = 0 ; i < sqlDto.size(); i++)
			{
				if(sqlDto.get(i).transType.equalsIgnoreCase("HavingFilter"))
				{
					sqlDto.get(i).nextCompName = compName;
				}
			}
		}
		return prevCompName;
	}
	
	private static String getprevCompNameForJoinerSubQuery(ArrayList<sqlDTO> sqlDto, String compName) 
	{
		String prevCompName = "";
		for(int i = 0 ; i < sqlDto.size(); i++)
		{
			if(sqlDto.get(i).transType.equalsIgnoreCase("Expression"))
				prevCompName = sqlDto.get(i).compName;
		}
		
		return prevCompName;
	}
	
	

	private static void createSourceTrans(SQLQuery sqlQuery, ArrayList<SQLObject> tableList,
			ArrayList<SQLObject> colsList, DataTransformations dataTrans, ArrayList<String> tables, ArrayList<String> uniqueCompNamesList, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap, 
			String getCatalogName, ArrayList<sqlDTO> sqlDto, TargetDataElements tgtDataElems, boolean checkSubQuery, LinkedHashMap<String, sqlDTO> sqlDtoList, LinkedHashMap<String, SystemEntity> sysEntityMap, int currentDbId, boolean checkJoinerSubQuery)
	{
		if(tableList.size() > 0)
		{
			for(int i = 0 ; i < tableList.size(); i++)
			{
				SQLElement sqlElem = new SQLElement();
				if(tableList.get(i) instanceof SQLElement)
					sqlElem = (SQLElement) tableList.get(i);
				
				SQLQuery sqlSubQuery = new SQLQuery();
				if(tableList.get(i) instanceof SQLQuery)
					sqlSubQuery = (SQLQuery) tableList.get(i);
				
				if(sqlElem.name != null)
				{
					String transType = "Source Definition";
					
					String compName = Utils.getUpdatedCompName(sqlElem.name);
					compName = Utils.getUniqueCompName(compName, uniqueCompNamesList);
					sqlElem.compName = compName; 
					
					SourceDataset srcDataSet = new SourceDataset();
					srcDataSet.setSystemCatalogRef(getCatalogName);
					
					String entityName = "";
					if(sqlElem.name.contains("."))
						entityName = sqlElem.name.substring(sqlElem.name.lastIndexOf(".")+1, sqlElem.name.length());
					else
						entityName = sqlElem.name;
					
					entityName = entityName.replace("$", "").replace("{", "").replace("}", "");
					
					srcDataSet.setSystemEntityRef(entityName);
					
					srcDataSet.setFetchDistinct(false);
					srcDataSet.setIncludePassThruTx(false);
					
					updateColslistForMainSource(colsList, compName);
					
					getSrcDTOObjeds(sqlDto, "MainSource", compName, sqlElem.aliasName, sqlElem.name);
					
					createSrcDataTransDtls(compName, dataTrans, transType, null, uniqueCompNamesList, srcDataSet);
					
					tables.add(sqlElem.name);
				}
				
				if(sqlSubQuery != null)
				{
					if(sqlSubQuery.dmlType != null)
					{
						if(sqlSubQuery.colsList.size() > 0)
						{
							checkSubQuery = true;
							fillTransFormationDtls(dataTrans, sqlSubQuery, tables, tgtDataElemnsMap, getCatalogName, uniqueCompNamesList, tgtDataElems, checkSubQuery, sqlDtoList, sysEntityMap, currentDbId, checkJoinerSubQuery);
						}
					}
				}
			}
			
		}
		
	}



	private static void updateColslistForMainSource(ArrayList<SQLObject> colsList, String compName)
	{
		
			for(int j = 0 ; j < colsList.size() ; j++)
			{
				SQLElement sqlElem = new SQLElement();
				
				if(colsList.get(j) instanceof SQLElement)
					sqlElem = (SQLElement) colsList.get(j);
				
				if(sqlElem.name != null)
				{
					ArrayList<Token> tokenVal = FunctionTypeConv.getTokens(sqlElem.name);
					
					if(tokenVal.size() == 1)
					{
						//sqlElem.name = getUpdatedCondVal(sqlElem.name, sqlDto);
						
						if(sqlElem.aliasName == null)
						{
							if(tokenVal.get(0).type.name().equalsIgnoreCase("VAR"))
							{
								sqlElem.aliasName = sqlElem.name;
								sqlElem.name = compName+"."+sqlElem.name;
							}
						}
					}
				}
			}
		
	}

	private static void addColsListForMainSource(ArrayList<sqlDTO> sqlDto, ArrayList<SQLObject> colsList) 
	{
		
		for (int i = 0; i < sqlDto.size(); i++) 
		{
			if(sqlDto.get(i).transType.equalsIgnoreCase("MainSource"))
			{
				for(int j = 0 ; j < colsList.size() ; j++)
				{
					SQLElement sqlElem = new SQLElement();
					
					if(colsList.get(j) instanceof SQLElement)
						sqlElem = (SQLElement) colsList.get(j);
					
					if(sqlElem.name != null)
					{
						ArrayList<Token> tokenVal = FunctionTypeConv.getTokens(sqlElem.name);
						
						if(tokenVal.size() == 1)
						{
							sqlElem.name = getUpdatedCondVal(sqlElem.name, sqlDto, colsList);
							if(sqlElem.aliasName == null)
							{
								if(sqlElem.name.contains("."))
								{
									sqlElem.aliasName = sqlElem.name.substring(sqlElem.name.lastIndexOf(".")+1, sqlElem.name.length());
								}
							}
							sqlDto.get(i).sqlElems.add(sqlElem);
						}
					}
				}
			}
		}
	}

	private static void createSrcDataTransDtls(String compName, DataTransformations dataTrans, String transType, String prevComp, ArrayList<String> uniqueCompNamesList, SourceDataset srcDataSet) 
	{
		DataTransformation dataTran = new DataTransformation();
		
		dataTran.setName(compName);
		dataTran.setTransformationType(transType);
		
		if(prevComp != null)
			dataTran.setPrevComponentRef(prevComp);
		
		dataTran.setSourceDataset(srcDataSet);
		dataTrans.getDataTransformation().add(dataTran);
		
	}

	private static void createJoinerTrans(SQLQuery sqlQuery, SQLJoin joinCond,
			DataTransformations dataTrans, ArrayList<String> tables, ArrayList<String> uniqueCompNamesList, ArrayList<sqlDTO> sqlDto, 
			LinkedHashMap<String, sqlDTO> tgtDataElemnsMap, String getCatalogName, TargetDataElements tgtDataElems, boolean checkSubQuery, LinkedHashMap<String, sqlDTO> sqlDtoList, LinkedHashMap<String, SystemEntity> sysEntityMap, int currentDbId, boolean checkJoinerSubQuery) 
	{
		if(joinCond != null)
		{
			String prevComp = getPrevCompNameForJoiner(sqlDto);
			
			ArrayList<String> checkExpr = new ArrayList<String>();
			
			LinkedList<String> dtlCompsList = new LinkedList<String>();
			 
            DataTransformation dataTran = new DataTransformation();
            
			dataTran.setTransformationType("Multi Joiner Transformation");
			
			Multijoiner multiJnr = new Multijoiner();

			boolean checkMultiJoin = false;
			
			String jnrCompName = "";
			if(!checkMultiJoin)
			{
				jnrCompName = "jnr_"+prevComp; 
				jnrCompName = Utils.getUniqueCompName(jnrCompName, uniqueCompNamesList);
			}
		
			if(prevComp == null)
			{
				prevComp = getCompNameForJoinerTrans(sqlDto, "", tgtDataElemnsMap, sqlQuery);
			}
	
			 ArrayList<String> compNameLst = new ArrayList<String>();
			 
			 fillJoinTransDtls(joinCond , multiJnr, dataTrans,prevComp, tables, tgtDataElemnsMap, getCatalogName, 
						sqlQuery, sqlDto, tgtDataElems, uniqueCompNamesList, checkMultiJoin, dataTran, checkExpr, dtlCompsList, checkSubQuery, sqlDtoList, sysEntityMap, currentDbId, checkJoinerSubQuery, compNameLst);
			 
			 if(compNameLst.size() > 0)// for Joiner Subquery
			 {
				 jnrCompName = compNameLst.get(0);
			 }
			 
			 if(checkExpr.size() > 0)
		     {
				String exprComp = createExpressionTransForJoiner(dataTrans,prevComp, uniqueCompNamesList);
				prevComp = exprComp;
		     }
			 
			dataTran.setName(jnrCompName);
 			getSrcDTOObjeds(sqlDto, "Joiner",jnrCompName, jnrCompName , jnrCompName);
			 
			String dtlComps = dtlCompsList.toString().replace("[", "").replace("]", "").replace(",", ";").replace(" ", "");
			
			multiJnr.setMasterComponentRef(prevComp);
			
			if(compNameLst.size() > 0)
			{
				dtlComps =  getprevCompNameForJoinerSubQuery(sqlDto, jnrCompName);
			}
			
			dataTran.setPrevComponentRef(prevComp+";"+dtlComps);
			
			dataTran.setMultijoiner(multiJnr);
			
			dataTrans.getDataTransformation().add(dataTran);
			
			
		}
	}

	private static void fillJoinTransDtls(SQLJoin join, Multijoiner multiJnr, DataTransformations dataTrans, String prevComp, ArrayList<String> tables, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap, String getCatalogName, SQLQuery sqlQuery, ArrayList<sqlDTO> sqlDto, TargetDataElements tgtDataElems, ArrayList<String> uniqueCompNamesList, 
			                boolean checkMultiJoin, DataTransformation dataTran, ArrayList<String> checkExpr, LinkedList<String> dtlCompsList, boolean checkSubQuery, LinkedHashMap<String, sqlDTO> sqlDtoList, LinkedHashMap<String, SystemEntity> sysEntityMap, int currentDbId, boolean checkJoinerSubQuery, ArrayList<String> compNameLst) 
	{
		    
			SQLJoinCond sqlJoinCond = new SQLJoinCond();
		    if(join.joinCondDtls instanceof SQLJoinCond) 
				sqlJoinCond = (SQLJoinCond) join.joinCondDtls;
		   
		    String joinType = join.joinType;
		    
			if(joinType.equalsIgnoreCase("join"))
				joinType = "Inner";
			
			if(sqlJoinCond.childTable != null)
			{
				SQLElement sqlElemChildTable = new SQLElement();
				if(sqlJoinCond.childTable instanceof SQLElement)
					sqlElemChildTable = (SQLElement) sqlJoinCond.childTable;
				
				SQLQuery sqlQueryChildTbl = new SQLQuery();
				if (sqlJoinCond.childTable instanceof SQLQuery)
					sqlQueryChildTbl = (SQLQuery) sqlJoinCond.childTable;
				
				DataTransformation SrcDataTran = new DataTransformation();
				SrcDataTran.setTransformationType("Source Definition");
				SourceDataset srcDataSet = new SourceDataset();
				srcDataSet.setSystemCatalogRef(getCatalogName);
				
				String srcPrevComp = "";
				
	    		DetailComponent dtlJnr = new DetailComponent();
	    		dtlJnr.setJoinType(joinType);
	    		
	    		if(sqlQuery.OrderBy.size() > 0)
	    			dtlJnr.setUseSortedInput(true);
	    		else
	    			dtlJnr.setUseSortedInput(false);
	    		
	    		multiJnr.getDetailComponent().add(dtlJnr);
	    		
	    		
			    if(sqlElemChildTable != null)
			    {
			    	if(sqlElemChildTable.name != null)
			    	{
			    		String compName = Utils.getUpdatedCompName(sqlElemChildTable.name);
			    		compName = Utils.getUniqueCompName(compName, uniqueCompNamesList);
			    		sqlElemChildTable.compName = compName; 
			    		
			    		getJoinCondition(join.joinCond, dtlJnr, compName, sqlElemChildTable.aliasName,sqlElemChildTable.name, 
			    							sqlDto, checkExpr, tgtDataElems, prevComp, uniqueCompNamesList, tgtDataElemnsMap);
			    		
			    		
			    		SrcDataTran.setName(compName);
		    			
		    			srcPrevComp = compName;
		    				
		    			dtlJnr.setComponentRef(srcPrevComp);
		    				
		    			getSrcDTOObjeds(sqlDto, "Source", compName, sqlElemChildTable.aliasName, sqlElemChildTable.name);
			    			    		
			    		tables.add(sqlElemChildTable.name);
			    		
			    		String entityName = "";
			    		if(sqlElemChildTable.name.contains("."))
							entityName = sqlElemChildTable.name.substring(sqlElemChildTable.name.lastIndexOf(".")+1, sqlElemChildTable.name.length());
						else
							entityName = sqlElemChildTable.name;
			    		
			    		entityName = entityName.replace("$", "").replace("{", "").replace("}", "");
			    		
			    		srcDataSet.setSystemEntityRef(entityName);
			    		
			    		srcDataSet.setFetchDistinct(false);
						srcDataSet.setIncludePassThruTx(false);
						SrcDataTran.setSourceDataset(srcDataSet);
						dataTrans.getDataTransformation().add(SrcDataTran);
			    	}
			    }
			    if(sqlQueryChildTbl != null)
			    {
			    	if(sqlQueryChildTbl.dmlType != null)
			    	{
			    			
		    			checkSubQuery = true;
		    			checkJoinerSubQuery = true;
						fillTransFormationDtls(dataTrans, sqlQueryChildTbl, tables, tgtDataElemnsMap, getCatalogName, uniqueCompNamesList, tgtDataElems, checkSubQuery, 
								sqlDtoList, sysEntityMap, currentDbId, checkJoinerSubQuery);
						
//						 String compName = sqlQueryChildTbl.aliasName;

//			    		 compName = Utils.getUniqueCompName(compName, uniqueCompNamesList);
			    		 String dtlCompName = getCompNameForTrans(sqlDto, "", tgtDataElemnsMap, sqlQuery);
			    		 
			    		 dtlJnr.setComponentRef(dtlCompName);
			    		 sqlQueryChildTbl.compName = dtlCompName;
			    		
			    		 getJoinCondition(join.joinCond, dtlJnr, dtlCompName, sqlQueryChildTbl.aliasName, null , 
			    						sqlDto, checkExpr, tgtDataElems, prevComp, uniqueCompNamesList, tgtDataElemnsMap);
			    		
						 String jnrCompName = getCompNameForTrans(sqlDto, "jnr_", tgtDataElemnsMap, sqlQuery);
						 jnrCompName = Utils.getUniqueCompName(jnrCompName, uniqueCompNamesList);
						 
						// prevComp = jnrCompName.substring(jnrCompName.indexOf("jnr_")+4, jnrCompName.length());
						compNameLst.add(jnrCompName);
						
		    			//getSrcDTOObjeds(sqlDto, "Source", compName, sqlQueryChildTbl.aliasName, null);
		    			
			    		//ReportLogInfo rpLogInfo = new ReportLogInfo();
			    		//SQLValue sqlVal = QueryGenerator.fillSQLStatementForDbs(sqlQueryChildTbl, 0, 0, rpLogInfo, null);
			    		// As of now I have add the query in source We have to recursively call this subquery
			    		
			    		//srcDataSet.setSqlQuery(sqlVal.sqlValue);
			    		
			    	}
			    }
			    
			    dtlCompsList.add(srcPrevComp);
				
				if(join.multiJoinTbl != null)
				{
					SQLJoin multiJoin = (SQLJoin) join.multiJoinTbl;
					checkMultiJoin = true;
					
					fillJoinTransDtls(multiJoin, multiJnr, dataTrans, prevComp, tables, tgtDataElemnsMap, getCatalogName, sqlQuery, sqlDto, tgtDataElems,
											uniqueCompNamesList, checkMultiJoin, dataTran, checkExpr, dtlCompsList, checkSubQuery, sqlDtoList, sysEntityMap, currentDbId, checkJoinerSubQuery, compNameLst);
				}
			}
		
	}
	
	private static String createExpressionTransForJoiner(DataTransformations dataTrans, String prevComp, ArrayList<String> uniqueCompNamesList)
	{
	    DataTransformation dataTran = new DataTransformation();
	    String compName = "exprmstr_"+prevComp;
	    compName = Utils.getUniqueCompName(compName, uniqueCompNamesList);
	    dataTran.setName(compName);
	    dataTran.setTransformationType("Expression");
	    dataTran.setPrevComponentRef(prevComp);
	    dataTrans.getDataTransformation().add(dataTran);
	    
		return compName;
	}

	private static void getSrcDTOObjeds(ArrayList<sqlDTO> sqlDto, String transTyp, String compName, String aliasName, String name) 
	{
		sqlDTO sqlSrcDto = new sqlDTO();
		sqlSrcDto.transType = transTyp;
		sqlSrcDto.compName = compName;
		sqlSrcDto.originalTableName = name;
		if(aliasName != null)
			sqlSrcDto.aliasName = aliasName;
		
		sqlDto.add(sqlSrcDto);
		
	}

	private static void getJoinCondition(List<SQLObject> joinCondSQ, DetailComponent joiner, String compName, String aliasName,
			String elemName, ArrayList<sqlDTO> sqlDto, ArrayList<String> checkExpr, TargetDataElements tgtDataElems, String prevComp, ArrayList<String> uniqueCompNamesList, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap) 
	{
		List<JoinCondition> joinCondList = new ArrayList<JoinCondition>();
		
		for(int i = 0; i < joinCondSQ.size(); i++) 
		{
			JoinCondition jnCond = new JoinCondition();
			MasterDataElement mstrDataElem = new MasterDataElement();
			DetailDataElement dtlDataElem = new DetailDataElement();
			
			SQLExpr sqlExpr = new SQLExpr();
			if(joinCondSQ.get(i) instanceof SQLExpr)
				sqlExpr = (SQLExpr) joinCondSQ.get(i);
			
			String condition = sqlExpr.value;
			
			ArrayList<Token> condTkns = FunctionTypeConv.getTokens(condition);
			
			for(int j = 0 ; j < condTkns.size(); j++)
			{	
				getMstrDtlCondsFromToken(condTkns.get(j), dtlDataElem, mstrDataElem, sqlDto, aliasName,
											elemName, compName, checkExpr ,tgtDataElems, i , prevComp, uniqueCompNamesList, tgtDataElemnsMap);	
			} 
			
			jnCond.setMasterDataElement(mstrDataElem);
			jnCond.setDetailDataElement(dtlDataElem);
			joinCondList.add(jnCond);
		}
		joiner.getJoinCondition().addAll(joinCondList);
	}
	
	private static void getMstrDtlCondsFromToken(Token bfrToken, DetailDataElement dtlDataElem,
			MasterDataElement mstrDataElem, ArrayList<sqlDTO> sqlDto, String aliasName, String elemName, String compName, ArrayList<String> checkExpr, TargetDataElements tgtDataElems,
			int countVal, String prevComp, ArrayList<String> uniqueCompNamesList, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap) 
	{
		
		if(bfrToken.type.name().equalsIgnoreCase("VARWITHDOT"))
		{
			String name = bfrToken.data.substring(0,bfrToken.data.lastIndexOf("."));
			String colName =  bfrToken.data.substring(bfrToken.data.lastIndexOf(".")+1 , bfrToken.data.length());
			if(aliasName != null)
			{
				if(name.equalsIgnoreCase(aliasName))
				{
					dtlDataElem.setDataElementName(colName);
					dtlDataElem.setComponentRef(compName);
				}
			}
			else if(name.equalsIgnoreCase(elemName))
			{
				dtlDataElem.setDataElementName(colName);
				dtlDataElem.setComponentRef(compName);
			}
				
			getMasterCondForJnr(name, colName, sqlDto, mstrDataElem, tgtDataElemnsMap);
			
		}
		else if(bfrToken.type.name().equalsIgnoreCase("STRCONSTANTS") || bfrToken.data.equalsIgnoreCase("null"))
		{
			checkExpr.add(bfrToken.data);
			
			String colName = "";
			
			if(bfrToken.data.equalsIgnoreCase("null"))
				colName = "null_Value"+countVal;
			else if(bfrToken.type.name().equalsIgnoreCase("STRCONSTANTS"))
				colName = "str_Constants"+countVal;
			
			colName = Utils.getUniqueCompName(colName, uniqueCompNamesList);
			
			String expCompName = "exprmstr_"+prevComp;
			createDummyExprForJoiner(bfrToken.data, tgtDataElems,expCompName, colName);
			mstrDataElem.setComponentRef(expCompName);
			mstrDataElem.setDataElementName(colName);
		}
		
	}

	private static void createDummyExprForJoiner(String data, TargetDataElements tgtDataElems, String exprCompName, String colName) 
	{
		 TargetDataElementType tgtElemType = new TargetDataElementType();
		
		 tgtElemType.setTargetDatsetRef(exprCompName);
		 tgtElemType.setTargetDataElementRef(colName);
		 tgtElemType.setDataType("string");
		 tgtElemType.setPrecision(50);
		 tgtElemType.setScale(0);
		 TransformationRule transRule = new TransformationRule();
		 transRule.setComponentName(exprCompName);
		 transRule.setOriginalExpression(data);
		 transRule.setRuleName("General - Expression");
		 Parameter param = new Parameter();
		 param.setName("Expression");
		 param.setType("Exp");
		 param.setValue(data);
		 transRule.getParameter().add(param);
		 tgtElemType.getTransformationRule().add(transRule);
		 
		 tgtDataElems.getTargetDataElement().add(tgtElemType);
		
	}

	private static void getMasterCondForJnr(String name, String colName, ArrayList<sqlDTO> sqlDto, MasterDataElement mstrDataElem, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap) 
	{
		for(int j1 = 0 ; j1 < sqlDto.size(); j1++)
		{
			if(sqlDto.get(j1).transType.equalsIgnoreCase("MainSource") || 
					sqlDto.get(j1).transType.equalsIgnoreCase("Source"))
			{
				if(sqlDto.get(j1).aliasName != null)
				{
					if(sqlDto.get(j1).aliasName.equalsIgnoreCase(name))
					{
						mstrDataElem.setComponentRef(sqlDto.get(j1).compName);
						mstrDataElem.setDataElementName(colName);
					}
					
				}
				else
				{
					if(sqlDto.get(j1).originalTableName.equalsIgnoreCase(name))
					{
						mstrDataElem.setComponentRef(sqlDto.get(j1).compName);
						mstrDataElem.setDataElementName(colName);
					}
				}
				
				if(mstrDataElem.getComponentRef() == null)
				{
					mstrDataElem.setComponentRef(sqlDto.get(j1).compName);
					mstrDataElem.setDataElementName(colName);
				}
			}
			else if(sqlDto.get(j1).subSQLType.equalsIgnoreCase("SubQueryPrevComp") && sqlDto.get(j1).subQuery && (mstrDataElem.getComponentRef() == null) )
			{
				ArrayList<sqlDTO> srcElems = new ArrayList<sqlDTO>();
				
				for(Entry<String, sqlDTO> tgtElemnsMap : tgtDataElemnsMap.entrySet())
				{
					String keyVal = tgtElemnsMap.getKey();
					if(!tgtElemnsMap.getValue().transType.equalsIgnoreCase("Target"))
						srcElems.add(tgtElemnsMap.getValue());
				}
				
				String srcCompDtl = "";
				String masterColName = name + "." + colName;
				ArrayList<Token> tkn = FunctionTypeConv.getTokens(masterColName);
				String srcComp = processSubQuery.getSrcColsFromSubQuery(srcElems, tkn, srcCompDtl);
				
				if(srcComp.contains("."))
				{
					String mstrcompName = srcComp.substring(0, srcComp.indexOf("."));
					String mastercolName = srcComp.substring(srcComp.indexOf(".")+1);
 					if(colName.equalsIgnoreCase(mastercolName))
 					{
 						mstrDataElem.setComponentRef(mstrcompName);
						mstrDataElem.setDataElementName(colName);
 					}
				}
			}
			
		}
		
	}

	private static String getPrevCompNameForJoiner(ArrayList<sqlDTO> sqlDto) 
	{
		String prevCompName = null;
		for(int i = 0 ; i < sqlDto.size() ; i++)
		{
			if(sqlDto.get(i).subSQLType != null)
			{
				if(sqlDto.get(i).subSQLType.equalsIgnoreCase("SubQueryPrevComp") && sqlDto.get(i).subQuery)
				{
					prevCompName = sqlDto.get(i).compName;
					sqlDto.get(i).subQuery = false;
				}
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("MainSource"))
				prevCompName = sqlDto.get(i).compName;
		}
		
		return prevCompName;
	}

	private static void createSorterTrans(SQLQuery sqlQuery,
			ArrayList<SQLObject> orderBy, DataTransformations dataTrans, ArrayList<String> tables, ArrayList<String> uniqueCompNamesList, ArrayList<SQLObject> colsList, 
			ArrayList<sqlDTO> sqlDto, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap, String getCatalogName) 
	{
		DataTransformation dataTran = new DataTransformation();
		String compName = getCompNameForTrans(sqlDto, "sorter_", tgtDataElemnsMap, sqlQuery);
		
		if(compName == null)
			compName = "sorter_"+sqlQuery.compName;
		
		compName = Utils.getUniqueCompName(compName, uniqueCompNamesList);
		dataTran.setName(compName);
		String prevCompName = getprevCompNameForSorter(sqlDto, compName);
		dataTran.setTransformationType("Sorter");
		dataTran.setPrevComponentRef(prevCompName);
		
		getSrcDTOObjeds(sqlDto, "Sorter", compName, compName , compName);
		
		fillSorterTransDtls(dataTran, colsList, orderBy, sqlDto);
		
		dataTrans.getDataTransformation().add(dataTran);
		
	}

	private static void fillSorterTransDtls(DataTransformation dataTran, ArrayList<SQLObject> colsList, ArrayList<SQLObject> orderBy, ArrayList<sqlDTO> sqlDto)
	{
		int count = 1;
		for(int i = 0 ; i < colsList.size() ; i++)
		{
			SQLElement sqlElem = new SQLElement();
			if(colsList.get(i) instanceof SQLElement)
				sqlElem = (SQLElement) colsList.get(i);
			
			if(sqlElem.name != null)
			{
				if(sqlElem.name.contains("*"))
					sqlElem.name = sqlElem.name.replace("*", " * ");
				
				ArrayList<Token> tknVal = FunctionTypeConv.getTokens(sqlElem.name);
				getUpdatedGrpbyOrdrbyCols(sqlElem, orderBy, count++, tknVal);
			}
		}
		
		Sorter sorter = new Sorter();
		for(int j = 0 ;j < orderBy.size() ; j++)
		{
			SQLElement orderByElem = new SQLElement();
			if(orderBy.get(j) instanceof SQLElement)
				orderByElem = (SQLElement) orderBy.get(j);
			
			if(orderByElem.name != null)
			{
				ArrayList<Token> srtTknVal = FunctionTypeConv.getTokens(orderByElem.name);
				String dataElem = getDataElemName(srtTknVal, sqlDto);
				String componentRef = dataElem.substring(0, dataElem.indexOf("."));
				String srcDataElem = dataElem.substring(dataElem.indexOf(".")+1, dataElem.length());
				SorterKey sorterKey = new SorterKey();
				sorterKey.setKeyComponentRef(componentRef);
				sorterKey.setSortKeyElementRef(srcDataElem);
				
				String sortOrdr = "";
				if(orderByElem.OrderBy != null)
				{
					if(orderByElem.OrderBy.equalsIgnoreCase("asc"))
						sortOrdr = "ASCENDING";
					else if(orderByElem.OrderBy.equalsIgnoreCase("desc"))
						sortOrdr = "DESCENDING";
					else
						sortOrdr = "ASCENDING";
				}
				else
					sortOrdr = "ASCENDING";
				
				sorterKey.setSortOrder(sortOrdr);
				sorter.getSorterKey().add(sorterKey);
			}
		}
		
		dataTran.setSorter(sorter);
		
	}

	private static String getprevCompNameForSorter(ArrayList<sqlDTO> sqlDto, String compName)
	{
		String prevCompName = "";
		String transType = "";
		LinkedList<String> jnrCompLst = new LinkedList<String>();
		for(int i = 0 ; i < sqlDto.size(); i++)
		{
			if(sqlDto.get(i).subSQLType != null)
			{
				if(sqlDto.get(i).subSQLType.equalsIgnoreCase("SubQueryPrevComp") && sqlDto.get(i).subQuery)
				{
					prevCompName = sqlDto.get(i).compName;
					transType =  sqlDto.get(i).transType;
					sqlDto.get(i).subQuery = false;
				}
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("HavingFilter"))
			{
				prevCompName = sqlDto.get(i).compName;
				transType =  sqlDto.get(i).transType;
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Aggregator"))
			{
				transType =  sqlDto.get(i).transType;
				prevCompName = sqlDto.get(i).compName;
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Expression"))
			{
				transType =  sqlDto.get(i).transType;
				prevCompName = sqlDto.get(i).compName;
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Filter"))
			{
				prevCompName = sqlDto.get(i).compName;
				transType =  sqlDto.get(i).transType;
			}
			else if(sqlDto.get(i).transType.equalsIgnoreCase("Joiner"))
				jnrCompLst.add(sqlDto.get(i).compName);
			else if(sqlDto.get(i).transType.equalsIgnoreCase("MainSource"))
				prevCompName = sqlDto.get(i).compName;
		}
		if(prevCompName != null)
		{
			if(prevCompName.equalsIgnoreCase(""))
			{
				if(jnrCompLst.size() > 0)
					prevCompName = jnrCompLst.get(jnrCompLst.size()-1);
			}
		}
		
		if(transType.equalsIgnoreCase("Filter"))
		{
			for(int i = 0 ; i < sqlDto.size(); i++)
			{
				if(sqlDto.get(i).transType.equalsIgnoreCase("Filter"))
				{
					sqlDto.get(i).nextCompName = compName;
				}
			}
		}
		else if(transType.equalsIgnoreCase("HavingFilter"))
		{
			for(int i = 0 ; i < sqlDto.size(); i++)
			{
				if(sqlDto.get(i).transType.equalsIgnoreCase("HavingFilter"))
				{
					sqlDto.get(i).nextCompName = compName;
				}
			}
		}
		return prevCompName;
	}

	private static void createFilterTrans(SQLQuery sqlQuery, SQLExpr whereCond,
			DataTransformations dataTrans, ArrayList<String> tables, ArrayList<String> uniqueCompNamesList, ArrayList<sqlDTO> sqlDto, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap, 
			                      LinkedHashMap<String, sqlDTO> sqlDtoList, ArrayList<SQLObject> colsList, int currentDbId, boolean checkJoinerSubQuery)
	{
		if(whereCond != null)
		{
			DataTransformation dataTran = new DataTransformation();
			String compName = getCompNameForTrans(sqlDto, "fltr_", tgtDataElemnsMap, sqlQuery);
			compName = Utils.getUniqueCompName(compName, uniqueCompNamesList);
			
			dataTran.setName(compName);
			whereCond.compName = compName;
			String prevCompName = getprevCompNameForFltr(sqlDto, checkJoinerSubQuery);
			dataTran.setTransformationType("Filter Transformation");
			dataTran.setPrevComponentRef(prevCompName);
			dataTran.setNextComponentRef("");
			
			getSrcDTOObjeds(sqlDto, "Filter", compName, compName , compName);
			
			boolean checkFlag = false;
			if(whereCond.args.size() > 1)
			{
				Filter fltr = new Filter();
				String fltrCondVal = "";
				for(int i = 0 ; i < whereCond.args.size(); i++)
				{
					SQLExpr sqlExpr = new SQLExpr();
					if(sqlQuery.whereCond.args.get(i) instanceof SQLExpr)
						sqlExpr = (SQLExpr) sqlQuery.whereCond.args.get(i);
					
					// As of now I'm handling for sqlExprVal we have to do the changes for subqueries 
					
					if(sqlExpr.value != null)
					{
						if(sqlExpr.args.size() > 0)
						{
							for(int i2 = 0 ; i2 <sqlExpr.args.size(); i2++)
							{
								SQLExpr sqlExpr_new = new SQLExpr();
								if(sqlExpr.args.get(i2) instanceof SQLExpr)
									sqlExpr_new = (SQLExpr) sqlExpr.args.get(i2);
								
								// As of now I'm handling for sqlExprVal we have to do the changes for subqueries 
								
								if(sqlExpr_new.value != null && (!getInStatements(sqlExpr_new.value)))
								{
								    if(i2 == 0)
									{
										ArrayList<Token> toknVal = FunctionTypeConv.getTokens(sqlExpr_new.value);
										
										if(toknVal.size() > 3)
										{
											if(sqlExpr_new.value.contains("AND") && (!sqlExpr_new.value.contains("ds")))
											{
												sqlExpr_new.value = FunctionTypeConv.getCurrentFunctions(sqlExpr_new.value, currentDbId, ToolTypeConstants.DS_TOOL);
											}
										}
										
										fltrCondVal += " "+getUpdatedCondVal(sqlExpr_new.value, sqlDto, colsList);
									}
									else
									{
										String condTyp = FunctionTypeConv.getCurrentFunctions(sqlExpr_new.type, currentDbId, ToolTypeConstants.DS_TOOL);
									
										if(sqlExpr_new.value.startsWith("dsBetWeen("))
										{
											checkFlag = true;
										}
										
										if(checkFlag)
										{
											if(sqlExpr_new.value.startsWith("dsBetWeen("))
											{
												sqlExpr_new.value = sqlExpr_new.value.substring(0, sqlExpr_new.value.lastIndexOf(")"));
												fltrCondVal += " "+condTyp+" "+getUpdatedCondVal(sqlExpr_new.value, sqlDto, colsList);
												
											}
											else
											{
												fltrCondVal += " "+","+getUpdatedCondVal(sqlExpr_new.value, sqlDto, colsList)+")";
												checkFlag = false;
											}
												
										}
										
										else
											fltrCondVal += " "+condTyp + " "+getUpdatedCondVal(sqlExpr_new.value, sqlDto, colsList);
									}
								}
								else
								{
									if(i == 0)
									{
										fltrCondVal +=" "+getUpdatedCondVal(sqlExpr.value, sqlDto, colsList);
									}
									else
									{
										String condTyp = FunctionTypeConv.getCurrentFunctions(sqlExpr.type, currentDbId, ToolTypeConstants.DS_TOOL);
										fltrCondVal +=" "+ condTyp +" "+ getUpdatedCondVal(sqlExpr.value, sqlDto, colsList);
									}
								}
							}
						}
						else
						{
							if(i == 0)
							{
								ArrayList<Token> toknVal = FunctionTypeConv.getTokens(sqlExpr.value);
								
								if(toknVal.size() > 3)
								{
									if(sqlExpr.value.contains("AND") && (!sqlExpr.value.contains("ds")))
									{
										sqlExpr.value = FunctionTypeConv.getCurrentFunctions(sqlExpr.value, currentDbId, ToolTypeConstants.DS_TOOL);
									}
								}
								
								fltrCondVal += " "+getUpdatedCondVal(sqlExpr.value, sqlDto, colsList);
							}
							else
							{
								String condTyp = FunctionTypeConv.getCurrentFunctions(sqlExpr.type, currentDbId, ToolTypeConstants.DS_TOOL);
							
								if(sqlExpr.value.startsWith("dsBetWeen("))
								{
									checkFlag = true;
								}
								
								if(checkFlag)
								{
									if(sqlExpr.value.startsWith("dsBetWeen("))
									{
										sqlExpr.value = sqlExpr.value.substring(0, sqlExpr.value.lastIndexOf(")"));
										fltrCondVal += " "+condTyp+" "+getUpdatedCondVal(sqlExpr.value, sqlDto, colsList);
										
									}
									else
									{
										fltrCondVal += " "+","+getUpdatedCondVal(sqlExpr.value, sqlDto, colsList)+")";
										checkFlag = false;
									}
										
								}
								
								else
									fltrCondVal += " "+condTyp + " "+getUpdatedCondVal(sqlExpr.value, sqlDto, colsList);
							}
						}
					}
				}
				
				fltr.setFilterCondition(fltrCondVal);
				fltr.setFilterPosition("none");
				
				dataTran.setFilter(fltr);
			}
			
			dataTrans.getDataTransformation().add(dataTran);
		}
		
	}

	private static boolean getInStatements(String value) 
	{
		ArrayList<Token> tknVal = FunctionTypeConv.getTokens(value);
		
		for(int j = 0 ; j < tknVal.size() ; j++)
		{
			if(tknVal.get(j).data.equalsIgnoreCase("in"))
			{
				return true;
			}
		}
		
		return false;
	}

	private static String getUpdatedCondVal(String value, ArrayList<sqlDTO> sqlDto, ArrayList<SQLObject> colsList) 
	{
		String condVals = ""; 
		ArrayList<Token> tkn = FunctionTypeConv.getTokens(value);
		for(int i = 0 ; i < tkn.size(); i++)
		{
			if(tkn.get(i).type.name().equalsIgnoreCase("Varwithdot"))
			{
				String checkValue = tkn.get(i).data.substring(0, tkn.get(i).data.lastIndexOf("."));
				String colValue = tkn.get(i).data.substring( tkn.get(i).data.lastIndexOf(".")+1, tkn.get(i).data.length());
				
				String updatedVal = getUpdatedValFromSQLDtoObjeds(sqlDto, checkValue, colValue);
				if(updatedVal != null)
					condVals += " " + updatedVal;
				else
					condVals += " " + tkn.get(i).data;
			}
			else
				condVals += " " + tkn.get(i).data;
		}
		
		condVals = Utils.getStructuredVal(condVals);
	
		return condVals;
		
	}

	private static String getUpdatedCondVals(String condVals, ArrayList<sqlDTO> sqlDto, ArrayList<Token> condtkn) 
	{
	    String updCondVals = "";
	    
	    
			for(int j1 = 0 ; j1 < condtkn.size() ; j1++)
			{
				if(condtkn.get(j1).type.name().equalsIgnoreCase("VAR") && (!condtkn.get(j1).data.contains("ds")))
				{
					String upDatedVal = getUpdatedValForSrcs(sqlDto, condtkn.get(j1));
					
					if(upDatedVal.length() > 0)
						updCondVals += " "+upDatedVal;
				}
				else
				{
					updCondVals += " "+condtkn.get(j1).data;
				}
			}
			
		
	    
		return updCondVals;
	}

	private static String getUpdatedValForSrcs(ArrayList<sqlDTO> sqlDto, Token token) 
	{
		String updatedVal ="";
		for(int i = 0 ; i < sqlDto.size() ; i++)
		{
			sqlDTO srcSqlDto = sqlDto.get(i);
			if(srcSqlDto.subSQLType != null)
			{
				if(srcSqlDto.subSQLType.equalsIgnoreCase("SubQueryPrevComp"))
				{
					if(srcSqlDto.sqlDtoList.size() > 0)
					{
						for(int k = 0 ; k < srcSqlDto.sqlDtoList.size(); k++)
						{
							sqlDTO sqlDt = srcSqlDto.sqlDtoList.get(k);
							if(sqlDt.transType.equalsIgnoreCase("MainSource") || 
									sqlDt.transType.equalsIgnoreCase("Expression") || sqlDt.transType.equalsIgnoreCase("Aggregator"))
							{
								 String newUpdatedVal = getNewUpdatedVal(sqlDt, token);
								 if(newUpdatedVal.contains("."))
									 return newUpdatedVal;
							}
							else
								updatedVal = token.data;
						}
					}
					else
						updatedVal =token.data;
				}
				else
					updatedVal = token.data;
			}
			else
				updatedVal = token.data;
		}
		
		return updatedVal;
		
	}

	private static String getNewUpdatedVal(sqlDTO sqlDt, Token token) 
	{
		String newUpdatedVal = "";
		for (int j = 0; j <sqlDt.sqlElems.size(); j++)
		{
			SQLElement sqlElem = new SQLElement();
			if(sqlDt.sqlElems.get(j) instanceof SQLElement)
				sqlElem = (SQLElement) sqlDt.sqlElems.get(j);
			
			String colName = "";
			if(sqlElem.name.contains("."))
				colName = sqlElem.name.substring(sqlElem.name.indexOf(".")+1, sqlElem.name.length());
			else
				colName =  sqlElem.name;
			
			if(sqlElem.name != null)
			{
				if(sqlElem.aliasName != null)
				{
					if(sqlElem.aliasName.equalsIgnoreCase(token.data))
						newUpdatedVal = sqlDt.compName+"."+token.data;
					else
						newUpdatedVal = token.data;
					
					if(newUpdatedVal.contains("."))
						return newUpdatedVal;
				}
				else
				{
					if(colName.equalsIgnoreCase(token.data))
						newUpdatedVal = sqlDt.compName+"."+token.data;
					else
						newUpdatedVal = token.data;
					
					if(newUpdatedVal.contains("."))
						return newUpdatedVal;
				}
				
			}		
		}
		return newUpdatedVal;
	}

	private static boolean checkUpdatedColsHasDataElem(ArrayList<Token> condtkn) 
	{
		for (int i = 0; i < condtkn.size(); i++) 
		{
			if(condtkn.get(i).type.name().equalsIgnoreCase("varWithdot"))
				return true;
		}
		return false;
	}

	private static String getUpdatedValFromSQLDtoObjeds(ArrayList<sqlDTO> sqlDto, String checkValue, String data)
	{
		String value = null;
		for(int j1 = 0 ; j1 < sqlDto.size(); j1++)
		{
			if(sqlDto.get(j1).transType.equalsIgnoreCase("MainSource") || 
					sqlDto.get(j1).transType.equalsIgnoreCase("Source"))
			{
				if(sqlDto.get(j1).aliasName != null)
				{
					if(sqlDto.get(j1).aliasName.equalsIgnoreCase(checkValue))
					{
						value = sqlDto.get(j1).compName+"."+data;
					}
					
				}
				else
				{
					if(sqlDto.get(j1).originalTableName.equalsIgnoreCase(checkValue))
					{
						value = sqlDto.get(j1).compName+"."+data;
					}
				}
			}
		}
		
		return value;
		
	}

	private static String getprevCompNameForFltr(ArrayList<sqlDTO> sqlDto, boolean checkJoinerSubQuery) 
	{
		String prevCompName = "";
		LinkedList<String> jnrCompLst = new LinkedList<String>();
		for(int i = 0 ; i < sqlDto.size(); i++)
		{
			
			if(sqlDto.get(i).subSQLType != null)
			{
				if(sqlDto.get(i).subSQLType.equalsIgnoreCase("SubQueryPrevComp") && sqlDto.get(i).subQuery && (!checkJoinerSubQuery))
				{
					prevCompName = sqlDto.get(i).compName;
					sqlDto.get(i).subQuery = false;
				}
			}
			
			if(sqlDto.get(i).transType.equalsIgnoreCase("Joiner"))
				jnrCompLst.add(sqlDto.get(i).compName);

			else if(sqlDto.get(i).transType.equalsIgnoreCase("MainSource"))
			{
				prevCompName = sqlDto.get(i).compName;
			}
		
		}
		
		if(jnrCompLst.size() > 0)
		{
			prevCompName = jnrCompLst.get(jnrCompLst.size()-1);
		}
		return prevCompName;
	}

	private static String getCompNameForTrans(ArrayList<sqlDTO> sqlDto, String prefixTrans, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap, SQLQuery sqlQuery) 
	{
		String compName = null;
		for(Entry<String, sqlDTO> entrySet : tgtDataElemnsMap.entrySet())
		{
			if(entrySet.getValue().subSQLType != null)
			{	
				if(entrySet.getValue().subSQLType.equalsIgnoreCase("SubQueryPrevComp") && entrySet.getValue().subQuery)
				{
					sqlDto.add(entrySet.getValue());
				}
			}
				
		}
		
		for(int i = 0 ; i < sqlDto.size() ; i++)
		{
		    if(sqlDto.get(i).transType.equalsIgnoreCase("MainSource"))
			{
				compName = prefixTrans + sqlDto.get(i).compName;
				return compName;
			}
		    else if(sqlDto.get(i).subSQLType != null)
			{
				if(sqlDto.get(i).subSQLType.equalsIgnoreCase("SubQueryPrevComp") && sqlDto.get(i).subQuery)
				{
					compName = getUpdatedCompNameForSubQueries(sqlDto.get(i));
					sqlQuery.compName = compName;
					compName = prefixTrans + compName;
					return compName;
				}
			}
		}
		
		return compName;
	}
	
	private static String getCompNameForJoinerTrans(ArrayList<sqlDTO> sqlDto, String prefixTrans, LinkedHashMap<String, sqlDTO> tgtDataElemnsMap, SQLQuery sqlQuery) 
	{
		String compName = null;
		for(Entry<String, sqlDTO> entrySet : tgtDataElemnsMap.entrySet())
		{
			if(entrySet.getValue().subSQLType != null)
			{	
				if(entrySet.getValue().subSQLType.equalsIgnoreCase("SubQueryPrevComp") && entrySet.getValue().subQuery)
				{
					sqlDto.add(entrySet.getValue());
				}
			}
				
		}
		
		for(int i = 0 ; i < sqlDto.size() ; i++)
		{
		    if(sqlDto.get(i).transType.equalsIgnoreCase("MainSource"))
			{
				compName = sqlDto.get(i).compName;
				return compName;
			}
		    else if(sqlDto.get(i).subSQLType != null)
			{
				if(sqlDto.get(i).subSQLType.equalsIgnoreCase("SubQueryPrevComp") && sqlDto.get(i).subQuery)
				{
					compName =  sqlDto.get(i).compName;
					sqlQuery.compName = compName;
					
					return compName;
				}
			}
		}
		
		return compName;
	}
	
	

	private static String getUpdatedCompNameForSubQueries(sqlDTO sqlDTO) 
	{
		String compName = "";
        if(sqlDTO.transType.equalsIgnoreCase("Expression"))
        {
        	compName = sqlDTO.compName.substring(sqlDTO.compName.indexOf("expr_")+5, sqlDTO.compName.length());
        }
        else if(sqlDTO.transType.equalsIgnoreCase("Aggregator")) 
        	compName = sqlDTO.compName.substring(sqlDTO.compName.indexOf("aggr_")+5, sqlDTO.compName.length());
        else
        	compName = sqlDTO.compName;
        
		return compName;
	}
	
	private static String getUpdatedCompNameForJoinerSubQueries(sqlDTO sqlDTO) 
	{
		String compName = sqlDTO.compName;
        
		return compName;
	}

}