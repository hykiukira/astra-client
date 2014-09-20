/*

 * MainFrameKeystroke.java

 *

 * Created on 11 septembre 2003, 8:54

 */


package srcastra.astra.gui;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import java.awt.event.*;

import javax.swing.*;

import java.util.*;

/**
 * @author Thomas
 */

public class MainFrameKeystroke {


    /**
     * Creates a new instance of MainFrameKeystroke
     */

    ActionListener actionsf1;

    ActionListener actionCtrlf1;

    ActionListener actionaltF;

    ActionListener actionaltG;

    ActionListener actionaltI;

    ActionListener actionaltA;

    ActionListener actionCtrlG;

    KeyStroke f1;

    KeyStroke Ctrlf1;

    KeyStroke altF;

    KeyStroke altG;

    KeyStroke altI;

    KeyStroke altA;

    KeyStroke Ctrlg;

    Hashtable keystrokeTable;

    Hashtable actionTable;

    MainFrame frame;

    public MainFrameKeystroke(MainFrame frame) {

        this.frame = frame;

        keystrokeTable = new Hashtable();

        f1 = KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_F1));

        Ctrlf1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.CTRL_MASK);

        //Ctrlg=KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_F2));

        //
        //altG=KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_));

        // altI=KeyStroke.getKeyStroke(KeyEvent.VK_I,KeyEvent.ALT_MASK);

        // altA=KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.ALT_MASK);

        keystrokeTable.put(new Integer(1), f1);

        keystrokeTable.put(new Integer(2), Ctrlf1);

        //  keystrokeTable.put(new Integer(3),Ctrlg);

        // keystrokeTable.put(new Integer(3),altF);

        // keystrokeTable.put(new Integer(4),altG);     

        // keystrokeTable.put(new Integer(5),altI);

        // keystrokeTable.put(new Integer(6),altA);

    }

    public void registerComponent() {

        actionsf1 = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.showAgenda();

            }

        };


        actionCtrlf1 = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.showHistorique();


            }

        };


        actionaltF = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.openFichier();


            }

        };

        actionaltG = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                //frame.openGestion();

                frame.showGDS();

            }

        };

        actionaltI = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.openFinancier();


            }

        };

        actionaltA = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                frame.openAchat();


            }

        };

        actionTable = new Hashtable();

        actionTable.put(new Integer(1), actionsf1);

        actionTable.put(new Integer(2), actionCtrlf1);

        // actionTable.put(new Integer(3),actionCtrlG);

        //  actionTable.put(new Integer(3),actionaltF);

        // actionTable.put(new Integer(4),actionaltG);

        // actionTable.put(new Integer(5),actionaltI);

        //  actionTable.put(new Integer(6),actionaltA);

    }

    public void setAction(int[] tab) {

        if (tab == null) {

            System.out.println("tableau null");
        } else {

            frame.resetKeyboardActions();

            for (int i = 0; i < tab.length; i++) {

                System.out.println("numero :" + tab[i]);

                frame.registerKeyboardAction((ActionListener) actionTable.get(new Integer(tab[i])), (KeyStroke) keystrokeTable.get(new Integer(tab[i])), JComponent.WHEN_IN_FOCUSED_WINDOW);

            }

        }

    }


}

