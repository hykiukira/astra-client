/*
 * DeleteManager.java
 *
 * Created on 7 mars 2003, 13:59
 */

package srcastra.astra.sys.signalitique;
import java.sql.*;
import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;
import srcastra.astra.sys.Transaction;

/**
 *
 * @author  thomas
 */
public class DeleteManager {
    
    /** Creates a new instance of DeleteManager */
    public DeleteManager() {
    }
    public static void DeleteFournisseur(int frcleunik,Connection con)throws ServeurSqlFailure{
        boolean sw=false;
        String[] table=new String[]{"avion_ticket","bateau","assurance","brochure","hotel","taxi","train","voiture"};
        ServeurSqlFailure sqe=null;
        for(int i=0;i<table.length;i++){
             try{
              //  String requete2="SELECT frgtcleunik
                String requete="SELECT frcleunik from "+table[i]+" WHERE frcleunik=?"; 
                PreparedStatement pstmt=con.prepareStatement(requete);
                pstmt.setInt(1,frcleunik);
                ResultSet result=pstmt.executeQuery();
                result.beforeFirst();
                while(result.next()){
                     sqe=new ServeurSqlFailure("Enregistrement lié, impossible de l'effacer");
                     sqe.setErrorcode(120);
                     throw sqe; 
                }
             
             }catch(SQLException se){
                se.printStackTrace();
                Transaction.rollback(con);
                sqe=new ServeurSqlFailure("Erreur lors de la requete à la base de donnée");
                sqe.setErrorcode(se.getErrorCode());
                throw sqe; 
    }
        }
       
}
}
