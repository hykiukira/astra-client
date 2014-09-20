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
public class Traduction_compteRmi extends java.rmi.server.UnicastRemoteObject implements GlobalRmiInterface{
astraimplement serveur;
public Traduction_compteRmi(astraimplement serveur,int port) throws RemoteException {      
   // super(port);
    super(port,SSLClientSocketFactory.getClientFactory(),SSLServerSocketFactory.getServeurFactory());
this.serveur=serveur;
} 

public void insert(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure{
    Traduction_compte obj=(Traduction_compte)obj1;
    Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
    try{
    String requete="INSERT into traduction_compte(ce_cleunik,trate_traduction,lmcleunik) VALUES(? ,?, ?)";
    PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
    pstmt.setInt(1,obj.getCe_cleunik());
    pstmt.setString(2,obj.getTrate_traduction());
    pstmt.setInt(3,obj.getLmcleunik());
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
    Traduction_compte obj=(Traduction_compte)obj1;
    Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
    try{
    Transaction.begin(tmpool.getConuser());
    String requete="UPDATE traduction_compte SET trate_traduction=? ,lmcleunik=? WHERE ce_cleunik=?";
    PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setString(1,obj.getTrate_traduction());
pstmt.setInt(2,obj.getLmcleunik());
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
public Object get(int urcleunik,int cleunik,int update) throws java.rmi.RemoteException, ServeurSqlFailure{
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
ArrayList array=null;
try{
String requete="SELECT * FROM traduction_compte WHERE ce_cleunik=? ORDER BY lmcleunik";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,cleunik);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();

while (result.next()){
    Traduction_compte obj=new Traduction_compte();
    if(array==null)
        array=new ArrayList();
obj.setCe_cleunik(result.getInt(1));
obj.setTrate_traduction(result.getString(2));
obj.setLmcleunik(result.getInt(3));
array.add(obj);

}
 return array;} 
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
String requete="UPDATE traduction_compte SET annuler=1 WHERE ce_cleunik=?";
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
String requete="SELECT * FROM traduction_compte ORDER BY trate_traduction";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);

ArrayList array=new ArrayList();
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
Traduction_compte obj=new Traduction_compte();
 obj.setCe_cleunik(result.getInt(1));
obj.setTrate_traduction(result.getString(2));
obj.setLmcleunik(result.getInt(3));

array.add(obj.getTrate_traduction());}
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
      return cp=Transaction.generecombostest3("SELECT ce_cleunik,trate_traduction,lmcleunik FROM traduction_compte ORDER BY trate_traduction",tmpool.getConuser());
}

public boolean checkLogin(int urcleunik, String login) throws java.rmi.RemoteException, ServeurSqlFailure {
    return false;
}

}