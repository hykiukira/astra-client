/*
 * Utils.java
 *
 * Created on 6 janvier 2004, 15:34
 */

package srcastra.astra.sys.utils;
import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.classetransfert.Loginusers_T;
/**
 *
 * @author  Thomas
 */
public class Utils {
    
    /** Creates a new instance of Utils */
    public Utils() {
    }
    public static void setServeur(astrainterface serveur){
      m_serveur=serveur;   
    }
    public static void setUser(Loginusers_T user){
      m_user=user;   
    }
    public static astrainterface  getServeur(){
      return m_serveur;
    }
    public static Loginusers_T  getUser(){
      return m_user;
    }
    private static astrainterface m_serveur;
    private static Loginusers_T m_user;
    
}
