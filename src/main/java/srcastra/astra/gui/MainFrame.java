/*

 * MainFrame.java


 *
 * Created on 6 mars 2002, 9:57


*/

package srcastra.astra.gui;


import java.awt.Point;
import java.awt.Dimension;
import java.util.*;

import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.gui.sys.*;
import srcastra.astra.sys.rmi.astrainterface;
//ajout de Driart le 11 aout 2003
import java.io.*;
//end ajout
//ajout de Driart le 12 aout 2003
import srcastra.astra.sys.rmi.FactureDeFloppy;

import java.sql.*;
//end ajout
import srcastra.astra.gui.sys.ErreurScreenLibrary;
import srcastra.astra.sys.classetransfert.Grpdecision_T;
//import srcastra.astra.edition.AListDialog;
import srcastra.astra.sys.configuration.*;
import srcastra.astra.sys.classetransfert.dossier.produit_T;

import javax.swing.JOptionPane;

import srcastra.astra.sys.classetransfert.dossier.*;

import javax.swing.*;

import srcastra.astra.gui.components.combobox.liste2.Mediator;
import srcastra.astra.gui.modules.compta.achat.MenuDesFichiers;
import srcastra.astra.gui.modules.*;
import srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux.*;
import srcastra.astra.gui.modules.dossier.productSpecification.integration.*;
import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.gui.modules.agenda.*;
import srcastra.astra.sys.classetransfert.utils.*;

/**
 * @author Sébastien
 */

