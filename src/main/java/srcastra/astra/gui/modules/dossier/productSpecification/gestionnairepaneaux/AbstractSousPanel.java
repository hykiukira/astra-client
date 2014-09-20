/*















 * AbstractSousPanel.java















 *















 * Created on 8 januari 2003, 9:35















 */


package srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux;


import srcastra.astra.gui.modules.dossier.productSpecification.*;


import srcastra.astra.gui.modules.dossier.*;


import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;


import srcastra.astra.gui.modules.*;


import javax.swing.*;


import srcastra.astra.gui.components.actions.actionToolBar.*;


import srcastra.astra.sys.classetransfert.dossier.Sup_reduc_T;


/**
 * @author Thomas
 */


public abstract class AbstractSousPanel {// implements InterfaceAbstractPanel{//InternScreenModule{//,srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer{


    /**
     * Creates a new instance of AbstractSousPanel
     */


    public AbstractSousPanel(DossierMainScreenModule parent, DossierProduitPane pane_produit) {

        this.m_parent = parent;

        this.m_produitpane = pane_produit;

        mode = ActionToolBar.ACT_READ;

        initTableaux();

        check = true;

    }

    public DossierMainScreenModule getMainScreenModule() {

        return m_parent;

    }

    private void initTableaux() {

        if (m_parent.getDossier().getAnnuler() == 0 && m_parent.getDossier().getServicefact() == 0 && !m_parent.getDossier().isReadMode()) {

            readTab = new int[]{

                    ActionToolBar.DO_MODIFY,

                    ActionToolBar.DO_CANCEL,

                    ActionToolBar.DO_PREVIOUS,

                    ActionToolBar.DO_SWITCH

            };

            readTab3 = new int[]{

                    ActionToolBar.DO_CREATE,

                    ActionToolBar.DO_CANCEL,

                    ActionToolBar.DO_PREVIOUS,

                    ActionToolBar.DO_SWITCH};

            readTab2 = new int[]{

                    ActionToolBar.DO_MODIFY,

                    ActionToolBar.DO_CREATE,

                    ActionToolBar.DO_CANCEL,

                    ActionToolBar.DO_PREVIOUS,

                    ActionToolBar.DO_SWITCH,

            };

            readTabDelete = new int[]{

                    ActionToolBar.DO_MODIFY,

                    ActionToolBar.DO_CREATE,

                    ActionToolBar.DO_CANCEL,

                    ActionToolBar.DO_PREVIOUS,

                    ActionToolBar.DO_SWITCH,

                    ActionToolBar.DO_DELETE

            };

        } else {

            readTab = new int[]{


                    ActionToolBar.DO_CANCEL,


                    ActionToolBar.DO_SWITCH

            };

            readTab3 = new int[]{


                    ActionToolBar.DO_CANCEL,


                    ActionToolBar.DO_SWITCH};

            readTab2 = new int[]{


                    ActionToolBar.DO_CANCEL,


                    ActionToolBar.DO_SWITCH,

            };


            readTabDelete = new int[]{

                    ActionToolBar.DO_CANCEL,

                    ActionToolBar.DO_SWITCH,

            };


        }

        insertTab = new int[]{

                ActionToolBar.DO_CANCEL,

                ActionToolBar.DO_PREVIOUS,

        };

        insertTabMain = new int[]{

                ActionToolBar.DO_CANCEL,

                ActionToolBar.DO_PREVIOUS,

                ActionToolBar.DO_F10

        };


        modifyTab = new int[]{

                ActionToolBar.DO_CANCEL,

                ActionToolBar.DO_PREVIOUS,

        };

        modifyTabMain = new int[]{

                ActionToolBar.DO_CANCEL,

                ActionToolBar.DO_PREVIOUS,

                ActionToolBar.DO_F10

        };

    }


    public abstract JPanel getUI();


    public abstract String getName();


    public abstract String getNexPanel(int mode);


    public abstract void setSelected(boolean selected);


    public abstract String getMyType();


    public abstract int[] getActions(int mode2);


    public abstract void setVisible(boolean visible);


    public abstract String getSwitchAble();


    public abstract String getNextToHide();


    public abstract boolean isMainPanel();


    public abstract void reloadTableModel();


    public abstract void moveInTable(int direction);


    public abstract void setSup_reduc(Object sup_reduc);


    public abstract Object getSupReduc(int i);


    public abstract java.awt.Component getTable();


    public abstract double getPrix();


    public abstract int doPrevious(boolean sw);


    public abstract int doModify(boolean sw);


    public abstract int doCancel(boolean sw);


    public abstract int doAccept(boolean sw);


    public abstract int doCreate(boolean sw);


    public abstract int doDelete(boolean sw);


    public abstract void doNext(boolean sw);


    public abstract String getTitle();


    public abstract void addGeneriqueListener(MainPanelListener listener);


    public abstract void killTbinteraction();


    public Config m_config;

    // public void goUp() {

    //}


    /**
     * Getter for property check.
     *
     * @return Value of property check.
     */


    public boolean isCheck() {


        return check;


    }


    /**
     * Setter for property check.
     *
     * @param check New value of property check.
     */


    protected void setCheck(boolean check) {


        this.check = check;


    }


    public java.awt.Component m_getGeneriqueTable() {


        return null;


    }


    protected DossierMainScreenModule m_parent;


    protected DossierProduitPane m_produitpane;


    protected javax.swing.border.Border oldBorder;


    protected int mode;


    protected int[] readTab;


    protected int[] readTab2;

    protected int[] readTabDelete;


    protected int[] readTab3;


    protected int[] insertTab;


    protected int[] insertTabMain;


    protected int[] modifyTab;


    protected int[] modifyTabMain;


    protected boolean check;


    protected javax.swing.event.EventListenerList m_listenerList;


}















