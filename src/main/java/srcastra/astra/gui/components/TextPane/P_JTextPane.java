/*
 * P_JTextPane.java
 *
 * Created on 3 juin 2004, 11:12
 */

package srcastra.astra.gui.components.TextPane;

import srcastra.astra.gui.components.AstraComponent;

import javax.swing.*;

/**
 * @author Administrateur
 */
public class P_JTextPane extends javax.swing.JTextPane implements AstraComponent {

    /**
     * Creates a new instance of P_JTextPane
     */
    public P_JTextPane() {
    }

    public void setEnabled(boolean enabled) {
        if (enabled) {
            setBackground(new java.awt.Color(255, 255, 255));
            setEditable(true);
        } else {
            setBackground(new java.awt.Color(204, 204, 204));
            setEditable(false);
        }
        super.setEnabled(enabled);
    }

    public void addActionListener(java.awt.event.ActionListener listener) {
    }

    public void clearIcon() {
    }

    public JComponent getGrp_Comp_nextComponent() {
        return null;
    }

    public String getText2() {
        return super.getText();
    }

    public boolean getverif() {
        return true;
    }

    public boolean isLastFocusedComponent() {
        return false;
    }

    public void removeActionListener(java.awt.event.ActionListener listener) {
    }

    public void setGrp_Comp_nextComponent(JComponent component) {
    }

    public void setLastFocusedComponent(boolean lastFocusedComponent) {
    }

    public void setWarningIcon(javax.swing.Icon workingIcon) {
    }

    public void setWorkingIcon(javax.swing.Icon workingIcon) {
    }

}
