/*
 * ServerIp.java
 *
 * Created on 22 juin 2004, 16:40
 */

package srcastra.astra.gui.modules.param;

/**
 *
 * @author  Administrateur
 */
public class ServerIp implements java.io.Serializable{
    
    /** Creates a new instance of ServerIp */
    public ServerIp() {
    }
    
    /**
     * Getter for property fireWall.
     * @return Value of property fireWall.
     */
    public int getFireWall() {
        return fireWall;
    }
    
    /**
     * Setter for property fireWall.
     * @param fireWall New value of property fireWall.
     */
    public void setFireWall(int fireWall) {
        this.fireWall = fireWall;
    }
    
    /**
     * Getter for property ip.
     * @return Value of property ip.
     */
    public java.lang.String getIp() {
        return ip;
    }
    
    /**
     * Setter for property ip.
     * @param ip New value of property ip.
     */
    public void setIp(java.lang.String ip) {
        this.ip = ip;
    }
    
 int fireWall;
 String ip;
}
