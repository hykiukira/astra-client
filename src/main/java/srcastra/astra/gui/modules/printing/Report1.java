/*

 * Report1.java

 *

 * Created on 14 mars 2003, 14:40

 */



package srcastra.astra.gui.modules.printing;

import srcastra.astra.gui.modules.printing.header.*;

import com.java4less.rreport.*;

import srcastra.astra.sys.Logger;

import srcastra.astra.gui.modules.printing.bloquepassager.*;

import srcastra.astra. sys.rmi.DossierRmiInterface;

import srcastra.astra.sys.classetransfert.dossier.*;

import java.util.*;

import srcastra.astra.gui.modules.printing.classelangueuser.*;

import srcastra.astra.gui.sys.ErreurScreenLibrary;

import srcastra.astra.gui.modules.printing.footer.*;

import srcastra.astra.gui.modules.printing.produit.*;

import srcastra.astra.sys.classetransfert.dossier.brochure.*;

import srcastra.astra.sys.classetransfert.dossier.hotel.*;

import srcastra.astra.sys.classetransfert.dossier.avion.Avion_ticket_T;

import srcastra.astra.gui.modules.printing.produit.produitInfo.*;

import srcastra.astra.gui.modules.aidedesicion.*;

import srcastra.astra.sys.configuration.*;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.gui.test.*;

import srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.*;

import srcastra.astra.sys.classetransfert.dossier.assurance.*;

import srcastra.astra.gui.modules.printing.prix.*;

import srcastra.astra.sys.classetransfert.dossier.bateau.Bateau_T;

import srcastra.astra.sys.classetransfert.dossier.train.Train_T;

import srcastra.astra.sys.classetransfert.dossier.car.Car_T;

import srcastra.astra.sys.classetransfert.dossier.voitureLocation.VoitureLocation_T;

import srcastra.astra.sys.classetransfert.dossier.divers.Divers_T;

import srcastra.astra.sys.classetransfert.*;

import srcastra.astra.gui.modules.printing.tva.*;

import java.io.*;

import srcastra.astra.gui.modules.config.ConfigMediator ;

/**

 *

 * @author  Thomas

 */

public class Report1 extends AbstractReport{

    

    /** Creates a new instance of Report1 */

    Dossier_T dossier;

    DossierRmiInterface dosin;

    int urcleunik;

    RArea tmpa;

    ArrayList genprintArray;

    astrainterface serveur;

    AbstractBuffer buffer;

    AbstractBuffer bufferDivers;

    AbstractBuffer bufferTexte;

    java.awt.Frame frame;

    java.awt.event.WindowListener winlistener;

    SortProduct sort;

    ArrayList produitArray;
    ArrayList prixArray;

    ArrayList produittab;

    AbstractProduitEdition prix;

    AbstractProduitEdition payementedition;

    AbstractProduitEdition totalEdition;

    int prodnumber=1;
    
    boolean voucher=false;

    Loginusers_T user;

    int facture;

    Hashtable tva;

    boolean preview;

    ArrayList typepayement;

    ConfigMediator mediator;

