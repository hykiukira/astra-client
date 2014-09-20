/*
 * PassagerBloque.java
 *
 * Created on 17 mars 2003, 16:17
 */

package srcastra.astra.gui.modules.printing.footer;
import srcastra.astra.gui.modules.printing.*;
import srcastra.astra.gui.modules.printing.header.*;
import com.java4less.rreport.*;
import srcastra.astra.sys.Logger;
import java.util.*;
import srcastra.astra.sys.classetransfert.dossier.Passager_T;
import srcastra.astra.gui.modules.printing.classelangueuser.*;
import srcastra.astra.sys.classetransfert.utils.CalculDate;
import srcastra.astra.sys.classetransfert.dossier.produit_T;
import srcastra.astra.gui.modules.aidedesicion.*;

/**
 *
 * @author  thomas
 */
public class FooterPrixBloque extends AbstractBloque{
    
    /** Creates a new instance of PassagerBloque */
    RReport report;
    RArea parent;
    produit_T prod;
    AbstractBuffer buffer;
    public FooterPrixBloque(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report, RArea parent,AbstractBuffer buffer,java.awt.Frame frame){
          super(obj,obj2,obj3,obj4,frame,report);      
          this.report=report;
          this.parent=parent;
         // this.prod=produit;
          this.buffer=buffer;
          initLabel(1,1,1,"",fontBold10); 
          initField(1,1,1,"",fontBold10); 
          genereArray();
    }   
    public java.util.ArrayList getFieldArray() {
        return fieldArray;
    }
     
    /** Creates a new instance of AbstractBloque  */
    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font) {       
      }
    
    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font,ArrayList array) {
     } 
      protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){
        produit=new MyRvfield(fcase,color,prod.getProduitnom(),igenerique,sgenerique,fontPlain8);
        produitLibelle=new MyRvfield(fcase,color,prod.getLibelleCompta(),igenerique,sgenerique,fontPlain8);
        prixu=new MyRvfield(fcase,color,""+prod.getAt_val_vente(),igenerique,sgenerique,fontPlain8);
        prct=new MyRvfield(fcase,color,""+prod.getPrct(),igenerique,sgenerique,fontPlain8);
        pax=new MyRvfield(fcase,color,""+prod.getPax(),igenerique,sgenerique,fontPlain8);
        qua=new MyRvfield(fcase,color,""+prod.getPrct(),igenerique,sgenerique,fontPlain8);
        prixtot=new MyRvfield(fcase,color,""+prod.getValeur_tot_tva_inc(),igenerique,sgenerique,fontPlain8);     
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
      /*  texteLegal.x=x;
        texteLegal.y=y;
        lieu.x=x;
        y=texteLegal.y+2d;
        lieu.y=y;
        date.x=lieu.x+lieu.width+1d;
        date.y=y;
        signcli.x=date.x+date.width+2d;
        signcli.y=y;
        agent.x=signcli.x+signcli.width+2d;
        agent.y=y;
       x1=x;
        y1=y;
        voyageur.y=y;
        adresse.y=y;
        datenai.y=y;
        nat.y=y;
        voyageur.x=x;
        cp.y=y;
        localite.y=y;
        adresse.x=voyageur.x+voyageur.width+1.5d;
        cp.x=adresse.x+adresse.width+5d;
        localite.x=cp.x+cp.width +0.5d;               
        nat.x=report.mPageWidthCM-(nat.width+report.marginLeft+report.marginRight); 
        datenai.x=nat.x-0.5d-datenai.width;
        x2=nat.x+nat.width;*/
    }
    
    protected void genereArray(){
        fieldArray=new ArrayList();
        fieldArray.add(new MyRvfield[]{produit,produitLibelle,prixu,prct,pax,qua,prixtot});
        //fieldArray.add(new MyRvfield[]{ clientadresse});
        //fieldArray.add(new MyRvfield[]{clientcp,clientlocalite});
    }
    
    /** Getter for property texteLegal.
     * @return Value of property texteLegal.
     */
    public srcastra.astra.gui.modules.printing.MyRvfield getTexteLegal() {
        return null;//texteLegal;
    }
    
    /** Setter for property texteLegal.
     * @param texteLegal New value of property texteLegal.
     */
    public void setTexteLegal(srcastra.astra.gui.modules.printing.MyRvfield texteLegal) {
      //  this.texteLegal = texteLegal;
    }
    
 MyRvfield produit;
 MyRvfield produitLibelle;
 MyRvfield prixu;
 MyRvfield prct;
 MyRvfield pax;
 MyRvfield qua;
 MyRvfield prixtot;
}
