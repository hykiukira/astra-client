/*



 * mtextfield.java



 *



 * Created on 27 février 2003, 12:59



 */
package srcastra.astra.gui.components.textFields;

import java.awt.event.*;

/**
 * @author thomas
 */
public class Mtextfield extends javax.swing.JTextField implements java.io.Serializable {
    /**
     * Creates a new instance of mtextfield
     */
    ComponentInterface m_parent;

    public Mtextfield(ComponentInterface parent) {
        m_parent = parent;
        this.addKeyListener(m_keylistener);


    }

    /**
     * Getter for property next.
     *
     * @return Value of property next.
     */
    public java.awt.Component getNext() {
        return next;


    }

    /**
     * Setter for property next.
     *
     * @param next New value of property next.
     */
    public void setNext(java.awt.Component next) {
        this.next = next;


    }

    public void transferFocus() {
        if (next != null) {
            if (next.isEnabled()) {
                if (!m_parent.isOpen()) if (m_parent.getCorreInput()) next.requestFocus();
                else m_parent.setGoodIcon(false);


            }


        }


    }

    /**
     * Getter for property remove.
     *
     * @return Value of property remove.
     */
    public boolean isRemove() {
        return remove;


    }

    /**
     * Setter for property remove.
     *
     * @param remove New value of property remove.
     */
    public void setRemove(boolean remove) {
        this.remove = remove;


    }

    /**
     * Getter for property remove2.
     *
     * @return Value of property remove2.
     */
    public boolean isRemove2() {
        return remove2;

    }

    /**
     * Setter for property remove2.
     *
     * @param remove2 New value of property remove2.
     */
    public void setRemove2(boolean remove2) {
        this.remove2 = remove2;

    }

    public class My_keyListener extends KeyAdapter implements java.io.Serializable {
        public void keyPressed(java.awt.event.KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == 8) remove = true;
            else if (keyEvent.getKeyCode() == keyEvent.VK_DELETE) remove2 = true;

        }

        public void keyReleased(java.awt.event.KeyEvent keyEvent) {
        }

        public void keyTyped(java.awt.event.KeyEvent keyEvent) {
        }


    }

    KeyListener m_keylistener = new Mtextfield.My_keyListener();

    java.awt.Component next;
    boolean remove;
    boolean remove2;


}



