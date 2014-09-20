/*





 * RollFocus.java





 *





 * Created on 30 juillet 2002, 15:49





 */


package srcastra.astra.gui.components.fx;


/**
 * @author david
 */


public class JComponentBorderFx


        extends java.awt.event.FocusAdapter


        implements java.awt.event.MouseListener,


        java.beans.PropertyChangeListener


{


    private javax.swing.JComponent parent;


    private javax.swing.border.Border focusBorder;


    private javax.swing.border.Border lostBorder;


    private javax.swing.border.Border rollBorder;


    private boolean mouseOver = false;


    private boolean hasFocus = false;


    /**
     * Creates a new instance of RollFocus
     */


    public JComponentBorderFx(javax.swing.JComponent parent) {


        this.parent = parent;


        focusBorder = parent.getBorder();


        rollBorder = new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED);


        lostBorder = new javax.swing.plaf.metal.MetalBorders.Flush3DBorder();


        parent.addFocusListener(this);


        parent.addMouseListener(this);


        parent.addPropertyChangeListener("enabled", this);


        setBorder();


    }


    private void setBorder()


    {


        if ((hasFocus) && (parent.isEnabled()))


            parent.setBorder(focusBorder);


        else if ((mouseOver == true) && (parent.isEnabled()))


            parent.setBorder(rollBorder);


        else


            parent.setBorder(lostBorder);


    }


    public void focusGained(java.awt.event.FocusEvent focusEvent) {


        hasFocus = true;


        setBorder();


    }


    public void focusLost(java.awt.event.FocusEvent focusEvent) {


        hasFocus = false;


        setBorder();


    }


    public void mouseClicked(java.awt.event.MouseEvent mouseEvent) {


    }


    public void mouseEntered(java.awt.event.MouseEvent mouseEvent) {


        mouseOver = true;


        setBorder();


    }


    public void mouseExited(java.awt.event.MouseEvent mouseEvent) {


        mouseOver = false;


        setBorder();


    }


    public void mousePressed(java.awt.event.MouseEvent mouseEvent) {


    }


    public void mouseReleased(java.awt.event.MouseEvent mouseEvent) {


    }


    public void propertyChange(java.beans.PropertyChangeEvent propertyChangeEvent) {


        setBorder();


    }


}





