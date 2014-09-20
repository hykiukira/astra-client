/*





 * ATextfieldWithButtonComponent.java





 *





 * Created on 15 juillet 2002, 18:31





 */


package srcastra.astra.gui.components.textfield2;


/**
 * This class is used to allow interaction between ATexFieldWithButton and the Component added to
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * his JDialogBox.
 *
 * @author Sébastien
 */


public interface ATextfieldWithButtonComponent {


    /**
     * Automatically called during the closing action of the dialogBox
     */


    public void actionOnDispose();


    /**
     * Adding the instance of the dialogBox
     *
     * @param dialogBox instance of the dialogBox
     */


    public void addDialogInstance(javax.swing.JDialog dialogBox);


    /**
     * Adding the instance of the ATextFieldWithButton
     *
     * @param dialogBox instance of the dialogBox
     */


    public void addATextFieldComponentWithButtonInstance(srcastra.astra.gui.components.textFields.ATextFieldWithButton atfwb);


    /**
     * Load the string corresponding with the cleUnik
     */


    public String loadContentString(int cleUnik);


}





