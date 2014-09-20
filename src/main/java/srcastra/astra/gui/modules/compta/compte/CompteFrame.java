/*

 * EntiteUser.java

 *

 * Created on 5 juillet 2003, 10:17

 */


package srcastra.astra.gui.modules.compta.compte;

import srcastra.astra.gui.modules.config.entite.*;

import srcastra.astra.gui.modules.config.user.*;

import srcastra.astra.gui.modules.*;

import srcastra.astra.sys.classetransfert.*;

import srcastra.astra.sys.rmi.*;

import srcastra.test.*;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import srcastra.astra.gui.sys.*;

import srcastra.astra.gui.components.*;

import srcastra.astra.gui.modules.Mailing.*;

import javax.swing.*;

import srcastra.astra.gui.modules.config.*;

import javax.swing.event.*;

/**
 * @author Thomas
 */

public class CompteFrame extends javax.swing.JInternalFrame implements MainScreenModule, AIframe, MailInterface, ModuleInterface {


    /**
     * Creates new form EntiteUser
     */

    ServeurConnect connect;

    Loginusers_T currentUser;

    astrainterface serveur;

    MainScreenModule parent;

    AstraComponent[] astrac;

    EntitePane entite;

    UserCOnfig user;

    ActionToolBar toolbar;

    GlobalInterface[] panel;

    int selectedPanel;

    javax.swing.event.InternalFrameListener iFrameListener;


