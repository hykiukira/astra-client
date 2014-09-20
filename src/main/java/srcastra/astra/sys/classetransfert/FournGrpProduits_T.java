/*
 * FournGrpProduits_T.java
 *
 * Created on 19 avril 2002, 16:10
 */

package srcastra.astra.sys.classetransfert;

/**
 *
 * @author  Sébastien
 * @version 
 */
public class FournGrpProduits_T extends srcastra.astra.sys.classetransfert.Gestionerreur_T implements java.io.Serializable {

    private int frgtcleunik;
    private String frgtitrecatalog;
    private String frgtreference1;
    private String frgtreference2;
    private int frgtanalytique;
    private java.sql.Date frgtdatetimecrea;
    private java.sql.Date frgtdatetimemodi;
    private String snumerosessioncrea;
    private String snumerosessionmodif;
    private String nomcrea;
    private String nommodif;
    private int frcleunik;
   // private int gncleunik;
    
    /** Creates new FournGrpProduits_T */
    public FournGrpProduits_T() {
    }
    
     /** Creates new FournGrpProduits_T */
    public FournGrpProduits_T( int frgtcleunik,
                                int frcleunik,
                               String frgtitrecatalog,
                               String frgtreference1,
                               String frgtreference2,
                               int frgtanalytique,
                               java.sql.Date frgtdatetimecrea,
                               java.sql.Date frgtdatetimemodi,
                               String snumerosessioncrea,
                               String snumerosessionmodif,
                               String nomcrea,
                               String nommodif /*,
                               int gncleunik*/ ) {
                                   
        this.frgtcleunik = frgtcleunik;
        this.frgtitrecatalog = frgtitrecatalog;
        this.frgtreference1 = frgtreference1;
        this.frgtreference2 = frgtreference2;
        this.frgtanalytique = frgtanalytique;
        this.frgtdatetimecrea = frgtdatetimecrea;
        this.frgtdatetimemodi = frgtdatetimemodi;
        this.snumerosessioncrea = snumerosessioncrea;
        this.snumerosessionmodif = snumerosessionmodif;
        this.nomcrea = nomcrea;
        this.nommodif = nommodif;
        this.frcleunik=frcleunik;
        // this.gncleunik = gncleunik;
    }

    /** Getter for property frgtanalytique.
     * @return Value of property frgtanalytique.
     */
    public int getFrgtanalytique() {
        return frgtanalytique;
    }
    
    /** Setter for property frgtanalytique.
     * @param frgtanalytique New value of property frgtanalytique.
     */
    public void setFrgtanalytique(int frgtanalytique) {
        this.frgtanalytique = frgtanalytique;
    }
    
    /** Getter for property frgtcleunik.
     * @return Value of property frgtcleunik.
     */
    public int getFrgtcleunik() {
        return frgtcleunik;
    }
    
    /** Setter for property frgtcleunik.
     * @param frgtcleunik New value of property frgtcleunik.
     */
    public void setFrgtcleunik(int frgtcleunik) {
        this.frgtcleunik = frgtcleunik;
    }
    
    /** Getter for property frgtdatetimecrea.
     * @return Value of property frgtdatetimecrea.
     */
    public java.sql.Date getFrgtdatetimecrea() {
        return frgtdatetimecrea;
    }
    
    /** Setter for property frgtdatetimecrea.
     * @param frgtdatetimecrea New value of property frgtdatetimecrea.
     */
    public void setFrgtdatetimecrea(java.sql.Date frgtdatetimecrea) {
        this.frgtdatetimecrea = frgtdatetimecrea;
    }
    
    /** Getter for property frgtdatetimemodi.
     * @return Value of property frgtdatetimemodi.
     */
    public java.sql.Date getFrgtdatetimemodi() {
        return frgtdatetimemodi;
    }
    
    /** Setter for property frgtdatetimemodi.
     * @param frgtdatetimemodi New value of property frgtdatetimemodi.
     */
    public void setFrgtdatetimemodi(java.sql.Date frgtdatetimemodi) {
        this.frgtdatetimemodi = frgtdatetimemodi;
    }
    
    /** Getter for property frgtitrecatalog.
     * @return Value of property frgtitrecatalog.
     */
    public java.lang.String getFrgtitrecatalog() {
        return frgtitrecatalog;
    }
    
    /** Setter for property frgtitrecatalog.
     * @param frgtitrecatalog New value of property frgtitrecatalog.
     */
    public void setFrgtitrecatalog(java.lang.String frgtitrecatalog) {
        this.frgtitrecatalog = frgtitrecatalog;
    }
    
    /** Getter for property frgtreference1.
     * @return Value of property frgtreference1.
     */
    public java.lang.String getFrgtreference1() {
        return frgtreference1;
    }
    
    /** Setter for property frgtreference1.
     * @param frgtreference1 New value of property frgtreference1.
     */
    public void setFrgtreference1(java.lang.String frgtreference1) {
        this.frgtreference1 = frgtreference1;
    }
    
    /** Getter for property frgtreference2.
     * @return Value of property frgtreference2.
     */
    public java.lang.String getFrgtreference2() {
        return frgtreference2;
    }
    
    /** Setter for property frgtreference2.
     * @param frgtreference2 New value of property frgtreference2.
     */
    public void setFrgtreference2(java.lang.String frgtreference2) {
        this.frgtreference2 = frgtreference2;
    }
    
    /** Getter for property nommodif.
     * @return Value of property nommodif.
     */
    public java.lang.String getNommodif() {
        return nommodif;
    }
    
    /** Setter for property nommodif.
     * @param nommodif New value of property nommodif.
     */
    public void setNommodif(java.lang.String nommodif) {
        this.nommodif = nommodif;
    }
    
    /** Getter for property nomcrea.
     * @return Value of property nomcrea.
     */
    public java.lang.String getNomcrea() {
        return nomcrea;
    }
    
    /** Setter for property nomcrea.
     * @param nomcrea New value of property nomcrea.
     */
    public void setNomcrea(java.lang.String nomcrea) {
        this.nomcrea = nomcrea;
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
    
    /** Getter for property gncleunik.
     * @return Value of property gncleunik.
     */
  /*  public int getGncleunik() {
        return gncleunik;
    }
    */
    /** Setter for property gncleunik.
     * @param gncleunik New value of property gncleunik.
     */
  /*  public void setGncleunik(int gncleunik) {
        this.gncleunik = gncleunik;
    }
   */ 
}
