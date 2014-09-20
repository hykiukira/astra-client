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
import srcastra.astra.sys.classetransfert.dossier.brochure.*;
import srcastra.astra.gui.modules.aidedesicion.*;
/**
 *
 * @author  Thomas
 */
public class PRBloquelogement extends AbstractBloque{
    
    /** Creates a new instance of RHBloqueGauche */
    DescriptionLogement_T logementDesc;
    AbstractBuffer buffer;
    public PRBloquelogement(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report,RArea parent,DescriptionLogement_T logement,AbstractBuffer buffer,java.awt.Frame frame) {
        super(obj,obj2,obj3,obj4,frame,report); 
        this.buffer=buffer;
        this.logementDesc=logement;
        this.logementDesc=logementDesc;
        initField(1,1,1,"",fontPlain10);   
        initLabel(1,1,1,"",fontBold10);   
        genereArray();
    }
    protected void initField(int fcase, int color, int igenerique, String sgenerique,java.awt.Font font){    
        Object tmp= getBufferData.getElement(buffer,1,logementDesc.getDlt_xlit(),currentUser.getUrlmcleunik());
        if(tmp==null) tmp="null";//getBufferData.getElement(buffer,1,logementDesc.getDlt_xlit(),currentUser.getUrlmcleunik())!=null)
            lit=new MyRvfield(fcase,color,tmp.toString(),igenerique,sgenerique,font);
        tmp= getBufferData.getElement(buffer,3,logementDesc.getDlt_regime(),currentUser.getUrlmcleunik());
        if(tmp==null) tmp="null";
            regime=new MyRvfield(fcase,color,tmp.toString(),igenerique,sgenerique,font);
            quantite=new MyRvfield(fcase,color,""+logementDesc.getDlt_quantité()+" x ",igenerique,sgenerique,font);
        tmp= getBufferData.getElement(buffer,8,logementDesc.getDlt_situation(),currentUser.getUrlmcleunik());
        if(tmp==null) tmp="null";
         situtation=new MyRvfield(fcase,color,tmp.toString(),igenerique,sgenerique,font);  
         tmp= getBufferData.getElement(buffer,2,logementDesc.getDlt_commodite(),currentUser.getUrlmcleunik());
        if(tmp==null) tmp="null";
         commod=new MyRvfield(fcase,color,tmp.toString(),igenerique,sgenerique,font);  
        tmp= getBufferData.getElement(buffer,5,logementDesc.getDlt_vue(),currentUser.getUrlmcleunik());
        if(tmp==null) tmp="null";
         vue=new MyRvfield(fcase,color,tmp.toString(),igenerique,sgenerique,font);
         String codes="";
         if(logementDesc.getCode()!=null)
             codes=logementDesc.getCode();
         code=new MyRvfield(fcase,color,codes,igenerique,sgenerique,font);
    }
    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){
         logement=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_log2"),igenerique,sgenerique,font);
        /*// date=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_dateDep"),igenerique,sgenerique,font);     
         agent=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_agent"),igenerique,sgenerique,font);      
         statut=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_statut"),igenerique,sgenerique,font);     
         tel=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_tel"),igenerique,sgenerique,font);      
    */}
    protected void genereArray(){
        fieldArray=new ArrayList(); 
       // fieldArray.add(new MyRvfield[]{logement});
        fieldArray.add(new MyRvfield[]{quantite,lit,commod,situtation,vue,regime,code});
       /* fieldArray.add(new MyRvfield[]{ datedep,fdatedep});
        fieldArray.add(new MyRvfield[]{agent,fagent});
        fieldArray.add(new MyRvfield[]{statut,fstatut});
        fieldArray.add(new MyRvfield[]{ tel,ftel});*/
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
        fieldArray = fieldArray;
    }
    
    public void initFieldPosition(double x, double y) {
    }
    
    public void initLabelPosition(double x, double y) {
    }
    
    public void initFieldPosition(double x1, double x2, double x3, double x4, double x5, double x6, double y) {
    }
    
 MyRvfield regime;
 MyRvfield quantite;
 MyRvfield lit;
 MyRvfield commod;
 MyRvfield situtation;
 MyRvfield vue;
 MyRvfield logement;
 MyRvfield code;
// MyRvfield vue;
// MyRvfield tel;
// MyRvfield ftel;
// MyRvfield agent;
// MyRvfield fagent;

}
