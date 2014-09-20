/*







 * KeyStrokeManager.java







 *







 * Created on 29 janvier 2003, 10:50







 */


package srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux;


import srcastra.astra.gui.components.actions.actionToolBar.*;


import java.awt.event.*;


import javax.swing.*;


import java.util.*;


/**
 * @author Thomas
 */


public class KeyStrokeManager {


    ActionListener actionswitch;


    ActionListener actionf5;


    ActionListener actionf4;


    ActionListener actionf3;


    ActionListener actionf2;


    ActionListener actionf8;


    ActionListener actionf9;

    ActionListener actionf10;


    ActionListener actionKeyup;


    ActionListener actionKeydown;


    KeyStroke testSwitch;


    KeyStroke testF5;


    KeyStroke testUp;


    KeyStroke testDown;


    KeyStroke testF3;


    KeyStroke testF2;


    KeyStroke testF8;


    KeyStroke testF9;


    KeyStroke testF4;

    KeyStroke testF10;


    /**
     * Creates a new instance of KeyStrokeManager
     */


    InterfaceMainPanel m_component;


    Hashtable keystrokeTable;


    Hashtable actionTable;


    boolean sw;


    public KeyStrokeManager(boolean sw) {


        this.sw = sw;


        if (sw) {

            keystrokeTable = new Hashtable();

            testSwitch = KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_F12));

