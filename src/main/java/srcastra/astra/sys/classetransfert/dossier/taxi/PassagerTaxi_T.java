/*
 * TaxiPassagerInfo_T.java
 *
 * Created on 11 décembre 2002, 9:19
 */

package srcastra.astra.sys.classetransfert.dossier.taxi;
import srcastra.astra.sys.classetransfert.dossier.*;
import srcastra.astra.sys.classetransfert.utils.*;
import java.util.Hashtable;
import java.util.ArrayList;
import srcastra.astra.sys.classetransfert.Grpdecision_T;
import srcastra.astra.gui.test.*;
import srcastra.astra.sys.classetransfert.dossier.brochure.DescriptionLogement_T;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import srcastra.astra.sys .classetransfert.Loginusers_T;
import srcastra.astra.sys.classetransfert.dossier.Dossier_T;
import srcastra.astra.sys.rmi.astrainterface;
/**
 *
 * @author  Sébastien
 */
public class PassagerTaxi_T extends Passager_T implements java.io.Serializable {
    
                                        
                                        
    private String passTax_aller_adresse;
    private long passTax_aller_codep;
    private String passTax_aller_localite;
    private String passTax_aller_heure;
    
    private int passTax_aller = 0;
    private int passTax_retour = 0;
    
    private String passTax_gsm;
    
    private String passTax_retour_heure;
    private String passTax_retour_adresse;
    private long passTax_retour_cp;
    private String passTax_retour_localite;
   
    
                                        
                                        
    public PassagerTaxi_T() {
    }
   
    /** Getter for property passTax_aller.
     * @return Value of property passTax_aller.
     */
    public int getPassTax_aller() {
        return passTax_aller;
    }    
    
    /** Setter for property passTax_aller.
     * @param passTax_aller New value of property passTax_aller.
     */
    public void setPassTax_aller(int passTax_aller) {
        this.passTax_aller = passTax_aller;
    }
    
    /** Getter for property passTax_aller_adresse.
     * @return Value of property passTax_aller_adresse.
     */
    public java.lang.String getPassTax_aller_adresse() {
        return passTax_aller_adresse;
    }
    
    /** Setter for property passTax_aller_adresse.
     * @param passTax_aller_adresse New value of property passTax_aller_adresse.
     */
    public void setPassTax_aller_adresse(java.lang.String passTax_aller_adresse) {
        this.passTax_aller_adresse = passTax_aller_adresse;
    }
    
    /** Getter for property passTax_aller_codep.
     * @return Value of property passTax_aller_codep.
     */
 //   public long getPassTax_aller_codep() {
   //     return passTax_aller_codep;
   //}
    
    /** Setter for property passTax_aller_codep.
     * @param passTax_aller_codep New value of property passTax_aller_codep.
     */
    public void setPassTax_aller_codep(long passTax_aller_codep) {
        this.passTax_aller_codep = passTax_aller_codep;
    }
    
    /** Getter for property passTax_aller_heure.
     * @return Value of property passTax_aller_heure.
     */
    public java.lang.String getPassTax_aller_heure() {
        return passTax_aller_heure;
    }
    
    /** Setter for property passTax_aller_heure.
     * @param passTax_aller_heure New value of property passTax_aller_heure.
     */
    public void setPassTax_aller_heure(java.lang.String passTax_aller_heure) {
        this.passTax_aller_heure = passTax_aller_heure;
    }
    
    /** Getter for property passTax_aller_localite.
     * @return Value of property passTax_aller_localite.
     */
    public java.lang.String getPassTax_aller_localite() {
        return passTax_aller_localite;
    }
    
    /** Setter for property passTax_aller_localite.
     * @param passTax_aller_localite New value of property passTax_aller_localite.
     */
    public void setPassTax_aller_localite(java.lang.String passTax_aller_localite) {
        this.passTax_aller_localite = passTax_aller_localite;
    }
    
    /** Getter for property passTax_gsm.
     * @return Value of property passTax_gsm.
     */
    public java.lang.String getPassTax_gsm() {
        return passTax_gsm;
    }
    
    /** Setter for property passTax_gsm.
     * @param passTax_gsm New value of property passTax_gsm.
     */
    public void setPassTax_gsm(java.lang.String passTax_gsm) {
        this.passTax_gsm = passTax_gsm;
    }
    
    /** Getter for property passTax_retour.
     * @return Value of property passTax_retour.
     */
    public int getPassTax_retour() {
        return passTax_retour;
    }
    
    /** Setter for property passTax_retour.
     * @param passTax_retour New value of property passTax_retour.
     */
    public void setPassTax_retour(int passTax_retour) {
        this.passTax_retour = passTax_retour;
    }
    
    /** Getter for property passTax_retour_adresse.
     * @return Value of property passTax_retour_adresse.
     */
    public java.lang.String getPassTax_retour_adresse() {
        return passTax_retour_adresse;
    }
    
    /** Setter for property passTax_retour_adresse.
     * @param passTax_retour_adresse New value of property passTax_retour_adresse.
     */
    public void setPassTax_retour_adresse(java.lang.String passTax_retour_adresse) {
        this.passTax_retour_adresse = passTax_retour_adresse;
    }
    
    /** Getter for property passTax_retour_cp.
     * @return Value of property passTax_retour_cp.
     */
  //  public long getPassTax_retour_cp() {
    //    return passTax_retour_cp;
   // }
    
    /** Setter for property passTax_retour_cp.
     * @param passTax_retour_cp New value of property passTax_retour_cp.
     */
    public void setPassTax_retour_cp(long passTax_retour_cp) {
        this.passTax_retour_cp = passTax_retour_cp;
    }
    
    /** Getter for property passTax_retour_heure.
     * @return Value of property passTax_retour_heure.
     */
    public java.lang.String getPassTax_retour_heure() {
        return passTax_retour_heure;
    }
    
    /** Setter for property passTax_retour_heure.
     * @param passTax_retour_heure New value of property passTax_retour_heure.
     */
    public void setPassTax_retour_heure(java.lang.String passTax_retour_heure) {
        this.passTax_retour_heure = passTax_retour_heure;
    }
    
    /** Getter for property passTax_retour_localite.
     * @return Value of property passTax_retour_localite.
     */
    public java.lang.String getPassTax_retour_localite() {
        return passTax_retour_localite;
    }
    
    /** Setter for property passTax_retour_localite.
     * @param passTax_retour_localite New value of property passTax_retour_localite.
     */
    public void setPassTax_retour_localite(java.lang.String passTax_retour_localite) {
        this.passTax_retour_localite = passTax_retour_localite;
    }
    
}
