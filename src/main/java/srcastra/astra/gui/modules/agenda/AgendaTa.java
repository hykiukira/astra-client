/*
 * AgendaTa.java
 *
 * Created on 10 mars 2004, 15:00
 */

package srcastra.astra.gui.modules.agenda;

import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.sys.rmi.Exception.*;

import java.rmi.*;

import srcastra.astra.gui.sys.*;

/**
 * @author Administrateur
 */

public class AgendaTa extends javax.swing.JPanel {

    /**
     * Creates new form AgendaTa
     */
    int mode;
    astrainterface serveur;
    Loginusers_T user;
    Task_T m_task;
    srcastra.astra.sys.classetransfert.utils.Date m_date;

    public AgendaTa(astrainterface serveur, Loginusers_T user, srcastra.astra.sys.classetransfert.utils.Date date) {
        initComponents();
        m_date = date;
        this.serveur = serveur;
        this.user = user;
        setBounds(0, 0, 480, 430);
        initListe();
    }

    public void initListe() {
        grp_li_clicon.setServeur(serveur);
        grp_li_clicon.setName("lc");
        grp_li_clicon.setLogin(user);
        grp_li_clicon.setModel(new srcastra.astra.gui.components.combobox.liste2.listemodel.SousClientListeTableModel(serveur, user));
        grp_li_clicon.init2();
        // grp_li_clicon.setLeft(true);

        grp_li_clifact.setServeur(serveur);
        grp_li_clifact.setName("lc");
        grp_li_clifact.setLogin(user);
        grp_li_clifact.setModel(new srcastra.astra.gui.components.combobox.liste2.listemodel.SousClientListeTableModel(serveur, user));
        grp_li_clifact.init2();
        grp_li_clifact.setLeft(true);


        grp_li_state.setServeur(serveur);
        //grp_li_state.setName("lc");
        grp_li_state.setLogin(user);
        grp_li_state.setModel(new srcastra.astra.gui.components.combobox.liste.TaskEtatListeTableModel(serveur, user));
        grp_li_state.init2();

        //grp_li_clifact.setLeft(true);
    }

