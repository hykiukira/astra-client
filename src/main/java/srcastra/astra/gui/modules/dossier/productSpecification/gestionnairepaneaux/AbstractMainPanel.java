/*




 * AbstractMainPanel.java




 *




 * Created on 8 januari 2003, 9:12




 */


package srcastra.astra.gui.modules.dossier.productSpecification.gestionnairepaneaux;


import srcastra.astra.gui.modules.dossier.productSpecification.*;


import srcastra.astra.gui.modules.dossier.*;


import srcastra.astra.gui.modules.mainScreenModule.DossierMainScreenModule;


/**
 * @author Thomas
 */


public abstract class AbstractMainPanel {


    /**
     * Creates a new instance of AbstractMainPanel
     */


    public AbstractMainPanel(DossierMainScreenModule parent, DossierProduitPane pane_produit) {


        this.m_parent = parent;


        this.m_produitpane = pane_produit;


    }


    public abstract String getTitle();


    public final static String DESC = "desc";


    public final static String DESC_EDITION = "descEdiction";


    public final static String PRODUIT = "pro";


    public final static String SUP_REDUC = "supreduc";


    public final static String SUP_REDUC_EDITION = "supreducedition";


    public final static String MEMO = "memo";

    // public final static String SEGMENT="segment";

    // public final static String SEGMENT_EDITION="segmentedition";


    protected DossierMainScreenModule m_parent;


    protected DossierProduitPane m_produitpane;


    protected int mode;


}




