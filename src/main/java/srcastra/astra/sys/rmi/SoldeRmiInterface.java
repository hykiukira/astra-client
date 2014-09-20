package srcastra.astra.sys.rmi;
import srcastra.astra.sys.classetransfert.compta.*;
import java.sql.*;
import srcastra.astra.sys.rmi.utils.*;
import srcastra.astra.sys.rmi.Exception.*;
import java.util.*;
import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.sys.compta.*;
import srcastra.astra.sys.classetransfert.dossier.*;
/**** @author  thomas*/
public interface SoldeRmiInterface extends java.rmi.Remote{

Object get(int urcleunik,int cleunik,int category,int periode) throws java.rmi.RemoteException, ServeurSqlFailure;

void modify(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure;

//public void insertDebit(int urcleunik, int categorie, int cleunik, double solde, int periodecompta, Payement_T pay,boolean update) throws java.rmi.RemoteException, ServeurSqlFailure,SQLException;
//public void insertCredit(int urcleunik, int categorie, int cleunik, double solde, int periodecompta, Payement_T pay,boolean update) throws java.rmi.RemoteException, ServeurSqlFailure,SQLException;
void insert(int urcleunik)throws java.rmi.RemoteException, ServeurSqlFailure,SQLException;
void delete(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure;

java.util.ArrayList getList(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure;

java.util.ArrayList getList2(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure;
public boolean checkLogin(int urcleunik,String login) throws java.rmi.RemoteException, ServeurSqlFailure;
}