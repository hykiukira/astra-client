/* * Sous_fournisseur_T.java * * 
 Created on 15 avril 2002, 15:44 */
package srcastra.astra.sys.classetransfert;
/** 
 * 
 * @author  Administrateur 
 * @version  */
public class Sous_fournisseur_T implements java.io.Serializable{    
    
    private int frcleunik;       
    private java.sql.Date frdatetimecrea;      
    private java.sql.Date frdatetimemodif;       
    private String frmail;       
    private String frfax;       
    private String frtelephone1;      
    private String frnom1;      
    private String frreference1;      
    private String nomCrea;    
    private String nomModif;     
    private int frfournprod;
    
    /** Creates new Sous_fournisseur_T */    
    public Sous_fournisseur_T(int frcleunik,
                              String frreference1,
                              String frnom1,
                              String frtelephone1,
                              String frfax,
                              String frmail,
                              java.sql.Date frdatetimecrea,
                              java.sql.Date frdatetimemodif,
                              String nomCrea,
                              String nomModif,
                              int frfournprod )    {        
                                  
                                  this.frcleunik=frcleunik;        
                                  this.frreference1=frreference1;        
                                  this.frnom1=frnom1;        
                                  this.frtelephone1=frtelephone1;        
                                  this.frfax=frfax;        
                                  this.frmail=frmail;        
                                  this.frdatetimecrea=frdatetimecrea;        
                                  this.frdatetimemodif=frdatetimemodif;        
                                  this.nomCrea=nomCrea;        
                                  this.nomModif=nomModif;    
                                  this.frfournprod = frfournprod;
    }    
    
    
    /** Getter for property frcleunik.     
     * @return Value of property frcleunik.     */    
    public int getFrcleunik() {        
        return frcleunik;    
    }        
    
    /** Setter for property frcleunik.     
     * @param frcleunik New value of property frcleunik.     */    
    public void setFrcleunik(int frcleunik) {        
        this.frcleunik = frcleunik;    
    }        
    
    /** Getter for property frdatetimecrea.     
     * @return Value of property frdatetimecrea.     */    
    public java.sql.Date getFrdatetimecrea() {        
        return frdatetimecrea;    
    }        
    
    /** Setter for property frdatetimecrea.     
     * @param frdatetimecrea New value of property frdatetimecrea.     */    
    public void setFrdatetimecrea(java.sql.Date frdatetimecrea) {        
        this.frdatetimecrea = frdatetimecrea;    
    }        
    
    /** Getter for property frdatetimemodif.     
     * @return Value of property frdatetimemodif.     */    
    public java.sql.Date getFrdatetimemodif() {        
        return frdatetimemodif;    
    }        
    
    /** Setter for property frdatetimemodif.     
     * @param frdatetimemodif New value of property frdatetimemodif.     */    
    public void setFrdatetimemodif(java.sql.Date frdatetimemodif) {        
        this.frdatetimemodif = frdatetimemodif;    
    }        
    
    /** Getter for property frfax.     
     * @return Value of property frfax.     */    
    public java.lang.String getFrfax() {        
        return frfax;    
    }        
    
    /** Setter for property frfax.     
     * @param frfax New value of property frfax.     */    
    public void setFrfax(java.lang.String frfax) {        
        this.frfax = frfax;    
    }        
    
    /** Getter for property frmail.     
     * @return Value of property frmail.     */    
    public java.lang.String getFrmail() {        
        return frmail;    
    }        
    
    /** Setter for property frmail.     
     * @param frmail New value of property frmail.     */    
    public void setFrmail(java.lang.String frmail) {        
        this.frmail = frmail;    
    }        
    
    /** Getter for property frnom1.     
     * @return Value of property frnom1.     */    
    public java.lang.String getFrnom1() {        
        return frnom1;    
    }        
    
    /** Setter for property frnom1.     
     * @param frnom1 New value of property frnom1.     */    
    public void setFrnom1(java.lang.String frnom1) {        
        this.frnom1 = frnom1;    
    }        
    
    /** Getter for property frreference1.     
     * @return Value of property frreference1.     */    
    public java.lang.String getFrreference1() {        
        return frreference1;    
    }        
    
    /** Setter for property frreference1.     
     * @param frreference1 New value of property frreference1.     */    
    public void setFrreference1(java.lang.String frreference1) {        
        this.frreference1 = frreference1;    
    }        
    
    /** Getter for property frtelephone1.     
     * @return Value of property frtelephone1.     */    
    public java.lang.String getFrtelephone1() {        
        return frtelephone1;    
    }        
    
    /** Setter for property frtelephone1.     
     * @param frtelephone1 New value of property frtelephone1.     */    
    public void setFrtelephone1(java.lang.String frtelephone1) {        
        this.frtelephone1 = frtelephone1;    
    }        
    
    /** Getter for property nomCrea.     
     * @return Value of property nomCrea.     */    
    public java.lang.String getNomCrea() {        
        return nomCrea;    
    }        
    
    /** Setter for property nomCrea.     
     * @param nomCrea New value of property nomCrea.     */    
    public void setNomCrea(java.lang.String nomCrea) {        
        this.nomCrea = nomCrea;    
    }        
    
    /** Getter for property nomModif.     
     * @return Value of property nomModif.     */    
    public java.lang.String getNomModif() {        
        return nomModif;    
    }        
    
    /** Setter for property nomModif.     
     * @param nomModif New value of property nomModif.     */    
    public void setNomModif(java.lang.String nomModif) {        
        this.nomModif = nomModif;    
    }        
    
    /** Getter for property frfournprod.
     * @return Value of property frfournprod.
     */
    public int getFrfournprod() {
        return frfournprod;
    }
    
    /** Setter for property frfournprod.
     * @param frfournprod New value of property frfournprod.
     */
    public void setFrfournprod(int frfournprod) {
        this.frfournprod = frfournprod;
    }
    
    /** Getter for property frcleunik.     
     * @return Value of property frcleunik.     */     

}