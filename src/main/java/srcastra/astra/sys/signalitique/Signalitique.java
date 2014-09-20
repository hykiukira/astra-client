/*
 * Signalitique.java
 *
 * Created on 21 août 2002, 15:54
 */

package srcastra.astra.sys.signalitique;
import java.util.*;
import java.rmi.RemoteException;
import srcastra.astra.sys.classetransfert.Gestionerreur_T;
import srcastra.astra.sys.compress.CompressArray;
import srcastra.astra.sys.rmi.utils.Poolconnection;
import srcastra.astra.sys.rmi.Exception.*;
/**
 *
 * @author  David
 */
public interface Signalitique {
    
    ArrayList renvcombo(int urcleunik,int urlmcleunik,char plettre,String cxcode,int cas,Poolconnection connect) throws RemoteException;
    
    Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas,Poolconnection connect) throws RemoteException;
    
    CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas,Poolconnection connect) throws RemoteException;
    
    java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik,Poolconnection connect) throws RemoteException;
    
    Object ChargeObject(int urlmcleunik,int urcleunik,int objcleunik,int cas,Poolconnection connect)throws RemoteException;
    
    Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik,Poolconnection connect) throws RemoteException;
    
    Object ChargeObjectPopup(int urlmcleunik, int urcleunik, int objcleunik, int cas,Poolconnection connect) throws RemoteException;
    
    Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas,Poolconnection connect) throws RemoteException;
    
    void deleteSignaletique(long objectCleunik,int typeObjec,Poolconnection connect) throws ServeurSqlFailure;
    // private CompressArray renvSignalitiqueCodepostaux(int urlmcleunik,Connection usercon,int cas, ServeurBuffer buf,String buffer);
    
}
