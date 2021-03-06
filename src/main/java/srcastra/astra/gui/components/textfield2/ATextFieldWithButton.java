/*





 * ATextFieldWithButton.java





 *





 * Created on 18 juin 2002, 10:37





 */


package srcastra.astra.gui.components.textfield2;

// For Grahics environnement


import java.awt.Font;

// for InterScreenModules Interaction


import srcastra.astra.gui.modules.InternScreenModule;


import srcastra.astra.gui.sys.AString;


import srcastra.astra.gui.components.AstraComponent;

// Listeners & event


import java.awt.event.ActionListener;


import java.awt.event.ActionEvent;


/**
 * Beans Graphique comportant toutes les propri�t�s de ATexfield.
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * Ajout d'un bouton pour lancer une dialogBox dans laquelle on peut instancier un objet
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * du type InternScreenModule.
 *
 * @author S�bastien
 * @version 1.1
 * @see ATexfield.java
 */


public class ATextFieldWithButton extends ATextField implements AstraComponent {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// CONSTRUCTOR

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Creates new ATextFieldWithButton
     */


    public ATextFieldWithButton() {


        super();


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// INITIALISATION

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    protected void preInit() {


        super.preInit();


        this.frameModal = true;


        this.content = new AString(-1, "");


    }


    /**
     * Initialise les composants graphiques qui compl�tent le beans
     */


    protected void initComponents() {


        grp_Pane_InternScreenModule = null;


        grp_JText_encode = new javax.swing.JFormattedTextField();


        grp_Label_warning = new javax.swing.JLabel();


        grp_But_Launcher = new javax.swing.JButton("...");


        grp_Label_espace = new javax.swing.JLabel();

        // particularit� du JText


        grp_JText_encode.setFont(new Font("Tahoma", Font.PLAIN, 10));


        grp_JText_encode.setDisabledTextColor(java.awt.Color.black);


        grp_JText_encode.setVerifyInputWhenFocusTarget(false);


        grp_JText_encode.setEditable(false);


        grp_JText_encode.setPreferredSize(new java.awt.Dimension(82, 18));

        // grp_Label_espace


        grp_Label_espace.setPreferredSize(new java.awt.Dimension(5, 18));

        // grp_But_Launcher


        grp_But_Launcher.setPreferredSize(new java.awt.Dimension(18, 18));


        grp_But_Launcher.setRequestFocusEnabled(false);

        // particularit� du JLabel


        grp_Label_warning.setPreferredSize(new java.awt.Dimension(18, 18));

        // this


        this.setPreferredSize(new java.awt.Dimension(123, 18));


        this.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));


        this.setRequestFocusEnabled(false);


        this.add(grp_JText_encode);


        this.add(grp_Label_espace);


        this.add(grp_But_Launcher);


