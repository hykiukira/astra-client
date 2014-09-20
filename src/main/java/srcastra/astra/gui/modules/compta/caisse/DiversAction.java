/*
 * UserAction.java
 *
 * Created on 26 août 2003, 16:09
 */

package srcastra.astra.gui.modules.compta.caisse;

import srcastra.astra.sys.rmi.*;
import srcastra.astra.gui.components.actions.actionToolBar.*;
import srcastra.astra.gui.sys.*;
import srcastra.astra.sys.classetransfert.*;

import java.util.*;

import srcastra.astra.sys.classetransfert.dossier.*;
import srcastra.astra.sys.classetransfert.utils.*;

/**
 * @author Thomas
 */
public class DiversAction extends Action {

    /**
     * Creates a new instance of UserAction
     */
    double entre;
    double sortie;

    public DiversAction(Caisse panel, astrainterface serveur, Loginusers_T user) {
        super(panel, serveur, user);
    }

    public void doCreate() {
        panel.activeToolBarAction(ActionToolBar.ACT_INSERT);
        enable(true);
    }

    private void enable(boolean enabled) {
        panel.getGrp_liste_typedepense().setEnabled(enabled);
        panel.getGrp_TField_entre().setEnabled(enabled);
        panel.getGrp_TextField_sortie().setEnabled(enabled);
        panel.getGrp_TFiel_lib().setEnabled(enabled);
        if (enabled)
            panel.getGrp_liste_typedepense().requestFocus();
        else
            panel.requestFocus();
    }

    public void doDelete() {
    }

    public void doF10() {
    }

    public void doF7() {
    }

    public void doHelp() {
    }

    public void doModify() {
    }

    public void doNext() {
    }

    public void doPrevious() {
        if (panel.getGrp_liste_typedepense().getCleUnik() == 0)
            return;
        try {
            ArrayList payement = checkTransfert();
            if (payement != null && payement.size() > 0) {
                for (int i = 0; i < payement.size(); i++) {
                    Payement_T pay = (Payement_T) payement.get(i);
                    pay.setDivers(true);
                    serveur.renvDossierRmiObject(this.user.getUrcleunik()).insertPaymentTransfert(pay, this.user.getUrcleunik(), 0);
                }
                panel.getAction(0).doPrevious();
                panel.activeToolBarAction(ActionToolBar.ACT_READ);
                enable(false);
            }
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(panel, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se, user);

        } catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(panel, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re, user);
        }
    }

    private ArrayList checkTransfert() {
        ArrayList payement = new ArrayList();
        Payement_T sortiep = null;
        Payement_T entrep = null;
        String test = panel.getGrp_TField_ECheque().getText();
        System.out.println("panneaux" + test);
        try {
            entre = Double.parseDouble(panel.getGrp_TField_entre().getText());
        } catch (NumberFormatException nn) {
            entre = 0;
        }
        try {
            sortie = Double.parseDouble(panel.getGrp_TextField_sortie().getText());
        } catch (NumberFormatException nn) {
            sortie = 0;
        }
        if ((entre != 0 && sortie != 0))
            return null;
        if (sortie != 0)
            sortiep = new Payement_T(0, CalculDate.getTodayDate(), -sortie, srcastra.astra.sys.classetransfert.configuration.TypePayement.CASH);
        if (entre != 0)
            entrep = new Payement_T(0, CalculDate.getTodayDate(), entre, srcastra.astra.sys.classetransfert.configuration.TypePayement.CASH);
        if (entrep != null) {
            entrep.setLibelle(panel.getGrp_liste_typedepense().getText() + panel.getGrp_TFiel_lib().getText());
            entrep.setCategorie(panel.getGrp_liste_typedepense().getCleUnik());
            payement.add(entrep);
        }
        if (sortiep != null) {
            sortiep.setLibelle(panel.getGrp_liste_typedepense().getText() + " " + panel.getGrp_TFiel_lib().getText());
            sortiep.setCategorie(panel.getGrp_liste_typedepense().getCleUnik());
            payement.add(sortiep);
        }
        return payement;
    }

    public void doPrint() {
    }

    public void doCancel() {
        panel.activeToolBarAction(ActionToolBar.ACT_READ);
        enable(false);
    }
}
