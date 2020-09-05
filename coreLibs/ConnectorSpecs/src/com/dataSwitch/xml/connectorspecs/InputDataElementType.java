
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
import javax.xml.bind.annotation.XmlType;


/**
 * Global element type used to specify named reference
 * 				to the entity data element including the system entity reference.
 * 			
 * 
 * <p>Java class for inputDataElementType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inputDataElementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="inputComponentRef" use="required" type="{}nameType" />
 *       &lt;attribute name="inputDataElementRef" use="required" type="{}nameType" />
 *       &lt;attribute name="alias" type="{}nameType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inputDataElementType")
public class InputDataElementType {

    @XmlAttribute(name = "inputComponentRef", required = true)
    protected String inputComponentRef;
    @XmlAttribute(name = "inputDataElementRef", required = true)
    protected String inputDataElementRef;
    @XmlAttribute(name = "alias")
    protected String alias;

    /**
     * Gets the value of the inputComponentRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputComponentRef() {
        return inputComponentRef;
    }

    /**
     * Sets the value of the inputComponentRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputComponentRef(String value) {
        this.inputComponentRef = value;
    }

    /**
     * Gets the value of the inputDataElementRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputDataElementRef() {
        return inputDataElementRef;
    }

    /**
     * Sets the value of the inputDataElementRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputDataElementRef(String value) {
        this.inputDataElementRef = value;
    }

    /**
     * Gets the value of the alias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the value of the alias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlias(String value) {
        this.alias = value;
    }

}