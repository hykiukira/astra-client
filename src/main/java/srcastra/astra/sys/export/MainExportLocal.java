/*
 * MainExportLocal.java
 *
 * Created on 13 mai 2004, 15:37
 */

package srcastra.astra.sys.export;
import srcastra.astra.sys.compta.*;
import java.util.*;
import java.sql.*;
import srcastra.astra.sys.*;
/**
 *
 * @author  Administrateur
 */
public class MainExportLocal extends MainExport{
    
    /** Creates a new instance of MainExportLocal */
    static Connection con;
    //Cubic_T cubic;
    public MainExportLocal(int lmcleunik,String SMTP,String tos,String froms) throws Exception{
        super(lmcleunik, SMTP, tos, froms, con);
        Cubic_T cubic=exportdData(true); 
        write(cubic);
        Transaction.commit(con); 
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // try{
       // con=connectDb("root", "XkLm2000", "vise", "195.144.69.10", 3306); 
       // new MainExportLocal(1,"smtp.xs4all.be","dussart.thomas@xs4all.be","thomas.dussart@skynet.be");   
       /*}catch(Exception en){
            Transaction.rollback(con);
          en.printStackTrace();    
        }*/
        
    }
     private void write(Cubic_T cubic) throws Exception{  
        // try{
        String path="C:\\Documents and Settings\\Administrateur\\Mes documents\\dbfcubic";
        System.out.println("Ecriture vers les fichiers");
        DbfManager2 dbfmanager=new DbfManager2(true);
        if(cubic.getVente()!=null){
                System.out.println("vente not null");
                dbfmanager.writeToDBF2(path+"\\CPTJV01.DBF",cubic.getVente()); 
        } 
        if(cubic.getAchat()!=null){
               System.out.println("achat not null"); 
                 dbfmanager.writeToDBF2(path+"\\CPTJA01.DBF",cubic.getAchat()); 
        }
        if(cubic.getFinancier()!=null){
               System.out.println("fiancier not null"); 
               dbfmanager.writeToDBF2(path+"\\CPTJF01.DBF",cubic.getFinancier()); 
        }
        if(cubic.getOd()!=null){
                System.out.println("od not null");
                dbfmanager.writeToDBF2(path+"\\CPTJO01.DBF",cubic.getOd());  
        }
        if(cubic.getFournisseur()!=null){
                 System.out.println("fournisseur not null");
                 dbfmanager.writeToDBFHash(path+"\\CPTSF01.DBF",cubic.getFournisseur(),DbfManager2.FOURNISSEUR); 
         }
        if(cubic.getClient()!=null){ 
                System.out.println("client not null");
                dbfmanager.writeToDBFHash(path+"\\CPTSC01.DBF",cubic.getClient(),DbfManager2.CLIENT); 
        }
       //  }catch(Exception e){
         //  e.printStackTrace();    
        // }
    }
    private static Connection connectDb(String userName, String password, String dbName, String dbHost, int dbPort) throws SQLException{
            String message;
            Connection tmpcon=null;
            try { 
                String jdbcDriverClassName="org.gjt.mm.mysql.Driver";
                if (jdbcDriverClassName!=null)    
                Class.forName(jdbcDriverClassName);
               // tmpcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?autoReconnect=true",userName,password);
                tmpcon = DriverManager.getConnection("jdbc:mysql://"+dbHost+"/"+dbName+"?autoReconnect=true",userName,password);
                System.out.println("ok connecter");              
            }
            catch(ClassNotFoundException e0) {
                e0.printStackTrace();            
            }          
            catch(Exception e2) {
                e2.printStackTrace();              
            }       // Add your handling code here:
            return tmpcon;
        }      
   
}
