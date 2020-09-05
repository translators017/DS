
package com.dataSwitch.xml.ETLMapping;

import java.util.ArrayList;
import java.util.List;


import com.dataSwitch.xml.ETLMapDTO.RankInfo;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation;

public class RankParser extends RankInfo
{

	public static final void processRankRule(TransRecExtDTO transRec, List<TransRecExtDTO> recsList, DataTransformation dt) 
	{
		
		RankParser transInfo = (RankParser) transRec.transInfo;

		/*if (transInfo == null)
			return;*/
		
		if (transInfo == null)
			transInfo = new RankParser();

		transRec.transInfo = transInfo;
		
		
		//transInfo = new RankParser();
		transRec.transInfo = transInfo;
		transInfo.rankFlag=dt.getRank().getFlag();
		transInfo.rankNumber=dt.getRank().getRankNumber();
		transInfo.rankPort=dt.getRank().getRankElement().getDataElementRef().toString();
		List<String> grpbyColumns=new ArrayList<String>();
		for(int i=0;i<dt.getRank().getGroupByElement().size();i++)
		{
			grpbyColumns.add(dt.getRank().getGroupByElement().get(i).getDataElementRef().toString());
		}
		
		transInfo.rankGroupElemList.addAll(grpbyColumns);
		
	}
	

}