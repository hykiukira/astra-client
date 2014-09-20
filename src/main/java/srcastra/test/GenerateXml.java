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
public class GenerateXml {
    
    /** Creates a new instance of GenerateXml */
    Connection con;
    public GenerateXml(String[] args) {
        try{
        con=connectDb("THOM","vidaloca","Astrainvalide","195.144.70.246",3306);
        getTableDesc("dossier",args);
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
    private void getTableDesc(String table,String[] args) throws SQLException,IOException{
       File tmp=new File(args[0]);
       BufferedWriter out = new BufferedWriter(new FileWriter(tmp));
       String first="Element "+args[1]+"=document.createElement(\""+args[1]+"\");\n";
       PreparedStatement prep=con.prepareStatement("DESC "+args[2]+";");
       ResultSet result=prep.executeQuery();
       result.afterLast();
       String text="null";
       while(result.previous()){
           String obj=args[3]+".get"+result.getObject(1).toString().substring(0,1).toUpperCase()+result.getObject(1).toString().substring(1)+"()";
           String fin=getType(result.getObject(2),obj);
           String tmp2="Element "+result.getObject(1)+"=document.createElement(\""+result.getObject(1)+"\");\n"+args[1]+".insertBefore("+result.getObject(1)+","+args[1]+".getFirstChild());\n"+result.getObject(1)+".appendChild(document.createTextNode("+fin+"));\n";
           first=first+tmp2;
           // System.out.println(" Champs"+result.getObject(1).toString()+"     "+result.getObject(2).toString());   
       }
       first=first+"root.insertBefore("+args[1]+",root.getFirstChild());";
       out.write(first);
       out.close();
       con.close();     
    }
    private String getType(Object obj,String string){
        String type=obj.toString();
        if(type.substring(0,3).equals("tin") || type.substring(0,2).equals("bid") || type.substring(0,2).equals("int") || type.substring(0,2).equals("med"))
            return "new Integer("+string+").toString()";
        if(type.substring(0,3).equals("dou"))
            return "new Double("+string+").toString()";
        if(type.substring(0,3).equals("flo"))
            return "new Float("+string+").toString()";
        if(type.substring(0,3).equals("dat"))
            return string+".toString()";
        return string;
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
        new GenerateXml(args);
    }
    
}
