/*

 * Mediator.java

 *

 * Created on 9 mai 2003, 11:54

 */


package srcastra.astra.gui.components.combobox.liste2;

import srcastra.astra.gui.*;

import srcastra.astra.gui.test.*;

/**
 * @author Thomas
 */

public class Mediator {


    /**
     * Creates a new instance of Mediator
     */

    String typeliste;

    public Mediator() {

    }

    public void registerListe(Liste2 liste) {

        this.liste = liste;

    }

    public void registerListe2(Liste2 liste) {

        this.liste2 = liste;

    }

    public void registerModule(MainFrame module) {

        this.module = module;

    }

    public void listeAction(int key, String liste) {

        //if(cleun

        typeliste = liste;

        clientModule = module.getGrp_mcp_mainpan().displayModulesClients2(module.getGrp_MenuItem_Clients());

        // My_menuItem menusubstitut=new My_menuItem(module.getGrp_MenuItem_Clients());

        // menusubstitut.fireActionEvent(new java.awt.event.ActionEvent(module.getGrp_MenuItem_Clients(),1,"test"));

        //  module.getGrp_MenuItem_Clients().firefireActionPerformed(new java.awt.event.ActionEvent(module.getGrp_MenuItem_Clients(),1,"test"));

        clientModule.setMediator(this);

        clientModule.displayCreateSequence();

        // clientModule.requestFocus();

    }

    public void registerDossierg(srcastra.astra.gui.modules.dossier.DossierGeneralInfoPane dossierg) {

        this.dossierg = dossierg;

    }

    public void moduleAction(int cleunik, String name) {

        //module.getGrp_mcp_mainpan().removeInternalFrame(clientModule);

        //odule.getGrp_mcp_mainpan().setGrp_Mod_clients(null);

        clientModule.cancelModule2();

        module.getGrp_mcp_mainpan().setFrontSelectedIframe((javax.swing.JInternalFrame) dossierg.getMainScrennModule());

        // clientModule=null;

        if (typeliste.equals("lc")) {

            synchronized (module.getGrp_mcp_mainpan().getSyn()) {


                liste.setModedisplay(true);

                liste.setCleUnik(cleunik);

                liste.setText(name);

                liste.setModedisplay(false);

                liste.requestFocus();

                dossierg.setNextListe(true);

                System.out.println("[\n\n\n\n++++++]give de focus to liste 2");

                // liste.requestFocus();

                ((Liste2) liste.getGrp_Comp_nextComponent()).requestFocus();

                //((Liste2)liste.getGrp_Comp_nextComponent()).getGrp_TField_encode().repaint();

                //((Liste2)liste.getGrp_Comp_nextComponent()).updateUI();

            }

        } else {

            liste2.setModedisplay(true);

            liste2.setCleUnik(cleunik);

            liste2.setText(name);

            liste2.setModedisplay(false);

            dossierg.setNextListe(false);

        }

        // liste.nextComp();

        //((Liste2)liste.getGrp_Comp_nextComponent()).getGrp_TField_encode().repaint();

        //((Liste2)liste.getGrp_Comp_nextComponent()).updateUI();

        // dossierg.repaint();

    }

    Liste2 liste;

    Liste2 liste2;

    MainFrame module;

    srcastra.astra.gui.modules.clients.ClientModules clientModule;

    srcastra.astra.gui.modules.dossier.DossierGeneralInfoPane dossierg;

}

