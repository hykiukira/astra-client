/*

 * Chain.java

 *

 * Created on 7 février 2003, 8:49

 */



package srcastra.astra.sys.compta;



/**

 *

 * @author  Thomas

 */

public interface Chain {

   public void addChain(Chain nextChain);

   public Chain getChain();

   public void sendToChain(String obj,Object obj2) throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException;

   public Object getConfig();

}

