/*
 * AgendaTaskPrint.java
 *
 * Created on 1 juin 2004, 10:20
 */

package srcastra.astra.gui.modules.agenda;

import com.java4less.rreport.*;
import srcastra.astra.sys.classetransfert.Loginusers_T;

import java.util.*;

import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.sys.classetransfert.utils.*;
import srcastra.astra.gui.components.combobox.liste.*;

/**
 * @author Administrateur
 */
public class AgendaTaskPrint extends RReportJ2 {

    /**
     * Creates a new instance of AgendaTaskPrint
     */

    Loginusers_T m_user;
    ArrayList statutA;

    public AgendaTaskPrint(java.awt.Frame owner, Loginusers_T user, ArrayList array, srcastra.astra.sys.classetransfert.utils.Date date) {
        super(owner);
        RPrintSetupJDK13 pt = new RPrintSetupJDK13();
        pt.horizontal = false;
        this.setPrintSetup(pt);
        this.setOrientation(this.ORIENTATION_VERTICAL);
        super.marginBottom = 1.0d;
        super.marginTop = 1.0d;
        super.marginLeft = 1.0d;
        super.marginRight = 1.0d;
        m_user = user;
        TaskEtatListeTableModel model = new TaskEtatListeTableModel(null, m_user);
        statutA = model.getM_vector();
        RField datef = new RField();
        datef.x = 0;
        datef.y = 0;
        datef.setruntimeValue(CalculDate.getTodayDate().toString2());
        RField title = new RField();
        title.setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda", m_user.getLangage()).getString("title") + "   " + date.toString2());
        title.FontType = new java.awt.Font("tahoma", java.awt.Font.BOLD, 15);
        title.x = 6;
        title.y = 1;
        RArea pageHeader = new RArea();
        pageHeader.add(title);
        pageHeader.add(datef);
        pageHeader.marginBottom = 1;
        setPageHeader(pageHeader);
        RField page = new RField();
        page.setruntimeValue("[Page]");
        RArea pageFooter = new RArea();
        pageFooter.add(page);
        setPageFooter(pageFooter);
        reportLayout(array);
        prepare();
        for (int i = 0; i < getAreaCount(); i++) {
            printArea(getAreaAt(i));
        }
    }

    private void reportLayout(ArrayList array) {
        if (array != null) {
            for (int i = 0; i < array.size(); i++) {
                Task_T task = (Task_T) array.get(i);
                RField numdoslib;
                RField numdos;
                RField clientFactLib;
                RField clientFact;
                RField clientContLib;
                RField clientCont;
                RField debutlib;
                RField debut;
                RField echeanceLib;
                RField echeance;
                RField statutLib;
                RField statut;
                RField objetLib;
                RField objet;
                RField memo;
                RArea detail;
                numdos = new RField();
                numdos.x = 0;
                numdos.y = 0;
                numdos.setruntimeValue("[" + task.getTask_numdos() + "]");
                numdos.FontType = new java.awt.Font("tahoma", java.awt.Font.BOLD, 10);
                //numdoslib=new RField();
                //numdoslib.setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda", m_user.getLangage()).getString("dossier"));
                //numdoslib.x=0;
                //numdoslib.y=0;
                //numdoslib.FontType=new java.awt.Font("tahoma", java.awt.Font.BOLD, 10);
                //clientFactLib=new RField();
                //clientFactLib.setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda", m_user.getLangage()).getString("csfact"));
                //clientFactLib.x=12;
                //clientFactLib.y=0;
                //clientFactLib.FontType=new java.awt.Font("tahoma", java.awt.Font.BOLD, 10);
                //clientFact=new RField();
                //clientFact.setruntimeValue(task.getCsfactname());
                //clientFact.x=numdos.getWidthpixel()+2;
                //clientFact.y=0;
                //clientContLib=new RField();
                //clientContLib.setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda",m_user.getLangage()).getString("cscont"));
                //clientContLib.x=numdos.getWidthpixel()+2;
                //clientContLib.y=0;
                //clientContLib.FontType=new java.awt.Font("tahoma", java.awt.Font.BOLD, 10);
                //clientCont=new RField();
                //clientCont.setruntimeValue(task.getCscontname());
                //clientCont.x=numdos.getWidthpixel()+5;//7.5;
                //clientCont.y=0;
                /*debutlib=new RField();
                debutlib.setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda",m_user.getLangage()).getString("debut"));
                debutlib.x=0;
                debutlib.y=1;
                debutlib.FontType=new java.awt.Font("tahoma", java.awt.Font.BOLD, 10);
                debut=new RField();
                debut.setruntimeValue(task.getTask_debut().toString2());
                debut.x=2;
                debut.y=1;
                echeanceLib=new RField();
                echeanceLib.setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda",m_user.getLangage()).getString("echeance"));
                echeanceLib.x=5;
                echeanceLib.y=1;
                echeanceLib.FontType=new java.awt.Font("tahoma", java.awt.Font.BOLD, 10);
                echeance=new RField();
                echeance.setruntimeValue(task.getTask_echeance().toString2());
                echeance.x=7.5;
                echeance.y=1;*/
                /* statutLib=new RField();
               statutLib.setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda",m_user.getLangage()).getString("statut"));
               statutLib.x=12;
               statutLib.y=0;
               statutLib.FontType=new java.awt.Font("tahoma", java.awt.Font.BOLD, 10);
               statut=new RField();*/
                memo = new RField();
                memo.setruntimeValue(task.getTask_memo() + "  -->  " + task.getCscontname());
                memo.x = 3;
                memo.y = 0;
                //memo.width=25;
                memo.Expand = true;
                memo.multiLine = true;

                /* if(statutA!=null){
                  int index=task.getTask_etat();
                  if(index>-1 && index<3){
                    Object[] tmp=(Object[])statutA.get(index);
                  if(tmp!=null){
                   if(tmp[2]!=null){
                     statut.setruntimeValue(tmp[2].toString());   
                   }
                  }
                      
                  }
                      
                }
                statut.x=14.5;
                statut.y=0;*/
                /* objetLib=new RField();
                objetLib.setruntimeValue(java.util.ResourceBundle.getBundle("srcastra/astra/locale/agenda",m_user.getLangage()).getString("obj"));
                objetLib.x=0;
                objetLib.y=0.5;
                objet=new RField();
                objet.x=3;
                objet.y=0.5;*/

                detail = new RArea();
                // detail.add(numdoslib);
                detail.add(numdos);
                //  detail.add(clientFactLib);
                //  detail.add(clientFact);
                //  detail.add(clientContLib);
                //  detail.add(clientCont);
                //  detail.add(debutlib);
                //  detail.add(debut);
                //  detail.add(echeanceLib);
                //  detail.add(echeance);
                //  detail.add(statutLib);
                //  detail.add(statut);
                // detail.add(objetLib);
                //  detail.add(objet);
                detail.add(memo);
                detail.marginTop = 0.5;
                addArea(detail);

            }
        }
    }

}
