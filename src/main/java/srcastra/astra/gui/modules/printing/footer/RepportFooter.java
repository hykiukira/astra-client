/*
 * PassagerEdition1.java
 *
 * Created on 17 mars 2003, 16:13
 */

package srcastra.astra.gui.modules.printing.footer;
import srcastra.astra.gui.modules.printing.*;
import com.java4less.rreport.*;
import srcastra.astra.gui.modules.printing.*;
import srcastra.astra.sys.Logger;
import java.util.*;
import srcastra.astra.gui.modules.printing.header.*;
import srcastra.astra.gui.modules.aidedesicion.*;
import srcastra.astra.sys.classetransfert.dossier.*;
/**
 *
 * @author  thomas
 */
public class RepportFooter extends AbstractHeader{
    
    /** Creates a new instance of PassagerEdition1 */
    AbstractBuffer buffer;
    java.awt.Frame main;
    public RepportFooter(Object obj, Object obj2, Object obj3, Object obj4, RReportJ2 report, double x, double y, ArrayList genprint,AbstractBuffer buffer,java.awt.Frame main,int facture)  {       
         super(obj,obj2,obj3,obj4);
         this.buffer=buffer;
      //   footerPrix=new FooterPrixBloque(obj,obj2,obj3,obj4,x,y,report,this,buffer); 
         footer1=new FooterBloque1(obj,obj2,obj3,obj4,x,y,report,this,main,buffer,facture); 
         footer1.ajustBloque();
         //footer1.
         footer1.initLabelPosition(0,0d); 
         addBloque(footer1); 
       /* cadre=new RRectangle();
         ligne=new RLine();
         cadre.y=y;
         cadre.x=0;
         cadre.height=0.5d;
         cadre.width=report.mPageWidthCM-(report.marginLeft+report.marginRight);
         add(cadre);
        //y=y+0.1d;
         entete=new PassagerBloque1(obj,obj2,obj3,obj4,x,y,report); 
         entete.ajustBloque(); 
         entete.initLabelPosition(x,y);
         y=y+0.6d;
         ligne.x=0;
         ligne.y=y;
         ligne.width=report.mPageWidthCM-(report.marginLeft+report.marginRight);
         add(ligne);
         addBloque(entete); 
         liste=new PassagerBloque2(obj,obj2,obj3,obj4,x,y,genprint);
         liste.ajustBloque();
         ArrayList tmp=entete.getFieldArray();
         MyRvfield[] tmptab=(MyRvfield[])tmp.get(0);
        // int size=tmptab.length;        
         liste.initFieldPosition(tmptab[0].x,tmptab[1].x,tmptab[2].x,tmptab[3].x,tmptab[4].x,tmptab[5].x,y);
         cadre.height=cadre.height+(liste.getFieldArray().size()*0.4d);
         Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"size of array="+liste.getFieldArray().size());
         addBloque(liste);*/
        
    }
    public void initRectangle(){
     
     
    }
    public void initBloqueGauche(Object obj, Object obj2, Object obj3, Object obj4) {
    }
AbstractBloque footer1;
AbstractBloque footer2;
AbstractBloque footerPrix;
//AbstractBloque liste;

//RRectangle cadre; 
//RLine ligne;
}
