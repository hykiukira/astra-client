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
public class UserentitecompteRmi extends java.rmi.server.UnicastRemoteObject implements UserEntiteRmiInterface{
astraimplement serveur;
public UserentitecompteRmi(astraimplement serveur,int port) throws RemoteException {      
//super(port);
super(port,SSLClientSocketFactory.getClientFactory(),SSLServerSocketFactory.getServeurFactory());
this.serveur=serveur;
}

public void insert(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure{
Userentitecompte_T obj=(Userentitecompte_T)obj1;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
Transaction.begin(tmpool.getConuser());
String requete="INSERT into userentitecompte(ureecleunik,ce_cleunik_cat,ce_cleunik) VALUES(? ,? ,?)";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,obj.getUreecleunik());
pstmt.setInt(2,obj.getCe_cleunik_cat());
pstmt.setInt(3,obj.getCe_cleunik());

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
public void modify(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure{
Userentitecompte_T obj=(Userentitecompte_T)obj1;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
Transaction.begin(tmpool.getConuser());
String requete="UPDATE userentitecompte SET ureecleunik=? ,ce_cleunik_cat=? ,ce_cleunik=? WHERE uste_cat=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,obj.getUreecleunik());
pstmt.setInt(2,obj.getCe_cleunik_cat());
pstmt.setInt(3,obj.getCe_cleunik());
pstmt.setInt(4,obj.getUste_cat());

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
public Object get(int urcleunik,int cleunik,int category) throws java.rmi.RemoteException, ServeurSqlFailure{
Userentitecompte_T obj=new Userentitecompte_T();
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
String requete="SELECT * FROM userentitecompte WHERE uste_cat=? AND ureecleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,category);
pstmt.setInt(2,cleunik);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
 obj.setUste_cat(result.getInt(1));
obj.setUreecleunik(result.getInt(2));
obj.setCe_cleunik_cat(result.getInt(3));
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
public void delete(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure{
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
Transaction.begin(tmpool.getConuser());
String requete="UPDATE userentitecompte SET annuler=1 WHERE uste_cat=?";
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
String requete="SELECT * FROM userentitecompte ORDER BY cale_lib1";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);

ArrayList array=new ArrayList();
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
Userentitecompte_T obj=new Userentitecompte_T();
 obj.setUste_cat(result.getInt(1));
obj.setUreecleunik(result.getInt(2));
obj.setCe_cleunik_cat(result.getInt(3));
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
      return cp=Transaction.generecombostest3("SELECT userentitecompte,cale_lib1,cale_lib2 FROM userentitecompte ORDER BY cale_lib1",tmpool.getConuser());
}

public boolean checkLogin(int urcleunik, String login) throws java.rmi.RemoteException, ServeurSqlFailure {
    return false;
}

}