    public void insert() {
        Task_T task = new Task_T();
        task.setTask_numdos(grp_TField_dossier.getText());
        task.setCscleunifact(grp_li_clifact.getCleUnik());
        task.setCscleunikcont(grp_li_clifact.getCleUnik());
        task.setTask_object(grp_TField_objet.getText());
        task.setTask_echeance(grp_Adate_eche.getDate());
        task.getTask_echeance().setMinutes(0);
        task.getTask_echeance().setHours(0);
        task.getTask_echeance().setSeconds(0);
        task.setTask_debut(grp_ADate_debut.getDate());
        task.getTask_debut().setMinutes(0);
        task.getTask_debut().setHours(0);
        task.getTask_debut().setSeconds(0);
        task.setTask_etat(grp_li_state.getCleUnik());
        task.setTask_auto(0);
        task.setTask_envois(0);
        task.setTask_memo(grp_TArea_memo.getText());
        task.setTask_message(grp_TArea_message.getText());
        try {
            serveur.renvDossierRmiObject(user.getUrcleunik()).insertTask(task, user.getUrcleunik());
        }
        catch (ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, 0, se, user);
        }
        catch (RemoteException rn) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, 0, rn, user);
        }
    }

    public void charge(Task_T task) {
        m_task = task;
        grp_TField_dossier.setText(task.getTask_numdos());

        grp_li_clifact.setModedisplay(true);
        grp_li_clicon.setModedisplay(true);

        grp_li_clifact.setCleUnik(new Long(task.getCscleunifact()).intValue());
        grp_li_clicon.setCleUnik(new Long(task.getCscleunikcont()).intValue());
        if (task.getCscontname() != null)
            grp_li_clicon.setText(task.getCscontname());
        if (task.getCsfactname() != null)
            grp_li_clifact.setText(task.getCsfactname());
        grp_li_clifact.setModedisplay(false);
        grp_li_clicon.setModedisplay(false);

        grp_TField_objet.setText(task.getTask_object());
        grp_ADate_debut.setDate(task.getTask_debut());
        grp_Adate_eche.setDate(task.getTask_echeance());
        grp_li_state.setCleUnik(task.getTask_etat());
        grp_TArea_memo.setText(task.getTask_memo());
        grp_TArea_message.setText(task.getTask_message());
        grp_TField_dossier.requestFocus();
    }

    public void modify() {
        m_task.setTask_numdos(grp_TField_dossier.getText());
        m_task.setCscleunifact(grp_li_clifact.getCleUnik());
        m_task.setCscleunikcont(grp_li_clicon.getCleUnik());
        m_task.setTask_object(grp_TField_objet.getText());
        m_task.setTask_echeance(grp_Adate_eche.getDate());
        m_task.getTask_echeance().setMinutes(0);
        m_task.getTask_echeance().setHours(0);
        m_task.getTask_echeance().setSeconds(0);
        m_task.setTask_debut(grp_ADate_debut.getDate());
        m_task.getTask_debut().setMinutes(0);
        m_task.getTask_debut().setHours(0);
        m_task.getTask_debut().setSeconds(0);
        m_task.setTask_etat(grp_li_state.getCleUnik());
        m_task.setTask_auto(0);
        m_task.setTask_envois(0);
        m_task.setTask_memo(grp_TArea_memo.getText());
        m_task.setTask_message(grp_TArea_message.getText());
        try {
            serveur.renvDossierRmiObject(user.getUrcleunik()).modifyTask(m_task, user.getUrcleunik());
        }
        catch (ServeurSqlFailure se) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.SERVEUR_SQL_FAILURE, 0, se, user);
        }
        catch (RemoteException rn) {
            ErreurScreenLibrary.displayErreur(this, ErreurScreenLibrary.REMOTE_EXCEPTION, 0, rn, user);
        }
    }

    public void reset() {
        grp_TField_dossier.setText("");

        grp_li_clifact.setModedisplay(true);
        grp_li_clicon.setModedisplay(true);

        grp_li_clifact.setCleUnik(0);
        grp_li_clicon.setCleUnik(0);
        grp_li_clicon.setText("");
        grp_li_clifact.setText("");
        grp_li_clifact.setModedisplay(false);
        grp_li_clicon.setModedisplay(false);

        grp_TField_objet.setText("");
        grp_ADate_debut.setDate(m_date);
        grp_Adate_eche.setDate(m_date);
        grp_li_state.setCleUnik(1);
        grp_TArea_memo.setText("");
        grp_TArea_message.setText("");
        grp_TField_dossier.requestFocus();

    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        grp_Adate_eche = new srcastra.astra.gui.components.date.thedate.ADate();
        grp_ADate_debut = new srcastra.astra.gui.components.date.thedate.ADate();
        jLabel7 = new javax.swing.JLabel();
        grp_li_state = new srcastra.astra.gui.components.combobox.liste.Liste();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        aCheckBox1 = new srcastra.astra.gui.components.checkbox.ACheckBox();
        aCheckBox2 = new srcastra.astra.gui.components.checkbox.ACheckBox();
        aCheckBox3 = new srcastra.astra.gui.components.checkbox.ACheckBox();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grp_TArea_message = new javax.swing.JTextPane();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grp_TArea_memo = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        grp_TField_dossier = new srcastra.astra.gui.components.textFields.ATextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        grp_TField_objet = new srcastra.astra.gui.components.textFields.ATextField();
        grp_li_clicon = new srcastra.astra.gui.components.combobox.liste2.Liste2();
        grp_li_clifact = new srcastra.astra.gui.components.combobox.liste2.Liste2();
        jPanel3 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("task")));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(10, 40));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel5.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("echeance"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 7);
        jPanel5.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel6.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("debut"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 7);
        jPanel5.add(jLabel6, gridBagConstraints);

        grp_Adate_eche.setGrp_Comp_nextComponent(grp_ADate_debut);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel5.add(grp_Adate_eche, gridBagConstraints);

        grp_ADate_debut.setGrp_Comp_nextComponent(grp_li_state);
        grp_ADate_debut.setNextFocusableComponent(grp_li_state);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel5.add(grp_ADate_debut, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel7.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("etat"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 7);
        jPanel5.add(jLabel7, gridBagConstraints);

        grp_li_state.setGrp_Comp_nextComponent(grp_TArea_memo);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel5.add(grp_li_state, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel8.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("auto"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 0, 0);
        jPanel5.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel9.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("mail"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 17, 0, 0);
        jPanel5.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel10.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("fax"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        jPanel5.add(jLabel10, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel11.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("papier"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        jPanel5.add(jLabel11, gridBagConstraints);

        aCheckBox1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel5.add(aCheckBox1, gridBagConstraints);

        aCheckBox2.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel5.add(aCheckBox2, gridBagConstraints);

        aCheckBox3.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel5.add(aCheckBox3, gridBagConstraints);

        jPanel7.add(jPanel5);

        jPanel1.add(jPanel7, java.awt.BorderLayout.NORTH);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel9.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("message")));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setViewportView(grp_TArea_message);

        jPanel9.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel10.setBorder(new javax.swing.border.TitledBorder(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("memo")));
        jPanel10.setPreferredSize(new java.awt.Dimension(10, 100));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportView(grp_TArea_memo);

        jPanel10.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel10, java.awt.BorderLayout.NORTH);

        jPanel1.add(jPanel8, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel2.setPreferredSize(new java.awt.Dimension(10, 60));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("dossier"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(jLabel1, gridBagConstraints);

        grp_TField_dossier.setGrp_Comp_nextComponent(grp_li_clicon);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(grp_TField_dossier, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("cscont"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("csfact"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda").getString("obj"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(jLabel4, gridBagConstraints);

        grp_TField_objet.setGrp_Comp_nextComponent(grp_Adate_eche);
        grp_TField_objet.setPreferredSize(new java.awt.Dimension(300, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(grp_TField_objet, gridBagConstraints);

        grp_li_clicon.setCanbenull(true);
        grp_li_clicon.setGrp_Comp_nextComponent(grp_li_clifact);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel4.add(grp_li_clicon, gridBagConstraints);

        grp_li_clifact.setCanbenull(true);
        grp_li_clifact.setGrp_Comp_nextComponent(grp_TField_objet);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel4.add(grp_li_clifact, gridBagConstraints);

        jPanel2.add(jPanel4);

        add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setPreferredSize(new java.awt.Dimension(10, 40));
        add(jPanel3, java.awt.BorderLayout.SOUTH);

    }//GEN-END:initComponents

    /**
     * Getter for property mode.
     *
     * @return Value of property mode.
     */
    public void action() {
        if (mode == 1) {
            insert();
        } else if (mode == 2) {
            modify();
        }
    }

    public int getMode() {
        return mode;
    }

    /**
     * Setter for property mode.
     *
     * @param mode New value of property mode.
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private srcastra.astra.gui.components.checkbox.ACheckBox aCheckBox1;
    private srcastra.astra.gui.components.checkbox.ACheckBox aCheckBox2;
    private srcastra.astra.gui.components.checkbox.ACheckBox aCheckBox3;
    private srcastra.astra.gui.components.date.thedate.ADate grp_ADate_debut;
    private srcastra.astra.gui.components.date.thedate.ADate grp_Adate_eche;
    private javax.swing.JTextPane grp_TArea_memo;
    private javax.swing.JTextPane grp_TArea_message;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_dossier;
    private srcastra.astra.gui.components.textFields.ATextField grp_TField_objet;
    private srcastra.astra.gui.components.combobox.liste2.Liste2 grp_li_clicon;
    private srcastra.astra.gui.components.combobox.liste2.Liste2 grp_li_clifact;
    private srcastra.astra.gui.components.combobox.liste.Liste grp_li_state;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}
