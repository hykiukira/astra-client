/*




 * Config.java




 *




 * Created on 15 januari 2003, 9:05




 */


package srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux;


import srcastra.astra.gui.event.*;


import javax.swing.JComponent;


/**
 * @author Thomas
 */


public class Config {


    /**
     * Creates a new instance of Config
     */


    public Config() {


        test = true;


    }


    /**
     * Getter for property validate.
     *
     * @return Value of property validate.
     */


    public srcastra.astra.gui.event.ValidateField getValidate() {


        return validate;


    }


    /**
     * Setter for property validate.
     *
     * @param validate New value of property validate.
     */


    public void setValidate(srcastra.astra.gui.event.ValidateField validate) {


        this.validate = validate;


    }


    /**
     * Getter for property user.
     *
     * @return Value of property user.
     */


    public boolean test;


    private ValidateField validate;


    public int typeProduct = 0;


    public ProductLayeredPanel avion;


    public JComponent textarea;


}




