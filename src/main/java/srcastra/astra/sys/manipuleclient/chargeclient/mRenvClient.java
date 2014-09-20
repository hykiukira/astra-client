/*
* ChargeClient.java
*
* Created on 12 juillet 2002, 15:48
*/
package srcastra.astra.sys.manipuleclient.chargeclient;

import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.compress.*;
import srcastra.astra.sys.rmi.utils.Poolconnection;

import java.sql.*;

import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;
import srcastra.astra.sys.classetransfert.clients.*;
//import srcastra.astra.sys.rmi.utils.Transaction;
import srcastra.astra.sys.rmi.utils.ServeurBuffer;
import srcastra.astra.sys.rmi.utils.QueryKeyGen;
import srcastra.astra.sys.Transaction;

import java.util.ArrayList;

/**
 * @author thomas
 */
public class mRenvClient implements srcastra.astra.sys.manipuleclient.ClientConstante {

    /**
     * Creates a new instance of ChargeClient
     */
    public mRenvClient(int cas, int[] listeParam, int urcleunik, int lmcleunik, String[] RequeteClient, Poolconnection tmpool, String name) {
        this.cas = cas;
        this.listeParam = listeParam;
        this.urcleunik = urcleunik;
        this.lmcleunik = lmcleunik;
        this.con = con;
        this.requeteClient = RequeteClient;
        this.tmpool = tmpool;
        this.m_name = name;
    }

    public CompressArray renvclient() throws java.rmi.RemoteException, ServeurSqlFailure {
        ServeurBuffer buf = tmpool.getBuffer();
        CompressArray returnValue;
        synchronized (buf) {
            switch (cas) {
                case RENV_ALL_CLIENTS_ORD_BY_REF:
                    String signature = QueryKeyGen.renvClient(RENV_ALL_CLIENTS_ORD_BY_REF, this.listeParam, this.urcleunik, this.lmcleunik);
                    if (buf.isValid("clientOrdByRef"))
                        return buf.getValue("clientOrdByRef");
                    else {
                        returnValue = renvClientInternal();
                        return returnValue;
                    }

                case RENV_ALL_CLIENTS_ORD_BY_NAME_WITH_ADRESS:
                    if (buf.isValid("clientOrdByNameWithAdress"))
                        return buf.getValue("clientOrdByNameWithAdress");
                    else {
                        returnValue = renvClientInternal();
                        buf.setValue("clientOrdByNameWithAdress", returnValue);
                        return returnValue;
                    }
                case RENV_ENT_CLIENTS_ORD_BY_REF:
                    returnValue = renvClientInternal();
                    buf.setValue("clientOrdByRefEntWithAdress", returnValue);
                    return returnValue;
                case RENV_ENT_CLIENTS_ORD_BY_NAME:
                    returnValue = renvClientInternal();
                    return returnValue;
                case RENV_ENT_CLIENTS_ORD_NANA:
                    returnValue = renvClientInternal();
                    return returnValue;
                case RENV_ALL_CLIENTS_ORD_BY_NAME:
                    if (buf.isValid("clientOrdByName"))
                        return buf.getValue("clientOrdByName");
                    else {
                        returnValue = renvClientInternal();
// buf.setValue ("clientOrdByName",returnValue);
//   buf.linkNewName(signature,"clientDir");
//  buf.setValue(signature, new Long(System.currentTimeMillis()));
                        return returnValue;
                    }
                case CHARGE_ALL_SOUS_CLIENT:
                    returnValue = renvClientInternal();
                    return returnValue;
                case RENV_GROUPEMENT:
                    if (buf.isValid("Groupements"))
                        return buf.getValue("Groupements");
                    else {
                        returnValue = renvClientInternal();
                        buf.setValue("Groupements", returnValue);
                        return returnValue;
                    }
                default:
                    return renvClientInternal();
            }
        }
    }

