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



/**

 *

 * @author  Thomas

 */

public class PRBRO_bloquebas extends AbstractBloque{

    

    /** Creates a new instance of RHBloqueGauche */

      Brochure_T brochure;

      public PRBRO_bloquebas(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report,RArea parent,Brochure_T brochure,java.awt.Frame frame) {

        super(obj,obj2,obj3,obj4,frame,report);   

         this.brochure=brochure;

        initField(1,1,1,"",fontPlain10);   

        initLabel(1,1,1,"",fontBold10);   

        genereArray();

    }

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){      

       // BonCommandeText=new MyRvfield(fcase,color,"Bon de Commande",igenerique,sgenerique,fontBold12);
       String numpage="";
       
       if (brochure.getBro_num().length()!=0) 
           numpage=" - "+brochure.getBro_num();
       
           
        fnumbro=new MyRvfield(fcase,color,brochure.getBro_ref_catalog()+numpage,igenerique,sgenerique,font);

        //fnumpage=new MyRvfield(fcase,color,brochure.getBro_num(),igenerique,sgenerique,font);

      //  situtation=new MyRvfield(fcase,color,"null",igenerique,sgenerique,font);  

        //commod=new MyRvfield(fcase,color,"null",igenerique,sgenerique,font);  

        //vue=new MyRvfield(fcase,color,"null",igenerique,sgenerique,font);

        //lit=new MyRvfield(fcase,color,"null",igenerique,sgenerique,font);

    }

    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){

         numbro=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_bronum"),igenerique,sgenerique,font);

         //numpage=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_bropag"),igenerique,sgenerique,font);

         assuanus=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_assuanus")+" - ",igenerique,sgenerique,fontPlain10);

         assubagage=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_bag")+" - ",igenerique,sgenerique,fontPlain10);

         assuparticul=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_particul"),igenerique,sgenerique,fontPlain10);

         /*// date=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_dateDep"),igenerique,sgenerique,font);     

         agent=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_agent"),igenerique,sgenerique,font);      

         statut=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_statut"),igenerique,sgenerique,font);     

         tel=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_tel"),igenerique,sgenerique,font);      

    */}

    protected void genereArray(){

        fieldArray=new ArrayList(); 

        fieldArray.add(new MyRvfield[]{numbro,fnumbro}); //,numpage,fnumpage});
        

        fieldArray.add(new MyRvfield[]{assuanus,assubagage,assuparticul});

        if(brochure.getBro_ass_anul()==0)

            assuanus.setruntimeValue("");

        if(brochure.getBro_ass_bag()==0)

            assubagage.setruntimeValue("");

        if(brochure.getBro_ass_part()==0)

            assuparticul.setruntimeValue("");

      

        

        

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

    }

    

    public void initLabelPosition(double x, double y) {

    }

    

    public void initFieldPosition(double x1, double x2, double x3, double x4, double x5, double x6, double y) {

    }

    

 MyRvfield numbro;

 MyRvfield numpage;

 MyRvfield fnumbro;

 MyRvfield fnumpage;

 MyRvfield assuanus;

 MyRvfield assubagage;

 MyRvfield assuparticul;



}

