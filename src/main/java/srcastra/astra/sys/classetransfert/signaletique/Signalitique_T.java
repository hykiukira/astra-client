/* * Transport_T.java * * Created on 6 mai 2002, 13:46 */package srcastra.astra.sys.classetransfert.signaletique;/** * * @author  Administrateur * @version  */public class Signalitique_T extends srcastra.astra.sys.rmi.utils.LangueSystem implements java.io.Serializable,returnValueForAlert {    /** Creates new Transport_T */    public Signalitique_T() {    }     public Signalitique_T(int lmcleunik, int tecleunik,int caecleunik,String tetraduction,String teabrev) {         this.lmcleunik=lmcleunik;         this.tecleunik=tecleunik;         this.caecleunik=caecleunik;         this.tetraduction=tetraduction;         this.teabrev=teabrev;    }        /** Getter for property caecleunik.     * @return Value of property caecleunik.     */    public int getCaecleunik() {        return caecleunik;    }        /** Setter for property caecleunik.     * @param caecleunik New value of property caecleunik.     */    public void setCaecleunik(int caecleunik) {        this.caecleunik = caecleunik;    }        /** Getter for property lmcleunik.     * @return Value of property lmcleunik.     */    public int getLmcleunik() {        return lmcleunik;    }        /** Setter for property lmcleunik.     * @param lmcleunik New value of property lmcleunik.     */    public void setLmcleunik(int lmcleunik) {        this.lmcleunik = lmcleunik;    }        /** Getter for property teabrev.     * @return Value of property teabrev.     */    public java.lang.String getTeabrev() {        return teabrev;    }        /** Setter for property teabrev.     * @param teabrev New value of property teabrev.     */    public void setTeabrev(java.lang.String teabrev) {        this.teabrev = teabrev;    }        /** Getter for property tecleunik.     * @return Value of property tecleunik.     */    public int getTecleunik() {        return tecleunik;    }        /** Setter for property tecleunik.     * @param tecleunik New value of property tecleunik.     */    public void setTecleunik(int tecleunik) {        this.tecleunik = tecleunik;    }        /** Getter for property tetraduction.     * @return Value of property tetraduction.     */    public java.lang.String getTetraduction() {        return tetraduction;    }        /** Setter for property tetraduction.     * @param tetraduction New value of property tetraduction.     */    public void setTetraduction(java.lang.String tetraduction) {        this.tetraduction = tetraduction;    }    public String[] returnValueForAlert() {        return null;    }          private int lmcleunik;  private int tecleunik;  private int caecleunik;  private String tetraduction;  private String teabrev;}