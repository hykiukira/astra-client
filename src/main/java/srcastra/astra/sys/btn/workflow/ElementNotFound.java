/*
 * ElementNotFound.java
 *
 * Created on 21 septembre 2004, 21:24
 */

package srcastra.astra.sys.btn.workflow;

/**
 *
 * @author  Administrateur
 */
import org.jdom.*;
public class ElementNotFound {
    
    /** Creates a new instance of ElementNotFound */
    Element child;
    Element parent;
    public ElementNotFound(Element child,Element parent) {
        this.child=child;
        this.parent=parent;
    }
    
    /**
     * Getter for property child.
     * @return Value of property child.
     */
    public org.jdom.Element getChild() {
        return child;
    }
    
    /**
     * Setter for property child.
     * @param child New value of property child.
     */
    public void setChild(org.jdom.Element child) {
        this.child = child;
    }
    
    /**
     * Getter for property parent.
     * @return Value of property parent.
     */
    public org.jdom.Element getParent() {
        return parent;
    }
    
    /**
     * Setter for property parent.
     * @param parent New value of property parent.
     */
    public void setParent(org.jdom.Element parent) {
        this.parent = parent;
    }
    
}
