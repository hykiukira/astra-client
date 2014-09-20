/*
* CheckJournalCompta.java
*
* Created on 21 août 2003, 13:19
*/
package srcastra.astra.sys.compta.checkcompte;

import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.rmi.utils.*;
import srcastra.astra.sys.rmi.Exception.*;

import java.sql.*;

import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.sys.classetransfert.utils.GetId;

import java.util.*;

import srcastra.astra.sys.classetransfert.compta.*;

/**
 * @author Thomas
 */
public class CheckJournalCompta {
    astraimplement serveur;
    ParamComptableInterface paramcompt;
    Poolconnection tmpool;
    int urcleunik;
    GlobalRmiInterface entite;
    GlobalRmiInterface user;

    /**
     * Creates a new instance of CheckJournalCompta
     */
    public CheckJournalCompta(astraimplement serveur, int urcleunik) {
        this.serveur = serveur;
        this.urcleunik = urcleunik;
    }

    public void init(Poolconnection tmpool) throws java.rmi.RemoteException, ServeurSqlFailure {
        this.tmpool = tmpool;
        paramcompt = serveur.renvParamCompta(urcleunik);
        entite = serveur.renvEntiteRmiObject(urcleunik);
        user = serveur.renvUserRmiObject(urcleunik);
    }
//je laisse les champs inutiles au cas ou il serait interessant de superclasser avec une classe abstraite
//ou d'étendre une interface commune
    public int comunCase(int ce_cleunik, int urentcleunik, int category, int typecentral) throws java.rmi.RemoteException, ServeurSqlFailure, SQLException {
        return checkIfExist(ce_cleunik, urentcleunik, category, typecentral);
    }

    public int checkIfExist(int ce_cleunik, int urentcleunik, int category, int typecentral) throws java.rmi.RemoteException, ServeurSqlFailure, SQLException {
        int retval = 0;
        JournalCompta_T journal = (JournalCompta_T) paramcompt.getJournalCentral(ce_cleunik, tmpool.getUrcleunik(), 0, 0);
        if (journal == null) {
            retval = generateJournal(ce_cleunik, urentcleunik, category, typecentral);
        } else {
            retval = journal.getJota_cleunik();
        }
        return retval;
    }

    public int checkIfExist2(int ce_cleunik, int urentcleunik, int category, int typecentral) throws java.rmi.RemoteException, ServeurSqlFailure, SQLException {
        int retval = 0;
        JournalCompta_T journal = (JournalCompta_T) paramcompt.getGlobalJournal(tmpool.getUrcleunik(), typecentral, tmpool.getNumeroentite());
        if (journal == null) {
            retval = generateJournal(ce_cleunik, urentcleunik, category, typecentral);
        } else {
            retval = journal.getJota_cleunik();
        }
        return retval;
    }

    public int checkIfExist3(int ce_cleunik, int urentcleunik, int category, int jotacategorie, int jota_cleunik) throws java.rmi.RemoteException, ServeurSqlFailure, SQLException {
        int retval = 0;
        JournalCompta_T journal = (JournalCompta_T) paramcompt.getGlobalJournal(tmpool.getUrcleunik(), jotacategorie, tmpool.getNumeroentite());
        if (journal == null) {
            retval = generateJournal(ce_cleunik, urentcleunik, category, jota_cleunik);
        } else {
            retval = journal.getJota_cleunik();
        }
        return retval;
    }

    public int generateJournal(int ce_cleunik, int urentcleunik, int category, int typecentral) throws java.rmi.RemoteException, ServeurSqlFailure, SQLException {
        User tmpuser = (User) user.get(tmpool.getUrcleunik(), tmpool.getUrcle2(), 0);
        Entite tmpentite = (Entite) entite.get(tmpool.getUrcleunik(), tmpool.getNumeroentite(), 0);
        System.out.println("\n\n\nJournal de base :" + typecentral);
        JournalCompta_T base = (JournalCompta_T) paramcompt.getJournalBase(tmpool.getUrcleunik(), typecentral, tmpool.getNumeroentite());
        System.out.println("\n\n\n\n [*************]type central " + typecentral + "  category   " + category);
        System.out.println("erreur" + base.getCour_cleunik());
        base.setCour_cleunik(ce_cleunik);
        String entites = tmpentite.getEeabrev();
        String entiteusers = tmpentite.getEeabrev().substring(0, 1) + tmpuser.getUrnom().substring(0, 1);
        base.setEecleunik(tmpool.getNumeroentite());
        long num = new Long(base.getNumdoc()).longValue();
// num=num-1;
        base.setNumdoc(new Long(num).toString());
        if (category == CheckCompteCommun.ENTITE) {

            base.setJota_abrev(base.getJota_abrev().substring(0, 1) + entites);
            base.setJota_abrev2(base.getJota_abrev2().substring(0, 1) + entites);
            base.setJota_libelle(base.getJota_libelle() + " " + entites);
            base.setJota_libelle2(base.getJota_libelle2() + " " + entites);
            base.setUrcleunik(0);
        } else if (category == CheckCompteCommun.USER) {
            base.setJota_abrev(base.getJota_abrev().substring(0, 1) + entiteusers);
            base.setJota_abrev2(base.getJota_abrev2().substring(0, 1) + entiteusers);
            base.setJota_libelle2(base.getJota_libelle2() + " " + entiteusers);
            base.setJota_libelle(base.getJota_libelle() + " " + entiteusers);
            base.setUrcleunik(tmpool.getUrcle2());
        }
        return paramcompt.isertJournalComptable(base, tmpool.getUrcleunik());
//return GetId.getLastId(tmpool.getConuser());

    }


}
