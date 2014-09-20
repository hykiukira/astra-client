/*
 * GenerateXml.java
 *
 * Created on 30 juin 2003, 13:59
 */

package srcastra.test;
import java.sql.*;
import java.io.*;

/**
 *
 * @author  Thomas
 */
public class GenerateClass {
    
    /** Creates a new instance of GenerateXml */
    Connection con;
    public GenerateClass(String[] args) {
        try{
        con=connectDb("THOM","vidaloca","Astrainvalide","195.144.70.246",3306);
        createSerialisedClass("dossier",args);
        new GenerateRmiObject(args);
        } catch(SQLException e1) {
                e1.printStackTrace();
           /// Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"--> SQLException : " + e1) ;
            /*returnerreur.setErreurmessage("SQLException :"+e1.getMessage());
            returnerreur.setErreurcode(e1.getErrorCode());*/
            } 
         catch(IOException in) { 
                in.printStackTrace();
           /// Logger.getDefaultLogger().log(Logger.LOG_SECURITY,"--> SQLException : " + e1) ;
            /*returnerreur.setErreurmessage("SQLException :"+e1.getMessage());
            returnerreur.setErreurcode(e1.getErrorCode());*/
            }
    }
    private void createSerialisedClass(String table,String[] args) throws SQLException,IOException{
       File tmp=new File(args[6]);
       BufferedWriter out = new BufferedWriter(new FileWriter(tmp));
       String first="package srcastra.astra.sys.classetransfert.configuration;\n"+
       "/**** @author  Thomas Automatique process class generation*/\n "+
       "public class "+args[2]+" implements java.io.Serializable{\n";
       PreparedStatement prep=con.prepareStatement("DESC "+args[5]+";");
       ResultSet result=prep.executeQuery();
       result.beforeFirst();
       String text="null";
       while(result.next()){
           String obj=result.getObject(1).toString();
           String fin="     "+genereAttribut(result.getObject(2),obj);
           first=first+fin+"\n"; 
           // System.out.println(" Champs"+result.getObject(1).toString()+"     "+result.getObject(2).toString());   
       }
       result.beforeFirst();
        while(result.next()){
           String obj=result.getObject(1).toString();
           String fin=genereFonctions(result.getObject(2),obj);
           first=first+fin+"\n"; 
           // System.out.println(" Champs"+result.getObject(1).toString()+"     "+result.getObject(2).toString());   
       }
       first=first+"}";
     //  first=first+"root.insertBefore("+args[1]+",root.getFirstChild());";
       out.write(first);
       out.close();
    //   genereRmiObject(null,null,args);
       con.close();     
    }
    private String genereAttribut(Object obj,String string){
        String type=obj.toString();
        if(type.substring(0,3).equals("tin") || type.substring(0,3).equals("int") || type.substring(0,3).equals("med"))
            return "int "+string+";";
        if(type.substring(0,3).equals("big"))
            return "long "+string+";";
        if(type.substring(0,3).equals("dou"))
            return "double "+string+";";
        if(type.substring(0,3).equals("flo"))
            return "float "+string+";";
        if(type.substring(0,3).equals("dat"))
            return "srcastra.astra.sys.classetransfert.utils.Date "+string+";";
        if(type.substring(0,3).equals("cha") || type.substring(0,3).equals("var") || type.substring(0,3).equals("tex"))
            return "String "+string+";";
        return string;
    }
    private String genereFonctions(Object obj,String string){
         String type=obj.toString();
         String ret=null;
         String endset="){\n    this."+string+"="+string+";\n}";
         String get="get"+string.substring(0,1).toUpperCase()+string.substring(1)+"(){\n"+
         "      return this."+string+";\n}";
         String set="public void set"+string.substring(0,1).toUpperCase()+string.substring(1)+"(";
        if(type.substring(0,3).equals("tin") || type.substring(0,3).equals("int") || type.substring(0,3).equals("med")){
            ret="public int "+get;
            set=set+"int "+string+endset;}
        else if(type.substring(0,3).equals("big")){
            ret="public long "+get;
            set=set+"long "+string+endset;}
        else if(type.substring(0,3).equals("dou")){
            ret="public double "+get;
            set=set+"double "+string+endset;}
        else if(type.substring(0,3).equals("flo")){
            ret="public float "+get;
            set=set+"float "+string+endset;}
        else if(type.substring(0,3).equals("dat")){
            ret="public srcastra.astra.sys.classetransfert.utils.Date "+get;
            set=set+"srcastra.astra.sys.classetransfert.utils.Date "+string+endset;}
        else if(type.substring(0,3).equals("cha") || type.substring(0,3).equals("var") || type.substring(0,3).equals("tex")){
            ret="public String "+get;
            set=set+"String "+string+endset;}
        else ret=string;
        return ret+"\n\n"+set;
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new GenerateClass(args);
    }
    
}
