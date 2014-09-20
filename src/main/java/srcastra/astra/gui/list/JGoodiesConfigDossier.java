/*

 * JGoodiesConfig.java

 *

 * Created on 6 novembre 2003, 14:22

 */


package srcastra.astra.gui.list;

import javax.swing.*;

import srcastra.astra.gui.components.date.thedate.*;

import srcastra.astra.gui.components.textFields.*;

import srcastra.astra.gui.components.checkbox.*;

import srcastra.astra.gui.components.combobox.liste2.*;

import srcastra.astra.gui.components.combobox.liste.*;

import srcastra.astra.sys.classetransfert.*;

import java.util.Locale;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.sys.classetransfert.utils.*;


/**
 * @author Thomas
 */

public class JGoodiesConfigDossier implements JGoodiesConfig {


    /**
     * Creates a new instance of JGoodiesConfig
     */

    Loginusers_T currentUser;

    String langue;

    astrainterface serveur;

    boolean admin;

    public JGoodiesConfigDossier(String langue, Loginusers_T currentUser, astrainterface serveur) {

        this.langue = langue;

        this.serveur = serveur;

        this.currentUser = currentUser;

        if (this.currentUser.getUrrights() == 1)

            admin = true;

        initComponents();

    }

    public String getClient() {

        return client.getCleUnik() == 0 ? "%" : new Integer(client.getCleUnik()).toString();


    }

    public String getClientText() {

        return client.getText();


    }

    public String getClientCont() {

        return clientcon.getCleUnik() == 0 ? "%" : new Integer(clientcon.getCleUnik()).toString();


    }

    public String getClientContText() {

        return clientcon.getText();


    }

    public String getDepartDeb() {

        Date date = departD.getDate();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);

        if (date == null || date.isOpen() || date.isUnknow()) {

            return "0000-00-00 00:00:00";

        }

