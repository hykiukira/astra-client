/*


 * AgendaTableModel.java


 *


 * Created on 27 août 2003, 9:41


 *


 *Cette classe permet de construire l'agenda


 */


package srcastra.astra.gui.modules.agenda;


/**
 * @author Driart
 */


public class AgendaTableModel extends javax.swing.table.DefaultTableModel {


    /**
     * Creates a new instance of AgendaTableModel
     */


    public AgendaTableModel(Object[][] data, Object[] column) {


        super(data, column);


    }


    public boolean isCellEditable(int row, int col) {


        return false;


    }


    public boolean isCellSelected(int row, int col) {


        if (col == 0)


            return false;


        return true;


    }


}


