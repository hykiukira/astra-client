/*
 * DossierXml.java 
 *
 * Created on 30 juin 2003, 10:49
 */

package srcastra.astra.sys.classetransfert.dossier;
import javax.xml.parsers.*;
import org.xml.sax.*;
import java.io.*;
import java.io.IOException; 
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import java.sql.*;
import srcastra.test.*;
//import com.sun.xml.tree.*;



/**
 *
 * @author  thomas
 */
public class DossierXml {
    Document  document;
    ServeurConnect connect;
    /** Creates a new instance of DossierXml */
    public static void main(String args[]){ 
        new DossierXml();   
    }
    public DossierXml() {
      /*  try{
        ServeurConnect connect=new ServeurConnect();
        connect.connectServeur();
        java.util.ArrayList array=null;//connect.serveur2.renvDossierRmiObject(connect.tmpgestion2.getUrcleunik()).chargeDossier(connect.tmpgestion2,348);
        Dossier_T doss2=(Dossier_T)array.get(1);
        prepareDocument(doss2);
        }catch(java.rmi.RemoteException rn){
         rn.printStackTrace();   
        }*/
    }
public void prepareDocument(Dossier_T dossier){
     DocumentBuilderFactory factory =
      DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder builder =
        factory.newDocumentBuilder();
      document = builder.newDocument();  
      // Create from whole cloth
       Element root = 
        (Element)
          document.createElement("Dossier"); 
        document.appendChild(root);
    /*  root.appendChild( 
        document.createTextNode("Some") );
      root.appendChild( 
        document.createTextNode(" ")    );
      root.appendChild( 
        document.createTextNode("text") );
      root.appendChild( 
        document.createTextNode("normal") );*/
      Element element2 = document.createElement("passager");
      root.insertBefore(element2,root.getFirstChild());
      element2.appendChild( 
        document.createTextNode("test") );
      Element element3= document.createElement("payement");
      root.insertBefore(element3,root.getFirstChild());
      element3.appendChild( 
        document.createTextNode("test") );
      Element element4= document.createElement("produit");
      root.insertBefore(element4,root.getFirstChild());
      element4.appendChild( 
        document.createTextNode("test") );
     /* Element element5= document.createElement("info");
      root.insertBefore(element5,root.getFirstChild());
      element5.appendChild( 
      document.createTextNode("test") );*/
      generedossierInfo(root,dossier);
      Source source = new DOMSource(document);
      System.out.println("document"+document.toString());
      File file = new File("c:/dossier2.xml");
      Result result = new StreamResult(file);   
            // Write the DOM document to the file
      Transformer xformer = TransformerFactory.newInstance().newTransformer();
      xformer.transform(source, result);
     
     // document.
    } catch (ParserConfigurationException pce) {
      // Parser with specified options can't be built
      pce.printStackTrace();
    }
     catch (TransformerConfigurationException e) {
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    
}
private void generedossierInfo(Element root,Dossier_T dossier){
    Element info=document.createElement("info");
    Element dr_bdc=document.createElement("dr_bdc");
    info.insertBefore(dr_bdc,info.getFirstChild());
    dr_bdc.appendChild(document.createTextNode(new Integer(dossier.getDr_bcd()).toString()));
    Element dr_ascomp=document.createElement("dr_ascomp");
    info.insertBefore(dr_ascomp,info.getFirstChild());
    dr_ascomp.appendChild(document.createTextNode(new Integer(dossier.getDr_ascomp()).toString()));
    Element dr_asbagage=document.createElement("dr_asbagage");
    info.insertBefore(dr_asbagage,info.getFirstChild());
    dr_asbagage.appendChild(document.createTextNode(new Integer(dossier.getDr_asbagage()).toString()));
    Element dr_asannul=document.createElement("dr_asannul");
    info.insertBefore(dr_asannul,info.getFirstChild());
    dr_asannul.appendChild(document.createTextNode(new Integer(dossier.getDr_asannul()).toString()));
    Element dr_paye=document.createElement("dr_paye");
    info.insertBefore(dr_paye,info.getFirstChild());
    dr_paye.appendChild(document.createTextNode(new Double(dossier.getDr_Paye()).toString()));
    Element dr_facture=document.createElement("dr_facture");
    info.insertBefore(dr_facture,info.getFirstChild());
    dr_facture.appendChild(document.createTextNode(new Integer(dossier.getDr_facture()).toString()));
    /*Element po=document.createElement("po");
    info.insertBefore(po,info.getFirstChild());
    po.appendChild(document.createTextNode(dossier.getPo()));
    Element destination=document.createElement("destination");
    info.insertBefore(destination,info.getFirstChild());
    destination.appendChild(document.createTextNode(dossier.getDestination()));
    Element hotel=document.createElement("hotel");
    info.insertBefore(hotel,info.getFirstChild());
    hotel.appendChild(document.createTextNode(dossier.getHotel()));
    Element fournisseur=document.createElement("fournisseur");
    info.insertBefore(fournisseur,info.getFirstChild());
    fournisseur.appendChild(document.createTextNode(dossier.getFournisseur()));
    Element client=document.createElement("client");
    info.insertBefore(client,info.getFirstChild());*/
    //client.appendChild(document.createTextNode(dossier.getClient()));
    Element dr_date_echeance=document.createElement("dr_date_echeance");
    info.insertBefore(dr_date_echeance,info.getFirstChild());
    String datee=null;
    if(dossier.getDr_date_echeance()!=null)
        datee=dossier.getDr_date_echeance().toString();
    else
        datee="????-??-??";
    dr_date_echeance.appendChild(document.createTextNode(datee));
    Element dr_type_payement=document.createElement("dr_type_payement");
    info.insertBefore(dr_type_payement,info.getFirstChild());
    dr_type_payement.appendChild(document.createTextNode(new Integer(dossier.getDr_type_payement()).toString()));
    Element dr_nbj_av_eche=document.createElement("dr_nbj_av_eche");
    info.insertBefore(dr_nbj_av_eche,info.getFirstChild());
    dr_nbj_av_eche.appendChild(document.createTextNode(new Integer(dossier.getDr_nbj_av_eche()).toString()));
    //Element dr_annull=document.createElement("dr_annull");
    //info.insertBefore(dr_annull,info.getFirstChild());
    //dr_annull.appendChild(document.createTextNode(new Integer(dossier.getDr_annull()).toString()));
    Element dr_nbrenuit=document.createElement("dr_nbrenuit");
    info.insertBefore(dr_nbrenuit,info.getFirstChild());
    dr_nbrenuit.appendChild(document.createTextNode(new Integer(dossier.getDr_nbrenuit()).toString()));
    Element dr_date_retour=document.createElement("dr_date_retour");
    info.insertBefore(dr_date_retour,info.getFirstChild());
    dr_date_retour.appendChild(document.createTextNode(dossier.getDr_date_retour().toString()));
    Element dr_date_depart=document.createElement("dr_date_depart");
    info.insertBefore(dr_date_depart,info.getFirstChild());
    dr_date_depart.appendChild(document.createTextNode(dossier.getDr_date_depart().toString()));
    Element cscleunik_fact=document.createElement("cscleunik_fact");
    info.insertBefore(cscleunik_fact,info.getFirstChild());
    cscleunik_fact.appendChild(document.createTextNode(new Long(dossier.getCscleunik_fact()).toString()));
    Element dr_totalprix=document.createElement("dr_totalprix");
    info.insertBefore(dr_totalprix,info.getFirstChild());
    dr_totalprix.appendChild(document.createTextNode(new Double(dossier.getDrtotalprix()).toString()));
    Element dr_tva=document.createElement("dr_tva");
    info.insertBefore(dr_tva,info.getFirstChild());
    dr_tva.appendChild(document.createTextNode(new Double(dossier.getDrtva()).toString()));
    Element dr_taxe=document.createElement("dr_taxe");
    info.insertBefore(dr_taxe,info.getFirstChild());
    dr_taxe.appendChild(document.createTextNode(new Double(dossier.getDrtaxe()).toString()));
    Element dr_prix=document.createElement("dr_prix");
    info.insertBefore(dr_prix,info.getFirstChild());
    dr_prix.appendChild(document.createTextNode(new Double(dossier.getDrprix()).toString()));
    Element urcleunik=document.createElement("urcleunik");
    info.insertBefore(urcleunik,info.getFirstChild());
    urcleunik.appendChild(document.createTextNode(new Integer(dossier.getUrcleunikcrea()).toString()));
    Element cscleunik=document.createElement("cscleunik");
    info.insertBefore(cscleunik,info.getFirstChild());
    cscleunik.appendChild(document.createTextNode(new Long(dossier.getCscleunik()).toString()));
    Element dr_datetimecrea=document.createElement("dr_datetimecrea");
    info.insertBefore(dr_datetimecrea,info.getFirstChild());
    dr_datetimecrea.appendChild(document.createTextNode(dossier.getDr_datetimecrea().toString()));
    Element dr_status=document.createElement("dr_status");
    info.insertBefore(dr_status,info.getFirstChild());
    dr_status.appendChild(document.createTextNode(new Integer(dossier.getDrstatus()).toString()));
  //  Element dr_reference=document.createElement("dr_reference");
    //info.insertBefore(dr_reference,info.getFirstChild());
    //dr_reference.appendChild(document.createTextNode(dossier.getDrreference()));
    Element dr_typbooking=document.createElement("dr_typbooking");
    info.insertBefore(dr_typbooking,info.getFirstChild());
    dr_typbooking.appendChild(document.createTextNode(new Integer(dossier.getDr_type_booking()).toString()));
    Element dr_numdos=document.createElement("dr_numdos");
    info.insertBefore(dr_numdos,info.getFirstChild());
    dr_numdos.appendChild(document.createTextNode(dossier.getDrnumdos()));
    Element dr_cleunik=document.createElement("dr_cleunik");
    info.insertBefore(dr_cleunik,info.getFirstChild());
    dr_cleunik.appendChild(document.createTextNode(new Long(dossier.getDrcleunik()).toString()));
    root.insertBefore(info,root.getFirstChild());
    info.appendChild( 
      document.createTextNode("test") );
}
}
