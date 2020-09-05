
package com.dataSwitch.ds.commons.datatyperules;

import com.dataSwitch.ds.commons.datatyperules.DataTypeMapRules.dsDataType;

public class DataTypeRule {

	private dsDataType genDataType;

	private String dbDataType;

	private String recommendedType;

	private String applicability;

	private char precisionScaleIndicator; 
	
	private String maxLengthPrecision;

	private String maxScale;

	private String defaultLengthPrecisionForInformatica;

	private String defaultScaleForInformatica;
	
	private String defaultLengthPrecision;

	private String defaultScale;

	private String minValue;

	private String maxValue;

	private String defaultSupport;

	private String defaultValue;

	private String sizeInBytes;

	private String notNullSupport;
	
	public DataTypeRule (TypeRule typeRule, SizeRule sizeRule, OtherRule otherRule) 
	{
		this.genDataType = typeRule.genDataType;
		this.dbDataType = typeRule.dbDataType;
		this.recommendedType = typeRule.recommendedType;
		this.applicability = typeRule.applicability;
		
		this.precisionScaleIndicator = sizeRule.precisionScaleIndicator;
		this.maxLengthPrecision = sizeRule.maxLengthPrecision;
		this.maxScale = sizeRule.maxScale;
		this.defaultLengthPrecisionForInformatica = sizeRule.defaultLengthPrecisionForInformatica;
		this.defaultScaleForInformatica = sizeRule.defaultScaleForInformatica;
		this.defaultLengthPrecision = sizeRule.defaultLengthPrecision;
		this.defaultScale = sizeRule.defaultScale;

		this.minValue = otherRule.minValue;
		this.maxValue = otherRule.maxValue;
		this.defaultSupport = otherRule.defaultSupport;
		this.defaultValue = otherRule.defaultValue;
		this.sizeInBytes = otherRule.sizeInBytes;
		this.notNullSupport = otherRule.notNullSupport;
	}

//	public DataTypeRule(String genDataType,String dbDataType,String recommendedType,String applicability,String maxPrecision,String minScale,String maxScale,String maxDataLength,String minValue,String maxValue,String defaultSupport,String defaultValue,String sizeInBytes,String defaultSize,String notNullSupport) {
//
//		this.genDataType = genDataType;
//		this.dbDataType = dbDataType;
//		this.recommendedType = recommendedType;
//		this.applicability = applicability;
//		this.maxPrecision = maxPrecision;
//		this.minScale = minScale;
//		this.maxScale = maxScale;
//		this.maxDataLength = maxDataLength;
//		this.minValue = minValue;
//		this.maxValue = maxValue;
//		this.defaultSupport = defaultSupport;
//		this.defaultValue = defaultValue;
//		this.sizeInBytes = sizeInBytes;
//		this.defaultSize = defaultSize;
//		this.notNullSupport = notNullSupport;
//		
//	
//	}
//

	public dsDataType getGenDataType() {
		return genDataType;
	}

	public void setGenDataType(dsDataType genDataType) {
		this.genDataType = genDataType;
	}

	public String getDbDataType() {
		return dbDataType;
	}

	public void setDbDataType(String dbDataType) {
		this.dbDataType = dbDataType;
	}

	public String getRecommendedType() {
		return recommendedType;
	}

	public void setRecommendedType(String recommendedType) {
		this.recommendedType = recommendedType;
	}

	public String getApplicability() {
		return applicability;
	}

	public void setApplicability(String applicability) {
		this.applicability = applicability;
	}

	public void setMaxScale(String maxScale) {
		this.maxScale = maxScale;
	}

