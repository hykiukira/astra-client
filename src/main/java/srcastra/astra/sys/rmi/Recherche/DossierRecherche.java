/*
* DossierRecherCheByNumdos.java
*
* Created on 2 december 2002, 14:21
*/
package srcastra.astra.sys.rmi.Recherche;

import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.sys.classetransfert.clients.Clients_T;
import srcastra.astra.sys.classetransfert.dossier.Dossier_T;
import srcastra.astra.sys.classetransfert.dossier.Passager_T;
import srcastra.astra.sys.classetransfert.dossier.Payement_T;
import srcastra.astra.sys.classetransfert.utils.Date;
import srcastra.astra.sys.rmi.astraimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Thomas
 */
public class DossierRecherche implements srcastra.astra.sys.rmi.DossierSql {

    /**
     * Creates a new instance of DossierRecherCheByNumdos
     */
    public DossierRecherche(astraimplement serveur2) {
        this.serveur2 = serveur2;
    }
    protected Clients_T getDossierClientLocal(int csCleunik, Loginusers_T currentUser) {
        Clients_T Client = null;
        try {
            Client = (Clients_T) new Clients_T().selectObject(this.serveur2, currentUser, csCleunik);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Client;

    }

    protected void chargePayement(Dossier_T dossier, PreparedStatement pstmt, Connection con) throws SQLException {
        pstmt = con.prepareStatement(CHARGE_PAYEMENT);
        pstmt.setInt(1, dossier.getDrcleunik());
        pstmt.setInt(2, 6);
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        while (result.next()) {
            Payement_T pay = new Payement_T(result.getLong(1), new Date(result.getString(2)), result.getDouble(3), result.getInt(4));
            dossier.addPayement2(pay);
        }

    }

    protected ArrayList chargePassager(Dossier_T dossier, Connection con, int lmcleunik) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(this.CHARGE_PASSAGER3);
        pstmt.setInt(1, lmcleunik);
        pstmt.setInt(2, dossier.getDrcleunik());
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        ArrayList tmpPassager;
        tmpPassager = new ArrayList();
        while (result.next()) {
            Passager_T tmpPass = new Passager_T();
            tmpPass.setDr_cleunik(result.getInt(1));
            tmpPass.setPr_cleunik(result.getInt(2));
            tmpPass.setPr_nom(result.getString(3));
            tmpPass.setPr_prénom(result.getString(4));
            tmpPass.setPr_tel(result.getString(5));
            tmpPass.setPr_fax(result.getString(6));
            tmpPass.setPr_gsm(result.getString(7));
            tmpPass.setPr_email(result.getString(8));
            tmpPass.setLecleunik(result.getInt(9));
            tmpPass.setPr_datenaissance(new Date(result.getString(10)));
            tmpPass.setPr_nat(result.getInt(11));
            tmpPass.setTscleunik(result.getInt(12));
            tmpPass.setCscleunik(result.getInt(13));
            tmpPass.setPyscleunik(result.getInt(14));
            tmpPass.setPr_code_mailing(result.getString(15));
            tmpPass.setPr_bookingclass(result.getString(16));
            tmpPass.setPr_windowseat(result.getInt(17));
            tmpPass.setPr_smoking(result.getInt(18));
            tmpPass.setPr_adrese(result.getString(19));
            tmpPass.setTitrepassager(result.getString(20));
            pstmt = con.prepareStatement(CHARGE_PASSAGER_CODE);
            pstmt.setInt(1, tmpPass.getCscleunik());
            ResultSet result2 = pstmt.executeQuery();
            result2.beforeFirst();
            while (result2.next()) {
                tmpPass.setCodenom(result2.getString(1));
            }
            pstmt = con.prepareStatement(CHARGE_PASSAGER_LANGUE);
            pstmt.setInt(1, tmpPass.getLecleunik());
            result2 = pstmt.executeQuery();
            result2.beforeFirst();
            while (result2.next()) {
                tmpPass.setLanguePassager(result2.getString(1));
            }
            pstmt = con.prepareStatement(CHARGE_PASSAGER_PAYS);
            pstmt.setInt(1, tmpPass.getPyscleunik());
            pstmt.setInt(2, lmcleunik);
            result2 = pstmt.executeQuery();
            result2.beforeFirst();
            while (result2.next()) {
                tmpPass.setPaysnom(result2.getString(1));
            }
            pstmt = con.prepareStatement(CHARGE_PASSAGER_PAYS);
            pstmt.setInt(1, tmpPass.getPyscleunik());
            pstmt.setInt(2, lmcleunik);
            result2 = pstmt.executeQuery();
            result2.beforeFirst();
            while (result2.next()) {
                tmpPass.setNatnom(result2.getString(1));
            }
// tmpPass.setLanguePassager(result.getString(20));
// tmpPass.setTitrepassager(result.getString(21));
// tmpPass.setCodenom(result.getString(22));
// tmpPass.setPaysnom(result.getString(23));
// tmpPass.setNatnom(result.getString(24));
            tmpPassager.add(tmpPass);
        }
        return tmpPassager;
    }

    protected astraimplement serveur2;
}
