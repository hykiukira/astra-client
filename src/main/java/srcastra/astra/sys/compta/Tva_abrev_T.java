

/*

 * Tva_abrev_T.java

 *

 * Created on 28 mai 2003, 8:52

 */



package srcastra.astra.sys.compta;
import srcastra.astra.gui.modules.compta.achat.*;


/**

 *

 * @author  Thomas

 */

public class Tva_abrev_T implements java.io.Serializable{

    

    /** Creates a new instance of Tva_abrev_T */

    public Tva_abrev_T() {

    }
    // public Tva_abrev_T(AchatCp cp) {
      //   parent=cp;
        
    //}

    

    /** Getter for property tva_base.

     * @return Value of property tva_base.

     */

    public double getTva_base() {

        return tva_base;

    }

    

    /** Setter for property tva_base.

     * @param tva_base New value of property tva_base.

     */

    public void setTva_base(double tva_base) {

        this.tva_base = tva_base;

    }

    

    /** Getter for property tva_id.

     * @return Value of property tva_id.

     */

    public int getTva_id() {

        return tva_id;

    }

    

    /** Setter for property tva_id.

     * @param tva_id New value of property tva_id.

     */

    public void setTva_id(int tva_id) {

        this.tva_id = tva_id;

    }

    

    /** Getter for property tva_rate.

     * @return Value of property tva_rate.

     */

    public float getTva_rate() {

        return tva_rate;

    }

    

    /** Setter for property tva_rate.

     * @param tva_rate New value of property tva_rate.

     */

    public void setTva_rate(float tva_rate) {

        this.tva_rate = tva_rate;

    }

    

    /** Getter for property tva_value.

     * @return Value of property tva_value.

     */

    public double getTva_value() {

        return tva_value;

    }

    

    /** Setter for property tva_value.

     * @param tva_value New value of property tva_value.

     */

    public void setTva_value(double tva_value) {

        this.tva_value = tva_value;

    }

    

    /** Getter for property tva_code.

     * @return Value of property tva_code.

     *

     */

    public java.lang.String getTva_code() {

        return tva_code;

    }

    

    /** Setter for property tva_code.

     * @param tva_code New value of property tva_code.

     *

     */

    public void setTva_code(java.lang.String tva_code) {

        this.tva_code = tva_code;

    }

    /**
     * Getter for property prixtot.
     * @return Value of property prixtot.
     */
    public double getPrixTot() {
        return prixtot;
    }    
    
    /**
     * Setter for property prixtot.
     * @param prixtot New value of property prixtot.
     */
    public void setPrixTot(double prixtot) {
        this.prixtot = prixtot;
    }    

int tva_id;
float tva_rate;
double tva_base;
double tva_value;
String tva_code;
String formule;
double prixtot;
//AchatCp parent;

}