    public CompteFrame(java.awt.Frame superOwner, astrainterface serveur, Loginusers_T currentUser, ActionToolBar toolbar, javax.swing.event.InternalFrameListener iFrameListener) {

        //   connect=new ServeurConnect();

        // connect.connectServeur();

        parent = this;

        this.toolbar = toolbar;

        this.currentUser = currentUser;

        this.serveur = serveur;

        this.iFrameListener = iFrameListener;

        initComponents();

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mousePressed(java.awt.event.MouseEvent evt) {

                jTabbedPane1MousePressed(evt);

            }
        });

        generatTabedPane();

        this.addInternalFrameListener(iFrameListener);


    }

    private void generatTabedPane() {

        panel = new GlobalInterface[2];

        //   try{

        panel[0] = new ComptePanel(this, toolbar);

        panel[1] = new CompteFichePanel(this, toolbar);

        toolbar.setTbComposer((ToolBarComposer) panel[0]);

        jTabbedPane1.add(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", currentUser.getLangage()).getString("compte"), (JPanel) panel[0]);

        jTabbedPane1.add(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", currentUser.getLangage()).getString("fiche"), (JPanel) panel[1]);

        /* }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){

          ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se); 

        }

        catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }*/

    }

    private void jTabbedPane1MousePressed(java.awt.event.MouseEvent evt) {

        System.out.println("evt " + evt.getSource());

        for (int i = 0; i < panel.length; i++) {

            if (jTabbedPane1.getSelectedIndex() == i) {

                toolbar.setTbComposer((ToolBarComposer) panel[i]);

                setSelectedPanel(i);

            }

        }

    }

    /**
     * This method is called from within the constructor to
     * <p/>
     * initialize the form.
     * <p/>
     * WARNING: Do NOT modify this code. The content of this method is
     * <p/>
     * always regenerated by the Form Editor.
     */

    private void initComponents() {//GEN-BEGIN:initComponents
        jTabbedPane1 = new javax.swing.JTabbedPane();

        setClosable(true);
        setIconifiable(true);
        setTitle(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("if_user"));
        setName(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", parent.getCurrentUser().getLangage()).getString("if_user"));
        setPreferredSize(new java.awt.Dimension(730, 520));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(730, 520));
        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents


    /**
     * Fermeture de l'�cran courrant et passage
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * � l'�cran d'index du module
     */

    public void cancelModule() {

    }


    public void changeCursor(int changeLocation, java.awt.Cursor cursor) {

    }


    /**
     * Permet de charger le panel des statuts
     */

    public void chargeStatusPanel(String[] statuts) {

    }


    /**
     * Ferme le module
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * (dans Astra -> JInternalFrame)
     */

    public void closeModule() {

    }


    /**
     * Lance la s�quence de cr�ation
     */

    public void displayCreateSequence() {

    }


    /**
     * Lance la s�quence de suppression
     */

    public void displayDeleteSequence() {

    }


    /**
     * Lance la s�quence de lecture
     */

    public void displayReadSequence(int cleUnik) {

    }


    /**
     * Permet de changer l'�tat de la TabbedPane dans le pricipal
     */

    public void enabledTabbedPane(boolean enabled) {

    }


    public Loginusers_T getCurrentUser() {

        return this.currentUser;

    }


    public boolean getNestedSignaletique() {

        return false;

    }


    public java.awt.Frame getOwner() {

        return null;

    }


    public astrainterface getServeur() {

        return this.serveur;

    }


    public DossierRmiInterface getServeurDossier() {

        return null;

    }


    public java.awt.Frame getSuperOwner() {

        return null;

    }


    /**
     * Passage � l'�cran suivant
     *
     * @param currentScreen num�ro de l'�cran courrant
     */

    public void nextScreen(int currentScreen) {

    }


    public void nextScreen(int currentScreen, int insert) {

    }


    /**
     * Passage � l'�cran suivant
     *
     * @param currentScreen num�ro de l'�cran courrant
     */

    public void nextScreen(int currentScreen, boolean affich) {

    }


    public void registerTable(javax.swing.JTable generique_table) {

    }


    public void reloadToolBarInfo() {

    }


    /**
     * Fixe la cl� unique dans le module parent
     *
     * @param ContextCleUnik cl� unique
     */

    public void setContextCleUnik(int ContextCleUnik) {

    }


    /**
     * permet d'�tablir une liste d'action choisie comme �tant les actions de la ToobBar
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * + permet � la classe principale du modules de pouvoir sauvegarder le tableau des actions
     */

    public void setCurrentActionEnabled(int[] actionEnabled) {

    }


    /**
     * permet d'�tablir un panneau comme panneau gestionnaire de la toolbar (voir tbComposer) +
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * permet � la classe principale du modules de pouvoir sauvegarder l'objet TbComposer
     */

    public void setCurrentPanel(srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer currentPanel) {

    }


    public void setNestedSignaletique(boolean netstedSignletique) {

    }


    public void saveToolBarInfo() {

    }


    /**
     * Creates a new instance of MailInterface
     */

    public String[] getEmailAdres() {

        return null;

    }

    public String getFormEntiteMail() {

        return "";
    }


    public Loginusers_T getUser() {

        return currentUser;

    }


    /**
     * Getter for property panel.
     *
     * @return Value of property panel.
     */

    public srcastra.astra.gui.modules.config.GlobalInterface[] getPanel() {

        return this.panel;

    }


    /**
     * Setter for property panel.
     *
     * @param panel New value of property panel.
     */

    public void setPanel(srcastra.astra.gui.modules.config.GlobalInterface[] panel) {

        this.panel = panel;

    }


    /**
     * Getter for property selectedPanel.
     *
     * @return Value of property selectedPanel.
     */

    public int getSelectedPanel() {

        return selectedPanel;

    }


    /**
     * Setter for property selectedPanel.
     *
     * @param selectedPanel New value of property selectedPanel.
     */

    public void setSelectedPanel(int selectedPanel) {

        this.selectedPanel = selectedPanel;

    }

    public void next() {

        if (selectedPanel >= 0 && selectedPanel < (panel.length - 1)) {

            selectedPanel = selectedPanel + 1;

            jTabbedPane1.setSelectedIndex(selectedPanel);


        }

    }

    public void previous() {

        if (selectedPanel > 0 && selectedPanel < (panel.length)) {

            selectedPanel = selectedPanel - 1;

            jTabbedPane1.setSelectedIndex(selectedPanel);

        }

    }

    public ToolBarComposer getToolPanel() {

        return (ToolBarComposer) panel[selectedPanel];

    }

    public int isBoderPaner() {

        if (selectedPanel == 0)

            return 0;

        else if (selectedPanel == panel.length - 1)

            return 2;

        else

            return 1;

    }

    public void enableJtabPane(boolean enable) {

        jTabbedPane1.setEnabled(enable);

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables


}

