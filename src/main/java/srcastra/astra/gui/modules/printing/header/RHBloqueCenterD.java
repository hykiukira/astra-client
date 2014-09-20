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

public class RHBloqueCenterD extends AbstractBloque{

    

    /** Creates a new instance of RHBloqueGauche */

    

    public RHBloqueCenterD(Object obj, Object obj2, Object obj3, Object obj4,java.awt.Frame frame,RReport report) {

        super(obj,obj2,obj3,obj4,frame,report);   //obj3 = dossier

        initField(1,1,1,"",fontPlain10);   

      //  initLabel(1,1,1,"",fontBold10);   

        genereArray();

       

    }

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){      

        if(!dossier.getClientFacturable().getTitrenom().equals("")){

            clienttitre=new MyRvfield(fcase,color,dossier.getClientFacturable().getTitrenom(),igenerique,sgenerique,font);

            clienttitre.name="clienttitre";

            StaticFields.setClient(clienttitre);

        }

        clientnom=new MyRvfield(fcase,color," "+dossier.getClientFacturable().getCsnom(),igenerique,sgenerique,font);

        String pys="";
        
        if(dossier.getClientFacturable().getPyscleunik()!=20)
            pys="\n"+dossier.getClientFacturable().getPysnom();
        
            
        if(clienttitre==null)

            StaticFields.setClient(clientnom);

        clientprenom=new MyRvfield(fcase,color,dossier.getClientFacturable().getCsnom2(),igenerique,sgenerique,font);

        clientcp=new MyRvfield(fcase,color,dossier.getClientFacturable().getCodenom(),igenerique,sgenerique,font);  

        clientadresse=new MyRvfield(fcase,color,dossier.getClientFacturable().getCsadresse(),igenerique,sgenerique,font);    

        clientlocalite=new MyRvfield(fcase,color,dossier.getClientFacturable().getCslocalite()+pys,igenerique,sgenerique,font); 

        //numDossier=new MyRvfield(fcase,color,dossier.getDrnumdos(),igenerique,sgenerique,font); 
        
      //  clienttitre.ajust=true;

       // clientnom.ajust=true;

        //clienttitre.name="clienttitre";

        clientnom.name="clientnom";

        clientprenom.name="clientprenom";

        clientcp.name="clientcp";

        clientadresse.name="clientadresse";

        clientlocalite.name="clientlocalite";
        
       // numDossier.name="numDoss";


    }

/*   private void initLabel(int fcase,int color,int igenerique,String sgenerique,java.awt.Font font){

         numdos=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_numdos"),igenerique,sgenerique,font);

         datedep=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_dateDep"),igenerique,sgenerique,font);     

         agent=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_agent"),igenerique,sgenerique,font);      

         statut=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_statut"),igenerique,sgenerique,font);     

         tel=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_tel"),igenerique,sgenerique,font);      

    }*/

    protected void genereArray(){

        fieldArray=new ArrayList();

        if(clienttitre!=null)

            fieldArray.add(new MyRvfield[]{clienttitre,clientnom});

        else

            fieldArray.add(new MyRvfield[]{clientnom});

        fieldArray.add(new MyRvfield[]{clientprenom});

        fieldArray.add(new MyRvfield[]{ clientadresse});

        fieldArray.add(new MyRvfield[]{clientcp,clientlocalite});
        
       // fieldArray.add(new MyRvfield[]{numDossier});

        

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

    

    /** Creates a new instance of AbstractBloque  */

    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font) {

    }

    

    public void initFieldPosition(double x, double y) {

    }

    

    public void initLabelPosition(double x, double y) {

    }

    

    public void initFieldPosition(double x1, double x2, double x3, double x4, double x5, double x6, double y) {

    }

    

 MyRvfield clienttitre;

 MyRvfield clientnom;

 MyRvfield clientprenom;

 MyRvfield clientcp;

 MyRvfield clientadresse; 

 MyRvfield clientlocalite;
 
 MyRvfield clientpays;
 
 //MyRvfield numDossier;

 //MyRvfield statut;

 //MyRvfield fstatut;

 //MyRvfield tel;

 //MyRvfield ftel;

 



}

