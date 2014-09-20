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

import srcastra.astra.sys.classetransfert.dossier.hotel.*;

import srcastra.astra.sys.classetransfert.dossier.produit_T;

import srcastra.astra.sys.classetransfert.dossier.brochure.*;

import srcastra.astra.gui.modules.aidedesicion.*;

/**

 *

 * @author  Thomas

 */

public class HotelEdition extends AbstractProduitEdition{

    

    /** Creates a new instance of BrochureEdition */

    RReportJ2 report;

    AbstractBuffer buffer;

      java.awt.Frame main;

    public HotelEdition(Object obj, Object obj2, Object obj3, RReportJ2 report, AbstractBuffer buffer,  java.awt.Frame main,String text)  {       

        super(obj,obj2,obj3,AbstractProduitEdition.HOTEL,report,text); 

        this.report=report;

        this.buffer=buffer;

        this.main=main;

        y1=0.5d;

       

    }

    public void addProduct(Object obj,Object obj2,Object obj3,Object obj4,produit_T prod){

        Hotel_T hotel=(Hotel_T)prod;

      /*  fournisseur=new BloqueFournisseur(obj,obj2,obj3,obj4,0,0,report,this,brochure);

        fournisseur.ajustBloque();

        fournisseur.calculWidth();

        fournisseur.setPosition(0,y1,0,0);    

        addBloque(fournisseur);*/

        gauche=new PRHO_BloqueGauche(obj,obj2,obj3,obj4,0,0,report,this,hotel,main);

        gauche.ajustBloque();

        gauche.calculWidth();

        gauche.setPosition(0,y1,0,0.4d); 

        addBloque(gauche);

        droite=new PRHO_BloqueDroit(obj,obj2,obj3,obj4,0,0,report,this,hotel,main);

        droite.ajustBloque();

        droite.calculWidth();

        double x=report.mPageWidthCM-(report.marginLeft+report.marginRight+droite.width+0.4d);

        droite.setPosition(x,y1,0,0.4d); 

        addBloque(droite);

        double bgbdDist=droite.x1-(gauche.x1+gauche.width);

        double demi=bgbdDist/2;

        milieu=new PRHO_BloqueMilieu(obj,obj2,obj3,obj4,0,0,report,this,hotel,main);

        milieu.ajustBloque();

        milieu.calculWidth();

        demi=demi-(milieu.width/2);

        milieu.setPosition(gauche.width+demi-2,y1,0,0.4d);  

        addBloque(milieu);

       /* bas=new PRBRO_bloquebas(obj,obj2,obj3,obj4,0,0,report,this,brochure);

        bas.ajustBloque();

        bas.calculWidth();

        bas.setPosition(0,gauche.y2,0,0.4d);     

        addBloque(bas);*/     

        double y=gauche.y2;

        if(hotel.getDescriptionLogement()!=null){         

            MyRvfield log=new  MyRvfield(1,1,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_log2"),1," ",fontBold10);      

            log.x=0;

            log.y=gauche.y2;

            add(log);

            boolean sw=false;

            for(int i=0;i<hotel.getDescriptionLogement().size();i++){

                y=y+0.4d;

                DescriptionLogement_T desc=(DescriptionLogement_T)hotel.getDescriptionLogement().get(i);

                logement=new PRBloquelogement(obj,obj2,obj3,obj4,0,0,report,this,desc,buffer,main);

                logement.ajustBloque();

                logement.calculWidth();

                logement.setPosition(0,y,0,0.4d);               

                addBloque(logement);

                sw=true;

            }

            if(sw)

                y=y+0.4d;

        }

       /* y1=y1+3.5d;   

        if(logement!=null)

            mainrectangle.height=logement.y2-0.3d;

        else

            mainrectangle.height=gauche.y2;*/

        y1=y1+3.5d;

        if(!hotel.getHl_memo().equals("")  && hotel.getHl_memo()!=null){

            memo=new MemoBloque(obj,obj2,obj3,obj4,0,0,report,this,null,hotel.getHl_memo(),main);

            //memo.ajustBloque();

            //memo.calculWidth();

            memo.setPosition(0,y,1d,0.4d);    

            addBloque(memo);

            double hei=((MemoBloque)memo).calculHeigth();

            mainrectangle.height=y+hei;

        } 

        else{

            if(logement!=null)

                mainrectangle.height=logement.y2-0.3d;

            else

                mainrectangle.height=gauche.y2;

            

            

        }

       

        this.height=mainrectangle.height; 

    }

    

    public void addProduct(Object obj, Object obj2, Object obj3, Object obj4, produit_T prod, String texte) {

    }

    

    public void addProduct(Object obj, Object obj2, Object obj3, Object obj4, produit_T prod, String texte, Object obj5) {

    }

    

AbstractBloque fournisseur;    

AbstractBloque droite;  

AbstractBloque milieu;  

AbstractBloque gauche;  

AbstractBloque bas;

AbstractBloque logement;  

AbstractBloque memo;    

}

