/*

 * RHBloqueGauche.java

 *

 * Created on 14 mars 2003, 11:58

 */
package srcastra.astra.gui.modules.printing.produit;

import srcastra.astra.gui.modules.printing.*;
import srcastra.astra.gui.modules.printing.header.*;
import com.java4less.rreport.*;
import srcastra.astra.sys.Logger;

import java.util.*;

import srcastra.astra.sys.classetransfert.dossier.Passager_T;
import srcastra.astra.gui.modules.printing.classelangueuser.*;
import srcastra.astra.sys.classetransfert.utils.CalculDate;
import srcastra.astra.sys.classetransfert.dossier.brochure.Brochure_T;
import srcastra.astra.sys.classetransfert.dossier.produit_T;
import srcastra.astra.sys.classetransfert.dossier.bateau.Bateau_T;
import srcastra.astra.sys.classetransfert.dossier.train.Train_T;
import srcastra.astra.sys.classetransfert.dossier.voitureLocation.VoitureLocation_T;

import java.awt.*;

/**
 * @author Thomas
 */
public class MemoBloque extends AbstractBloque {
    /**
     * Creates a new instance of RHBloqueGauche
     */
    produit_T prod;
    String memo2;
    Graphics g;

    public MemoBloque(Object obj, Object obj2, Object obj3, Object obj4, double x, double y, RReport report, RArea parent, produit_T prod, String memo, java.awt.Frame frame) {
        super(obj, obj2, obj3, obj4, frame, report);
        this.prod = prod;
        memo2 = memo;
        memo2 = memo2.toUpperCase();
        initField(1, 1, 1, "", fontTest);
        initLabel(1, 1, 1, "", fontPlain10);
        genereArray();
        Image pageImage = frame.createImage(21, 29);
        g = pageImage.getGraphics();

    }

    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font) {
        // BonCommandeText=new MyRvfield(fcase,color,"Bon de Commande",igenerique,sgenerique,fontBold12);
        memo = new MyRvfield(fcase, color, memo2, igenerique, sgenerique, font);
        memo.width = 19;
        memo.multiLine = true;
        //memo.Expand=true;
        System.out.println("\n\n[***********] ligne du memo " + memo.countLin);
        // ajustHeigth(memo);
        //  memo.ajust=true;
        /*  int i=memo2.indexOf("\n");

        if(i!=0){

           memo.height=0.4d;

           i=i+1;

           while(i!=0){

               memo.height=memo.height+0.4d;

               i=memo2.indexOf("\n",i)+1;

               System.out.println("ligne");

           }            

        }*/
        heigth = memo.height;
        // y2=memo.y+memo.height;
        // memo.
    }

    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font) {
        /*   if(prod instanceof Train_T)

           fpnr=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_AV_numbillet"),igenerique,sgenerique,font);

        else

           fpnr=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PRHO_pnr"),igenerique,sgenerique,font);

        fdatedep=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_Dep"),igenerique,sgenerique,font);

        fdateret=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_BRO_ret"),igenerique,sgenerique,font);

        fstatut=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_statut"),igenerique,sgenerique,font);

        */
        fmemo = new MyRvfield(fcase, color, java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("PR_B_Trans_memo"), igenerique, sgenerique, font);

    }

    protected void genereArray() {
        fieldArray = new ArrayList();
        /*   fieldArray.add(new MyRvfield[]{fpnr,pnr});

        fieldArray.add(new MyRvfield[]{fdatedep,datedep,fdateret,dateret});

        */
        fieldArray.add(new MyRvfield[]{fmemo});
        fieldArray.add(new MyRvfield[]{memo});
        /* fieldArray.add(new MyRvfield[]{ datedep,fdatedep});

      fieldArray.add(new MyRvfield[]{agent,fagent});

      fieldArray.add(new MyRvfield[]{statut,fstatut});

      fieldArray.add(new MyRvfield[]{ tel,ftel});*/
    }

    /**
     * Getter for property fieldArray.
     *
     * @return Value of property fieldArray.
     */
    public java.util.ArrayList getFieldArray() {
        return fieldArray;


    }

    /**
     * Setter for property fieldArray.
     *
     * @param fieldArray New value of property fieldArray.
     */
    public void setFieldArray(java.util.ArrayList fieldArray) {
        fieldArray = fieldArray;

    }

    public void initFieldPosition(double x, double y) {
    }

    public void initLabelPosition(double x, double y) {
    }

    public void initFieldPosition(double x1, double x2, double x3, double x4, double x5, double x6, double y) {
    }

    /**
     * Getter for property fmemo.
     *
     * @return Value of property fmemo.
     */
    public srcastra.astra.gui.modules.printing.MyRvfield getFmemo() {
        return fmemo;

    }

    /**
     * Setter for property fmemo.
     *
     * @param fmemo New value of property fmemo.
     */
    public void setFmemo(srcastra.astra.gui.modules.printing.MyRvfield fmemo) {
        this.fmemo = fmemo;

    }

    public double calculHeigth() {
        memo.calculHeigth(g, getReport().getResolution(), memo.getFont());
        return memo.height;

    }

    /**
     * Getter for property fmemo.
     *
     * @return Value of property fmemo.
     */
    MyRvfield pnr;
    MyRvfield datedep;
    MyRvfield dateret;
    MyRvfield statut;
    MyRvfield memo;
    MyRvfield fpnr;
    MyRvfield fdatedep;
    MyRvfield fdateret;
    MyRvfield fstatut;
    MyRvfield fmemo;
    //MyRvfield lit;
    //MyRvfield commod;
    //MyRvfield situtation;
// MyRvfield vue;
// MyRvfield logement;
// MyRvfield vue;
// MyRvfield tel;
// MyRvfield ftel;
// MyRvfield agent;
// MyRvfield fagent;


}

