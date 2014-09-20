/*

 * TvaFactory.java

 *

 * Created on 25 avril 2003, 16:01

 */


package srcastra.astra.gui.components.tva;

import javax.swing.JTree;

import javax.swing.tree.*;

import srcastra.astra.gui.modules.*;

/**
 * @author Thomas
 */


public class TvaFactory {

    MainScreenModule parent;

    public TvaFactory(MainScreenModule parent) {

        this.parent = parent;


    }

    public TvaComponent getTreeVenteNat() {

        TvaComposite vente = new TvaComposite(TvaComponent.NVENTE);


        TvaComposite ventesim = new TvaComposite(TvaComponent.NVENTESIM);

        TvaComposite venteco = new TvaComposite(TvaComponent.NVENTECO);

        TvaComposite venteta = new TvaComposite(TvaComponent.NVENTETA);

        TvaComposite venteamb = new TvaComposite(TvaComponent.NVENTETAMB);


        TvaComposite venteid = new TvaComposite(TvaComponent.VENTEVID);

        TvaComposite ventees = new TvaComposite(TvaComponent.VENTEES);

        TvaComposite venteauex = new TvaComposite(TvaComponent.VENTEAUEX);

        TvaComposite venteauexinf = new TvaComposite(TvaComponent.VENTEAUEXINF);


        vente.addComponent(venteid, true);

        vente.addComponent(ventees, true);

        vente.addComponent(venteauex, true);

        vente.addComponent(venteauexinf, true);


        vente.addComponent(ventesim, true);

        vente.addComponent(venteco, true);

        vente.addComponent(venteta, true);

        vente.addComponent(venteamb, true);

        vente.setLibelle1(java.util.ResourceBundle.getBundle("srcastra/astra/locale/tva", parent.getCurrentUser().getLangage()).getString("nat"));

        return vente;

    }

    public TvaComponent getTreeVenteExt() {

        TvaComposite vente = new TvaComposite(TvaComponent.EVENTE);


        TvaComposite ventehcc = new TvaComposite(TvaComponent.EVENTEHCC);

        TvaComposite ventehbe = new TvaComposite(TvaComponent.EVENTEPREHBE);


        TvaComposite venteid = new TvaComposite(TvaComponent.VENTEVID);

        TvaComposite ventees = new TvaComposite(TvaComponent.VENTEES);

        TvaComposite venteauex = new TvaComposite(TvaComponent.VENTEAUEX);

        TvaComposite venteauexinf = new TvaComposite(TvaComponent.VENTEAUEXINF);


        vente.addComponent(venteid, true);

        vente.addComponent(ventees, true);

        vente.addComponent(venteauex, true);

        vente.addComponent(venteauexinf, true);


        vente.addComponent(ventehcc, true);

        vente.addComponent(ventehbe, true);

        vente.setLibelle1(java.util.ResourceBundle.getBundle("srcastra/astra/locale/tva", parent.getCurrentUser().getLangage()).getString("extra"));

        return vente;

    }

