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

import srcastra.astra.gui.modules.aidedesicion.*;



/**

 *

 * @author  Thomas

 */

public class PayementBloque extends AbstractBloque{

    

    /** Creates a new instance of RHBloqueGauche */

     produit_T prod;

     RReport report;

     ArrayList elementArray;

     Payement_T pay;

     AbstractBuffer buffer;

     ArrayList typepaiementA;

      public PayementBloque(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report, RArea parent, Payement_T pay,java.awt.Frame frame,AbstractBuffer buffer) {

        super(obj,obj2,obj3,obj4,frame,report); 

        typepaiementA=(ArrayList)obj4;
        
        for(int i=0;i<typepaiementA.size();i++){
            Object[] tab=(Object[])typepaiementA.get(i);
            System.out.println("");
        }

        this.prod=prod;

        this.report=report;

        this.pay=pay;      

        this.buffer=buffer;

        initField(1,1,1,"",fontPlain10);   

        initLabel(1,1,1,"",fontBold10);   

        genereArray();

    }

       private Object getElement(int id){

        if(typepaiementA!=null)

        for(int i=0;i<typepaiementA.size();i++){

            Object[] tab=(Object[])typepaiementA.get(i);

            if(Integer.parseInt(tab[0].toString())==id){

              return tab[currentUser.getUrlmcleunik()]==null?" ":tab[currentUser.getUrlmcleunik()];   

            }            

        }       

       return "";

    }

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){

      date=new MyRvfield(fcase,color,pay.getDatePayement().toString2(),igenerique,sgenerique,font);  

      Object tmp=BufferEllement.getBufferElement(buffer,2,pay.getM_typepayement(),currentUser.getUrlmcleunik());

      typepayement=new MyRvfield(fcase,color,getElement(pay.getM_typepayement()).toString(),igenerique,sgenerique,font);     

      prix=new MyRvfield(fcase,color,""+pay.getPrix(),igenerique,sgenerique,font);   

      prix.Align=prix.ALIGN_RIGHT;                                      

     /*   elementArray.add(element);

       if(prod.getSup_reduc()!=null)

           for(Enumeration enu=prod.getSup_reduc().keys();enu.hasMoreElements();){

               Sup_reduc_T sup=(Sup_reduc_T)prod.getSup_reduc().get(enu.nextElement());

               MyRvfield[] supelement=new MyRvfield[]{ new MyRvfield(fcase,color," ",igenerique,sgenerique,fontBold10),

                                            new MyRvfield(fcase,color,sup.getFreeLibelle(),igenerique,sgenerique,font),       

                                            new MyRvfield(fcase,color,""+sup.getAt_val_vente(),igenerique,sgenerique,font),

                                            new MyRvfield(fcase,color,""+sup.getPax(),igenerique,sgenerique,font),

                                            new MyRvfield(fcase,color,""+sup.getQua(),igenerique,sgenerique,font),

                                            new MyRvfield(fcase,color,""+sup.getPrct(),igenerique,sgenerique,font),                                           

                                            new MyRvfield(fcase,color,""+sup.getValeur_tot_tva_inc(),igenerique,sgenerique,font)};

               elementArray.add(supelement);

                              

           }*/

        //commod=new MyRvfield(fcase,color,"null",igenerique,sgenerique,font);  

        //vue=new MyRvfield(fcase,color,"null",igenerique,sgenerique,font);

        //lit=new MyRvfield(fcase,color,"null",igenerique,sgenerique,font);

    }

    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){

          payement=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("BDC_pay2"),igenerique,sgenerique,fontBold10);

          par=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("BDC_par"),igenerique,sgenerique,font);     

     //    numbro=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_bronum"),igenerique,sgenerique,font);

       //  numpage=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_bropag"),igenerique,sgenerique,font);

         /*// date=new MyRvfield(fcase,color,java.util.ResourceBundle .getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_dateDep"),igenerique,sgenerique,font);     

         agent=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_agent"),igenerique,sgenerique,font);      

         statut=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_statut"),igenerique,sgenerique,font);     

         tel=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_tel"),igenerique,sgenerique,font);      

    */}

    protected void genereArray(){

       fieldArray=new ArrayList(); 

        fieldArray.add(new MyRvfield[]{payement,date,par,typepayement,prix});//fnumbro,numpage,fnumpage});

        

        

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

            payement.y=y;

            date.y=y;

            par.y=y;

            typepayement.y=y;

            prix.y=y;

            payement.x=x;

            date.x=4d;

            par.x=7d;

            typepayement.x=9d;

            prix.x=17d;

            prix.width=2d;

            prix.ajust=false;

            prix.Align=prix.ALIGN_RIGHT;

            FormatPrix.formatField(prix);

       /* if(elementArray!=null){

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

              tab[6].x=17d;

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

        

    }

    

    public void initFieldPosition(double x1, double x2, double x3, double x4, double x5, double x6, double y) {

    }

    

 MyRvfield payement;

 MyRvfield date;

 MyRvfield par;

 MyRvfield typepayement;

 MyRvfield prix;

 

 

}

