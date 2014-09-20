/*







 * InterfaceMainPanel.java







 *







 * Created on 29 janvier 2003, 11:02







 */


package srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux;


import java.awt.event.ActionListener;


import javax.swing.*;


/**
 * @author Thomas
 */


public interface InterfaceMainPanel {


    void registerKeyboardAction(ActionListener action, KeyStroke keystroke, int focusType);


    public int[] getDefaultActionToolBarMask();


    public void setThisAsToolBarComponent();


    public void resetKeyboardActions();


    public void doAccept();


    public void doCreate();


    public void doModify();


    public void doDelete();


    public void doPrevious();


    public void doNext();


    public void doCancel();


    public void doClose();


    public void doHelp();


    public void doPrint();


    public void doSwitch();


    public void doUp();


    public void doDown();

    public void doF10();


}







