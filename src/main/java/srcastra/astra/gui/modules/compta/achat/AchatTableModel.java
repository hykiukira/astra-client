/*

 * AchatTableModel.java

 *

 * Created on 22 juillet 2003, 9:43

 */


package srcastra.astra.gui.modules.compta.achat;

import javax.swing.table.AbstractTableModel;

import srcastra.astra.gui.test.*;

import javax.swing.*;

import srcastra.astra.sys.classetransfert.*;

import java.util.*;

import srcastra.astra.gui.modules.*;

import srcastra.astra.sys.rmi.*;

/**
 * @author thomas
 */

public class AchatTableModel extends AbstractTableModel {


    /**
     * Creates a new instance of AchatTableModel
     */

    ColumnData[] m_columns;

    Loginusers_T currentUser;

    ArrayList data;

    MainScreenModule parent;
    Achat frame;

    public AchatTableModel(MainScreenModule main, Loginusers_T user) {

        this.currentUser = user;
        parent = main;

        this.m_columns = new ColumnData[]{

                new ColumnData(loadName("col_compte"), 12, JLabel.LEFT),

                new ColumnData(loadName("col_tva"), 12, JLabel.LEFT),

                new ColumnData(loadName("col_montant"), 25, JLabel.RIGHT),

                new ColumnData(loadName("col_dc"), 5, JLabel.LEFT),

                new ColumnData(loadName("col_com"), 80, JLabel.LEFT),

                new ColumnData(loadName("col_po"), 12, JLabel.LEFT),

                new ColumnData("Dossier", 17, JLabel.LEFT)

        };

        data = new ArrayList();

        // data.add(new Object[5]);

    }

    public void addData() {

        Object[] tab = new Object[16];
        AchatCp achatcp = new AchatCp();
        int tmp = ((Achat) parent).getCategoriejournal();

        if (tmp == ParamComptable.JOURNAL_ACHAT) {
            achatcp.setDc("D");
        } else if (tmp == ParamComptable.JOURNAL_NCACHAT) {
            achatcp.setDc("C");

        }
        Achat_T achat = Achat.getAchat();
        achatcp.setFrcleunik(achat.getFrcleunik());
        achat.getAchat().add(achatcp);
        //data.add(achatcp);

    }

    public void addData(AchatCp achatcp) {
        achatcp.setFrcleunik(Achat.getAchat().getFrcleunik());
        Achat.getAchat().getAchat().add(achatcp);
        //data.add(achatcp);

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
        if (Achat.getAchat() != null)
            return Achat.getAchat().getAchat().size();
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

        if (param1 < 0 || param1 > 6)

            return "";

        if (param < 0 || param > Achat.getAchat().getAchat().size())

            return "";

        AchatCp achatCp = (AchatCp) Achat.getAchat().getAchat().get(param);
        if (achatCp != null) {
            switch (param1) {
                case 0:
                    return achatCp.getCeText();
                case 1:
                    return achatCp.getTva1().getTva_code();
                case 2:
                    return achatCp.getBase();
                case 3:
                    return achatCp.getDc();
                case 4:
                    return achatCp.getCommentaire();
                case 5:
                    return achatCp.getPoPnr();
                case 6:
                    return achatCp.getNumdos();
            }
        }
        return "";


    }

    public Object getValueAt2(int param, int param1) {
        AchatCp achatcp = (AchatCp) Achat.getAchat().getAchat().get(param);
        switch (param1) {
            case 0:
                return achatcp.getCeText();
            case 1:
                return achatcp.getTva1().getTva_code();
            case 6:
                return new Integer(achatcp.getCe_cleunik());
            case 7:
                return new Integer(achatcp.getTva1().getTva_id());
            case 2:
                return achatcp.getBase();
            case 3:
                return achatcp.getDc();
            case 4:
                return achatcp.getCommentaire();

        }
        return "";
        // Object[] obj=(Object[])data.get(param);

        //return obj[param1];

    }


    public void setValueAt(int param, int param1, Object obj) {
        AchatCp achatcp = (AchatCp) Achat.getAchat().getAchat().get(param);
        if (achatcp != null) {
            switch (param1) {
                case 3: {
                    if (obj != null) {
                        achatcp.setDc(obj.toString());

                    }
                    return;
                }
                case 4: {
                    if (obj != null) {
                        achatcp.setCommentaire(obj.toString());
                    }
                    return;
                }
            }

        }

    }
    /*  Object[] tmp=(Object[] )data.get(param);

      if(tmp!=null){

        tmp[param1]=obj;   

      }

    } */

    public void setValueTva(int param, Object cleunik, Object prcttva, Object base, Object montantTva, Object prixtot, Object libelle) {

        //  Object[] tmp=(Object[] )data.get(param);
        AchatCp achatCp = (AchatCp) Achat.getAchat().getAchat().get(param);
        if (achatCp != null) {
            achatCp.getTva1().setTva_id(Integer.parseInt(cleunik.toString()));
            achatCp.getTva1().setTva_code(libelle.toString());
            achatCp.setBase(base.toString());
            achatCp.getTva1().setTva_base(base.toString());
            achatCp.getTva1().setTva_value(montantTva.toString());
            achatCp.getTva1().setPrixTot(prixtot.toString());
            achatCp.getTva1().setTva_rate(prcttva.toString());
            //achatCp.getTva1().setDebitCredit(achatCp.getDc());
            /*tmp[7]=cleunik;

            tmp[1]=libelle;

            tmp[2]=base;

            tmp[8]=montantTva;

            tmp[9]=prixtot;

            tmp[10]=prcttva;*/

        }

    }

    public void setValueCompte(int param, Object cleunik, Object compte) {
        AchatCp achatCp = (AchatCp) Achat.getAchat().getAchat().get(param);
        if (achatCp != null) {
            achatCp.setCe_cleunik(Integer.parseInt(cleunik.toString()));
            achatCp.setCeText(compte.toString());
        }
    }

    public void setValueMontant(int param, Object base, Object montanttva, Object montanttot) {
        AchatCp achatCp = (AchatCp) Achat.getAchat().getAchat().get(param);
        if (achatCp != null) {
            achatCp.setBase(base.toString());
            achatCp.getTva1().setTva_base(base.toString());
            achatCp.getTva1().setTva_value(montanttva.toString());
            achatCp.getTva1().setPrixTot(montanttot.toString());
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

    }


}

