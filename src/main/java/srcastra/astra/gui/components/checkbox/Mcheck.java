/*



 * Mcheck.java



 *



 * Created on 27 février 2003, 14:47



 */


package srcastra.astra.gui.components.checkbox;


import srcastra.astra.gui.components.textFields.ComponentInterface;


/**
 * @author thomas
 */


public class Mcheck extends javax.swing.JCheckBox {


    ComponentInterface m_parent;


    /**
     * Creates a new instance of Mcheck
     */


    public Mcheck(ComponentInterface parent, String title) {

        super(title);

        m_parent = parent;


    }


    public java.awt.Component getNext() {


        return next;


    }


    public void setNext(java.awt.Component next) {


        this.next = next;


    }


    public void transferFocus() {


        if (next != null) {


            if (next.isEnabled()) {


                next.requestFocus();


            } else System.out.println("comp disable");


        } else System.out.println("comp null");


    }


    java.awt.Component next;


}



