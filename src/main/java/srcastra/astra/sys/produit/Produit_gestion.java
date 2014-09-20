/*















 * Assurance_gestion.java















 *















 * Created on 18 novembre 2002, 16:31















 */































package srcastra.astra.sys.produit;















import srcastra.astra.sys.classetransfert.dossier.avion.*;



import srcastra.astra.sys.rmi.utils.*;















import srcastra.astra.sys.classetransfert.dossier.*;















import java.sql.*;















import java.util.ArrayList;















import java.util.Hashtable;















import java.util.Enumeration;















import srcastra.astra.sys.rmi.*;















import srcastra.astra.sys.classetransfert.utils.Date;















import srcastra.astra.sys.classetransfert.*;















import srcastra.astra.sys.rmi.astrainterface;















import srcastra.astra.sys.compta.GenereLigneComptable;















import srcastra.astra.sys.Transaction;















import srcastra.astra.sys.classetransfert.dossier.*;

import srcastra.astra.sys.classetransfert.dossier.assurance.Assurance_T;

import srcastra.astra.sys.classetransfert.dossier.hotel.Hotel_T;

import srcastra.astra.sys.classetransfert.dossier.bateau.Bateau_T;

import srcastra.astra.sys.classetransfert.dossier.voitureLocation.VoitureLocation_T;

import srcastra.astra.sys.classetransfert.dossier.train.Train_T;

import srcastra.astra.sys.classetransfert.dossier.taxi.Taxi_T;

import srcastra.astra.sys.classetransfert.dossier.car.*;

import srcastra.astra.sys.classetransfert.utils.*;

import srcastra.astra.sys.rmi.groupe_dec.*;

import srcastra.astra.sys.classetransfert.dossier.brochure.*;

import srcastra.astra.sys.classetransfert.dossier.divers.*;













/**















/**















 *















 * @author  Thomas















 */















public class Produit_gestion implements DossierSql{

String numsesscrea;

String numsessmodif;















    















    /** Creates a new instance of Assurance_gestion */







   // GrpProduitGestion prodgest;







    public Produit_gestion(astrainterface serveur) { 



      



       // prodgest=new GrpProduitGestion();







    }















    















   















     public static void InsertProduit(Dossier_T dossier,Connection con,double cledossier,PreparedStatement pstmt,Poolconnection tmpool,astrainterface m_serveur )throws SQLException{    

      checkHastableInsert(dossier.getAssurance(),dossier,con,cledossier,pstmt,tmpool, m_serveur);

      checkHastableInsert(dossier.getHotel(),dossier,con,cledossier,pstmt,tmpool, m_serveur);

      checkHastableInsert(dossier.getBateau(),dossier,con,cledossier,pstmt,tmpool, m_serveur);

      checkHastableInsert(dossier.getVoitureLocation(),dossier,con,cledossier,pstmt,tmpool, m_serveur);

      checkHastableInsert(dossier.getTrain(),dossier,con,cledossier,pstmt,tmpool, m_serveur);

      checkHastableInsert(dossier.getTaxi(),dossier,con,cledossier,pstmt,tmpool, m_serveur);

      checkHastableInsert(dossier.getCar(),dossier,con,cledossier,pstmt,tmpool, m_serveur);

      checkHastableInsert(dossier.getFraisDossier(),dossier,con,cledossier,pstmt,tmpool, m_serveur);

      checkHastableInsert(dossier.getBrochure(),dossier,con,cledossier,pstmt,tmpool, m_serveur);

      checkHastableInsert(dossier.getAvionTicket(),dossier,con,cledossier,pstmt,tmpool, m_serveur);

      checkHastableInsert(dossier.getDivers(),dossier,con,cledossier,pstmt,tmpool, m_serveur);

     }















