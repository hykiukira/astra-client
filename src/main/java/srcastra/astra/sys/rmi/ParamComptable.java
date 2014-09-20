/*
* ParamComptable.java
*
* Created on 20 mai 2003, 17:08
*/
package srcastra.astra.sys.rmi;

import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.classetransfert.compta.ExerciceCompt_T;
import srcastra.astra.sys.classetransfert.compta.JournalCompta_T;
import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.sys.classetransfert.dossier.Payement_T;
import srcastra.astra.sys.classetransfert.utils.CalculDate;
import srcastra.astra.sys.classetransfert.utils.GetId;
import srcastra.astra.sys.compta.CaisseData;
import srcastra.astra.sys.compta.CheckHistorique_T;
import srcastra.astra.sys.compta.InfoCompta;
import srcastra.astra.sys.compta.Somme;
import srcastra.astra.sys.compta.checkcompte.CheckCompteCentral;
import srcastra.astra.sys.compta.checkcompte.CheckCompteCommun;
import srcastra.astra.sys.compta.command.NcCompta;
import srcastra.astra.sys.export.Cubic_T;
import srcastra.astra.sys.export.MainExport;
import srcastra.astra.sys.export.SoldeComptException;
import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;
import srcastra.astra.sys.rmi.socketfactory.SSLClientSocketFactory;
import srcastra.astra.sys.rmi.socketfactory.SSLServerSocketFactory;
import srcastra.astra.sys.rmi.utils.Poolconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author thomas
 */
public class ParamComptable extends java.rmi.server.UnicastRemoteObject implements ParamComptableInterface {

    /**
     * Creates a new instance of ParamComptable
     */
    astraimplement m_serveur;
    Object sync;
    PeriodeRmi periode;

    public ParamComptable(astraimplement serveur, int port) throws java.rmi.RemoteException {
//super(port);
        super(port, SSLClientSocketFactory.getClientFactory(), SSLServerSocketFactory.getServeurFactory());
        m_serveur = serveur;
        sync = new Object();

    }

    public int insertExComptable(ExerciceCompt_T exercice, int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);

