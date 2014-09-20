/*
 * ResumeClasse.java
 *
 * Created on 26 avril 2004, 14:30
 */

package srcastra.astra.sys.classetransfert;
import java.util.*;
import srcastra.astra.sys.compta.*;
/**
 *
 * @author  Administrateur
 */
public class ResumeClasse implements java.io.Serializable{
    
    /** Creates a new instance of ResumeClasse */
    public ResumeClasse() {
    }
    
    /** Getter for property descriptif.
     * @return Value of property descriptif.
     *
     */
    public java.util.ArrayList getDescriptif() {
        return descriptif;
    }
    
    /** Setter for property descriptif.
     * @param descriptif New value of property descriptif.
     *
     */
    public void setDescriptif(java.util.ArrayList descriptif) {
        this.descriptif = descriptif;
    }
    
    /** Getter for property destination.
     * @return Value of property destination.
     *
     */
    public java.lang.String getDestination() {
        return destination;
    }
    
    /** Setter for property destination.
     * @param destination New value of property destination.
     *
     */
    public void setDestination(java.lang.String destination) {
        this.destination = destination;
    }
    
    /** Getter for property fournisseur.
     * @return Value of property fournisseur.
     *
     */
    public java.lang.String getFournisseur() {
        return fournisseur;
    }
    
    /** Setter for property fournisseur.
     * @param fournisseur New value of property fournisseur.
     *
     */
    public void setFournisseur(java.lang.String fournisseur) {
        this.fournisseur = fournisseur;
    }
    
    /** Getter for property groep.
     * @return Value of property groep.
     *
     */
    public java.lang.String getGroep() {
        return groep;
    }
    
    /** Setter for property groep.
     * @param groep New value of property groep.
     *
     */
    public void setGroep(java.lang.String groep) {
        this.groep = groep;
    }
    
    /** Getter for property id.
     * @return Value of property id.
     *
     */
    public long getId() {
        return id;
    }
    
    /** Setter for property id.
     * @param id New value of property id.
     *
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /** Getter for property lieu.
     * @return Value of property lieu.
     *
     */
    public java.lang.String getLogement() {
        return lieu;
    }
    
    /** Setter for property lieu.
     * @param lieu New value of property lieu.
     *
     */
    public void setLogement(java.lang.String lieu) {
        this.lieu = lieu;
    }
    
    /** Getter for property poPnr.
     * @return Value of property poPnr.
     *
     */
    public java.lang.String getPoPnr() {
        return poPnr;
    }
    
    /** Setter for property poPnr.
     * @param poPnr New value of property poPnr.
     *
     */
    public void setPoPnr(java.lang.String poPnr) {
        this.poPnr = poPnr;
    }
    
    /** Getter for property prix.
     * @return Value of property prix.
     *
     */
    public double getPrix() {
        return prix;
    }
    
    /** Setter for property prix.
     * @param prix New value of property prix.
     *
     */
    public void setPrix(double prix) {
        this.prix = MathRound.roundThisToDouble(prix);
    }
    
    /** Getter for property type.
     * @return Value of property type.
     *
     */
    public java.lang.String getType() {
        return type;
    }
    
    /** Setter for property type.
     * @param type New value of property type.
     *
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }
    
    /** Getter for property memo.
     * @return Value of property memo.
     *
     */
    public java.lang.String getMemo() {
        return memo;
    }
    
    /** Setter for property memo.
     * @param memo New value of property memo.
     *
     */
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
    }
    public void addDescriptif(Object[] desctab){
        if(descriptif==null)
            descriptif=new ArrayList();
        descriptif.add(desctab);
        
    }
    
  String type;
  String fournisseur;
  String groep;
  String destination;
  String poPnr;
  String lieu;
  double prix;
  String memo;
  ArrayList descriptif;
  long id;
  
    
}
