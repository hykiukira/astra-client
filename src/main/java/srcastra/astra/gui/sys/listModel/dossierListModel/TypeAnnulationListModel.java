/*
 * TypeBListModel.java
 *
 * Created on 6 septembre 2002, 9:59
 */

package srcastra.astra.gui.sys.listModel.dossierListModel;
import srcastra.astra.gui.sys.listModel.AbstractAstraListModel;
import srcastra.astra.gui.sys.AString;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.classetransfert.Loginusers_T;

/**
 *
 * @author  Sébastien
 */
public class TypeAnnulationListModel extends AbstractAstraListModel {
    
    /** Creates a new instance of TypeBListModel */
    public TypeAnnulationListModel(astrainterface serveur, Loginusers_T currentUser) {
        super(serveur, currentUser);
    }
    
    protected AString[] loadData() {
        AString[] retVal = new AString[2];
        retVal[0] = new AString(1, loadName("1"));
        retVal[1] = new AString(2, loadName("2"));
                
        return retVal;
    }    
    
    private String loadName(String key) {
        String retVal = ""; 
        try {
            retVal = java.util.ResourceBundle.getBundle("srcastra/astra/locale/tableHeader/signaletique/TypeAnnulationListHeader", currentUser.getLangage()).getString(key);
        }
        catch (java.util.MissingResourceException e) {
            e.printStackTrace();
        }
        finally {
            return retVal;
        }
    }
}
