//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.03 at 09:04:51 AM CET
//


package matching;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for WSMatchingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WSMatchingType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Macthing" type="{http://www.kth.se/ict/id2208/Matching}MatchedWebServiceType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WSMatchingType", propOrder = {
        "matching"
})
@XmlRootElement(name = "WSMatchingType")
        public class WSMatchingType {

    @XmlElement(name = "Matching", required = true)
    protected List<MatchedWebServiceType> matching;

    /**
     * Gets the value of the matching property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the matching property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMatching().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MatchedWebServiceType }
     * 
     * 
     */
    public List<MatchedWebServiceType> getMatching() {
        if (matching == null) {
            matching = new ArrayList<MatchedWebServiceType>();
        }
        return this.matching;
    }

}
