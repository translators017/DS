
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.07 at 04:15:27 PM IST 
//


package com.dataSwitch.xml.connectorspecs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parentProcessFlowVariables" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="mappingVariables" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="variableAssignmentType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Pre Session Variable Assignment"/>
 *             &lt;enumeration value="Pre Session on Success Variable Assignment"/>
 *             &lt;enumeration value="Post Session on Failure Variable Assignment"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "parentProcessFlowVariables"
})
@XmlRootElement(name = "prePostVariableAssignment")
public class PrePostVariableAssignment {

    @XmlElement(required = true)
    protected String parentProcessFlowVariables;
    @XmlAttribute(name = "mappingVariables")
    protected String mappingVariables;
    @XmlAttribute(name = "variableAssignmentType", required = true)
    protected String variableAssignmentType;

    /**
     * Gets the value of the parentProcessFlowVariables property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentProcessFlowVariables() {
        return parentProcessFlowVariables;
    }

    /**
     * Sets the value of the parentProcessFlowVariables property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentProcessFlowVariables(String value) {
        this.parentProcessFlowVariables = value;
    }

    /**
     * Gets the value of the mappingVariables property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMappingVariables() {
        return mappingVariables;
    }

    /**
     * Sets the value of the mappingVariables property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMappingVariables(String value) {
        this.mappingVariables = value;
    }

    /**
     * Gets the value of the variableAssignmentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVariableAssignmentType() {
        return variableAssignmentType;
    }

    /**
     * Sets the value of the variableAssignmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVariableAssignmentType(String value) {
        this.variableAssignmentType = value;
    }

}