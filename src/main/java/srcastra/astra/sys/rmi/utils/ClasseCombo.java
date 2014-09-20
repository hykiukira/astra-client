/*
 * ClasseCombo.java
 *
 * Created on 14 mai 2002, 11:28
 */

package srcastra.astra.sys.rmi.utils;

/**
 *
 * @author  Administrateur
 * @version 
 */
public class ClasseCombo extends srcastra.astra.sys.classetransfert.Gestionerreur_T {

    /** Creates new ClasseCombo */
    public ClasseCombo() {
        super();
    }
    /** Getter for property cp.
     * @return Value of property cp.
     */
    public java.lang.String getCp() {
        return cp;
    }
    
    /** Setter for property cp.
     * @param cp New value of property cp.
     */
    public void setCp(java.lang.String cp) {
        this.cp = cp;
    }
    
    /** Getter for property devise.
     * @return Value of property devise.
     */
    public java.lang.String getDevise() {
        return devise;
    }
    
    /** Setter for property devise.
     * @param devise New value of property devise.
     */
    public void setDevise(java.lang.String devise) {
        this.devise = devise;
    }
    
    /** Getter for property langue.
     * @return Value of property langue.
     */
    public java.lang.String getLangue() {
        return langue;
    }
    
    /** Setter for property langue.
     * @param langue New value of property langue.
     */
    public void setLangue(java.lang.String langue) {
        this.langue = langue;
    }
    
    /** Getter for property localite.
     * @return Value of property localite.
     */
    public java.lang.String getLocalite() {
        return localite;
    }
    
    /** Setter for property localite.
     * @param localite New value of property localite.
     */
    public void setLocalite(java.lang.String localite) {
        this.localite = localite;
    }
    
    /** Getter for property pays.
     * @return Value of property pays.
     */
    public java.lang.String getPays() {
        return pays;
    }
    
    /** Setter for property pays.
     * @param pays New value of property pays.
     */
    public void setPays(java.lang.String pays) {
        this.pays = pays;
    }
    
    /** Getter for property regimeTva.
     * @return Value of property regimeTva.
     */
    public java.lang.String getRegimeTva() {
        return regimeTva;
    }
    
    /** Setter for property regimeTva.
     * @param regimeTva New value of property regimeTva.
     */
    public void setRegimeTva(java.lang.String regimeTva) {
        this.regimeTva = regimeTva;
    }
    
private String pays;
private String localite;
private String cp;
private String langue;
private String regimeTva;
private String devise;
}
