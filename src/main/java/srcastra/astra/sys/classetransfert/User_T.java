/*
 * User_T.java
 *
 * Created on 26 december 2002, 12:06
 */

package srcastra.astra.sys.classetransfert;

/**
 *
 * @author  Thomas
 */
public class User_T implements java.io.Serializable{ 
    
    /** Creates a new instance of User_T */
    public User_T(int urcleunik) {
        this.urcleunik=urcleunik;
    }
    
    /** Getter for property cxcode.
     * @return Value of property cxcode.
     */
    public java.lang.String getCxcode() {
        return cxcode;
    }
    
    /** Setter for property cxcode.
     * @param cxcode New value of property cxcode.
     */
    public void setCxcode(java.lang.String cxcode) {
        this.cxcode = cxcode;
    }
    
    /** Getter for property eelcleunik.
     * @return Value of property eelcleunik.
     */
    public int getEelcleunik() {
        return eelcleunik;
    }
    
    /** Setter for property eelcleunik.
     * @param eelcleunik New value of property eelcleunik.
     */
    public void setEelcleunik(int eelcleunik) {
        this.eelcleunik = eelcleunik;
    }
    
    /** Getter for property entiténom.
     * @return Value of property entiténom.
  
    public java.lang.String getSocietenom() {
        return societenom;
    }
    
    /** Setter for property sociéténom.
     * @param sociéténom New value of property sociéténom.
     */
    public void setSocietenom(java.lang.String sociéténom) {
        this.societenom = societenom;
    }
    
    /** Getter for property uradresse.
     * @return Value of property uradresse.
     */
    public java.lang.String getUradresse() {
        return uradresse;
    }
    
    /** Setter for property uradresse.
     * @param uradresse New value of property uradresse.
     */
    public void setUradresse(java.lang.String uradresse) {
        this.uradresse = uradresse;
    }
    
    /** Getter for property urcleunik.
     * @return Value of property urcleunik.
     */
    public int getUrcleunik() {
        return urcleunik;
    }
    
    /** Setter for property urcleunik.
     * @param urcleunik New value of property urcleunik.
     */
    public int getUrdroit() {
        return urdroit;
    }
    
    /** Setter for property urdroit.
     * @param urdroit New value of property urdroit.
     */
    public void setUrdroit(int urdroit) {
        this.urdroit = urdroit;
    }
    
    /** Getter for property urgsm.
     * @return Value of property urgsm.
     */
    public java.lang.String getUrgsm() {
        return urgsm;
    }
    
    /** Setter for property urgsm.
     * @param urgsm New value of property urgsm.
     */
    public void setUrgsm(java.lang.String urgsm) {
        this.urgsm = urgsm;
    }
    
    /** Getter for property urlocalite.
     * @return Value of property urlocalite.
     */
    public java.lang.String getUrlocalite() {
        return urlocalite;
    }
    
    /** Setter for property urlocalite.
     * @param urlocalite New value of property urlocalite.
     */
    public void setUrlocalite(java.lang.String urlocalite) {
        this.urlocalite = urlocalite;
    }
    
    /** Getter for property urmailbur.
     * @return Value of property urmailbur.
     */
    public java.lang.String getUrmailbur() {
        return urmailbur;
    }
    
    /** Setter for property urmailbur.
     * @param urmailbur New value of property urmailbur.
     */
    public void setUrmailbur(java.lang.String urmailbur) {
        this.urmailbur = urmailbur;
    }
    
    /** Getter for property urmailprive.
     * @return Value of property urmailprive.
     */
    public java.lang.String getUrmailprive() {
        return urmailprive;
    }
    
    /** Setter for property urmailprive.
     * @param urmailprive New value of property urmailprive.
     */
    public void setUrmailprive(java.lang.String urmailprive) {
        this.urmailprive = urmailprive;
    }
    
    /** Getter for property urnom.
     * @return Value of property urnom.
     */
    public java.lang.String getUrnom() {
        return urnom;
    }
    
    /** Setter for property urnom.
     * @param urnom New value of property urnom.
     */
    public void setUrnom(java.lang.String urnom) {
        this.urnom = urnom;
    }
    
    /** Getter for property urprenom.
     * @return Value of property urprenom.
     */
    public java.lang.String getUrprenom() {
        return urprenom;
    }
    
    /** Setter for property urprenom.
     * @param urprenom New value of property urprenom.
     */
    public void setUrprenom(java.lang.String urprenom) {
        this.urprenom = urprenom;
    }
    
    /** Getter for property urtelephonebd.
     * @return Value of property urtelephonebd.
     */
    public java.lang.String getUrtelephonebd() {
        return urtelephonebd;
    }
    
    /** Setter for property urtelephonebd.
     * @param urtelephonebd New value of property urtelephonebd.
     */
    public void setUrtelephonebd(java.lang.String urtelephonebd) {
        this.urtelephonebd = urtelephonebd;
    }
    
    /** Getter for property urtelephonepriv.
     * @return Value of property urtelephonepriv.
     */
    public java.lang.String getUrtelephonepriv() {
        return urtelephonepriv;
    }
    
    /** Setter for property urtelephonepriv.
     * @param urtelephonepriv New value of property urtelephonepriv.
     */
    public void setUrtelephonepriv(java.lang.String urtelephonepriv) {
        this.urtelephonepriv = urtelephonepriv;
    }
    
    /** Getter for property entitenom.
     * @return Value of property entitenom.
     */
    public java.lang.String getEntitenom() {
        return entitenom;
    }
    
    /** Setter for property entitenom.
     * @param entitenom New value of property entitenom.
     */
    public void setEntitenom(java.lang.String entitenom) {
        this.entitenom = entitenom;
    }
    
    int urcleunik;
    String urnom;
    String urprenom;
    int urdroit;
    String uradresse;
    String cxcode;
    String urlocalite;
    String urmailbur;
    String urmailprive;
    String urtelephonebd;
    String urtelephonepriv;
    String urgsm;
    int eelcleunik;
    String entitenom;
    String societenom;
    
}
