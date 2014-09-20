/*
 * FournMemo_T.java
 *
 * Created on 1 octobre 2002, 8:53
 */

package srcastra.astra.sys.classetransfert;

/**
 *
 * @author  Sébastien
 */


public class FournMemo_T extends srcastra.astra.sys.classetransfert.Gestionerreur_T implements java.io.Serializable {
    
    private int frcleunik;
    private String frmemo;
    private String snumerosessionmodif;    
    
    /** Creates a new instance of FournMemo_T */
    public FournMemo_T() {
    }
    
    /** Creates a new instance of FournMemo_T */
    public FournMemo_T(int frcleunik, String frmemo, String snumerosessionmodif) {
        this.frcleunik = frcleunik;
        this.frmemo = frmemo;
        this.snumerosessionmodif = snumerosessionmodif;
    }
    
    /** Getter for property frcleunik.
     * @return Value of property frcleunik.
     */
    public int getFrcleunik() {
        return frcleunik;
    }
    
    /** Setter for property frcleunik.
     * @param frcleunik New value of property frcleunik.
     */
    public void setFrcleunik(int frcleunik) {
        this.frcleunik = frcleunik;
    }
    
    /** Getter for property frmemo.
     * @return Value of property frmemo.
     */
    public java.lang.String getFrmemo() {
        return frmemo;
    }
    
    /** Setter for property frmemo.
     * @param frmemo New value of property frmemo.
     */
    public void setFrmemo(java.lang.String frmemo) {
        this.frmemo = frmemo;
    }
    
    /** Getter for property snumerosessionmodif.
     * @return Value of property snumerosessionmodif.
     */
    public java.lang.String getSnumerosessionmodif() {
        return snumerosessionmodif;
    }    
   
    /** Setter for property snumerosessionmodif.
     * @param snumerosessionmodif New value of property snumerosessionmodif.
     */
    public void setSnumerosessionmodif(java.lang.String snumerosessionmodif) {
        this.snumerosessionmodif = snumerosessionmodif;
    }    
    
}
