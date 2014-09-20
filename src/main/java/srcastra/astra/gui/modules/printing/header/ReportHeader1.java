/*

 * ReportHeader1.java

 *

 * Created on 14 mars 2003, 11:52

 */



package srcastra.astra.gui.modules.printing.header;

import com.java4less.rreport.*;

import srcastra.astra.gui.modules.printing.*;

import srcastra.astra.sys.Logger;

import srcastra.astra.gui.modules.aidedesicion.*;

import srcastra.astra.sys.rmi.*;



/**

 *

 * @author  Thomas

 */

public class ReportHeader1 extends AbstractHeader{

    

    /** Creates a new instance of ReportHeader1 */

    RReportJ2 report;

    java.awt.Frame main;

    public ReportHeader1(Object obj,Object obj2,Object obj3,Object obj4,RReportJ2 report,java.awt.Frame main,AbstractBuffer descTre,int facture,astrainterface serveur)  {       

        super(obj,obj2,obj3,obj4);  

        espaceligne=0.4d;

        this.report=report;

        this.main=main;

        y1=0;

        x1=0;

        initBloqueGauche(obj,obj2,obj3,obj4); 

        addBloque(bloquegauche);

        initBloqueDroit(obj,obj2,obj3,obj4); 

        addBloque(bloquedroit);

        initBloqueCenterDroit(obj,obj2,obj3,obj4); 

        addBloque(bloqueCenterDroit);

        initBloqueCenterGauche(obj,obj2,obj3,obj4,facture,serveur); 

        addBloque(bloqueCenterGauche);

        initBloqueBas1(obj,obj2,obj3,obj4); 

        addBloque(bloqueBas1);

        initBloqueBas2(obj,obj2,obj3,obj4,descTre); 

        addBloque(bloqueBas2);

        y2=bloqueBas2.y2;

    }

    public void initBloqueGauche(Object obj,Object obj2,Object obj3,Object obj4) {

         bloquegauche=new RHBloqueGauche(obj,obj2,obj3, obj4,main,report);     

         bloquegauche.setMain(main);

         bloquegauche.setReport(report);

         bloquegauche.ajustBloque();

         bloquegauche.calculWidth();      

         bloquegauche.setPosition(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getBloquegupx(),srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getBloquegupy(),0.2d,espaceligne);

        // new ReportFormat().formatBloque( bloquegauche,0d,0d);

    }

    public void initBloqueDroit(Object obj,Object obj2,Object obj3,Object obj4){

          

         bloquedroit=new RHBloqueDroit(obj,obj2,obj3, obj4,main,report);     

         bloquedroit.ajustBloque();

         bloquedroit.calculWidth();

         Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"Taille du document : "+report.mPageWidthCM);

         //Logger.getDefaultLogger().log(Logger.LOG_DEBUG,"Taille de la marge : "+report.m);

         double x=report.mPageWidthCM-(report.marginRight+bloquedroit.width+report.marginLeft);

         bloquedroit.setPosition(srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getBloquedupx(),srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getBloquedupy(),0d,espaceligne);   

    }

   public void initBloqueCenterDroit(Object obj,Object obj2,Object obj3,Object obj4){

       bloqueCenterDroit=new RHBloqueCenterD(obj,obj2,obj3, obj4,main,report);     

       bloqueCenterDroit.ajustBloque();

       bloqueCenterDroit.calculWidth();
 
       bloqueCenterDroit.setPosition(bloquedroit.x1-2.5d,bloquegauche.y2+1,2,espaceligne);

   }

    public void initBloqueCenterGauche(Object obj,Object obj2,Object obj3,Object obj4,int facture,astrainterface serveur){

       bloqueCenterGauche=new RHBloqueCenterG(obj,obj2,obj3, obj4,main,report,facture,serveur);     

       bloqueCenterGauche.ajustBloque();

       bloqueCenterGauche.calculWidth();

       bloqueCenterGauche.setPosition(bloquegauche.x1+2.5d,bloquegauche.y2+1,0,espaceligne);

   }

      public void initBloqueBas1(Object obj,Object obj2,Object obj3,Object obj4){

       bloqueBas1=new RHBloqueBas1(obj,obj2,obj3, obj4,main,report);    

      // bloqueBas1.initFieldPosition(0,bloqueCenterDroit.y2+1);

       bloqueBas1.ajustBloque();

       bloqueBas1.calculWidth();

       bloqueBas1.setPosition(0,srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getCorpy(),0,espaceligne);

       bloqueBas1.initFieldPosition(0,srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getCorpy());

       //bloqueBas1.initFieldPosition(0,bloqueCenterDroit.y2+1);

      }

      public void initBloqueBas2(Object obj,Object obj2,Object obj3,Object obj4,AbstractBuffer descTre){

       bloqueBas2=new RHBloqueBas2(obj,obj2,obj3, obj4,main,report,descTre);     

       bloqueBas2.ajustBloque();

       bloqueBas2.calculWidth();

       bloqueBas2.setPosition(0,bloqueBas1.y2,0,espaceligne);

       bloqueBas2.initFieldPosition(0,bloqueBas1.y2);

     

   }

      

    

         

     

AbstractBloque bloquegauche;   

AbstractBloque bloquedroit;   

AbstractBloque bloqueCenterDroit;   

AbstractBloque bloqueCenterGauche;   

AbstractBloque bloqueBas1; 

AbstractBloque bloqueBas2; 

double espaceligne;

} 

