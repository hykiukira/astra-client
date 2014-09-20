package srcastra.astra.sys.rmi;
import srcastra.astra.sys.classetransfert.compta.*;
import java.sql.*;
import srcastra.astra.sys.rmi.utils.*;
import srcastra.astra.sys.rmi.Exception.*;
import java.util.*;
import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.sys.compta.*;
/**** @author  thomas*/
public interface UserRmiInterface extends java.rmi.Remote{

User getUser(int urcleunik,int Usercleunik,int update) throws java.rmi.RemoteException, ServeurSqlFailure;  
}