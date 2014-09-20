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







/**



 *



 * @author  Thomas



 */



public class PrintingPreview extends javax.swing.JInternalFrame {



    



    /** Creates new form PrintingPreview */



    Dossier_T m_dossier;

    DossierMainScreenModule m_parent;

    java.awt.Frame m_maman;

    DossierRmiInterface dossierInterface;

    srcastra.astra.sys.classetransfert.dossier.Dossier_T dossier;

    Entite_T entite;

    GeneralePrinting genprint;

    ServeurConnect kiss;

    Loginusers_T user;

    public PrintingPreview(java.awt.Frame maman) {

        initComponents();

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

	init();	

    }

     public PrintingPreview(java.awt.Frame maman,DossierRmiInterface dossierInterface,int  numdossier,Loginusers_T user) {

        initComponents();

        this.dossierInterface=dossierInterface;

        this.user=user;

        genprint=new GeneralePrinting();

        try{

            //dossierInterface=kiss.serveur2.renvDossierRmiObject(kiss.tmpgestion2.getUrcleunik());

            ArrayList tmp=null;//=dossierInterface.chargeDossier(user,numdossier);

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

      //  m_parent=parent;

        m_maman=maman;

        //initComponents();

        

        //exampleOrder2 f=new exampleOrder2();

	//	f.setVisible(true);



       /* try{



           // kiss.serveur2.



        }catch(RemoteException rn){



            rn.printStackTrace();

            

        }*/

	init();	

    }

    public void init() {	

 // load report from file

		RReportJ2 report=new RReportJ2(m_maman); 					   

		if (!report.importReport(getClass().getResource("bdecom.rep"))) System.exit(0);	

                // set the values for the header programatically

		report.getReportHeader().getItemByName("bc_num_dos").setruntimeValue(dossier.getDrnumdos());

                report.getReportHeader().getItemByName("bc_fax").setruntimeValue(entite.getEefax());

                report.getReportHeader().getItemByName("bc_email").setruntimeValue(entite.getEemail());

                report.getReportHeader().getItemByName("bc_agent").setruntimeValue(dossier.getCreator().getUrnom());

                report.getReportHeader().getItemByName("bc_agence_tel").setruntimeValue(entite.getEefax());

                report.getReportHeader().getItemByName("bc_agence_tva").setruntimeValue(entite.getEetva());

                report.getReportHeader().getItemByName("bc_agent_tel").setruntimeValue(dossier.getCreator().getUrtelephonebd());

		report.getReportHeader().getItemByName("bc_date_depart").setruntimeValue(dossier.getDr_date_depart().toString2());

		report.getReportHeader().getItemByName("bc_nom_titre").setruntimeValue(dossier.getClientContractuel().getTitrenom());

		report.getReportHeader().getItemByName("bc_nom").setruntimeValue(dossier.getClientContractuel().getCsnom());

		report.getReportHeader().getItemByName("bc_adresse_cli").setruntimeValue(dossier.getClientContractuel().getCsadresse());	              	

                report.getReportHeader().getItemByName("bc_cp").setruntimeValue(dossier.getClientContractuel().getCodenom());	

                report.getReportHeader().getItemByName("bc_loc").setruntimeValue(dossier.getClientContractuel().getLocnom());	

                report.getReportHeader().getItemByName("bc_agence").setruntimeValue(entite.getEenom());	

                report.getReportHeader().getItemByName("bc_adresse").setruntimeValue(entite.getEeadresse());	

                report.getReportHeader().getItemByName("bc_cp").setruntimeValue(dossier.getClientContractuel().getLocnom());	

                report.getReportHeader().getItemByName("bc_loc").setruntimeValue("  ");

                report.getReportHeader().getItemByName("bc_date_dep2").setruntimeValue(dossier.getDr_date_depart().toString2());

                report.getReportHeader().getItemByName("bc_date_retour").setruntimeValue(dossier.getDr_date_retour().toString2());

                report.getReportHeader().getItemByName("bc_nbj").setruntimeValue(new Integer(dossier.getDr_nbrjour()).toString());

                report.getReportHeader().getItemByName("nb_nuit").setruntimeValue(new Integer(dossier.getDr_nbrenuit()).toString());

                report.getReportHeader().getItemByName("bc_agence_cbc").setruntimeValue(entite.getEecomptebancaire());

                report.getReportHeader().getItemByName("bc_cp").setruntimeValue(genprint.getUser().getCp());

                report.getReportHeader().getItemByName("bc_loc").setruntimeValue(genprint.getUser().getLocalité());

                report.getReportHeader().getItemByName("bc_cpag").setruntimeValue(genprint.getAgence().getCp());

                report.getReportHeader().getItemByName("bc_locag").setruntimeValue(genprint.getAgence().getLocalité()); 

                RArea area=report.getAreaByName("Detail");                              

                addUserToArea(area);

                

                //report.getReportHeader().getItemByName("bc_agence_cbc").setruntimeValue(entite.getEecomptebancaire());

		// remove the link between Header and detail, we will trigger the printing programatically

	//	report.getAreaByName("Detail").setLinkedArea(null);	

	//	RArea detail=report.getAreaByName("Detail");			

		// create preview window	

              

		RReportWindow Win= new RReportWindow(report,m_maman);

                Win.setModal(false);

		// this will print the Header Area 

		report.prepare();	

                report.printArea(area);

		// no print line 1

	/*	detail.getItemByName("Article").setruntimeValue("FT455");

		detail.getItemByName("Description").setruntimeValue("Printer XXX");

		detail.getItemByName("Price").setruntimeValue(new Double(34.3));

		detail.getItemByName("Quantity").setruntimeValue("100");

		detail.getItemByName("Priority").setruntimeValue("0");

		detail.getItemByName("Amount").setruntimeValue("3430");

		report.printArea(detail); */		

		// no print line 2

	/*	detail.getItemByName("Article").setruntimeValue("B301");

		detail.getItemByName("Description").setruntimeValue("CD-ROM writer");

		detail.getItemByName("Price").setruntimeValue(new Double(333.3));

		detail.getItemByName("Quantity").setruntimeValue("190");

		detail.getItemByName("Amount").setruntimeValue("63327");

		detail.getItemByName("Priority").setruntimeValue("1");

		report.printArea(detail);	*/					

		// set total

		//report.getReportFooter().getItemByName("Total").setruntimeValue(new Integer(66757));		

		report.endReport();

		Win.show();			

		//System.exit(0);

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

                tmpgen=dossierInterface.getPassagerInfo(tmpgen,user.getUrcleunik());

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

        pack();
    }//GEN-END:initComponents



    



    



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables



    



}



