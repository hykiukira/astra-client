/*
 * PrintingConfig.java
 *
 * Created on 4 mars 2003, 11:14
 */

package srcastra.astra.gui.modules.printing;
import srcastra.astra.gui.MainFrame;
import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.gui.sys.ErreurScreenLibrary;
/**
 *
 * @author  Thomas
 */
public class PrintingConfig extends javax.swing.JPanel {
    
    /** Creates new form PrintingConfig */
    MainFrame m_frame;
    public PrintingConfig(MainFrame frame) {
        m_frame=frame;
        initComponents();
        init();
        jTabbedPane1.setTitleAt(0,srcastra.astra.gui.GetTextFromBundle.getText("srcastra/astra/locale/generale","print_module_bdc",m_frame.getCurrentUser().getLangage()));
        jTabbedPane1.setTitleAt(1,srcastra.astra.gui.GetTextFromBundle.getText("srcastra/astra/locale/generale","print_module_fact",m_frame.getCurrentUser().getLangage()));
        jTabbedPane1.setTitleAt(2,srcastra.astra.gui.GetTextFromBundle.getText("srcastra/astra/locale/generale","print_module_voucher",m_frame.getCurrentUser().getLangage()));
        jButton1.setText(srcastra.astra.gui.GetTextFromBundle.getText("srcastra/astra/locale/generale","print_module_valid",m_frame.getCurrentUser().getLangage()));
    }
    private void init(){
        AbstractConfig_T config=srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig();
        if(config.getBdc_bloc_g()==1)
            grp_CheckBox_enteteg.setSelected(true);
        else
            grp_CheckBox_enteteg.setSelected(false);
         if(config.getBdc_bloc_d()==1)
            grp_CheckBox_enteted.setSelected(true);
         else
            grp_CheckBox_enteted.setSelected(false);
        grp_Label_xBDC.setText(""+config.getBdc_bdc_x());
        grp_Label_yBDC.setText(""+config.getBdc_bdc_y());
        grp_Label_xCLI.setText(""+config.getBdc_bdc_cli_x());
        grp_Label_yCLI.setText(""+config.getBdc_bdc_cli_y());
    }
    public void setMediator(srcastra.astra.gui.modules.config.ConfigMediator mediator){
      mediator.registerPrintingConfig(this);   
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        grp_JBut_downBDC = new javax.swing.JButton();
        grp_JBut_upBDC = new javax.swing.JButton();
        grp_JBut_leftBDC = new javax.swing.JButton();
        grp_JBut_rightBDC = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        grp_Label_xBDC = new javax.swing.JLabel();
        grp_Label_yBDC = new javax.swing.JLabel();
        jPanel51 = new javax.swing.JPanel();
        grp_JBut_downCLI = new javax.swing.JButton();
        grp_JBut_upCLI = new javax.swing.JButton();
        grp_JBut_leftCLI = new javax.swing.JButton();
        grp_JBut_rigthCLI = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        grp_Label_xCLI = new javax.swing.JLabel();
        grp_Label_yCLI = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        grp_CheckBox_enteteg = new javax.swing.JCheckBox();
        grp_CheckBox_enteted = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("prt_conf_title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        setPreferredSize(new java.awt.Dimension(500, 296));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(500, 273));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("prt_conf_bdc"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        grp_JBut_downBDC.setText("jButton1");
        grp_JBut_downBDC.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(grp_JBut_downBDC, gridBagConstraints);

        grp_JBut_upBDC.setText("jButton2");
        grp_JBut_upBDC.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(grp_JBut_upBDC, gridBagConstraints);

        grp_JBut_leftBDC.setText("jButton3");
        grp_JBut_leftBDC.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(grp_JBut_leftBDC, gridBagConstraints);

        grp_JBut_rightBDC.setText("jButton4");
        grp_JBut_rightBDC.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(grp_JBut_rightBDC, gridBagConstraints);

        jLabel3.setText("X");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Y");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(jLabel4, gridBagConstraints);

        grp_Label_xBDC.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(grp_Label_xBDC, gridBagConstraints);

        grp_Label_yBDC.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(grp_Label_yBDC, gridBagConstraints);

        jPanel1.add(jPanel5);

        jPanel51.setLayout(new java.awt.GridBagLayout());

        jPanel51.setBorder(new javax.swing.border.TitledBorder(null, java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("prt_conf_nomclient"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)));
        grp_JBut_downCLI.setText("jButton1");
        grp_JBut_downCLI.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel51.add(grp_JBut_downCLI, gridBagConstraints);

        grp_JBut_upCLI.setText("jButton2");
        grp_JBut_upCLI.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel51.add(grp_JBut_upCLI, gridBagConstraints);

        grp_JBut_leftCLI.setText("jButton3");
        grp_JBut_leftCLI.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel51.add(grp_JBut_leftCLI, gridBagConstraints);

        grp_JBut_rigthCLI.setText("jButton4");
        grp_JBut_rigthCLI.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel51.add(grp_JBut_rigthCLI, gridBagConstraints);

        jLabel31.setText("X");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel51.add(jLabel31, gridBagConstraints);

        jLabel41.setText("Y");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel51.add(jLabel41, gridBagConstraints);

        grp_Label_xCLI.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel51.add(grp_Label_xCLI, gridBagConstraints);

        grp_Label_yCLI.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel51.add(grp_Label_yCLI, gridBagConstraints);

        jPanel1.add(jPanel51);

        jPanel6.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(new javax.swing.border.EtchedBorder());
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel1.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("prt_conf_blg"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 2, 13);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10));
        jLabel2.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("prt_conf_bld"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 3, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        grp_CheckBox_enteteg.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_CheckBox_enteteg.setSelected(true);
        grp_CheckBox_enteteg.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("prt_conf_vis"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel2.add(grp_CheckBox_enteteg, gridBagConstraints);

        grp_CheckBox_enteted.setFont(new java.awt.Font("Tahoma", 0, 10));
        grp_CheckBox_enteted.setSelected(true);
        grp_CheckBox_enteted.setText(java.util.ResourceBundle.getBundle("srcastra/astra/locale/printing").getString("prt_conf_vis"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel2.add(grp_CheckBox_enteted, gridBagConstraints);

        jPanel4.add(jPanel2);

        jPanel6.add(jPanel4, java.awt.BorderLayout.NORTH);

        jPanel3.setPreferredSize(new java.awt.Dimension(10, 70));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 10));
        jButton1.setText("jButton1");
        jButton1.setPreferredSize(new java.awt.Dimension(81, 20));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel3.add(jButton1);

        jPanel6.add(jPanel3, java.awt.BorderLayout.SOUTH);

        jTabbedPane1.addTab("tab4", jPanel6);

        jTabbedPane1.addTab("tab2", jPanel7);

        jTabbedPane1.addTab("tab3", jPanel8);

        add(jTabbedPane1, java.awt.BorderLayout.NORTH);

    }//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     
    }//GEN-LAST:event_jButton1ActionPerformed