        return date.toString();

    }

    public String getDepartDebText() {

        Date date = departD.getDate();


        if (date == null || date.isOpen() || date.isUnknow()) {

            return "??-??-????";

        }

        return date.toString2();

    }

    public String getDepartFin() {

        Date date = departF.getDate();
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);

        if (date == null || date.isOpen() || date.isUnknow()) {

            return "9999-01-01 00:00:00";

        }

        return date.toString();


    }

    public String getDepartFinText() {

        Date date = departF.getDate();

        if (date == null || date.isOpen() || date.isUnknow()) {

            return "??-??-????";

        }

        return date.toString2();


    }

    public String getCreationDeb() {

        Date date = creationD.getDate();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);

        if (date == null || date.isOpen() || date.isUnknow()) {

            return "0000-00-00 00:00:00";

        }

        return date.toString();


    }

    public String getCreationDebText() {

        Date date = creationD.getDate();

        if (date == null || date.isOpen() || date.isUnknow()) {

            return "??-??-????";

        }

        return date.toString2();


    }

    public String getCreationFin() {

        Date date = creationF.getDate();
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);

        if (date == null || date.isOpen() || date.isUnknow()) {

            return "9999-01-01 00:00:00";

        }

        return date.toString();


    }

    public String getCreationFinText() {

        Date date = creationF.getDate();

        if (date == null || date.isOpen() || date.isUnknow()) {

            return "??-??-????";

        }

        return date.toString2();


    }

    public String getSolde() {

        return solde.isSelected() ? "0" : "%";


    }

    public String getSoldeText() {

        return solde.isSelected() ? "NON" : "TOUS";


    }

    public String getFacture() {

        return facture.isSelected() ? "0" : "%";


    }

    public String getFactureText() {

        return facture.isSelected() ? "NON" : "TOUS";


    }

    public String getFournisseur() {

        return fournl.getCleUnik() == 0 ? "%" : new Integer(fournl.getCleUnik()).toString();

    }

    public String getFournisseurText() {

        return fournl.getText();//getCleUnik()==0?"%":new Integer(fournl.getCleUnik()).toString();

    }

    public String getPassager() {

        return passa.getText();


    }

    public String getDestination() {

        return destin.getText();

    }

    public String getDestinationText() {

        return destin.getText();//getCleUnik()==0?"%":new Integer(destin.getCleUnik()).toString();

    }

    public String getPo() {

        return pot.getText();


    }

    public int getUser() {

        System.out.println("user cle " + user.getCleUnik() + " text " + user.getText());

        return user.getCleUnik();

    }

    public String getNumdos() {

        return numdost.getText();


    }

    public String getEntite() {

        return sucursale.getCleUnik() == 0 ? "%" : new Integer(sucursale.getCleUnik()).toString();


    }

    public String getEntiteText() {

        return sucursale.getText();//getCleUnik()==0?"%":new Integer(sucursale.getCleUnik()).toString();


    }

    public String getGrpProd() {

        return grp_prod.getCleUnik() == 0 ? "%" : new Integer(grp_prod.getCleUnik()).toString();


    }

    public String getGrpProdText() {

        return grp_prod.getText();//getCleUnik()==0?"%":new Integer(grp_prod.getCleUnik()).toString();


    }

    public String getProd() {

        return typeprodl.getText();


    }

    public String getBdc() {

        return bdc.isSelected() ? "0" : "%";


    }

    public String getBdcText() {

        return bdc.isSelected() ? "NON" : "TOUS";


    }

    public String getAnnuler() {

        return annul.isSelected() ? "1" : "%";


    }

    public String getAnnulerText() {

        return annul.isSelected() ? "OUI" : "TOUS";


    }

    private void initListe() {

        client.setServeur(serveur);

        client.setName("lc");

        client.setLogin(currentUser);

        client.setModel(new srcastra.astra.gui.components.combobox.liste2.listemodel.SousClientListeTableModel(serveur, currentUser));

        client.init2();

        client.setLeft(true);


        client.setLogin(currentUser);

        //client.setModel(new srcastra.astra.gui.components.combobox.liste2.listemodel.SousClientListeTableModel(serveur,currentUser));

        client.init2();

        client.setLeft(true);

        user.setServeur(serveur);
        user.setName("lc");
        user.setLogin(currentUser);


        user.setModel(new srcastra.astra.gui.components.combobox.liste.UserListeTableModel(serveur, currentUser));
        user.init2();


        clientcon.setServeur(serveur);

        clientcon.setName("lc");

        clientcon.setLogin(currentUser);

        clientcon.setModel(new srcastra.astra.gui.components.combobox.liste2.listemodel.SousClientListeTableModel(serveur, currentUser));

        clientcon.init2();

        clientcon.setLeft(true);


        fournl.setServeur(serveur);

        fournl.setLogin(currentUser);

        fournl.setModel(new srcastra.astra.gui.components.combobox.liste.FournisseurListeTableModel(serveur, currentUser));

        fournl.init2();


        destin.setServeur(serveur);

        destin.setLogin(currentUser);

        destin.setModel(new srcastra.astra.gui.components.combobox.liste.DestinationtListeTableModel(serveur, currentUser));

        // destin.setClesignalitique(astrainterface.COMBO_DESTINATION);

        // destin.setInterfaceManipulePanel(parent.getMainframe());

        destin.init2();


        typeprodl.setServeur(serveur);

        typeprodl.setLogin(currentUser);

        typeprodl.setModel(new srcastra.astra.gui.components.combobox.liste.TypeProduitListeTableModel(serveur, currentUser));

        // destin.setClesignalitique(astrainterface.COMBO_DESTINATION);

        // destin.setInterfaceManipulePanel(parent.getMainframe());

        typeprodl.init2();


        grp_prod.setServeur(serveur);

        grp_prod.setLogin(currentUser);

        grp_prod.setModel(new srcastra.astra.gui.components.combobox.liste.GrpProdListeTableModel(serveur, currentUser));

        // destin.setClesignalitique(astrainterface.COMBO_DESTINATION);

        // destin.setInterfaceManipulePanel(parent.getMainframe());

        grp_prod.init2();


        sucursale.setServeur(serveur);

        sucursale.setLogin(currentUser);

        sucursale.setModel(new srcastra.astra.gui.components.combobox.liste.EntiteListeTableModel(serveur, currentUser));

        // destin.setClesignalitique(astrainterface.COMBO_DESTINATION);

        // destin.setInterfaceManipulePanel(parent.getMainframe());

        sucursale.init2();

        if (!admin) {

            sucursale.setEnabled(false);

            sucursale.setCleUnik(this.currentUser.getUreecleunik());

        }


    }

    private void initComponents() {

        separator1 = java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_dattitle");

        // separator1.

        departJ = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_dep"));

        depart = new ACheckBox();

        de = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("do_de"));

        departD = new ADate();

        a = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_a"));

        departF = new ADate();


        creationJ = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_cre"));

        creation = new ACheckBox();

        de1 = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("do_de"));

        creationD = new ADate();

        a1 = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_a"));

        creationF = new ADate();


        separator2 = java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_comp");

        soldeL = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_so"));

        solde = new ACheckBox();

        factureL = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_fact"));

        facture = new ACheckBox();


        separator3 = java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_tile");

        cli = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("doc_cli"));

        client = new Liste2();

        clicon = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_clic"));

        clientcon = new Liste2();


        fourn = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_fou"));

        fournl = new Liste();

        dest = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_dest"));

        destin = new Liste();


        pass = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_pas"));

        passa = new Liste();

        po = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_po"));

        pot = new ATextField();


        numdos = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_num"));

        numdost = new ATextField();

        typeprod = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_per"));

        typeprodl = new Liste();


        grp_prodL = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("grp_prod"));

        grp_prod = new Liste();


        bdcL = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("bdc"));

        annulerL = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("annul"));

        bdc = new ACheckBox();

        annul = new ACheckBox();

        sucursaleL = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("sucursale"));

        sucursale = new Liste();

        // numfacl=new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("dos_date"));

        //numncl=new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("dos_so"));

        separator = new String[3];


        triJ = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("tri"));

        tri = new Liste();

        userJ = new JLabel("User");
        user = new Liste();


        separator[0] = separator1;


        component = new Object[4][8];

        component[0][0] = new JComponent[]{departJ};

        component[0][1] = new JComponent[]{de, departD, a, departF};

        component[0][2] = new JComponent[]{creationJ};

        component[0][3] = new JComponent[]{de1, creationD, a1, creationF};


        separator[1] = separator2;

        component[1][0] = new JComponent[]{soldeL, solde, factureL, facture};


        separator[2] = separator3;

        component[2][0] = new JComponent[]{cli, client, clicon, clientcon};

        component[2][1] = new JComponent[]{fourn, fournl, dest, destin};

        component[2][2] = new JComponent[]{pass, passa, po, pot};

        component[2][3] = new JComponent[]{numdos, numdost, typeprod, typeprodl};

        component[2][4] = new JComponent[]{grp_prodL, grp_prod, sucursaleL, sucursale};

        component[2][5] = new JComponent[]{bdcL, bdc, annulerL, annul};

        component[2][6] = new JComponent[]{userJ, user};

        //component[2][7]=new JComponent[]{userJ,user};

        new ConfigComponent().config(component);

        initListe();

    }


    /**
     * Getter for property separator.
     *
     * @return Value of property separator.
     */

    public java.lang.String[] getSeparator() {

        return this.separator;

    }


    /**
     * Setter for property separator.
     *
     * @param separator New value of property separator.
     */

    public void setSeparator(java.lang.String[] separator) {

        this.separator = separator;

    }


    /**
     * Getter for property component.
     *
     * @return Value of property component.
     */

    public java.lang.Object[][] getComponent() {

        return this.component;

    }


    /**
     * Setter for property component.
     *
     * @param component New value of property component.
     */

    public void setComponent(java.lang.Object[][] component) {

        this.component = component;

    }


    /**
     * Getter for property grp_prod.
     *
     * @return Value of property grp_prod.
     */

    public srcastra.astra.gui.components.combobox.liste.Liste getGrp_prod() {

        return grp_prod;

    }


    /**
     * Setter for property grp_prod.
     *
     * @param grp_prod New value of property grp_prod.
     */

    public void setGrp_prod(srcastra.astra.gui.components.combobox.liste.Liste grp_prod) {

        this.grp_prod = grp_prod;

    }


    /**
     * Setter for property bdc.
     *
     * @param bdc New value of property bdc.
     */

    public srcastra.astra.gui.components.checkbox.ACheckBox getBdcCheck() {

        return bdc;

    }


    String[] separator;

    Object[][] component;

    String separator1;

    String separator2;

    String separator3;

    JLabel de;

    JLabel a;

    JLabel de1;

    JLabel a1;

    JLabel numfacl;

    JLabel numncl;

    JLabel cli;

    JLabel clicon;

    JLabel fourn;

    JLabel dest;

    JLabel pass;

    JLabel po;

    JLabel numdos;

    JLabel typeprod;

    JLabel soldeL;

    JLabel factureL;


    JLabel grp_prodL;

    JLabel bdcL;

    JLabel annulerL;


    Liste grp_prod;

    ACheckBox bdc;

    ACheckBox annul;


    JLabel sucursaleL;

    Liste sucursale;


    ACheckBox depart;

    ACheckBox creation;

    ACheckBox solde;

    ACheckBox facture;

    ATextField numfac;

    ATextField numnc;

    Liste2 client;

    Liste2 clientcon;

    Liste passa;

    Liste destin;

    Liste fournl;

    ATextField pot;

    ATextField numdost;

    Liste typeprodl;

    Liste user;

    ADate departD;

    ADate departF;

    ADate creationD;

    ADate creationF;

    JLabel departJ;

    JLabel creationJ;

    JLabel userJ;


    Liste tri;

    JLabel triJ;

}

