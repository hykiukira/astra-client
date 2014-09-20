/*
 * CodePostauxTableModel.java
 *
 * Created on 14 août 2002, 14:14
 */

package srcastra.astra.gui.sys.tableModel.listSelectorTableModel;

import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.classetransfert.clients.Clients_T;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import java.util.ArrayList;
import srcastra.astra.Astra;
import java.rmi.RemoteException;
import srcastra.astra.gui.sys.ErreurScreenLibrary;
import srcastra.astra.gui.sys.formVerification.ListSelectorMask;
import srcastra.astra.sys.Logger;
import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;
import srcastra.astra.sys.manipuleclient.ClientConstante;
import srcastra.astra.gui.sys.tableModel.AbstractAstraTableModel;
import srcastra.astra.gui.sys.ErreurScreenLibrary;

/**
 *
 * @author  Sébastien
 */
public class RoutingTableModel extends AbstractAstraTableModel {
    
       
    /** Creates a new instance of CodePostauxTableModel */
    public RoutingTableModel(astrainterface serveur, Loginusers_T currentUser) {
        super(serveur, currentUser);
    }
    
    protected String[] loadColumnNames() {
        try {
            java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("srcastra/astra/locale/tableHeader/signaletique/RoutingTableHeader", currentUser.getLangage());
            return new String[] { rb.getString("1"), rb.getString("2") };
        }
        catch (java.util.MissingResourceException e) {
            ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, e);            
        }
        return new String[] {"Abréviation", "Intitulé"};
    }
    
    protected ArrayList loadData() {
        ArrayList retVal = new ArrayList(0);
        try{
        retVal=serveur.renvSignalitiques(currentUser.getUrlmcleunik(),currentUser.getUrcleunik(),1,serveur.COMBO_DESTINATION,true);
        }catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(null,  ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
        }
        
        /* Temporaire ------------- */
       // retVal.add(new Object[] {"1", "BRU", "BRUXELLES" });
       // retVal.add(new Object[] {"2", "CHI", "CHIGAGO" });
        
        /* Temporaire fin -----------*/
        System.out.println(serveur);
/*        try {
            retVal = new srcastra.astra.sys.classetransfert.clients.Sous_Client_T().selectAllObject(serveur, currentUser,null,ClientConstante.RENV_ALL_CLIENTS_ORD_BY_NAME_WITH_ADRESS);
        }
        catch(ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(null,  ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);
        }
        catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(null,  ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
        }
        catch (Exception e) {
            ErreurScreenLibrary.displayErreur(null,  ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);
        }
        finally { */
            return retVal;
      /*  }            */
    }
    
    protected int[] getColumnMask() {
        return new int[0];
    }
    
}