     public static void chargeProduit(Loginusers_T currentuser,astrainterface serveur,Dossier_T dossier,Connection con,double cledossier,PreparedStatement pstmt)throws SQLException,java.rmi.RemoteException{ 

        Hashtable tableprod=produit_T.chargeDossierProduit(new Integer(dossier.getDrcleunik()).longValue(),con);

        if(tableprod.get(""+produit_T.AS)!=null){

            InterfaceIndivProduit assurance=new Assurance_T();

            assurance.chargeMe(currentuser,serveur,dossier,con,cledossier,pstmt);

        }

        if(tableprod.get(""+produit_T.HO)!=null){

            InterfaceIndivProduit hotel=new Hotel_T();

            hotel.chargeMe(currentuser,serveur,dossier,con,cledossier,pstmt);

        }

        if(tableprod.get(""+produit_T.BA)!=null){

            InterfaceIndivProduit bateau=new Bateau_T();

            bateau.chargeMe(currentuser,serveur,dossier,con,cledossier,pstmt);

        }

        if(tableprod.get(""+produit_T.VO)!=null){



            InterfaceIndivProduit voiture=new VoitureLocation_T();



            voiture.chargeMe(currentuser,serveur,dossier,con,cledossier,pstmt);



        }

        if(tableprod.get(""+produit_T.TR)!=null){

            InterfaceIndivProduit train=new Train_T();

            train.chargeMe(currentuser,serveur,dossier,con,cledossier,pstmt);

        }

        if(tableprod.get(""+produit_T.TAX)!=null){

            InterfaceIndivProduit taxi=new Taxi_T();

            taxi.chargeMe(currentuser,serveur,dossier,con,cledossier,pstmt);

        }

        if(tableprod.get(""+produit_T.CAR)!=null){

          InterfaceIndivProduit car=new Car_T();

           car.chargeMe(currentuser,serveur,dossier,con,cledossier,pstmt);

        }

       // if(tableprod.get(""+produit_T.FRAIS)!=null){

            InterfaceIndivProduit frais=new Frais_T();

            frais.chargeMe(currentuser,serveur,dossier,con,cledossier,pstmt);



       // }



        if(tableprod.get(""+produit_T.BRO)!=null){

            InterfaceIndivProduit brochure=new Brochure_T();

            brochure.chargeMe(currentuser,serveur,dossier,con,cledossier,pstmt);

        }

         if(tableprod.get(""+produit_T.AV)!=null){

            InterfaceIndivProduit avion=new Avion_ticket_T();

            avion.chargeMe(currentuser,serveur,dossier,con,cledossier,pstmt);

        }

        if(tableprod.get(""+produit_T.DIV)!=null){

            InterfaceIndivProduit diver=new Divers_T();

            diver.chargeMe(currentuser,serveur,dossier,con,cledossier,pstmt);

        }

     } 















   /*  private static void chargeFournisseurDocument(PreparedStatement pstmt,Connection con,InterfaceIndivProduit prod)throws SQLException{















         String sqlrequete="SELECT frdtdatetimecrea,frdtdatetimemodif,frdtnbrdocprev,frdtnbrconfprev,frdtnbrfactprev,frdtnbrncprev,frdtnbrfactsprev,snumerosessioncrea,snumerosessionmodif from fournisseur_document WHERE frcleunik=?";















         pstmt=con.prepareStatement(sqlrequete);















         pstmt.setInt(1,prod.getFrcleunik());















         ResultSet result=pstmt.executeQuery();















         Document_T doc=null;















         while (result.next()){















             doc=new Document_T();















             doc.setFrdtnbrdocprev(result.getInt(3));















             doc.setFrdtnbrconfprev(result.getInt(4));















             doc.setFrdtnbrfactprev(result.getInt(5));















             doc.setFrdtnbrcprev(result.getInt(6));















             doc.setFrdtnbrfactsprev(result.getInt(7));            















         }















         if(doc!=null)















            prod.setDoc(doc);        















     }*/















