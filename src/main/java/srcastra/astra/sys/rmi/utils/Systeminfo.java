/*
 * Systeminfo.java
 *
 * Created on 29 mars 2002, 10:53
 */

package srcastra.astra.sys.rmi.utils;

/**
 *
 * @author  thomas
 * @version 
 */
public class Systeminfo {

    /** Creates new Systeminfo */
    public Systeminfo() {
        osname=System.getProperty("os.name");
        osversion=System.getProperty("os.version");
        jvmversion=System.getProperty("java.vm.version");
        username=System.getProperty("user.name");
        try{
        //java.net.Socket sock=new java.net.Socket("localhost",80);
        //java.net.InetAddress adres=sock.getLocalAddress();
        java.net.InetAddress adres=java.net.InetAddress.getLocalHost();
        ipadresse=(String)adres.toString();
        System.out.println(ipadresse);
        }catch(java.net.UnknownHostException e)
        {
              System.out.println("Erreur dans la tentative de lecture de l'ip :"+e.getMessage());
              
        }
        catch(java.io.IOException i)
        {
           System.out.println("erreur d'io dans Systeminfo :"+i.getMessage()); 
        }
    }
    public void afficheinfo()
    {
        System.out.println(ipadresse);
        System.out.println(osname);
        System.out.println(osversion);
        System.out.println(jvmversion);
        System.out.println(username);
    }
    
    /** Getter for property ipadresse.
     * @return Value of property ipadresse.
     */
    public java.lang.String getIpadresse() {
        return ipadresse;
    }
    
   
    
    /** Getter for property jvmversion.
     * @return Value of property jvmversion.
     */
    public java.lang.String getJvmversion() {
        return jvmversion;
    }
    
    
    /** Getter for property osname.
     * @return Value of property osname.
     */
    public java.lang.String getOsname() {
        return osname;
    }
    
    /** Setter for property osname.
     * @param osname New value of property osname.
     */
    public void setOsname(java.lang.String osname) {
        this.osname = osname;
    }
    
    /** Getter for property osversion.
     * @return Value of property osversion.
     */
    public java.lang.String getOsversion() {
        return osversion;
    }
    
    /** Setter for property osversion.
     * @param osversion New value of property osversion.
     */
    public void setOsversion(java.lang.String osversion) {
        this.osversion = osversion;
    }
    
    /** Getter for property username.
     * @return Value of property username.
     */
    public java.lang.String getUsername() {
        return username;
    }
    
   
    
 private String ipadresse;  
 private String osname;
 private String osversion;
 private String jvmversion;
 private String username;
}
