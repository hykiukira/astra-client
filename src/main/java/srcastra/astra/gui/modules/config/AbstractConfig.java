/*





 * AbstractConfig.java





 *





 * Created on 24 janvier 2003, 12:33





 */


package srcastra.astra.gui.modules.config;


import srcastra.astra.gui.modules.aidedesicion.*;


import java.util.*;


import srcastra.astra.gui.components.AstraComponent;


import javax.swing.*;


/**
 * @author Thomas
 */


public abstract class AbstractConfig extends javax.swing.JPanel {


    /**
     * Creates a new instance of AbstractConfig
     */


    public AbstractConfig() {


    }


    public abstract void setElementFocus();


    public abstract java.awt.Component getMe();


    public abstract int[] getDefaultActionToolBarMask();


    public abstract Hashtable getHashCompLangue();


    public abstract ArrayList getHashCompLangue2();


    public abstract AbstractBuffer getAbstractBuffer();


    public int mode;


    public int typeProduit;


    public int idToModiFy;


    public int tmpId = 0;


    public abstract AstraComponent[] getComponantToVerif();


    public abstract void enableComponent(boolean enabled);


    public abstract ButtonGroup getButtonGroup();


    public int idToDelete;


    public abstract Hashtable getCompBefore();


    public abstract Hashtable getCompAfter();

    // public abstract void enableComponent(boolean enabled);


}





