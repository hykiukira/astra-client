/*
 * ManageMailingFrame.java
 *
 * Created on 10 avril 2003, 12:33
 */

package srcastra.astra.gui.modules.Mailing;
import javax.swing.*;
import java.awt.event.*;
import srcastra.astra.sys.classetransfert.configuration.*;
import srcastra.astra.sys.*;
/**
 *
 * @author  Thomas
 */
public class ManageMailingFrame {
    javax.swing.JComponent parent;
    MailInterface frame;
    java.awt.Frame main;
    AbstractConfig_T config;
    KeyStroke openmail;
    /** Creates a new instance of ManageMailingFrame */
    public ManageMailingFrame(javax.swing.JComponent parent,MailInterface frame,java.awt.Frame main,AbstractConfig_T config) {
          Logger.getDefaultLogger().log(Logger.LOG_INFOS,"[MMMMMMMMMMMMMMMMMM]Initialisation de manangeMailing");
        this.main=main;
        this.frame=frame;
        this.parent=parent;
        this.config=config;
        ActionListener action=new ActionListener(){
          public void actionPerformed(ActionEvent evt){
              Logger.getDefaultLogger().log(Logger.LOG_INFOS,"[MMMMMMMMMMMMMMMMMM]demande d'ouverture du mailing");
          openFrame(evt);
          } 
        };
      parent.unregisterKeyboardAction(openmail);
      openmail= KeyStroke.getKeyStroke(KeyEvent.VK_M,InputEvent.ALT_MASK);
      parent.registerKeyboardAction(action,openmail,JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
     public ManageMailingFrame(javax.swing.JPanel parent,MailInterface frame,java.awt.Frame main,AbstractConfig_T config) {
          Logger.getDefaultLogger().log(Logger.LOG_INFOS,"[MMMMMMMMMMMMMMMMMM]Initialisation de manangeMailing");
        this.main=main;
        this.frame=frame;
        this.parent=(javax.swing.JComponent)parent;
        this.config=config;
        ActionListener action=new ActionListener(){
          public void actionPerformed(ActionEvent evt){
              Logger.getDefaultLogger().log(Logger.LOG_INFOS,"[MMMMMMMMMMMMMMMMMM]demande d'ouverture du mailing");
          openFrame(evt);
          } 
        };
      parent.unregisterKeyboardAction(openmail);
      openmail= KeyStroke.getKeyStroke(KeyEvent.VK_M,InputEvent.ALT_MASK);
      parent.registerKeyboardAction(action,openmail,JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
  
     public void openFrame(ActionEvent evt){
         if(frame.getEmailAdres()!=null){
            mailing=new MaiLing(main,true,frame,config);
            mailing.show();
         }
        }
    MaiLing mailing;
}
