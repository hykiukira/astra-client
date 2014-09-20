/*





 * ColoredListRenderer.java





 *





 * Created on 21 novembre 2002, 9:41





 */


package srcastra.astra.gui.components.combobox.liste2;


import javax.swing.table.DefaultTableCellRenderer;


import java.awt.Color;


/**
 * /**
 *
 * @author Thomas
 */


public class ColoredListRenderer extends DefaultTableCellRenderer {


    /**
     * Creates a new instance of ColoredListRenderer
     */


    public ColoredListRenderer() {


    }


    public void setValue(Object value) {


        if (value instanceof RowColorListe) {


            if (value instanceof RowColorListe) {


                setForeground(((RowColorListe) value).m_color);


                setText(((RowColorListe) value).m_data.toString());


            } else


                super.setValue(value);


        }


    }


    public Color m_color;


    public static Color GREEN = new Color(0, 128, 0);


}





