/* * DecisionModule.java * * Created on 3 octobre 2002, 14:48 */package srcastra.astra.gui.modules.decision;/** * * @author Thomas */import srcastra.astra.sys.classetransfert.Loginusers_T;import srcastra.astra.sys.rmi.astrainterface;import srcastra.astra.gui.modules.*;import srcastra.astra.gui.components.actions.actionToolBar.*;import srcastra.astra.gui.components.AIframe;import srcastra.astra.gui.sys.ErreurScreenLibrary;import srcastra.astra.sys.classetransfert.dossier.produit_T;import srcastra.astra.sys.rmi.*;/** * @author S�bastien */public class DecisionModule extends javax.swing.JInternalFrame implements MainScreenModule, AIframe {    /**     * Fermeture de l'�cran courrant et passage     * <p/>     * <p/>     * <p/>     * � l'�cran d'index du module     */    public DecisionModule(java.awt.Frame superOwner, astrainterface serveur, Loginusers_T currentUser, ActionToolBar actionToolBar, javax.swing.event.InternalFrameListener iFrameListener) {        this.serveur = serveur;        this.currentUser = currentUser;        this.actionToolBar = actionToolBar;        this.iFrameListener = iFrameListener;        this.superOwner = superOwner;        this.addInternalFrameListener(iFrameListener);        try {            setName(java.util.ResourceBundle.getBundle("srcastra/astra/locale/IFrame", currentUser.getLangage()).getString("IF_Decision"));            setTitle(java.util.ResourceBundle.getBundle("srcastra/astra/locale/IFrame", currentUser.getLangage()).getString("IF_Decision"));        }        catch (java.util.MissingResourceException mre) {            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, mre);        }        initComponents();    }    public DossierRmiInterface getServeurDossier() {        return null;    }    public DecisionModule(java.awt.Frame superOwner, astrainterface serveur, Loginusers_T currentUser, ActionToolBar actionToolBar, javax.swing.event.InternalFrameListener iFrameListener, srcastra.astra.sys.classetransfert.dossier.InterfaceProduit produit, srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.InterfacePanel panel) {        this.serveur = serveur;        this.currentUser = currentUser;        this.actionToolBar = actionToolBar;        this.iFrameListener = iFrameListener;        this.superOwner = superOwner;        this.addInternalFrameListener(iFrameListener);        try {            setName(java.util.ResourceBundle.getBundle("srcastra/astra/locale/IFrame", currentUser.getLangage()).getString("IF_Decision"));            setTitle(java.util.ResourceBundle.getBundle("srcastra/astra/locale/IFrame", currentUser.getLangage()).getString("IF_Decision"));        }        catch (java.util.MissingResourceException mre) {            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, mre);        }        initComponents2(produit, panel);    }    private void initComponents()    {        this.decisionpane = new DecisionPane(serveur, currentUser, actionToolBar, null, 1, this);        this.setPreferredSize(new java.awt.Dimension(730, 450));        this.setMaximizable(false);        this.setIconifiable(true);        this.setClosable(true);        this.setResizable(false);        this.getContentPane().setLayout(new java.awt.GridLayout(1, 0));        this.getContentPane().add(decisionpane);        pack();    }    private void initComponents2(srcastra.astra.sys.classetransfert.dossier.InterfaceProduit produit, srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.InterfacePanel panel)    {        this.decisionpane = new DecisionPane(serveur, currentUser, actionToolBar, null, 1, this, produit, panel);        this.setPreferredSize(new java.awt.Dimension(730, 450));        this.setMaximizable(false);        this.setIconifiable(true);        this.setClosable(true);        this.setResizable(false);        this.getContentPane().setLayout(new java.awt.GridLayout(1, 0));        this.getContentPane().add(decisionpane);        pack();    }    public Loginusers_T getCurrentUser() {        return this.currentUser;    }    public astrainterface getServeur() {        return this.serveur;    }    /**     * Passage � l'�cran suivant     *     * @param currentScreen num�ro de l'�cran courrant     */    public void reloadToolBarInfo() {        if (currentPanel != null) {            actionToolBar.setTbComposer(this.currentPanel);            actionToolBar.reloadActionEnabled(this.currentActionEnabled);        }    }    public srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer getCurrentPanel() {        return currentPanel;    }    public void saveToolBarInfo() {    }    /**     * Fixe la cl� unique dans le module parent     *     * @param ContextCleUnik cl� unique     */    public void setContextCleUnik(int ContextCleUnik) {    }    /**     * permet d'�tablir une liste d'action choisie comme �tant les actions de la ToobBar     * <p/>     * <p/>     * <p/>     * + permet � la classe principale du modules de pouvoir sauvegarder le tableau des actions     */    public void setCurrentActionEnabled(int[] actionEnabled) {        actionToolBar.setActionEnabled(actionEnabled);        this.currentActionEnabled = this.actionToolBar.getActionEnabled();    }    /**     * permet d'�tablir un panneau comme panneau gestionnaire de la toolbar (voir tbComposer) +     * <p/>     * <p/>     * <p/>     * permet � la classe principale du modules de pouvoir sauvegarder l'objet TbComposer     */    public void setCurrentPanel(srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer currentPanel) {        this.currentPanel = currentPanel;        actionToolBar.setTbComposer(currentPanel);    }    public java.awt.Frame getSuperOwner() {        return this.superOwner;    }    public void displayGeneralInfosPane() {        nextScreen(-1, false);    }    public void nextScreen(int currentScreen) {    }    public void nextScreen(int currentScreen, boolean affich) {    }    /**     * De l'interface : MainScreenModule     * <p/>     * <p/>     * <p/>     * fixe la cl� unik selon le contexte     */    public void displayDeleteSequence() {    }    public void displayReadSequence(int cleUnik) {    }    public void displayCreateSequence() {    }    public void closeModule() {        this.doDefaultCloseAction();    }    public void cancelModule() {    }    /**     * Permet de changer l'�tat de la TabbedPane dans le pricipal     */    public void enabledTabbedPane(boolean enabled) {    }    /**     * Permet de charger le panel des statuts     */    public void chargeStatusPanel(String[] statuts) {    }    /**     * Permet de renvoyer un objet frame pour un eventuel modal     */    public java.awt.Frame getFrame() {        return superOwner;    }    public void loadScreen() {    }    /** Getter for property currentPanel.     * @return Value of property currentPanel.     */    /** Setter for property currentPanel.     * @param currentPanel New value of property currentPanel.     */    /**     * Getter for property frCleUnik.     *     * @return Value of property frCleUnik.     */    public int getFrCleUnik() {        return 0;    }    /**     * Setter for property frCleUnik.     *     * @param frCleUnik New value of property frCleUnik.     */    public void setFrCleUnik(int frCleUnik) {    }    public void changeCursor(int changeLocation, java.awt.Cursor cursor) {        srcastra.astra.gui.sys.utils.CursorChange.changeCursor(changeLocation, cursor, superOwner, this);    }    public boolean getNestedSignaletique() {        return false;    }    public void setNestedSignaletique(boolean netstedSignletique) {    }    public java.awt.Frame getOwner() {        return null;    }    public void registerTable(javax.swing.JTable generique_table) {    }    public void nextScreen(int currentScreen, int insert) {    }    private astrainterface serveur;    private Loginusers_T currentUser;    private ActionToolBar actionToolBar;    private java.awt.Frame superOwner;    private javax.swing.event.InternalFrameListener iFrameListener;    private DecisionPane decisionpane;    private srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer currentPanel;    private int[] currentActionEnabled;    private InternScreenModule[] screenDisplaying;}