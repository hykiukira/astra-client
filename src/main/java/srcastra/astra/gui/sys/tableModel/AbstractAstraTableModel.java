/*
 * AstraTableModel.java
 *
 * Created on 14 août 2002, 14:19
 */

package srcastra.astra.gui.sys.tableModel;

import srcastra.astra.gui.components.combobox.listSelector.AbstractListSelectorTableModel;
import srcastra.astra.sys.rmi.astrainterface;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import javax.swing.event.EventListenerList;
import java.util.ArrayList;

/**
 *
 * @author  Sébastien
 */
public abstract class AbstractAstraTableModel extends AbstractListSelectorTableModel {
    
    /** Creates a new instance of AstraTableModel */
    public AbstractAstraTableModel(astrainterface serveur, Loginusers_T currentUser) {
        super(null);
        this.serveur = serveur;
        this.currentUser = currentUser;
        m_listener = new EventListenerList();
    }
    
    abstract protected String[] loadColumnNames();
    abstract protected ArrayList loadData();
    abstract protected int[] getColumnMask();
    
    protected void dataNeeded() {
        //System.out.println("\n\n\n\n\n\n[DATA NEEDED] datacontainer : " + ((dataContainer == null) ? "la c'est null" : "pas null"));
        if (dataContainer == null) {
            fireDataContainerEvent(new DataContainerEvent(this, DataContainerEvent.DATASTATE_LOADING));
           // System.out.println("[DATA NEEDED] DATASTATE LOADING");
            dataContainer = loadData();
        }
        if (dataContainer != null) {
            if (dataContainer.size() > 0) {
                fireDataContainerEvent(new DataContainerEvent(this, DataContainerEvent.DATASTATE_FULL));
             //   System.out.println("[DATA NEEDED] DATASTATE FULL");
            }
            else {
                fireDataContainerEvent(new DataContainerEvent(this, DataContainerEvent.DATASTATE_EMPTY));
               // System.out.println("[DATA NEEDED] DATASTATE EMPTY");
            }
        }
       // System.out.println("[DATA NEEDED] fin => datacontainer contient " + dataContainer.size() + " éléments \n\n\n\n\n\n");
    }
    public void sortData(){
         if (dataContainer != null) {
            if (dataContainer.size() > 0) {
             //  Collections.sort(dataContainer,new ListeSelectorComparaTor(modelIndex,m_sortAsc));
             //   System.out.println("[DATA NEEDED] DATASTATE FULL");
            }
         }        
    }
    
    public void resetData() {
        dataContainer = null;
    }    
    
    public void addDataContainerListener(DataContainerListener listener) {
        m_listener.add(DataContainerListener.class, listener);
    }
    
    public void removeDataContainerListener(DataContainerListener listener) {
        m_listener.add(DataContainerListener.class, listener);
    }
    
    protected void fireDataContainerEvent(DataContainerEvent evt) {
        if (m_listener.getListenerCount() > 0) {
            Object[] listeners = m_listener.getListenerList();
            for (int i=0; i < listeners.length; i++) {
                if (DataContainerListener.class.isAssignableFrom(listeners[i].getClass())) {
                    ((DataContainerListener)listeners[i]).dataChanged(evt);
                }
            }
        }
        
    }
    
    
    protected astrainterface serveur;
    protected Loginusers_T currentUser;
    protected EventListenerList m_listener;
    
}
