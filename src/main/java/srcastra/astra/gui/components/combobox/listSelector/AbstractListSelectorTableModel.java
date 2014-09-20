/*





 * ListSelectorTableModel.java





 *





 * Created on 14 août 2002, 10:56





 */


package srcastra.astra.gui.components.combobox.listSelector;

// Swing Importations


import javax.swing.table.AbstractTableModel;

// Divers


import java.util.ArrayList;


import java.util.Arrays;


import java.util.Comparator;


import srcastra.astra.sys.Logger;


/**
 * @author Sébastien
 */


public abstract class AbstractListSelectorTableModel extends AbstractTableModel implements Comparator {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// CONSTRUCTOR

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Creates a new instance of ListSelectorTableModel
     */


    public AbstractListSelectorTableModel(ListSelector listSelector) {


        this.listSelector = listSelector;


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => METHODE APPARENTE AU BEANS

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public int getColumnCount() {


        dataNeeded();


        int colNbr = 0;


        if (dataContainer.size() > 0) {


            Object[] row = (Object[]) dataContainer.get(0);


            colNbr = row.length - 1;


        }


        return colNbr;


    }


    public int getRowCount() {


        dataNeeded();


        int rowNbr = 0;


        if (dataContainer.size() > 0) rowNbr = dataContainer.size();


        return rowNbr;


    }


    public Object getValueAt(int row, int column) {

        //Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"Getint data at ["+row+":"+column+"]");

        //Thread.currentThread().dumpStack();


        dataNeeded();


        Object[] line = (Object[]) dataContainer.get(row);


        return line[column + 1];


    }


    public String getColumnName(int column) {


        String[] colNames = loadColumnNames();


        dataNeeded();


        Object[] tmp = (Object[]) dataContainer.get(0);


        int size = tmp.length;


        if (size > colNames.length)


        {


            if (column == size - 2)


                return "provisoire";


            else


                return colNames[column];


        } else


            return colNames[column];


    }


    protected abstract ArrayList loadData();


    protected abstract String[] loadColumnNames();


    protected abstract void dataNeeded();


    protected abstract int[] getColumnMask();


    protected abstract void resetData();


    public int compare(Object obj, Object obj1) {


        System.out.println("[COMPARE] column = " + column);


        Object[] tempObj = (Object[]) obj;


        Object[] tempObj2 = (Object[]) obj1;


        Object comp1 = tempObj[column];


        Object comp2 = tempObj2[1];


        String cp1 = comp1.toString();


        String cp2 = comp2.toString();


        cp1 = cp1.toUpperCase();


        cp2 = cp2.toUpperCase();


        int comparaison = cp1.compareTo(cp2);


        System.out.println("[COMPARE] Objet comparé 1 = " + cp1 + " Objet comparé 2 = " + cp2 + " Nbre de comparaison retourné = " + comparaison);


        if (comparaison == 0) return 1;


        return comparaison;


    }


    public int searchAWord(Object word, int column) {


        dataNeeded();


        int position = -1;


        this.column = column;


        Object[] array = dataContainer.toArray();


        Object[] array2 = new Object[getColumnCount() + 1];


        for (int i = 0; i < array2.length; i++) {


            array2[i] = word;


        }


        position = Arrays.binarySearch(array, array2, this);

        // position = Arrays.binarySearch(array, array2, this);

        // position = Arrays.binarySearch(array, word);


        System.out.println("[SEARCH A WORD] position after binarySearch : " + position);


        if (position < 0) return (position + 1) * -1;


        return position;


    }


    public void sortByCollonne() {


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => Champs de la classe

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    protected ListSelector listSelector;


    protected ArrayList dataContainer;


    protected String[] columnNames;


    protected int column;


}





