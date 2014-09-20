/*



 * AbstractBloque.java



 *



 * Created on 14 mars 2003, 11:56



 */







package srcastra.astra.gui.modules.printing.header;



import srcastra.astra.gui.modules.printing.*;



import srcastra.astra.sys.classetransfert.*;



import srcastra.astra.sys.classetransfert.dossier.*;



import srcastra.astra.gui.modules.printing.classelangueuser.*;



import java.awt.Font;



import com.java4less.rreport.*;



//import com.java4less.rreportVB.RVField;



import srcastra.astra.sys.Logger;



import java.awt.font.*;



import java.awt.geom.*;



import java.awt.Graphics2D;



/**



 *



 * @author  Thomas



 */



public abstract  class AbstractBloque {



    



    /** Creates a new instance of AbstractBloque */



    MiseEnPage mispag;



    protected abstract void initLabel(int fcase,int color,int igenerique,String sgenerique,java.awt.Font font);



    protected abstract void initField(int fcase,int color,int igenerique,String sgenerique,java.awt.Font font);



    public AbstractBloque(Object obj,Object obj2,Object obj3,Object obj4,java.awt.Frame frame,RReport report) {



        currentUser=(Loginusers_T)obj;



        entite=(Entite_T)obj2;



        dossier=(Dossier_T)obj3;

        if(obj4 instanceof GeneralePrinting)

            genprint=(GeneralePrinting)obj4;



        this.main=frame;



        this.report=report;



       // if(report.getResolution()==



        System.out.println("system resolution "+report.getResolution());



        mispag=new MiseEnPage(main,report.getResolution());



    }



public void calculWidth(){

    double size1,size2;

    if(fieldArray!=null){

        for(int i=0;i<fieldArray.size();i++){

         RField[] tmp=(RField[])fieldArray.get(i); 

         if(tmp!=null && tmp.length>0){

             size1=0d;

            for(int j=0;j<tmp.length  ;j++){

                if(tmp[j]!=null){

                size1=size1+tmp[j].width;

                Logger.getDefaultLogger().log(Logger.LOG_INFOS,"element "+j+" "+tmp[j].getruntimeValue()+" taille "+tmp[j].width); 

            }

            }

          if(size1>width)width=size1;

            Logger.getDefaultLogger().log(Logger.LOG_INFOS,"Taille temporaire "+size1); 

            Logger.getDefaultLogger().log(Logger.LOG_INFOS,"Taille du bloque "+width); 

         }

    }

        System.out.println("Fin de bloque\n\n\n");

   } 

}



public void ajustBloque(){



    double size1,size2;



    if(fieldArray!=null){



        for(int i=0;i<fieldArray.size();i++){



         MyRvfield[] tmp=(MyRvfield[])fieldArray.get(i); 



         if(tmp!=null && tmp.length>0){



             size1=0d;



            for(int j=0;j<tmp.length  ;j++){



              // ajustSize(tmp[j]);



                if(tmp[j]!=null)



                    if(tmp[j].ajust){



                        Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Main "+main);



                        if(main==null)



                            ajusteSize(tmp[j]);



                        else 



                            ajusteSize2(tmp[j]);



                    }



            }



           if(size1>width)width=size1;



         }



    }



    } 



}



private void ajusteSize(MyRvfield field){



   FontRenderContext frc = new FontRenderContext(null,true,true);



   String tmp=(String)field.getruntimeValue();



   if(tmp!=null && tmp.length()>0){



    TextLayout layout = new TextLayout(tmp, field.getFont(),frc);



    Rectangle2D bounds = layout.getBounds();

   // int fact=45;

    int fact=50;

    field.width=(bounds.getWidth()/1024)*fact;



    Logger.getDefaultLogger().log(Logger.LOG_DEBUG,field.getruntimeValue()+"x "+bounds.getX()+"  y "+bounds.getY()+"  width "+bounds.getWidth()+" heigth "+bounds.getHeight());



   }else field.width=0;



 



  



}



private void ajusteSize2(MyRvfield field){



  Logger.getDefaultLogger().log(Logger.LOG_WARNING,"J'ajuste ");



  mispag.calculWidth(field);



  mispag.calculHeight(field);



}

public void ajustHeigth(MyRvfield field){

    mispag.calculHeight(field);

}



