/*

* Tva_abrev_T.java

*

* Created on 28 mai 2003, 8:52

*/


package srcastra.astra.gui.modules.compta.achat;

import srcastra.astra.gui.modules.compta.achat.*;
import srcastra.astra.sys.rmi.utils.MY_bigDecimal;


/**
 * @author Thomas
 */

public class Tva_abrev_Achat_T implements java.io.Serializable {


    /**
     * Creates a new instance of Tva_abrev_T
     */

    public Tva_abrev_Achat_T() {
        tva_base = new MY_bigDecimal("0.00");
        tva_value = new MY_bigDecimal("0.00");
        tva_rate = new MY_bigDecimal("0.00");
        prixtot = new MY_bigDecimal("0.00");

    }

    public Tva_abrev_Achat_T(AchatCp cp) {
        tva_base = new MY_bigDecimal("0.00");
        tva_value = new MY_bigDecimal("0.00");
        tva_rate = new MY_bigDecimal("0.00");
        prixtot = new MY_bigDecimal("0.00");
        parent = cp;

    }

    public Tva_abrev_Achat_T(int tvaid, String base, String tvaValue, String rate, String dc) {
        this.tva_id = tvaid;
        this.tva_base = new MY_bigDecimal(base);
        this.tva_rate = new MY_bigDecimal(rate);
        this.tva_value = new MY_bigDecimal(tvaValue);
        this.dc = dc;

        //tva_base=new MY_bigDecimal("0.00");
        //tva_value=new MY_bigDecimal("0.00");
        //tva_rate=new MY_bigDecimal("0.00");
        // parent=cp;

    }


    /**
     * Getter for property tva_base.
     *
     * @return Value of property tva_base.
     */

    public MY_bigDecimal getTva_base() {

        return tva_base;

    }


    /**
     * Setter for property tva_base.
     *
     * @param tva_base New value of property tva_base.
     */

    public void setTva_base(String tva_base) {

        this.tva_base = new MY_bigDecimal(tva_base);
        this.tva_base.abs();

    }


    /**
     * Getter for property tva_id.
     *
     * @return Value of property tva_id.
     */

    public int getTva_id() {

        return tva_id;

    }


    /**
     * Setter for property tva_id.
     *
     * @param tva_id New value of property tva_id.
     */

    public void setTva_id(int tva_id) {

        this.tva_id = tva_id;

    }


    /**
     * Getter for property tva_rate.
     *
     * @return Value of property tva_rate.
     */

    public MY_bigDecimal getTva_rate() {

        return tva_rate;

    }


    /**
     * Setter for property tva_rate.
     *
     * @param tva_rate New value of property tva_rate.
     */

    public void setTva_rate(String tva_rate) {

        this.tva_rate = new MY_bigDecimal(tva_rate);

    }


    /**
     * Getter for property tva_value.
     *
     * @return Value of property tva_value.
     */

    public MY_bigDecimal getTva_value() {

        return tva_value;

    }


    /**
     * Setter for property tva_value.
     *
     * @param tva_value New value of property tva_value.
     */

    public void setTva_value(String tva_value) {

        this.tva_value = new MY_bigDecimal(tva_value);
        this.tva_value.abs();

    }


    /**
     * Getter for property tva_code.
     *
     * @return Value of property tva_code.
     */

    public java.lang.String getTva_code() {

        return tva_code;

    }


    /**
     * Setter for property tva_code.
     *
     * @param tva_code New value of property tva_code.
     */

    public void setTva_code(java.lang.String tva_code) {

        this.tva_code = tva_code;

    }

    /**
     * Getter for property prixtot.
     *
     * @return Value of property prixtot.
     */
    public MY_bigDecimal getPrixTot() {
        return prixtot;
    }

    /**
     * Setter for property prixtot.
     *
     * @param prixtot New value of property prixtot.
     */
    public void setPrixTot(String prixtot) {
        this.prixtot = new MY_bigDecimal(prixtot);
        this.prixtot.abs();
    }

    public void addValue(String bases, String tvaValues, String m_dc) {

        MY_bigDecimal base = new MY_bigDecimal(bases);
        MY_bigDecimal tvaValue = new MY_bigDecimal(tvaValues);
        if (dc.equals(m_dc)) {
            tva_base = tva_base.add(base);
            tva_value = tva_value.add(tvaValue);
        } else {

            tva_base = tva_base.subtract(base);
            tva_value = tva_value.subtract(tvaValue);
            if (tva_base.doubleValue() < 0) {
                tva_base.abs();
                tva_value.abs();
                if (dc.equals("D")) {
                    dc = "C";
                } else if (dc.equals("C")) {
                    dc = "D";
                }


            }
        }
    }

    /**
     * Getter for property dc.
     *
     * @return Value of property dc.
     */
    public java.lang.String getDc() {
        return dc;
    }

    /**
     * Setter for property dc.
     *
     * @param dc New value of property dc.
     */
    public void setDc(java.lang.String dc) {
        this.dc = dc;
    }

    int tva_id;
    MY_bigDecimal tva_rate;
    MY_bigDecimal tva_base;
    MY_bigDecimal tva_value;
    String tva_code;
    String formule;
    MY_bigDecimal prixtot;
    AchatCp parent;
    String dc;

}

