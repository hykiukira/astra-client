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

import srcastra.astra.sys.rmi.utils.*;

import srcastra.astra.sys.classetransfert.utils.*;

import srcastra.astra.sys.rmi.socketfactory.*; 

/**

*

* @author  Thomas

*/

public class UserRmi extends java.rmi.server.UnicastRemoteObject implements GlobalRmiInterface{

astraimplement serveur;

public UserRmi(astraimplement serveur,int port) throws RemoteException {      

    //super(port);

    super(port,SSLClientSocketFactory.getClientFactory(),SSLServerSocketFactory.getServeurFactory());

    this.serveur=serveur;

}

public void insert(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure{

    User obj=( User)obj1;

    Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);

    try{

        Transaction.begin(tmpool.getConuser());

        String requete="INSERT INTO `user` ( `urnom` , `urprenom` , `droits` , `urlogin` , `urcode` , `uradresse` , `cxcleunik`  , `urmailbur` , `urmailprive` , `uridentificationsecu` , `urtelephonebd` , `urtelephoneprive` , `uranalytique` , `uridlogo` , `urdatetimecrea` , `urdatetimemodif` , `snumerosessioncrea` , `snumerosessionmodif` , `eecleunik` , `lmcleunik` , `urgsm` , `annuler` , `urdroit`,`ursignature` ) "

        +"VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?,?);";

PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);

pstmt.setString(1,obj.getUrnom());

pstmt.setString(2,obj.getUrprenom());

pstmt.setString(3,obj.getDroits());

pstmt.setString(4,"non");

pstmt.setString(5,obj.getUrcode());

pstmt.setString(6,obj.getUradresse());

pstmt.setInt(7,obj.getCxcleunik());

pstmt.setString(8,obj.getUrmailbur());

pstmt.setString(9,obj.getUrmailprive());

pstmt.setString(10,obj.getUridentificationsecu());

pstmt.setString(11,obj.getUrtelephonebd());

pstmt.setString(12,obj.getUrtelephoneprive());

pstmt.setInt(13,obj.getUranalytique());

pstmt.setInt(14,obj.getUridlogo());

pstmt.setString(15,"NOW()");

pstmt.setString(16,"NOW()");

pstmt.setInt(17,obj.getSnumerosessioncrea());

pstmt.setInt(18,obj.getSnumerosessionmodif());

pstmt.setInt(19,obj.getEecleunik());

pstmt.setInt(20,obj.getLmcleunik());

pstmt.setString(21,obj.getUrgsm());

pstmt.setInt(22,obj.getAnnuler());

pstmt.setInt(23,obj.getUrdroit());

pstmt.setString(24,obj.getSignature());

pstmt.execute();

obj.setUrcleunik(GetId.getLastId(tmpool.getConuser()));

newUser(obj,tmpool);

Transaction.commit(tmpool.getConuser());

} 

catch(SQLException sn){

    sn.printStackTrace();

Transaction.rollback(tmpool.getConuser());

String[] message=new String[1];

 message[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");

 ManageServSQLExcption.gestion2(sn,message,tmpool.getConuser());

}

}

private void modifyUser(User user,Poolconnection tmpool,int urcleunik) throws ServeurSqlFailure{

    Connection conMysql=null;

    Connection conAstraCentrum=null;

    try{

    

     String requetAstrac="UPDATE  `user` SET `urlogin`=? WHERE `urcleunik`=?"; 

//     String requetemysql=

//     "UPDATE `user` SET `User`=? WHERE `User`=?";    

//    conMysql=connectDb(tmpool.getDatabaseLogin(),tmpool.getDatabasePassword(),serveur.getM_centrum(),serveur.getHost(),3306);

    conAstraCentrum=connectDb(tmpool.getDatabaseLogin(),tmpool.getDatabasePassword(),serveur.getM_centrum(),serveur.getHost(),3306);
   

//    Transaction.begin(conMysql);

    Transaction.begin(conAstraCentrum);

    PreparedStatement pstmt;

//    pstmt.setString(1,user.getUrlogin());

//    pstmt.setString(2,user.getUrlogin());

//    pstmt.execute();

//    pstmt=conMysql.prepareStatement("FLUSH PRIVILEGES;");

//    pstmt.execute();

    pstmt=conAstraCentrum.prepareStatement(requetAstrac);

    pstmt.setString(1,user.getUrlogin());

    pstmt.setInt(2,user.getUrcleunik());

    pstmt.execute();

//    Transaction.commit(conMysql);

    Transaction.commit(conAstraCentrum);

//    conMysql.close();

    conAstraCentrum.close();

 } 

catch(SQLException sn){

    sn.printStackTrace();

    try{

      //  conMysql.close();

        conAstraCentrum.close();

    }catch(SQLException sn2){

        sn2.printStackTrace();

    }

  //  Transaction.rollback(conMysql);

    Transaction.rollback(conAstraCentrum);

    Transaction.rollback(tmpool.getConuser());

String[] message=new String[1];

message[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");

ManageServSQLExcption.gestion2(sn,message,tmpool.getConuser());



}

}



public void modify(int urcleunik,Object obj1) throws java.rmi.RemoteException, ServeurSqlFailure{

User obj=(User)obj1;

Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);

try{

Transaction.begin(tmpool.getConuser());

String requete="UPDATE user SET urnom=? ,urprenom=? ,droits=? ,urlogin=? ,urcode=? ,uradresse=? ,cxcleunik=? ,urmailbur=? ,urmailprive=? ,uridentificationsecu=? ,urtelephonebd=? ,urtelephoneprive=? ,uranalytique=? ,uridlogo=? ,urdatetimemodif=? ,snumerosessioncrea=? ,snumerosessionmodif=? ,eecleunik=? ,lmcleunik=? ,urgsm=? ,annuler=? ,urdroit=?,ursignature=? WHERE urcleunik=?";

PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);

pstmt.setString(1,obj.getUrnom());

pstmt.setString(2,obj.getUrprenom());

pstmt.setString(3,obj.getDroits());

pstmt.setString(4,obj.getUrlogin());

pstmt.setString(5,obj.getUrcode());

pstmt.setString(6,obj.getUradresse());

pstmt.setInt(7,obj.getCxcleunik());

pstmt.setString(8,obj.getUrmailbur());

pstmt.setString(9,obj.getUrmailprive());

pstmt.setString(10,obj.getUridentificationsecu());

pstmt.setString(11,obj.getUrtelephonebd());

pstmt.setString(12,obj.getUrtelephoneprive());

pstmt.setInt(13,obj.getUranalytique());

pstmt.setInt(14,obj.getUridlogo());

pstmt.setString(15,"NOW()");

pstmt.setInt(16,obj.getSnumerosessioncrea());

pstmt.setInt(17,obj.getSnumerosessionmodif());

pstmt.setInt(18,obj.getEecleunik());

pstmt.setInt(19,obj.getLmcleunik());

pstmt.setString(20,obj.getUrgsm());

pstmt.setInt(21,obj.getAnnuler());

pstmt.setInt(22,obj.getUrdroit());

pstmt.setString(23,obj.getSignature());

pstmt.setInt(24,obj.getUrcleunik());

pstmt.execute();

//modifyUser(obj,tmpool, urcleunik);

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

User obj=new User();

Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);

try{

String requete="SELECT * FROM user WHERE urcleunik=?";

PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);

pstmt.setInt(1,cleunik);

ResultSet result=pstmt.executeQuery();

result.beforeFirst();

while (result.next()){

 obj.setUrcleunik(result.getInt(1));

obj.setUrnom(result.getString(2));

obj.setUrprenom(result.getString(3));

obj.setDroits(result.getString(4));

obj.setUrlogin(result.getString(5));

obj.setUrcode(result.getString(6));

obj.setUradresse(result.getString(7));

obj.setCxcleunik(result.getInt(8));

obj.setUrlocalite(result.getString(9));

obj.setUrmailbur(result.getString(10));

obj.setUrmailprive(result.getString(11));

obj.setUridentificationsecu(result.getString(12));

obj.setUrtelephonebd(result.getString(13));

obj.setUrtelephoneprive(result.getString(14));

obj.setUranalytique(result.getInt(15));

obj.setUridlogo(result.getInt(16));

obj.setUrdatetimecrea(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(17)));

obj.setUrdatetimemodif(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(18)));