    /** Getter for property grp_CheckBox_enteted.
     * @return Value of property grp_CheckBox_enteted.
     */
    public javax.swing.JCheckBox getGrp_CheckBox_enteted() {
        return grp_CheckBox_enteted;
    }    
    private AbstractConfig_T chargeData(){
     AbstractConfig_T config=new Config2_T();
     config.setBdc_bdc_cli_x(Double.parseDouble(grp_Label_xCLI.getText()));
     config.setBdc_bdc_cli_y(Double.parseDouble(grp_Label_yCLI.getText()));
     config.setBdc_bdc_x(Double.parseDouble(grp_Label_xBDC.getText()));
     config.setBdc_bdc_y(Double.parseDouble(grp_Label_yBDC.getText()));
     if(grp_CheckBox_enteteg.isSelected())
         config.setBdc_bloc_g(1);
     if(grp_CheckBox_enteted.isSelected())
         config.setBdc_bloc_d(1);
     return config;
    }
    
    /** Setter for property grp_CheckBox_enteted.
     * @param grp_CheckBox_enteted New value of property grp_CheckBox_enteted.
     */
    public void setGrp_CheckBox_enteted(javax.swing.JCheckBox grp_CheckBox_enteted) {
        this.grp_CheckBox_enteted = grp_CheckBox_enteted;
    }    
    
