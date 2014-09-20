/*
 * GereStatut.java
 *
 * Created on 12 février 2003, 11:51
 */

package srcastra.astra.gui.test;
import srcastra.astra.gui.sys.*;
/**
 *
 * @author  thomas
 */
public class GereStatut {
    
    /** Creates a new instance of GereStatut */
    public GereStatut(java.util.Locale langue) {
        this.langue=langue;
        loadData();
    }
     protected AString[] loadData() {
        retVal = new AString[5];
        retVal[0] = new AString(1, loadName("1"));
        retVal[1] = new AString(2, loadName("2"));
        retVal[2] = new AString(3, loadName("3"));
        retVal[3] = new AString(4, loadName("4"));
        retVal[4] = new AString(5, loadName("5"));      
        return retVal;
    }    
    public String getStatut(int cleStatut){
       cleStatut=cleStatut-1;
       AString tmp=null;
       if(cleStatut >= 0 && cleStatut < retVal.length)
        tmp=retVal[cleStatut];
       else
        tmp=retVal[0];
       return tmp.toString();
    }
    private String loadName(String key) {
        String retVal = ""; 
        try {
            retVal = java.util.ResourceBundle.getBundle("srcastra/astra/locale/tableHeader/signaletique/StatutListHeader", langue).getString(key);
        }
        catch (java.util.MissingResourceException e) {
            e.printStackTrace();
        }
        finally {
            return retVal;
        }
    }
 java.util.Locale langue; 
 AString[] retVal;
}
