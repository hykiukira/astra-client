/*
 * ReservationBuilder.java
 *
 * Created on 25 mai 2004, 09:29
 */

package srcastra.astra.sys.btn;
import srcastra.astra.sys.classetransfert.dossier.brochure.Brochure_T;
import java.util.*;
import org.jdom.*;
/**
 *
 * @author  Administrateur 
 */
public class ReservationBrochureBuilder {
    
    /** Creates a new instance of ReservationBuilder */
    Hashtable hash;
    public String retval;
    Element root;
    public ReservationBrochureBuilder(Element root) {
        hash=new Hashtable();
        hash.put("T", new TransfertDienst());
        this.root=root;       
        dienst(root);
    }
    private void dienst(Element element){       
        System.out.println(element.getName()+"  "+element.getAttributeValue("name"));
        if(element.getName().equals("entity") && element.getAttributeValue("name").equals("T_Dienst")){
            String type=element.getChild("soortdienstcode").getChild("code_btn").getText();
            System.out.println("type "+type);
            Dienst dienst=(Dienst)hash.get(type);
            if(dienst!=null){
                retval=dienst.getDienst(element);
                System.out.println("retval"+retval);
            }
        }
        List children=element.getChildren();
        Iterator ite=children.iterator();
        while(ite.hasNext()){
            Element child=(Element)ite.next();
            dienst(child);
        }
    }
    private void typeDienst(){
        
    }
  //  public abstract Brochure_T build();
    protected long bro_cleUnik;
    protected String bro_contact;  
    protected String bro_po;
    protected int tecleunik_trans;
    protected String bro_transport;
    protected int bro_accomodation;
    protected String bro_accomodation_libele;
    protected int de_cleunik;
    protected String bro_localite;
    protected String bro_destination;
    protected String free_destination;
    protected String bro_logement;
    protected srcastra.astra.sys.classetransfert.utils.Date bro_embarq;
    protected int bro_lieuEmbarq_cleUnik;
    protected String bro_lieuEmbarq_libele;
    protected srcastra.astra.sys.classetransfert.utils.Date bro_debarq;
    protected int bro_lieuDebarq_cleUnik;
    protected String bro_lieuDebarq_libele;
    protected String bro_num;
    protected String bro_ref_catalog;
    protected double bro_valeur;
    protected int bro_montant;
    protected String bro_memo;
    protected int bro_ass_anul;
    protected int bro_ass_bag;
    protected int bro_ass_part;    
    protected ArrayList descriptionLogement;
    protected String bro_memForCatalogue;
    protected String free_embarq;
    protected String free_debarq;
    protected int typeProduit;
    protected int pax;
    protected int qua;
}
