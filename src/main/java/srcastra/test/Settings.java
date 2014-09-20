/*
 * Settings.java
 *
 * Created on 11 juin 2003, 13:39
 */

/**
 *
 * @author  Administrateur
 */
package srcastra.test;
import java.util.Hashtable;
import java.io.*;

public class Settings implements java.io.Serializable {
    
    public static String SCRIPT_ENV_URL = "http://madaconnection.com/lib/vars.php";     // url du script php qui renvoi les env du serveur web
    
    private static String SETTINGS_FILE = "settings.ser";                               // nom du fichier de sauvegarde de la configuration
    
    // private Hashtable environnementValues;                                           // contient les valeurs d'environnement renvoyer par le serveur web
    
    private boolean proxySettings;                                                      // intégration proxy à la connection
    private String proxyServerName;                                                     // nom du serveur proxy
    private String proxyServerPort;                                                     // nom du port du proxy
    
    private boolean emailLogEnabled;                                                    // activation de l'envoi de log par mail
    private String mail_smtpServer;                                                     // nom du serveur smtp pour envoi de rapport
    private String mail_emailAddr;                                                      // email auquel sera envoyé le rapport
    private String mail_senderName;                                                      // nom de l'envoyeur du mail
    
    private int mmsTimeDelay;                                                           // laps de temps entre les checks des adresses en millisecondes
    private boolean logEnabled;                                                         // active l'inscription de log sur disque
    
    /** Creates a new instance of Settings */
    private Settings() {
        proxySettings = false;
        proxyServerName = "";
        proxyServerPort = "";
        emailLogEnabled = false;
        mail_smtpServer = "";
        mail_emailAddr = "";
    }
    
    
    public static Settings getDefaultInstance() {
        File savFile = new File(Settings.SETTINGS_FILE);
        try {
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(savFile));
            Settings retVal = (Settings) oin.readObject();
            oin.close();
            return retVal;
        }
        catch (Exception e) {
          //  e.printStackTrace();
            return new Settings();
        }
    }
    
    public boolean saveSettings() {
        File savFile = new File(Settings.SETTINGS_FILE);
        try { 
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savFile));
            oos.writeObject(this);
            oos.close();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    /** Getter for property logEnabled.
     * @return Value of property logEnabled.
     */
    public boolean isLogEnabled() {
        return logEnabled;
    }
    
    /** Setter for property logEnabled.
     * @param logEnabled New value of property logEnabled.
     */
    public void setLogEnabled(boolean logEnabled) {
        this.logEnabled = logEnabled;
    }
    
    /** Getter for property mail_emailAddr.
     * @return Value of property mail_emailAddr.
     */
    public java.lang.String getMail_emailAddr() {
        return mail_emailAddr;
    }
    
    /** Setter for property mail_emailAddr.
     * @param mail_emailAddr New value of property mail_emailAddr.
     */
    public void setMail_emailAddr(java.lang.String mail_emailAddr) {
        this.mail_emailAddr = mail_emailAddr;
    }
    
    /** Getter for property mail_smtpServer.
     * @return Value of property mail_smtpServer.
     */
    public java.lang.String getMail_smtpServer() {
        return mail_smtpServer;
    }
    
    /** Setter for property mail_smtpServer.
     * @param mail_smtpServer New value of property mail_smtpServer.
     */
    public void setMail_smtpServer(java.lang.String mail_smtpServer) {
        this.mail_smtpServer = mail_smtpServer;
    }
    
    /** Getter for property mmsTimeDelay.
     * @return Value of property mmsTimeDelay.
     */
    public int getMmsTimeDelay() {
        return mmsTimeDelay;
    }
    
    /** Setter for property mmsTimeDelay.
     * @param mmsTimeDelay New value of property mmsTimeDelay.
     */
    public void setMmsTimeDelay(int mmsTimeDelay) {
        this.mmsTimeDelay = mmsTimeDelay;
    }
    
    /** Getter for property proxyServerName.
     * @return Value of property proxyServerName.
     */
    public java.lang.String getProxyServerName() {
        return proxyServerName;
    }
    
    /** Setter for property proxyServerName.
     * @param proxyServerName New value of property proxyServerName.
     */
    public void setProxyServerName(java.lang.String proxyServerName) {
        this.proxyServerName = proxyServerName;
    }
    
    /** Getter for property proxyServerPort.
     * @return Value of property proxyServerPort.
     */
    public java.lang.String getProxyServerPort() {
        return proxyServerPort;
    }
    
    /** Setter for property proxyServerPort.
     * @param proxyServerPort New value of property proxyServerPort.
     */
    public void setProxyServerPort(java.lang.String proxyServerPort) {
        this.proxyServerPort = proxyServerPort;
    }
    
    /** Getter for property proxySettings.
     * @return Value of property proxySettings.
     */
    public boolean isProxySettings() {
        return proxySettings;
    }
    
    /** Setter for property proxySettings.
     * @param proxySettings New value of property proxySettings.
     */
    public void setProxySettings(boolean proxySettings) {
        this.proxySettings = proxySettings;
    }
    
    /** Getter for property emailLogEnabled.
     * @return Value of property emailLogEnabled.
     */
    public boolean isEmailLogEnabled() {
        return emailLogEnabled;
    }
    
    /** Setter for property emailLogEnabled.
     * @param emailLogEnabled New value of property emailLogEnabled.
     */
    public void setEmailLogEnabled(boolean emailLogEnabled) {
        this.emailLogEnabled = emailLogEnabled;
    }
    
    /** Getter for property mail_senderName.
     * @return Value of property mail_senderName.
     */
    public java.lang.String getMail_senderName() {
        return mail_senderName;
    }    
  
    /** Setter for property mail_senderName.
     * @param mail_senderName New value of property mail_senderName.
     */
    public void setMail_senderName(java.lang.String mail_senderName) {
        this.mail_senderName = mail_senderName;
    }    
 
    
}
