/*
* InsertModClient.java
*
* Created on 12 juillet 2002, 17:26
*/
package srcastra.astra.sys.manipuleclient.chargeclient;

import srcastra.astra.sys.classetransfert.clients.*;

import java.sql.*;

import srcastra.astra.sys.rmi.utils.Poolconnection;
import srcastra.astra.sys.rmi.Exception.*;
import srcastra.astra.sys.rmi.utils.HashServeurBuffer;
import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.Logger;
import srcastra.astra.sys.rmi.*;

/**
 * @author thomas
 */
public class InsertModClient implements srcastra.astra.sys.manipuleclient.ClientConstante {

    /**
     * Creates a new instance of InsertModClient
     */
    astraimplement m_serveur;
    boolean importc;

    public InsertModClient(Poolconnection tmpool, String[] requeteClient, int cas, astraimplement serveur, boolean importc) {
        this.tmpool = tmpool;
        this.cas = cas;
        this.requeClient = requeteClient;
        m_serveur = serveur;
        this.importc = importc;
    }

    private void generateClientCounter(Connection con) throws SQLException {
        String requete = "INSERT into compteur values(?,?)";
        PreparedStatement pstmt = con.prepareStatement(requete);
        pstmt.setInt(1, 2);
        pstmt.setInt(2, 1);
        pstmt.execute();
    }

    private String genereRefClient(Connection con, String nomclient) throws SQLException {
        synchronized (m_serveur.getClientSyn()) {
            PreparedStatement pstmt;
            String refclient = "";
            pstmt = con.prepareStatement("SELECT comur_transact FROM compteur WHERE comur_cat=?");
            pstmt.setInt(1, 2);
            ResultSet result = pstmt.executeQuery();
            result.first();
            boolean counterIn = false;
            long numcompteur = 0;
            while (result.next()) {
                numcompteur = result.getLong(1);
                counterIn = true;
            }
            if (counterIn) {
                String numero = "";
                if (numcompteur < 10)
                    numero = "0000" + numcompteur;
                else if (numcompteur < 100)
                    numero = "000" + numcompteur;
                else if (numcompteur < 1000)
                    numero = "00" + numcompteur;
                else if (numcompteur < 10000)
                    numero = "0" + numcompteur;
                else if (numcompteur < 100000)
                    numero = "" + numcompteur;
                else if (numcompteur == 100000) {
                    numcompteur = 1;
                    numero = "00000" + 1;
                }
                pstmt = con.prepareStatement("UPDATE compteur SET comur_transact=? WHERE comur_cat=?");
                pstmt.setLong(1, (numcompteur + 1));
                pstmt.setInt(2, 2);
                pstmt.execute();
                if (nomclient.length() >= 3) {
                    String tmp = nomclient.substring(0, 3);
                    refclient = tmp + numero;
                } else {
                    refclient = refclient + numero;
                }
//return refclient;
            } else {
                generateClientCounter(con);
                refclient = genereRefClient(con, nomclient);

            }
            return refclient;
        }
    }

    public void insertCLient(java.util.ArrayList array) throws SQLException {
        if (array != null) {
            String date = "0000-00-00 00:00:00";
            for (int i = 0; i < array.size(); i++) {
                Clients_T clients = (Clients_T) array.get(i);
                PreparedStatement pstmt;
                pstmt = tmpool.getConuser().prepareStatement(requeClient[this.INSERT_CLIENT]);
                pstmt.setInt(1, clients.getCsgecleunik());
                pstmt.setInt(2, clients.getEecleunik());
                pstmt.setString(3, clients.getCsreference());
                pstmt.setInt(4, clients.getTscleunik());
                pstmt.setString(5, clients.getCsnom());
                pstmt.setString(6, clients.getCsadresse());
                pstmt.setInt(7, clients.getCxcleunik());
                pstmt.setInt(8, clients.getPyscleunik());
                pstmt.setString(9, clients.getCsnom2());
                pstmt.setString(10, clients.getCstelephonep());
                pstmt.setString(11, clients.getCstelephones());
                pstmt.setString(12, clients.getCsfax());
                pstmt.setString(13, clients.getCsgsm());
                pstmt.setString(14, clients.getCsmailprincip());
                pstmt.setString(15, clients.getCsmailsecond());
                pstmt.setString(16, clients.getCstvatype());
                pstmt.setString(17, clients.getCstvanum());
                pstmt.setInt(18, clients.getCstvaregime());
                if (clients.getCsdatenaiss() != null) date = clients.getCsdatenaiss().toString();
                pstmt.setString(19, date);
                pstmt.setString(20, clients.getCscodemailing());
                pstmt.setInt(21, clients.getLecleunik());
                pstmt.setString(22, clients.getCsbanque());
                pstmt.setString(23, clients.getCscartecredit());
                pstmt.setInt(24, clients.getCsanalytique());
                pstmt.setString(25, clients.getCscodecotisateur());
                if (clients.getCsdatecotisation() != null) date = clients.getCsdatecotisation().toString();
                pstmt.setString(26, date);
                pstmt.setFloat(27, clients.getCsmontcotisation());
                pstmt.setInt(28, clients.getCsbloque());
                pstmt.setInt(29, clients.getCsdelaipaiem());
                pstmt.setString(30, tmpool.getUrnumerosession());
                pstmt.setString(31, tmpool.getUrnumerosession());
                pstmt.setInt(32, clients.getTvatype());
                pstmt.setInt(33, clients.getCe_cleunik());
                System.out.println("insert" + " " + i + " " + clients.getCsnom());
                pstmt.execute();
                System.out.println("insered");
            }
        }
    }

