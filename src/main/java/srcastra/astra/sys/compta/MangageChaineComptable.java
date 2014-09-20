/*







 * MangageChaineComptable.java







 *







 * Created on 11 février 2003, 9:25







 */















package srcastra.astra.sys.compta;







import srcastra.astra.sys.classetransfert.dossier.*;







import java.util.*;







import srcastra.astra.sys.compta.command.*;



import srcastra.astra.sys.rmi.*;



import srcastra.astra.sys.classetransfert.*;



import srcastra.astra.sys.classetransfert.utils.*;



import srcastra.astra.sys.rmi.*;



import srcastra.astra.gui.modules.compta.achat.*;



/**



 *



 * @author  Thomas



 */



public class MangageChaineComptable {



    /** Creates a new instance of MangageChaineComptable */



    astrainterface serveur;



    astraimplement serveur2;



    Loginusers_T user;



    InfoCompta infocompta;



    int urcleunik;



    AchatChain achat;



    public MangageChaineComptable(srcastra.astra.sys.compta.Configuration config,astraimplement serveur,int urcleunik) {



       // config.setM_urcleunik(urcleunik);



        this.urcleunik=urcleunik;



        this.serveur2=serveur;



        nc=new NoteCreditChain(config);



        Payement=new PayementChain(config);



        Base=new BaseChain(config);



        supreduc=new SupReducChain(config);



        Dossier=new DossierChain(config);



        CheckS=new CheckSumChain(config);



        tva=new TvaChain(config);



        achat=new AchatChain(config);



        nc.addChain(Payement);



        Payement.addChain(Base);  



        Base.addChain(supreduc);



        supreduc.addChain(Dossier);



        Dossier.addChain(tva);



        tva.addChain(achat);



        //CheckS.addChain(tva);



        //CheckS.addChain(achat);



     /*   try{

            ParamComptableInterface paraminterface=config.getM_serveur().renvParamCompta(urcleunik);

            infocompta=paraminterface.getInfoCompta(urcleunik,CalculDate.getTodayDate());



            config.setInfo(infocompta);



        }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){



          se.printStackTrace();   



        }



        



        catch(java.rmi.RemoteException rn){



          rn.printStackTrace();   



        }*/



    }



    public String NC(long cledossier)throws java.sql.SQLException,java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure{



     tmp2=new CheckSum_T();



    // compta=new srcastra.astra.sys.compta.command.CheskSumCompta("",null,(Configuration)CheckS.getConfig());



    // compta.setCheck(tmp2);



     //compta.first=true; 



     //nc.sendToChain("modif",compta);



     nc.sendToChain("insert",new srcastra.astra.sys.compta.command.NcCompta(cledossier,(Configuration)nc.getConfig()));  



     return ((Configuration)nc.getConfig()).getNumnc();



    }

    public String NCService(long cledossier)throws java.sql.SQLException,java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure{



     tmp2=new CheckSum_T();



    // compta=new srcastra.astra.sys.compta.command.CheskSumCompta("",null,(Configuration)CheckS.getConfig());



    // compta.setCheck(tmp2);



     //compta.first=true; 



     //nc.sendToChain("modif",compta);



     nc.sendToChain("insertNCService",new srcastra.astra.sys.compta.command.NcCompta(cledossier,(Configuration)nc.getConfig()));  



     return ((Configuration)nc.getConfig()).getNumnc();



    }


    public String insertAchat(Achat_T achat) throws java.sql.SQLException,java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure{



     //   new ManageCompte(serveur2).achatCompte(achat,urcleunik);



        nc.sendToChain("insert",new srcastra.astra.sys.compta.command.AchatCompta(achat,(Configuration)nc.getConfig()));



        return ((Configuration)nc.getConfig()).getNumnc();



    }



    public String insertPayement(Payement_T pay) throws java.sql.SQLException,java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure{



     //   new ManageCompte(serveur2).achatCompte(achat,urcleunik); 



        nc.sendToChain("insert",pay);



        return ((Configuration)nc.getConfig()).getNumnc();



    }



