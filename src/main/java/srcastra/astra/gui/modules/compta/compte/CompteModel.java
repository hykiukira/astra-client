/*



 * FacturationModel.java



 *



 * Created le 05/08/2003, 9:43



 */


package srcastra.astra.gui.modules.compta.compte;


import javax.swing.table.AbstractTableModel;


import srcastra.astra.gui.test.*;


import javax.swing.*;


import srcastra.astra.sys.classetransfert.*;


import java.util.*;


import srcastra.astra.gui.modules.*;


/**
 * @author Driart
 */


public class CompteModel extends srcastra.astra.gui.components.combobox.liste.ListeTableModel {


    /**
     * Creates a new instance of FacturationModel
     */


    ColumnData[] m_columns;


    Loginusers_T currentUser;


    ArrayList data;


    public CompteModel(MainScreenModule main, Loginusers_T user) {


        super(main.getServeur(), main.getCurrentUser(), true);

        this.currentUser = user;


        this.m_columns = new ColumnData[]{


                new ColumnData(loadName("com_num"), 30, JLabel.LEFT),


                new ColumnData(loadName("com_libelle"), 250, JLabel.LEFT),


                new ColumnData(loadName("com_cat"), 10, JLabel.LEFT),


                new ColumnData(loadName("com_debit"), 20, JLabel.LEFT),


                new ColumnData(loadName("com_credit"), 20, JLabel.LEFT),

                //   new ColumnData(loadName("cai_col_entre"),20,JLabel.RIGHT),

                // new ColumnData(loadName("cai_col_sortie"),20,JLabel.LEFT),

                //  new ColumnData(loadName("cai_col_cc"),5,JLabel.LEFT),


        };


        data = new ArrayList();

        m_vector = data;

        //   Object[] tab = new Object[11];

        // data.add(new Object[5]);


    }


    public void addData() {


        Object[] tab = new Object[12];


        tab[3] = "D";


        data.add(tab);


    }


    public void addData(Object[] tab) {


        data.add(tab);


    }


    private String loadName(String key) {


        String retVal = "";


        try {


            retVal = java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", currentUser.getLangage()).getString(key);


            return retVal;


        }


        catch (java.util.MissingResourceException e) {


        }


        return "";


    }


    public int getColumnCount() {


        return m_columns.length;


    }


    public int getRowCount() {


        if (data != null)


            return data.size();


        return 0;


    }


    public void afficheMe() {


        String test = "";


        String t = "";


        if (data != null)


            for (int i = 0; i < data.size(); i++) {


                Object[] tmp = (Object[]) data.get(i);


                if (tmp != null) {


                    t = "";


                    for (int j = 0; j < tmp.length; j++) {


                        if (tmp[j] != null)


                            test = tmp[j].toString();


                        else


                            test = "null";


                        t = t + i + " " + j + " :" + test + "  ";


                    }


                    System.out.println(t);


                    System.out.println("\nfin");


                }


            }


    }


    public Object getValueAt(int param, int param1) {


        if (param1 < 0 || param1 > 7)


            return "";


        if (param < 0 || param > data.size())


            return "";


        Object[] obj = (Object[]) data.get(param);


        switch (param1) {


            case 0:
                return obj[param1 + 1];


            case 1:
                return obj[param1 + 1];


            case 2:
                return obj[param1 + 1];


            case 3:
                return obj[param1 + 1];


            case 4:
                return obj[param1 + 1];


            case 5:
                return obj[param1 + 1];


            case 6:
                return obj[param1 + 1];


            case 7:
                return obj[param1 + 1];


        }


        return "";


    }


    public Object getValueAt2(int param, int param1) {


        Object[] obj = (Object[]) data.get(param);


        return obj[param1];


    }


    private Object checkType(Object[] tab) {


        if (((Integer) tab[5]).intValue() == srcastra.astra.sys.classetransfert.configuration.TypePayement.CARD) {


            return "CC";


        } else
        if (((Integer) tab[5]).intValue() == srcastra.astra.sys.classetransfert.configuration.TypePayement.CASH) {


            return "CA";


        } else
        if (((Integer) tab[5]).intValue() == srcastra.astra.sys.classetransfert.configuration.TypePayement.CHEQUE) {


            return "CH";


        }


        return "";


    }


    private Object checkTypePayement(Object[] tab, int col) {


        if (((Integer) tab[5]).intValue() == srcastra.astra.sys.classetransfert.configuration.TypePayement.CARD) {


            if (col == 7)


                return tab[4];


            else


                return "";


        } else if (((Double) tab[4]).doubleValue() > 0) {


            if (col == 5)


                return tab[4];


            else


                return "";


        } else if (((Double) tab[4]).doubleValue() < 0) {


            if (col == 6) {


                tab[4] = new Double(Math.abs(((Double) tab[4]).doubleValue()));


                return tab[4];


            } else


                return "";


        }


        return "";


    }


    public void setValueAt(int param, int param1, Object obj) {


        Object[] tmp = (Object[]) data.get(param);


        if (tmp != null) {


            tmp[param1] = obj;


        }


    }


    public void setValueTva(int param, Object cleunik, Object prcttva, Object base, Object montantTva, Object prixtot, Object libelle) {


        Object[] tmp = (Object[]) data.get(param);


        if (tmp != null) {


            tmp[7] = cleunik;


            tmp[1] = libelle;


            tmp[2] = base;


            tmp[8] = montantTva;


            tmp[9] = prixtot;


            tmp[10] = prcttva;


        }


    }


    public void setValueCompte(int param, Object cleunik, Object compte) {


        Object[] tmp = (Object[]) data.get(param);


        if (tmp != null) {


            tmp[0] = compte;


            tmp[6] = cleunik;


        }


    }


    public void setValueMontant(int param, Object base, Object montanttva, Object montanttot) {


        Object[] tmp = (Object[]) data.get(param);


        if (tmp != null) {


            tmp[2] = base;


            tmp[8] = montanttva;


            tmp[9] = montanttot;


        }


    }


    public boolean isCellEditable(int nrow, int ncol) {


        if (ncol == 5) return false;


        return true;


    }


    /**
     * Getter for property m_columns.
     *
     * @return Value of property m_columns.
     */


    public srcastra.astra.gui.test.ColumnData[] getM_columns() {


        return this.m_columns;


    }


    /**
     * Setter for property m_columns.
     *
     * @param m_columns New value of property m_columns.
     */


    public void setM_columns(srcastra.astra.gui.test.ColumnData[] m_columns) {


        this.m_columns = m_columns;


    }


    /**
     * Getter for property data.
     *
     * @return Value of property data.
     */


    public java.util.ArrayList getData() {


        return data;


    }


    /**
     * Setter for property data.
     *
     * @param data New value of property data.
     */


    public void setData(java.util.ArrayList data) {


        this.data = data;

        m_vector = data;


    }


}



