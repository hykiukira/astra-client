/*
 * PassagerBloque.java
 *
 * Created on 17 mars 2003, 16:17
 */

package srcastra.astra.gui.modules.printing.tva;
import srcastra.astra.gui.modules.printing.*;
import srcastra.astra.gui.modules.printing.header.*;
import com.java4less.rreport.*;
import srcastra.astra.sys.Logger;
import java.util.*;
import srcastra.astra.sys.classetransfert.dossier.Passager_T;
import srcastra.astra.gui.modules.printing.classelangueuser.*;

/**
 *
 * @author  thomas
 */
public class TvaTitre extends AbstractBloque{
    
    /** Creates a new instance of PassagerBloque */
    RReport report;
    public TvaTitre(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report, java.awt.Frame frame) {
          super(obj,obj2,obj3,obj4,frame,report);      
          this.report=report;
          initLabel(1,1,1,"",fontBold10);  
          genereArray();
    }
    
    public java.util.ArrayList getFieldArray() {
        return fieldArray;
    }
    
    /** Creates a new instance of AbstractBloque  */
    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font) {
         taux=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("tva_taux"),igenerique,sgenerique,font);
         base=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("tva_base"),igenerique,sgenerique,font);     
         tva=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("tva_montant"),igenerique,sgenerique,font);      
         total=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("tva_total"),igenerique,sgenerique,font);     
         //cp=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_cp"),igenerique,sgenerique,font);     
         //localite=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_loc"),igenerique,sgenerique,font);     
         
    }
    
    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font,ArrayList genprint) {
       /* if(dossier.getPassager()!=null){
        for(int i=0;i<dossier.getPassager().size();i++){
            Passager_T pass=(Passager_T)dossier.getPassager().get(i);     v 
            GeneralePrinting gen=(GeneralePrinting)genprint.get(i);
            MyRvfield[] myfields=new MyRvfield[6];
            myfields[0]=new MyRvfield(fcase,color, gen.getPassager().getTitre(),igenerique,sgenerique,font);
            myfields[1]=new MyRvfield(fcase,color, pass.getPr_nom(),igenerique,sgenerique,font);
            myfields[2]=new MyRvfield(fcase,color, pass.getPr_prénom(),igenerique,sgenerique,font);
            myfields[3]=new MyRvfield(fcase,color, pass.getPr_adrese(),igenerique,sgenerique,font);
            myfields[4]=new MyRvfield(fcase,color, pass.getCodenom(),igenerique,sgenerique,font);
            myfields[5]=new MyRvfield(fcase,color, gen.getPassager().getLocalité(),igenerique,sgenerique,font);
            myfields[6]=new MyRvfield(fcase,color, pass.getPr_datenaissance().toString2(),igenerique,sgenerique,font);
            myfields[7]=new MyRvfield(fcase,color, gen.getPassager().getNationalité(),igenerique,sgenerique,font);
            passager.add(myfields);
        }
       }*/
       //tel=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_tel"),igenerique,sgenerique,font);      
    }
      protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){
          
      }
       public void initFieldPosition(double x1,double x2,double x3,double x4,double x5,double x6,double y){
  
        
    }
    public void initFieldPosition(double x,double y){
     /*   double y2=y;
        for(int i=0;i<dossier.getPassager().size();i++){              
            MyRvfield[] myfields=new MyRvfield[6];
            myfields[0].x=voyageur.x;
            myfields[0].y=y2;
            myfields[1].x=myfields[0].x+myfields[0].width;
            myfields[1].y=y2;
            myfields[2].x=myfields[1].x+myfields[1].width;
            myfields[2].y=y2;
            myfields[3].x=adresse.x;
            myfields[3].y=y2;
            myfields[4].x=cp.x;
            myfields[4].y=y;
            myfields[5].x=localite.x;
            myfields[5].y=y;
            myfields[6].x=datenai.x;
            myfields[6].y=y;
            myfields[7].x=nat.x;
            myfields[7].y=y;
            passager.add(myfields);
        }*/
        
        
    }
    public void initLabelPosition(double x,double y){
        x1=x;
        y1=y;
        taux.y=y;
        base.y=y;
        tva.y=y;
        total.y=y;
        taux.x=x;
        base.x=taux.x+taux.width+1.5d;
        tva.x=base.x+base.width+1.5d;
        total.x=tva.x+tva.width +1.5d;               
      //  nat.x=report.mPageWidthCM-(nat.width+report.marginLeft+report.marginRight); 
       // datenai.x=nat.x-0.5d-datenai.width;
        x2=total.x+total.width;
    }
    
    protected void genereArray() {
        fieldArray=new ArrayList();
        fieldArray.add(new MyRvfield[]{taux,base,tva,total});
        //fieldArray.add(new MyRvfield[]{ clientadresse});
        //fieldArray.add(new MyRvfield[]{clientcp,clientlocalite});
    }
    
 MyRvfield taux;
 MyRvfield base;
 MyRvfield tva;
 MyRvfield total;
}
