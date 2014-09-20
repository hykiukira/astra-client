package srcastra.astra.sys.rmi;
import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.rmi.astraimplement;
import java.sql.*;
import srcastra.astra.sys.rmi.utils.Poolconnection;
import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;
import java.rmi.*;
import srcastra.astra.sys.rmi.Exception.ManageServSQLExcption;
import java.util.*;
import srcastra.astra.sys.compress.*;
import srcastra.astra.sys.classetransfert.Fournisseur_T;
import srcastra.astra.sys.rmi.groupe_dec.*;
import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.sys.compress.*;
import srcastra.astra.sys.rmi.socketfactory.*; 
/**
*
* @author  Thomas
*/
public class CompteCentralRmi extends java.rmi.server.UnicastRemoteObject implements GlobalRmiInterface{
astraimplement serveur;
public CompteCentralRmi(astraimplement serveur,int port) throws RemoteException {      
//super(port);
    super(port,SSLClientSocketFactory.getClientFactory(),SSLServerSocketFactory.getServeurFactory());
this.serveur=serveur;
}

public void insert(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure{
CompteCentral obj=(CompteCentral)obj1;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{

String requete="INSERT into comptecentralisateur(cour_intitule1,cour_intitule2,ce_cleunik) VALUES(? ,? ,?)";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setString(1,obj.getCour_intitule1());
pstmt.setString(2,obj.getCour_intitule2());
pstmt.setInt(3,obj.getCe_cleunik());
pstmt.execute();

} 
catch(SQLException sn){
Transaction.rollback(tmpool.getConuser());
String[] message=new String[1];
 message[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
 ManageServSQLExcption.gestion2(sn,message,tmpool.getConuser());
}

}
public void modify(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure{
CompteCentral obj=(CompteCentral)obj1;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{

String requete="UPDATE comptecentralisateur SET cour_intitule1=? ,cour_intitule2=? ,ce_cleunik=? WHERE cour_cleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setString(1,obj.getCour_intitule1());
pstmt.setString(2,obj.getCour_intitule2());
pstmt.setInt(3,obj.getCe_cleunik());
pstmt.setInt(4,obj.getCour_cleunik());
pstmt.execute();
} 
catch(SQLException sn){
Transaction.rollback(tmpool.getConuser());
String[] message=new String[1];
 message[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
 ManageServSQLExcption.gestion2(sn,message,tmpool.getConuser());
}

}
public Object get(int urcleunik,int cleunik,int update) throws java.rmi.RemoteException, ServeurSqlFailure{
CompteCentral obj=new CompteCentral();
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
String requete="SELECT * FROM comptecentralisateur WHERE cour_cleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,cleunik);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
obj.setCour_cleunik(result.getInt(1));
obj.setCour_intitule1(result.getString(2));
obj.setCour_intitule2(result.getString(3));
obj.setCe_cleunik(result.getInt(4));
}
 return obj;} 
catch(SQLException sn){
Transaction.rollback(tmpool.getConuser());
String[] message=new String[1];
 message[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
 ManageServSQLExcption.gestion2(sn,message,tmpool.getConuser());
}

 return null;
}
public Object get2(int urcleunik,int cleunik,int update) throws SQLException,RemoteException{
CompteCentral obj=null;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
String requete="SELECT * FROM comptecentralisateur WHERE ce_cleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,cleunik);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
    if(obj==null)
        obj=new CompteCentral();
obj.setCour_cleunik(result.getInt(1));
obj.setCour_intitule1(result.getString(2));
obj.setCour_intitule2(result.getString(3));
obj.setCe_cleunik(result.getInt(4));
}
 return obj;
}

public void delete(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure{
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
Transaction.begin(tmpool.getConuser());
String requete="UPDATE comptecentralisateur SET annuler=1 WHERE cour_cleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,cleunik);;

pstmt.execute();
Transaction.commit(tmpool.getConuser());
} 
catch(SQLException sn){
Transaction.rollback(tmpool.getConuser());
String[] message=new String[1];
 message[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
 ManageServSQLExcption.gestion2(sn,message,tmpool.getConuser());
}

}
public java.util.ArrayList getList(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure{
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
Transaction.begin(tmpool.getConuser());
String requete="SELECT * FROM comptecentralisateur ORDER BY cour_intitule1";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);

ArrayList array=new ArrayList();
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
CompteCentral obj=new CompteCentral();
 obj.setCour_cleunik(result.getInt(1));
obj.setCour_intitule1(result.getString(2));
obj.setCour_intitule2(result.getString(3));
obj.setCe_cleunik(result.getInt(4));

array.add(obj);}
Transaction.commit(tmpool.getConuser());

 return array;} 
catch(SQLException sn){
Transaction.rollback(tmpool.getConuser());
String[] message=new String[1];
 message[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
 ManageServSQLExcption.gestion2(sn,message,tmpool.getConuser());
}

 return null;
}
public java.util.ArrayList getList2(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure{

   CompressArray cp;
     Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
      return cp=Transaction.generecombostest3("SELECT cour_cleunik,cour_intitule1,cour_intitule2 FROM comptecentralisateur ORDER BY cour_intitule1",tmpool.getConuser());
}

public boolean checkLogin(int urcleunik, String login) throws java.rmi.RemoteException, ServeurSqlFailure {
    return false;
}
}