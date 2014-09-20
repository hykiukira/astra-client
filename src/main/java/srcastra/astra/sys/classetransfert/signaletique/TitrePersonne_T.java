/*
 * TitrePersonne_T.java
 *
 * Created on 6 juin 2002, 11:28
 */

package srcastra.astra.sys.classetransfert.signaletique;

/**
 *
 * @author  Sébastien
 * @version 
 */
public class TitrePersonne_T extends srcastra.astra.sys.rmi.utils.LangueSystem implements java.io.Serializable,returnValueForAlert {

    private int tscleunik;
    private int lmcleunik;
    private String tsintitule;
    private String tsabrege;
    private java.util.ArrayList traductionTitrePers;
    private java.sql.Date tsdatetimecrea;
    private java.sql.Date tsdatetimemodif;
    private String snumerosessioncrea;
    private String snumerosessionmodif;
    
    
    /** Creates new TitrePersonne_T */
    public TitrePersonne_T() {
    }

    /** Creates new TitrePersonne_T */
    public TitrePersonne_T(int tscleunik,
                           int lmcleunik,
                           String tsintitule,
                           String tsabrege,
                           java.sql.Date tsdatetimecrea,
                           java.sql.Date tsdatetimemodif,
                           String snumerosessioncrea,
                           String snumerosessionmodif) {
          
          this.tscleunik = tscleunik;
          this.lmcleunik = lmcleunik;
          this.tsintitule = tsintitule;
          this.tsabrege = tsabrege;
          this.tsdatetimecrea = tsdatetimecrea;
          this.tsdatetimemodif = tsdatetimemodif;
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
    
    /** Getter for property traductionTitrePers.
     * @return Value of property traductionTitrePers.
     */
    public java.util.ArrayList getTraductionTitrePers() {
        return traductionTitrePers;
    }
    
    /** Setter for property traductionTitrePers.
     * @param traductionTitrePers New value of property traductionTitrePers.
     */
    public void setTraductionTitrePers(java.util.ArrayList traductionTitrePers) {
        this.traductionTitrePers = traductionTitrePers;
    }
    
    /** Getter for property tsabrege.
     * @return Value of property tsabrege.
     */
    public java.lang.String getTsabrege() {
        return tsabrege;
    }
    
    /** Setter for property tsabrege.
     * @param tsabrege New value of property tsabrege.
     */
    public void setTsabrege(java.lang.String tsabrege) {
        this.tsabrege = tsabrege;
    }
    
    /** Getter for property tscleunik.
     * @return Value of property tscleunik.
     */
    public int getTscleunik() {
        return tscleunik;
    }
    
    /** Setter for property tscleunik.
     * @param tscleunik New value of property tscleunik.
     */
    public void setTscleunik(int tscleunik) {
        this.tscleunik = tscleunik;
    }
    
    /** Getter for property tsdatetimecrea.
     * @return Value of property tsdatetimecrea.
     */
    public java.sql.Date getTsdatetimecrea() {
        return tsdatetimecrea;
    }
    
    /** Setter for property tsdatetimecrea.
     * @param tsdatetimecrea New value of property tsdatetimecrea.
     */
    public void setTsdatetimecrea(java.sql.Date tsdatetimecrea) {
        this.tsdatetimecrea = tsdatetimecrea;
    }
    
    /** Getter for property tsdatetimemodif.
     * @return Value of property tsdatetimemodif.
     */
    public java.sql.Date getTsdatetimemodif() {
        return tsdatetimemodif;
    }
    
    /** Setter for property tsdatetimemodif.
     * @param tsdatetimemodif New value of property tsdatetimemodif.
     */
    public void setTsdatetimemodif(java.sql.Date tsdatetimemodif) {
        this.tsdatetimemodif = tsdatetimemodif;
    }
    
    /** Getter for property tsintitule.
     * @return Value of property tsintitule.
     */
    public java.lang.String getTsintitule() {
        return tsintitule;
    }
    
    /** Setter for property tsintitule.
     * @param tsintitule New value of property tsintitule.
     */
    public void setTsintitule(java.lang.String tsintitule) {
        this.tsintitule = tsintitule;
    }
    
    public String[] returnValueForAlert() {
        return null;
    }
    
}
