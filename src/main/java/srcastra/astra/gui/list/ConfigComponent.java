/*

 * ConfigComponent.java

 *

 * Created on 7 novembre 2003, 11:34

 */


package srcastra.astra.gui.list;

import javax.swing.*;

import srcastra.astra.gui.components.*;

import srcastra.astra.gui.components.combobox.liste.*;

import srcastra.astra.gui.components.combobox.liste2.*;

/**
 * @author Thomas
 */

public class ConfigComponent {


    /**
     * Creates a new instance of ConfigComponent
     */

    public ConfigComponent() {

    }

    public void config(Object[][] obj) {

        AstraComponent compToNext = null;

        for (int i = 0; i < obj.length; i++) {

            Object[] tabtabcomp = (Object[]) obj[i];

            for (int j = 0; j < tabtabcomp.length; j++) {

                JComponent[] tabcomp = (JComponent[]) tabtabcomp[j];

                if (tabcomp != null) {

                    for (int x = 0; x < tabcomp.length; x++) {

                        JComponent comp = (JComponent) tabcomp[x];

                        if (comp instanceof AstraComponent) {

                            if (compToNext != null)

                                compToNext.setGrp_Comp_nextComponent(comp);

                            if (compToNext instanceof Liste) {

                                ((Liste) compToNext).setCanbenull(true);

                            }

                            if (compToNext instanceof Liste2) {

                                ((Liste2) compToNext).setCanbenull(true);

                            }

                            ((AstraComponent) comp).setWarningIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/warning.gif")));

                            ((AstraComponent) comp).setWorkingIcon(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/aTextfield/working.gif")));

                            compToNext = (AstraComponent) comp;

                        }

                    }

                }

            }

        }

        if (compToNext != null) {

            if (compToNext instanceof Liste) {

                ((Liste) compToNext).setIsLastComponent(true);
                ;

            }

            if (compToNext instanceof Liste2) {

                ((Liste2) compToNext).setIsLastComponent(true);
                ;

            }

            //   ((Liste)compToNext).set


        }


    }


}

