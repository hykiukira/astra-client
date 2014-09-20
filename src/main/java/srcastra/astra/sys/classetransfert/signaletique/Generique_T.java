/*
 * CodePost_T.java
 *
 * Created on 4 avril 2002, 16:36
 */

package srcastra.astra.sys.classetransfert.signaletique;

/**
 *
 * @author  Administrateur
 * @version 
 */
public class Generique_T extends srcastra.astra.sys.rmi.utils.LangueSystem implements java.io.Serializable, returnValueForAlert{

    /** Creates new CodePost_T */
    public Generique_T(int cxcleunik, String cxcode, String cxlocalite, int cxlangue) {
        super();
        this.generique_cleunik=cxcleunik;
        this.generique_abrev=cxcode;
        this.generique_traduction=cxlocalite;
        this.generique_langue=cxlangue;
    }
    public Generique_T() {
        super();
    }
    public Generique_T(     int trtcleunik,
                            String trtintitule,
                            String trtdatetimecrea,
                            String trtdatetimemodif,
                            String snumerosessioncrea,
                            String snumerosessionmodif,                          
                            String tratabrev,
                            String trattraduction
                           )  
    {
         super();
         this.generique_cleunik=trtcleunik;
         this.generique_intitule=trtintitule;
         this.generique_datetimecrea=new srcastra.astra.sys.classetransfert.utils.Date(trtdatetimecrea);
         this.generique_datetimodif=new srcastra.astra.sys.classetransfert.utils.Date(trtdatetimemodif);
         this.snumerosessioncrea=snumerosessioncrea;
         this.snumerosessionmodif=snumerosessionmodif;
         this.generique_traduction=trattraduction;
         this.generique_abrev=tratabrev;                                                  
        
    }
    /** Getter for property cxcleunik.
     * @return Value of property cxcleunik.
     */
   
    /** Getter for property cxcode.
     * @return Value of property cxcode.
     */
  
    
    /** Getter for property cxlangue.
     * @return Value of property cxlangue.
     */
  
  
    
    public String[] returnValueForAlert() {
        String[] returnValue=new String[2];
        returnValue[1]=this.generique_abrev;
        returnValue[0]=this.generique_traduction;
        return returnValue;
    }
    
    /** Getter for property dn_cleunik.
     * @return Value of property dn_cleunik.
     */
   
    
    /** Getter for property tn_traduction.
     * @return Value of property tn_traduction.
     */
 
    /** Getter for property generique_cleunik.
     * @return Value of property generique_cleunik.
     */
    public int getGenerique_cleunik() {
        return generique_cleunik;
    }
    
    /** Setter for property generique_cleunik.
     * @param generique_cleunik New value of property generique_cleunik.
     */
    public void setGenerique_cleunik(int generique_cleunik) {
        this.generique_cleunik = generique_cleunik;
    }
    
    /** Getter for property generique_abrev.
     * @return Value of property generique_abrev.
     */
    public java.lang.String getGenerique_abrev() {
        return generique_abrev;
    }
    
    /** Setter for property generique_abrev.
     * @param generique_abrev New value of property generique_abrev.
     */
    public void setGenerique_abrev(java.lang.String generique_abrev) {
        this.generique_abrev = generique_abrev;
    }
    
    /** Getter for property generique_traduction.
     * @return Value of property generique_traduction.
     */
    public java.lang.String getGenerique_traduction() {
        return generique_traduction;
    }
    
    /** Setter for property generique_traduction.
     * @param generique_traduction New value of property generique_traduction.
     */
    public void setGenerique_traduction(java.lang.String generique_traduction) {
        this.generique_traduction = generique_traduction;
    }
    
    /** Getter for property generique_langue.
     * @return Value of property generique_langue.
     */
    public int getGenerique_langue() {
        return generique_langue;
    }
    
    /** Setter for property generique_langue.
     * @param generique_langue New value of property generique_langue.
     */
    public void setGenerique_langue(int generique_langue) {
        this.generique_langue = generique_langue;
    }
    
    /** Getter for property generique_datetimecrea.
     * @return Value of property generique_datetimecrea.
     */
    public srcastra.astra.sys.classetransfert.utils.Date getGenerique_datetimecrea() {
        return generique_datetimecrea;
    }
    
    /** Setter for property generique_datetimecrea.
     * @param generique_datetimecrea New value of property generique_datetimecrea.
     */
    public void setGenerique_datetimecrea(srcastra.astra.sys.classetransfert.utils.Date generique_datetimecrea) {
        this.generique_datetimecrea = generique_datetimecrea;
    }
    
    /** Getter for property generique_datetimodif.
     * @return Value of property generique_datetimodif.
     */
    public srcastra.astra.sys.classetransfert.utils.Date getGenerique_datetimodif() {
        return generique_datetimodif;
    }
    
    /** Setter for property generique_datetimodif.
     * @param generique_datetimodif New value of property generique_datetimodif.
     */
    public void setGenerique_datetimodif(srcastra.astra.sys.classetransfert.utils.Date generique_datetimodif) {
        this.generique_datetimodif = generique_datetimodif;
    }
    
    /** Getter for property generique_intitule.
     * @return Value of property generique_intitule.
     */
    public java.lang.String getGenerique_intitule() {
        return generique_intitule;
    }
    
    /** Setter for property generique_intitule.
     * @param generique_intitule New value of property generique_intitule.
     */
    public void setGenerique_intitule(java.lang.String generique_intitule) {
        this.generique_intitule = generique_intitule;
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
    
    /** Getter for property cxcleunik.
     * @return Value of property cxcleunik.
     */

    private int generique_cleunik;
    private String generique_abrev ;
    private String generique_traduction; 
    private int generique_langue;
    private String snumerosessioncrea;
    private String snumerosessionmodif;
    private String generique_intitule;
    private srcastra.astra.sys.classetransfert.utils.Date generique_datetimecrea;
    private srcastra.astra.sys.classetransfert.utils.Date generique_datetimodif;
    
}
