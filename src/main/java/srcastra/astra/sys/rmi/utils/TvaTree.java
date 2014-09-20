/*
 * TvaTree.java
 *
 * Created on 25 avril 2003, 11:22
 */

package srcastra.astra.sys.rmi.utils;
import java.sql.*;
import java.util.*;
import srcastra.astra.sys.*;

/**
 *
 * @author  Thomas
 */
public class TvaTree {
    /** Creates a new instance of TvaTree */
    public TvaTree() {
    }
    public static ArrayList tvaTab(Poolconnection tmpool,int lmcleunik) throws SQLException{
      String requete="SELECT * FROM tva t,tvatraduction2 tt WHERE t.tva_cleunik=tt.tva_cleunik AND tt.lmcleunik=? ORDER BY t.genField3";
      ArrayList retval=null;
      PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);
      Logger.getDefaultLogger().log(Logger.LOG_INFOS,"[*******************]langue"+tmpool.getLmcleunik());
      if(lmcleunik==-1)
        pstmt.setInt(1,tmpool.getLmcleunik());
      else
        pstmt.setInt(1,lmcleunik);
      ResultSet result=pstmt.executeQuery();
      ResultSetMetaData meta=result.getMetaData();
      int col=meta.getColumnCount();
      result.beforeFirst();
      while(result.next()){
          if(retval==null)
              retval=new ArrayList();
              Object[] tab=new Object[col];
                for(int i=0;i<col;i++){
                   //  Logger.getDefaultLogger().log(Logger.LOG_INFOS,"[*******************]result"+result.getObject(i+1).toString()); 
                     tab[i]=result.getObject(i+1);       
                }
                retval.add(tab);
      }
       return retval;
    }
}