public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */

    Mediator mediator2;
    MainFrameKeystroke keystroke;
    int selectedDossier;
    boolean rattrap = false;
    boolean ncfs = false;
    boolean groupe = false;
    boolean modeGroupe = false;

    public MainFrame(){};
    public MainFrame(astrainterface serveur, Loginusers_T currentUser) {
        this.setTitle("ASTRA [Travel Back-Office Systems]");
        srcastra.astra.sys.compta.TvaVente.setTvaFrame(serveur, currentUser, new substitutDossierModule(serveur, currentUser));
        srcastra.astra.sys.utils.Utils.setServeur(serveur);
        srcastra.astra.sys.utils.Utils.setUser(currentUser);
        this.serveur = serveur;
        //     vente=new srcastra.astra.gui.components.tva.TvaFrame(this,true,this.serveur, this.currentUser, 2,new substitutDossierModule(this.serveur,this.currentUser));
        this.currentUser = currentUser;
        mediator2 = new Mediator();
        mediator2.registerModule(this);
        mediator = new srcastra.astra.gui.modules.config.ConfigMediator(this);
        srcastra.astra.sys.configuration.MainConfig.getMainConfig();
        generale = new Hashtable();
        supReducVector = new Hashtable();
        memoVector = new Hashtable();
        typeTvaVector = new Hashtable();
        regimeTvaVector = new Hashtable();
        bonCommandeVector = new Hashtable();
        typPayementVector = new Hashtable();
        divers = new Hashtable();
        desclog = new Hashtable();
        generale.put(new Integer(AbstractRequete.SUP_RECUC), supReducVector);
        generale.put(new Integer(AbstractRequete.MEMO), memoVector);
        generale.put(new Integer(AbstractRequete.TVA_TYPE), typeTvaVector);
        generale.put(new Integer(AbstractRequete.TVA_REGIME), regimeTvaVector);
        generale.put(new Integer(AbstractRequete.IMPRESSION), bonCommandeVector);
        generale.put(new Integer(AbstractRequete.TYPE_PAYEMENT), typPayementVector);
        generale.put(new Integer(AbstractRequete.DECSRIPTIF_LOG), desclog);
        generale.put(new Integer(AbstractRequete.DIVERS), divers);
        initComponents();
        initComponents2();
        grp_menu_btn.setVisible(false);
        try {
            this.configRmi = serveur.renvConfigRmiObject(currentUser.getUrcleunik());
            fournisseurRmi = serveur.renvFournisseurRmiObject(currentUser.getUrcleunik());
            srcastra.astra.sys.configuration.MainConfig.getMainConfig().setConfig(configRmi.selectConfig(currentUser.getUrcleunik()));
            if (srcastra.astra.sys.configuration.MainConfig.getMainConfig() == null) {
                initConfig();
            }
            exercice = serveur.renvParamCompta(currentUser.getUrcleunik()).getExerciceCourant(currentUser.getUrcleunik());
            comptainferface = serveur.renvParamCompta(currentUser.getUrcleunik());
            // System.out.println("[*******************************]verif si config est null"+mainconfig.getConfig());
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);
        }
        catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
        }
        setSize(805, 600);
        setLocation(getCenterPosition());
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/lion.gif")).getImage());
        sockclie = new srcastra.astra.sys.reseaux.SocketClient(this.currentUser);
        sockclie.start();
        ToolTipManager.sharedInstance().setReshowDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(3600000);
        keystroke = new MainFrameKeystroke(this);
        keystroke.registerComponent();
        keystroke.setAction(new int[]{1, 2});
        setMenuAction();
        // javax.swing.FocusManager.setCurrentManager(new srcastra.astra.gui.components.focusmanager.OwnfocusManagerSwing());
        grp_menu_user.setText("USER : " + currentUser.getUrlogin());
        enableMenu(true, currentUser.getUrrights());
        //int param=srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getPassagerVisibleBDC()==0 && facture==0 ) || (srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getPassagerVisibleFact()
        //serveur.renvDossierRmiObject(currentUser.getUrcleunik()).
        try {
            serveur.renvDossierRmiObject(currentUser.getUrcleunik()).setVerifAgenda(currentUser.getUrcleunik());
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);
        }
        catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
        }
        AgendaFrame agendaFrame = new AgendaFrame(this, true, serveur, currentUser, CalculDate.getTodayDate(), null);

        if (agendaFrame.show == true)
            agendaFrame.show();
        else
            agendaFrame.close();
    }

    public void setRattrapage(boolean rattrapage) {
        jCheckBoxMenuItem1.setSelected(rattrapage);

    }

    public boolean getRattrapage() {
        return jCheckBoxMenuItem1.isSelected();

    }

    public void setNCFactService(boolean ncfact) {

        this.ncfs = ncfact;

    }

    public void setGroupe(boolean groupe) {

        this.groupe = groupe;

    }

    public boolean getGroupe() {

        return this.groupe;
    }

    public boolean getNCFactService() {

        return this.ncfs;

    }


    private void initConfig() {

        //enableMenu(false);

        grp_mcp_mainpan.initialisePanelConfiguration();

        //AbstractConfig_T config=new AbstractConfig_T();

    }

    private void enableMenu(boolean enable, int urright) {
        boolean inverse = true;
        if (enable = true)
            inverse = false;
        switch (urright) {

            case 1:
                grp_MenuItem_Langue.setEnabled(enable);
                grp_MenuItem_TypeTva.setEnabled(enable);
                ///  grp_Menu_integration.setEnabled(inverse);
                grp_Menu_parametre.setEnabled(true);
                grp_Menu_AutreApplication.setEnabled(true);
                grp_MenuItem_Langue.setEnabled(true);
                grp_MenuItem_TypeTva.setEnabled(true);
                grp_menui_od.setEnabled(true);
                grp_menui_bank.setEnabled(true);
                grp_Menu_Achat.setEnabled(true);
                grp_Menu_parametre.setEnabled(true);
                grp_Menu_AutreApplication.setEnabled(true);
                grp_MenuItem_Fournisseur.setEnabled(true);
                grp_Sep_SignaletiqueSeparator.setEnabled(true);
                grp_MenuItem_Devise.setEnabled(true);
                grp_MenuItem_Langue.setEnabled(true);
                grp_MenuItem_Pays.setEnabled(true);
                grp_MenuItem_TitrePersonnes.setEnabled(true);
                grp_MenuItem_TypeTva.setEnabled(true);
                grp_MenuItem_Destination.setEnabled(true);
                grp_MenuItem_Compagnie.setEnabled(true);
                grp_MenuItem_Embarq.setEnabled(true);
                grp_menui_od.setEnabled(true);
                grp_menui_bank.setEnabled(true);
                break;

            case 2:
                //  grp_Menu_integration.setEnabled(false);
                grp_Menu_parametre.setEnabled(false);
                grp_Menu_AutreApplication.setEnabled(false);
                grp_MenuItem_Langue.setEnabled(false);
                grp_MenuItem_TypeTva.setEnabled(false);
                grp_menui_od.setEnabled(false);
                grp_menui_bank.setEnabled(false);
                grp_Menu_Achat.setEnabled(true);
                grp_Menu_parametre.setEnabled(true);
                grp_Menu_AutreApplication.setEnabled(true);
                grp_MenuItem_Fournisseur.setEnabled(true);
                grp_Sep_SignaletiqueSeparator.setEnabled(true);
                grp_MenuItem_Devise.setEnabled(true);
                grp_MenuItem_Langue.setEnabled(true);
                grp_MenuItem_Pays.setEnabled(true);
                grp_MenuItem_TitrePersonnes.setEnabled(true);
                grp_MenuItem_TypeTva.setEnabled(true);
                grp_MenuItem_Destination.setEnabled(true);
                grp_MenuItem_Compagnie.setEnabled(true);
                grp_MenuItem_Embarq.setEnabled(true);
                grp_menui_od.setEnabled(true);
                grp_menui_bank.setEnabled(true);

                break;

            case 3:

                grp_Menu_Achat.setEnabled(false);
                grp_Menu_parametre.setEnabled(false);
                grp_Menu_AutreApplication.setEnabled(false);
                grp_MenuItem_Fournisseur.setEnabled(false);
                grp_Sep_SignaletiqueSeparator.setEnabled(false);
                // grp_MenuItem_CodePostaux.setEnabled(false);
                grp_MenuItem_Devise.setEnabled(false);
                grp_MenuItem_Langue.setEnabled(false);
                grp_MenuItem_Pays.setEnabled(false);
                grp_MenuItem_TitrePersonnes.setEnabled(false);
                grp_MenuItem_TypeTva.setEnabled(false);
                grp_MenuItem_Destination.setEnabled(false);
                grp_MenuItem_Compagnie.setEnabled(false);
                grp_MenuItem_Embarq.setEnabled(false);
                grp_menui_od.setEnabled(false);
                grp_menui_bank.setEnabled(false);
                break;

            case 4:

                break;

        }

    }

    /**
     * This method is called from within the constructor to
     * <p/>
     * initialize the form.
     * <p/>
     * <p/>
     * WARNING: Do NOT modify this code. The content of this method is
     * <p/>
     * always regenerated by the Form Editor.
     */

    private void initComponents() {//GEN-BEGIN:initComponents
        grp_ButGroup_gestionFenetre = new javax.swing.ButtonGroup();
        grp_Pan_MainNorth = new javax.swing.JPanel();
        grp_Tbar_modules = new javax.swing.JToolBar();
        grp_Pan_MainCenter = new javax.swing.JPanel();
        grp_Mbar_Main = new javax.swing.JMenuBar();
        grp_Menu_Fichier = new javax.swing.JMenu();
        grp_SMenu_Signalitique = new javax.swing.JMenu();
        grp_MenuItem_Clients = new javax.swing.JMenuItem();
        grp_MenuItem_Fournisseur = new javax.swing.JMenuItem();
        grp_Sep_SignaletiqueSeparator = new javax.swing.JSeparator();
        grp_MenuItem_CodePostaux = new javax.swing.JMenuItem();
        grp_MenuItem_Devise = new javax.swing.JMenuItem();
        grp_MenuItem_Langue = new javax.swing.JMenuItem();
        grp_MenuItem_Pays = new javax.swing.JMenuItem();
        grp_MenuItem_TitrePersonnes = new javax.swing.JMenuItem();
        grp_MenuItem_TypeTva = new javax.swing.JMenuItem();
        grp_MenuItem_Destination = new javax.swing.JMenuItem();
        grp_MenuItem_Compagnie = new javax.swing.JMenuItem();
        grp_MenuItem_Embarq = new javax.swing.JMenuItem();
        grp_MenuItem_Edition = new javax.swing.JMenuItem();
        grp_MenuItem_agenda = new javax.swing.JMenuItem();
        grp_JMenuItem_session = new javax.swing.JMenuItem();
        grp_Menu_Gestion = new javax.swing.JMenu();
        grp_MenuItem_Dossier = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        grp_menu_integra = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        grp_menu_btn = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        grp_Menu_Financier = new javax.swing.JMenu();
        grp_MenuItem_caisse = new javax.swing.JMenuItem();
        grp_menui_od = new javax.swing.JMenuItem();
        grp_menui_bank = new javax.swing.JMenuItem();
        grp_Menu_Achat = new javax.swing.JMenu();
        grp_Menu_integration = new javax.swing.JMenuItem();
        grp_MenuItem_Achat = new javax.swing.JMenuItem();
        grp_MenuItem_bsp = new javax.swing.JMenuItem();
        grp_Menu_Impression = new javax.swing.JMenu();
        grp_MenuItem_Listes = new javax.swing.JMenuItem();
        grp_Menu_parametre = new javax.swing.JMenu();
        grp_Menu_produit = new javax.swing.JMenu();
        grp_MenuItem_Groupe_Dec = new javax.swing.JMenuItem();
        grp_Menu_aide_dec = new javax.swing.JMenu();
        grp_menu_aide_decision = new javax.swing.JMenuItem();
        grp_Menu_Memo = new javax.swing.JMenuItem();
        grp_MenuItem_typtva = new javax.swing.JMenuItem();
        grp_MenuItem_regimTva = new javax.swing.JMenuItem();
        grp_MenuItem_Logement = new javax.swing.JMenuItem();
        grp_MenuItem_Transport = new javax.swing.JMenuItem();
        grp_MnItem_Options = new javax.swing.JMenuItem();
        grp_Menu_Document = new javax.swing.JMenuItem();
        grp_menu_Desclog = new javax.swing.JMenuItem();
        grp_menu_divers = new javax.swing.JMenuItem();
        grp_Menu_user = new javax.swing.JMenuItem();
        grp_Menu_TypePayement = new javax.swing.JMenuItem();
        grp_Menu_CaisseLibelle = new javax.swing.JMenuItem();
        grp_JMenuitem_plc = new javax.swing.JMenuItem();
        grp_JMenuitem_connection = new javax.swing.JMenuItem();
        grp_Menu_AutreApplication = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        grp_Menu_Fenetres = new javax.swing.JMenu();
        grp_MenuItem_Reorganisation = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        grp_Menu_Aide = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        grp_menu_user = new javax.swing.JMenu();

        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        grp_Pan_MainNorth.setLayout(new java.awt.GridLayout(1, 0));

        grp_Pan_MainNorth.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(2, 2, 2, 2)));
        grp_Pan_MainNorth.add(grp_Tbar_modules);

        getContentPane().add(grp_Pan_MainNorth, java.awt.BorderLayout.NORTH);

        grp_Pan_MainCenter.setLayout(new java.awt.GridLayout(1, 0));

        getContentPane().add(grp_Pan_MainCenter, java.awt.BorderLayout.CENTER);

        grp_Mbar_Main.setEnabled(false);
        grp_Menu_Fichier.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("Mn_Fichier"));
        grp_Menu_Fichier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Menu_FichierActionPerformed(evt);
            }
        });

        grp_SMenu_Signalitique.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("SMn_Signalitique"));
        grp_MenuItem_Clients.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        grp_MenuItem_Clients.setText(java.util.ResourceBundle.getBundle("srcastra.astra.locale.MenuPrincipal", currentUser.getLangage()).getString("MnItem_Clients"));
        grp_MenuItem_Clients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_ClientsActionPerformed(evt);
            }
        });

        grp_SMenu_Signalitique.add(grp_MenuItem_Clients);

        grp_MenuItem_Fournisseur.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        grp_MenuItem_Fournisseur.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("MnItem_Fournisseur"));
        grp_MenuItem_Fournisseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_FournisseurActionPerformed(evt);
            }
        });

        grp_SMenu_Signalitique.add(grp_MenuItem_Fournisseur);

        grp_SMenu_Signalitique.add(grp_Sep_SignaletiqueSeparator);

        grp_MenuItem_CodePostaux.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("MnItem_CodePostaux"));
        grp_MenuItem_CodePostaux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_CodePostauxActionPerformed(evt);
            }
        });

        grp_SMenu_Signalitique.add(grp_MenuItem_CodePostaux);

        grp_MenuItem_Devise.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("MnItem_Devise"));
        grp_MenuItem_Devise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_DeviseActionPerformed(evt);
            }
        });

        grp_SMenu_Signalitique.add(grp_MenuItem_Devise);

        grp_MenuItem_Langue.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("MnItem_Langue"));
        grp_MenuItem_Langue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_LangueActionPerformed(evt);
            }
        });

        grp_SMenu_Signalitique.add(grp_MenuItem_Langue);

        grp_MenuItem_Pays.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("MnItem_Pays"));
        grp_MenuItem_Pays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_PaysActionPerformed(evt);
            }
        });

        grp_SMenu_Signalitique.add(grp_MenuItem_Pays);

        grp_MenuItem_TitrePersonnes.setText(java.util.ResourceBundle.getBundle("srcastra.astra.locale.MenuPrincipal", currentUser.getLangage()).getString("MnItem_titrePersonne"));
        grp_MenuItem_TitrePersonnes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_TitrePersonnesActionPerformed(evt);
            }
        });

        grp_SMenu_Signalitique.add(grp_MenuItem_TitrePersonnes);

        grp_MenuItem_TypeTva.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("MnItem_TypeTva"));
        grp_MenuItem_TypeTva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_TypeTvaActionPerformed(evt);
            }
        });

        grp_SMenu_Signalitique.add(grp_MenuItem_TypeTva);

        grp_MenuItem_Destination.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("MnItem_Destination"));
        grp_MenuItem_Destination.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_DestinationActionPerformed(evt);
            }
        });

        grp_SMenu_Signalitique.add(grp_MenuItem_Destination);

        grp_MenuItem_Compagnie.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("MnItem_Compagnie"));
        grp_MenuItem_Compagnie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_CompagnieActionPerformed(evt);
            }
        });

        grp_SMenu_Signalitique.add(grp_MenuItem_Compagnie);

        grp_MenuItem_Embarq.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal").getString("Mn_embar"));
        grp_MenuItem_Embarq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_EmbarqActionPerformed(evt);
            }
        });

        grp_SMenu_Signalitique.add(grp_MenuItem_Embarq);

        grp_Menu_Fichier.add(grp_SMenu_Signalitique);

        grp_MenuItem_Edition.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("MnItem_Edition"));
        grp_MenuItem_Edition.setEnabled(false);
        grp_Menu_Fichier.add(grp_MenuItem_Edition);

        grp_MenuItem_agenda.setText("Agenda");
        grp_MenuItem_agenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_agendaActionPerformed(evt);
            }
        });
        grp_MenuItem_agenda.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                grp_MenuItem_agendaComponentShown(evt);
            }
        });

        grp_Menu_Fichier.add(grp_MenuItem_agenda);

        grp_JMenuItem_session.setText("Change user");
        grp_JMenuItem_session.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JMenuItem_sessionActionPerformed(evt);
            }
        });

        grp_Menu_Fichier.add(grp_JMenuItem_session);

        grp_Mbar_Main.add(grp_Menu_Fichier);

        grp_Menu_Gestion.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("Mn_Gestion"));
        grp_Menu_Gestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Menu_GestionActionPerformed(evt);
            }
        });

        grp_MenuItem_Dossier.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        grp_MenuItem_Dossier.setText("Dossier");
        grp_MenuItem_Dossier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_DossierActionPerformed(evt);
            }
        });

        grp_Menu_Gestion.add(grp_MenuItem_Dossier);

        grp_Menu_Gestion.add(jSeparator2);

        grp_menu_integra.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("MnItem_integra"));
        grp_menu_integra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_menu_integraActionPerformed(evt);
            }
        });

        grp_Menu_Gestion.add(grp_menu_integra);

        jCheckBoxMenuItem1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal").getString("mnt_check_rattrap"));
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });

        grp_Menu_Gestion.add(jCheckBoxMenuItem1);

        jCheckBoxMenuItem2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal").getString("mnt_check_groupe"));
        jCheckBoxMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem2ActionPerformed(evt);
            }
        });

        grp_Menu_Gestion.add(jCheckBoxMenuItem2);

        grp_menu_btn.setText("BTN");
        grp_menu_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_menu_btnActionPerformed(evt);
            }
        });

        grp_Menu_Gestion.add(grp_menu_btn);

        jMenuItem2.setText("Batch Fact.");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });

        grp_Menu_Gestion.add(jMenuItem2);

        grp_Mbar_Main.add(grp_Menu_Gestion);

        grp_Menu_Financier.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("Mn_Financier"));
        grp_Menu_Financier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Menu_FinancierActionPerformed(evt);
            }
        });
        grp_Menu_Financier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                grp_Menu_FinancierKeyPressed(evt);
            }
        });

        grp_MenuItem_caisse.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", currentUser.getLangage()).getString("Cai"));
        grp_MenuItem_caisse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_caisseActionPerformed(evt);
            }
        });

        grp_Menu_Financier.add(grp_MenuItem_caisse);

        grp_menui_od.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("jmenuod"));
        grp_menui_od.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_menui_odActionPerformed(evt);
            }
        });

        grp_Menu_Financier.add(grp_menui_od);

        grp_menui_bank.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("jmenubank"));
        grp_menui_bank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_menui_bankActionPerformed(evt);
            }
        });

        grp_Menu_Financier.add(grp_menui_bank);

        grp_Mbar_Main.add(grp_Menu_Financier);

        grp_Menu_Achat.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("Mn_Achat"));
        grp_Menu_Achat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Menu_AchatActionPerformed(evt);
            }
        });

        grp_Menu_integration.setText("Integration automatique");
        grp_Menu_integration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Menu_integrationActionPerformed(evt);
            }
        });

        grp_Menu_Achat.add(grp_Menu_integration);

        grp_MenuItem_Achat.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta", currentUser.getLangage()).getString("ach_ach"));
        grp_MenuItem_Achat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_AchatActionPerformed(evt);
            }
        });

        grp_Menu_Achat.add(grp_MenuItem_Achat);

        grp_MenuItem_bsp.setText("BSP");
        grp_MenuItem_bsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_bspActionPerformed(evt);
            }
        });

        grp_Menu_Achat.add(grp_MenuItem_bsp);

        grp_Mbar_Main.add(grp_Menu_Achat);

        grp_Menu_Impression.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("Mn_Impressions"));
        grp_MenuItem_Listes.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("MnItem_List"));
        grp_MenuItem_Listes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_ListesActionPerformed(evt);
            }
        });

        grp_Menu_Impression.add(grp_MenuItem_Listes);

        grp_Mbar_Main.add(grp_Menu_Impression);

        grp_Menu_parametre.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("Mn_Parametre"));
        grp_Menu_parametre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Menu_parametreActionPerformed(evt);
            }
        });

        grp_Menu_produit.setText("Produit");
        grp_Menu_produit.setEnabled(false);
        grp_MenuItem_Groupe_Dec.setText("Groupe de d\u00e9cision");
        grp_MenuItem_Groupe_Dec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_Groupe_DecActionPerformed(evt);
            }
        });

        grp_Menu_produit.add(grp_MenuItem_Groupe_Dec);

        grp_Menu_parametre.add(grp_Menu_produit);

        grp_Menu_aide_dec.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal").getString("mn_aide_dec"));
        grp_menu_aide_decision.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal").getString("mn_lib_sup"));
        grp_menu_aide_decision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_menu_aide_decisionActionPerformed(evt);
            }
        });

        grp_Menu_aide_dec.add(grp_menu_aide_decision);

        grp_Menu_Memo.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal").getString("mn_memo"));
        grp_Menu_Memo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Menu_MemoActionPerformed(evt);
            }
        });

        grp_Menu_aide_dec.add(grp_Menu_Memo);

        grp_Menu_parametre.add(grp_Menu_aide_dec);

        grp_MenuItem_typtva.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal").getString("MnItem_TypeTva"));
        grp_MenuItem_typtva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_typtvaActionPerformed(evt);
            }
        });

        grp_Menu_parametre.add(grp_MenuItem_typtva);

        grp_MenuItem_regimTva.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal").getString("MnItem_RegimeTva"));
        grp_MenuItem_regimTva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_regimTvaActionPerformed(evt);
            }
        });

        grp_Menu_parametre.add(grp_MenuItem_regimTva);

        grp_MenuItem_Logement.setText(java.util.ResourceBundle.getBundle("srcastra.astra.locale.MenuPrincipal", currentUser.getLangage()).getString("MnItem_Logement"));
        grp_MenuItem_Logement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_LogementActionPerformed(evt);
            }
        });

        grp_Menu_parametre.add(grp_MenuItem_Logement);

        grp_MenuItem_Transport.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal").getString("MnItem_Transport"));
        grp_MenuItem_Transport.setActionCommand("Item");
        grp_MenuItem_Transport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MenuItem_TransportActionPerformed(evt);
            }
        });

        grp_Menu_parametre.add(grp_MenuItem_Transport);

        grp_MnItem_Options.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("Mn_Options"));
        grp_MnItem_Options.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_MnItem_OptionsActionPerformed(evt);
            }
        });

        grp_Menu_parametre.add(grp_MnItem_Options);

        grp_Menu_Document.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale").getString("mn_item_Doc"));
        grp_Menu_Document.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Menu_DocumentActionPerformed(evt);
            }
        });

        grp_Menu_parametre.add(grp_Menu_Document);

        grp_menu_Desclog.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale").getString("desc_title"));
        grp_menu_Desclog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_menu_DesclogActionPerformed(evt);
            }
        });

        grp_Menu_parametre.add(grp_menu_Desclog);

        grp_menu_divers.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale").getString("mn_item_divers"));
        grp_menu_divers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_menu_diversActionPerformed(evt);
            }
        });

        grp_Menu_parametre.add(grp_menu_divers);

        grp_Menu_user.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_Menu_user.setText("User");
        grp_Menu_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Menu_userActionPerformed(evt);
            }
        });

        grp_Menu_parametre.add(grp_Menu_user);

        grp_Menu_TypePayement.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", currentUser.getLangage()).getString("menu_item_typepai"));
        grp_Menu_TypePayement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Menu_TypePayementActionPerformed(evt);
            }
        });

        grp_Menu_parametre.add(grp_Menu_TypePayement);

        grp_Menu_CaisseLibelle.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/generale", currentUser.getLangage()).getString("menu_item_cailib"));
        grp_Menu_CaisseLibelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Menu_CaisseLibelleActionPerformed(evt);
            }
        });

        grp_Menu_parametre.add(grp_Menu_CaisseLibelle);

        grp_JMenuitem_plc.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("jmenuitem"));
        grp_JMenuitem_plc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JMenuitem_plcActionPerformed(evt);
            }
        });

        grp_Menu_parametre.add(grp_JMenuitem_plc);

        grp_JMenuitem_connection.setText("Show connections");
        grp_JMenuitem_connection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_JMenuitem_connectionActionPerformed(evt);
            }
        });

        grp_Menu_parametre.add(grp_JMenuitem_connection);

        grp_Mbar_Main.add(grp_Menu_parametre);

        grp_Menu_AutreApplication.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("Mn_AutreApplication"));
        grp_Menu_AutreApplication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Menu_AutreApplicationActionPerformed(evt);
            }
        });

        jMenuItem4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/compta").getString("exportc"));
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });

        grp_Menu_AutreApplication.add(jMenuItem4);

        jMenuItem5.setText("CSV");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });

        grp_Menu_AutreApplication.add(jMenuItem5);

        grp_Mbar_Main.add(grp_Menu_AutreApplication);

        grp_Menu_Fenetres.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("Mn_Fenetres"));
        grp_MenuItem_Reorganisation.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/MenuPrincipal", currentUser.getLangage()).getString("MnItem_ReogFenetre"));
        grp_MenuItem_Reorganisation.setEnabled(false);
        grp_Menu_Fenetres.add(grp_MenuItem_Reorganisation);

        grp_Menu_Fenetres.add(jSeparator1);

        grp_Mbar_Main.add(grp_Menu_Fenetres);

        grp_Menu_Aide.setText("?");
        grp_Menu_Aide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_Menu_AideActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Help");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });

        grp_Menu_Aide.add(jMenuItem1);

        jMenuItem3.setText("About");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });

        grp_Menu_Aide.add(jMenuItem3);

        grp_Mbar_Main.add(grp_Menu_Aide);

        grp_menu_user.setForeground(new java.awt.Color(204, 0, 51));
        grp_menu_user.setText("USER : THOM");
        grp_menu_user.setFocusable(false);
        grp_menu_user.setFont(new java.awt.Font("Tahoma", 1, 12));
        grp_menu_user.setDelay(0);
        grp_menu_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grp_menu_userActionPerformed(evt);
            }
        });

        grp_Mbar_Main.add(grp_menu_user);

        setJMenuBar(grp_Mbar_Main);

        pack();
    }//GEN-END:initComponents

    private void grp_Menu_FinancierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grp_Menu_FinancierKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_grp_Menu_FinancierKeyPressed

    private void jCheckBoxMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem2ActionPerformed
        // TODO add your handling code here:

        if (jCheckBoxMenuItem2.isSelected()) this.modeGroupe = true;
        else modeGroupe = false;

        if (jCheckBoxMenuItem1.isSelected()) {
            jCheckBoxMenuItem1.setSelected(false);
            rattrap = false;
        }

    }//GEN-LAST:event_jCheckBoxMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        grp_mcp_mainpan.displayModulesMailOut(evt.getSource());
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        grp_mcp_mainpan.displayModulesExportCompta(evt.getSource());
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        grp_mcp_mainpan.displayModulesAbout(evt.getSource());


    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void grp_Menu_AideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Menu_AideActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grp_Menu_AideActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        grp_mcp_mainpan.displayModulesBatchFacturation(evt.getSource());


    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void grp_Menu_FinancierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Menu_FinancierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grp_Menu_FinancierActionPerformed

    private void grp_Menu_AutreApplicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Menu_AutreApplicationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grp_Menu_AutreApplicationActionPerformed

    private void grp_menu_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_menu_btnActionPerformed
        grp_mcp_mainpan.displayModulesBtn(evt.getSource());
    }//GEN-LAST:event_grp_menu_btnActionPerformed

    private void grp_MenuItem_bspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_bspActionPerformed
        new srcastra.astra.gui.modules.bsp.BspFrame((java.awt.Frame) this, true, this.serveur, this.currentUser).show();
    }//GEN-LAST:event_grp_MenuItem_bspActionPerformed


    private void grp_JMenuItem_sessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JMenuItem_sessionActionPerformed

        closeAllWindows();

    }//GEN-LAST:event_grp_JMenuItem_sessionActionPerformed


    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed

        if (jCheckBoxMenuItem1.isSelected()) rattrap = true;

        else rattrap = false;

        if (jCheckBoxMenuItem2.isSelected()) {
            jCheckBoxMenuItem2.setSelected(false);
            modeGroupe = false;
        }

    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed


    private void grp_JMenuitem_connectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JMenuitem_connectionActionPerformed

        try {

            ArrayList connection = serveur.getTransacState(currentUser.getSociete());
            if (connection != null) {
                for (int i = 0; i < connection.size(); i++) {
                    Object[] tmp = (Object[]) connection.get(i);
                    System.out.println("nom " + tmp[0].toString() + " connection state " + tmp[1].toString());
                }
            }
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);
        }
        catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
        }

    }//GEN-LAST:event_grp_JMenuitem_connectionActionPerformed


    private void grp_menu_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_menu_userActionPerformed

        grp_mcp_mainpan.displayModulesExportCompta(evt.getSource());

    }//GEN-LAST:event_grp_menu_userActionPerformed


    private void grp_menui_bankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_menui_bankActionPerformed

        grp_mcp_mainpan.displayModulesBank(evt.getSource());

    }//GEN-LAST:event_grp_menui_bankActionPerformed


    private void grp_menui_odActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_menui_odActionPerformed

        grp_mcp_mainpan.displayModulesOd(evt.getSource());

    }//GEN-LAST:event_grp_menui_odActionPerformed


    private void grp_JMenuitem_plcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_JMenuitem_plcActionPerformed

        grp_mcp_mainpan.displayModulesPlanComptable(evt.getSource());

    }//GEN-LAST:event_grp_JMenuitem_plcActionPerformed


    private void grp_menu_integraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_menu_integraActionPerformed

        grp_mcp_mainpan.displayModulesIntegrationIATA(evt.getSource());

    }//GEN-LAST:event_grp_menu_integraActionPerformed


    private void grp_Menu_TypePayementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Menu_TypePayementActionPerformed


        grp_mcp_mainpan.displayModulesTypePayement(evt.getSource());         // Add your handling code here:


    }//GEN-LAST:event_grp_Menu_TypePayementActionPerformed


    private void grp_Menu_CaisseLibelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Menu_CaisseLibelleActionPerformed


        grp_mcp_mainpan.displayModulesCaisseLibelle(evt.getSource());


    }//GEN-LAST:event_grp_Menu_CaisseLibelleActionPerformed


    private void grp_MenuItem_agendaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_grp_MenuItem_agendaComponentShown

        // Add your handling code here:


    }//GEN-LAST:event_grp_MenuItem_agendaComponentShown

