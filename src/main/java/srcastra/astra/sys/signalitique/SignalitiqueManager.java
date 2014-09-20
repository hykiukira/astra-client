/*
 * SignalitiqueManager.java
 *
 * Created on 21 août 2002, 15:34
 */

package srcastra.astra.sys.signalitique;
import srcastra.astra.sys.signalitique.Signalitique;
import java.util.ArrayList;
import java.rmi.RemoteException;
import srcastra.astra.sys.classetransfert.Gestionerreur_T;
import srcastra.astra.sys.compress.CompressArray;
import srcastra.astra.sys.rmi.utils.Poolconnection;
import srcastra.astra.sys.rmi.utils.ManagePoolconnection;
import srcastra.astra.sys.rmi.utils.ServeurBuffer;
import srcastra.astra.sys.Logger;
import srcastra.astra.sys.rmi.Exception.*;
/**
 *
 * @author  David
 *
 * @version phoenix
 */
public class SignalitiqueManager {
    
    /** Creates a new instance of SignalitiqueManager */
    private java.util.Hashtable signLettre = new java.util.Hashtable();
    private java.util.Hashtable signInt = new java.util.Hashtable();
    private ManagePoolconnection managepool;
    
    public SignalitiqueManager() {
    }
    /**
     * Add a signalitique object that can handle the specified char as for renvcombo and the specified
     * int for the reste.
     * If there is no handle for the char, use ?.
     * If there is no handle for the int use -1;
     */
    public void addSignalitique(char lettreID, int intID, srcastra.astra.sys.signalitique.Signalitique signalitique){
        signLettre.put(new Character(lettreID),signalitique);
        signInt.put(new Integer(intID),signalitique);
    }
    
