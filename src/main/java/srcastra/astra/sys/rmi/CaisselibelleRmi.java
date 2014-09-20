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
public class CaisselibelleRmi extends java.rmi.server.UnicastRemoteObject implements GlobalRmiInterface{
astraimplement serveur;
public CaisselibelleRmi(astraimplement serveur,int port) throws RemoteException {      
//super(port);
    super(port,SSLClientSocketFactory.getClientFactory(),SSLServerSocketFactory.getServeurFactory());
this.serveur=serveur;
}

public void insert(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure{
Caisselibelle_T obj=(Caisselibelle_T)obj1;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
Transaction.begin(tmpool.getConuser());
String requete="INSERT into caisselibelle(cale_lib1,cale_lib2,cale_categorie,ce_cleunik) VALUES(? ,? ,? ,?)";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setString(1,obj.getCale_lib1());
pstmt.setString(2,obj.getCale_lib2());
pstmt.setInt(3,obj.getCale_categorie());
pstmt.setInt(4,obj.getCe_cleunik());

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
Caisselibelle_T obj=(Caisselibelle_T)obj1;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
Transaction.begin(tmpool.getConuser());
String requete="UPDATE caisselibelle SET cale_lib1=? ,cale_lib2=? ,cale_categorie=? ,ce_cleunik=? WHERE cale_cleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setString(1,obj.getCale_lib1());
pstmt.setString(2,obj.getCale_lib2());
pstmt.setInt(3,obj.getCale_categorie());
pstmt.setInt(4,obj.getCe_cleunik());
pstmt.setInt(5,obj.getCale_cleunik());

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
Caisselibelle_T obj=new Caisselibelle_T();
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
Transaction.begin(tmpool.getConuser());
String requete="SELECT * FROM caisselibelle WHERE cale_cleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,cleunik);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
 obj.setCale_cleunik(result.getInt(1));
obj.setCale_lib1(result.getString(2));
obj.setCale_lib2(result.getString(3));
obj.setCale_categorie(result.getInt(4));
obj.setCe_cleunik(result.getInt(5));

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
String requete="DELETE FROM caisselibelle  WHERE cale_cleunik=?";
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
    ArrayList array=new ArrayList();
    try{
        String requetecli="SELECT c.cale_cleunik,c.cale_lib1,c.cale_lib2,c.cale_categorie,c.ce_cleunik,cs.csnom FROM caisselibelle c,clients cs WHERE c.cale_categorie=? AND c.ce_cleunik=cs.cscleunik ORDER BY cale_lib1";
        String requetefou="SELECT c.cale_cleunik,c.cale_lib1,c.cale_lib2,c.cale_categorie,c.ce_cleunik,f.frnom1 FROM caisselibelle c,fournisseur f WHERE c.cale_categorie=? AND c.ce_cleunik=f.frcleunik ORDER BY cale_lib1";
        String requetecompte="SELECT c.cale_cleunik,c.cale_lib1,c.cale_lib2,c.cale_categorie,c.ce_cleunik,c2.ce_num  FROM caisselibelle c,compte c2 WHERE c.cale_categorie=? AND c.ce_cleunik=c2.ce_cleunik ORDER BY cale_lib1";
        reqliste(tmpool,requetecli,array,Caisselibelle_T.CLIENT);
        reqliste(tmpool,requetefou,array,Caisselibelle_T.FOURNISSEUR);
        reqliste(tmpool,requetecompte,array,Caisselibelle_T.GENE);
        return array;
    } 
    catch(SQLException sn){
        Transaction.rollback(tmpool.getConuser());
        String[] message=new String[1];
         message[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
         ManageServSQLExcption.gestion2(sn,message,tmpool.getConuser());
    }

 return null;
}
private void reqliste(Poolconnection tmpool,String requete,ArrayList array,int categorie)throws SQLException{
    PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
    pstmt.setInt(1,categorie);
    ResultSet result=pstmt.executeQuery();
    result.beforeFirst();
    while (result.next()){
    Caisselibelle_T obj=new Caisselibelle_T();
    obj.setCale_cleunik(result.getInt(1));
    obj.setCale_lib1(result.getString(2));
    obj.setCale_lib2(result.getString(3));
    obj.setCale_categorie(result.getInt(4));
    obj.setCe_cleunik(result.getInt(5));
    obj.setComptetiers(result.getString(6));
    array.add(obj);
    }   
}
public java.util.ArrayList getList2(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure{

   CompressArray cp;
   Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
   String tmp="";
   if(tmpool.getUrcleunik()==1)
    return cp=Transaction.generecombostest3("SELECT cale_cleunik,cale_lib1,'test' FROM caisselibelle ORDER BY cale_lib1",tmpool.getConuser());
   else 
    return cp=Transaction.generecombostest3("SELECT cale_cleunik,cale_lib2,'test' FROM caisselibelle ORDER BY cale_lib1",tmpool.getConuser());
}

public boolean checkLogin(int urcleunik, String login) throws java.rmi.RemoteException, ServeurSqlFailure {
    return true;
}

}