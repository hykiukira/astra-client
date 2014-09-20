/*
 * ComptaExport.java
 *
 * Created on 18 février 2004, 09:21
 */

package srcastra.astra.sys.export;
import java.sql.*;
import java.util.*;
/**
 *
 * @author  Administrateur
 */
public interface ComptaExport {
    public java.util.ArrayList execute() throws SQLException;
    public Hashtable getClient();
    public Hashtable getFournisseur();
}