 private  String ajustSize(RField field){



        String text=null; 



        text=(String)field.getruntimeValue();



        if(text==null)text="";



        int size=text.length()+2;



        double compSize=size*0.215d;



        field.width=compSize;



        return text;       



    }



public void setPosition(double x,double y,double espaceMot,double espaceligne){

    x1=x;

    y1=y;

    int m=0;

    double bloquesep=0;

    if(fieldArray!=null){

        for(int i=0;i<fieldArray.size();i++){

         RField[] tmp=(RField[])fieldArray.get(i); 

         if(tmp!=null && tmp.length>0){

             if(m==2){

                 m=0;

                 bloquesep=0.5d;

             }

            for(int j=0;j<tmp.length  ;j++){

              if(j==0)

                tmp[j].x=x;

              else

                tmp[j].x=espaceMot+tmp[j-1].x+tmp[j-1].width;

              bloquesep=0;

              tmp[j].y=y;

            }

            m=m+1;

         }

         y=y+espaceligne;

    }

    y2=y;

    x2=x+width;



    } 



    



    



}

/*public void setPosition(double x,double y,double espaceMot,double espaceligne){



    x1=x;



    y1=y;



    int m=0;



    double bloquesep=0;



    if(fieldArray!=null){



        for(int i=0;i<fieldArray.size();i++){



         RField[] tmp=(RField[])fieldArray.get(i); 



         if(tmp!=null && tmp.length>0){



             if(m==2){



                 m=0;



                 bloquesep=0.5d;



             }



            for(int j=0;j<tmp.length  ;j++){



              if(j==0)



                tmp[j].x=x;



              else



                tmp[j].x=+bloquesep+tmp[j-1].x+tmp[j-1].width;



              bloquesep=0;



              tmp[j].y=y;



            }



            m=m+1;



         }



         y=y+espaceligne;



    }



    y2=y;



    x2=x+width;



    } 



    



    



}*/



public abstract java.util.ArrayList getFieldArray();



protected abstract void genereArray();



public abstract void initFieldPosition(double x,double y);



public abstract void initLabelPosition(double x,double y);



public abstract void initFieldPosition(double x1,double x2,double x3,double x4,double x5,double x6,double y);







/** Getter for property main.



 * @return Value of property main.



 */



public java.awt.Frame getMain() {

    return main;

}

/** Setter for property main.

 * @param main New value of property main.

 */

public void setMain(java.awt.Frame main) {

    this.main = main;

}

/** Getter for property report.



 * @return Value of property report.



 */

public com.java4less.rreport.RReport getReport() {

    return report;

}

/** Setter for property report.



 * @param report New value of property report.



 */

public void setReport(com.java4less.rreport.RReport report) {

    this.report = report;

}

protected Loginusers_T currentUser;

protected Dossier_T dossier;

protected Entite_T entite;

protected GeneralePrinting genprint;   

public   double x1,x2,width,heigth,y1,y2,xbiger,ybiger;

protected Font fontPlain10=new java.awt.Font("Courrier",java.awt.Font.PLAIN,10);

protected Font fontPlain8=new java.awt.Font("Courrier",java.awt.Font.PLAIN,8);

protected Font fontPlain6=new java.awt.Font("Courrier",java.awt.Font.PLAIN,6);

protected Font fontBold10=new java.awt.Font("Courrier",java.awt.Font.BOLD,10);

protected Font fontBold12=new java.awt.Font("Courrier",java.awt.Font.BOLD,12);

protected Font fontTest=new java.awt.Font("Courier",java.awt.Font.PLAIN,8);



protected java.util.ArrayList fieldArray;

private java.awt.Frame main;

private RReport report;

}



