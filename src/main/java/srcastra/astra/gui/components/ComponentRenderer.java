/*







 * Renderer.java







 *







 * Created on 27 f�vrier 2002, 10:38







 */


package srcastra.astra.gui.components;


import java.awt.Component;


import java.awt.Graphics;


/**
 * @author S�bastien
 */


public interface ComponentRenderer {


    public java.awt.Component getRendering(Component parent, String text, boolean hasFocus, boolean isSelected);


}















