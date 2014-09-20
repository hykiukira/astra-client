/*
 * GeneralePrinting.java
 *
 * Created on 3 mars 2003, 13:37
 */

package srcastra.astra.gui.modules.printing.classelangueuser;

/**
 *
 * @author  Thomas
 */
public class GeneralePrinting implements java.io.Serializable{
    
    /** Creates a new instance of GeneralePrinting */
    public GeneralePrinting() {
       
    } 
    
    /** Getter for property cxusecleunik.
     * @return Value of property cxusecleunik.
     */
    public int getCxusecleunik() {
        return cxusecleunik;
    }
    
    /** Setter for property cxusecleunik.
     * @param cxusecleunik New value of property cxusecleunik.
     */
    public void setCxusecleunik(int cxusecleunik) {
        this.cxusecleunik = cxusecleunik;
    }
    
    /** Getter for property cxagencecleunik.
     * @return Value of property cxagencecleunik.
     */
    public int getCxagencecleunik() {
        return cxagencecleunik;
    }
    
    /** Setter for property cxagencecleunik.
     * @param cxagencecleunik New value of property cxagencecleunik.
     */
    public void setCxagencecleunik(int cxagencecleunik) {
        this.cxagencecleunik = cxagencecleunik;
    }
    
    /** Getter for property clientLmcleunik.
     * @return Value of property clientLmcleunik.
     */
    public int getClientLmcleunik() {
        return clientLmcleunik;
    }
    
    /** Setter for property clientLmcleunik.
     * @param clientLmcleunik New value of property clientLmcleunik.
     */
    public void setClientLmcleunik(int clientLmcleunik) {
        this.clientLmcleunik = clientLmcleunik;
    }
    
    /** Getter for property agence.
     * @return Value of property agence.
     */
    public srcastra.astra.gui.modules.printing.classelangueuser.Generiquecp getAgence() {
        return agence;
    }
    
    /** Setter for property agence.
     * @param agence New value of property agence.
     */
    public void setAgence(srcastra.astra.gui.modules.printing.classelangueuser.Generiquecp agence) {
        this.agence = agence;
    }
    
    /** Getter for property user.
     * @return Value of property user.
     */
    public srcastra.astra.gui.modules.printing.classelangueuser.Generiquecp getUser() {
        return user;
    }
    
    /** Setter for property user.
     * @param user New value of property user.
     */
    public void setUser(srcastra.astra.gui.modules.printing.classelangueuser.Generiquecp user) {
        this.user = user;
    }
    
    /** Getter for property natcleunik.
     * @return Value of property natcleunik.
     */
    public int getNatcleunik() {
        return natcleunik;
    }
    
    /** Setter for property natcleunik.
     * @param natcleunik New value of property natcleunik.
     */
    public void setNatcleunik(int natcleunik) {
        this.natcleunik = natcleunik;
    }    
    /** Getter for property titrecleunik.
     * @return Value of property titrecleunik.
     */
    public int getTitrecleunik() {
        return titrecleunik;
    }
    
    /** Setter for property titrecleunik.
     * @param titrecleunik New value of property titrecleunik.
     */
    public void setTitrecleunik(int titrecleunik) {
        this.titrecleunik = titrecleunik;
    }
    
    /** Getter for property clientCxCleunik.
     * @return Value of property clientCxCleunik.
     */
    public int getClientCxCleunik() {
        return clientCxCleunik;
    }
    
    /** Setter for property clientCxCleunik.
     * @param clientCxCleunik New value of property clientCxCleunik.
     */
    public void setClientCxCleunik(int clientCxCleunik) {
        this.clientCxCleunik = clientCxCleunik;
    }
    
    /** Getter for property client.
     * @return Value of property client.
     */
    public srcastra.astra.gui.modules.printing.classelangueuser.Generiquecp getClient() {
        return client;
    }    
/** Setter for property client.
     * @param client New value of property client.
     */
    public void setClient(srcastra.astra.gui.modules.printing.classelangueuser.Generiquecp client) {
        this.client = client;
    }    
    /** Getter for property passager.
     * @return Value of property passager.
     */
    public srcastra.astra.gui.modules.printing.classelangueuser.Generiquecp getPassager() {
        return passager;
    }    
    /** Setter for property passager.
     * @param passager New value of property passager.
     */
    public void setPassager(srcastra.astra.gui.modules.printing.classelangueuser.Generiquecp passager) {
        this.passager = passager;
    }    
    /** Getter for property founisseur.
     * @return Value of property founisseur.
     */
    public srcastra.astra.gui.modules.printing.classelangueuser.Generiquecp getFounisseur() {
        return founisseur;
    }    
    /** Setter for property founisseur.
     * @param founisseur New value of property founisseur.
     */
    public void setFounisseur(srcastra.astra.gui.modules.printing.classelangueuser.Generiquecp founisseur) {
        this.founisseur = founisseur;
    }
    Generiquecp agence;
    Generiquecp user;
    Generiquecp client;
    Generiquecp passager;
    Generiquecp founisseur;
    int cxusecleunik;
    int cxagencecleunik;
    int clientLmcleunik;
    int titrecleunik;
    int natcleunik;
    int clientCxCleunik;
}
