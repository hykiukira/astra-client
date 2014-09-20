/*
* DateMask.java
*
* Created on 6 août 2002, 13:00
*/

package srcastra.astra.gui.sys.formVerification;

import java.util.Locale;
import javax.swing.text.*;
import srcastra.astra.gui.components.date.aDate.ADate;
import javax.swing.JTextField;
import java.util.Calendar;

/**
*
* @author  Sébastien
*/
public class DateMask extends DefaultMask {

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// CONSTRUCTORS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

/** Creates a new instance of DateMask */
    public DateMask(ADate aDate, int dateType, JTextField parent) {
    super(0, 0, aDate.getLocale(), DefaultMask.CASE_UNSENSITIVE);
    this.dateType = dateType;
    this.aDate = aDate;
    this.parent = parent;
}     


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// METHODES APPARENTE AU BEANS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
   /* public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        String currentText = getText(0, getLength());
        String beforeOffset = currentText.substring(0, offs);
        String afterOffset = currentText.substring(offs, currentText.length());

        boolean validate = false;
        boolean focus_lost = false;
        // regardons si il s'agit d'une action de validation
        if (str.equals(DefaultMask.VALIDATE)) validate = true;
        if (str.equals(DateMask.FOCUS_LOST)) {
            focus_lost = true;
            validate = true;
        }
        str = (validate || focus_lost) ? "" : str;
        String proposedResult = beforeOffset + str + afterOffset;

        System.out.println("proposed Result : " + proposedResult);
        int proposedNumber = 0;
        boolean fit = true;
        boolean noNumber = false;





        // transformation du résultat proposé en entier
        try {
            proposedNumber = Integer.parseInt(proposedResult);
        }
        catch (NumberFormatException ne) {
            fit = false;
        }

        finally {
            if (!fit && !validate) {
                super.getToolkit().beep();
                return;
            }
             // valeurs de verif
             int maxDate = 0;
             int minDate = 0;
             int reqLength = 0;

             // définition des valeurs de verif selon le dateType
                switch (dateType) {
                    case DateMask.DAY :
                        maxDate = 31;
                        minDate = 1;
                        reqLength = 2;
                        break;
                    case DateMask.MONTH :
                        maxDate = 12;
                        minDate = 1;
                        reqLength = 2;
                        break;
                    case DateMask.YEAR :
                        maxDate = 9999;
                        minDate = 0;
                        reqLength = 4;
                        break;
                }

            if (fit && proposedResult.length() == reqLength) {
                System.out.println("proposedResult.length() == reqLength");
                validate = true;
            }

            if (validate) {
                System.out.println("validate - validate - validate - validate - validate - validate - validate - validate - ");
                // verif !
                if (proposedNumber > maxDate) {
                    System.out.println("[DATEMASK] proposedNumber > maxDate");
                    super.removeAll();
                    super.insertStringWithoutModification(0, String.valueOf(maxDate), a);
                    currentText = getText(0, getLength());
                }
                else if (proposedNumber < minDate) {
                    System.out.println("[DATEMASK] proposedNumber < minDate");
                    super.removeAll();
                    super.insertStringWithoutModification(0, String.valueOf(minDate), a);
                    currentText = getText(0, getLength());
                }
                else if (noNumber) {
                    System.out.println("[DATEMASK] NO NUMBER");
                    super.insertStringWithoutModification(0, String.valueOf(minDate), a);
                }
                else {
                    System.out.println("[DATEMASK] currentText = proposedResult");
                    currentText = proposedResult;
                }
                    
                System.out.println("proposedNumber : " + proposedNumber + "proposedResult : " + proposedResult + "  -  reqLength : " + reqLength + "currentText : " + currentText);
                if (currentText.length() < reqLength) {
                    System.out.println("yooooooo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    String zero = "";
                    for (int i=0; i < reqLength - getLength(); i++) {
                        zero += "0";
                    }
                    super.insertStringWithoutModification(0, zero, a);
                }
                else if (fit && currentText.length() == reqLength) {
                    super.insertStringWithoutModification(offs, str, a);
                    if (!focus_lost) aDate.skipField(parent);
                }
            }
            else if (fit) {
                if (proposedResult.length() > reqLength) getToolkit().beep();
                else  super.insertStringWithoutModification(offs, str, a);
             }
            else getToolkit().beep();

        }

    }*/
  
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        // variables
        boolean validate = false;
        boolean focusLost = false;
        boolean alreadyChanged = false;
        String proposedResult = "";
        int proposedNumber = 0;
                
        //=> traitement de la chaîne str 
        // on repère s'il s'agit d'une action
        if (str.equals(VALIDATE)) {
            validate = true;
            str = "";
        }
        else if (str.equals(FOCUS_LOST)) {
            focusLost = true;
            validate = true;
            str = "";
        }
        
        //=> fabrication du résultat proposé
        String currentString = getText(0, getLength());
        String beforeOffset = currentString.substring(0, offs);
        String afterOffset = currentString.substring(offs, currentString.length());
        
        proposedResult = beforeOffset + str + afterOffset;
        
        //=> vérification de caractères spéciaux
        if (!unknown && !open && str.indexOf("?") == 0 && offs == 0) {
            System.out.println("[DATEMASK] transformation en type unknow !");
            unknown = true;
            aDate.makeUnknown();
            return;
        }
        else if (!unknown && !open && (str.indexOf("o") == 0 || str.indexOf("O") == 0) && offs == 0) {
            System.out.println("[DATEMASK] transformation en type open !");
            open = true;
            aDate.makeOpen();
            return;
        }
        
