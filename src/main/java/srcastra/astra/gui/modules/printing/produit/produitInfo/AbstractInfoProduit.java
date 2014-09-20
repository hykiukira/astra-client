/*
 * AbstractInfoProduit.java
 *
 * Created on 21 mars 2003, 11:02
 */

package srcastra.astra.gui.modules.printing.produit.produitInfo;
import java.sql.*;
/**
 *
 * @author  thomas
 */
public abstract class AbstractInfoProduit implements java.io.Serializable{
    protected int urcleunik;
    protected int lmcleunik;
    /** Creates a new instance of AbstractInfoProduit */
    public AbstractInfoProduit(int urcleunik,int lmcleunik) {
        this.urcleunik=urcleunik;
        this.lmcleunik=lmcleunik;
    }
     public abstract void initMe(Connection con) throws SQLException;
    
}