    public TvaComponent getTreeVenteInt() {

        TvaComposite vente = new TvaComposite(TvaComponent.IVENTE);


        TvaComposite venteecc = new TvaComposite(TvaComponent.IVENTEECC);

        TvaComposite ventelivins = new TvaComposite(TvaComponent.IVENTELIVINS);

        TvaComposite venteopri = new TvaComposite(TvaComponent.IVENTEOPTRI);

        TvaComposite ventepresin = new TvaComposite(TvaComponent.IVENTEPRESIN);

        TvaComposite venteregdou = new TvaComposite(TvaComponent.IVENTEREGDOU);

        TvaComposite ventesoumtva = new TvaComposite(TvaComponent.IVENTESOUMTVA);

        TvaComposite ventetrabi = new TvaComposite(TvaComponent.IVENTETRABI);

        TvaComposite ventetrafa = new TvaComposite(TvaComponent.IVENTETRAFA);


        TvaComposite venteid = new TvaComposite(TvaComponent.VENTEVID);

        TvaComposite ventees = new TvaComposite(TvaComponent.VENTEES);

        TvaComposite venteauex = new TvaComposite(TvaComponent.VENTEAUEX);

        TvaComposite venteauexinf = new TvaComposite(TvaComponent.VENTEAUEXINF);


        vente.addComponent(venteid, true);

        vente.addComponent(ventees, true);

        vente.addComponent(venteauex, true);

        vente.addComponent(venteauexinf, true);


        vente.addComponent(venteecc, true);

        vente.addComponent(ventelivins, true);

        vente.addComponent(venteopri, true);

        vente.addComponent(ventepresin, true);

        vente.addComponent(venteregdou, true);

        vente.addComponent(ventesoumtva, true);

        vente.addComponent(ventetrabi, true);

        vente.addComponent(ventetrafa, true);

        vente.setLibelle1(java.util.ResourceBundle.getBundle("srcastra/astra/locale/tva", parent.getCurrentUser().getLangage()).getString("intr"));

        return vente;

    }

