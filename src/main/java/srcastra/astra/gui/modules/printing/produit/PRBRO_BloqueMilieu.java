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

import srcastra.astra.gui.modules.printing.produit.produitInfo.*;

/**

 *

 * @author  Thomas

 */

public class PRBRO_BloqueMilieu extends AbstractBloque{

    

    /** Creates a new instance of RHBloqueGauche */

      Brochure_T brochure;

    public PRBRO_BloqueMilieu(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report,RArea parent,Brochure_T brochure,java.awt.Frame frame) {

        super(obj,obj2,obj3,obj4,frame,report);   

         this.brochure=brochure;

        initField(1,1,1,"",fontPlain10);   

        initLabel(1,1,1,"",fontBold10);    

        genereArray();

    }

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){    

        BrochureInfo broinf=(BrochureInfo)brochure.getProduitInfo();

        fretour=new MyRvfield(fcase,color,brochure.getBro_debarq().toString2(),igenerique,sgenerique,font);

        flieu=new MyRvfield(fcase,color,brochure.getBro_localite(),igenerique,sgenerique,font);

        if(((BrochureInfo)brochure.getProduitInfo()).getLogement()!=null && !((BrochureInfo)brochure.getProduitInfo()).getLogement().equals("null"))

            flogement=new MyRvfield(fcase,color,((BrochureInfo)brochure.getProduitInfo()).getLogement()+" "+brochure.getBro_logement()+" -",igenerique,sgenerique,font);

        else

             flogement=new MyRvfield(fcase,color,brochure.getBro_logement()+" -",igenerique,sgenerique,font);

        if(broinf.getDebarquement()!=null){

            if(!broinf.getDebarquement().equals("") && !broinf.getDebarquement().equals("NO"))

                fdebarq=new MyRvfield(fcase,color,broinf.getDebarquement(),igenerique,sgenerique,font);  

        }

     //   agfax=new MyRvfield(fcase,color,entite.getEefax(),igenerique,sgenerique,font);    

       // agtel=new MyRvfield(fcase,color,entite.getEetelephones(),igenerique,sgenerique,font);     

       // agmail=new MyRvfield(fcase,color,entite.getEemail(),igenerique,sgenerique,font);      

       // agtva=new MyRvfield(fcase,color,entite.getEetva(),igenerique,sgenerique,font);     

       // agcbc=new MyRvfield(fcase,color,entite.getEecomptebancaire(),igenerique,sgenerique,font);     

    }

    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){

         retour=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_ret"),igenerique,sgenerique,font);

         lieu=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_lieu"),igenerique,sgenerique,font);     

         logement=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_log"),igenerique,sgenerique,font);      

         debarq=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_deb"),igenerique,sgenerique,font);     

         //tva=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BG_tva"),igenerique,sgenerique,font);      

         //cbc=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BG_cbc"),igenerique,sgenerique,font);     

    }

    protected void genereArray(){

        fieldArray=new ArrayList();

        fieldArray.add(new MyRvfield[]{retour,fretour});

        fieldArray.add(new MyRvfield[]{ lieu,flieu});

        fieldArray.add(new MyRvfield[]{ logement,flogement});

        if(fdebarq!=null)

            fieldArray.add(new MyRvfield[]{ debarq,fdebarq});

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

 MyRvfield retour;

 MyRvfield fretour;

 MyRvfield lieu;

 MyRvfield flieu;

 MyRvfield logement; 

 MyRvfield flogement;

 MyRvfield debarq;

 MyRvfield fdebarq;

}

