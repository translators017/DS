//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.11 at 11:45:35 AM IST 
//


package co.dataswitch.config.bean;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
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


    
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Configurations }
     * 
     */
    public Configurations createConfigurations() {
        return new Configurations();
    }

    /**
     * Create an instance of {@link Configurations.Configuration }
     * 
     */
    public Configurations.Configuration createConfigurationsConfiguration() {
        return new Configurations.Configuration();
    }

    /**
     * Create an instance of {@link Configurations.Configuration.Properties }
     * 
     */
    public Configurations.Configuration.Properties createConfigurationsConfigurationProperties() {
        return new Configurations.Configuration.Properties();
    }

    /**
     * Create an instance of {@link Configurations.Configuration.Properties.Property }
     * 
     */
    public Configurations.Configuration.Properties.Property createConfigurationsConfigurationPropertiesProperty() {
        return new Configurations.Configuration.Properties.Property();
    }

}
