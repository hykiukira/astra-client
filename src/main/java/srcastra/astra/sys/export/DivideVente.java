/*
 * DivideVente.java
 *
 * Created on 19 février 2004, 15:24
 */

package srcastra.astra.sys.export;

/**
 *
 * @author  Administrateur
 */
public class DivideVente {
    
    /** Creates a new instance of DivideVente */
    public DivideVente() {
    }
    
    /** Getter for property cledossier.
     * @return Value of property cledossier.
     *
     */
    public long getCledossier() {
        return cledossier;
    }
    
    /** Setter for property cledossier.
     * @param cledossier New value of property cledossier.
     *
     */
    public void setCledossier(long cledossier) {
        this.cledossier = cledossier;
    }
    
    /** Getter for property jxcleunik.
     * @return Value of property jxcleunik.
     *
     */
    public int getJxcleunik() {
        return jxcleunik;
    }
    
    /** Setter for property jxcleunik.
     * @param jxcleunik New value of property jxcleunik.
     *
     */
    public void setJxcleunik(int jxcleunik) {
        this.jxcleunik = jxcleunik;
    }
    
    /** Getter for property henumpiece.
     * @return Value of property henumpiece.
     *
     */
    public long getHenumpiece() {
        return henumpiece;
    }
    
    /** Setter for property henumpiece.
     * @param henumpiece New value of property henumpiece.
     *
     */
    public void setHenumpiece(long henumpiece) {
        this.henumpiece = henumpiece;
    }
    
    long cledossier;
    int jxcleunik;
    long henumpiece;
    
}
