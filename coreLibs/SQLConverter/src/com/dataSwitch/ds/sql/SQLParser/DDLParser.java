
package com.dataSwitch.ds.sql.SQLParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.dataSwitch.ds.commons.datatyperules.DataTypeConvertor;
import com.dataSwitch.ds.commons.datatyperules.ToolTypeConstants;
import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.sql.SQLObjeds.DDLQuery;
import com.dataSwitch.ds.sql.SQLObjeds.SQLColstruct;
import com.dataSwitch.ds.sql.SQLObjeds.SQLElement;
import com.dataSwitch.ds.sql.SQLObjeds.SQLQuery;

public class DDLParser 
{
   public static final void processDDLParser(SQLQuery sqlObj, ArrayList<Token> coltoken, HashMap<String, String> sqlObjts, int currentDBId, int newDBId, String sqlValue, ArrayList<Token> tokens)
   {

		DDLQuery ddlQuery = null;

		if (sqlObj.ddlQuery == null)
			ddlQuery = new DDLQuery();
		else
			ddlQuery = sqlObj.ddlQuery;

		sqlValue = SQLStatement.updatedStmt(sqlValue, "as");

		boolean checkCreateAlias = SQLStatement.checkValues(coltoken, "as");
		if (checkCreateAlias) {
			SQLElement tblElem = new SQLElement();
			String tableName = sqlValue.substring(0, sqlValue.indexOf(" AS "));
			tblElem.name = tableName;
			// sqlObj.tableList.add(tblElem);
			ddlQuery.tableName = tblElem;

			if (newDBId == ToolTypeConstants.AZURESQL || newDBId == ToolTypeConstants.SNOWFLAKE) {
				String with = SQLStatement.updatedStmt(sqlValue, "with");
				String no = SQLStatement.updatedStmt(with, "no");
				String data = SQLStatement.updatedStmt(no, "data");
				String on = SQLStatement.updatedStmt(data, "on");
				String commit = SQLStatement.updatedStmt(on, "commit");
				String preserve = SQLStatement.updatedStmt(commit, "preserve");
				String rows = SQLStatement.updatedStmt(preserve, "rows");
				sqlValue = rows;
				String aliasTableName = sqlValue.substring(sqlValue.indexOf(" AS ") + 3);
				boolean topVal = false;
				if (aliasTableName.contains(" WITH NO DATA ") || aliasTableName.contains(" WITH NO DATA")) {
					String subStr = "WITH NO DATA";
					aliasTableName = aliasTableName.substring(0, aliasTableName.indexOf(subStr));
					topVal = true;
				} else if (aliasTableName.contains(" ;")) {
					aliasTableName = aliasTableName.substring(0, aliasTableName.indexOf(";"));
				}
				if (!aliasTableName.contains("ds__QueryNode_")) {
					SQLQuery tblElemNew = new SQLQuery();
					String colName = "";
					if (topVal) {
						colName = "top(*)";
					} else {
						colName = "*";
					}
					tblElemNew.dmlType = "SELECT";
					tblElemNew.withInParanthesis = true;
					SQLElement sqlColElem = new SQLElement();
					sqlColElem.name = colName;
					tblElemNew.colsList.add(sqlColElem);
					SQLElement sqlTableElem = new SQLElement();
					sqlTableElem.name = aliasTableName;
					tblElemNew.tableList.add(sqlTableElem);

					if (newDBId == ToolTypeConstants.AZURESQL) {
						// sqlObj.tableList.add(tblElemNew);
						ddlQuery.colsList.add(tblElemNew);
					} else {
						// tblElem.name = tableName;
						tblElem.aliasName = " LIKE " + aliasTableName;
						// sqlObj.tableList.remove(0);
						// sqlObj.tableList.add(tblElem);
					}
				} else {
					String aliasName = null;
					ArrayList<Token> tokenVal = FunctionTypeConv.getTokens(aliasTableName.trim());
					if (tokenVal.size() > 0) {
						if (tokenVal.get(0).data.contains("ds__QueryNode_")) {
							aliasTableName = tokenVal.get(0).data;
							if (sqlObjts.get(aliasTableName.trim()) != null) {
								if (SQLStatement.checkNodeObjtIsSubquery(sqlObjts.get(aliasTableName.trim()))) {
									// sqlObj.withInParanthesis = true;
									String sqlQuery = sqlObjts.get(aliasTableName.trim());
									sqlQuery = sqlQuery.substring(sqlQuery.indexOf("(") + 1, sqlQuery.length() - 1)
											+ ";";
									// sqlObj.colsList.add(processSelectStmt(sqlQuery, tokens,
									// sqlObjts,aliasName,currentDBId,newDBId,false));
									ddlQuery.colsList.add(SQLStatement.processSelectStmt(sqlQuery, tokens, sqlObjts, aliasName,
											currentDBId, newDBId, false));
								} else {
									// sqlObj.withInParanthesis = true;
									String sqlQuery = sqlObjts.get(aliasTableName.trim());
									sqlQuery = sqlQuery.substring(sqlQuery.indexOf("(") + 1, sqlQuery.length() - 1)
											+ ";";

									String aftrWithQuery = SQLStatement.getUpdatedColName(sqlObjts, sqlQuery);
									ArrayList<Token> tknWith = FunctionTypeConv.getTokens(aftrWithQuery);
									if (tknWith.get(0).data.equalsIgnoreCase("WITH")) {
										// sqlObj.colsList.add(SQLConverter.SQLParser(tknWith, aftrWithQuery,
										// currentDBId, newDBId));
										ddlQuery.colsList.add(SQLConverter.SQLParser(tknWith, aftrWithQuery,
												 currentDBId, newDBId));
									}
								}
							}
						}
					}
				}
			}
		} else {
			String sqlNoVal = SQLStatement.updatedStmt(sqlValue, "no");
			String fallBack = SQLStatement.updatedStmt(sqlNoVal, "fallback");
			String before = SQLStatement.updatedStmt(fallBack, "before");
			String after = SQLStatement.updatedStmt(before, "after");
			sqlValue = after;

			/*
			 * if(!(sqlValue.contains("NO FALLBACK") ||
			 * sqlValue.contains("NO BEFORE JOURNAL") ||
			 * sqlValue.contains("NO AFTER JOURNAL"))) {
			 */
			if (sqlValue.contains("NO FALLBACK")) {
				String subStr = "NO FALLBACK";
				sqlValue = sqlValue.substring(sqlValue.indexOf(subStr) + subStr.length(), sqlValue.length());
			}
			if (sqlValue.contains("NO BEFORE JOURNAL")) {
				String subStr = "NO BEFORE JOURNAL";
				sqlValue = sqlValue.substring(sqlValue.indexOf(subStr) + subStr.length(), sqlValue.length());
			}
			if (sqlValue.contains("NO AFTER JOURNAL")) {
				String subStr = "NO AFTER JOURNAL";
				sqlValue = sqlValue.substring(sqlValue.indexOf(subStr) + subStr.length(), sqlValue.length());
			}
			if (sqlValue.contains("MAP =")) {
				String subStr = "MAP =";
				sqlValue = sqlValue.substring(sqlValue.indexOf(subStr) + subStr.length(), sqlValue.length());
			}
			if (sqlValue.contains("FALLBACK")) {
				String subStr = "FALLBACK";
				sqlValue = sqlValue.substring(sqlValue.indexOf(subStr) + subStr.length(), sqlValue.length());
			}
			if (sqlValue.contains("DEFAULT MERGEBLOCKRATIO")) {
				String subStr = "DEFAULT MERGEBLOCKRATIO";
				sqlValue = sqlValue.substring(sqlValue.indexOf(subStr) + subStr.length(), sqlValue.length());
			}
			String updatedVal = SQLStatement.getUpdatedDDLStruct(sqlValue);

			ArrayList<Token> tblTkn = FunctionTypeConv.getTokens(updatedVal);
			for (int i = 0; i < tblTkn.size(); i++) {
				Token tkn = tblTkn.get(i);
				String tblDtls = tkn.data;

				if (tblDtls.contains("ds__QueryNode_")) {
					String aliasName = null;
					if (SQLStatement.checkNodeObjtIsSubquery(sqlObjts.get(tblDtls))) {
						sqlObj.withInParanthesis = true;
						String sqlQuery = sqlObjts.get(tblDtls);
						sqlQuery = sqlQuery.substring(sqlQuery.indexOf("(") + 1, sqlQuery.length() - 1) + ";";
						ddlQuery.colsList.add(
								SQLStatement.processSelectStmt(sqlQuery, tokens, sqlObjts, aliasName, currentDBId, newDBId, false));
						// sqlObj.colsList.add(processSelectStmt(sqlQuery, tokens,
						// sqlObjts,aliasName,currentDBId,newDBId,false));
					} else {
						ArrayList<Token> colTkn = FunctionTypeConv.getTokens(sqlObjts.get(tblDtls));
						boolean checkDecSep = SQLStatement.checkValues(colTkn, ",");
						boolean checkOpenParen = SQLStatement.checkValues(colTkn, "(");
						String updVal = "";
						if (checkOpenParen == true)
							updVal = sqlObjts.get(tblDtls).substring(sqlObjts.get(tblDtls).indexOf("(") + 1,
									sqlObjts.get(tblDtls).lastIndexOf(")"));
						ArrayList<Token> updColTkn = FunctionTypeConv.getTokens(updVal);
						if (checkDecSep == true) {
							for (Entry<Token, Token> entry : SQLStatement.getKeywordDecSep(updVal, updColTkn).entrySet()) {
								
								SQLColstruct sqlcolsTruct = new SQLColstruct();
								String value = SQLStatement.getIndexPositionForToken(entry.getKey(), entry.getValue(), updColTkn).trim();
								String decSepVal = entry.getKey().data;
								String decSepEndVal = entry.getValue().data;
								if (decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
									value = value.substring(value.indexOf(decSepVal) + decSepVal.length(),
											value.lastIndexOf(decSepEndVal)).trim();
								else if (decSepVal.equalsIgnoreCase(",") && !decSepEndVal.equalsIgnoreCase(","))
									value = value
											.substring(value.indexOf(decSepVal) + decSepVal.length(), value.length())
											.trim();
								else if (!decSepVal.equalsIgnoreCase(",") && decSepEndVal.equalsIgnoreCase(","))
									value = value.substring(0, value.lastIndexOf(decSepEndVal)).trim();

								String sqlChar = SQLStatement.updatedStmt(value, "character");
								String sqlSet = SQLStatement.updatedStmt(sqlChar, "set");
								String sqlnot = SQLStatement.updatedStmt(sqlSet, "not");
								String sqlNull = SQLStatement.updatedStmt(sqlnot, "null");
								value = sqlNull;
								
								if (value.contains(" NOT NULL"))
									sqlcolsTruct.nullType = " NOT NULL ";//	temp = " NOT NULL ";
									
								String defaultVal = "";
								if (value.toUpperCase().contains(" DEFAULT ")) {
									ArrayList<Token> tknVal = FunctionTypeConv.getTokens(value);
									boolean flag = false;
									defaultVal = "";
									for (int j = 0; j < tknVal.size(); j++) {
										if (flag) {
											defaultVal = tknVal.get(j).data;
											break;
										}
										if (tknVal.get(j).data.equalsIgnoreCase("default"))
											flag = true;
									}
									if (newDBId == ToolTypeConstants.SNOWFLAKE && defaultVal.length() > 0) {
										defaultVal = " DEFAULT " + defaultVal;
										sqlcolsTruct.defaultVal = defaultVal;
									}
								}

								if (value.contains(" CHARACTER SET "))
									value = value.substring(0, value.indexOf(" CHARACTER SET "));

								ArrayList<Token> updColsDtl = FunctionTypeConv.getTokens(value);

								String colName = updColsDtl.get(0).data;
								String coldtTyp = "";
								String nullConditions = "";
								if (updColsDtl.get(1).data != null) {
									coldtTyp = updColsDtl.get(1).data;
									DataTypeConvertor dtc = new DataTypeConvertor();
									coldtTyp = dtc.getTargetDataType(coldtTyp, currentDBId, newDBId);
								}

								if (coldtTyp == null)
									coldtTyp = "";

								if (updColsDtl.size() > 2) {
									if (updColsDtl.get(2).data.contains("ds__QueryNode_")) {
										if (newDBId == ToolTypeConstants.AZURESQL) {
											String prescisonScale = "";
											if (updColsDtl.get(2).data != null) {
												if (coldtTyp.equalsIgnoreCase("datatime")
														|| coldtTyp.equalsIgnoreCase("DATETIME2")) {
													prescisonScale = "";
												} else {
													prescisonScale = updColsDtl.get(2).data;
												}
											}

											coldtTyp = coldtTyp + " " + prescisonScale;
										} else {
											coldtTyp = coldtTyp + " " + updColsDtl.get(2).data;
										}
									}

									for (int k = 0; k < updColsDtl.size(); k++) {
										if (!updColsDtl.get(k).data.contains("ds__QueryNode_")) {
											if (k >= 2) {
												nullConditions += " " + updColsDtl.get(k).data;
											}
										}
									}
								}

								String updDtTyp = "";
								if (coldtTyp != null) {
									updDtTyp = SQLStatement.getUpdatedColName(sqlObjts, coldtTyp);
								}

								
								sqlcolsTruct.colName = colName;

//								String dataType = "";
//								String precs_Length = "";
//								String scale = "";
//								if (updDtTyp.length() > 0) {
//									if (updDtTyp.contains("(")) {
//										dataType = updDtTyp.substring(0, updDtTyp.indexOf("("));
//										if (updDtTyp.contains(",")) {
//											precs_Length = updDtTyp
//													.substring(updDtTyp.indexOf("(") + 1, updDtTyp.indexOf(","))
//													.replaceAll("[^0-9]", "");
//											scale = updDtTyp.substring(updDtTyp.indexOf(",") + 1, updDtTyp.indexOf(")"))
//													.replaceAll("[^0-9]", "");
//										} else {
//											precs_Length = updDtTyp
//													.substring(updDtTyp.indexOf("(") + 1, updDtTyp.indexOf(")"))
//													.replaceAll("[^0-9]", "");
//										}
//
//									} else {
//										dataType = updDtTyp;
//									}
//
//								}

								sqlcolsTruct.dataType = getColsStructDtls(updDtTyp)[0];
								sqlcolsTruct.length_precision = getColsStructDtls(updDtTyp)[1];
								sqlcolsTruct.Scale = getColsStructDtls(updDtTyp)[2];
//								sqlcolsTruct.nullType = nullConditions;
								ddlQuery.colsList.add(sqlcolsTruct);

								//SQLElement sqlElem = new SQLElement();
								//sqlElem.name = colName;

								//String updDtTypElem = "";

								//if (coldtTyp != null)
									//updDtTypElem = SQLStatement.getUpdatedColName(sqlObjts, coldtTyp);

								//updDtTypElem += temp;

								//sqlElem.aliasName = updDtTypElem + defaultVal;
								//sqlObj.colsList.add(sqlElem);

							}
						} else {
							ArrayList<Token> updColsDtl = FunctionTypeConv.getTokens(updVal);
							boolean chkOpenParen = SQLStatement.checkValues(colTkn, "(");
							boolean chkCloseParen = SQLStatement.checkValues(colTkn, ")");
							if (!chkCloseParen && !chkOpenParen) {
								String colName = updColsDtl.get(0).data;
								String coldtTyp = "";
								if (updColsDtl.get(1).data != null) {
									coldtTyp = updColsDtl.get(1).data;
									DataTypeConvertor dtc = new DataTypeConvertor();
									coldtTyp = dtc.getTargetDataType(coldtTyp, currentDBId, newDBId);
								}
								if (updColsDtl.size() > 2) {
									if (updColsDtl.get(2).data.contains("ds__QueryNode_")) {
										coldtTyp = coldtTyp + " " + updColsDtl.get(2).data;
									}
								}
								
								
//								SQLElement sqlElem = new SQLElement();
	//							String updDtTyp = SQLStatement.getUpdatedColName(sqlObjts, coldtTyp);
//								sqlElem.name = colName;
//								sqlElem.aliasName = updDtTyp;
//								sqlObj.colsList.add(sqlElem);
								SQLColstruct sqlcolsTruct = new SQLColstruct();
								sqlcolsTruct.colName = colName;
								
								//sqlcolsTruct.length_precision = precs_Length;
								//sqlcolsTruct.Scale = scale;
								//sqlcolsTruct.nullType = nullConditions;
								sqlcolsTruct.dataType = getColsStructDtls(coldtTyp)[0];
								sqlcolsTruct.length_precision = getColsStructDtls(coldtTyp)[1];
								sqlcolsTruct.Scale = getColsStructDtls(coldtTyp)[2];
								ddlQuery.colsList.add(sqlcolsTruct);
								
							} else {
								ddlQuery.otherDetails = updVal;
							}
						}
					}
				} else {
					if (i == 0) {
						if (tblTkn.get(0).data != null || tblTkn.get(0).data.length() > 0) {

							SQLElement tblElem = new SQLElement();
							// tableName = tblTkn.get(0).data;
							tblElem.name = tblTkn.get(0).data;
							ddlQuery.tableName = tblElem;
							//sqlObj.tableList.add(tblElem);
						}
					}

				}

			}

		}
//		for (int i = 0; i < coltoken.size(); i++) {
//			Token tkn = coltoken.get(i);
//			if (tkn.data.contains("ds__QueryNode")) {
//				String aliasName = null;
//				if (SQLStatement.checkNodeObjtIsSubquery(sqlObjts.get(tkn.data))) {
//					//sqlObj.withInParanthesis = true;
//					String sqlQuery = sqlObjts.get(tkn.data);
//					sqlQuery = sqlQuery.substring(sqlQuery.indexOf("(") + 1, sqlQuery.length() - 1) + ";";
//					ddlQuery.colsList
//							.add(SQLStatement.processSelectStmt(sqlQuery, tokens, sqlObjts, aliasName, currentDBId, newDBId, false));
//				}
//			}
//
//		}
		sqlObj.ddlQuery = ddlQuery;
		  
   
   }
   
   
   public static String[] getColsStructDtls(String updDtTyp)
   {
	   String dataType = "";
	   String precs_Length = "";
	   String scale = "";
		if (updDtTyp.length() > 0) {
			if (updDtTyp.contains("(")) {
				dataType = updDtTyp.substring(0, updDtTyp.indexOf("("));
				if (updDtTyp.contains(",")) {
					precs_Length = updDtTyp
							.substring(updDtTyp.indexOf("(") + 1, updDtTyp.indexOf(","))
							.replaceAll("[^0-9]", "");
					scale = updDtTyp.substring(updDtTyp.indexOf(",") + 1, updDtTyp.indexOf(")"))
							.replaceAll("[^0-9]", "");
				} else {
					precs_Length = updDtTyp
							.substring(updDtTyp.indexOf("(") + 1, updDtTyp.indexOf(")"))
							.replaceAll("[^0-9]", "");
				}

			} else {
				dataType = updDtTyp;
			}

		}
	
		String[] colstructArray = {dataType, precs_Length,scale}; 
		
	 return colstructArray;
	   
   }
}