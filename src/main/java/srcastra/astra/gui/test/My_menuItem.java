/*
 * My_menuItem.java
 *
 * Created on 16 mai 2003, 10:47
 */

package srcastra.astra.gui.test;
import java.awt.event.*;
import java.util.*;
/**
 *
 * @author  Thomas
 */
public class My_menuItem extends javax.swing.JMenuItem{
    
    /** Creates a new instance of My_menuItem */
    public My_menuItem(javax.swing.JMenuItem item) {
        super();
        Object[]  listeners=item.getListeners(ActionListener.class);
        if(listeners!=null){
         for(int i=0;i<listeners.length;i++){
           if(ActionListener.class.isAssignableFrom(listeners[i].getClass()))
            super.addActionListener((ActionListener)listeners[i]);
         }          
        }    
    }
    public void fireActionEvent(ActionEvent evt){
      super.fireActionPerformed(evt);     
    }
    
}
