/*
 * MysqlUser.java
 *
 * Created on 14 octobre 2004, 21:30
 */

package srcastra.astra.sys.init;
import java.sql.*;
import srcastra.astra.gui.sys.*;
/**
 *
 * @author  Administrateur
 */
public class MysqlUser {
    
    /** Creates a new instance of MysqlUser */
    boolean graphic;
    String centrumName;
    String astraName;
    String login;
    String password;
    public MysqlUser(boolean graphic,String centrumName,String astraName,String login,String password) throws SQLException{
        graphic=graphic;
        this.astraName=astraName;
        this.centrumName=centrumName;
        this.login=login;
        this.password=password;
        try{
            Connection con=connectDb(login, "",  centrumName,  "", 3306);
            initialise(con);
            con.close();
            InitOtherDb();
        }catch(SQLException sn){
            System.out.println("IN MYSQL INIT");
            sn.printStackTrace();                      
            try{
                Connection con=connectDb(login, password,   centrumName,  "", 3306);
                initialise(con);
                con.close();
                InitOtherDb();
                
                
            }catch(SQLException sn2){
                sn2.printStackTrace();
            if(graphic){
               // sn2.printStackTrace();
                ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, sn);
            }
                throw sn2;
            } 
           
        }    
    }
   private void InitOtherDb()throws SQLException{
       Connection con=connectDb(login, password,  centrumName,  "", 3306);
       srcastra.astra.sys.Transaction.begin(con);
       new InitCentrum(con,astraName);
       srcastra.astra.sys.Transaction.commit(con);
       con.close(); 
       con=connectDb(login, password,  astraName,  "", 3306);      
       srcastra.astra.sys.Transaction.begin(con);
       new InitAstra(con);
       srcastra.astra.sys.Transaction.commit(con);
       con.close();
       
   }
   private void initialise(Connection con) throws SQLException{
       boolean adminInserted=checkAdminUpdated(con);
       System.out.println("adminInserted "+adminInserted);
       if(!adminInserted){
           insertAdmin(con);
       }
       /*boolean rootUpdated=checkRootUpdated(con);
       if(!rootUpdated){
           changePrivilege(con);
       }*/
       
       //System.out.println("RootUpdated "+rootUpdated);
   }
   private boolean checkRootUpdated(Connection con) throws SQLException{
       String requete="SELECT * FROM usermysql WHERE User='root' AND Password=PASSWORD('XkLm2000')";
       PreparedStatement pstmt=con.prepareStatement(requete);
       ResultSet result=pstmt.executeQuery();
       return result.next();       
   }
   private boolean checkAdminUpdated(Connection con) throws SQLException{
       String requete="SELECT * FROM user WHERE urlogin='ADMIN' AND urpassword=PASSWORD('GgHh459')";
       PreparedStatement pstmt=con.prepareStatement(requete);
       ResultSet result=pstmt.executeQuery();
       return result.next();       
   }
   public void changePrivilege(Connection con)throws SQLException{        
        PreparedStatement pstmt=con.prepareStatement("UPDATE user SET Password=PASSWORD('XkLm2000') WHERE user='root';");
        pstmt.execute();
        pstmt=con.prepareStatement("FLUSH PRIVILEGES;");
        pstmt.execute();
        con.close();
    }
   private void insertAdmin(Connection con) throws SQLException{
         /*String requetemysql=
         "INSERT INTO `usermysql` ( `Host` , `User` , `Password` , `Select_priv` , `Insert_priv` , `Update_priv` , `Delete_priv` , `Create_priv` , `Drop_priv` ,"+
         "`Reload_priv` , `Shutdown_priv` , `Process_priv` , `File_priv` , `Grant_priv` , `References_priv` , `Index_priv` , `Alter_priv` , `Show_db_priv` ,"+
         "`Super_priv` , `Create_tmp_table_priv` , `Lock_tables_priv` , `Execute_priv` , `Repl_slave_priv` , `Repl_client_priv` , `ssl_type` , `ssl_cipher` ,"+
         "`x509_issuer` , `x509_subject` , `max_questions` , `max_updates` , `max_connections` ) VALUES (?, ?, PASSWORD(?), 'Y', 'Y', 'Y', 'Y', 'N', 'N', 'N', 'N',"+
         "'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', '', '', '', '', '0', '0', '0');";  */
         String requetemysql=
         "INSERT INTO `user` (`urlogin` , `urpassword`, `uridlogo`, `soccleunik`,`lmcleunik`) values(?,password(?),1,1,1);";
         PreparedStatement pstmt=con.prepareStatement(requetemysql);
        // pstmt.setString(1,"localhost");
         pstmt.setString(1,"ADMIN");
         pstmt.setString(2,"GgHh459"); 
         pstmt.execute();
   }
    public static void main(String[] args){
       // try{
       //     MysqlUser mysqluser=new MysqlUser(true,"installcentrum","install");
       // }catch(SQLException sn){
            
       // }
    }
    private Connection connectDb(String userName, String password, String dbName, String dbHost, int dbPort) throws SQLException{
            String message;
            Connection tmpcon=null;            
        try{
            String jdbcDriverClassName="org.gjt.mm.mysql.Driver";
           if (jdbcDriverClassName!=null)
                Class.forName(jdbcDriverClassName) ;
               // tmpcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?autoReconnect=true",userName,password);
                tmpcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?autoReconnect=true",userName,password);
                System.out.println("ok connecter");              
            }
            catch(ClassNotFoundException e0) {
                e0.printStackTrace();           
            }
           
               // Add your handling code here:
            return tmpcon;
        }
    
}
