
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapDTO.UnionInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.ElementParameter;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.Metadata;
import com.dataSwitch.xml.connectorspecs.Connector;

public class createUnion {
	
	public static void fillColsforUnion(TransRecExtDTO recordperPipe, Connector docspecs, Node compNode,SrcTgtInfo srctgtdb)
	{
		 String Uniontranstype ="tUnite";
		 Double compVersion = 0.101;
		 String xPos = recordperPipe.xLevelPos;
		 String yPos = recordperPipe.yLevelPos;
		 Utils.getcompDetails(compNode,Uniontranstype, compVersion,xPos,yPos);
		 String compName = recordperPipe.compName;
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT", "CONNECTION_FORMAT", "row"));
		compNode.getElementParameter().addAll(elemParam);
		List<String> prevComp = Utils.getPrevCompName(recordperPipe);
		String prevCompName = prevComp.toString().replace("[", "").replace("]", "");
		//String prevcomp = prevCompName.substring(0, prevCompName.indexOf(","));
		StringTokenizer tokenizer = new StringTokenizer(prevCompName, ",");
		String prevcompsName = "";
        while (tokenizer.hasMoreTokens())
        {
        	prevcompsName = tokenizer.nextToken();
        }
        String prevcomp = prevcompsName.substring(0, prevcompsName.indexOf("/"));
		TalendfileProcessType.Node.Metadata metadata = new TalendfileProcessType.Node.Metadata();
		metadata.setConnector("FLOW");
		//metadata.setLabel("rw_"+prevcomp);
		metadata.setName(recordperPipe.compName);
		UnionInfo union = (UnionInfo) recordperPipe.transInfo;
		
		for(int i=0;i<union.colDTOList.size();i++)
		{
			ColumnDTO cols = union.colDTOList.get(i);
			String colsname = cols.colStruct.name;
			//String ColName = colsname.substring(colsname.indexOf(".")+1, colsname.length());
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

//			tlndCol.setLength(cols.colStruct.length);
			
			
			if(cols.colStruct.length==0){
				tlndCol.setLength(cols.colStruct.precision);
				tlndCol.setPrecision(cols.colStruct.Scale);
			}
			else{
				tlndCol.setLength(cols.colStruct.length);
				tlndCol.setPrecision(cols.colStruct.Scale);
			}
			
			
//			tlndCol.setPrecision(cols.colStruct.precision);
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
		compNode.getMetadata().add(metadata);
	}

}