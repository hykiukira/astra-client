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
/**
*
* @author  Thomas
*/
public class SouscomtpecentralRmi extends java.rmi.server.UnicastRemoteObject implements GlobalRmiInterface{
astraimplement serveur;
public SouscomtpecentralRmi(astraimplement serveur) throws RemoteException {      
this.serveur=serveur;
}

public void insert(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure{
Souscomtpecentral obj=(Souscomtpecentral)obj1;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
    String requete="INSERT into souscomtpecentral(soal_intitule1,soal_intitule2,cour_cleunik) VALUES(? ,? ,?)";
    PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
    pstmt.setString(1,obj.getSoal_intitule1());
    pstmt.setString(2,obj.getSoal_intitule2());
    pstmt.setInt(3,obj.getCour_cleunik());
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
Souscomtpecentral obj=(Souscomtpecentral)obj1;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
Transaction.begin(tmpool.getConuser());
String requete="UPDATE souscomtpecentral SET soal_intitule1=? ,soal_intitule2=? ,cour_cleunik=? WHERE soal_cleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setString(1,obj.getSoal_intitule1());
pstmt.setString(2,obj.getSoal_intitule2());
pstmt.setInt(3,obj.getCour_cleunik());
pstmt.setInt(4,obj.getSoal_cleunik());
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
Souscomtpecentral obj=new Souscomtpecentral();
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
Transaction.begin(tmpool.getConuser());
String requete="SELECT * FROM souscomtpecentral WHERE soal_cleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,cleunik);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
 obj.setSoal_cleunik(result.getInt(1));
obj.setSoal_intitule1(result.getString(2));
obj.setSoal_intitule2(result.getString(3));
obj.setCour_cleunik(result.getInt(4));

}
Transaction.commit(tmpool.getConuser());

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
String requete="UPDATE souscomtpecentral SET annuler=1 WHERE soal_cleunik=?";
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
String requete="SELECT * FROM souscomtpecentral ORDER BY soal_intitule1";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);

ArrayList array=new ArrayList();
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
Souscomtpecentral obj=new Souscomtpecentral();
 obj.setSoal_cleunik(result.getInt(1));
obj.setSoal_intitule1(result.getString(2));
obj.setSoal_intitule2(result.getString(3));
obj.setCour_cleunik(result.getInt(4));
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
      return cp=Transaction.generecombostest3("SELECT soal_cleunik,soal_intitule1,soal_intitule2 FROM souscomtpecentral ORDER BY soal_intitule1",tmpool.getConuser());
}

public boolean checkLogin(int urcleunik, String login) throws java.rmi.RemoteException, ServeurSqlFailure {
    return false;
}

}