        String requete = "INSERT INTO `exercicecomptable` ( `exle_abrevfr` , `exle_abrevnl` , `exle_intitulefr` , `exle_intitulevnl` , `exle_annee` , `exle_datedebut` , `exle_datedefin` , `exle_nbrperiode` , `exle_courant`, exle_debut,exle_fin) VALUES ( ? , ? , ? , ? , ? , ?, ?, ?, ?,? ,? )";
        try {
            PreparedStatement pstmt = user.getConuser().prepareStatement(requete);
            pstmt.setString(1, exercice.getExle_abrevfr());
            pstmt.setString(2, exercice.getExle_abrevnl());
            pstmt.setString(3, exercice.getExle_intitulefr());
            pstmt.setString(4, exercice.getExle_intitulevnl());
            pstmt.setString(5, exercice.getExle_annee());
            pstmt.setString(6, exercice.getExle_datedebut().toString());
            pstmt.setString(7, exercice.getExle_datedefin().toString());
            pstmt.setInt(8, exercice.getExle_nbrperiode());
            pstmt.setString(9, "O");
            pstmt.setInt(10, exercice.getExle_debut());
            pstmt.setInt(11, exercice.getExle_fin());
            pstmt.execute();
            int exlecleunik = GetId.getLastId(user.getConuser());
            int per = exercice.getExle_debut();
            for (int i = 0; i < exercice.getExle_nbrperiode(); i++) {
                String periode = "INSERT INTO `periode` ( `pede_cleunik` , `pede_numper` , `pede_de` , `pede_a` , `pede_locked` , `exle_cleunik` , `pede_actif` ) VALUES ('', ?, ?, ? , ?, ?, ?);";
                pstmt = user.getConuser().prepareStatement(periode);
                srcastra.astra.sys.classetransfert.utils.Date date1 = new srcastra.astra.sys.classetransfert.utils.Date();
                srcastra.astra.sys.classetransfert.utils.Date date2 = new srcastra.astra.sys.classetransfert.utils.Date();
                CalculDate.calculPeriodeComptable(date1, date2, exercice.getExle_annee(), per, per);
                pstmt.setInt(1, i + 1);
                pstmt.setString(2, date1.toString());
                pstmt.setString(3, date2.toString());
                pstmt.setInt(4, 0);
                pstmt.setInt(5, exlecleunik);
                pstmt.setInt(6, 0);
                pstmt.execute();
                per++;
            }
            ArrayList array = getAllJournal(urcleunik);
            for (int k = 0; k < array.size(); k++) {
                JournalCompta_T journal = (JournalCompta_T) array.get(k);
                per = exercice.getExle_debut();
                for (int i = 0; i < exercice.getExle_nbrperiode(); i++) {
                    insertJournalPeriode(exercice, journal, user, per, i);
                    per++;
                }
            }
            srcastra.astra.sys.init.JournalParameter journparam = new srcastra.astra.sys.init.JournalParameter(this.m_serveur);
            journparam.updateJournalTable(urcleunik, user.getConuser());
            return exlecleunik;

        } catch (SQLException se) {
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }
    }

    public CheckHistorique_T getInforHistorique(int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        CheckHistorique_T check = new CheckHistorique_T();
        ArrayList historique = new ArrayList();
        Poolconnection tmpool = m_serveur.getConnectionAndCheck(urcleunik, true);
        try {
            String req = "SELECT h.henotcpt, h.hetransact, p.pede_numper, j.jota_abrev, h.henumpiece, h.hedatecreation, h.hetypeligne, c.ce_num, h.hecodetva, h.hevaleur, h.hevaleurbase, h.hevaleurtva,he_dc,  h.helibelle,d.dr_numdos FROM journcompta j, historique2 h LEFT  JOIN tva t ON h.tva_cleunik = t.tva_cleunik LEFT JOIN dossier d ON h.drcleunik=d.dr_cleunik, periode p, compte c WHERE c.ce_cleunik = h.ce_cleunik2 AND j.jota_cleunik = h.jxcleunik AND h.urcleunik = ? AND h.heperiode=p.pede_cleunik ORDER  BY h.hecleunik,d.dr_numdos,h.hetransact, h.henumpiece";
            PreparedStatement pstmt = tmpool.getConuser().prepareStatement(req);
            pstmt.setInt(1, tmpool.getUrcle2());
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                Object[] tab = new Object[15];
                for (int i = 0; i < tab.length; i++) {
                    if (i == 5) {
                        srcastra.astra.sys.classetransfert.utils.Date date = new srcastra.astra.sys.classetransfert.utils.Date(result.getString(i + 1));
                        tab[i] = date;
                    } else
                        tab[i] = result.getObject(i + 1);
                }
                historique.add(tab);
            }
            check.setHistorique(historique);
            check.setSolde(getSolde(tmpool));
            return check;
        } catch (SQLException se) {
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }
    }

    private ArrayList getSolde(Poolconnection tmpool) throws SQLException {
        ArrayList data = new ArrayList();
        String client = "SELECT c.csnom, s.sode_solded, s.sode_soldec FROM solde s, clients c WHERE s.sode_cat = ? AND s.sode_divcleunik = c.cscleunik";
        String fournisseur = "SELECT f.frnom1, s.sode_solded, s.sode_soldec FROM solde s, fournisseur f WHERE s.sode_cat = ? AND s.sode_divcleunik = f.frcleunik";
        String generale = "SELECT c.ce_num, s.sode_solded, s.sode_soldec FROM solde s, compte c WHERE s.sode_cat = ? AND s.sode_divcleunik = c.ce_cleunik";
        generateArray(Caisselibelle_T.CLIENT, client, tmpool, data);
        generateArray(Caisselibelle_T.FOURNISSEUR, fournisseur, tmpool, data);
        generateArray(Caisselibelle_T.GENE, generale, tmpool, data);
        return data;
    }

    private void generateArray(int arg, String requete, Poolconnection tmpool, ArrayList data) throws SQLException {
        PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
        pstmt.setInt(1, arg);
        ResultSet result = pstmt.executeQuery();
        result.beforeFirst();
        while (result.next()) {
            Object[] tab = new Object[3];
            for (int i = 0; i < tab.length; i++) {
                tab[i] = result.getObject(i + 1);
            }
            data.add(tab);
        }
    }

    public ExerciceCompt_T getExerciceCourant(int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        ExerciceCompt_T exercice = null;
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        String requete = "SELECT * FROM exercicecomptable WHERE exle_courant='O'";
        try {
            PreparedStatement pstmt = user.getConuser().prepareStatement(requete);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                exercice = new ExerciceCompt_T();
                exercice.setExle_cleunik(result.getInt(1));
                exercice.setExle_abrevfr(result.getString(2));
                exercice.setExle_abrevnl(result.getString(3));
                exercice.setExle_intitulefr(result.getString(4));
                exercice.setExle_intitulevnl(result.getString(5));
                exercice.setExle_annee(result.getString(6));
                exercice.setExle_datedebut(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(7)));
                exercice.setExle_datedefin(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(8)));
                exercice.setExle_nbrperiode(result.getInt(9));
                exercice.setExle_debut(result.getInt(11));
                exercice.setExle_fin(result.getInt(12));
            }
        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }
        return exercice;
    }

    public double getSoldeCaisse(Poolconnection tmpool, int urcleunik, int excleunik) throws java.rmi.RemoteException, ServeurSqlFailure, java.sql.SQLException {

        String requete = "SELECT sum(p.joer_solde) FROM journcomptaper p,journcompta j WHERE j.exle_cleunik=?  AND j.jota_cleunik=p.jota_cleunik AND j.jota_categorie=? AND j.urcleunik=?";
        PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
        pstmt.setInt(1, excleunik);
        pstmt.setInt(2, JOURNAL_CAISSE);
        pstmt.setInt(3, tmpool.getUrcle2());
        ResultSet result = pstmt.executeQuery();
        result.first();
        Object obj = result.getObject(1);
        if (obj != null)
            return ((Double) obj).doubleValue();
        else
            return 0;
    }

    public void setSoldeByTypePayement(CaisseData data, int excleunik, Poolconnection tmpool) throws java.rmi.RemoteException, ServeurSqlFailure, java.sql.SQLException {
        String requete = "SELECT sum(h.hevaleur) FROM historique2 h, dossier d, journcompta j WHERE j.urcleunik = ? AND j.jota_categorie = ? AND h.jxcleunik = j.jota_cleunik AND h.heperiode = ? AND j.exle_cleunik=? AND h.drcleunik = d.dr_cleunik AND h.hetypeligne = 'P'";
        PreparedStatement pstmt = tmpool.getConuser().prepareStatement(requete);
    }

    public CaisseData getListeCaisse(int urcleunik, srcastra.astra.sys.classetransfert.utils.Date date, int eecleunik, int urcleunik2) throws java.rmi.RemoteException, ServeurSqlFailure {
        int jota_cleunik = 0;
        Poolconnection tmpool = m_serveur.getConnectionAndCheck(urcleunik, true);
        try {
            ExerciceCompt_T exe = getExerciceCourant(urcleunik);
            Object[] periode = getPeriodeCompta(JOURNAL_CAISSE, urcleunik, date);
            Periode_T periodet = (Periode_T) periode[periode.length - 1];
            CompteCentral central = (CompteCentral) m_serveur.renvCompteCentralRmiObject(urcleunik).get(urcleunik, CheckCompteCentral.CAISSE, 0);
            CaisseData retval = new CaisseData();
            Solde_T soldetmp = null;
            if (m_serveur.getConfig() == null)
                m_serveur.renvConfigRmiObject(urcleunik).selectConfig(urcleunik);
            if (m_serveur.getConfig().getMultibureaux() == 0) {
                soldetmp = (Solde_T) m_serveur.renvSoldeRmiObject(urcleunik).get(urcleunik, central.getCe_cleunik(), Caisselibelle_T.GENE, ((Integer) periode[0]).intValue());
                if (m_serveur.renvConfigRmiObject(tmpool.getUrcleunik()).selectConfig(tmpool.getUrcleunik()).getCaisseparutilisateur() == 1) {
                    if (urcleunik2 == 0) {
                        Userentitecompte_T userentite = (Userentitecompte_T) m_serveur.renvUserEntiteRmiObject(urcleunik).get(urcleunik, eecleunik, CheckCompteCommun.ENTITE);
                        soldetmp = (Solde_T) m_serveur.renvSoldeRmiObject(urcleunik).get(urcleunik, userentite.getCe_cleunik(), Caisselibelle_T.GENE, ((Integer) periode[0]).intValue());
//  if(soldetmp==null)
//     return retval;
                        Somme.getSoldesByEntite(m_serveur, eecleunik, urcleunik, tmpool, retval, periodet.getPede_cleunik(), exe.getExle_cleunik(), central.getCe_cleunik(), periodet.getPede_numper(), periodet);
                    } else {
                        Userentitecompte_T userentite = (Userentitecompte_T) m_serveur.renvUserEntiteRmiObject(urcleunik).get(urcleunik, urcleunik2, CheckCompteCommun.USER);
                        soldetmp = (Solde_T) m_serveur.renvSoldeRmiObject(urcleunik).get(urcleunik, userentite.getCe_cleunik(), Caisselibelle_T.GENE, ((Integer) periode[0]).intValue());
//    if(soldetmp==null)
//      return retval;
                        Somme.getSoldeUser(m_serveur, retval, tmpool, urcleunik2, periodet.getPede_cleunik(), central.getCe_cleunik(), periodet.getPede_numper(), periodet);

                    }

                } else {
                    Userentitecompte_T userentite = (Userentitecompte_T) m_serveur.renvUserEntiteRmiObject(urcleunik).get(urcleunik, eecleunik, CheckCompteCommun.ENTITE);
                    soldetmp = (Solde_T) m_serveur.renvSoldeRmiObject(urcleunik).get(urcleunik, userentite.getCe_cleunik(), Caisselibelle_T.GENE, ((Integer) periode[0]).intValue());
//  if(soldetmp==null)
//     return retval;
                    Somme.getSoldesByEntite(m_serveur, eecleunik, urcleunik, tmpool, retval, periodet.getPede_cleunik(), exe.getExle_cleunik(), central.getCe_cleunik(), periodet.getPede_numper(), periodet);


                }


            } else {
                if (m_serveur.renvConfigRmiObject(tmpool.getUrcleunik()).selectConfig(tmpool.getUrcleunik()).getCaisseparutilisateur() == 1) {
                    if (urcleunik2 == 0) {
                        Userentitecompte_T userentite = (Userentitecompte_T) m_serveur.renvUserEntiteRmiObject(urcleunik).get(urcleunik, eecleunik, CheckCompteCommun.ENTITE);
                        soldetmp = (Solde_T) m_serveur.renvSoldeRmiObject(urcleunik).get(urcleunik, userentite.getCe_cleunik(), Caisselibelle_T.GENE, ((Integer) periode[0]).intValue());
//  if(soldetmp==null)
//     return retval;
                        Somme.getSoldesByEntite(m_serveur, eecleunik, urcleunik, tmpool, retval, periodet.getPede_cleunik(), exe.getExle_cleunik(), central.getCe_cleunik(), periodet.getPede_numper(), periodet);
                    } else {
                        Userentitecompte_T userentite = (Userentitecompte_T) m_serveur.renvUserEntiteRmiObject(urcleunik).get(urcleunik, urcleunik2, CheckCompteCommun.USER);
                        soldetmp = (Solde_T) m_serveur.renvSoldeRmiObject(urcleunik).get(urcleunik, userentite.getCe_cleunik(), Caisselibelle_T.GENE, ((Integer) periode[0]).intValue());
//         if(soldetmp==null)
//           return retval;
                        Somme.getSoldeUser(m_serveur, retval, tmpool, urcleunik2, periodet.getPede_cleunik(), central.getCe_cleunik(), periodet.getPede_numper(), periodet);

                    }

                } else {
                    Userentitecompte_T userentite = (Userentitecompte_T) m_serveur.renvUserEntiteRmiObject(urcleunik).get(urcleunik, eecleunik, CheckCompteCommun.ENTITE);
                    soldetmp = (Solde_T) m_serveur.renvSoldeRmiObject(urcleunik).get(urcleunik, userentite.getCe_cleunik(), Caisselibelle_T.GENE, ((Integer) periode[0]).intValue());
//  if(soldetmp==null)
//     return retval;
                    Somme.getSoldesByEntite(m_serveur, eecleunik, urcleunik, tmpool, retval, periodet.getPede_cleunik(), exe.getExle_cleunik(), central.getCe_cleunik(), periodet.getPede_numper(), periodet);

                }

            }
            return retval;
        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }
/*if(periode==null)
return null;
try{
Poolconnection tmpool=m_serveur.getConnectionAndCheck(urcleunik,true);
Somme.getSoldes(this.m_serveur,eecleunik,urcleunik2,tmpool,retval,((Integer)periode[0]).intValue(),exe.getExle_cleunik());
return retval;
}catch(SQLException se){
// Transaction.rollback(tmpool.getConuser());
se.printStackTrace();
System.out.println("erreur de locking"+se.getErrorCode());
ServeurSqlFailure sqe=new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
sqe.setErrorcode(se.getErrorCode());
sqe.setSqlException(se);
throw sqe;
}
String caisse="SELECT h.hedatemouv, d.dr_numdos, h.heperiode, h.helibelle, h.hevaleur FROM historique2 h, dossier d, journcompta j WHERE j.urcleunik = ? AND j.jota_categorie = ? AND h.jxcleunik = j.jota_cleunik AND h.heperiode = ? AND j.exle_cleunik=? AND h.drcleunik = d.dr_cleunik AND h.hetypeligne = 'P'";

try{
Somme.getSoldes(this.m_serveur,eecleunik,urcleunik2,tmpool,retval);
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(caisse);
pstmt.setInt(1,tmpool.getUrcle2());
pstmt.setInt(2,JOURNAL_CAISSE);
pstmt.setInt(3,((Integer)periode[0]).intValue());
pstmt.setInt(4,exe.getExle_cleunik());
ResultSet result=pstmt.executeQuery();
ResultSetMetaData meta=result.getMetaData();
ArrayList array=null;
while(result.next()){
int size=meta.getColumnCount();
Object[] data=new Object[size];
for(int i=0;i<size;i++){
if(i==0)
data[i]=new srcastra.astra.sys.classetransfert.utils.Date(result.getString(i+1));
else
data[i]=result.getObject(i+1);
}
if(array==null)
array=new ArrayList();
array.add(data);
}
retval.setCaisse(array);
return retval;
}catch(SQLException se){
// Transaction.rollback(tmpool.getConuser());
se.printStackTrace();
System.out.println("erreur de locking"+se.getErrorCode());
ServeurSqlFailure sqe=new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
sqe.setErrorcode(se.getErrorCode());
sqe.setSqlException(se);
throw sqe;
}
//return null;*/
    }

    public int isertJournalComptable(JournalCompta_T journal, int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        String journper = "INSERT INTO `journcomptaper` (`jota_cleunik` , `joer_numper` , `joer_de` , `joer_a` , `joer_locked` ) VALUES ( ?, ?, ?, ? , ?)";
        String journ = "INSERT INTO `journcompta` (  `jota_abrev` , `jota_libelle` , `jota_abrev2` , `jota_libelle2` , `jota_categorie` , `jota_ext` , `jota_beg` , `jota_end` , `jota_lock` , `jota_com` , `jota_numdoc` , `exle_cleunik` ,urcleunik,eecleunik,cour_cleunik) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        ExerciceCompt_T exercice = null;
        try {
            exercice = getExerciceCourant(urcleunik);
            journal.setJota_beg(exercice.getExle_datedebut());
            journal.setJota_end(exercice.getExle_datedefin());
            PreparedStatement pstmt = user.getConuser().prepareStatement(journ);
            pstmt.setString(1, journal.getJota_abrev());
            pstmt.setString(2, journal.getJota_libelle());
            pstmt.setString(3, journal.getJota_abrev2());
            pstmt.setString(4, journal.getJota_libelle2());
            pstmt.setInt(5, journal.getJota_categorie());
            pstmt.setString(6, journal.getJota_ext());
            pstmt.setString(7, journal.getJota_beg().toString());
            pstmt.setString(8, journal.getJota_end().toString());
            pstmt.setInt(9, 0);
            pstmt.setString(10, "");
            String numero;
            if (journal.getNumdoc() == null)
                numero = "00000";
            else
                numero = journal.getNumdoc();
            pstmt.setString(11, numero);
            pstmt.setInt(12, exercice.getExle_cleunik());
            pstmt.setInt(13, journal.getUrcleunik());
            pstmt.setInt(14, journal.getEecleunik());
            pstmt.setInt(15, journal.getCour_cleunik());
            pstmt.execute();
            journal.setJota_cleunik(GetId.getLastId(user.getConuser()));
            int per = exercice.getExle_debut();
            for (int i = 0; i < exercice.getExle_nbrperiode(); i++) {
                pstmt = user.getConuser().prepareStatement(journper);
                srcastra.astra.sys.classetransfert.utils.Date date1 = new srcastra.astra.sys.classetransfert.utils.Date();
                srcastra.astra.sys.classetransfert.utils.Date date2 = new srcastra.astra.sys.classetransfert.utils.Date();
                CalculDate.calculPeriodeComptable(date1, date2, exercice.getExle_annee(), per, per);
                pstmt.setInt(1, journal.getJota_cleunik());
                pstmt.setInt(2, i + 1);
                pstmt.setString(3, date1.toString());
                pstmt.setString(4, date2.toString());
                pstmt.setInt(5, 0);
                pstmt.execute();
                per++;
            }
            return journal.getJota_cleunik();
//return GetId.getLastId(user.getConuser());
        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }
//  return 0;
    }

    private void insertJournalPeriode(ExerciceCompt_T exercice, JournalCompta_T journal, Poolconnection tmpool, int per, int i) throws java.rmi.RemoteException, ServeurSqlFailure, SQLException {
        String journper = "INSERT INTO `journcomptaper` (`jota_cleunik` , `joer_numper` , `joer_de` , `joer_a` , `joer_locked` ) VALUES ( ?, ?, ?, ? , ?)";
        PreparedStatement pstmt = tmpool.getConuser().prepareStatement(journper);
        srcastra.astra.sys.classetransfert.utils.Date date1 = new srcastra.astra.sys.classetransfert.utils.Date();
        srcastra.astra.sys.classetransfert.utils.Date date2 = new srcastra.astra.sys.classetransfert.utils.Date();
        CalculDate.calculPeriodeComptable(date1, date2, exercice.getExle_annee(), per, per);
        pstmt.setInt(1, journal.getJota_cleunik());
        pstmt.setInt(2, i + 1);
        pstmt.setString(3, date1.toString());
        pstmt.setString(4, date2.toString());
        pstmt.setInt(5, 0);
        pstmt.execute();

    }

    public int deleteJournalCompta(int jota_cleunik, int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        try {
            if (jota_cleunik == this.JOURNAL_ACHAT || jota_cleunik == this.JOURNAL_VENTE || jota_cleunik == this.JOURNAL_CAISSE || jota_cleunik == this.JOURNAL_NCACHAT || jota_cleunik == this.JOURNAL_NCVENTE || jota_cleunik == this.JOURNAL_OCCCF)
                return 0;
            String requete = "SELECT * FROM historique2 WHERE jxcleunik=?";
            PreparedStatement pstmt = user.getConuser().prepareStatement(requete);
            pstmt.setInt(1, jota_cleunik);
            Transaction.begin(user.getConuser());
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                return 1;
            }
            requete = "DELETE FROM journcompta WHERE jota_cleunik=?";
            pstmt = user.getConuser().prepareStatement(requete);
            pstmt.setInt(1, jota_cleunik);
            pstmt.execute();
            Transaction.commit(user.getConuser());
            return 2;
        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }

    }
    
    public ArrayList getDossier(int urcleunik,int eecleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        
      
        
        
        
        String requete = "SELECT d.drcleunik,d.dr_numdos,c.csnom from dossier d,user u,clients c where d.urcleunik=u.urcleunik AND u.eecleunik=? AND dr_annull=0 and d.cscleunik=c.cscleunik ORDER BY d.dr_numdos";
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        ArrayList array = null;
        
        try {
            PreparedStatement pstmt = user.getConuser().prepareStatement(requete);
            pstmt.setInt(1, eecleunik);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            array = new ArrayList();
            while (result.next()) {
                Object[] tmp = new Object[]{result.getObject(1), result.getObject(2), result.getObject(3)};
                array.add(tmp);
            }
            return array;
        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }
        
        
    }

    public ArrayList getJournalCompta(int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        String requete = "SELECT * FROM journcompta ";
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        ArrayList array = null;
        try {
            PreparedStatement pstmt = user.getConuser().prepareStatement(requete);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            array = new ArrayList();
            while (result.next()) {
                Object[] tmp = new Object[]{result.getObject(1), result.getObject(2), result.getObject(3), result.getObject(4),
                                            result.getObject(5), result.getObject(6), result.getObject(7), result.getObject(8),
                                            result.getObject(9), result.getObject(10), result.getObject(11), result.getObject(12),
                                            result.getObject(13), result.getObject(14)};
                array.add(tmp);
            }
            return array;
        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }
    }

    public ArrayList getJournalComptaListe(int urcleunik, int type) throws java.rmi.RemoteException, ServeurSqlFailure {
        ArrayList array = null;
        try {
            String requete = "";
            Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);

            int ee_cleunik = 0;
//  if(m_serveur.getConfig().getMultibureaux()==1){
            ee_cleunik = user.getNumeroentite();
            
            if(type==20)
            {
//}
                if (user.getLmcleunik() == 1) {
                    requete = "SELECT jota_cleunik,jota_abrev,jota_libelle,jota_categorie  FROM journcompta WHERE (jota_categorie=" + ParamComptableInterface.JOURNAL_ACHAT + "  AND (eecleunik=? OR eecleunik=0)) OR (jota_categorie=" + ParamComptableInterface.JOURNAL_NCACHAT + "  AND (eecleunik=? OR eecleunik=0)) ORDER BY jota_abrev";
                } else {
                    requete = "SELECT jota_cleunik,jota_abrev2,jota_libelle2,jota_categorie  FROM journcompta WHERE (jota_categorie=" + ParamComptableInterface.JOURNAL_ACHAT + "  AND (eecleunik=? OR eecleunik=0)) OR (jota_categorie=" + ParamComptableInterface.JOURNAL_NCACHAT + " AND (eecleunik=? OR eecleunik=0)) ORDER BY jota_abrev";
                }
            }
            
            if(type==21)
            {
                if (user.getLmcleunik() == 1) {
                    requete = "SELECT jota_cleunik,jota_abrev,jota_libelle,jota_categorie  FROM journcompta WHERE (jota_categorie=" + ParamComptableInterface.JOURNAL_VENTE + "  AND (eecleunik=? OR eecleunik=0)) OR (jota_categorie=" + ParamComptableInterface.JOURNAL_NCVENTE + "  AND (eecleunik=? OR eecleunik=0)) ORDER BY jota_abrev";
                } else {
                    requete = "SELECT jota_cleunik,jota_abrev2,jota_libelle2,jota_categorie  FROM journcompta WHERE (jota_categorie=" + ParamComptableInterface.JOURNAL_VENTE + "  AND (eecleunik=? OR eecleunik=0)) OR (jota_categorie=" + ParamComptableInterface.JOURNAL_NCVENTE + " AND (eecleunik=? OR eecleunik=0)) ORDER BY jota_abrev";
                }
            }    
            PreparedStatement pstmt = user.getConuser().prepareStatement(requete);
            pstmt.setInt(1, ee_cleunik);
            pstmt.setInt(2, ee_cleunik);
            ResultSet result = pstmt.executeQuery();
            array = new ArrayList();
            while (result.next()) {
                Object[] tmp = new Object[5];
                for (int i = 0; i < 5; i++) {
                    if (i == 4) {
                        tmp[i] = new Integer(i);
                    } else if (i == 0) {
                        tmp[i] = new Integer(result.getInt(1));
                    } else {
                        tmp[i] = result.getString(i + 1);
                    }

                }
                array.add(tmp);

            }
        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }

