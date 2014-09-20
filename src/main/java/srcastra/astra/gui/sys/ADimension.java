/*
 * ADimension.java
 *
 * Created on 10 juillet 2002, 10:11
 */

package srcastra.astra.gui.sys;

/**
 *
 * @author  Sébastien
 * @version 
 */
public class ADimension {
    
    private double dWidth;
    private double dHeight;
    private int iWidth;
    private int iHeight;

    /** Creates new ADimension */
    public ADimension() {
        this.dHeight =   0;
        this.dWidth  =   0;
        this.iHeight =   0;
        this.iWidth  =   0;
    }
    
    public ADimension(int width, int height) {
        this.dHeight =   height;
        this.dWidth  =   width;
        this.iHeight =   height;
        this.iWidth  =   width;
    }
    
    public ADimension(double width, double height) {
        this.dHeight =   height;
        this.dWidth  =   width;
        this.iHeight =   new Double(height).intValue();
        this.iWidth  =   new Double(width).intValue();
    }
    
    public ADimension(java.awt.Dimension dimension) {
        this.dHeight = dimension.getHeight();
        this.dWidth = dimension.getWidth();
        this.iHeight = dimension.height;
        this.iWidth = dimension.width;
    }        
    
    public void setDimension(java.awt.Dimension dimension) {
        this.dHeight = dimension.getHeight();
        this.dWidth = dimension.getWidth();
        this.iHeight = dimension.height;
        this.iWidth = dimension.width;
    }
    
    public java.awt.Dimension getDimension() {
        return new java.awt.Dimension(iWidth, iHeight);
    }
    
    public void setSize(int w, int h) {
        this.dHeight = h;
        this.dWidth = w;
        this.iHeight = h;
        this.iWidth = w;
    }
    
    public void setSize(double w, double h) {
        this.dHeight = h;
        this.dWidth = w;
        this.iHeight = new Double(h).intValue();
        this.iWidth = new Double(w).intValue();
    }       
    
    /** Getter for property dHeight.
     * @return Value of property dHeight.
     */
    public double getDHeight() {
        return dHeight;
    }    
    
    /** Setter for property dHeight.
     * @param dHeight New value of property dHeight.
     */
    public void setDHeight(double dHeight) {
        this.dHeight = dHeight;
        this.iHeight = new Double(dHeight).intValue();
    }    

    /** Getter for property dWidth.
     * @return Value of property dWidth.
     */
    public double getDWidth() {
        return dWidth;
    }
    
    /** Setter for property dWidth.
     * @param dWidth New value of property dWidth.
     */
    public void setDWidth(double dWidth) {
        this.dWidth = dWidth;
        this.iWidth = new Double(dWidth).intValue();
    }
    
    /** Getter for property iHeight.
     * @return Value of property iHeight.
     */
    public int getIHeight() {
        return iHeight;
    }
    
    /** Setter for property iHeight.
     * @param iHeight New value of property iHeight.
     */
    public void setIHeight(int iHeight) {
        this.iHeight = iHeight;
        this.dHeight = iHeight;
    }
    
    /** Getter for property iWidth.
     * @return Value of property iWidth.
     */
    public int getIWidth() {
        return iWidth;
    }
    
    /** Setter for property iWidth.
     * @param iWidth New value of property iWidth.
     */
    public void setIWidth(int iWidth) {
        this.iWidth = iWidth;
        this.dWidth = iWidth;
    }
    
}
