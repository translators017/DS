
package com.dataSwitch.xml.ETLMapDTO;

import java.util.*;

import com.dataSwitch.xml.ETLMapDTO.BaseTransInfo;


public class TransRuleDTO extends BaseTransInfo
{
//	public List<String> inElementsList =  new ArrayList<String>(); //inLinkName1.col1, inLinkName1.col2, ... all source columns
	public String transRuleName = null;
	public String originalExpression = null;
	public String errorDetails= null;
	
	
	public ArrayList<String> paramNamesList = new ArrayList<String>();
	public ArrayList<String> paramValuesList = new ArrayList<String>();
	public ArrayList<String> paramTypesList = new ArrayList<String>();

	public ArrayList<String> srcColNamesList = new ArrayList<String>();
	public ArrayList<String> grpByColNamesList = new ArrayList<String>();

	public List<ColumnDTO> srcColumns = new ArrayList<ColumnDTO>();
	public List<ColumnDTO>  grpByColumns = new ArrayList<ColumnDTO>();//null;
	
	public String aggOnNames = new String();
	public ColumnDTO aggOnCols = new ColumnDTO();

}