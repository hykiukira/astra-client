/* * DecisionTableModel.java * * Created on 17 januari 2003, 8:51 */package srcastra.astra.gui.modules.aidedesicion;import javax.swing.table.AbstractTableModel;import javax.swing.*;import srcastra.astra.gui.test.ColumnData;//ColumnData;import srcastra.astra.sys.classetransfert.*;import java.util.*;import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;import srcastra.astra.gui.sys.comparator.*;/** * @author Thomas */public class DecisionTableModel extends MyTableModel {    Loginusers_T currentUser;    DossierMainScreenModule parent;    AbstractBuffer decTree;    public DecisionTableModel(Loginusers_T currentUser, DossierMainScreenModule parent, AbstractBuffer decTree) {        this.currentUser = currentUser;        this.parent = parent;        this.decTree = decTree;        m_columns = new ColumnData[]{                new ColumnData(loadName("01"), 60, JLabel.LEFT),                new ColumnData(loadName("02"), 60, JLabel.LEFT),                new ColumnData(loadName("03"), 60, JLabel.LEFT),                new ColumnData(loadName("04"), 60, JLabel.LEFT),                new ColumnData(loadName("05"), 60, JLabel.LEFT),        };    }    private String loadName(String key) {        String retVal = "";        try {            retVal = java.util.ResourceBundle.getBundle("srcastra/astra/locale/tableHeader/dossier/DecisionBundle", currentUser.getLangage()).getString(key);        }        catch (java.util.MissingResourceException e) {        }        finally {            return retVal;        }    }    public int getColumnCount() {        return m_columns.length;    }    public int getRowCount() {        if (m_vector == null)            return 0;        else            return m_vector.size();    }    public Object getValueAt(int param, int param1) {        if (param < 0 || param1 > getColumnCount())            return "";        Object[] row = (Object[]) m_vector.elementAt(param);        switch (param1) {            case 0:                return row[0];            case 1:                return row[1];            case 2:                return row[2];            case 3:                return row[3];            case 4:                return row[4];        }        return "";    }    public void loadData() {        m_vector = new Vector();        ((DecBuffer) decTree).fillVector(m_vector, this.generiqueInt);        Collections.sort(m_vector, new ListeSelectorComparaTor(0, true));    }    public String getColumnName(int column) {        return m_columns[column].c_title;    }    public Object[] getObject(int row) {        if (m_vector != null)            return (Object[]) m_vector.get(row);        else            return null;    }    public java.util.Vector getVector() {        return m_vector;    }    public void loadData(int typeProduit) {    }    ColumnData[] m_columns;    protected Vector m_vector;    int m_sortCol;}