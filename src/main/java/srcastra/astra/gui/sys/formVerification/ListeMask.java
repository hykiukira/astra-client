/*
 * IntegerMask.java
 *
 * Created on 10 mai 2002, 16:38
 */

package srcastra.astra.gui.sys.formVerification;
import javax.swing.text.*;
import srcastra.astra.gui.components.textFields.ATextFieldComponent;
import java.util.Locale;
import srcastra.astra.gui.test.*;
import srcastra.astra.gui.components.combobox.liste.*;
import srcastra.astra.gui.sys.utils.*;
import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.rmi.Exception.*;
import java.rmi.*;
import java.util.*;
import javax.swing.*;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.sys.classetransfert.clients.*;
/**
 *
 * @author  Sébastien
 * @version 
 */

public class ListeMask extends DefaultMask {
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// CONSTRUCTORS
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    /** Creates new IntegerMask */
    public ListeMask(astrainterface serveur,int minLenght, int maxLenght, Locale locale, int caseType,InterfaceModel model,Liste parent,int longueurtexte) {
        super(minLenght, maxLenght, locale, caseType);
        m_parent=parent;
        m_model=model;
        m_serveur=serveur;
        m_longeur_saisie=longueurtexte;
    }
    public void setColumn(int column)
    {
        m_column=column;
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// METHODES APPARENTE AU BEANS
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        String currentText = getText(0, getLength());
        String beforeOffset = currentText.substring(0, offs);
        String afterOffset = currentText.substring(offs, currentText.length());
        System.out.println("[*********]INSERTSTRING");

       // boolean validate = false;
        boolean check=false;
        // regardons si il s'agit d'une action de validation
    //    if (str.equals(DefaultMask.VALIDATE)) 
         //   validate = true;
        //str = validate ? "" : str;
        String proposedResult = beforeOffset + str + afterOffset;
        if(proposedResult.length()<m_longeur_saisie || validate==true){  
            super.insertString(offs,str, a);
             validate=false;
        }
        else{
            if(charger==false){
                m_model.loadData(null,proposedResult);
                m_model.sortByColonne(0,m_parent.getGrp_Table_table());
                
             }
            charger=true;
            if(!m_model.isM_sortAsc())
                m_model.sortByColonne(0,m_parent.getGrp_Table_table());
            if(m_model.getArray()!=null){
                 check=search(proposedResult);            
                    if (check){
                        super.insertString(offs,str, a);
                        affichePopup(true);
                        m_parent.setGoodIcon(true); 
                    }
                    
                    else{ 
                        java.awt.Toolkit.getDefaultToolkit().beep();
                        JToolTip toolTip=new JToolTip();
                        toolTip.setToolTipText("test");
                        toolTip.setComponent(m_parent.getGrp_Table_table());
                        toolTip.setVisible(true);
                        System.out.println("showtooltip");
                        
                    }
            }
            else{
                
                
            }
        }          
    }
    private boolean search(String proposedResult){
            boolean check=false;
            position=m_model.searchAWord(proposedResult,m_column);
            System.out.println("search position :"+position);
            if(position>-1 && position< m_parent.getGrp_Table_table().getRowCount()){
                RowColor row=(RowColor)m_parent.getGrp_Table_table().getValueAt(position,m_column);
                String word=row.m_data.toString();
               // char[] wordtab=word.toUpperCase().toCharArray();
                char[] proposetab=proposedResult.toUpperCase().toCharArray();
                int longueur=proposetab.length;
                if(longueur<=word.length())
                {   
                String tmp=word.substring(0,longueur);
                if(tmp.toUpperCase().equals(proposedResult.toUpperCase()))
                    check=true;
                 if(position>-1 && position< m_parent.getGrp_Table_table().getRowCount() && check==true)
                    m_parent.getGrp_Table_table().setRowSelectionInterval(position,position);
                    m_parent.getGrp_Table_table().scrollRectToVisible(new java.awt.Rectangle( 0, m_parent.getGrp_Table_table().getRowHeight()*position, 20, m_parent.getGrp_Table_table().getRowHeight()));
                } 
            }
            return check;
    }
    public Object getObject(){
       ListeObjectAffichage client=m_model.getClassAffichage2(position);
       if(client==null) return null;
       InterfaceClasseTransfert tmp=m_model.getClassAffichage2(position).getParent(); 
       return tmp;
    }
    public void remove(int offs,int len) throws BadLocationException {
      
       
       // String result = m_parent.getGrp_TField_encode().getText(0,m_parent.getGrp_TField_encode().getText().length());
        super.remove(offs, len);
          System.out.println("[*********]REMOVE");
        if(validate==false){       
        String result = m_parent.getGrp_TField_encode().getText(0,getLength());        
        System.out.println("je remove "+result.length());
        if (result.length() >= m_longeur_saisie && m_longeur_saisie!=0 ) {
            System.out.println("+grand "+result);
            if(m_parent.getGrp_PopMenu_popup().isVisible())
            search(result);
        }
        else
        {
           System.out.println("+petit "+result); 
           affichePopup(false);
           charger=false;    
           m_model.setM_vector(null);
        }     
        }
        else{
             affichePopup(false);
        }
    }
    private void affichePopup(boolean sw){  
        if(sw==true) 
        {
        m_parent.getGrp_PopMenu_popup().setVisible(true);
        int x=m_parent.getGrp_PopMenu_popup().getBorder().getBorderInsets(m_parent.getGrp_PopMenu_popup()).right-m_parent.getGrp_TField_encode().getX();
      //  m_parent.getGrp_PopMenu_popup().setSize( m_parent.getGrp_PopMenu_popup().getWidth(),m_parent.getGrp_Table_table().getHeight());
        m_parent.getGrp_PopMenu_popup().show(m_parent, ( m_parent.getGrp_TField_encode().getX()+ m_parent.getGrp_TField_encode().getWidth())-m_parent.getGrp_PopMenu_popup().getWidth(),
                                                 m_parent.getGrp_TField_encode().getY()+18);   
      // model.loadData(this.array);
        
        m_parent.getGrp_Table_table().repaint();
        m_parent.getGrp_PopMenu_popup().repaint();
        m_parent.setFocusFromTable(true);
        m_parent.getGrp_TField_encode().requestFocus();     
        }
        else
        {
            m_parent.getGrp_PopMenu_popup().setVisible(false);
            m_parent.getGrp_TField_encode().requestFocus();              
        }
    }
    
    /** Getter for property validate.
     * @return Value of property validate.
     */
    public boolean isValidate() {
        return validate;
    }
    
    /** Setter for property validate.
     * @param validate New value of property validate.
     */
    public void setValidate(boolean validate) {
        this.validate = validate;
    }
    
    /** Getter for property position.
     * @return Value of property position.
     */
    
    /** Setter for property position.
     * @param position New value of property position.
     */
    public void setPosition(int position) {
        this.position = position;
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// STATIC VARIABLES
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    /** value for erreur code */
    public static int ERREUR_CODE = FormVerification.ERREUR_STRING;
    private int currentposition;
    InterfaceModel m_model;
    Liste m_parent;
    int m_column;
    astrainterface m_serveur;
    boolean charger=false;
    int m_longeur_saisie;
    ArrayList array;
    int position;
    boolean validate;
}