     private static void checkHastableInsert(Hashtable produitTable,Dossier_T dossier,Connection con,double cledossier,PreparedStatement pstmt,Poolconnection tmpool,astrainterface m_serveur )throws SQLException{

         if(produitTable!=null){

              for(Enumeration enu=produitTable.keys();enu.hasMoreElements();)

                  {

                    InterfaceIndivProduit produit= (InterfaceIndivProduit)produitTable.get(enu.nextElement());

                    produit.insertOnlyme(con,cledossier,pstmt);      

                    produit.getGroupdec().setSnumerosessioncrea(tmpool.getUrnumerosession());

                    produit.getGroupdec().setSnumerosessionmodif(tmpool.getUrnumerosession());

                    GrpProduitGestion.grpdecinsert((produit_T)produit,con,tmpool.getUrcleunik(),m_serveur);

                    produit_T produitt=(produit_T)produit;                  

                       if(produit.getTypeDeProduitCleunik()!=produit_T.FRAIS){                    

                        produit.insertDescriptif(con,cledossier,pstmt);      

                        produitt.insertDossierProduit(new Integer(dossier.getDrcleunik()).longValue(),con);

                        manageDoc.gereDocument(produitt,con,pstmt);

                        SupplementReduction.insertSupreduc(produitt,con,pstmt,cledossier,dossier.getClientContractuel().getCscleunik(),tmpool.getUrcleunik(),m_serveur);

                    }

                  }            

         }        

     }

