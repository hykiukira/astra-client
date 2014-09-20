/*





 * OwnfocusManager.java





 *





 * Created on 7 januari 2003, 17:08





 */


package srcastra.astra.gui.components.focusmanager;


import javax.swing.FocusManager;


import javax.swing.*;


import javax.swing.event.*;


import java.awt.*;


import java.awt.event.*;


import java.util.Hashtable;


import srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.*;


/**
 * @author Thomas
 */


public class OwnfocusManagerAwt extends java.awt.DefaultKeyboardFocusManager {


    /**
     * Creates a new instance of OwnfocusManager
     */


    public OwnfocusManagerAwt(JLayeredPane layer, Hashtable table, int mode) {


        super();


        m_layer = layer;


        m_table = table;


        panelSelected = (AbstractSousPanel) m_table.get(AbstractMainPanel.PRODUIT);


        selectPanel(true);


    }


    public OwnfocusManagerAwt() {


        super();

        // m_layer=layer;

        // m_table=table;

        // panelSelected=(AbstractSousPanel)m_table.get(AbstractMainPanel.PRODUIT);

        // selectPanel(true);


    }


    public void processKeyEvent(Component focusedComponent, KeyEvent event)


    {


        System.out.println("numéro de la touche :" + event.getKeyCode());

        /*    if(focusedComponent.getName() !=null){





              System.out.println("nom composant"+focusedComponent.getName());





             // if(focusedComponent.getName().equals("theframe"))





             if(focusedComponent.getName().equals(panelSelected.getName()))





              {





                  System.out.println("from the frame");





                  System.out.println("id : "+event.getKeyCode()+" f12 : "+event.VK_F2);





                  if (event.getID() != KeyEvent.KEY_PRESSED) {





                      if(event.getKeyCode()==event.VK_F12){





                               System.out.println("from f12");





                               selectNexPanel();





                        }





                      else if(event.getKeyCode()==event.VK_F2){





                         panelSelected.displayInsertMode();





                      }





                      else if(event.getKeyCode()==event.VK_F8){





                         panelSelected.displayReadMode();





                      }





                  }





                  else





                      System.out.println("key released");





              }





       } */


        super.processKeyEvent(focusedComponent, event);


    }

    /*     public void processKeyEvent( KeyEvent event){

















    }*/


    private void selectPanel(boolean selected) {


        panelSelected.setSelected(selected);


    }


    private void selectNexPanel() {


        selectPanel(false);


        panelSelected = getAbstractPanelFromTable(panelSelected.getNexPanel(0));


        selectPanel(true);


        m_layer.moveToFront(getPanelFromTable(panelSelected.getMyType()));


    }


    private AbstractSousPanel getAbstractPanelFromTable(String key) {


        return (AbstractSousPanel) m_table.get(key);


    }


    private JPanel getPanelFromTable(String key) {


        return ((AbstractSousPanel) m_table.get(key)).getUI();


    }


    JLayeredPane m_layer;


    Hashtable m_table = new Hashtable();


    AbstractSousPanel panelSelected;


}





