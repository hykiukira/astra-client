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
 * @author  S�bastien
 */
public class Regime extends AbstractAstraListModel {
    
    /** Creates a new instance of TypeBListModel */
    public Regime(astrainterface serveur, Loginusers_T currentUser) {
        super(serveur, currentUser);
    }
    
    protected AString[] loadData() {
        AString[] retVal = new AString[6];
        retVal[0] = new AString(1, loadName("AI_libel"));
        retVal[1] = new AString(2, loadName("CD_libel"));
        retVal[2] = new AString(3, loadName("DP_libel"));
        retVal[3] = new AString(4, loadName("LO_libel"));
        retVal[4] = new AString(5, loadName("PC_libel"));
        retVal[5] = new AString(6, loadName("PR_libel"));
        
        return retVal;
    }  
    
    private String loadName(String key) {
        String retVal = ""; 
        try {
        retVal = java.util.ResourceBundle.getBundle("srcastra/astra/locale/tableHeader/dossier/descriptionLogement/Regime", currentUser.getLangage()).getString(key);
        }
        catch (java.util.MissingResourceException e) {
            e.printStackTrace();
        }
        finally {
            return retVal;
        }
    }
    
    /* AI_abrev
    AI_libele
    CD_abrev
    CD_libel
    DP_abrev
    DP_libel
    LO_abrev
    LO_libel
    PC_abrev
    PC_libel
    PR_abrev
    PR_libel


     */
}
