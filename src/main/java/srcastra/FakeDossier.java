package srcastra;

import srcastra.astra.sys.classetransfert.clients.Clients_T;
import srcastra.astra.sys.classetransfert.dossier.Dossier_T;

/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 10/10/11
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public class FakeDossier extends Dossier_T {

    public srcastra.astra.sys.classetransfert.clients.Clients_T getClientFacturable() {


         return new Clients_T();


     }

}
