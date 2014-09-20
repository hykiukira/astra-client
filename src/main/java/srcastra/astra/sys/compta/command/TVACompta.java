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
import srcastra.astra.sys.compta.Tva_abrev_T;
import srcastra.astra.sys.compta.checkcompte.Check;
import srcastra.astra.sys.compta.checkcompte.CheckCompteCentral;
import srcastra.astra.sys.rmi.ParamComptableInterface;
import srcastra.astra.sys.rmi.astraimplement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author Thomas
 */
public class TVACompta extends AbstractLigneCompta implements Command {

    /**
     * Creates a new instance of DossierCompta
     */
    Dossier_T dossier;
    Configuration m_config;
    astraimplement m_serveur;
    Hashtable m_tva;
    int compte;
    int centralisateur;
    String comptes;
    int jxcleunik;
    long numpiece;
    long hetransact;
    int henocpt;
    Check check;

    public TVACompta(Object obj, Configuration config, Hashtable tva) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {
        dossier = (Dossier_T) obj;
        m_config = config;
        m_tva = tva;
        check = new Check(config.getCle2(), config);
        check.init();
        check.base3(CheckCompteCentral.TVA_PAYE, ParamComptableInterface.JOURNAL_VENTE);

        comptes = m_config.getM_serveur().getCompte(GetData.getCompteData("" + m_config.getM_urcleunik()).getCe_cleunik(), new Long(m_config.getCle2()).intValue());
    }

    public void executeInsert(int urcleunik) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {
        String tvarequete = "  INSERT INTO `historique2` (`heperiode`, `henotcpt`, `heclottva`, `heclotperiode`, " +
                "`heclotexercice`, `hetransact`, `jxcleunik`, `henumpiece`, `hedatecreation`, `hedatemouv`, `hevaleur`," +
                "`hecodetva`, `hevaleurbase`, `hevaleurtva`, `decleunik`, `hedatedevise`, `hevaleurdevise`, `helibelle`," +
                "`drcleunik`, `lignecleunik`, `sn_cleunik`, `ctprocleunik`, `typeintervenantcleunik`, `intervenantcleunik`," +
                "`ce_cleunik`, `cate_cleunik`, `hedossiercourant`, `hetypeligne`, `urcleunik`,`hetypepayement`,tva_cleunik,ce_cleunik2 )" +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?) ";
        for (Enumeration enu = m_tva.keys(); enu.hasMoreElements();) {
            Tva_abrev_T tvaa = (Tva_abrev_T) m_tva.get(enu.nextElement());
            new ComptaInseMod().insert(INSERT_LIGNE, m_config, henocpt, 0, 0, 0, 0, numpiece, "0000-00-00 00:00:00", centralisateur, tvaa.getTva_id(), compte, comptes, -tvaa.getTva_base(), tvaa.getTva_rate(), -tvaa.getTva_value(),
                    -tvaa.getTva_value(), 0, "", 0, "", dossier.getDrcleunik(), -1, -1, 0, 1, dossier.getClientContractuel().getCscleunik(), 1, "O", "TVAV", urcleunik, 0, "Ligne de globalisation du dossier",
                    0, 0, 0, 0, 0, 0, "C",0);
        }
    }
    public void executeInsertWithTransaction(int urcleunik) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {
        for (Enumeration enu = m_tva.keys(); enu.hasMoreElements();) {
            Tva_abrev_T tvaa = (Tva_abrev_T) m_tva.get(enu.nextElement());
            new ComptaInseMod().insertWithTransact(INSERT_LIGNE, m_config, henocpt, 0, 0, 0, hetransact, numpiece, "0000-00-00 00:00:00", centralisateur, tvaa.getTva_id(), compte, comptes, -tvaa.getTva_base(), tvaa.getTva_rate(), -tvaa.getTva_value(),
                    -tvaa.getTva_value(), 0, "", 0, "", dossier.getDrcleunik(), -1, -1, 0, 1, dossier.getClientContractuel().getCscleunik(), 1, "O", "TVAV", urcleunik, 0, "Ligne de globalisation du dossier",
                    0, 0, 0, 0, 0, 0, "C");
        }
    }

    public void executeModif(int urcleunik, int sw) throws java.rmi.RemoteException, srcastra.astra.sys.rmi.Exception.ServeurSqlFailure, java.sql.SQLException {
        if (dossier.getDr_facture() == 0) {

            String tvarequete = "DELETE FROM historique2 WHERE hetypeligne='TVAV' AND drcleunik=?";
            PreparedStatement pstmt = m_config.getCon().prepareStatement(tvarequete);
            pstmt.setLong(1, dossier.getDrcleunik());
            pstmt.execute();
            String requete = "SELECT henumpiece,jxcleunik,henotcpt,hetransact  FROM historique2 WHERE drcleunik=? AND hetypeligne='B' AND hedossiercourant='O'";
            pstmt = m_config.getCon().prepareStatement(requete);
            pstmt.setLong(1, dossier.getDrcleunik());
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                numpiece = result.getLong(1);
                jxcleunik = result.getInt(2);
                henocpt = result.getInt(3);
                hetransact=result.getLong(4);
            }
            pstmt.setLong(1, dossier.getDrcleunik());
            pstmt.execute();
            executeInsertWithTransaction(urcleunik);
        }
    }
    
     public void executeInsertNCService(int  urcleunik) throws java.sql.SQLException
 {
     
 } 

}