    public TvaComponent getTreeAchatIntra() {

        TvaComposite achat = new TvaComposite(TvaComponent.IATACHAT);

        //  TvaComposite vente=new TvaComposite(TvaComponent.NATVENTE);

        TvaComposite imarchandise = new TvaComposite(TvaComponent.IMARCHANDISE);

        TvaComposite ibien = new TvaComposite(TvaComponent.IBIEN);

        TvaComposite iservdiv = new TvaComposite(TvaComponent.ISERVDIV);


        TvaComposite iinvest = new TvaComposite(TvaComponent.IINVEST);

        TvaComposite iinvestbien = new TvaComposite(TvaComponent.IINVESTBIEN);

        TvaComposite iinvestbienlivin = new TvaComposite(TvaComponent.IINVESTBIENLIVINS);

        TvaComposite iinvestserdiv = new TvaComposite(TvaComponent.IINVESTSERVDIV);


        TvaComposite iinvestbiended = new TvaComposite(TvaComponent.IINVESTBIENDED);

        TvaComposite iinvestbienNded = new TvaComposite(TvaComponent.IINVESTBIENNDED);

        TvaComposite iinvestbienPded = new TvaComposite(TvaComponent.IINVESTBIENPDED);

        TvaComposite iinvestbienPaysTax = new TvaComposite(TvaComponent.IINVESTBIENTAXPAY);

        TvaComposite iinvestbienAcc = new TvaComposite(TvaComponent.IINVESTBIENACC);


        TvaComposite iinvestbienlivinded = new TvaComposite(TvaComponent.IINVESTBIENLIVINSDED);

        TvaComposite iinvestbienlivinNded = new TvaComposite(TvaComponent.IINVESTBIENLIVINSNDED);

        TvaComposite iinvestbienlivinPded = new TvaComposite(TvaComponent.IINVESTBIENLIVINSPDED);

        TvaComposite iinvestbienlivinPaysTax = new TvaComposite(TvaComponent.IINVESTBIENLIVINSTAXPAY);

        TvaComposite iinvestbienlivinAcc = new TvaComposite(TvaComponent.IINVESTBIENLIVINSACC);


        TvaComposite iinvestserdivded = new TvaComposite(TvaComponent.IINVESTSERVDIVDED);

        TvaComposite iinvestserdivNded = new TvaComposite(TvaComponent.IINVESTSERVDIVNDED);

        TvaComposite iinvestserdivPded = new TvaComposite(TvaComponent.IINVESTSERVDIVPDED);

        TvaComposite iinvestserdivPaysTax = new TvaComposite(TvaComponent.IINVESTSERVDIVTAXPAY);

        TvaComposite iinvestserdivAcc = new TvaComposite(TvaComponent.IINVESTSERVDIVACC);


        TvaComposite iamarchded = new TvaComposite(TvaComponent.IMARCHANDISEDED);

        TvaComposite iamarchNded = new TvaComposite(TvaComponent.IMARCHANDISENDED);

        TvaComposite iamarchPded = new TvaComposite(TvaComponent.IMARCHANDISEPDED);

        TvaComposite iamarchPaysTax = new TvaComposite(TvaComponent.IMARCHANDISETAXPAY);

        TvaComposite iamarchAcc = new TvaComposite(TvaComponent.IMARCHANDISEACC);

        TvaComposite ibiended = new TvaComposite(TvaComponent.IBIENDED);

        TvaComposite ibienNded = new TvaComposite(TvaComponent.IBIENNDED);

        TvaComposite ibienPded = new TvaComposite(TvaComponent.IBIENPDED);

        TvaComposite ibienPaysTax = new TvaComposite(TvaComponent.IBIENTAXPAY);

        TvaComposite ibienAcc = new TvaComposite(TvaComponent.IBIENACC);

        TvaComposite iservdivded = new TvaComposite(TvaComponent.ISERVDIVDED);

        TvaComposite iservdivNded = new TvaComposite(TvaComponent.ISERVDIVNDED);

        TvaComposite iservdivPded = new TvaComposite(TvaComponent.ISERVDIVPDED);

        TvaComposite iservdivPaysTax = new TvaComposite(TvaComponent.ISERVDIVTAXPAY);

        TvaComposite iservdivAcc = new TvaComposite(TvaComponent.ISERVDIVACC);


        TvaComposite avidange = new TvaComposite(TvaComponent.NAVIDANGE);

        TvaComposite aescompte = new TvaComposite(TvaComponent.NAESCOMPTE);

        TvaComposite aexemp = new TvaComposite(TvaComponent.NAAUTREEX);

        TvaComposite aexempca = new TvaComposite(TvaComponent.NAAUTREEXCA);


        TvaComposite isousttrans = new TvaComposite(TvaComponent.ISOUTTRANS);

        TvaComposite isousttransnotaux = new TvaComposite(TvaComponent.ISOUTTRANSNOTAUX);

        TvaComposite isousttrans0 = new TvaComposite(TvaComponent.ISOUTTRANS0);


        TvaComposite itransNoFact = new TvaComposite(TvaComponent.ITRANOFACT);


        iinvestbien.addComponent(iinvestbiended, true);

        iinvestbien.addComponent(iinvestbienNded, true);

        iinvestbien.addComponent(iinvestbienPded, true);

        iinvestbien.addComponent(iinvestbienPaysTax, true);

        iinvestbien.addComponent(iinvestbienAcc, true);

        iinvest.addComponent(iinvestbien, true);


        iinvestbienlivin.addComponent(iinvestbienlivinded, true);

        iinvestbienlivin.addComponent(iinvestbienlivinNded, true);

        iinvestbienlivin.addComponent(iinvestbienlivinPded, true);

        iinvestbienlivin.addComponent(iinvestbienlivinPaysTax, true);

        iinvestbienlivin.addComponent(iinvestbienlivinAcc, true);

        iinvest.addComponent(iinvestbienlivin, true);


        iinvestserdiv.addComponent(iinvestserdivded, true);

        iinvestserdiv.addComponent(iinvestserdivNded, true);

        iinvestserdiv.addComponent(iinvestserdivPded, true);

        iinvestserdiv.addComponent(iinvestserdivPaysTax, true);

        iinvestserdiv.addComponent(iinvestserdivAcc, true);

        iinvest.addComponent(iinvestserdiv, true);


        imarchandise.addComponent(iamarchded, true);

        imarchandise.addComponent(iamarchNded, true);

        imarchandise.addComponent(iamarchPaysTax, true);

        imarchandise.addComponent(iamarchAcc, true);

        imarchandise.addComponent(iamarchPded, true);


        ibien.addComponent(ibiended, true);

        ibien.addComponent(ibienNded, true);

        ibien.addComponent(ibienPaysTax, true);

        ibien.addComponent(ibienAcc, true);

        ibien.addComponent(ibienPded, true);


        iservdiv.addComponent(iservdivded, true);

        iservdiv.addComponent(iservdivNded, true);

        iservdiv.addComponent(iservdivPaysTax, true);

        iservdiv.addComponent(iservdivAcc, true);

        iservdiv.addComponent(iservdivPded, true);


        achat.addComponent(ibien, true);

        achat.addComponent(iservdiv, true);

        achat.addComponent(imarchandise, true);

        achat.addComponent(iinvest, true);

        achat.addComponent(avidange, true);

        achat.addComponent(aescompte, true);

        achat.addComponent(aexemp, true);

        achat.addComponent(aexempca, true);

        achat.addComponent(isousttrans, true);

        achat.addComponent(itransNoFact, true);


        achat.setLibelle1(java.util.ResourceBundle.getBundle("srcastra/astra/locale/tva", parent.getCurrentUser().getLangage()).getString("intr"));

        return achat;

    }

