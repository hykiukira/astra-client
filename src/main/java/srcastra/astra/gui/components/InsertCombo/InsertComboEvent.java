/*





 * InsertComboEvent.java





 *





 * Created on 12 août 2002, 11:49





 */


package srcastra.astra.gui.components.InsertCombo;


import java.awt.event.ActionEvent;


/**
 * @author Sébastien
 */


public class InsertComboEvent extends ActionEvent {


    private int state;


    private int environnement;


    /**
     * Creates a new instance of InsertComboEvent
     */


    public InsertComboEvent(Object source, int id, String command, int state, int environnement) {


        super(source, id, command);


        this.state = state;


        this.environnement = environnement;


    }


    public int getState() {


        return state;


    }


    public void setState(int state) {


        this.state = state;


    }


    public void setEnvironnement(int env) {


        this.environnement = env;


    }


    public int getEnvironnement() {


        return environnement;


    }


}





