/*

 * ParamComptableInterface.java

 *

 * Created on 21 mai 2003, 10:27

 */



package srcastra.astra.sys.rmi;

import srcastra.astra.sys.classetransfert.compta.*;

import java.sql.*;

import srcastra.astra.sys.rmi.utils.*;

import srcastra.astra.sys.rmi.Exception.*;

import java.util.*;

import srcastra.astra.sys.compta.*;

import srcastra.astra.sys.classetransfert.configuration.*;

import srcastra.astra.sys.classetransfert.dossier.*;

import srcastra.astra.sys.export.*;

/**

 *

 * @author  thomas

 */

public interface ParamComptableInterface extends java.rmi.Remote{   

    public int insertExComptable(ExerciceCompt_T exercice,int urcleunik) throws java.rmi.RemoteException,ServeurSqlFailure;

    public ExerciceCompt_T getExerciceCourant(int urcleunik) throws java.rmi.RemoteException,ServeurSqlFailure;

    int isertJournalComptable(JournalCompta_T journal,int urcleunik) throws java.rmi.RemoteException,ServeurSqlFailure;

    ArrayList getJournalCompta(int urcleunik)  throws java.rmi.RemoteException,ServeurSqlFailure;

    ArrayList getJournauxPeriode(int urcleunik) throws java.rmi.RemoteException,ServeurSqlFailure;

    void cloturePeriode(int cleperiode,int urcleunik) throws java.rmi.RemoteException,ServeurSqlFailure;

    public String  checkNumero(int urcleunik,int type,int urcleunik2,int eecleunik) throws java.rmi.RemoteException,ServeurSqlFailure;

    String setHistoriqueCpt(int urcleunik,long cledossier,long numpiece,double valeurdossier) throws java.rmi.RemoteException,ServeurSqlFailure;

    InfoCompta getInfoCompta(int urcleunik,srcastra.astra.sys.classetransfert.utils.Date date) throws java.rmi.RemoteException, ServeurSqlFailure;

    public void commitNumpiece(int urcleunik)throws java.rmi.RemoteException,ServeurSqlFailure;

   // public int  addJournalEntite(Entite entite,int urcleunik,int typejournal,int idcompte) throws java.rmi.RemoteException,ServeurSqlFailure;

   // public int  addJournalUser(Entite entite,int urcleunik,int typejournal,int idcompte,User user) throws java.rmi.RemoteException,ServeurSqlFailure;

    public JournalCompta_T getGlobalJournal(int urcleunik,int typejournal,int eecleunik)  throws java.rmi.RemoteException,ServeurSqlFailure;

    int deleteJournalCompta(int jota_cleunik,int urcleunik) throws java.rmi.RemoteException,ServeurSqlFailure;

    public ArrayList getJournalComptaListe(int urcleunik,int type)  throws java.rmi.RemoteException,ServeurSqlFailure;

    public ArrayList getDossier(int urcleunik,int eecleunik) throws java.rmi.RemoteException, ServeurSqlFailure;
    
    
    public ArrayList getJournauxPeriodeListe(int urcleunik) throws java.rmi.RemoteException,ServeurSqlFailure;

    public Object[] getPeriodeCompta(int clejournal,int urcleunik,srcastra.astra.sys.classetransfert.utils.Date datep)throws java.rmi.RemoteException, ServeurSqlFailure;

    public int  addJournal(int ee_cleunik,int urcleunik,int urcleunik2,int typejournal,int central,String intitule) throws java.rmi.RemoteException,ServeurSqlFailure;

    public JournalCompta_T getJournalCentral(int cour_cleunik,int urcleunik,int typejournal,int eecleunik)  throws java.rmi.RemoteException,ServeurSqlFailure;

    JournalCompta_T getJournalBase(int urcleunik,int typejournal,int eecleunik)  throws java.rmi.RemoteException,ServeurSqlFailure;

    void updateSoldeJournal(int jota_cleunik,int periode,Payement_T pay,Poolconnection tmpool)throws java.rmi.RemoteException,ServeurSqlFailure,SQLException;
    public CaisseData getListeCaisse(int urcleunik,srcastra.astra.sys.classetransfert.utils.Date date,int eecleunik,int urcleunik2) throws java.rmi.RemoteException,ServeurSqlFailure;
    public CheckHistorique_T getInforHistorique(int urcleunik)throws java.rmi.RemoteException,ServeurSqlFailure;
    Cubic_T exportCubic(int urcleunik,boolean bydossier)throws java.rmi.RemoteException, ServeurSqlFailure,SoldeComptException,Exception;

public final static int JOURNAL_VENTE=21;

public final static int JOURNAL_NCVENTE=18;

public final static int JOURNAL_ACHAT=20;

public final static int JOURNAL_NCACHAT=19;

public final static int JOURNAL_CAISSE=22;

public final static int JOURNAL_BANK=23;

public final static int JOURNAL_OCCCF=24;

}

