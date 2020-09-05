
package com.dataSwitch.ds.commons.datatyperules;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TestDataTypeConv 
{
	public static void main (String args[])
	{
		//int toolType=9;
		DataTypeConvertor dtc= new DataTypeConvertor();
		//System.out.println("log1");
		//System.out.println(dtc.getOutputToolDataTypeMapRule (toolType));
		//System.out.println(dtc.getOutputToolDataTypeMapRule (toolType).get(0).getGenDataType());
		//System.out.println("log2");

		//String sourceDataType = "BIT";
		
		//Map<String,DataTypeRule> srcRuleMap = dtc.getSourceDataTypeRule(toolType);
		//System.out.println(dtc.getSourceDataTypeRule(1));

		
		//Map<String,DataTypeRule> tgtRuleMap = dtc.getTargetDataTypeRule(1);

		//System.out.println("Target datatype: "+ dtc.getTargetDataType(sourceDataType, srcRuleMap, tgtRuleMap ));
	
		
		ToolTypeConstants t = new ToolTypeConstants();
		HashMap<String, Integer> db2Tool = t.getMap();
		
		
		//dtc.getTargetData(1,2);
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Source DB: ");
		String sourceDB = input.next();
		sourceDB = sourceDB.toUpperCase();
		System.out.println("Enter Target DB: ");
		String targetDB = input.next();
		targetDB=targetDB.toUpperCase();
		System.out.println("Enter Source Datatype: ");
		String srcDType = input.next();
		//srcDType=srcDType.toUpperCase();
		if(srcDType.equalsIgnoreCase("skip"))
		{
			getTargetData(db2Tool.get(sourceDB), db2Tool.get(targetDB));
		}
		else
		{
			String tgtDtType= dtc.getTargetDataType(srcDType, db2Tool.get(sourceDB), db2Tool.get(targetDB));
			System.out.println("Corresponding Target Data Type is: "+tgtDtType);
		}
		
		input.close();

		//dtc.getcorresDataType(srcDType);

	}

	
	
	
	public static void getTargetData(int srctoolType, int tgttooltype)
	{
		
		HashMap<String, String> srcTgt = new HashMap<>();
		
		DataTypeConvertor dtc= new DataTypeConvertor();	
		Map<String,DataTypeRule> srcRuleMap = dtc.getSourceDataTypeRule(srctoolType);
		Map<String,DataTypeRule> tgtRuleMap = dtc.getTargetDataTypeRule(tgttooltype);
		System.out.println();
		System.out.format("%25s%25s", "SOURCE_DATATYPE","TARGET_DATATYPE");
		System.out.println();
		
		
			
		for (Map.Entry<String, DataTypeRule> entry1 : srcRuleMap.entrySet()) {
			String tgtDataTyp="";
			DataTypeRule source_dtr = entry1.getValue();
			String sdt = entry1.getKey();
			String gdt = source_dtr.getGenDataType().toString();
			String rdt = source_dtr.getRecommendedType().toString();	
 		
			if(rdt == "NULL")
			{			
				for (Map.Entry<String, DataTypeRule> entry2 : tgtRuleMap.entrySet()) {
					DataTypeRule target_dtr = entry2.getValue();
					if(target_dtr.getGenDataType().toString() == gdt){ 
						tgtDataTyp = target_dtr.getDbDataType();
						break;
					}	
				}
			}
			else{
			
				for (Map.Entry<String, DataTypeRule> entry3 : tgtRuleMap.entrySet()) {
					DataTypeRule target_dtr = entry3.getValue();
					//System.out.println(target_dtr);
					//System.out.println(target_dtr.getGenDataType());
					if(target_dtr.getGenDataType().toString() == rdt) {
						tgtDataTyp = target_dtr.getDbDataType();
						//System.out.println(tgtDataTyp);
						break;
					}	
				}	
			}	
	
			if(tgtDataTyp=="")
				tgtDataTyp="NA";
			System.out.println();
			System.out.format("%25s%25s", sdt,tgtDataTyp);
			
			
		}
		
		
	
	}
	

}