// try{
        return array;
/*   }catch(SQLException se){
// Transaction.rollback(tmpool.getConuser());
se.printStackTrace();
System.out.println("erreur de locking"+se.getErrorCode());
ServeurSqlFailure sqe=new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
sqe.setErrorcode(se.getErrorCode());
sqe.setSqlException(se);
throw sqe;
}*/
    }

    public JournalCompta_T getGlobalJournal(int urcleunik, int typejournal, int eecleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        JournalCompta_T obj = null;
        if (m_serveur.getConfig().getMultibureaux() == 1) {
            if (typejournal != this.JOURNAL_CAISSE) {
                obj = getJournalComptaEntite(urcleunik, typejournal, eecleunik);
            } else {
                if (m_serveur.getConfig().getCaisseparutilisateur() == 1) {
                    obj = getJournalComptaUser(urcleunik, typejournal, eecleunik);

                } else {
                    obj = getJournalComptaEntite(urcleunik, typejournal, eecleunik);
                }
            }
        } else {
            obj = getJournalBase(urcleunik, typejournal, eecleunik);
        }
        return obj;
    }

    public JournalCompta_T getJournalComptaEntite(int urcleunik, int typejournal, int eecleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        String requete = "SELECT * FROM journcompta WHERE jota_categorie =? AND eecleunik=?";
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        try {
            PreparedStatement pstmt = user.getConuser().prepareStatement(requete);
            pstmt.setInt(1, typejournal);
            pstmt.setInt(2, user.getNumeroentite());
            ResultSet result = pstmt.executeQuery();
            return getJournal(result);
        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }

    }

    public JournalCompta_T getJournalBase(int urcleunik, int typejournal, int eecleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        String requete = "SELECT * FROM journcompta WHERE jota_cleunik =?";
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        try {
            PreparedStatement pstmt = user.getConuser().prepareStatement(requete);
            pstmt.setInt(1, typejournal);
            ResultSet result = pstmt.executeQuery();
            return getJournal(result);
        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }

    }

    public JournalCompta_T getJournalCentral(int cour_cleunik, int urcleunik, int typejournal, int eecleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        String requete = "SELECT * FROM journcompta WHERE cour_cleunik =?";
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        try {
            PreparedStatement pstmt = user.getConuser().prepareStatement(requete);
            pstmt.setInt(1, cour_cleunik);
            ResultSet result = pstmt.executeQuery();
            return getJournal(result);
        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }

    }

    public ArrayList getAllJournal(int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        String requete = "SELECT * FROM journcompta";
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        ArrayList retval = new ArrayList();
        try {
            PreparedStatement pstmt = user.getConuser().prepareStatement(requete);
            ResultSet result = pstmt.executeQuery();
            getJournal2(result, retval);
            return retval;
        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }

    }

    public JournalCompta_T getJournalComptaUser(int urcleunik, int typejournal, int eecleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        String requete = "SELECT * FROM journcompta WHERE jota_categorie =? AND urcleunik=?";
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        try {
            PreparedStatement pstmt = user.getConuser().prepareStatement(requete);
            pstmt.setInt(1, typejournal);
            pstmt.setInt(2, user.getUrcle2());
            ResultSet result = pstmt.executeQuery();
            return getJournal(result);
        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }
    }


    public int addJournal(int ee_cleunik, int urcleunik, int urcleunik2, int typejournal, int central, String intitule) throws java.rmi.RemoteException, ServeurSqlFailure {
        synchronized (sync) {
            JournalCompta_T obj = getJournalComptaEntite(urcleunik, typejournal, ee_cleunik);
            if (obj != null)
                return obj.getJota_cleunik();
            obj = getJournalBase(urcleunik, typejournal, 0);
            obj.setEecleunik(ee_cleunik);
            obj.setUrcleunik(urcleunik);
            obj.setJota_abrev(obj.getJota_abrev() + " " + intitule);
            obj.setJota_abrev2(obj.getJota_abrev2() + " " + intitule);
            obj.setJota_libelle(obj.getJota_libelle() + " " + intitule);
            obj.setJota_libelle2(obj.getJota_libelle2() + " " + intitule);
            obj.setCour_cleunik(central);
            isertJournalComptable(obj, urcleunik);
            return obj.getJota_cleunik();
        }
    }
