/*

 * RHBloqueGauche.java

 *

 * Created on 14 mars 2003, 11:58

 */



package srcastra.astra.gui.modules.printing.header;

import srcastra.astra.gui.modules.printing.*;

import srcastra.astra.sys.classetransfert.*;

import srcastra.astra.sys.classetransfert.dossier.*;

import java.util.ArrayList;

import com.java4less.rreport.*;

import srcastra.astra.sys.classetransfert.utils.*;

import srcastra.astra.gui.modules.aidedesicion.*;

/**

 *

 * @author  Thomas

 */

public class RHBloqueBas2 extends AbstractBloque{

    

    /** Creates a new instance of RHBloqueGauche */

    AbstractBuffer descTree;

    String tvas="";

    public RHBloqueBas2(Object obj, Object obj2, Object obj3, Object obj4,java.awt.Frame frame,RReport report,AbstractBuffer descTree) {

        super(obj,obj2,obj3,obj4,frame,report);

        this.descTree=descTree;

        tvas=dossier.getClientFacturable().getCstvanum();

        initField(1,1,1,"",fontPlain10);   

        initLabel(1,1,1,"",fontBold10);   

        genereArray();

    }

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){      

       // BonCommandeText=new MyRvfield(fcase,color,"Bon de Commande",igenerique,sgenerique,fontBold12);

        

        fdatedep=new MyRvfield(fcase,color,dossier.getDr_date_depart().toString2(),igenerique,sgenerique,font);
        //System.out.println(dossier.getDr_date_depart().toString2()+" "+dossier.getDr_date_depart().toString2().length());
        
       
        fdatedep.name="fdatedep2";

        fdateret=new MyRvfield(fcase,color,dossier.getDr_date_retour().toString2(),igenerique,sgenerique,font);

        fdateret.name="fdateret2";

        int[] tab=CalculDate.renvDifferenceBetweenDate(dossier.getDr_date_depart(),dossier.getDr_date_retour());

        fnbjour=new MyRvfield(fcase,color,""+dossier.getDr_nbrjour(),igenerique,sgenerique,font); 

        fnbjour.name="fnbjour2";

        fnbrnuit=new MyRvfield(fcase,color,""+dossier.getDr_nbrenuit(),igenerique,sgenerique,font);  

        fnbrnuit.name="fnbrnuit2";

        ftelp=new MyRvfield(fcase,color,dossier.getClientFacturable().getCstelephonep(),igenerique,sgenerique,font); 

        ftelp.name="ftelp2";

     //   Object tmp=BufferEllement.getBufferElement(descTree,1,dossier.getDr_type_booking(),currentUser.getUrlmcleunik());

       // if(tmp==null)tmp=" ";

        ftelb=new MyRvfield(fcase,color,dossier.getClientFacturable().getCstelephones(),igenerique,sgenerique,font);

        ftelb.name="ftelb2";

        //String reference=dossier.getClientFacturable().getCsreference();

        ftelg=new MyRvfield(fcase,color,dossier.getClientFacturable().getCsgsm(),igenerique,sgenerique,font); 

        ftelg.name="ftelg2";

        if(!tvas.equals("")){

          ftva=new MyRvfield(fcase,color,tvas,igenerique,sgenerique,font);   

          ftva.name="ftva2";

        }
        
        String ff="";
        
        if(dossier.getClientFacturable().getCsfax().length()!=0)
            ff="Fax: "+dossier.getClientFacturable().getCsfax();
        
        
        ffax=new MyRvfield(fcase,color,ff,igenerique,sgenerique,font); 

        ffax.name="ffax";
            

        

    }

    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){
         if(dossier.getDr_date_retour().toString2().length()==0)
            dateret=new MyRvfield(fcase,color,"",igenerique,sgenerique,font);
         else
         dateret=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BB2_dateret"),igenerique,sgenerique,font);

         
         dateret.name="dateret2";
          if(dossier.getDr_date_depart().toString2().length()==0)
            datedep=new MyRvfield(fcase,color,"",igenerique,sgenerique,font);
          else
         datedep=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_dateDep"),igenerique,sgenerique,font);     

         datedep.name="datedep2";

         nbjour=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BB2_jour"),igenerique,sgenerique,font);      

         nbjour.name="nbjour2";

         nbrnuit=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BB2_nuit"),igenerique,sgenerique,font);             

         nbrnuit.name="nbrnuit2";

         telp=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("rh_telp"),igenerique,sgenerique,font); 

         telp.name="telp2";

         telb=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("rh_telb"),igenerique,sgenerique,font); 

         telb.name="telb2";

         telg=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("rh_gsm"),igenerique,sgenerique,font); 

         telg.name="telg2";

         if(!tvas.equals("")){

                tva=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BB2_tva"),igenerique,sgenerique,font);     

                tva.name="tva2";

         }

    }

    protected void genereArray(){

        super.fieldArray=new ArrayList();

        fieldArray.add(new MyRvfield[]{datedep,fdatedep,dateret,fdateret,nbjour,fnbjour,nbrnuit,fnbrnuit});

        if(tva!=null){

            fieldArray.add(new MyRvfield[]{telp,ftelp,telb,ftelb,telg,ftelg,tva,ftva,ffax});

        }

        else{

            fieldArray.add(new MyRvfield[]{telp,ftelp,telb,ftelb,telg,ftelg,ffax});

        }

       /* fieldArray.add(new MyRvfield[]{agent,fagent});

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

        datedep.x=0;

        datedep.y=y;

        fdatedep.x=datedep.width;

        fdatedep.y=y;

        dateret.x=5.5;

        dateret.y=y;

        fdateret.x=5.5+dateret.width;

        fdateret.y=y;

        nbjour.x=11;

        nbjour.y=y;

        fnbjour.x=11+nbjour.width;

        fnbjour.y=y;

        nbrnuit.x=14;

        nbrnuit.y=y;

        fnbrnuit.x=14+nbrnuit.width;

        fnbrnuit.y=y;

        telp.x=0;

        telp.y=y+0.5d;

        ftelp.x=telp.x+telp.width;

        ftelp.y=y+0.5d;

        telb.x=5.5;

        telb.y=y+0.5d;

        ftelb.x=5.5+telb.width;

        ftelb.y=y+0.5d;

        telg.x=11;

        telg.y=y+0.5d;

        ftelg.x=11+telg.width;

        ftelg.y=y+0.5d;

        if(tva!=null){

            tva.x=0;

            tva.y=y+1d;

            ftva.x=tva.width;

            ftva.y=y+1d;

        }
        
        ffax.x=nbrnuit.x+nbrnuit.width;
        ffax.y=y+1.25d;
        

    }    

 

    public void initLabelPosition(double x, double y) {

    }

    

    public void initFieldPosition(double x1, double x2, double x3, double x4, double x5, double x6, double y) {

    }

    

 MyRvfield datedep;

 MyRvfield fdatedep;

 MyRvfield dateret;

 MyRvfield fdateret;

 MyRvfield nbjour;

 MyRvfield fnbjour;

 MyRvfield nbrnuit;

 MyRvfield fnbrnuit;

 MyRvfield telb;

 MyRvfield ftelb;

 MyRvfield telp;

 MyRvfield ftelp;

 MyRvfield telg;

 MyRvfield ftelg;

 MyRvfield typbeb;

 MyRvfield ftypbeb;

 MyRvfield refcli;

 MyRvfield tva;

 MyRvfield frefcli;

 MyRvfield ftva;
 
 MyRvfield ffax;





}

