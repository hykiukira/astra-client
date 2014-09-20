/*
 * LockObject.java
 *
 * Created on 5 décembre 2003, 14:32
 */

package srcastra.astra.sys.rmi.utils;

/**
 *
 * @author  Thomas
 */
public class LockObject {
    
    /** Creates a new instance of LockObject */
    public LockObject() {
    }
     public synchronized java.lang.Object getSyn() {
          try{
          
            wait();
        }catch(java.lang.InterruptedException in){
            
        }     
        return null;
    }
    
    /** Setter for property syn.
     * @param syn New value of property syn.
     *
     */
    public synchronized void setSyn() {
        
            notifyAll();
          
    }
}
