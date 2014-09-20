/*
 * mainPanel.java
 * Created on 8 januari 2003, 11:02
 */
package srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux;

import java.util.Hashtable;

import srcastra.astra.gui.components.actions.ToolBarInteraction;
import srcastra.astra.gui.components.actions.actionToolBar.*;
import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import srcastra.astra.gui.modules.dossier.DossierProduitPane;
import srcastra.astra.sys.classetransfert.dossier.InterfaceProduit;

import java.awt.event.*;

import srcastra.astra.gui.event.*;
import srcastra.astra.sys.classetransfert.dossier.produit_T;

/**
 * @author Thomas
 */
public class ProductLayeredPanel extends javax.swing.JPanel implements srcastra.astra.gui.components.actions.actionToolBar.ToolBarComposer, KeyListener, InterfaceMainPanel, MainPanelListener {//,ComponentListener{
    /**
     * Creates new form mainPanel
     */
    Config m_config;
    int typProd;

    public ProductLayeredPanel(DossierMainScreenModule parent, ActionToolBar actionToolBar, int mode, DossierProduitPane parentpane, Config config, int panel) {
        typProd = panel;
        m_keyStroke = new KeyStrokeManager(false);
        m_keyStroke.registerComponent(this);
        m_config = config;
        m_config.avion = this;
        m_config.typeProduct = panel;
        m_parentPane = parentpane;
        m_mode = mode;
        initComponents();
        setSize(733, 351);
        setLayout(new java.awt.GridLayout(1, 0));
        initListeners();
        config.setValidate(validateAndDoPrevious);
        pfact = new PanelFactory(config);
        table = new Hashtable();
        m_parent = parent;
        m_actionToolBar = actionToolBar;
        setName("main panel");
        table.put(AbstractMainPanel.SUP_REDUC, pfact.getPanel(3, parent));
        table.put(AbstractMainPanel.SUP_REDUC_EDITION, pfact.getPanel(5, parent));
        table.put(AbstractMainPanel.MEMO, pfact.getPanel(17, parent));
        if (panel == produit_T.AV) {
            table.put(AbstractMainPanel.PRODUIT, pfact.getPanel(1, parent));
            table.put(AbstractMainPanel.DESC, pfact.getPanel(2, parent));
            table.put(AbstractMainPanel.DESC_EDITION, pfact.getPanel(4, parent));
            simpleProduct = false;
        } else if (panel == produit_T.BRO) {
            table.put(AbstractMainPanel.PRODUIT, pfact.getPanel(6, parent));
            table.put(AbstractMainPanel.DESC, pfact.getPanel(7, parent));
            table.put(AbstractMainPanel.DESC_EDITION, pfact.getPanel(8, parent));
            simpleProduct = false;
        } else if (panel == produit_T.TAX) {
            table.put(AbstractMainPanel.PRODUIT, pfact.getPanel(15, parent));
            table.put(AbstractMainPanel.DESC, pfact.getPanel(14, parent));
            table.put(AbstractMainPanel.DESC_EDITION, pfact.getPanel(16, parent));
            simpleProduct = false;
        } else if (panel == produit_T.HO) {
            table.put(AbstractMainPanel.PRODUIT, pfact.getPanel(9, parent));
            table.put(AbstractMainPanel.DESC, pfact.getPanel(7, parent));
            table.put(AbstractMainPanel.DESC_EDITION, pfact.getPanel(8, parent));
            simpleProduct = false;
        } else if (panel == produit_T.BA) {
            table.put(AbstractMainPanel.PRODUIT, pfact.getPanel(10, parent));
            simpleProduct = true;
        } else if (panel == produit_T.AS) {
            table.put(AbstractMainPanel.PRODUIT, pfact.getPanel(11, parent));
            simpleProduct = true;
        } else if (panel == produit_T.TR) {
            table.put(AbstractMainPanel.PRODUIT, pfact.getPanel(12, parent));
            simpleProduct = true;
        } else if (panel == produit_T.VO) {
            table.put(AbstractMainPanel.PRODUIT, pfact.getPanel(13, parent));
            simpleProduct = true;
        } else if (panel == produit_T.CAR) {
            table.put(AbstractMainPanel.PRODUIT, pfact.getPanel(18, parent));
            simpleProduct = true;
        } else if (panel == produit_T.DIV) {
            table.put(AbstractMainPanel.PRODUIT, pfact.getPanel(19, parent));
            simpleProduct = true;
        }
        fillLayer();
        if (mode == ActionToolBar.ACT_INSERT) {
            if (!simpleProduct) ((AbstractSousPanel) table.get(AbstractMainPanel.SUP_REDUC)).setVisible(false);
            panelSelected = getAbstractPanelFromTable(AbstractMainPanel.PRODUIT);
            selectPanel(true, ActionToolBar.ACT_INSERT);
            if (m_parent.getDossier().getAnnuler() == 0 && m_parent.getDossier().getServicefact() == 0) {
                m_parent.setCurrentActionEnabled(panelSelected.getActions(ActionToolBar.ACT_INSERT));
            } else {
                m_parent.setCurrentActionEnabled(new int[]{ActionToolBar.DO_CANCEL});
            }
        } else if (mode == ActionToolBar.ACT_READ) {
            panelSelected = getAbstractPanelFromTable(AbstractMainPanel.SUP_REDUC);
            panelSelected.getUI().requestFocus();
            selectPanel(true, ActionToolBar.ACT_READ);
            m_parent.calculTotalUnProduit(returnTheRightProduct());
            requestFocus();
        }
        m_actionToolBar.setTbComposer(this);
        this.setVisible(true);
        addKeyListener(this);
    }

