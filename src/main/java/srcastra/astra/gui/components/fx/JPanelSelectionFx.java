/*





 * JPanelBorderFx.java





 *





 * Created on 2 septembre 2002, 8:26





 */


package srcastra.astra.gui.components.fx;


import javax.swing.JPanel;


import java.awt.event.MouseAdapter;


import java.awt.event.MouseEvent;


import java.awt.Component;


import java.awt.event.FocusListener;


import srcastra.astra.gui.modules.MainScreenModule;


import srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer;


import javax.swing.event.EventListenerList;


/**
 * @author Sébastien
 */


public class JPanelSelectionFx extends MouseAdapter implements FocusListener {


    private JPanel panel;


    private MainScreenModule module;


    private EventListenerList m_listenerList;


    private javax.swing.border.Border oldBorder;


    private javax.swing.border.Border newBorder;


    private ToolBarComposer dependance;


    private boolean canChangeCursor = true;


    private boolean activated = true;


    private javax.swing.JTable generiqueTable;


    /**
     * Creates a new instance of JPanelBorderFx
     */


    public JPanelSelectionFx(MainScreenModule module, JPanel panel, javax.swing.border.Border newBorder, ToolBarComposer dependance) {


        this.module = module;


        this.panel = panel;


        this.oldBorder = panel.getBorder();


        this.newBorder = newBorder;


        this.dependance = dependance;


        m_listenerList = new EventListenerList();


        panel.addMouseListener(this);


    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// LISTENERS

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void mouseClicked(MouseEvent e) {

        /*if (activated)





     fireJPanelSelectionfxEvent(new JPanelSelectionFxEvent(this, JPanelSelectionFxEvent.ACTION_PERFORMED, "mouseClick"));*/


    }


    public void mouseEntered(MouseEvent e) {

        // -> change le curseur de la souris en main

        /*if (canChangeCursor && activated) {





         module.changeCursor(srcastra.astra.gui.sys.utils.CursorChange.CHANGE_CURSOR_IN_INTERNALFRAME, new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));





        }*/


    }


    public void mouseExited(MouseEvent e) {

        // -> remet le curseur de la souris en défaut

        /*if (activated)





      module.changeCursor(srcastra.astra.gui.sys.utils.CursorChange.CHANGE_CURSOR_IN_INTERNALFRAME, new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));*/


    }


    public void mousePressed(MouseEvent e) {


    }


    public void mouseReleased(MouseEvent e) {


    }


    public void focusGained(java.awt.event.FocusEvent focusEvent) {


        if (activated)


            fireJPanelSelectionfxEvent(new JPanelSelectionFxEvent(this, JPanelSelectionFxEvent.ACTION_PERFORMED, "focusGain"));


    }


    public void focusLost(java.awt.event.FocusEvent focusEvent) {


    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// METHODES

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void selectPanel() {


        fireJPanelSelectionfxEvent(new JPanelSelectionFxEvent(this, JPanelSelectionFxEvent.ACTION_PERFORMED, "mouseClick"));


    }


    public void selectPanel(boolean select) {


        if (!select) {


            panel.setBorder(oldBorder);

            // -> demande une action spéciale


            if (JPanelSelectionSpecialFxActions.class.isAssignableFrom(panel.getClass())) {


                ((JPanelSelectionSpecialFxActions) panel).fxPanelDiselected();


            }


        } else {


            panel.setBorder(newBorder);

            // -> demande une action spéciale


            if (JPanelSelectionSpecialFxActions.class.isAssignableFrom(panel.getClass())) {


                if (comFromF5 == false)


                    ((JPanelSelectionSpecialFxActions) panel).fxPanelSelected();


                else


                    ((JPanelSelectionSpecialFxActions) panel).fxPanelSelected(1);


            }


        }


        panel.updateUI();


    }


    protected void fireJPanelSelectionfxEvent(JPanelSelectionFxEvent event) {


        Object[] listeners = m_listenerList.getListenerList();


        for (int i = 0; i < listeners.length; i++) {


            if (JPanelSelectionFxListener.class.isAssignableFrom(listeners[i].getClass())) {


                if (event == null) {


                    event = new JPanelSelectionFxEvent(this, JPanelSelectionFxEvent.ACTION_PERFORMED, "null");


                }


                ((JPanelSelectionFxListener) listeners[i]).panelSelected(event);


            }


        }


    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// GET / SET METHOD

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void addJPanelSelectionFxListener(JPanelSelectionFxListener listener) {


        m_listenerList.add(JPanelSelectionFxListener.class, listener);


    }


    public void removeJpanelSelectionFxListener(JPanelSelectionFxListener listener) {


        m_listenerList.remove(JPanelSelectionFxListener.class, listener);


    }


    /**
     * Getter for property panel.
     *
     * @return Value of property panel.
     */


    public javax.swing.JPanel getPanel() {


        return panel;


    }


    /**
     * Getter for property module.
     *
     * @return Value of property module.
     */


    public srcastra.astra.gui.modules.MainScreenModule getModule() {


        return module;


    }


    /**
     * Getter for property dependance.
     *
     * @return Value of property dependance.
     */


    public srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer getDependance() {


        return dependance;


    }


    /**
     * Setter for property dependance.
     *
     * @param dependance New value of property dependance.
     */


    public void setDependance(srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer dependance) {


        this.dependance = dependance;


    }


    /**
     * Getter for property canChangeCursor.
     *
     * @return Value of property canChangeCursor.
     */


    public boolean isCanChangeCursor() {


        return canChangeCursor;


    }


    /**
     * Setter for property canChangeCursor.
     *
     * @param canChangeCursor New value of property canChangeCursor.
     */


    public void setCanChangeCursor(boolean canChangeCursor) {


        this.canChangeCursor = canChangeCursor;


    }


    /**
     * Getter for property activated.
     *
     * @return Value of property activated.
     */


    public boolean isActivated() {


        return activated;


    }


    /**
     * Setter for property activated.
     *
     * @param activated New value of property activated.
     */


    public void setActivated(boolean activated) {


        this.activated = activated;


    }


    /**
     * Getter for property generiqueTable.
     *
     * @return Value of property generiqueTable.
     */


    public javax.swing.JTable getGeneriqueTable() {


        return generiqueTable;


    }


    /**
     * Setter for property generiqueTable.
     *
     * @param generiqueTable New value of property generiqueTable.
     */


    public void setGeneriqueTable(javax.swing.JTable generiqueTable) {


        this.generiqueTable = generiqueTable;


    }


    public boolean comFromF5;


}





