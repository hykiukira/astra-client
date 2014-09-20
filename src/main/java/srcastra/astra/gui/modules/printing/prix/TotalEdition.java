/*
 * BrochureEdition.java
 *
 * Created on 18 mars 2003, 16:21
 */

package srcastra.astra.gui.modules.printing.prix;
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
import srcastra.astra.sys.classetransfert.dossier.assurance.*;
import srcastra.astra.gui.modules.printing.produit.*;

/**
 *
 * @author  Thomas
 */
public class TotalEdition extends AbstractProduitEdition{
    
    /** Creates a new instance of BrochureEdition */
    RReportJ2 report;
    AbstractBuffer buffer;
    DossierRmiInterface dossin;
    Assurance_T assurance; 
    double position=0;
      java.awt.Frame main;
      public TotalEdition(Object obj, Object obj2, Object obj3,Object obj4, RReportJ2 report, AbstractBuffer buffer, DossierRmiInterface dossin, java.awt.Frame main,int facture)  {       
        super(obj,obj2,obj3,AbstractProduitEdition.TOTAL,report,""); 
        this.report=report; 
        this.buffer=buffer;
        this.dossin=dossin;
//        position=mainrectangle.y;     
        this.main=main;
        y1=0.5d;  
        total=new TotalBloque(obj,obj2,obj3,obj4,0,0,report,this,null,main,facture);   
        total.ajustBloque();    
        //total.initFieldPosition(0,y1);   
        total.calculWidth();
        double x=report.mPageWidthCM -(report.marginRight+report.marginLeft+total.width);
        //total.setPosition(x-1d,0,0,0.4d);
        total.initLabelPosition(x-1d,0);
        addBloque(total);
        y1=y1+0.5d;
       // this.height=y1;
    }
    public void addProduct(Object obj,Object obj2,Object obj3,Object obj4,produit_T prod){
       
       /* gauche=new PRBRO_BloqueGauche(obj,obj2,obj3,obj4,0,0,report,this,brochure);
        gauche.ajustBloque();
        gauche.calculWidth();
        gauche.setPosition(0,fournisseur.y2+0.4d,0,0.4d); 
        addBloque(gauche);
        droite=new PRBRO_BloqueDroite(obj,obj2,obj3,obj4,0,0,report,this,brochure);
        droite.ajustBloque();
        droite.calculWidth();
        double x=report.mPageWidthCM-(report.marginLeft+report.marginRight+droite.width)-0.2d;
        droite.setPosition(x,fournisseur.y2+0.4d,0,0.4d); 
        addBloque(droite);
        double bgbdDist=droite.x1-(gauche.x1+gauche.width);
        double demi=bgbdDist/2;
        milieu=new PRBRO_BloqueMilieu(obj,obj2,obj3,obj4,0,0,report,this,brochure);
        milieu.ajustBloque();
        milieu.calculWidth();
        demi=demi-(milieu.width/2);
        milieu.setPosition(gauche.width+demi,fournisseur.y2+0.4d,0,0.4d);  
        addBloque(milieu);
        bas=new PRBRO_bloquebas(obj,obj2,obj3,obj4,0,0,report,this,brochure);
        bas.ajustBloque();
        bas.calculWidth();
        bas.setPosition(0,gauche.y2,0.8d,0.4d);     
        addBloque(bas);
        MyRvfield log=new  MyRvfield(1,1,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_log2"),1," ",fontBold10);      
        log.x=0;
        log.y=bas.y2;
        add(log);
        double y=bas.y2+0.4d;
        if(brochure.getDescriptionLogement()!=null){
        for(int i=0;i<brochure.getDescriptionLogement().size();i++){
            DescriptionLogement_T desc=(DescriptionLogement_T)brochure.getDescriptionLogement().get(i);
        logement=new PRBloquelogement(obj,obj2,obj3,obj4,0,0,report,this,desc,buffer);
        logement.ajustBloque();
        logement.calculWidth();
        logement.setPosition(0,y,0,0.4d);
        y=y+0.4d;
        addBloque(logement);
        }
        }
        y1=y1+3.5d;   
         if(logement!=null)
            mainrectangle.height=logement.y2-0.3d;
        else
            mainrectangle.height=gauche.y2;*/
    }
    
    public void addProduct(Object obj, Object obj2, Object obj3, Object obj4, produit_T prod, String texte) {
    }
    
    public void addProduct(Object obj, Object obj2, Object obj3, Object obj4, produit_T prod, String texte, Object obj5) {
    }
    
AbstractBloque total;    


}
