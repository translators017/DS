
package com.dataSwitch.ds.ScriptTranspiler.Translator.Teradata;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.dataSwitch.ds.functionTypeRules.FunctionTypeConv;
import com.dataSwitch.ds.functionparser.Token;
import com.dataSwitch.ds.functionparser.TokenType;
import com.dataSwitch.ds.Script.StatementObjts.StatType;
import com.dataSwitch.ds.Script.StatementObjts.iBlockStatement;
import com.dataSwitch.ds.Script.StatementObjts.iStatement;

public class TDtPumpDtls 
{
	public static iStatement processTdPumpScripts(LinkedHashMap<Float, iStatement> sqlNodeMaps, int currentDbId, int newDbId, String extension)
	{
		//LinkedHashMap<Float, iStatement> newTokenCloseBraceMap = addCurlyBrace(sqlNodeMaps);
		
		//ScriptUtils.getStatementNodes(newTokenCloseBraceMap, currentDbId, "{", "}", extension, newDbId, istatDtls);

		//createSQL(sqlNodeMaps, currentDbId, newDbId);
		
       iBlockStatement iBlockStat = new iBlockStatement();
		
		iBlockStat.iBlockStatements = sqlNodeMaps;
		iBlockStat.type =  StatType.UNKNOWNSTAT;
		
		return iBlockStat;
		
	}

	private static LinkedHashMap<Float, iStatement> addCurlyBrace(LinkedHashMap<Float, iStatement> sqlNodeMaps) 
	{
		LinkedHashMap<Float, iStatement> newTokenOpenBraceMap = new LinkedHashMap<Float, iStatement>();

		for (Entry<Float, iStatement> entrySet : sqlNodeMaps.entrySet()) {
			float keyVal = entrySet.getKey();
			ArrayList<Token> tokenList = entrySet.getValue().tokenList;

			iStatement newiStList = new iStatement();

			newiStList.type = entrySet.getValue().type;
			newiStList.originalLineNo = entrySet.getValue().originalLineNo;

			for (int i = 0; i < tokenList.size(); i++) {
				Token tkn = tokenList.get(i);

				if (tkn.data.equalsIgnoreCase(".layout")) {
					Token tmp = addOpenParan(tkn);
					newiStList.tokenList.add(tmp);
					newiStList.tokenList.add(tkn);

					for (int j = 0; j < tokenList.size(); j++) {
						Token tmpTkn = tokenList.get(j);

						if (j == i)
							continue;

						newiStList.tokenList.add(tmpTkn);
					}
					newTokenOpenBraceMap.put(keyVal, newiStList);
					break;
				} else
					newTokenOpenBraceMap.put(keyVal, entrySet.getValue());
			}
		}

		ArrayList<ArrayList<String>> getEndKey = new ArrayList<ArrayList<String>>();
		getEndKey(sqlNodeMaps, getEndKey);

		LinkedHashMap<Float, iStatement> newTokenCloseBraceMap = new LinkedHashMap<Float, iStatement>();
		boolean flag = false;
		for (Entry<Float, iStatement> entrySet : newTokenOpenBraceMap.entrySet()) {
			float keyVal = entrySet.getKey();
			ArrayList<Token> tokenList = entrySet.getValue().tokenList;
			iStatement newiStList = new iStatement();
			newiStList.type = entrySet.getValue().type;
			newiStList.originalLineNo = entrySet.getValue().originalLineNo;

			for (int i = 0; i < tokenList.size(); i++) {
				Token tkn = tokenList.get(i);

				if (getEndKey.size() > 0) {
					for (int j = 0; j < getEndKey.size(); j++) {
						for (int j2 = 0; j2 < getEndKey.get(j).size(); j2++) {
							String tmp = getEndKey.get(j).get(j2);
							String tmpKey = null;

							if (tmp.contains("dsKey"))
								tmpKey = tmp.substring(0, tmp.indexOf("dsKey"));

							if (tmpKey != null)
								if (Float.parseFloat(tmpKey) == keyVal)
									flag = true;

							if (flag) {
								// TO fill the remaining tokens in the list
								for (int k = 0; k < tokenList.size(); k++) {
									Token tmpTkn1 = tokenList.get(k);
									if (tmpTkn1.type.name().equalsIgnoreCase("EOL"))// &&
																					// (keyVal.equalsIgnoreCase(tmpKey)))
									{
										Token tmpTkn = addCloseParan(tmpTkn1);
										newiStList.tokenList.add(tmpTkn);

										flag = false;
									} else
										newiStList.tokenList.add(tmpTkn1);

								}

								newTokenCloseBraceMap.put(keyVal, newiStList);

								break;
							} else {
								if (!newTokenCloseBraceMap.containsKey(keyVal))
									newTokenCloseBraceMap.put(keyVal, entrySet.getValue());
								break;
							}
						}

					}
				} else
					newTokenCloseBraceMap.put(keyVal, entrySet.getValue());

				break;
			}

		}
		return newTokenCloseBraceMap;
	}

