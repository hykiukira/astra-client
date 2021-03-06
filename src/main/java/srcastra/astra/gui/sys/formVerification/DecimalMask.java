/*
 * DecimalMask.java
 *
 * Created on 13 mai 2002, 12:33
 */
package srcastra.astra.gui.sys.formVerification;
import java.text.*;
import javax.swing.text.*;
import srcastra.astra.gui.components.textFields.ATextFieldComponent;
import java.util.Locale;
/**
 *
 * @author  Sébastien
 * @version 
 */
public class DecimalMask extends DefaultMask {
    private NumberFormat decimalFormat;
    
    /** Creates new DecimalMask */
    public DecimalMask(int chiffreAvantVirgule, int chiffreApresVirgule, Locale locale) {
        super(chiffreAvantVirgule, chiffreApresVirgule, locale, DefaultMask.CASE_UNSENSITIVE);
        setUpFormat();
    }
    
    
    private void setUpFormat() {
        decimalFormat = NumberFormat.getNumberInstance();
        ((DecimalFormat) decimalFormat).setMaximumIntegerDigits(2);
        ((DecimalFormat) decimalFormat).setMaximumFractionDigits(2);
    }
    
    public Double getValue() {
        double retVal = 0.0;
        return new Double(retVal);
    }
    
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str.equals(",")) {
            str = ".";
        }        
        String currentText = getText(0, getLength());
        int separator = currentText.indexOf('.');
        String beforeOffset = currentText.substring(0, offs);
        String afterOffset = currentText.substring(offs, currentText.length());
                
        boolean validate = false;
        // regardons si il s'agit d'une action de validation
        if (str.equals(DefaultMask.VALIDATE)) validate = true;
        str = validate ? "" : str;
        String proposedResult = beforeOffset + str + afterOffset;
        
        // separator = ((separator < 0) && (str.equals("."))) ? proposedResult.indexOf('.') : separator;
        
        System.out.println("Résultat proposé : " + proposedResult);
        
        try {
            // séparateur
            if (separator >= 0 && str.length() == 1) {
                // verif sur nbre entier
               if ((offs < separator + 1) && (proposedResult.substring(0, separator + 1).length() > super.getMinLenght())) {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    confirmCorrectInput(false);
                    System.out.println("1");
                }
                else if ((offs > separator) && (proposedResult.substring(separator, proposedResult.length()).length() - 1 > super.getMaxLenght())) {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    confirmCorrectInput(false);
                    System.out.println("2");
                }
                else {
                    Float.valueOf(proposedResult);
                    super.insertStringWithoutModification(offs, str, a);
                    confirmCorrectInput(true);
                    System.out.println("3");
                }
            }
            // pas de séparateur mais séparateur == str 
            else if (separator <= 0 && str.equals(".")) {
                if (separator == 0) {
                    str = "0" + str;
                }
                Float.valueOf(proposedResult);
                super.insertStringWithoutModification(offs, str, a);
                confirmCorrectInput(true);
                System.out.println("4");
            }
            // pas de séparateur dc nombre entier 
            else if (separator < 0 && proposedResult.length() > super.getMinLenght() && str.indexOf('.') < 0) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                confirmCorrectInput(false);
                System.out.println("5");
            }
            else {
                Float.valueOf(proposedResult);
                super.insertStringWithoutModification(offs, str, a);
                confirmCorrectInput(true);
                System.out.println("6");
            }
                
        }
        catch (NumberFormatException pe) {
            if (validate && proposedResult.equals("")) {
                super.insertStringWithoutModification(offs, "0", a);
                confirmCorrectInput(true);
            }
            else {
                java.awt.Toolkit.getDefaultToolkit().beep();
                confirmCorrectInput(false);
            }
        }
    }
    
    public void remove (int offs, int len) throws BadLocationException {
        String currentText = getText(0, getLength());
        String beforeOffset = currentText.substring(0, offs);
        String afterOffset = currentText.substring(len + offs, currentText.length());
        String proposedResult = beforeOffset + afterOffset;
        int separator = proposedResult.indexOf('.');
       
         try {
            // séparateur
            if (separator >= 0) {
                // verif sur nbre entier
                if ((offs < separator + 1) && (proposedResult.substring(0, separator).length() > super.getMinLenght())) {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    confirmCorrectInput(false);
                }
                else if ((offs > separator) && (proposedResult.substring(separator, proposedResult.length()).length() - 1 > super.getMaxLenght())) {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    confirmCorrectInput(false);
                }
                else if (separator == 0) {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    confirmCorrectInput(false);
                }
                else {
                        Float.valueOf(proposedResult);
                        super.remove(offs, len);
                        confirmCorrectInput(true);
                }
            }
            
            // pas de séparateur dc nombre entier 
            else if (separator < 0 && proposedResult.length() > super.getMinLenght()) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                confirmCorrectInput(false);
            }
            else {
                 if (proposedResult.length() == 0) {
                        super.remove(offs, len);
                        confirmCorrectInput(true);
                 }
                 else {
                     Float.valueOf(proposedResult);
                     super.remove(offs, len);
                     confirmCorrectInput(true);
                 }
            }
                
        }
        catch (NumberFormatException pe) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            confirmCorrectInput(false);
        }       
    }
    
  
}
