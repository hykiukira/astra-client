/*
 * RHBloqueGauche.java
 *
 * Created on 14 mars 2003, 11:58
 */

package srcastra.astra.gui.modules.printing.header;
import srcastra.astra.gui.modules.printing.*;
import srcastra.astra.gui.sys.listModel.dossierListModel.StatusListModel;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.sys.classetransfert.dossier.*;
import java.util.ArrayList;
import srcastra.astra.sys.classetransfert.utils.*;
import com.java4less.rreport.*;
/**
 *
 * @author  Thomas
 */
public class RHBloqueBas1 extends AbstractBloque{
    
    /** Creates a new instance of RHBloqueGauche */
        srcastra.astra.gui.sys.listModel.dossierListModel.StatusListModel statutliste;
    public RHBloqueBas1(Object obj, Object obj2, Object obj3, Object obj4,java.awt.Frame frame,RReport report) {
        super(obj,obj2,obj3,obj4,frame,report);
        statutliste=new  srcastra.astra.gui.sys.listModel.dossierListModel.StatusListModel(null,currentUser);
        initField(1,1,1,"",fontPlain10);
        initLabel(1,1,1,"",fontBold10);   
        genereArray();
    }
    protected void initField(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){      
       // BonCommandeText=new MyRvfield(fcase,color,"Bon de Commande",igenerique,sgenerique,fontBold12);
        fnumdos=new MyRvfield(fcase,color,dossier.getDrnumdos(),igenerique,sgenerique,font);
        fnumdos.name="fnumdos2";
       //fdate=new MyRvfield(fcase,color,CalculDate.getTodayDate().toString(),igenerique,sgenerique,font);
        fagent=new MyRvfield(fcase,color," ",igenerique,sgenerique,font);
        fagent.name="fagent2";
         if(dossier.getCreator()!=null)
            if(dossier.getCreator().getUrnom()!=null)
        fagent=new MyRvfield(fcase,color,dossier.getCreator().getUrnom(),igenerique,sgenerique,font);
        fagent.name="fagent2";
        String status=statutliste.getValue(dossier.getDrstatus());
        if(status==null){
            status="";
        }
        fstatut=new MyRvfield(fcase,color,status,igenerique,sgenerique,font);
        fstatut.name="fstatut2";
        fdate=new MyRvfield(fcase,color,CalculDate.getTodayDate().toString2(),igenerique,sgenerique,font);    
        fdate.name="fdate2";
    }
    protected void initLabel(int fcase, int color, int igenerique, String sgenerique, java.awt.Font font){
         
        numdos=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_numdos"),igenerique,sgenerique,font);
        StaticFields.setCorp(numdos);
        numdos.name="numdos2";
         // date=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_dateDep"),igenerique,sgenerique,font);     
         agent=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_agent1"),igenerique,sgenerique,font);      
         agent.name="agent2";
         statut=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BD_statut1"),igenerique,sgenerique,font);     
         statut.name="statut2";
         date=new MyRvfield(fcase,color,java.util.ResourceBundle.getBundle("srcastra.astra.edition.BonDeCommande", currentUser.getLangage()).getString("RH_BB2_dateedit"),igenerique,sgenerique,font);      
         date.name="date2";
    }
    protected void genereArray(){
        fieldArray=new ArrayList(); 
        fieldArray.add(new MyRvfield[]{numdos,fnumdos,agent,fagent,statut,fstatut,date,fdate});
       /* fieldArray.add(new MyRvfield[]{ datedep,fdatedep});
        fieldArray.add(new MyRvfield[]{agent,fagent});
        fieldArray.add(new MyRvfield[]{statut,fstatut});
        fieldArray.add(new MyRvfield[]{ tel,ftel});*/
    }
    
    /** Getter for property fieldArray.
     * @return Value of property fieldArray.
     */
    public java.util.ArrayList getFieldArray() {
        return fieldArray;
    }
    
    /** Setter for property fieldArray.
     * @param fieldArray New value of property fieldArray.
     */
    public void setFieldArray(java.util.ArrayList fieldArray) {
        fieldArray = fieldArray;
    }
    
    public void initFieldPosition(double x, double y) {
        numdos.x=0;
        numdos.y=y;
        fnumdos.x=numdos.width;
        fnumdos.y=y;
        agent.x=5.5;//5.5;
        agent.y=y;
        fagent.x=agent.width+agent.x;
        fagent.y=y;
        statut.x=11;
        statut.y=y;
        fstatut.x=11+statut.width;
        fstatut.y=y;
        date.x=14;
        date.y=y;
        fdate.x=14+date.width;
        fdate.y=y;
    }
    
    public void initLabelPosition(double x, double y) {
    }
    
    public void initFieldPosition(double x1, double x2, double x3, double x4, double x5, double x6, double y) {
    }
    
 MyRvfield numdos;
 MyRvfield fnumdos;
 MyRvfield date;
 MyRvfield fdate;
 MyRvfield statut;
 MyRvfield fstatut;
 MyRvfield agent;
 MyRvfield fagent;

}
