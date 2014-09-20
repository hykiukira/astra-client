/*
 * InitAdmin.java
 *
 * Created on 16 octobre 2004, 16:44
 */

package srcastra.astra.sys.init;
import java.sql.*;
/**
 *
 * @author  Administrateur
 */
public class InitCentrum {
    
    /** Creates a new instance of InitAdmin */
    String astraDb;
    public InitCentrum(Connection con,String astraDb) throws SQLException{      
        this.astraDb=astraDb;
        if(!checkSoc(con)){
            System.out.println("Nobody in centrum societe ");
            insertSoc(con);            

        }
        else{
            System.out.println("centrum societe ok");
        }
        if(!checkUser(con)){
            System.out.println("Nobody in centrum user ");
            newUser(con);
        }
        else{
            System.out.println("centrum user ok");
        }
    }
    private boolean checkSoc(Connection con) throws SQLException{      
        String requete="SELECT * FROM societe";
        PreparedStatement pstmt=con.prepareStatement(requete);
        ResultSet result=pstmt.executeQuery();
        return result.next();
    }
    private boolean checkUser(Connection con) throws SQLException{      
        String requeteUser="SELECT * FROM user;";
        PreparedStatement pstmt=con.prepareStatement(requeteUser);
        ResultSet result=pstmt.executeQuery();
        return result.next(); 
    }
    public void insertSoc(Connection con) throws SQLException{      
      String requete="INSERT INTO `societe` ( `soccleunik` , `nom` , `databasename` , `databaseserver` , `databaseport` ) VALUES (1, 'new_soc', ?, 'localhost', '3306');";
      
      PreparedStatement pstmt=con.prepareStatement(requete);
      pstmt.setString(1, astraDb);
      pstmt.execute();     
    }
    private void newUser(Connection con) throws SQLException{   
        System.out.println("new user");
        //String 
         String requetAstrac=
         "INSERT INTO `user` ( `urcleunik` , `urlogin` , `uridlogo` , `urdatetimecrea` , `urdatetimemodif` , `soccleunik` , `lmcleunik` ) "+
         "VALUES (1,'ADMIN', 1, NOW(), NOW(), 1, 1);";                        
         PreparedStatement pstmt=con.prepareStatement(requetAstrac);
         pstmt.execute();
       
    }
    
}
