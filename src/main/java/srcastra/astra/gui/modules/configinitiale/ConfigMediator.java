/*

 * ConfigMediator.java

 *

 * Created on 5 novembre 2003, 11:14

 */


package srcastra.astra.gui.modules.configinitiale;

import srcastra.astra.gui.components.actions.actionToolBar.*;

/**
 * @author Thomas
 */

public class ConfigMediator {


    /**
     * Creates a new instance of ConfigMediator
     */

    javax.swing.JLayeredPane layeredpane;

    GeneralConfig genconf;

    EntitePane eniteconf;

    UserCOnfig userconf;

    public ConfigMediator(javax.swing.JLayeredPane layeredpane) {

        this.layeredpane = layeredpane;

    }


    public void nexPanel(Object panel) {

        System.out.println("next panel");

        if (panel instanceof GeneralConfig)

        {

            System.out.println("oki generalconfig");

            layeredpane.moveToBack(genconf);

            layeredpane.moveToFront(eniteconf);

            eniteconf.activeToolbar(ActionToolBar.ACT_READ);

        } else if (panel instanceof EntitePane)

        {

            System.out.println("oki generalconfig");

            layeredpane.moveToBack(eniteconf);

            layeredpane.moveToFront(userconf);

        }


    }

    /**
     * Getter for property genconf.
     *
     * @return Value of property genconf.
     */


    public srcastra.astra.gui.modules.configinitiale.GeneralConfig getGenconf() {

        return genconf;

    }


    /**
     * Setter for property genconf.
     *
     * @param genconf New value of property genconf.
     */

    public void setGenconf(srcastra.astra.gui.modules.configinitiale.GeneralConfig genconf) {

        // layeredpane.add(genconf,2);

        this.genconf = genconf;

        //  layeredpane.moveToFront(genconf);

    }


    /**
     * Getter for property eniteconf.
     *
     * @return Value of property eniteconf.
     */


    public void setCurrentPanel() {


    }


    /**
     * Getter for property eniteconf.
     *
     * @return Value of property eniteconf.
     */

    public srcastra.astra.gui.modules.configinitiale.EntitePane getEniteconf() {

        return eniteconf;

    }


    /**
     * Setter for property eniteconf.
     *
     * @param eniteconf New value of property eniteconf.
     */

    public void setEniteconf(srcastra.astra.gui.modules.configinitiale.EntitePane eniteconf) {

        //layeredpane.add(eniteconf,1);

        this.eniteconf = eniteconf;

    }


    /**
     * Getter for property userconf.
     *
     * @return Value of property userconf.
     */

    public srcastra.astra.gui.modules.configinitiale.UserCOnfig getUserconf() {

        return userconf;

    }


    /**
     * Setter for property userconf.
     *
     * @param userconf New value of property userconf.
     */

    public void setUserconf(srcastra.astra.gui.modules.configinitiale.UserCOnfig userconf) {

        this.userconf = userconf;

    }

    /** Setter for property eniteconf.

     * @param eniteconf New value of property eniteconf.

     *

     */


}

