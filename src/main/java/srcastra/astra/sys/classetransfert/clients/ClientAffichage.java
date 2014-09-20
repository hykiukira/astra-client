/*
 * ClientAffichage.java
 *
 * Created on 7 novembre 2002, 15:44
 */

package srcastra.astra.sys.classetransfert.clients;
import srcastra.astra.gui.sys.utils.*;
import srcastra.astra.gui.test.*;
import srcastra.astra.sys.classetransfert.*;
/**
 *
 * @author  Thomas
 */
public class ClientAffichage implements java.io.Serializable,ListeObjectAffichage{
    
    /** Creates a new instance of ClientAffichage */
    public ClientAffichage(Clients_T parent,int index) {
        m_ref=new RowColor(parent.getCsreference(),index);
        m_nom=new RowColor(parent.getCsnom(),index);
        m_adresse=new RowColor(parent.getCsadresse(),index);
        m_cp=new RowColor(parent.getCodenom(),index);
        m_parent=parent;
    }
    
    public InterfaceClasseTransfert getParent() { 
        return m_parent;
    }
    
   public RowColor m_ref;
   public RowColor m_nom;
   public RowColor m_adresse;
   public RowColor m_cp;
   public Clients_T m_parent;
    
}
