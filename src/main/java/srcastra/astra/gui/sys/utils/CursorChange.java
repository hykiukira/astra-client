/*
 * CursorChange.java
 *
 * Created on 2 septembre 2002, 11:31
 */

package srcastra.astra.gui.sys.utils;

/**
 *
 * @author  Sébastien
 */
public class CursorChange {
   
   public static final int CHANGE_CURSOR_IN_INTERNALFRAME = 0;
   public static final int CHANGE_CURSOR_IN_MAINFRAME = 1;
   public static final int CHANGE_CURSOR_EVERYWHERE = 2;
    
   public static void changeCursor(int whereChange, java.awt.Cursor cursor, java.awt.Frame frame, javax.swing.JInternalFrame iFrame) {
       boolean inFrame = false;
       boolean inIFrame = false;
       
       switch (whereChange) {
           case CursorChange.CHANGE_CURSOR_IN_INTERNALFRAME :
               inIFrame = true;
               break;
           case CursorChange.CHANGE_CURSOR_IN_MAINFRAME :
               inFrame = true;
               break;
           case CursorChange.CHANGE_CURSOR_EVERYWHERE :
               inFrame = true;
               inIFrame = true;
               break;
       }
       
       if (inIFrame && iFrame != null) {
           iFrame.getContentPane().setCursor(cursor);
       }
       if (inFrame && frame != null) {
           frame.setCursor(cursor.getType());
       }
           
    
   }
    
}
