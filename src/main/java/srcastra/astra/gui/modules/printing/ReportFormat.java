/*
 * ReportFormat.java
 *
 * Created on 14 mars 2003, 12:33
 */

package srcastra.astra.gui.modules.printing;
import com.java4less.rreport.*;
import srcastra.astra.gui.modules.printing.header.*;
import srcastra.astra.sys.Logger;
/**
 *
 * @author  Thomas
 */
public class ReportFormat {
    
    /** Creates a new instance of ReportFormat */
    RReport report;
    public ReportFormat(RReport report) {
        this.report=report;
    }
 private  String ajustSize(RField field){
        String text=(String)field.getruntimeValue();
        int size=text.length()+2;
        double compSize=size*0.215d;
        field.width=compSize;
        return text;       
    }
private  double calculPosition(String text,double x){
       int size=text.length();
       size=size+3;
       double offset=x+(size*0.215d);
       return offset;
    }
public double calculTotalWith(RField[] rfieldtab){
    double diff=0d;
    double size=0d;
   if(rfieldtab.length>0){
    for(int i= rfieldtab.length-1;i>-1;i--){
        if(i!=0){
       // Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"Valeur champ1 "+rfieldtab[i-1].getruntimeValue()+" lageur champ1 = "+rfieldtab[i-1].width+" champ1 x = "+rfieldtab[i-1].x+" champ2 x = "+rfieldtab[i].x+" lageur champ2= "+rfieldtab[i].width+" Valeur champ2 "+rfieldtab[i].getruntimeValue());
        diff=diff+(rfieldtab[i].x-(rfieldtab[i-1].x+rfieldtab[i-1].width));
        }
        size=size+ rfieldtab[i].width;   
      //  Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"champ1= "+rfieldtab[0].width);
        //Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"champ2= "+rfieldtab[1].width);
       // Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"dif= "+diff);
        
    }
   }
    double retval=diff+size;
    Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"Largeur total= "+retval);
    return retval;
    
}
public  void formatLigne(RField[] rfieldtab,double x,double y,AbstractBloque bloque){
       String text;
       double nextx=x;
       double base;
        if(rfieldtab.length>0){
            rfieldtab[0].x=x;
            rfieldtab[0].y=y;
           // bloque.x2=x+rfieldtab[0].width;
        for(int i=0;i<rfieldtab.length;i++){
            if(i!=rfieldtab.length-1){
            text=ajustSize(rfieldtab[i]);
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"champ x= "+rfieldtab[i].x+"  "+"champs width "+rfieldtab[i].width);
            nextx=rfieldtab[i].x+rfieldtab[i].width;
            rfieldtab[i+1].x=nextx;
            rfieldtab[i+1].y=y;
            ajustSize(rfieldtab[i+1]);
            base=calculTotalWith(rfieldtab);
            if(base>bloque.width)bloque.width=base;
            Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"bloquex2= "+bloque.width);
            }   
        }
    }
    }
public  void CalculWidth(RField[] rfieldtab,double x,double y,AbstractBloque bloque){
       String text;
       double nextx=x;
       double base=0;
        if(rfieldtab.length>0){
            rfieldtab[0].x=x;
            rfieldtab[0].y=y;
            text=ajustSize(rfieldtab[0]);
            bloque.width=x+rfieldtab[0].width;          
        for(int i=0;i<rfieldtab.length;i++){
            if(i!=rfieldtab.length-1){
                text=ajustSize(rfieldtab[i]);
                nextx=calculPosition(text,nextx);              
                xbiger=nextx+rfieldtab[i+1].width;
                if(bloque.width<xbiger)bloque.width=xbiger;
             //   Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"bloquex2= "+bloque.x2);
               // Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"xbiger= "+xbiger);
               
            }   
        }
    }
    }
public  void formatBloque(AbstractBloque bloque,double x,double y){
    java.util.ArrayList tabligne=bloque.getFieldArray();
    bloque.x1=x;
    bloque.y1=y;
    for(int i=0;i<tabligne.size();i++){
     RField[] tmp=(RField[])tabligne.get(i);
     formatLigne(tmp,x,y, bloque);
     // CalculWidth(tmp,x,y, bloque);
     bloque.y2=y;
     y=y+0.5d;
    }

}
double xbiger;
}
