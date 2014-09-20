/*







 * tavaCalcul.java







 *







 * Created on 7 février 2003, 14:30







 */















package srcastra.astra.sys.compta;







import srcastra.astra.sys.classetransfert.dossier.*;



import srcastra.astra.sys.*;



import java.util.*;



import srcastra.astra.sys.configuration.*;
import srcastra.astra.sys.rmi.utils.*;











/**







 *







 * @author  Thomas







 */







public class tvaCalcul {







    







    /** Creates a new instance of tavaCalcul */







    public tvaCalcul() {







    }







    public static  TvaReturnValue TvaReturnValue45(InterfaceProduit prod){
        double tmp1;
        double tmp2;
        double tmp3;
        double tmp4=prod.getPrct();
        double tva=prod.getGroupdec().getValeurGenFloat1();
        prod.setAt_val_vente(MathRound.roundThisToDouble(prod.getAt_val_vente()));
        tmp3=((prod.getAt_val_vente()*prod.getPax()*prod.getQua())/100d)*tmp4;
        tmp3=MathRound.roundThisToDouble(tmp3);
        TvaReturnValue retval=new TvaReturnValue();           
        retval.montantHtva=tmp3;
        tmp1=((retval.montantHtva)/100d)*tva; 
        retval.montant_Tva=srcastra.astra.sys.compta.MathRound.roundThisToDouble(tmp1);
        tmp2=retval.montantHtva+retval.montant_Tva;
        tmp2=srcastra.astra.sys.compta.MathRound.roundThisToDouble(tmp2);
        retval.montant_TvaCompris=tmp2;
        retval.checkMe();
        return retval;          
    }
     public static  TvaReturnValue TvaReturnValue(InterfaceProduit prod){
        double tmp1;
        double tmp2;
        double tmp3;
        double tmp4=prod.getPrct();
        MY_bigDecimal totalProd=getTotalWithoutTva(prod);
        MY_bigDecimal tva=new MY_bigDecimal(new Double(prod.getGroupdec().getValeurGenFloat1()).toString());
        tva=tva.divide(new MY_bigDecimal("100.00"),4);
        TvaReturnValue retval=new TvaReturnValue();           
        retval.montantHtva=totalProd.doubleValue();
        System.out.println("MONTANT TOTAL :"+totalProd.toString());
        System.out.println("%TVA :"+tva.toString());
        MY_bigDecimal montantTva=totalProd.multiply(tva);
        System.out.println("%TVA VALUE :"+montantTva.toString());
        MY_bigDecimal montantTot=totalProd.add(montantTva);
        System.out.println("%MONTANT TVA :"+montantTva.toString());
        retval.montant_Tva=montantTva.doubleValue();
        System.out.println("MONTANT TOTAL : "+montantTot.toString());
        retval.montant_TvaCompris=montantTot.doubleValue();
        retval.checkMe();
        return retval;          
    } public static  TvaReturnValue TvaReturnValueLess(InterfaceProduit prod){
        double tmp1;
        double tmp2;
        double tmp3;
        double tmp4;
        double tmp5=prod.getPrct();
       // double tva=prod.getGroupdec().getValeurGenFloat1();
        MY_bigDecimal totalProd=getTotalWithoutTva(prod);
        MY_bigDecimal tva=new MY_bigDecimal(new Double(prod.getGroupdec().getValeurGenFloat1()).toString());
        tva=tva.divide(new MY_bigDecimal("100.00"),4);
        tva=tva.addWithoutScaling(new MY_bigDecimal("1.00"));
        System.out.println("%TVA :"+tva.toString());
        MY_bigDecimal montantHorsTva=totalProd.divide(tva);
        System.out.println("MONTANT HORS TVA :"+montantHorsTva);
        MY_bigDecimal montantTva=totalProd.subtract(montantHorsTva);
        System.out.println("MONTANT TVA :"+montantTva);
        TvaReturnValue retval=new TvaReturnValue();
        retval.montant_TvaCompris=totalProd.doubleValue();
        retval.montant_Tva=montantTva.doubleValue();
        retval.montantHtva=montantHorsTva.doubleValue();
        //  prod.setAt_val_vente(MathRound.roundThisToDouble(prod.getAt_val_vente()));
            //tva=tva/100d;
           // Logger.getDefaultLogger().log(Logger.LOG_INFOS,"tva avant arrondi"+tva);
            //tva=srcastra.astra.sys.compta.MathRound.roundThisToDouble2(tva);
     /*       Logger.getDefaultLogger().log(Logger.LOG_INFOS,"tva après arrondi"+tva);
            tmp1=1d+tva;         
            Logger.getDefaultLogger().log(Logger.LOG_INFOS,"valeur a divisé"+tmp1);
           
            tmp4=((prod.getAt_val_vente()*prod.getPax()*prod.getQua())/100d)*tmp5;
            Logger.getDefaultLogger().log(Logger.LOG_INFOS,"prct "+tmp5+"  "+"valeu tva compris "+ tmp4);          
            tmp4=srcastra.astra.sys.compta.MathRound.roundThisToDouble(tmp4);
            retval.montant_TvaCompris=tmp4;
            tmp3=tmp4/tmp1;
            Logger.getDefaultLogger().log(Logger.LOG_INFOS,"montant de la tva"+tmp3);
            retval.montantHtva=srcastra.astra.sys.compta.MathRound.roundThisToDouble(tmp3);
            tmp2=retval.montant_TvaCompris-retval.montantHtva;
            tmp2=srcastra.astra.sys.compta.MathRound.roundThisToDouble(tmp2);
            retval.montant_Tva=tmp2;
            retval.checkMe();*/
            return retval;          
    }
      public static MY_bigDecimal getTotalWithoutTva(InterfaceProduit prod){
        MY_bigDecimal totalProd=new MY_bigDecimal("0.00");
        MY_bigDecimal prct=new MY_bigDecimal(new Float(prod.getPrct()).toString());       
        MY_bigDecimal pax=new MY_bigDecimal(new Integer(prod.getPax()).toString());
        MY_bigDecimal qua=new MY_bigDecimal(new Integer(prod.getQua()).toString());
        MY_bigDecimal valvente=new MY_bigDecimal(new Double(prod.getAt_val_vente()).toString()); 
        prod.setAt_val_vente(valvente.doubleValue());
        totalProd=valvente.multiply(pax.multiply(qua));
        totalProd=totalProd.multiply(prct);
        totalProd=totalProd.divide(new MY_bigDecimal("100.00"));
        return totalProd;
      }
      public static  TvaReturnValue TvaReturnValueNoTva(InterfaceProduit prod){
        double tmp1;
        double tmp2;
        double tmp3;
        double tmp4=prod.getPrct();
        double tva=0d;
        MY_bigDecimal totalProd=getTotalWithoutTva(prod);        
        TvaReturnValue retval=new TvaReturnValue();           
        retval.montantHtva=totalProd.doubleValue();
        retval.montant_TvaCompris=totalProd.doubleValue();
        retval.montant_Tva=0d;
        return retval;          
    }



