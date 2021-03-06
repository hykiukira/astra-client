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
import srcastra.astra.gui.modules.aidedesicion.*;
/**
 *
 * @author  thomas
 */
public class FooterBloque1 extends AbstractBloque{
    
    /** Creates a new instance of PassagerBloque */
    RReport report;
    RArea parent;
    AbstractBuffer buffer;
    int facture;
    public FooterBloque1(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report,RArea parent,java.awt.Frame frame,AbstractBuffer buffer,int facture) {
          super(obj,obj2,obj3,obj4,frame,report);      
          this.report=report;
          this.parent=parent;
          this.buffer=buffer;
          this.facture=facture;
          initLabel(1,1,1,"",fontBold10); 
          initField(1,1,1,"",fontBold10); 
          genereArray();
    }
    
    public java.util.ArrayList getFieldArray() {
        return fieldArray;
    }
    
    /** Creates a new instance of AbstractBloque  */
    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font) {
         agent=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RF_B1_agent"),igenerique,sgenerique,font);
         signcli=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RF_B1_signcli"),igenerique,sgenerique,font);     
      //   datenai=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_datenai"),igenerique,sgenerique,font);      
        // nat=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_nat"),igenerique,sgenerique,font);     
         //cp=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_cp"),igenerique,sgenerique,font);     
         //localite=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_loc"),igenerique,sgenerique,font);     
         
    }
    
    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font,ArrayList array) {
     
        /* if(dossier.getPassager()!=null){
        for(int i=0;i<dossier.getPassager().size();i++){
            Passager_T pass=(Passager_T)dossier.getPassager().get(i);     v 
            GeneralePrinting gen=(GeneralePrinting)genprint.get(i);
            MyRvfield[] myfields=new MyRvfield[6];
            myfields[0]=new MyRvfield(fcase,color, gen.getPassager().getTitre(),igenerique,sgenerique,font);
            myfields[1]=new MyRvfield(fcase,color, pass.getPr_nom(),igenerique,sgenerique,font);
            myfields[2]=new MyRvfield(fcase,color, pass.getPr_pr�nom(),igenerique,sgenerique,font);
            myfields[3]=new MyRvfield(fcase,color, pass.getPr_adrese(),igenerique,sgenerique,font);
            myfields[4]=new MyRvfield(fcase,color, pass.getCodenom(),igenerique,sgenerique,font);
            myfields[5]=new MyRvfield(fcase,color, gen.getPassager().getLocalit�(),igenerique,sgenerique,font);
            myfields[6]=new MyRvfield(fcase,color, pass.getPr_datenaissance().toString2(),igenerique,sgenerique,font);
            myfields[7]=new MyRvfield(fcase,color, gen.getPassager().getNationalit�(),igenerique,sgenerique,font);
            passager.add(myfields);
        }
       }*/
       //tel=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_tel"),igenerique,sgenerique,font);      
    }
      protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){
        Object tmp= getBufferData.getElement(buffer,1,2,currentUser.getUrlmcleunik());
        if(facture==0)
            tmp= getBufferData.getElement(buffer,1,2,currentUser.getUrlmcleunik());
        else
            tmp= getBufferData.getElement(buffer,2,3,currentUser.getUrlmcleunik());
        String message="";
        if(tmp!=null){
            message=tmp.toString();
        }
        if(!message.equals("")){
            texteLegal=new MyRvfield(fcase,color,message,igenerique,sgenerique,fontPlain8);
            double size=report.mPageWidthCM -(report.marginLeft + report.marginRight);
            texteLegal.width=report.mPageWidthCM+5;
       // texteLegal.multiLine=true;
            texteLegal.Expand=true;
            texteLegal.ajust=false;       
        }
     //   Logger.getDefaultLogger().log(Logger.LOG_INFOS,"taille de la frame :"+size);//-(report.marginLeft+report.marginRight);
       // texteLegal.setruntimeValue("Quelques minutes avant le d�but des consultations � huis clos du Conseil de s�curit� sur l'Irak, lundi apr�s-midi 17 mars, les ambassadeurs am�ricain, britannique et espagnol � l'ONU ont annonc� que Washington, Londres et Madrid avaient retir� leur projet de deuxi�me r�solution qui visait � autoriser le recours � la force contre l'Irak. Pour expliquer ce retrait, l'ambassadeur britannique aux Nations unies, Jeremy Greenstock, a expliqu� � la presse que le consensus au Conseil n'�tait pas possible");
        lieu=new MyRvfield(fcase,color,genprint.getAgence().getLocalit�(),igenerique,sgenerique,font);
        date=new MyRvfield(fcase,color,CalculDate.getTodayDate().toString2(),igenerique,sgenerique,font);
          
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
        if(texteLegal!=null){
        texteLegal.x=x;
        texteLegal.y=y;
        lieu.x=x;
        y=texteLegal.y+2.5d;
        lieu.y=y;
        date.x=lieu.x+lieu.width+1d;
        date.y=y;
        signcli.x=date.x+date.width+2d;
        signcli.y=y;
        agent.x=signcli.x+signcli.width+2d;
        agent.y=y;
        }
        else{
            lieu.x=x;
          //  y=y+2.5d;
            lieu.y=y;
            date.x=lieu.x+lieu.width+1d;
            date.y=y;
            signcli.x=date.x+date.width+2d;
            signcli.y=y;
            agent.x=signcli.x+signcli.width+2d;
            agent.y=y;        
        }
    /*   x1=x;
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
    
    protected void genereArray() {
        fieldArray=new ArrayList();
        fieldArray.add(new MyRvfield[]{texteLegal});
        if(facture==0)
            fieldArray.add(new MyRvfield[]{lieu,date,signcli,agent});
        //fieldArray.add(new MyRvfield[]{ clientadresse});
        //fieldArray.add(new MyRvfield[]{clientcp,clientlocalite});
    }
    
    /** Getter for property texteLegal.
     * @return Value of property texteLegal.
     */
    public srcastra.astra.gui.modules.printing.MyRvfield getTexteLegal() {
        return texteLegal;
    }
    
    /** Setter for property texteLegal.
     * @param texteLegal New value of property texteLegal.
     */
    public void setTexteLegal(srcastra.astra.gui.modules.printing.MyRvfield texteLegal) {
        this.texteLegal = texteLegal;
    }
    
 MyRvfield texteLegal;
 MyRvfield lieu;
 MyRvfield date;
 MyRvfield agent; 
 MyRvfield signcli; 
}
