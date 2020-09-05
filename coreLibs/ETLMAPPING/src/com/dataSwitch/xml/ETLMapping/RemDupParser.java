
package com.dataSwitch.xml.ETLMapping;

import java.util.List;

import com.dataSwitch.xml.ETLMapDTO.ColumnDTO;
import com.dataSwitch.xml.ETLMapDTO.RemoveDupInfo;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation;
import com.dataSwitch.xml.connectorspecs.Connector.DataMappings.DataMapping.DataTransformations.DataTransformation.RemoveDuplicate.KeyColumn;


public class RemDupParser extends RemoveDupInfo
{
/*	public static final void processRankRule (TransRecExtDTO transRec, List<TransRecExtDTO> recsList, DataTransformation dt)
	{
		RemDupParser transInfo = (RemDupParser) transRec.transInfo;
		if (transInfo != null)
			return;

		transInfo = new RemDupParser();
		transRec.transInfo = transInfo;
		//transInfo.keyCols.add(dt.getRank().getGroupByPort());

		String flagOrder = dt.getRank().getFlag();
		if (flagOrder == null)
			flagOrder = "ASCENDING";

		//transInfo.flagList.add(flagOrder);
	}*/

	public static final void processRemoveDuplicateRule(TransRecExtDTO transRec, List<TransRecExtDTO> recsList,
			DataTransformation dt) {

		RemDupParser transInfo = (RemDupParser) transRec.transInfo;
		if (transInfo != null)
			return;
		transInfo = new RemDupParser();
		transRec.transInfo = transInfo;

		for (int i = 0; i < dt.getRemoveDuplicate().getKeyColumn().size(); i++)
		{
			KeyColumn key = dt.getRemoveDuplicate().getKeyColumn().get(i);

			String srcDataRef = key.getKeyComponentRef();
			String srcDataElementName = key.getKeyElementRef();

			int nIndex = srcDataElementName.indexOf(".");
			if (nIndex != -1)
			{
				if (srcDataRef.trim().length() <= 0)
					srcDataRef = srcDataElementName.substring(0, nIndex);
				srcDataRef = srcDataElementName.substring(nIndex + 1);
			}

			ColumnDTO colDTO = Utils.getColumnDTOByNameInTransRec(transRec, srcDataRef, srcDataElementName);
			transInfo.keyCols.add(colDTO);
			transInfo.keyColsList.add(srcDataRef + "." + srcDataElementName);
			
			String FirstOrLastOrder = dt.getRemoveDuplicate().getFirstOrLastValue();
			if (FirstOrLastOrder == null)
				FirstOrLastOrder = "First";

			transInfo.FirstOrLastValueList.add(FirstOrLastOrder);
		}
		
	}
}