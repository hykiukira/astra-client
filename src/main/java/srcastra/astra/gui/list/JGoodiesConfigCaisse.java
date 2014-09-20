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

public class JGoodiesConfigCaisse implements JGoodiesConfig {


    /**
     * Creates a new instance of JGoodiesConfig
     */

    Loginusers_T currentUser;

    String langue;

    astrainterface serveur;

    boolean admin;

    public JGoodiesConfigCaisse(String langue, Loginusers_T currentUser, astrainterface serveur) {

        this.langue = langue;

        this.serveur = serveur;

        this.currentUser = currentUser;

        if (this.currentUser.getUrrights() == 1)

            admin = true;

        // initListe();

        initComponents();

    }


    private void initListe() {


        sucursaleL.setServeur(serveur);

        sucursaleL.setName("lc");

        sucursaleL.setLogin(currentUser);

        sucursaleL.setModel(new srcastra.astra.gui.components.combobox.liste.EntiteListeTableModel(serveur, currentUser));

        sucursaleL.init2();
        userL.setServeur(serveur);
        userL.setName("lc");
        userL.setLogin(currentUser);
        userL.setModel(new srcastra.astra.gui.components.combobox.liste.UserListeTableModel(serveur, currentUser));
        userL.init2();
        if (!admin) {

            sucursaleL.setEnabled(false);

            sucursaleL.setCleUnik(this.currentUser.getUreecleunik());
            if (srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getCaisseparutilisateur() == 1) {
                userL.setCleUnik(this.currentUser.getCle2());
                userL.setEnabled(false);
            }

        }

        //  sucursaleL.setLeft(true);

        // userL.setLeft(true);

        // userL=new Liste();

        /*    periodeL=new Liste();

     periodeL.setServeur(serveur);

     periodeL.setName("lc");

     periodeL.setLogin(currentUser);

     periodeL.setModel(new srcastra.astra.gui.components.combobox.liste.UserListeTableModel(serveur,currentUser));

     periodeL.init2();   */

        // periodeL=new Liste();


    }

    public Date getDatebeg() {

        return datedeb.getDate();

    }

    public Date getDateend() {

        return dateend.getDate();

    }

    public int getUser() {

        System.out.println("user cle " + userL.getCleUnik() + " text " + userL.getText());

        return userL.getCleUnik();

    }

    public String getUserText() {

        System.out.println("user cle " + userL.getCleUnik() + " text " + userL.getText());

        return userL.getText();

    }

    public int getEntite() {

        System.out.println("cle " + sucursaleL.getCleUnik() + " text " + sucursaleL.getText());

        return sucursaleL.getCleUnik();

    }

    public String getEntiteText() {

        System.out.println("cle " + sucursaleL.getCleUnik() + " text " + sucursaleL.getText());

        return sucursaleL.getText();

    }

    private void initComponents() {

        separator1 = java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("param");

        separator2 = java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("periode");

        sucursale = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("sucursale"));

        user = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("user"));

        periode = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("periode"));

        datedeb = new ADate();

        dateend = new ADate();

        de = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("do_de"));

        a = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_a"));

        userL = new Liste();

        sucursaleL = new Liste();

        separator = new String[2];

        separator[0] = separator1;

        component = new Object[2][6];

        component[0][0] = new JComponent[]{sucursale, sucursaleL};

        component[0][1] = new JComponent[]{user, userL};

        separator[1] = separator2;

        component[1][0] = new JComponent[]{de, datedeb, a, dateend};

        //component[0][3]=new JComponent[]{de1,creationD,a1,creationF};

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


    String[] separator;

    Object[][] component;

    String separator1;

    String separator2;

    String separator3;


    JLabel user;

    JLabel periode;

    JLabel de;

    JLabel a;
    JLabel sucursale;
    Liste sucursaleL;

    Liste userL;

    ADate datedeb;

    ADate dateend;


}

