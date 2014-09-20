/*
 * IntegerMask.java
 *
 * Created on 10 mai 2002, 16:38
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

public class StringMask extends DefaultMask {
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// CONSTRUCTORS
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    /** Creates new IntegerMask */
    public StringMask(int minLenght, int maxLenght, Locale locale,int caseType) {
        super(minLenght, maxLenght, locale, caseType);
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
            if (Character.isLetter(source[i])) {
                result[z++] = source[i];
                System.out.println(source[i]);
            }
            else {
                java.awt.Toolkit.getDefaultToolkit().beep();
                fit = false;
            }
        }
            
        if (fit) super.insertString(offs, new String(result, 0, z), a);
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// STATIC VARIABLES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    /** value for erreur code */
    public static int ERREUR_CODE = FormVerification.ERREUR_STRING;
}
