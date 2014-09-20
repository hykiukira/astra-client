/*
 * TvaBelgium.java
 *
 * Created on 15 mai 2002, 10:01
 */

package srcastra.astra.gui.sys.formVerification;
import javax.swing.text.*;
import srcastra.astra.gui.components.textFields.ATextFieldComponent;
import java.util.Locale;

/**
 *
 * @author  Sébastien
 * @version 
 */

public class TvaBelgium extends DefaultMask {
    protected int separator;
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// CONSTRUCTORS
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
  
    /** Creates new TvaBelgium */
    public TvaBelgium(int minLenght, int maxLenght, Locale locale) {
        super(minLenght, maxLenght, locale, DefaultMask.CASE_UNSENSITIVE);
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// METHODES APPARENTE AU BEANS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        String currentText = getText(0, getLength());
        String beforeOffset = currentText.substring(0, offs);
        String afterOffset = currentText.substring(offs, currentText.length());
        
        boolean validate = false;
        // regardons si il s'agit d'une action de validation
        if (str.equals(DefaultMask.VALIDATE)) validate = true;
        str = validate ? "" : str;
        String proposedResult = beforeOffset + str + afterOffset;
        String tmpResult = extractSeparator(proposedResult);
       
        super.insertStringWithoutModification(offs, str, a);
    }
    
    private String extractSeparator(String tva) {
        try {
            char[] ch = tva.toCharArray();
            String tvaNum = "";
            separator = 0;
            for (int i=0; i < ch.length; i++) {
                if (Character.isDigit(ch[i])) {
                    tvaNum += String.valueOf(ch[i]);
                }
                else if (ch[i] == '.') {
                    separator++;
                }                    
            }
            return tvaNum;
        }
        catch (Exception e) {
            return "";
        }
    }
    
    
    private boolean isCorrect(String tva) {
        if (tva.length() == 9) {
            // conversion et split de la chaîne de cararctère en XXXXXX et XXX
            try {
                int tvaNum = Integer.parseInt(tva.substring(0,7));
                int tvaInd = Integer.parseInt(tva.substring(7));
                // initiation du résultat final
                int tvaResult = 0;
            
                // Calcul de vérification
                tvaResult = tvaNum / 97;
                tvaResult = tvaResult * 97;
                tvaResult = tvaNum - tvaResult;
                if ((97 - tvaResult) == tvaInd) {
                    // numéro de tva Belge valide
                    return true;
                }
            }
            catch (NumberFormatException nfe) {
                return false;
            }
        }
        return false;
            
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// STATIC VARIABLES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    /** value for erreur code */
    public static int ERREUR_CODE = FormVerification.ERREUR_STRING;
}
