/*

 * ManageGroupeDec.java

 *

 * Created on 25 février 2003, 10:29

 */



package srcastra.astra.sys.rmi.groupe_dec;

import java.sql.*;

import srcastra.astra.sys.classetransfert.Grpdecision_T;

import java.rmi.RemoteException;

import srcastra.astra.sys.rmi.Exception.*;

import srcastra.astra.sys.rmi.astraimplement;

import srcastra.astra.sys.Transaction;

import srcastra.astra.sys.rmi.groupe_dec.*;
import srcastra.astra.sys.rmi.socketfactory.*;



/**

 *

 * @author  Thomas

 */

public class ManageGroupeDec extends java.rmi.server.UnicastRemoteObject implements GrpGroupDecRmiInterface {

int catprod;

long lignecleunik;

astraimplement serveur;



String select="SELECT * FROM groupedecisiondef WHERE gncleunik=?";

String selectFournisseur="SELECT * FROM groupedecision WHERE frgtcleunik=?";

String selectProd="SELECT * FROM groupedecisionproduit   WHERE lignecleunik=? AND ctpro_cleunik =? ";

String selectSupReduc="SELECT * FROM groupedecisionsupreduc WHERE  lignecleunik=? ";



public final static String TABLEDEF="groupedecisiondef";

public final static String TABLEFOURN="groupedecision";

public final static String TABLEPROD="groupedecisionproduit";

public final static String TABLESUPREDUC="groupedecisionsupreduc";



public ManageGroupeDec(String table,astraimplement serveur,int port) throws RemoteException{   
    super(port,SSLClientSocketFactory.getClientFactory(),SSLServerSocketFactory.getServeurFactory());
    this.serveur=serveur;
    }

private ServeurSqlFailure renvexception(SQLException se,Connection con)throws ServeurSqlFailure{     

         se.printStackTrace();

         ServeurSqlFailure sqe=new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");

         sqe.setSqlException(se);

         sqe.setErrorcode(se.getErrorCode());

         throw sqe;       

   }



public int insertDef(Grpdecision_T grpd,long lignecleunik,int catprodcleunik,int urcleunik) throws RemoteException,ServeurSqlFailure{

   Connection con=serveur.getConnectionAndCheck(urcleunik,true).getConuser();

   try{

        return insertLocale(grpd,lignecleunik,con,catprodcleunik,TABLEDEF);

    }catch(SQLException sn){     

 }

 return 0;

}

public int insertFourn(Grpdecision_T grpd,long lignecleunik,int catprodcleunik,int urcleunik) throws RemoteException,ServeurSqlFailure{

   Connection con=serveur.getConnectionAndCheck(urcleunik,true).getConuser();

   try{

        return insertLocale(grpd,lignecleunik,con,catprodcleunik,TABLEFOURN);

    }catch(SQLException sn){     

 }

 return 0;

}

public int insertProd(Grpdecision_T grpd,long lignecleunik,int catprodcleunik,int urcleunik) throws RemoteException,ServeurSqlFailure{

   Connection con=serveur.getConnectionAndCheck(urcleunik,true).getConuser();

   try{

        return insertLocale(grpd,lignecleunik,con,catprodcleunik,TABLEPROD);

    }catch(SQLException sn){     

 }

 return 0;

}

public int insertSup(Grpdecision_T grpd,long lignecleunik,int catprodcleunik,int urcleunik) throws RemoteException,ServeurSqlFailure{

   Connection con=serveur.getConnectionAndCheck(urcleunik,true).getConuser();

   try{

        return insertLocale(grpd,lignecleunik,con,catprodcleunik,TABLESUPREDUC);

    }catch(SQLException sn){     

 }

 return 0;

}

public int insertLocale(Grpdecision_T grpd,long lignecleunik,Connection con,int catprodcleunik,String tmptable) throws SQLException{

    return insert(grpd,con,lignecleunik,catprodcleunik,tmptable);    

}

public int modifyLocale(Grpdecision_T grpd,long lignecleunik,Connection con,int catprodcleunik,String tmptable) throws SQLException{

    return modify(grpd,con,lignecleunik,catprodcleunik,tmptable);    

}







private int insert(Grpdecision_T grpd,Connection con,long lignecleunik,int catprodcleunik,String table) throws SQLException{

    String insert=  "INSERT INTO `"+table+"` ( `gncleunik` , `frcleunik` , `ttcleunik` , `itcleunik` , `aecleunik` , "+

                "`gndatetimecrea` , `gndatetimemodif` , `gncodetvavente` , `gntvainclusvente` , `gncodetvaachat` , "+

                "`gntvainclusachat` , `gnnbravanteche` , `gnacompteminpp` , `gnprodstockiata` , `gnprodstockautre` ,"+

                "`gnpccomvente` , `gncominclpvent` , `gnpcacompte` , `gnpccomachat` , `gnpcclerepvend` , `gnpcclerepconcept` , "+

                "`gnpcclerepmmere` , `snumerosessioncrea` , `snumerosessionmodif` , `frgtcleunik` , `gncomptevente` , `gncompteachat` "+

                ", `gnfracomptepc` , `gnfrnbda` , `gnfrsoldepc` , `gnfrnbds` , `gnfranchise` , `caecleunik` , `tecleunik` , `lignecleunik` , "+

                "`ctpro_cleunik` , `gn_tvacomptabiliser` , `gn_comsurtva` ) VALUES ('', ?, ?, ?, ?,NOW(),NOW() , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";



    PreparedStatement pstmt=con.prepareStatement(insert);

    pstmt.setInt(1,grpd.getFrcleunik());

    pstmt.setInt(2,grpd.getTtcleunik());

    pstmt.setInt(3,grpd.getItcleunik());

    pstmt.setInt(4,grpd.getAecleunik());

    pstmt.setInt(5,grpd.getGncodetvavente());

    pstmt.setInt(6,grpd.getGntvainclusvente());

    pstmt.setInt(7,grpd.getGncodetvaachat());

    pstmt.setInt(8,grpd.getGntvainclusachat());

    pstmt.setInt(9,grpd.getGnnbravanteche());

    pstmt.setFloat(10,grpd.getGnacompteminpp());

    pstmt.setInt(11,grpd.getGnprodstockiata());

    pstmt.setInt(12,grpd.getGnprodstockautre());

    pstmt.setFloat(13,grpd.getGnpccomvente());

    pstmt.setInt(14,grpd.getGncominclpvent());

    pstmt.setFloat(15,grpd.getGnpcacompte());

    pstmt.setFloat(16,grpd.getGnpccomachat());

    pstmt.setFloat(17,grpd.getGnpcclerepvend());

    pstmt.setFloat(18,grpd.getGnpcclerepconcept());

    pstmt.setFloat(19,grpd.getGnpcclerepmmere());

    pstmt.setString(20,grpd.getSnumerosessioncrea());

    pstmt.setString(21,grpd.getSnumerosessionmodif());

    pstmt.setInt(22,grpd.getFrgtcleunik());

    pstmt.setInt(23,grpd.getGncomptevente());

    pstmt.setInt(24,grpd.getGncompteachat());

    pstmt.setFloat(25,grpd.getGnfracomptepc());

    pstmt.setFloat(26,grpd.getGnfrnbda());

    pstmt.setFloat(27,grpd.getGnfrsoldepc());

    pstmt.setInt(28,grpd.getGnfrnbds());

    pstmt.setInt(29,grpd.getGnfranchise());

    pstmt.setInt(30,grpd.getCaecleunik());

    pstmt.setInt(31,grpd.getTecleunik());

    pstmt.setLong(32,lignecleunik);

    pstmt.setInt(33,catprodcleunik);

    pstmt.setInt(34,grpd.getGnfrtvaComptabiliserVente());

    pstmt.setInt(35,grpd.getGnfrtvaCommissionVente());

    pstmt.execute();

    Statement select=null;

    select=con.createStatement();        

    ResultSet tmpresult=select.executeQuery("select LAST_INSERT_ID();");

    tmpresult.first();

    int id=tmpresult.getInt(1);

   return id;   

}

public int modifyDef(Grpdecision_T grpd,long lignecleunik,int catprodcleunik,int urcleunik) throws RemoteException,ServeurSqlFailure{

    Connection con=serveur.getConnectionAndCheck(urcleunik,true).getConuser();

    try{    

      return modifyLocale(grpd,lignecleunik,con,catprodcleunik,TABLEDEF);

    }catch(SQLException sn){   

 }

 return 0;

}

public int modifyFourn(Grpdecision_T grpd,long lignecleunik,int catprodcleunik,int urcleunik) throws RemoteException,ServeurSqlFailure{

    Connection con=serveur.getConnectionAndCheck(urcleunik,true).getConuser();

    try{    

     int ret=modifyLocale(grpd,lignecleunik,con,catprodcleunik,TABLEFOURN);

     Transaction.commit(con);

     return ret;

    }catch(SQLException sn){  

        Transaction.rollback(con);

        sn.printStackTrace();

        renvexception( sn, con)  ;

    }

  // catch(Exception e){

    //   e.printStackTrace();

      // Transaction.rollback(con); 

   //}  

 

 return 0;

}

public int modifyProd(Grpdecision_T grpd,long lignecleunik,int catprodcleunik,int urcleunik) throws RemoteException,ServeurSqlFailure{

    Connection con=serveur.getConnectionAndCheck(urcleunik,true).getConuser();

    try{    

      return modifyLocale(grpd,lignecleunik,con,catprodcleunik,TABLEPROD);

    }catch(SQLException sn){   

 }

 return 0;

}

public int modifySup(Grpdecision_T grpd,long lignecleunik,int catprodcleunik,int urcleunik) throws RemoteException,ServeurSqlFailure{

    Connection con=serveur.getConnectionAndCheck(urcleunik,true).getConuser();

    try{    

      return modifyLocale(grpd,lignecleunik,con,catprodcleunik,TABLESUPREDUC);

    }catch(SQLException sn){   

 }

 return 0;

}



public int modify(Grpdecision_T grpd,Connection con,long lignecleunik,int catprodcleunik,String table) throws SQLException{

    String update=  "UPDATE "+table+"  set ttcleunik=? , itcleunik=? , aecleunik=?  , gndatetimemodif=NOW() , gncodetvavente=? , "+

                "gntvainclusvente=? , gncodetvaachat=? , gntvainclusachat=? ,gnnbravanteche=? ,gnacompteminpp=? , gnprodstockiata=?  ,  "+

                "gnprodstockautre=? ,gnpccomvente=? ,gncominclpvent=? , gnpcacompte=? , gnpccomachat=? , gnpcclerepvend=? , gnpcclerepconcept=? , "+

                "gnpcclerepmmere=? , snumerosessioncrea=? , snumerosessionmodif=? , frgtcleunik=? , gncomptevente=? , gncompteachat=? , gnfracomptepc=? "+

                ", gnfrnbda=? , gnfrsoldepc=? , gnfrnbds=? , gnfranchise=? , caecleunik=? , tecleunik=? , lignecleunik=? , ctpro_cleunik=? , "+

                "gn_tvacomptabiliser=? , gn_comsurtva=? WHERE gncleunik=?";

    

    System.out.println("requete modify"+update + "cle"+grpd.getGncleunik());

    PreparedStatement pstmt=con.prepareStatement(update);

    pstmt.setInt(1,grpd.getTtcleunik());

    pstmt.setInt(2,grpd.getItcleunik());

    pstmt.setInt(3,grpd.getAecleunik());

    pstmt.setInt(4,grpd.getGncodetvavente());

    pstmt.setInt(5,grpd.getGntvainclusvente());

    pstmt.setInt(6,grpd.getGncodetvaachat());

    pstmt.setInt(7,grpd.getGntvainclusachat());

    pstmt.setInt(8,grpd.getGnnbravanteche());

    pstmt.setFloat(9,grpd.getGnacompteminpp());

    pstmt.setInt(10,grpd.getGnprodstockiata());

    pstmt.setInt(11,grpd.getGnprodstockautre());

    pstmt.setFloat(12,grpd.getGnpccomvente());

    pstmt.setInt(13,grpd.getGncominclpvent());

    pstmt.setFloat(14,grpd.getGnpcacompte());

    pstmt.setFloat(15,grpd.getGnpccomachat());

    pstmt.setFloat(16,grpd.getGnpcclerepvend());

    pstmt.setFloat(17,grpd.getGnpcclerepconcept());

    pstmt.setFloat(18,grpd.getGnpcclerepmmere());

    pstmt.setString(19,grpd.getSnumerosessioncrea());

    pstmt.setString(20,grpd.getSnumerosessionmodif());

    pstmt.setInt(21,grpd.getFrgtcleunik());

    pstmt.setInt(22,grpd.getGncomptevente());

    pstmt.setInt(23,grpd.getGncompteachat());

    pstmt.setFloat(24,grpd.getGnfracomptepc());

    pstmt.setFloat(25,grpd.getGnfrnbda());

    pstmt.setFloat(26,grpd.getGnfrsoldepc());

    pstmt.setInt(27,grpd.getGnfrnbds());

    pstmt.setInt(28,grpd.getGnfranchise());

    pstmt.setInt(29,grpd.getCaecleunik());

    pstmt.setInt(30,grpd.getTecleunik());

    pstmt.setLong(31,lignecleunik);

    pstmt.setInt(32,catprodcleunik);

    pstmt.setInt(33,grpd.getGnfrtvaComptabiliserVente());

    pstmt.setInt(34,grpd.getGnfrtvaCommissionVente());

    pstmt.setInt(35,grpd.getGncleunik());

    pstmt.execute();

   return 0;   

}

public Grpdecision_T selectDef(int gncleunik,Connection con,int cas)throws SQLException{  

   ResultSet tmpresult;

   PreparedStatement pstmt=con.prepareStatement(select);

   pstmt.setInt(1,gncleunik);

   tmpresult=pstmt.executeQuery();

   return genereGrpdec(tmpresult,con);

}

public Grpdecision_T selectFournisseur(int frcleunik,int cas,int urcleunik)throws RemoteException,ServeurSqlFailure{

   Connection con=serveur.getConnectionAndCheck(urcleunik,true).getConuser();

    try{

       return selectFournisseur(frcleunik,con,cas);

        /*    ResultSet tmpresult;

            PreparedStatement pstmt=con.prepareStatement(req);

            pstmt.setInt(1,frcleunik);

            tmpresult=pstmt.executeQuery();

            return genereGrpdec(tmpresult);*/

    }catch(SQLException sn){

        Transaction.rollback(con);

        sn.printStackTrace();

      renvexception( sn, con)  ;

    }

 //  catch(Exception e){

   //    Transaction.rollback(con); 

   //}

    return null;

}

public Grpdecision_T selectFournisseur(int frcleunik,Connection con,int cas)throws SQLException{

    String req=selectFournisseur;

    if(cas==1){

            req=req+" FOR UPDATE";

            Transaction.begin(con);

        }

   ResultSet tmpresult;

   PreparedStatement pstmt=con.prepareStatement(req);

   pstmt.setInt(1,frcleunik);

   tmpresult=pstmt.executeQuery();

   return genereGrpdec(tmpresult,con);

}

public Grpdecision_T selectProd(long lignecleunik,int catprod,Connection con,int cas)throws SQLException{

   ResultSet tmpresult;

   PreparedStatement pstmt=con.prepareStatement(selectProd);

   pstmt.setLong(1,lignecleunik);

   pstmt.setInt(2,catprod);

   tmpresult=pstmt.executeQuery();

   return genereGrpdec(tmpresult,con);

}

public Grpdecision_T selectSupreduc(long lignecleunik,Connection con,int cas)throws SQLException{

   ResultSet tmpresult;

   PreparedStatement pstmt=con.prepareStatement(selectSupReduc);

   pstmt.setLong(1,lignecleunik);

   tmpresult=pstmt.executeQuery();

   return genereGrpdec(tmpresult,con);

}

private Grpdecision_T genereGrpdec(ResultSet tmpresult,Connection con) throws SQLException{

   Grpdecision_T  tmpGrpDec=null;

   tmpresult.beforeFirst();

   while(tmpresult.next()){

        tmpGrpDec=new Grpdecision_T();

        tmpGrpDec=new Grpdecision_T(tmpresult.getInt(1),tmpresult.getInt(2),tmpresult.getInt(3),tmpresult.getInt(4),tmpresult.getInt(5), tmpresult.getDate(6),tmpresult.getDate(7),tmpresult.getInt(8),tmpresult.getInt(9),tmpresult.getInt(10),tmpresult.getInt(11),tmpresult.getInt(12),tmpresult.getFloat(13),tmpresult.getInt(14),tmpresult.getInt(15),tmpresult.getFloat(16),tmpresult.getInt(17),tmpresult.getFloat(18),tmpresult.getFloat(19),tmpresult.getFloat(20),tmpresult.getFloat(21),tmpresult.getFloat(22),tmpresult.getString(23),tmpresult.getString(24),tmpresult.getInt(25),tmpresult.getInt(26),tmpresult.getInt(27),tmpresult.getFloat(28),tmpresult.getInt(29),tmpresult.getInt(30),tmpresult.getInt(31),tmpresult.getInt(32),tmpresult.getInt(33),tmpresult.getInt(34),"","", tmpresult.getInt(37),tmpresult.getInt(38));

        PreparedStatement pstmt=con.prepareStatement("SELECT t1.Tva_rate ,t2.Tva_rate  FROM tva t1,tva t2 WHERE t1.tva_cleunik  =? AND t2.tva_cleunik  =?");

        pstmt.setInt(1,tmpGrpDec.getGncodetvaachat());

        pstmt.setInt(2,tmpGrpDec.getGncodetvavente());

        ResultSet valtva=pstmt.executeQuery();

        valtva.beforeFirst();

        while(valtva.next()){

            tmpGrpDec.setValeurGenFloat2(valtva.getFloat(1));

            tmpGrpDec.setValeurGenFloat1(valtva.getFloat(2));

            

            

        }

   } 

   return tmpGrpDec;   

}

}