        System.out.println("[DATEMASK] => début de la fct => validate = " + validate + ", open = " + open + ", unknown = " + unknown + ", proposedResult = " + proposedResult + ", str = " + str + ", offs = " + offs );
        
        //=> transformation en numérique
        if (!open && !unknown) {
            System.out.println("[DATEMASK] pas unknow et pas open => je transforme en numérique");
            try {
                if (proposedResult.length() == 0) {
                    proposedNumber = 0;
                }
                else {
                    proposedNumber = Integer.parseInt(proposedResult);
                }
            }
            catch (NumberFormatException e) {
                System.out.println("[DATEMASK] le résultat proposé n'est pas un numérique => aurevoir");
                //getToolkit().beep();
                return;
            }
        }
        
        //=> initialisation des champs !
        System.out.println("[DATEMASK] oki j'initialise les champs");
        int maxDate = 0;
        int minDate = 0;
        int reqLength = 0;
        int trueDate = 0;
        switch (dateType) {
                case DateMask.DAY :
                    maxDate = 31;
                    minDate = 1;
                    reqLength = 2;
                    break;
                case DateMask.MONTH :
                    maxDate = 12;
                    minDate = 1;
                    reqLength = 2;
                    break;
                case DateMask.YEAR :
                    maxDate = 9999;
                    minDate = 0;
                    reqLength = 4;
                    trueDate = 2;
                    break;
        }
        
        if (validate || open || unknown) {
            System.out.println("(validate || open || unknown)");
            if (validate && !open && !unknown) {
                /* date proposée supérieur à la date maximum :
                 * on supprime tout et on remplace la chaine par le maximum */
                System.out.println("[DATEMASK] je vais tester les dates => proposedNumber = " + proposedNumber);
                if (proposedNumber > maxDate) {
                    System.out.println("DATE SUPERIEURE");
                    super.removeAll();
                    super.insertStringWithoutModification(0, String.valueOf(maxDate), a);
                }
                /* date proposée inférieure à la date minimum :
                 * on supprime tout et on remplace la chaine par la date minimum */
                else if (proposedNumber < minDate) {
                    System.out.println("DATE INFERIEURE");
                    super.removeAll();
                    aDate.makeUnknown();
                    return;
                    //super.insertStringWithoutModification(0, String.valueOf(minDate), a);
                }
                if (!focusLost) aDate.skipField(parent);
            }
            if (!alreadyChanged) {
                //=> verif et ajustement des champs
                System.out.println("[DATEMASK] j'ajuste les champs");
                String input = "";
                for (int i=0; i < reqLength - getLength(); i++) {
                    if (open) {
                        input += "O";
                    }
                    else if (unknown) {
                        input += "?";
                    }
                    else if (dateType == DateMask.YEAR && i==0 ) {
                        String tmpd = String.valueOf(trueDate);
                        input += tmpd.substring(0,1);
                    }
                    else {
                        input += "0";
                    }
                }
                super.insertStringWithoutModification(0, input, a);
                
            }
            
        }
        else {
            System.out.println("[DATEMASK] j'insert str par défaut => str = " + str + ", offs = " + offs);
            if (proposedResult.length() - str.length() > reqLength) {
               // getToolkit().beep();
                return;
            }
            else {
                if (afterOffset.length() > 0 && afterOffset.length() >= str.length()) {
                    remove(offs, str.length());
                    insertStringWithoutModification(offs, str, a);
                }
                else {
                    removeAll();
                    insertStringWithoutModification(0, proposedResult, a);
                }

                if (getLength() == reqLength && offs == reqLength -1) {
                    this.insertString(0, VALIDATE, a);
                    return;
                }
            }
        }
               
        System.out.println("[DATEMASK] => fin de la fct => validate = " + validate + ", open = " + open + ", unknown = " + unknown);
    }    
    //--------------------------------------------------------------------------------------------------------------------------------
    
    
    
    public void remove(int offs, int len) throws BadLocationException {
        String currentText = getText(0, getLength());
        System.out.println("[REMOVE] open = " + open + ", unknown = " + unknown + ", currentText = " + currentText);
        /*if (len > 0 && (open || unknown) && (currentText.indexOf("?") < 0 || currentText.indexOf("O") < 0 || currentText.indexOf("o") < 0 || currentText.equals(""))) {
            aDate.makeDefault();
        }*/
        if (aDate.isOpen() || aDate.isUnknown()) {
            aDate.makeDefault();
        }
        
        else {
            super.remove(offs, len);
        }
            
    }
    
    /** Getter for property open.
     * @return Value of property open.
     */
    public boolean isOpen() {
        return open;
    }    
   
    /** Setter for property open.
     * @param open New value of property open.
     */
    public void setOpen(boolean open) {
        this.open = open;
    }    
    
    /** Getter for property unknown.
     * @return Value of property unknown.
     */
    public boolean isUnknown() {
        return unknown;
    }    

    /** Setter for property unknown.
     * @param unknown New value of property unknown.
     */
    public void setUnknown(boolean unknown) {
        this.unknown = unknown;
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// STATIC VARIABLES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
/** value for erreur code */
public static int ERREUR_CODE = FormVerification.ERREUR_DATE;
/** the validate value */
public static final String VALIDATE = "*#*VALIDATE*#*";
public static final String FOCUS_LOST = "*#*FOCULOST*#*";
/** value for day */
public static final int DAY = 0;
/** value for month */
public static final int MONTH = 1;
/** value for year */
public static final int YEAR = 2;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// CHAMPS DE LA CLASSE
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
protected int dateType;
protected ADate aDate;
protected JTextField parent;
protected boolean open;
protected boolean unknown;


}
