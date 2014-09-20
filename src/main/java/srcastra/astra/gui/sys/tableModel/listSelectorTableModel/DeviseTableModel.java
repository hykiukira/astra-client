/*
 * LangueTableModel.java
 *
 * Created on 20 ao�t 2002, 16:08
 */

package srcastra.astra.gui.sys.tableModel.listSelectorTableModel;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import java.util.ArrayList;
import srcastra.astra.Astra;
import java.rmi.RemoteException;
import srcastra.astra.gui.sys.ErreurScreenLibrary;
import srcastra.astra.gui.sys.formVerification.ListSelectorMask;
import srcastra.astra.gui.sys.tableModel.AbstractAstraTableModel;
import srcastra.astra.gui.sys.ErreurScreenLibrary;

/**
 *
 * @author  S�bastien
 */
public class DeviseTableModel extends AbstractAstraTableModel {
    
    /** Creates a new instance of LangueTableModel */
    public DeviseTableModel(astrainterface serveur, Loginusers_T currentUser) {
        super(serveur, currentUser);
    }
    
    protected int[] getColumnMask() {
        return new int[0];
    }
    
    protected String[] loadColumnNames() {
        try {
            java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("srcastra/astra/locale/tableHeader/signaletique/DeviseTableHeader", currentUser.getLangage());
            return new String[] { rb.getString("1"), rb.getString("2") };
        }
        catch (java.util.MissingResourceException e) {
            ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, e);            
        }
        return new String[] {"Abr�viations", "Devises" };
    }
    
    protected ArrayList loadData() {
        ArrayList retVal = new ArrayList(0);
        System.out.println(serveur);
        try {
            retVal = serveur.renvSignalitiques(currentUser.getUrlmcleunik(),currentUser.getUrcleunik(),1,astrainterface.COMBO_DEVISE,true);
        }
        catch (RemoteException e) {
            ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.REMOTE_EXCEPTION, Astra.DEBUG, e, currentUser);
        }
        catch (Exception e) {
            ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.EXCEPTION, Astra.DEBUG, e, currentUser);
        }
        finally {
            return retVal;
        }            
    }
    
}
