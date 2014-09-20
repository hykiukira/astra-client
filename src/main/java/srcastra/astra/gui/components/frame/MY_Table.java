/*

 * MY_Table.java

 *

 * Created on 14 octobre 2003, 10:18

 */


package srcastra.astra.gui.components.frame;

import javax.swing.KeyStroke;

import java.awt.event.*;

import java.awt.*;

import javax.swing.*;

/**
 * @author Thomas
 */

public class MY_Table extends javax.swing.JTable {


    /**
     * Creates a new instance of MY_Table
     */

    boolean editingmode = false;

    public MY_Table() {


    }

    protected boolean processKeyBinding(KeyStroke ks, KeyEvent e,

                                        int condition, boolean pressed) {

        if (e.getKeyCode() == e.VK_UP || e.getKeyCode() == e.VK_DOWN || e.getKeyCode() == KeyEvent.VK_SHIFT)

            return false;

        else

            super.processKeyBinding(ks, e, condition, pressed);

        return false;

    }


}

