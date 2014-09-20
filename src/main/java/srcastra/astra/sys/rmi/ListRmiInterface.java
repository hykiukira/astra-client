/*

 * ListRmiInterface.java

 *

 * Created on 10 novembre 2003, 15:47

 */



package srcastra.astra.sys.rmi;

import java.util.*;

import srcastra.astra.sys.rmi.Exception.*;

import srcastra.astra.gui.list.*;

/**
 
 *

 * @author  Thomas

 */

public interface ListRmiInterface extends java.rmi.Remote{

    ArrayList getDossierList(int urcleunik,String datedepbeg,String datedebend,String creationbeg,String creationend,String solde,String facture,String clientc,String clientf,String fournisseur,String passager,String destination,String po,String numdos,String entite,String groupprod,String produit,String bdc,int user) throws java.rmi.RemoteException;
    ArrayList getMailOut(int urcleunik, String dateretbeg, String dateretend) throws java.rmi.RemoteException;
    CaisseList_T getCaisseList(int urCleunik,srcastra.astra.sys.classetransfert.utils.Date datedeb,srcastra.astra.sys.classetransfert.utils.Date dateend,int eecleunik,int urcle) throws java.rmi.RemoteException,ServeurSqlFailure;
    Hashtable getBspt(int urCleunik,srcastra.astra.sys.classetransfert.utils.Date startDate,srcastra.astra.sys.classetransfert.utils.Date endDate)throws java.rmi.RemoteException,ServeurSqlFailure;
}

