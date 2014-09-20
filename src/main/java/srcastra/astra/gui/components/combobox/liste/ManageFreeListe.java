/*

 * ManageFreeListe.java

 *

 * Created on 14 mai 2003, 12:04

 */


package srcastra.astra.gui.components.combobox.liste;


/**
 * @author Thomas
 */

public class ManageFreeListe {


    /**
     * Creates a new instance of ManageFreeListe
     */

    public ManageFreeListe() {

    }

    public int getCleunik(Liste liste) {

        return 0;

    }

    public String getFreetexte() {

        return "";

    }

    public static void setValue(int cleunik, String freeTexte, Liste liste) {

        if (liste.isFreeModeAllow())

        {

            if (freeTexte != null) {

                if (!freeTexte.equals("NO")) {

                    liste.setFreemode(true);

                    liste.setCleUnik2(0);

                    liste.setFreeTexte(freeTexte);

                    liste.getGrp_TField_encode().moveCaretPosition(0);

                    return;

                }

            }

            liste.setCleUnik(cleunik);


        }


    }

}

