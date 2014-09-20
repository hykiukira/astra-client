/*







 * PrintingPreview.java







 *







 * Created on 19 février 2003, 14:35







 */















package srcastra.astra.gui.modules.printing;



import srcastra.astra.sys.classetransfert.dossier.Dossier_T;



import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;



import com.java4less.rreport.*;



import srcastra.astra.gui.components.actions.actionToolBar.*;



import srcastra.astra.gui.modules.aidedesicion.decision;



import srcastra.test.*;



import srcastra.astra.sys.classetransfert.dossier.produit_T;



import srcastra.astra.sys.rmi.*;



import srcastra.astra.sys.classetransfert.dossier.*;



import java.util.ArrayList;



import srcastra.astra.sys.classetransfert.*;



import srcastra.astra.gui.modules.printing.classelangueuser.*;



import srcastra.astra.gui.modules.config.ConfigMediator;



import srcastra.astra.gui.modules.printing.header.*;



import srcastra.astra.sys.Logger;















/**







 *







 * @author  Thomas







 */







public class PrintingPreview2 extends javax.swing.JInternalFrame {







    







    /** Creates new form PrintingPreview */



    AbstractHeader header;



    AbstractReport report;



    RArea footer;



    RArea reportheader;



    RArea reportfooter;



    RArea reportdetail;



    Dossier_T m_dossier;



    DossierMainScreenModule m_parent;



    java.awt.Frame m_maman;



    DossierRmiInterface dossierInterface;



    srcastra.astra.sys.classetransfert.dossier.Dossier_T dossier;



    Entite_T entite;



    GeneralePrinting genprint;



    ServeurConnect kiss;



    Loginusers_T user;



    RArea area;



    ConfigMediator mediator;



    RReportJWindow Win;



    astrainterface serveur;



    boolean preview;
    
    String savepdf="";



    public PrintingPreview2(java.awt.Frame maman) {



        //initComponents();



        kiss=new ServeurConnect();



        kiss.connectServeur();



        user=kiss.tmpgestion2;



        genprint=new GeneralePrinting();



        try{



            dossierInterface=kiss.serveur2.renvDossierRmiObject(kiss.tmpgestion2.getUrcleunik());



            ArrayList tmp=null;//=dossierInterface.chargeDossier(kiss.tmpgestion2,147);



            dossier=(Dossier_T)tmp.get(0);      



            entite=(Entite_T)dossierInterface.getAgenceInfo(kiss.tmpgestion2.getUrcleunik()); 



            genprint.setClientLmcleunik(kiss.tmpgestion2.getUrlmcleunik());



            genprint.setCxusecleunik(dossier.getClientContractuel().getCxcleunik());



            genprint.setCxagencecleunik(entite.getCxcleunik());



            genprint.setClientCxCleunik(dossier.getClientContractuel().getCxcleunik());



            genprint=dossierInterface.getLocaliteInfo(genprint,kiss.tmpgestion2.getUrcleunik());



        }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){



          se.printStackTrace();   



        }



        catch(java.rmi.RemoteException rn){



          rn.printStackTrace();   



        }



        requestFocus(); 



      //  m_parent=parent;



        m_maman=maman;



        initComponents();



        



        //exampleOrder2 f=new exampleOrder2();



	//	f.setVisible(true);







       /* try{







           // kiss.serveur2.







        }catch(RemoteException rn){







            rn.printStackTrace();



            



        }*/



