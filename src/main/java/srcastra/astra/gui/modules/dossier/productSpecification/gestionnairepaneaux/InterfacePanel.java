/*



 * InterfacePanel.java



 *



 * Created on 29 janvier 2003, 8:48



 */


package srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux;


/**
 * @author Thomas
 */


public interface InterfacePanel {


    public int doAccept(boolean sw);


    public int doCancel(boolean sw);


    public int doCreate(boolean sw);


    public int doModify(boolean sw);


    public int doPrevious(boolean sw);


    public Object getSupReduc2(int i);


    public java.awt.Component getTable();


    public void moveInTable(int direction);


    public void reloadTableModel();


    public void setSup_reduc(Object sup_reduc);


    public int doDelete(boolean sw);


}