    java.awt.event.FocusAdapter test;

    public void process(ActionEvent evt) {
        this.goUp();


    }

    private void fillLayer() {
        jLayeredPane1.add(((AbstractSousPanel) table.get(AbstractMainPanel.MEMO)).getUI(),50, 5);
        JPanel supReducEdition = ((AbstractSousPanel) table.get(AbstractMainPanel.SUP_REDUC_EDITION)).getUI();
        supReducEdition.requestFocus();
        supReducEdition.setVisible(false);

        if (typProd == produit_T.AS || typProd == produit_T.TR || typProd == produit_T.VO || typProd == produit_T.BA || typProd == produit_T.CAR || typProd == produit_T.DIV) {
            jLayeredPane1.add(((AbstractSousPanel) table.get(AbstractMainPanel.PRODUIT)).getUI(), 50, 1);
            jLayeredPane1.add(((AbstractSousPanel) table.get(AbstractMainPanel.SUP_REDUC)).getUI(),50, 1);
            jLayeredPane1.add(supReducEdition, 50, 1);
            add(jLayeredPane1);
            jLayeredPane1.moveToFront(((AbstractSousPanel) table.get(AbstractMainPanel.SUP_REDUC)).getUI());
            jLayeredPane1.moveToBack(supReducEdition);


        } else {
            JPanel p_product = ((AbstractSousPanel) table.get(AbstractMainPanel.PRODUIT)).getUI();
            p_product.requestFocus();
            //add(p_product);
            jLayeredPane1.add(p_product, 50, 1);
            JPanel desc = ((AbstractSousPanel) table.get(AbstractMainPanel.DESC)).getUI();
            desc.requestFocus();
            desc.setVisible(false);
            jLayeredPane1.add(desc,50, 2);
            JPanel descEdition = ((AbstractSousPanel) table.get(AbstractMainPanel.DESC_EDITION)).getUI();
            //descEdition.requestFocus();
            //descEdition.setVisible(false);
            jLayeredPane1.add(descEdition,50, 3);
            jLayeredPane1.add(((AbstractSousPanel) table.get(AbstractMainPanel.SUP_REDUC_EDITION)).getUI(),53, 0);
            add(jLayeredPane1);
            JPanel supReduc = ((AbstractSousPanel) table.get(AbstractMainPanel.SUP_REDUC)).getUI();
            //supReduc.requestFocus();
            //supReduc.setVisible(false);
            jLayeredPane1.add(supReduc,50, 4);
            //jLayeredPane1.moveToBack(supReduc);
            jLayeredPane1.moveToBack(supReducEdition);
            jLayeredPane1.moveToBack(desc);


        }
        JPanel memoPanel = ((AbstractSousPanel) table.get(AbstractMainPanel.MEMO)).getUI();
        memoPanel.requestFocus();
        memoPanel.setVisible(false);
        jLayeredPane1.moveToBack(memoPanel);


    }

