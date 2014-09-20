package srcastra.astra.sys.rmi;
import srcastra.astra.sys.classetransfert.compta.*;
import java.sql.*;
import srcastra.astra.sys.rmi.utils.*;
import srcastra.astra.sys.rmi.Exception.*;
import java.util.*;
import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.sys.compta.*;
/**** @author  thomas*/
public interface GlobalRmiInterface extends java.rmi.Remote{

Object get(int urcleunik,int cleunik,int update) throws java.rmi.RemoteException, ServeurSqlFailure;

void modify(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure;

void insert(int urcleunik,Object obj11) throws java.rmi.RemoteException, ServeurSqlFailure;

void delete(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure;

java.util.ArrayList getList(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure;

java.util.ArrayList getList2(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure;
public boolean checkLogin(int urcleunik,String login) throws java.rmi.RemoteException, ServeurSqlFailure;
}