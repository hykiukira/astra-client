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

import srcastra.astra.sys.classetransfert.dossier.hotel.*;

import srcastra.astra.gui.modules.printing.produit.produitInfo.*;

/**

 *

 * @author  Thomas

 */

public class PRHO_BloqueGauche extends AbstractBloque{

    

    /** Creates a new instance of RHBloqueGauche */

      Hotel_T hotel;

      public PRHO_BloqueGauche(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report, RArea parent, Hotel_T hotel,java.awt.Frame frame) {

        super(obj,obj2,obj3,obj4,frame,report);   

        this.hotel=hotel;

        initField(1,1,1,"",fontPlain10);   

        initLabel(1,1,1,"",fontBold10);    

        genereArray();

    }

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){      

        fdateent=new MyRvfield(fcase,color,hotel.getHl_dateEntree().toString2(),igenerique,sgenerique,font);

        flogement=new MyRvfield(fcase,color,((HotelInfo)hotel.getProduitInfo()).getAccomodation()+" "+hotel.getHl_hotel()+" -",igenerique,sgenerique,font);

        String out=hotel.getHl_adresse()+" "+hotel.getHl_localite()+" "+((HotelInfo)hotel.getProduitInfo()).getDestination()+" -";
        
        if (hotel.getHl_email().length()!=0)
            out=out+"     "+hotel.getHl_email();
        
        
        fadresse=new MyRvfield(fcase,color,out,igenerique,sgenerique,font);

        fPNR=new MyRvfield(fcase,color,hotel.getHl_pnr(),igenerique,sgenerique,font);

       // fdebarq=new MyRvfield(fcase,color," ",igenerique,sgenerique,font);  

       //   agfax=new MyRvfield(fcase,color,entite.getEefax(),igenerique,sgenerique,font);    

       // agtel=new MyRvfield(fcase,color,entite.getEetelephones(),igenerique,sgenerique,font);     

       // agmail=new MyRvfield(fcase,color,entite.getEemail(),igenerique,sgenerique,font);      

       // agtva=new MyRvfield(fcase,color,entite.getEetva(),igenerique,sgenerique,font);     

       // agcbc=new MyRvfield(fcase,color,entite.getEecomptebancaire(),igenerique,sgenerique,font);     

    }

    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){

         dateent=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PRHO_dateent"),igenerique,sgenerique,font);

         logement=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_log"),igenerique,sgenerique,font);     

         adresse=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_adresse"),igenerique,sgenerique,font);      

         PNR=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PRHO_pnr"),igenerique,sgenerique,font);     

         //tva=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BG_tva"),igenerique,sgenerique,font);      

         //cbc=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BG_cbc"),igenerique,sgenerique,font);     

    }

    protected void genereArray(){

        fieldArray=new ArrayList();

        fieldArray.add(new MyRvfield[]{PNR});

        fieldArray.add(new MyRvfield[]{ dateent,fdateent});

        fieldArray.add(new MyRvfield[]{ logement,flogement});

        fieldArray.add(new MyRvfield[]{ adresse,fadresse});

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

 MyRvfield PNR;

 MyRvfield fPNR;

 MyRvfield dateent;

 MyRvfield logement;

 MyRvfield adresse;

 MyRvfield fdateent;

 MyRvfield flogement; 

 MyRvfield fadresse;

 //MyRvfield debarq;

 //MyRvfield fdebarq;

}

