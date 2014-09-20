/*

 * GlobalInterface.java

 *

 * Created on 8 juillet 2003, 9:39

 */


package srcastra.astra.gui.modules.config;

import srcastra.astra.sys.rmi.*;


/**
 * @author Thomas
 */

public interface GlobalInterface {

    public void updateFields();

    public Object getObject();

    public GlobalRmiInterface getRmiInteface();

    public void setFields();

    public javax.swing.JTable getTable();

    boolean isOkPass();

    public srcastra.astra.gui.modules.config.user.Pwd getPass();

}