	public static Token addCloseParan(Token tkn) {
		byte val = 0;
		TokenType t = TokenType.CLOSEBRACKET;
		Token curleyToken = new Token(t, "}", val);

		return curleyToken;
	}

	public static Token addOpenParan(Token tkn) {
		byte val = 0;
		TokenType t = TokenType.OPENBRACKET;
		Token curleyToken = new Token(t, "{", val);

		return curleyToken;
	}

	private static ArrayList<String> getEndKey(LinkedHashMap<Float, iStatement> sqlNodeMaps,
			ArrayList<ArrayList<String>> getEndKey) {
		boolean flag = false;
		ArrayList<String> entityName = new ArrayList<String>();

		int cnt = 0;
		for (Entry<Float, iStatement> entrySet : sqlNodeMaps.entrySet()) {
			float keyVal = entrySet.getKey();
			ArrayList<Token> tokenList = entrySet.getValue().tokenList;

			for (int i = 0; i < tokenList.size(); i++) {
				Token tkn = tokenList.get(i);
				if (flag) {
					if (cnt == 0) {
						entityName.add(keyVal + "dsKey");
						cnt++;
					}
					if ((!tkn.data.equalsIgnoreCase(".layout")) && (!tkn.data.equalsIgnoreCase(".end"))
							&& (!tkn.data.equalsIgnoreCase("rc")))
						entityName.add(tkn.data);

					if (tkn.data.equalsIgnoreCase(".layout") || tkn.data.equalsIgnoreCase(".end")
							|| tkn.data.equalsIgnoreCase("rc")) {
						flag = false;
						getEndKey.add(entityName);
					}
				}
				if (tkn.data.equalsIgnoreCase("apply")) {
					flag = true;
					entityName = new ArrayList<String>();
					cnt = 0;
				}
			}
		}
		return entityName;
	}

	public static void createSQL(LinkedHashMap<Float, iStatement> sqlNodeMaps, int currentDbId, int newDbId) {
		boolean flag = false;
		ArrayList<String> tmpSql = new ArrayList<String>();
		LinkedList<ArrayList<Token>> sqlToken = new LinkedList<ArrayList<Token>>();
		int cnt = 0, sqlCnt = 1;
		String layoutName = "";
		LinkedHashMap<String, LinkedList<ArrayList<Token>>> layoutSql = new LinkedHashMap<String, LinkedList<ArrayList<Token>>>();
		for (Entry<Float, iStatement> entryScript : sqlNodeMaps.entrySet()) {
			ArrayList<Token> tokenList = entryScript.getValue().tokenList;
			ArrayList<Token> tmpTkn = new ArrayList<Token>();

			for (int i = 0; i < tokenList.size(); i++) {
				Token tkn = tokenList.get(i);
				if (tkn.data.equalsIgnoreCase(".layout")) {
					flag = true;
					layoutName = tokenList.get(i + 1).data;
				}

				if (flag) {
					if (tkn.data.equalsIgnoreCase(".field"))
						tmpSql.add(tokenList.get(i + 1).data);

					if (tokenList.get(0).data.equalsIgnoreCase("insert")
							|| tokenList.get(0).data.equalsIgnoreCase("update")
									&& tokenList.get(tokenList.size() - 1).type.name().equalsIgnoreCase("eol")) {
						tmpTkn.addAll(tokenList);
						sqlToken.add(tmpTkn);// flag = false;
						break;
					}
					// if (tkn.data.equalsIgnoreCase(".dml"))
					// flag = false;
				}
				if (tkn.data.equalsIgnoreCase(".import"))
					flag = true;
				if (flag) {
					if (cnt == 1) {
						tmpSql.add(tkn.data);
						cnt++;
						flag = false;
					}
					if (tkn.data.equalsIgnoreCase("infile"))
						cnt++;
				}
				if (cnt == 2) {
					String sql = buildSqlFromList(tmpSql, sqlCnt);
					sqlCnt++;
					if (!sql.equalsIgnoreCase("")) {
						layoutSql.put(sql, sqlToken);
						layoutName = "";
					}
					tmpSql = new ArrayList<String>();
					sqlToken = new LinkedList<ArrayList<Token>>();
					cnt = 0;
				}
			}
		}
		appendTempSqlToDML(layoutSql, sqlNodeMaps);

		for (Entry<Float, iStatement> entrySet : sqlNodeMaps.entrySet()) {
		   //System.out.println(entrySet.getKey()+"======" + entrySet.getValue().tokenList);
		}
	}

