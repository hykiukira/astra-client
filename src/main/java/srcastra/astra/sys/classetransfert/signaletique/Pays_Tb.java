/*
 * Pays_T.java
 *
 * Created on 17 avril 2002, 10:01
 */

package srcastra.astra.sys.classetransfert.signaletique;

/**
 *
 * @author  Administrateur
 * @version 
 */
public class Pays_Tb extends srcastra.astra.sys.rmi.utils.LangueSystem implements java.io.Serializable,returnValueForAlert{

    /** Creates new Pays_T */
    public Pays_Tb() {
        super();
    }
     public Pays_Tb(int pyscleunik, String pysabrev, String pystraduction) {
         super();
         this.pyscleunik=pyscleunik;
         this.pysabrev=pysabrev;
         this.pystraduction=pystraduction;        
    }
    
    /** Getter for property listeTraductPays.
     * @return Value of property listeTraductPays.
     */
    public java.util.ArrayList getListeTraductPays() {
        return listeTraductPays;
    }
    
    /** Setter for property listeTraductPays.
     * @param listeTraductPays New value of property listeTraductPays.
     */
    public void setListeTraductPays(java.util.ArrayList listeTraductPays) {
        this.listeTraductPays = listeTraductPays;
    }
    
    /** Getter for property pysabrev.
     * @return Value of property pysabrev.
     */
    public String getPysabrev() {
        return pysabrev;
    }
    
    /** Setter for property pysabrev.
     * @param pysabrev New value of property pysabrev.
     */
    public void setPysabrev(String pysabrev) {
        this.pysabrev = pysabrev;
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
    
    /** Getter for property pystraduction.
     * @return Value of property pystraduction.
     */
    public java.lang.String getPystraduction() {
        return pystraduction;
    }
    
    /** Setter for property pystraduction.
     * @param pystraduction New value of property pystraduction.
     */
    public void setPystraduction(java.lang.String pystraduction) {
        this.pystraduction = pystraduction;
    }
    
    /** Getter for property pysLangue.
     * @return Value of property pysLangue.
     */
    public int getPysLangue() {
        return pysLangue;
    }
    
    /** Setter for property pysLangue.
     * @param pysLangue New value of property pysLangue.
     */
    public void setPysLangue(int pysLangue) {
        this.pysLangue = pysLangue;
    }
    
    public String[] returnValueForAlert() {
        return null;
    }
    
    private int pyscleunik;
    private String pysabrev;
    private String pystraduction;
    private java.util.ArrayList listeTraductPays;
    private int pysLangue;
        private class langue
        {
            public langue(int lmcleunik,String pystraduction)
            {
              this.lmcleunik=lmcleunik;
              this.pystraduction=pystraduction;               
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
            
            /** Getter for property pystraduction.
             * @return Value of property pystraduction.
             */
            public java.lang.String getPystraduction() {
                return pystraduction;
            }
            
            /** Setter for property pystraduction.
             * @param pystraduction New value of property pystraduction.
             */
            public void setPystraduction(java.lang.String pystraduction) {
                this.pystraduction = pystraduction;
            }
            
            private int lmcleunik;
            private String pystraduction;
        }

}
