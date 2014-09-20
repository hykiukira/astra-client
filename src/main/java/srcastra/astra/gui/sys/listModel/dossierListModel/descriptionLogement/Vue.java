/*
 * TypeBListModel.java
 *
 * Created on 6 septembre 2002, 9:59
 */

package srcastra.astra.gui.sys.listModel.dossierListModel.descriptionLogement;
import srcastra.astra.gui.sys.listModel.AbstractAstraListModel;
import srcastra.astra.gui.sys.AString;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.classetransfert.Loginusers_T;

/**
 *
 * @author  Sébastien
 */
public class Vue extends AbstractAstraListModel {
    
    /** Creates a new instance of TypeBListModel */
    public Vue(astrainterface serveur, Loginusers_T currentUser) {
        super(serveur, currentUser);
    }
    
    protected AString[] loadData() {
        AString[] retVal = new AString[6];
        retVal[0] = new AString(1, "***");
        retVal[1] = new AString(2, loadName("mer"));
        retVal[2] = new AString(3, loadName("jardin"));
        retVal[3] = new AString(4, loadName("montagne"));
        retVal[4] = new AString(5, loadName("piscine"));
        retVal[5] = new AString(6, loadName("lac"));
                
        return retVal;
    }  
    
    private String loadName(String key) {
        String retVal = ""; 
        try {
            retVal = java.util.ResourceBundle.getBundle("srcastra/astra/locale/tableHeader/dossier/descriptionLogement/Vue", currentUser.getLangage()).getString(key);
        }
        catch (java.util.MissingResourceException e) {
            e.printStackTrace();
        }
        finally {
            return retVal;
        }
    }
    
    /* mer
       jardin
       montagne
       piscine
       lac

     */
}
