/*
 * GrpGroupDecRmiInterface.java
 *
 * Created on 25 février 2003, 14:38
 */

package srcastra.astra.sys.rmi.groupe_dec;
import srcastra.astra.sys.classetransfert.Grpdecision_T;
import srcastra.astra.sys.rmi.Exception.*;
import java.rmi.RemoteException;

/**
 *
 * @author  Thomas
 */
public interface GrpGroupDecRmiInterface extends java.rmi.Remote{
  
    public int insertDef(Grpdecision_T grpd,long lignecleunik,int catprodcleunik,int urcleunik) throws RemoteException,ServeurSqlFailure;
    public int insertFourn(Grpdecision_T grpd,long lignecleunik,int catprodcleunik,int urcleunik) throws RemoteException,ServeurSqlFailure;
    public int insertProd(Grpdecision_T grpd,long lignecleunik,int catprodcleunik,int urcleunik) throws RemoteException,ServeurSqlFailure;
    public int insertSup(Grpdecision_T grpd,long lignecleunik,int catprodcleunik,int urcleunik) throws RemoteException,ServeurSqlFailure;
    public int modifyDef(Grpdecision_T grpd,long lignecleunik,int catprodcleunik,int urcleunik) throws RemoteException,ServeurSqlFailure;   
    public int modifyFourn(Grpdecision_T grpd,long lignecleunik,int catprodcleunik,int urcleunik) throws RemoteException,ServeurSqlFailure;   
    public int modifyProd(Grpdecision_T grpd,long lignecleunik,int catprodcleunik,int urcleunik) throws RemoteException,ServeurSqlFailure; 
    public int modifySup(Grpdecision_T grpd,long lignecleunik,int catprodcleunik,int urcleunik) throws RemoteException,ServeurSqlFailure;
    public Grpdecision_T selectFournisseur(int frcleunik,int cas,int urcleunik)throws RemoteException,ServeurSqlFailure;
   
   
}