    public void insertCLientArray(java.util.ArrayList array) throws SQLException {
        String requete = "INSERT INTO clients(csgecleunik,eecleunik,csdatetimecrea,csdatetimemodi,"
                + "csreference,tscleunik,csnom,csadresse,cxcleunik,pyscleunik,csnom2,cstelephonep,"
                + "cstelephones,csfax,csgsm,csmailprincip,csmailsecond,cstvatype,cstvanum,cstvaregime,"
                + "csdatenaiss,cscodemailing,lecleunik,csbanque,cscartecredit,csanalytique,"
                + "cscodecotisateur,csdatecotisation,csmontcotisation,csbloque,csdelaipaiem,"
                + "snumerosessioncrea,snumerosessionmodif, cstvatype2,ce_cleunik2,cscleunik,csmemo  ) values(?,?,NOW(),NOW(),?,?,?,?,?,?,?,?,?,?,?,?,?"
                + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        if (array != null) {
            String date = "0000-00-00 00:00:00";
            for (int i = 0; i < array.size(); i++) {
                Clients_T clients = (Clients_T) array.get(i);
                PreparedStatement pstmt;
                pstmt = tmpool.getConuser().prepareStatement(requete);
                pstmt.setInt(1, clients.getCsgecleunik());
                pstmt.setInt(2, clients.getEecleunik());
                pstmt.setString(3, clients.getCsreference());
                pstmt.setInt(4, clients.getTscleunik());
                pstmt.setString(5, clients.getCsnom());
                pstmt.setString(6, clients.getCsadresse());
                pstmt.setInt(7, clients.getCxcleunik());
                pstmt.setInt(8, clients.getPyscleunik());
                pstmt.setString(9, clients.getCsnom2());
                pstmt.setString(10, clients.getCstelephonep());
                pstmt.setString(11, clients.getCstelephones());
                pstmt.setString(12, clients.getCsfax());
                pstmt.setString(13, clients.getCsgsm());
                pstmt.setString(14, clients.getCsmailprincip());
                pstmt.setString(15, clients.getCsmailsecond());
                pstmt.setString(16, clients.getCstvatype());
                pstmt.setString(17, clients.getCstvanum());
                pstmt.setInt(18, clients.getCstvaregime());
                if (clients.getCsdatenaiss() != null) date = clients.getCsdatenaiss().toString();
                pstmt.setString(19, date);
                pstmt.setString(20, clients.getCscodemailing());
                pstmt.setInt(21, clients.getLecleunik());
                pstmt.setString(22, clients.getCsbanque());
                pstmt.setString(23, clients.getCscartecredit());
                pstmt.setInt(24, clients.getCsanalytique());
                pstmt.setString(25, clients.getCscodecotisateur());
                if (clients.getCsdatecotisation() != null) date = clients.getCsdatecotisation().toString();
                pstmt.setString(26, date);
                pstmt.setFloat(27, clients.getCsmontcotisation());
                pstmt.setInt(28, clients.getCsbloque());
                pstmt.setInt(29, clients.getCsdelaipaiem());
                pstmt.setString(30, tmpool.getUrnumerosession());
                pstmt.setString(31, tmpool.getUrnumerosession());
                pstmt.setInt(32, clients.getTvatype());
                pstmt.setInt(33, clients.getCe_cleunik());
                pstmt.setInt(34, clients.getCscleunik());
                pstmt.setString(35, clients.getCsmemo());
                System.out.println("insert" + " " + i + " " + clients.getCsnom());
                pstmt.execute();
                System.out.println("insered");
            }
        }
    }

