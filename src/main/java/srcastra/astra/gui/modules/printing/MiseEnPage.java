/*
 * MiseEnPage.java
 *
 * Created on 28 mars 2003, 9:32
 */

package srcastra.astra.gui.modules.printing;
import java.awt.*;
import srcastra.astra.sys.*;

/**
 *
 * @author  Thomas
 */
public class MiseEnPage {
    Image pageImage;
    Graphics pageGraphic;
    int resolution;
    /** Creates a new instance of MiseEnPage */
    public MiseEnPage(java.awt.Frame main,int resolution){
        this.resolution=resolution;
        pageImage=main.createImage(21,29);
        pageGraphic=pageImage.getGraphics();
    }
  //  public int getNumberOfLine(MyRvfield fields){
    //    charsPerLine=(int) (fields.widthpixel/g.getFontMetrics().stringWidth("X"));
    //}
    public void calculHeight(MyRvfield field){
        pageGraphic.setFont(field.getFont());
        int fontHeigth= pageGraphic.getFontMetrics().getHeight();
        double height=(double)fontHeigth/resolution;
        String memo2=(String)field.getruntimeValue();
        field.height=height;
        if(memo2!=null){
        int i=memo2.indexOf("\n");
        if(i!=-1){
          i=i+1;
           while(i!=-1){
               field.height=field.height+height;
               i=memo2.indexOf("\n",i);
               if(i==-1)break;
               i=i+1;
               System.out.println("ligne");
           }            
        }
        }
    }
    public  void calculWidth(MyRvfield field){
        if(field.ajust){
            Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Ok field ajust ");
        pageGraphic.setFont(field.getFont());
        if(field.getruntimeValue()!=null){
        String tmp=field.getruntimeValue().toString();
        if(tmp!=null){      
            if(field.getruntimeValue().toString().equals("null"))field.setruntimeValue("");
            int fontWidth= pageGraphic.getFontMetrics().stringWidth((String)field.getruntimeValue());
             Logger.getDefaultLogger().log(Logger.LOG_WARNING,field.getruntimeValue().toString());
             Logger.getDefaultLogger().log(Logger.LOG_WARNING,"texte not null "+"resolution ="+resolution+" fontwidth "+fontWidth);
            // if(((Font)field.getFont()).getSize()==10 && ((Font)field.getFont()).getSize()
           
             field.width=(double)fontWidth/resolution+0.3d;
             Logger.getDefaultLogger().log(Logger.LOG_WARNING,"Largeur du champ :"+field.width);
        }else 
            field.width=0;
        }
        }
    }
   // public generat
}
