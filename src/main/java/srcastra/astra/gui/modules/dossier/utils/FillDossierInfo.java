/*
 * FillDossierInfo.java
 *
 * Created on 19 mai 2003, 11:09
 */

package srcastra.astra.gui.modules.dossier.utils;
import java.util.Hashtable;
import srcastra.astra.gui.components.actions.ToolBarInteraction;
import srcastra.astra.gui.components.actions.actionToolBar.*;
import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import srcastra.astra.gui.modules.dossier.DossierProduitPane;
import srcastra.astra.sys.classetransfert.dossier.InterfaceProduit;
import java.awt.event.*;
import srcastra.astra.gui.event.*;
import srcastra.astra.sys.classetransfert.dossier.produit_T;
import srcastra.astra.sys.classetransfert.dossier.*;
import srcastra.astra.sys.classetransfert.dossier.avion.*;
/**
 *
 * @author  Thomas
 */
public class FillDossierInfo {
    
    /** Creates a new instance of FillDossierInfo */
    public FillDossierInfo() {
    }
     public static void validateProductAvion(Dossier_T dossier,Avion_ticket_T ticket){         
             if(dossier.getPoBilletIntitule().equals(""))
                    dossier.setPoBilletIntitule(ticket.getAt_pnr());
             if(dossier.getFournIntitule().equals(""))
                    dossier.setFournIntitule(ticket.getFrnom());
     }
    public static void validateProdutct(DossierMainScreenModule m_parent,int typProd){
        if(typProd==produit_T.AV){          
             if(m_parent.getDossier().getPoBilletIntitule().equals(""))
                    m_parent.getDossier().setPoBilletIntitule(m_parent.getTicket().getAt_pnr());
             if(m_parent.getDossier().getFournIntitule().equals(""))
                    m_parent.getDossier().setFournIntitule(m_parent.getTicket().getFrnom());
        }
        else if(typProd==produit_T.HO){
             if(m_parent.getDossier().getPoBilletIntitule().equals(""))
                  m_parent.getDossier().setPoBilletIntitule(m_parent.getHotel().getHl_pnr());
             if(m_parent.getDossier().getFournIntitule().equals(""))
                  m_parent.getDossier().setFournIntitule(m_parent.getHotel().getFrnom());
             if(m_parent.getDossier().getDestIntitule().equals(""))
                  m_parent.getDossier().setDestIntitule(""+m_parent.getHotel().getHl_pays_libele());
             if(m_parent.getDossier().getHotelIntitule().equals(""))
                  m_parent.getDossier().setHotelIntitule(m_parent.getHotel().getHl_hotel());
        }
        else if(typProd==produit_T.BRO){
             if(m_parent.getDossier().getPoBilletIntitule()!=null && m_parent.getDossier().getFournIntitule()!=null && m_parent.getDossier().getDestIntitule()!=null){
                   if(m_parent.getDossier().getPoBilletIntitule().equals(""))
                        m_parent.getDossier().setPoBilletIntitule(m_parent.getBrochure().getBro_po());
                   if(m_parent.getDossier().getFournIntitule().equals(""))
                        m_parent.getDossier().setFournIntitule(m_parent.getBrochure().getFrnom());
                   if(m_parent.getDossier().getDestIntitule().equals(""))
                        m_parent.getDossier().setDestIntitule(m_parent.getBrochure().getBro_destination());
                    }    
        }
          else if(typProd==produit_T.AS){
              if(m_parent.getDossier().getPoBilletIntitule()!=null){
                    if(m_parent.getDossier().getPoBilletIntitule().equals(""))
                        m_parent.getDossier().setPoBilletIntitule(m_parent.getAssurance().getAce_numPolice());
              }else{
                  if(m_parent.getAssurance()!=null)
                    m_parent.getDossier().setPoBilletIntitule(m_parent.getAssurance().getAce_numPolice()); 
              }
                  
              }
          else if(typProd==produit_T.BA){
                    if(m_parent.getDossier().getPoBilletIntitule().equals(""))
                        m_parent.getDossier().setPoBilletIntitule(m_parent.getBateau().getBat_pnr());
        }
        else if(typProd==produit_T.VO){
            if(m_parent.getDossier().getPoBilletIntitule().equals(""))
                        m_parent.getDossier().setPoBilletIntitule(m_parent.getVoitureLocation().getVl_pnr());
        }
         else if(typProd==produit_T.CAR){
            if(m_parent.getDossier().getPoBilletIntitule().equals(""))
                        m_parent.getDossier().setPoBilletIntitule(m_parent.getCar().getVl_pnr());
        }
        else if(typProd==produit_T.TR){
            if(m_parent.getDossier().getPoBilletIntitule().equals(""))
                        m_parent.getDossier().setPoBilletIntitule(m_parent.getTrain().getTrn_nBillet());
        }
        else if(typProd==produit_T.TAX){
                ;
        }
    }   
        
    
    
}
