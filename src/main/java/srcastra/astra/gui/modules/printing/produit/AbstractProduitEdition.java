/*

 * AbstractProduitEdition.java

 *

 * Created on 18 mars 2003, 15:54

 */



package srcastra.astra.gui.modules.printing.produit;

import srcastra.astra.gui.modules.printing.header.*;

import srcastra.astra.gui.modules.printing.*;

import srcastra.astra.gui.modules.printing.header.*;

import com.java4less.rreport.*;

import srcastra.astra.sys.Logger;

import java.util.*;

import srcastra.astra.sys.classetransfert.dossier.Passager_T;

import srcastra.astra.gui.modules.printing.classelangueuser.*;

import srcastra.astra.sys.classetransfert.utils.CalculDate;

import srcastra.astra.sys.classetransfert.*;

import srcastra.astra.sys.classetransfert.dossier.*;



/**

 *

 * @author  Thomas

 */

public abstract class AbstractProduitEdition extends AbstractArea{

    

    /** Creates a new instance of AbstractProduitEdition */

    public AbstractProduitEdition (Object obj,Object obj2,Object  obj3,int type,RReportJ2 report,String text) {

          super(obj,obj2,obj3); 

          if(!text.equals("pay45")){

            this.report=report;

            currentUser=(Loginusers_T)obj;

            titlehash=new Hashtable();

            titlehash.put(new Integer(BROCHURE),java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("Bro_titre")+" PO :");

            titlehash.put(new Integer(HOTEL),java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("HO_titre"));

            titlehash.put(new Integer(AVION),java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("AV_titre"));

            titlehash.put(new Integer(ASSURANCE),java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("AS_titre")+" :");

            titlehash.put(new Integer(SYNTH),java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("p_synth"));

            titlehash.put(new Integer(PAY),java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("BDC_payement"));

            titlehash.put(new Integer(TRAIN),java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("TR_titre"));

            titlehash.put(new Integer(BATEAU),java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("BA_titre"));

            titlehash.put(new Integer(VOITURE),java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("VO_titre"));

            titlehash.put(new Integer(CAR),java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("CA_titre"));

            titlehash.put(new Integer(DIVERS),java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("DIV_titre"));

            entite=(Entite_T)obj2;

            dossier=(Dossier_T)obj3;

            titre=new MyRvfield(1,1,text+" "+(String)titlehash.get(new Integer(type)),1,"",fontBold12);     

            titre.x=0;

            titre.y=0;

            if(type!=TOTAL)

            {

               add(titre);

             mainrectangle=new RRectangle();

             mainrectangle.x=0;

             mainrectangle.y=1d;

             mainrectangle.height=0.5;

             y2=1;

             mainrectangle.width=report.mPageWidthCM-(report.marginLeft+report.marginRight);

             add(mainrectangle);}

          }

    }

     public abstract void addProduct(Object obj,Object obj2,Object obj3,Object obj4,produit_T prod);

     public abstract void addProduct(Object obj,Object obj2,Object obj3,Object obj4,produit_T prod,String texte);

     public abstract void addProduct(Object obj, Object obj2, Object obj3, Object obj4, produit_T prod, String texte,Object obj5);

     

protected MyRvfield titre;

protected RRectangle mainrectangle;

protected java.awt.Font fontBold12=new java.awt.Font("Courrier",java.awt.Font.BOLD,12);    

protected java.awt.Font fontPlain10=new java.awt.Font("Courrier",java.awt.Font.PLAIN,10);

protected java.awt.Font fontBold10=new java.awt.Font("Courrier",java.awt.Font.BOLD,10);

protected Loginusers_T currentUser;

protected Dossier_T dossier;

protected Entite_T entite;

protected GeneralePrinting genprint;   

public double x1,x2,y1,y2,height1;

public final static int BROCHURE=0;

public final static int HOTEL=1;

public static int AVION=2;

public static int ASSURANCE=3;

public static int SYNTH=4;

public static int BATEAU=5;

public static int VOITURE=6;

public static int TRAIN=7;

public static int PAY=8;

public static int TOTAL=9;

public static int CAR=10;

public static int DIVERS=11;

Hashtable titlehash;

RReportJ2 report;

}