    public TvaComponent getTreeAchatExtra() {


        TvaComposite avidange = new TvaComposite(TvaComponent.NAVIDANGE);

        TvaComposite aescompte = new TvaComposite(TvaComponent.NAESCOMPTE);

        TvaComposite aexemp = new TvaComposite(TvaComponent.NAAUTREEX);

        TvaComposite aexempca = new TvaComposite(TvaComponent.NAAUTREEXCA);


        TvaComposite achat = new TvaComposite(TvaComponent.EACHAT);

        //  TvaComposite vente=new TvaComposite(TvaComponent.NATVENTE);

        TvaComposite emarchandise = new TvaComposite(TvaComponent.EMARCHANDISETRAN);

        TvaComposite ebien = new TvaComposite(TvaComponent.EBIENSERV);

        TvaComposite einvtrans = new TvaComposite(TvaComponent.EINVESTRANS);

        TvaComposite ereport = new TvaComposite(TvaComponent.EREPORT);

        TvaComposite etransnofact = new TvaComposite(TvaComponent.ETRANNOFACT);


        TvaComposite ereportmarch = new TvaComposite(TvaComponent.EREPORTMARCH);

        TvaComposite ereportmarchded = new TvaComposite(TvaComponent.EREPORTMARCHDED);

        TvaComposite ereportmarchNded = new TvaComposite(TvaComponent.EREPORTMARCHNDED);


        TvaComposite ereportbien = new TvaComposite(TvaComponent.EREPORTBIEN);

        TvaComposite ereportbiended = new TvaComposite(TvaComponent.EREPORTBIENDED);

        TvaComposite ereportbienNded = new TvaComposite(TvaComponent.EREPORTBIENNDED);


        TvaComposite ereportinv = new TvaComposite(TvaComponent.EREPORTINV);

        TvaComposite ereportinvded = new TvaComposite(TvaComponent.EREPORTINVDED);

        TvaComposite ereportinvNded = new TvaComposite(TvaComponent.EREPORTINVNDED);


        TvaComposite ereportsertax = new TvaComposite(TvaComponent.EREPORTSERVTAX);


        TvaComposite ereportsertaxmarch = new TvaComposite(TvaComponent.EREPORTSERVTAXMARCH);

        TvaComposite ereportsertaxmarchded = new TvaComposite(TvaComponent.EREPORTSERVTAXMARCHDED);

        TvaComposite ereportsertaxmarchNded = new TvaComposite(TvaComponent.EREPORTSERVTAXMARCHNDED);


        TvaComposite ereportsertaxbien = new TvaComposite(TvaComponent.EREPORTSERVTAXBIEN);

        TvaComposite ereportsertaxbiended = new TvaComposite(TvaComponent.EREPORTSERVTAXBIENDED);

        TvaComposite ereportsertaxbienNded = new TvaComposite(TvaComponent.EREPORTSERVTAXBIENNDED);


        TvaComposite ereportsertaxinv = new TvaComposite(TvaComponent.EREPORTSERVTAXINV);

        TvaComposite ereportsertaxinvded = new TvaComposite(TvaComponent.EREPORTSERVTAXINVDED);

        TvaComposite ereportsertaxinvNded = new TvaComposite(TvaComponent.EREPORTSERVTAXINVNDED);


        ereportsertaxmarch.addComponent(ereportsertaxmarchded, true);

        ereportsertaxmarch.addComponent(ereportsertaxmarchNded, true);


        ereportsertaxbien.addComponent(ereportsertaxbiended, true);

        ereportsertaxbien.addComponent(ereportsertaxbiended, true);


        ereportsertaxinv.addComponent(ereportsertaxinvded, true);

        ereportsertaxinv.addComponent(ereportsertaxinvNded, true);


        ereportsertax.addComponent(ereportsertaxmarch, true);

        ereportsertax.addComponent(ereportsertaxbien, true);

        ereportsertax.addComponent(ereportsertaxinv, true);


        ereportmarch.addComponent(ereportmarchded, true);

        ereportmarch.addComponent(ereportmarchNded, true);


        ereportbien.addComponent(ereportbiended, true);

        ereportbien.addComponent(ereportbienNded, true);


        ereportinv.addComponent(ereportinvded, true);

        ereportinv.addComponent(ereportinvNded, true);


        ereport.addComponent(ereportmarch, true);

        ereport.addComponent(ereportbien, true);

        ereport.addComponent(ereportinv, true);

        ereport.addComponent(ereportsertax, true);


        TvaComposite ereportbienserv = new TvaComposite(TvaComponent.NATABIENSERVE);

        TvaComposite ereportbienservded = new TvaComposite(TvaComponent.NATABIENSERVEDED);

        TvaComposite ereportbienservNded = new TvaComposite(TvaComponent.NATABIENSERVENDED);

        TvaComposite ereportbienservPded = new TvaComposite(TvaComponent.NATABIENSERVEPDED);


        achat.addComponent(emarchandise, true);

        achat.addComponent(ebien, true);

        achat.addComponent(einvtrans, true);

        achat.addComponent(ereport, true);

        achat.addComponent(etransnofact, true);

        achat.addComponent(avidange, true);

        achat.addComponent(aescompte, true);

        achat.addComponent(aexemp, true);

        achat.addComponent(aexempca, true);


        achat.setLibelle1(java.util.ResourceBundle.getBundle("srcastra/astra/locale/tva", parent.getCurrentUser().getLangage()).getString("extra"));

        return achat;


    }

