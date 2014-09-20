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
import srcastra.astra.sys.classetransfert.dossier.*;

import java.util.*;

import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.sys.classetransfert.utils.*;
import srcastra.astra.gui.sys.*;

/**
 * @author Thomas
 */
public class TransfertAction extends Action {

    /**
     * Creates a new instance of UserAction
     */
    double entcheque;
    double socheque;
    double entcash;
    double socash;

    public TransfertAction(Caisse panel, astrainterface serveur, Loginusers_T user) {
        super(panel, serveur, user);
    }

    public void doCreate() {
        panel.activeToolBarAction(ActionToolBar.ACT_INSERT);
        enable(true);
    }

    private void enable(boolean enabled) {
        panel.getGrp_TField_ECheque().setEnabled(enabled);
        panel.getGrp_TField_Ecash().setEnabled(enabled);
        // panel.getGrp_TField_Scash().setEnabled(enabled);
        // panel.getGrp_TField_Scheq().setEnabled(enabled);
        panel.getGrp_TField_SoCash().setEnabled(enabled);
        panel.getGrp_TField_SoCheq().setEnabled(enabled);
        panel.getGrp_TField_transfertlib().setEnabled(enabled);
        if (enabled) {
            panel.getGrp_TField_SoCash().requestFocus();
            panel.getGrp_TField_ECheque().setText("0.0");
            panel.getGrp_TField_Ecash().setText("0.0");
            panel.getGrp_TField_SoCash().setText("0.0");
            panel.getGrp_TField_SoCheq().setText("0.0");
        } else
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
        try {
            ArrayList payement = checkTransfert();
            if (payement != null && payement.size() > 0) {
                for (int i = 0; i < payement.size(); i++) {
                    Payement_T pay = (Payement_T) payement.get(i);
                    pay.setTransfert(true);
                    serveur.renvDossierRmiObject(this.user.getUrcleunik()).insertPaymentTransfert(pay, this.user.getUrcleunik(), 0);
                }
                panel.getAction(0).doPrevious();
                panel.activeToolBarAction(ActionToolBar.ACT_READ);
                enable(false);
            }
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            panel.getAction(1).doCancel();
            panel.getAction(0).doPrevious();
            ErreurScreenLibrary.displayErreur(panel, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se, user);


        } catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(panel, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re, user);
        }
    }

    private ArrayList checkTransfert() {
        double cash = panel.getData().getSoldecash();
        double cheque = panel.getData().getSoldecheque();
        double cashPer = panel.getData().getSoldecashPer();
        double chequePer = panel.getData().getSoldechequePer();
        ArrayList payement = new ArrayList();
        Payement_T sortie = null;
        Payement_T entre = null;
        String test = panel.getGrp_TField_ECheque().getText();
        System.out.println("panneaux" + test);
        try {
            entcheque = Double.parseDouble(panel.getGrp_TField_ECheque().getText());
        } catch (NumberFormatException nn) {
            entcheque = 0;
        }
        try {
            entcash = Double.parseDouble(panel.getGrp_TField_Ecash().getText());
        } catch (NumberFormatException nn) {
            entcash = 0;
        }
        try {
            socheque = Double.parseDouble(panel.getGrp_TField_SoCheq().getText());
        } catch (NumberFormatException nn) {
            socheque = 0;
        }
        try {
            socash = Double.parseDouble(panel.getGrp_TField_SoCash().getText());
        } catch (NumberFormatException nn) {
            socash = 0;
        }
        if ((socheque != 0 && entcheque != 0))
            return null;
        if ((socash != 0 && entcash != 0))
            return null;
        if (socheque != 0) {
            sortie = new Payement_T(0, CalculDate.getTodayDate(), -socheque, srcastra.astra.sys.classetransfert.configuration.TypePayement.CHEQUE);
            sortie.setLibelle(panel.getGrp_TField_transfertlib().getText());
            sortie.setCash(cashPer);
            sortie.setCheque(chequePer);
            chequePer = chequePer + sortie.getPrix();
            cheque = cheque + sortie.getPrix();
            payement.add(sortie);
        }
        if (socash != 0) {
            sortie = new Payement_T(0, CalculDate.getTodayDate(), -socash, srcastra.astra.sys.classetransfert.configuration.TypePayement.CASH);
            sortie.setLibelle(panel.getGrp_TField_transfertlib().getText());
            sortie.setCash(cashPer);
            sortie.setCheque(chequePer);
            cashPer = cashPer + sortie.getPrix();
            payement.add(sortie);
        }
        if (entcheque != 0) {
            entre = new Payement_T(0, CalculDate.getTodayDate(), entcheque, srcastra.astra.sys.classetransfert.configuration.TypePayement.CHEQUE);
            entre.setLibelle(panel.getGrp_TField_transfertlib().getText());
            entre.setCash(cashPer);
            entre.setCheque(chequePer);
            chequePer = chequePer + entre.getPrix();
            payement.add(entre);
        }
        if (entcash != 0) {
            entre = new Payement_T(0, CalculDate.getTodayDate(), entcash, srcastra.astra.sys.classetransfert.configuration.TypePayement.CASH);
            entre.setLibelle(panel.getGrp_TField_transfertlib().getText());
            entre.setCash(cashPer);
            entre.setCheque(chequePer);
            cashPer = cashPer + entre.getPrix();
            payement.add(entre);
        }
        /*   if(entre!=null){
            entre.setLibelle(panel.getGrp_TField_transfertlib().getText());
            entre.setCash(panel.getData().getSoldecash());
            entre.setCheque(panel.getData().getSoldecheque());
            payement.add(entre);
            
            
        }
        if(sortie!=null){
            sortie.setLibelle(panel.getGrp_TField_transfertlib().getText());
            sortie.setCash(panel.getData().getSoldecash());
            sortie.setCheque(panel.getData().getSoldecheque());
            System.out.println("sortie cash"+sortie.getCash());
            System.out.println("sortie cheque"+sortie.getCheque());
            payement.add(sortie);          
        }*/
        return payement;
    }

    public void doPrint() {
    }

    public void doCancel() {
        panel.activeToolBarAction(ActionToolBar.ACT_READ);
        enable(false);
    }

}
