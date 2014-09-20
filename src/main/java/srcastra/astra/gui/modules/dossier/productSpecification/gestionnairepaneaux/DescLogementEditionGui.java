/*




 * avionGui.java




 *




 * Created on 8 januari 2003, 9:50




 */
package srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux;

import javax.swing.*;

import srcastra.astra.gui.modules.dossier.productSpecification.*;
import srcastra.astra.gui.modules.dossier.*;
import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;
import srcastra.astra.gui.modules.*;
import srcastra.astra.gui.components.actions.actionToolBar.*;
import srcastra.astra.sys.classetransfert.dossier.Sup_reduc_T;

/**
 * @author Thomas
 */
public class DescLogementEditionGui extends AbstractSousPanel {
    /**
     * Creates a new instance of avionGui
     */
    public DescLogementEditionGui(DossierMainScreenModule parent, DossierProduitPane pane_produit, Config config) {
        super(parent, pane_produit);
        m_config = config;
        panel = new DescriptionLogementEdition(this);
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
        return AbstractMainPanel.DESC_EDITION;


    }

    public int[] getDefaultActionToolBarMask() {
        return null;
    }

    public void doAccept() {
    }

    public void doCreate() {
    }

    public void doModify() {
    }

    public void doDelete() {
    }

    public int doPrevious(boolean sw) {
        panel.doPrevious(true);
        return 1;


    }

    public void doNext() {
    }

    public void doCancel() {
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
        return AbstractMainPanel.SUP_REDUC;


    }

    public void setSelected(boolean selected) {
        if (selected) panel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 5));
        else panel.setBorder(oldBorder);


    }

    public String getMyType() {
        return AbstractMainPanel.DESC_EDITION;


    }

    public int[] getActions(int mode2) {
        int[] returnvalue = null;
        mode = mode2;
        if (mode == ActionToolBar.ACT_READ) {
            returnvalue = readTab2;


        } else if (mode == ActionToolBar.ACT_INSERT) {
            returnvalue = insertTab;


        } else if (mode == ActionToolBar.ACT_MODIFY) {
            returnvalue = modifyTab;


        }
        return returnvalue;


    }

    public void setVisible(boolean visible) {
    }

    public String getSwitchAble() {
        return AbstractMainPanel.DESC;


    }

    public String getNextToHide() {
        return null;


    }

    public boolean isMainPanel() {
        return false;


    }

    public void reloadTableModel() {
        panel.reloadTableModel();


    }

    DescriptionLogementEdition panel;

    public void setSup_reduc(Object sup_reduc) {
        panel.setSup_reduc(sup_reduc);


    }

    public Object getSupReduc(int i) {
        return panel.getSupReduc2(i);


    }

    public void moveInTable(int direction) {
        panel.moveInTable(direction);


    }

    public java.awt.Component getTable() {
        return panel.getTable();


    }

    public double getPrix() {
        return 0;


    }

    public int doAccept(boolean sw) {
        return panel.doAccept(sw);


    }

    public int doCancel(boolean sw) {
        return panel.doCancel(sw);


    }

    public int doCreate(boolean sw) {
        return panel.doCreate(sw);


    }

    public int doModify(boolean sw) {
        return panel.doModify(sw);


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




