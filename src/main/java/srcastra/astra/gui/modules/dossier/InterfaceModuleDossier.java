/*




 * InterfaceModuleDossier.java




 *




 * Created on 5 septembre 2002, 9:36




 */


package srcastra.astra.gui.modules.dossier;


import srcastra.astra.sys.rmi.DossierRmiInterface;


import srcastra.astra.sys.classetransfert.dossier.Dossier_T;


import srcastra.astra.sys.classetransfert.Loginusers_T;


/**
 * @author Thomas
 */


public interface InterfaceModuleDossier {


    DossierRmiInterface getServeurDossier();


    Dossier_T getDossier();


    void setDossier(Dossier_T dossier);


    Loginusers_T getCurrentUser();


}