obj.setSnumerosessioncrea(result.getInt(19));

obj.setSnumerosessionmodif(result.getInt(20));

obj.setEecleunik(result.getInt(21));

obj.setLmcleunik(result.getInt(22));

obj.setUrgsm(result.getString(23));

obj.setAnnuler(result.getInt(24));

obj.setUrdroit(result.getInt(25));

obj.setSignature(result.getString(26));

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

String requete="UPDATE user SET annuler=1 WHERE urcleunik=?";

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

String requete="SELECT * FROM user ORDER BY urprenom";

PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);



ArrayList array=new ArrayList();

ResultSet result=pstmt.executeQuery();

result.beforeFirst();

while (result.next()){

User obj=new User();

 obj.setUrcleunik(result.getInt(1));

obj.setUrnom(result.getString(2));

obj.setUrprenom(result.getString(3));

obj.setDroits(result.getString(4));

obj.setUrlogin(result.getString(5));

obj.setUrcode(result.getString(6));

obj.setUradresse(result.getString(7));

obj.setCxcleunik(result.getInt(8));

obj.setUrlocalite(result.getString(9));

obj.setUrmailbur(result.getString(10));

obj.setUrmailprive(result.getString(11));

obj.setUridentificationsecu(result.getString(12));

obj.setUrtelephonebd(result.getString(13));

obj.setUrtelephoneprive(result.getString(14));

obj.setUranalytique(result.getInt(15));

obj.setUridlogo(result.getInt(16));

obj.setUrdatetimecrea(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(17)));