     public static  TvaReturnValue TvaReturnValue2(float tva,double montant){



        TvaReturnValue retval=new TvaReturnValue();       



        float tmp4=(tva+100f)/100f;



        double tmp2=montant/tmp4;



        retval.montantHtva=MathRound.roundThisToDouble(tmp2);



        retval.montant_TvaCompris=montant;



        retval.montant_Tva=retval.montant_TvaCompris-retval.montantHtva;



        return retval;



    }



    public static  TvaReturnValue TvaReturnValue3(float tva,double montant){



        TvaReturnValue retval=new TvaReturnValue();       



        float tmp4=(tva+100f)/100f;



        double tmp2=montant*tmp4;



        retval.montantHtva=montant;



        retval.montant_TvaCompris=MathRound.roundThisToDouble(tmp2);



        retval.montant_Tva=retval.montant_TvaCompris-retval.montantHtva;



        return retval;



    }



   



     public static  TvaReturnValue TvaReturnValue2(InterfaceProduit prod,float tva){



        double tmp1;



        double tmp2;



        double tmp3;



        double tmp4=prod.getPrct();



        prod.setAt_val_vente(MathRound.roundThisToDouble(prod.getAt_val_vente()));



        tmp3=((prod.getAt_val_vente()*prod.getPax()*prod.getQua())/100d)*tmp4;



        tmp3=MathRound.roundThisToDouble(tmp3);



        TvaReturnValue retval=new TvaReturnValue();           



        retval.montantHtva=tmp3;



        tmp1=((retval.montantHtva)/100d)*tva; 



        retval.montant_Tva=srcastra.astra.sys.compta.MathRound.roundThisToDouble(tmp1);



        tmp2=retval.montantHtva+retval.montant_Tva;



        tmp2=srcastra.astra.sys.compta.MathRound.roundThisToDouble(tmp2);



        retval.montant_TvaCompris=tmp2;



        retval.checkMe();



        return retval;          



    }







