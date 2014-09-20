/*
 * TypeFacer.java
 *
 * Created on 28 février 2003, 16:38
 */

package srcastra.testhelp;

/**
 *
 * @author  Thomas
 */
import java.awt.event.*;
import java.awt.*;
import java.util.Hashtable;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.help.*;

// ....................................... TypeFacer (frame)
 
public class TypeFacer extends JFrame {

  // JavaHelp items

  HelpSet hs;
  HelpBroker hb;

  // screen components

  JTextField inputField, displayField;
  JComboBox  fontChoice, foreChoice, backChoice;
  JCheckBox  boldBox, italicBox;
  JButton    showButton, clearButton;
  JButton    helpButton;
  JButton    qButton;

  // embedded help components
  JPanel helpPanel;
  JHelpNavigator nav;
  JHelpContentViewer viewer;

  // menu components

  JMenu     fileMenu, viewMenu;
  JMenuItem exitItem, typeItem, colorItem;
  JMenu helpMenu;
  JMenuItem helpItem;
  JMenuItem helpItemTOC;
  JMenuItem embeddedItem;

  // frame will contain a panel using the CardLayout manager

  CardLayout manager;
  JPanel cards;

  // fonts and colors for use in display box

  Hashtable fonts, colors;

  // combo box choices

  final String[] fontList =
           { "TimesRoman", "Helvetica", "Courier" };
  final String[] colorList =
           { "Black", "Red", "Green", "Yellow", "Blue", "White" };

  // title for each screen

  final String typefTitle = "Typeface Tester: Choose Typeface";
  final String colorTitle = "Typeface Tester: Choose Colors";

  // ....................................... constructor

