/*
 * IntegerMask.java
 *
 * Created on 10 mai 2002, 16:38
 */
package srcastra.astra.gui.sys.formVerification;
import javax.swing.text.*;
import java.util.Locale;

/**
 *
 * @author  Sébastien
 * @version 
 */
public class IntegerMask extends DefaultMask {
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// CONSTRUCTORS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    
    /** Creates new IntegerMask */
    public IntegerMask(int minLenght, int maxLenght) {
        super(minLenght, maxLenght, DefaultMask.CASE_UNSENSITIVE);
    }
    
    /** Creates new IntegerMask */
    public IntegerMask(int minLenght, int maxLenght, Locale locale) {
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
        
        boolean fit = true;
        
        char[] source = str.toCharArray();
        char[] result = new char[source.length];
        
        int z=0;
        for (int i=0; i < source.length; i++) {
            if (Character.isDigit(source[i])) {
                result[z++] = source[i];
            }
            else {
                java.awt.Toolkit.getDefaultToolkit().beep();
                fit =false;
            }
        }
        String res = new String(result, 0 , z);
        if (validate && proposedResult.equals("")) res = "0";
        if (fit) super.insertString(offs, res, a);
    }
    
     
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// STATIC VARIABLES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    /** value for erreur code */
    public static int ERREUR_CODE = FormVerification.ERREUR_INT;
    
}