/*    public int  addJournalUser(Entite entite,int urcleunik,int typejournal,int idcompte,User user) throws java.rmi.RemoteException,ServeurSqlFailure{
synchronized(sync){
JournalCompta_T obj=getJournalComptaUser(urcleunik,typejournal,entite.getEecleunik());
if(obj!=null)
return obj.getJota_cleunik();
obj=getJournalBase(urcleunik,typejournal,0);
obj.setEecleunik(0);
obj.setUrcleunik(user.getUrcleunik());
obj.setJota_abrev(obj.getJota_abrev()+" "+entite.getEeabrev()+" "+user.getUrnom());
obj.setJota_abrev2(obj.getJota_abrev2()+" "+entite.getEeabrev()+" "+user.getUrnom());
obj.setJota_libelle(obj.getJota_libelle()+" "+entite.getEeabrev()+" "+user.getUrnom());
obj.setJota_libelle2(obj.getJota_libelle2()+" "+entite.getEeabrev()+" "+user.getUrnom());
obj.setCour_cleunik(idcompte);
isertJournalComptable(obj,urcleunik);
return obj.getJota_cleunik();
}
}*/
    private JournalCompta_T getJournal(ResultSet result) throws SQLException {
        JournalCompta_T tmp = null;
        result.beforeFirst();
        while (result.next()) {
            if (tmp == null)
                tmp = new JournalCompta_T();
            tmp.setJota_cleunik(result.getInt(1));
            tmp.setJota_abrev(result.getString(2));
            tmp.setJota_libelle(result.getString(3));
            tmp.setJota_abrev2(result.getString(4));
            tmp.setJota_libelle2(result.getString(5));
            tmp.setJota_categorie(result.getInt(6));
            tmp.setJota_ext(result.getString(7));
            tmp.setJota_beg(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(8)));
            tmp.setJota_end(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(9)));
            tmp.setJota_lock(result.getInt(10));
            tmp.setJota_com(result.getString(11));
            tmp.setNumdoc(result.getString(12));
            tmp.setExle_cleunik(result.getInt(13));
            tmp.setCour_cleunik(result.getInt(14));
