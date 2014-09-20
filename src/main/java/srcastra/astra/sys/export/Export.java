/*
 * Export.java
 *
 * Created on 18 février 2004, 09:21
 */

package srcastra.astra.sys.export;
import java.sql.*;
import srcastra.astra.sys.*;
/**
 *
 * @author  Administrateur
 */
public class Export {
    
    /** Creates a new instance of Export */
    ComptaExport vente;
    ComptaExport financier;
    ComptaExport opdiv; 
    ComptaExport achat;
    Connection con=null;
    int lmcleunik;
    int pedecleunik;
    public static void main(String[] args){
       // new Export(2);   
    }
    public  Cubic_T exportData(boolean bydossier) throws Exception{
       // String setComptabiliser="UPDATE historique2 SET he_exported =? WHERE henotcpt=1 AND heperiode=?";        
        //con=connectDb("root", "XkLm2000", "oranje", "", 3306); 
        Cubic_T cubic=new Cubic_T(); 
        long exported=System.currentTimeMillis();
        System.out.println("check des vente");
        vente=new Vente(lmcleunik,con,bydossier,pedecleunik);
        ((Vente)vente).setExported(exported);
        System.out.println("check des caisse");
        financier=new Caisse(lmcleunik,con,bydossier,pedecleunik);
        opdiv=new OD(lmcleunik,con,bydossier,pedecleunik);
        achat=new Achat(lmcleunik, con,bydossier,pedecleunik);
        cubic.setVente(vente.execute());
        cubic.setOd(opdiv.execute());
        cubic.setAchat(achat.execute());
        cubic.setFinancier(financier.execute());
        cubic.setFournisseur(financier.getFournisseur());
        cubic.setClient(financier.getClient());
        return cubic;
    }
    public Export(int lmcleunik,Connection con) {
        this.lmcleunik=lmcleunik;
        this.con=con;
        
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
    private Connection connectDb(String userName, String password, String dbName, String dbHost, int dbPort) throws SQLException{
            String message;
            Connection tmpcon=null;
            try { 
                String jdbcDriverClassName="org.gjt.mm.mysql.Driver";
                if (jdbcDriverClassName!=null)    
                Class.forName(jdbcDriverClassName);
               // tmpcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?autoReconnect=true",userName,password);
                tmpcon = DriverManager.getConnection("jdbc:mysql://192.168.1.53:3306/"+dbName+"?autoReconnect=true",userName,password);
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
