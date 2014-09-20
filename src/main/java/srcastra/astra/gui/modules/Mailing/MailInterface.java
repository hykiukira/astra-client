/*

 * MailInterface.java

 *

 * Created on 10 avril 2003, 12:33

 */



package srcastra.astra.gui.modules.Mailing;

import srcastra.astra.sys.classetransfert.Loginusers_T;

/**

 *

 * @author  Thomas

 */

public interface  MailInterface {

    

    /** Creates a new instance of MailInterface */

    String[] getEmailAdres();

    Loginusers_T getUser();
    
    String getFormEntiteMail();

    

}

