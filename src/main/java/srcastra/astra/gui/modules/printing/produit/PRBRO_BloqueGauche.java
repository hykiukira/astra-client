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

public class PRBRO_BloqueGauche extends AbstractBloque{

    

    /** Creates a new instance of RHBloqueGauche */

    Brochure_T brochure;

    public PRBRO_BloqueGauche(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report,RArea parent,Brochure_T brochure,java.awt.Frame frame) {

        super(obj,obj2,obj3,obj4,frame,report);   

        this.brochure=brochure;

        initField(1,1,1,"",fontPlain10);   

        initLabel(1,1,1,"",fontBold10);    

        genereArray();

    }

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){      

        BrochureInfo broinf=(BrochureInfo)brochure.getProduitInfo();

        fdepart=new MyRvfield(fcase,color,brochure.getBro_embarq().toString2(),igenerique,sgenerique,font);

        if(brochure.getFree_destination().equals("NO"))

            fdestination=new MyRvfield(fcase,color,broinf.getDestination(),igenerique,sgenerique,font);

        else

            fdestination=new MyRvfield(fcase,color,brochure.getFree_destination(),igenerique,sgenerique,font);

        ftransport=new MyRvfield(fcase,color,broinf.getTransport(),igenerique,sgenerique,font);

        if(broinf.getEmbarquement()!=null){

            if(!broinf.getEmbarquement().equals("") && !broinf.getEmbarquement().equals("NO"))

            fembarquement=new MyRvfield(fcase,color,broinf.getEmbarquement(),igenerique,sgenerique,font);  

        }

     //   agfax=new MyRvfield(fcase,color,entite.getEefax(),igenerique,sgenerique,font);    

       // agtel=new MyRvfield(fcase,color,entite.getEetelephones(),igenerique,sgenerique,font);     

       // agmail=new MyRvfield(fcase,color,entite.getEemail(),igenerique,sgenerique,font);      

       // agtva=new MyRvfield(fcase,color,entite.getEetva(),igenerique,sgenerique,font);     

       // agcbc=new MyRvfield(fcase,color,entite.getEecomptebancaire(),igenerique,sgenerique,font);     

    }

    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){
        
        
        
        
        
         depart=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_Dep"),igenerique,sgenerique,font);
         
         
         
         destination=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_Dest"),igenerique,sgenerique,font);     

         transport=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_Trans"),igenerique,sgenerique,font);      

         embarquement=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_Emb"),igenerique,sgenerique,font);     

         //tva=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BG_tva"),igenerique,sgenerique,font);      

         //cbc=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BG_cbc"),igenerique,sgenerique,font);     

    }

    protected void genereArray(){

        fieldArray=new ArrayList();

        fieldArray.add(new MyRvfield[]{depart,fdepart});

        fieldArray.add(new MyRvfield[]{ destination,fdestination});

        fieldArray.add(new MyRvfield[]{ transport,ftransport});

        if(fembarquement!=null)

            fieldArray.add(new MyRvfield[]{ embarquement,fembarquement});

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

    

 MyRvfield depart;

 MyRvfield fdepart;

 MyRvfield destination;

 MyRvfield fdestination;

 MyRvfield transport; 

 MyRvfield ftransport;

 MyRvfield embarquement;

 MyRvfield fembarquement;

}

