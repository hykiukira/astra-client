/*




 * ConfPanel.java




 *




 * Created on 7 novembre 2002, 12:45




 */


package srcastra.astra.gui.conf;


/**
 * @author Sébastien
 */


public interface ConfigurationParent {


    public void doClose();


    public srcastra.astra.sys.rmi.astrainterface getServeur();


    public srcastra.astra.sys.classetransfert.Loginusers_T getCurrentUser();


}




