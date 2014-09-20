/*
 * UtilAbstract.java
 *
 * Created on 13 septembre 2004, 20:29
 */

package srcastra.astra.sys.btn.utils;
import java.sql.*;
/**
 *
 * @author  Administrateur
 */

public class UtilAbstract {
    final static String driver="com.mysql.jdbc.Driver";
    /** Creates a new instance of UtilAbstract */
    public UtilAbstract() {
    }
    protected Connection testConnection(String login,String password,String database,String port,String ip) throws Exception{      
        Class.forName(driver).newInstance() ;
        Connection tmpcon = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+database+"?autoReconnect=true",login,password);
       // PreparedStatement pstmt=tmpcon.prepareStatement("CONNECT "+database);        
        return tmpcon;
    }
    
}
