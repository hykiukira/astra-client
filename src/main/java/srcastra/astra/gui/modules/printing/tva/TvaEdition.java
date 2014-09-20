/*

 * PassagerEdition1.java

 *

 * Created on 17 mars 2003, 16:13

 */



package srcastra.astra.gui.modules.printing.tva;

import srcastra.astra.gui.modules.printing.*;

import com.java4less.rreport.*;

import srcastra.astra.gui.modules.printing.*;

import srcastra.astra.sys.Logger;

import java.util.*;

import srcastra.astra.gui.modules.printing.header.*;

/**

 *

 * @author  thomas

 */

public class TvaEdition extends AbstractHeader{

    

    /** Creates a new instance of PassagerEdition1 */

    java.awt.Frame main;

    public TvaEdition(Object obj, Object obj2, Object obj3, Object obj4, RReportJ2 report, double x, double y, ArrayList genprint, java.awt.Frame main,Hashtable hash)  {       

         super(obj,obj2,obj3,obj4);

         cadre=new RRectangle();

         ligne=new RLine();

         y1=0;
         y=y+0.5d;

         cadre.y=y;

         cadre.x=0;

         cadre.height=0.5d;

         cadre.width=report.mPageWidthCM-(report.marginLeft+report.marginRight);

         add(cadre);

        //y=y+0.1d;

         entete=new TvaTitre(obj,obj2,obj3,obj4,x,y,report,main); 

         entete.ajustBloque(); 

         entete.initLabelPosition(7.5,y);

         y=y+1;//0.6d;

         ligne.x=0;

         ligne.y=y;

         ligne.width=report.mPageWidthCM-(report.marginLeft+report.marginRight);

         add(ligne);

         addBloque(entete); 

         Logger.getDefaultLogger().log(Logger.LOG_WARNING,"[++++++++++++++++++]initialisation du bloque passager");

         liste=new TvaDetail(obj,obj2,obj3,obj4,x,y,genprint,main,report,hash);

         liste.ajustBloque();

         liste.calculWidth();

         ArrayList tmp=entete.getFieldArray();

         MyRvfield[] tmptab=(MyRvfield[])tmp.get(0);

        // int size=tmptab.length;        

         liste.initFieldPosition(tmptab[0].x+tmptab[0].width,tmptab[1].x+tmptab[1].width,tmptab[2].x+tmptab[2].width,tmptab[3].x+tmptab[3].width,0,0,y);

         cadre.height=cadre.height+(liste.getFieldArray().size()*1d);//0.5d);

         addBloque(liste);

         tvatotal=new TvaDetailTotal(obj,obj2,obj3,obj4,x,y,genprint,main,report,hash);

         tvatotal.ajustBloque();

         tvatotal.calculWidth();

         tvatotal.initFieldPosition(tmptab[0].x+tmptab[0].width,tmptab[1].x+tmptab[1].width,tmptab[2].x+tmptab[2].width,tmptab[3].x+tmptab[3].width,0,0,cadre.y+cadre.height+0.1d);

         addBloque(tvatotal);

         y2=cadre.y+cadre.height+tvatotal.heigth;  

         //cadre.height=liste.y2;

    }

    public void initRectangle(){

     

     

    }

    public void initBloqueGauche(Object obj, Object obj2, Object obj3, Object obj4) {

    }

AbstractBloque entete;

AbstractBloque liste;

AbstractBloque tvatotal;



RRectangle cadre; 

RLine ligne;

}