	//init();	



    }



    public com.java4less.rreport.RReportJ2 getUi(){



        return report;



        



    }



    public void initPart(){



      // header=new ReportHeader1(user,entite,dossier,genprint); 



    }



     public PrintingPreview2(java.awt.Frame maman, DossierRmiInterface dossierInterface, int numdossier, Loginusers_T user, ConfigMediator mediator,astrainterface serveur) {



        this.serveur=serveur;



        initComponents();



        if(mediator!=null){



            mediator.registerPrintingFrame(this);



            this.mediator=mediator;



        this.dossierInterface=dossierInterface;



        }



        this.user=user;



        genprint=new GeneralePrinting();



        try{



            //dossierInterface=kiss.serveur2.renvDossierRmiObject(kiss.tmpgestion2.getUrcleunik());



            ArrayList tmp=null;//="";//=dossierInterface.chargeDossier(user,191);//162



            dossier=(Dossier_T)tmp.get(0);      



            entite=(Entite_T)dossierInterface.getAgenceInfo(user.getUrcleunik()); 



            genprint.setClientLmcleunik(user.getUrlmcleunik());



            genprint.setCxusecleunik(dossier.getClientContractuel().getCxcleunik());



            genprint.setCxagencecleunik(entite.getCxcleunik());



            genprint=dossierInterface.getLocaliteInfo(genprint,user.getUrcleunik());



        }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){



          se.printStackTrace();   



        }



        catch(java.rmi.RemoteException rn){



          rn.printStackTrace();   



        }



        requestFocus(); 



        m_maman=maman;



       // initPart();



	//init();	



    }



      public PrintingPreview2(java.awt.Frame maman, DossierRmiInterface dossierInterface, Dossier_T dossier, Loginusers_T user, ConfigMediator mediator,astrainterface serveur,int facture,boolean preview) {



        this.serveur=serveur;



        this.preview=preview;



        initComponents();



        //if(preview){



        if(mediator!=null){



            mediator.registerPrintingFrame(this);



            this.mediator=mediator;



            this.dossierInterface=dossierInterface;



        }



        //}



        try{



        this.user=(Loginusers_T)user.clone();



        }catch(CloneNotSupportedException cn){



         cn.printStackTrace();   



        }



        genprint=new GeneralePrinting();



        try{



            //dossierInterface=kiss.serveur2.renvDossierRmiObject(kiss.tmpgestion2.getUrcleunik());



            //ArrayList tmp=dossierInterface.chargeDossier(user,169);//162



            this.dossier=dossier;    



            this.user.setUrlmcleunik(dossier.getClientContractuel().getLecleunik());



            this.user.resetLangage();



            entite=(Entite_T)dossierInterface.getAgenceInfo(user.getUrcleunik()); 



            genprint.setClientLmcleunik(user.getUrlmcleunik());



            genprint.setCxusecleunik(dossier.getClientContractuel().getCxcleunik());



            genprint.setCxagencecleunik(entite.getCxcleunik());



            genprint=dossierInterface.getLocaliteInfo(genprint,user.getUrcleunik());



        }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se){



          se.printStackTrace();   



        }



        catch(java.rmi.RemoteException rn){



          rn.printStackTrace();   



        }



        requestFocus(); 



        m_maman=maman;



       // initPart();



	//init( facture,preview);	