    public Object genereOperationComptable(Dossier_T dossier) throws java.sql.SQLException,java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure{



      Object ret=null;



 //     new ManageCompte(serveur2).DossierCompte(dossier,urcleunik);



      if(dossier.isNewreccord()){      



        ret=newdossier(dossier,true,"insert");      



        serveur2.renvSoldeRmiObject(urcleunik).insert(urcleunik); 



        return ret;



    }



      else if(dossier.isModifreccord()){



          if(dossier.getDr_facture()==1){



                  if(dossier.isComptaModify()){



               /*     tmp2=new CheckSum_T();



                    compta=new srcastra.astra.sys.compta.command.CheskSumCompta("",dossier,(Configuration)CheckS.getConfig());



                    compta.setCheck(tmp2);



                    compta.first=true; 



                    nc.sendToChain("modif",compta);



                    nc.sendToChain("insert",new srcastra.astra.sys.compta.command.NcCompta(dossier,(Configuration)nc.getConfig()));*/

                    ret=newdossier(dossier,false,"insert");

                    try{



                           // String numpiece=serveur.renvParamCompta(urcleunik).checkNumero(urcleunik,ParamComptableInterface.JOURNAL_VENTE);

                            serveur.renvDossierRmiObject(urcleunik).setDossierFacture(urcleunik,dossier.getDrcleunik(),0,dossier.getDrtotalprix());

                            return ret;

                   }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){

                          se.printStackTrace();  

                   }

                   catch(java.rmi.RemoteException rn){



                       rn.printStackTrace();   



                    }



                        return ret;           



            }          



          }



        return  modify(dossier,"modify");



       



      }



          



       /*  if(dossier.isComptaModify()){



            tmp2=new CheckSum_T();



            compta=new srcastra.astra.sys.compta.command.CheskSumCompta("",dossier,(Configuration)CheckS.getConfig());



            compta.setCheck(tmp2);



            compta.first=true; 



            nc.sendToChain("modif",compta);



            nc.sendToChain("insert",new srcastra.astra.sys.compta.command.NcCompta(dossier,(Configuration)nc.getConfig()));



            Object ret=newdossier(dossier,false);



            if(dossier.getDr_facture()==1){



                try{



                    String numpiece=serveur.renvParamCompta(user.getUrcleunik()).checkNumeroFacture(user.getUrcleunik());



                    serveur.renvDossierRmiObject(user.getUrcleunik()).setDossierFacture(user.getUrcleunik(),dossier.getDrcleunik(),Long.parseLong(numpiece));



                }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){



                  se.printStackTrace();  



                }



                catch(java.rmi.RemoteException rn){



                  rn.printStackTrace();   



                }



            }



            return ret;   



         }



         else{          



         }*/



