/*



 * InterfaceAbstractPanel.java



 *



 * Created on 29 janvier 2003, 9:17



 */


package srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux;


/**
 * @author Thomas
 */


public interface InterfaceAbstractPanel {


    public void setSup_reduc(Object sup_reduc);


    public Object getSupReduc();


    public void reloadTableModel();


    public int doPrevious(boolean sw);


    public int doModify(boolean sw);


    public int doCancel(boolean sw);


    public int doAccept(boolean sw);


    public int doCreate(boolean sw);


    public void moveInTable(int direction);


    public java.awt.Component getTable();


}



