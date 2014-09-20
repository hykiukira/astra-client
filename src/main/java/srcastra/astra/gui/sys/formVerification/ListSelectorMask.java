/*
 * ListSelectorMask.java
 *
 * Created on 13 août 2002, 12:53
 */

package srcastra.astra.gui.sys.formVerification;

import javax.swing.text.*;

// Listeners
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// awt Import
import java.awt.Toolkit;
import java.util.Locale;
import srcastra.astra.gui.components.combobox.listSelector.ListSelector;
import srcastra.astra.gui.components.combobox.listSelector.AbstractListSelectorTableModel;
import srcastra.astra.sys.Logger;


/**
 *
 * @author  Sébastien
 */
public class ListSelectorMask extends PlainDocument {

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// CONSTRUCTOR
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
        
    /** Creates a new instance of ListSelectorMask */
    public ListSelectorMask(int typeValue, Locale locale, ListSelector listSelector) {
        this.typeValue =typeValue;
        this.locale = locale;
        this.listSelector = listSelector;
    }

    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
// => METHODE APPARENTE AU BEANS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
    
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        
         
        boolean authorizeInsertOperation = true /*checkTextFieldValue(str)*/;
        Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"String recue dans insertString: "+str);
        
        if (authorizeInsertOperation) {
        
            str = str.toUpperCase();

            String currentText = getText(0, getLength());
            String beforeOffset = currentText.substring(0, offs);
            String afterOffset = currentText.substring(offs, currentText.length());

            String proposedResult = beforeOffset + str + afterOffset;

            boolean okCheck = true;
            boolean okBoucle = true;


            searchCurrentWord(proposedResult, 1);

            // comparaison des chaines trouvées et encodées
            if (listSelector.getValue(position).toString().toUpperCase().indexOf(proposedResult.toUpperCase()) == -1) {
                okCheck = false;
            }


            if (listSelector.getValue(position).toString().toUpperCase().equals(proposedResult.toUpperCase())) {
                okBoucle = false;
            }

            int i =1;
            System.out.println("position = "  + position);
            System.out.println("[INSERTSTRING] indexof " + proposedResult + " = " + listSelector.getValue(position).toString().indexOf(proposedResult) + "dans la chaine : " + listSelector.getValue(position).toString());

            while(listSelector.getValue(position).toString().toUpperCase().indexOf(proposedResult.toUpperCase()) != 0 && okBoucle && proposedResult.length()>0) {
                proposedResult = proposedResult.substring(0, proposedResult.length() - 1);
                searchCurrentWord(proposedResult, 1);
                okCheck = false;
            }

            listSelector.changeSelection(position);

            if (okCheck) super.insertString(offs, str, a);
            else java.awt.Toolkit.getDefaultToolkit().beep();
        }
        else {
            java.awt.Toolkit.getDefaultToolkit().beep();
        }   
        
    }
    
    
    public void remove(int offs, int len) throws BadLocationException {
        
        super.remove(offs, len);
        String result = this.getText(0, getLength());
        if (result.length() > 0 ) {
            searchCurrentWord(result, 1);
        }
        else {
            position = 0;
        }
        listSelector.changeSelection(position);
        
    }
    
    protected Toolkit getToolkit() {
        return Toolkit.getDefaultToolkit();
    }
        
    public String getGuiErrorCode() {
        String erreur = "";
        try {
            erreur = java.util.ResourceBundle.getBundle("srcastra/astra/locale/ErreurFormVerification", locale).getString(String.valueOf(ERREUR_CODE));
            return erreur;
        } catch (java.util.MissingResourceException mre) {
               return "";
         }
    }
    
    private void searchCurrentWord(Object word, int column) {
        position = listSelector.getListSelectorTableModel().searchAWord(word, column);
        System.out.println("[SEARCH A WORD] position returned" + position);
        if (position >= listSelector.getListSelectorTableModel().getRowCount()) position = 0;
        System.out.println("nombre de ligne au total = " + listSelector.getListSelectorTableModel().getRowCount());
        System.out.println("[SEARCH A WORD] position returned" + position);
    }
    public boolean goToCleUnikPosition(int cleUnik) {
        return goToCleUnikPosition(cleUnik,null,-1);
    }
   
    public boolean goToCleUnikPosition(int cleUnik, String value, int positionDepart) {
        Logger.getDefaultLogger().log(Logger.LOG_EMERGENCY,"\n\n\n\n\n\n\n\n\n\n\n\nSearching whole list. cle:"+cleUnik+" value:"+(value==null?"null":value)+" depart: "+positionDepart);
        if(cleUnik==0)
        {
            position=0;
            return false;
        }
        if (value==null)
            Logger.getDefaultLogger().log(Logger.LOG_EMERGENCY,"Searching whole list");
        if ((value!=null) && 
            ((positionDepart<0) 
                    ||(!listSelector.getListSelectorTableModel().getValueAt(positionDepart,0).toString().toUpperCase().equals(value.toUpperCase()))
            )
           ){
            positionDepart = listSelector.getListSelectorTableModel().searchAWord(value, 1);
            if (positionDepart <= listSelector.getListSelectorTableModel().getRowCount()&& positionDepart>=0)
                position=positionDepart;
            int k=listSelector.getListSelectorTableModel().getRowCount();
            if (positionDepart >= k) 
                return false;
        }
        if (positionDepart <0)
            return false;
        position=0;
        Object temp = null;
        for (int i=positionDepart; 
             (i < listSelector.getListSelectorTableModel().getRowCount()) 
              && ((value==null) 
                 ||(listSelector.getListSelectorTableModel().getValueAt(positionDepart,0).toString().toUpperCase().equals(value.toUpperCase()))
                );
              i++) {
            temp = listSelector.getListSelectorTableModel().getValueAt(i, -1);
            if (Integer.parseInt((temp).toString()) == cleUnik) {
                position = i;
                return true;
            }
        }
        return false;
        
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
// => METHODE POUR LA VERIF DES CHAMPS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    public boolean checkTextFieldValue(String str) {
        boolean isCorrect = false;
        
        switch (this.typeValue) {
            case ListSelectorMask.TYPEVALUE_ALPHANUM :
               isCorrect = checkTextForAlphanumeric(str);
               break;
            case ListSelectorMask.TYPEVALUE_DECIMAL :
                isCorrect = checkTextForDecimal(str);
                break;
            case ListSelectorMask.TYPEVALUE_DIGIT :
                isCorrect = checkTextForInteger(str);
                break;
            case ListSelectorMask.TYPEVALUE_LETTRE :
                isCorrect = checkTextForLetter(str);
                break;
        }
        return isCorrect;
    }
    
    public boolean checkTextForLetter(String str) {
        char[] caract = str.toCharArray();
        for (int i=0; i < caract.length; i++) {
            if (!Character.isLetter(caract[i])) return false;
        }
        return true;
    }
    
    public boolean checkTextForInteger(String str) {
        char[] caract = str.toCharArray();
        for (int i=0; i < caract.length; i++) {
            if (!Character.isDigit(caract[i])) return false;
        }
        return true;
    }
    
    public boolean checkTextForDecimal(String str) {
        char[] caract = str.toCharArray();
        for (int i=0; i < caract.length; i++) {
            if (!Character.isDigit(caract[i])) return false;
        }
        return true;
    }
    
    public boolean checkTextForAlphanumeric(String str) {
        char[] caract = str.toCharArray();
        for (int i=0; i < caract.length; i++) {
            if (!Character.isLetterOrDigit(caract[i])) return false;
        }
        return true;
    }
    
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
// => BEANS PROPERTIES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    private int typeValue;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
// => Champs de la classe
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    private Locale locale;
    private ListSelector listSelector;
    private int position = 0;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// STATIC VARIABLES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    public static final int TYPEVALUE_LETTRE = 0;
    public static final int TYPEVALUE_DIGIT = 1;
    public static final int TYPEVALUE_ALPHANUM = 2;
    public static final int TYPEVALUE_DECIMAL = 3;

    public static final String BEANS_PROPERTY_CORRECT_INPUT = "CorrectInput";
    public static final String BEANS_PROPERTY_VALIDATE = "Validate";
    /** Value for PropertyChangeListener */
    public static final String BEAN_CORRECTINPUT = "CorrectInput";
    /** Value for PropertyChangeListener */
    public static final String BEAN_VALIDATE = "Validate";
    /** value for erreur code */
    public static int ERREUR_CODE = FormVerification.ERREUR_VIDE;
    /** special string for component validation */
    public static final String VALIDATE = "*#*VALIDATE*#*";
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
// BEANS PROPERTIES GET/SET SUPPORT
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
      /** Getter for property typeValue.
     * @return Value of property typeValue.
     */
    public int getTypeValue() {
        return typeValue;
    }
    
    /** Setter for property typeValue.
     * @param typeValue New value of property typeValue.
     */
    public void setTypeValue(int typeValue) {
        this.typeValue = typeValue;
    }
    
    /** Getter for property position.
     * @return Value of property position.
     */
    public int getPosition() {
        return position;
    }
    
    /** Setter for property position.
     * @param position New value of property position.
     */
    public void setPosition(int position) {
        this.position = position;
    }    
    
}