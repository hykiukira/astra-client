/*
 * InterCompteInterface.java
 *
 * Created on 11 juillet 2003, 14:11
 */

package srcastra.astra.sys.rmi;
import srcastra.astra.sys.classetransfert.compta.*;
import java.sql.*;
import srcastra.astra.sys.rmi.utils.*;
import srcastra.astra.sys.rmi.Exception.*;
import java.util.*;
import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.sys.compta.*;
/**
 *
 * @author  thomas
 */
public interface InterCompteInterface extends GlobalRmiInterface{
public Object getEntiteResultat(int urcleunik,int cleunik,int update,int eecleunik) throws java.rmi.RemoteException, ServeurSqlFailure;
public Object getEntiteCompte(int urcleunik,int cleunik,int update,int eecleunik) throws java.rmi.RemoteException, ServeurSqlFailure;
public Object getUserCompte(int urcleunik,int cleunik,int update) throws java.rmi.RemoteException, ServeurSqlFailure;
    
}
