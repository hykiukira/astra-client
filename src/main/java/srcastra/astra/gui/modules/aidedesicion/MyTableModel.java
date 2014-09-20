/*





 * MyTableModel.java





 *





 * Created on 6 février 2003, 11:13





 */


package srcastra.astra.gui.modules.aidedesicion;


/**
 * @author Thomas
 */


public abstract class MyTableModel extends javax.swing.table.AbstractTableModel {


    /**
     * Creates a new instance of MyTableModel
     */


    public MyTableModel() {


    }


    public abstract java.util.Vector getVector();


    public abstract int getColumnCount();


    public abstract void loadData(int typeProduit);


    public abstract void loadData();


    public int generiqueInt;


    public abstract Object[] getObject(int row);


}





