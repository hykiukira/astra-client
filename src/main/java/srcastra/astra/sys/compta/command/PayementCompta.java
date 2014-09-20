/*
* DossierCompta.java
*
* Created on 10 février 2003, 15:15
*/
package srcastra.astra.sys.compta.command;

import srcastra.astra.sys.classetransfert.configuration.Caisselibelle_T;
import srcastra.astra.sys.classetransfert.configuration.TypePayement;
import srcastra.astra.sys.classetransfert.dossier.Payement_T;
import srcastra.astra.sys.compta.*;
import srcastra.astra.sys.compta.checkcompte.Check;
import srcastra.astra.sys.rmi.CaisselibelleRmi;
import srcastra.astra.sys.rmi.TypePayementRmi;
import srcastra.astra.sys.rmi.astraimplement;

/**
 * @author Thomas
 */
public class PayementCompta extends AbstractLigneCompta implements Command {
    /**
     * Creates a new instance of DossierCompta
     */
    Payement_T payement;
    Configuration m_config;
    astraimplement m_serveur;
    int compte;
    int centralisateur;
    String comptes;
    int compteP;
    int centralisateurP;
    String comptesP;
    Check check;

    public PayementCompta(Object obj, Configuration config) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {
        payement = (Payement_T) obj;
        m_config = config;
        check = new Check(config.getCle2(), config);
        check.init();
        long transact = GenerateNum.generateTransactionNumber(m_config.getCon(), m_config.getM_serveur().getTransactSyn());
       
        System.out.println("Cle user : " + m_config.getM_urcleunik());
        GetData.getCompteData("" + m_config.getM_urcleunik()).setTransaction(new Long(transact).intValue());
//srcastra.astra.sys.compta.checkcompte.Check.getTransaction(m_config.get, serveur.getTransactSyn(), serveur);
    }

    public void executeInsert(int urcleunik) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {
        Caisselibelle_T caisse = null;
        TypePayement typepayt = (TypePayement) ((TypePayementRmi) m_config.getM_serveur().renvTypePayementRmiObject(m_config.getCle2())).get(m_config.getCle2(), payement.getM_typepayement(), 0);
        if (typepayt.getTynt_categorie() == TypePayement.CCCF) {
            payement.setCccf(true);
        }
        
        
        if (payement.isCccf()) {
            new CccfPayement(check, payement, m_config).generatHistorique();
 //      }
 //       else if (typepayt.getTynt_categorie() == TypePayement.MCO){
            
 //            new CccfPayement(check, payement, m_config).generatHistorique();
        
        } else if (payement.isTransfert() || payement.isCc()) {
            new TransfertPayement(check, payement, m_config).generatHistorique();
        } else if (payement.isDivers()) {
            caisse = (Caisselibelle_T) ((CaisselibelleRmi) m_config.getM_serveur().renvCaisseLibelleRmiObject(m_config.getCle2())).get(m_config.getCle2(), payement.getCategorie(), 0);
            if (caisse.getCale_categorie() == caisse.GENE) {
                new DiversPayementGen(check, payement, m_config, caisse).generatHistorique();
            } else if (caisse.getCale_categorie() == caisse.CLIENT) {
                m_config.setCleintervenant(caisse.getCe_cleunik());
                m_config.setTypeintervenant(1);
                new DiversPayementClient(check, payement, m_config, caisse).generatHistorique();
            } else if (caisse.getCale_categorie() == caisse.FOURNISSEUR) {
                m_config.setCleintervenant(caisse.getCe_cleunik());
                m_config.setTypeintervenant(2);
                new DiversPayementFournisseur(check, payement, m_config, caisse).generatHistorique();
            }
        } else {
            ClientPayement client = new ClientPayement(check, payement, m_config);
            int typepay = client.generatHistorique();
            if (typepay == TypePayement.CARD) {
                payement.setCc(true);
                executeInsert(m_config.getM_urcleunik());
            }
        }
    }

    public void executeModif(int urcleunik, int sw) throws java.sql.SQLException {
    }
     public void executeInsertNCService(int  urcleunik) throws java.sql.SQLException
 {
     
 } 
    
}
