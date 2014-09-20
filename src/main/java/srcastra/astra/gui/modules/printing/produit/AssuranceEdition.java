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

import srcastra.astra.sys.classetransfert.dossier.assurance.*;



/**

 *

 * @author  Thomas

 */

public class AssuranceEdition extends AbstractProduitEdition{

    

    /** Creates a new instance of BrochureEdition */

    RReportJ2 report;

    AbstractBuffer buffer;

    DossierRmiInterface dossin;

    Assurance_T assurance;

      java.awt.Frame main;

    double position=0;

     public AssuranceEdition(Object obj, Object obj2, Object obj3, RReportJ2 report, AbstractBuffer buffer, DossierRmiInterface dossin,  java.awt.Frame main,String text)  {       

        super(obj,obj2,obj3,AbstractProduitEdition.ASSURANCE,report,text); 

        this.report=report; 

        this.buffer=buffer;

        this.dossin=dossin;

        this.main=main;

        position=mainrectangle.y;     

        y1=1d;      

    }

    public void addProduct(Object obj,Object obj2,Object obj3,Object obj4,produit_T prod){

        assurance=(Assurance_T)prod;

        titre.setruntimeValue(titre.getruntimeValue()+assurance.getAce_numPolice());

        fournisseur=new BloqueFournisseur(obj,obj2,obj3,obj4,0,0,report,this,assurance,main);

        fournisseur.ajustBloque();

        fournisseur.calculWidth();

        fournisseur.setPosition(0,y1,0,0.5);    

        addBloque(fournisseur);

       // bas=new PRAS_Bloque(obj,obj2,obj3,obj4,0,0,report,this,assurance,main);

       // bas.ajustBloque();

        //bas.calculWidth();

       // bas.setPosition(0,fournisseur.y2-0.2d,0,0.5);   

        //addBloque(bas);

        if(!assurance.getAce_memo().equals("") && assurance.getAce_memo()!=null){
            
            
           
            
            memo=new MemoBloque(obj,obj2,obj3,obj4,0,0,report,this,null,assurance.getAce_memo(),main);

            memo.ajustBloque();

            memo.calculWidth();

            memo.setPosition(0,fournisseur.y2,1d,0.4d);    

            addBloque(memo);

            double hei=((MemoBloque)memo).calculHeigth();

            mainrectangle.height=fournisseur.y2+hei;

            height=fournisseur.y2+hei;

        }

        else{

               mainrectangle.height=fournisseur.y2-0.5d;

               this.height=mainrectangle.height;

               //height=fournisseur.y2;       

        }

       /* 

        gauche=new PRBRO_BloqueGauche(obj,obj2,obj3,obj4,0,0,report,this,brochure);

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

    

AbstractBloque fournisseur;    

AbstractBloque bas;

AbstractBloque memo;

}