        this.add(grp_Label_warning);


    }


    protected void postInit() {


        System.out.println("[ATFWB] postInit");


        new srcastra.astra.gui.components.fx.JComponentBorderFx(grp_JText_encode);


        grp_JText_encode.addFocusListener(super.focusListener);


        grp_JText_encode.addActionListener(super.actionListener);


        grp_But_Launcher.addActionListener(new actionPopupListener());

        // grp_But_Launcher.addActionListener(actionTest);

        // grp_But_Launcher.addActionListener(this.actionPopupListener);

        // grp_But_Launcher.addFocusListener(super.focusListener);


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => METHODE APPARENTE AU BEANS

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private void closeDialog(java.awt.event.WindowEvent evt) {


        javax.swing.JDialog dialog = (javax.swing.JDialog) evt.getSource();


        dialog.setVisible(false);

        /* si l'InternScreenModule est une instance de ATextFieldComponent





* on appelle la fonction actionOnDispose() sinon on ne fait rien */


        try {


            ((ATextfieldWithButtonComponent) grp_Pane_InternScreenModule).actionOnDispose();


        } catch (ClassCastException e) {

            // ne rien faire


        }


        dialog.dispose();


        grp_JText_encode.requestFocus();


        grp_Pane_InternScreenModule.setThisAsToolBarComponent();


    }


    private java.awt.Point getCenteredLocation(java.awt.Component comp) {


        java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();


        java.awt.Dimension resolution = toolkit.getScreenSize();


        int w = comp.getSize().width;


        int h = comp.getSize().height;


        int x = (resolution.width / 2) - (w / 2);


        int y = (resolution.height / 2) - (h / 2);


        return new java.awt.Point(x, y);


    }


    private ATextFieldWithButton getThisInstance() {


        return this;


    }


    public void selectAListComponent(int cleUnik) {


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => Beans Properties

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => Champs de la classe

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private boolean frameModal;


    private AString content;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// STATIC VARIABLES

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  .

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// => Graphic Component

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private javax.swing.JButton grp_But_Launcher;


    private javax.swing.JLabel grp_Label_espace;


    private InternScreenModule grp_Pane_InternScreenModule;


    private java.awt.Dimension dialogDimension;


    private java.awt.Frame frame;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

// => Listeners

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    protected class actionPopupListener implements ActionListener {


        public void actionPerformed(java.awt.event.ActionEvent actionEvent) {


            System.out.println("[ATEXTFIELD WITH BUTTON] je rentre ...");


            if (grp_Pane_InternScreenModule != null) {


                System.out.println("[ATEXTFIELD WITH BUTTON] je suis rentr�");


                javax.swing.JDialog dialog = null;


                if (frame != null)


                    dialog = new javax.swing.JDialog(frame, frameModal);


                else


                    dialog = new javax.swing.JDialog();


                dialog.setResizable(false);


                dialog.addWindowListener(new java.awt.event.WindowAdapter() {


                    public void windowClosing(java.awt.event.WindowEvent evt) {


                        closeDialog(evt);


                    }


                });


                dialog.setSize(dialogDimension);


                dialog.setLocation(getCenteredLocation(dialog));


                dialog.getContentPane().add((java.awt.Component) grp_Pane_InternScreenModule);

                // met le composant inclus dans la DialogBox en ReadMode


                grp_Pane_InternScreenModule.displayReadMode();

                // passage de l'instance de la dialogBox au composant InterScreenModule


                try {


                    ((ATextfieldWithButtonComponent) grp_Pane_InternScreenModule).addDialogInstance(dialog);

                    //  ((ATextfieldWithButtonComponent)grp_Pane_InternScreenModule).addATextFieldComponentWithButtonInstance(getThisInstance());


                } catch (Exception e) {


                    e.printStackTrace();


                }

                // on montre la dialog box


                dialog.show(true);


            }


        }


    }


    private ActionListener actionPopupListener = new ActionListener() {


        public void actionPerformed(ActionEvent evt) {


        }


    };


    private ActionListener actionTest = new ActionListener() {


        public void actionPerformed(ActionEvent evt) {


            System.out.println("[JE FAIS CETTE ACTION BORDEL !!!]");


        }


    };

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// BEANS PROPERTIES GET/SET SUPPORT

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * D�fini la PreferedSize du composant
     *
     * @param dim dimension � appliquer
     */


    public void setPreferredSize(java.awt.Dimension dim) {


        super.setPreferredSize(dim);


        int x = dim.width;


        int y = dim.height;


        grp_JText_encode.setPreferredSize(new java.awt.Dimension(x - (y * 2) - 5, y));


        grp_Label_espace.setPreferredSize(new java.awt.Dimension(5, y));


        grp_But_Launcher.setPreferredSize(new java.awt.Dimension(y, y));


        grp_Label_warning.setPreferredSize(new java.awt.Dimension(y, y));


    }


    /**
     * D�fini la MinimumSize du composant
     *
     * @param dim dimension � appliquer
     */


    public void setMinimumSize(java.awt.Dimension dim) {


        super.setPreferredSize(dim);


        int x = dim.width;


        int y = dim.height;


        grp_JText_encode.setPreferredSize(new java.awt.Dimension(x - (y * 2) - 5, y));


        grp_Label_espace.setPreferredSize(new java.awt.Dimension(5, y));


        grp_But_Launcher.setPreferredSize(new java.awt.Dimension(y, y));


        grp_Label_warning.setPreferredSize(new java.awt.Dimension(y, y));


    }


    /**
     * D�fini la MaximumSize du composant
     *
     * @param dim dimension � appliquer
     */


    public void setMaximumSize(java.awt.Dimension dim) {


        super.setPreferredSize(dim);


        int x = dim.width;


        int y = dim.height;


        grp_JText_encode.setPreferredSize(new java.awt.Dimension(x - (y * 2) - 5, y));


        grp_Label_espace.setPreferredSize(new java.awt.Dimension(5, y));


        grp_But_Launcher.setPreferredSize(new java.awt.Dimension(y, y));


        grp_Label_warning.setPreferredSize(new java.awt.Dimension(y, y));


    }


    /**
     * Active le beans et ses composants
     *
     * @param enabled activation oui/non
     */


    public void setEnabled(boolean enabled) {


        grp_But_Launcher.setEnabled(enabled);


        grp_Label_espace.setEnabled(enabled);


        super.setEnabled(enabled);


    }


    public void setContent(AString content) {


        if (content != null) {


            this.content = content;


            grp_JText_encode.setText(content.toString());


        }


    }


    public void loadContentString(int cleUnik) {


        String content = "";


        if (grp_Pane_InternScreenModule != null) {


            try {


                content = ((ATextfieldWithButtonComponent) grp_Pane_InternScreenModule).loadContentString(cleUnik);


            }


            catch (Exception e) {

                // ne rien faire


            }


        }


        grp_JText_encode.setText(content);


    }


    public int getCleUnik() {


        return content.getCleunik();


    }


    /**
     * Getter for property grp_Pane_InternScreenModule.
     *
     * @return Value of property grp_Pane_InternScreenModule.
     */


    public srcastra.astra.gui.modules.InternScreenModule getGrp_Pane_InternScreenModule() {


        return grp_Pane_InternScreenModule;


    }


    /**
     * Setter for property grp_Pane_InternScreenModule.
     *
     * @param grp_Pane_InternScreenModule New value of property grp_Pane_InternScreenModule.
     */


    public void setGrp_Pane_InternScreenModule(srcastra.astra.gui.modules.InternScreenModule grp_Pane_InternScreenModule) {


        this.grp_Pane_InternScreenModule = grp_Pane_InternScreenModule;


    }


    /**
     * Getter for property dialogDimension.
     *
     * @return Value of property dialogDimension.
     */


    public java.awt.Dimension getDialogDimension() {


        return dialogDimension;


    }


    /**
     * Setter for property dialogDimension.
     *
     * @param dialogDimension New value of property dialogDimension.
     */


    public void setDialogDimension(java.awt.Dimension dialogDimension) {


        this.dialogDimension = dialogDimension;


    }


    /**
     * Getter for property frame.
     *
     * @return Value of property frame.
     */


    public java.awt.Frame getFrame() {


        return frame;


    }


    /**
     * Setter for property frame.
     *
     * @param frame New value of property frame.
     */


    public void setFrame(java.awt.Frame frame) {


        this.frame = frame;


    }


    /**
     * Getter for property frameModal.
     *
     * @return Value of property frameModal.
     */


    public boolean isFrameModal() {


        return frameModal;


    }


    /**
     * Setter for property frameModal.
     *
     * @param frameModal New value of property frameModal.
     */


    public void setFrameModal(boolean frameModal) {


        this.frameModal = frameModal;


    }


    public int getVerif() {


        return 0;


    }


    public void requestFocus() {


        grp_JText_encode.requestFocus();


    }


}





