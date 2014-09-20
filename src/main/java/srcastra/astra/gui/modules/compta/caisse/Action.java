/*

 * Action.java

 *

 * Created on 26 août 2003, 15:44

 */


package srcastra.astra.gui.modules.compta.caisse;

import srcastra.astra.gui.modules.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.sys.classetransfert.*;

/**
 * @author Thomas
 */

public abstract class Action {


    /**
     * Creates a new instance of Action
     */

    public Action(Caisse panel, astrainterface serveur, Loginusers_T user) {

        this.panel = panel;

        this.serveur = serveur;

        this.user = user;

    }

    public abstract void doCreate();

    public abstract void doDelete();

    public abstract void doF10();

    public abstract void doF7();

    public abstract void doHelp();

    public abstract void doModify();

    public abstract void doNext();

    public abstract void doPrevious();

    public abstract void doPrint();

    public abstract void doCancel();

    protected Caisse panel;

    protected astrainterface serveur;

    protected Loginusers_T user;

}

