/*
 * DossierIndexTableModel.java
 *
 * Created on 30 août 2002, 9:49
 */

package srcastra.astra.gui.sys.tableModel.dossierTableModel;

import srcastra.astra.gui.sys.tableModel.AbstractAstraTableModel;
import java.util.ArrayList;
import srcastra.astra.gui.sys.ErreurScreenLibrary;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.gui.sys.ErreurScreenLibrary;

/**
 *
 * @author  Sébastien
 */
public class DossierTypeDeProduitTableModel extends AbstractAstraTableModel {
    
    /** Creates a new instance of DossierIndexTableModel */
    public DossierTypeDeProduitTableModel(astrainterface serveur, Loginusers_T currentUser) {
        super(serveur, currentUser);
    }
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Méthodes Surchargées
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    protected int[] getColumnMask() {
        return new int[0];
    }
    
    
    protected String[] loadColumnNames() {
        /*try {
            java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("srcastra/astra/locale/tableHeader/dossier/DossierIndexTableTableHeader", currentUser.getLangage());
            return new String[] { rb.getString("1"), rb.getString("2"), rb.getString("3"), rb.getString("4"), rb.getString("5"), rb.getString("6"), rb.getString("7"), rb.getString("8"), rb.getString("9") };
        }
        catch (java.util.MissingResourceException e) {
            ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, e);            
        }
         */
        return new String[] { "Abréviation", "Intitulé" };
    }
    
    protected ArrayList loadData() {
        data = new ArrayList(0);
       /* try {
            data = serveur.renvSignalitiques(currentUser.getUrlmcleunik(), currentUser.getUrcleunik(), 1, serveur.COMBO_CATEGORIE_PRODUIT);
        }
        catch (java.rmi.RemoteException e) {
            ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, e);
        }
        catch (Exception e) {
            ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.EXCEPTION, srcastra.astra.Astra.DEBUG, e);
        }*/

        data.add(new Object[] { new Integer(8), loadNames("as"), loadNames("as_full") } );
        data.add(new Object[] { new Integer(1), loadNames("av"), loadNames("av_full") } ); 
        data.add(new Object[] { new Integer(5), loadNames("ba") , loadNames("ba_full") } );
        data.add(new Object[] { new Integer(2), loadNames("bro"), loadNames("bro_full") } );
        data.add(new Object[] { new Integer(3), loadNames("ho"), loadNames("ho_full") } );
        data.add(new Object[] { new Integer(4), loadNames("tax"), loadNames("tax_full") } );
        data.add(new Object[] { new Integer(7), loadNames("tr"), loadNames("tr_full") } );
        data.add(new Object[] { new Integer(6), loadNames("vo"), loadNames("vo_full") } );        
        
    return data;
    }
    public String returnValueInArray(int i)
    {
        Object[] tmpObj=(Object[])data.get(i);
        String returnvalue=tmpObj[1].toString();
        return returnvalue;
        
    }

    
    private String loadNames(String key) {
        String retVal = "";
        try {
            retVal = java.util.ResourceBundle.getBundle("srcastra/astra/locale/ModuleDossier/ProduitAffichage", currentUser.getLangage()).getString(key);
        }
        catch (java.util.MissingResourceException e) {
        }
        finally {
            return retVal;
        }
    }
    

public ArrayList data;   

}
