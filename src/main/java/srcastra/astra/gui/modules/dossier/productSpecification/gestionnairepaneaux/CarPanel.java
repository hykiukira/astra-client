/*

 * CarPanel.java

 *

 * Created on 12 mai 2003, 10:11

 */


package srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux;

import srcastra.astra.gui.modules.*;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import srcastra.astra.gui.modules.mainScreenModule.*;

import srcastra.astra.gui.modules.dossier.*;

import srcastra.astra.gui.components.*;

import srcastra.astra.gui.components.actions.*;

import srcastra.astra.gui.components.fx.*;

import srcastra.astra.gui.event.*;

import srcastra.astra.gui.sys.utils.*;

import srcastra.astra.gui.sys.formVerification.*;

import srcastra.astra.sys.classetransfert.dossier.*;

import srcastra.astra.gui.sys.listModel.dossierListModel.*;

import srcastra.astra.sys.classetransfert.dossier.voitureLocation.*;

import java.awt.event.*;

import javax.swing.*;

import javax.swing.border.*;

import java.awt.*;

/**
 * @author Thomas
 */

public class CarPanel extends VoitureLocationPane {


    /**
     * Creates a new instance of CarPanel
     */

    public CarPanel(AbstractSousPanel parent2) {

        super(parent2);

    }

    public int doPrevious(boolean sw) {

        switch (action) {

            case ActionToolBar.ACT_INSERT:

                dbInsert();

                break;

            case ActionToolBar.ACT_READ:

                break;

            case ActionToolBar.ACT_MODIFY:

                dbUpdate();

                break;

        }

        return 1;

    }

    public int doCreate(boolean sw) {

        displayInsertMode();

        return 1;

    }

    public int doDelete(boolean sw) {

        return 1;

    }

    public int doModify(boolean sw) {

        displayUpdateMode();

        return 1;

    }

    public void dbInsert() {

        chargeVoitureLocationClassInsert();

        parent.getCar().setLocalyModify(true);

        parent.calculTotalUnProduit(parent.getCar());

        displayReadMode();

    }

    public void dbUpdate() {

        chargeVoitureLocationClassUpdate();

        parent.getCar().setLocalyModify(true);

        parent.calculTotalUnProduit(parent.getCar());

        displayReadMode();

    }

    protected void chargeVoitureLocationClassUpdate() {

        if (!parent.getDossier().isNewreccord())

            parent.getDossier().setModifreccord(true);

        if (!parent.getCar().isIsnewreccord())

            parent.getCar().setModify(true);

        parent.getCar().calculMontantTotal();

        chargeVoitureLocationClass();

    }


    private void chargeVoitureLocationClassInsert() {

        if (!parent.getDossier().isNewreccord())

            parent.getDossier().setModifreccord(true);

        parent.getCar().setAt_cleunik(parent.getVoitureLocationCompteur());

        parent.decrementeVoitureLocationCompteur();

        parent.getCar().setIsnewreccord(true);


        parent.getCar().setLongtime();

        //  parent.getAssurance().setDatetimecrea(new srcastra.astra.sys.classetransfert.utils.Date());

        // parent.getAssurance().getDatetimecrea().setLongTime();

        chargeVoitureLocationClass();

    }

    public void displayReadMode() {

        action = ActionToolBar.ACT_READ;

        tb_interaction.enableValidateActionListener(false);

        for (int i = 0; i < componentToVerif.length; i++) {

            componentToVerif[i].setText("");

            componentToVerif[i].setEnabled(false);

            componentToVerif[i].setLastFocusedComponent(false);

        }

        getGrp_TArea_Memo().setText("");

        enableMemo(false);

        chargeData();

        for (int i = 0; i < componentToVerif.length; i++) {

            componentToVerif[i].clearIcon();

        }

    }

    public void chargeData() {

        VoitureLocation_T voit = parent.getCar();

        getGrp_TField_Pnr().setText("" + voit.getVl_pnr());

        getGrp_ADate_depart().setDate(voit.getVl_dateDepart());

        getGrp_ADate_retour().setDate(voit.getVl_dateRetour());

        getGrp_ACB_Statut().setSelectedCleUnik(voit.getStatut());

        getGrp_TArea_Memo().setText(voit.getVl_memo());

    }

    public void displayUpdateMode() {

        action = ActionToolBar.ACT_MODIFY;

        tb_interaction.enableValidateActionListener(false);

        for (int i = 0; i < componentToVerif.length; i++) {

            componentToVerif[i].setText("");

        }

        getGrp_TArea_Memo().setText("");

        chargeData();

        for (int i = 0; i < componentToVerif.length; i++) {

            componentToVerif[i].clearIcon();

            componentToVerif[i].setEnabled(true);

            componentToVerif[i].setLastFocusedComponent(true);

        }

        enableMemo(true);

        getGrp_TField_Pnr().requestFocus();

    }

    public void displayInsertMode() {

        action = ActionToolBar.ACT_INSERT;

        for (int i = 0; i < componentToVerif.length; i++) {

            componentToVerif[i].setText("");

        }

        chargeDefaultValue();

        for (int i = 0; i < componentToVerif.length; i++) {

            componentToVerif[i].setEnabled(true);

            componentToVerif[i].setLastFocusedComponent(true);

            componentToVerif[i].clearIcon();

        }

        getGrp_TArea_Memo().setText("");

        enableMemo(true);

        getGrp_TField_Pnr().requestFocus();

        tb_interaction.enableValidateActionListener(true);

    }

    protected VoitureLocation_T chargeVoitureLocationClass() {

        VoitureLocation_T voit = parent.getCar();

        String tmp = getGrp_TField_Pnr().getText();

        if (tmp == null) tmp = "";

        voit.setVl_pnr(tmp);

        voit.setVl_dateDepart(getGrp_ADate_depart().getDate());

        voit.setVl_dateRetour(getGrp_ADate_retour().getDate());

        voit.setStatut(getGrp_ACB_Statut().getSelectedCleUnik());

        voit.setVl_memo(getGrp_TArea_Memo().getText());

        voit.calculMontantTotal();

        voit.prepareAffichage();

        return voit;

    }


    public String getTitle() {

        return java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/DossierProduitPane", parent.getCurrentUser().getLangage()).getString("Titre_Pane_car");

    }

}

