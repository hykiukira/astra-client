/*
 * TransfertDienst.java
 *
 * Created on 24 août 2004, 22:22
 */

package srcastra.astra.sys.btn;
import org.jdom.*;
/**
 *
 * @author  Administrateur
 */
public class ArrangementDienst implements Dienst{
    
    /** Creates a new instance of TransfertDienst */
    Element dienst;
    public ArrangementDienst() {
        
        
    }
    public  String getDienst(Element element){
        ArrangementDienst transfert=new ArrangementDienst();
        String retval="";
        if(element.getChild("soortdienstcode")!=null){
            transfert.setType(element.getChild("soortdienstcode").getChild("code_astra").getText());
            retval=retval+"Type :"+transfert.getType()+"    ";
        }
        if(element.getChild("begindatum")!=null){
            transfert.setDatedebut(element.getChild("begindatum").getChild("code_btn").getText());
            retval=retval+"Date debut :"+transfert.getDatedebut()+"    ";
        }
        if(element.getChild("einddatum")!=null){
            transfert.setDatefin(element.getChild("einddatum").getChild("code_btn").getText());
             retval=retval+"Date fin :"+transfert.getDatefin()+"\n";
        }
        if(element.getChild("statusdienst")!=null){
            transfert.setStatut(element.getChild("statusdienst").getChild("code_astra").getText());
             retval=retval+"Statut :"+transfert.getStatut()+"    ";
        }
        if(element.getChild("typevervoercode")!=null){
            transfert.setTypeTransport(element.getChild("typevervoercode").getChild("code_astra").getText());
             retval=retval+"Transport :"+transfert.getTypeTransport()+"    ";
        }
        if(element.getChild("vertrekplcode")!=null)    {
            transfert.setDepart(element.getChild("vertrekplcode").getChild("code_astra").getText());
             retval=retval+"Départ :"+transfert.getDepart()+"\n";
        }
        if(element.getChild("aankomstplcode")!=null){
            transfert.setArrive(element.getChild("aankomstplcode").getChild("code_astra").getText());
            retval=retval+"Arrivé :"+transfert.getArrive()+"    ";
        }
        if(element.getChild("uurvertrek")!=null){
            transfert.setHeuredep(element.getChild("uurvertrek").getChild("code_btn").getText());
            retval=retval+"Heure départ :"+transfert.getHeuredep()+"    ";
        }
        if(element.getChild("uuraankomst")!=null){
            transfert.setHeurearriv(element.getChild("uuraankomst").getChild("code_btn").getText());
            retval=retval+"Heure arrivé :"+transfert.getHeurearriv()+"\n";
        }
        if(element.getChild("trklassecd")!=null){
            transfert.setClasse(element.getChild("trklassecd").getChild("code_astra").getText());
            retval=retval+"Classe :"+transfert.getClasse()+"    ";
        }
     //  transfert.setCarrier(element.getChild("carriercode").getChild("code_astra").getText());
        // retval=retval+"Carrier :"+transfert.getCarrier()+"\n";
     
        
        
       
       
       
       
        
        
        
        
       
        
        return retval;
        
    }
    
    /**
     * Getter for property aller.
     * @return Value of property aller.
     */
    public java.lang.String getAller() {
        return aller;
    }
    
    /**
     * Setter for property aller.
     * @param aller New value of property aller.
     */
    public void setAller(java.lang.String aller) {
        this.aller = aller;
    }
    
    /**
     * Getter for property arrive.
     * @return Value of property arrive.
     */
    public java.lang.String getArrive() {
        return arrive;
    }
    
    /**
     * Setter for property arrive.
     * @param arrive New value of property arrive.
     */
    public void setArrive(java.lang.String arrive) {
        this.arrive = arrive;
    }
    
    /**
     * Getter for property carrier.
     * @return Value of property carrier.
     */
  
    
    /**
     * Getter for property classe.
     * @return Value of property classe.
     */
    public java.lang.String getClasse() {
        return classe;
    }
    
    /**
     * Setter for property classe.
     * @param classe New value of property classe.
     */
    public void setClasse(java.lang.String classe) {
        this.classe = classe;
    }
    
    /**
     * Getter for property datedebut.
     * @return Value of property datedebut.
     */
    public java.lang.String getDatedebut() {
        return datedebut;
    }
    
    /**
     * Setter for property datedebut.
     * @param datedebut New value of property datedebut.
     */
    public void setDatedebut(java.lang.String datedebut) {
        this.datedebut = datedebut;
    }
    
    /**
     * Getter for property datefin.
     * @return Value of property datefin.
     */
    public java.lang.String getDatefin() {
        return datefin;
    }
    
    /**
     * Setter for property datefin.
     * @param datefin New value of property datefin.
     */
    public void setDatefin(java.lang.String datefin) {
        this.datefin = datefin;
    }
    
    /**
     * Getter for property depart.
     * @return Value of property depart.
     */
    public java.lang.String getDepart() {
        return depart;
    }
    
    /**
     * Setter for property depart.
     * @param depart New value of property depart.
     */
    public void setDepart(java.lang.String depart) {
        this.depart = depart;
    }
    
    /**
     * Setter for property dienst.
     * @param dienst New value of property dienst.
     */
    public void setDienst(org.jdom.Element dienst) {
        this.dienst = dienst;
    }
    
    /**
     * Getter for property heurearriv.
     * @return Value of property heurearriv.
     */
    public java.lang.String getHeurearriv() {
        return heurearriv;
    }
    
    /**
     * Setter for property heurearriv.
     * @param heurearriv New value of property heurearriv.
     */
    public void setHeurearriv(java.lang.String heurearriv) {
        this.heurearriv = heurearriv;
    }
    
    /**
     * Getter for property heuredep.
     * @return Value of property heuredep.
     */
    public java.lang.String getHeuredep() {
        return heuredep;
    }
    
    /**
     * Setter for property heuredep.
     * @param heuredep New value of property heuredep.
     */
    public void setHeuredep(java.lang.String heuredep) {
        this.heuredep = heuredep;
    }
    
    /**
     * Getter for property ligne.
     * @return Value of property ligne.
     */
   
    
    /**
     * Getter for property statut.
     * @return Value of property statut.
     */
    public java.lang.String getStatut() {
        return statut;
    }
    
    /**
     * Setter for property statut.
     * @param statut New value of property statut.
     */
    public void setStatut(java.lang.String statut) {
        this.statut = statut;
    }
    
    /**
     * Getter for property type.
     * @return Value of property type.
     */
    public java.lang.String getType() {
        return type;
    }
    
    /**
     * Setter for property type.
     * @param type New value of property type.
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }
    
    /**
     * Getter for property typeTransport.
     * @return Value of property typeTransport.
     */
    public java.lang.String getTypeTransport() {
        return typeTransport;
    }
    
    /**
     * Setter for property typeTransport.
     * @param typeTransport New value of property typeTransport.
     */
    public void setTypeTransport(java.lang.String typeTransport) {
        this.typeTransport = typeTransport;
    }
    
    String type;
    String datedebut;;
    String datefin;
    String statut;
    String aller;
    String typeTransport;
    String depart;
    String arrive;
    String heuredep;
    String heurearriv;
    String classe;
    //new fields
    String codeaccomodation;
    String typeChambre;
    String nbrchambre;
    String board;
    
}
