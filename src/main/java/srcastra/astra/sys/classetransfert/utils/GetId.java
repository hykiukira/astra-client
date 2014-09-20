/*
 * GetId.java
 *
 * Created on 26 mai 2003, 9:21
 */

package srcastra.astra.sys.classetransfert.utils;
import java.sql.*;
/**
 *
 * @author  Thomas
 */
public class GetId {
    
    /** Creates a new instance of GetId */
    public GetId() {
    }
    public static int getLastId(Connection con ){
         int id=0;
         try{
         PreparedStatement pstmt=con.prepareStatement("select LAST_INSERT_ID()"); 
         ResultSet tmpresult=pstmt.executeQuery();       
         tmpresult.first();
         id=tmpresult.getInt(1);
         tmpresult.close();
         }catch(SQLException se){
             se.printStackTrace();
         }
         return id;
        
    }
     public static long getLastIdLong(Connection con ){
         long id=0;
         try{
         PreparedStatement pstmt=con.prepareStatement("select LAST_INSERT_ID()"); 
         ResultSet tmpresult=pstmt.executeQuery();       
         tmpresult.first();
         id=tmpresult.getLong(1);
         tmpresult.close();
         }catch(SQLException se){
             se.printStackTrace();
         }
         return id;
        
    }
    
}