      private static void checkHastableModif(Hashtable produitTable,Dossier_T dossier,Connection con,PreparedStatement pstmt,astrainterface serveur,Loginusers_T currentuser,Poolconnection tmpool )throws SQLException,java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure{

          Hashtable integrated=null;

          Hashtable inverseintegrated=null;

          if(produitTable!=null){

                  double cledossier=new Double(dossier.getDrcleunik()).doubleValue();

                  for(Enumeration enu=produitTable.keys();enu.hasMoreElements();)

                      {

                           InterfaceIndivProduit produit= (InterfaceIndivProduit)produitTable.get(enu.nextElement());

                           produit_T produitt=(produit_T) produit;

                           if(produit.isDeleted())

                            {   

                                produit.annulMe(con,pstmt);

                                ((produit_T)produit).annulerDossierProduit(new Integer(dossier.getDrcleunik()).longValue(),con);

                                if(((produit_T)produit).getTypeDeProduitCleunik()==produit_T.AV){

                                        Avion_ticket_T ticket=(Avion_ticket_T)produit;

                                if(ticket.getEtatAnnulation()==ticket.CANCEL){

                                            if(inverseintegrated==null)

                                                inverseintegrated=new Hashtable();

                                                inverseintegrated.put(new Long(ticket.getTion_cleunik()),"");

                                                ticket.setTion_cleunik(0);                                                                                          

                                            

                                        }

                                }

                                

                            }

                           else{

                               if(produit.isIsnewreccord()){

                                    produit.insertOnlyme(con,dossier.getDrcleunik(),pstmt);  

                                    if(((produit_T)produit).getTypeDeProduitCleunik()==produit_T.AV){

                                        Avion_ticket_T ticket=(Avion_ticket_T)produit;

                                        if(ticket.getTion_cleunik()!=0){

                                            if(integrated==null)

                                                integrated=new Hashtable();

                                            integrated.put(new Long(ticket.getTion_cleunik()),"");                                        

                                        }

                                    }



                                        GrpProduitGestion.grpdecinsert(produitt,con,tmpool.getUrcleunik(),serveur);

                                        if(produitt.getTypeDeProduitCleunik()!=produit_T.FRAIS){

                                            ((produit_T)produit).insertDossierProduit(new Integer(dossier.getDrcleunik()).longValue(),con);

                                            produit.insertDescriptif(con,cledossier,pstmt);   

                                            manageDoc.gereDocument(produitt,con,pstmt);

                                            SupplementReduction.insertSupreduc(produitt,con,pstmt,dossier.getDrcleunik(),dossier.getClientContractuel().getCscleunik(),tmpool.getUrcleunik(),serveur);

                                    }

                                   } 



                               else if(produit.isModify())



                                   {



                                    // double cledossier=new Double(dossier.getDrcleunik()).doubleValue();



                                   

                                      if(((produit_T)produit).getTypeDeProduitCleunik()==produit_T.AV){

                                        Avion_ticket_T ticket=(Avion_ticket_T)produit;

                                        if((ticket.getEtatAnnulation()==ticket.CANCEL || ticket.getEtatAnnulation()==ticket.VOID) && ticket.isCancel()){

                                          //annulation de l'od comptable pour les cccf

                                         ((ParamComptable)serveur.renvParamCompta(tmpool.getUrcleunik())).inverseODcccf(tmpool.getUrcleunik(), ticket.getAt_cleunik());

                                        

                                        }                    

                                    }

                                      produit.modifyOnlyMe(con,dossier.getDrcleunik(),pstmt); 

                                     if(produitt.getTypeDeProduitCleunik()!=produit_T.FRAIS){

                                        ((produit_T)produit).modifyDossierProduit(new Integer(dossier.getDrcleunik()).longValue(),con);

                                        manageDoc.gereDocument(produitt,con,pstmt);

                                        GrpProduitGestion.grpdecinsert(produitt,con,tmpool.getUrcleunik(),serveur);

                                        GrpProduitGestion.grpdecModify(produitt,con,tmpool.getUrcleunik(),serveur);

                                        produit.modifyDescriptif(con,pstmt);

                                        SupplementReduction.modifySupreduc(produitt,con,pstmt,dossier.getDrcleunik(),dossier.getClientContractuel().getCscleunik(),tmpool.getUrcleunik(),serveur);

                                     }



                                   }



                               if(produitt.getSup_reduc()!=null)



                               {



                                 for(Enumeration enum4=produitt.getSup_reduc().keys();enum4.hasMoreElements();){



                                     Sup_reduc_T supreduc=(Sup_reduc_T)produitt.getSup_reduc().get(enum4.nextElement());



                                     if(!supreduc.isDeleted()){



                                           float tva=supreduc.getSoumis_tva()==1 ? produitt.getGroupdec().getValeurGenFloat1():0;



                                           int clecompte=0;



                                           int categorieCompte=0;



                                           clecompte=produitt.getGroupdec().getGncomptevente();



                                           categorieCompte=produitt.getGroupdec().getCategoriecomptev();



                                           boolean sw;



                                           if(supreduc.getSup()==1){



                                                 sw=true;



                                              }



                                           else if(supreduc.getReduc()==1){



                                                sw=false;



                                            } 



                                     }



                                     else{



                                         SupplementReduction.annulSupreducLocale(supreduc,con,pstmt,null);                                      



                                     }



                                 }



                               }



                           } 



                      }



                  if(integrated!=null)

                      ((DossierImplement)serveur.renvDossierRmiObject(currentuser.getUrcleunik())).setTicketIntegrated(integrated,tmpool,1);

                  if(inverseintegrated!=null)

                      ((DossierImplement)serveur.renvDossierRmiObject(currentuser.getUrcleunik())).setTicketIntegrated(inverseintegrated,tmpool,0);







         }        



     }















