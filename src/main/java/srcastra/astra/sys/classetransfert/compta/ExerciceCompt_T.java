/*
 * ExerciceCompt_T.java
 *
 * Created on 21 mai 2003, 8:46
 */

package srcastra.astra.sys.classetransfert.compta;

/**
 *
 * @author  thomas
 */
public class ExerciceCompt_T implements java.io.Serializable{
    
    /** Creates a new instance of ExerciceCompt_T */
    public ExerciceCompt_T() {
    }
    
    /** Getter for property exle_abrevfr.
     * @return Value of property exle_abrevfr.
     */
    public java.lang.String getExle_abrevfr() {
        return exle_abrevfr;
    }
    
    /** Setter for property exle_abrevfr.
     * @param exle_abrevfr New value of property exle_abrevfr.
     */
    public void setExle_abrevfr(java.lang.String exle_abrevfr) {
        this.exle_abrevfr = exle_abrevfr;
    }
    
    /** Getter for property exle_abrevnl.
     * @return Value of property exle_abrevnl.
     */
    public java.lang.String getExle_abrevnl() {
        return exle_abrevnl;
    }
    
    /** Setter for property exle_abrevnl.
     * @param exle_abrevnl New value of property exle_abrevnl.
     */
    public void setExle_abrevnl(java.lang.String exle_abrevnl) {
        this.exle_abrevnl = exle_abrevnl;
    }
    
    /** Getter for property exle_annee.
     * @return Value of property exle_annee.
     */
    public java.lang.String getExle_annee() {
        return exle_annee;
    }
    
    /** Setter for property exle_annee.
     * @param exle_annee New value of property exle_annee.
     */
    public void setExle_annee(java.lang.String exle_annee) {
        this.exle_annee = exle_annee;
    }
    
    /** Getter for property exle_cleunik.
     * @return Value of property exle_cleunik.
     */
    public int getExle_cleunik() {
        return exle_cleunik;
    }
    
    /** Setter for property exle_cleunik.
     * @param exle_cleunik New value of property exle_cleunik.
     */
    public void setExle_cleunik(int exle_cleunik) {
        this.exle_cleunik = exle_cleunik;
    }
    
    /** Getter for property exle_datedebut.
     * @return Value of property exle_datedebut.
     */
    public srcastra.astra.sys.classetransfert.utils.Date getExle_datedebut() {
        return exle_datedebut;
    }
    
    /** Setter for property exle_datedebut.
     * @param exle_datedebut New value of property exle_datedebut.
     */
    public void setExle_datedebut(srcastra.astra.sys.classetransfert.utils.Date exle_datedebut) {
        this.exle_datedebut = exle_datedebut;
    }
    
    /** Getter for property exle_datedefin.
     * @return Value of property exle_datedefin.
     */
    public srcastra.astra.sys.classetransfert.utils.Date getExle_datedefin() {
        return exle_datedefin;
    }
    
    /** Setter for property exle_datedefin.
     * @param exle_datedefin New value of property exle_datedefin.
     */
    public void setExle_datedefin(srcastra.astra.sys.classetransfert.utils.Date exle_datedefin) {
        this.exle_datedefin = exle_datedefin;
    }
    
    /** Getter for property exle_debut.
     * @return Value of property exle_debut.
     */
    public int getExle_debut() {
        return exle_debut;
    }
    
    /** Setter for property exle_debut.
     * @param exle_debut New value of property exle_debut.
     */
    public void setExle_debut(int exle_debut) {
        this.exle_debut = exle_debut;
    }
    
    /** Getter for property exle_fin.
     * @return Value of property exle_fin.
     */
    public int getExle_fin() {
        return exle_fin;
    }
    
    /** Setter for property exle_fin.
     * @param exle_fin New value of property exle_fin.
     */
    public void setExle_fin(int exle_fin) {
        this.exle_fin = exle_fin;
    }
    
    /** Getter for property exle_intitulefr.
     * @return Value of property exle_intitulefr.
     */
    public java.lang.String getExle_intitulefr() {
        return exle_intitulefr;
    }
    
    /** Setter for property exle_intitulefr.
     * @param exle_intitulefr New value of property exle_intitulefr.
     */
    public void setExle_intitulefr(java.lang.String exle_intitulefr) {
        this.exle_intitulefr = exle_intitulefr;
    }
    
    /** Getter for property exle_intitulevnl.
     * @return Value of property exle_intitulevnl.
     */
    public java.lang.String getExle_intitulevnl() {
        return exle_intitulevnl;
    }
    
    /** Setter for property exle_intitulevnl.
     * @param exle_intitulevnl New value of property exle_intitulevnl.
     */
    public void setExle_intitulevnl(java.lang.String exle_intitulevnl) {
        this.exle_intitulevnl = exle_intitulevnl;
    }
    
    /** Getter for property exle_nbrperiode.
     * @return Value of property exle_nbrperiode.
     */
    public int getExle_nbrperiode() {
        return exle_nbrperiode;
    }
    
    /** Setter for property exle_nbrperiode.
     * @param exle_nbrperiode New value of property exle_nbrperiode.
     */
    public void setExle_nbrperiode(int exle_nbrperiode) {
        this.exle_nbrperiode = exle_nbrperiode;
    }
    public void calculBegAndEndDate(){
        exle_datedebut=new  srcastra.astra.sys.classetransfert.utils.Date ();
        exle_datedefin=new  srcastra.astra.sys.classetransfert.utils.Date ();
        srcastra.astra.sys.classetransfert.utils.CalculDate.calculPeriodeComptable(exle_datedebut,exle_datedefin,exle_annee,exle_debut,exle_fin);
    }
 int exle_cleunik;
 String exle_abrevfr;
 String  exle_abrevnl;
 String  exle_intitulefr;
 String  exle_intitulevnl;
 String  exle_annee;
 srcastra.astra.sys.classetransfert.utils.Date  exle_datedebut;  
 srcastra.astra.sys.classetransfert.utils.Date  exle_datedefin;  
 int exle_debut;
 int exle_fin;
 int  exle_nbrperiode;  
}
