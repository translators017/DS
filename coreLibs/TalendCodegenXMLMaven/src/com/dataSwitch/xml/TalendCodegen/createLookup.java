
package com.dataSwitch.xml.TalendCodegen;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.LookupInfo;
import com.dataSwitch.xml.ETLMapDTO.OutRecDTO;
import com.dataSwitch.xml.ETLMapDTO.SrcTgtInfo;
import com.dataSwitch.xml.ETLMapping.TransRecExtDTO;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node;
import com.dataSwitch.xml.Talend.TalendfileProcessType.Node.ElementParameter;
import com.dataSwitch.xml.connectorspecs.Connector;

public class createLookup {
	
	public static void fillPropfortLkp(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb)
	{
		LookupInfo lkp = (LookupInfo)recordperPipe.transInfo;
		String compName = recordperPipe.compName;
		ArrayList<ElementParameter> elemParam = new ArrayList<ElementParameter>();
		elemParam.add(Utils.getPropDtlsWithShwforExp("TEXT","UNIQUE_NAME",compName,"false"));
		elemParam.add(Utils.getPropDtlsforExp("CHECK","USE_LOOKUP_COLS","true"));
		ElementParameter elemParamForJnr = new ElementParameter();
		elemParamForJnr.setField("TABLE");
		elemParamForJnr.setName("JOIN_KEY");
		String jnrMstrAdd = "";
		String jnrDtlAdd  = "";
		/*for(int j = 0 ; j < lkp.masterElemList.size(); j++)
		{
			String jnrMstrName = lkp.masterElemList.get(j);
			jnrMstrAdd += jnrMstrName+",";	
		}
		for(int j1 = 0 ; j1 < lkp.lookupElemList.size(); j1++)
		{
			String jnrDtlsName = lkp.lookupElemList.get(j1);
			jnrDtlAdd += jnrDtlsName+",";
		}*/
		StringTokenizer tokenizer = new StringTokenizer(jnrMstrAdd, ",");
		StringTokenizer tokenizer1 = new StringTokenizer(jnrDtlAdd, ",");
        while (tokenizer.hasMoreTokens() || tokenizer1.hasMoreTokens())
        {
        	String mstrCompName = tokenizer.nextToken();
        	String mstrColName = mstrCompName.substring(mstrCompName.indexOf(".")+1, mstrCompName.length());
        	String dtlCompname = tokenizer1.nextToken();	
        	String dtlColName = dtlCompname.substring(dtlCompname.indexOf(".")+1, dtlCompname.length());
			List<ElementParameter.ElementValue> elemValueList = new ArrayList<ElementParameter.ElementValue>();
	    	elemValueList.add(Utils.getPropElemforTrans("INPUT_COLUMN", mstrColName));
	    	elemValueList.add(Utils.getPropElemforTrans("LOOKUP_COLUMN", dtlColName));
	    	elemParamForJnr.getElementValue().addAll(elemValueList);
        }
		ElementParameter elemParamForLkp = new ElementParameter();
		elemParamForLkp.setField("TABLE");
		elemParamForLkp.setName("LOOKUP_COLS");
		/*if(lkp.returnDataList != null)
		{
			for(int j = 0 ;j < lkp.returnDataList.size() ; j++)
			{
				String returnCols = lkp.returnDataList.get(j);
				String returnColName = returnCols.substring(returnCols.indexOf(".")+1, returnCols.length());
				List<ElementParameter.ElementValue> elemValueList = new ArrayList<ElementParameter.ElementValue>();
		    	elemValueList.add(Utils.getPropElemforTrans("OUTPUT_COLUMN", returnColName));
		    	elemValueList.add(Utils.getPropElemforTrans("LOOKUP_COLUMN", returnColName));
		    	elemParamForLkp.getElementValue().addAll(elemValueList);
			}
		}*/
		elemParam.add(elemParamForJnr);
		elemParam.add(elemParamForLkp);
	    elemParam.add(Utils.getPropDtlsforExp("CHECK","USE_INNER_JOIN","false"));
		elemParam.add(Utils.getPropDtlsforExp("TEXT","CONNECTION_FORMAT","row"));
		compNode.getElementParameter().addAll(elemParam);
	}
	public static void fillLkpTrans(TransRecExtDTO recordperPipe,Connector docspecs,Node compNode,SrcTgtInfo srctgtdb)
	{
		Double compVersion = 0.101;
		String Jnrtranstype = "tJoin";
		String compName = recordperPipe.compName;
		String xPos = recordperPipe.xLevelPos;
		String yPos = recordperPipe.yLevelPos;
		Utils.getcompDetails(compNode,Jnrtranstype, compVersion,xPos,yPos);
		fillPropfortLkp(recordperPipe,docspecs,compNode,srctgtdb);
		Node.Metadata mtdata = new Node.Metadata();
		mtdata.setConnector("FLOW");
		mtdata.setLabel("rw_"+compName);
		mtdata.setName(compName);
		Node.Metadata rjtMtdata = new Node.Metadata();
		rjtMtdata.setConnector("FLOW");
		rjtMtdata.setLabel("metadata");
		rjtMtdata.setName("REJECT");
		for(int j = 0 ; j < recordperPipe.outRecsList.size();j++)
		{
			OutRecDTO outRecCols = recordperPipe.outRecsList.get(j);
			for (int i = 0; i <outRecCols.columnList.size(); i++) 
			{
				Node.Metadata.Column colmn = new Node.Metadata.Column();
				ColumnDTO cols = outRecCols.columnList.get(i);
				
				/*String colsName = cols.colStruct.name;
				String colName = colsName.substring(colsName.indexOf(".")+1, colsName.length());
				*/
				String colName = cols.aliasName;
				String dataType = cols.colStruct.dataType;
				int length = cols.colStruct.length;
				int precs = cols.colStruct.precision;
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
//				colmn.setPrecision(precs);
				colmn.setType(dataType);
				colmn.setUsefulColumn("true");
				mtdata.getColumn().add(colmn);
				rjtMtdata.getColumn().add(colmn);
			}	
		}
		compNode.getMetadata().add(mtdata);
		compNode.getMetadata().add(rjtMtdata);	
	}

}