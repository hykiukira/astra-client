/*
 * JGoodiesConfigFourn.java
 *
 * Created on 5 octobre 2005, 14:06
 */

package srcastra.astra.gui.list;

/**
 *
 * @author Administrateur
 */

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

public class JGoodiesConfigFourn implements JGoodiesConfig {


    /**
     * Creates a new instance of JGoodiesConfig
     */

    Loginusers_T currentUser;

    String langue;

    astrainterface serveur;

    boolean admin;

    public JGoodiesConfigFourn(String langue, Loginusers_T currentUser, astrainterface serveur) {

        this.langue = langue;

        this.serveur = serveur;

        this.currentUser = currentUser;

        if (this.currentUser.getUrrights() == 1)

            admin = true;

        initComponents();

    }

    public int getEntite() {

        System.out.println("cle " + sucursaleL.getCleUnik() + " text " + sucursaleL.getText());

        return sucursaleL.getCleUnik();

    }

    public String getEntiteText() {

        System.out.println("cle " + sucursaleL.getCleUnik() + " text " + sucursaleL.getText());

        return sucursaleL.getText();

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

    /*        public String getCreationDebText(){

        Date date=dCreationD.getDate();

        if(date==null ||date.isOpen() || date.isUnknow()){

         return "??-??-????";  

        }

        return date.toString2();

        

    }
    
     public String getCreationDeb(){

        Date date=dCreationD.getDate();
        date.setHours(0);
         date.setMinutes(0);
         date.setSeconds(0);

        if(date==null ||date.isOpen() || date.isUnknow()){

          return "0000-00-00 00:00:00";   

        }

        return date.toString();

    }*/


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


    public String getDe() {

        return dCreationD.getText();

    }

    public String getA() {

        return dCreationF.getText();

    }

    /*  public String getCreationFin(){

         Date date=dCreationF.getDate();
         date.setHours(23);
         date.setMinutes(59);
         date.setSeconds(59);

        if(date==null ||date.isOpen() || date.isUnknow()){

          return "9999-01-01 00:00:00";   

        }

        return date.toString();

        

    }
    
      public String getCreationFinText(){

        Date date=dCreationF.getDate();

        if(date==null ||date.isOpen() || date.isUnknow()){

         return "??-??-????";  

        }

        return date.toString2();

        

    }*/


    public String getDepartFinText() {

        Date date = departF.getDate();

        if (date == null || date.isOpen() || date.isUnknow()) {

            return "??-??-????";

        }

        return date.toString2();


    }


    private void initListe() {


    }

    private void initComponents() {

        separator1 = java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_dattitle1");

        //----

        //dCreation=new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_dep1"));


        de1 = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("do_de"));

        dCreationD = new JTextField(15);

        a1 = new JLabel(java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_a"));

        dCreationF = new JTextField(15);

        //dCreation=new ACheckBox();


        separator2 = "";

        //separator3=java.util.ResourceBundle.getBundle("srcastra/astra/locale/listing", currentUser.getLangage()).getString("dos_tile");


        separator = new String[2];


        separator[0] = separator1;

        separator[1] = separator2;


        component = new Object[6][5];

        //component[0][2]=new JComponent[]{dCreation};

        component[0][3] = new JComponent[]{de1, dCreationD, a1, dCreationF};

        //   separator[1]=separator2;

        //   separator[2]=separator3;


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

    /** Getter for property grp_prod.

     * @return Value of property grp_prod.

     *

     */

    /** Setter for property grp_prod.

     * @param grp_prod New value of property grp_prod.

     *

     */


    /**
     * Setter for property bdc.
     *
     * @param bdc New value of property bdc.
     */


    String[] separator;

    Object[][] component;

    String separator1;

    String separator2;

    String separator3;


    ADate departD;

    ADate departF;

    JTextField dCreationD;

    JTextField dCreationF;


    JLabel departJ;

    JLabel dCreation;

    JLabel de;

    JLabel a;

    JLabel de1;

    JLabel a1;

    ACheckBox depart;

    ACheckBox creation;

    JLabel sucursale;
    Liste sucursaleL;






}



