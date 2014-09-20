/*
 * CodePost_T.java
 *
 * Created on 4 avril 2002, 16:36
 */

package srcastra.astra.sys.classetransfert;

/**
 *
 * @author  Administrateur
 * @version 
 */
public class CodePost_T extends srcastra.astra.sys.rmi.utils.LangueSystem implements java.io.Serializable{

    /** Creates new CodePost_T */
    public CodePost_T(int cxcleunik,String cxcode, String cxlocalite,int cxlangue) {
        super();
        this.cxcleunik=cxcleunik;
        this.cxcode=cxcode;
        this.cxlocalite=cxlocalite;
        this.cxlangue=cxlangue;
    }
    
    /** Getter for property cxcleunik.
     * @return Value of property cxcleunik.
     */
    public int getCxcleunik() {
        return cxcleunik;
    }
    
    /** Setter for property cxcleunik.
     * @param cxcleunik New value of property cxcleunik.
     */
    public void setCxcleunik(int cxcleunik) {
        this.cxcleunik = cxcleunik;
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
    
    /** Getter for property cxlangue.
     * @return Value of property cxlangue.
     */
    public int getCxlangue() {
        return cxlangue;
    }
    
    /** Setter for property cxlangue.
     * @param cxlangue New value of property cxlangue.
     */
    public void setCxlangue(int cxlangue) {
        this.cxlangue = cxlangue;
    }
    
    /** Getter for property cxlocalite.
     * @return Value of property cxlocalite.
     */
    public java.lang.String getCxlocalite() {
        return cxlocalite;
    }
    
    /** Setter for property cxlocalite.
     * @param cxlocalite New value of property cxlocalite.
     */
    public void setCxlocalite(java.lang.String cxlocalite) {
        this.cxlocalite = cxlocalite;
    }
    
    /** Getter for property pyscleunik.
     * @return Value of property pyscleunik.
     */
    public int getPyscleunik() {
        return pyscleunik;
    }
    
    /** Setter for property pyscleunik.
     * @param pyscleunik New value of property pyscleunik.
     */
    public void setPyscleunik(int pyscleunik) {
        this.pyscleunik = pyscleunik;
    }
    
    /** Getter for property cxcleunik.
     * @return Value of property cxcleunik.
     */

    private int cxcleunik;
    private String cxcode;
    private String cxlocalite;
    private int cxlangue;
    private int pyscleunik;

}
