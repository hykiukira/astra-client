/*
 * MainExport.java
 *
 * Created on 13 mai 2004, 14:39
 */

package srcastra.astra.sys.export;
import java.sql.*;
import srcastra.astra.sys.*;
/**
 *
 * @author  Administrateur
 */
public class MainExport {
    
    /** Creates a new instance of MainExport */
    Connection con;
    Export export;
    int lmcleunik;
    int pedecleunik;

    String SMTP;
    String tos;
    String froms;
    public MainExport(int lmcleunik,String SMTP,String tos,String froms,Connection con) {
        this.con=con;
        this.lmcleunik=lmcleunik;
        System.out.println("langue"+lmcleunik);

        this.SMTP=SMTP;
        this.tos=tos;
        this.froms=froms;              
    }
    public Cubic_T exportdData(boolean bydossier) throws SoldeComptException,Exception{
      try{
            Transaction.begin(con); 
            System.out.println("\nDebut du check comptable\n");
            new CheckExport(lmcleunik, SMTP, tos, froms,con);
            System.out.println("\nDebut de l'import\n");
            export=new Export(lmcleunik,con);
            return export.exportData(bydossier);
      }catch(Exception sn){  
            sn.printStackTrace(); 
            Transaction.rollback(con);
            if(sn instanceof SoldeComptException){
                throw (SoldeComptException)sn;
            }
                     
            throw sn;            
        }                 
    }
    public void commitExport(){
        Transaction.commit(con); 
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }
    
}
