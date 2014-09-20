/*

 * UserAction.java

 *

 * Created on 26 août 2003, 16:09

 */


package srcastra.astra.gui.modules.compta.caisse;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import srcastra.astra.sys.classetransfert.*;

import srcastra.astra.gui.sys.*;

import java.util.*;

import srcastra.astra.sys.compta.*;

/**
 * @author Thomas
 */

public class UserAction extends Action {


    /**
     * Creates a new instance of UserAction
     */

    int urcleunik;

    int eecleunik;

    srcastra.astra.sys.classetransfert.utils.Date date;

    public UserAction(Caisse panel, astrainterface serveur, Loginusers_T user) {

        super(panel, serveur, user);

    }


    public void doCreate() {

        panel.activeToolBarAction(ActionToolBar.ACT_INSERT);

        enable(true);

    }

    private void enable(boolean enabled) {

        panel.getGrp_date_date().setEnabled(enabled);

        if (user.getUrrights() == 1)

            panel.getGrp_liste_entite().setEnabled(enabled);

        panel.getGrp_liste_user().setEnabled(enabled);

        if (enabled)

            if (user.getUrrights() == 1) {

                panel.getGrp_liste_entite().requestFocus();

            } else {

                panel.getGrp_liste_user().requestFocus();

            }

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

        try {

            panel.activeToolBarAction(ActionToolBar.ACT_READ);

            enable(false);

            urcleunik = 0;

            if (!panel.getGrp_liste_user().getText().equals(""))

                urcleunik = panel.getGrp_liste_user().getCleUnik();

            System.out.println("cle due user" + urcleunik);

            this.eecleunik = panel.getGrp_liste_entite().getCleUnik();

            this.date = panel.getGrp_date_date().getDate();

            CaisseData data = serveur.renvParamCompta(user.getUrcleunik()).getListeCaisse(user.getUrcleunik(), this.date, this.eecleunik, this.urcleunik);

            panel.setData(data);

            panel.refreshTable(data.getCaisse());

            calculSolde(data);

            double total = data.getSoldecash() + data.getSoldecheque();

            panel.getGrp_Label_solde().setText("" + MathRound.roundThisToDouble(total));

            panel.getGrp_TField_Scash().setText("" + data.getSoldecash());

            panel.getGrp_TField_Scheq().setText("" + data.getSoldecheque());

        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {

            ErreurScreenLibrary.displayErreur(panel, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);

        }

        catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(panel, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }

    }

    public void doPrint() {

    }

    private void calculSolde(CaisseData data) {

        double cc = 0;

        double entre = 0;

        double sortie = 0;

        if (data.getCaisse() != null)

            for (int i = 0; i < data.getCaisse().size(); i++) {

                Object[] tab = (Object[]) data.getCaisse().get(i);

                if (((Integer) tab[5]).intValue() == srcastra.astra.sys.classetransfert.configuration.TypePayement.CARD) {

                    cc = cc + ((Double) tab[4]).doubleValue();

                } else if (((Double) tab[4]).doubleValue() > 0) {

                    entre = entre + ((Double) tab[4]).doubleValue();

                } else if (((Double) tab[4]).doubleValue() < 0) {

                    sortie = sortie + ((Double) tab[4]).doubleValue();

                }

                /*   if(((Integer)tab[5]).intValue()==srcastra.astra.sys.classetransfert.configuration.TypePayement.CASH)

                data.setSoldecash(data.getSoldecash()+((Double)tab[4]).doubleValue());

        else if(((Integer)tab[5]).intValue()==srcastra.astra.sys.classetransfert.configuration.TypePayement.CHEQUE)

                data.setSoldecheque(data.getSoldecheque()+((Double)tab[4]).doubleValue());*/

            }

        //data.setSoldecash(MathRound.roundThisToDouble(data.getSoldecash()));

        //data.setSoldecheque(MathRound.roundThisToDouble(data.getSoldecheque()));

        sortie = Math.abs(sortie);

        cc = MathRound.roundThisToDouble(cc);

        entre = MathRound.roundThisToDouble(entre);

        sortie = MathRound.roundThisToDouble(sortie);

        panel.getGrp_Lable_CC().setText("" + cc);

        panel.getGrp_Lable_soEntre().setText("" + entre);

        panel.getGrp_Lable_soSortie().setText("" + sortie);

    }


    public void doCancel() {

        panel.activeToolBarAction(ActionToolBar.ACT_READ);

        enable(false);

    }


}