//tmp.setJota
        }
        return tmp;
    }

    private ArrayList getJournal2(ResultSet result, ArrayList array) throws SQLException {
        JournalCompta_T tmp = null;
        result.beforeFirst();
        while (result.next()) {
            tmp = new JournalCompta_T();
            tmp.setJota_cleunik(result.getInt(1));
            tmp.setJota_abrev(result.getString(2));
            tmp.setJota_libelle(result.getString(3));
            tmp.setJota_abrev2(result.getString(4));
            tmp.setJota_libelle2(result.getString(5));
            tmp.setJota_categorie(result.getInt(6));
            tmp.setJota_ext(result.getString(7));
            tmp.setJota_beg(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(8)));
            tmp.setJota_end(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(9)));
            tmp.setJota_lock(result.getInt(10));
            tmp.setJota_com(result.getString(11));
            tmp.setNumdoc(result.getString(12));
            tmp.setExle_cleunik(result.getInt(13));
            tmp.setCour_cleunik(result.getInt(14));
            array.add(tmp);
//tmp.setJota
        }
        return array;
    }

    public ArrayList getJournauxPeriode(int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        String requete = "SELECT j.jota_libelle,j.jota_libelle2,jp.joer_cleunik,jp.jota_cleunik,jp.joer_numper,jp.joer_de,joer_a,jp.joer_locked FROM journcompta j,journcomptaper jp WHERE j.jota_cleunik=jp.jota_cleunik ORDER BY jp.jota_cleunik,jp.joer_numper ";
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        ArrayList array = null;
        try {
            PreparedStatement pstmt = user.getConuser().prepareStatement(requete);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            array = new ArrayList();
            while (result.next()) {
                Object[] tmp = new Object[]{result.getObject(1), result.getObject(2), result.getObject(3), result.getObject(4),
                                            result.getObject(5), result.getObject(6), result.getObject(7), result.getObject(8)};
                array.add(tmp);
            }
            return array;
        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }
    }

    public ArrayList getJournauxPeriodeListe(int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        String requete = "SELECT joer_cleunik,joer_numper,joer_numper FROM journcomptaper WHERE jota_cleunik =7 ";
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        return Transaction.generecombostest3(requete, user.getConuser());

    }

    public void cloturePeriode(int cleperiode, int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        String requete = "UPDATE journcomptaper SET joer_locked=1 WHERE  joer_cleunik=?";
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        ArrayList array = null;
        try {
            PreparedStatement pstmt = user.getConuser().prepareStatement(requete);
            pstmt.setInt(1, cleperiode);
            pstmt.execute();
        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }
    }

    public void updateSoldeJournal(int jota_cleunik, int periode, Payement_T pay, Poolconnection tmpool) throws java.rmi.RemoteException, ServeurSqlFailure, SQLException {
        String select = "SELECT joer_cleunik FROM journcomptaper WHERE jota_cleunik=? AND  joer_numper =?";
        PreparedStatement pstmt = tmpool.getConuser().prepareStatement(select);
        pstmt.setInt(1, jota_cleunik);
        pstmt.setInt(2, periode);
        ResultSet result = pstmt.executeQuery();
        result.first();
        int joer_cleunik = result.getInt(1);
//result.updateDouble(1,newsolde);
        Somme.SommeCaise(joer_cleunik, tmpool, pay);

    }

    public String checkNumero(int urcleunik, int type, int urcleunik2, int eecleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        String requete;
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        ArrayList array = null;
        String docnumber = null;
        int jota_cleunik = 0;
        long numdoc = 0;
        try {

            PreparedStatement pstmt = null;
//   requete="SELECT jota_numdoc FROM journcompta WHERE jota_cleunik="+type;
            AbstractConfig_T config = m_serveur.renvConfigRmiObject(urcleunik).selectConfig(urcleunik);
            if (config.getMultibureaux() == 1) {
                if (type != this.JOURNAL_CAISSE) {
                    requete = "SELECT jota_cleunik,jota_numdoc FROM journcompta WHERE jota_categorie=? AND eecleunik=? AND urcleunik=0";
                    pstmt = user.getConuser().prepareStatement(requete);
                    pstmt.setInt(1, type);
                    pstmt.setInt(2, eecleunik);
                } else {
                    if (config.getCaisseparutilisateur() == 1) {
                        requete = "SELECT jota_cleunik,jota_numdoc FROM journcompta WHERE jota_categorie=? AND urcleunik=?";
                        pstmt = user.getConuser().prepareStatement(requete);
                        pstmt.setInt(1, type);
                        pstmt.setInt(2, urcleunik2);
                    } else {
                        requete = "SELECT jota_cleunik,jota_numdoc FROM journcompta WHERE jota_categorie=? AND eecleunik=?";
                        pstmt = user.getConuser().prepareStatement(requete);
                        pstmt.setInt(1, type);
                        pstmt.setInt(2, eecleunik);
                    }
                }

            } else {
                if (type != this.JOURNAL_CAISSE) {
                    requete = "SELECT jota_cleunik,jota_numdoc FROM journcompta WHERE jota_categorie=? AND eecleunik=? AND urcleunik=0";
                    pstmt = user.getConuser().prepareStatement(requete);
                    pstmt.setInt(1, type);
                    pstmt.setInt(2, eecleunik);
                } else {

                    if (config.getCaisseparutilisateur() == 1) {
                        requete = "SELECT jota_cleunik,jota_numdoc FROM journcompta WHERE jota_categorie=? AND urcleunik=?";
                        pstmt = user.getConuser().prepareStatement(requete);
                        pstmt.setInt(1, type);
                        pstmt.setInt(2, urcleunik2);
                    } else {
                        requete = "SELECT jota_cleunik,jota_numdoc FROM journcompta WHERE jota_categorie=? AND urcleunik=0 AND eecleunik=0";
                        pstmt = user.getConuser().prepareStatement(requete);
                        pstmt.setInt(1, type);
                    }
                }
            }
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                jota_cleunik = result.getInt(1);
                docnumber = result.getString(2);
            }
            numdoc = Long.parseLong(docnumber);
            Long newnumdoc = new Long(numdoc + 1);
            System.out.println("[**********************]journalUpdate " + jota_cleunik);
            pstmt = user.getConuser().prepareStatement("UPDATE journcompta SET jota_numdoc=? WHERE jota_cleunik=?");
            pstmt.setString(1, newnumdoc.toString());
            pstmt.setInt(2, jota_cleunik);
            pstmt.execute();

        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            Transaction.rollback(user.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        } catch (NumberFormatException nn) {
            nn.printStackTrace();
        }
        return new Long(numdoc).toString();
    }

    public String checkNumero2(int urcleunik, int jota_cleunik) throws java.rmi.RemoteException {
        String requete;
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        ArrayList array = null;
        String docnumber = null;

        long numdoc = 0;
        try {

            PreparedStatement pstmt = null;
            requete = "SELECT jota_cleunik,jota_numdoc FROM journcompta WHERE jota_cleunik=?";
            pstmt = user.getConuser().prepareStatement(requete);
            pstmt.setInt(1, jota_cleunik);


//  Transaction.begin(user.getConuser());



            synchronized (sync) {

                ResultSet result = pstmt.executeQuery();
                result.beforeFirst();
                while (result.next()) {
                    jota_cleunik = result.getInt(1);
                    docnumber = result.getString(2);
                }
                numdoc = Long.parseLong(docnumber);
                Long newnumdoc = new Long(numdoc + 1);
                pstmt = user.getConuser().prepareStatement("UPDATE journcompta SET jota_numdoc=? WHERE jota_cleunik=" + jota_cleunik);
                pstmt.setString(1, newnumdoc.toString());
                pstmt.execute();
            }
        } catch (SQLException se) {
// Transaction.rollback(tmpool.getConuser());
            Transaction.rollback(user.getConuser());
/*     se.printStackTrace();
System.out.println("erreur de locking"+se.getErrorCode());
ServeurSqlFailure sqe=new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
sqe.setErrorcode(se.getErrorCode());
sqe.setSqlException(se);
throw sqe;           */
        } catch (NumberFormatException nn) {
            nn.printStackTrace();
        }
        return new Long(numdoc).toString();
    }

    public void commitNumpiece(int urcleunik) throws java.rmi.RemoteException, ServeurSqlFailure {
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        Transaction.commit(user.getConuser());
    }

    public String setHistoriqueCpt(int urcleunik, long cledossier, long numpiece, double valeurdossier) throws java.rmi.RemoteException, ServeurSqlFailure {
        Object[] tab = getPeriodeCompta(ParamComptableInterface.JOURNAL_ACHAT, urcleunik, null);
        int periode = ((Integer) tab[0]).intValue();
        String requete = "UPDATE historique2 SET henotcpt=1,henumpiece=?,jxcleunik=?,heperiode=?,hedatemouv=? WHERE drcleunik=? AND hedossiercourant='O' AND henotcpt=0 AND (hevaleur!=0 OR hevaleurbase!=0)";
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);

        try {
            String num = "0";
            if (valeurdossier != 0d) {
                num = checkNumero(urcleunik, JOURNAL_VENTE, user.getUrcle2(), user.getNumeroentite());
            }

            System.out.println("[********\n\n\n setHistoriqueCpt " + num + " cle dossier " + cledossier);
            JournalCompta_T tmpjour = getGlobalJournal(urcleunik, JOURNAL_VENTE, user.getNumeroentite());
            PreparedStatement pstmt = user.getConuser().prepareStatement(requete);
            pstmt.setLong(1, Long.parseLong(num));
            pstmt.setInt(2, tmpjour.getJota_cleunik());
            pstmt.setLong(5, cledossier);
            pstmt.setInt(3, periode);
            pstmt.setString(4, CalculDate.getTodayDate().toString());
            pstmt.execute();
            return num;

        } catch (SQLException se) {
            Transaction.rollback(user.getConuser());
            se.printStackTrace();
            System.out.println(" " + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }
    }

    public void inverseODcccf(int urcleunik, long avionkey) throws java.rmi.RemoteException, ServeurSqlFailure, SQLException {
        Poolconnection tmpool = m_serveur.getConnectionAndCheck(urcleunik, true);
        srcastra.astra.sys.compta.Configuration config = new srcastra.astra.sys.compta.Configuration(tmpool.getConuser(), null, 0, 0, 0, 0, 0, 0, m_serveur, tmpool.getUrcle2(), 0);
        config.setCle2(urcleunik);
        new NcCompta(tmpool.getUrcleunik(), config).inverseODcccf(urcleunik, avionkey);
    }

    public InfoCompta getInfoCompta(int urcleunik, srcastra.astra.sys.classetransfert.utils.Date date) throws java.rmi.RemoteException, ServeurSqlFailure {
        String requete1 = "SELECT jota_cleunik FROM journcompta WHERE jota_vente =1";
        String requete2 = "SELECT joer_numper FROM journcomptaper  WHERE ? BETWEEN joer_de  AND joer_a AND jota_cleunik=?";
        InfoCompta info = new InfoCompta();
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        try {
            PreparedStatement pstmt = user.getConuser().prepareStatement(requete1);
            ResultSet result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                info.setJota_cleunik(result.getInt(1));
            }
            pstmt = user.getConuser().prepareStatement(requete2);
            pstmt.setString(1, date.toString());
            pstmt.setInt(2, info.getJota_cleunik());
            result = pstmt.executeQuery();
            result.beforeFirst();
            while (result.next()) {
                info.setHeperiode(result.getInt(1));
            }
            info.setExle_cleunik(getExerciceCourant(urcleunik).getExle_cleunik());
            return info;
        } catch (SQLException se) {
            Transaction.rollback(user.getConuser());
            se.printStackTrace();
            System.out.println("erreur de locking" + se.getErrorCode());
            ServeurSqlFailure sqe = new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
            sqe.setErrorcode(se.getErrorCode());
            sqe.setSqlException(se);
            throw sqe;
        }
    }

    public Object[] getPeriodeCompta(int clejournal, int urcleunik, srcastra.astra.sys.classetransfert.utils.Date datep) throws java.rmi.RemoteException, ServeurSqlFailure {
        Object[] retval;
        Poolconnection user = m_serveur.getConnectionAndCheck(urcleunik, true);
        if (periode == null)
            periode = (PeriodeRmi) m_serveur.renvPeriodeRmiObject(urcleunik);
        Periode_T periodetmp = (Periode_T) periode.getByDate(urcleunik, 0, 0, datep, user);
        retval = new Object[]{new Integer(periodetmp.getPede_cleunik()), periodetmp.getPede_de(), new Integer(periodetmp.getExle_cleunik()), periodetmp};
        return retval;
    }

    public Cubic_T exportCubic(int urcleunik, boolean bydossier) throws java.rmi.RemoteException, ServeurSqlFailure, SoldeComptException, Exception {
        Poolconnection tmpool = m_serveur.getConnectionAndCheck(urcleunik, true);
        MainExport export = null;
        String to = "info.astra@xs4all.be";
        if (m_serveur.m_to != null) {
            if (!m_serveur.m_to.equals("")) {
                to = m_serveur.m_to;
            } else {
                System.out.println("to vide");
            }
        } else {
            System.out.println("to null");
        }
        String from = "thomas.dussart@skynet.be";
        if (m_serveur.m_from != null) {
            if (!m_serveur.m_from.equals("")) {
                from = m_serveur.m_from;
            } else {
                System.out.println("from vide");
            }
        } else {
            System.out.println("from null");
        }
        String smtp = "smtp.xs4all.be";
        if (m_serveur.m_smtp != null) {
            if (!m_serveur.m_smtp.equals("")) {
                smtp = m_serveur.m_smtp;
            } else {
                System.out.println("smtp vide");
            }

        } else {
            System.out.println("smtp null");
        }

        System.out.println("smtp = " + smtp);
        System.out.println("to = " + to);
        System.out.println("from = " + from);
        export = new MainExport(tmpool.getLmcleunik(), smtp, to, from, tmpool.getConuser());
        return export.exportdData(bydossier);
    }
