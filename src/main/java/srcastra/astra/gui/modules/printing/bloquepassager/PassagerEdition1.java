/*

 * PassagerEdition1.java

 *

 * Created on 17 mars 2003, 16:13

 */
package srcastra.astra.gui.modules.printing.bloquepassager;

import srcastra.astra.gui.modules.printing.*;
import com.java4less.rreport.*;
import srcastra.astra.gui.modules.printing.*;
import srcastra.astra.sys.Logger;

import java.util.*;

import srcastra.astra.gui.modules.printing.header.*;

/**
 * @author thomas
 */
public class PassagerEdition1 extends AbstractHeader {
    /**
     * Creates a new instance of PassagerEdition1
     */
    java.awt.Frame main;

    public PassagerEdition1(Object obj, Object obj2, Object obj3, Object obj4, RReportJ2 report, double x, double y, ArrayList genprint, java.awt.Frame main) {
        super(obj, obj2, obj3, obj4);
        cadre = new RRectangle();
        ligne = new RLine();
        y1 = 0;
        cadre.y = y;
        cadre.x = 0;
        cadre.height = 0.5d;
        cadre.width = report.mPageWidthCM - (report.marginLeft + report.marginRight);
        add(cadre);
        //y=y+0.1d;
        entete = new PassagerBloque1(obj, obj2, obj3, obj4, x, y, report, main);
        entete.ajustBloque();
        entete.initLabelPosition(x, y);
        y = y + 0.6d;
        ligne.x = 0;
        ligne.y = y;
        ligne.width = report.mPageWidthCM - (report.marginLeft + report.marginRight);
        add(ligne);
        addBloque(entete);
        Logger.getDefaultLogger().log(Logger.LOG_WARNING, "[++++++++++++++++++]initialisation du bloque passager");
        liste = new PassagerBloque2(obj, obj2, obj3, obj4, x, y, genprint, main, report);
        liste.ajustBloque();
        liste.calculWidth();
        ArrayList tmp = entete.getFieldArray();
        MyRvfield[] tmptab = (MyRvfield[]) tmp.get(0);
        // int size=tmptab.length;
        liste.initFieldPosition(tmptab[0].x, 0, 0, 0, 0, 0, y);//tmptab[1].x,tmptab[2].x,tmptab[3].x,0,0,y);
        //cadre.height=cadre.height+(liste.getFieldArray().size()*0.5d);
        cadre.height = cadre.height + liste.y2;
        addBloque(liste);
        y2 = cadre.y + cadre.height;
        this.height = cadre.height;
        //cadre.height=liste.y2;
    }

    public void initRectangle() {
    }

    public void initBloqueGauche(Object obj, Object obj2, Object obj3, Object obj4) {
    }

    AbstractBloque entete;
    AbstractBloque liste;
    RRectangle cadre;
    RLine ligne;

}

