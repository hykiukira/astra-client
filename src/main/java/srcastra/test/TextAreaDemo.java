/*
 * TextAreaDemo.java
 *
 * Created on 18 février 2003, 8:43
 */

package srcastra.test;

/**
 *
 * @author  Thomas
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class TextAreaDemo extends JFrame {
    //========================================================= constructor
    public TextAreaDemo() {
        // create textarea with initial text, scrolling, and border.
        JTextArea resultArea = new JTextArea(10, 40);
        resultArea.setText("Enter more text to see scrollbars");
        JScrollPane scrollingArea = new JScrollPane(resultArea);
        scrollingArea.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
        
        // Get the content pane, set layout, add to center
        Container content = this.getContentPane();
        content.setLayout(new BorderLayout());
        content.add(scrollingArea, BorderLayout.CENTER);
        
        this.setTitle("TextAreaDemo");
        this.pack();
    }//endconstructor
    
    //=============================================================== main()
    public static void main(String[] args) {
        JFrame myTextAreaDemo = new TextAreaDemo();
        myTextAreaDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myTextAreaDemo.setVisible(true);
    }//endmethod main

}//endclass
