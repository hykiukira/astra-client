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
public class TypeClasseListModel extends AbstractAstraListModel {
    
    /** Creates a new instance of TypeBListModel */
    public TypeClasseListModel(astrainterface serveur, Loginusers_T currentUser) {
        super(serveur, currentUser);
    }
    
    protected AString[] loadData() {
        AString[] retVal = new AString[3];
        retVal[0] = new AString(1, "1 °");
        retVal[1] = new AString(2, "2 °");
        retVal[2] = new AString(3, "Business");
        
        return retVal;
    }    
    
}
