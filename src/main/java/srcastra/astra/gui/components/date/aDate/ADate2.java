/*





 * ADate2.java





 *





 * Created on 2 décembre 2002, 11:06





 */


package srcastra.astra.gui.components.date.aDate;


import javax.swing.*;


import javax.swing.text.*;


import javax.swing.plaf.*;


import javax.swing.plaf.basic.*;


import java.awt.*;


/**
 * @author Sébastien
 */


public class ADate2 extends JPanel implements java.io.Serializable {


    private JLabel grp_label_warning;


    private ADate2.DateField grp_DField_encode;


    /**
     * Creates a new instance of ADate2
     */


    public ADate2() {


        init();


    }


    private void init() {


        grp_DField_encode = new ADate2.DateField();


        grp_label_warning = new JLabel();


        grp_label_warning.setOpaque(true);


        grp_label_warning.setBackground(Color.red);


        grp_label_warning.setPreferredSize(new Dimension(18, 18));


        grp_DField_encode.setPreferredSize(new Dimension(100, 18));


        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));


        add(grp_DField_encode);


        add(grp_label_warning);


    }


    public class DateField extends JTextField {


        public DateField() {


            super();


        }


    }


}