	private static void appendTempSqlToDML(LinkedHashMap<String, LinkedList<ArrayList<Token>>> layoutSql,
			LinkedHashMap<Float, iStatement> sqlNodeMaps) {

		for (Entry<Float, iStatement> entrySet : sqlNodeMaps.entrySet()) {
			ArrayList<Token> tokenList = entrySet.getValue().tokenList;

			for (int i = 0; i < tokenList.size(); i++) {
				for (Entry<String, LinkedList<ArrayList<Token>>> entrySql : layoutSql.entrySet()) {
					LinkedList<ArrayList<Token>> tmpSql = entrySql.getValue();

					for (int j = 0; j < tmpSql.size(); j++) {
						ArrayList<Token> tmp = tmpSql.get(j);
						if (tmp.equals(tokenList) && (entrySet.getValue().type == StatType.UNKNOWNSTAT)) {
							iStatement iSt = buildSqlFromTokens(entrySql.getKey(), tmp);
							iSt.originalLineNo = entrySet.getValue().originalLineNo;
							iSt.type = entrySet.getValue().type;
							entrySet.setValue(iSt);
							break;
						}
					}
				}
				break;
			}
		}
	}

	private static iStatement buildSqlFromTokens(String tmpSql, ArrayList<Token> sqlTkns) {

		LinkedList<String> oldSql = new LinkedList<String>();
		boolean insrtFlag = false, updateFlag = false;

		ArrayList<Token> token = new ArrayList<Token>();
		iStatement iSt = new iStatement();
		
		for (int i = 0; i < sqlTkns.size(); i++) {
			Token tkn = sqlTkns.get(i);

			if (insrtFlag) {
				oldSql.add(tkn.data);

				if (insrtFlag && tkn.type.name().equalsIgnoreCase("eol")) {
					insrtFlag = false;
					String newSql = "", sqlAlias = "";
					boolean flag = true;

					sqlAlias = tmpSql.substring(tmpSql.indexOf("dsSql"), tmpSql.length());

					for (int j = 0; j < oldSql.size(); j++) {
						if (oldSql.get(j).equalsIgnoreCase("values")) {
							flag = false;
						}
						if (flag)
							newSql += oldSql.get(j) + " ";
						else {
							if (oldSql.get(j).equalsIgnoreCase("values")) {
								newSql += " select ";
							} else if (oldSql.get(j).equalsIgnoreCase(":")) {
								newSql += sqlAlias + ".";
							} else if (oldSql.get(j).equalsIgnoreCase("(")) {

							} else if (oldSql.get(j).equalsIgnoreCase(")")) {
								newSql += " from " + tmpSql;
							} else {
								newSql += oldSql.get(j) + " ";
							}
						}
					}
					token = FunctionTypeConv.getTokens(newSql);
					iSt.tokenList.addAll(token);
//					System.out.println("insert==  " + token);
				}
			}
			if (updateFlag) {
				oldSql.add(tkn.data);

				if (updateFlag && tkn.type.name().equalsIgnoreCase("eol")) {
					updateFlag = false;
					String newSql = "", sqlAlias = "";
					boolean flag = true, whereFlag = false;

					sqlAlias = tmpSql.substring(tmpSql.indexOf("dsSql"), tmpSql.length());

					for (int j = 0; j < oldSql.size(); j++) {
						 if (oldSql.get(j).equalsIgnoreCase("set"))
						 {
							 flag = false;
						 }
						 if (flag)
							 newSql += oldSql.get(j) +" ";
						 else
						 {
							 if (oldSql.get(j).equalsIgnoreCase(":"))
							 {
								 newSql += sqlAlias+".";
							 }
							 else if (oldSql.get(j).equalsIgnoreCase("where"))
							 {
								 newSql += " from " + tmpSql+ " "+oldSql.get(j)+ " ";
								 whereFlag = true;
							 }
							 else
							 {
								 newSql += oldSql.get(j) +" ";
							 }
						 }
					 }
					if (!whereFlag)
					 {
						 newSql = newSql.substring(0, newSql.indexOf(";"));
						 newSql += " from " + tmpSql+ ";";
					 }
					token = FunctionTypeConv.getTokens(newSql);
					iSt.tokenList.addAll(token);
//					System.out.println("update==  " + newSql);
				}
			}
			if (tkn.data.equalsIgnoreCase("insert")) {
				insrtFlag = true;
				oldSql.add(tkn.data);
			}
			if (tkn.data.equalsIgnoreCase("update")) {
				updateFlag = true;
				oldSql.add(tkn.data);
			}
		}
		return iSt;
	}

	private static String buildSqlFromList(ArrayList<String> tmpSql, int sqlCnt) {
		String sql = "";
		for (int i = 0; i < tmpSql.size(); i++) {
			if (i == 0)
				sql = "(select " + tmpSql.get(i) + ",";
			else if (i == tmpSql.size() - 1)
				sql += " from " + tmpSql.get(i) + ") dsSql" + sqlCnt;
			else if (i == tmpSql.size() - 2)
				sql += tmpSql.get(i);
			else
				sql += tmpSql.get(i) + ",";
		}
		//System.out.println(sql);
		return sql;
	}
}