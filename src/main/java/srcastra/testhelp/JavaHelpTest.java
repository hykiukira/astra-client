/*
 * JavaHelpTest.java
 *
 * Created on 28 février 2003, 17:04
 */

package srcastra.testhelp;

/**
 *
 * @author  Thomas
 */
import javax.help.*;
import java.net.URL;
import javax.swing.*;
public class JavaHelpTest {
   public JavaHelpTest(int lmcleunik){
        JHelp helpViewer = null;
      try {
         // Get the classloader of this class.
	      ClassLoader cl = JavaHelpTest.class.getClassLoader();
         // Use the findHelpSet method of HelpSet to create a URL referencing the helpset file.
         // Note that in this example the location of the helpset is implied as being in the same
         // directory as the program by specifying "jhelpset.hs" without any directory prefix,
         // this should be adjusted to suit the implementation.
               URL url =null;
                System.out.println("\n\n[***********]langue "+lmcleunik);
              if(lmcleunik==1){    
               // url = new URL("FRHelpSet.hs");
                 // url=new java.io.File("FRHelpSet.hs").toURL(  );
               url = getClass().getResource("FRHelpSet.hs");
              }
              else if(lmcleunik==2){
               url = getClass().getResource("NLHelpSet.hs");}
         // Create a new JHelp object with a new HelpSet.
                System.out.println("Url:"+url);
         helpViewer = new JHelp(new HelpSet(cl, url));
         // Set the initial entry point in the table of contents.
         helpViewer.setCurrentID("astra-aide");
	   } catch (Exception e) {
	      System.err.println("API Help Set not found");
              e.printStackTrace();
     	}

      // Create a new frame.
      JFrame frame = new JFrame();
      // Set it's size.
      frame.setSize(500,500);
      // Add the created helpViewer to it.
      frame.getContentPane().add(helpViewer);
      // Set a default close operation.
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      // Make the frame visible.
      frame.setVisible(true);
    
       
   }
   public static void main(String args[]) {
        new JavaHelpTest(1);
   }
}
