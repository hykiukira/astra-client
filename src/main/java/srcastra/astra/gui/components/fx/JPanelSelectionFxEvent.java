/*





 * JPanelSelectionFxEvent.java





 *





 * Created on 2 septembre 2002, 12:44





 */


package srcastra.astra.gui.components.fx;


/**
 * @author Sébastien
 */


public class JPanelSelectionFxEvent extends java.awt.event.ActionEvent {


    private JPanelSelectionFx fx;


    /**
     * Creates a new instance of JPanelSelectionFxEvent
     */


    public JPanelSelectionFxEvent(Object source, int id, String command) {


        super(source, id, command);


        this.fx = (JPanelSelectionFx) source;


    }


    public JPanelSelectionFx getJPanelSelectionFx() {


        return fx;


    }


}





