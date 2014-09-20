/*
 * TestServeurRmiInterface.java
 *
 * Created on 2 octobre 2003, 15:44
 */

package srcastra.astra.sys.rmi.test;

/**
 *
 * @author  Thomas
 */
public interface TestServeurRmiInterface  extends java.rmi.Remote{
    String getTest() throws java.rmi.RemoteException; 
    
}