   /*  public static  TvaReturnValue TvaReturnValueLess(InterfaceProduit prod){
        double tmp1;
        double tmp2;
        double tmp3;
        double tmp4;
        double tmp5=prod.getPrct();
        double tva=prod.getGroupdec().getValeurGenFloat1();
            prod.setAt_val_vente(MathRound.roundThisToDouble(prod.getAt_val_vente()));
            tva=tva/100d;
            Logger.getDefaultLogger().log(Logger.LOG_INFOS,"tva avant arrondi"+tva);
            tva=srcastra.astra.sys.compta.MathRound.roundThisToDouble2(tva);
            Logger.getDefaultLogger().log(Logger.LOG_INFOS,"tva après arrondi"+tva);
            tmp1=1d+tva;         
            Logger.getDefaultLogger().log(Logger.LOG_INFOS,"valeur a divisé"+tmp1);
            TvaReturnValue retval=new TvaReturnValue();
            tmp4=((prod.getAt_val_vente()*prod.getPax()*prod.getQua())/100d)*tmp5;
            Logger.getDefaultLogger().log(Logger.LOG_INFOS,"prct "+tmp5+"  "+"valeu tva compris "+ tmp4);          
            tmp4=srcastra.astra.sys.compta.MathRound.roundThisToDouble(tmp4);
            retval.montant_TvaCompris=tmp4;
            tmp3=tmp4/tmp1;
            Logger.getDefaultLogger().log(Logger.LOG_INFOS,"montant de la tva"+tmp3);
            retval.montantHtva=srcastra.astra.sys.compta.MathRound.roundThisToDouble(tmp3);
            tmp2=retval.montant_TvaCompris-retval.montantHtva;
            tmp2=srcastra.astra.sys.compta.MathRound.roundThisToDouble(tmp2);
            retval.montant_Tva=tmp2;
            retval.checkMe();
            return retval;          
    }*/



   private static void fillHastable(Hashtable tvalist, Tva_abrev_T tva){



       if(tvalist.get(new Integer(tva.getTva_id()))==null){



                        tvalist.put(new Integer(tva.getTva_id()),tva);   



                      }



                      else{



                          Tva_abrev_T tmp=(Tva_abrev_T)tvalist.get(new Integer(tva.getTva_id()));



                          double sumtva=tmp.getTva_value()+tva.getTva_value();



                          double sumbase=tmp.getTva_base()+tva.getTva_base();



                         // double sumtotal=tmp.get+tva.getTva_value();



                          tmp.setTva_value(MathRound.roundThisToDouble(sumtva));



                          tmp.setTva_base(MathRound.roundThisToDouble(sumbase));



                      }



   }



