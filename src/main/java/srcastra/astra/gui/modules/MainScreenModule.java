/*




 * MainScreenModule.java




 *




 * Created on 27 mars 2002, 18:01




 */


package srcastra.astra.gui.modules;


import srcastra.astra.sys.rmi.astrainterface;


import srcastra.astra.sys.classetransfert.Loginusers_T;


import srcastra.astra.sys.rmi.*;


/**
 * @author Sébastien
 */


public interface MainScreenModule {


    /**
     * Fixe la clé unique dans le module parent
     *
     * @param ContextCleUnik clé unique
     */


    public void setContextCleUnik(int ContextCleUnik);


    /**
     * Passage à l'écran suivant
     *
     * @param currentScreen numéro de l'écran courrant
     */


    public void nextScreen(int currentScreen);


    /**
     * Passage à l'écran suivant
     *
     * @param currentScreen numéro de l'écran courrant
     */


    public void nextScreen(int currentScreen, boolean affich);


    /**
     * Lance la séquence de création
     */


    public void displayCreateSequence();


    /**
     * Lance la séquence de lecture
     */


    public void displayReadSequence(int cleUnik);


    /**
     * Lance la séquence de suppression
     */


    public void displayDeleteSequence();


    /**
     * Ferme le module
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * (dans Astra -> JInternalFrame)
     */


    public void closeModule();


    /**
     * Fermeture de l'écran courrant et passage
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * à l'écran d'index du module
     */


    public void cancelModule();


    /**
     * Permet de changer l'état de la TabbedPane dans le pricipal
     */


    public void enabledTabbedPane(boolean enabled);


    /**
     * Permet de charger le panel des statuts
     */


    public void chargeStatusPanel(String[] statuts);


    /**
     * permet d'établir un panneau comme panneau gestionnaire de la toolbar (voir tbComposer) +
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * permet à la classe principale du modules de pouvoir sauvegarder l'objet TbComposer
     */


    public void setCurrentPanel(srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer currentPanel);


    /**
     * permet d'établir une liste d'action choisie comme étant les actions de la ToobBar
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * + permet à la classe principale du modules de pouvoir sauvegarder le tableau des actions
     */


    public void setCurrentActionEnabled(int[] actionEnabled);


    public astrainterface getServeur();


    public Loginusers_T getCurrentUser();


    public void reloadToolBarInfo();


    public void changeCursor(int changeLocation, java.awt.Cursor cursor);


    public java.awt.Frame getSuperOwner();


    public boolean getNestedSignaletique();


    public void setNestedSignaletique(boolean netstedSignletique);


    public DossierRmiInterface getServeurDossier();


    public java.awt.Frame getOwner();


    public void registerTable(javax.swing.JTable generique_table);


    public void nextScreen(int currentScreen, int insert);


}




