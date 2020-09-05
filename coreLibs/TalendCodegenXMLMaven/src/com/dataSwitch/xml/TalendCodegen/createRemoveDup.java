
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.RemoveDupInfo;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.ElementParameter;
import com.dataSwitch.xml.connectorspecs.Connector;

public class createRemoveDup {
	public static void fillPropfortUniqueRow(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb)
	{
		RemoveDupInfo remDup = (RemoveDupInfo) recordperPipe.transInfo;
		String compName = recordperPipe.compName;
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		ElementParameter elemparamRemDup = new ElementParameter();
		elemparamRemDup.setField("TABLE");
		elemparamRemDup.setName("UNIQUE_KEY");
		String remDupCheck = "";
		for(int i1 = 0 ; i1 < remDup.keyCols.size();i1++)
		{
			ColumnDTO cols =  remDup.keyCols.get(i1);
			if(remDup.keyCols.get(i1) != null)
			{
				
				String ColsName = cols.colStruct.name;
				String colName = ColsName.substring(ColsName.indexOf(".")+1, ColsName.length());
				remDupCheck += colName+",";
				
			}
		}
		for(int i = 0 ; i < recordperPipe.outRecsList.size();i++)
		{
			OutRecDTO outRecs =  recordperPipe.outRecsList.get(i);
			for (int j = 0; j < outRecs.columnList.size(); j++) 
			{
			   ColumnDTO cols =  outRecs.columnList.get(j);
			   String colsName = cols.colStruct.name;
			   String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
			   List<ElementParameter.ElementValue> elemValueList = new ArrayList<ElementParameter.ElementValue>();
		       elemValueList.add(Utils.getPropElemforTrans("SCHEMA_COLUMN", colName));
		       ElementParameter.ElementValue elemParamValue = new ElementParameter.ElementValue();
			   elemParamValue.setElementRef("KEY_ATTRIBUTE");
			   elemParamValue.setValue("false");
			   StringTokenizer tokenizer = new StringTokenizer(remDupCheck, ",");
			   while(tokenizer.hasMoreTokens())
			   {
				   String ColNameCheck = tokenizer.nextToken();
				   if(colName.equalsIgnoreCase(ColNameCheck))
				   {
					   elemParamValue.setValue("true");
				   }
			   }
			   elemparamRemDup.getElementValue().addAll(elemValueList);
			   elemparamRemDup.getElementValue().add(elemParamValue);
			}
		}
		elemParam.add(Utils.getPropDtlsforExp("CHECK","ONLY_ONCE_EACH_DUPLICATED_KEY","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","IS_VIRTUAL_COMPONENT","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("OPENED_LIST","BUFFER_SIZE","M","false"));
		elemParam.add(Utils.getPropDtlsWithShwforExp("DIRECTORY","TEMP_DIRECTORY","","false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
		compNode.getElementParameter().addAll(elemParam);
		compNode.getElementParameter().add(elemparamRemDup);
	}
	public static void fillRemDupTrans(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb)
	{
		 String Rtrtranstype ="tUniqRow";
		 Double compVersion = 2.1;
		 String compName = recordperPipe.compName;
		 String xPos = recordperPipe.xLevelPos;
		 String yPos = recordperPipe.yLevelPos;
		 Utils.getcompDetails(compNode,Rtrtranstype, compVersion,xPos,yPos);
		 fillPropfortUniqueRow(recordperPipe,docspecs,compNode,srctgtdb);
		 Node.Metadata mtdata = new Node.Metadata();
			mtdata.setConnector("FLOW");
			mtdata.setLabel("rw_"+compName);
			mtdata.setName(compName);
			Node.Metadata uniqueMetadata = new Node.Metadata();
			uniqueMetadata.setConnector("UNIQUE");
			uniqueMetadata.setLabel("rw_"+compName);
			uniqueMetadata.setName("UNIQUE");
			Node.Metadata remDupmtdata = new Node.Metadata();
			remDupmtdata.setConnector("DUPLICATE");
			remDupmtdata.setLabel("rw_"+compName);
			remDupmtdata.setName("DUPLICATE");
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
					
					if(dataType==null){
						dataType = "id_String";
					}
					
					int length = cols.colStruct.length;
					int precs = cols.colStruct.precision;
					colmn.setKey("false");

//					colmn.setLength(length);
					
					
					if(cols.colStruct.length==0){
						 colmn.setLength(cols.colStruct.precision);
						 colmn.setPrecision(cols.colStruct.Scale);
					}
					else{
						colmn.setLength(cols.colStruct.length);
						colmn.setPrecision(cols.colStruct.Scale);
					}
					colmn.setName(colName);

					 String nullable = Boolean.toString(cols.colStruct.isNullable);
					 colmn.setNullable(nullable);
						if(cols.colStruct.description != null)
						{
							String comment = cols.colStruct.description;
							colmn.setComment(comment);
						}
						else
						{
							colmn.setComment("");
						}
					colmn.setPattern("");
//					colmn.setPrecision(precs);
					colmn.setType(dataType);
					colmn.setUsefulColumn("true");
					mtdata.getColumn().add(colmn);
					uniqueMetadata.getColumn().add(colmn);
					remDupmtdata.getColumn().add(colmn);
				}
			}
			compNode.getMetadata().add(mtdata);
			compNode.getMetadata().add(uniqueMetadata);
			compNode.getMetadata().add(remDupmtdata);
		 
	}
}