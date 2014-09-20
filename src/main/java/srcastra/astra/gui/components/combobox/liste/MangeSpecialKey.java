/*

 * MangeSpecialKey.java

 *

 * Created on 3 décembre 2003, 13:07

 */


package srcastra.astra.gui.components.combobox.liste;

import java.awt.event.*;

import javax.swing.*;


/**
 * @author Thomas
 */

public class MangeSpecialKey {


    /**
     * Creates a new instance of MangeSpecialKey
     */

    public MangeSpecialKey() {

    }

    public static void closePopup(KeyEvent evt, JPopupMenu popup) {


        if (evt.getKeyCode() == evt.VK_F1) {

            popup.setVisible(false);

        } else if (evt.getKeyCode() == evt.VK_F2) {

            popup.setVisible(false);

            System.out.println("set popupinvisible");

        } else if (evt.getKeyCode() == evt.VK_F3) {

            popup.setVisible(false);

        } else if (evt.getKeyCode() == evt.VK_F4) {

            popup.setVisible(false);

        } else if (evt.getKeyCode() == evt.VK_F5) {

            popup.setVisible(false);

        } else if (evt.getKeyCode() == evt.VK_F6) {

            popup.setVisible(false);

        } else if (evt.getKeyCode() == evt.VK_F7) {

            popup.setVisible(false);

        } else if (evt.getKeyCode() == evt.VK_F8) {

            popup.setVisible(false);

        } else if (evt.getKeyCode() == evt.VK_F9) {

            popup.setVisible(false);

        } else if (evt.getKeyCode() == evt.VK_F10) {

            popup.setVisible(false);

        } else if (evt.getKeyCode() == evt.VK_F11) {

            popup.setVisible(false);

        } else if (evt.getKeyCode() == evt.VK_F12) {

            popup.setVisible(false);

        }

    }


}

