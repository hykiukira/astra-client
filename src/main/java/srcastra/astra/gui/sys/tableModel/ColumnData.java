/*
 * ColumnData.java
 *
 * Created on 15 octobre 2002, 12:51
 */

package srcastra.astra.gui.sys.tableModel;
import java.util.ResourceBundle;
import java.util.MissingResourceException;
import srcastra.astra.gui.sys.ErreurScreenLibrary;


/**
 *
 * @author  Sébastien
 */
public class ColumnData {
    
    private String m_title;
    private int m_width;
    private int m_alignment;
    
    
     public ColumnData(String bundleKey, ResourceBundle bundle, int width, int alignment) {
        this(retrieveDataFromBundle(bundleKey, bundle), width, alignment);
     }
     
    /** Creates a new instance of ColumnData */
    public ColumnData(String title, int width, int alignment) {
        this.m_title = title;
        this.m_width = width;
        this.m_alignment = alignment;
    }
    
   
    
    private static String retrieveDataFromBundle(String bundleKey, ResourceBundle bundle) {
        String title = ""; 
        try {
            title = bundle.getString(bundleKey);
        }
        catch (MissingResourceException e) { 
            ErreurScreenLibrary.displayErreur(null, ErreurScreenLibrary.MISSING_RESSOURCE_EXCEPTION, srcastra.astra.Astra.DEBUG, e);
        }
        catch (Exception e) {}
        
        return title;
    }
    
    /** Getter for property m_title.
     * @return Value of property m_title.
     */
    public java.lang.String getM_title() {
        return m_title;
    }
    
    /** Setter for property m_title.
     * @param m_title New value of property m_title.
     */
    public void setM_title(java.lang.String m_title) {
        this.m_title = m_title;
    }
    
    /** Getter for property m_width.
     * @return Value of property m_width.
     */
    public int getM_width() {
        return m_width;
    }
    
    /** Setter for property m_width.
     * @param m_width New value of property m_width.
     */
    public void setM_width(int m_width) {
        this.m_width = m_width;
    }
    
    /** Getter for property m_alignment.
     * @return Value of property m_alignment.
     */
    public int getM_alignment() {
        return m_alignment;
    }
    
    /** Setter for property m_alignment.
     * @param m_alignment New value of property m_alignment.
     */
    public void setM_alignment(int m_alignment) {
        this.m_alignment = m_alignment;
    }
    
}
