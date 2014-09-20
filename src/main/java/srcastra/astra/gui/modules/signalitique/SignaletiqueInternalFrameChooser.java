/*
 * SignaletiqueInternalFrameChooser.java
 * Created on 25 avril 2002, 17:54
 */
package srcastra.astra.gui.modules.signalitique;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.gui.modules.MainScreenModule;
import srcastra.astra.gui.components.actions.actionToolBar.ActionToolBar;
import srcastra.astra.gui.components.AIframe;
import srcastra.astra.sys.rmi.*;
/**
 * @author  S�bastien
 */
public class SignaletiqueInternalFrameChooser extends javax.swing.JInternalFrame implements MainScreenModule, AIframe {
    private int signaletiqueType;
    private astrainterface serveur;
    private Loginusers_T currentUser;
    private ActionToolBar actionToolBar;
    private javax.swing.event.InternalFrameListener iFrameListener;
    private srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer currentPanel;
    private int[] currentActionEnabled;
    private java.awt.Frame superOwner;
    
    /** Creates new form SignaletiqueInternalFrameChooser */
    public SignaletiqueInternalFrameChooser(astrainterface serveur, Loginusers_T currentUser, ActionToolBar actionToolBar, int signaletiqueType, javax.swing.event.InternalFrameListener iFrameListener, java.awt.Frame superOwner) {
        this.serveur = serveur;
        this.currentUser = currentUser;
        this.actionToolBar = actionToolBar;
        this.signaletiqueType = signaletiqueType;
        this.iFrameListener = iFrameListener;
        this.superOwner = superOwner;
        
        initComponents();
        this.addInternalFrameListener(iFrameListener);
        displayReadSequence(signaletiqueType);
        this.setName(loadName());
        this.setTitle(loadName());
    }
     public SignaletiqueInternalFrameChooser(astrainterface serveur, Loginusers_T currentUser, ActionToolBar actionToolBar, int signaletiqueType, javax.swing.event.InternalFrameListener iFrameListener, java.awt.Frame superOwner,boolean nesteSignaletique) {
        this.serveur = serveur;
        this.currentUser = currentUser;
        this.actionToolBar = actionToolBar;
        this.signaletiqueType = signaletiqueType;
        this.iFrameListener = iFrameListener;
        this.superOwner = superOwner;
        this.netstedSignletique=nesteSignaletique;
        initComponents();
        this.addInternalFrameListener(iFrameListener);
        displayReadSequence(signaletiqueType);
        this.setName(loadName());
        this.setTitle(loadName());
       
    }
      public DossierRmiInterface getServeurDossier() {
        return null;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents

        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        setClosable(true);
        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(700, 450));
        pack();
    }//GEN-END:initComponents
    /** Fermeture de l'�cran courrant et passage
     * � l'�cran d'index du module
     *
     */
    public void cancelModule() {
    }    
    /** Passage � l'�cran suivant
     * @param currentScreen num�ro de l'�cran courrant
    */
    public void nextScreen(int currentScreen, boolean affich) {
    }    
    /** Lance la s�quence de lecture
     */
    public void displayReadSequence(int cleUnik) {
                this.getContentPane().add(new SignaletiqueOtherLanguage(this.serveur, this.currentUser, this.actionToolBar, this, this.signaletiqueType));
    }
    
    private String loadName() {
        // Selection du titre suivant le type de Signal�tique demand�
        String titreSignaletique = "";
        switch (signaletiqueType) {
            case astrainterface.COMBO_CODE_POST :
                titreSignaletique = "IF_SIGN_codePostaux";
                break;
            case astrainterface.COMBO_DEVISE :
                 titreSignaletique = "IF_SIGN_devise";
                 break;
            case astrainterface.COMBO_LANGUE :
                 titreSignaletique = "IF_SIGN_LANG"; 
                 break;
            case astrainterface.COMBO_PAYS :
                 titreSignaletique = "IF_SIGN_PAYS";
                 break;
            case astrainterface.COMBO_LOGEMENT :
                titreSignaletique = "IF_SIGN_logement";
                break;
            case astrainterface.COMBO_TITREPERSONNES :
                titreSignaletique = "IF_SIGN_titre";
                break;
            case astrainterface.COMBO_TRANSPORT :
                titreSignaletique = "IF_SIGN_transport";
                break;
            case astrainterface.COMBO_TVA_TYPE :
                titreSignaletique = "IF_SIGN_TypeTva";
                break;
            case astrainterface.COMBO_DESTINATION :
                titreSignaletique = "IF_SIGN_destination";
                break;
            case astrainterface.COMBO_COMPAGNIE :
                titreSignaletique = "IF_SIGN_Compagnie";
                break;
           
        }
        String titredefault;
        try {
            titredefault = java.util.ResourceBundle.getBundle("srcastra.astra.locale.IFrame", currentUser.getLangage()).getString("IF_SIGN_default");
            if (!titreSignaletique.equals("")) {
                titreSignaletique = java.util.ResourceBundle.getBundle("srcastra.astra.locale.IFrame", currentUser.getLangage()).getString(titreSignaletique);
            }
        }
        catch (java.util.MissingResourceException mre) {
            titredefault = "";
            titreSignaletique = "";
        }
        return titredefault + " : " + titreSignaletique;
    }
    
    /** Lance la s�quence de suppression
     */
    public void displayDeleteSequence() {
    }
    /** Permet de changer l'�tat de la TabbedPane dans le pricipal  */
    public void enabledTabbedPane(boolean enabled) {
    }
    
    /** Ferme le module
     * (dans Astra -> JInternalFrame)
     */
    public void closeModule() {
        this.doDefaultCloseAction();      
    }
    
    /** Passage � l'�cran suivant
     * @param currentScreen num�ro de l'�cran courrant
     */
    public void nextScreen(int currentScreen) {
    }
    /** Fixe la cl� unique dans le module parent
     * @param ContextCleUnik cl� unique
     */
    public void setContextCleUnik(int ContextCleUnik) {
    }
   
    /** Lance la s�quence de cr�ation
     */
    public void displayCreateSequence() {
    }
    /** Permet de charger le panel des statuts  */
    public void chargeStatusPanel(String[] statuts) {
    }
    
    public void setCurrentPanel(srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer currentPanel) {
        this.currentPanel = currentPanel;
        actionToolBar.setTbComposer(currentPanel);
    }    
    
    public void reloadToolBarInfo() {
        if (currentPanel != null) {
            actionToolBar.setTbComposer(this.currentPanel);
            actionToolBar.reloadActionEnabled(this.currentActionEnabled);
            System.out.println("reload les infos de la toolBar");
        }
    }    
    
   public void saveToolBarInfo() {
        //currentPanel = actionToolBar.getTbComposer();
        // currentActionEnabled = actionToolBar.getActionEnabled();
        System.out.println("reload les infos de la toolBar");
    }
   
    public void setCurrentActionEnabled(int[] actionEnabled) {
        actionToolBar.setActionEnabled(actionEnabled);
        this.currentActionEnabled = this.actionToolBar.getActionEnabled();
    }
    
    public astrainterface getServeur() {
        return this.serveur;
    }
    
    public Loginusers_T getCurrentUser() {
        return this.currentUser;
    }
    
    public void changeCursor(int changeLocation, java.awt.Cursor cursor) {
        srcastra.astra.gui.sys.utils.CursorChange.changeCursor(changeLocation, cursor, superOwner, this);
    }
     public java.awt.Frame getSuperOwner(){
        return (srcastra.astra.gui.MainFrame)this.superOwner;  
    }
    
     public void closePanne() {
     }
     
     
     public void openPannel(int i) {
     }
     
     public void setReadModeToPanel() {
     }
     
     public void getSignaletique(int typeSignaletique) {
     }
     
     public boolean getNestedSignaletique() {
         return this.netstedSignletique;
     }
     
     public void setNestedSignaletique(boolean netstedSignletique) {
         this.netstedSignletique=netstedSignletique;
     }
     
     public java.awt.Frame getOwner() {
         return null;
     }
     
     public void registerTable(javax.swing.JTable generique_table) {
     }
     
     public void nextScreen(int currentScreen, int insert) {
     }
     
     private boolean netstedSignletique;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