    /** Getter for property grp_CheckBox_enteteg.
     * @return Value of property grp_CheckBox_enteteg.
     */
    public javax.swing.JCheckBox getGrp_CheckBox_enteteg() {
        return grp_CheckBox_enteteg;
    }
    
    /** Setter for property grp_CheckBox_enteteg.
     * @param grp_CheckBox_enteteg New value of property grp_CheckBox_enteteg.
     */
    public void setGrp_CheckBox_enteteg(javax.swing.JCheckBox grp_CheckBox_enteteg) {
        this.grp_CheckBox_enteteg = grp_CheckBox_enteteg;
    }
    
    /** Getter for property grp_JBut_downBDC.
     * @return Value of property grp_JBut_downBDC.
     */
    public javax.swing.JButton getGrp_JBut_downBDC() {
        return grp_JBut_downBDC;
    }
    
    /** Setter for property grp_JBut_downBDC.
     * @param grp_JBut_downBDC New value of property grp_JBut_downBDC.
     */
    public void setGrp_JBut_downBDC(javax.swing.JButton grp_JBut_downBDC) {
        this.grp_JBut_downBDC = grp_JBut_downBDC;
    }
    
    /** Getter for property grp_JBut_downCLI.
     * @return Value of property grp_JBut_downCLI.
     */
    public javax.swing.JButton getGrp_JBut_downCLI() {
        return grp_JBut_downCLI;
    }
    
    /** Setter for property grp_JBut_downCLI.
     * @param grp_JBut_downCLI New value of property grp_JBut_downCLI.
     */
    public void setGrp_JBut_downCLI(javax.swing.JButton grp_JBut_downCLI) {
        this.grp_JBut_downCLI = grp_JBut_downCLI;
    }
    
    /** Getter for property grp_JBut_leftBDC.
     * @return Value of property grp_JBut_leftBDC.
     */
    public javax.swing.JButton getGrp_JBut_leftBDC() {
        return grp_JBut_leftBDC;
    }
    
    /** Setter for property grp_JBut_leftBDC.
     * @param grp_JBut_leftBDC New value of property grp_JBut_leftBDC.
     */
    public void setGrp_JBut_leftBDC(javax.swing.JButton grp_JBut_leftBDC) {
        this.grp_JBut_leftBDC = grp_JBut_leftBDC;
    }
    
    /** Getter for property grp_JBut_leftCLI.
     * @return Value of property grp_JBut_leftCLI.
     */
    public javax.swing.JButton getGrp_JBut_leftCLI() {
        return grp_JBut_leftCLI;
    }
    
    /** Setter for property grp_JBut_leftCLI.
     * @param grp_JBut_leftCLI New value of property grp_JBut_leftCLI.
     */
    public void setGrp_JBut_leftCLI(javax.swing.JButton grp_JBut_leftCLI) {
        this.grp_JBut_leftCLI = grp_JBut_leftCLI;
    }
    
    /** Getter for property grp_JBut_rightBDC.
     * @return Value of property grp_JBut_rightBDC.
     */
    public javax.swing.JButton getGrp_JBut_rightBDC() {
        return grp_JBut_rightBDC;
    }
    
    /** Setter for property grp_JBut_rightBDC.
     * @param grp_JBut_rightBDC New value of property grp_JBut_rightBDC.
     */
    public void setGrp_JBut_rightBDC(javax.swing.JButton grp_JBut_rightBDC) {
        this.grp_JBut_rightBDC = grp_JBut_rightBDC;
    }
    
    /** Getter for property grp_JBut_rigthCLI.
     * @return Value of property grp_JBut_rigthCLI.
     */
    public javax.swing.JButton getGrp_JBut_rigthCLI() {
        return grp_JBut_rigthCLI;
    }
    
    /** Setter for property grp_JBut_rigthCLI.
     * @param grp_JBut_rigthCLI New value of property grp_JBut_rigthCLI.
     */
    public void setGrp_JBut_rigthCLI(javax.swing.JButton grp_JBut_rigthCLI) {
        this.grp_JBut_rigthCLI = grp_JBut_rigthCLI;
    }
    
    /** Getter for property grp_JBut_upBDC.
     * @return Value of property grp_JBut_upBDC.
     */
    public javax.swing.JButton getGrp_JBut_upBDC() {
        return grp_JBut_upBDC;
    }
    
