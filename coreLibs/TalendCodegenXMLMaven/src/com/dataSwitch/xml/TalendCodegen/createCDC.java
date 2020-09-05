
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.InRecDTO;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapDTO.UnionInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.ElementParameter;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.NodeData.VarTables;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;

public class createCDC {

	public static void fillTransforCDC(TransRecExtDTO recordperPipe, Connector docspecs, Node compNode,
			SrcTgtInfo srctgtdb, List<String> cntxtParamsList, DataMapping dataMappingObj, boolean checkCDChasInsrtDelete) 
	{
		String key = "";
		String beforeStage = "";
		String afterStage = "";
		boolean onlyDelete = createCDCStage.checkCDChasDelete(recordperPipe, srctgtdb);
		
		if(srctgtdb.transDtls.size() > 0)
		{
			for(int i = 0 ; i < srctgtdb.transDtls.size() ; i++)
			{
				String propNames = srctgtdb.transDtls.get(i);
				String propName= propNames.substring(0,propNames.indexOf(":")).trim();
				String propValue = propNames.substring(propNames.indexOf(":")+1).trim();
				if(propName.equalsIgnoreCase("key"))
				{
					key = propValue;
				}
				else if(propName.equalsIgnoreCase("BeforeStage"))
				{
					beforeStage = propValue;	
				}
				else if(propName.equalsIgnoreCase("AfterStage"))
				{
					afterStage = propValue;
				}
				
			}
		}
		
		Double compVersion = 0.101;
		String sorttranstype = "tMap";
		String compName = recordperPipe.compName;
		String xPos = recordperPipe.xLevelPos;
		String yPos = recordperPipe.yLevelPos;
		Utils.getcompDetails(compNode,sorttranstype, compVersion,xPos,yPos);
		Utils.fillPropforExp_tMap(recordperPipe, docspecs, compNode, srctgtdb);
		
		String preTransType = "";
		String prevCompName = "";
		List<String> prevComp = Utils.getPrevCompName(recordperPipe);
		for(int i=0; i<prevComp.size(); i++){
			String pre = prevComp.get(i);
			String preCompName = pre.substring(0,pre.indexOf('/'));
			if(preCompName.equalsIgnoreCase(afterStage)){
				preTransType = pre.substring(pre.indexOf('/')+1);
				prevCompName = preCompName;
			}
		}
		
		Node.Metadata mtdata = new Node.Metadata();
		mtdata.setConnector("FLOW");
		mtdata.setLabel("rw_"+compName);
		mtdata.setName(compName);
	    Node.NodeData ndData = new Node.NodeData();
	    ndData.setXsiType("TalendMapper:MapperData");
	    Node.NodeData.UIProperties ndUI = new Node.NodeData.UIProperties();
	    ndUI.setshellMaximized("true");
	    ndData.setUIProperties(ndUI);
	    Node.NodeData.VarTables ndVars = new Node.NodeData.VarTables();
	    ndVars.setname("Var");
	    ndVars.setminimized("true");
	    ndVars.setsizeState("INTERMEDIATE");
	    Node.NodeData.OutPutTables outTbl = null;
	    
	    String afterIPTableName = "";
	    String beforeIPTableName = "";
	    
	    for(int j = 0 ; j < recordperPipe.inRecsList.size() ; j++)
		{
			  InRecDTO inrec = recordperPipe.inRecsList.get(j);
			  String prevoutRec = inrec.prevOutRec.name.substring(0, inrec.prevOutRec.name.indexOf("Out"));
			  if(prevoutRec.equalsIgnoreCase(afterStage)){
				  if(inrec.prevOutRec.parentTransRec.transType.equalsIgnoreCase("Conditional Grouping") || inrec.prevOutRec.parentTransRec.transType.equalsIgnoreCase("Filter Transformation"))
				  {
					  prevoutRec = "Case_"+compName;
				  }
				  else
				  {
					  prevoutRec = "rw_"+prevoutRec;
				  }
				  afterIPTableName = prevoutRec;
				  
			  }
			  else if(prevoutRec.equalsIgnoreCase(beforeStage)){
				  if(inrec.prevOutRec.parentTransRec.transType.equalsIgnoreCase("Conditional Grouping") || inrec.prevOutRec.parentTransRec.transType.equalsIgnoreCase("Filter Transformation"))
				  {
					  prevoutRec = "Case_"+compName;
				  }
				  else
				  {
					  prevoutRec = "rw_"+prevoutRec;
				  }
				  beforeIPTableName = prevoutRec;
			  }
		}
	    
	    for(int j = 0 ; j < recordperPipe.inRecsList.size() ; j++)
		{
			  InRecDTO inrec = recordperPipe.inRecsList.get(j);
			  String inReCmp = inrec.name.substring(0, inrec.name.indexOf("Out"));
			  Node.NodeData.InPutTables inTbl = new Node.NodeData.InPutTables();
			  inTbl.setsizeState("INTERMEDIATE");
			  inTbl.setlookupMode("LOAD_ONCE");
			  inTbl.setmatchingMode("UNIQUE_MATCH");
			  
			  String prevoutRec = inrec.prevOutRec.name.substring(0, inrec.prevOutRec.name.indexOf("Out"));
			  if(inrec.prevOutRec.parentTransRec.transType.equalsIgnoreCase("Conditional Grouping") || inrec.prevOutRec.parentTransRec.transType.equalsIgnoreCase("Filter Transformation"))
			  {
				  prevoutRec = "Case_"+compName; 
			  }
			  else
			  {
				  prevoutRec = "rw_"+prevoutRec;
			  }
			  inTbl.setname(prevoutRec);
			  
			  for( int j1 = 0 ; j1 < inrec.prevOutRec.columnList.size(); j1++)
			  {
				  Node.NodeData.InPutTables.MapperTableEntries mapTbl = new Node.NodeData.InPutTables.MapperTableEntries();
				  ColumnDTO colsList = inrec.prevOutRec.columnList.get(j1);
				  String colsName = colsList.colStruct.name;
				  String dataType = colsList.colStruct.dataType;
				  String nullable = Boolean.toString(colsList.colStruct.isNullable);	
				  String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				  //String colName = colsList.aliasName;
				  mapTbl.setName(colName);
				  mapTbl.setType(dataType);
				  mapTbl.setNullable(nullable);
				  
				  if(prevoutRec.contains(beforeStage)){
					  inTbl.setactivateCondensedTool("true");
					  mapTbl.setexpression(afterIPTableName+"."+colName);
					  mapTbl.setoperator("=");
				  }
				  
				  inTbl.getMapperTableEntries().add(mapTbl);
			  }
			  
			  ndData.getInPutTables().add(inTbl);
		 }
	    
	    
	    
	    
	    for(int i = 0 ; i < recordperPipe.outRecsList.size() ; i++)
		{
			  OutRecDTO outRecs = recordperPipe.outRecsList.get(i);
			  outTbl = new Node.NodeData.OutPutTables();
			  outTbl.setsizeState("INTERMEDIATE");
			  outTbl.setname("rw_"+compName);
			  Node.Metadata mtData = new Node.Metadata();
			  mtData.setConnector("FLOW");
			  mtData.setName("rw_"+compName);
			  mtData.setLabel("rw_"+compName);
			 
			  int changeCodeFlag = 0;
			  
			  for(int j = 0 ; j < outRecs.columnList.size() ; j++)
			  {
				 Metadata.Column colm = new Metadata.Column();
				 ColumnDTO cols = outRecs.columnList.get(j);
				 String ColsName = cols.colStruct.name;
				 String dataType = cols.colStruct.dataType;
				 int length = cols.colStruct.length;
				 int precision = cols.colStruct.precision;
				 String srcComp = cols.srcColumn.colStruct.name;
				 String srcCompName = srcComp.substring(0, srcComp.indexOf("."));
				 //String colName = ColsName.substring(ColsName.indexOf(".")+1, ColsName.length());
				 String colName = cols.aliasName;
				 String srcCols = cols.srcColumn.colStruct.name;
				 String srcColsName = srcCols.substring(srcCols.indexOf(".")+1, srcCols.length());
				 colm.setName(colName);
				 
				 boolean isNullable = cols.colStruct.isNullable;
				 String nullStatus = String.valueOf(isNullable);
				 String comment = cols.colStruct.description;
				 
				 if(comment!=null){
				 	 colm.setComment(comment);
				 }
				 else{
					 colm.setComment("");
				 }
				 
				 
				 colm.setKey("false");

//				 colm.setLength(length);
				 
				 if(cols.colStruct.length==0){
					 colm.setLength(cols.colStruct.precision);
					 colm.setPrecision(cols.colStruct.Scale);
				}
				else{
					colm.setLength(cols.colStruct.length);
					colm.setPrecision(cols.colStruct.Scale);
				}
				 
				 if(nullStatus!=null){
					 colm.setNullable(nullStatus);
				 }
				 else{
					 colm.setNullable("true");
				 }
				 
				 
				 colm.setPattern("");
//				 colm.setPrecision(precision);
				 colm.setType(dataType);
				 colm.setUsefulColumn("true");
				 mtData.getColumn().add(colm);
				
				 
				 /*String prevOutrec = cols.srcColumn.parentOutRec.name;
				 String parentTransType = cols.srcColumn.parentOutRec.parentTransRec.transType;
				 String suffixForMultipleInput = "";
				 if(prevOutrec.substring(prevOutrec.lastIndexOf("t")+1) != null){
					 suffixForMultipleInput = prevOutrec.substring(prevOutrec.lastIndexOf("t")+1);
				 }*/
				 /*String prevOutrecName = prevOutrec.substring(0, prevOutrec.indexOf("Out"));*/
				 
				 Node.NodeData.OutPutTables.MapperTableEntries mapTbl = new Node.NodeData.OutPutTables.MapperTableEntries ();
				 mapTbl.setName(colName);
				 mapTbl.setType(dataType);
				 mapTbl.setNullable(nullStatus);
				 //mapTbl.setexpression("rw_"+prevCompName+"."+colName);
				 if(colName.equalsIgnoreCase("change_code")){
					 mapTbl.setexpression("(Var.insert) == 1 ? 1 : (Var.no_change) == 1 ? 0 : (Var.modify) == 1 ? 3 : (Var.delete) == 1 ? 2 : 0");
					 changeCodeFlag++;
				 }
				 else{
					 
					if(preTransType.equalsIgnoreCase("Filter Transformation") || preTransType.equalsIgnoreCase("Conditional Grouping"))
					 {
					   mapTbl.setexpression("Case_"+compName+"."+srcColsName);
					 }
					 else
					 {
	                    mapTbl.setexpression("rw_"+prevCompName+"."+srcColsName);
					 } 
					 
					 
				 }
				 
				 
				 
				 outTbl.getMapperTableEntries().add(mapTbl);
				
			 }
			 
			 if(changeCodeFlag==0){
				 
				 Metadata.Column colm = new Metadata.Column();
				 colm.setName("change_code");
				 colm.setComment("");
				 colm.setKey("false");
				 colm.setNullable("true");
				 colm.setLength(5);
				 colm.setPrecision(0);
				 colm.setPattern("");
				 colm.setType("id_Integer");
				 colm.setUsefulColumn("true");
				 mtData.getColumn().add(colm);
				 
				Node.NodeData.OutPutTables.MapperTableEntries mapTbl2 = new Node.NodeData.OutPutTables.MapperTableEntries ();
				mapTbl2.setName("change_code");
				mapTbl2.setType("id_Integer");
				mapTbl2.setNullable("true");
				mapTbl2.setexpression("(Var.insert) == 1 ? 1 : (Var.no_change) == 1 ? 0 : (Var.modify) == 1 ? 3 : (Var.delete) == 1 ? 2 : 0");
				outTbl.getMapperTableEntries().add(mapTbl2);
			 } 
			 
			  
			 ndData.getOutPutTables().add(outTbl);
			 compNode.getMetadata().add(mtData);
		  }
	    
	    
	      VarTables.MapperTableEntries varmapTbl =  new VarTables.MapperTableEntries();
		  varmapTbl.setName("insert");
		  varmapTbl.setType("id_Integer");
	      varmapTbl.setexpression("("+afterIPTableName+"."+ key +" != null && " + beforeIPTableName + "." + key + "==null)?1:0");
		  ndVars.getMapperTableEntries().add(varmapTbl);
	    
		  VarTables.MapperTableEntries varmapTbl2 =  new VarTables.MapperTableEntries();
		  varmapTbl2.setName("delete");
		  varmapTbl2.setType("id_Integer");
	      varmapTbl2.setexpression("("+afterIPTableName+"."+ key +" == null && " + beforeIPTableName + "." + key + "!=null)?1:0");
		  ndVars.getMapperTableEntries().add(varmapTbl2);
		  
		  
		  InRecDTO inrec = recordperPipe.inRecsList.get(1);
		  
		  String updateExp = "(";
		  VarTables.MapperTableEntries varmapTbl3 =  new VarTables.MapperTableEntries();
		  varmapTbl3.setName("modify");
		  varmapTbl3.setType("id_Integer");
		  
		  for( int j1 = 0 ; j1 < inrec.prevOutRec.columnList.size(); j1++)
		  {
			  ColumnDTO colsList = inrec.prevOutRec.columnList.get(j1);
			  String colsName = colsList.colStruct.name;	
			  String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
			  
			  if(colName.equalsIgnoreCase(key)){
				  updateExp += afterIPTableName+"."+colName+".equalsIgnoreCase("+beforeIPTableName+"."+colName+") && ";
			  }
			  else{
				  updateExp += "!"+afterIPTableName+"."+colName+".equalsIgnoreCase("+beforeIPTableName+"."+colName+") && ";
			  }
		  }
		  updateExp = updateExp.substring(0, updateExp.length() - 3);
		  updateExp += ") ? 1:0";
		  varmapTbl3.setexpression(updateExp);
		  ndVars.getMapperTableEntries().add(varmapTbl3);
		  
		  
		  String noChangeExpExp = "(";
		  VarTables.MapperTableEntries varmapTbl4 =  new VarTables.MapperTableEntries();
		  varmapTbl4.setName("no_change");
		  varmapTbl4.setType("id_Integer");
		  for( int j1 = 0 ; j1 < inrec.prevOutRec.columnList.size(); j1++)
		  {
			  ColumnDTO colsList = inrec.prevOutRec.columnList.get(j1);
			  String colsName = colsList.colStruct.name;	
			  String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
			  
			  noChangeExpExp += afterIPTableName+"."+colName+".equalsIgnoreCase("+beforeIPTableName+"."+colName+") && ";
			  
		  }
		  noChangeExpExp = noChangeExpExp.substring(0, noChangeExpExp.length() - 3);
		  noChangeExpExp += ") ? 1:0";
		  varmapTbl4.setexpression(noChangeExpExp);
		  ndVars.getMapperTableEntries().add(varmapTbl4);
		  
		  
		 ndData.setVarTables(ndVars);
		 compNode.setNodeData(ndData);
	}

