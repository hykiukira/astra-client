/*

 * BrochureEdition.java

 *

 * Created on 18 mars 2003, 16:21

 */



package srcastra.astra.gui.modules.printing.prix;

import srcastra.astra.gui.modules.printing.header.*;

import srcastra.astra.gui.modules.printing.*;

import srcastra.astra.gui.modules.printing.header.*;

import com.java4less.rreport.*;

import srcastra.astra.sys.Logger;

import java.util.*;

import srcastra.astra.sys.classetransfert.dossier.Passager_T;

import srcastra.astra.gui.modules.printing.classelangueuser.*;

import srcastra.astra.sys.classetransfert.utils.CalculDate;

import srcastra.astra.sys.classetransfert.dossier.brochure.*;

import srcastra.astra.sys.classetransfert.dossier.produit_T;

import srcastra.astra.sys.classetransfert.dossier.avion.Avion_ticket_T;

import srcastra.astra.gui.modules.aidedesicion.*;

import srcastra.astra.sys.classetransfert.dossier.avion.*;

import srcastra.astra.sys.rmi.DossierRmiInterface;

import srcastra.astra.gui.modules.printing.produit.produitInfo.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.gui.sys.*;

import srcastra.astra.sys.classetransfert.dossier.assurance.*;

import srcastra.astra.gui.modules.printing.produit.*;
import srcastra.astra.gui.modules.printing.*;



/**

 *

 * @author  Thomas

 */

public class PrixEdition extends AbstractProduitEdition{

    

    /** Creates a new instance of BrochureEdition */

    RReportJ2 report;

    AbstractBuffer buffer;

    DossierRmiInterface dossin;

    Assurance_T assurance; 

    ArrayList array;

    int facture;

    double position=0;

      java.awt.Frame main;

     public PrixEdition(Object obj, Object obj2, Object obj3, RReportJ2 report, AbstractBuffer buffer, DossierRmiInterface dossin,  java.awt.Frame main,int facture)  {       

        super(obj,obj2,obj3,AbstractProduitEdition.SYNTH,report,""); 

      //  mainrectangle.y=0;

       // titre.y=0;

        this.facture=facture;

        this.report=report; 

        this.buffer=buffer;

        this.dossin=dossin;

        position=mainrectangle.y;     

        this.main=main;

        array=new ArrayList();

        y1=0.5d;      

    }

    public void addProduct(Object obj,Object obj2,Object obj3,Object obj4,produit_T prod){

        prix=new PrixBloque(obj,obj2,obj3,null,((Integer)obj4).doubleValue(),0,report,this,prod,main,facture);   

        prix.ajustBloque();    

        prix.initFieldPosition(0,y1);     

        RArea area=new RArea();

        addBloque(prix);


        mainrectangle.height=prix.y2-0.4d;

        this.height=prix.y2-0.4d;

        y1=prix.y2;             

     

    } 

    public void addProduct(Object obj, Object obj2, Object obj3, Object obj4, produit_T prod, String texte) {

        prix=new PrixBloque(obj,obj2,obj3,null,Double.parseDouble(texte),0,report,this,prod,main,facture);   

        prix.ajustBloque();    

        prix.initFieldPosition(0,y1);      

        addBloque(prix);

        mainrectangle.height=prix.y2-0.4d;

        this.height=prix.y2-0.4d;

        y1=prix.y2;        

    }
      public MY_Area addProductToArea(Object obj, Object obj2, Object obj3, Object obj4, produit_T prod, String texte) {

         prix=new PrixBloque(obj,obj2,obj3,null,Double.parseDouble(texte),0,report,this,prod,main,facture);   

        prix.ajustBloque();    

        prix.initFieldPosition(0,0);      


        MY_Area area=new MY_Area(null,null,null);
        //PrixEdition tmp=new PrixEdition();
        area.addBloque(prix);
        area.marginTop=0.d;
        area.marginBottom=0.d;
        //addBloque(prix);


        mainrectangle.height=prix.y2-0.4d;

        this.height=prix.y2-0.4d;

        y1=prix.y2;          
        return area;
    }

    

    public void addProduct(Object obj, Object obj2, Object obj3, Object obj4, produit_T prod, String texte, Object obj5) {

    }

    

AbstractBloque prix;    

AbstractBloque bas;

MyRvfield log;

}

