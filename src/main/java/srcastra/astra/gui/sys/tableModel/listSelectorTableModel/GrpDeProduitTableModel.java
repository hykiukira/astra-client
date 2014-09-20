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
public class GrpDeProduitTableModel extends AbstractAstraTableModel {
    
       
    /** Creates a new instance of CodePostauxTableModel */
    public GrpDeProduitTableModel(astrainterface serveur, Loginusers_T currentUser) {
        super(serveur, currentUser);
    }
    
    protected String[] loadColumnNames() {
        try {
            java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("srcastra/astra/locale/tableHeader/signaletique/GrpProduitTableHeader", currentUser.getLangage());
            return new String[] { rb.getString("1"), rb.getString("2") };
        }
        catch (java.util.MissingResourceException e) {
            ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, e);            
        }
        return new String[] {"Titre du Catalogue", "Référence"};
    }
    
    protected ArrayList loadData() {
        ArrayList retVal = new ArrayList(0);
        System.out.println(serveur);
        try {
            retVal = serveur.renvcombo('g', currentUser.getUrcleunik(), currentUser.getUrlmcleunik(), '#', "" + frCleUnik, 1);
        }
        catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(null,  ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
        }
        catch (Exception e) {
            ErreurScreenLibrary.displayErreur(null,  ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);
        }
        finally {
            return retVal;
        }            
    }
    
    protected int[] getColumnMask() {
        return new int[0];
    }
    
    /** Getter for property frCleUnik.
     * @return Value of property frCleUnik.
     */
    public int getFrCleUnik() {
        return frCleUnik;
    }
    
    /** Setter for property frCleUnik.
     * @param frCleUnik New value of property frCleUnik.
     */
    public void setFrCleUnik(int frCleUnik) {
        this.frCleUnik = frCleUnik;
    }
    
    protected int frCleUnik = 0;
    
}
