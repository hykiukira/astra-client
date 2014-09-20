/*





 * RollButtonListener.java





 *





 * Created on 30 juillet 2002, 15:19





 */


package srcastra.astra.gui.components;


/**
 * @author rene
 */


public class RollButtonListener extends java.awt.event.MouseAdapter {


    private javax.swing.JButton parentButton;


    /**
     * Creates a new instance of RollButtonListener
     */


    public RollButtonListener(javax.swing.JButton parent) {


        parentButton = parent;


        parentButton.setBorderPainted(false);


    }


    public void mouseEntered(java.awt.event.MouseEvent mouseEvent) {


        parentButton.setBorderPainted(parentButton.isEnabled());


    }


    public void mouseExited(java.awt.event.MouseEvent mouseEvent) {


        parentButton.setBorderPainted(false);


    }


}





