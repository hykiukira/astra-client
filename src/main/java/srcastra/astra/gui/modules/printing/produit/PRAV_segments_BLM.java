/*
 * RHBloqueGauche.java
 *
 * Created on 14 mars 2003, 11:58
 */

package srcastra.astra.gui.modules.printing.produit;
import srcastra.astra.gui.modules.printing.*;
import srcastra.astra.gui.modules.printing.header.*;
import com.java4less.rreport.*;
import srcastra.astra.sys.Logger;
import java.util.*;
import srcastra.astra.sys.classetransfert.dossier.Passager_T;
import srcastra.astra.gui.modules.printing.classelangueuser.*;
import srcastra.astra.sys.classetransfert.utils.CalculDate;
import srcastra.astra.sys.classetransfert.dossier.hotel.*;
import srcastra.astra.gui.modules.printing.produit.produitInfo.*;
import srcastra.astra.sys.classetransfert.dossier.avion.*;
/**
 *
 * @author  Thomas
 */
public class PRAV_segments_BLM extends AbstractBloque{
    
    /** Creates a new instance of RHBloqueGauche */
      Avion_segment_T segment;
      public PRAV_segments_BLM(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report, RArea parent,Avion_segment_T segment,java.awt.Frame frame) {
        super(obj,obj2,obj3,obj4,frame,report);   
        this.segment=segment;
        initField(1,1,1,"",fontPlain10);   
        initLabel(1,1,1,"",fontBold10);    
        genereArray();
    }
    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){      
        fnumerovol=new MyRvfield(fcase,color,segment.getAs_numero_vol(),igenerique,sgenerique,font);
        froutinta=new MyRvfield(fcase,color,((SegmentInfo)segment.getProduitInfo()).getArrive(),igenerique,sgenerique,font);
        fheurearriv=new MyRvfield(fcase,color,segment.getAs_heure_arrive(),igenerique,sgenerique,font);
        //ffarebasis=new MyRvfield(fcase,color,"  ",igenerique,sgenerique,font);  
        //agfax=new MyRvfield(fcase,color,entite.getEefax(),igenerique,sgenerique,font);    
        // agtel=new MyRvfield(fcase,color,entite.getEetelephones(),igenerique,sgenerique,font);     
        // agmail=new MyRvfield(fcase,color,entite.getEemail(),igenerique,sgenerique,font);      
        // agtva=new MyRvfield(fcase,color,entite.getEetva(),igenerique,sgenerique,font);     
        // agcbc=new MyRvfield(fcase,color,entite.getEecomptebancaire(),igenerique,sgenerique,font);     
    }
    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){
         numerovol=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_AV_numvol"),igenerique,sgenerique,font);
         routinta=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_AV_arriv"),igenerique,sgenerique,font);     
         heurearriv=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_AV_heurearriv"),igenerique,sgenerique,font);      
         //farebasis=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_AV_farebasis"),igenerique,sgenerique,font);      //debarq=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_deb"),igenerique,sgenerique,font);     
         //tva=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BG_tva"),igenerique,sgenerique,font);      
         //cbc=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BG_cbc"),igenerique,sgenerique,font);     
    }
    protected void genereArray(){
        fieldArray=new ArrayList();
        fieldArray.add(new MyRvfield[]{numerovol,fnumerovol});
        fieldArray.add(new MyRvfield[]{routinta,froutinta});
        fieldArray.add(new MyRvfield[]{heurearriv,fheurearriv});
        //fieldArray.add(new MyRvfield[]{farebasis,ffarebasis});
     //   fieldArray.add(new MyRvfield[]{ fax,agfax});
       // fieldArray.add(new MyRvfield[]{ mail,agmail});
       // fieldArray.add(new MyRvfield[]{ tva,agtva});
       // fieldArray.add(new MyRvfield[]{ cbc,agcbc});
    }
    
    /** Getter for property fieldArray.
     * @return Value of property fieldArray.
     */
    public java.util.ArrayList getFieldArray() {
        return fieldArray;
    }
    
    /** Setter for property fieldArray.
     * @param fieldArray New value of property fieldArray.
     */
    public void setFieldArray(java.util.ArrayList fieldArray) {
        this.fieldArray = fieldArray;
    }
    
    public void initFieldPosition(double x, double y) {
    }
    
    public void initLabelPosition(double x, double y) {
    }
    
    public void initFieldPosition(double x1, double x2, double x3, double x4, double x5, double x6, double y) {
    }
    
 MyRvfield numerobillet;
 MyRvfield numerovol;
 MyRvfield compagnie;
 MyRvfield routingde;
 MyRvfield routinta; 
 MyRvfield datedep;
 MyRvfield heuredep;
 MyRvfield heurearriv;
 MyRvfield classe;
 MyRvfield farebasis;
 MyRvfield fnumerobillet;
 MyRvfield fnumerovol;
 MyRvfield fcompagnie;
 MyRvfield froutingde;
 MyRvfield froutinta; 
 MyRvfield fdatedep;
 MyRvfield fheuredep;
 MyRvfield fheurearriv;
 MyRvfield fclasse;
 MyRvfield ffarebasis;
// MyRvfield fdeba;
}
