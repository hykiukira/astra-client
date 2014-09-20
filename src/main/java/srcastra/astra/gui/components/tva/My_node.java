/*

 * My_node.java

 *

 * Created on 29 avril 2003, 9:59

 */


package srcastra.astra.gui.components.tva;


/**
 * @author Thomas
 */

public class My_node extends javax.swing.tree.DefaultMutableTreeNode {


    /**
     * Creates a new instance of My_node
     */

    public My_node(TvaComponent comp) {

        super(comp.getLibelle1());

        component = comp;


    }


    /**
     * Getter for property component.
     *
     * @return Value of property component.
     */

    public srcastra.astra.gui.components.tva.TvaComponent getComponent() {

        return component;

    }


    /**
     * Setter for property component.
     *
     * @param component New value of property component.
     */


    TvaComponent component;


}

