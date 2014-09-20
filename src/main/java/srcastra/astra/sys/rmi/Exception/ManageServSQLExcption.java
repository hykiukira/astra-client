/*
 * ManageServSQLExcption.java
 *
 * Created on 6 mars 2003, 16:34
 */

package srcastra.astra.sys.rmi.Exception;
import java.sql.Connection;
import srcastra.astra.sys.Transaction;

/**
 *
 * @author  Thomas
 */
public class ManageServSQLExcption {
    
    /** Creates a new instance of ManageServSQLExcption */
    public ManageServSQLExcption() {
    }
    public static void gestion1(java.sql.SQLException e,Connection con) throws ServeurSqlFailure{
         Transaction.rollback(con);
         e.printStackTrace();
         ServeurSqlFailure sqe=new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
         sqe.setSqlException(e);
         sqe.setErrorcode(e.getErrorCode());
         throw sqe;   
    }
    public static void gestionall(Exception e,Connection con)throws ServeurSqlFailure{
         Transaction.rollback(con);
         ServeurSqlFailure sqe=new ServeurSqlFailure(e.toString());
         e.printStackTrace();
        // sqe.setMessagePerso(message);
         throw sqe;   
    }
    public static void gestion2(java.sql.SQLException e,String[] message,Connection con) throws ServeurSqlFailure{
         Transaction.rollback(con);
         e.printStackTrace();
         ServeurSqlFailure sqe=new ServeurSqlFailure("Erreur lors de la requête au serveur de base de donnée");
         sqe.setSqlException(e);
         sqe.setErrorcode(e.getErrorCode());
         sqe.setMessagePerso(message);
         throw sqe;   
    }
    
}
