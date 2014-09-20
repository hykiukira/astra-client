/*

 * JGoodiesConfigMain.java

 *

 * Created on 7 novembre 2003, 9:25

 */


package srcastra.astra.gui.list;

import javax.swing.*;

/**
 * @author Thomas
 */

public class JGoodiesConfigMain implements JGoodiesConfig {


    /**
     * Creates a new instance of JGoodiesConfigMain
     */

    public JGoodiesConfigMain(JPanel[] paneltab) {

        component = new Object[1][1];

        component[0][0] = paneltab;

    }

    public java.lang.Object[][] getComponent() {

        return component;

    }


    public java.lang.String[] getSeparator() {

        return null;

    }

    Object[][] component;

}

