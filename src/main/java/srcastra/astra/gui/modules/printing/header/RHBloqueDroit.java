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

public class RHBloqueDroit extends AbstractBloque{

    

    /** Creates a new instance of RHBloqueGauche */

    

    public RHBloqueDroit(Object obj, Object obj2, Object obj3, Object obj4,java.awt.Frame frame,RReport report) {

        super(obj,obj2,obj3,obj4,frame,report);   

        initField(1,1,1,"",fontPlain10);   

        initLabel(1,1,1,"",fontBold10);   

        genereArray();

    }

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){      

        fnumdos=new MyRvfield(fcase,color,dossier.getDrnumdos(),igenerique,sgenerique,font);
        
        space=new MyRvfield(fcase,color,"",igenerique,sgenerique,font);

        fdatedep=new MyRvfield(fcase,color,dossier.getDr_date_depart().toString2(),igenerique,sgenerique,font);

        fagent=new MyRvfield(fcase,color," ",igenerique,sgenerique,font);

        if(dossier.getCreator()!=null)

            if(dossier.getCreator().getUrnom()!=null)

        fagent=new MyRvfield(fcase,color,dossier.getCreator().getUrnom(),igenerique,sgenerique,font);
        
        srcastra.astra.gui.sys.listModel.dossierListModel.StatusListModel statutliste;
        
        statutliste=new  srcastra.astra.gui.sys.listModel.dossierListModel.StatusListModel(null,currentUser);
        
        String statut=statutliste.getValue(dossier.getDrstatus());
        
        
        if(statut==null)
            statut="";
        
        
        fstatut=new MyRvfield(fcase,color,statut,igenerique,sgenerique,font);  
        

        ftel=new MyRvfield(fcase,color,dossier.getClientContractuel().getCstelephonep(),igenerique,sgenerique,font);    

        fnumdos.name="fnumdos";

        fdatedep.name="fdatedep";

        fagent.name="fagent";

        fstatut.name="fstatut";

        ftel.name="ftel";
        
        space.name="space";

    }

    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){

         numdos=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_numdos"),igenerique,sgenerique,font);

         StaticFields.setBloqueD(numdos);

         datedep=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_dateDep"),igenerique,sgenerique,font);     

         agent=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_agent"),igenerique,sgenerique,font);      

         statut=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_statut"),igenerique,sgenerique,font);     

         tel=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_tel"),igenerique,sgenerique,font);      

         numdos.name="numdos";

         datedep.name="datedep";

         agent.name="agent";

         statut.name="statut";

         tel.name="tel2";

    }

    protected void genereArray(){

        fieldArray=new ArrayList();

        fieldArray.add(new MyRvfield[]{numdos,fnumdos});
        
        fieldArray.add(new MyRvfield[]{ datedep,fdatedep});

        fieldArray.add(new MyRvfield[]{agent,fagent});

        fieldArray.add(new MyRvfield[]{statut,fstatut});

        fieldArray.add(new MyRvfield[]{ tel,ftel});

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

    

 MyRvfield numdos;

 MyRvfield fnumdos;

 MyRvfield datedep;

 MyRvfield fdatedep;

 MyRvfield agent; 

 MyRvfield fagent;

 MyRvfield statut;

 MyRvfield fstatut;

 MyRvfield tel;

 MyRvfield ftel;
 
MyRvfield space;





}

