/*
 * SignaletiqueTypeProduit.java
 *
 * Created on 4 septembre 2002, 13:14
 */

package srcastra.astra.sys.signalitique;
import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.Logger;
import srcastra.astra.sys.rmi.utils.Poolconnection;
import java.rmi.RemoteException;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.sys.compress.CompressArray;
import java.util.ArrayList;
import java.sql.*;
import srcastra.astra.sys.rmi.utils.ServeurBuffer;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.rmi.utils.QueryKeyGen;
import srcastra.astra.sys.rmi.Exception.*;
/**
 *
 * @author  Thomas
 */
public class SignaletiqueTypeProduit implements srcastra.astra.sys.signalitique.Signalitique{
    
    /** Creates a new instance of SignaletiqueTypeProduit */
    public SignaletiqueTypeProduit() {
    }
   
    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
     return null;
    }
    
    public Object ChargeObjectPopup(int urlmcleunik, int urcleunik, int objcleunik, int cas, Poolconnection connect) throws RemoteException {
     return null;
    }
    
    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik, Poolconnection connect) throws RemoteException {
     return null;
    }
    
    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
     return null;
    }
    
    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
     return null;
    }
    
    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int caecleunik, Poolconnection connect) throws RemoteException {
     return null;
    }
    
    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, Poolconnection connect) throws RemoteException {
        return renvSignalitiquesTypeProduit(urlmcleunik,connect.getConuser(),cas, connect.getBuffer());
    }
    
    public ArrayList renvcombo(int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, Poolconnection connect) throws RemoteException {
     return null;
    }
     public CompressArray renvSignalitiquesTypeProduit(int urlmcleunik,Connection usercon, int cas, ServeurBuffer buf) throws RemoteException {   
        String tmplangue;
        String sqlrequete=null;
        CompressArray cp;
        String signature=QueryKeyGen.renvSignalitiques(urlmcleunik, 0, cas, astrainterface.COMBO_DEVISE );
        synchronized (buf) {
            if(cas==1) {
                if (buf.isValid("signTypeProduitCas1"))
                    cp=buf.getValue("signTypeProduitCas1");
                else {
                    sqlrequete="select c.ctpro_cleunik,c.ctpro_abrev,tc.trat_traduction"
                    +" from categorie_produit c,traduction_categorieproduit tc"
                    +" where c.ctpro_cleunik=tc.ctpro_cleunik  and tc.lmcleunik="+urlmcleunik
                    +" order by c.ctpro_abrev;";
                    cp=Transaction.generecombostest(sqlrequete,usercon);
                    buf.setValue("signTypeProduitCas1",cp);
                    buf.linkNewName(signature,"ProduitDir");
                    buf.setValue(signature, new Long(System.currentTimeMillis()));                   
                }
            }
            else if(cas==2) {
                if (buf.isValid("signTypeProduitCas2"))
                    cp=buf.getValue("signTypeProduitCas2");
                else {
                   sqlrequete="select c.ct_cleunik,tc.trat_traduction,c.ct_abrev"
                    +" from categorie_produit c,traduction_categorieproduit tc"
                    +" where c.ct_cleunik=tc.ct_cleunik  and tc.lmcleunik="+urlmcleunik
                    +" order by tc.trat_traduction;";
                    cp=Transaction.generecombostest(sqlrequete,usercon);
                    buf.setValue("signTypeProduitCas2",cp);
                    buf.linkNewName(signature,"ProduitDir");
                    buf.setValue(signature, new Long(System.currentTimeMillis()));
                }
            }
            else
                cp=null;
        }
        return cp;
    }
    
     public void deleteSignaletique(long objectCleunik, int typeObjec, Poolconnection connect) throws ServeurSqlFailure {
     }
     
}
