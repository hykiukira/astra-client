/*

 * TvaMediator.java

 *

 * Created on 29 avril 2003, 16:17

 */


package srcastra.astra.gui.components.tva;

import srcastra.astra.gui.components.combobox.liste.*;


/**
 * @author Thomas
 */

public class TvaMediator implements java.io.Serializable {


    /**
     * Creates a new instance of TvaMediator
     */

    public TvaMediator() {

    }

    public void registeListe(Liste liste) {

        this.liste = liste;

    }

    public void registeFrame(TvaFrame tva) {

        this.tva = tva;

    }

    public void specifiyKey(int keycode) {

        System.out.println("key " + keycode);

        tva.show(liste.getCleUnik(), keycode);

    }

    public void setTvaCleunik(int cleunik) {

        System.out.println("liste " + liste + "  cleunik " + cleunik);

        liste.setCleUnik(cleunik);

    }

    public void setTreeCleunik(int cleunik) {

        tva.setTreeCleunik(cleunik);

    }

    public TvaAchatListeTableModel getModel() {

        return tva.getModelachat();

    }

    transient Liste liste;

    TvaFrame tva;

}

