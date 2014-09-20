/*


 * FacturationModel.java


 *


 * Created le 05/08/2003, 9:43


 */


package srcastra.astra.gui.modules.compta.caisse;


import javax.swing.table.AbstractTableModel;


import srcastra.astra.gui.test.*;


import javax.swing.*;


import srcastra.astra.sys.classetransfert.*;


import java.util.*;


import srcastra.astra.gui.modules.*;


/**
 * @author Driart
 */


public class CaisseLibelletModel extends AbstractTableModel implements srcastra.astra.gui.modules.config.ConfigModel {


    /**
     * Creates a new instance of FacturationModel
     */


    ColumnData[] m_columns;


    Loginusers_T currentUser;


    ArrayList data;


    public CaisseLibelletModel(MainScreenModule main, Loginusers_T user) {


        this.currentUser = user;


        this.m_columns = new ColumnData[]{


                new ColumnData(loadName("typpai1"), 40, JLabel.LEFT),


                new ColumnData(loadName("typpai2"), 0, JLabel.LEFT),


                new ColumnData(loadName("typaycat"), 60, JLabel.LEFT),


                new ColumnData(loadName("typaicompte"), 60, JLabel.LEFT)


        };


        data = new ArrayList();
        Object[] tab = new Object[11];

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


        return data.size();


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


        srcastra.astra.sys.classetransfert.configuration.Caisselibelle_T caisse = (srcastra.astra.sys.classetransfert.configuration.Caisselibelle_T) data.get(param);


        switch (param1) {


            case 0:
                return caisse.getCale_lib1();


            case 1:
                return caisse.getCale_lib2();


            case 2:
                return getCateGorie(caisse);


            case 3:
                return caisse.getComptetiers();


        }


        return "";


    }


    private String getCateGorie(srcastra.astra.sys.classetransfert.configuration.Caisselibelle_T caisse) {


        if (caisse.getCale_categorie() == caisse.CLIENT) {


            return java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", currentUser.getLangage()).getString("calib_cli");


        } else if (caisse.getCale_categorie() == caisse.FOURNISSEUR) {


            return java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", currentUser.getLangage()).getString("calib_four");


        } else if (caisse.getCale_categorie() == caisse.GENE) {


            return java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", currentUser.getLangage()).getString("calib_gen");


        }


        return "";


    }


    public Object getValueAt2(int param, int param1) {


        Object[] obj = (Object[]) data.get(param);


        return obj[param1];


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

        // afficheMe();


    }


}


