/*
 * TableUpdate.java
 *
 * Created on 28 août 2004, 12:15
 */

package srcastra.astra.sys.btn;
import java.sql.*;
import srcastra.astra.sys.Transaction;
/**
 *
 * @author  Administrateur
 */
public class TableUpdate {
    Connection tmpcon;
    final static String driver="org.gjt.mm.mysql.Driver";
    /** Creates a new instance of TableUpdate */
    public TableUpdate() {
        try{
            tmpcon=testConnection("root", "XkLm2000", "Astra", "3306", "localhost");
            Transaction.begin(tmpcon);
           // carrier();
            destination();
           // pays() ;
          //  fournisseur();
          //  grpDec(1);
        //    title();
            Transaction.commit(tmpcon);
        }catch(Exception sn){
            sn.printStackTrace();
            Transaction.rollback(tmpcon);
        }
        
    }
    public void grpDec(int langue) throws SQLException{
        String requete="SELECT f.frcleunik,t.code,t.tradfr,t.tradnl FROM tobroch1 t,fournisseur f WHERE f.btncode=t.codefr";
        PreparedStatement pstmt=tmpcon.prepareStatement(requete);
        ResultSet result=pstmt.executeQuery();
        String insert="INSERT INTO `fournisseur_grproduit` (  `frcleunik` , `frgtitrecatalog` , `frgtreference1` , `frgtreference2` , `aecleunik` , `frgtdatetimecrea` , `frgtdatetimemodi` , `snumerosessioncrea` , `snumerosessionmodif` , `annuler` , `urcleunikcrea` , `urcleunikmodif`,`btncode` ) VALUES ( ?, ?, ? , ? , 0, NOW(), NOW(), 11, 11, 0, 1, 1,?)";
        String checkIfExist="SELECT frgtcleunik FROM `fournisseur_grproduit` WHERE `frgtitrecatalog` =?";
        String update="UPDATE fournisseur_grproduit SET btncode=? where frgtcleunik=?";
        PreparedStatement pstmt2=tmpcon.prepareStatement(insert);
        PreparedStatement pstmt3=tmpcon.prepareStatement(checkIfExist);
        PreparedStatement pstmt4=tmpcon.prepareStatement(update);
       
        while(result.next()){
            int frgtcleunik=0;
            boolean sw=false;
            String titrecatalogue="";
            int id=result.getInt(1);
            String code=result.getString(2);
            if(code.equals("AIR")){
                System.out.println("fuck");
            }
         
            if(langue==1)
                titrecatalogue=result.getString(3);
            else 
                titrecatalogue=result.getString(4);
            pstmt3.setString(1,titrecatalogue);
            ResultSet result2=pstmt3.executeQuery();
            while(result2.next()){                 
                 sw=true; 
                 frgtcleunik=result2.getInt(1);
            }
            if(sw){
                pstmt4.setString(1,code);
                pstmt4.setInt(2,frgtcleunik);
                pstmt4.execute();
                System.out.println("Updated "+code);
            }
            else{
                pstmt2.setInt(1,id);
                pstmt2.setString(2,titrecatalogue);
                pstmt2.setString(3,code);
                pstmt2.setString(4,code);
                pstmt2.setString(5,code);
                pstmt2.execute();
                System.out.println("Inserted "+code);
            }
        }        
    }
    public void fournisseur() throws SQLException{
        String requete="SELECT code,tradfr FROM to1";
                                               //1       2      3               4               5           6       7           8           9               10          11              12         13     14        15          16                  17              18          19          20                  21              22                  23          24          25          26          27          28          29        30      31        32              33          34           1 2 3 4    5       6       7 8   9 10 11   12          13     14 15 16 17  18    19   20    21 22   23  24  25 26 27 28  29  30    31   32 33 34    
        String insert="INSERT INTO fournisseur(frnom1, frnom2, frreference1, frreference2, fradresse, frtvanum, frtvatype, frtvaregime, frnumbanque1, frnumbanque2, frnumbanque3, frtelephone1, frfax, frmail, frmodecccf, frdelaipaienbrjour, frdomiciliation, frmemo, frdatetimecrea, frdatetimemodif, snumerosessioncrea, snumerosessionmodif, decleunik, cxcleunik, aecleunik, lecleunik, pyscleunik, frfournprod, frNcompte, annuler, fr_web, urcleunikcrea, urcleunikmodif, btncode)VALUES(?,?,?,?,000000,000000000, 1, 1, 0, 0, 0, 00000000,000000000, 0, 1, 0, 0,'test', now(),now() ,0,0, 53, 2281, 0, 2, 20, 1, 458, 0, 00000, 0, 21,?);"; 
        String checkIfExist="SELECT * from fournisseur where btncode=?";
        String checkIfExist2="SELECT frcleunik from fournisseur where frreference1=?";
        String update="UPDATE fournisseur SET btncode=? WHERE frcleunik=?";
        PreparedStatement pstmt=tmpcon.prepareStatement(requete);
        PreparedStatement pstmt2=tmpcon.prepareStatement(checkIfExist);
        PreparedStatement pstmt3=tmpcon.prepareStatement(insert);
        PreparedStatement pstmt4=tmpcon.prepareStatement(checkIfExist2);
        PreparedStatement pstmt5=tmpcon.prepareStatement(update);
        ResultSet result=pstmt.executeQuery();
        while(result.next()){
            String code=result.getString(1);
            if(code.equals("SNW"))
                System.out.println("ee");
            String tradfr=result.getString(2);            
          
            pstmt4.setString(1,tradfr);
            ResultSet result3=pstmt4.executeQuery();
            boolean updated=false;
            while(result3.next()){
                System.out.println("UPDATED code "+code+" "+tradfr);
                int id=result3.getInt(1);
                pstmt5.setString(1,code);
                pstmt5.setInt(2,id);
                pstmt5.execute();
                updated=true;
            }
            pstmt2.setString(1, code);
            ResultSet result2=pstmt2.executeQuery();
            boolean in=false;
            while(result2.next()){
                in=true;                
            }
            if(!in && !updated){
                System.out.println("TO ADD code "+code+" "+tradfr);
                pstmt3.setString(1, tradfr);
                pstmt3.setString(2, tradfr);
                pstmt3.setString(3, tradfr);
                pstmt3.setString(4, tradfr);
                pstmt3.setString(5, code);
                pstmt3.execute();
            }
            
        }
    }
    public void title() throws SQLException{
        String requete="SELECT code,tradfr,tradnl FROM titel";
                                               //1       2      3               4               5           6       7           8           9               10          11              12         13     14        15          16                  17              18          19          20                  21              22                  23          24          25          26          27          28          29        30      31        32              33          34           1 2 3 4    5       6       7 8   9 10 11   12          13     14 15 16 17  18    19   20    21 22   23  24  25 26 27 28  29  30    31   32 33 34    
        String insert="insert into titrepers(tsdatetimecrea, tsdatetimemodif, snumerosessioncrea, snumerosessionmodif) values (NOW(), NOW(),0,0)";
        String insertTrad="insert into traductiontitrepers (tscleunik, lmcleunik, tsintitule, tsabrege) values(?,?,?,?)";
        String checkIfExist="SELECT * from traductiontitrepers where tsabrege=?";
        String checkIfExist2="SELECT frcleunik from fournisseur where frreference1=?";
        String update="UPDATE fournisseur SET btncode=? WHERE frcleunik=?";
        PreparedStatement pstmt=tmpcon.prepareStatement(requete);
        PreparedStatement pstmt2=tmpcon.prepareStatement(checkIfExist);
        PreparedStatement pstmt3=tmpcon.prepareStatement(insert);
        PreparedStatement pstmt4=tmpcon.prepareStatement(insertTrad);
        PreparedStatement pstmt5=tmpcon.prepareStatement(update);
        ResultSet result=pstmt.executeQuery();
        while(result.next()){
            String code=result.getString(1);
            String tradfr=result.getString(2);    
            String tradnl=result.getString(3);    
          
            pstmt2.setString(1,code);
            ResultSet result3=pstmt2.executeQuery();
            boolean updated=false;
            while(result3.next()){              
                updated=true;
            }
            if(!updated){
                pstmt3.execute();
                int id=getLastId();
                for(int i=1;i<6;i++){
                     //pstmt4=tmpcon.prepareStatement();
                     String trad=tradfr;
                     if(i==2)
                         trad=tradnl;
                     pstmt4.setInt(1,id);
                     pstmt4.setInt(2, i);
                     pstmt4.setString(3,trad);
                     pstmt4.setString(4, code);
                     pstmt4.execute();
                }
               
            }
            
        }
    }
    public void carrier() throws SQLException{
        String requete="SELECT ca.code,ca.tradfr,ca.tradnl FROM compagnie c RIGHT JOIN carrier ca ON (c.coe_abrev = ca.code) WHERE c.coe_abrev is NULL ORDER BY code";
        String insert="INSERT INTO compagnie (coe_abrev,coe_nom,coe_urcleunikcrea,coe_urcleunikmodif,coe_datetimecrea,coe_datetimemodif,btncode) VALUES (?,?,?,?,NOW(),NOW(),?);";
        String checkIfExist="SELECT * from compagnie where btncode=?";
        PreparedStatement pstmt=tmpcon.prepareStatement(requete);
        ResultSet result=pstmt.executeQuery();
        PreparedStatement pstmt2=tmpcon.prepareStatement(checkIfExist);        
        while(result.next()){
            boolean sw=false;
            String code=result.getString(1);
            String tradfr=result.getString(2);
            String tradnl=result.getString(3);
            pstmt2.setString(1,code);
            ResultSet result2=pstmt2.executeQuery();
            while(result2.next()){                 
                 sw=true;                 
            }
            if(!sw){
                pstmt=tmpcon.prepareStatement(insert);
                pstmt.setString(1,code);
                pstmt.setString(2, tradfr);
                pstmt.setInt(3,1);
                pstmt.setInt(4,1);
                pstmt.setString(5,code);
                pstmt.execute();
            }
        }
    }
   
