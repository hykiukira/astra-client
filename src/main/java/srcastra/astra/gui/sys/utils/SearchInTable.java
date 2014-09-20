/*
 * SearchInTable.java
 *
 * Created on 6 février 2003, 10:52
 */

package srcastra.astra.gui.sys.utils;
import javax.swing.*;
import javax.swing.table.*;
import srcastra.astra.gui.components.actions.actionToolBar.*;
import srcastra.astra.gui.sys.formVerification.*;
import javax.swing.text.*;
import java.util.*;


/**
 *
 * @author  Thomas
 */
public class SearchInTable implements TableWork,Comparator{
    
    /** Creates a new instance of SearchInTable */
    ToolBarComposer m_main;
    JTextField m_textfield;
    JTable m_table;
    srcastra.astra.gui.modules.aidedesicion.MyTableModel tb_model;
    Comparator m_comparator;
    public SearchInTable(ToolBarComposer main,JTextField textfield,JTable table,int col,java.util.Comparator comparator) {
        m_main=main;
        m_textfield=textfield;
        m_table=table;
        tb_model=(srcastra.astra.gui.modules.aidedesicion.MyTableModel)m_table.getModel();
        m_sortCol=col;
        m_textfield.setDocument(new SearchInTable.ZeMask());
    }
     public int searchAWord(Object word, int column) {
        int position = -1;
        m_sortCol = column;
        Object[] array;
        Object[] array2 = new Object[tb_model.getColumnCount()+1];
        for (int i=0; i < array2.length; i++) {
          array2[i] = word;
        }
             array=tb_model.getVector().toArray();
             position = Arrays.binarySearch(array, array2,this);
      
            
        
       
       // position = Arrays.binarySearch(array, array2, this);
       // position = Arrays.binarySearch(array, word);
       // System.out.println("[SEARCH A WORD] position after binarySearch : " + position);
        if (position < 0) return (position +1) * -1;
            return position;
    }
     
     public int compare(Object obj, Object obj1) {
         System.out.println("[COMPARE] column = " + m_sortCol);
        Object[] tmpObj1 =(Object[]) obj;
        Object[] tempObj2 = (Object[]) obj1;
        Object comp1=tempObj2[0];
        int compare;
        String cp1=null;
        String cp2=null;
        int entier1;
        int entier2;
        int comparaison =0;
        switch(m_sortCol)
        {
            case 1:
                entier1=((Integer)tmpObj1[0]).intValue();
                entier2=((Integer)comp1).intValue();
                if(entier1<entier2) comparaison=-1;
                else if(entier1>entier2) comparaison=1;
                else if(entier1==entier2) comparaison=0; 
                break;
            case 0:
                cp1=tmpObj1[m_sortCol].toString();
                cp2=comp1.toString();   
                comparaison = (cp1.toUpperCase()).compareTo(cp2.toUpperCase());
                break;
        }        
       
      //  System.out.println("[COMPARE] Objet comparé 1 = " + cp1 + " Objet comparé 2 = " + cp2 + " Nbre de comparaison retourné = " + comparaison);
        return comparaison;
     }
     
     private class ZeMask extends DefaultMask {
        private boolean needLoad = true;
        private int beginLoad = 1;
        private int colSearch = 1;
        private boolean research = true;
        
        public ZeMask() {
            super();
        }       
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            String c = getText(0, getLength());
            String befO = c.substring(0, offs);
            String aftO = c.substring(offs, getLength());
            String ct = befO + str + aftO;
            boolean encode = true;
            if (str.equals(DefaultMask.VALIDATE)) {
               // main.doAccept();
                return;                
            }
            research=true;
            if (research) {
              /* if (needLoad) {
                    if (ct.length() == beginLoad) { 
                        tb_model.loadata(ct);
                        reloadTable();
                        needLoad = false;
                        if (m_table.getRowCount() > 0) {
                            m_table.changeSelection(0, 0, false, false);
                            fillInfos();
                            super.insertString(offs, ct, a);
                        }
                        else java.awt.Toolkit.getDefaultToolkit().beep();

                    }
                    else if (ct.length() >= beginLoad) {
                        tb_model.loadata(ct.substring(0, beginLoad));
                        reloadTable();
                        needLoad = false;
                        if (grp_Table_ClientIndex.getRowCount() > 0) {
                            grp_Table_ClientIndex.changeSelection(0, 0, false, false);
                            fillInfos();
                            super.insertString(offs, ct, a);
                        }
                        else java.awt.Toolkit.getDefaultToolkit().beep();
                        needLoad = false;
                    }

                }*/
                if (ct.length() > 0 ){//&& ct.length() > beginLoad) {
                    int ln = searchAWord(ct, 0);
                    int tot = m_table.getRowCount();
                    if (ln >=0 && ln < tot) {
                        m_table.changeSelection(ln, 0, false, false);
                        //fillInfos();
                        String value = tb_model.getValueAt(ln, 0).toString();
                        System.out.println("==+> value = " + value.toUpperCase());
                        System.out.println("==+> ct = " + ct.toUpperCase());
                        if (value.toUpperCase().startsWith(ct.toUpperCase())) super.insertString(offs, str, a);
                        else java.awt.Toolkit.getDefaultToolkit().beep();
                    }
                }
            }
            else if (encode) super.insertString(offs, str, a);
            research = true;
        }
        
        public void remove(int offs, int len) throws BadLocationException {
            super.remove(offs, len);
            int ct = getLength();
            String tmp=m_textfield.getText();
            if(tmp.equals(""))
                m_table.changeSelection(0,0,false,false);
            else{
                searchAWord(tmp,0);
            }
        }
        
        /** Getter for property research.
         * @return Value of property research.
         */
        public boolean isResearch() {
            return research;
        }
        
        /** Setter for property research.
         * @param research New value of property research.
         */
        public void setResearch(boolean research) {
            this.research = research;
        }        
    }
     int  m_sortCol ;
}
