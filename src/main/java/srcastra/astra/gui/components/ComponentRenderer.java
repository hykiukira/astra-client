/*







 * Renderer.java







 *







 * Created on 27 février 2002, 10:38







 */


package srcastra.astra.gui.components;


import java.awt.Component;


import java.awt.Graphics;


/**
 * @author Sébastien
 */


public interface ComponentRenderer {


    public java.awt.Component getRendering(Component parent, String text, boolean hasFocus, boolean isSelected);


}















