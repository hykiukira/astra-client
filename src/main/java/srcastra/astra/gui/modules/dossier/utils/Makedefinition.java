/*



 * makedefinition.java



 *



 * Created on 23 janvier 2003, 10:30



 */







package srcastra.astra.gui.modules.dossier.utils;

import srcastra.astra.gui.modules.mainScreenModule.*;

import srcastra.astra.sys.classetransfert.dossier.produit_T;

import srcastra.astra.gui.sys.*;

import srcastra.astra.gui.modules.dossier.DossierProduitPane;

import srcastra.astra.sys.classetransfert.Grpdecision_T;

import srcastra.astra.sys.classetransfert.dossier.InterfaceProduit;

import srcastra.astra.sys.classetransfert.Document_T;

import srcastra.astra.sys.rmi.groupe_dec.*;

import srcastra.astra.sys.rmi.*;





/**



 *



 * @author  Thomas



 */



public class Makedefinition {



    



    /** Creates a new instance of makedefinition */



    public Makedefinition() {



        



    }



    public static void  makeProduitDefinition(DossierMainScreenModule parent, produit_T produit,DossierProduitPane parentPane,int typeProduct,int fournisseur){



         try{         



             if(produit.getGroupdec()==null){

               GrpGroupDecRmiInterface grprmi=parent.getServeur().renvGrpDecRmiObject(parent.getCurrentUser().getUrcleunik());

               Grpdecision_T tmp=grprmi.selectFournisseur(typeProduct,0,parent.getCurrentUser().getUrcleunik());

                 // Grpdecision_T tmp=(Grpdecision_T)parent.getServeur().ChargeObject(0, parent.getCurrentUser().getUrcleunik(),

                   //                     typeProduct, 1, parent.getServeur().COMBO_FOURGRPDEC);

               produit.setGroupdec(tmp);

               produit.setFrgtcleunik(tmp.getFrgtcleunik());

                   try{

                      produit.setGroupdecBase((Grpdecision_T)tmp.clone());

                   }catch(CloneNotSupportedException cn){

                       cn.printStackTrace(); 

                   }

                   parentPane.setProdutFournAndGpdec((InterfaceProduit)produit);

             }

                

                parent.setMenuProduit(true);



                //parent.getMainframe().setGrpdec(produit.getGroupdec());   



             if(produit.getDoc()==null){



                 produit.setDoc((Document_T)parent.getServeur().ChargeObject(0, parent.getCurrentUser().getUrcleunik(), fournisseur, 1, parent.getServeur().COMBO_FOURNDOC));                 



             }



                }

                catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {



                    ErreurScreenLibrary.displayErreur(parentPane,  ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);



                }

                catch (java.rmi.RemoteException re) {



                    ErreurScreenLibrary.displayErreur(parentPane,  ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);



                }



               



    }

     public static void  makeProduitDefinition(astrainterface parent, produit_T produit,int typeProduct,int fournisseur,int urcleunik){
         try{         



             if(produit.getGroupdec()==null){
               GrpGroupDecRmiInterface grprmi=parent.renvGrpDecRmiObject(urcleunik);
               Grpdecision_T tmp=grprmi.selectFournisseur(typeProduct,0,urcleunik);
               if(tmp!=null){
                 // Grpdecision_T tmp=(Grpdecision_T)parent.getServeur().ChargeObject(0, parent.getCurrentUser().getUrcleunik(),

                   //                     typeProduct, 1, parent.getServeur().COMBO_FOURGRPDEC);
               produit.setGroupdec(tmp);               
               produit.setFrgtcleunik(tmp.getFrgtcleunik());
                   try{
                      produit.setGroupdecBase((Grpdecision_T)tmp.clone());
                   }catch(CloneNotSupportedException cn){
                       cn.printStackTrace(); 
                   }
               }
                 //  parentPane.setProdutFournAndGpdec((InterfaceProduit)produit);
             }              
                //parent.setMenuProduit(true);
                //parent.getMainframe().setGrpdec(produit.getGroupdec());   
             if(produit.getDoc()==null){



                 produit.setDoc((Document_T)parent.ChargeObject(0, urcleunik, fournisseur, 1, parent.COMBO_FOURNDOC));                 



             }



                } 

                catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {



                    ErreurScreenLibrary.displayErreur(null,  ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);



                }

                catch (java.rmi.RemoteException re) {



                    ErreurScreenLibrary.displayErreur(null,  ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);



                }



               



    }



  /*  private static Object setProdutFournAndGpdec(InterfaceProduit tmp)



    {



        tmp.setFrcleunik(grp_LSelect_Fournisseur.getCleUnik());



        tmp.setFrnom(grp_LSelect_Fournisseur.getText());



        tmp.setFrgtcleunik(grp_LSelect_GrpProduits.getCleUnik());



        tmp.setGroupe_produit_nom(grp_LSelect_GrpProduits.getText());



        //tmp.setTypeDeProduitCleunik(grp_LSelect_TypeProduits.getCleUnik());



        tmp.setTypeDeProduitNom(grp_LSelect_TypeProduits.getText());    



        return tmp;



    }*/



    



}