       private static void insertGroupeDec(long lignecleunik,int categoriecleunik,Grpdecision_T grpd,Connection con){















            String sqlrequete;















            Gestionerreur_T tmpret=null;















            try{















                sqlrequete="insert into groupedecisionproduit(frcleunik,ttcleunik,itcleunik,aecleunik,gndatetimecrea,gndatetimemodif,gncodetvavente,"+















                "gntvainclusvente,gncodetvaachat,gntvainclusachat,gnnbravanteche,gnacompteminpp,gnprodstockiata,gnprodstockautre,gnpccomvente,gncominclpvent,gnpcacompte,gnpccomachat,gnpcclerepvend,gnpcclerepconcept,gnpcclerepmmere,snumerosessioncrea,snumerosessionmodif,frgtcleunik,gncomptevente,gncompteachat,gnfracomptepc,gnfrnbda,gnfrsoldepc,gnfrnbds,gnfranchise,caecleunik,tecleunik,lignecleunik,ctpro_cleunik ) values("+0+















                ","+grpd.getTtcleunik()+","+grpd.getItcleunik()+","+















                grpd.getAecleunik()+",NOW(),NOW(),"+grpd.getGncodetvavente()+","+grpd.getGntvainclusvente()+","+grpd.getGncodetvaachat()+","+grpd.getGntvainclusachat()+","+grpd.getGnnbravanteche()+","+grpd.getGnacompteminpp()+","+grpd.getGnprodstockiata()+","+grpd.getGnprodstockautre()+","+grpd.getGnpccomvente()+","+grpd.getGncominclpvent()+","+grpd.getGnpcacompte()+","+grpd.getGnpccomachat()+","+grpd.getGnpcclerepvend()+","+grpd.getGnpcclerepconcept()+","+grpd.getGnpcclerepmmere()+",' ',' ',"+grpd.getFrgtcleunik()+",'"+grpd.getGncomptevente()+"','"+grpd.getGncompteachat()+"',"+grpd.getGnfracomptepc()+","+grpd.getGnfrnbda()+","+grpd.getGnfrsoldepc()+","+grpd.getGnfrnbds()+","+grpd.getGnfranchise()+","+grpd.getCaecleunik()+","+grpd.getTecleunik()+","+lignecleunik+","+categoriecleunik+");";















                tmpret=Transaction.execrequeteinsert(sqlrequete,con);















            }















            catch(Exception i) {















            }   















        }















     public static void modifyproduct(Dossier_T dossier,Connection con,PreparedStatement pstmt,astrainterface serveur,Loginusers_T currentuser,Poolconnection tmpool )throws SQLException,java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure{

          checkHastableModif(dossier.getAssurance(),dossier,con,pstmt,serveur,currentuser,tmpool); 

          checkHastableModif(dossier.getHotel(),dossier,con,pstmt,serveur,currentuser,tmpool); 

          checkHastableModif(dossier.getBateau(),dossier,con,pstmt,serveur,currentuser,tmpool); 

          checkHastableModif(dossier.getVoitureLocation(),dossier,con,pstmt,serveur,currentuser,tmpool); 

          checkHastableModif(dossier.getTrain(),dossier,con,pstmt,serveur,currentuser,tmpool); 

          checkHastableModif(dossier.getTaxi(),dossier,con,pstmt,serveur,currentuser,tmpool); 

          checkHastableModif(dossier.getCar(),dossier,con,pstmt,serveur,currentuser,tmpool); 

          checkHastableModif(dossier.getFraisDossier(),dossier,con,pstmt,serveur,currentuser,tmpool); 

          checkHastableModif(dossier.getBrochure(),dossier,con,pstmt,serveur,currentuser,tmpool);

          checkHastableModif(dossier.getAvionTicket(),dossier,con,pstmt,serveur,currentuser,tmpool);

          checkHastableModif(dossier.getDivers(),dossier,con,pstmt,serveur,currentuser,tmpool);

}







     /** Getter for property numsesscrea.



      * @return Value of property numsesscrea.



      */



     public java.lang.String getNumsesscrea() {



         return numsesscrea;



     }     







     /** Setter for property numsesscrea.



      * @param numsesscrea New value of property numsesscrea.



      */



     public void setNumsesscrea(java.lang.String numsesscrea) {



         this.numsesscrea = numsesscrea;



     }     







     /** Getter for property numsessmodif.



      * @return Value of property numsessmodif.



      */



     public java.lang.String getNumsessmodif() {



         return numsessmodif;



     }



     



     /** Setter for property numsessmodif.



      * @param numsessmodif New value of property numsessmodif.



      */



     public void setNumsessmodif(java.lang.String numsessmodif) {



         this.numsessmodif = numsessmodif;



     }



     



}