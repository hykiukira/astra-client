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

/**

 *

 * @author  Thomas

 */

public class RHBloqueGauche extends AbstractBloque{

    

    /** Creates a new instance of RHBloqueGauche */

    

    public RHBloqueGauche(Object obj,Object obj2,Object obj3,Object obj4,java.awt.Frame frame,RReport report) {

        super(obj,obj2,obj3,obj4,frame,report);   

        initField(1,1,1,"",fontPlain10);   

        initLabel(1,1,1,"",fontBold10);    

        genereArray();

    }

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){      

        nomagence=new MyRvfield(fcase,color,entite.getEenom(),igenerique,sgenerique,fontBold12);

        StaticFields.setBloqueG(nomagence);

        cp=new MyRvfield(fcase,color,genprint.getAgence().getCp(),igenerique,sgenerique,font);

        localite=new MyRvfield(fcase,color,genprint.getAgence().getLocalité(),igenerique,sgenerique,font);

        adresse=new MyRvfield(fcase,color,entite.getEeadresse(),igenerique,sgenerique,font);  
        
        space=new MyRvfield(fcase,color,"",igenerique,sgenerique,font);  

        agfax=new MyRvfield(fcase,color,entite.getEefax(),igenerique,sgenerique,font);    

        agtel=new MyRvfield(fcase,color,entite.getEetelephones(),igenerique,sgenerique,font);     

        agmail=new MyRvfield(fcase,color,entite.getEemail(),igenerique,sgenerique,font);      

        agtva=new MyRvfield(fcase,color,entite.getEetva(),igenerique,sgenerique,font);     

        agcbc=new MyRvfield(fcase,color,entite.getEecomptebancaire(),igenerique,sgenerique,font); 

        if(entite.getLicence()!=null)

            licence=new MyRvfield(fcase,color,entite.getLicence(),igenerique,sgenerique,font); 

        nomagence.name="nomagence";
        
        space.name="space";
        
        cp.name="cp";

        localite.name="localite";

        adresse.name="adresse";

        agfax.name="agfax";

        agtel.name="agtel";

        agmail.name="agmail";

        agtva.name="agtva";

        agcbc.name="agcbc";

        licence.name="licdata";

        

    }

    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){

         
         agence=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BG_agence"),igenerique,sgenerique,font);

         fax=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande",  currentUser.getLangage()).getString("RH_BG_fax"),igenerique,sgenerique,font);     

         tel=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BG_tel"),igenerique,sgenerique,font);      

         mail=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BG_email"),igenerique,sgenerique,font);     

         tva=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BG_tva"),igenerique,sgenerique,font);      

         cbc=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BG_cbc"),igenerique,sgenerique,font);     

         licencec=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("bdc_lic"),igenerique,sgenerique,font);     

         agence.name="agence";

         fax.name="fax";

         tel.name="tel";

         mail.name="mail";

         tva.name="tva";

         cbc.name="cbc";

         licencec.name="lic";

    }

    protected void genereArray(){

        fieldArray=new ArrayList();

        fieldArray.add(new MyRvfield[]{nomagence});
        
        fieldArray.add(new MyRvfield[]{ space});

        fieldArray.add(new MyRvfield[]{ adresse});

        fieldArray.add(new MyRvfield[]{ cp,localite});
        
        fieldArray.add(new MyRvfield[]{ space});

        fieldArray.add(new MyRvfield[]{ tel,agtel});

        fieldArray.add(new MyRvfield[]{ fax,agfax});

        fieldArray.add(new MyRvfield[]{ mail,agmail});

        fieldArray.add(new MyRvfield[]{ tva,agtva});

        fieldArray.add(new MyRvfield[]{ cbc,agcbc});

        if(licence!=null){

            fieldArray.add(new MyRvfield[]{ licencec,licence});

        }

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

 MyRvfield space; 

 MyRvfield agence;

 MyRvfield nomagence;

 MyRvfield cp;

 MyRvfield localite;

 MyRvfield adresse; 

 MyRvfield fax;

 MyRvfield agfax;

 MyRvfield tel;

 MyRvfield agtel;

 MyRvfield mail;

 MyRvfield agmail;

 MyRvfield tva;

 MyRvfield agtva;

 MyRvfield cbc;

 MyRvfield agcbc;

 MyRvfield licencec;

 MyRvfield licence;





}

