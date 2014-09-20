/*
 * ClientPayement.java
 *
 * Created on 8 septembre 2003, 17:48
 */

package srcastra.astra.sys.compta;

import srcastra.astra.sys.classetransfert.configuration.Caisselibelle_T;
import srcastra.astra.sys.classetransfert.configuration.TypePayement;
import srcastra.astra.sys.classetransfert.dossier.Payement_T;
import srcastra.astra.sys.classetransfert.utils.CalculDate;
import srcastra.astra.sys.compta.checkcompte.Check;
import srcastra.astra.sys.compta.command.SoldeTot;
import srcastra.astra.sys.rmi.ParamComptableInterface;
import srcastra.astra.sys.rmi.TypePayementRmi;

/**
 * @author Thomas
 */
public class TransfertPayement extends Payement {

    /**
     * Creates a new instance of ClientPayement
     */
    String decre = "";

    public TransfertPayement(Check check, Payement_T payement, Configuration config) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {
        super(check, payement, config);
        payement.setDatePayement(CalculDate.getTodayDate());
        getInfo();
    }

    protected void getInfo() throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {
        if (!payement.isCc()) {
            typepay=(TypePayement)((TypePayementRmi)m_config.getM_serveur().renvTypePayementRmiObject(m_config.getCle2())).getByCategorie(m_config.getCle2(),payement.getM_typepayement(),0);
           // typepay = (TypePayement) ((TypePayementRmi) m_config.getM_serveur().renvTypePayementRmiObject(m_config.getCle2())).getCash(m_config.getCle2());
            payement.setCategorie(typepay.getTynt_categorie());
            payement.setM_typepayement(typepay.getTynt_cleunik());
        } else {
            typepay = (TypePayement) ((TypePayementRmi) m_config.getM_serveur().renvTypePayementRmiObject(m_config.getCle2())).get(m_config.getCle2(), payement.getM_typepayement(), 0);
            payement.setCategorie(typepay.getTynt_categorie());
            payement.setM_typepayement(typepay.getTynt_cleunik());
            payement.setPrix(payement.getPrix() * -1);
        }
    }

    public void generatHistorique() throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {
        if (payement.getPrix() > 0) {
            decre = "D";
            if (payement.isTransfert()) {
                GetData.getCompteData("" + m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE, GetData.getCompteData("" + m_config.getM_urcleunik()).getCe_cleunik(), payement.getPrix(), "D", payement));
            } else {
                if (!m_config.getDossier().isRattrap()) {
                    GetData.getCompteData("" + m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE, GetData.getCompteData("" + m_config.getM_urcleunik()).getCe_cleunik(), payement.getPrix(), "D", payement));
                }
                //m_config.getM_serveur().renvSoldeRmiObject(m_config.getCle2()).insertDebit(m_config.getCle2(),Caisselibelle_T.GENE,GetData.getCompteData().getCe_cleunik(),payement.getPrix(),GetData.getCompteData().getPeriode(),payement,true);
            }
        } else if (payement.getPrix() < 0) {
            decre = "C";
            if (payement.isTransfert()) {
                GetData.getCompteData("" + m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE, GetData.getCompteData("" + m_config.getM_urcleunik()).getCe_cleunik(), Math.abs(payement.getPrix()), "C", payement));
            } else {
                if (!m_config.getDossier().isRattrap()) {
                    GetData.getCompteData("" + m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE, GetData.getCompteData("" + m_config.getM_urcleunik()).getCe_cleunik(), Math.abs(payement.getPrix()), "C", payement));
                    // m_config.getM_serveur().renvSoldeRmiObject(m_config.getCle2()).insertCredit(m_config.getCle2(),Caisselibelle_T.GENE,GetData.getCompteData().getCe_cleunik(),-payement.getPrix(),GetData.getCompteData().getPeriode(),payement,true);
                }
            }
        }
        if (!payement.isCc())
            super.insertPayement("P", "+", decre);
        else
            super.insertPayement("CPC", "+", decre);
        check.commun(typepay.getCe_cleunik(), ParamComptableInterface.JOURNAL_VENTE);
        if (payement.getPrix() > 0) {
            decre = "C";
            if (payement.isTransfert()) {
                GetData.getCompteData("" + m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE, GetData.getCompteData("" + m_config.getM_urcleunik()).getCe_cleunik(), payement.getPrix(), "C", null));
            } else {
                if (!m_config.getDossier().isRattrap()) {
                    GetData.getCompteData("" + m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE, GetData.getCompteData("" + m_config.getM_urcleunik()).getCe_cleunik(), payement.getPrix(), "C", null));
                    //  m_config.getM_serveur().renvSoldeRmiObject(m_config.getCle2()).insertCredit(m_config.getCle2(),Caisselibelle_T.GENE,GetData.getCompteData().getCe_cleunik(),payement.getPrix(),GetData.getCompteData().getPeriode(),payement,false);
                }
            }
        } else if (payement.getPrix() < 0) {
            decre = "D";
            if (payement.isTransfert()) {
                GetData.getCompteData("" + m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE, GetData.getCompteData("" + m_config.getM_urcleunik()).getCe_cleunik(), Math.abs(payement.getPrix()), "D", null));
            } else {
                if (!m_config.getDossier().isRattrap()) {
                    GetData.getCompteData("" + m_config.getM_urcleunik()).getSolde().add(new SoldeTot(Caisselibelle_T.GENE, GetData.getCompteData("" + m_config.getM_urcleunik()).getCe_cleunik(), Math.abs(payement.getPrix()), "D", null));
                }
            }
            // m_config.getM_serveur().renvSoldeRmiObject(m_config.getCle2()).insertDebit(m_config.getCle2(),Caisselibelle_T.GENE,GetData.getCompteData().getCe_cleunik(),-payement.getPrix(),GetData.getCompteData().getPeriode(),payement,false);
        }
        super.insertPayement("CP", "-", decre);
        /* new ComptaInseMod().insert(AbstractLigneCompta.INSERT_LIGNE,m_config,1,0,0,0,0,Long.parseLong(numpiece),datepayement,0,0,0,"",-payement.getPrix(),0,0,
         0,0,"",0,"",m_config.getCledossier(),-1,-1,0,1,m_config.getCleintervenant(),1,"O","CP",urcleunik,payement.getM_typepayement(),"Ligne de globalisation du dossier",
         0,0,0,0,0,0);  */
    }

}
