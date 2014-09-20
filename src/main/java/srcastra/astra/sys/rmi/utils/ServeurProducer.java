/*
 * ServeurProducer.java
 *
 * Created on 5 décembre 2003, 14:28
 */

package srcastra.astra.sys.rmi.utils;

/**
 *
 * @author  Thomas
 */
public class ServeurProducer extends Thread{
    
    /** Creates a new instance of ServeurProducer */
    LockObject frame;
    public ServeurProducer(LockObject frame) {
        this.frame=frame;
    }
    public void run(){
      frame.getSyn();   
    }
    
}
