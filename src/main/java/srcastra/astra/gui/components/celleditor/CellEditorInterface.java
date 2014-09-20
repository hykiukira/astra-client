/*

 * CellEditorInterface.java

 *

 * Created on 24 juillet 2003, 15:00

 */


package srcastra.astra.gui.components.celleditor;


/**
 * @author thomas
 */

public interface CellEditorInterface {

    public java.awt.Component getComponent();

    public int getPosition();

    public Object getCellEditorValue();

    public void reset();

    public boolean isTva();

    public boolean isListe();

    public boolean isMontant();

    public void interTable(int row);


}

