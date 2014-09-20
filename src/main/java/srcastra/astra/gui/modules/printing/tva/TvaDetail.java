/*

 * PassagerBloque.java

 *

 * Created on 17 mars 2003, 16:17

 */



package srcastra.astra.gui.modules.printing.tva;

import srcastra.astra.gui.modules.printing.*;

import srcastra.astra.gui.modules.printing.header.*;

import com.java4less.rreport.*;

import srcastra.astra.sys.Logger;

import java.util.*;

import srcastra.astra.sys.classetransfert.dossier.Passager_T;

import srcastra.astra.gui.modules.printing.classelangueuser.*;

import srcastra.astra.sys.compta.*;

import  srcastra.astra.gui.modules.printing.prix.FormatPrix;



/**

 *

 * @author  thomas

 */

public class TvaDetail extends AbstractBloque{

    

    /** Creates a new instance of PassagerBloque */

    Hashtable tvahash;

    public TvaDetail(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, ArrayList genprint, java.awt.Frame frame, RReport report,Hashtable tvahash) {

          super(obj,obj2,obj3,obj4,frame,report); 

          this.tvahash=tvahash;

          initField(1,1,1,"",fontPlain8,genprint);  

          //genereArray();

    }

    

    public java.util.ArrayList getFieldArray() {

        return fieldArray;

    }

    

    /** Creates a new instance of AbstractBloque  */

    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font) {

      /*   voyageur=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_voy"),igenerique,sgenerique,font);

         adresse=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_adresse"),igenerique,sgenerique,font);     

         datenai=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_datenai"),igenerique,sgenerique,font);      

         nat=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_nat"),igenerique,sgenerique,font);     

         cp=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_cp"),igenerique,sgenerique,font);     

         localite=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_loc"),igenerique,sgenerique,font);     

        */ 

    }

     

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font,ArrayList genprint) {

        fieldArray=new ArrayList();

        if(tvahash!=null){

            for(Enumeration enu=tvahash.keys();enu.hasMoreElements();){

              Tva_abrev_T tmp=(Tva_abrev_T)tvahash.get(enu.nextElement());

            MyRvfield[] myfields=new MyRvfield[4];

            String rate=new Float(tmp.getTva_rate()).toString();

            if(tmp!=null){

                 if(tmp.getTva_rate()==3.78f || tmp.getTva_rate()==2.73f || tmp.getTva_rate()==1.68f || tmp.getTva_rate()==1.26f){

                      rate=new Float(21.00f).toString();

                  }                 

                  }

                 myfields[0]=new MyRvfield(fcase,color,rate,igenerique,sgenerique,font);

                 myfields[0].Expand=true;

                 myfields[0].multiLine=false;     

                 myfields[1]=new MyRvfield(fcase,color, new Double(MathRound.roundThisToDouble(tmp.getTva_base())).toString(),igenerique,sgenerique,font);

                 myfields[1].Expand=true;

                 myfields[1].multiLine=false;

                 myfields[2]=new MyRvfield(fcase,color,new Double(MathRound.roundThisToDouble(tmp.getTva_value())).toString(),igenerique,sgenerique,font);

                 myfields[2].Expand=true;

                 myfields[2].multiLine=false;

                 myfields[3]=new MyRvfield(fcase,color,new Double(MathRound.roundThisToDouble(tmp.getTva_base()+tmp.getTva_value())).toString()+"€",igenerique,sgenerique,font);

                 myfields[3].Expand=true;

                 myfields[3].multiLine=false;

                 fieldArray.add(myfields);

        }

       }

       //tel=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_tel"),igenerique,sgenerique,font);      

    }

    

      protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){

          

      }

    public void initFieldPosition(double x1,double x2,double x3,double x4,double x5,double x6,double y){

        double y2=y;

        //

        for(int i=0;i<fieldArray.size();i++){

            MyRvfield[] myfields=(MyRvfield[])fieldArray.get(i);

            myfields[0].width=3d;

            myfields[0].x=x1-3d;

            myfields[0].y=y2;

            myfields[0].Align=myfields[0].ALIGN_RIGHT;

            FormatPrix.formatField(myfields[0]);

            myfields[1].width=3d;

            myfields[1].x=x2-3d;//-myfields[1].width;

            myfields[1].y=y2;

            myfields[1].Align=myfields[1].ALIGN_RIGHT;

            FormatPrix.formatField(myfields[1]);

            myfields[2].width=3d;

            myfields[2].x=x3-3d;//-myfields[2].width;

            myfields[2].y=y2;

            myfields[2].Align=myfields[2].ALIGN_RIGHT;

            FormatPrix.formatField(myfields[2]);

            myfields[3].width=3d;

            myfields[3].x=x4-3d;//-myfields[3].width;

            myfields[3].y=y2;

            myfields[3].Align=myfields[3].ALIGN_RIGHT;

            FormatPrix.formatField(myfields[3]);

           /* myfields[4].x=x3;

            myfields[4].y=y2;

            myfields[5].x=x4;

            myfields[5].y=y2;

            myfields[6].x=x5;

            myfields[6].y=y2;

            myfields[7].x=x6;

            myfields[7].y=y2;*/

           // fieldArray.add( myfields);

           // passager.add(myfields);

            y2=y2+0.3d;

            this.y2=y2;

        }

        

        

    }

    public  void initFieldPosition(double x,double y){

        

    }

    public void initLabelPosition(double x,double y){

   /*     x1=x;

        y1=y;

        voyageur.y=y;

        adresse.y=y;

        datenai.y=y;

        nat.y=y;

        voyageur.x=x;

        cp.y=y;

        localite.y=y;

        adresse.x=voyageur.x+voyageur.width+1d;

        cp.x=adresse.x+adresse.width+3d;

        localite.x=cp.x+cp.width +1d;        

        datenai.x=localite.x+localite.width+0.5d;

        nat.x=datenai.x+datenai.width+0.5d; 

        x2=nat.x+nat.width;*/

    } 

    

    protected void genereArray() {

        //fieldArray=new ArrayList();

       // fieldArray.add(new MyRvfield[]{voyageur,adresse,cp,localite,datenai,nat});

        //fieldArray.add(new MyRvfield[]{ clientadresse});

        //fieldArray.add(new MyRvfield[]{clientcp,clientlocalite});

    }

    

//MyRvfield voyageur;

//MyRvfield adresse;

//MyRvfield cp;

//MyRvfield localite;

//MyRvfield datenai;

//MyRvfield nat;    

ArrayList passager;

}

