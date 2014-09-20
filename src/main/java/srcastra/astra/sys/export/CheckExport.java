/*
 * Export.java
 *
 * Created on 18 février 2004, 09:21
 */

package srcastra.astra.sys.export;
import java.sql.*;
import srcastra.astra.sys.*;
import java.util.*;
/**
 *
 * @author  Administrateur
 */
public class CheckExport {
    
    /** Creates a new instance of Export */
    ComptaExport vente;
    ComptaExport financier;
    ComptaExport opdiv; 
    ComptaExport achat;
    Connection con=null;
    int pedecleunik;
    public static void main(String[] args){
      /*  try{
         Connection con=connectDb("root", "XkLm2000", "tmp","212.68.251.184", 3306);      
         new CheckExport(2,"relay.skynet.be","dussart.thomas@skynet.be","dussart.thomas@skynet.be",con);   
        }catch(Exception sn){
            sn.printStackTrace();
        }*/
    }
    public CheckExport(int lmcleunik,String SMTP,String tos,String froms,Connection con) throws Exception{
       // try{
      //  String setComptabiliser="UPDATE historique2 SET he_exported =? WHERE henotcpt=1 AND heperiode=?";        
       // con=connectDb("root", "XkLm2000", "oranje", "", 3306);            
        srcastra.astra.sys.utils.My_Logger.getLogger().info("STARTING CHECK EXPORT"); 
        this.con=con;
        ArrayList erreur=new ArrayList();
        vente=new CheckSoldeVente(lmcleunik,con,true,pedecleunik,erreur);
        achat=new CheckSoldeAchat(lmcleunik,con,true,pedecleunik,erreur);
        opdiv=new CheckSoldeOD(lmcleunik,con,true,pedecleunik,erreur);
        financier=new CheckSoldeCaisse(lmcleunik,con,true,pedecleunik,erreur);
        vente.execute();   
        achat.execute();
        opdiv.execute();
        financier.execute();
        String message="";
        if(erreur.size()>0){
            System.out.println("Détail des erreur :");
            for(int i=0;i<erreur.size();i++){
                String tmp=(String)erreur.get(i);
                message=message+tmp+"\n\n";
                System.out.println(tmp); 
            }     
            Mailing.sendMail(SMTP, tos, froms, "Erreur", message);
            srcastra.astra.sys.utils.My_Logger.getLogger().info("SENDING BAD MAIL");
            SoldeComptException exception=new SoldeComptException();
            exception.setErreur(erreur);
            Transaction.rollback(con); 
            throw exception;
        }
        else {
           Mailing.sendMail(SMTP, tos, froms, "Pas d'Erreur", message); 
           srcastra.astra.sys.utils.My_Logger.getLogger().info("SENDING COOL MAIL");
            
        }
         srcastra.astra.sys.utils.My_Logger.getLogger().info("END OF CHECK EXPORT**********]\n\n\n\n\n");
    } 
    private void write(Cubic_T cubic) throws Exception{  
        // try{
       
    }
    private static Connection connectDb(String userName, String password, String dbName, String dbHost, int dbPort) throws SQLException{
            String message;
            Connection tmpcon=null;
            try { 
                String jdbcDriverClassName="org.gjt.mm.mysql.Driver";
                if (jdbcDriverClassName!=null)    
                Class.forName(jdbcDriverClassName);
               // tmpcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?autoReconnect=true",userName,password);
                tmpcon = DriverManager.getConnection("jdbc:mysql://"+dbHost+":3306/"+dbName+"?autoReconnect=true",userName,password);
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
