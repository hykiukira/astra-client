/*
 * CheckThread.java
 *
 * Created on 13 mai 2004, 09:19
 */

package srcastra.astra.sys.export;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import srcastra.astra.sys.classetransfert.utils.*;
import java.sql.*;
import java.util.logging.*;
/**
 *
 * @author  Administrateur
 */
public class CheckThread {
    
    /** Creates a new instance of CheckThread */
    Toolkit toolkit;
    Timer timer;
    int m_starthour;
    int m_endhour;
    boolean check;
    Connection con;
    String m_smtp="";
    String m_to="";
    String m_from="";
    String m_database="";
    String m_host="";
    String m_database_login="";
    String m_database_password="";
    
    int m_am_pm;
    static  java.util.logging.Logger logger= java.util.logging.Logger.getAnonymousLogger();
    public CheckThread(String starthour,String  endhour,String smtp,String to,String from,String database,String host,String am_pm,String login,String password) {
        m_starthour=new Integer(starthour).intValue();
        m_endhour=new Integer(endhour).intValue();
        m_am_pm=new Integer(am_pm).intValue();
        m_smtp=smtp;
        m_to=to;
        m_from=from;
        m_database=database;
        m_host=host;
        m_database_login=login;
        m_database_password=password;
        
        //toolkit = Toolkit.getDefaultToolkit(); 
        timer = new Timer();
        timer.schedule(new RemindTask(),
	               0,        //initial delay
	               3000000);  //subsequent rate

    }
     class RemindTask extends TimerTask {
	int numWarningBeeps = 3;
        public void run() {
            try{     
                srcastra.astra.sys.utils.My_Logger.getLogger().info("\n\n\n\n\n[**********Starting check accounting thread");
                srcastra.astra.sys.utils.My_Logger.getLogger().info("Start Hour "+m_starthour);
                srcastra.astra.sys.utils.My_Logger.getLogger().info("End Hour "+m_endhour);
                srcastra.astra.sys.utils.My_Logger.getLogger().info("Smtp "+m_smtp);
                srcastra.astra.sys.utils.My_Logger.getLogger().info("to "+m_to);
                srcastra.astra.sys.utils.My_Logger.getLogger().info("Database "+m_database);
                srcastra.astra.sys.utils.My_Logger.getLogger().info("Host "+m_host);
           if(CalculDate.checkTime(m_starthour,m_endhour, m_am_pm)==1){
               srcastra.astra.sys.utils.My_Logger.getLogger().info("CHECK AT :"+CalculDate.getTime());
           //  if(!check){
                con=connectDb(m_database_login,m_database_password, m_database, m_host, 3306);
                new CheckExport(2,m_smtp,m_to,m_from,con);   
                con.close();
                check=true;
             //}
           }                	  
	  }
        catch(Exception en){
            en.printStackTrace();
        }
        }  
     }
 public static void main(String args[]){
   //new CheckThread(10,11,null);   
 }
   private static Connection connectDb(String userName, String password, String dbName, String dbHost, int dbPort) throws SQLException{
            String message;
            Connection tmpcon=null;
            try { 
                String jdbcDriverClassName="org.gjt.mm.mysql.Driver";
                if (jdbcDriverClassName!=null)    
                Class.forName(jdbcDriverClassName);
               // tmpcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?autoReconnect=true",userName,password);
                tmpcon = DriverManager.getConnection("jdbc:mysql://"+dbHost+":3306/"+dbName+"?autoReconnect=true",userName,password);
                System.out.println("ok connecter");              
            }
            catch(ClassNotFoundException e0) {
                e0.printStackTrace();            
            }          
            catch(Exception e2) {
                e2.printStackTrace();              
            }       // Add your handling code here:
            return tmpcon;
        }    
    
}