obj.setUrdatetimemodif(new srcastra.astra.sys.classetransfert.utils.Date(result.getString(18)));

obj.setSnumerosessioncrea(result.getInt(19));

obj.setSnumerosessionmodif(result.getInt(20));

obj.setEecleunik(result.getInt(21));

obj.setLmcleunik(result.getInt(22));

obj.setUrgsm(result.getString(23));

obj.setAnnuler(result.getInt(24));

obj.setUrdroit(result.getInt(25));

obj.setSignature(result.getString(26));

array.add(obj);}

Transaction.commit(tmpool.getConuser());

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

public java.util.ArrayList getList2(int urcleunik,int cleunik) throws java.rmi.RemoteException, ServeurSqlFailure{



   CompressArray cp;

     Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);

     return cp=Transaction.generecombostest3("SELECT urcleunik,urprenom,urnom FROM user ORDER BY urprenom",tmpool.getConuser());

}

public boolean checkLogin(int urcleunik,String login) throws java.rmi.RemoteException, ServeurSqlFailure{

Poolconnection tmpool=serveur.getConnectionAndCheck(urcleunik,true);

try{

Transaction.begin(tmpool.getConuser());

String requete="SELECT * FROM user WHERE urlogin=?";

PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);

pstmt.setString(1,login);

ResultSet result=pstmt.executeQuery();

result.beforeFirst();

boolean alreadyin=false;

while(result.next()){

    alreadyin=true;  

}

return alreadyin;

}catch(SQLException sn){

    Transaction.rollback(tmpool.getConuser());

    String[] message=new String[1];

    message[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");

    ManageServSQLExcption.gestion2(sn,message,tmpool.getConuser());

}

return true;

}

