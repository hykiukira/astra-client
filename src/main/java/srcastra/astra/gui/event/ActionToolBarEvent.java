/*







 * ActionToolBarEvent.java







 *







 * Created on 3 avril 2002, 12:00







 */


package srcastra.astra.gui.event;


/**
 * @author Sébastien
 */


public class ActionToolBarEvent extends java.awt.AWTEvent {


    public static final int ACTION_TOOLBAR_EVENT = java.awt.AWTEvent.RESERVED_ID_MAX + 5555;


    private String action;


    /**
     * Creates new ActionToolBarEvent
     */


    public ActionToolBarEvent(srcastra.astra.gui.components.actions.actionToolBar.ActionToolBar source, String action) {


        super(source, ACTION_TOOLBAR_EVENT);


        this.action = action;


    }


    public String getAction() {


        return this.action;


    }


}