//init( facture,preview);	
init( facture,preview);	
        



    }



      public void my_windowOpened(java.awt.event.WindowEvent evt){



          if(mediator!=null)



            mediator.setPrintingBdcommandeOpened(true);



       }



      public void my_windowClosed(java.awt.event.WindowEvent evt){



            if(mediator!=null)



            mediator.setPrintingBdcommandeOpened(false);



       }



     java.awt.event.WindowListener winlistener=new java.awt.event.WindowListener(){



       public void windowOpened(java.awt.event.WindowEvent evt){



            my_windowOpened(evt);



       }



        public void windowClosing(java.awt.event.WindowEvent evt){



           



       }



        public void windowClosed(java.awt.event.WindowEvent evt){



           my_windowClosed( evt);



       }



        public void windowIconified(java.awt.event.WindowEvent evt){



           



       }



         public void windowDeiconified(java.awt.event.WindowEvent evt){



           



       }



           public void windowActivated(java.awt.event.WindowEvent evt){



           



       }



         public void windowDeactivated(java.awt.event.WindowEvent evt){



           



       }



     };



    public void init(int facture,boolean preview) {	



 // load report from file



                report=new Report1(m_maman,user,entite,dossier,genprint,dossierInterface,user.getUrcleunik(),serveur,winlistener,facture,preview,mediator,this); 
                //report.setPDFFile("test.pdf");
                
                

                



		



 }    



    public void finaliser(boolean pdf)
    { 
                
                if (savepdf.equals(""))
                    savepdf=report.getPDFFile();
                
                
                if(!pdf)
                {
                    savepdf=report.getPDFFile();
                    
                    if(savepdf.indexOf("_origin") != -1)
                        savepdf=savepdf;
                    else
                    {
                        String temp=savepdf;
                        
                        temp=temp.replaceAll(".pdf","");
                        
                        temp=temp+"_origin.pdf";
                        
                        
                        savepdf=temp;
                    }    
                    
                    report.setPDFFile(savepdf);
                    //report.setPDFFile("");
                    
                }
                else
                report.setPDFFile(savepdf);
                    
                report.prepare();         



                report.prepareArea();	 



                report.endReport();



                report.hide();



                report.show();    



    }
    
    public void finaliser()
    { 
                
                    
                report.prepare();         



                report.prepareArea();	 



                report.endReport();



                report.hide();



                report.show();    



    }

    

    public void finaliser2(){



    

                report.prepare();

                report.printArea(header);

                report.show();              

    }



    /** This method is called from within the constructor to



     * initialize the form.



     * WARNING: Do NOT modify this code. The content of this method is



     * always regenerated by the Form Editor.



     */



    



    private void addUserToArea(RArea area){



     /*   double y=1.007d;



        GeneralePrinting tmpgen=new GeneralePrinting();



        if(dossier.getPassager()!=null)      {



            area.getItemByName("nb_voy").setdefaultValue(new Integer(dossier.getPassager().size()).toString());



        for(int i=0;i<dossier.getPassager().size();i++){              



                Passager_T tmppass=(Passager_T)dossier.getPassager().get(i);



                tmpgen.setClientLmcleunik(1);



                tmpgen.setCxusecleunik(tmppass.getCscleunik());



                tmpgen.setTitrecleunik(tmppass.getTscleunik());



                tmpgen.setNatcleunik(tmppass.getPr_nat());



                try{



               // tmpgen=dossierInterface.getPassagerInfo(tmpgen,user.getUrcleunik());



                }



                catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn){



                 sn.printStackTrace();   



                }



                catch(java.rmi.RemoteException rn){



                 rn.printStackTrace();   



                }



                RField titre=new RField();



                titre.name="titre"+i;



                titre.setdefaultValue(tmpgen.getUser().getTitre());



                titre.x=0.0d;



                titre.y=y;



                area.add(titre);



                RField nom=new RField();



                nom.name="nom"+i;



                nom.setdefaultValue(tmppass.getPr_nom());



                nom.x=1.393d;



                nom.y=y;



                area.add(nom);



                RField adresse=new RField();



                adresse.name="adresse"+i;



                adresse.setdefaultValue(tmppass.getPr_adrese());



                adresse.x=3.107d;



                adresse.y=y;



                area.add(adresse);



                RField cp=new RField();



                cp.name="cp"+i;



                cp.setdefaultValue(tmpgen.getUser().getCp());



                cp.x=10.964d;



                cp.y=y;



                area.add(cp);



                RField loc=new RField();



                loc.name="loc"+i;



                loc.setdefaultValue(tmpgen.getUser().getLocalité());



                loc.x=12.143d;



                loc.y=y;



                area.add(loc);



                RField date=new RField();



                date.name="date"+i;



                date.setdefaultValue(tmppass.getPr_datenaissance().toString2());



                date.x=14.643d;



                date.y=y;



                area.add(date);



                RField nat=new RField();



                nat.name="nat"+i;



                nat.setdefaultValue(tmpgen.getUser().getNationalité());



                nat.x=16.857d;



                nat.y=y;



                area.add(nat);



                y=y+400;



        }



              



        }  */



    }



    private void initComponents() {//GEN-BEGIN:initComponents

        setBorder(new javax.swing.border.TitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 0, 11)));
        pack();
    }//GEN-END:initComponents



    /** Getter for property report.

     * @return Value of property report.

     *

     */

    public srcastra.astra.gui.modules.printing.AbstractReport getReport() {

        return report;

    }    



    /** Setter for property report.

     * @param report New value of property report.

     *

     */

    public void setReport(srcastra.astra.gui.modules.printing.AbstractReport report) {

        this.report = report;

    }    





    







    







    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables







    







}







