/*

 * PassagerBloque.java

 *

 * Created on 17 mars 2003, 16:17

 */
package srcastra.astra.gui.modules.printing.bloquepassager;

import srcastra.astra.gui.modules.printing.*;
import srcastra.astra.gui.modules.printing.header.*;
import com.java4less.rreport.*;
import srcastra.astra.sys.Logger;

import java.util.*;

import srcastra.astra.sys.classetransfert.dossier.Passager_T;
import srcastra.astra.gui.modules.printing.classelangueuser.*;

/**
 * @author thomas
 */
public class PassagerBloque2 extends AbstractBloque {
    /**
     * Creates a new instance of PassagerBloque
     */
    public PassagerBloque2(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, ArrayList genprint, java.awt.Frame frame, RReport report) {
        super(obj, obj2, obj3, obj4, frame, report);
        initField(1, 1, 1, "", fontPlain10, genprint);
        //genereArray();
    }

    public java.util.ArrayList getFieldArray() {
        return fieldArray;

    }

    /**
     * Creates a new instance of AbstractBloque
     */
    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font) {
        /*   voyageur=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_voy"),igenerique,sgenerique,font);

         adresse=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_adresse"),igenerique,sgenerique,font);     

         datenai=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_datenai"),igenerique,sgenerique,font);      

         nat=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_nat"),igenerique,sgenerique,font);     

         cp=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_cp"),igenerique,sgenerique,font);     

         localite=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RP_ENT_loc"),igenerique,sgenerique,font);     

        */
    }

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font, ArrayList genprint) {
        if (dossier.getPassager() != null) {
            fieldArray = new ArrayList();
            for (int i = 0; i < dossier.getPassager().size(); i++) {
                Passager_T pass = (Passager_T) dossier.getPassager().get(i);
                GeneralePrinting gen = (GeneralePrinting) genprint.get(i);
                MyRvfield[] myfields = new MyRvfield[3];
                String titre = "";
                String localite = "";
                if (gen.getPassager() != null) {
                    titre = prepareString(gen.getPassager().getTitre());
                    myfields[0] = new MyRvfield(fcase, color, titre, igenerique, sgenerique, font);
                    myfields[0].Expand = true;
                    myfields[0].multiLine = false;
                    String pays = null;
                    //   if(pass.getPyscleunik()!=20)
                    //   pays=pass.getPaysnom();
                    if (pays == null) pays = "";
                    String loc = gen.getPassager().getLocalité();
                    localite = prepareMajuscule(loc);
                    if (loc == null) loc = "";
                    //    myfields[5]=new MyRvfield(fcase,color, localite,igenerique,sgenerique,font);
                    //     myfields[5].Expand=true;
                    //     myfields[5].multiLine=false;
                    //  if(gen.getPassager().getNationalité()!=null)
                    //   myfields[7]=new MyRvfield(fcase,color, gen.getPassager().getNationalité().toLowerCase(),igenerique,sgenerique,font);
                    //else
                    // myfields[7]=new MyRvfield(fcase,color,"  ",igenerique,sgenerique,font);
                    // myfields[7].Expand=true;
                    // myfields[7].multiLine=false;
                }
                String passnom = prepareStringNormal(pass.getPr_nom());
                /*int ii=passnom.length()-1;

                boolean found=false;


                while(!found && ii>=0)
                {
                    if(passnom.charAt(ii)==' ')
                    {found=true;}
                    else
                    {ii--;}
                }


                if(found)
                {String tmp="";
                 char caract=passnom.charAt(i+1);
                 tmp=passnom.substring(0,ii);
                 passnom=tmp+" "+caract;
                }
                */
               // if (passnom.length() > 22) passnom = passnom.substring(0, 18) + "...";
                /* int ii;

           for (ii=22;ii<=passnom.length();ii--)
               passnom=passnom+" ";*/
                myfields[1] = new MyRvfield(fcase, color, passnom, igenerique, sgenerique, font);
                myfields[1].Expand = true;
                myfields[1].multiLine = false;
                String dateN = "";
                if (pass.getPr_datenaissance().getYear() < 1900) dateN = "          ";
                else if (pass.getPr_datenaissance() == null) dateN = "          ";
                else
                    dateN = " (" + Integer.toString(pass.getPr_datenaissance().getDay()) + "/" + Integer.toString(pass.getPr_datenaissance().getMonth()) + "/" + Integer.toString(pass.getPr_datenaissance().getYear()) + ") ";
                int jj;
                /* for (jj=12;jj<dateN.length();jj--)
               dateN=dateN+" ";*/
                //01/01/1000
                /*     if(pass.getPr_datenaissance().getDay()==1)
        {
            if(pass.getPr_datenaissance().getYear()==1000)
            {
                if(pass.getPr_datenaissance().getMonth()==1)
                {
                    dateN="";
                }
                else
                {*/
                //dateN=" ("+Integer.toString(pass.getPr_datenaissance().getDay())+"/"+Integer.toString(pass.getPr_datenaissance().getMonth())+"/"+Integer.toString(pass.getPr_datenaissance().getYear())+") ";
                /*              }
                    }
                }*/
                myfields[2] = new MyRvfield(fcase, color, dateN, igenerique, sgenerique, font);
                myfields[2].Expand = true;
                myfields[2].multiLine = false;
                /*String codeNom = pass.getCodenom();
                if (codeNom == null) codeNom = "";
                else codeNom = ", " + codeNom;
                String adresse = prepareMajuscule(pass.getPr_adrese() + codeNom + " " + localite);
                myfields[3] = new MyRvfield(fcase, color, adresse, igenerique, sgenerique, font); //Plain8);
                myfields[3].Expand = true;
                myfields[3].multiLine = false;     */
                fieldArray.add(myfields);

            }

        }
        //tel=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_tel"),igenerique,sgenerique,font);
    }

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font) {
    }

    public void initFieldPosition(double x1, double x2, double x3, double x4, double x5, double x6, double y) {
        double y2 = y;
        //
        if (fieldArray != null) {
            for (int i = 0; i < fieldArray.size(); i++) {
                MyRvfield[] myfields = (MyRvfield[]) fieldArray.get(i);
                myfields[0].x = x1;
                myfields[0].y = y2;
                //  myfields[1].x=myfields[0].x+myfields[0].width+0.2d;
                myfields[1].x = 1d;
                myfields[1].y = y2;
                myfields[2].x = myfields[1].x + myfields[1].width + 0.4d;
                myfields[2].y = y2;
                //myfields[3].x = myfields[2].x + myfields[2].width + 0.4d;
                //myfields[3].y = y2;
                //  myfields[4].x=myfields[3].x+myfields[3].width+0.4d;//x3;
                //  myfields[4].y=y2;
                //  myfields[5].x=x4;
                //  myfields[5].y=y2;
                //  myfields[6].x=x5;
                //  myfields[6].y=y2;
                // myfields[7].x=x6;
                // myfields[7].y=y2;
                // fieldArray.add( myfields);
                // passager.add(myfields);
                y2 = y2 + 0.4d;

            }
            this.y2 = y2 - 0.4d;
            // this.y2=y2-0.9d;
        }


    }

    private String prepareString(String tmp) {
        if (tmp == null) return " ";
        if (tmp.length() > 1) tmp = tmp.substring(0, 1).toUpperCase() + tmp.substring(1, tmp.length()).toLowerCase();
        return tmp;


    }

    private String prepareMajuscule(String tmp) {
        if (tmp == null) return " ";
        return tmp.toUpperCase();
        //if(tmp.length()>1)
        // tmp=tmp.substring(0,1).toUpperCase()+tmp.substring(1,tmp.length()).toLowerCase();
        //return tmp;
    }

    private String prepareStringNormal(String tmp) {
        if (tmp == null) return " ";
        return tmp;


    }

    public void initFieldPosition(double x, double y) {
    }

    public void initLabelPosition(double x, double y) {
        /*     x1=x;

        y1=y;

        voyageur.y=y;

        adresse.y=y;

        datenai.y=y;

        nat.y=y;

        voyageur.x=x;

        cp.y=y;

        localite.y=y;

        adresse.x=voyageur.x+voyageur.width+1d;

        cp.x=adresse.x+adresse.width+3d;

        localite.x=cp.x+cp.width +1d;        

        datenai.x=localite.x+localite.width+0.5d;

        nat.x=datenai.x+datenai.width+0.5d; 

        x2=nat.x+nat.width;*/
    }

    protected void genereArray() {
        //fieldArray=new ArrayList();
        // fieldArray.add(new MyRvfield[]{voyageur,adresse,cp,localite,datenai,nat});
        //fieldArray.add(new MyRvfield[]{ clientadresse});
        //fieldArray.add(new MyRvfield[]{clientcp,clientlocalite});
    }
//MyRvfield voyageur;
//MyRvfield adresse;
//MyRvfield cp;
//MyRvfield localite;
//MyRvfield datenai;
//MyRvfield nat;
    ArrayList passager;

}

