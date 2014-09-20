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

import srcastra.astra.sys.classetransfert.dossier.avion.*;



/**

 *

 * @author  Thomas

 */

public class PrixBloque extends AbstractBloque{

    

    /** Creates a new instance of RHBloqueGauche */

     produit_T prod;

     RReport report;

     ArrayList elementArray;

     int prodnumber;

     int facture;

      public PrixBloque(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report, RArea parent,produit_T prod,java.awt.Frame frame,int facture) {

        super(obj,obj2,obj3,obj4,frame,report);   

        this.prod=prod;

        this.facture=facture;

        this.report=report;

        prodnumber=new Double(x).intValue();

        initField(1,1,1,"",fontPlain10);   

        //initLabel(1,1,1,"",fontBold10);   

        genereArray();

    }

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){

       elementArray=new ArrayList();

       double prixunitaire=0d;

      
           
            if(prod instanceof Frais_T)
            {
               prixunitaire=Math.round((prod.getValeur_tot_tva_inc()/(prod.getQua()*prod.getPax()))*100);       //getValeur_tot();
               prixunitaire/=100;
               
            }       //prod.getV
           else
           {
              prixunitaire=Math.round((prod.getValeur_tot_tva_inc()/(prod.getQua()*prod.getPax())*100));  //getAt_val_vente();
              prixunitaire/=100;
           }
       
       //}
           
       String billet="";

       if(prod instanceof Avion_ticket_T){

           if(((Avion_ticket_T)prod).getAt_num_billet()!=null){

                billet=((Avion_ticket_T)prod).getAt_num_billet();

           }

           if(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFacturenumber()==0)
           {    
               
               if(((Avion_ticket_T)prod).getPassagert()!=null){

                 if(((Avion_ticket_T)prod).getPassagert().getPr_nom()!=null){

                   billet=billet+"  "+((Avion_ticket_T)prod).getPassagert().getPr_nom();   

                 }

               } 
           }
       }

       String qua="";

       String prct="";

       if(prod.getQua()!=1)

           qua=qua+prod.getQua();

       if(prod.getPrct()!=100)

           prct=prct+prod.getPrct();

       String libelle="";

       if(prod.getLibelleCompta()!=null)

           libelle=prod.getLibelleCompta().toUpperCase();

      

      if(prod.getFrnom()!=null && srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFactfourn()==1 && facture!=0){

      MyRvfield[] element=new MyRvfield[]{// new MyRvfield(fcase,color,prod.getFullname(),igenerique,sgenerique,fontBold10),

                                            new MyRvfield(fcase,color,prod.getFrnom(),igenerique,sgenerique,font),    

                                            null,

                                            null,

                                            null,

                                            null,

                                            null

                                             };

       elementArray.add(element);

      }
       
     

       MyRvfield[] element2=new MyRvfield[]{// new MyRvfield(fcase,color,prod.getFullname(),igenerique,sgenerique,fontBold10),

                                            new MyRvfield(fcase,color,prodnumber+")  "+libelle+"  "+billet,igenerique,sgenerique,font),    

                                            new MyRvfield(fcase,color,""+prixunitaire,igenerique,sgenerique,font),

                                            new MyRvfield(fcase,color,""+prod.getPax(),igenerique,sgenerique,font),

                                            new MyRvfield(fcase,color,""+qua,igenerique,sgenerique,font),

                                            new MyRvfield(fcase,color,""+prct,igenerique,sgenerique,font),                                           

                                            new MyRvfield(fcase,color,""+prod.getValeur_tot_tva_inc(),igenerique,sgenerique,font)};

                                            

        elementArray.add(element2);

        

       if(prod.getSup_reduc()!=null){

            ArrayList tmparray=new ArrayList();

             for(Enumeration enu=prod.getSup_reduc().keys();enu.hasMoreElements();){

                 Sup_reduc_T sup=(Sup_reduc_T)prod.getSup_reduc().get(enu.nextElement());

                 tmparray.add(sup);              

             }

            Collections.sort(tmparray,new srcastra.astra.gui.sys.comparator.SupereducComparator(0,true,true));

           for(int m=0;m<tmparray.size();m++){

               Sup_reduc_T sup=(Sup_reduc_T)tmparray.get(m);

                String qua2="";

                String prct2="";

                if(sup.getQua()!=1)

                    qua2=qua2+sup.getQua();

                if(sup.getPrct()!=100)

                    prct2=prct2+sup.getPrct();

                String libelle2="";

                if(sup.getFreeLibelle()!=null)

                    libelle2=sup.getFreeLibelle().toUpperCase();

                if(sup.getPrct() ==100)
                {
                 prixunitaire=Math.round((sup.getValeur_tot_tva_inc()/(sup.getQua()*sup.getPax()))*100);
                 prixunitaire/=100;
                }
                else
                {
                  prixunitaire=sup.getPrix();//Math.round((sup.getPrix()*(sup.getQua()*sup.getPax()))*100);//Math.round((sup.getValeur_tot_tva_inc()*(sup.getQua()*sup.getPax()))*100);
                 //prixunitaire/=100;   
                  prct2=prct2+"%";
                 
                }
                 
               MyRvfield[] supelement=new MyRvfield[]{// new MyRvfield(fcase,color," ",igenerique,sgenerique,fontBold10),

                                            new MyRvfield(fcase,color,libelle2,igenerique,sgenerique,font,true),       

                                            new MyRvfield(fcase,color,""+prixunitaire,igenerique,sgenerique,font,true),

                                            new MyRvfield(fcase,color,""+sup.getPax(),igenerique,sgenerique,font,true),

                                            new MyRvfield(fcase,color,qua2,igenerique,sgenerique,font,true),

                                            new MyRvfield(fcase,color,prct2,igenerique,sgenerique,font,true),                                           

                                            new MyRvfield(fcase,color,""+sup.getValeur_tot_tva_inc(),igenerique,sgenerique,font,true)};

                                            elementArray.add(supelement);

                              

           }

       }

        //commod=new MyRvfield(fcase,color,"null",igenerique,sgenerique,font);  

        //vue=new MyRvfield(fcase,color,"null",igenerique,sgenerique,font);

        //lit=new MyRvfield(fcase,color,"null",igenerique,sgenerique,font);

    }

    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){

     //    numbro=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_bronum"),igenerique,sgenerique,font);

       //  numpage=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_bropag"),igenerique,sgenerique,font);

         /*// date=new MyRvfield(fcase,color,java.util.ResourceBundle .getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_dateDep"),igenerique,sgenerique,font);     

         agent=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_agent"),igenerique,sgenerique,font);      

         statut=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_statut"),igenerique,sgenerique,font);     

         tel=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_tel"),igenerique,sgenerique,font);      

    */}

    protected void genereArray(){

        //fieldArray=new ArrayList(); 

      //  fieldArray.add(new MyRvfield[]{libelle,libellecompta,pax,qua,prct,prixtot,prixu});//fnumbro,numpage,fnumpage});

        fieldArray=elementArray;

        

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

              if(tab[0].sousproduit==true)

                  tab[0].x=x+0.5d;

              else

                  tab[0].x=x;

              tab[0].y=y;      

              tab[0].width=12.5d ;

               

           //   tab[0].ajust=true;

              if(tab[1]!=null){

                  tab[1].ajust=false;     

                  tab[1].x=10d;

                  tab[1].y=y;

                  tab[1].width=3;           

                  tab[1].Align=tab[0].ALIGN_RIGHT;    

                  FormatPrix.formatField(tab[1]);

                  tab[2].x=13.5d;

                  tab[2].y=y;

                  tab[3].x=14.5d;

                  tab[3].y=y;

                  tab[4].x=15d;

                  tab[4].y=y;

                  tab[5].x=16d;

                  tab[5].y=y;

                  tab[5].ajust=false;

                  tab[5].width=3d;

                  tab[5].Align=tab[0].ALIGN_RIGHT;

                  FormatPrix.formatField(tab[5]);            

                  x2=tab[5].x+tab[5].width;

              }

              this.heigth=y;

              y=y+0.4d;

              this.y2=y;

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

    

 MyRvfield libelle;

 MyRvfield libellecompta;

 MyRvfield prixu;

 MyRvfield pax;

 MyRvfield qua;

 MyRvfield prct;

 MyRvfield prixtot;

}

