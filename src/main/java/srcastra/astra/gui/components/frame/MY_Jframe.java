/*

 * MY_Jframe.java

 *

 * Created on 9 octobre 2003, 13:12

 */


package srcastra.astra.gui.components.frame;

import srcastra.astra.sys.classetransfert.Loginusers_T;

import srcastra.astra.sys.rmi.astrainterface;

import srcastra.astra.gui.modules.*;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import srcastra.astra.gui.components.AIframe;

import srcastra.astra.gui.sys.ErreurScreenLibrary;

import srcastra.astra.sys.rmi.*;

import javax.swing.event.InternalFrameEvent;

import srcastra.astra.gui.components.combobox.liste2.Mediator;

import srcastra.astra.gui.*;

/**
 * @author thomas
 */

public class MY_Jframe extends javax.swing.JInternalFrame implements MainScreenModule, AIframe {


    /**
     * Creates a new instance of MY_Jframe
     */

    int[] actionTab;

    Loginusers_T currentUser;

    astrainterface serveur;

    ActionToolBar actionToolBar;

    java.awt.Frame frame;

    MainFrame mainframe;

    public int mode;

    java.awt.Dimension dim;

    javax.swing.JTabbedPane tabpane;

    String title;

    ToolBarComposer[] paneltab;

    int numberpanel;

    int selectedpanel;

    javax.swing.event.InternalFrameListener iFrameListener;

    public MY_Jframe(java.awt.Frame superOwner, astrainterface serveur, Loginusers_T currentUser, ActionToolBar actionToolBar, javax.swing.event.InternalFrameListener iFrameListener, MainFrame main, ToolBarComposer[] paneltab, String title, java.awt.Dimension dim) {

        this.serveur = serveur;

        this.currentUser = currentUser;

        this.actionToolBar = actionToolBar;

        this.frame = frame;

        this.mainframe = mainframe;

        setName(title);

        this.dim = dim;

        this.title = title;

        this.iFrameListener = iFrameListener;

        this.addInternalFrameListener(this.iFrameListener);

        this.paneltab = paneltab;

        if (paneltab != null)

            numberpanel = paneltab.length;

        // getContentPane().add((javax.swing.JPanel)paneltab[0]);

        selectedpanel = 0;

        initComponents();

        System.out.println("ya");

    }

    private void initComponents() {

        tabpane = new javax.swing.JTabbedPane();

        setClosable(true);

        setTitle(title);

        setName(title);

        setPreferredSize(dim);

        tabpane.setPreferredSize(dim);

        getContentPane().add(tabpane, java.awt.BorderLayout.CENTER);

        pack();

    }

    public void nextPanel() {

        if (paneltab != null) {

            if (selectedpanel < numberpanel) {

                selectedpanel++;

                tabpane.setSelectedIndex(selectedpanel);

            }

        }


    }

    public void previousPanel() {

        if (paneltab != null) {

            if (selectedpanel > 0) {

                selectedpanel--;

                tabpane.setSelectedIndex(selectedpanel);

            }

        }

    }

    public void cancelModule() {

    }


    public void changeCursor(int changeLocation, java.awt.Cursor cursor) {

    }


    public void chargeStatusPanel(String[] statuts) {

    }


    public void closeModule() {

        doDefaultCloseAction();

    }


    public void displayCreateSequence() {

    }


    public void displayDeleteSequence() {

    }


    public void displayReadSequence(int cleUnik) {

    }


    public void enabledTabbedPane(boolean enabled) {

    }


    public Loginusers_T getCurrentUser() {

        return currentUser;

    }


    public boolean getNestedSignaletique() {

        return false;

    }


    public java.awt.Frame getOwner() {

        return frame;

    }


    public astrainterface getServeur() {

        return serveur;

    }


    public DossierRmiInterface getServeurDossier() {

        return null;

    }


    public java.awt.Frame getSuperOwner() {

        return frame;

    }


    public void nextScreen(int currentScreen) {

    }


    public void nextScreen(int currentScreen, boolean affich) {

    }


    public void nextScreen(int currentScreen, int insert) {

    }


    public void registerTable(javax.swing.JTable generique_table) {

    }


    public void reloadToolBarInfo() {

        if (actionTab != null)

            actionToolBar.setActionEnabled(actionTab);

    }


    public void saveToolBarInfo() {

    }


    public void setContextCleUnik(int ContextCleUnik) {

    }


    public void setCurrentActionEnabled(int[] actionEnabled) {

    }


    public void setCurrentPanel(srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer currentPanel) {

    }


    public void setNestedSignaletique(boolean netstedSignletique) {

    }

    public void doClose() {

        doDefaultCloseAction();

    }

    public void doDefaultCloseAction() {


        try {

            serveur.remoterollback(getCurrentUser().getUrcleunik());

        } catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }

        InternalFrameEvent closeWindow = new InternalFrameEvent(this, InternalFrameEvent.INTERNAL_FRAME_CLOSING);

        this.iFrameListener.internalFrameClosing(closeWindow);

        super.doDefaultCloseAction();

    }


}

