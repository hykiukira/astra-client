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

import srcastra.astra.sys.rmi.*;

import  srcastra.astra.gui.sys.*;

import srcastra.astra.sys.classetransfert.utils.*;

/**

 *

 * @author  Thomas

 */

public class RHBloqueCenterG extends AbstractBloque{

    

    /** Creates a new instance of RHBloqueGauche */

    int facture;

    astrainterface serveur;

    public RHBloqueCenterG(Object obj, Object obj2, Object obj3, Object obj4,java.awt.Frame frame,RReport report,int facture,astrainterface serveur) {

        super(obj,obj2,obj3,obj4,frame,report);

        this.facture=facture;

        this.serveur=serveur;

        initField(1,1,1,"",fontPlain10);   

      // initLabel(1,1,1,"",fontBold10);   

        genereArray();

        StaticFields.setBonCommande(BonCommandeText);

    }

    protected void initField(int fcase,int color,int igenerique,String sgenerique,java.awt.Font font){     

        if(facture==0 || facture==4 ){
            
           if(facture==0)
           {
            BonCommandeText=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("BDC_titre"),igenerique,sgenerique,fontBold12);
            BonCommandeText.name="lib_bdc";
            }
            else
            {
            BonCommandeText=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("vouch"),igenerique,sgenerique,fontBold12);
              
            BonCommandeText.name="lib_bdc";
            facture=0;
            }
            date=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_numdos"),igenerique,sgenerique,fontBold12);

            date.name="lib_date";
            
            datefields=new MyRvfield(fcase,color, dossier.getDrnumdos(),igenerique,sgenerique,fontBold12);   

            datefields.name="lib_date_fields"; 

        }

        else if(facture==1 || facture==2){

            BonCommandeText=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("Fac_num"),igenerique,sgenerique,fontBold12);

            BonCommandeText.name="lib_bdc";

            date=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BB2_datefact"),igenerique,sgenerique,fontBold12);

            date.name="lib_date";

            datefields=new MyRvfield(fcase,color, dossier.getDr_date_facturation().toString2(),igenerique,sgenerique,fontBold12);   

            datefields.name="lib_date_fields";           

            if(dossier.getDr_facture()==0){                       

              //  try{

                   // String numfact=serveur.renvParamCompta(currentUser.getUrcleunik()).checkNumero(currentUser.getUrcleunik(),ParamComptableInterface.JOURNAL_VENTE);

                    numdoc=new MyRvfield(fcase,color,dossier.getNumfact(),igenerique,sgenerique,fontBold12);

                    numdoc.name="lib_numdoc";

                    //dossier.setNumfact(numfact);

            /*    }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn){

                    ErreurScreenLibrary.displayErreur(getMain(), ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, sn);

                }

                catch(java.rmi.RemoteException rn){

                    ErreurScreenLibrary.displayErreur(getMain(), ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);

                } */ 

                }

            else{

               numdoc=new MyRvfield(fcase,color,dossier.getNumfact(),igenerique,sgenerique,fontBold12);

               numdoc.name="lib_numdoc";

            }

           }

        else if(facture==3){

            BonCommandeText=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("nc_num"),igenerique,sgenerique,fontBold12);

            BonCommandeText.name="lib_bdc";

            date=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BB2_dateedit"),igenerique,sgenerique,fontBold12);
            
           
                
            datefields=new MyRvfield(fcase,color, CalculDate.getTodayDate().toString2(),igenerique,sgenerique,fontBold12);  
           
            
            
            numdoc=new MyRvfield(fcase,color,dossier.getNumnc(),igenerique,sgenerique,fontBold12);

            date.name="lib_date";

            datefields.name="lib_date_fields";        

            numdoc.name="lib_numdoc";

            /*    try{

                    String numfact=serveur.renvParamCompta(currentUser.getUrcleunik()).checkNumero(currentUser.getUrcleunik(),ParamComptableInterface.JOURNAL_NCVENTE);

                    dossier.setNumnc(numfact);

                    numdoc=new MyRvfield(fcase,color,numfact,igenerique,sgenerique,fontBold12);

                    //dossier.setNumfact(numfact);

                }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn){

                    ErreurScreenLibrary.displayErreur(getMain(), ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, sn);

                }

                catch(java.rmi.RemoteException rn){

                    ErreurScreenLibrary.displayErreur(getMain(), ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);

                } */ 

        }

        //fdatedep=new MyRvfield(fcase,color,dossier.getDr_date_depart().toString2(),igenerique,sgenerique,font);

        //fagent=new MyRvfield(fcase,color,dossier.getCreator().getUrnom(),igenerique,sgenerique,font);

        //fstatut=new MyRvfield(fcase,color,"OK",igenerique,sgenerique,font);  

        //ftel=new MyRvfield(fcase,color,dossier.getClientContractuel().getCstelephonep(),igenerique,sgenerique,font);    

    }  

   protected void initLabel(int fcase,int color,int igenerique,String sgenerique,java.awt.Font font){

      /*   numdos=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_numdos"),igenerique,sgenerique,font);

         datedep=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_dateDep"),igenerique,sgenerique,font);     

         agent=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_agent"),igenerique,sgenerique,font);      

         statut=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_statut"),igenerique,sgenerique,font);     

         tel=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_tel"),igenerique,sgenerique,font);     */ 

    }

   protected void genereArray(){

        super.fieldArray=new ArrayList();

        if(facture==0){

            fieldArray.add(new MyRvfield[]{BonCommandeText});
            fieldArray.add(new MyRvfield[]{date,datefields});
        }

        else if(facture==1 || facture==2){

            fieldArray.add(new MyRvfield[]{BonCommandeText,numdoc});

            fieldArray.add(new MyRvfield[]{date,datefields});

        }

        else if(facture==3){

            fieldArray.add(new MyRvfield[]{BonCommandeText,numdoc});

            fieldArray.add(new MyRvfield[]{date,datefields});          

        }

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

    

 MyRvfield BonCommandeText;

 MyRvfield numdoc;

 MyRvfield date;

 MyRvfield datefields;

/* MyRvfield fnumdos;

 MyRvfield datedep;

 MyRvfield fdatedep;

 MyRvfield agent; 

 MyRvfield fagent;

 MyRvfield statut;

 MyRvfield fstatut;

 MyRvfield tel;

 MyRvfield ftel;

 ArrayList fieldArray;*/



}

