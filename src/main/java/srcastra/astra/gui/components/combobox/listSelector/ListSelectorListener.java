/*





 * ListSelectorListener.java





 *





 * Created on 6 septembre 2002, 10:52





 */


package srcastra.astra.gui.components.combobox.listSelector;


import java.util.EventListener;


/**
 * @author Thomas
 */


public interface ListSelectorListener extends EventListener {


    public void focusGained(ListSelectorEvent evt);


    public void focusLost(ListSelectorEvent evt);


}