    private CompressArray renvClientInternal() throws java.rmi.RemoteException, ServeurSqlFailure {
        CompressArray returnvalue = null;
        String signature = "";
        ResultSet tmpResult;
        signature = QueryKeyGen.renvClient(cas, listeParam, urcleunik, lmcleunik);
        ServeurBuffer buf = tmpool.getBuffer();
        try {
            PreparedStatement pstmt;
            switch (cas) {
                case RENV_ALL_CLIENTS_ORD_BY_REF:
                    pstmt = tmpool.getConuser().prepareStatement(requeteClient[cas]);
                    pstmt.setInt(1, lmcleunik);
                    tmpResult = pstmt.executeQuery();
                    returnvalue = new CompressArray();
                    returnvalue.Compress_from_resulset(tmpResult);
                    break;
                case RENV_ALL_CLIENTS_ORD_BY_NAME_WITH_ADRESS:
                    pstmt = tmpool.getConuser().prepareStatement(requeteClient[cas]);
// pstmt.setInt(1,lmcleunik);
                    tmpResult = pstmt.executeQuery();
                    returnvalue = new CompressArray();
                    returnvalue.Compress_from_resulset(tmpResult);
                    break;
                case RENV_ALL_CLIENTS_ORD_BY_NAME:
                    pstmt = tmpool.getConuser().prepareStatement(requeteClient[cas]);
                    pstmt.setInt(1, lmcleunik);
                    tmpResult = pstmt.executeQuery();
                    returnvalue = new CompressArray();
                    returnvalue.Compress_from_resulset(tmpResult);
                    break;
                case RENV_ENT_CLIENTS_ORD_BY_REF:
                    pstmt = tmpool.getConuser().prepareStatement(requeteClient[cas]);
                    pstmt.setInt(1, lmcleunik);
                    pstmt.setInt(2, listeParam[0]);
                    pstmt.setString(3, this.m_name);
                    tmpResult = pstmt.executeQuery();
                    returnvalue = new CompressArray();
                    returnvalue.Compress_from_resulset2(tmpResult);
                    break;
                case RENV_ENT_CLIENTS_ORD_BY_NAME:
                    pstmt = tmpool.getConuser().prepareStatement(requeteClient[cas]);
                    pstmt.setInt(1, lmcleunik);
                    pstmt.setInt(2, listeParam[0]);
                    pstmt.setString(3, this.m_name);
                    tmpResult = pstmt.executeQuery();
                    returnvalue = new CompressArray();
                    returnvalue.Compress_from_resulset2(tmpResult);
                    break;
                case RENV_ENT_CLIENTS_ORD_NANA:
                    pstmt = tmpool.getConuser().prepareStatement("SELECT c.cscleunik,c.csdatetimecrea,c.csdatetimemodi,c.csmailprincip,c.csfax,c.cstelephonep ,c.csnom,c.csreference,c.csadresse,c.cxcleunik,c.csadresse,t.txtraduction,co.cxcode,c.annuler  FROM clients c ,traductioncodpostaux t,codepostaux co WHERE c.cxcleunik=t.cxcleunik AND co.cxcleunik=c.cxcleunik AND t.lmcleunik= ?  AND c.eecleunik=? AND c.annuler=0 AND c.csnom LIKE(CONCAT(?,'%')) ORDER BY c.csnom;");
                    pstmt.setInt(1, lmcleunik);
                    pstmt.setInt(2, listeParam[0]);
                    pstmt.setString(3, this.m_name);
                    tmpResult = pstmt.executeQuery();
                    returnvalue = new CompressArray();
                    returnvalue.Compress_from_resulset2(tmpResult);
                    break;
                case RENV_CLIENT_MULT_ADR_ORD_BY_TYPE:
                    pstmt = tmpool.getConuser().prepareStatement(requeteClient[cas]);
                    pstmt.setInt(1, lmcleunik);
                    pstmt.setInt(2, listeParam[0]);
                    tmpResult = pstmt.executeQuery();
                    returnvalue = new CompressArray();
                    returnvalue.Compress_from_resulset(tmpResult);
                    break;
                case RENV_CLIENT_MULT_ADR_ORD_BY_NAME:
                    pstmt = tmpool.getConuser().prepareStatement(requeteClient[cas]);
                    pstmt.setInt(1, lmcleunik);
                    pstmt.setInt(2, listeParam[0]);
                    tmpResult = pstmt.executeQuery();
                    returnvalue = new CompressArray();
                    returnvalue.Compress_from_resulset(tmpResult);
                    break;
                case RENV_CLIENT_FEES_ORDBY_REMISE:
                    if (lmcleunik == 1) {
                        pstmt = tmpool.getConuser().prepareStatement("SELECT clse_cleunik,`clse_libelle1` ,   `clse_fees` ,`clse_prctdos`   FROM `clients_remise2` WHERE cs_cleunik =?");
                    } else {
                        pstmt = tmpool.getConuser().prepareStatement("SELECT clse_cleunik,`clse_libelle2` ,  `clse_fees` ,`clse_prctdos`   FROM `clients_remise2` WHERE cs_cleunik =?");
                    }
                    pstmt.setInt(1, listeParam[0]);
                    tmpResult = pstmt.executeQuery();
                    returnvalue = new CompressArray();
                    returnvalue.Compress_from_resulset(tmpResult);
                    break;
                case RENV_CLIENT_FEES_ORDBY_FEES:
                    if (lmcleunik == 1) {
                        pstmt = tmpool.getConuser().prepareStatement("SELECT clse_cleunik,`clse_libelle1` ,  `clse_fees` ,`clse_prctdos`   FROM `clients_remise2` WHERE cs_cleunik =?");
                    } else {
                        pstmt = tmpool.getConuser().prepareStatement("SELECT clse_cleunik,`clse_libelle2` ,   `clse_fees` ,`clse_prctdos`  FROM `clients_remise2` WHERE cs_cleunik =?");
                    }
// pstmt = tmpool.getConuser().prepareStatement(requeteClient[cas]);
                    pstmt.setInt(1, listeParam[0]);
                    tmpResult = pstmt.executeQuery();
                    returnvalue = new CompressArray();
                    returnvalue.Compress_from_resulset(tmpResult);
                    break;
                case RENV_GROUPEMENT:
                    pstmt = tmpool.getConuser().prepareStatement(requeteClient[cas]);
                    tmpResult = pstmt.executeQuery();
                    returnvalue = new CompressArray();
                    returnvalue.Compress_from_resulset(tmpResult);
                    break;
                case CHARGE_ALL_SOUS_CLIENT:
                    pstmt = tmpool.getConuser().prepareStatement(requeteClient[cas]);
                    pstmt.setInt(1, lmcleunik);
                    pstmt.setString(2, this.m_name);
                    tmpResult = pstmt.executeQuery();
                    returnvalue = new CompressArray();
                    returnvalue.Compress_from_resulset2(tmpResult);
                    break;
            }
            if (cas != CHARGE_ALL_SOUS_CLIENT && cas != RENV_ENT_CLIENTS_ORD_BY_NAME && cas != RENV_ENT_CLIENTS_ORD_NANA) {
                if (!buf.isValid(signature)) {
                    if (cas == RENV_ALL_CLIENTS_ORD_BY_REF) {
                        buf.setValue("clientOrdByRef", returnvalue);
                        buf.linkNewName(signature, "clientDir");
                        buf.setValue(signature, new Long(System.currentTimeMillis()));
                    } else if (cas == RENV_ALL_CLIENTS_ORD_BY_NAME) {
                        buf.setValue("clientOrdByName", returnvalue);
                        buf.linkNewName(signature, "clientDir");
                        buf.setValue(signature, new Long(System.currentTimeMillis()));
                    } else {
                        buf.linkNewName(signature, "clientDir");
                        buf.setValue(signature, new Long(System.currentTimeMillis()));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
        }
        return returnvalue;

    }

    public Object chargeAllclient(int cas, astraimplement serveur, String tmp2, int tmpint) throws java.rmi.RemoteException, ServeurSqlFailure {
        ResultSet tmpResult;
        Object returnvalue = null;
        String[] tmp = null;
        try {
            PreparedStatement pstmt;
            switch (cas) {
                case CHARGE_CLIENT_ALL_CLIENT:
//Transaction.begin(tmpool.getConuser());
                    pstmt = null;
                    returnvalue = chargeAllClients(cas, pstmt, serveur, tmp2);
                    break;
            }
        } catch (SQLException se) {
            Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            throw sqe;
//throw new NullPointerException("exception test");
        }
        return returnvalue;
    }

    public Object chargeClient(astraimplement serveur) throws java.rmi.RemoteException, ServeurSqlFailure {
        ResultSet tmpResult;
        Object returnvalue = null;
        String[] tmp = null;
        try {
            PreparedStatement pstmt;
            switch (cas) {

              case CHARGE_SOUS_CLIENT:
                    pstmt = null;
                    returnvalue = chargeSousClient(pstmt, serveur);
                    break;
                case CHARGE_CLIENT:
                    pstmt = null;
                    returnvalue = chargeClient(pstmt, serveur);
                    break;
                case CHARGE_CLIENT_MULT_ADR:
                    pstmt = null;
                    returnvalue = chargeClientMultiAdr(pstmt, serveur);
                    break;
                case CHARGE_CLIENT_FEES:
                    pstmt = null;
                    returnvalue = chargeClientFees(pstmt, serveur);
                    break;
                case CHARGE_CLIENT_FOR_UPDATE:
                    Transaction.begin(tmpool.getConuser());
                    pstmt = null;
                    returnvalue = chargeClient(pstmt, serveur);
                    break;
                case CHARGE_CLIENT_MULT_ADR_FOR_UPDATE:
                    Transaction.begin(tmpool.getConuser());
                    pstmt = null;
                    returnvalue = chargeClientMultiAdr(pstmt, serveur);
                    break;
                case CHARGE_CLIENT_FEES_FOR_UPDATE:
                    Transaction.begin(tmpool.getConuser());
                    pstmt = null;
                    returnvalue = chargeClientFees(pstmt, serveur);
                    break;
                case CHARGE_CLIENT_MEMO:
                    pstmt = null;
                    returnvalue = chargeclientMemo(pstmt, serveur);
                    break;
                case CHARGE_CLIENT_MEMO_FOR_UPDATE:
                    Transaction.begin(tmpool.getConuser());
                    pstmt = null;
                    returnvalue = chargeclientMemo(pstmt, serveur);
                    break;
                case CHARGE_GROUPEMENT_FOR_UPDATE:
                    Transaction.begin(tmpool.getConuser());
                    pstmt = null;
                    chargeGroupement(pstmt, serveur);
                    break;

            }
        } catch (SQLException se) {
            Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            throw sqe;
//throw new NullPointerException("exception test");
        }
        return returnvalue;
    }

    private Sous_Client_T chargeSousClient(PreparedStatement pstmt, astraimplement serveur) throws ServeurSqlFailure, SQLException {
        pstmt = tmpool.getConuser().prepareStatement(requeteClient[cas]);
        pstmt.setInt(1, listeParam[0]);
        ResultSet tmpResult = pstmt.executeQuery();
        tmpResult.beforeFirst();
        Sous_Client_T Scli = new Sous_Client_T();
        while (tmpResult.next()) {
            Scli.setCscleunik(tmpResult.getInt(1));
            Scli.setCsdatetimecrea(tmpResult.getDate(2));
            Scli.setCsdatetimemodif(tmpResult.getDate(3));
            Scli.setCsmailprinc(tmpResult.getString(4));
            Scli.setCsfax(tmpResult.getString(5));
            Scli.setCstelephonep(tmpResult.getString(6));
            Scli.setCsnom(tmpResult.getString(7));
            Scli.setCsreference(tmpResult.getString(8));
            Scli.setCslocalite(tmpResult.getString(9));
            String[] tmp = serveur.renvNomUserPourSession(tmpool.getConuser(), tmpResult.getString(10), tmpResult.getString(11));
            Scli.setUserCreaName(tmp[0]);
            Scli.setUserModifName(tmp[1]);
        }
        return Scli;

    }

    private void chargeGroupement(PreparedStatement pstmt, astraimplement serveur) throws ServeurSqlFailure, SQLException {
        pstmt = tmpool.getConuser().prepareStatement(requeteClient[cas]);
        pstmt.setInt(1, listeParam[0]);
        ResultSet tmpResult = pstmt.executeQuery();
    }

    private ClientsRemises_T chargeClientFees(PreparedStatement pstmt, astraimplement serveur) throws ServeurSqlFailure, SQLException {
        if (cas == CHARGE_CLIENT_FEES)
            pstmt = tmpool.getConuser().prepareStatement("SELECT  `clse_cleunik`, `clse_libelle1` , `clse_libelle2` , `clse_cate` , `clse_prctdos` , `clse_fees` , `cs_cleunik` ,`urcleunikcrea`,`urcleunimodif` FROM `clients_remise2` WHERE `clse_cleunik`=?");
        else
            pstmt = tmpool.getConuser().prepareStatement("SELECT  `clse_cleunik`, `clse_libelle1` , `clse_libelle2` , `clse_cate` , `clse_prctdos` , `clse_fees` , `cs_cleunik` ,`urcleunikcrea`,`urcleunimodif` FROM `clients_remise2` WHERE `clse_cleunik`=? FOR UPDATE");
        pstmt.setInt(1, listeParam[0]);
        ResultSet tmpResult = pstmt.executeQuery();
        tmpResult.beforeFirst();
        ClientsRemises_T clirem = new ClientsRemises_T();
        while (tmpResult.next()) {
            clirem.setCrscleunik(tmpResult.getInt(1));
            clirem.setLibelleFr(tmpResult.getString(2));
            clirem.setLibelleNL(tmpResult.getString(3));
            clirem.setCategorie(tmpResult.getInt(4));
            clirem.setCsrspcdossier(tmpResult.getFloat(5));
            clirem.setCsrsfees(tmpResult.getDouble(6));
            clirem.setCscleunik(tmpResult.getInt(7));
        }
        return clirem;
    }

    private ClientsMemo_T chargeclientMemo(PreparedStatement pstmt, astraimplement serveur) throws ServeurSqlFailure, SQLException {
        pstmt = tmpool.getConuser().prepareStatement(requeteClient[cas]);
        pstmt.setInt(1, listeParam[0]);
        ResultSet tmpResult = pstmt.executeQuery();
        tmpResult.beforeFirst();
        ClientsMemo_T clientMemo = new ClientsMemo_T();
        while (tmpResult.next()) {
            clientMemo.setCscleunik(tmpResult.getInt(1));
            clientMemo.setMemo(tmpResult.getString(2));
            clientMemo.setCsdatetimecrea(tmpResult.getDate(3));
            clientMemo.setCsdatetimemodif(tmpResult.getDate(4));
            clientMemo.setSnumerosessioncrea(tmpResult.getString(5));
            clientMemo.setSnumerosessionmodif(tmpResult.getString(6));
            String[] tmp = serveur.renvNomUserPourSession(tmpool.getConuser(), clientMemo.getSnumerosessioncrea(), clientMemo.getSnumerosessionmodif());
            clientMemo.setUrnomcrea(tmp[0]);
            clientMemo.setUrnommodif(tmp[1]);
        }
        return clientMemo;

    }

    private Clients_T chargeClient(PreparedStatement pstmt, astraimplement serveur) throws ServeurSqlFailure, SQLException {
        if (cas == CHARGE_CLIENT_FOR_UPDATE) {
            pstmt = tmpool.getConuser().prepareStatement("SELECT cscleunik FROM clients WHERE cscleunik=? FOR UPDATE");
            pstmt.setInt(1, listeParam[0]);
            pstmt.executeQuery();
        }
        String date2 = "0000-00-00 00:00:00";
        
       pstmt =tmpool.getConuser().prepareStatement("SELECT lecleunik FROM clients WHERE cscleunik=?" );
       pstmt.setInt(1, listeParam[0]);
       ResultSet tmpResult = pstmt.executeQuery();
       tmpResult.beforeFirst();
       tmpResult.next();
       int l=tmpResult.getInt(1);
        
        pstmt = tmpool.getConuser().prepareStatement(requeteClient[cas]);
        pstmt.setInt(1, l);//this.lmcleunik);
        pstmt.setInt(2, l);//this.lmcleunik);
        pstmt.setInt(3, l);//this.lmcleunik);
        pstmt.setInt(4, l);//this.lmcleunik);
        pstmt.setInt(5, l);//this.lmcleunik);
        pstmt.setInt(6, listeParam[0]);
        tmpResult = pstmt.executeQuery();
        tmpResult.beforeFirst();
        Clients_T Scli2 = new Clients_T();
        while (tmpResult.next()) {
            Scli2.setCscleunik(tmpResult.getInt(1));
            Scli2.setCsgecleunik(tmpResult.getInt(2));
            Scli2.setEecleunik(tmpResult.getInt(3));
            Scli2.setCsdatetimecrea(tmpResult.getDate(4));
            Scli2.setCsdatetimemodif(tmpResult.getDate(5));
            Scli2.setCsreference(tmpResult.getString(6));
            Scli2.setTscleunik(tmpResult.getInt(7));
            Scli2.setCsnom(tmpResult.getString(8));
            Scli2.setCsadresse(tmpResult.getString(9));
            Scli2.setCxcleunik(tmpResult.getInt(10));
            Scli2.setPyscleunik(tmpResult.getInt(11));
            Scli2.setCsnom2(tmpResult.getString(12));
            Scli2.setCstelephonep(tmpResult.getString(13));
            Scli2.setCstelephones(tmpResult.getString(14));
            Scli2.setCsfax(tmpResult.getString(15));
            Scli2.setCsgsm(tmpResult.getString(16));
            Scli2.setCsmailprincip(tmpResult.getString(17));
            Scli2.setCsmailsecond(tmpResult.getString(18));
            Scli2.setCstvatype(tmpResult.getString(19));
            Scli2.setCstvanum(tmpResult.getString(20));
            Scli2.setCstvaregime(tmpResult.getInt(21));
            if (tmpResult.getString(22) != null)
                Scli2.setCsdatenaiss(new srcastra.astra.sys.classetransfert.utils.Date(tmpResult.getString(22)));
            else
                Scli2.setCsdatenaiss(new srcastra.astra.sys.classetransfert.utils.Date(date2));
            Scli2.setCscodemailing(tmpResult.getString(23));
            Scli2.setLecleunik(tmpResult.getInt(24));
            Scli2.setCsbanque(tmpResult.getString(25));
            Scli2.setCscartecredit(tmpResult.getString(26));
            Scli2.setCsanalytique(tmpResult.getInt(27));
            Scli2.setCscodecotisateur(tmpResult.getString(28));
            if (tmpResult.getString(29) != null)
                Scli2.setCsdatenaiss(new srcastra.astra.sys.classetransfert.utils.Date(tmpResult.getString(29)));
            else
                Scli2.setCsdatenaiss(new srcastra.astra.sys.classetransfert.utils.Date(date2));
            Scli2.setCsmontcotisation(tmpResult.getFloat(30));
            Scli2.setCsbloque(tmpResult.getInt(31));
            Scli2.setCsdelaipaiem(tmpResult.getInt(32));
            Scli2.setPysnom(tmpResult.getString(35));
            Scli2.setCslocalite(tmpResult.getString(36));
            Scli2.setCodenom(tmpResult.getString(37));
            Scli2.setTvaregimenom(tmpResult.getString(38));
            Scli2.setLanguenom(tmpResult.getString(39));
            Scli2.setTitrenom(tmpResult.getString(40));
            if (tmpResult.getString(42) != null)
                Scli2.setCsdatecotisation(new srcastra.astra.sys.classetransfert.utils.Date(tmpResult.getString(42)));
            else
                Scli2.setCsdatecotisation(new srcastra.astra.sys.classetransfert.utils.Date(date2));
            Scli2.setTvatype(tmpResult.getInt(43));
            Scli2.setCe_cleunik(tmpResult.getInt(44));
        }
        return Scli2;
    }

    private CompressArray chargeAllClients(int cas, PreparedStatement pstmt, astraimplement serveur, String param) throws ServeurSqlFailure, SQLException {
        System.out.println("ok je suis dans chargeAllclient");
//   if(cas==CHARGE_CLIENT_FOR_UPDATE){
//     pstmt = tmpool.getConuser().prepareStatement("SELECT cscleunik FROM clients WHERE cscleunik=? FOR UPDATE");
//   pstmt.setInt(1,listeParam[0]);
// pstmt.executeQuery();
//}
        ArrayList array = new ArrayList();
        CompressArray returnvalue = new CompressArray();
        pstmt = tmpool.getConuser().prepareStatement("SELECT * FROM clients WHERE UPPER(csnom) LIKE(UPPER('" + param + "%')) ORDER BY cscleunik");
        ResultSet tmpResult = pstmt.executeQuery();
        tmpResult.beforeFirst();
        while (tmpResult.next()) {
            Clients_T Scli2 = new Clients_T();
            Scli2.setCscleunik(tmpResult.getInt(1));
            Scli2.setCsgecleunik(tmpResult.getInt(2));
            Scli2.setEecleunik(tmpResult.getInt(3));
            Scli2.setCsdatetimecrea(tmpResult.getDate(4));
            Scli2.setCsdatetimemodif(tmpResult.getDate(5));
            Scli2.setCsreference(tmpResult.getString(6));
            Scli2.setTscleunik(tmpResult.getInt(7));
            Scli2.setCsnom(tmpResult.getString(8));
            Scli2.setCsadresse(tmpResult.getString(9));
            Scli2.setCxcleunik(tmpResult.getInt(10));
            Scli2.setPyscleunik(tmpResult.getInt(11));
            Scli2.setCsnom2(tmpResult.getString(12));
            Scli2.setCstelephonep(tmpResult.getString(13));
            Scli2.setCstelephones(tmpResult.getString(14));
            Scli2.setCsfax(tmpResult.getString(15));
            Scli2.setCsgsm(tmpResult.getString(17));
            Scli2.setCsmailprincip(tmpResult.getString(18));
            Scli2.setCsmailsecond(tmpResult.getString(19));
            Scli2.setCstvatype(tmpResult.getString(20));
            Scli2.setCstvanum(tmpResult.getString(21));
            Scli2.setCstvaregime(tmpResult.getInt(22));
            Scli2.setCsdatenaiss(new srcastra.astra.sys.classetransfert.utils.Date(tmpResult.getString(23)));
            Scli2.setCscodemailing(tmpResult.getString(24));
            Scli2.setLecleunik(tmpResult.getInt(25));
            Scli2.setCsbanque(tmpResult.getString(26));
            Scli2.setCscartecredit(tmpResult.getString(27));
            Scli2.setCsanalytique(tmpResult.getInt(28));
            Scli2.setCscodecotisateur(tmpResult.getString(29));
            Scli2.setCsmontcotisation(tmpResult.getFloat(31));
            Scli2.setCsbloque(tmpResult.getInt(32));
            Scli2.setCsdelaipaiem(tmpResult.getInt(33));
            String req1 = "SELECT cxcode FROM codepostaux WHERE cxcleunik=?";
            String req2 = "SELECT pystraduction FROM traductionpays WHERE pyscleunik=? AND lmcleunik=?";
            pstmt = tmpool.getConuser().prepareStatement(req1);
            pstmt.setInt(1, Scli2.getCxcleunik());
            ResultSet result2 = pstmt.executeQuery();
            result2.beforeFirst();
            while (result2.next()) {
                Scli2.setCodenom(result2.getString(1));
            }
            pstmt = tmpool.getConuser().prepareStatement(req2);
            pstmt.setInt(1, Scli2.getPyscleunik());
            pstmt.setInt(2, this.lmcleunik);
            result2 = pstmt.executeQuery();
            result2.beforeFirst();
            while (result2.next()) {
                Scli2.setPysnom(result2.getString(1));
            }
            array.add(Scli2);
        }
        returnvalue.Compress_From_Array(array);
        return returnvalue;
    }

    private ClientsMultiAdresses_T chargeClientMultiAdr(PreparedStatement pstmt, astraimplement serveur) throws ServeurSqlFailure, SQLException {
        pstmt = tmpool.getConuser().prepareStatement(requeteClient[cas]);
        pstmt.setInt(1, this.lmcleunik);
        pstmt.setInt(2, this.lmcleunik);
        pstmt.setInt(3, listeParam[0]);
        ResultSet tmpResult = pstmt.executeQuery();
        tmpResult.beforeFirst();
        ClientsMultiAdresses_T cliad = new ClientsMultiAdresses_T();
        while (tmpResult.next()) {
            cliad.setCsmscleunik(tmpResult.getInt(1));
            cliad.setCscleunik(tmpResult.getInt(2));
            cliad.setCsmstype(tmpResult.getString(3));
            cliad.setCsmsnom(tmpResult.getString(4));
            cliad.setCsmsadresse(tmpResult.getString(5));
            cliad.setCxcleunik(tmpResult.getInt(6));
            cliad.setPyscleunik(tmpResult.getInt(7));
            cliad.setCsmstelephone(tmpResult.getString(8));
            cliad.setCsmsfax(tmpResult.getString(9));
            cliad.setCsmsgsm(tmpResult.getString(10));
            cliad.setCsmsmail(tmpResult.getString(11));
            cliad.setSnumerosessioncrea(tmpResult.getString(12));
            cliad.setSnumerosessionmodif(tmpResult.getString(13));
            cliad.setCsmsdatetimecrea(tmpResult.getDate(14));
            cliad.setCsmsdatetimodif(tmpResult.getDate(15));
            cliad.setPysnom(tmpResult.getString(16));
            cliad.setCsmslocalite(tmpResult.getString(17));
            cliad.setCodenom(tmpResult.getString(18));
            String[] tmp = serveur.renvNomUserPourSession(tmpool.getConuser(), cliad.getSnumerosessioncrea(), cliad.getSnumerosessionmodif());
            cliad.setUrnomcrea(tmp[0]);
            cliad.setUrnommodif(tmp[1]);
        }
        return cliad;
    }

    /**
     * Getter for property time.
     *
     * @return Value of property time.
     */
    public int getTime() {
        return time;
    }

    /**
     * Setter for property time.
     *
     * @param time New value of property time.
     */
    public void setTime(int time) {
        this.time = time;
    }

    private int cas;
    int[] listeParam;
    private int urcleunik;
    private int lmcleunik;
    private Connection con;
    private astrainterface serveur;
    private String[] requeteClient;
    private Poolconnection tmpool;
    private int time;
    private String m_name;
}
