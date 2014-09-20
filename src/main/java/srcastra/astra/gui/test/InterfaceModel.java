/*
 * InterfaceModel.java
 *
 * Created on 24 octobre 2002, 9:19
 */

package srcastra.astra.gui.test;
import srcastra.astra.sys.classetransfert.dossier.ProduitAffichage;
import javax.swing.table.TableModel;
import java.util.*;
import javax.swing.table.*;
import javax.swing.*;
/**
 *
 * @author  Thomas
 */
public interface InterfaceModel extends TableModel{
 //  void loadData(); 
   ProduitAffichage getClassAffichage(int ligne);
   void loadData(ArrayList tmparray,String proposedResult);
   void  sortByColonne(int col,JTable m_table);
   int searchAWord(Object word, int column);
   ListeObjectAffichage getClassAffichage2(int ligne);
   boolean isM_sortAsc(); 
   void setM_sortAsc(boolean m_sortAsc);
   java.util.ArrayList getArray(); 
   java.util.ArrayList getM_vector(); 
   void setM_vector(java.util.ArrayList m_vector);
   String getColumnName(int column);
   int getColumnCount();
   int getRowCount();
   boolean isCellEditable(int nrow,int ncol);
    srcastra.astra.sys.rmi.astrainterface getM_serveur(); 
    public void setM_serveur(srcastra.astra.sys.rmi.astrainterface m_serveur); 
    public srcastra.astra.sys.classetransfert.Loginusers_T getM_login();
    public void setM_login(srcastra.astra.sys.classetransfert.Loginusers_T m_login);
    public java.awt.Component getM_textField();
    public void setM_textField(java.awt.Component m_textField);
    public srcastra.astra.gui.test.ColumnData[] getM_columns();    
    public void setM_columns(srcastra.astra.gui.test.ColumnData[] m_columns); 
    ArrayList loadData(); 
}