private void newUser(User user,Poolconnection tmpool) throws ServeurSqlFailure{

     Connection conMysql=null;

     Connection conAstraCentrum=null;

     String requetAstrac=

     "INSERT INTO `user` ( `urcleunik` , `urlogin` , `uridlogo` , `urdatetimecrea` , `urdatetimemodif` , `soccleunik` , `lmcleunik`, `urpassword`  ) "+

     "VALUES (?, ?, 1, NOW(), NOW(), 1, ?,password(?));";



 /*    String requetemysql=

     "INSERT INTO `user` ( `Host` , `User` , `Password` , `Select_priv` , `Insert_priv` , `Update_priv` , `Delete_priv` , `Create_priv` , `Drop_priv` ,"+

     "`Reload_priv` , `Shutdown_priv` , `Process_priv` , `File_priv` , `Grant_priv` , `References_priv` , `Index_priv` , `Alter_priv` , `Show_db_priv` ,"+

     "`Super_priv` , `Create_tmp_table_priv` , `Lock_tables_priv` , `Execute_priv` , `Repl_slave_priv` , `Repl_client_priv` , `ssl_type` , `ssl_cipher` ,"+

     "`x509_issuer` , `x509_subject` , `max_questions` , `max_updates` , `max_connections` ) VALUES (?, ?, PASSWORD(?), 'Y', 'Y', 'Y', 'Y', 'N', 'N', 'N', 'N',"+

     "'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', '', '', '', '', '0', '0', '0');";*/

 try{

// conMysql=connectDb("root","XkLm2000","mysql",serveur.getHost(),3306);

 conAstraCentrum=connectDb(tmpool.getDatabaseLogin(),tmpool.getDatabasePassword(),serveur.getM_centrum(),serveur.getHost(),3306);

 //Transaction.begin(conMysql);

 Transaction.begin(conAstraCentrum);

 PreparedStatement pstmt;
 //PreparedStatement pstmt=conMysql.prepareStatement(requetemysql);

 //pstmt.setString(1,"localhost");

 //pstmt.setString(2,user.getUrlogin());

 //pstmt.setString(3,user.getPwd()); 

 //pstmt.execute();

 //pstmt=conMysql.prepareStatement("FLUSH PRIVILEGES;");

 //pstmt.execute();

 pstmt=conAstraCentrum.prepareStatement(requetAstrac);

 pstmt.setInt(1,user.getUrcleunik());

 pstmt.setString(2,user.getUrlogin());

 pstmt.setInt(3,user.getLmcleunik());
 
 pstmt.setString(4,user.getPwd());

 pstmt.execute();

// Transaction.commit(conMysql);

 Transaction.commit(conAstraCentrum);

// conMysql.close();

 conAstraCentrum.close();



 } 

catch(SQLException sn){

    sn.printStackTrace();

    try{

      //  conMysql.close();

        conAstraCentrum.close();

    }catch(SQLException sn2){

        sn2.printStackTrace();

    }

  //  Transaction.rollback(conMysql);

    Transaction.rollback(conAstraCentrum);

    Transaction.rollback(tmpool.getConuser());

String[] message=new String[1];

message[0]=java.util.ResourceBundle.getBundle("srcastra.astra.locale.alertmessage", tmpool.getLangage()).getString("fr_doublon");

ManageServSQLExcption.gestion2(sn,message,tmpool.getConuser());



}

}

 private Connection connectDb(String userName, String password, String dbName, String dbHost, int dbPort) throws SQLException{

            String message;

            Connection tmpcon=null;

            try {

                String jdbcDriverClassName="org.gjt.mm.mysql.Driver";

                if (jdbcDriverClassName!=null)

                Class.forName(jdbcDriverClassName) ;

                tmpcon = DriverManager.getConnection("jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName+"?autoReconnect=true",userName,password);

                System.out.println("ok connecter");

               // tmpcon.close();

                //tmpcon = DriverManager.getConnection("jdbc:"+jdbcName+"://195.162.199.148:5000/Astratmp",user,pass);

            /*returnerreur.setErreurmessage("Connection etablie avec succès");

            returnerreur.setErreurcode(10000);*/

               // Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"connecter à la bd");

            }

            catch(ClassNotFoundException e0) {

                e0.printStackTrace();

             //   Logger.getDefaultLogger().log(Logger.LOG_EMERGENCY,"--> ClassNotFoundException : " + e0) ;

            /*returnerreur.setErreurmessage("ClassNotFoundException :"+e0.getMessage());

            returnerreur.setErreurcode(0);*/

            }

           

            catch(Exception e2) {

                e2.printStackTrace();

              //  Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"--> Exception : " + e2) ;

            /*returnerreur.setErreurmessage("Exception :"+e2.getMessage());

            returnerreur.setErreurcode(0);*/

            }       // Add your handling code here:

            return tmpcon;

        }

}