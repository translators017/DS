
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.25 at 11:35:53 AM IST 
//


package com.dataSwitch.xml.TalendConfigurations;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dataSwitch.xml.pcConfigurations package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dataSwitch.xml.pcConfigurations
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Talendconfig }
     * 
     */
    public Talendconfig createDSconfig() {
        return new Talendconfig();
    }

    /**
     * Create an instance of {@link Talendconfig.Configuration }
     * 
     */
    public Talendconfig.Configuration createDSconfigConfiguration() {
        return new Talendconfig.Configuration();
    }

    /**
     * Create an instance of {@link Talendconfig.Configuration.Property }
     * 
     */
    public Talendconfig.Configuration.Property createDSconfigConfigurationProperty() {
        return new Talendconfig.Configuration.Property();
    }

}