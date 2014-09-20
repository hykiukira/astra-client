/*
 * StaticFields.java
 *
 * Created on 21 avril 2004, 10:21
 */

package srcastra.astra.gui.modules.printing;

/**
 *
 * @author  Administrateur
 */
public class StaticFields {
    
    /** Creates a new instance of StaticFields */
    public StaticFields() {
    }
    
    /** Getter for property bloqueD.
     * @return Value of property bloqueD.
     *
     */
    static public srcastra.astra.gui.modules.printing.MyRvfield getBloqueD() {
        return m_bloqueD;
    }
    
    /** Setter for property bloqueD.
     * @param bloqueD New value of property bloqueD.
     *
     */
    static public void setBloqueD(srcastra.astra.gui.modules.printing.MyRvfield bloqueD) {
        m_bloqueD = bloqueD;
    }
    
    /** Getter for property bloqueG.
     * @return Value of property bloqueG.
     *
     */
    static public srcastra.astra.gui.modules.printing.MyRvfield getBloqueG() {
        return m_bloqueG;
    }
    
    /** Setter for property bloqueG.
     * @param bloqueG New value of property bloqueG.
     *
     */
    static public void setBloqueG(srcastra.astra.gui.modules.printing.MyRvfield bloqueG) {
        m_bloqueG = bloqueG;
    }
    
    /** Getter for property bonCommande.
     * @return Value of property bonCommande.
     *
     */
    static public srcastra.astra.gui.modules.printing.MyRvfield getBonCommande() {
        return m_bonCommande;
    }
    
    /** Setter for property bonCommande.
     * @param bonCommande New value of property bonCommande.
     *
     */
    static public void setBonCommande(srcastra.astra.gui.modules.printing.MyRvfield bonCommande) {
        m_bonCommande = bonCommande;
    }
    
    /** Getter for property client.
     * @return Value of property client.
     *
     */
    static public srcastra.astra.gui.modules.printing.MyRvfield getClient() {
        return m_client;
    }
    
    /** Setter for property client.
     * @param client New value of property client.
     *
     */
    static public void setClient(srcastra.astra.gui.modules.printing.MyRvfield client) {
        m_client = client;
    }
    
    /** Getter for property corp.
     * @return Value of property corp.
     *
     */
    static public srcastra.astra.gui.modules.printing.MyRvfield getCorp() {
        return m_corp;
    }
    
    /** Setter for property corp.
     * @param corp New value of property corp.
     *
     */
    static public void setCorp(srcastra.astra.gui.modules.printing.MyRvfield corp) {
        m_corp = corp;
    }
    
 static private MyRvfield m_bonCommande;
 static private MyRvfield m_client;
 static private MyRvfield m_corp;
 static private MyRvfield m_bloqueG;
 static private MyRvfield m_bloqueD;
}