            testF5 = KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_F5));

            testF3 = KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_F3));

            testF2 = KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_F2));

            testF8 = KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_F8));

            testF10 = KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_F10));


            testF9 = KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_F9));


            testF4 = KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_F4));


            String f4 = KeyEvent.getKeyText(KeyEvent.VK_F4);


            String up = KeyEvent.getKeyText(KeyEvent.VK_UP);


            String down = KeyEvent.getKeyText(KeyEvent.VK_DOWN);


            testUp = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);


            testDown = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);


            keystrokeTable.put(new Integer(ActionToolBar.DO_SWITCH), testSwitch);


            keystrokeTable.put(new Integer(ActionToolBar.DO_MODIFY), testF3);


            keystrokeTable.put(new Integer(ActionToolBar.DO_CREATE), testF2);


            keystrokeTable.put(new Integer(ActionToolBar.DO_CANCEL), testF8);


            keystrokeTable.put(new Integer(ActionToolBar.DO_DELETE), testF9);


            keystrokeTable.put(new Integer(ActionToolBar.DO_PREVIOUS), testF5);


            keystrokeTable.put(new Integer(ActionToolBar.DO_ACCEPT), testF4);


            keystrokeTable.put(new Integer(ActionToolBar.DO_UP), testUp);


            keystrokeTable.put(new Integer(ActionToolBar.DO_DOWN), testDown);

            keystrokeTable.put(new Integer(ActionToolBar.DO_F10), testF10);


        }


    }


    public void registerComponent(InterfaceMainPanel component) {


        if (sw) {


            m_component = component;


            ActionListener actionswitch = new ActionListener() {


                public void actionPerformed(ActionEvent e) {


                    m_component.doSwitch();


                    System.out.println("keystrokemanager:doSwitch");


                }


            };


            actionf5 = new ActionListener() {


                public void actionPerformed(ActionEvent e) {


                    m_component.doPrevious();


                    System.out.println("keystrokemanager:previous");


                }


            };


            actionf4 = new ActionListener() {


                public void actionPerformed(ActionEvent e) {


                    m_component.doAccept();


                    System.out.println("keystrokemanager:previous");


                }


            };


            actionf3 = new ActionListener() {


                public void actionPerformed(ActionEvent e) {


                    m_component.doModify();


                    System.out.println("keystrokemanager:doModify");


                }


            };


            actionf2 = new ActionListener() {


                public void actionPerformed(ActionEvent e) {


                    m_component.doCreate();


                    System.out.println("keystrokemanager:docreate");


                }


            };


            actionf8 = new ActionListener() {


                public void actionPerformed(ActionEvent e) {


                    m_component.doCancel();


                    System.out.println("keystrokemanager:docancel");


                }


            };


            actionf9 = new ActionListener() {


                public void actionPerformed(ActionEvent e) {


                    m_component.doDelete();


                    System.out.println("keystrokemanager:dodelete");


                }


            };

            actionf10 = new ActionListener() {


                public void actionPerformed(ActionEvent e) {


                    m_component.doF10();


                    System.out.println("keystrokemanager:dodelete");


                }


            };


            actionKeyup = new ActionListener() {


                public void actionPerformed(ActionEvent e) {


                    m_component.doUp();


                    System.out.println("keystrokemanager:dodelete");


                }


            };


            actionKeydown = new ActionListener() {


                public void actionPerformed(ActionEvent e) {


                    m_component.doDown();


                    System.out.println("keystrokemanager:dodelete");


                }


            };

            /* m_component.registerKeyboardAction(actionswitch,testSwitch,JComponent.WHEN_IN_FOCUSED_WINDOW);







        m_component.registerKeyboardAction(actionf5,testF5,JComponent.WHEN_IN_FOCUSED_WINDOW);







         m_component.registerKeyboardAction(actionf3,testF3,JComponent.WHEN_IN_FOCUSED_WINDOW);







         m_component.registerKeyboardAction(actionf2,testF2,JComponent.WHEN_IN_FOCUSED_WINDOW);







         m_component.registerKeyboardAction(actionf8,testF8,JComponent.WHEN_IN_FOCUSED_WINDOW);







         m_component.registerKeyboardAction(actionf9,testF9,JComponent.WHEN_IN_FOCUSED_WINDOW);   */


            actionTable = new Hashtable();


            actionTable.put(new Integer(ActionToolBar.DO_SWITCH), actionswitch);


            actionTable.put(new Integer(ActionToolBar.DO_MODIFY), actionf3);


            actionTable.put(new Integer(ActionToolBar.DO_CREATE), actionf2);


            actionTable.put(new Integer(ActionToolBar.DO_CANCEL), actionf8);


            actionTable.put(new Integer(ActionToolBar.DO_DELETE), actionf9);


            actionTable.put(new Integer(ActionToolBar.DO_PREVIOUS), actionf5);


            actionTable.put(new Integer(ActionToolBar.DO_ACCEPT), actionf4);


            actionTable.put(new Integer(ActionToolBar.DO_DOWN), actionKeydown);

            actionTable.put(new Integer(ActionToolBar.DO_F10), actionf10);


            actionTable.put(new Integer(ActionToolBar.DO_UP), actionKeyup);
        }


    }


    public void setAction(int[] tab) {


        if (sw) {


            if (tab == null) {


                System.out.println("tableau null");
            } else {


                m_component.resetKeyboardActions();


                for (int i = 0; i < tab.length; i++) {


                    System.out.println("numero :" + tab[i]);


                    m_component.registerKeyboardAction((ActionListener) actionTable.get(new Integer(tab[i])), (KeyStroke) keystrokeTable.get(new Integer(tab[i])), JComponent.WHEN_IN_FOCUSED_WINDOW);


                }


            }


        }

        //    registerKeyboardAction(actionswitch,testSwitch,JComponent.WHEN_IN_FOCUSED_WINDOW);

        //   registerKeyboardAction(actionf5,testF5,JComponent.WHEN_IN_FOCUSED_WINDOW);

        //  registerKeyboardAction(actionf3,testF3,JComponent.WHEN_IN_FOCUSED_WINDOW);

        // registerKeyboardAction(actionf2,testF2,JComponent.WHEN_IN_FOCUSED_WINDOW);

        // registerKeyboardAction(actionf8,testF8,JComponent.WHEN_IN_FOCUSED_WINDOW);

        // registerKeyboardAction(actionf9,testF9,JComponent.WHEN_IN_FOCUSED_WINDOW);


    }


}