/*
if(datep==null || datep.isOpen() || datep.isUnknow()){
Calendar c =Calendar.getInstance();
date=new srcastra.astra.sys.classetransfert.utils.Date(c.get(Calendar.YEAR),c.get(Calendar.MONTH)+1,c.get(Calendar.DAY_OF_MONTH));}
else{
date=datep;
}
String requete="SELECT pede_numper,joer_de FROM journcomptaper  WHERE ? BETWEEN joer_de  AND joer_a AND jota_cleunik=?";
Object [] retval=null;
try{
PreparedStatement pstmt=user.getConuser().prepareStatement(requete);
pstmt.setString(1,date.toString());
pstmt.setInt(2,clejournal);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while(result.next()){
Integer i=(Integer)result.getObject(1);
srcastra.astra.sys.classetransfert.utils.Date date2=new srcastra.astra.sys.classetransfert.utils.Date (result.getString(2));
retval=new Object[]{i,date2};
}
return retval;
}catch(SQLException se){
Transaction.rollback(user.getConuser());
se.printStackTrace();
System.out.println("erreur de locking"+se.getErrorCode());
ServeurSqlFailure sqe=new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
sqe.setErrorcode(se.getErrorCode());
sqe.setSqlException(se);
throw sqe;
}

}*/
}
