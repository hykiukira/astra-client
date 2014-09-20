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

import srcastra.astra.sys.classetransfert.dossier.avion.*;

import srcastra.astra.gui.modules.printing.produit.produitInfo.*;

/**

 *

 * @author  Thomas

 */

public class PRAV_Bloque extends AbstractBloque{

    

    /** Creates a new instance of RHBloqueGauche */

      Avion_ticket_T ticket;

      public PRAV_Bloque(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report, RArea parent,Avion_ticket_T ticket,java.awt.Frame frame) {

        super(obj,obj2,obj3,obj4,frame,report);   

        this.ticket=ticket;

        initField(1,1,1,"",fontPlain10);   

        initLabel(1,1,1,"",fontBold10);    

        genereArray();

    }

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){      

        fdatedep=new MyRvfield(fcase,color,ticket.getAt_date_depart().toString2(),igenerique,sgenerique,font);

        fnumbillet=new MyRvfield(fcase,color,ticket.getAt_num_billet(),igenerique,sgenerique,font);

        fcompagnie=new MyRvfield(fcase,color,((AvionInfo)ticket.getProduitInfo()).getCompagnie(),igenerique,sgenerique,font);

        fPNR=new MyRvfield(fcase,color,ticket.getAt_pnr()+" ["+ticket.getFrnom()+"]",igenerique,sgenerique,font);

        String pass="";

        if(ticket.getPassagert()!=null){

            if(ticket.getPassagert().getPr_nom()!=null)

                pass=ticket.getPassagert().getPr_nom();

        } 

        nompass=new MyRvfield(fcase,color,pass,igenerique,sgenerique,font);

       // fdebarq=new MyRvfield(fcase,color," ",igenerique,sgenerique,font);  

       //   agfax=new MyRvfield(fcase,color,entite.getEefax(),igenerique,sgenerique,font);    

       // agtel=new MyRvfield(fcase,color,entite.getEetelephones(),igenerique,sgenerique,font);     

       // agmail=new MyRvfield(fcase,color,entite.getEemail(),igenerique,sgenerique,font);      

       // agtva=new MyRvfield(fcase,color,entite.getEetva(),igenerique,sgenerique,font);     

       // agcbc=new MyRvfield(fcase,color,entite.getEecomptebancaire(),igenerique,sgenerique,font);      

    }

    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){

         datedep=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_Dep"),igenerique,sgenerique,font);

         numbillet=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_AV_numbillet"),igenerique,sgenerique,font);     

         compagnie=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_AV_comp"),igenerique,sgenerique,font);      

         PNR=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PRHO_pnr"),igenerique,sgenerique,font);     

         fnompass=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_AV_pass"),igenerique,sgenerique,font);      

         //cbc=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BG_cbc"),igenerique,sgenerique,font);     

    }

    protected void genereArray(){

        fieldArray=new ArrayList();

      //  fieldArray.add(new MyRvfield[]{PNR});

        fieldArray.add(new MyRvfield[]{PNR,fPNR,numbillet,fnumbillet,fnompass,nompass});

       //fieldArray.add(new MyRvfield[]{ compaglogement,flogement});

       //fieldArray.add(new MyRvfield[]{ adresse,fadresse});

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

 MyRvfield datedep;

 MyRvfield fdatedep;

 MyRvfield numbillet;

 MyRvfield fnumbillet;

 MyRvfield compagnie;

 MyRvfield fcompagnie; 

 MyRvfield nompass; 

 MyRvfield fnompass; 

 //MyRvfield debarq;

 //MyRvfield fdebarq;

}