	public String getMaxScale() {
		return maxScale;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getDefaultSupport() {
		return defaultSupport;
	}

	public void setDefaultSupport(String defaultSupport) {
		this.defaultSupport = defaultSupport;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setSizeInBytes(String sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}

	public String getSizeInBytes() {
		return sizeInBytes;
	}

	public String getNotNullSupport() {
		return notNullSupport;
	}

	public void setNotNullSupport(String notNullSupport) {
		this.notNullSupport = notNullSupport;
	}

	public char getPrecisionScaleIndicator() {
		return precisionScaleIndicator;
	}

	public void setPrecisionScaleIndicator(char precisionScaleIndicator) {
		this.precisionScaleIndicator = precisionScaleIndicator;
	}

	public String getMaxLengthPrecision() {
		return maxLengthPrecision;
	}

	public void setMaxLengthPrecision(String maxLengthPrecision) {
		this.maxLengthPrecision = maxLengthPrecision;
	}

	public String getDefaultLengthPrecisionForInformatica() {
		return defaultLengthPrecisionForInformatica;
	}

	public void setDefaultLengthPrecisionForInformatica(String defaultLengthPrecisionForInformatica) {
		this.defaultLengthPrecisionForInformatica = defaultLengthPrecisionForInformatica;
	}

	public String getDefaultScaleForInformatica() {
		return defaultScaleForInformatica;
	}

	public void setDefaultScaleForInformatica(String defaultScaleForInformatica) {
		this.defaultScaleForInformatica = defaultScaleForInformatica;
	}
	
	public String getDefaultLengthPrecision() {
		return defaultLengthPrecision;
	}

	public void setDefaultLengthPrecision(String defaultLengthPrecision) {
		this.defaultLengthPrecision = defaultLengthPrecision;
	}

	public String getDefaultScale() {
		return defaultScale;
	}

	public void setDefaultScale(String defaultScale) {
		this.defaultScale = defaultScale;
	}
	


}


class TypeRule {

	public dsDataType genDataType;

	public String dbDataType;

	public String recommendedType;

	public String applicability;
	
	public TypeRule (dsDataType charVarchar,String dbDataType,String recommendedType,String applicability)
	{
		this.genDataType = charVarchar;
		this.dbDataType = dbDataType;
		this.recommendedType = recommendedType;
		this.applicability = applicability;
	}
}

class SizeRule {

	public char precisionScaleIndicator; // P - For Length/Precision Only, - N For Not applicable, S- Includes Scale

	public String maxLengthPrecision;

	public String maxScale;

	public String defaultLengthPrecisionForInformatica;

	public String defaultScaleForInformatica;
	
	public String defaultLengthPrecision;

	public String defaultScale;
	
	
	public SizeRule (char precisionScaleIndicator, String maxLengthPrecision, String maxScale, String defaultLengthPrecisionForInformatica, String defaultScaleForInformatica)
	{
		this.precisionScaleIndicator = precisionScaleIndicator; 
		this.maxLengthPrecision = maxLengthPrecision;
		this.maxScale = maxScale;
		this.defaultLengthPrecisionForInformatica = defaultLengthPrecisionForInformatica;
		this.defaultScaleForInformatica = defaultScaleForInformatica;
	}
	

	public SizeRule (char precisionScaleIndicator, String maxLengthPrecision, String maxScale, String defaultLengthPrecisionForInformatica, String defaultScaleForInformatica,String defaultLengthPrecision, String defaultScale)
	{
		this.precisionScaleIndicator = precisionScaleIndicator; 
		this.maxLengthPrecision = maxLengthPrecision;
		this.maxScale = maxScale;
		this.defaultLengthPrecisionForInformatica = defaultLengthPrecisionForInformatica;
		this.defaultScaleForInformatica = defaultScaleForInformatica;
		this.defaultLengthPrecision = defaultLengthPrecision;
		this.defaultScale = defaultScale;
	}
}


class OtherRule {

	public String minValue;

	public String maxValue;

	public String defaultSupport;

	public String defaultValue;

	public String sizeInBytes;

	public String notNullSupport;

	public OtherRule (String minValue,String maxValue,String defaultSupport,String defaultValue,String sizeInBytes,String notNullSupport)
	{
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.defaultSupport = defaultSupport;
		this.defaultValue = defaultValue;
		this.sizeInBytes = sizeInBytes;
		this.notNullSupport = notNullSupport;
	}
}