/*
 * DossierConfig.java
 *
 * Created on 6 novembre 2002, 16:01
 */

package srcastra.astra.sys.configuration.dossier;

/**
 *
 * @author  Thomas
 */
public class DossierConfig implements java.io.Serializable{
    
    /** Creates a new instance of DossierConfig */
    public DossierConfig(float pourcent,float frais_dossier) {
        m_pourcent_annulation=pourcent;
        m_frais_dossier=frais_dossier;
    }
    public float m_pourcent_annulation;
    public float m_frais_dossier;    
}
