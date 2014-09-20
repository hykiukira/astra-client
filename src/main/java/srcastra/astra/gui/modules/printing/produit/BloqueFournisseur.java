/*
 * BloqueFournisseur.java
 *
 * Created on 18 mars 2003, 17:06
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
import srcastra.astra.sys.classetransfert.dossier.brochure.Brochure_T;
import srcastra.astra.sys.classetransfert.dossier.*;
import srcastra.astra.gui.modules.printing.produit.produitInfo.*;
/**
 *
 * @author  Thomas
 */
public class BloqueFournisseur extends AbstractBloque{
    
    /** Creates a new instance of BloqueFournisseur */
   produit_T prod;
    public BloqueFournisseur (Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report,RArea parent,produit_T prod,java.awt.Frame frame) {
          super(obj,obj2,obj3,obj4,frame,report);    
          this.prod=prod;
          initField(1,1,1,"",fontPlain10);   
          initLabel(1,1,1,"",fontBold10);   
          genereArray();
         
    }
    
    protected void genereArray() {
        fieldArray=new ArrayList();
        if(prod instanceof Brochure_T){
            fieldArray.add(new MyRvfield[]{organisateur,fnom,fadresse,fcp,flocalite});
          // fieldArray.add(new MyRvfield[]{organisateur,fnom});
          // fieldArray.add(new MyRvfield[]{fadresse,fcp,flocalite});
        }
        else{
            fieldArray.add(new MyRvfield[]{fnom,fadresse,fcp,flocalite});
            //fieldArray.add(new MyRvfield[]{fadresse,fcp,flocalite});
        }
        
    }
    
    public java.util.ArrayList getFieldArray() {
        return fieldArray;
    }
    
    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font) {
        fref=new MyRvfield(fcase,color,prod.getFrreference(),igenerique,sgenerique,font);
        fnom=new MyRvfield(fcase,color,FormatTexte.format(prod.getFrnom()),igenerique,sgenerique,font);
        fadresse=new MyRvfield(fcase,color,FormatTexte.format(prod.getFradresse()),igenerique,sgenerique,font);
        fcp=new MyRvfield(fcase,color,genprint.getFounisseur().getCp(),igenerique,sgenerique,font);
        flocalite=new MyRvfield(fcase,color,FormatTexte.format(genprint.getFounisseur().getLocalité()),igenerique,sgenerique,font);
        email=new MyRvfield(fcase,color,prod.getFrrmail(),igenerique,sgenerique,font);
        fax=new MyRvfield(fcase,color,prod.getFrfax(),igenerique,sgenerique,font);
        telephone=new MyRvfield(fcase,color,prod.getFrtelephone(),igenerique,sgenerique,font);        
    }
    
    public void initFieldPosition(double x, double y) {
    }
    
    public void initFieldPosition(double x1, double x2, double x3, double x4, double x5, double x6, double y) {
    }
    
    /** Creates a new instance of AbstractBloque  */
    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font) {      
         organisateur=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("BL_FOUR_orga"),igenerique,sgenerique,font);
    }
    
    public void initLabelPosition(double x, double y) {
        //organisateur.x=x
    }
MyRvfield organisateur;
MyRvfield fref;
MyRvfield fnom;
MyRvfield fadresse;
MyRvfield fcp;
MyRvfield flocalite;
MyRvfield email;
MyRvfield telephone;
MyRvfield fax;
}