    public TvaComponent getTreeAchatNat() {

        //DefaultMutableTreeNode troot;

        // troot = new DefaultMutableTreeNode("main");

        //  tree=new JTree(troot);

//      parent.remove(tree);

        //    tree.addTreeSelectionListener((javax.swing.event.TreeSelectionListener)parent);

//      ((TvaFrame)parent).getJScrollPane2().getViewport().add(tree);

        //TvaComponent main=new TvaComposite();

        TvaComposite achat = new TvaComposite(TvaComponent.NATACHAT);

        TvaComposite vente = new TvaComposite(TvaComponent.NATVENTE);

        TvaComposite amarchandise = new TvaComposite(TvaComponent.NATMARCHANDISE);

        TvaComposite amarchded = new TvaComposite(TvaComponent.NATMARCHANDISEDED);

        TvaComposite amarchNded = new TvaComposite(TvaComponent.NATMARCHANDISENDED);

        TvaComposite amarchPded = new TvaComposite(TvaComponent.NATMARCHANDISEPDED);

        TvaComposite abienserv = new TvaComposite(TvaComponent.NATABIENSERVE);

        TvaComposite abienservded = new TvaComposite(TvaComponent.NATABIENSERVEDED);

        TvaComposite abienservNded = new TvaComposite(TvaComponent.NATABIENSERVENDED);

        TvaComposite abienservPded = new TvaComposite(TvaComponent.NATABIENSERVEPDED);

        TvaComposite aprest = new TvaComposite(TvaComponent.NATAPRESTATION);

        TvaComposite aprestdiv = new TvaComposite(TvaComponent.NATAPRESTATIONDIV);

        TvaComposite aprestim = new TvaComposite(TvaComponent.NATAPRESTATIONMOB);

        TvaComposite ainvest = new TvaComposite(TvaComponent.NATAINVEST);

        TvaComposite ainvestded = new TvaComposite(TvaComponent.NATAINVESTDED);

        TvaComposite ainvestNded = new TvaComposite(TvaComponent.NATAINVESTNDED);

        TvaComposite ainvestPded = new TvaComposite(TvaComponent.NATAINVESTPDED);

        TvaComposite ainvcoim = new TvaComposite(TvaComponent.NATAINVCOIM);

        TvaComposite ainvcoimded = new TvaComposite(TvaComponent.NATAINVCOIMDED);

        TvaComposite ainvcoimNded = new TvaComposite(TvaComponent.NATAINVCOIMNDED);

        TvaComposite ainvcoimPded = new TvaComposite(TvaComponent.NATAINVCOIMPDED);

        TvaComposite acoim = new TvaComposite(TvaComponent.NATACOIM);

        TvaComposite acoimded = new TvaComposite(TvaComponent.NATACOIMDED);

        TvaComposite acoimNded = new TvaComposite(TvaComponent.NATACOIMNDED);

        TvaComposite acoimPded = new TvaComposite(TvaComponent.NATACOIMPDED);

        TvaComposite avidange = new TvaComposite(TvaComponent.NAVIDANGE);

        TvaComposite aescompte = new TvaComposite(TvaComponent.NAESCOMPTE);

        TvaComposite aexemp = new TvaComposite(TvaComponent.NAAUTREEX);

        TvaComposite aexempca = new TvaComposite(TvaComponent.NAAUTREEXCA);

        // main.addComponent(achat,true);

        achat.setLibelle1(java.util.ResourceBundle.getBundle("srcastra/astra/locale/tva", parent.getCurrentUser().getLangage()).getString("nat"));

        achat.addComponent(amarchandise, true);

        achat.addComponent(abienserv, true);

        achat.addComponent(aprest, true);

        achat.addComponent(ainvest, true);

        achat.addComponent(ainvcoim, true);

        achat.addComponent(acoim, true);

        achat.addComponent(avidange, true);

        achat.addComponent(aescompte, true);

        achat.addComponent(aexemp, true);

        achat.addComponent(aexempca, true);

        abienserv.addComponent(abienservded, true);

        abienserv.addComponent(abienservNded, true);

        abienserv.addComponent(abienservPded, true);

        amarchandise.addComponent(amarchded, true);

        amarchandise.addComponent(amarchNded, true);

        amarchandise.addComponent(amarchPded, true);

        aprest.addComponent(aprestdiv, true);

        aprest.addComponent(aprestim, true);

        ainvest.addComponent(ainvestded, true);

        ainvest.addComponent(ainvestNded, true);

        ainvest.addComponent(ainvestPded, true);

        ainvcoim.addComponent(ainvcoimded, true);

        ainvcoim.addComponent(ainvcoimNded, true);

        ainvcoim.addComponent(ainvcoimPded, true);

        acoim.addComponent(acoimded, true);

        acoim.addComponent(acoimNded, true);

        acoim.addComponent(acoimPded, true);

        //  main.addComponent(vente,true);

        // main.addComponent(new TvaComposite(TvaComponent.ACHAT,tree));

        //main.addComponent(new TvaComposite(TvaComponent.VENTE,tree));

        //    main.node=troot;

        return achat;

    }


}

