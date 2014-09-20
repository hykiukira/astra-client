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
public class InterCompteRmi extends java.rmi.server.UnicastRemoteObject implements InterCompteInterface{
astraimplement serveur;
public InterCompteRmi(astraimplement serveur) throws RemoteException {      
this.serveur=serveur;
}

public void insert(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure{
InterCompte obj=(InterCompte)obj1;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
String requete="INSERT into intercompte(ce_cleunik,cour_cleunik,eecleunik,urcleunik,soal_cleunik,jota_cleunik,ce_cleunik2) VALUES(? ,? ,? ,? ,?, ?, ?)";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,obj.getCe_cleunik());
pstmt.setInt(2,obj.getCour_cleunik());
pstmt.setInt(3,obj.getEecleunik());
pstmt.setInt(4,obj.getUrcleunik());
pstmt.setInt(5,obj.getSoal_cleunik());
pstmt.setInt(6,obj.getJota_cleunik());
pstmt.setInt(7,obj.getCe_cleunik2());
pstmt.execute();
} 
catch(SQLException sn){
String[] message=new String[1];
 message[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
 ManageServSQLExcption.gestion2(sn,message,tmpool.getConuser());
}

}
public void modify(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure{
InterCompte obj=(InterCompte)obj1;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
Transaction.begin(tmpool.getConuser());
String requete="UPDATE intercompte SET cour_cleunik=? ,eecleunik=? ,urcleunik=? ,soal_cleunik=? ,jota_cleunik=? WHERE ce_cleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,obj.getCour_cleunik());
pstmt.setInt(2,obj.getEecleunik());
pstmt.setInt(3,obj.getUrcleunik());
pstmt.setInt(4,obj.getSoal_cleunik());
pstmt.setInt(5,obj.getJota_cleunik());
pstmt.setInt(6,obj.getCe_cleunik());

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
InterCompte obj=new InterCompte();
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
Transaction.begin(tmpool.getConuser());
String requete="SELECT * FROM intercompte WHERE ce_cleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,cleunik);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
 obj.setCe_cleunik(result.getInt(1));
obj.setCour_cleunik(result.getInt(2));
obj.setEecleunik(result.getInt(3));
obj.setUrcleunik(result.getInt(4));
obj.setSoal_cleunik(result.getInt(5));
obj.setJota_cleunik(result.getInt(6));

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
public Object getUserCompte(int urcleunik,int cleunik,int update) throws java.rmi.RemoteException, ServeurSqlFailure{
InterCompte obj=null;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
String requete="SELECT * FROM intercompte WHERE ce_cleunik=? AND urcleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,cleunik);
pstmt.setInt(2,tmpool.getUrcle2());
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
     if(obj==null)
       obj=new InterCompte();
obj.setCe_cleunik(result.getInt(1));
obj.setCour_cleunik(result.getInt(2));
obj.setEecleunik(result.getInt(3));
obj.setUrcleunik(result.getInt(4));
obj.setSoal_cleunik(result.getInt(5));
obj.setJota_cleunik(result.getInt(6));
obj.setCe_cleunik2(result.getInt(7));
}
if(obj==null)
    return new Integer(0);
else
 return obj;} 
catch(SQLException sn){
Transaction.rollback(tmpool.getConuser());
String[] message=new String[1];
 message[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
 ManageServSQLExcption.gestion2(sn,message,tmpool.getConuser());
}

 return null;
}
public Object getEntiteCompte(int urcleunik,int cleunik,int update,int eecleunik) throws java.rmi.RemoteException, ServeurSqlFailure{
InterCompte obj=null;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
String requete="SELECT * FROM intercompte WHERE cour_cleunik=? AND eecleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,cleunik);
pstmt.setInt(2,eecleunik);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
    if(obj==null)
       obj=new InterCompte();
 obj.setCe_cleunik(result.getInt(1));
obj.setCour_cleunik(result.getInt(2));
obj.setEecleunik(result.getInt(3));
obj.setUrcleunik(result.getInt(4));
obj.setSoal_cleunik(result.getInt(5));
obj.setJota_cleunik(result.getInt(6));
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
public Object getEntiteResultat(int urcleunik,int cleunik,int update,int eecleunik) throws java.rmi.RemoteException, ServeurSqlFailure{
InterCompte obj=null;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
String requete="SELECT * FROM intercompte WHERE ce_cleunik=? AND eecleunik!=0";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,cleunik);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
int i=0;
while (result.next()){
    i++;
    if(obj==null)
       obj=new InterCompte();
obj.setCe_cleunik(result.getInt(1));
obj.setCour_cleunik(result.getInt(2));
obj.setEecleunik(result.getInt(3));
obj.setUrcleunik(result.getInt(4));
obj.setSoal_cleunik(result.getInt(5));
obj.setJota_cleunik(result.getInt(6));
obj.setCe_cleunik2(result.getInt(7));
if(obj.getEecleunik()==eecleunik)
    return obj;
}
i++;

 return new Integer(i);} 
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

String requete="UPDATE intercompte SET annuler=1 WHERE ce_cleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,cleunik);;

pstmt.execute();

} 
catch(SQLException sn){

String[] message=new String[1];
 message[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
 ManageServSQLExcption.gestion2(sn,message,tmpool.getConuser());
}

}
public java.util.ArrayList getList(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure{
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
Transaction.begin(tmpool.getConuser());
String requete="SELECT * FROM intercompte ORDER BY cour_cleunik";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);

ArrayList array=new ArrayList();
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
InterCompte obj=new InterCompte();
 obj.setCe_cleunik(result.getInt(1));
obj.setCour_cleunik(result.getInt(2));
obj.setEecleunik(result.getInt(3));
obj.setUrcleunik(result.getInt(4));
obj.setSoal_cleunik(result.getInt(5));
obj.setJota_cleunik(result.getInt(6));

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
      return cp=Transaction.generecombostest3("SELECT ce_cleunik,cour_cleunik,eecleunik FROM intercompte ORDER BY cour_cleunik",tmpool.getConuser());
}
public boolean checkLogin(int urcleunik,String login) throws java.rmi.RemoteException, ServeurSqlFailure{
 return false;   
}
}