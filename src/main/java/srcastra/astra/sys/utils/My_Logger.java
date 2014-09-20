/*
 * My_Logger.java
 *
 * Created on 26 octobre 2004, 22:28
 */

package srcastra.astra.sys.utils;
import java.util.logging.*;
import java.io.*;
/**
 *
 * @author  Administrateur
 */
public class My_Logger {
    
    /** Creates a new instance of My_Logger */
    static Logger logger;
    static My_Logger mylogger;
    private My_Logger() {
        if(logger==null){
            try{
                String path=srcastra.astra.sys.ManageDirectory.testDirectory("log");
                String file=path+"\\NewLogs.txt";
                logger=Logger.getAnonymousLogger();
                logger.addHandler(new FileHandler(file));
            }catch(IOException in){
                in.printStackTrace();
            }
        }
    }
    public static My_Logger getLogger(){
        if(mylogger==null){
            mylogger=new My_Logger();
        }
       return mylogger;
    }
    public static void warning(Object info){
        getLogger();
        String message="";
        if(info!=null){
            if(info instanceof Exception){
             //   try                                
                   Exception exception=(Exception)info;
                    StringWriter sw = new StringWriter();
                    PrintWriter printw = new PrintWriter(sw);
                    exception.printStackTrace(printw);
                    printw.close();
                    message=sw.toString();                                        
                
            }
            else{
                message=info.toString();
            }
            logger.warning(message);
        }
    }
     public static void info(Object info){
        getLogger();
        String message="";
        if(info!=null){
            if(info instanceof Exception){
             //   try                                
                   Exception exception=(Exception)info;
                    StringWriter sw = new StringWriter();
                    PrintWriter printw = new PrintWriter(sw);
                    exception.printStackTrace(printw);
                    printw.close();
                    message=sw.toString();                                        
                
            }
            else{
                message=info.toString();
            }
            logger.info(message);
        }
    }
}