  public TypeFacer(  ) {

    // create and size a JFrame; set up content pane
    super("Typeface Tester: Choose Typeface");
    //!!in next line, change "250" to "500"
    setSize(500, 250);

    JPanel contentPane = (JPanel) getContentPane(  );
    contentPane.setLayout(new FlowLayout(  ));
    contentPane.setBorder(
      BorderFactory.createEmptyBorder(10,10,10,10));

    // exit the program if the user closes the window

    addWindowListener(new WindowAdapter(  ) {
      public void windowClosing(WindowEvent we) {System.exit(0);}
    });

    // open HelpSet, send console message
    // hardcoded location: "HelpSet.hs" in "TFhelp" subdirectory

    try {
      //URL hsURL = new URL((new File(".")).toURL(  ), "TFhelp/HelpSet.hs");
        
      URL hsURL =  getClass().getResource("HelpSet.hs");
      System.out.println("url"+hsURL);
      hs = new HelpSet(null, hsURL);
      System.out.println("Found help set at " + hsURL);
    }
    catch (Exception ee) {
      System.out.println("HelpSet not found");
      System.exit(0);
    }

    // create HelpBroker from HelpSet
    hb = hs.createHelpBroker(  );
    // enable function key F1
    hb.enableHelpKey(getRootPane(  ), "overview", hs);

    // set up top-most panel containing text-input field

    JPanel inputPanel = new JPanel(new FlowLayout(  ));
    contentPane.add(inputPanel);

    JLabel inputLabel = new JLabel("Text");
    inputField = new JTextField("Enter some text here", 30);

    inputPanel.add(inputLabel);
    inputPanel.add(inputField);

    // set up middle panel, in which two cards will
    // be displayed: typefCard and colorCard

    manager = new CardLayout(  );
    cards = new JPanel(manager);
    contentPane.add(cards);

    JPanel typefCard = new JPanel(new GridLayout(2,4,5,5));
    JPanel colorCard = new JPanel(new GridLayout(2,4,5,5));

    cards.add(typefCard, "Typefaces");
    cards.add(colorCard, "Colors");

    // TypeFace card: create components

    JLabel fontLabel = new JLabel("Font", JLabel.RIGHT);
    fontChoice = new JComboBox(fontList);

    JLabel styleLabel = new JLabel("Style", JLabel.RIGHT);
    boldBox = new JCheckBox("Bold");
    italicBox = new JCheckBox("Italic");

    // TypeFace card: place components on card

    // first row
    typefCard.add(fontLabel);
    typefCard.add(fontChoice);
    typefCard.add(styleLabel);
    typefCard.add(boldBox);
    // second row (start with three empty slots)
    typefCard.add(new JLabel(  ));
    typefCard.add(new JLabel(  ));
    typefCard.add(new JLabel(  ));
    typefCard.add(italicBox);

    // Colors card: create components

    JLabel foreLabel = new JLabel("Foreground", JLabel.RIGHT);
    foreChoice = new JComboBox(colorList);
    foreChoice.setSelectedIndex(0);  // initialize to "black"

    JLabel backLabel = new JLabel("Background", JLabel.RIGHT);
    backChoice = new JComboBox(colorList);
    backChoice.setSelectedIndex(5);  // initialize to "white"

    // Colors card: place components on card

    // first row
    colorCard.add(new JLabel(  ));
    colorCard.add(foreLabel);
    colorCard.add(foreChoice);
    colorCard.add(new JLabel(  ));
    // second row
    colorCard.add(new JLabel(  ));
    colorCard.add(backLabel);
    colorCard.add(backChoice);
    colorCard.add(new JLabel(  ));

    // set up styled output panel

    JPanel displayPanel = new JPanel(new FlowLayout(  ));
    contentPane.add(displayPanel);

    displayField = new JTextField(40);
    displayField.setEditable(false);
    displayField.setFont(new Font("TimesRoman", Font.PLAIN, 16));
    displayField.setHorizontalAlignment(SwingConstants.CENTER);

    // set up button panel

    JPanel buttonPanel =
      new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    contentPane.add(buttonPanel);

    showButton = new JButton("Show");
    clearButton = new JButton("Clear");
    helpButton = new JButton("Help");
    qButton = new JButton(new ImageIcon("help.gif"));  // make this an Icon

    buttonPanel.add(showButton);
    buttonPanel.add(clearButton);
    buttonPanel.add(helpButton);
    buttonPanel.add(qButton);
    displayPanel.add(displayField);

    // create an embedded help panel
    helpPanel = new JPanel(new GridLayout(1,2,5,5));
   
    // add a content viewer
    viewer = new JHelpContentViewer(hs);
    viewer.setPreferredSize(new Dimension(200,220));
//    viewer.setCurrentID("typefaces");
   
    // add a navigator with a table of contents view
    nav = (JHelpNavigator)
      hs.getNavigatorView("TOC").createNavigator(viewer.getModel(  ));
    nav.setPreferredSize(new Dimension(200,220));
   
    // add the components to the layout
    helpPanel.add(nav);
    helpPanel.add(viewer);
    contentPane.add(helpPanel);

    // set up menu structure

    fileMenu = new JMenu("File");
    exitItem = new JMenuItem("Exit");
    fileMenu.add(exitItem);

    viewMenu = new JMenu("View");
    typeItem = new JMenuItem("Typefaces");
    colorItem = new JMenuItem("Colors");
    viewMenu.add(typeItem);
    viewMenu.add(colorItem);

    helpMenu = new JMenu("Help");
    helpItemTOC = new JMenuItem("Contents");
    helpMenu.add(helpItemTOC);
    CSH.setHelpIDString(helpItemTOC, "overview");
    //!!in next line, change "Contents" to "For This Screen"
    helpItem = new JMenuItem("Contents...");
    helpMenu.add(helpItem);

    embeddedItem = new JMenuItem("Hide Embedded Help");
    helpMenu.add(embeddedItem);
    embeddedItem.setActionCommand("hide");

    JMenuBar menuBar = new JMenuBar(  );
    menuBar.add(fileMenu);
    menuBar.add(viewMenu);
    menuBar.add(helpMenu);

    setJMenuBar(menuBar);

    // Fill the fonts and colors hashtables

    fonts  = new Hashtable(12);
    fonts.put("Helvetica",
              new Font("Helvetica", Font.PLAIN, 16));
    fonts.put("Helveticabold",
              new Font("Helvetica", Font.BOLD, 16));
    fonts.put("Helveticaitalic",
              new Font("Helvetica", Font.ITALIC, 16));
    fonts.put("Helveticabolditalic",
              new Font("Helvetica", Font.BOLD + Font.ITALIC, 16));
    fonts.put("TimesRoman",
              new Font("TimesRoman", Font.PLAIN, 16));
    fonts.put("TimesRomanbold",
              new Font("TimesRoman", Font.BOLD, 16));
    fonts.put("TimesRomanitalic",
              new Font("TimesRoman", Font.ITALIC, 16));
    fonts.put("TimesRomanbolditalic",
              new Font("TimesRoman", Font.BOLD + Font.ITALIC, 16));
    fonts.put("Courier",
              new Font("Courier", Font.PLAIN, 16));
    fonts.put("Courierbold",
              new Font("Courier", Font.BOLD, 16));
    fonts.put("Courieritalic",
              new Font("Courier", Font.ITALIC, 16));
    fonts.put("Courierbolditalic",
              new Font("Courier", Font.BOLD + Font.ITALIC, 16));

    colors = new Hashtable(6);
    colors.put("Black", Color.black);
    colors.put("Red", Color.red);
    colors.put("Blue", Color.blue);
    colors.put("Green", Color.green);
    colors.put("Yellow", Color.yellow);
    colors.put("White", Color.white);

    // activate the "Show" button

    showButton.addActionListener(new ActionListener(  ) {
      public void actionPerformed(ActionEvent ae) {

        // compute the font's name (hash key)
        String fontFace = fontChoice.getSelectedItem().toString(  );
        fontFace += boldBox.isSelected(  ) ? "bold" : "";
        fontFace += italicBox.isSelected(  ) ? "italic" : "";

        // set the font
        displayField.setFont((Font)fonts.get(fontFace));

        // set the colors
        displayField.setForeground(
          (Color) colors.get(foreChoice.getSelectedItem(  )));
        displayField.setBackground(
          (Color) colors.get(backChoice.getSelectedItem(  )));
        displayField.setText(inputField.getText(  ));
      }
    });

    // activate the "Clear" button

    clearButton.addActionListener(new ActionListener(  ) {
      public void actionPerformed(ActionEvent ae) {
        displayField.setText("");
        inputField.setText("");
      }
    });

    // activate the "Exit" menu item

    exitItem.addActionListener(new ActionListener(  ) {
      public void actionPerformed(ActionEvent ae) {System.exit(0);}
    });

    // activate the "Typefaces" menu item

    typeItem.addActionListener(new ActionListener(  ) {
      public void actionPerformed(ActionEvent ae) {
        manager.show(cards, "Typefaces");
        TypeFacer.this.setTitle(typefTitle);
        // configure function key F1, help button, help menu item
        CSH.setHelpIDString(TypeFacer.this.getRootPane(  ), "typefaces");
        CSH.setHelpIDString(helpItem, "typefaces");
        CSH.setHelpIDString(helpButton, "typefaces");

        // update the embedded help content panel
        viewer.setCurrentID("typefaces");
      }
    });

    // activate the "Colors" menu item

    colorItem.addActionListener(new ActionListener(  ) {
      public void actionPerformed(ActionEvent ae) {
        manager.show(cards, "Colors");
        TypeFacer.this.setTitle(colorTitle);
        // configure function key F1, help button, help menu item
        CSH.setHelpIDString(TypeFacer.this.getRootPane(  ), "colors");
        CSH.setHelpIDString(helpItem, "colors");
        CSH.setHelpIDString(helpButton, "colors");

        // update the embedded help content panel
        viewer.setCurrentID("colors");
      }
    });

    // activate the "Embedded Help" toggle menu item
    embeddedItem.addActionListener(new ActionListener(  ) {
      public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand(  ).equals("hide")) {
          helpPanel.remove(nav);
          helpPanel.remove(viewer);
          helpPanel.validate(  );
          TypeFacer.this.setSize(500,250);
          embeddedItem.setText("Show Embedded Help");
          embeddedItem.setActionCommand("show");
        }
        else {
          helpPanel.add(nav);
          helpPanel.add(viewer);
          helpPanel.validate(  );
          TypeFacer.this.setSize(500,500);
          embeddedItem.setText("Hide Embedded Help");
          embeddedItem.setActionCommand("hide");
        }
      }
    });


    // activate the field-level help button
    qButton.addActionListener(
      new CSH.DisplayHelpAfterTracking(hb)
    );

    // activate the Help menu item and Help button
    ActionListener helper = new CSH.DisplayHelpFromSource(hb);
    helpItem.addActionListener(helper);
    helpButton.addActionListener(helper);
    helpItemTOC.addActionListener(helper);

    // assign map IDs for field-level context-sensitive help
    CSH.setHelpIDString(inputField, "text");
    CSH.setHelpIDString(fontChoice, "font");
    CSH.setHelpIDString(boldBox, "bold");
    CSH.setHelpIDString(italicBox, "italic");
    CSH.setHelpIDString(showButton, "view"); 
    CSH.setHelpIDString(clearButton, "clear"); 
    CSH.setHelpIDString(helpButton, "help");
    CSH.setHelpIDString(qButton, "whats_this");
    CSH.setHelpIDString(displayField, "text_display");
    CSH.setHelpIDString(foreChoice, "fore_color");
    CSH.setHelpIDString(backChoice, "back_color");

  }

  public 
static void main(String args[]) {
    (new TypeFacer(  )).setVisible(true);
  }
}
