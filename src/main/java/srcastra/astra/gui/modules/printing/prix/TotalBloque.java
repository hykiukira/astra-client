/*

 * RHBloqueGauche.java

 *

 * Created on 14 mars 2003, 11:58

 */



package srcastra.astra.gui.modules.printing.prix;

import srcastra.astra.gui.modules.printing.*;

import srcastra.astra.gui.modules.printing.header.*;

import com.java4less.rreport.*;

import srcastra.astra.sys.Logger;

import java.util.*;

import srcastra.astra.sys.classetransfert.dossier.Passager_T;

import srcastra.astra.gui.modules.printing.classelangueuser.*;

import srcastra.astra.sys.classetransfert.utils.CalculDate;

import srcastra.astra.sys.classetransfert.dossier.brochure.Brochure_T;

import srcastra.astra.sys.classetransfert.dossier.*;

import srcastra.astra.sys.classetransfert.clients.Clients_T;


import srcastra.astra.sys.compta.*;



/**

 *

 * @author  Thomas

 */

public class TotalBloque extends AbstractBloque{

    

    /** Creates a new instance of RHBloqueGauche */

     produit_T prod;

     RReport report;

     ArrayList elementArray;

     int facture;

      public TotalBloque(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report, RArea parent, produit_T prod,java.awt.Frame frame,int facture) {

        super(obj,obj2,obj3,obj4,frame,report);   

        this.prod=prod;

        this.report=report;

        this.facture=facture;

        initField(1,1,1,"",fontPlain10); 

        initLabel(1,1,1,"",fontBold10);   

        genereArray();

    }

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){

        System.out.println(dossier.getDrtotalprix());
        
        ftotal=new MyRvfield(fcase,color,""+MathRound.roundThisToDouble(dossier.getDrtotalprix())+"€",igenerique,sgenerique,font);

        fpaye=new MyRvfield(fcase,color,""+MathRound.roundThisToDouble(dossier.getDr_Paye())+"€",igenerique,sgenerique,font);

        double solde2=dossier.getDrtotalprix()-dossier.getDr_Paye();

        double acompte=0;
        
        if(dossier.getDr_nbj_av_eche()!=0)
        {
            if(dossier.getDr_Paye()==0)
            {
            acompte=dossier.getDrtaxe();
            acompte=MathRound.roundThisToDouble(acompte);

            }

            solde2=MathRound.roundThisToDouble(solde2);

            fsolde=new MyRvfield(fcase,color,""+solde2+"€",igenerique,sgenerique,font); 

            String date;

            if(dossier.getDr_date_echeance()!=null)

                date=dossier.getDr_date_echeance().toString2();

            else

                date="??/??/????"; 

            datePayement=new MyRvfield(fcase,color,date,igenerique,sgenerique,font); 
            if(acompte!=-1)
            acompteDu=new MyRvfield(fcase,color,""+acompte+"€",igenerique,sgenerique,font); 
        }
        else
        {
            solde2=MathRound.roundThisToDouble(solde2);
            fsolde=new MyRvfield(fcase,color,""+solde2+"€",igenerique,sgenerique,font); 

        }
        //commod=new MyRvfield(fcase,color,"null",igenerique,sgenerique,font);  

        //vue=new MyRvfield(fcase,color,"null",igenerique,sgenerique,font);

        //lit=new MyRvfield(fcase,color,"null",igenerique,sgenerique,font);

    }

    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){

        total=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("BDC_total"),igenerique,sgenerique,font);

        paye=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("BDC_pay"),igenerique,sgenerique,font);

        solde=new MyRvfield(fcase,color,java.util.ResourceBundle .getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("BDC_solde"),igenerique,sgenerique,font);     

        if(dossier.getDr_nbj_av_eche()!=0)
        {
            if(dossier.getDr_solde()!=1)

                libelle=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("BDC_libelle"),igenerique,sgenerique,font);      

            if(dossier.getDr_Paye()==0)
            {
                if(dossier.getDrtaxe()!=-1 && ((Clients_T)dossier.getClientFacturable()).getCsdelaipaiem()==0)
                libelle1=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("BDC_libelle1"),igenerique,sgenerique,font);      
            libelle=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("BDC_libelle"),igenerique,sgenerique,font);      
            }
        }
        else
            if(dossier.getDr_solde()!=1)
            libelle1=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("BDC_libelle2"),igenerique,sgenerique,font);
        /* statut=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_statut"),igenerique,sgenerique,font);     

         tel=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_tel"),igenerique,sgenerique,font);      
        */
    }

    protected void genereArray(){

        fieldArray=new ArrayList();

        if(this.facture==0)

            fieldArray.add(new MyRvfield[]{total,ftotal});//fnumbro,numpage,fnumpage});

        fieldArray.add(new MyRvfield[]{paye,fpaye});//fnumbro,numpage,fnumpage});

       

        fieldArray.add(new MyRvfield[]{solde,fsolde});//fnumbro,numpage,fnumpage});
        
        if(dossier.getDr_nbj_av_eche()!=0)
        {

            if(dossier.getDr_solde()!=1)
            fieldArray.add(new MyRvfield[]{libelle,datePayement});//fnumbro,numpage,fnumpage});
        
            if(dossier.getDr_Paye()==0)
            {
            fieldArray.add(new MyRvfield[]{libelle,datePayement});//fnumbro,numpage,fnumpage});
            fieldArray.add(new MyRvfield[]{libelle1,acompteDu});//fnumbro,numpage,fnumpage});
            }
         
        }
        else
           fieldArray.add(new MyRvfield[]{libelle1,});
           
           
        //fieldArray.add(new MyRvfield[]{libelle,datePayement});//fnumbro,numpage,fnumpage});

        //fieldArray=elementArray;

        

       /* fieldArray.add(new MyRvfield[]{ datedep,fdatedep});

        fieldArray.add(new MyRvfield[]{agent,fagent});

        fieldArray.add(new MyRvfield[]{statut,fstatut});

        fieldArray.add(new MyRvfield[]{ tel,ftel});*/

    }

    

    /** Getter for property fieldArray.

     * @return Value of property fieldArray.

     */

    public java.util.ArrayList getFieldArray() {

        return fieldArray;

    }

    

    /** Setter for property fieldArray.

     * @param fieldArray New value of property fieldArray.

     */

    public void setFieldArray(java.util.ArrayList fieldArray) {

        fieldArray = fieldArray;

    }

    

    public void initFieldPosition(double x, double y) {

        if(elementArray!=null){

           for(int i=0;i<elementArray.size();i++){

              MyRvfield[] tab= (MyRvfield[])elementArray.get(i);

              tab[0].x=x;

              tab[0].y=y;

              tab[1].x=3d;

              tab[1].y=y;

              tab[2].x=11d;

              tab[2].y=y;

              tab[3].x=13d;

              tab[3].y=y;

              tab[4].x=14d;

              tab[4].y=y;

              tab[5].x=15d;

              tab[5].y=y;

              tab[6].x=14d;

              tab[6].y=y;

              y2=y;

              x2=tab[6].x+tab[6].width;

              y=y+0.4d;

           }        

        }

    /*    x1=x;

        y1=y;

        libelle.y=y;

        libelle.x=x;

        libellecompta.y=y;

        prixu.y=y;

        pax.y=y;

        qua.y=y;

        prct.y=y;

        prixtot.y=y;

        libellecompta.x=3d;

        prixu.x=11d;

        pax.x=13d;               

        qua.x=14d;//mPageWidthCM-(nat.width+report.marginLeft+report.marginRight); 

        prct.x=15d;

        prixtot.x=16d; 

        x2=prixtot.x+prixtot.width; 

        y2=y;*/

    }

    

    public void initLabelPosition(double x, double y) {

        ftotal.ajust=false;

        ftotal.width=2d;

        ftotal.Align=ftotal.ALIGN_RIGHT;

        ftotal.x=report.mPageWidthCM-(report.marginRight+report.marginLeft+ftotal.width);

        ftotal.y=y;

        FormatPrix.formatField(ftotal);

        total.x=ftotal.x-3d;

        total.y=ftotal.y;

        fpaye.ajust=false;

        fpaye.width=2d;

        fpaye.Align=fpaye.ALIGN_RIGHT;

        fpaye.x=report.mPageWidthCM-(report.marginRight+report.marginLeft+fpaye.width);

        fpaye.y=y+0.4d;

        FormatPrix.formatField(fpaye);

        paye.x=fpaye.x-3d;

        paye.y=fpaye.y;

        if(dossier.getDr_nbj_av_eche()!=0)
        {
            if(dossier.getDr_solde()==1){

                fsolde.ajust=false;

                fsolde.width=2d;

                fsolde.Align=fsolde.ALIGN_RIGHT;

                fsolde.x=report.mPageWidthCM-(report.marginRight+report.marginLeft+fsolde.width);

                fsolde.y=y+0.8d;

                FormatPrix.formatField(fsolde);

                solde.x=fsolde.x-3d;

                solde.y=fsolde.y;




            }

            else{

                fsolde.ajust=false;

                fsolde.width=3d;

                fsolde.Align=fsolde.ALIGN_LEFT;

                fsolde.x=report.mPageWidthCM-(report.marginRight+report.marginLeft+fsolde.width+0.25d);

                fsolde.y=y+1.2d;

                FormatPrix.formatField(fsolde);

                solde.x=fsolde.x-3d;

                solde.y=fsolde.y;

                libelle.x=report.mPageWidthCM-(report.marginRight+report.marginLeft+this.width+1d);//+0.5d);

                libelle.y=y+0.8d;

                datePayement.y=y+0.8d;

                datePayement.x=libelle.x+libelle.width; 

            }

           if(dossier.getDr_Paye()==0)
            {

                //libelle.x=report.mPageWidthCM-(report.marginRight+report.marginLeft+this.width);//+0.5d);

                libelle.y=solde.y+0.4d;

                datePayement.y=libelle.y;

                datePayement.x=libelle.x+libelle.width; 


                if(dossier.getDrtaxe()!=-1)
                {
               // libelle1.ajust=false;
                    //libelle1.Align=libelle1.ALIGN_RIGHT;
                    //libelle1.
                libelle1.y=libelle.y+0.4d;

                //libelle1.x=libelle1.x+libelle1.width; 
                acompteDu.y=libelle1.y;
                acompteDu.x=libelle1.x+libelle1.width;
                }
               
                


            }

        }
        else
       
        {
                fsolde.ajust=false;

                fsolde.width=2d;

                fsolde.Align=fsolde.ALIGN_RIGHT;

                fsolde.x=report.mPageWidthCM-(report.marginRight+report.marginLeft+fsolde.width);

                fsolde.y=y+0.8d;

                FormatPrix.formatField(fsolde);

                solde.x=fsolde.x-3d;

                solde.y=fsolde.y;
                
                if(dossier.getDr_solde()!=1)
                {
                 libelle1.x=report.mPageWidthCM-(report.marginRight+report.marginLeft+this.width+1d);//+0.5d);

                libelle1.y=solde.y+0.4d;
                }
        }        

        

       // ftotal.ajust=false;

        //ftotal.width=2d;

        //ftotal.Align=tab[6].ALIGN_RIGHT;

    }

    

    public void initFieldPosition(double x1, double x2, double x3, double x4, double x5, double x6, double y) {

       

    }

    

 MyRvfield total;

 MyRvfield ftotal;

 MyRvfield paye;

 MyRvfield fpaye;

 MyRvfield solde;

 MyRvfield fsolde;

 MyRvfield libelle;
 
  MyRvfield libelle1;

 MyRvfield datePayement;
 
 MyRvfield acompteDu;
}

