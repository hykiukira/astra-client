/* * UserIcon.java * * Created on 8 mars 2002, 9:14 */package srcastra.astra.gui.components.userIcon;import javax.swing.JButton;import javax.swing.JLabel;/** * @author Sébastien */public class UserIcon extends javax.swing.JPanel implements java.awt.event.MouseListener {    /**     * Creates new form UserIcon     */    public UserIcon(UserIconBridge parent, String username, String indexImg) {        this.parentComponent = parent;        this.userName = username;        this.indexImg = indexImg;        initComponents2();        addMouseListener(this);        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));    }    /**     * This method is called from within the constructor to     * <p/>     * <p/>     * <p/>     * <p/>     * <p/>     * <p/>     * initialize the form.     * <p/>     * <p/>     * <p/>     * <p/>     * <p/>     * <p/>     * WARNING: Do NOT modify this code. The content of this method is     * <p/>     * <p/>     * <p/>     * <p/>     * <p/>     * <p/>     * always regenerated by the Form Editor.     */    private void initComponents() {//GEN-BEGIN:initComponents        setLayout(new java.awt.GridBagLayout());        java.awt.GridBagConstraints gridBagConstraints1;        setBackground(java.awt.Color.white);        setPreferredSize(new java.awt.Dimension(57, 60));        setMinimumSize(new java.awt.Dimension(57, 60));        setMaximumSize(new java.awt.Dimension(57, 60));    }//GEN-END:initComponents    private void initComponents2() {        grp_Label_Icon = new JLabel();        grp_Label_UserIcon = new JLabel();        // Panel (this) initiation        this.setLayout(new java.awt.GridBagLayout());        java.awt.GridBagConstraints gridBagConstraints1;        this.setBackground(backgroundColor);        this.setPreferredSize(userIconDimension);        this.setMaximumSize(userIconDimension);        this.setMinimumSize(userIconDimension);        // icone        grp_Label_Icon.setIcon(getUserIcon());        grp_Label_Icon.setBackground(backgroundColor);        grp_Label_Icon.setPreferredSize(new java.awt.Dimension(45, 45));        grp_Label_Icon.setMinimumSize(new java.awt.Dimension(45, 45));        grp_Label_Icon.setMaximumSize(new java.awt.Dimension(45, 45));        gridBagConstraints1 = new java.awt.GridBagConstraints();        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 5, 0);        add(grp_Label_Icon, gridBagConstraints1);        // label username        grp_Label_UserIcon.setText(userName);        grp_Label_UserIcon.setHorizontalTextPosition(JLabel.CENTER);        grp_Label_UserIcon.setHorizontalAlignment(JLabel.CENTER);        grp_Label_UserIcon.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 10));        grp_Label_UserIcon.setPreferredSize(new java.awt.Dimension(54, 10));        grp_Label_UserIcon.setMaximumSize(new java.awt.Dimension(54, 10));        grp_Label_UserIcon.setMinimumSize(new java.awt.Dimension(54, 10));        gridBagConstraints1 = new java.awt.GridBagConstraints();        gridBagConstraints1.gridx = 0;        gridBagConstraints1.gridy = 1;        add(grp_Label_UserIcon, gridBagConstraints1);    }    /**     * Getter for property backgroundColor.     *     * @return Value of property backgroundColor.     */    public java.awt.Color getBackgroundColor() {        return backgroundColor;    }    /**     * Setter for property backgroundColor.     *     * @param backgroundColor New value of property backgroundColor.     */    public void setBackgroundColor(java.awt.Color backgroundColor) {        this.backgroundColor = backgroundColor;    }    /**     * Getter for property userIconDimension.     *     * @return Value of property userIconDimension.     */    public java.awt.Dimension getUserIconDimension() {        return userIconDimension;    }    /**     * Setter for property userIconDimension.     *     * @param userIconDimension New value of property userIconDimension.     */    public void setUserIconDimension(java.awt.Dimension userIconDimension) {        this.userIconDimension = userIconDimension;    }    private javax.swing.Icon getUserIcon() {        String imageFile = "lion.gif";        //java.util.ResourceBundle.getBundle("srcastra\astra\gui\img").getString("1");        javax.swing.Icon icon = null;        icon = new javax.swing.ImageIcon(getClass().getResource("/srcastra/astra/gui/img/" + imageFile));        return icon;    }    public void mouseExited(java.awt.event.MouseEvent mouseEvent) {    }    public void mouseReleased(java.awt.event.MouseEvent mouseEvent) {    }    public void mousePressed(java.awt.event.MouseEvent mouseEvent) {    }    public void mouseClicked(java.awt.event.MouseEvent mouseEvent) {        this.parentComponent.setUsernameFromUserIcon(this.userName);    }    public void mouseEntered(java.awt.event.MouseEvent mouseEvent) {    }    private JLabel grp_Label_Icon;    private JLabel grp_Label_UserIcon;    private java.awt.Color backgroundColor = java.awt.Color.white;    private java.awt.Dimension userIconDimension = new java.awt.Dimension(57, 60);    private UserIconBridge parentComponent;    private String userName;    private String indexImg;    // Variables declaration - do not modify//GEN-BEGIN:variables    // End of variables declaration//GEN-END:variables}