   public static void parseTva(InterfaceProduit prod,Hashtable tvalist){



    if(prod!=null){



      if(prod.getGroupdec()!=null){



          if(!prod.isDeleted()){



          if(prod.getGroupdec().getGnfrtvaComptabiliserVente()==1){



              float test=prod.getGroupdec().getValeurGenFloat1();



              if(prod.getGroupdec().getValeurGenFloat1() != 3.78f && prod.getGroupdec().getValeurGenFloat1() != 2.73f && prod.getGroupdec().getValeurGenFloat1() != 1.68f && prod.getGroupdec().getValeurGenFloat1()!=1.26f) {



                      Tva_abrev_T tva=new Tva_abrev_T();



                      tva.setTva_id(prod.getGroupdec().getGncodetvavente());



                      tva.setTva_rate(prod.getGroupdec().getValeurGenFloat1());



                      tva.setTva_base(MathRound.roundThisToDouble(prod.getValeur_tot()));



                      tva.setTva_value(prod.getMontant_tva());



                      fillHastable(tvalist,tva);



                }



              else{



                  double tmp2=0d;



                  if(prod.getGroupdec().getValeurGenFloat1()==3.78f){



                      tmp2=18d;



                  }



                  else if(prod.getGroupdec().getValeurGenFloat1()==2.73f){



                    tmp2=13d;



                  }



                  else if(prod.getGroupdec().getValeurGenFloat1()==1.68f){



                    tmp2=8d;



                  }



                  else if(prod.getGroupdec().getValeurGenFloat1()==1.26f){



                    tmp2=6d;



                  }



                  Tva_abrev_T base1=new Tva_abrev_T();



                  Tva_abrev_T base2=new Tva_abrev_T();



                  double tmp1=(prod.getValeur_tot()/100d)*tmp2;



                  base1.setTva_id(prod.getGroupdec().getGncodetvavente());



                  base1.setTva_rate(prod.getGroupdec().getValeurGenFloat1());



                  base1.setTva_base(MathRound.roundThisToDouble(tmp1));



                  base1.setTva_value(prod.getMontant_tva());



                  base2.setTva_id(285);



                  base2.setTva_rate(0f);



                  base2.setTva_base(MathRound.roundThisToDouble(prod.getValeur_tot()-tmp1));



                  base2.setTva_value(0d);



                  fillHastable(tvalist,base1);



                  fillHastable(tvalist,base2);



                  



              }



          



          }



          else{



                Tva_abrev_T tva=new Tva_abrev_T();



                tva.setTva_id(285);



                tva.setTva_rate(0f);



                tva.setTva_base(MathRound.roundThisToDouble(prod.getValeur_tot()));



                tva.setTva_value(0);



                fillHastable(tvalist,tva);



                



          }



      }



      }



    }



   }



 



  public static ArrayList addTva(ArrayList cont,ArrayList tvalist){



       tvalist=new ArrayList();



       for(int i=0;i<cont.size();i++){



          boolean sw=false;



          Object[] tab=(Object[])cont.get(i);         



          if(tab[7]!=null)



            if(tvalist.size()>0){



                for(int j=0;j<tvalist.size();j++){    



                    Tva_abrev_T tvatmp=(Tva_abrev_T)tvalist.get(j);



                        if(((Integer)tab[7]).intValue()==tvatmp.getTva_id()){



                            tvatmp.setTva_base(MathRound.roundThisToDouble((tvatmp.getTva_base()+((Double)tab[2]).doubleValue())));



                            tvatmp.setTva_value(MathRound.roundThisToDouble(tvatmp.getTva_value()+((Double)tab[8]).doubleValue()));  



                            sw=true;



                            break;



                        } 



                      



                             



                          }



                  if(!sw){



                             Tva_abrev_T tva=new Tva_abrev_T();



                             tva.setTva_id(((Integer)tab[7]).intValue());



                             tva.setTva_rate(((Float)tab[10]).floatValue());



                             tva.setTva_base(MathRound.roundThisToDouble(((Double)tab[2]).doubleValue()));



                             tva.setTva_value(MathRound.roundThisToDouble(((Double)tab[8]).doubleValue()));



                             tvalist.add(tva);



       }



        sw=false;



       



       }else{



            Tva_abrev_T tva=new Tva_abrev_T();



            tva.setTva_id(((Integer)tab[7]).intValue());



            tva.setTva_rate(((Float)tab[10]).floatValue());



            tva.setTva_base(MathRound.roundThisToDouble(((Double)tab[2]).doubleValue()));



            tva.setTva_value(MathRound.roundThisToDouble(((Double)tab[8]).doubleValue()));



            tvalist.add(tva);



           



       }



       



       }



       return tvalist;



  }



