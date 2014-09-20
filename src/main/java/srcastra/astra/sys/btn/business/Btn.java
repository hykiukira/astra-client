/*
 * Btn.java
 *
 * Created on 13 septembre 2004, 21:39
 */

package srcastra.astra.sys.btn.business;
import java.util.*;
import java.rmi.*;
import java.sql.*;
import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;
/**
 *
 * @author  Administrateur
 */
public interface Btn extends java.rmi.Remote{
    ArrayList getReservations(int entity,int urcleunik) throws RemoteException,ServeurSqlFailure;
}
