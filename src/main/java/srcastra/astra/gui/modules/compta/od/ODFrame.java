/*

 * ODFrame.java

 *

 * Created on 14 octobre 2003, 16:40

 */


package srcastra.astra.gui.modules.compta.od;

import srcastra.astra.sys.classetransfert.Loginusers_T;

import srcastra.astra.sys.rmi.astrainterface;

import srcastra.astra.gui.modules.*;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import srcastra.astra.gui.components.AIframe;

import srcastra.astra.gui.sys.ErreurScreenLibrary;

import srcastra.astra.sys.rmi.*;

import javax.swing.event.InternalFrameEvent;

import srcastra.astra.gui.components.combobox.liste2.Mediator;

import srcastra.astra.gui.*;

/**
 * @author Thomas
 */

public class ODFrame extends srcastra.astra.gui.components.frame.MY_Jframe {


    /**
     * Creates a new instance of ODFrame
     */

    public ODFrame(java.awt.Frame superOwner, astrainterface serveur, Loginusers_T currentUser, ActionToolBar actionToolBar, javax.swing.event.InternalFrameListener iFrameListener, MainFrame main, ToolBarComposer[] paneltab, String title, java.awt.Dimension dim, int type) {

        super(superOwner, serveur, currentUser, actionToolBar, iFrameListener, main, paneltab, title, dim);

        String titre = "";

        if (type == 0)

            titre = java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", currentUser.getLangage()).getString("odtitle");

        else

            titre = java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", currentUser.getLangage()).getString("bktitle");

        super.setTitle(titre);

        ToolBarComposer tab = new ODPanel(this, type);

        getContentPane().add((javax.swing.JPanel) tab);

        actionToolBar.setTbComposer(tab);

        actionToolBar.setActionEnabled(tab.getDefaultActionToolBarMask());

    }


}