    public int InsertObject(Object monObjet/*,HashServeurBuffer buf*/) throws java.rmi.RemoteException, ServeurSqlFailure {
        String date = "0000-00-00 00:00:00";
        int returnvalue = 0;
        ResultSet r = null;
        try {
            PreparedStatement pstmt;
            Transaction.begin(tmpool.getConuser());
            switch (cas) {
                case INSERT_CLIENT:
                    Clients_T clients = (Clients_T) monObjet;
                    if (!importc) {
                        clients.setCsreference(genereRefClient(tmpool.getConuser(), clients.getCsnom()));

                    }
                    pstmt = tmpool.getConuser().prepareStatement(requeClient[this.INSERT_CLIENT]);
                    pstmt.setInt(1, clients.getCsgecleunik());
                    pstmt.setInt(2, tmpool.getNumeroentite());
                    pstmt.setString(3, clients.getCsreference());
                    pstmt.setInt(4, clients.getTscleunik());
                    pstmt.setString(5, clients.getCsnom());
                    pstmt.setString(6, clients.getCsadresse());
                    pstmt.setInt(7, clients.getCxcleunik());
                    pstmt.setInt(8, clients.getPyscleunik());
                    pstmt.setString(9, clients.getCsnom2());
                    pstmt.setString(10, clients.getCstelephonep());
                    pstmt.setString(11, clients.getCstelephones());
                    pstmt.setString(12, clients.getCsfax());
                    pstmt.setString(13, clients.getCsgsm());
                    pstmt.setString(14, clients.getCsmailprincip());
                    pstmt.setString(15, clients.getCsmailsecond());
                    pstmt.setString(16, clients.getCstvatype());
                    pstmt.setString(17, clients.getCstvanum());
                    pstmt.setInt(18, clients.getCstvaregime());
                    if (clients.getCsdatenaiss() != null) date = clients.getCsdatenaiss().toString();
                    pstmt.setString(19, date);
                    pstmt.setString(20, clients.getCscodemailing());
                    pstmt.setInt(21, clients.getLecleunik());
                    pstmt.setString(22, clients.getCsbanque());
                    pstmt.setString(23, clients.getCscartecredit());
                    pstmt.setInt(24, clients.getCsanalytique());
                    pstmt.setString(25, clients.getCscodecotisateur());
                    if (clients.getCsdatecotisation() != null) date = clients.getCsdatecotisation().toString();
                    pstmt.setString(26, date);
                    pstmt.setFloat(27, clients.getCsmontcotisation());
                    pstmt.setInt(28, clients.getCsbloque());
                    pstmt.setInt(29, clients.getCsdelaipaiem());
                    pstmt.setString(30, tmpool.getUrnumerosession());
                    pstmt.setString(31, tmpool.getUrnumerosession());
                    pstmt.setInt(32, clients.getTvatype());
                    pstmt.setInt(33, clients.getCe_cleunik());
                    pstmt.execute();
                    break;
                case INSERT_CLIENT_MULT_ADR:
                    ClientsMultiAdresses_T clientad = (ClientsMultiAdresses_T) monObjet;
                    pstmt = tmpool.getConuser().prepareStatement(requeClient[this.INSERT_CLIENT_MULT_ADR]);
                    pstmt.setInt(1, clientad.getCscleunik());
                    pstmt.setString(2, clientad.getCsmstype());
                    pstmt.setString(3, clientad.getCsmsnom());
                    pstmt.setString(4, clientad.getCsmsadresse());
                    pstmt.setInt(5, clientad.getCxcleunik());
                    pstmt.setString(6, clientad.getCsmslocalite());
                    pstmt.setInt(7, clientad.getPyscleunik());
                    pstmt.setString(8, clientad.getCsmstelephone());
                    pstmt.setString(9, clientad.getCsmsfax());
                    pstmt.setString(10, clientad.getCsmsgsm());
                    pstmt.setString(11, clientad.getCsmsmail());
                    pstmt.setString(12, tmpool.getUrnumerosession());
                    pstmt.setString(13, tmpool.getUrnumerosession());
                    pstmt.execute();
                    break;
                case INSERT_CLIENT_FEES:
                    ClientsRemises_T clirem = (ClientsRemises_T) monObjet;
                    pstmt = tmpool.getConuser().prepareStatement("INSERT INTO `clients_remise2` ( `clse_libelle1` , `clse_libelle2` , `clse_cate` , `clse_prctdos` , `clse_fees` , `cs_cleunik` ,`urcleunikcrea`,`urcleunimodif`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                    pstmt.setString(1, clirem.getLibelleFr());
                    pstmt.setString(2, clirem.getLibelleNL());
                    pstmt.setInt(3, clirem.getCategorie());
                    pstmt.setFloat(4, clirem.getCsrspcdossier());
                    pstmt.setDouble(5, clirem.getCsrsfees());
                    pstmt.setLong(6, clirem.getCscleunik());
                    pstmt.setInt(7, tmpool.getUrcle2());
                    pstmt.setInt(8, tmpool.getUrcle2());
                    pstmt.execute();
                    break;
                case INSERT_GROUPEMENT:
                    ClientsGroupe_T clientGroup = (ClientsGroupe_T) monObjet;
                    pstmt = tmpool.getConuser().prepareStatement(requeClient[cas]);
                    pstmt.setString(1, clientGroup.getCsgenom());
                    pstmt.setString(2, tmpool.getUrnumerosession());
                    pstmt.setString(3, tmpool.getUrnumerosession());
                    pstmt.execute();
                    break;
            }
// pstmt.execute();
            pstmt = tmpool.getConuser().prepareStatement("select LAST_INSERT_ID();");
            r = pstmt.executeQuery();
            r.first();
            returnvalue = r.getInt(1);
            r.close();
            pstmt.close();
            synchronized (tmpool.getBuffer()) {
                Transaction.commit(tmpool.getConuser());
                tmpool.getBuffer().invalidateBuffer(bufferString);
            }
        } catch (SQLException e) {
            Transaction.rollback(tmpool.getConuser());
            e.printStackTrace();
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setSqlException(e);
            sqe.setErrorcode(e.getErrorCode());
            throw sqe;
        }
        return returnvalue;
    }

    public void modifyObject(Object monObjet) throws java.rmi.RemoteException, ServeurSqlFailure {
        String date = "0000-00-00 00:00:00";
        try {
            PreparedStatement pstmt;
            switch (cas) {
                case UPDATE_CLIENT:
                    Clients_T clients = (Clients_T) monObjet;
                    pstmt = tmpool.getConuser().prepareStatement(requeClient[cas]);
                    pstmt.setInt(1, clients.getEecleunik());
                    pstmt.setString(2, clients.getCsreference());
                    pstmt.setInt(3, clients.getTscleunik());
                    pstmt.setString(4, clients.getCsnom());
                    pstmt.setString(5, clients.getCsadresse());
                    pstmt.setInt(6, clients.getCxcleunik());
                    Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "-------pays cle unik: " + clients.getPyscleunik());
                    pstmt.setInt(7, clients.getPyscleunik());
                    pstmt.setString(8, clients.getCsnom2());
                    pstmt.setString(9, clients.getCstelephonep());
                    pstmt.setString(10, clients.getCstelephones());
                    pstmt.setString(11, clients.getCsfax());
                    pstmt.setString(12, clients.getCsgsm());
                    pstmt.setString(13, clients.getCsmailprincip());
                    pstmt.setString(14, clients.getCsmailsecond());
                    pstmt.setString(15, clients.getCstvatype());
                    pstmt.setString(16, clients.getCstvanum());
                    pstmt.setInt(17, clients.getCstvaregime());
                    if (clients.getCsdatenaiss() != null) date = clients.getCsdatenaiss().toString();
                    pstmt.setString(18, date);
                    pstmt.setString(19, clients.getCscodemailing());
                    pstmt.setInt(20, clients.getLecleunik());
                    pstmt.setString(21, clients.getCsbanque());
                    pstmt.setString(22, clients.getCscartecredit());
                    pstmt.setInt(23, clients.getCsanalytique());
                    pstmt.setString(24, clients.getCscodecotisateur());
                    if (clients.getCsdatecotisation() != null) date = clients.getCsdatecotisation().toString();
                    pstmt.setString(25, date);
                    pstmt.setFloat(26, clients.getCsmontcotisation());
                    pstmt.setInt(27, clients.getCsbloque());
                    pstmt.setInt(28, clients.getCsdelaipaiem());
                    pstmt.setString(29, tmpool.getUrnumerosession());
                    pstmt.setInt(30, clients.getCsgecleunik());
                    pstmt.setInt(31, clients.getTvatype());
                    pstmt.setInt(32, clients.getCe_cleunik());
                    pstmt.setInt(33, clients.getCscleunik());
                    pstmt.execute();
//Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"-------Metadate (7): "+tmp.getMetaData().getColumnLabel(7)+" : "+tmp.getMetaData().getColumnTypeName(7)+" "+tmp.getMetaData().getColumnName(7));
//Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"Query pstmt: "+pstmt.toString());
//Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"Query pstmt: "+tmp.toString());
                    break;
                case UPDATE_CLIENT_ADR:
                    ClientsMultiAdresses_T clientad = (ClientsMultiAdresses_T) monObjet;
                    pstmt = tmpool.getConuser().prepareStatement(requeClient[cas]);
                    pstmt.setString(1, clientad.getCsmstype());
                    pstmt.setString(2, clientad.getCsmsnom());
                    pstmt.setString(3, clientad.getCsmsadresse());
                    pstmt.setInt(4, clientad.getCxcleunik());
                    pstmt.setString(5, clientad.getCsmslocalite());
                    pstmt.setInt(6, clientad.getPyscleunik());
                    pstmt.setString(7, clientad.getCsmstelephone());
                    pstmt.setString(8, clientad.getCsmsfax());
                    pstmt.setString(9, clientad.getCsmsgsm());
                    pstmt.setString(10, clientad.getCsmsmail());
                    pstmt.setString(11, tmpool.getUrnumerosession());
                    pstmt.setInt(12, clientad.getCsmscleunik());
                    pstmt.execute();
                    break;
                case UPDATE_CLIENT_FEES:
                    ClientsRemises_T clirem = (ClientsRemises_T) monObjet;
                    pstmt = tmpool.getConuser().prepareStatement("UPDATE `clients_remise2` SET clse_libelle1 = ?,`clse_libelle2` = ?,`clse_cate` = ?,`clse_prctdos` = ?,`clse_fees` = ?,`urcleunimodif` = ? WHERE `clse_cleunik` = ?");
                    pstmt.setString(1, clirem.getLibelleFr());
                    pstmt.setString(2, clirem.getLibelleNL());
                    pstmt.setInt(3, clirem.getCategorie());
                    pstmt.setFloat(4, clirem.getCsrspcdossier());
                    pstmt.setDouble(5, clirem.getCsrsfees());
                    pstmt.setInt(6, tmpool.getUrcle2());
                    pstmt.setLong(7, clirem.getCrscleunik());
                    pstmt.execute();
                    break;
                case UPDATE_CLIENT_MEMO:
                    ClientsMemo_T cliMemo = (ClientsMemo_T) monObjet;
                    pstmt = tmpool.getConuser().prepareStatement(requeClient[cas]);
                    pstmt.setString(1, cliMemo.getMemo());
                    pstmt.setInt(2, cliMemo.getCscleunik());
                    pstmt.execute();
                    break;
                case UPDATE_GROUPEMENT:
                    cliGroup = (ClientsGroupe_T) monObjet;
                    pstmt = tmpool.getConuser().prepareStatement(requeClient[cas]);
                    pstmt.setString(1, cliGroup.getCsgenom());
                    pstmt.setString(2, tmpool.getUrnumerosession());
                    pstmt.setInt(3, cliGroup.getCsgecleunik());
                    pstmt.execute();
                    break;
            }
            synchronized (tmpool.getBuffer()) {
                Transaction.commit(tmpool.getConuser());
                tmpool.getBuffer().invalidateBuffer(bufferString);
            }
        } catch (SQLException e) {
            Transaction.rollback(tmpool.getConuser());
            e.printStackTrace();
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setSqlException(e);
            sqe.setErrorcode(e.getErrorCode());
            throw sqe;
        }

    }

    private Poolconnection tmpool;
    private String[] requeClient;
    private int cas;
    private ClientsGroupe_T cliGroup;
}
