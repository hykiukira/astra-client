/*
 * DataContainerListener.java
 *
 * Created on 6 septembre 2002, 9:45
 */

package srcastra.astra.gui.sys.tableModel;
import java.util.EventListener;

/**
 *
 * @author  Sébastien
 */
public interface DataContainerListener extends EventListener {
    
    public void dataChanged(DataContainerEvent evt);    
}
