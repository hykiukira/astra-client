/*

 * PrintRepHeagAbstractLayout.java

 *

 * Created on 4 mars 2003, 12:38

 */



package srcastra.astra.gui.modules.printing.printinglayout;

import com.java4less.rreport.*;

/**

 *

 * @author  Thomas

 */

public  class PrintRepHeadAbstractLayout extends AbstractPrintingLayout{

    

    /** Creates a new instance of PrintRepHeagAbstractLayout */

    public PrintRepHeadAbstractLayout(RReportJ2 report,String headerName) {

        super(report);

        area=report.getPageHeader();

    } 

    

    public void performConfig1(boolean visible) {

       

        area.getItemByName("nomagence").setVisible(visible);

        area.getItemByName("cp").setVisible(visible);

        area.getItemByName("localite").setVisible(visible);

        area.getItemByName("adresse").setVisible(visible);

        area.getItemByName("agfax").setVisible(visible);

        area.getItemByName("agtel").setVisible(visible);

        area.getItemByName("agmail").setVisible(visible);

        area.getItemByName("agtva").setVisible(visible);

        area.getItemByName("agcbc").setVisible(visible);

       // area.getItemByName("agence").setVisible(visible);

        area.getItemByName("fax").setVisible(visible);

        area.getItemByName("tel").setVisible(visible);

        area.getItemByName("mail").setVisible(visible);

        area.getItemByName("tva").setVisible(visible);

        area.getItemByName("cbc").setVisible(visible); 

        area.getItemByName("lic").setVisible(visible);

        area.getItemByName("licdata").setVisible(visible);

    }

     public ReturnClass performConfig5(double xvariation, double yvariation) {

         RObject bondc=area.getItemByName("nomagence");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         ReturnClass retval=new ReturnClass(bondc.x,bondc.y);

         bondc=area.getItemByName("cp");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("localite");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("adresse");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("agfax");

         bondc.x=bondc.x+xvariation; 

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("agtel");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("agmail");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("agtva");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("agcbc");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("fax");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("tel");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("mail");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("tva");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("cbc");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("lic");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("licdata");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;         

         return retval;

    }

    

    public void performConfig2(boolean visible) {

        area.getItemByName("fnumdos").setVisible(visible);

        area.getItemByName("fdatedep").setVisible(visible);

        area.getItemByName("fagent").setVisible(visible);

        area.getItemByName("fstatut").setVisible(visible);

        area.getItemByName("ftel").setVisible(visible);

        area.getItemByName("numdos").setVisible(visible);

        area.getItemByName("datedep").setVisible(visible);

        area.getItemByName("agent").setVisible(visible);

        area.getItemByName("statut").setVisible(visible);

        area.getItemByName("tel2").setVisible(visible);

    }

