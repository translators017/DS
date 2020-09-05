
package com.dataSwitch.xml.ETLMapDTO;

import java.util.ArrayList;

public class MultiJoinerInfo extends BaseTransInfo
{
	public String masterCompRef = null; // Inner Join, Outer Join, ...
	
	public ArrayList<DetailElementDTO> detailElements = new ArrayList<DetailElementDTO>();
}