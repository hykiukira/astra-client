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
public class EntiteRmi extends java.rmi.server.UnicastRemoteObject implements GlobalRmiInterface{
astraimplement serveur;
public EntiteRmi(astraimplement serveur,int port) throws RemoteException {      
//super(port);
  super(port,SSLClientSocketFactory.getClientFactory(),SSLServerSocketFactory.getServeurFactory());
this.serveur=serveur;
}

public void insert(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure{
Entite obj=(Entite)obj1;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
Transaction.begin(tmpool.getConuser());
String requete="INSERT into entite(eenom,eeadresse,eetelephonep,eetelephones,eefax,eemail,eeactif,eedatetimecrea,eedatetimemodi,lmcleunik,cxcleunik,secleunik,eeabrev,eecompteur,eetva,eecomptebancaire,eesmtp) VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?,?)";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setString(1,obj.getEenom());
pstmt.setString(2,obj.getEeadresse());
pstmt.setString(3,obj.getEetelephonep());
pstmt.setString(4,obj.getEetelephones());
pstmt.setString(5,obj.getEefax());
pstmt.setString(6,obj.getEemail());
pstmt.setInt(7,obj.getEeactif());
pstmt.setString(8,"NOW()");
pstmt.setString(9,"NOW()");
pstmt.setInt(10,obj.getLmcleunik());
pstmt.setInt(11,obj.getCxcleunik());
pstmt.setInt(12,obj.getSecleunik());
pstmt.setString(13,obj.getEeabrev());
pstmt.setLong(14,obj.getEecompteur());
pstmt.setString(15,obj.getEetva());
pstmt.setString(16,obj.getEecomptebancaire());
pstmt.setString(17,obj.getEesmtp());

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
Entite obj=(Entite)obj1;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
Transaction.begin(tmpool.getConuser());
String requete="UPDATE entite SET eenom=? ,eeadresse=? ,eetelephonep=? ,eetelephones=? ,eefax=? ,eemail=? ,eeactif=?  ,eedatetimemodi=? ,lmcleunik=? ,cxcleunik=? ,secleunik=? ,eeabrev=? ,eecompteur=? ,eetva=? ,eecomptebancaire=?,eesmtp=? WHERE eecleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setString(1,obj.getEenom());
pstmt.setString(2,obj.getEeadresse());
pstmt.setString(3,obj.getEetelephonep());
pstmt.setString(4,obj.getEetelephones());
pstmt.setString(5,obj.getEefax());
pstmt.setString(6,obj.getEemail());
pstmt.setInt(7,obj.getEeactif());
pstmt.setString(8,"NOW()");
pstmt.setInt(9,obj.getLmcleunik());
pstmt.setInt(10,obj.getCxcleunik());
pstmt.setInt(11,obj.getSecleunik());
pstmt.setString(12,obj.getEeabrev());
pstmt.setLong(13,obj.getEecompteur());
pstmt.setString(14,obj.getEetva());
pstmt.setString(15,obj.getEecomptebancaire());
pstmt.setString(16,obj.getEesmtp());
pstmt.setInt(17,obj.getEecleunik());
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
Entite obj=new Entite();
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
String requete="SELECT * FROM entite WHERE eecleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,cleunik);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
 obj.setEecleunik(result.getInt(1));
obj.setEenom(result.getString(2));
obj.setEeadresse(result.getString(3));
obj.setEetelephonep(result.getString(4));
obj.setEetelephones(result.getString(5));
obj.setEefax(result.getString(6));
obj.setEemail(result.getString(7));
obj.setEeactif(result.getInt(8));
obj.setEedatetimecrea(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(9)));
obj.setEedatetimemodi(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(10)));
obj.setLmcleunik(result.getInt(11));
obj.setCxcleunik(result.getInt(12));
obj.setSecleunik(result.getInt(13));
obj.setEeabrev(result.getString(14));
obj.setEecompteur(result.getLong(15));
obj.setEetva(result.getString(16));
obj.setEecomptebancaire(result.getString(17));

obj.setEesmtp(result.getString(19));

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
String requete="UPDATE entite SET annuler=1 WHERE eecleunik=?";
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
String requete="SELECT * FROM entite ORDER BY eeabrev";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);

ArrayList array=new ArrayList();
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
Entite obj=new Entite();
 obj.setEecleunik(result.getInt(1));
obj.setEenom(result.getString(2));
obj.setEeadresse(result.getString(3));
obj.setEetelephonep(result.getString(4));
obj.setEetelephones(result.getString(5));
obj.setEefax(result.getString(6));
obj.setEemail(result.getString(7));
obj.setEeactif(result.getInt(8));
obj.setEedatetimecrea(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(9)));
obj.setEedatetimemodi(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(10)));
obj.setLmcleunik(result.getInt(11));
obj.setCxcleunik(result.getInt(12));
obj.setSecleunik(result.getInt(13));
obj.setEeabrev(result.getString(14));
obj.setEecompteur(result.getLong(15));
obj.setEetva(result.getString(16));
obj.setEecomptebancaire(result.getString(17));
obj.setEesmtp(result.getString(19));

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
     return cp=Transaction.generecombostest3("SELECT eecleunik,eeabrev,eenom FROM entite ORDER BY eeabrev",tmpool.getConuser());
}

public boolean checkLogin(int urcleunik, String login) throws java.rmi.RemoteException, ServeurSqlFailure {
    return false;
}


}