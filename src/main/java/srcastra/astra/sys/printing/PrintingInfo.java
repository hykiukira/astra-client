/*
 * PrintingInfo.java
 *
 * Created on 18 mars 2003, 9:22
 */

package srcastra.astra.sys.printing;
import srcastra.astra.gui.modules.printing.classelangueuser.*;
import srcastra.astra.sys.rmi.utils.*;
import srcastra.astra.sys.*;
import java.sql.*;
/**
 *
 * @author  Thomas
 */
public class PrintingInfo {
    
    /** Creates a new instance of PrintingInfo */
    public PrintingInfo() {
    }
    public static void getLocalite(GeneralePrinting tmp, int urcleunik,Poolconnection tmpool,int type) throws java.sql.SQLException{ 
        String req="SELECT c.cxcode,t.txtraduction FROM traductioncodpostaux t,codepostaux c WHERE c.cxcleunik=? AND c.cxcleunik=t.cxcleunik AND t.lmcleunik=?";
        PreparedStatement pstmt=tmpool.getConuser().prepareStatement(req);
        pstmt.setInt(1,tmp.getCxusecleunik());
        pstmt.setInt(2,tmp.getClientLmcleunik());
        ResultSet result=pstmt.executeQuery();
        result.beforeFirst();
        while(result.next()){ 
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"Recuperation des Localite");
            Generiquecp gen=new Generiquecp();
            gen.setCp(result.getString(1));
            gen.setLocalité(result.getString(2));
            setRightGenPrint(tmp,gen,type);
    }  
    }
     public static void getTitle(GeneralePrinting tmp, int urcleunik,Poolconnection tmpool,int type) throws java.sql.SQLException{ 
        String req="SELECT tsabrege FROM traductiontitrepers WHERE tscleunik =? AND lmcleunik=?";
           Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"Recuperation des titre");
           if(type==USER){
            if(tmp.getUser()==null)
                tmp.setUser(new Generiquecp());
                tmp.getUser().setTitre(" ");   
            
           }
           else if(type==CLIENT){
              if(tmp.getClient()==null)
                tmp.setClient(new Generiquecp());
            tmp.getClient().setTitre(" ");
           }
           else if(type==PASSAGER){
                 if(tmp.getPassager()==null)
                tmp.setPassager(new Generiquecp());
            tmp.getPassager().setTitre(" ");
           }
        PreparedStatement pstmt=tmpool.getConuser().prepareStatement(req);
        pstmt.setInt(1,tmp.getTitrecleunik());
        pstmt.setInt(2,tmp.getClientLmcleunik());
        ResultSet result=pstmt.executeQuery();
        result.beforeFirst();
        while(result.next()){ 
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"Recuperation des titre");
           if(type==USER){
            if(tmp.getUser()==null)
                tmp.setUser(new Generiquecp());
                tmp.getUser().setTitre(result.getString(1));   
            
           }
           else if(type==CLIENT){
              if(tmp.getClient()==null)
                tmp.setClient(new Generiquecp());
            tmp.getClient().setTitre(result.getString(1));
           }
           else if(type==PASSAGER){
                 if(tmp.getPassager()==null)
                tmp.setPassager(new Generiquecp());
            tmp.getPassager().setTitre(result.getString(1));
           }

    }  
   }
      public static void getNationalite(GeneralePrinting tmp, int urcleunik,Poolconnection tmpool,int type) throws java.sql.SQLException{ 
        String req="SELECT nae_abrev  FROM nationalite WHERE nae_cleunik =? ";
        PreparedStatement pstmt=tmpool.getConuser().prepareStatement(req);
        pstmt.setInt(1,tmp.getNatcleunik());
        ResultSet result=pstmt.executeQuery();
        result.beforeFirst();
        while(result.next()){ 
             Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"Recuperation des Nationalité");
           if(type==USER)
            tmp.getUser().setNationalité(result.getString(1));
           else if(type==CLIENT)
            tmp.getClient().setNationalité(result.getString(1));
           else if(type==PASSAGER)
            tmp.getPassager().setNationalité(result.getString(1));
          
    }  
   }
 private static void setRightGenPrint(GeneralePrinting tmp,Generiquecp gen,int type){
      if(type==USER)
          tmp.setUser(gen);
      else if(type==CLIENT)
          tmp.setClient(gen);
      else if(type==PASSAGER)
          tmp.setPassager(gen); 
      else if(type==FOUNISSEUR)
          tmp.setFounisseur(gen); 
 }
 public final static int CLIENT=0;   
 public final static int USER=1;
 public final static int PASSAGER=2;
  public final static int FOUNISSEUR=3;
}
