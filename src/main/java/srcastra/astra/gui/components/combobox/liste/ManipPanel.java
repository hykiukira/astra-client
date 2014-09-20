/*





 * ManipPanel.java





 *





 * Created on 12 novembre 2002, 15:07





 */


package srcastra.astra.gui.components.combobox.liste;


import srcastra.astra.sys.classetransfert.*;


/**
 * @author Thomas
 */


public interface ManipPanel {


    void openPannel(int i);


    void closePanne();


    void setReadModeToPanel();


    void fillValueFromComponent(InterfaceClasseTransfert transfert, String nameOfComponent);


    void getSignaletique(int typeSignaletique);


}