     public ReturnClass performConfig6(double xvariation, double yvariation) {

         RObject bondc=area.getItemByName("fnumdos");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         ReturnClass retval=new ReturnClass(bondc.x,bondc.y);

         bondc=area.getItemByName("fdatedep");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("fagent");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("fstatut");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("ftel");

         bondc.x=bondc.x+xvariation; 

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("numdos");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("datedep");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("agent");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("statut");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("tel2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;         

         return retval;

    }

     public ReturnClass performConfig7(double xvariation, double yvariation) {

         

         RObject bondc=area.getItemByName("dateret2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         ReturnClass retval=new ReturnClass(bondc.x,bondc.y);

         bondc=area.getItemByName("datedep2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("nbjour2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("nbrnuit2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("telp2");

         bondc.x=bondc.x+xvariation; 

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("telb2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("telg2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("tva2");

         if(bondc!=null){

            bondc.x=bondc.x+xvariation;

            bondc.y=bondc.y+yvariation;

         }

         bondc=area.getItemByName("fdatedep2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("fdateret2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("fnbjour2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("fnbrnuit2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;        

         bondc=area.getItemByName("ftelp2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("ftelb2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;        

         bondc=area.getItemByName("ftelg2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("ftva2");

         if(bondc!=null){

            bondc.x=bondc.x+xvariation;

            bondc.y=bondc.y+yvariation;        

         }

         bondc=area.getItemByName("fnumdos2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("fagent2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("fstatut2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("fdate2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;        

         bondc=area.getItemByName("numdos2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("agent2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;        

         bondc=area.getItemByName("statut2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("date2");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         area.height=area.height+yvariation;

         return retval;

    }

    public ReturnClass performConfig3(double xvariation, double yvariation) {

         System.out.println("performconfig3");

         RObject bondc=area.getItemByName("lib_bdc");

         RObject date=area.getItemByName("lib_date");

         RObject datef=area.getItemByName("lib_date_fields");

         RObject tamere=area.getItemByName("lib_numdoc");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         if(date!=null){

           date.x=date.x+xvariation;

           date.y=date.y+yvariation;   

         }

         if(datef!=null){

           datef.x=datef.x+xvariation;

           datef.y=datef.y+yvariation;   

         }    

         if(tamere!=null){

           tamere.x=tamere.x+xvariation;

           tamere.y=tamere.y+yvariation;   

         }   

         return new ReturnClass(bondc.x,bondc.y);

    }

    

    public ReturnClass performConfig4(double xvariation, double yvariation) {

         RObject bondc=area.getItemByName("clienttitre");

         if(bondc!=null){

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         }

         ReturnClass retval=new ReturnClass(bondc.x,bondc.y);

         bondc=area.getItemByName("clientnom");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("clientprenom");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("clientadresse");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("clientcp");

         bondc.x=bondc.x+xvariation; 

         bondc.y=bondc.y+yvariation;

         bondc=area.getItemByName("clientlocalite");

         bondc.x=bondc.x+xvariation;

         bondc.y=bondc.y+yvariation;

         return retval;

    }

    

    public void performCOnfigDefault1(double x, double y) {

         System.out.println("performconfig3");

         RObject bondc=area.getItemByName("lib_bdc");

         RObject date=area.getItemByName("lib_date");

         RObject datef=area.getItemByName("lib_date_fields");

         RObject tamere=area.getItemByName("lib_numdoc");

         bondc.x=x;

         bondc.y=y;

         if(date!=null){

             date.x=x;

             date.y=bondc.y+bondc.height; 

         }

         if(datef!=null){

             datef.x=date.x+date.width;

             datef.y=date.y;

         }

         if(tamere!=null){

             tamere.x=bondc.x+bondc.width;

             tamere.y=bondc.y;

         }

    }

    public void performCOnfigDefault3() {

        int i=srcastra.astra.sys.configuration.MainConfig.getMainConfig().getConfig().getNombrebloc();

        if(i!=0){

          double decal=i*0.4d;   

          performConfig7(0, decal);

        }        

    }

    private String ajustSize(RField field){

        String text=(String)field.getruntimeValue();

        int size=text.length()+2;

        double compSize=size*0.215d;

        field.width=compSize;

        return text;       

    }

    private double calculPosition(String text,double x){

       int size=text.length();

       size=size+3;

       double offset=x+(size*0.215d);

       return offset;

    }

    public void performConfigDefautl2(double x, double y) {

         String text;

         double tmpx=0;

         double xorigne=x;

         RObject bondc=area.getItemByName("clienttitre");

         RObject nom=area.getItemByName("clientnom");

         RObject pre=area.getItemByName("clientprenom");

         RObject adr=area.getItemByName("clientadresse");

         RObject cp=area.getItemByName("clientcp");

         RObject loc=area.getItemByName("clientlocalite");

         if(bondc!=null){

            text=ajustSize((RField)bondc);

            bondc.x=x;

            bondc.y=y;

            nom.x=bondc.x+bondc.width;

            nom.y=bondc.y;

            pre.x=x;

            pre.y=bondc.y+bondc.height;

         }

         else{

           nom.x=x;

           nom.y=y;   

           pre.x=x;

           pre.y=nom.y+nom.height;

         }

         

         adr.x=x;

         adr.y=pre.y+pre.height;

         cp.x=x;

         cp.y=adr.y+adr.height;

         loc.y=adr.y+adr.height;

         loc.x=cp.x+cp.width+0.3d;

    }    

RArea area;  

}

