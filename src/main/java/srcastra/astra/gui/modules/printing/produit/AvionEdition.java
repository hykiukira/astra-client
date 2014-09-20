/*
 * BrochureEdition.java
 *
 * Created on 18 mars 2003, 16:21
 */

package srcastra.astra.gui.modules.printing.produit;
import srcastra.astra.gui.modules.printing.header.*;
import srcastra.astra.gui.modules.printing.*;
import srcastra.astra.gui.modules.printing.header.*;
import com.java4less.rreport.*;
import srcastra.astra.sys.Logger;
import java.util.*;
import srcastra.astra.sys.classetransfert.dossier.Passager_T;
import srcastra.astra.gui.modules.printing.classelangueuser.*;
import srcastra.astra.sys.classetransfert.utils.CalculDate;
import srcastra.astra.sys.classetransfert.dossier.brochure.*;
import srcastra.astra.sys.classetransfert.dossier.produit_T;
import srcastra.astra.sys.classetransfert.dossier.avion.Avion_ticket_T;
import srcastra.astra.gui.modules.aidedesicion.*;
import srcastra.astra.sys.classetransfert.dossier.avion.*;
import srcastra.astra.sys.rmi.DossierRmiInterface;
import srcastra.astra.gui.modules.printing.produit.produitInfo.*;
import srcastra.astra.sys.rmi.*;
import srcastra.astra.gui.sys.*;

/**
 *
 * @author  Thomas
 */
public class AvionEdition extends AbstractProduitEdition{
    
