//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.26 at 09:04:51 AM CET 
//


package generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the se.kth.ict.id2208.matching package. 
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

    private final static QName _WSMatching_QNAME = new QName("http://www.kth.se/ict/id2208/Matching", "WSMatching");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: se.kth.ict.id2208.matching
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WSMatchingType }
     * 
     */
    public se.kth.ict.id2208.matching.WSMatchingType createWSMatchingType() {
        return new WSMatchingType();
    }

    /**
     * Create an instance of {@link MatchedElementType }
     * 
     */
    public MatchedElementType createMatchedElementType() {
        return new MatchedElementType();
    }

    /**
     * Create an instance of {@link MatchedOperationType }
     * 
     */
    public MatchedOperationType createMatchedOperationType() {
        return new MatchedOperationType();
    }

    /**
     * Create an instance of {@link MatchedWebServiceType }
     * 
     */
    public MatchedWebServiceType createMatchedWebServiceType() {
        return new MatchedWebServiceType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSMatchingType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.kth.se/ict/id2208/Matching", name = "WSMatching")
    public JAXBElement<WSMatchingType> createWSMatching(WSMatchingType value) {
        return new JAXBElement<WSMatchingType>(_WSMatching_QNAME, WSMatchingType.class, null, value);
    }

}
