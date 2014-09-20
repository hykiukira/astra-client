/*




 * avionGui.java




 *




 * Created on 8 januari 2003, 9:50




 */
package srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux;

import javax.swing.*;
//import srcastra.astra.gui.modules.dossier.productSpecification.*;
import srcastra.astra.gui.modules.dossier.*;
import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;
import srcastra.astra.gui.modules.*;
import srcastra.astra.gui.components.actions.actionToolBar.*;
import srcastra.astra.sys.classetransfert.dossier.Sup_reduc_T;

/**
 * @author Thomas
 */
public class SupreducEditionGui extends AbstractSousPanel {
    /**
     * Creates a new instance of avionGui
     */
    JPanel m_parentPanel;
    public Config m_config;

    public JPanel getM_parentPanel() {
        return m_parentPanel;


    }

    public SupreducEditionGui(DossierMainScreenModule parent, DossierProduitPane pane_produit, Config config) {
        super(parent, pane_produit);
        m_config = config;
        panel = new SupReducPanelEdition(this, m_config.typeProduct);
        oldBorder = panel.getBorder();


    }

    /**
     * Permet au parent de lancer le chargement des données au
     * sein de liste (Ici : ListSelector)
     */
    public void chargeData() {
    }

    /**
     * Demande d'une suppression ou d'une annulation physique au serveur
     */
    public void dbDelete() {
    }

    /**
     * Demande d'une insertion au serveur
     */
    public void dbInsert() {
    }

    /**
     * Demande de sélection au serveur
     */
    public void dbSelect() {
    }

    /**
     * Demande de sélection en vue d'une modification au serveur
     */
    public void dbSelectForUpdate() {
    }

    /**
     * Demande d'une modification au serveur
     */
    public void dbUpdate() {
    }

    /**
     * Affichage en Mode disable
     */
    public void displayDisableMode() {
    }

    /**
     * Affichage en mode Insertion
     */
    public void displayInsertMode() {
    }

    /**
     * Affichage en mode Lecture
     */
    public void displayReadMode() {
    }

    /**
     * Affichage en mode Modification
     */
    public void displayUpdateMode() {
    }

    /**
     * Permet à la classe qui implémente cette méthode de se
     * référencer auprès d' ActionToolBar
     *
     * @return le n° de l'action
     */
    public int getAction() {
        return 0;


    }

    /**
     * Sert à recevoir le titre de son parent
     * pour un cadre éventuel
     *
     * @return le titre du panneau
     */
    public String getTitle() {
        return null;


    }

    public JPanel getUI() {
        return panel;


    }

    /**
     * Permet de préciser le type d'action qu'on est occupé de faire :
     * 0 pour lecture
     * 1 pour création
     * 2 pour modification
     *
     * @param action type d'action
     */
    public void setAction(int action) {
    }

    public void setEnabled(boolean enabled) {
    }

    /**
     * Permet de recevoir la clé unique d'un objet relatif
     * au modules : création par partie ou modification
     *
     * @param frCleUnik la clé unique
     */
    public void setFrCleunik(int frCleUnik) {
    }

    public void setRequestFocusEnabled(boolean enabled) {
    }

    /**
     * Spécifie le composant qui implémente cette fonction comme
     * le composant qui pilote l'actionToolBar
     */
    public void setThisAsToolBarComponent() {
    }

    /**
     * Méthode pour l'update de tous les champs
     */
    public void updateAllFields() {
    }

    public void updateAllFields(Object donnee) {
    }

    public String getName() {
        return AbstractMainPanel.SUP_REDUC;


    }

    public int[] getDefaultActionToolBarMask() {
        return null;
    }

    public void doAccept() {
    }

    public void doCreate() {
        panel.doCreate();


    }

    public void doModify() {
        panel.doModify();


    }

    public void doDelete() {
    }

    public int doPrevious(boolean sw) {
        return panel.doPrevious(sw);


    }

    public void doNext() {
    }

    public void doCancel() {
        panel.doCancel();


    }

    public void doClose() {
    }

    public void doHelp() {
    }

    public void doPrint() {
    }

    public void doSwitch() {
    }

    public String getNexPanel(int mode) {
        return AbstractMainPanel.PRODUIT;


    }

    public void setSelected(boolean selected) {
        if (selected) panel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 5));
        else panel.setBorder(oldBorder);


    }

    public String getMyType() {
        return AbstractMainPanel.SUP_REDUC_EDITION;


    }

    public int[] getActions(int mode2) {
        int[] returnvalue = null;
        mode = mode2;
        if (mode == ActionToolBar.ACT_READ) {
            returnvalue = readTab;


        } else if (mode == ActionToolBar.ACT_INSERT) {
            returnvalue = insertTab;


        } else if (mode == ActionToolBar.ACT_MODIFY) {
            returnvalue = modifyTab;


        }
        return returnvalue;


    }

    public void setVisible(boolean visible) {
        panel.setVisible(visible);


    }

    public String getSwitchAble() {
        return AbstractMainPanel.SUP_REDUC;


    }

    public String getNextToHide() {
        return null;


    }

    public boolean isMainPanel() {
        return false;


    }

    public void reloadTableModel() {
    }

    SupReducPanelEdition panel;

    public void setSup_reduc(Object sup_reduc) {
        if (sup_reduc != null) panel.setSup_reduc(sup_reduc);


    }

    public Object getSupReduc(int i) {
        return null;


    }

    public void moveInTable(int direction) {
    }

    public java.awt.Component getTable() {
        return null;


    }

    public double getPrix() {
        return 0;


    }

    public int doModify(boolean sw) {
        return panel.doModify(sw);


    }

    public int doCancel(boolean sw) {
        return panel.doModify(sw);


    }

    public int doAccept(boolean sw) {
        return panel.doModify(sw);


    }

    public int doCreate(boolean sw) {
        return panel.doCreate(sw);


    }

    public int doDelete(boolean sw) {
        return panel.doDelete(sw);


    }

    public void addGeneriqueListener(MainPanelListener listener) {
    }

    public void killTbinteraction() {
    }

    public void doNext(boolean sw) {
    }


}




