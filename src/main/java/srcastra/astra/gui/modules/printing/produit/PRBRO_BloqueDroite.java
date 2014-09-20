/*

 * RHBloqueGauche.java

 *

 * Created on 14 mars 2003, 11:58

 */



package srcastra.astra.gui.modules.printing.produit;

import srcastra.astra.gui.modules.printing.*;

import srcastra.astra.gui.modules.printing.header.*;

import com.java4less.rreport.*;

import srcastra.astra.sys.Logger;

import java.util.*;

import srcastra.astra.sys.classetransfert.dossier.Passager_T;

import srcastra.astra.gui.modules.printing.classelangueuser.*;

import srcastra.astra.sys.classetransfert.utils.CalculDate;

import srcastra.astra.sys.classetransfert.dossier.brochure.Brochure_T;

import srcastra.astra.sys.classetransfert.utils.*;

/**

 *

 * @author  Thomas

 */

public class PRBRO_BloqueDroite extends AbstractBloque{

    

    /** Creates a new instance of RHBloqueGauche */

      Brochure_T brochure;

    public PRBRO_BloqueDroite(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report,RArea parent,Brochure_T brochure,java.awt.Frame frame) {

        super(obj,obj2,obj3,obj4,frame,report);   

        this.brochure=brochure;

        initField(1,1,1,"",fontPlain10);   

        initLabel(1,1,1,"",fontBold10);    

        genereArray();

    }

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){     

        int[] tab=CalculDate.renvDifferenceBetweenDate(brochure.getBro_embarq(),brochure.getBro_debarq());

        String jour;

        String nuit;

        if(tab[0]==-1 || tab[1]==-1){

            jour="?";

            nuit="?";           

        }

        else{

         nuit=""+(tab[0]);

         jour=""+(tab[1]);

        }

            

        fjour=new MyRvfield(fcase,color,jour,igenerique,sgenerique,font);

        fnuit=new MyRvfield(fcase,color,nuit,igenerique,sgenerique,font);

        fregime=new MyRvfield(fcase,color,"null",igenerique,sgenerique,font);

       // fdebarq=new MyRvfield(fcase,color," ",igenerique,sgenerique,font);  

     //   agfax=new MyRvfield(fcase,color,entite.getEefax(),igenerique,sgenerique,font);    

       // agtel=new MyRvfield(fcase,color,entite.getEetelephones(),igenerique,sgenerique,font);     

       // agmail=new MyRvfield(fcase,color,entite.getEemail(),igenerique,sgenerique,font);      

       // agtva=new MyRvfield(fcase,color,entite.getEetva(),igenerique,sgenerique,font);     

       // agcbc=new MyRvfield(fcase,color,entite.getEecomptebancaire(),igenerique,sgenerique,font);     

    }

    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){

         jour=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BB2_jour1"),igenerique,sgenerique,font);

         nuit=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BB2_nuit"),igenerique,sgenerique,font);     

         regime=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_reg"),igenerique,sgenerique,font);      

         //debarq=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_deb"),igenerique,sgenerique,font);     

         //tva=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BG_tva"),igenerique,sgenerique,font);      

         //cbc=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BG_cbc"),igenerique,sgenerique,font);     

    }

    protected void genereArray(){

        fieldArray=new ArrayList();

        fieldArray.add(new MyRvfield[]{jour,fjour});

        fieldArray.add(new MyRvfield[]{ nuit,fnuit});

       // fieldArray.add(new MyRvfield[]{ regime,fregime});

        //fieldArray.add(new MyRvfield[]{ debarq,fdebarq});

     //   fieldArray.add(new MyRvfield[]{ fax,agfax});

       // fieldArray.add(new MyRvfield[]{ mail,agmail});

       // fieldArray.add(new MyRvfield[]{ tva,agtva});

       // fieldArray.add(new MyRvfield[]{ cbc,agcbc});

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

        this.fieldArray = fieldArray;

    }

    

    public void initFieldPosition(double x, double y) {

    }

    

    public void initLabelPosition(double x, double y) {

    }

    

    public void initFieldPosition(double x1, double x2, double x3, double x4, double x5, double x6, double y) {

    }

    

 MyRvfield jour;

 MyRvfield fjour;

 MyRvfield nuit;

 MyRvfield fnuit;

 MyRvfield regime; 

 MyRvfield fregime;

 //MyRvfield debarq;

 //MyRvfield fdebarq;

}