         return null;



    



    }



 /*   private void filltva(InterfaceProduit prod,Hashtable tvalist){



    if(prod!=null){



      if(prod.getGroupdec()!=null){



          if(prod.getGroupdec().getGnfrtvaComptabiliserVente()==1){



              Tva_abrev_T tva=new Tva_abrev_T();



              tva.setTva_id(prod.getGroupdec().getGncodetvavente());



              tva.setTva_rate(prod.getGroupdec().getValeurGenFloat1());



              tva.setTva_base(prod.getValeur_tot());



              tva.setTva_value(prod.getMontant_tva());



              if(tvalist.get(new Integer(tva.getTva_id()))==null){



                tvalist.put(new Integer(tva.getTva_id()),tva);   



              }



              else{



                  Tva_abrev_T tmp=(Tva_abrev_T)tvalist.get(new Integer(tva.getTva_id()));



                  double sum=tmp.getTva_value()+tva.getTva_value();



                  tmp.setTva_value(sum);



              }



        }



      }



    }



    }*/



    private Object newdossier(Dossier_T dossier,boolean newdossier,String command) throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{



          handleChain(dossier,newdossier,command);



          modifyPayement(dossier,newdossier);              



          tmp2=new CheckSum_T();



          return tmp2;



    }



    public Object modify(Dossier_T dossier,String command)throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{



         handleChain(dossier,false,command);



         modifyPayement(dossier,false);



         serveur2.renvSoldeRmiObject(urcleunik).insert(urcleunik);          



         tmp2=new CheckSum_T();



         return tmp2;



    }



    private void handleChain(Dossier_T dossier,boolean newdossier,String command) throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{ 



      Hashtable tvalist=new Hashtable();



      ArrayList tmp=null;



      nc.sendToChain(command,dossier);    



      /*if(dossier.getPayement()!=null){



       *



           java.util.TreeMap treemap=dossier.getPayement();



           java.util.Set set=treemap.keySet();



           java.util.Iterator iterator=set.iterator();



           while(iterator.hasNext())



            {



                Payement_T lpayement=(Payement_T)treemap.get((Long)iterator.next());



                if(lpayement.isNewreccord())



                    nc.sendToChain("insert",lpayement);         



            }



      }*/



          tmp=new ArrayList();



          tmp.add(dossier.getBrochure());



          tmp.add(dossier.getTaxi());



          tmp.add(dossier.getTrain());



          tmp.add(dossier.getVoitureLocation());



          tmp.add(dossier.getAssurance());



          tmp.add(dossier.getAvionTicket());



          tmp.add(dossier.getHotel());



          tmp.add(dossier.getBateau());     



          tmp.add(dossier.getCar()); 



          tmp.add(dossier.getFraisDossier());

          tmp.add(dossier.getDivers());

          for(int i=0;i<tmp.size();i++){



            Hashtable tmpHash=(Hashtable)tmp.get(i);



            if(tmpHash!=null){



                for(java.util.Enumeration enu=tmpHash.keys();enu.hasMoreElements();){



                    produit_T produit=(produit_T)tmpHash.get(enu.nextElement());



                   // if(!produit.isDeleted()){



                        tvaCalcul.parseTva(produit,tvalist);                        



                        nc.sendToChain(command,produit);



                        if(produit.getSup_reduc()!=null){



                            Hashtable hashSupReduc=produit.getSup_reduc();



                             for(java.util.Enumeration enum2=hashSupReduc.keys();enum2.hasMoreElements();){



                                    ((Configuration)supreduc.getConfig()).setCleproduit(produit.getAt_cleunik());



                                     ((Configuration)supreduc.getConfig()).setTypeProduit(produit.getTypeDeProduitCleunik());



                                    Sup_reduc_T supreduc=(Sup_reduc_T)hashSupReduc.get(enum2.nextElement());



                                  //  if(produit.isDeleted())



                                    //    supreduc.isDeleted();



                                  //  if(!supreduc.isDeleted()){                                       



                                        tvaCalcul.parseTva(supreduc,tvalist);



                                        nc.sendToChain(command,supreduc);



                                                               



                        }                      



                    }



                



            }             



          }



      }



          TVACompta tvac=new TVACompta(dossier,(Configuration)tva.getConfig(),tvalist);



          nc.sendToChain(command,tvac);



    }



    private void modifyPayement(Dossier_T dossier,boolean all) throws java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure,java.sql.SQLException{



         if(dossier.getPayement()!=null){



           java.util.TreeMap treemap=dossier.getPayement();



           java.util.Set set=treemap.keySet();



           java.util.Iterator iterator=set.iterator();



           while(iterator.hasNext())



            {



                Payement_T lpayement=(Payement_T)treemap.get((Long)iterator.next());



                if(all){



                    nc.sendToChain("insert",lpayement);



                }else{



                    if(lpayement.isNewreccord())



                         nc.sendToChain("insert",lpayement);



                }



            }



      }            



    }



    /** Getter for property serveur.



     * @return Value of property serveur.



     */



    public astrainterface getServeur() {



        return serveur;



    }



    



    /** Setter for property serveur.



     * @param serveur New value of property serveur.



     */



    public void setServeur(astrainterface serveur) {



        this.serveur = serveur;



    }



    



    /** Getter for property user.



     * @return Value of property user.



     */



    public Loginusers_T getUser() {



        return user;



    }



    



    /** Setter for property user.



     * @param user New value of property user.



     */



    public void setUser(Loginusers_T user) {



        this.user = user;



    }



    



    Chain nc;



    Chain Payement;



    Chain Base;



    Chain supreduc;



    Chain Dossier;



    Chain CheckS;



    Chain tva;



    Chain frais;



    CheckSum_T tmp2;



    srcastra.astra.sys.compta.command.CheskSumCompta compta;



}







