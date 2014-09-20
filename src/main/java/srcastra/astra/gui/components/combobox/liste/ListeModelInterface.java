/*





 * ListeModelInterface.java





 *





 * Created on 20 novembre 2002, 14:41





 */


package srcastra.astra.gui.components.combobox.liste;


import java.util.*;


import javax.swing.table.AbstractTableModel;


import javax.swing.table.TableModel;


/**
 * @author Thomas
 */


public interface ListeModelInterface extends TableModel {


    /**
     * Creates a new instance of TransportTableModel
     */


    public int getColumnCount();


    public ArrayList loadata();


    public int getRowCount();


    public Object getValueAt(int param, int param1);


    public srcastra.astra.gui.test.ColumnData[] getM_column();


    public void setM_column(srcastra.astra.gui.test.ColumnData[] m_column);


    public srcastra.astra.sys.rmi.astrainterface getServeur();


    public void setServeur(srcastra.astra.sys.rmi.astrainterface seveur);


    public srcastra.astra.sys.classetransfert.Loginusers_T getLogin();


    public void setLogin(srcastra.astra.sys.classetransfert.Loginusers_T login);


    int searchAWord(Object word, int column);


    java.util.ArrayList getM_vector();


    void setM_vector(java.util.ArrayList m_vector);


    String getColumnName(int column);


    java.util.ArrayList getM_vector_by_key();


    void setM_vector_by_key(java.util.ArrayList m_vector_by_key);


}





