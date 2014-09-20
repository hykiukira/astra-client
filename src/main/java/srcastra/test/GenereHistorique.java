/*
 * GenereHistorique.java
 *
 * Created on 2 septembre 2003, 12:01
 */

package srcastra.test;
import java.sql.*;

/**
 *
 * @author  thomas
 */
public class GenereHistorique {
    
    /** Creates a new instance of GenereHistorique */
    Connection con;
    public GenereHistorique() {
        try {
        con=connectDb("THOM","vidaloca","Astraclient","195.144.70.246",3306);
        
        
        }catch(SQLException sn){
          sn.printStackTrace();    
        }
         
    }
   public void generaterecord(int reccords){
        try {
        for(int i=0;i<reccords;i++){
          genereReccords(1);
          genereReccords(2);
        }        
        }catch(SQLException sn){
          sn.printStackTrace();    
        }     
   }
   public void countTable(){
       try{
         String requete="SELECT COUNT(hecleunik) FROM historique2";
         PreparedStatement pstmt=con.prepareStatement(requete);
         ResultSet result=pstmt.executeQuery();
         result.first();
         System.out.println("Nombre de ligne dans la table "+result.getInt(1));
        }catch(SQLException sn){
          sn.printStackTrace();    
        }
   
}
   public void sumValue(){
       long t1;
       long t2;
       long t3;
        try{
         String requete="SELECT SUM(hevaleur) FROM historique2";
         PreparedStatement pstmt=con.prepareStatement(requete);
         t1=System.currentTimeMillis();
         ResultSet result=pstmt.executeQuery();
         result.first();
         System.out.println("Somme des valeurs"+result.getDouble(1));
         t2=System.currentTimeMillis();
         t3=t2-t1;
         System.out.println("temps pour la somme = "+t3/1000);
        }catch(SQLException sn){
          sn.printStackTrace();    
        }
   
}
       
       
   
   private void genereReccords(int typepayement)throws SQLException{
     String requete="INSERT INTO historique2 ( `heperiode` , `henotcpt` , `heclottva` , `heclotperiode` "
                                            +", `heclotexercice` , `hetransact` , `jxcleunik` , `henumpiece` , `hedatecreation` , `hedatemouv` "
                                            +", `ce_cleunik_cent` , `tva_cleunik` , `ce_cleunik2` , `ce_cleunik` , `hevaleur` , `hecodetva` "
                                            +", `hevaleurbase` , `hevaleurtva` , `decleunik` , `hedatedevise` , `hevaleurdevise` , `helibelle` "
                                            +", `drcleunik` , `lignecleunik` , `sn_cleunik` , `ctprocleunik` , `typeintervenantcleunik` "
                                            +", `intervenantcleunik` , `cate_cleunik` , `hedossiercourant` , `hetypeligne` , `urcleunik` "
                                            +", `hetypepayement` , `helibellecompta2` , `pax` , `quantite` , `pourcent` , `hevaleuru` , `gn_cleunik` "
                                            +", `typegrpdec`,`exle_cleunik`) VALUES (? , ? , ? , ? , ? , ? , ?, ?, NOW() , ?"
                                            +", ?, ?, ?, ?, ?, ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?, ?, ?"
                                            +", ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?)";
        
       PreparedStatement pstmt=con.prepareStatement(requete);
       pstmt.setInt(1,1); //heperiode`
       pstmt.setInt(2,1); //henotcpt
       pstmt.setInt(3,1); //heclottva
       pstmt.setInt(4,0); //heclotperiode
       pstmt.setInt(5,1); //heclotexercice
       pstmt.setLong(6,1); //hetransact
       pstmt.setInt(7,22); //jxcleunik
       pstmt.setLong(8,1000); //henumpiece
      // pstmt.setString(9,"NOW()"); //hedatecreation
       pstmt.setString(9,"000-00-00 00:00:00"); //`hedatemouv    
       pstmt.setInt(10,1); // `ce_cleunik_cent` ,
       pstmt.setInt(11,1);  //  `tva_cleunik` , 
       pstmt.setInt(12,1);  //`ce_cleunik2` 
       pstmt.setString(13,"570000");//`ce_cleunik` ,
       pstmt.setDouble(14,120); //`hevaleur
       pstmt.setDouble(15,120); //hecodetva
       pstmt.setDouble(16,110); //hevaleurbase
       pstmt.setDouble(17,120); //`hevaleurtva
       pstmt.setDouble(18,20); //`decleunik`
       pstmt.setString(19,"000-00-00 00:00:00"); //`hedatedevise`
       pstmt.setDouble(20,100); //hevaleurdevise
       pstmt.setString(21,"testststete"); //helibelle`
       pstmt.setLong(22,312); //drcleunik`
       pstmt.setLong(23,1); //lignecleunik`
       pstmt.setLong(24,1); //`sn_cleunik`
       pstmt.setInt(25,1); //ctprocleunik`
       pstmt.setInt(26,1); //typeintervenantcleunik
       pstmt.setLong(27,1); //`intervenantcleunik
       pstmt.setInt(28,1); //cate_cleunik
       pstmt.setString(29,"0"); //hedossiercourant`
       pstmt.setString(30,"P"); //hetypeligne`
       pstmt.setInt(31,1); //`urcleunik`
       pstmt.setInt(32,typepayement); // hetypepaiement 
       pstmt.setString(33,"teset");   //helibellecompta2` 
       pstmt.setInt(34,1);//`pax` 
       pstmt.setInt(35,1);//`quantite` ,
       pstmt.setFloat(36,10); //`pourcent` , 
       pstmt.setDouble(37,10); //`hevaleuru` , 
       pstmt.setInt(38,1);   // `gn_cleunik` 
       pstmt.setInt(39,1);//`typegrpdec`
       pstmt.setInt(40,1); //exle_cleunik 
       pstmt.execute();          
   }
   private Connection connectDb(String userName, String password, String dbName, String dbHost, int dbPort) throws SQLException{
            String message;
            Connection tmpcon=null;
            try {
                String jdbcDriverClassName="org.gjt.mm.mysql.Driver";
                if (jdbcDriverClassName!=null)
                Class.forName(jdbcDriverClassName) ;
                tmpcon = DriverManager.getConnection("jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName+"?autoReconnect=true",userName,password);
                System.out.println("ok connecter");
               // tmpcon.close();
                //tmpcon = DriverManager.getConnection("jdbc:"+jdbcName+"://195.162.199.148:5000/Astratmp",user,pass);
            /*returnerreur.setErreurmessage("Connection etablie avec succès");
            returnerreur.setErreurcode(10000);*/
               // Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"connecter à la bd");
            }
            catch(ClassNotFoundException e0) {
                e0.printStackTrace();
             //   Logger.getDefaultLogger().log(Logger.LOG_EMERGENCY,"--> ClassNotFoundException : " + e0) ;
            /*returnerreur.setErreurmessage("ClassNotFoundException :"+e0.getMessage());
            returnerreur.setErreurcode(0);*/
            }
           
            catch(Exception e2) {
                e2.printStackTrace();
              //  Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"--> Exception : " + e2) ;
            /*returnerreur.setErreurmessage("Exception :"+e2.getMessage());
            returnerreur.setErreurcode(0);*/
            }       // Add your handling code here:
            return tmpcon;
        }
}