  public void removeTva(ArrayList tvalist,Tva_abrev_T tva){



       if(tvalist==null)



        return;



       for(int i=0;i<tvalist.size();i++){



          Tva_abrev_T tvatmp=(Tva_abrev_T)tvalist.get(i);



          if(tvatmp.getTva_id()==tva.getTva_id()){



              if(tvatmp.getTva_value()==tva.getTva_value()){



                tvalist.remove(tvatmp);   



              }



              else{



                tvatmp.setTva_base(tvatmp.getTva_base()-tva.getTva_base());



                tvatmp.setTva_value(tvatmp.getTva_value()-tva.getTva_value());



              }



          }



       }   



      



      



  }



   /* public static void parseTva2(float tva,Hashtable tvalist){  



      if(prod.getGroupdec()!=null){



              if(tva!= 3.78f && tva!= 2.73f &&tva!= 1.68f &&tva!=1.26f) {



                      Tva_abrev_T tva=new Tva_abrev_T();



                      tva.setTva_id(prod.getGroupdec().getGncodetvavente());



                      tva.setTva_rate(prod.getGroupdec().getValeurGenFloat1());



                      tva.setTva_base(MathRound.roundThisToDouble(prod.getValeur_tot()));



                      tva.setTva_value(prod.getMontant_tva());



                      fillHastable(tvalist,tva);



                }



              else{



                  double tmp2=0d;



                  if(prod.getGroupdec().getValeurGenFloat1()==3.78f){



                      tmp2=18d;



                  }



                  else if(prod.getGroupdec().getValeurGenFloat1()==2.73f){



                    tmp2=13d;



                  }



                  else if(prod.getGroupdec().getValeurGenFloat1()==1.68f){



                    tmp2=8d;



                  }



                  else if(prod.getGroupdec().getValeurGenFloat1()==1.26f){



                    tmp2=6d;



                  }



                  Tva_abrev_T base1=new Tva_abrev_T();



                  Tva_abrev_T base2=new Tva_abrev_T();



                  double tmp1=(prod.getValeur_tot()/100d)*tmp2;



                  base1.setTva_id(prod.getGroupdec().getGncodetvavente());



                  base1.setTva_rate(prod.getGroupdec().getValeurGenFloat1());



                  base1.setTva_base(MathRound.roundThisToDouble(tmp1));



                  base1.setTva_value(prod.getMontant_tva());



                  base2.setTva_id(4);



                  base2.setTva_rate(0f);



                  base2.setTva_base(MathRound.roundThisToDouble(prod.getValeur_tot()-tmp1));



                  base2.setTva_value(0d);



                  fillHastable(tvalist,base1);



                  fillHastable(tvalist,base2);



                  



              }



          



          }



          else{



                Tva_abrev_T tva=new Tva_abrev_T();



                tva.setTva_id(4);



                tva.setTva_rate(0f);



                tva.setTva_base(MathRound.roundThisToDouble(prod.getValeur_tot()));



                tva.setTva_value(0);



                fillHastable(tvalist,tva);



                



          }



      



    /*  else if(prod instanceof produit_T){



        if(((produit_T)prod).getTypeDeProduitCleunik()==produit_T.FRAIS){



              Tva_abrev_T tva=new Tva_abrev_T();



                      tva.setTva_id(((Frais_T)prod).getTva_cleunik());



                      tva.setTva_rate(((Frais_T)prod).getSup_tva());



                      tva.setTva_base(prod.getValeur_tot());



                      tva.setTva_value(prod.getMontant_tva());



                      fillHastable(tvalist,tva);          



        }        



      }



    



    }*/



   



   /** Getter for property mainconfig.



    * @return Value of property mainconfig.



    */



   public MainConfig getMainconfig() {



       return mainconfig;



   }



   



   /** Setter for property mainconfig.



    * @param mainconfig New value of property mainconfig.



    */



   public void setMainconfig(MainConfig mainconfig) {



       this.mainconfig = mainconfig;



   }



   



   MainConfig mainconfig;



}







