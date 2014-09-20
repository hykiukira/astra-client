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
import srcastra.astra.sys.classetransfert.dossier.*;
import srcastra.astra.sys.classetransfert.utils.*;
import srcastra.astra.sys.compta.*;
import srcastra.astra.sys.compta.command.*;
import srcastra.astra.sys.rmi.socketfactory.*; 
/**
*
* @author  Thomas
*/
public class SoldeRmi extends java.rmi.server.UnicastRemoteObject implements SoldeRmiInterface{
astraimplement serveur;
public SoldeRmi(astraimplement serveur,int port) throws RemoteException {      
//super(port);
 super(port,SSLClientSocketFactory.getClientFactory(),SSLServerSocketFactory.getServeurFactory());
this.serveur=serveur;
}
public void insert(int urcleunik)throws java.rmi.RemoteException, ServeurSqlFailure,SQLException{
     Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
    // GetData.test(""+tmpool.getUrcle2());
    ArrayList array=GetData.getCompteData(""+tmpool.getUrcle2()).getSolde();
    String checkD="SELECT sode_cleunik,sode_solded FROM solde WHERE sode_cat=? AND sode_divcleunik=? AND pede_cleunik=?";
    String checkC="SELECT sode_cleunik,sode_soldec FROM solde WHERE sode_cat=? AND sode_divcleunik=? AND pede_cleunik=?";
    String requetUdpateC="UPDATE solde set sode_soldec=? WHERE sode_cleunik=?";
    String requetUdpateD="UPDATE solde set sode_solded=? WHERE sode_cleunik=?";
    String requeteinsert="INSERT into solde(sode_cat,sode_divcleunik,sode_solded,sode_soldec,pede_cleunik) VALUES(? ,? ,? ,? ,?)";
    long cle=0;
    for(int i=0;i<array.size();i++){
        SoldeTot solde=(SoldeTot)array.get(i);
        System.out.println("Categorie "+solde.getCategorie()+" cleunik "+solde.getCle()+"  d/c "+solde.getDc()+" Montant "+solde.getMontant()+"\n");
        solde.setPeriode(GetData.getCompteData(""+tmpool.getUrcle2()).getPeriode());
        if(solde.getDc().equals("D")){
            cle=updateSolde(checkD,requetUdpateD,requeteinsert,solde,tmpool);
        }
        else if(solde.getDc().equals("C")){
            cle=updateSolde(checkC,requetUdpateC,requeteinsert,solde,tmpool);           
        }
         if(solde.getPayement()!=null)
            Somme.SommeCaise(cle,tmpool,solde.getPayement());
    }
    GetData.getCompteData(""+tmpool.getUrcle2()).setSolde(null);  
   
}
/*public void insertDebit(int urcleunik, int categorie, int cleunik, double solde, int periodecompta, Payement_T pay,boolean update) throws java.rmi.RemoteException, ServeurSqlFailure,SQLException{
    Solde_T obj=new Solde_T();
    obj.setSode_cat(categorie);
    obj.setSode_solded(solde);
    obj.setSode_soldec(0);
    obj.setPede_cleunik(periodecompta);
    obj.setSode_divcleunik(cleunik);
    PreparedStatement pstmt=null;
    Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
    boolean alreadyin=false;
        synchronized(serveur.getSoldeSyn()){
        String check="SELECT sode_cleunik,sode_solded FROM solde WHERE sode_cat=? AND sode_divcleunik=? AND pede_cleunik=?";
        String requeteinsert="INSERT into solde(sode_cat,sode_divcleunik,sode_solded,sode_soldec,pede_cleunik) VALUES(? ,? ,? ,? ,?)";
        String requetUdpate="UPDATE solde set sode_solded=? WHERE sode_cleunik=?";
        long cle=updateSolde(check,requetUdpate,requeteinsert,obj,tmpool,obj.getSode_solded());
        if(update)
            Somme.SommeCaise(cle,tmpool,pay);
}
}*/
/*public void insertCredit(int urcleunik, int categorie, int cleunik, double solde, int periodecompta, Payement_T pay,boolean update) throws java.rmi.RemoteException, ServeurSqlFailure,SQLException{
    Solde_T obj=new Solde_T();
    obj.setSode_cat(categorie);
    obj.setSode_solded(0);
    obj.setSode_soldec(solde);
    obj.setPede_cleunik(periodecompta);
    obj.setSode_divcleunik(cleunik);
    PreparedStatement pstmt=null;
    Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
    boolean alreadyin=false;
        synchronized(serveur.getSoldeSyn()){
        String check="SELECT sode_cleunik,sode_soldec FROM solde WHERE sode_cat=? AND sode_divcleunik=? AND pede_cleunik=?";
        String requeteinsert="INSERT into solde(sode_cat,sode_divcleunik,sode_solded,sode_soldec,pede_cleunik) VALUES(? ,? ,? ,? ,?)";
        String requetUdpate="UPDATE solde set sode_soldec=? WHERE sode_cleunik=?";
        long cle=updateSolde(check,requetUdpate,requeteinsert,obj,tmpool,obj.getSode_soldec());
        if(update)
            Somme.SommeCaise(cle,tmpool,pay);
    }
}*/
private long updateSolde(String check,String requetUdpate,String requeteinsert,SoldeTot obj,Poolconnection tmpool) throws java.rmi.RemoteException, ServeurSqlFailure,SQLException{
        double tmp=0;
        long cle=0;
        boolean alreadyin=false;
        PreparedStatement pstmt;
        pstmt=tmpool.getConuser().prepareStatement(check);
        pstmt.setInt(1,obj.getCategorie()); 
        pstmt.setInt(2,obj.getCle());
        pstmt.setLong(3,obj.getPeriode());
        ResultSet result=pstmt.executeQuery();
        result.beforeFirst();
        while (result.next()){
            cle=result.getInt(1);
            tmp=result.getDouble(2);
            alreadyin=true;
        }
        if(alreadyin){
            pstmt=tmpool.getConuser().prepareStatement(requetUdpate);
            pstmt.setDouble(1,tmp+obj.getMontant()); 
            pstmt.setLong(2,cle); 
            pstmt.execute();
        }
        else{
            pstmt=tmpool.getConuser().prepareStatement(requeteinsert);
            pstmt.setInt(1,obj.getCategorie());
            pstmt.setInt(2,obj.getCle());
            double value=0;
            if(obj.getDc().equals("D"))
               value=obj.getMontant(); 
            pstmt.setDouble(3,value);
            value=0;
            if(obj.getDc().equals("C"))
               value=obj.getMontant(); 
            pstmt.setDouble(4,value);
            pstmt.setLong(5,obj.getPeriode());
            pstmt.execute();
            cle=GetId.getLastIdLong(tmpool.getConuser());                   
        }
        return cle;
      
}
    

public void modify(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure{
Solde_T obj=(Solde_T)obj1;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
Transaction.begin(tmpool.getConuser());
String requete="UPDATE solde SET sode_cat=? ,sode_divcleunik=? ,sode_solde=? ,pede_cleunik=? WHERE sode_cleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,obj.getSode_cat());
pstmt.setInt(2,obj.getSode_divcleunik());
pstmt.setDouble(3,obj.getSode_solded());
pstmt.setInt(4,obj.getPede_cleunik());
pstmt.setLong(5,obj.getSode_cleunik());

pstmt.execute();
} 
catch(SQLException sn){
Transaction.rollback(tmpool.getConuser());
String[] message=new String[1];
 message[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");
 ManageServSQLExcption.gestion2(sn,message,tmpool.getConuser());
}

}
public Object get(int urcleunik,int cleunik,int category,int periode) throws java.rmi.RemoteException, ServeurSqlFailure{
Solde_T obj=null;
Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);
try{
String requete="SELECT * FROM solde WHERE sode_cat=? AND sode_divcleunik=? AND pede_cleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,category);
pstmt.setInt(2,cleunik);
pstmt.setInt(3,periode);
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
    if(obj==null)
        obj=new Solde_T();
 obj.setSode_cleunik(result.getLong(1));
obj.setSode_cat(result.getInt(2));
obj.setSode_divcleunik(result.getInt(3));
obj.setSode_solded(result.getDouble(4));
obj.setSode_soldec(result.getDouble(5));
obj.setPede_cleunik(result.getInt(6));
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
String requete="UPDATE solde SET annuler=1 WHERE sode_cleunik=?";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
pstmt.setInt(1,cleunik);;
pstmt.execute();
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

String requete="SELECT * FROM solde ORDER BY sode_cleunik";
PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);

ArrayList array=new ArrayList();
ResultSet result=pstmt.executeQuery();
result.beforeFirst();
while (result.next()){
Solde_T obj=new Solde_T();
 obj.setSode_cleunik(result.getInt(1));
obj.setSode_cat(result.getInt(2));
obj.setSode_divcleunik(result.getInt(3));
obj.setSode_solded(result.getDouble(4));
obj.setSode_soldec(result.getDouble(5));
obj.setPede_cleunik(result.getInt(6));

array.add(obj);}

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
   return cp=Transaction.generecombostest3("SELECT _T.java,sode_cleunik,cale_lib1 FROM solde ORDER BY sode_cleunik",tmpool.getConuser());
}

public boolean checkLogin(int urcleunik, String login) throws java.rmi.RemoteException, ServeurSqlFailure {
    return false;
}

}