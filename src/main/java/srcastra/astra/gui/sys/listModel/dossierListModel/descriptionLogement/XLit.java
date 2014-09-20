/*





 * TypeBListModel.java





 *





 * Created on 6 septembre 2002, 9:59





 */


package srcastra.astra.gui.sys.listModel.dossierListModel.descriptionLogement;


import srcastra.astra.gui.sys.listModel.AbstractAstraListModel;


import srcastra.astra.gui.sys.AString;


import srcastra.astra.sys.rmi.astrainterface;


import srcastra.astra.sys.classetransfert.Loginusers_T;


/**
 * @author Sébastien
 */


public class XLit extends AbstractAstraListModel implements Abrev {


    /**
     * Creates a new instance of TypeBListModel
     */


    public XLit(astrainterface serveur, Loginusers_T currentUser) {


        super(serveur, currentUser);


    }


    protected AString[] loadData() {


        AString[] retVal = new AString[6];


        retVal[0] = new AString(1, loadName("single_libel"));


        retVal[1] = new AString(2, loadName("double_libel"));


        retVal[2] = new AString(3, loadName("triple_libel"));


        retVal[3] = new AString(4, loadName("4pers_libel"));


        retVal[4] = new AString(5, loadName("appart_libel"));


        retVal[5] = new AString(6, loadName("bungalow_libel"));


        return retVal;


    }


    private String loadName(String key) {


        String retVal = "";


        try {


            retVal = java.util.ResourceBundle.getBundle("srcastra/astra/locale/tableHeader/dossier/descriptionLogement/XLit", currentUser.getLangage()).getString(key);


        }


        catch (java.util.MissingResourceException e) {


            e.printStackTrace();


        }


        finally {


            return retVal;


        }


    }


    public String loadAbrev(int cleUnik) {


        switch (cleUnik) {


            case 1:


                return loadName("single_abrev");


            case 2:


                return loadName("double_abrev");


            case 3:


                return loadName("triple_abrev");


            case 4:


                return loadName("4pers_abrev");


            case 5:


                return loadName("appart_abrev");


            case 6:


                return loadName("bungalow_abrev");


            default:


                return "";


        }


    }

    /* single_abrev





      single_libel





      double_abrev





      double_libel





      tripe_abrev





      triple_libel





      4pers_abrev





      4pers_libel





      appart_abrev





      appart_libel





      bungalow_abrev





      bungalow_libel





    */


}