    public Report1(java.awt.Frame frame,Object obj,Object obj2,Object obj3,Object obj4,DossierRmiInterface dosin,int urcleunik,astrainterface serveur,java.awt.event.WindowListener winlistener,int facture,boolean preview,srcastra.astra.gui.modules.config.ConfigMediator mediator,PrintingPreview2 previewF)  {

        super(frame);

        previewF.setReport(this);
        
        if(facture==4)
        {
            voucher=true;
            facture=0;
        }
        

        RPrintSetupJDK13 pt = new RPrintSetupJDK13();  

        pt.horizontal = false;

        this.setPrintSetup(pt);  

        this.setOrientation(this.ORIENTATION_VERTICAL); 

        int type=srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFactureauto();
        
      /*  if (type==10)
        this.setDefaultPageSize(21, 24);
        
        if (type==11)
        this.setDefaultPageSize(21, 30.5);*/
        
        if (type!=0)
        {
            double value=(double) type;
            value=value/100;
        
            this.setDefaultPageSize(21,value);
        
        }

        this.preview=preview;

        this.facture=facture;

        this.frame=frame;

        this.mediator=mediator;

        this.winlistener=winlistener;

        double hauteur=0;

        this.serveur=serveur;

        user=(Loginusers_T)obj;

        Logger.getDefaultLogger().setMinLogLevel(Logger.LOG_INFOS);

        dossier=(Dossier_T)obj3;
        
       

        this.dosin=dosin;

        this.urcleunik=urcleunik;

        super.marginBottom=srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getBottomargin();

        super.marginTop=srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getTopmargin();

        super.marginLeft=1.0d;

        super.marginRight=1.0d; 

        

        substitutDossierModule dossiermodule=new substitutDossierModule();

        dossiermodule.setDossier(dossier);

        sort=new SortProduct(dossiermodule);

        produitArray=sort.loadData();

        produittab=new ArrayList();

        tva=new Hashtable();

        try{

                buffer=(AbstractBuffer)serveur.workWithDecision(null,urcleunik,ActionToolBar.ACT_READ,null,0,0,0,AbstractRequete.DECSRIPTIF_LOG); 

                bufferDivers=(AbstractBuffer)serveur.workWithDecision(null,urcleunik,ActionToolBar.ACT_READ,null,0,0,0,AbstractRequete.DIVERS); 

                bufferTexte=(AbstractBuffer)serveur.workWithDecision(null,urcleunik,ActionToolBar.ACT_READ,null,0,0,0,AbstractRequete.IMPRESSION); 
                
                if(voucher)
                
                header=new ReportHeader1(obj,obj2,obj3,obj4,this,frame,(AbstractBuffer)serveur.workWithDecision(null,user.getUrcleunik(),ActionToolBar.ACT_READ,null,0,0,0,AbstractRequete.DIVERS),4,serveur);
                else
                     header=new ReportHeader1(obj,obj2,obj3,obj4,this,frame,(AbstractBuffer)serveur.workWithDecision(null,user.getUrcleunik(),ActionToolBar.ACT_READ,null,0,0,0,AbstractRequete.DIVERS),facture,serveur);
                    
                typepayement=serveur.renvTypePayementRmiObject(urcleunik).getList2(urcleunik, 1);

            }

        catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn){

                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, sn);

                }

                catch(java.rmi.RemoteException rn){

                 ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);

                }  

        

        hauteur=header.y2-header.y1;

        setPageHeader(header);

        footer=new RepportFooter(obj,obj2,obj3,obj4,this,0,0.7d,genprintArray,bufferTexte,frame,facture);

        if(dossier.getMemo()!=null){

            if((!dossier.getMemo().getMemoPrint().equals("") && facture==0) || (!dossier.getMemo().getMemoFacture().equals("") && facture!=0)){

                footer2=new RepportFooter2(obj,obj2,obj3,obj4,this,0,0.7d,genprintArray,bufferTexte,frame,facture);

            }

        }  

        if(facture==0 || facture==1 || facture==3){

        preparePassager();

        detail1=new PassagerEdition1(obj,obj2,obj3,obj4,this,0,0.1d,genprintArray,frame);

        hauteur=hauteur+(detail1.y2-detail1.y1);

        //setPageHeader(header);

     //   footer=new RepportFooter(obj,obj2,obj3,obj4,this,0,0.7d,genprintArray,bufferTexte,frame);

       // footer.y=1;

     //   setReportFooter(footer);

       if(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getPassagerVisibleBDC()==0 && facture==0 ){

            addArea(detail1);     

        }

       else if(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getPassagerVisibleFact()==0 && facture==1 ){

           addArea(detail1);     

       }

        if(produitArray!=null)

            for(int k=0;k<produitArray.size();k++){

                if(!((InterfaceProduit)produitArray.get(k)).isDeleted()){

                    checkBrochure(obj,obj2,obj3, produitArray.get(k));

                    if(detailproduit1!=null)

                    hauteur=hauteur+detailproduit1.height;

                    checkHotelObject(obj,obj2,obj3, produitArray.get(k));

                    if(detailproduit2!=null)

                    hauteur=hauteur+detailproduit2.height;

                    checkAvionObject(obj,obj2,obj3, produitArray.get(k));

                    if(detailproduit3!=null) 

                    hauteur=hauteur+detailproduit3.y2;

                    checkAssuranceObject(obj,obj2,obj3, produitArray.get(k));

                    checkBateauObject(obj,obj2,obj3, produitArray.get(k));

                    checkTrainObject(obj,obj2,obj3, produitArray.get(k));

                    checkVoitureObject(obj,obj2,obj3, produitArray.get(k));

                    checkDiversObject(obj,obj2,obj3, produitArray.get(k));

                    checkCarObject(obj,obj2,obj3, produitArray.get(k));

                    checkPrix(obj,obj2,obj3, produitArray.get(k));

                    if(facture==1 || facture==2 || facture==3)

                        checkTva(produitArray.get(k));

                    if(produitArray.get(k) instanceof produit_T)

                        prodnumber++;

                }

            } 

        }

        else{

            if(produitArray!=null)

            for(int k=0;k<produitArray.size();k++){

                if(!((InterfaceProduit)produitArray.get(k)).isDeleted()){

                        checkPrix(obj,obj2,obj3, produitArray.get(k));

                        checkTva(produitArray.get(k));

                    if(produitArray.get(k) instanceof produit_T)

                        prodnumber++;

                }

            }

            

            

        }

       // checkFrais(obj,obj2,obj3, null);

        addArea(prix);

        if(facture==0 || facture==1){

            checkPayement(obj,obj2,obj3,null);

            addArea(payementedition);

        }

         if(this.facture!=3)

            totalEdition=new TotalEdition(obj,obj2,obj3,null,this,buffer,dosin,frame,this.facture);

        if(facture==1 || facture==2 || facture==3){

            tvaDetail=new TvaEdition(obj,obj2,obj3,obj4,this,0,0d,genprintArray,frame,tva);

            addArea(tvaDetail);

        }

        if(this.facture!=3)

            addArea(totalEdition);

        if(footer2!=null)

            addArea(footer2);

        addArea(footer);

        tmpa=new RArea();

        Logger.getDefaultLogger().log(Logger.LOG_INFOS,"Hauteur de la page :"+mPageHeightCM+" Hauteur des bloque :"+hauteur);

        tmpa.height=mPageHeightCM-(hauteur+5+marginBottom+marginTop);

       // addArea(tmpa);

      //  toPdf();

        if(preview)

            toAffichage();

        else
     
        
            directPrint();

        //toAffichage();

       // addArea(detail1);

       

       // setPDFFile("bdc.pdf");

     //   disablePrinting(true);

      //disablePrinting(true);

      // prepare();        

     //  printArea(detail1);

      // printArea(detailproduit1);

      // printArea(tmpa);

      // toHtml();

     //  endReport();

      //  printArea(footer);

        //Logger.getDefaultLogger().log(Logger.LOG_DEBUG,super.mPageWidthCM);

        //endReport();

       // disablePrinting(false);

       

       

    }

    private void checkBrochure(Object obj,Object obj2,Object obj3,Object obj4){

        Hashtable brochure=dossier.getBrochure();

         ArrayList tmp=null;

        if(obj4 instanceof Brochure_T){

         Brochure_T bro=(Brochure_T)obj4;     

         AbstractProduitEdition produitedition=null;

            try{

                AbstractBuffer buffer=(AbstractBuffer)serveur.workWithDecision(null,urcleunik,ActionToolBar.ACT_READ,null,0,0,0,AbstractRequete.DECSRIPTIF_LOG);

                produitedition=new BrochureEdition(obj,obj2,obj3,this,buffer,frame,""+prodnumber);

                GeneralePrinting gen=new GeneralePrinting();

                gen.setClientLmcleunik(user.getUrlmcleunik());

                gen.setCxusecleunik( bro.getFrcxcleunik());

                tmp=new ArrayList();

                tmp.add(gen);

                tmp=dosin.getFournisseurrInfo(tmp,urcleunik);

                AbstractInfoProduit broinf=new BrochureInfo(urcleunik,user.getUrlmcleunik(),bro.getTecleunik_trans(),bro.getDe_cleunik(),bro.getBro_lieuEmbarq_cleUnik(),bro.getBro_lieuDebarq_cleUnik(),bro.getBro_accomodation(),bro.getAt_cleunik());                

                broinf=dosin.getProduitInfo(urcleunik,broinf);

                bro.setProduitInfo(broinf);              

            }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn){

                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, sn);

                }

                catch(java.rmi.RemoteException rn){

                 ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);

                }   

            produitedition.addProduct(obj,obj2,obj3,tmp.get(0),bro);

            addArea( produitedition);

            produittab.add(produitedition);

        } 

    }

     private void checkHotelObject (Object obj,Object obj2,Object obj3,Object obj4){

        Hashtable hotel=dossier.getHotel();

        ArrayList tmp=null;

        AbstractProduitEdition produitedition=null;

        if(obj4 instanceof Hotel_T){

          Hotel_T ho=(Hotel_T)obj4;   

             produitedition=new HotelEdition(obj,obj2,obj3,this,buffer,frame,""+prodnumber);//,tmp.get(0),this,bro);

            try{

                GeneralePrinting gen=new GeneralePrinting();

                gen.setClientLmcleunik(user.getUrlmcleunik());

                gen.setCxusecleunik( ho.getFrcxcleunik());

                tmp=new ArrayList();

                tmp.add(gen);

                tmp=dosin.getFournisseurrInfo(tmp,urcleunik);

                AbstractInfoProduit hoinf=new HotelInfo(urcleunik,user.getUrlmcleunik(),ho.getHl_pays_cleUnik(),ho.getHl_accomodation_cleUnik());                

                hoinf=dosin.getProduitInfo(urcleunik,hoinf);

                ho.setProduitInfo(hoinf);      

            }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn){

                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, sn);

                }

                catch(java.rmi.RemoteException rn){

                 ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);

                }   

            produitedition.addProduct(obj,obj2,obj3,tmp.get(0),ho);

            addArea(detailproduit2);

             produittab.add(produitedition);

        }

    }

     private void checkAvionObject (Object obj,Object obj2,Object obj3,Object obj4){

        Hashtable ticket=dossier.getAvionTicket();

        ArrayList tmp=null;

        AbstractProduitEdition produitedition=null;

        if(obj4 instanceof Avion_ticket_T){

          Avion_ticket_T tick=(Avion_ticket_T)obj4;  

             produitedition=new AvionEdition(obj,obj2,obj3,this,buffer,dosin,frame,""+prodnumber);//,tmp.get(0),this,bro);

            try{

                GeneralePrinting gen=new GeneralePrinting();

                gen.setClientLmcleunik(user.getUrlmcleunik());

                gen.setCxusecleunik( tick.getFrcxcleunik());

                tmp=new ArrayList();

                tmp.add(gen);

                tmp=dosin.getFournisseurrInfo(tmp,urcleunik);

                AbstractInfoProduit avioninf=new AvionInfo(urcleunik,user.getUrlmcleunik(),tick.getCoe_cleunik(),1);                

                avioninf=dosin.getProduitInfo(urcleunik,avioninf);

                tick.setProduitInfo(avioninf);       

            }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn){

                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, sn);

                }

             catch(java.rmi.RemoteException rn){

                 ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);

                }   

            produitedition.addProduct(obj,obj2,obj3,tmp.get(0),tick);

            addArea(produitedition);

            produittab.add(produitedition);

        }

    }

      private void checkBateauObject (Object obj,Object obj2,Object obj3,Object obj4){

        ArrayList tmp=null;

        AbstractProduitEdition produitedition=null;

        if(obj4 instanceof Bateau_T){

          Bateau_T bat=(Bateau_T)obj4;  

             produitedition=new TransportEdition(obj,obj2,obj3,this,buffer,dosin,AbstractProduitEdition.BATEAU,frame,""+prodnumber);//,tmp.get(0),this,bro);

            try{

                GeneralePrinting gen=new GeneralePrinting();

                gen.setClientLmcleunik(user.getUrlmcleunik());

                gen.setCxusecleunik( bat.getFrcxcleunik());

                tmp=new ArrayList();

                tmp.add(gen);

                tmp=dosin.getFournisseurrInfo(tmp,urcleunik);

               // AbstractInfoProduit=new AvionInfo(urcleunik,AbstractProduitEdition.AVION,tick.getCoe_cleunik(),1);                

               // avioninf=dosin.getProduitInfo(urcleunik,avioninf);

                //tick.setProduitInfo(avioninf);       

            }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn){

                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, sn);

                }

             catch(java.rmi.RemoteException rn){

                 ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);

                }   

            produitedition.addProduct(obj,obj2,obj3,tmp.get(0),bat,bat.getBat_memo());

            addArea(produitedition);

            produittab.add(produitedition);

        }

    }

      private void checkVoitureObject (Object obj,Object obj2,Object obj3,Object obj4){

        ArrayList tmp=null;

        AbstractProduitEdition produitedition=null;

        if(obj4 instanceof VoitureLocation_T ){

          if(obj4 instanceof Car_T);

          else{

          VoitureLocation_T voi=(VoitureLocation_T)obj4;  

             produitedition=new TransportEdition(obj,obj2,obj3,this,buffer,dosin,AbstractProduitEdition.VOITURE,frame,""+prodnumber);//,tmp.get(0),this,bro);

            try{

                GeneralePrinting gen=new GeneralePrinting();

                gen.setClientLmcleunik(user.getUrlmcleunik());

                gen.setCxusecleunik( voi.getFrcxcleunik());

                tmp=new ArrayList();

                tmp.add(gen);

                tmp=dosin.getFournisseurrInfo(tmp,urcleunik);

               // AbstractInfoProduit=new AvionInfo(urcleunik,AbstractProduitEdition.AVION,tick.getCoe_cleunik(),1);                

               // avioninf=dosin.getProduitInfo(urcleunik,avioninf);

                //tick.setProduitInfo(avioninf);       

            }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn){

                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, sn);

                }

             catch(java.rmi.RemoteException rn){

                 ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);

                }   

            produitedition.addProduct(obj,obj2,obj3,tmp.get(0),voi,voi.getVl_memo());

            addArea(produitedition);

            produittab.add(produitedition);

        }

        }

    }

      private void checkDiversObject (Object obj,Object obj2,Object obj3,Object obj4){

        ArrayList tmp=null;

        AbstractProduitEdition produitedition=null;

        if(obj4 instanceof Divers_T){

          Divers_T voi=(Divers_T)obj4;  

             produitedition=new TransportEdition(obj,obj2,obj3,this,buffer,dosin,AbstractProduitEdition.DIVERS,frame,""+prodnumber);//,tmp.get(0),this,bro);

            try{

                GeneralePrinting gen=new GeneralePrinting();

                gen.setClientLmcleunik(user.getUrlmcleunik());

                gen.setCxusecleunik( voi.getFrcxcleunik());

                tmp=new ArrayList();

                tmp.add(gen);

                tmp=dosin.getFournisseurrInfo(tmp,urcleunik);

               // AbstractInfoProduit=new AvionInfo(urcleunik,AbstractProduitEdition.AVION,tick.getCoe_cleunik(),1);                

               // avioninf=dosin.getProduitInfo(urcleunik,avioninf);

                //tick.setProduitInfo(avioninf);       

            }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn){

                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, sn);

                }

             catch(java.rmi.RemoteException rn){

                 ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);

                }   

            produitedition.addProduct(obj,obj2,obj3,tmp.get(0),voi,voi.getVl_memo());

            addArea(produitedition);

            produittab.add(produitedition);

        

        }

    }

      private void checkCarObject (Object obj,Object obj2,Object obj3,Object obj4){

        ArrayList tmp=null;

        AbstractProduitEdition produitedition=null;

        if(obj4 instanceof Car_T){

          Car_T voi=(Car_T)obj4;  

             produitedition=new TransportEdition(obj,obj2,obj3,this,buffer,dosin,AbstractProduitEdition.CAR,frame,""+prodnumber);//,tmp.get(0),this,bro);

            try{

                GeneralePrinting gen=new GeneralePrinting();

                gen.setClientLmcleunik(user.getUrlmcleunik());

                gen.setCxusecleunik( voi.getFrcxcleunik());

                tmp=new ArrayList();

                tmp.add(gen);

                tmp=dosin.getFournisseurrInfo(tmp,urcleunik);

               // AbstractInfoProduit=new AvionInfo(urcleunik,AbstractProduitEdition.AVION,tick.getCoe_cleunik(),1);                

               // avioninf=dosin.getProduitInfo(urcleunik,avioninf);

                //tick.setProduitInfo(avioninf);       

            }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn){

                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, sn);

                }

             catch(java.rmi.RemoteException rn){

                 ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);

                }   

            produitedition.addProduct(obj,obj2,obj3,tmp.get(0),voi,voi.getVl_memo());

            addArea(produitedition);

            produittab.add(produitedition);

        }

    }

    

      private void checkTrainObject (Object obj,Object obj2,Object obj3,Object obj4){

        ArrayList tmp=null;

        AbstractProduitEdition produitedition=null;

        if(obj4 instanceof Train_T){

          Train_T train=(Train_T)obj4;  

             produitedition=new TransportEdition(obj,obj2,obj3,this,buffer,dosin,AbstractProduitEdition.TRAIN,frame,""+prodnumber);//,tmp.get(0),this,bro);

            try{

                GeneralePrinting gen=new GeneralePrinting();

                gen.setClientLmcleunik(user.getUrlmcleunik());

                gen.setCxusecleunik( train.getFrcxcleunik());

                tmp=new ArrayList();

                tmp.add(gen);

                tmp=dosin.getFournisseurrInfo(tmp,urcleunik);

               // AbstractInfoProduit=new AvionInfo(urcleunik,AbstractProduitEdition.AVION,tick.getCoe_cleunik(),1);                

               // avioninf=dosin.getProduitInfo(urcleunik,avioninf);

                //tick.setProduitInfo(avioninf);       

            }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn){

                ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, sn);

                }

             catch(java.rmi.RemoteException rn){

                 ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);

                }   

            produitedition.addProduct(obj,obj2,obj3,tmp.get(0),train,train.getTrn_memo());

            addArea(produitedition);

            produittab.add(produitedition);

        }

    }

     private void checkAssuranceObject (Object obj,Object obj2,Object obj3,Object obj4){

        Hashtable ticket=dossier.getAvionTicket();

        ArrayList tmp=null;

        AbstractProduitEdition produitedition=null;

        if(obj4 instanceof Assurance_T){

          Assurance_T assu=(Assurance_T)obj4;  

          produitedition=new AssuranceEdition(obj,obj2,obj3,this,buffer,dosin,frame,""+prodnumber);//,tmp.get(0),this,bro);

           try{

                GeneralePrinting gen=new GeneralePrinting();

                gen.setClientLmcleunik(user.getUrlmcleunik());

                gen.setCxusecleunik( assu.getFrcxcleunik());

                tmp=new ArrayList();

                tmp.add(gen);

                tmp=dosin.getFournisseurrInfo(tmp,urcleunik);

                //AbstractInfoProduit avioninf=new AvionInfo(urcleunik,AbstractProduitEdition.AVION,tick.getCoe_cleunik(),1);                

               // avioninf=dosin.getProduitInfo(urcleunik,avioninf);

                //tick.setProduitInfo(avioninf);       

           }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn){

               ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, sn);

                }

            catch(java.rmi.RemoteException rn){

                 ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);

               }   

            produitedition.addProduct(obj,obj2,obj3,tmp.get(0),assu);

            addArea(produitedition);

            produittab.add(produitedition);

        }

    }

     private void checkTva(Object obj){

         if(obj instanceof InterfaceProduit)

           srcastra.astra.sys.compta.tvaCalcul.parseTva((InterfaceProduit)obj,tva);

     }

     private void checkPrix (Object obj,Object obj2,Object obj3,Object obj4){

        ArrayList tmp=null;
        
        if(obj4 instanceof produit_T){

          produit_T prod=(produit_T)obj4;  
          
          if(prix==null)

           prix=new PrixEdition(obj,obj2,obj3,this,buffer,dosin,frame,facture);//,tmp.get(0),this,bro);
           if(prixArray==null){
               prixArray=new ArrayList();
               MyRvfield titre;
               titre=new MyRvfield(1,1,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", user.getLangage()).getString("p_synth"),1,"",new java.awt.Font("Courrier",java.awt.Font.BOLD,12));     
               titre.x=0;
               titre.y=0;
               RArea area=new RArea();
               area.add(titre);
               area.marginBottom=0.5d;
               
               prixArray.add(area);
            }
         /*  try{

                GeneralePrinting gen=new GeneralePrinting();

                gen.setClientLmcleunik(1);

                gen.setCxusecleunik( assu.getFrcxcleunik());

                tmp=new ArrayList();

                tmp.add(gen);

               tmp=dosin.getFournisseurrInfo(tmp,urcleunik);

                //AbstractInfoProduit avioninf=new AvionInfo(urcleunik,AbstractProduitEdition.AVION,tick.getCoe_cleunik(),1);                

               // avioninf=dosin.getProduitInfo(urcleunik,avioninf);

                //tick.setProduitInfo(avioninf);       

           }catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn){

               ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, sn);

                }

            catch(java.rmi.RemoteException rn){

                 ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);

               }   */
           
           prixArray.add(((PrixEdition)prix).addProductToArea(obj,obj2,obj3,obj4,prod,""+prodnumber));

            //addArea(produitedition);

           // produittab.add(produitedition);

        }

    }

       private void checkFrais (Object obj,Object obj2,Object obj3,Object obj4){

        ArrayList tmp=null;

        Hashtable fraishash=dossier.getFraisDossier();

        if(fraishash!=null)

            for(Enumeration enu=fraishash.keys();enu.hasMoreElements();){

               produit_T prod=(produit_T)fraishash.get(enu.nextElement());

               if(prix!=null){

                prix.addProduct(obj,obj2,obj3,obj4,prod,""+prodnumber);

                prodnumber++;

               }

        }

    }

    private void checkFrais2 (Object obj,Object obj2,Object obj3,Object obj4){

        ArrayList tmp=null;

        Hashtable fraishash=dossier.getFraisDossier();

        if(fraishash!=null)

            for(Enumeration enu=fraishash.keys();enu.hasMoreElements();){

               produit_T prod=(produit_T)fraishash.get(enu.nextElement());

               if(prix!=null){

                prix.addProduct(obj,obj2,obj3,obj4,prod,""+prodnumber);

                prodnumber++;

               }

        }

    }

      private void checkPayement (Object obj,Object obj2,Object obj3,Object obj4){

          int nbPaiement=0;
        ArrayList tmp=null;

        payementedition=null;

        java.util.TreeMap treemap=dossier.getPayement();

        boolean todaypay=false;

        if(treemap!=null){

        java.util.Set set=treemap.keySet();

        java.util.Iterator iterator=set.iterator();      

           while(iterator.hasNext()){

                   Payement_T lpayement=(Payement_T)treemap.get((Long)iterator.next());

                   //Logger.getDefaultLogger().log(Logger.LOG_INFOS,"[UUUUUUUUUUUUUUUUUU]date du payement ="+lpayement.getDatePayement().toString()+" Date du jour"+srcastra.astra.sys.classetransfert.utils.CalculDate.getTodayDate().toString());

                   if(lpayement.getDatePayement().toString2().equals(srcastra.astra.sys.classetransfert.utils.CalculDate.getTodayDate().toString2()))

                        todaypay=true;

            }

        

        }

        if(treemap!=null){

            if(todaypay)

                payementedition=new PayementEdition(obj,obj2,obj3,this,bufferDivers,dosin,frame,"");

            else

                payementedition=new PayementEdition(obj,obj2,obj3,this,bufferDivers,dosin,frame,"pay45");  

        

        }

        else

            payementedition=new PayementEdition(obj,obj2,obj3,this,bufferDivers,dosin,frame,"pay45");  

        if(treemap!=null){
        double totP25=0;
        double totP6=0;
        boolean putP25=false;
        boolean putP6=false;
        
        java.util.Set set=treemap.keySet();

        java.util.Iterator iterator=set.iterator();      

        int type=srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getNcauto();
        
        if(type==10)
        {
           while(iterator.hasNext()){

                   Payement_T lpayement=(Payement_T)treemap.get((Long)iterator.next());

                   Logger.getDefaultLogger().log(Logger.LOG_INFOS,"[UUUUUUUUUUUUUUUUUU]date du payement ="+lpayement.getDatePayement().toString()+" Date du jour"+srcastra.astra.sys.classetransfert.utils.CalculDate.getTodayDate().toString());
                   
                   if(lpayement.getM_typepayement()==25 && lpayement.getDatePayement().toString2().equals(srcastra.astra.sys.classetransfert.utils.CalculDate.getTodayDate().toString2()))
                       totP25+=lpayement.getPrix();
                   
                   if(lpayement.getM_typepayement()==6 && lpayement.getDatePayement().toString2().equals(srcastra.astra.sys.classetransfert.utils.CalculDate.getTodayDate().toString2()))
                       totP6+=lpayement.getPrix();
                   
                   
                  // if(lpayement.getDatePayement().toString2().equals(srcastra.astra.sys.classetransfert.utils.CalculDate.getTodayDate().toString2()))

                  //      payementedition.addProduct(obj,obj2,obj3,typepayement,null," ",lpayement);

            }
        iterator=set.iterator();
        }
        
        
        
        while(iterator.hasNext()){

                   Payement_T lpayement=(Payement_T)treemap.get((Long)iterator.next());

                   Logger.getDefaultLogger().log(Logger.LOG_INFOS,"[UUUUUUUUUUUUUUUUUU]date du payement ="+lpayement.getDatePayement().toString()+" Date du jour"+srcastra.astra.sys.classetransfert.utils.CalculDate.getTodayDate().toString());
                                  
                   
                   if(lpayement.getDatePayement().toString2().equals(srcastra.astra.sys.classetransfert.utils.CalculDate.getTodayDate().toString2()))
                   {  
                       nbPaiement++;
                       if(type==10)
                       {
                        double savePrix=0;
                        
                        if(lpayement.getM_typepayement()==6 && !putP6)
                        {    
                            savePrix=lpayement.getPrix();
                            lpayement.setPrix(totP6+totP25);
                            payementedition.addProduct(obj,obj2,obj3,typepayement,null," ",lpayement);
                            putP6=true;
                            lpayement.setPrix(savePrix);
                        }else if(lpayement.getM_typepayement()!=25 && lpayement.getM_typepayement()!=6){
                             payementedition.addProduct(obj,obj2,obj3,typepayement,null," ",lpayement);
                        } 
                       }
                       else
                          payementedition.addProduct(obj,obj2,obj3,typepayement,null," ",lpayement);
                   }
                   
            }

        

        }

        if(nbPaiement==0)
        payementedition.addProduct(obj,obj2,obj3,null,null,"");  
        else
        payementedition.addProduct(obj,obj2,obj3,null,null,"YES");  
            
       

    }

    public void toHtml(){

        setHTMLActive(true); // active HTML / DHTML output

        setDHTMLActive(true); // select DHTML mode

        disablePrinting(true); // do not send output to printer

        prepare();

        prepareArea();

        

       // printArea(tmpa);

        endReport(); 

        String htmlString=getHTML(); 

        try {

            String name=null;

            if(facture==0)name="bdc.html";

            else if(facture==1 || facture==2) name="fact"+dossier.getNumfact()+".html";

            File file=new File("c:\\"+name);

            java.io.OutputStreamWriter out2= new java.io.OutputStreamWriter(new java.io.FileOutputStream(file),System.getProperty("file.encoding")); 

            out2.write(htmlString,0,htmlString.length());

            out2.close(); 

        } catch (Exception e) { e.printStackTrace(); }



    }

     private String toPdf(){

        // String path=srcastra.astra.sys.ManageDirectory.testDirectory("compta");

        String fileName="";
        String path="";
         if(facture==1 || facture==2) {
              path=srcastra.astra.sys.ManageDirectory.testDirectory("compta\\Fa");
              fileName="\\F";
              fileName=fileName+dossier.getNumfact();
              path=path+fileName+".pdf";
              System.out.println("Paht of pdf"+path);
              setPDFFile(path);
         }
         else if(facture==3){
             path=srcastra.astra.sys.ManageDirectory.testDirectory("compta\\NO");
             fileName="\\N";
             fileName=fileName+dossier.getNumnc();
             path=path+fileName+".pdf";
             setPDFFile(path);
         }
        else if(facture==0){
             path=srcastra.astra.sys.ManageDirectory.testDirectory("compta\\B");
             fileName="\\B";
             fileName=fileName+dossier.getDrnumdos();
             path=path+fileName+".pdf";
             setPDFFile(path);
         }

        return path;

    }

    public void prepareArea(){

        if(detail1!=null){

          if((srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getPassagerVisibleBDC()==0 && facture==0 ) || (srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getPassagerVisibleFact()==0 && (facture==1 || facture==3)))

            printArea(detail1);

        }

       if(produittab!=null)

           for(int i=0;i<produittab.size();i++){

               AbstractProduitEdition tmp=(AbstractProduitEdition)produittab.get(i);

               if(tmp!=null)  

               {

                   if(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getDetailProduitVisibleBDC()==0 && facture==0 ){

                          printArea(tmp);

        }

                    else if(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getDetailProduitVisibleFact()==0 && (facture==1 ||  facture==3) &&  srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getFactureresum()==0){

                         printArea(tmp); 

                    }

          

               }

           }

        if(prix!=null){

            if(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getDetailPrixVisibleBDC()==0 && facture==0 ){
                if(prixArray!=null)   {       
                for(int i=0;i<prixArray.size();i++){
                    RArea area=(RArea)prixArray.get(i);
                    if(area!=null){
                           printArea(area);
                    }
                }
                }
            }

            else if(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getDetailPrixVisibleFact()==0 && (facture==1 || facture==3) ){
                     if(prixArray!=null)   {       
                for(int i=0;i<prixArray.size();i++){
                    RArea area=(RArea)prixArray.get(i);
                    if(area!=null){
                           printArea(area);
                    }
                }
                }
                       //  printArea(prix);

            }  

        }

         if(payementedition!=null)

            printArea(payementedition);

        if(tvaDetail!=null)

            printArea(tvaDetail);

        if(totalEdition!=null)

            printArea(totalEdition);

        if(footer2!=null)

            printArea(footer2);

        if(footer!=null)

            printArea(footer);

      

        

    }
    


    private void toAffichage2(){

       // disablePrinting(true);

      // toPdf();

        if(mediator==null) {
            prepare();

            prepareArea();
        }
        else if(mediator!=null){

            mediator.setReport(this);

            mediator.setDefautltPrintingConfig();  

           // prepare();    

            prepare(); 

            prepareArea();

        }

        else System.out.println("[*************]mediator null");

       // printArea(tmpa);

        //endReport();

      

        RReportJWindow win= new RReportJWindow(this,frame,null,"","");

       // srcastra.JCFactureTest win2=new srcastra.JCFactureTest(this);

        //win2.show();

        

        win.addWindowListener(winlistener);

        RPrintSetupJDK13 pt = new RPrintSetupJDK13();  

        pt.horizontal = false;

        this.setPrintSetup(pt);  

        this.setOrientation(this.ORIENTATION_VERTICAL);

       

        win.show();

       // toHtml();

      //  toPdf();

      //  printArea(footer);      

    }

    private void toAffichage(){

       // disablePrinting(true);

       String path=toPdf();


        if(mediator==null) {

            prepare();

            prepareArea();

        }
        else if(mediator!=null){

            mediator.setReport(this);

            mediator.setDefautltPrintingConfig();  

           // prepare();    

            prepare(); 

    
            prepareArea();

        }

        else System. out.println("[*************]mediator null");

       // printArea(tmpa);

        //endReport();

      

        //RReportJWindow win= new RReportJWindow(this,frame);

        srcastra.JCFactureTest win2=new srcastra.JCFactureTest(this,mediator,this.serveur,this.user,frame,1);

        String[] emailAdresse=new String[]{dossier.getClientContractuel().getCsmailprincip(),dossier.getClientContractuel().getCsmailsecond(),dossier.getClientFacturable().getCsmailprincip(),dossier.getClientFacturable().getCsmailprincip()} ;

        win2.setPathForMail(path);

        win2.setEmailAdresse(emailAdresse);

        win2.show();

        

       /* win.addWindowListener(winlistener);

        RPrintSetupJDK13 pt = new RPrintSetupJDK13();  

        pt.horizontal = false;

        this.setPrintSetup(pt);  

        this.setOrientation(this.ORIENTATION_VERTICAL);

       

        win.show();*/

       // toHtml();

      //  toPdf();

      //  printArea(footer);      

    }

    private void directPrint(){

        String path=toPdf();
        
        prepare();        

        prepareArea();

        if(mediator!=null){

            mediator.setReport(this);

            mediator.setDefautltPrintingConfig();  

             prepare();        

             prepareArea();
        }

        else System.out.println("[*************]mediator null");

       // mediator.setDefautltPrintingConfig();  

       /* srcastra.JCFactureTest win2=new srcastra.JCFactureTest(this,mediator,this.serveur,this.user,frame,1);

        String[] emailAdresse=new String[]{dossier.getClientContractuel().getCsmailprincip(),dossier.getClientContractuel().getCsmailsecond(),dossier.getClientFacturable().getCsmailprincip(),dossier.getClientFacturable().getCsmailprincip()} ;

        win2.setPathForMail(path);

        win2.setEmailAdresse(emailAdresse);
        
        win2=null;*/
        
        
        ///win2.show();
        
        //win2.dispose();
        
        
       
        
        endReport();

    }

    private void preparePassager(){    

        if(dossier.getPassager()!=null){

        for(int i=0;i<dossier.getPassager().size();i++){            

            if(genprintArray==null)

                genprintArray=new ArrayList();

                Passager_T tmppass=(Passager_T)dossier.getPassager().get(i);

                GeneralePrinting tmpgen=new GeneralePrinting();

                tmpgen.setClientLmcleunik(user.getUrlmcleunik());

                tmpgen.setCxusecleunik(tmppass.getCscleunik());

                tmpgen.setTitrecleunik(tmppass.getTscleunik());

                tmpgen.setNatcleunik(tmppass.getPr_nat());

                genprintArray.add(tmpgen);                

        }

        if(genprintArray!=null){

         if(genprintArray.size()>0){

             try{

             genprintArray=dosin.getPassagerInfo(genprintArray,urcleunik);

             }

                catch(srcastra.astra.sys.rmi.Exception.ServeurSqlFailure sn){

                    ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, sn);

                }

                catch(java.rmi.RemoteException rn){

                    ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, rn);

                }           

         }          

        }

        } 

    }    

    

    /** Getter for property facture.

     * @return Value of property facture.

     */

    public int getFacture() {

        return facture;

    }

    

    /** Setter for property facture.

     * @param facture New value of property facture.

     */

    public void setFacture(int facture) {

        this.facture = facture;

    }

    

    /** Getter for property serveur.

     * @return Value of property serveur.

     */

    public srcastra.astra.sys.rmi.astrainterface getServeur() {

        return serveur;

    }

    

    /** Setter for property serveur.

     * @param serveur New value of property serveur.

     */

    public void setServeur(srcastra.astra.sys.rmi.astrainterface serveur) {

        this.serveur = serveur;

    }

    

    /** Getter for property user.

     * @return Value of property user.

     */

    public srcastra.astra.sys.classetransfert.Loginusers_T getUser() {

        return user;

    }

    

    /** Setter for property user.

     * @param user New value of property user.

     */

    public void setUser(srcastra.astra.sys.classetransfert.Loginusers_T user) {

        this.user = user;

    }

    

    /** Getter for property dossier.

     * @return Value of property dossier.

     */

    public srcastra.astra.sys.classetransfert.dossier.Dossier_T getDossier() {

        return dossier;

    }

    

    /** Setter for property dossier.

     * @param dossier New value of property dossier.

     */

    public void setDossier(srcastra.astra.sys.classetransfert.dossier.Dossier_T dossier) {

        this.dossier = dossier;

    }

    

}

