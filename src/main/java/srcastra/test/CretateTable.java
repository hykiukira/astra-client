/*
 * CretateTable.java
 *
 * Created on 24 avril 2003, 14:39
 */

package srcastra.test;
import java.sql.*;
import srcastra.astra.sys.*;
/**
 *
 * @author  Thomas
 */
public class CretateTable {
    
    /** Creates a new instance of CretateTable */
    public CretateTable() {
        Statement select=null;
        try{
         Class.forName("org.gjt.mm.mysql.Driver") ;
         Connection tmpcon = DriverManager.getConnection("jdbc:mysql://195.162.199.148:3306/Astrainvalide?autoReconnect=true", "THOM","vidaloca");
         select=tmpcon.createStatement();
         select.execute("BEGIN");
        // tmpcon.
         Logger.getDefaultLogger().log(Logger.LOG_INFOS,"connecter");
         select.execute(createTable(5,"tvatraduction2"));
         
         select.execute("COMMIT");
         tmpcon.close();
         Logger.getDefaultLogger().log(Logger.LOG_INFOS,"deconnecter");         
        }catch(SQLException sn){
            try{
            select.execute("ROLLBACK");
            }catch(SQLException sn2){
                
            }
            sn.printStackTrace();   
        }
        catch(ClassNotFoundException cn){
         cn.printStackTrace();   
        }
        }
private String createTable(int numfields,String tablename)  {
    String champs="";
    for(int i=0;i<numfields;i++){
        //if(i==numfields-1)
        //    champs=champs+"genField"+(i+1)+" varchar";
        //else
            champs=champs+"genField"+(i+1)+" varchar(20),";
    }
    String requete="CREATE TABLE "+tablename+" (tva_cleunik int(11) NOT NULL ,"+champs+"KEY tva_cleunik (tva_cleunik),FOREIGN KEY (tva_cleunik) REFERENCES tva (tva_cleunik) ON DELETE CASCADE) TYPE=InnoDB";
    Logger.getDefaultLogger().log(Logger.LOG_INFOS,requete);
    return requete;
}
public static void main(String args[]){
 new CretateTable();   
}
}