//ajout de Driart le 25 aout 2003


    private void grp_MenuItem_agendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_agendaActionPerformed

        grp_mcp_mainpan.displayModulesAgenda(evt.getSource());

    }//GEN-LAST:event_grp_MenuItem_agendaActionPerformed

    private void grp_Menu_integrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Menu_integrationActionPerformed

        // Add your handling code here:
        //FactureDeFloppy f = new FactureDeFloppy();
        //FactureDeFloppy [] arr = new FactureDeFloppy[256];
        // new MenuDesFichiers().show();
        grp_mcp_mainpan.displayModulesIntegration(evt.getSource());
        //arr = f.SetFactureDeFloppy("c:\\Test.txt");


    }//GEN-LAST:event_grp_Menu_integrationActionPerformed

//end ajout


    private void grp_Menu_AchatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Menu_AchatActionPerformed

        // Add your handling code here:

    }//GEN-LAST:event_grp_Menu_AchatActionPerformed


    private void grp_MenuItem_caisseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_caisseActionPerformed

        grp_mcp_mainpan.displayModulesCaisses(evt.getSource());

    }//GEN-LAST:event_grp_MenuItem_caisseActionPerformed


    private void grp_MenuItem_AchatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_AchatActionPerformed
        grp_mcp_mainpan.displayModulesAchat(evt.getSource());      // Add your handling code here:

    }//GEN-LAST:event_grp_MenuItem_AchatActionPerformed


    private void grp_Menu_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Menu_userActionPerformed

        grp_mcp_mainpan.displayModulesUsers(evt.getSource());        // Add your handling code here:

    }//GEN-LAST:event_grp_Menu_userActionPerformed


    private void grp_menu_diversActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_menu_diversActionPerformed


        grp_mcp_mainpan.displayModulesConfig(evt.getSource(), 4, AbstractRequete.DIVERS, true);


    }//GEN-LAST:event_grp_menu_diversActionPerformed


    private void grp_menu_DesclogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_menu_DesclogActionPerformed


        grp_mcp_mainpan.displayModulesConfig(evt.getSource(), 3, AbstractRequete.DECSRIPTIF_LOG, true);


    }//GEN-LAST:event_grp_menu_DesclogActionPerformed


    private void grp_Menu_DocumentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Menu_DocumentActionPerformed


        grp_mcp_mainpan.displayModulesConfig(evt.getSource(), 2, AbstractRequete.IMPRESSION, true);


    }//GEN-LAST:event_grp_Menu_DocumentActionPerformed


    private void grp_MenuItem_regimTvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_regimTvaActionPerformed


        grp_mcp_mainpan.displayModulesConfig(evt.getSource(), 0, AbstractRequete.TVA_REGIME, false);


    }//GEN-LAST:event_grp_MenuItem_regimTvaActionPerformed


    private void grp_MenuItem_typtvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_typtvaActionPerformed


        grp_mcp_mainpan.displayModulesConfig(evt.getSource(), 0, AbstractRequete.TVA_TYPE, false);// Add your handling code here:


    }//GEN-LAST:event_grp_MenuItem_typtvaActionPerformed


    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed


        new srcastra.testhelp.JavaHelpTest(currentUser.getUrlmcleunik());        // Add your handling code here:


    }//GEN-LAST:event_jMenuItem1ActionPerformed


    private void grp_Menu_MemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Menu_MemoActionPerformed


        grp_mcp_mainpan.displayModulesConfig(evt.getSource(), 1, AbstractRequete.MEMO, true); // Add your handling code here:        // Add your handling code here:


    }//GEN-LAST:event_grp_Menu_MemoActionPerformed


    private void grp_menu_aide_decisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_menu_aide_decisionActionPerformed


        grp_mcp_mainpan.displayModulesConfig(evt.getSource(), 0, AbstractRequete.SUP_RECUC, true); // Add your handling code here:


    }//GEN-LAST:event_grp_menu_aide_decisionActionPerformed


    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost


    }//GEN-LAST:event_formFocusLost


    private void grp_Menu_parametreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Menu_parametreActionPerformed


    }//GEN-LAST:event_grp_Menu_parametreActionPerformed


    private void grp_MenuItem_EmbarqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_EmbarqActionPerformed


        grp_mcp_mainpan.displaySignaletique(serveur.COMBO_EMBARQDEBARQ, evt.getSource());


    }//GEN-LAST:event_grp_MenuItem_EmbarqActionPerformed


    private void grp_MenuItem_CompagnieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_CompagnieActionPerformed


        grp_mcp_mainpan.displaySignaletique(serveur.COMBO_COMPAGNIE, evt.getSource());


    }//GEN-LAST:event_grp_MenuItem_CompagnieActionPerformed


    private void grp_MenuItem_DestinationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_DestinationActionPerformed


        grp_mcp_mainpan.displaySignaletique(serveur.COMBO_DESTINATION, evt.getSource());        // Add your handling code here:


    }//GEN-LAST:event_grp_MenuItem_DestinationActionPerformed


    private void grp_MnItem_OptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MnItem_OptionsActionPerformed


        if (optionDialog == null)
            optionDialog = new MainFrame.OptionsDialog(this, "Options", false, new srcastra.astra.gui.conf.ConfigurationPane(mediator, this));


        optionDialog.show();


    }//GEN-LAST:event_grp_MnItem_OptionsActionPerformed


    private void grp_MenuItem_ListesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_ListesActionPerformed

        grp_mcp_mainpan.displayModulesListe(evt.getSource());

        //     if (printDialog ==http://www.petites-annonces.be/FR/Liste4bFR.asp?Page=8&strrech={WHERE{ANNUMBR{is{not{null{{AND{ANPOST{IN{({SELECT{POCODE{FROM{POST{WHERE{POBASSIN{IS{NOT{NULL{AND{POBASSIN{IN{({'WVLKR',{{'XXXXX'){)&strbook=I&strfami=LH&strrubr=00&strsrub=000 null) printDialog = new AListDialog(this, true, serveur, currentUser);

        //   printDialog.show();


    }//GEN-LAST:event_grp_MenuItem_ListesActionPerformed

    private void grp_MenuItem_Groupe_DecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_Groupe_DecActionPerformed


        grp_mcp_mainpan.displayModulesGrpDec(evt.getSource());


    }//GEN-LAST:event_grp_MenuItem_Groupe_DecActionPerformed


    private void grp_Menu_GestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Menu_GestionActionPerformed


    }//GEN-LAST:event_grp_Menu_GestionActionPerformed


    private void grp_MenuItem_DossierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_DossierActionPerformed

        grp_mcp_mainpan.displayDossier(evt.getSource());

    }//GEN-LAST:event_grp_MenuItem_DossierActionPerformed


    private void grp_Menu_FichierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_Menu_FichierActionPerformed

        // Add your handling code here:

    }//GEN-LAST:event_grp_Menu_FichierActionPerformed


    private void grp_MenuItem_LogementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_LogementActionPerformed

        grp_mcp_mainpan.displaySignaletique(serveur.COMBO_LOGEMENT, evt.getSource());

    }//GEN-LAST:event_grp_MenuItem_LogementActionPerformed


    private void grp_MenuItem_TitrePersonnesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_TitrePersonnesActionPerformed

        grp_mcp_mainpan.displaySignaletique(serveur.COMBO_TITREPERSONNES, evt.getSource());


    }//GEN-LAST:event_grp_MenuItem_TitrePersonnesActionPerformed


    private void grp_MenuItem_ClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_ClientsActionPerformed

        grp_mcp_mainpan.displayModulesClients(evt.getSource());


    }//GEN-LAST:event_grp_MenuItem_ClientsActionPerformed


    private void grp_MenuItem_TransportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_TransportActionPerformed

        grp_mcp_mainpan.displaySignaletique(serveur.COMBO_TRANSPORT, evt.getSource());


    }//GEN-LAST:event_grp_MenuItem_TransportActionPerformed


    private void grp_MenuItem_FournisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_FournisseurActionPerformed


        grp_mcp_mainpan.displayModulesFournisseurs(evt.getSource());


    }//GEN-LAST:event_grp_MenuItem_FournisseurActionPerformed


    private void grp_MenuItem_TypeTvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_TypeTvaActionPerformed


        grp_mcp_mainpan.displaySignaletique(serveur.COMBO_TVA_TYPE, evt.getSource());


    }//GEN-LAST:event_grp_MenuItem_TypeTvaActionPerformed


    private void grp_MenuItem_PaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_PaysActionPerformed


        grp_mcp_mainpan.displaySignaletique(serveur.COMBO_PAYS, evt.getSource());


    }//GEN-LAST:event_grp_MenuItem_PaysActionPerformed


    private void grp_MenuItem_LangueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_LangueActionPerformed


        grp_mcp_mainpan.displaySignaletique(serveur.COMBO_LANGUE, evt.getSource());

    }//GEN-LAST:event_grp_MenuItem_LangueActionPerformed


    private void grp_MenuItem_DeviseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_DeviseActionPerformed


        grp_mcp_mainpan.displaySignaletique(serveur.COMBO_DEVISE, evt.getSource());


    }//GEN-LAST:event_grp_MenuItem_DeviseActionPerformed


    private void grp_MenuItem_CodePostauxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grp_MenuItem_CodePostauxActionPerformed


        grp_mcp_mainpan.displaySignaletique(serveur.COMBO_CODE_POST, evt.getSource());


    }

    private void initComponents2() {//GEN-LAST:event_grp_MenuItem_CodePostauxActionPerformed


        grp_mcp_mainpan = new MainCenterPanel(serveur, currentUser, this, this);
        grp_Pan_MainCenter.add(grp_mcp_mainpan);
        grp_Pan_MainCenter.updateUI();

    }


    /**
     * Exit the Application
     */


    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        try {
            unlog();
            serveur.renvDossierRmiObject(currentUser.getUrcleunik()).remoteUnlock(getSelectedDossier());
        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, srcastra.astra.Astra.DEBUG, se);
        }
        catch (java.rmi.RemoteException re) {
            re.printStackTrace();
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);
        }

        try {
            serveur.userlogof(currentUser.getUrcleunik());
        }
        catch (java.rmi.RemoteException re) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, srcastra.astra.Astra.DEBUG, re);

        }

        System.exit(0);

    }//GEN-LAST:event_exitForm

    private void unlog() {
        try {

            String jdbcDriverClassName = "org.gjt.mm.mysql.Driver";
            if (jdbcDriverClassName != null)
                Class.forName(jdbcDriverClassName);
            String url = "";
            url = "jdbc:mysql://ooohtml.no-ip.info:3306/AstraConnect";
            String user = "agence";
            String password = "keyagence3164978520";
            String actif = "";
            Connection con = null;
            boolean oki = false;
            try {
                con = DriverManager.getConnection(url, user, password);
                oki = true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            finally {
                if (!oki) {
                    //url="jdbc:mysql://192.168.1.63:3306/AstraConnect";
                    System.out.println("je passe par ici");
                    try {
                        url = "jdbc:mysql://ns3093828.ovh.net:3306/AstraConnect";
                        con = DriverManager.getConnection(url, user, password);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error param connection");
                        System.exit(0);
                    }
                }

                srcastra.astra.sys.rmi.utils.Systeminfo system = new srcastra.astra.sys.rmi.utils.Systeminfo();
                ArrayList entite = serveur.renvEntiteRmiObject(currentUser.getUrcleunik()).getList(currentUser.getUrcleunik(), 0);

                String eenom = "";

                for (int i = 0; i < entite.size(); i++) {
                    Entite t = (Entite) entite.get(i);
                    if (t.getEecleunik() == currentUser.getUreecleunik())
                        eenom = t.getEenom();

                }

                /*System.out.println(system.getUsername());
                System.out.println(system.getIpadresse());
                System.out.println(system.getOsname());
                System.out.println(system.getJvmversion());*/

                //loginUser_T.length

                //System.out.println(currentUser.getUrlogin());
                //
                String requ = "update LogAstra set ConnectionDeconnection=0, Quand=Quand, Delog = CURRENT_TIMESTAMP(), tempsConnection=(Delog-Quand) where session like ?";
                PreparedStatement pstmt;
                pstmt = con.prepareStatement(requ);
                pstmt.setString(1, currentUser.getUrlogin() + Integer.toString(currentUser.getUrcleunik()));
                // pstmt.setString(1,eenom);
                // pstmt.setString(2,currentUser.getUrlogin());
                // pstmt.setString(3,system.getIpadresse());
                // pstmt.setString(4,system.getJvmversion());
                // pstmt.setString(5,system.getUsername());
                // pstmt.setString(6,"");
                pstmt.execute();
                pstmt.close();
                con.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////

// ADD-REMOVE OPENED WINDOW ITEM

/////////////////////////////////////////////////////////////////////////////////////////////////////////


    private java.util.Vector itemsWindow = new java.util.Vector();

    public void addNewOpenedWindowItem(javax.swing.JInternalFrame iFrame) {
        javax.swing.JCheckBoxMenuItem mnItem = new javax.swing.JCheckBoxMenuItem();
        mnItem.setName(iFrame.getName());
        mnItem.setText(iFrame.getName());
        mnItem.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11));
        mnItem.addActionListener(new ActionPerf(iFrame));
        grp_ButGroup_gestionFenetre.add(mnItem);
        mnItem.setSelected(true);
        itemsWindow.addElement(mnItem);
        grp_Menu_Fenetres.add(mnItem);
        grp_Menu_Fenetres.updateUI();

    }

    public void removeOpenedWindowItem(javax.swing.JInternalFrame iFrame) {

        int pos = getMenuItem(iFrame.getName());
        if (pos > -1) {
            javax.swing.JCheckBoxMenuItem item = (javax.swing.JCheckBoxMenuItem) itemsWindow.get(pos);
            itemsWindow.remove(pos);
            grp_ButGroup_gestionFenetre.remove(item);
            grp_Menu_Fenetres.remove(item);
            grp_Menu_Fenetres.updateUI();

        }

    }


    public void getSignaletique(int typeSignaletique) {

        switch (typeSignaletique) {
            case astrainterface.COMBO_DESTINATION:
                grp_mcp_mainpan.displayNestedSignaletique(typeSignaletique, this.grp_MenuItem_Destination, true);

                break;

            case astrainterface.COMBO_TRANSPORT:
                grp_mcp_mainpan.displayNestedSignaletique(typeSignaletique, this.grp_MenuItem_Transport, true);

                break;

            case astrainterface.COMBO_LOGEMENT:
                grp_mcp_mainpan.displayNestedSignaletique(typeSignaletique, this.grp_MenuItem_Logement, true);

                break;

            case astrainterface.COMBO_EMBARQDEBARQ:

                grp_mcp_mainpan.displayNestedSignaletique(typeSignaletique, this.grp_MenuItem_Embarq, true);

                break;

        }


    }


    public void afficheNestedGroupdec(java.awt.event.ActionEvent evt, boolean nested, InterfaceProduit produit, InterfacePanel panel) {

        grp_mcp_mainpan.displayModulesGrpDec(evt.getSource(), nested, produit, panel);

    }

    public void setSelectedOpenedWindowItem(javax.swing.JInternalFrame iFrame) {
        int pos = getMenuItem(iFrame.getName());
        if (pos > -1) {
            javax.swing.JCheckBoxMenuItem item = (javax.swing.JCheckBoxMenuItem) itemsWindow.get(pos);
            item.setSelected(true);
        }


    }


    private int getMenuItem(String name) {
        for (int i = 0; i < itemsWindow.size(); i++) {
            if (((javax.swing.JCheckBoxMenuItem) itemsWindow.get(i)).getName().equals(name)) {
                return i;

            }
        }
        return -1;

    }

///////////////////////////////////////////////////////////////////////////////////////////////////////

    protected class ActionPerf implements java.awt.event.ActionListener {
        protected javax.swing.JInternalFrame target;

        public ActionPerf(javax.swing.JInternalFrame target) {
            this.target = target;
        }

        public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
            grp_mcp_mainpan.setFrontSelectedIframe(target);

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        srcastra.astra.gui.sys.Plaf.setPaf(false);
        new MainFrame(null, null).show();

    }

    private Point getCenterPosition() {
        Dimension dimProg = this.getSize();
        Dimension dimScreen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int Xp = (dimScreen.width - dimProg.width) / 2;
        int Yp = (dimScreen.height - dimProg.height) / 2;
        return new Point(Xp, Yp);


    }

    /**
     * Getter for property grp_Menu_parametre.
     *
     * @return Value of property grp_Menu_parametre.
     */

    public javax.swing.JMenu getGrp_Menu_parametre() {
        return grp_Menu_parametre;

    }

    /**
     * Setter for property grp_Menu_parametre.
     *
     * @param grp_Menu_parametre New value of property grp_Menu_parametre.
     */

    public void setGrp_Menu_parametre(javax.swing.JMenu grp_Menu_parametre) {
        this.grp_Menu_parametre = grp_Menu_parametre;

    }

    /**
     * Getter for property grp_Menu_produit.
     *
     * @return Value of property grp_Menu_produit.
     */
    public javax.swing.JMenu getGrp_Menu_produit() {
        return grp_Menu_produit;

    }

    /**
     * Setter for property grp_Menu_produit.
     *
     * @param grp_Menu_produit New value of property grp_Menu_produit.
     */

    public void setGrp_Menu_produit(javax.swing.JMenu grp_Menu_produit) {
        this.grp_Menu_produit = grp_Menu_produit;

    }


    /**
     * Getter for property grp_MenuItem_Groupe_Dec.
     *
     * @return Value of property grp_MenuItem_Groupe_Dec.
     */

    public javax.swing.JMenuItem getGrp_MenuItem_Groupe_Dec() {
        return grp_MenuItem_Groupe_Dec;

    }

    /**
     * Setter for property grp_MenuItem_Groupe_Dec.
     *
     * @param grp_MenuItem_Groupe_Dec New value of property grp_MenuItem_Groupe_Dec.
     */

    public void setGrp_MenuItem_Groupe_Dec(javax.swing.JMenuItem grp_MenuItem_Groupe_Dec) {
        this.grp_MenuItem_Groupe_Dec = grp_MenuItem_Groupe_Dec;


    }


    /**
     * Getter for property grpdec.
     *
     * @return Value of property grpdec.
     */


    public Object getGrpdec() {


        return grpdec;


    }


    /**
     * Setter for property grpdec.
     *
     * @param grpdec New value of property grpdec.
     */


    public void setGrpdec(Object grpdec) {


        this.grpdec = grpdec;


    }


    /**
     * Getter for property grp_mcp_mainpan.
     *
     * @return Value of property grp_mcp_mainpan.
     */


    public srcastra.astra.gui.MainCenterPanel getGrp_mcp_mainpan() {


        return grp_mcp_mainpan;


    }


    /**
     * Setter for property grp_mcp_mainpan.
     *
     * @param grp_mcp_mainpan New value of property grp_mcp_mainpan.
     */


    public void setGrp_mcp_mainpan(srcastra.astra.gui.MainCenterPanel grp_mcp_mainpan) {


        this.grp_mcp_mainpan = grp_mcp_mainpan;


    }


    /**
     * Getter for property grp_MenuItem_Clients.
     *
     * @return Value of property grp_MenuItem_Clients.
     */


    public javax.swing.JMenuItem getGrp_MenuItem_Clients() {


        return grp_MenuItem_Clients;


    }


    /**
     * Setter for property grp_MenuItem_Clients.
     *
     * @param grp_MenuItem_Clients New value of property grp_MenuItem_Clients.
     */


    public void setGrp_MenuItem_Clients(javax.swing.JMenuItem grp_MenuItem_Clients) {


        this.grp_MenuItem_Clients = grp_MenuItem_Clients;


    }


    /**
     * Getter for property mediator.
     *
     * @return Value of property mediator.
     */


    public srcastra.astra.gui.modules.config.ConfigMediator getMediator() {


        return mediator;


    }


    /**
     * Setter for property mediator.
     *
     * @param mediator New value of property mediator.
     */


    public void setMediator(srcastra.astra.gui.modules.config.ConfigMediator mediator) {


        this.mediator = mediator;


    }


    /**
     * Getter for property configRmi.
     *
     * @return Value of property configRmi.
     */


    public srcastra.astra.sys.rmi.ConfigRmiInterface getConfigRmi() {


        return configRmi;


    }


    /**
     * Getter for property currentUser.
     *
     * @return Value of property currentUser.
     */


    public srcastra.astra.sys.classetransfert.Loginusers_T getCurrentUser() {


        return currentUser;


    }


    /**
     * Setter for property currentUser.
     *
     * @param currentUser New value of property currentUser.
     */


    public void setCurrentUser(srcastra.astra.sys.classetransfert.Loginusers_T currentUser) {


        this.currentUser = currentUser;

        grp_mcp_mainpan.setCurrentUser(this.currentUser);

        grp_menu_user.setText("USER : " + currentUser.getUrlogin());

        enableMenu(true, currentUser.getUrrights());


    }


    /**
     * Getter for property memoVector.
     *
     * @return Value of property memoVector.
     */

    public void closeAllWindows() {

        javax.swing.JInternalFrame[] iframes = grp_mcp_mainpan.getGrp_DPan_desktop().getAllFrames();

        if (iframes.length > 0) {

            new MessageManager().showMessageDialog(this, "change_sess", "change_sess_title", this.currentUser);

            /* if(rep==0){

               for(int i=0;i<iframes.length;i++){

                   iframes[i].doDefaultCloseAction();

               }

               new LoginFrame(this, true, this.serveur, currentUser.getLangage(), false,this).show();

            }*/


        } else {
            //currentUser.

            new LoginFrame(this, true, this.serveur, currentUser.getLangage(), false, this, "").show();
            grp_menu_user.setText("USER : " + currentUser.getUrlogin());
            enableMenu(true, currentUser.getUrrights());
            //enableMenu(true, currentUser.getUrrights());

        }

    }

    public Hashtable getMemoVector() {

        if (memoVector == null)

            memoVector = new Hashtable();

        return memoVector;


    }


    /**
     * Setter for property memoVector.
     *
     * @param memoVector New value of property memoVector.
     */


    public void setMemoVector(Hashtable memoVector) {


        this.memoVector = memoVector;


    }


    /**
     * Getter for property supReducVector.
     *
     * @return Value of property supReducVector.
     */


    public Hashtable getSupReducVector() {


        if (supReducVector == null)


            supReducVector = new Hashtable();


        return supReducVector;


    }


    /**
     * Setter for property supReducVector.
     *
     * @param supReducVector New value of property supReducVector.
     */


    public void setSupReducVector(Hashtable supReducVector) {


        this.supReducVector = supReducVector;


    }


    /**
     * Getter for property fournisseurRmi.
     *
     * @return Value of property fournisseurRmi.
     */


    public srcastra.astra.sys.rmi.FournisseurRmiInterface getFournisseurRmi() {


        return fournisseurRmi;


    }


    /**
     * Setter for property fournisseurRmi.
     *
     * @param fournisseurRmi New value of property fournisseurRmi.
     */


    public void setFournisseurRmi(srcastra.astra.sys.rmi.FournisseurRmiInterface fournisseurRmi) {


        this.fournisseurRmi = fournisseurRmi;


    }


    /**
     * Getter for property typeTvaVector.
     *
     * @return Value of property typeTvaVector.
     */


    public java.util.Hashtable getTypeTvaVector() {


        return typeTvaVector;


    }


    /**
     * Setter for property typeTvaVector.
     *
     * @param typeTvaVector New value of property typeTvaVector.
     */


    public void setTypeTvaVector(java.util.Hashtable typeTvaVector) {


        this.typeTvaVector = typeTvaVector;


    }


    /**
     * Getter for property regimeTvaVector.
     *
     * @return Value of property regimeTvaVector.
     */


    public java.util.Hashtable getRegimeTvaVector() {
        return regimeTvaVector;

    }


    /**
     * Setter for property regimeTvaVector.
     *
     * @param regimeTvaVector New value of property regimeTvaVector.
     */


    public void setRegimeTvaVector(java.util.Hashtable regimeTvaVector) {
        this.regimeTvaVector = regimeTvaVector;

    }


    /**
     * Getter for property bonCommandeVector.
     *
     * @return Value of property bonCommandeVector.
     */


    public java.util.Hashtable getBonCommandeVector() {
        return bonCommandeVector;

    }


    /**
     * Setter for property bonCommandeVector.
     *
     * @param bonCommandeVector New value of property bonCommandeVector.
     */


    public void setBonCommandeVector(java.util.Hashtable bonCommandeVector) {
        this.bonCommandeVector = bonCommandeVector;
    }


    /**
     * Getter for property typPayementVector.
     *
     * @return Value of property typPayementVector.
     */


    public java.util.Hashtable getTypPayementVector() {


        return typPayementVector;


    }


    /**
     * Setter for property typPayementVector.
     *
     * @param typPayementVector New value of property typPayementVector.
     */


    public void setTypPayementVector(java.util.Hashtable typPayementVector) {


        this.typPayementVector = typPayementVector;


    }


    /**
     * Getter for property generale.
     *
     * @return Value of property generale.
     */


    public java.util.Hashtable getGenerale() {


        return generale;


    }


    /**
     * Setter for property generale.
     *
     * @param generale New value of property generale.
     */


    public void setGenerale(java.util.Hashtable generale) {


        this.generale = generale;


    }


    /**
     * Getter for property desclog.
     *
     * @return Value of property desclog.
     */


    public java.util.Hashtable getDesclog() {


        return desclog;


    }


    /**
     * Setter for property desclog.
     *
     * @param desclog New value of property desclog.
     */


    public void setDesclog(java.util.Hashtable desclog) {


        this.desclog = desclog;


    }


    /**
     * Getter for property divers.
     *
     * @return Value of property divers.
     */


    public java.util.Hashtable getDivers() {


        return divers;


    }


    /**
     * Setter for property divers.
     *
     * @param divers New value of property divers.
     */


    public void setDivers(java.util.Hashtable divers) {


        this.divers = divers;


    }


    /**
     * Getter for property optionDialog.
     *
     * @return Value of property optionDialog.
     */


    public srcastra.astra.gui.MainFrame.OptionsDialog getOptionDialog() {


        return optionDialog;


    }


    /**
     * Setter for property optionDialog.
     *
     * @param optionDialog New value of property optionDialog.
     */


    public void setOptionDialog(srcastra.astra.gui.MainFrame.OptionsDialog optionDialog) {


        this.optionDialog = optionDialog;


    }


    /**
     * Getter for property mediator2.
     *
     * @return Value of property mediator2.
     */


    public srcastra.astra.gui.components.combobox.liste2.Mediator getMediator2() {
        return mediator2;
    }


    /**
     * Setter for property mediator2.
     *
     * @param mediator2 New value of property mediator2.
     */


    public void setMediator2(srcastra.astra.gui.components.combobox.liste2.Mediator mediator2) {
        this.mediator2 = mediator2;
    }


    /**
     * Getter for property exercice.
     *
     * @return Value of property exercice.
     */


    public srcastra.astra.sys.classetransfert.compta.ExerciceCompt_T getExercice() {
        return exercice;
    }


    /**
     * Setter for property exercice.
     *
     * @param exercice New value of property exercice.
     */


    public void setExercice(srcastra.astra.sys.classetransfert.compta.ExerciceCompt_T exercice) {

        this.exercice = exercice;

    }

    /**
     * Getter for property serveur.
     *
     * @return Value of property serveur.
     */


    public srcastra.astra.sys.rmi.astrainterface getServeur() {
        return serveur;
    }

    /**
     * Setter for property serveur.
     *
     * @param serveur New value of property serveur.
     */

    public void setServeur(srcastra.astra.sys.rmi.astrainterface serveur) {
        this.serveur = serveur;

    }

    /**
     * Getter for property comptainferface.
     *
     * @return Value of property comptainferface.
     */

    public srcastra.astra.sys.rmi.ParamComptableInterface getComptainferface() {

        return comptainferface;
    }

    /**
     * Setter for property comptainferface.
     *
     * @param comptainferface New value of property comptainferface.
     */


    public void setComptainferface(srcastra.astra.sys.rmi.ParamComptableInterface comptainferface) {
        this.comptainferface = comptainferface;

    }


    public void showAgenda() {
        grp_mcp_mainpan.displayModulesAgenda(grp_MenuItem_agenda);

    }

    public void showGDS() {
        grp_mcp_mainpan.displayModulesIntegrationIATA(grp_menu_integra);
    }


    public void showHistorique() {
        new srcastra.astra.gui.modules.compta.CheckHistorique(this.serveur, this, this.currentUser);


    }


    public void resetKeyboardActions() {
        grp_Pan_MainCenter.resetKeyboardActions();

    }


    public void registerKeyboardAction(java.awt.event.ActionListener anAction, KeyStroke akeystroke, int condition) {
        grp_Pan_MainCenter.registerKeyboardAction(anAction, akeystroke, condition);


    }


    public void openFichier() {
        openMenu(grp_Menu_Fichier);
    }


    public void openGestion() {
        openMenu(grp_Menu_Gestion);
    }


    public void openFinancier() {
        openMenu(grp_Menu_Financier);
    }


    public void openAchat() {
        openMenu(grp_Menu_Achat);

    }


    private void openMenu(JMenu menu) {
        System.out.println("open menu");
        menu.setPopupMenuVisible(true);
        menu.getItem(0).setSelected(true);
        //
    }


    private void setMenuAction() {
        grp_Menu_Fichier.setMnemonic(java.awt.event.KeyEvent.VK_F);
        grp_Menu_Gestion.setMnemonic(java.awt.event.KeyEvent.VK_G);
        grp_Menu_Financier.setMnemonic(java.awt.event.KeyEvent.VK_I);
        grp_Menu_Achat.setMnemonic(java.awt.event.KeyEvent.VK_A);
        grp_Menu_Impression.setMnemonic(java.awt.event.KeyEvent.VK_M);
        grp_Menu_parametre.setMnemonic(java.awt.event.KeyEvent.VK_P);
        grp_Menu_Fenetres.setMnemonic(java.awt.event.KeyEvent.VK_E);
        grp_Menu_Aide.setMnemonic(java.awt.event.KeyEvent.VK_D);
    }


    /**
     * Getter for property grp_MenuItem_Dossier.
     *
     * @return Value of property grp_MenuItem_Dossier.
     */
    public javax.swing.JMenuItem getGrp_MenuItem_Dossier() {


        return grp_MenuItem_Dossier;


    }


    /**
     * Setter for property grp_MenuItem_Dossier.
     *
     * @param grp_MenuItem_Dossier New value of property grp_MenuItem_Dossier.
     */


    public void setGrp_MenuItem_Dossier(javax.swing.JMenuItem grp_MenuItem_Dossier) {


        this.grp_MenuItem_Dossier = grp_MenuItem_Dossier;


    }


    /**
     * Getter for property grp_Tbar_modules.
     *
     * @return Value of property grp_Tbar_modules.
     */

    public javax.swing.JToolBar getGrp_Tbar_modules() {

        return grp_Tbar_modules;

    }


    /**
     * Setter for property grp_Tbar_modules.
     *
     * @param grp_Tbar_modules New value of property grp_Tbar_modules.
     */

    public void setGrp_Tbar_modules(javax.swing.JToolBar grp_Tbar_modules) {

        this.grp_Tbar_modules = grp_Tbar_modules;

    }


    /**
     * Getter for property selectedDossier.
     *
     * @return Value of property selectedDossier.
     */

    public int getSelectedDossier() {

        return selectedDossier;

    }


    /**
     * Setter for property selectedDossier.
     *
     * @param selectedDossier New value of property selectedDossier.
     */

    public void setSelectedDossier(int selectedDossier) {

        this.selectedDossier = selectedDossier;

    }


    /**
     * Getter for property rattrap.
     *
     * @return Value of property rattrap.
     */

    public boolean isRattrap() {

        return rattrap;

    }

    public void resetModeGroupe() {

        jCheckBoxMenuItem2.setSelected(false);
        modeGroupe = false;

    }

    public boolean isModeGroupe() {
        return modeGroupe;

    }


    /**
     * Setter for property rattrap.
     *
     * @param rattrap New value of property rattrap.
     */

    public void setRattrap(boolean rattrap) {

        jCheckBoxMenuItem1.setSelected(rattrap);
        this.rattrap = rattrap;

    }

    /** Setter for property configRmi.



     * @param configRmi New value of property configRmi.



     */


    /**
     * Getter for property dossierModule.
     *
     * @return Value of property dossierModule.
     */

    //private srcastra.astra.gui.modules.fournisseurs.Fournisseurs grp_Modules_Fournisseur;

    private MainCenterPanel grp_mcp_mainpan;

//    private AListDialog printDialog;

    private OptionsDialog optionDialog;
    private srcastra.astra.sys.reseaux.SocketClient sockclie;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup grp_ButGroup_gestionFenetre;
    private javax.swing.JMenuItem grp_JMenuItem_session;
    private javax.swing.JMenuItem grp_JMenuitem_connection;
    private javax.swing.JMenuItem grp_JMenuitem_plc;
    private javax.swing.JMenuBar grp_Mbar_Main;
    private javax.swing.JMenuItem grp_MenuItem_Achat;
    private javax.swing.JMenuItem grp_MenuItem_Clients;
    private javax.swing.JMenuItem grp_MenuItem_CodePostaux;
    private javax.swing.JMenuItem grp_MenuItem_Compagnie;
    private javax.swing.JMenuItem grp_MenuItem_Destination;
    private javax.swing.JMenuItem grp_MenuItem_Devise;
    private javax.swing.JMenuItem grp_MenuItem_Dossier;
    private javax.swing.JMenuItem grp_MenuItem_Edition;
    private javax.swing.JMenuItem grp_MenuItem_Embarq;
    private javax.swing.JMenuItem grp_MenuItem_Fournisseur;
    private javax.swing.JMenuItem grp_MenuItem_Groupe_Dec;
    private javax.swing.JMenuItem grp_MenuItem_Langue;
    private javax.swing.JMenuItem grp_MenuItem_Listes;
    private javax.swing.JMenuItem grp_MenuItem_Logement;
    private javax.swing.JMenuItem grp_MenuItem_Pays;
    private javax.swing.JMenuItem grp_MenuItem_Reorganisation;
    private javax.swing.JMenuItem grp_MenuItem_TitrePersonnes;
    private javax.swing.JMenuItem grp_MenuItem_Transport;
    private javax.swing.JMenuItem grp_MenuItem_TypeTva;
    private javax.swing.JMenuItem grp_MenuItem_agenda;
    private javax.swing.JMenuItem grp_MenuItem_bsp;
    private javax.swing.JMenuItem grp_MenuItem_caisse;
    private javax.swing.JMenuItem grp_MenuItem_regimTva;
    private javax.swing.JMenuItem grp_MenuItem_typtva;
    private javax.swing.JMenu grp_Menu_Achat;
    private javax.swing.JMenu grp_Menu_Aide;
    private javax.swing.JMenu grp_Menu_AutreApplication;
    private javax.swing.JMenuItem grp_Menu_CaisseLibelle;
    private javax.swing.JMenuItem grp_Menu_Document;
    private javax.swing.JMenu grp_Menu_Fenetres;
    private javax.swing.JMenu grp_Menu_Fichier;
    private javax.swing.JMenu grp_Menu_Financier;
    private javax.swing.JMenu grp_Menu_Gestion;
    private javax.swing.JMenu grp_Menu_Impression;
    private javax.swing.JMenuItem grp_Menu_Memo;
    private javax.swing.JMenuItem grp_Menu_TypePayement;
    private javax.swing.JMenu grp_Menu_aide_dec;
    private javax.swing.JMenuItem grp_Menu_integration;
    private javax.swing.JMenu grp_Menu_parametre;
    private javax.swing.JMenu grp_Menu_produit;
    private javax.swing.JMenuItem grp_Menu_user;
    private javax.swing.JMenuItem grp_MnItem_Options;
    private javax.swing.JPanel grp_Pan_MainCenter;
    private javax.swing.JPanel grp_Pan_MainNorth;
    private javax.swing.JMenu grp_SMenu_Signalitique;
    private javax.swing.JSeparator grp_Sep_SignaletiqueSeparator;
    private javax.swing.JToolBar grp_Tbar_modules;
    private javax.swing.JMenuItem grp_menu_Desclog;
    private javax.swing.JMenuItem grp_menu_aide_decision;
    private javax.swing.JMenuItem grp_menu_btn;
    private javax.swing.JMenuItem grp_menu_divers;
    private javax.swing.JMenuItem grp_menu_integra;
    private javax.swing.JMenu grp_menu_user;
    private javax.swing.JMenuItem grp_menui_bank;
    private javax.swing.JMenuItem grp_menui_od;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

    private Loginusers_T currentUser;
    private astrainterface serveur;
    private Object grpdec;
    private srcastra.astra.gui.modules.config.ConfigMediator mediator;
    private srcastra.astra.sys.rmi.ConfigRmiInterface configRmi;
    private srcastra.astra.sys.rmi.FournisseurRmiInterface fournisseurRmi;


    public class OptionsDialog extends javax.swing.JDialog implements srcastra.astra.gui.conf.ConfigurationParent, java.awt.event.WindowListener {
        private srcastra.astra.gui.conf.ConfigurationOptions m_confPane;

        public OptionsDialog(java.awt.Frame owner, String title, boolean modal, srcastra.astra.gui.conf.ConfigurationOptions confPane) {
            super(owner, title, modal);
            m_confPane = confPane;
            getContentPane().setLayout(new java.awt.GridLayout());
            getContentPane().add(m_confPane.getComponent());
            java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            int width=587;
            int heigth=384;
            setSize(new java.awt.Dimension(width, heigth));
            setLocation((screenSize.width - width) / 2, (screenSize.height - heigth) / 2);
            addWindowListener(this);
            mediator.setConfigOpened(true);
        }

        public void doClose() {
            setVisible(false);
            sockclie.setMore(false);
            dispose();
            mediator.setConfigOpened(false);

        }

        public srcastra.astra.sys.classetransfert.Loginusers_T getCurrentUser() {
            return currentUser;

        }

        public srcastra.astra.sys.rmi.astrainterface getServeur() {

            return serveur;

        }


        public void windowActivated(java.awt.event.WindowEvent windowEvent) {


        }


        public void windowClosed(java.awt.event.WindowEvent windowEvent) {

            m_confPane.getMediator().setConfigOpened(false);

        }


        public void windowClosing(java.awt.event.WindowEvent windowEvent) {

            m_confPane.applyChanges();
            setVisible(false);
            dispose();

        }

        public void windowDeactivated(java.awt.event.WindowEvent windowEvent) {

        }


        public void windowDeiconified(java.awt.event.WindowEvent windowEvent) {

        }

        public void windowIconified(java.awt.event.WindowEvent windowEvent) {


        }

        public void windowOpened(java.awt.event.WindowEvent windowEvent) {
            m_confPane.getMediator().setConfigOpened(true);
        }

        public void show() {
            m_confPane.getMediator().setConfigOpened(true);

            ((srcastra.astra.gui.conf.ConfigurationPane) m_confPane).resetMediator();

            super.show();

        }

    }


    Hashtable supReducVector;
    Hashtable memoVector;
    Hashtable typeTvaVector;
    Hashtable regimeTvaVector;
    Hashtable bonCommandeVector;
    Hashtable typPayementVector;
    Hashtable generale;
    Hashtable desclog;
    Hashtable divers;
    srcastra.astra.sys.classetransfert.compta.ExerciceCompt_T exercice;
    srcastra.astra.sys.rmi.ParamComptableInterface comptainferface;
    srcastra.astra.gui.components.tva.TvaFrame vente;

}