	public static void fillUnionCompTrans(TransRecExtDTO recordperPipe, Connector docspecs,
			TalendfileProcessType pipeline, SrcTgtInfo srctgtdb, List<String> cntxtParamsList, DataMapping dataMappingObj,
			boolean checkCDChasInsrtDelete) 
	{
		
		TalendfileProcessType.Node compNode = new TalendfileProcessType.Node();
		 String Uniontranstype ="tUnite";
		 Double compVersion = 0.101;
		 int xPos1 = Integer.parseInt(recordperPipe.xLevelPos)+20;
		 int yPos1 = Integer.parseInt(recordperPipe.yLevelPos)+20;
		 String xPos = Integer.toString(xPos1);
		 String yPos = Integer.toString(yPos1);
		 Utils.getcompDetails(compNode,Uniontranstype, compVersion,xPos,yPos);
		String compName = "dsUnion_"+recordperPipe.compName;
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT", "CONNECTION_FORMAT", "row"));
		compNode.getElementParameter().addAll(elemParam);
		TalendfileProcessType.Node.Metadata metadata = new TalendfileProcessType.Node.Metadata();
		metadata.setConnector("FLOW");
		metadata.setName("dsUnion_"+recordperPipe.compName);
		
		for(int i=0;i<recordperPipe.outRecsList.size();i++)
		{
			OutRecDTO outRec = recordperPipe.outRecsList.get(i);
			for(int i1 = 0 ; i1 < outRec.columnList.size() ; i1++)
			{
				ColumnDTO cols = outRec.columnList.get(i);
				String ColName = cols.aliasName;
				Metadata.Column tlndCol = new  Metadata.Column();
				tlndCol.setName(ColName);
				
				boolean isNullable = cols.colStruct.isNullable;
				 String nullStatus = String.valueOf(isNullable);
				 String comment = cols.colStruct.description;
				 
				 if(comment!=null){
					 tlndCol.setComment(comment);
				 }
				 else{
					 tlndCol.setComment("");
				 }
				
				String keyType = cols.colStruct.keyType;
				if(keyType=="0"){
					tlndCol.setKey("FALSE");
				}	
	
				
				if(cols.colStruct.length==0){
					tlndCol.setLength(cols.colStruct.precision);
					tlndCol.setPrecision(cols.colStruct.Scale);
				}
				else{
					tlndCol.setLength(cols.colStruct.length);
					tlndCol.setPrecision(cols.colStruct.Scale);
				}
				
				if(nullStatus!=null){
					tlndCol.setNullable(nullStatus);
				 }
				 else{
					 tlndCol.setNullable("true");
				 }
				
				tlndCol.setPattern("");
				tlndCol.setUsefulColumn("true");
				String dataType = cols.colStruct.dataType;
				tlndCol.setType(dataType);	
				metadata.getColumn().add(tlndCol);
			}
		
		}
		compNode.getMetadata().add(metadata);
		pipeline.getNode().add(compNode);
	}
		
	

}