/*
* DossierCompta.java
*
* Created on 10 février 2003, 15:15
*/
package srcastra.astra.sys.compta.command;

import srcastra.astra.sys.classetransfert.dossier.Dossier_T;
import srcastra.astra.sys.compta.AbstractLigneCompta;
import srcastra.astra.sys.compta.Configuration;
import srcastra.astra.sys.compta.GetData;
import srcastra.astra.sys.compta.checkcompte.Check;
import srcastra.astra.sys.compta.checkcompte.CheckCompteCentral;
import srcastra.astra.sys.rmi.ParamComptableInterface;
import srcastra.astra.sys.rmi.astraimplement;

/**
 * @author Thomas
 */
public class DossierCompta extends AbstractLigneCompta implements Command {

    /**
     * Creates a new instance of DossierCompta
     */
    Dossier_T dossier;
    Configuration m_config;
    astraimplement m_serveur;
    int compte;
    int centralisateur;
    String comptes;
    Check check;

    public DossierCompta(Object obj, Configuration config) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {
        dossier = (Dossier_T) obj;
        check = new Check(config.getCle2(), config);
        check.init();
        m_config = config;
        check.base(CheckCompteCentral.CLIENT, ParamComptableInterface.JOURNAL_VENTE);
        comptes = m_config.getM_serveur().getCompte(GetData.getCompteData("" + m_config.getM_urcleunik()).getCe_cleunik(), new Long(m_config.getCle2()).intValue());
    }

    public void executeInsert(int urcleunik) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {
        new ComptaInseMod().insert(INSERT_LIGNE, m_config, 0, 0, 0, 0, 0, 0, "0000-00-00 00:00:00", centralisateur, 0, compte, comptes, dossier.getDrtotalprix(), 0, dossier.getDrprix(),
                dossier.getDrtva(), 0, "", 0, "", dossier.getDrcleunik(), 0, 0, 0, 1, dossier.getClientContractuel().getCscleunik(), 1, "O", "B", urcleunik, 0, "Ligne de globalisation du dossier",
                0, 0, 0, 0, 0, 0, "D",0);
    }

    public void executeModif(int urcleunik, int sw) throws java.sql.SQLException {
        new ComptaInseMod().modify(dossier.getDrcleunik(), 0, 0, UPDATE_LIGNE, dossier, m_config, centralisateur, 0, compte, comptes, dossier.getDrtotalprix(), 0
                , dossier.getDrprix(), dossier.getDrtva(), 0, "", 0, ""
                , dossier.getClientContractuel().getCscleunik(), 1, urcleunik
                , 0, " ", 0, 0, 0, 0, 0, 0);
    }

     public void executeInsertNCService(int  urcleunik) throws java.sql.SQLException
 {
     
 } 
}
