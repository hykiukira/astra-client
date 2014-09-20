/*
 * Logement_T.java
 *
 * Created on 6 juin 2002, 11:27
 */

package srcastra.astra.sys.classetransfert.signaletique;
/**
 *
 * @author  Sébastien
 * @version 
 */

public class Logement_T extends srcastra.astra.sys.rmi.utils.LangueSystem implements java.io.Serializable,returnValueForAlert {
    
    private int ltcleunik;
    private int ltanalytique;
    private int lmcleunik;
    private String ltintitule;
    private String ltabrege;
    private java.sql.Date ltdatetimecrea;
    private java.sql.Date ltdatetimemodif;
    private String snumerosessioncrea;
    private String snumerosessionmodif;
  
    /** Creates new Logement_T */
    public Logement_T() {
    }
    
    /** Creates new Logement_T */
    public Logement_T(int ltcleunik,
                      int ltanalytique,
                      int lmcleunik,
                      String ltintitule,
                      String ltabrege,
                      java.sql.Date ltdatetimecrea,
                      java.sql.Date ltdatetimemodif,
                      String snumerosessioncrea,
                      String snumerosessionmodif) {
    
        this.ltcleunik = ltcleunik;
        this.ltanalytique = ltanalytique;
        this.lmcleunik = lmcleunik;
        this.ltintitule = ltintitule;
        this.ltabrege = ltabrege;
        this.ltdatetimecrea = ltdatetimecrea;
        this.ltdatetimemodif = ltdatetimemodif;
        this.snumerosessioncrea = snumerosessioncrea;
        this.snumerosessionmodif = snumerosessionmodif;
    }
    
    /** Getter for property lmcleunik.
     * @return Value of property lmcleunik.
     */
    public int getLmcleunik() {
        return lmcleunik;
    }    
    
    /** Setter for property lmcleunik.
     * @param lmcleunik New value of property lmcleunik.
     */
    public void setLmcleunik(int lmcleunik) {
        this.lmcleunik = lmcleunik;
    }
    
    /** Getter for property ltabrege.
     * @return Value of property ltabrege.
     */
    public java.lang.String getLtabrege() {
        return ltabrege;
    }
    
    /** Setter for property ltabrege.
     * @param ltabrege New value of property ltabrege.
     */
    public void setLtabrege(java.lang.String ltabrege) {
        this.ltabrege = ltabrege;
    }
    
    /** Getter for property ltanalytique.
     * @return Value of property ltanalytique.
     */
    public int getLtanalytique() {
        return ltanalytique;
    }
    
    /** Setter for property ltanalytique.
     * @param ltanalytique New value of property ltanalytique.
     */
    public void setLtanalytique(int ltanalytique) {
        this.ltanalytique = ltanalytique;
    }
    
    /** Getter for property ltcleunik.
     * @return Value of property ltcleunik.
     */
    public int getLtcleunik() {
        return ltcleunik;
    }
    
    /** Setter for property ltcleunik.
     * @param ltcleunik New value of property ltcleunik.
     */
    public void setLtcleunik(int ltcleunik) {
        this.ltcleunik = ltcleunik;
    }
    
    /** Getter for property ltdatetimecrea.
     * @return Value of property ltdatetimecrea.
     */
    public java.sql.Date getLtdatetimecrea() {
        return ltdatetimecrea;
    }
    
    /** Setter for property ltdatetimecrea.
     * @param ltdatetimecrea New value of property ltdatetimecrea.
     */
    public void setLtdatetimecrea(java.sql.Date ltdatetimecrea) {
        this.ltdatetimecrea = ltdatetimecrea;
    }
    
    /** Getter for property ltdatetimemodif.
     * @return Value of property ltdatetimemodif.
     */
    public java.sql.Date getLtdatetimemodif() {
        return ltdatetimemodif;
    }
    
    /** Setter for property ltdatetimemodif.
     * @param ltdatetimemodif New value of property ltdatetimemodif.
     */
    public void setLtdatetimemodif(java.sql.Date ltdatetimemodif) {
        this.ltdatetimemodif = ltdatetimemodif;
    }
    
    /** Getter for property ltintitule.
     * @return Value of property ltintitule.
     */
    public java.lang.String getLtintitule() {
        return ltintitule;
    }
    
    /** Setter for property ltintitule.
     * @param ltintitule New value of property ltintitule.
     */
    public void setLtintitule(java.lang.String ltintitule) {
        this.ltintitule = ltintitule;
    }
    
    /** Getter for property snumerosessioncrea.
     * @return Value of property snumerosessioncrea.
     */
    public java.lang.String getSnumerosessioncrea() {
        return snumerosessioncrea;
    }
    
    /** Setter for property snumerosessioncrea.
     * @param snumerosessioncrea New value of property snumerosessioncrea.
     */
    public void setSnumerosessioncrea(java.lang.String snumerosessioncrea) {
        this.snumerosessioncrea = snumerosessioncrea;
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
    
    public String[] returnValueForAlert() {
        return null;
    }
    
}
