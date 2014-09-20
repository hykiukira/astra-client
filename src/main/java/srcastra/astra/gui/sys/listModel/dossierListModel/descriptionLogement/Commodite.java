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
public class Commodite extends AbstractAstraListModel implements Abrev {
    
    /** Creates a new instance of TypeBListModel */
    public Commodite(astrainterface serveur, Loginusers_T currentUser) {
        super(serveur, currentUser);
    }
    
    protected AString[] loadData() {
        AString[] retVal = new AString[7];
        retVal[0] = new AString(1, "***");
        retVal[1] = new AString(2, loadName("bain_libel"));
        retVal[2] = new AString(3, loadName("douche_libel"));
        retVal[3] = new AString(4, loadName("lavabo_libel"));
        retVal[4] = new AString(5, loadName("badWc_libel"));
        retVal[5] = new AString(6, loadName("doucheWc_libel"));
        retVal[6] = new AString(7, loadName("lavWc_libel"));
        
        return retVal;
    }  
    
    private String loadName(String key) {
        String retVal = ""; 
        try {
            retVal = java.util.ResourceBundle.getBundle("srcastra/astra/locale/tableHeader/dossier/descriptionLogement/Commodite", currentUser.getLangage()).getString(key);
        }
        catch (java.util.MissingResourceException e) {
            e.printStackTrace();
        }
        finally {
            return retVal;
        }
    }
    
    public String loadAbrev(int cleUnik) {
         switch (cleUnik) {
            case 1 :
                return "";
            case 2 :
                return loadName("bain_abrev");
            case 3 :
                return loadName("douche_abrev");
            case 4 :
                return loadName("lavabo_abrev");
            case 5 :
                return loadName("badWc_abrev");
            case 6 :    
                return loadName("doucheWc_abrev");
            case 7 : 
                return loadName("lavWc");
            default :
                return "";
            
        }
    }
    
    /* bain_abrev
       bain_libel
       douche_abrev
       douche_libel
       lavabo_abrev
       lavabo_libel
       badWc_abrev
       badWc_libel
       doucheWc_abrev
       doucheWc_libel
       lavWc_abrev
       lavWc_libel

     */
}
