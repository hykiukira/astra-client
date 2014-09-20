/*

 * UserTableModel.java

 *

 * Created on 5 juillet 2003, 9:56

 */


package srcastra.astra.gui.modules.config.entite;

import java.util.*;

import srcastra.astra.sys.classetransfert.configuration.*;

import srcastra.astra.gui.modules.config.*;

/**
 * @author Thomas
 */

public class EntiteTableModel extends javax.swing.table.AbstractTableModel implements ConfigModel {


    /**
     * Creates a new instance of UserTableModel
     */

    public EntiteTableModel() {

    }


    public int getColumnCount() {

        return 2;

    }


    public int getRowCount() {

        return data.size();

    }


    public Object getValueAt(int param, int param1) {

        if (param1 < 0 || param1 > 2)

            return "";

        if (param < 0 || param > data.size())

            return "";

        Entite obj = (Entite) data.get(param);

        switch (param1) {

            case 0:
                return obj.getEenom();

            case 1:
                return obj.getEeadresse();

        }

        return "";

    }


    /**
     * Getter for property data.
     *
     * @return Value of property data.
     */

    public ArrayList getData() {

        return data;

    }


    /**
     * Setter for property data.
     *
     * @param data New value of property data.
     */

    public Entite getObject(int row) {

        return (Entite) data.get(row);

    }

    public void setData(ArrayList data) {

        this.data = data;

    }


    ArrayList data;

}

