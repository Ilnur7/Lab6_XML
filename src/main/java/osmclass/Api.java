
package osmclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{}version"/>
 *         &lt;element ref="{}area"/>
 *         &lt;element ref="{}tracepoints"/>
 *         &lt;element ref="{}waynodes"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "version",
    "area",
    "tracepoints",
    "waynodes"
})
@XmlRootElement(name = "api")
public class Api {

    @XmlElement(required = true)
    protected Version version;
    @XmlElement(required = true)
    protected Area area;
    @XmlElement(required = true)
    protected Tracepoints tracepoints;
    @XmlElement(required = true)
    protected Waynodes waynodes;

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link Version }
     *     
     */
    public Version getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link Version }
     *     
     */
    public void setVersion(Version value) {
        this.version = value;
    }

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link Area }
     *     
     */
    public Area getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link Area }
     *     
     */
    public void setArea(Area value) {
        this.area = value;
    }

    /**
     * Gets the value of the tracepoints property.
     * 
     * @return
     *     possible object is
     *     {@link Tracepoints }
     *     
     */
    public Tracepoints getTracepoints() {
        return tracepoints;
    }

    /**
     * Sets the value of the tracepoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tracepoints }
     *     
     */
    public void setTracepoints(Tracepoints value) {
        this.tracepoints = value;
    }

    /**
     * Gets the value of the waynodes property.
     * 
     * @return
     *     possible object is
     *     {@link Waynodes }
     *     
     */
    public Waynodes getWaynodes() {
        return waynodes;
    }

    /**
     * Sets the value of the waynodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Waynodes }
     *     
     */
    public void setWaynodes(Waynodes value) {
        this.waynodes = value;
    }

}
