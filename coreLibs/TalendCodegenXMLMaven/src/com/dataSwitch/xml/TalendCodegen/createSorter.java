
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.List;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.SorterInfo;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.ElementParameter;
import com.dataSwitch.xml.connectorspecs.Connector;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping;

public class createSorter {
	
	public static void fillPropfortSort(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb)
	{
		SorterInfo sort = (SorterInfo) recordperPipe.transInfo;
		String compName = recordperPipe.compName;
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		ElementParameter elemparamSort = new ElementParameter();
		elemparamSort.setField("TABLE");
		elemparamSort.setName("CRITERIA");
		
		List<String> outCols = new ArrayList<String>();
		
		for(int i=0; i<recordperPipe.outRecsList.size(); i++){
			for(int j=0; j<recordperPipe.outRecsList.get(i).columnList.size();j++){
				String s = recordperPipe.outRecsList.get(i).columnList.get(j).colStruct.name;
				s = s.substring(s.indexOf(".")+1,s.length());
				outCols.add(s);
			}
		}
		
	    for(int i = 0 ; i < sort.keyCols.size();i++)
	    {
	    	
	    	ColumnDTO sortCols = sort.keyCols.get(i);
	    	if(sortCols != null)
		    {
		    	if(sortCols.isBeingUsed==89){
			    	String sortColName = sortCols.colStruct.name;
			    	String sortColType = sortCols.colStruct.dataType;
			    	String sortCol = sortColName.substring(sortColName.indexOf(".")+1, sortColName.length());
			    	if(outCols.contains(sortCol)){
				    	String sortOrder = sort.sortOrdersList.get(i);
				    	String sortOrderName = "";
				    	if(sortOrder.equalsIgnoreCase("ASCENDING"))
				    	{
				    		sortOrderName = "asc";
				    	}
				    	if(sortOrder.equalsIgnoreCase("DESCENDING"))
				    	{
				    		sortOrderName = "desc";
				    	}
				    	List<ElementParameter.ElementValue> elemValueList = new ArrayList<ElementParameter.ElementValue>();
				    	elemValueList.add(Utils.getPropElemforTrans("COLNAME", sortCol));
				    	
				    	if(sortColType != null)
				    	{
					    	if(sortColType.equalsIgnoreCase("id_String")||sortColType.equalsIgnoreCase("id_Character")||sortColType.equalsIgnoreCase("id_Char")){
					    		elemValueList.add(Utils.getPropElemforTrans("SORT", "alpha"));
					    	}
					    	else if(sortColType.equalsIgnoreCase("id_Date")){
					    		elemValueList.add(Utils.getPropElemforTrans("SORT", "date"));
					    	}
					    	else{
					    		elemValueList.add(Utils.getPropElemforTrans("SORT", "num"));
					    	}
				    	}
				    	
				    	elemValueList.add(Utils.getPropElemforTrans("ORDER", sortOrderName));
				    	elemparamSort.getElementValue().addAll(elemValueList);
			    	}
		    	}
		     }
	    }
		elemParam.add(Utils.getPropDtlsforExp("CHECK","EXTERNAL","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("DIRECTORY","TEMPFILE","&quot;C:/Users/unameit/workspace/temp&quot;","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("CHECK","CREATEDIR","true","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","EXTERNAL_SORT_BUFFERSIZE","1000000","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
		compNode.getElementParameter().addAll(elemParam);
		compNode.getElementParameter().add(elemparamSort);
	}
	public static void fillSortTrans(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb, DataMapping dataMappingObj)
	{
		Double compVersion = 0.102;
		String sorttranstype = "tSortRow";
		String compName = recordperPipe.compName;
		String xPos = recordperPipe.xLevelPos;
		String yPos = recordperPipe.yLevelPos;
		Utils.getcompDetails(compNode,sorttranstype, compVersion,xPos,yPos);
		fillPropfortSort(recordperPipe,docspecs,compNode,srctgtdb);
		Node.Metadata mtdata = new Node.Metadata();
		mtdata.setConnector("FLOW");
		mtdata.setLabel("rw_"+compName);
		mtdata.setName(compName);
		
		boolean checkRcp = Utils.getBooleanForRcp(dataMappingObj);
		if(checkRcp == true)
		{
			mtdata.getColumn().add(Utils.createExtraColForRCP());
			//rjtmetadata.getColumn().add(Utils.createExtraColForRCP());
		}
		
		for(int i = 0 ; i < recordperPipe.outRecsList.size(); i++)
		{
			OutRecDTO outRecs = recordperPipe.outRecsList.get(i);
			for (int j = 0; j < outRecs.columnList.size(); j++) 
			{
				ColumnDTO cols = outRecs.columnList.get(j);
				Node.Metadata.Column colmn = new Node.Metadata.Column();
				String colsName = cols.colStruct.name;
				String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				String dataType = cols.colStruct.dataType;
				int length = cols.colStruct.length;
				int precs = cols.colStruct.precision;
				
				boolean isNullable = cols.colStruct.isNullable;
				 String nullStatus = String.valueOf(isNullable);
				 String comment = cols.colStruct.description;
				 
				 if(comment!=null){
					 colmn.setComment(comment);
				 }
				 else{
					 colmn.setComment("");
				 }
				
				colmn.setKey("false");

//				colmn.setLength(length);
				
				
				if(cols.colStruct.length==0){
					 colmn.setLength(cols.colStruct.precision);
					 colmn.setPrecision(cols.colStruct.Scale);
				}
				else{
					colmn.setLength(cols.colStruct.length);
					colmn.setPrecision(cols.colStruct.Scale);
				}
				
				colmn.setName(colName);
				
				 if(nullStatus!=null){
					 colmn.setNullable(nullStatus);
				 }
				 else{
					 colmn.setNullable("true");
				 }
				
				
				
				colmn.setPattern("");
//				colmn.setPrecision(precs);
				colmn.setType(dataType);
				colmn.setUsefulColumn("true");
				mtdata.getColumn().add(colmn);
			}
		}
		compNode.getMetadata().add(mtdata);	
	}

}