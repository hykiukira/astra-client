/*





 * JPanelSelectionFxManager.java





 *





 * Created on 2 septembre 2002, 12:53





 */


package srcastra.astra.gui.components.fx;


import java.util.Vector;


import srcastra.astra.gui.modules.MainScreenModule;


import srcastra.astra.gui.modules.InternScreenModule;


import srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer;


import java.util.Hashtable;


/**
 * @author Sébastien
 */


public class JPanelSelectionFxManager implements JPanelSelectionFxListener {


    private Vector fx_listeners;


    private Hashtable fx_listenersHash;


    private JPanelSelectionFx selectedPanel;


    private boolean activated = true;


    private javax.swing.border.Border selectedBorder;


    private javax.swing.border.Border unselectedBorder;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// CONSTRUCTOR

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Creates a new instance of JPanelSelectionFxManager
     */


    public JPanelSelectionFxManager() {


        fx_listeners = new Vector(0);


        fx_listenersHash = new Hashtable();


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// METHODES

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void addJPanelSelectionFx(JPanelSelectionFx fx) {


        fx.addJPanelSelectionFxListener(this);


        fx_listeners.addElement(fx);

        // s'il est le premier élément de la liste on le sélectionne par défaut


        if (fx_listeners.indexOf(fx) == 0) {


            selectedPanel = fx;


            fx.selectPanel(true);


        }


    }


    public void addJPanelSelectionFx(JPanelSelectionFx fx, String key) {


        fx.addJPanelSelectionFxListener(this);


        fx_listeners.addElement(fx);


        fx_listenersHash.put(key, fx);

        // s'il est le premier élément de la liste on le sélectionne par défaut


        if (fx_listeners.indexOf(fx) == 0) {


            selectedPanel = fx;


            fx.selectPanel(true);


        }


    }


    public void removeJPanel(JPanelSelectionFx fx) {

        // s'il s'agit du panel actuellement sélectionné


        if (selectedPanel.equals(fx)) {

            // déselection


            fx.selectPanel(false);

            // position dans le vecteur


            int position = fx_listeners.indexOf(fx);

            // recherche du prochain panneau à sélectionner


            if (position < fx_listeners.size() - 1) {


                selectedPanel = (JPanelSelectionFx) fx_listeners.get(position + 1);


                selectedPanel.selectPanel(true);


            }


            if (fx_listeners.size() > 0) {


                selectedPanel = (JPanelSelectionFx) fx_listeners.firstElement();


                selectedPanel.selectPanel(true);


            }


        }


        fx_listeners.remove(fx);


        fx_listeners.trimToSize();


    }


    public void swithPanel() {


        int tot = fx_listeners.size();


        int pos = fx_listeners.indexOf(selectedPanel);


        if (activated) {


            if (pos >= 0) {


                if (selectedPanel != null) {


                    selectedPanel.selectPanel(false);


                }


                JPanelSelectionFx fx;


                if (pos < tot - 1) {


                    fx = (JPanelSelectionFx) fx_listeners.get(pos + 1);


                } else {


                    fx = (JPanelSelectionFx) fx_listeners.firstElement();


                }


                selectedPanel = fx;


                fx.comFromF5 = false;


                fx.selectPanel(true);


                giveTbComposerAction(fx);


            }


        }


    }


    public void swithPanel(String key) {


        if (this.fx_listenersHash != null && this.fx_listenersHash.size() != 0)


        {


            if (selectedPanel != null) {


                selectedPanel.selectPanel(false);


            }


            JPanelSelectionFx fx;


            fx = (JPanelSelectionFx) fx_listenersHash.get(key);


            selectedPanel = fx;


            fx.comFromF5 = true;

            // fx.selectPanel(true);


            giveTbComposerAction(fx);


        }


    }


    public boolean isInTheList(Object obj) {


        return fx_listeners.contains(obj);


    }


    public void needReselect() {


        if (selectedPanel != null) {

            // selectedPanel.selectPanel();


            giveTbComposerAction(selectedPanel);


        }


    }


    public void enableMouseCursorChange(boolean enabled) {


        for (int i = 0; i < fx_listeners.size(); i++) {


            if (fx_listeners.get(i) != null) {


                ((JPanelSelectionFx) fx_listeners.get(i)).setCanChangeCursor(enabled);


            }


        }


    }


    public void reloadReadMode() {


        selectedPanel.getModule().setCurrentActionEnabled(((ToolBarComposer) selectedPanel.getPanel()).getDefaultActionToolBarMask());


    }


    private void giveTbComposerAction(JPanelSelectionFx fx) {


        System.out.println("\n\n[giveTbComposerAction] début");


        if (isInTheList(fx)) {


            System.out.println("[giveTbComposerAction] fx est dans la liste");


            selectedPanel = fx;


            fx.selectPanel(true);


            if (InternScreenModule.class.isInstance(fx.getPanel()) && ToolBarComposer.class.isInstance(fx.getPanel())) {


                System.out.println("[giveTbComposerAction] instance de InternScreenModule");

                //  fx.getPanel().requestFocus();


                fx.getModule().setCurrentPanel((ToolBarComposer) fx.getPanel());


                fx.getModule().setCurrentActionEnabled(((ToolBarComposer) fx.getPanel()).getDefaultActionToolBarMask());


            } else if (fx.getDependance() != null) {


                System.out.println("[giveTbComposerAction] dépendance");


                ToolBarComposer tb_composer = fx.getDependance();


                fx.getModule().setCurrentPanel(tb_composer);


                fx.getModule().setCurrentActionEnabled(tb_composer.getDefaultActionToolBarMask());


            }


            fx.getModule().registerTable(fx.getGeneriqueTable());


        }


        System.out.println("[giveTbComposerAction] fin\n\n");


    }


    public void resetToDefault() {


        for (int i = 0; i < fx_listeners.size(); i++) {


            JPanelSelectionFx jpfx = (JPanelSelectionFx) fx_listeners.get(i);


            jpfx.selectPanel(false);


        }


        fx_listeners.removeAllElements();


        selectedPanel = null;


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// LISTENERS

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void panelSelected(JPanelSelectionFxEvent evt) {

        /* obtenir la scource et son numéro de panneau





 * faire correspondre avec le vecteur





 * si occurence de celle-ci sélection du panneau





 * déselection du panneau numéroté par currentSelection */


        JPanelSelectionFx fx = evt.getJPanelSelectionFx();


        if (activated) {


            if (selectedPanel != null) {


                selectedPanel.selectPanel(false);


            }


            giveTbComposerAction(fx);


        }


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// GET / SET METHOD

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Getter for property activated.
     *
     * @return Value of property activated.
     */


    public boolean isActivated() {


        if (selectedPanel != null)


            return selectedPanel.isActivated();


        else return false;


    }


    /**
     * Setter for property activated.
     *
     * @param activated New value of property activated.
     */


    public void setActivated(boolean activated) {


        if (selectedPanel != null) {


            if (selectedPanel.isActivated() != activated) {


                selectedPanel.setActivated(activated);


                if (activated) enableMouseCursorChange(true);


                else enableMouseCursorChange(false);


            }


        }


    }


    /**
     * Getter for property selectedPanel.
     *
     * @return Value of property selectedPanel.
     */


    public srcastra.astra.gui.components.fx.JPanelSelectionFx getSelectedPanel() {


        return selectedPanel;


    }


    public int getNumberOfFxPanel() {


        return fx_listeners.size();


    }


}