    public void destination() throws SQLException{
        //attention to modify de size of dn_abrev
        String requete="SELECT pl.code,pl.tradfr,pl.tradnl FROM plaatsna pl";
        String insert="insert into destination(dn_abrev,urcleunikcrea,urcleunikmodif,dn_datetimecrea,dn_datetimemodif,btncode ) values(?,?,?,NOW(),NOW(),?);";
        String traduction="insert into traduction_destination (dn_cleunik,tn_traduction ,lmcleunik) values(?,?,?);";
        String checkIfExist="SELECT * from destination where btncode=?";
        PreparedStatement pstmt=tmpcon.prepareStatement(requete);
        PreparedStatement pstmt2=tmpcon.prepareStatement(checkIfExist);    
        ResultSet result=pstmt.executeQuery();
        while(result.next()){
            boolean sw=false;
            String code=result.getString(1);
            String tradfr=result.getString(2);
            String tradnl=result.getString(3);
            pstmt2.setString(1,code);
            ResultSet result2=pstmt2.executeQuery();
            while(result2.next()){                 
                 sw=true;                 
            }
            if(!sw){
                pstmt=tmpcon.prepareStatement(insert);
                pstmt.setString(1,code);          
                pstmt.setInt(2,1);
                pstmt.setInt(3,1);
                pstmt.setString(4,code);
                pstmt.execute();
                int id=getLastId();
                if(!tradfr.equals(tradnl)){
                    for(int i=0;i<2;i++){
                        pstmt=tmpcon.prepareStatement(traduction);
                         String trad=tradfr;
                         if(i==2)
                             trad=tradnl;
                         pstmt.setInt(1,id);
                         pstmt.setString(2,trad);
                         pstmt.setInt(3, i);
                         pstmt.execute();
                    }
                }
                else{
                         pstmt=tmpcon.prepareStatement(traduction);
                         String trad=tradfr;                      
                         pstmt.setInt(1,id);
                         pstmt.setString(2,trad);
                         pstmt.setInt(3, 0);
                         pstmt.execute();
                    
                }
        }
            else System.out.println(sw);
        }
    }
     public void pays() throws SQLException{
        //attention to modify de size of dn_abrev
        String requete="SELECT p.code,p.tradfr,p.tradnl FROM pays pa  RIGHT JOIN land p ON (pa.pysabrev = p.code ) WHERE pa.pysabrev is NULL ORDER BY code";
        String insert="insert into pays(pysabrev,btncode)  values(?,?);";
        String traduction="insert into traductionpays(pyscleunik,pystraduction,lmcleunik)  values(?,?,?);";
        PreparedStatement pstmt=tmpcon.prepareStatement(requete);
        ResultSet result=pstmt.executeQuery();
        String checkIfExist="SELECT * from pays where btncode=?";
        PreparedStatement pstmt2=tmpcon.prepareStatement(checkIfExist);    
        while(result.next()){
            boolean sw=false;
            String code=result.getString(1);
            String tradfr=result.getString(2);
            String tradnl=result.getString(3);
            pstmt2.setString(1,code);
            ResultSet result2=pstmt2.executeQuery();
            while(result2.next()){                 
                 sw=true;                 
            }
            if(!sw){
                pstmt=tmpcon.prepareStatement(insert);
                pstmt.setString(1,code);    
                pstmt.setString(2,code); 
                pstmt.execute();
                int id=getLastId();
                for(int i=1;i<6;i++){
                     pstmt=tmpcon.prepareStatement(traduction);
                     String trad=tradfr;
                     if(i==2)
                         trad=tradnl;
                     pstmt.setInt(1,id);
                     pstmt.setString(2,trad);
                     pstmt.setInt(3, i);
                     pstmt.execute();
                }
        }
        }
    }
    
    public int getLastId() throws SQLException{
         Statement select=tmpcon.createStatement();
         ResultSet tmpresult=select.executeQuery("select LAST_INSERT_ID();");
         tmpresult.first();
         int id=tmpresult.getInt(1);
         return id;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new TableUpdate();
    }
     public Connection testConnection(String login,String password,String database,String port,String ip) throws Exception{
        tmpcon=null;          
        Class.forName(driver).newInstance() ;
        tmpcon = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+database+"?autoReconnect=true",login,password);
       // PreparedStatement pstmt=tmpcon.prepareStatement("CONNECT "+database);        
        return tmpcon;
    }
    
}
