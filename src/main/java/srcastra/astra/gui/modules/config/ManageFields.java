/*

 * ManageFields.java

 *

 * Created on 7 juillet 2003, 14:37

 */


package srcastra.astra.gui.modules.config;

import srcastra.astra.gui.components.*;

import javax.swing.*;

import srcastra.astra.gui.components.actions.actionToolBar.*;

import srcastra.astra.gui.components.actions.*;

import java.awt.event.*;

import srcastra.astra.gui.modules.*;

import java.util.*;

import srcastra.astra.gui.components.combobox.liste.*;

import srcastra.astra.gui.components.textFields.*;

import srcastra.astra.sys.classetransfert.*;

import srcastra.astra.gui.sys.*;

import srcastra.astra.sys.classetransfert.configuration.*;

import srcastra.astra.gui.modules.compta.caisse.*;

import srcastra.astra.gui.modules.compta.compte.*;


/**
 * @author Thomas
 */

public class ManageFields {


    /**
     * Creates a new instance of ManageFields
     */

    AstraComponent[] astrac;

    JInternalFrame parent;

    JTable table;

    ActionToolBar toolbar;

    GlobalInterface panel;

    ToolBarInteraction tb_interaction;

    Hashtable checkFields;

    Loginusers_T user;

    public ManageFields(AstraComponent[] astrac, javax.swing.JInternalFrame parent, JTable table, ActionToolBar toolbar, GlobalInterface panel, Hashtable checkFields, Loginusers_T user) {

        this.user = user;

        this.astrac = astrac;

        this.parent = parent;

        this.table = table;

        this.toolbar = toolbar;

        this.panel = panel;

        this.checkFields = checkFields;

        if (astrac != null) {

            AstraComponent last = astrac[astrac.length - 1];

            last.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {

                    validate();

                }

            });

        }

    }

    public ManageFields(AstraComponent[] astrac, javax.swing.JInternalFrame parent, JTable table, ActionToolBar toolbar, GlobalInterface panel, Hashtable checkFields, Loginusers_T user, boolean action) {

        this.user = user;

        this.astrac = astrac;

        this.parent = parent;

        this.table = table;

        this.toolbar = toolbar;

        this.panel = panel;

        this.checkFields = checkFields;

        if (astrac != null) {

            AstraComponent last = astrac[astrac.length - 1];

            if (action) {

                last.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {

                        validate();

                    }

                });

            }

        }

    }

    private String message(ArrayList check) {

        String mess = "";

        for (int i = 0; i < check.size(); i++) {

            if (i == check.size() - 1)

                mess = mess + check.get(i);

            else

                mess = mess + check.get(i) + ", ";

        }

        if (check.size() == 1)

            mess = java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", user.getLangage()).getString("user_mess1") +

                    " " + mess + " " + java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", user.getLangage()).getString("user_mess11");

        else

            mess = java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", user.getLangage()).getString("user_mess2") + " " +

                    mess + " " + java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", user.getLangage()).getString("user_mess21");

        return mess;

    }

    private boolean generateErroMessage() {

        ArrayList wrongChamp = new ArrayList();

        boolean sw = true;

        if (checkFields == null)

            return true;

        for (Enumeration enu = checkFields.keys(); enu.hasMoreElements();) {

            int key = ((Integer) enu.nextElement()).intValue();

            AstraComponent tmp = astrac[key];

            if (tmp instanceof ATextField) {

                if (((ATextField) tmp).getText().equals("")) {

                    wrongChamp.add(checkFields.get(new Integer(key)));

                    sw = false;

                }

            } else if (tmp instanceof Liste) {

                if (((Liste) tmp).getCleUnik() == 0) {

                    wrongChamp.add(checkFields.get(new Integer(key)));

                    sw = false;

                }

            }

        }

        if (!sw) {

            String mes = message(wrongChamp);

            new JOptionPane().showMessageDialog(parent, mes);

        }

        return sw;

    }

    public void readMode() {

        if (astrac != null)

            for (int i = 0; i < astrac.length; i++) {

                astrac[i].setEnabled(false);

                astrac[i].setLastFocusedComponent(false);

            }

    }

    public void loadTable() {

        try {

            ArrayList data = panel.getRmiInteface().getList(user.getUrcleunik(), 0);

            if (table != null) {

                ((ConfigModel) panel.getTable().getModel()).setData(data);

                panel.getTable().tableChanged(new javax.swing.event.TableModelEvent(panel.getTable().getModel()));

                if (panel.getTable().getRowCount() > 0)

                    panel.getTable().changeSelection(0, 0, false, false);

            }


        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {

            ErreurScreenLibrary.displayErreur(parent, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);

        }

        catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(parent, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }


    }

    public void insertModifMode(boolean modif) {

        if (modif)

            panel.setFields();

        for (int i = 0; i < astrac.length; i++) {

            astrac[i].setEnabled(true);

            astrac[i].clearIcon();

            if (!modif) {

                if (astrac[i] instanceof srcastra.astra.gui.components.combobox.liste.Liste)

                    ((srcastra.astra.gui.components.combobox.liste.Liste) astrac[i]).setCleUnik(0);

                else

                    astrac[i].setText("");

            }

        }

        astrac[0].requestFocus();

    }

    public void beforeCreate(boolean modif) {

        if (!modif)

            mode = ActionToolBar.ACT_INSERT;

        else

            mode = ActionToolBar.ACT_MODIFY;

        setAction();

        insertModifMode(modif);

        ((ModuleInterface) parent).enableJtabPane(false);

        if (table != null) {

            table.setBackground(java.awt.Color.lightGray);

            table.setEnabled(false);

        }

    }

    public void afterUpdate() {

        mode = ActionToolBar.ACT_READ;

        readMode();

        setAction();

        if (table != null) {

            table.setBackground(java.awt.Color.white);

            table.setEnabled(true);

        }

    }

    public void init() {

        readMode();

        if (table != null) {

            table.setFocusable(false);

            if (table.getRowCount() > 0)

                table.changeSelection(0, 0, false, false);

        }

        mode = ActionToolBar.ACT_READ;

        setAction();

        parent.requestFocus();

    }

    public void init2() {

        readMode();

        table.setFocusable(false);

        if (table.getRowCount() > 0)

            table.changeSelection(0, 0, false, false);

        mode = ActionToolBar.ACT_READ;

        setAction();

        parent.requestFocus();

    }

    private int[] setReadAction(int rowcount) {

        int[] tmp = null;

        if (rowcount != 0) {

            if (((ModuleInterface) parent).isBoderPaner() == 0) {

                tmp = new int[]{ActionToolBar.DO_CREATE,

                        ActionToolBar.DO_MODIFY,

                        ActionToolBar.DO_PREVIOUS,

                        ActionToolBar.DO_CLOSE,

                        ActionToolBar.DO_DELETE,

                };


            } else if (((ModuleInterface) parent).isBoderPaner() == 2) {

                tmp = new int[]{ActionToolBar.DO_CREATE,

                        ActionToolBar.DO_MODIFY,

                        ActionToolBar.DO_CANCEL,

                        ActionToolBar.DO_CLOSE,

                        ActionToolBar.DO_DELETE,

                };

            } else {

                tmp = new int[]{ActionToolBar.DO_CREATE,

                        ActionToolBar.DO_MODIFY,

                        ActionToolBar.DO_CANCEL,

                        ActionToolBar.DO_PREVIOUS,

                        ActionToolBar.DO_CLOSE,

                        ActionToolBar.DO_DELETE,

                };


            }

        } else {

            if (((ModuleInterface) parent).isBoderPaner() == 0) {

                tmp = new int[]{ActionToolBar.DO_CREATE,

                        ActionToolBar.DO_PREVIOUS,

                        ActionToolBar.DO_CLOSE,

                };


            } else if (((ModuleInterface) parent).isBoderPaner() == 2) {

                tmp = new int[]{ActionToolBar.DO_CREATE,

                        ActionToolBar.DO_CANCEL,

                        ActionToolBar.DO_CLOSE,

                };

            } else {

                tmp = new int[]{ActionToolBar.DO_CREATE,

                        ActionToolBar.DO_CANCEL,

                        ActionToolBar.DO_PREVIOUS,

                        ActionToolBar.DO_CLOSE,

                };


            }


        }


        return tmp;


    }

    public int[] setAction() {

        int[] tmp = null;

        if (typePanel == 1) {

            if (mode == ActionToolBar.ACT_READ) {

                if (table != null)

                    tmp = setReadAction(table.getRowCount());

                else {

                    tmp = new int[]{ActionToolBar.DO_PREVIOUS,

                            ActionToolBar.DO_CANCEL,

                            ActionToolBar.DO_CREATE,

                    };

                }

            } else if (mode == ActionToolBar.ACT_INSERT) {

                tmp = new int[]{ActionToolBar.DO_PREVIOUS,

                        ActionToolBar.DO_CANCEL,

                };

            } else if (mode == ActionToolBar.ACT_MODIFY) {

                tmp = new int[]{ActionToolBar.DO_PREVIOUS,

                        ActionToolBar.DO_CANCEL,

                };

            }


        } else {

            if (mode == ActionToolBar.ACT_READ) {

                if (table != null)

                    tmp = setReadAction(table.getRowCount());

                else {

                    tmp = new int[]{ActionToolBar.DO_PREVIOUS,

                            ActionToolBar.DO_CANCEL,

                            ActionToolBar.DO_CREATE,

                            ActionToolBar.DO_MODIFY};

                }

            } else if (mode == ActionToolBar.ACT_INSERT) {

                tmp = new int[]{ActionToolBar.DO_PREVIOUS,

                        ActionToolBar.DO_CANCEL,

                };

            } else if (mode == ActionToolBar.ACT_MODIFY) {

                tmp = new int[]{ActionToolBar.DO_PREVIOUS,

                        ActionToolBar.DO_CANCEL,

                };

            }

        }

        toolbar.setActionEnabled(tmp);

        parent.requestFocus();

        return tmp;

    }

    public void nextPanelModify() {

        Object tmp = ((CompteModel) table.getModel()).getValueAt2(table.getSelectedRow(), 0);

        ((ModuleInterface) parent).next();

        setAction();

        toolbar.setTbComposer(((ModuleInterface) parent).getToolPanel());

        this.key = ((Integer) tmp).intValue();

        ((ModuleInterface) parent).getToolPanel().doModify();

    }

    public void nextPanelCreate() {

        //Object tmp=((CompteModel)table.getModel()).getValueAt2(table.getSelectedRow(),0);

        ((ModuleInterface) parent).next();

        setAction();

        toolbar.setTbComposer(((ModuleInterface) parent).getToolPanel());

        // this.key=((Integer)tmp).intValue();

        ((ModuleInterface) parent).getToolPanel().doCreate();

    }

    public void prevPanelRefresh() {

        //Object tmp=((CompteModel)table.getModel()).getValueAt2(table.getSelectedRow(),0);

        if (parent != null) {

            if (((ModuleInterface) parent).getPanel() != null)

                if (((ModuleInterface) parent).getPanel()[0] instanceof ComptePanel)

                    if ((ComptePanel) ((ModuleInterface) parent).getPanel()[0] != null)

                        ((ComptePanel) ((ModuleInterface) parent).getPanel()[0]).doF10();

            //setAction();

            //((ModuleInterface)parent).getToolPanel();

            //toolbar.setTbComposer(((ModuleInterface)parent).getToolPanel());

            // this.key=((Integer)tmp).intValue();

            // ((ModuleInterface)parent).getToolPanel().doCreate();

        }

    }

    public void validate() {

        try {

            if (mode == ActionToolBar.ACT_INSERT) {

                boolean check = generateErroMessage();

                if (!check)

                    return;

                panel.updateFields();

                if (panel.getPass() != null) {

                    panel.getPass().setModif(false);

                    panel.getPass().setUser((User) panel.getObject());

                    panel.getPass().show();

                    if (panel.getPass().isCancel()) {

                        cancel();

                        return;

                    }

                }

                panel.getRmiInteface().insert(this.user.getUrcleunik(), panel.getObject());

                loadTable();

                if (panel.getTable() != null) {

                    if (panel.getTable().getRowCount() > 0)

                        panel.getTable().changeSelection(0, 0, false, false);

                    panel.updateFields();

                }


                afterUpdate();

                prevPanelRefresh();


            } else if (mode == ActionToolBar.ACT_MODIFY) {

                boolean check = generateErroMessage();

                if (!check)

                    return;

                panel.updateFields();

                panel.getRmiInteface().modify(this.user.getUrcleunik(), panel.getObject());

                if (table != null) {

                    loadTable();

                    if (panel.getTable().getRowCount() > 0)

                        panel.getTable().changeSelection(0, 0, false, false);

                    panel.updateFields();

                }

                afterUpdate();

                prevPanelRefresh();


            } else if (mode == ActionToolBar.ACT_READ) {

                ((ModuleInterface) parent).next();

                setAction();

                toolbar.setTbComposer(((ModuleInterface) parent).getToolPanel());

            }

            ((ModuleInterface) parent).enableJtabPane(true);

            parent.requestFocus();

        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {

            ErreurScreenLibrary.displayErreur(parent, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se, this.user);

            afterUpdate();

            loadTable();

        }

        catch (java.rmi.RemoteException re) {

            ErreurScreenLibrary.displayErreur(parent, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re, this.user);

            afterUpdate();

            loadTable();

        }

    }

    public void cancel() {

        if (mode == ActionToolBar.ACT_INSERT) {

            afterUpdate();

        } else if (mode == ActionToolBar.ACT_MODIFY) {

            afterUpdate();

        } else if (mode == ActionToolBar.ACT_READ) {

            ((ModuleInterface) parent).previous();

            boolean sw = false;

            if (typePanel == 1) {

                sw = true;

                typePanel = 0;

            }

            setAction();

            if (sw == true)

                typePanel = 1;

            toolbar.setTbComposer(((ModuleInterface) parent).getToolPanel());

        }

        ((ModuleInterface) parent).enableJtabPane(true);

        parent.requestFocus();


    }

    public int mode;

    public static int key;

    public int typePanel = 0;

}

