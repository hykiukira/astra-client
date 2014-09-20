/*

 * TvaTableModel.java

 *

 * Created on 29 juillet 2003, 13:45

 */


package srcastra.astra.gui.modules.compta.achat;

import javax.swing.table.AbstractTableModel;

import srcastra.astra.gui.test.*;

import javax.swing.*;

import srcastra.astra.sys.classetransfert.*;

import java.util.*;

import srcastra.astra.gui.modules.*;

import srcastra.astra.sys.compta.*;

import srcastra.astra.sys.*;
import srcastra.astra.sys.rmi.utils.MY_bigDecimal;

/**
 * @author thomas
 */

public class TvaTableModel extends AbstractTableModel {

    ColumnData[] m_columns;

    Loginusers_T currentUser;

    ArrayList data;

    /**
     * Creates a new instance of TvaTableModel
     */

    public TvaTableModel(MainScreenModule main, Loginusers_T user) {

        this.currentUser = user;

        this.m_columns = new ColumnData[]{

                new ColumnData(loadName("col2_tva"), 12, JLabel.LEFT),

                new ColumnData(loadName("co2_base"), 12, JLabel.LEFT),

                new ColumnData(loadName("col2_montant"), 30, JLabel.RIGHT),

        };

        data = new ArrayList();

    }

    public void addData() {

        data.add(new Object[3]);

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

        return 3;

    }


    public int getRowCount() {

        if (data != null)
            return data.size();

        return 0;

    }

    private void addTva(Hashtable hash, Tva_abrev_Achat_T tva1, AchatCp achatcp) {
        Affichage aff = (Affichage) hash.get(new Integer(tva1.getTva_id()));
        if (aff == null) {
            System.out.println("creation " + tva1.getTva_rate() + " " + tva1.getTva_base() + " " + tva1.getTva_value());
            aff = new Affichage(tva1.getTva_rate().toString(), tva1.getTva_base().toString(), tva1.getTva_value().toString(), achatcp.getDc());
            if (tva1.getTva_id() != 0)
                hash.put(new Integer(tva1.getTva_id()), aff);
        } else {
            System.out.println("ajout " + tva1.getTva_rate() + " " + tva1.getTva_base() + " " + tva1.getTva_value());
            aff.addValue(tva1.getTva_base().toString(), tva1.getTva_value().toString(), achatcp.getDc().toString());
        }


    }

    private void getTvaValue() {
        Hashtable hash = new Hashtable();
        ArrayList achatCP = Achat.getAchat().getAchat();
        data = new ArrayList();
        if (achatCP != null) {
            for (int i = 0; i < achatCP.size(); i++) {
                AchatCp achatcp = (AchatCp) achatCP.get(i);
                if (achatcp != null) {
                    Tva_abrev_Achat_T tva1 = achatcp.getTva1();
                    addTva(hash, tva1, achatcp);
                    Tva_abrev_Achat_T tva2 = achatcp.getTva2();
                    addTva(hash, tva2, achatcp);
                }
            }
        }
        for (Enumeration en = hash.keys(); en.hasMoreElements();) {

            TvaTableModel.Affichage aff = (TvaTableModel.Affichage) hash.get(en.nextElement());
            data.add(aff);

        }
    }

    public class Affichage {
        public Affichage(String tvarate, String base, String tvaValue, String dc) {
            m_tvarate = new MY_bigDecimal(tvarate);
            m_base = new MY_bigDecimal(base);
            m_tvaValue = new MY_bigDecimal(tvaValue);
            m_dc = dc;
        }

        public void addValue(String base, String tvaValue, String dc) {
            if (dc.equals(m_dc)) {
                //MY_bigDecimal tmp=new MY_bigDecimal(base);
                m_base = m_base.add(new MY_bigDecimal(base));
                //MY_bigDecimal tmp2=new MY_bigDecimal(tvaValue);
                m_tvaValue = m_tvaValue.add(new MY_bigDecimal(tvaValue));
            } else {
                //MY_bigDecimal tmp=new MY_bigDecimal(base);
                m_base = m_base.subtract(new MY_bigDecimal(base));
                //MY_bigDecimal tmp2=new MY_bigDecimal(tvaValue);
                m_tvaValue = m_tvaValue.subtract(new MY_bigDecimal(tvaValue));
            }

        }

        MY_bigDecimal m_tvarate;
        MY_bigDecimal m_base;
        MY_bigDecimal m_tvaValue;
        String m_dc;
    }

    public Object getValueAt(int param, int param1) {

        if (param1 < 0 || param1 > 2)

            return "";

        if (param < 0 || param > data.size())

            return "";

        TvaTableModel.Affichage aff = (TvaTableModel.Affichage) data.get(param);

        switch (param1) {
            case 0:
                return aff.m_tvarate;
            case 1:
                return aff.m_base;
            case 2:
                return aff.m_tvaValue;
        }
        return "";

    }

    public boolean isCellEditable(int nrow, int ncol) {

        return false;

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

        // if(data==null)

        //       Logger.getDefaultLogger().log(Logger.LOG_INFOS,"tva data NULL");

        //this.data = data;
        getTvaValue();

    }


}