    /** Setter for property grp_JBut_upBDC.
     * @param grp_JBut_upBDC New value of property grp_JBut_upBDC.
     */
    public void setGrp_JBut_upBDC(javax.swing.JButton grp_JBut_upBDC) {
        this.grp_JBut_upBDC = grp_JBut_upBDC;
    }
    
    /** Getter for property grp_JBut_upCLI.
     * @return Value of property grp_JBut_upCLI.
     */
    public javax.swing.JButton getGrp_JBut_upCLI() {
        return grp_JBut_upCLI;
    }
    
    /** Setter for property grp_JBut_upCLI.
     * @param grp_JBut_upCLI New value of property grp_JBut_upCLI.
     */
    public void setGrp_JBut_upCLI(javax.swing.JButton grp_JBut_upCLI) {
        this.grp_JBut_upCLI = grp_JBut_upCLI;
    }
    
    /** Getter for property grp_Label_xBDC.
     * @return Value of property grp_Label_xBDC.
     */
    public javax.swing.JLabel getGrp_Label_xBDC() {
        return grp_Label_xBDC;
    }
    
    /** Setter for property grp_Label_xBDC.
     * @param grp_Label_xBDC New value of property grp_Label_xBDC.
     */
    public void setGrp_Label_xBDC(javax.swing.JLabel grp_Label_xBDC) {
        this.grp_Label_xBDC = grp_Label_xBDC;
    }
    
    /** Getter for property grp_Label_xCLI.
     * @return Value of property grp_Label_xCLI.
     */
    public javax.swing.JLabel getGrp_Label_xCLI() {
        return grp_Label_xCLI;
    }
    
    /** Setter for property grp_Label_xCLI.
     * @param grp_Label_xCLI New value of property grp_Label_xCLI.
     */
    public void setGrp_Label_xCLI(javax.swing.JLabel grp_Label_xCLI) {
        this.grp_Label_xCLI = grp_Label_xCLI;
    }
    
    /** Getter for property grp_Label_yBDC.
     * @return Value of property grp_Label_yBDC.
     */
    public javax.swing.JLabel getGrp_Label_yBDC() {
        return grp_Label_yBDC;
    }
    
    /** Setter for property grp_Label_yBDC.
     * @param grp_Label_yBDC New value of property grp_Label_yBDC.
     */
    public void setGrp_Label_yBDC(javax.swing.JLabel grp_Label_yBDC) {
        this.grp_Label_yBDC = grp_Label_yBDC;
    }
    
    /** Getter for property grp_Label_yCLI.
     * @return Value of property grp_Label_yCLI.
     */
    public javax.swing.JLabel getGrp_Label_yCLI() {
        return grp_Label_yCLI;
    }
    
    /** Setter for property grp_Label_yCLI.
     * @param grp_Label_yCLI New value of property grp_Label_yCLI.
     */
    public void setGrp_Label_yCLI(javax.swing.JLabel grp_Label_yCLI) {
        this.grp_Label_yCLI = grp_Label_yCLI;
    }
    
    /** Getter for property grp_JBUT_reload.
     * @return Value of property grp_JBUT_reload.
     */
 
    
    /** Setter for property grp_JBUT_reload.
     * @param grp_JBUT_reload New value of property grp_JBUT_reload.
     */
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox grp_CheckBox_enteted;
    private javax.swing.JCheckBox grp_CheckBox_enteteg;
    private javax.swing.JButton grp_JBut_downBDC;
    private javax.swing.JButton grp_JBut_downCLI;
    private javax.swing.JButton grp_JBut_leftBDC;
    private javax.swing.JButton grp_JBut_leftCLI;
    private javax.swing.JButton grp_JBut_rightBDC;
    private javax.swing.JButton grp_JBut_rigthCLI;
    private javax.swing.JButton grp_JBut_upBDC;
    private javax.swing.JButton grp_JBut_upCLI;
    private javax.swing.JLabel grp_Label_xBDC;
    private javax.swing.JLabel grp_Label_xCLI;
    private javax.swing.JLabel grp_Label_yBDC;
    private javax.swing.JLabel grp_Label_yCLI;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
    
}
