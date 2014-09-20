/*
 * ConfigRmiInterface.java
 *
 * Created on 5 mars 2003, 8:48
 */

package srcastra.astra.sys.rmi;
import srcastra.astra.sys.rmi.Exception.*;
import srcastra.astra.sys.classetransfert.configuration.*;
/**
 *
 * @author  Thomas
 */
public interface ConfigRmiInterface extends java.rmi.Remote{
    public int insertConfig(AbstractConfig_T user,int urcleunik) throws ServeurSqlFailure,java.rmi.RemoteException;
    public int modifyConfig(AbstractConfig_T user,int urcleunik) throws ServeurSqlFailure,java.rmi.RemoteException;
    public AbstractConfig_T selectConfig(int urcleunik) throws ServeurSqlFailure,java.rmi.RemoteException;
}
