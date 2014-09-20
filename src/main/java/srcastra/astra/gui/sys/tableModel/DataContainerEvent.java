/*
 * DataContainerEvent.java
 *
 * Created on 6 septembre 2002, 9:46
 */

package srcastra.astra.gui.sys.tableModel;
import java.awt.event.ActionEvent;

/**
 *
 * @author  Sébastien
 */
public class DataContainerEvent extends ActionEvent {
    
    public static final int DATASTATE_EMPTY = 0;
    public static final int DATASTATE_FULL = 1;
    public static final int DATASTATE_LOADING = 2;
    
    
    private int state;
    
    /** Creates a new instance of DataContainerEvent */
    public DataContainerEvent(Object source, int state) {
        super(source, DataContainerEvent.ACTION_PERFORMED, "DataContainerEvent");
    }
    
    /** Getter for property state.
     * @return Value of property state.
     */
    public int getState() {
        return state;
    }
    
    /** Setter for property state.
     * @param state New value of property state.
     */
    public void setState(int state) {
        this.state = state;
    }
    
}