    /** Creates a new instance of BrochureEdition */
    RReportJ2 report;
    AbstractBuffer buffer;
    DossierRmiInterface dossin;
     double position=0;
       java.awt.Frame main;
    public AvionEdition(Object obj, Object obj2, Object obj3, RReportJ2 report,AbstractBuffer buffer,DossierRmiInterface dossin,  java.awt.Frame main,String text)  {       
        super(obj,obj2,obj3,AbstractProduitEdition.AVION,report,text); 
        this.report=report; 
        this.buffer=buffer;
        this.dossin=dossin;
        this.main=main;
        position=mainrectangle.y;
        y1=0.5d;      
    }
    public void addProduct(Object obj,Object obj2,Object obj3,Object obj4,produit_T prod){
     
        Avion_ticket_T ticket=(Avion_ticket_T)prod;
          double m=0;
        bas=new PRAV_Bloque(obj,obj2,obj3,obj4,0,0,report,this,ticket,main);
        bas.ajustBloque();
        bas.calculWidth();
        bas.setPosition(0,position,0,0.4d);     
        addBloque(bas);
        double y=bas.y2+0.4d;
        double k=0d;
        ArrayList conj=ticket.getConjonctionList();
        this.y2=bas.y2;
        if(conj!=null){
        for(int i=0;i<conj.size();i++){         
            Conjonction_T conjonction=(Conjonction_T)conj.get(i);
            ArrayList seg=conjonction.getSegmentList();
            if(seg!=null){
                for(int j=0;j<seg.size();j++){
                /*    if(log==null){
                        log=new  MyRvfield(1,1,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_AV_Seg"),1," ",fontBold10);      
                        log.x=0;
                        log.y=bas.y2;
                        add(log);
                        k=log.y;
                        bas.y2=bas.y2+0.5d;
        }*/
                    if(segmentgauche==null){
                        segmentgauche=new PRAV_segments_Bloque(obj,obj2,obj3,obj4,0,0,report,this,null,main);        
                        segmentgauche.ajustBloque();
                        //segment
                        segmentgauche.calculWidth();
                        segmentgauche.initFieldPosition(0,bas.y2);                  
                        addBloque(segmentgauche);
                        m=bas.y2+0.5d;
                    }
                   Avion_segment_T segment=(Avion_segment_T)seg.get(j);
                   SegmentInfo seginfo=new SegmentInfo(currentUser.getUrcleunik(),1,segment.getCoe_cleunik(),segment.getAs_routing_de(),segment.getAs_routing_a(),new Double(segment.getAs_cleunik()).intValue());
                   try{
                       seginfo=(SegmentInfo)dossin.getProduitInfo(currentUser.getUrcleunik(),seginfo);
                       segment.setProduitInfo(seginfo);
                   }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn){
                        ErreurScreenLibrary.displayErreur(report, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, sn,currentUser);
                    }
                    catch(java.rmi.RemoteException rn){
                        ErreurScreenLibrary.displayErreur(report, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn,currentUser);
                    }                          
                        segmentbas=new PRAV_segments_Bloque2(obj,obj2,obj3,obj4,0,0,report,this,segment,main);     
                        segmentbas.ajustBloque();
                        segmentbas.calculWidth();
                        segmentbas.initFieldPosition(0,m);                  
                        addBloque(segmentbas);
                        m=m+0.4d;
                   /*
                   segmentdroit=new PRAV_segments_BLD(obj,obj2,obj3,obj4,0,0,report,this,segment,main); 
                   segmentdroit.ajustBloque();
                   segmentdroit.calculWidth();
                   double x=report.mPageWidthCM-(report.marginLeft+report.marginRight+segmentdroit.width);
                   segmentdroit.setPosition(x,bas.y2,0,0.4d);     
                   addBloque(segmentdroit);
                   double bgbdDist=segmentdroit.x1-(segmentgauche.x1+segmentgauche.width);
                   double demi=bgbdDist/2;
                   segmentmilieu=new PRAV_segments_BLM(obj,obj2,obj3,obj4,0,0,report,this,segment,main); 
                   segmentmilieu.ajustBloque();
                   segmentmilieu.calculWidth();
                   demi=demi-(segmentmilieu.width/2);
                   segmentmilieu.setPosition(segmentgauche.width+demi,bas.y2,0,0.4d);     
                   addBloque(segmentmilieu);      */            
                   this.y2=segmentbas.y2;
                  // bas.y2=y2;
                   mainrectangle.height=segmentbas.y2;
                   this.height=this.y2;//-this.y;
                                 
                }           
            }
        }
         if(!ticket.getAt_memo().equals("") && ticket.getAt_memo()!=null){
                memo=new MemoBloque(obj,obj2,obj3,obj4,0,0,report,this,null,ticket.getAt_memo(),main);
                //memo.ajustBloque();
                //memo.calculWidth();
                memo.setPosition(0,this.y2+0.5d,1d,0.4d);    
                addBloque(memo);    
                double hei=((MemoBloque)memo).calculHeigth();
               // Logger.getDefaultLogger().log(Logger.LOG_INFOS,"y2 "+this.y2+" hauteur du memo"+memo.heigth);
                mainrectangle.height=this.y2+hei+0.5d;
                this.height=this.y2+hei+0.5d;
            }
        }
        else{
            if(!ticket.getAt_memo().equals("")  && ticket.getAt_memo()!=null){
                memo=new MemoBloque(obj,obj2,obj3,obj4,0,0,report,this,null,ticket.getAt_memo(),main);
               // memo.ajustBloque();
                //memo.calculWidth();
                memo.setPosition(0,bas.y2,1d,0.4d);    
                addBloque(memo);   
                double hei=((MemoBloque)memo).calculHeigth();
              //  Logger.getDefaultLogger().log(Logger.LOG_INFOS,"y2 "+this.y2+" hauteur du memo"+memo.heigth);
                mainrectangle.height=this.y2+hei;
                this.height=this.y2+hei;
            }
        }
        segmentgauche=null;
        y1=y1+3.5d;   
        mainrectangle.height=this.height;
        position=this.y2+0.5d;
        log=null;
    }
    
    public void addProduct(Object obj, Object obj2, Object obj3, Object obj4, produit_T prod, String texte) {
    }
    
    public void addProduct(Object obj, Object obj2, Object obj3, Object obj4, produit_T prod, String texte, Object obj5) {
    }
    

AbstractBloque segmentgauche;  
AbstractBloque memo;
AbstractBloque segmentbas;  
AbstractBloque segmentdroit;  
AbstractBloque bas;
AbstractBloque logement;  
MyRvfield log;
}
