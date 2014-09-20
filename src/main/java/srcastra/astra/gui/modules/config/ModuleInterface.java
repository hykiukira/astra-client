/*

 * ModuleInterface.java

 *

 * Created on 8 juillet 2003, 10:14

 */


package srcastra.astra.gui.modules.config;

import srcastra.astra.gui.components.actions.actionToolBar.*;


/**
 * @author Thomas
 */

public interface ModuleInterface {

    public srcastra.astra.gui.modules.config.GlobalInterface[] getPanel();

    public void setSelectedPanel(int selectedPanel);

    public void next();

    public void previous();

    public int isBoderPaner();

    public ToolBarComposer getToolPanel();

    public void enableJtabPane(boolean enable);

}

