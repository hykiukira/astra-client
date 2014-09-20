/*
 * RmiObjectOperation.java
 *
 * Created on 14 juin 2002, 15:06
 */

package srcastra.astra.sys.classetransfert;
import srcastra.astra.sys.rmi.Exception.*;

/**
 *
 * @author  Sébastien
 * @version 
 */
public interface RmiObjectOperations {
       
    /** Add an object to an ObjectOutputStream */
    public void insertObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception,ServeurSqlFailure;
    /** select an object from an ObjectInputStream */
    public Object selectObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception,ServeurSqlFailure;
    /** select an object from an ObjectInputStream */
    public Object selectObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser, int cleUnik) throws java.rmi.RemoteException, Exception,ServeurSqlFailure;
    /** select an object for update from an ObjectInputStream */
    public Object selectObjectForUpdate(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser, int cleUnik) throws java.rmi.RemoteException, Exception,ServeurSqlFailure;
    /** get a object list from an ObjectInputStream */
    public java.util.ArrayList selectAllObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser, int[] param, int cas) throws java.rmi.RemoteException, Exception,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;
    /** update an object to an ObjectOutputStream */
    public void updateObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception;
    /** delete an object to ObjectOutPutStream */
    public void deleteObject(srcastra.astra.sys.rmi.astrainterface serveur, srcastra.astra.sys.classetransfert.Loginusers_T currentUser) throws java.rmi.RemoteException, Exception;
    
}

