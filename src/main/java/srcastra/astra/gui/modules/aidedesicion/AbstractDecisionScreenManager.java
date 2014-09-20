/*

 * AbstraDecisionScreenManager.java

 *

 * Created on 6 mars 2003, 11:12

 */


package srcastra.astra.gui.modules.aidedesicion;

import java.util.*;

import javax.swing.*;

/**
 * @author Thomas
 */

public abstract class AbstractDecisionScreenManager {


    /**
     * Creates a new instance of  AbstraDecisionScreenManager
     */

    public AbstractDecisionScreenManager() {

    }

    public abstract void doPrevious();

    public abstract ArrayList genereUneEntree();

    public abstract void addData(ArrayList entree);

    public abstract void addData(ArrayList entree, int id, long timestamp);

    public abstract boolean checkLangue();

    public abstract void displayInsertMode();

    public abstract void displayReadMode();

    public abstract void doCancel();

    public abstract void doCreate();

    public abstract void doDelete();

    public abstract void doClose();

    public abstract void reloadTable();

    public abstract int[] getDefaultActionToolBarMask();

    public abstract JRadioButton getSelectComponent();

    public abstract void selectComponent(boolean after);

    public abstract void displayUpdateMode();

    public abstract boolean isMultiproduct();

    public abstract void setMultiproduct(boolean multiproduct);


}

