/*

 * M_actionEvent.java

 *

 * Created on 27 mai 2003, 16:29

 */


package srcastra.astra.gui.components.actions.actionToolBar;


/**
 * @author Thomas
 */

public class M_actionEvent extends java.awt.event.ActionEvent {


    /**
     * Creates a new instance of M_actionEvent
     */

    public M_actionEvent(Object source, int id, String command) {

        super(source, id, command);

    }

    public void cancel(java.awt.event.ActionEvent evt) {

        // java.awt.event.ActionEvent evt=super.clone();

    }


}

