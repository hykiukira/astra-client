/*
 * FUCKYOUMOTHERFUCKER.java
 *
 * Created on 2 août 2004, 20:05
 */

package srcastra.astra.sys.btn;
import java.sql.*;
import java.io.*;
import java.util.*;
import srcastra.astra.sys.*;
/**
 *
 * @author  Administrateur
 */
public class FUCKYOUMOTHERFUCKER {
    
    /** Creates a new instance of FUCKYOUMOTHERFUCKER */
    Connection tmpcon;
    String directory="C:\\Documents and Settings\\Administrateur\\Mes documents\\perforinco\\btn_codage\\modif";
    public FUCKYOUMOTHERFUCKER() {
        try{
            tmpcon=testConnection("root", "XkLm2000", "btn", "3306", "localhost");
            System.out.println("Connected");
            
            createTable("C:\\Documents and Settings\\Administrateur\\Mes documents\\perforinco\\btn_codage\\modif");
            Transaction.begin(tmpcon);
            parseFile();
            Transaction.commit(tmpcon);
        }catch(Exception sn){
            sn.printStackTrace();
            Transaction.rollback(tmpcon);
        }
    }
    
    public void createTable(String directory) throws SQLException{
        File dir = new File(directory);    
        String[] children = dir.list();
        if (children == null) {
        
        } else {
        for (int i=0; i<children.length; i++) {
            // Get filename of file or directory
            String filename = children[i];
            System.out.println(filename);
            filename=filename.substring(0,(filename.length()-4));
            System.out.println(filename);          
            String createTable="CREATE TABLE "+filename+" (`id` int(11) NOT NULL auto_increment,`code` char(10) NOT NULL default '',`tradfr` varchar(50) NOT NULL default '',  `tradnl` varchar(50) NOT NULL default '',PRIMARY KEY  (`id`)) TYPE=MyISAM";
            if(filename.equals("tobroch1"))
                createTable="CREATE TABLE "+filename+" (`id` int(11) NOT NULL auto_increment,`code` char(10) NOT NULL default '',`tradfr` varchar(50) NOT NULL default '',  `tradnl` varchar(50) NOT NULL default '',`codefr` varchar(15) NOT NULL default '',PRIMARY KEY  (`id`)) TYPE=MyISAM";
            PreparedStatement pstmt=tmpcon.prepareStatement(createTable);
            pstmt.execute();
        }
    }
    } 
    public void parseFile() throws Exception{
        File dir = new File(directory);    
        String[] children = dir.list();
        if (children == null) {
        
        } else {
        for (int i=0; i<children.length; i++) {
            Hashtable hash=new Hashtable();
            // Get filename of file or directory
            String filename = children[i];
            File file=new File(directory+"\\"+filename);
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;
            System.out.println("File "+filename);
            while ((str = in.readLine()) != null) {
                process(str,filename,hash);
            }
            System.out.println("hash size "+hash.size());
            for(Enumeration enu=hash.keys();enu.hasMoreElements();){
              
             
                String tableName=filename.substring(0,(filename.length()-4));
                System.out.println("Table Name "+tableName);
                if(!tableName.equals("tobroch1")){
                    PreparedStatement pstmt=tmpcon.prepareStatement("INSERT INTO "+tableName+"(code,tradfr,tradnl) values(?,?,?)");                
                    FUCKYOUMOTHERFUCKER.TableCode tablecode=(FUCKYOUMOTHERFUCKER.TableCode)hash.get(enu.nextElement());
                    pstmt.setString(1,tablecode.code);
                    pstmt.setString(2,tablecode.traductionFr==null?tablecode.traductionNl:tablecode.traductionFr);
                    pstmt.setString(3,tablecode.traductionNl==null?tablecode.traductionFr:tablecode.traductionNl);
                    pstmt.execute();
                }else{
                    PreparedStatement pstmt=tmpcon.prepareStatement("INSERT INTO "+tableName+"(code,tradfr,tradnl,codefr) values(?,?,?,?)");                
                    FUCKYOUMOTHERFUCKER.TableCode tablecode=(FUCKYOUMOTHERFUCKER.TableCode)hash.get(enu.nextElement());
                    pstmt.setString(1,tablecode.code);
                    pstmt.setString(2,tablecode.traductionFr==null?tablecode.traductionNl:tablecode.traductionFr);
                    pstmt.setString(3,tablecode.traductionNl==null?tablecode.traductionFr:tablecode.traductionNl);
                    pstmt.setString(4,tablecode.frCode);
                    pstmt.execute();
                    ;
                }
                
            }

    

    }
        }
    }
    public void process(String str,String fileName, Hashtable hash){
             
     StringTokenizer st = new StringTokenizer(str,"\t");
     String affiche="";
     String frcode="";
     int i=0;
     int y=4;
     int j=0;
     String code="";
     Object [] tmp=null;
     int indexlangue=1;
     int indexcode=0;
     int indexfr=10;
     if(fileName.equals("plaatsna.txt")){
         y=4;
         indexlangue=2;
         indexcode=1;
     }
     else if(fileName.equals("carrier.txt")){
         y=4;
       
     }
     else if(fileName.equals("to1.txt")){
         y=5;
     }
     else if(fileName.equals("tobroch1.txt")){
         y=6;
         indexlangue=2;
         indexcode=1;
         indexfr=0;
     }
   
     else y=3;
    
     FUCKYOUMOTHERFUCKER.TableCode tablecode=null;
     String langue="";
 //    System.out.println("token size "+ st.countTokens());
     int diff=st.countTokens()-4;
     while (st.hasMoreTokens()) {
         String text=st.nextToken();   
         
         if(i==indexcode){
             code=text;
             if(hash.get(text)==null){
                 tablecode=new FUCKYOUMOTHERFUCKER.TableCode();
                 hash.put(code,tablecode);
             }
             else{
                 tablecode=(FUCKYOUMOTHERFUCKER.TableCode)hash.get(text);
             }
         }
         if(i==indexcode){
            tablecode.code=code;
         }
         if(i==0 && indexfr==0){
             frcode=text;
         }
         if(i==indexlangue)
            langue=text;
      /*   if(y==5){
             if(i==4){
             if(text!=null){
               //  System.out.println("langue "+langue);
             if(langue.equals("N")){
                 tablecode.traductionNl=text;
             }
             else if(langue.equals("FN") || langue.equals("NF")){
                tablecode.traductionNl=text;
                tablecode.traductionFr=text;
             }
             else{
                 tablecode.traductionFr=text;
             }
         }
         }
         }
         else{*/
         if(i==y){
             if(text!=null){
               //  System.out.println("langue "+langue);
             if(langue.equals("N")){
                 tablecode.traductionNl=text;
             }
             else if(langue.equals("FN") || langue.equals("NF")){
                tablecode.traductionNl=text;
                tablecode.traductionFr=text;
             }
             else{
                 tablecode.traductionFr=text;
             }
              tablecode.frCode=frcode;
         }
         }
     //}
        i++;     
      
     }
    
   //  System.out.println(affiche);
    }
    private class TableCode{
      public String code;
      public String traductionNl;
      public String traductionFr;
      public String frCode;
    }
    
    public static void main (String args[]){
        new FUCKYOUMOTHERFUCKER();
    }
    final static String driver="com.mysql.jdbc.Driver";
 public Connection testConnection(String login,String password,String database,String port,String ip) throws Exception{
        tmpcon=null;          
        Class.forName(driver).newInstance() ;
        tmpcon = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+database+"?autoReconnect=true",login,password);
       // PreparedStatement pstmt=tmpcon.prepareStatement("CONNECT "+database);        
        return tmpcon;
    }
    
}
