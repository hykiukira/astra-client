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

import srcastra.astra.gui.modules.aidedesicion.*;

/**

 *

 * @author  Thomas

 */

public class BrochureEdition extends AbstractProduitEdition{

    

    /** Creates a new instance of BrochureEdition */

    RReportJ2 report;

    AbstractBuffer buffer;

      java.awt.Frame main;

    public BrochureEdition (Object obj,Object obj2,Object obj3,RReportJ2 report,AbstractBuffer buffer,  java.awt.Frame main,String text)  {       

        super(obj,obj2,obj3,0,report,text); 

        this.report=report;

        this.main=main;

        y1=1d;//0.5d;

        this.buffer=buffer;

       

    }

    public void addProduct(Object obj,Object obj2,Object obj3,Object obj4,produit_T prod){
        
        double hei=0;
        
        Brochure_T brochure=(Brochure_T)prod;

        titre.setruntimeValue(titre.getruntimeValue()+brochure.getPnr());

        fournisseur=new BloqueFournisseur(obj,obj2,obj3,obj4,0,0,report,this,(produit_T)brochure,main);

        fournisseur.ajustBloque();

        fournisseur.calculWidth();

        fournisseur.setPosition(0,y1,0,0.4d);    

        addBloque(fournisseur);

        gauche=new PRBRO_BloqueGauche(obj,obj2,obj3,obj4,0,0,report,this,brochure,main);

        gauche.ajustBloque();

        gauche.calculWidth();

        gauche.setPosition(0,fournisseur.y2,0,0.4d); 

        addBloque(gauche);

        droite=new PRBRO_BloqueDroite(obj,obj2,obj3,obj4,0,0,report,this,brochure,main);

        droite.ajustBloque();

        droite.calculWidth();

        double x=report.mPageWidthCM-(report.marginLeft+report.marginRight+droite.width+0.4d);

        droite.setPosition(x,fournisseur.y2,0,0.4d); 

        addBloque(droite);

        double bgbdDist=droite.x1-(gauche.x1+gauche.width);

        double demi=bgbdDist/2;

        milieu=new PRBRO_BloqueMilieu(obj,obj2,obj3,obj4,0,0,report,this,brochure,main);

        milieu.ajustBloque();

        milieu.calculWidth();

        demi=demi-(milieu.width/2);

        milieu.setPosition(gauche.width+demi+2,fournisseur.y2,0,0.4d);  

        addBloque(milieu);

        bas=new PRBRO_bloquebas(obj,obj2,obj3,obj4,0,0,report,this,brochure,main);

        bas.ajustBloque();

        bas.calculWidth();

        bas.setPosition(0,gauche.y2,0.8d,0.4d);     

        addBloque(bas);

       

       // MyRvfield log=new  MyRvfield(1,1,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_log2"),1," ",fontBold10);      

       // log.x=0;

       // log.y=bas.y2;

       // add(log); s

        double y=bas.y2;

        if(brochure.getDescriptionLogement()!=null){

      //  MyRvfield log=new  MyRvfield(1,1,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_log2"),1," ",fontBold10);      

        //    log.x=0;

          //  log.y=bas.y2;

           // add(log);

        for(int i=0;i<brochure.getDescriptionLogement().size();i++){

            DescriptionLogement_T desc=(DescriptionLogement_T)brochure.getDescriptionLogement().get(i);

        logement=new PRBloquelogement(obj,obj2,obj3,obj4,0,0,report,this,desc,buffer,main);

        logement.ajustBloque();

        logement.calculWidth();

        logement.setPosition(0,y,0,0.4d);

        y=y+0.4d;

        addBloque(logement);

        }

        }

        y1=y1+3.5d;

        if(!brochure.getBro_memo().equals("") && brochure.getBro_memo()!=null){
          
          String bro=brochure.getBro_memo();
          String out=" "+"\n";
          
          StringTokenizer token=new StringTokenizer(bro,"\n");

          ArrayList array=new ArrayList();

          int countLine=0;

          while(token.hasMoreElements()){

              out=out+token.nextElement()+"\n";              

          }
            
            System.out.println(out);
            
            
            memo=new MemoBloque(obj,obj2,obj3,obj4,0,0,report,this,null,out,main);

          //  memo.ajustBloque();

           // memo.calculWidth();

            memo.setPosition(0,y,1d,0.4d);    

            addBloque(memo);

            hei=((MemoBloque)memo).calculHeigth();
           // hei=
             
             
            //if ((int)hei ==0)
            //hei=y;
            
          //  if ((int)hei !=0)
          //  mainrectangle.height=hei;//hei;//+y;//+y;//hei+y; //y+hei;
          //  else
            mainrectangle.height=hei+(y);
            
            
        }

        else{

       if(logement!=null)

            mainrectangle.height=logement.y2-0.3d;

        else

            mainrectangle.height=gauche.y2;

        }

        //
        

      
        // y=this.height+1.6;
      
        //this.y2=hei;
       
         
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

