/*





 * NavigateInTable.java





 *





 * Created on 25 octobre 2002, 10:15





 */


package srcastra.astra.gui.event;


import java.awt.event.KeyListener;


import java.awt.event.KeyEvent;


/**
 * @author Sébastien
 */


public class NavigateInTable implements KeyListener {


    private javax.swing.JTable m_table;


    /**
     * Creates a new instance of NavigateInTable
     */


    public NavigateInTable(javax.swing.JTable table) {


        this.m_table = table;


    }


    public void keyPressed(java.awt.event.KeyEvent e) {


        int key = e.getKeyCode();


        if (m_table.getRowCount() > 0) {


            switch (key) {


                case KeyEvent.VK_UP:


                    if (m_table.getSelectedRow() > 0)
                        m_table.changeSelection(m_table.getSelectedRow() - 1, 0, false, false);


                    break;


                case KeyEvent.VK_DOWN:


                    if (m_table.getSelectedRow() < m_table.getRowCount() - 1)
                        m_table.changeSelection(m_table.getSelectedRow() + 1, 0, false, false);


                    break;


                case KeyEvent.VK_PAGE_UP:


                    break;


                case KeyEvent.VK_PAGE_DOWN:


                    break;


            }


        }


    }


    public void keyReleased(java.awt.event.KeyEvent e) {


    }


    public void keyTyped(java.awt.event.KeyEvent e) {


    }


}





