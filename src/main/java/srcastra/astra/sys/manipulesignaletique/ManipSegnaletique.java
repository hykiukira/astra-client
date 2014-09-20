/*
 * ManipSegnaletique.java
 *
 * Created on 2 août 2002, 9:43
 */

package srcastra.astra.sys.manipulesignaletique;
import java.sql.*;
import srcastra.astra.sys.rmi.Exception.*;
/**
 *
 * @author  thomas
 */
public class ManipSegnaletique implements  ManipSegnaletiqueInterface  {
    
    /** Creates a new instance of ManipSegnaletique */
    public ManipSegnaletique() {
    }
    public static boolean checkForeignKeyClient(String nomcleunik,int cleunik,Connection con,String[] returnMessage){
        String req="SELECT csnom,csnom2 FROM clients WHERE "+nomcleunik+"=?"; 
        boolean returnvalue=false;
        int i=0;
        try{
            PreparedStatement pstmt=con.prepareStatement(req);
            pstmt.setInt(1,cleunik);
            ResultSet result=pstmt.executeQuery();
            result.beforeFirst();
            returnMessage[1]="";
            while(result.next()&& i<5){
                i++;
                returnvalue=true;
                returnMessage[0]="link_to_client";
                returnMessage[1]=returnMessage[1]+result.getString(1)+" - "+result.getString(2)+"\n";
            }
            return returnvalue;
        }
        catch(SQLException se)
        {
          
           se.printStackTrace();
        }
    return true;
    }
    public static boolean checkForeingKeyInFournisseur(String nomcleunik,int cleunik,Connection con,String[] returnMessage){
    String req="SELECT frnom1,frreference1 FROM fournisseur WHERE "+nomcleunik+"=?"; 
        boolean returnvalue=false;
        int i=0;
        try{
            PreparedStatement pstmt=con.prepareStatement(req);
            pstmt.setInt(1,cleunik);
            ResultSet result=pstmt.executeQuery();
            result.beforeFirst();
            returnMessage[1]="";
            while(result.next()&& i<5){
                i++;
                returnvalue=true;
                returnMessage[0]="link_to_fourn";
                returnMessage[1]=returnMessage[1]+result.getString(1)+" - "+result.getString(2)+"\n";
            }
        }
        catch(SQLException se)
        {
           
           se.printStackTrace();
           returnvalue=true;
           //throw new ServeurSqlFailure("erreur dans la base de donnée");
        }
          return returnvalue;
    }
    public static boolean checkForeingKeyInFeesTransport(int cleunik,Connection con,String[] returnMessage){
    String req="SELECT c.csnom,c.csnom2,r.csrscleunik FROM clients c,clients_remises r WHERE c.cscleunik=r.cscleunik AND r.ttcleunik=?";
        boolean returnvalue=false;
        int i=0;
        try{
            PreparedStatement pstmt=con.prepareStatement(req);
            pstmt.setInt(1,cleunik);
            ResultSet result=pstmt.executeQuery();
            result.beforeFirst();
            returnMessage[1]="";
            while(result.next()&& i<5){
                i++;
                returnvalue=true;
                returnMessage[0]="trans_link_to_remise";
                returnMessage[1]=returnMessage[1]+result.getString(1)+" - "+result.getString(2)+"\n";
            }
        }
        catch(SQLException se)
        {
           se.printStackTrace();
           returnvalue=true;
           
           //throw new ServeurSqlFailure("erreur dans la base de donnée");
        }
          return returnvalue;
    }
    public static boolean checkForeingKeyInFeesLogement(int cleunik,Connection con,String[] returnMessage){
    String req="SELECT c.csnom,c.csnom2,r.csrscleunik FROM clients c,clients_remises r WHERE c.cscleunik=r.cscleunik AND r.ltcleunik=?";
        boolean returnvalue=false;
        int i=0;
        try{
            PreparedStatement pstmt=con.prepareStatement(req);
            pstmt.setInt(1,cleunik);
            ResultSet result=pstmt.executeQuery();
            result.beforeFirst();
            returnMessage[1]="";
            while(result.next()&& i<5){
                i++;
                returnvalue=true;
                returnMessage[0]="log_link_to_remise";
                returnMessage[1]=returnMessage[1]+result.getString(1)+" - "+result.getString(2)+"\n";
            }
        }
        catch(SQLException se)
        {
           se.printStackTrace();
         
           //throw new ServeurSqlFailure("erreur dans la base de donnée");
        }
          return returnvalue;
    }
    public static void renvServeurFailureLogementTransport(int checkcode,int cleunik,Connection con)throws ServeurSqlFailure{
       boolean rep1=false;
       String[] returnMessage=new String[2];
       if(checkcode==CHECK_FOR_LOGEMENT_REMISE){
           rep1=checkForeingKeyInFeesLogement(cleunik,con,returnMessage);
           if(rep1==true)
           {
                genereException(returnMessage,CHECK_FOR_LOGEMENT_REMISE);
           }
       }
       else if(checkcode==CHECK_FOR_TRANS_REMISE){
            rep1=checkForeingKeyInFeesTransport(cleunik,con,returnMessage);
            if(rep1==true)
            {
              genereException(returnMessage,CHECK_FOR_TRANS_REMISE);
           }
       }
        
    }
    public static void renvServeurFailuerException(int checkcode,int cleunik,String nomcleunik,Connection con)throws ServeurSqlFailure{
       boolean rep1=false;
       boolean rep2=false;
       String[] returnMessage=new String[2];
        if(checkcode==CHECK_FOR_CLIENT){
           rep1=checkForeignKeyClient(nomcleunik,cleunik,con,returnMessage);
           if(rep1==true)
           {
                
                genereException(returnMessage,CHECK_WRONG);
           }
       }
       else if(checkcode==CHECK_FOR_FOURNISSEUR){
            rep1=checkForeingKeyInFournisseur(nomcleunik,cleunik,con,returnMessage);
            if(rep1==true)
            {
              genereException(returnMessage,CHECK_WRONG);
           }
       }
       else if(checkcode==CHECK_FOR_FOURN_AND_CLIENT){
            String[] repon1=new String[2];
            String[] repon2=new String[2];
            rep1=checkForeingKeyInFournisseur(nomcleunik,cleunik,con,repon1);
            rep2=checkForeignKeyClient(nomcleunik,cleunik,con,repon2);
            if(rep1==true&&rep2==false)
            {
               genereException(repon1,CHECK_WRONG); 
            }
            else if(rep1==false && rep2==true)
            {
               genereException(repon2,CHECK_WRONG); 
            }
            else if(rep1==true && rep2==true)
            {
                returnMessage=new String[5];
                returnMessage[0]="link_to_both_cli_fourn";
                returnMessage[1]="cli";
                returnMessage[2]=repon1[1];
                returnMessage[3]="fr";
                returnMessage[4]=repon2[1];
                genereException(returnMessage,CHECK_WRONG_BOTH_CLI_FOURN); 
            }           
       }   
       else if(checkcode==CHECK_FOR_NOTHING){
           
       }
    }
    private static void  genereException(String[] returnMessage,int checkCode) throws ServeurSqlFailure{
              
               ServeurSqlFailure sqe=new ServeurSqlFailure("Enregistrement lié, impossible de l'effacer");
               sqe.setErrorcode(checkCode);
               sqe.setMessagePerso(returnMessage);
               throw sqe; 
    }
    public static void delete(String table,String nomcleunik,int cleunik,Connection con,srcastra.astra.sys.rmi.utils.ServeurBuffer buf) throws ServeurSqlFailure{
        String req="DELETE  FROM "+table+" WHERE "+nomcleunik+"=?"; 
        String nomBuf=null;
        try{
             PreparedStatement pstmt=con.prepareStatement(req);
             pstmt.setInt(1,cleunik);
             pstmt.execute();
             nomBuf=renvIntituleBuffer(table);
             synchronized(buf){
                 if(nomBuf!=null)
                     buf.invalidateBuffer(nomBuf);                  
                     System.out.println("nombuf est null:"+nomBuf);
                
             }
            
        }
        catch(SQLException se)
        {
           se.printStackTrace();
           ServeurSqlFailure sqe=new ServeurSqlFailure("Erreur dans la base de donnée");
           sqe.setErrorcode(se.getErrorCode());
           throw sqe;
        }       
    }
   public static void deleteTransLog(int cleunik,Connection con,srcastra.astra.sys.rmi.utils.ServeurBuffer buf,int checkcode)throws ServeurSqlFailure{
         String req1="DELETE  FROM transport WHERE trtcleunik=?"; 
         String req2="DELETE  FROM logement WHERE ltcleunik=?"; 
         String nomBuf=null;
         PreparedStatement pstmt=null;
        try{
            if(checkcode==CHECK_FOR_LOGEMENT_REMISE)
            {
              pstmt=con.prepareStatement(req2);
              nomBuf=renvIntituleBuffer("log");
            }
            else if(checkcode==CHECK_FOR_TRANS_REMISE)
            {
               pstmt=con.prepareStatement(req1); 
               nomBuf=renvIntituleBuffer("trans");
            }
             pstmt.setInt(1,cleunik);
             pstmt.execute();
            // nomBuf=renvIntituleBuffer(table);
             synchronized(buf){
                 if(nomBuf!=null)
                     buf.invalidateBuffer(nomBuf);                  
                     System.out.println("nombuf est null:"+nomBuf);              
             }
        }
        catch(SQLException se)
        {
           se.printStackTrace();
           ServeurSqlFailure sqe=new ServeurSqlFailure("Erreur dans la base de donnée");
           sqe.setErrorcode(se.getErrorCode());
           throw sqe;
        }       
    }      
    private static String renvIntituleBuffer(String table){
    String returnvalue=null;
    if(table.equals(TABLE_CP))
    {
       returnvalue="codePostalDir"; 
    }
    else if(table.equals(TABLE_DEVISE))
    {
         returnvalue="deviseDir";
    }
     else if(table.equals(TABLE_LANGUE))
    {
          returnvalue="langueDir";
    }
     else if(table.equals(TABLE_PAYS))
    {
         returnvalue="paysDir";
    }
     else if(table.equals(TABLE_VALEUR_TVA))
    {
       returnvalue="tvaTypeDir";
    }  
     else if(table.equals(TABLE_TITRE_PERS))
    {
       returnvalue="titrePersDir";
    } 
      else if(table.equals("log"))
    {
       returnvalue="logementDir";
    }  
      else if(table.equals("trans"))
    {
       returnvalue="transportDir";
    }
    return returnvalue;
}
  
}
