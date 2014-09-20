/*
 * AbstractAstraListModel.java
 *
 * Created on 6 septembre 2002, 10:01
 */

package srcastra.astra.gui.sys.listModel;
import javax.swing.DefaultListModel;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.gui.sys.AString;

/**
 *
 * @author  Sébastien
 */
public abstract class AbstractAstraListModel2 extends DefaultListModel {
    
    /** Creates a new instance of AbstractAstraListModel */
   protected int m_typeProduit;
    public AbstractAstraListModel2(astrainterface serveur, Loginusers_T currentUser,int typeproduit) {
        this.serveur = serveur;
        this.currentUser = currentUser;
        m_typeProduit=typeproduit;
        needData();
    }
    
    public Object getElementAt(int param) {
        needData();
        return dataContainer[param];
    }
    
    public int getSize() {
        needData();
        if(dataContainer!=null)
            return dataContainer.length;
        else
            return 0;
    }
    
    public void needData() {
        if (dataContainer == null) dataContainer = loadData();
    }
    
    public void clearList() {
        dataContainer = null;
    }
    
    public AString getObjectFromCleUnik(int cleUnik) {
        for (int i=0; i < dataContainer.length; i++) {
            if (dataContainer[i].getCleunik() == cleUnik) return dataContainer[i];
        }
        return null;
    }
    
    public int getObjectAt(AString obj) {
        for (int i=0; i < dataContainer.length; i++) {
            if (dataContainer[i].equals(obj)) return i;
        }
        return -1;
    }
    
    public AString[] getData() {
        return dataContainer;
    }
    
    
    protected String getNameValue(java.util.ResourceBundle bundle, String key) {
        String retVal = "";
        try {
            retVal = bundle.getString(key);
        }
        finally {
            return retVal;
        }
    }
    
    protected abstract AString[] loadData();
    
    protected astrainterface serveur;
    protected Loginusers_T currentUser;
    protected AString[] dataContainer;
       
    
    
}
