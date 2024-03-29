
package osmclass;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
 *     &lt;extension base="{}osmBasicType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element ref="{}tag"/>
 *           &lt;element ref="{}member"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tagOrMember"
})
@XmlRootElement(name = "relation")
public class Relation
    extends OsmBasicType
{

    @XmlElements({
        @XmlElement(name = "tag", type = Tag.class),
        @XmlElement(name = "member", type = Member.class)
    })
    protected List<Object> tagOrMember;

    /**
     * Gets the value of the tagOrMember property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tagOrMember property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTagOrMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tag }
     * {@link Member }
     * 
     * 
     */
    public List<Object> getTagOrMember() {
        if (tagOrMember == null) {
            tagOrMember = new ArrayList<Object>();
        }
        return this.tagOrMember;
    }

}