    /** Gets a combo according to specified typedecombo and parameters.
     * typedecombo is matched against the list of know signalitique handlers.
     * If it can be found, the handler is called. Else, an exception is thown
     * @throws InvalidSignalitiqueOpoeration if there is no handle for this
     *         typedecombo or the handler does not support renvcombo
     */
    public ArrayList renvcombo(char typedecombo,int urcleunik,int urlmcleunik,char plettre,String cxcode,int cas,Poolconnection connect) throws RemoteException{
        ServeurBuffer buf=connect.getBuffer();
        ArrayList tmp=null;
        java.util.Date date1;
        java.util.Date date2;
        date1=new java.util.Date();
        Signalitique sign = ((Signalitique)signLettre.get(new Character(typedecombo)));
        if (sign==null)
            throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("Combotype "+typedecombo + ": No manager for renvcombo");
        tmp = sign.renvcombo(urcleunik, urlmcleunik, plettre, cxcode, cas, connect);
        date2=new java.util.Date();
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"fin de renvcombo");
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"Time used: "+((float)(date2.getTime()-date1.getTime()))/1000+" secs.");
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG,tmp.size());
        return tmp;
    }
    /** Insert an opbject of the specified signalitique type.
     * TypObject is matched against the list of know signalitique handlers.
     * If it can be found, the handler is called. Else, an exception is thrown
     * @throws InvalidSignalitiqueOpoeration if there is no handler for this
     *         TypObject or the handler does not support insertObjectPopup
     */
    public Gestionerreur_T insertObjectPopup(Object objdp,int urcleunik,int TypObject,int cas,Poolconnection connect) throws RemoteException{
        
        boolean x=false;
        String[] sqlrequete;
        String sqlrequete2;
        Gestionerreur_T tmpret=null;
        ServeurBuffer buf=connect.getBuffer();
        ArrayList tmplist=null;
        Signalitique s=(Signalitique)signInt.get(new Integer(TypObject));
        if (s==null)
            throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("InsertObjectPopup "+TypObject + ": No manager for renvcombo");
        return s.insertObjectPopup(objdp, urcleunik, cas, connect);
    }
    
    /** Loads an object of the specified signalitique type.
     * comboConstante is matched against the list of know signalitique handlers.
     * If it can be found, the handler is called. Else, an exception is thrown
     * @throws InvalidSignalitiqueOpoeration if there is no handler for this
     *         comboConstante or the handler does not support ChargeObject
     */
    public Object ChargeObject(int urlmcleunik,int urcleunik,int objcleunik,int cas,int comboConstante,Poolconnection connect)throws RemoteException{
        
        Signalitique s=(Signalitique)signInt.get(new Integer(comboConstante));
        if (s==null)
            throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("ChargeObject "+comboConstante + ": No manager for renvcombo");
        
        /*switch(comboConstante) {//a virer
         
            case GRP_PROD_DEF: /// <---- !!!!!!!!!!----!!!!!---!!!--!
                if(cas==1) {
                    returnValue=(Grpdecision_T)ChargeDefaultGrpFourn(tmpool.getConuser(),objcleunik);
                }
        }*/
        
        return s.ChargeObject(urlmcleunik,urcleunik,objcleunik,cas, connect);
    }
    
    /** Loads an object of the specified signalitique type.
     * comboConstante is matched against the list of know signalitique handlers.
     * If it can be found, the handler is called. Else, an exception is thrown
     * @throws InvalidSignalitiqueOpoeration if there is no handler for this
     *         comboConstante or the handler does not support ChargeObjectCombo
     */
    public Object chargeObjetCombo(int objectCleunik,int urcleunik,int urlmcleunik,int comboConstante,Poolconnection connect) throws RemoteException{
        Signalitique s=(Signalitique)signInt.get(new Integer(comboConstante));
        if (s==null)
            throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("chargeObjectCombo "+comboConstante + ": No manager for renvcombo");
        
        return s.chargeObjetCombo(objectCleunik,urcleunik,urlmcleunik, connect);
    }
    
    /** Loads an object of the specified signalitique type.
     * comboConstante is matched against the list of know signalitique handlers.
     * If it can be found, the handler is called. Else, an exception is thrown
     * @throws InvalidSignalitiqueOpoeration if there is no handler for this
     *         comboConstante or the handler does not support ChargeObjectPopup
     */
    public Object ChargeObjectPopup(int urlmcleunik,int urcleunik,int objcleunik,int cas,int comboConstante,Poolconnection connect)throws RemoteException{
        Signalitique s=(Signalitique)signInt.get(new Integer(comboConstante));
        if (s==null)
            throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("chargeObjectPopup "+comboConstante + ": No manager for renvcombo");
        
        return s.ChargeObjectPopup(urlmcleunik,urcleunik,objcleunik,cas, connect);
    }
     public void deleteSignaletique(long objectCleunik, int typeObject, Poolconnection connect) throws ServeurSqlFailure,RemoteException {
        Signalitique s=(Signalitique)signInt.get(new Integer(typeObject));
        if (s==null)
            throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("deletesignaletique "+typeObject + ": No manager for renvcombo");
        
        s.deleteSignaletique(objectCleunik,typeObject,connect);
     }
    public CompressArray renvSignalitiques(int urlmcleunik,int urcleunik,int cas,int typedesignalitique,Poolconnection connect) throws RemoteException{
        
        /*String signature="renvSignalitique."
        +" type: "+typedesignalitique
        +" cas: "+cas;*/
        Signalitique s=(Signalitique)signInt.get(new Integer(typedesignalitique));
        if (s==null)
            throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("renvSignalitique "+typedesignalitique + ": No manager for renvcombo");
        
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"renv signalitique");
        return s.renvSignalitiques(urlmcleunik, urcleunik, cas, connect);
        
    }
    public Gestionerreur_T modifyObjectPopup(Object objdp,int urcleunik,int TypObject,int cas,Poolconnection connect) throws RemoteException{
        
        Signalitique s=(Signalitique)signInt.get(new Integer(TypObject));
        if (s==null)
            throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("modifyObjectPopup "+TypObject + ": No manager for renvcombo");
        
        return s.modifyObjectPopup(objdp,urcleunik,cas, connect);
    }
    public java.util.ArrayList renvIntitule(int  urlmcleunik,int urcleunik,int cas,int typedesignalitique,int caecleunik,Poolconnection connect) throws RemoteException{
        Signalitique s=(Signalitique)signInt.get(new Integer(typedesignalitique));
        if (s==null)
            throw new srcastra.astra.sys.rmi.Exception.InvalidSignalitiqueOperation("renvIntitule "+typedesignalitique + ": No manager for renvcombo");
        
        return s.renvIntitule(urlmcleunik,urcleunik,cas,caecleunik, connect);
    }
    
}