    private void initListeners() {
        validateAndDoPrevious = new ValidateField() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("ok je rentre bien dans l'event");
                if (m_mode == ActionToolBar.ACT_INSERT || m_mode == ActionToolBar.ACT_MODIFY) {
                    doPrevious();
                }
            }
        };
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLayeredPane1 = new javax.swing.JLayeredPane();
        setLayout(new java.awt.BorderLayout());
        setName("thepanel");
        setPreferredSize(new java.awt.Dimension(704, 390));
        add(jLayeredPane1, java.awt.BorderLayout.NORTH);


    }//GEN-END:initComponents

    public void doAccept() {
    }

    public void doCancel() {
        if (m_mode != ActionToolBar.ACT_READ) {
            if (panelSelected.getMyType().equals(AbstractMainPanel.MEMO)) {
                panelSelected.getUI().requestFocus();
                panelSelected.getUI().setVisible(false);
                jLayeredPane1.moveToBack(panelSelected.getUI());
                panelSelected = getAbstractPanelFromTable(AbstractMainPanel.PRODUIT);
                selectPanel(true, m_mode);
                return;


            }
            if (panelSelected.getSwitchAble() == null) {
                if (panelSelected.getMyType().equals(AbstractMainPanel.PRODUIT) && m_mode == ActionToolBar.ACT_INSERT) {
                    if (!m_config.test) {
                        panelSelected.killTbinteraction();
                        this.setVisible(false);
                        m_parentPane.showProductPanel(0, 0);
                        return;


                    }


                } else {
                    m_keyStroke.setAction(panelSelected.getActions(ActionToolBar.ACT_READ));
                    if (m_parent.getDossier().getAnnuler() == 0 && m_parent.getDossier().getServicefact() == 0) {
                        m_parent.setCurrentActionEnabled(panelSelected.getActions(ActionToolBar.ACT_READ));

                    } else {
                        m_parent.setCurrentActionEnabled(new int[]{ActionToolBar.DO_CANCEL});

                    }
                    panelSelected.doCancel(true);


                }


            } else {
                if (!panelSelected.isMainPanel()) {
                    panelSelected.getUI().requestFocus();
                    panelSelected.getUI().setVisible(false);
                    jLayeredPane1.moveToBack(panelSelected.getUI());
                    panelSelected = getAbstractPanelFromTable(panelSelected.getSwitchAble());
                    selectPanel(true, ActionToolBar.ACT_READ);


                }


            }
            requestFocus();
            Component compFocusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
//     System.out.println("Composant nom :" +compFocusOwner.getName());
            m_mode = ActionToolBar.ACT_READ;


        } else {
            if (askForUpdateProduct()) {
                this.setVisible(false);
                m_parentPane.showProductPanel(0, 0);


            } else requestFocus();


        }


    }

    private boolean askForUpdateProduct() {
        if (returnTheRightProduct().isLocalyModify()) {
            int rep = m_parent.getMessageManager().showConfirmDialog(this, "produit_annul_texte", "produit_annul", m_parent.getCurrentUser());
            if (rep == 0) {
                return true;


            }


        } else return true;
        return false;


    }
    /*   if(typProd==produit_T.AV){















            boolean sw=false;















            if(m_parent.getTicket().isLocalyModify()){















                            int rep=m_parent.getMessageManager().showConfirmDialog(this,"produit_annul_texte","produit_annul",m_parent.getCurrentUser());















                    if(rep==0){ 















                      //  if(tmpBrochure!=null){















                        //    parent.setBrochure(tmpBrochure);















                          //  parent.getDossier().addBrochure(parent.getBrochure());                           















                       // }















                        return true;















                    }















                }















                else















                   return true;















        return false;















        }















        else if(typProd==produit_T.HO){















                boolean sw=false;















            if(m_parent.getHotel().isLocalyModify()){















                            int rep=m_parent.getMessageManager().showConfirmDialog(this,"produit_annul_texte","produit_annul",m_parent.getCurrentUser());















                    if(rep==0){ 















                      //  if(tmpBrochure!=null){















                        //    parent.setBrochure(tmpBrochure);















                          //  parent.getDossier().addBrochure(parent.getBrochure());                           















                       // }















                        return true;















                    }















                }















                else















                   return true;















        return false;















            















        }















       else if(typProd==produit_T.BRO){















            boolean sw=false;















            if(m_parent.getBrochure().isLocalyModify()){















                            int rep=m_parent.getMessageManager().showConfirmDialog(this,"produit_annul_texte","produit_annul",m_parent.getCurrentUser());















                    if(rep==0){ 















                      //  if(tmpBrochure!=null){















                        //    parent.setBrochure(tmpBrochure);















                          //  parent.getDossier().addBrochure(parent.getBrochure());                           















                       // }















                        return true;















                    }















                }















                else















                   return true;















        return false;















       }        















        return false;















        















    }*/

    public void read() {
        getAbstractPanelFromTable(AbstractMainPanel.SUP_REDUC).reloadTableModel();
        AbstractSousPanel tmp = getAbstractPanelFromTable(AbstractMainPanel.DESC);
        if (tmp != null) tmp.reloadTableModel();
        m_keyStroke.setAction(panelSelected.getActions(ActionToolBar.ACT_READ));
        if (m_parent.getDossier().getAnnuler() == 0 && m_parent.getDossier().getServicefact() == 0) {
            m_parent.setCurrentActionEnabled(panelSelected.getActions(ActionToolBar.ACT_READ));

        } else {
            m_parent.setCurrentActionEnabled(new int[]{ActionToolBar.DO_CANCEL});

        }
        // panelSelected.displayReadMode();
        requestFocus();


    }

    public void doClose() {
    }

    public void doCreate() {
        m_mode = ActionToolBar.ACT_INSERT;
        if (panelSelected.getSwitchAble() == null) {
            panelSelected.doCreate(true);
        } else {
            panelSelected.setSelected(false);
            panelSelected.getUI().requestFocus();
            panelSelected.getUI().setVisible(false);
            jLayeredPane1.moveToBack(panelSelected.getUI());
            panelSelected = getAbstractPanelFromTable(panelSelected.getSwitchAble());
            selectPanel(true, ActionToolBar.ACT_INSERT);
            panelSelected.doCreate(true);

        }


    }

    public void doDelete() {
        int rep = panelSelected.doDelete(true);
        if (rep == 1) {
            getAbstractPanelFromTable(AbstractMainPanel.SUP_REDUC).reloadTableModel();
            AbstractSousPanel tmp = getAbstractPanelFromTable(AbstractMainPanel.DESC);
            if (tmp != null) tmp.reloadTableModel();
            m_parent.calculTotalUnProduit(returnTheRightProduct());


        } else if (rep == 0) {
            this.setVisible(false);
            m_parentPane.showProductPanel(0, 0);


        }


    }

    public void doHelp() {
    }

    public void doModify() {
        m_mode = ActionToolBar.ACT_MODIFY;
        if (panelSelected.getSwitchAble() == null) {
            m_keyStroke.setAction(panelSelected.getActions(ActionToolBar.ACT_MODIFY));
            m_parent.setCurrentActionEnabled(panelSelected.getActions(ActionToolBar.ACT_MODIFY));
            if (m_config.test) m_actionToolBar.setActionEnabled(panelSelected.getActions(ActionToolBar.ACT_MODIFY));
            panelSelected.doModify(true);
            try {
                Component compFocusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();


            } catch (NullPointerException nu) {
                nu.printStackTrace();
                System.out.println("composant null");


            }


        } else {
            if (!selectElementInTable(0)) m_mode = ActionToolBar.ACT_READ;


        }


    }

    private boolean selectElementInTable(int i) {
        Object tmp = panelSelected.getSupReduc(i);
        boolean retval = false;
        if (tmp != null) {
            panelSelected.setSelected(false);
            panelSelected = getAbstractPanelFromTable(panelSelected.getSwitchAble());
            panelSelected.setSup_reduc(tmp);
            selectPanel(true, ActionToolBar.ACT_MODIFY);
            panelSelected.doModify(true);
            retval = true;

        }
        return retval;


    }

    public void doNext() {
        try {
            compFocusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();


        } catch (NullPointerException nu) {
            nu.printStackTrace();
            System.out.println("composant null");


        }
        if (panelSelected.getMyType().equals(AbstractMainPanel.PRODUIT)) {
            panelSelected.setSelected(false);
            panelSelected = getAbstractPanelFromTable(AbstractMainPanel.MEMO);
            selectPanel(true, ActionToolBar.ACT_MODIFY);
            panelSelected.doNext(true);


        }


    }

    private produit_T returnTheRightProduct() {
        produit_T tmp = null;
        if (typProd == produit_T.AV) {
            tmp = m_parent.getTicket();


        } else if (typProd == produit_T.HO) {
            tmp = m_parent.getHotel();


        } else if (typProd == produit_T.BRO) {
            tmp = m_parent.getBrochure();


        } else if (typProd == produit_T.BA) {
            tmp = m_parent.getBateau();


        } else if (typProd == produit_T.AS) {
            tmp = m_parent.getAssurance();


        } else if (typProd == produit_T.TR) {
            tmp = m_parent.getTrain();


        } else if (typProd == produit_T.VO) {
            tmp = m_parent.getVoitureLocation();


        } else if (typProd == produit_T.CAR) {
            tmp = m_parent.getCar();


        } else if (typProd == produit_T.DIV) {
            tmp = m_parent.getDiverst();


        } else if (typProd == produit_T.TAX) {
            tmp = m_parent.getTaxi();


        }
        return tmp;


    }

    private void refreshPrizes(boolean sw) {
        if (sw) if (panelSelected.getMyType().equals(AbstractMainPanel.SUP_REDUC_EDITION))
            m_parent.calculTotalUnProduit(returnTheRightProduct());


    }

    public void doPrevious() {
        if (m_mode != ActionToolBar.ACT_READ) {
            returnTheRightProduct().resetLibelleCompta(m_parent.getDossier().getClientContractuel().getLecleunik());
            int rep = panelSelected.doPrevious(true);
            if (rep == 0) return;
            refreshPrizes(true);
            getAbstractPanelFromTable(AbstractMainPanel.SUP_REDUC).reloadTableModel();
            AbstractSousPanel tmp = getAbstractPanelFromTable(AbstractMainPanel.DESC);
            if (tmp != null) tmp.reloadTableModel();
            if (panelSelected.getMyType().equals(AbstractMainPanel.MEMO)) {
                panelSelected.getUI().requestFocus();
                panelSelected.getUI().setVisible(false);
                jLayeredPane1.moveToBack(panelSelected.getUI());
                panelSelected = getAbstractPanelFromTable(AbstractMainPanel.PRODUIT);
                selectPanel(true, m_mode);
                m_config.textarea.requestFocus();
                return;


            }
            if (panelSelected.getSwitchAble() != null) {
                if (!panelSelected.isMainPanel()) {
                    panelSelected.getUI().requestFocus();
                    panelSelected.getUI().setVisible(false);
                    jLayeredPane1.moveToBack(panelSelected.getUI());
                    panelSelected = getAbstractPanelFromTable(panelSelected.getSwitchAble());
                    selectPanel(true, ActionToolBar.ACT_READ);


                }


            } else {
                if (panelSelected.getMyType().equals(AbstractMainPanel.PRODUIT) && m_mode == ActionToolBar.ACT_INSERT) {
                    selectPanel(false, ActionToolBar.ACT_READ);
                    panelSelected = getAbstractPanelFromTable(panelSelected.getNexPanel(0));
                    selectPanel(true, ActionToolBar.ACT_READ);
                    if (simpleProduct && typProd != produit_T.AS) {
                        selectElementInTable(1);
                        return;


                    }


                }


            }
            // m_keyStroke.setAction(panelSelected.getActions(ActionToolBar.ACT_READ));
            // m_actionToolBar.setActionEnabled(panelSelected.getActions(mode));
            m_parent.setCurrentActionEnabled(panelSelected.getActions(ActionToolBar.ACT_READ));
            m_parent.calculTotalUnProduit(returnTheRightProduct());
            if (!m_config.test) returnTheRightProduct().setLocalyModify(true);

        } else {
            if (panelSelected.getMyType().equals(AbstractMainPanel.SUP_REDUC)) {
                if (!m_config.test) {
                    this.setVisible(false);
                    validateProduct();
                    m_parentPane.showProductPanel(0, 0);


                }


            } else {
                selectPanel(false, ActionToolBar.ACT_READ);
                panelSelected = getAbstractPanelFromTable(AbstractMainPanel.SUP_REDUC);
                selectPanel(true, ActionToolBar.ACT_READ);
                if (returnTheRightProduct().getAt_val_vente() == 0) {
                    m_mode = ActionToolBar.ACT_MODIFY;
                    selectElementInTable(1);
                    return;


                }


            }


        }
        if ((m_mode == ActionToolBar.ACT_INSERT || m_mode == ActionToolBar.ACT_MODIFY) && panelSelected.isCheck()) {
            m_mode = ActionToolBar.ACT_READ;


        }
        requestFocus();


    }

    public void doPrint() {
    }

    public void doSwitch() {

        System.out.println("do switch");
        selectNexPanel();


    }

    public int[] getDefaultActionToolBarMask() {
        if (panelSelected != null) return panelSelected.getActions(m_mode);
        return null;


    }

    private void modifyCompta(produit_T prod) {
        if (prod.isIsnewreccord()) m_parent.getDossier().setComptaModify(true);


    }

    private void validateProduct() {
        srcastra.astra.gui.modules.dossier.utils.FillDossierInfo.validateProdutct(m_parent, typProd);
        if (typProd == produit_T.AV) {
            m_parent.getTicket().setIsattached(true);
            m_parent.getDossier().addTicket(m_parent.getTicket());
            m_parent.getTicket().setLocalyModify(false);
            if (m_parent.getTicket() != null)
                if (m_parent.getTicket().checkComptaModif()) m_parent.getDossier().setComptaModify(true);
            modifyCompta(m_parent.getTicket());


        } else if (typProd == produit_T.HO) {
            m_parent.getHotel().setIsattached(true);
            m_parent.getDossier().addHotel(m_parent.getHotel());
            m_parent.getHotel().setLocalyModify(false);
            modifyCompta(m_parent.getHotel());


        } else if (typProd == produit_T.BRO) {
            m_parent.getBrochure().setIsattached(true);
            m_parent.getDossier().addBrochure(m_parent.getBrochure());
            m_parent.getBrochure().setLocalyModify(false);
            modifyCompta(m_parent.getBrochure());


        } else if (typProd == produit_T.AS) {
            m_parent.getAssurance().setIsattached(true);
            m_parent.getDossier().addAssurance(m_parent.getAssurance());
            m_parent.getAssurance().setLocalyModify(false);
            modifyCompta(m_parent.getAssurance());


        } else if (typProd == produit_T.BA) {
            m_parent.getBateau().setIsattached(true);
            m_parent.getDossier().addBateau(m_parent.getBateau());
            m_parent.getBateau().setLocalyModify(false);
            modifyCompta(m_parent.getBateau());


        } else if (typProd == produit_T.VO) {
            m_parent.getVoitureLocation().setIsattached(true);
            m_parent.getDossier().addVoitureLocation(m_parent.getVoitureLocation());
            m_parent.getVoitureLocation().setLocalyModify(false);
            modifyCompta(m_parent.getVoitureLocation());


        } else if (typProd == produit_T.CAR) {
            m_parent.getCar().setIsattached(true);
            m_parent.getDossier().addCar(m_parent.getCar());
            m_parent.getCar().setLocalyModify(false);
            modifyCompta(m_parent.getCar());


        } else if (typProd == produit_T.DIV) {
            m_parent.getDiverst().setIsattached(true);
            m_parent.getDossier().addDivers(m_parent.getDiverst());
            m_parent.getDiverst().setLocalyModify(false);
            modifyCompta(m_parent.getDiverst());


        } else if (typProd == produit_T.TR) {
            m_parent.getTrain().setIsattached(true);
            m_parent.getDossier().addTrain(m_parent.getTrain());
            m_parent.getTrain().setLocalyModify(false);
            modifyCompta(m_parent.getTrain());


        } else if (typProd == produit_T.TAX) {
            m_parent.getTaxi().setIsattached(true);
            m_parent.getTaxi().setLongtime();
            m_parent.getDossier().addTaxi(m_parent.getTaxi());
            m_parent.getTaxi().setLocalyModify(false);
            modifyCompta(m_parent.getTaxi());


        }


    }

    public void setThisAsToolBarComponent() {
    }

    private void selectPanel(boolean selected, int mode) {
        panelSelected.setSelected(selected);
        if (selected) {
            int[] test = panelSelected.getActions(mode);
            if (m_parent.getDossier().getAnnuler() == 0 && m_parent.getDossier().getServicefact() == 0) {
                m_parent.setCurrentActionEnabled(panelSelected.getActions(mode));
            } else {
                m_parent.setCurrentActionEnabled(new int[]{ActionToolBar.DO_CANCEL});
            }
            if (m_config.test) m_actionToolBar.setActionEnabled(panelSelected.getActions(mode));
            jLayeredPane1.moveToFront(panelSelected.getUI());
            panelSelected.getUI().requestFocus();
            panelSelected.getUI().setVisible(true);
            panelSelected.getUI().repaint();





            //panelFromTable.repaint();
            //panelFromTable.revalidate();

        }
    }

    private void selectNexPanel() {
        selectPanel(false, ActionToolBar.ACT_READ);
        if (panelSelected.getNextToHide() != null){
            JPanel panel=((JPanel) getPanelFromTable(panelSelected.getNextToHide()));
        }
        panelSelected = getAbstractPanelFromTable(panelSelected.getNexPanel(0));
        panelSelected.setVisible(true);
        selectPanel(true, ActionToolBar.ACT_READ);
        jLayeredPane1.moveToFront(getPanelFromTable(panelSelected.getMyType()));
        requestFocus();


    }

    private AbstractSousPanel getAbstractPanelFromTable(String key) {
        return (AbstractSousPanel) table.get(key);


    }

    private JPanel getPanelFromTable(String key) {
        return ((AbstractSousPanel) table.get(key)).getUI();


    }

    public String getTitle() {
        return getAbstractPanelFromTable(AbstractMainPanel.PRODUIT).getTitle();


    }

    public void keyPressed(java.awt.event.KeyEvent keyEvent) {
        if (m_mode == ActionToolBar.ACT_READ) {
            System.out.println("keypressed");
            if (keyEvent.getKeyCode() == KeyEvent.VK_UP || keyEvent.getKeyCode() == KeyEvent.VK_DOWN)
                panelSelected.moveInTable(keyEvent.getKeyCode());


        }


    }

    public void keyReleased(java.awt.event.KeyEvent keyEvent) {
    }

    public void keyTyped(java.awt.event.KeyEvent keyEvent) {
    }

    public void goUp() {
        System.out.println("goUp");


    }

    /**
     * Getter for property validateAndDoPrevious.
     *
     * @return Value of property validateAndDoPrevious.
     */
    public ValidateField getValidateAndDoPrevious() {
        return validateAndDoPrevious;


    }

    public java.awt.Component m_getGeneriqueTable() {
        return panelSelected.getTable();


    }

    /**
     * Getter for property m_keyStroke.
     *
     * @return Value of property m_keyStroke.
     */
    public srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.KeyStrokeManager getM_keyStroke() {
        return m_keyStroke;


    }

    /**
     * Setter for property m_keyStroke.
     *
     * @param m_keyStroke New value of property m_keyStroke.
     */
    public void setM_keyStroke(srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.KeyStrokeManager m_keyStroke) {
        this.m_keyStroke = m_keyStroke;


    }

    /**
     * Creates a new instance of MainPanelListener
     */
    public void goBack(MainPanelEvent evt) {
    }

    public void doF10() {
        try {
            compFocusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();


        } catch (NullPointerException nu) {
            nu.printStackTrace();
            System.out.println("composant null");


        }
        if (panelSelected.getMyType().equals(AbstractMainPanel.PRODUIT)) {
            panelSelected.setSelected(false);
            panelSelected = getAbstractPanelFromTable(AbstractMainPanel.MEMO);
            selectPanel(true, ActionToolBar.ACT_MODIFY);
            panelSelected.doNext(true);


        }


    }

    public void doF7() {
    }

    public void doDown() {
    }

    public void doUp() {
    }

    /**
     * Setter for property validateAndDoPrevious.
     *
     * @param validateAndDoPrevious New value of property validateAndDoPrevious.
     */
    //JInternalFrame m_parentFrame;
    produit_T generiqueProduct;
    AbstractSousPanel panelSelected;
    PanelFactory pfact;
    Hashtable table;
    ToolBarInteraction tb_interaction;
    DossierMainScreenModule m_parent;
    ActionToolBar m_actionToolBar;
    DossierProduitPane m_parentPane;
    int m_mode;
    JButton jButton1;
    private ValidateField validateAndDoPrevious;
    private KeyStrokeManager m_keyStroke;
    boolean simpleProduct;
    Component compFocusOwner;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredPane1;
    // End of variables declaration//GEN-END:variables


}















