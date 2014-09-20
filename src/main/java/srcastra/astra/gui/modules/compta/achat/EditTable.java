/*

 * EditTable.java

 *

 * Created on 24 juillet 2003, 12:02

 */


package srcastra.astra.gui.modules.compta.achat;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.event.*;

import javax.swing.table.*;

import srcastra.astra.gui.components.celleditor.CellEditorInterface;

import srcastra.astra.gui.components.combobox.liste.*;

import java.util.*;

import srcastra.astra.gui.components.tva.*;

import srcastra.astra.sys.*;

import srcastra.astra.sys.compta.*;

import srcastra.astra.gui.components.textFields.*;

import srcastra.astra.sys.*;
import srcastra.astra.sys.utils.Rentabilite;

import srcastra.astra.gui.modules.compta.achat.*;
import srcastra.astra.sys.utils.Rentabilite;

import srcastra.astra.sys.rmi.*;
import srcastra.astra.sys.rmi.utils.*;


/**
 * @author thomas
 */

public class EditTable implements CellEditorListener {


    /**
     * Creates a new instance of EditTable
     */

    JTable table;

    JTable table2;

    AchatTableModel model;

    TvaTableModel modeltva;

    double sommeGlobal;

    Hashtable valeur;

    TvaFrame tvaf;

    double value;

    double tva;

    double total;

    Achat frame;

    boolean showRechFrame = true;

    int jota_categorie;

    MouseAdapter mouse = new MouseAdapter() {

        public void mouseClicked(MouseEvent evt) {

            System.out.println("Im in");

            interTable(table.getSelectedRow(), table.getSelectedColumn());

        }

    };

    public EditTable() {


    }

    /**
     * Getter for property action.
     *
     * @return Value of property action.
     */

    public ActionListener getAction() {

        return action;

    }

    public void interTable(int row, int col) {

        System.out.println("insert without value\n");

        model.afficheMe();

        table.editCellAt(row, col);

        srcastra.astra.gui.components.celleditor.CellEditorInterface celledit = (srcastra.astra.gui.components.celleditor.CellEditorInterface) table.getCellEditor(row, col);//getColumnModel().getColumn(col).getCellEditor();

        celledit.interTable(row);

        //  celledit.reset();

        //  srcastra.astra.gui.components.celleditor.CellEditorInterface celledit=(srcastra.astra.gui.components.celleditor.CellEditorInterface)table.getCellEditor(row,col);

        //srcastra.astra.gui.components.celleditor.CellEditorInterface inter=(srcastra.astra.gui.components.celleditor.CellEditorInterface)celledit;

        celledit.getComponent().requestFocus();

        //((java.awt.Component)table.getCellEditor(row,col).getTableCellEditorComponent(table)).requestFocus();

    }

    public void interTable(int row, int col, String value, boolean open) {

        System.out.println("insert with value\n");

        model.afficheMe();

        if (open)

            table.editCellAt(row, col);

        srcastra.astra.gui.components.celleditor.CellEditorInterface celledit = (srcastra.astra.gui.components.celleditor.CellEditorInterface) table.getColumnModel().getColumn(col).getCellEditor();

        //  celledit.reset();

        //  srcastra.astra.gui.components.celleditor.CellEditorInterface celledit=(srcastra.astra.gui.components.celleditor.CellEditorInterface)table.getCellEditor(row,col);

        //srcastra.astra.gui.components.celleditor.CellEditorInterface inter=(srcastra.astra.gui.components.celleditor.CellEditorInterface)celledit;

        celledit.getComponent().requestFocus();

        ((ATextField) celledit.getComponent()).setText(value);

        //((java.awt.Component)table.getCellEditor(row,col).getTableCellEditorComponent(table)).requestFocus();

    }

    public void interTableListe(int row, int col, int value, boolean open) {

        System.out.println("insert with value\n");

        Liste tmp = null;

        model.afficheMe();

        if (open)

            table.editCellAt(row, col);

        srcastra.astra.gui.components.celleditor.CellEditorInterface celledit = (srcastra.astra.gui.components.celleditor.CellEditorInterface) table.getColumnModel().getColumn(col).getCellEditor();

        //  celledit.reset();

        //  srcastra.astra.gui.components.celleditor.CellEditorInterface celledit=(srcastra.astra.gui.components.celleditor.CellEditorInterface)table.getCellEditor(row,col);

        //srcastra.astra.gui.components.celleditor.CellEditorInterface inter=(srcastra.astra.gui.components.celleditor.CellEditorInterface)celledit;

        celledit.getComponent().requestFocus();

        if (celledit.isListe())

            tmp = (Liste) celledit.getComponent();

        tmp.setCleUnik(value);

        //((Liste)celledit.getComponent()).setCleunik(value);

    }

    /**
     * Setter for property action.
     *
     * @param action New value of property action.
     */

    /* private void reloadTotal(){

       double base;

       double tva;

       for(int i=0;i<valeur.size();i++){

         Object[] tmp=(Object[])valeur.get(new Integer(i));   

           

       }

        

    }*/
    private void addEntryToValeur(Object key, Object obj) {

        if (valeur == null)

            valeur = new Hashtable();

        valeur.put(key, obj);

    }

    public MY_bigDecimal getNewSommeGlobal() {

        /*  value=0;

      tva=0;

      total=0;

      if(valeur==null)
          return getSommeGlobal();

      else{

          if(modeltva.getData()!=null){
           double tvaCredit=0;
           double tvaDebit=0;
        for(int i=0;i<modeltva.getData().size();i++){

            Tva_abrev_T tmp=(Tva_abrev_T)modeltva.getData().get(i);
          //  if(tmp.getDebitCredit().equals("D")) {


            //}
            value=MathRound.roundThisToDouble(value+tmp.getTva_base());

            tva=MathRound.roundThisToDouble(tva+tmp.getTva_value());

            total=MathRound.roundThisToDouble(total+tmp.getTva_base()+tmp.getTva_value());

           // System.out.println("valeur total ="+((Double)tmp[3]).doubleValue());

        }


       //  total=total*-1;

      }
          int journalType=frame.getCategoriejournal();
          String genDebitCredit="C";
          if(journalType==ParamComptable.JOURNAL_NCACHAT){
               genDebitCredit="D";
          }
          double valueCredit=0;
          double valueDebit=0;
   for(int i=0;i<model.getData().size();i++){
            Object[] tmpobj=(Object[])model.getData().get(i);
            if(tmpobj[3].toString().equals("C")){
                valueCredit=valueCredit+MathRound.roundThisToDouble(Double.parseDouble(tmpobj[2].toString()));
            }
            else if(tmpobj[3].toString().equals("D")){
                valueDebit=valueDebit+MathRound.roundThisToDouble(Double.parseDouble(tmpobj[2].toString()));
            }

        }
    //   frame.getGrp_Label_Base().setText(new Double(value).toString());

       if(frame.getAchatc()==null)

           frame.setAchatc(new Achat_T());

        frame.getAchatc().setTvamontant(tva);

         frame.getAchatc().setBase(value);

         frame.getGrp_Label_base().setText(new Double(Math.abs(value)).toString());
         frame.getGrp_Label_Tva().setText(new Double(Math.abs(tva)).toString());

//        frame.getGrp_Label_Base().setText(new Double(Math.abs(value)).toString());
         System.out.println("total "+total);

      }

       if(Math.abs(total)==Math.abs(getSommeGlobal())){

           frame.activeToolbar(1);

           showRechFrame=false;

       }

       else{

           showRechFrame=true;

           frame.activeToolbar(0);

       }

       return Math.abs(sommeGlobal)-total;*/
        int journalType = frame.getCategoriejournal();
        String genDebitCredit = "C";
        if (journalType == ParamComptable.JOURNAL_NCACHAT) {
            genDebitCredit = "D";
        }
        MY_bigDecimal retval = null;
        try {
            retval = Achat.getAchat().CalculSoldeComptable(frame.getGrp_TField_montanteur().getText(), genDebitCredit);
        } catch (AchatSoldeException an) {
            an.printStackTrace();
        }
        if (retval.doubleValue() == 0d) {
            frame.activeToolbar(1);
            showRechFrame = false;
        } else {
            showRechFrame = true;
            frame.activeToolbar(0);

        }


        MY_bigDecimal total = Achat.getAchat().getBase().add(Achat.getAchat().getTvamontant());
        frame.getGrp_Label_base().setText(Achat.getAchat().getBase().subtract(Achat.getAchat().getTvamontant()).toString());
        frame.getGrp_Label_Tva().setText(Achat.getAchat().getTvamontant().toString());
        frame.getGrp_Label_achat().setText(Achat.getAchat().getBase().toString());

//        frame.getGrp_Label_Base().setText(new Double(Math.abs(value)).toString());
        System.out.println("total " + total);


        return retval;

    }

    public void resume() {
        frame.getGrp_Label_dosVente().setText("0.00");
        frame.getGrp_Label_dosAchat().setText("0.00");
        frame.getGrp_Label_dosRent().setText("0.00");
        frame.getGrp_Label_prodAchat().setText("0.00");
        frame.getGrp_Label_prodRent().setText("0.00");
        frame.getGrp_Label_prodVente().setText("0.00");
        MY_bigDecimal debitDossier = new MY_bigDecimal("0.00");
        MY_bigDecimal creditDossier = new MY_bigDecimal("0.00");
        MY_bigDecimal debitProduit = new MY_bigDecimal("0.00");
        MY_bigDecimal creditProduit = new MY_bigDecimal("0.00");
        MY_bigDecimal dossierVente = new MY_bigDecimal("0.00");
        MY_bigDecimal produitVente = new MY_bigDecimal("0.00");
        if (frame.getGrp_Table_Achat().getRowCount() > 0) {
            int selectedRow = frame.getGrp_Table_Achat().getSelectedRow();
            AchatCp selected = (AchatCp) Achat.getAchat().getAchat().get(selectedRow);
            if (selected != null) {
                long cleDossier = selected.getCledossier();
                long prod = selected.getCleprod();
                int catprod = selected.getCatProd();
                if (Achat.getAchat().getAchat() != null) {
                    for (int i = 0; i < Achat.getAchat().getAchat().size(); i++) {
                        AchatCp achatcp = (AchatCp) Achat.getAchat().getAchat().get(i);
                        if (achatcp != null) {
                            if (achatcp.getCledossier() == cleDossier && cleDossier != 0) {
                                if (achatcp.getDc().equals("D")) {

                                    debitDossier = debitDossier.add(achatcp.getTva1().getPrixTot());
                                    if (achatcp.getCleprod() == prod && achatcp.getCatProd() == catprod) {
                                        debitProduit = debitProduit.add(achatcp.getTva1().getPrixTot());
                                    }
                                } else if (achatcp.getDc().equals("C")) {
                                    creditDossier = creditDossier.add(achatcp.getTva1().getPrixTot());
                                    if (achatcp.getCleprod() == prod && achatcp.getCatProd() == catprod) {
                                        creditProduit = creditProduit.add(achatcp.getTva1().getPrixTot());
                                    }
                                }

                            }
                        }
                    }

                }
                if (selected.getVenteRent() != null) {
                    if (selected.getVenteRent().getAchat() != null) {
                        for (int i = 0; i < selected.getVenteRent().getAchat().size(); i++) {
                            AchatCp achatcp = (AchatCp) selected.getVenteRent().getAchat().get(i);
                            if (achatcp != null) {
                                if (achatcp.getDc().equals("D")) {
                                    debitDossier = debitDossier.add(achatcp.getTva1().getPrixTot());
                                    if (achatcp.getCleprod() == prod && achatcp.getCatProd() == catprod) {
                                        debitProduit = debitProduit.add(achatcp.getTva1().getPrixTot());
                                    }
                                } else if (achatcp.getDc().equals("C")) {
                                    creditDossier = creditDossier.add(achatcp.getTva1().getPrixTot());
                                    if (achatcp.getCleprod() == prod && achatcp.getCatProd() == catprod) {
                                        creditProduit = creditProduit.add(achatcp.getTva1().getPrixTot());
                                    }
                                }
                            }
                        }
                    }
                    dossierVente = selected.getVenteRent().getVenteTotal();
                    produitVente = selected.getVenteRent().getVenteProduit();
                    //  produitVente.inverse();
                    frame.getGrp_Label_dosVente().setText(selected.getVenteRent().getVenteTotal().toString());
                    frame.getGrp_Label_prodVente().setText(selected.getVenteRent().getVenteProduit().toString());
                    if (selected.getVenteRent().getAchat() != null) {
                        frame.getModelfact().setData(selected.getVenteRent().getAchat());
                        frame.getGrp_Table_resume().tableChanged(new TableModelEvent(frame.getModelfact()));
                        if (frame.getGrp_Table_resume().getRowCount() > 0) {
                            frame.getGrp_Table_resume().changeSelection(frame.getGrp_Table_resume().getRowCount() - 1, 0, false, false);

                        }
                    }
                }
                MY_bigDecimal totalDossier = debitDossier.subtract(creditDossier);
                MY_bigDecimal totalProduit = debitProduit.subtract(creditProduit);
                MY_bigDecimal rentaDossier = Rentabilite.getRentabilite(totalDossier, dossierVente);
                MY_bigDecimal rentaProduit = Rentabilite.getRentabilite(totalProduit, produitVente);
                frame.getGrp_Label_dosRent().setText(rentaDossier.toString());
                frame.getGrp_Label_dosAchat().setText(totalDossier.toString());
                frame.getGrp_Label_prodRent().setText(rentaProduit.toString());
                frame.getGrp_Label_prodAchat().setText(totalProduit.toString());
            }
        }


    }

    private double calculDebitCredit(String geneDebCredit, double tvaValue, double valueDebit, double valueCredit) {
        System.out.println("[***************] CALCUL DEBIT CREDIT:\n\n");
        return 0;

    }

    public void setAction(ActionListener action) {

        this.action = action;

    }

    public void editingCanceled(javax.swing.event.ChangeEvent changeEvent) {

        System.out.println("editing canceled");

    }

    private void checkValue(CellEditorInterface inter) {

        TvaReturnValue retval = null;
        int row = table.getSelectedRow();
        Liste tmp = (Liste) inter.getComponent();
        Object[] tmp2 = null;
        AchatCp achatcp = (AchatCp) Achat.getAchat().getAchat().get(row);
        achatcp.resetTva();

        Object debit = achatcp.getDc();
        //    if(valeur==null)
        //      tmp2=null;
        // else
        //  tmp2=(Object[])valeur.get(new Integer(row));
        // if(tmp2==null){
        float tva = getTvaPrct(tmp.getCleUnik(), tvaf);
        MY_bigDecimal tmpBig = getNewSommeGlobal();

        if (achatcp.getBase().toString().equals("0.00")) {
            retval = tvaCalcul.TvaReturnValue2(tva, tmpBig.doubleValue());
            achatcp.setDc(tmpBig.getDc());
        } else {
            retval = tvaCalcul.TvaReturnValue3(tva, achatcp.getBase().doubleValue());
        }

        //   retval=sign(debit,retval);

        // tmp2=new Object[]{new Float(tva),new Double(retval.montant_Tva),new Double(retval.montantHtva),new Double(retval.montant_TvaCompris)};

        //addEntryToValeur(new Integer(table.getSelectedRow()),tmp2);

        model.setValueTva(table.getSelectedRow(), new Integer(tmp.getCleUnik()), new Float(tva), new Double(retval.montantHtva), new Double(retval.montant_Tva), new Double(retval.montant_TvaCompris), tmp.getText());
        table.changeSelection(row, 0, false, false);
        System.out.println("tablecount " + table.getRowCount() + "  selectedrow" + row);
        interTable(row, inter.getPosition() + 1, new Double(Math.abs(retval.montantHtva)).toString(), true);

        /* }

        else{

               float tva= getTvaPrct(tmp.getCleUnik(),tvaf);

               double tmp3=((Double)tmp2[3]).doubleValue();

               retval=tvaCalcul.TvaReturnValue2(tva,tmp3);

               retval=sign(debit,retval);

               tmp2=new Object[]{new Float(tva),new Double(retval.montant_Tva),new Double(retval.montantHtva),new Double(retval.montant_TvaCompris)};

               addEntryToValeur(new Integer(table.getSelectedRow()),tmp2);

               model.setValueTva(table.getSelectedRow(),new Integer(tmp.getCleUnik()),new Float(tva),new Double(retval.montantHtva),new Double(retval.montant_Tva),new Double(retval.montant_TvaCompris),tmp.getText());

               table.changeSelection(row,0,false,false);

               System.out.println("tablecount "+table.getRowCount()+"  selectedrow"+row);

               interTable(row,inter.getPosition()+1,new Double(retval.montantHtva).toString(),true);

        }*/

    }

    public void updateListe(CellEditorInterface inter) {
        TvaReturnValue retval = null;
        int row = table.getSelectedRow();
        if (inter.isListe()) {
            Liste tmp = (Liste) inter.getComponent();
            if (inter.isTva()) {
                checkValue(inter);
                updateTvaListe();
                getNewSommeGlobal();
                resume();
            } else {

                model.setValueCompte(table.getSelectedRow(), new Integer(tmp.getCleUnik()), tmp.getText());

                table.changeSelection(row, 0, false, false);

                interTable(table.getSelectedRow(), inter.getPosition() + 1);

            }

        } else {

            if (!inter.isMontant()) {

                updateValueInModel(inter.getCellEditorValue(), table.getSelectedRow(), inter.getPosition());
                getNewSommeGlobal();
                updateTvaListe();
                /*   if(inter.getPosition()==3){
                  if(inter.getCellEditorValue().toString().equals("C")){
                    Object montant=model.getValueAt2(row,2);
                    setMontant(null,row,retval,(Double)montant);
                  }

                  else{
                    Object montant=model.getValueAt2(row,2);
                    setMontant(null,row,retval,(Double)montant);
                  }



                }*/

            } else {
                setMontant(inter, row, retval, null);


            }

            table.changeSelection(row, 0, false, false);

            interTable(table.getSelectedRow(), inter.getPosition() + 1);
            resume();
        }

    }

    private void setMontant(CellEditorInterface inter, int row, TvaReturnValue retval, Double montant) {

        //    Object[] tab=(Object[])valeur.get(new Integer(row));
        try {
            double montantTmp = Double.parseDouble(inter.getCellEditorValue().toString());
        } catch (NumberFormatException nn) {
            return;
        }
        AchatCp achatcp = (AchatCp) Achat.getAchat().getAchat().get(row);
        //achatcp.resetTva();
        Object debit = achatcp.getDc();
        int tva_cleunik = achatcp.getTva1().getTva_id();
        float tva = getTvaPrct(tva_cleunik, tvaf);
        String tvacode = achatcp.getTva1().getTva_code();
        achatcp.resetTva();

        //   if(tab!=null)

        //   tva=((Float)tab[0]).floatValue();

        //   if(montant==null)

        retval = tvaCalcul.TvaReturnValue3(tva, Double.parseDouble(inter.getCellEditorValue().toString()));

        // else

        //  retval=tvaCalcul.TvaReturnValue3(tva,montant.doubleValue());

        // retval=sign(debit,retval);

        //       Object[] tmp2=new Object[]{new Float(tva),new Double(MathRound.roundThisToDouble(retval.montant_Tva)),new Double(MathRound.roundThisToDouble(retval.montantHtva)),new Double(MathRound.roundThisToDouble(retval.montant_TvaCompris))};

        //     addEntryToValeur(new Integer(table.getSelectedRow()),tmp2);
        model.setValueTva(table.getSelectedRow(), new Integer(tva_cleunik), new Float(tva), new Double(retval.montantHtva), new Double(retval.montant_Tva), new Double(retval.montant_TvaCompris), tvacode);
        // model.setValueMontant(row,new Double(MathRound.roundThisToDouble(retval.montantHtva)),new Double(MathRound.roundThisToDouble(retval.montant_Tva)),new Double(MathRound.roundThisToDouble(retval.montant_TvaCompris)));

        updateTvaListe();

        getNewSommeGlobal();

    }

    private TvaReturnValue sign(Object debit, TvaReturnValue retval) {
        // retval.setDebitCredit(debit.toString());
        if (frame.getCategoriejournal() == ParamComptable.JOURNAL_ACHAT) {

            if (debit != null)

                if (debit.toString().equals("C")) {

                    retval.montantHtva = Math.abs(retval.montantHtva) * -1;

                    retval.montant_Tva = Math.abs(retval.montant_Tva) * -1;

                    retval.montant_TvaCompris = Math.abs(retval.montant_TvaCompris) * -1;

                }

            if (debit.toString().equals("D")) {

                retval.montantHtva = Math.abs(retval.montantHtva);

                retval.montant_Tva = Math.abs(retval.montant_Tva);

                retval.montant_TvaCompris = Math.abs(retval.montant_TvaCompris);

            }

        } else if (frame.getCategoriejournal() == ParamComptable.JOURNAL_NCACHAT) {

            if (debit.toString().equals("C")) {

                retval.montantHtva = Math.abs(retval.montantHtva);

                retval.montant_Tva = Math.abs(retval.montant_Tva);

                retval.montant_TvaCompris = Math.abs(retval.montant_TvaCompris);

            }

            if (debit.toString().equals("D")) {

                retval.montantHtva = Math.abs(retval.montantHtva) * -1;

                retval.montant_Tva = Math.abs(retval.montant_Tva) * -1;

                retval.montant_TvaCompris = Math.abs(retval.montant_TvaCompris) * -1;

            }

        }

        return retval;

    }

    public void editingStopped(javax.swing.event.ChangeEvent changeEvent) {


    }

    public void action(CellEditorInterface inter) {

        System.out.println("fucking action received");

        int row = table.getSelectedRow();

        if (inter.getPosition() < table.getColumnCount() - 3) {
            updateListe(inter);

        } else

        {

            updateValueInModel(inter.getCellEditorValue(), table.getSelectedRow(), inter.getPosition());
            if (table.getRowCount() == row + 1)
                model.addData();
            table.tableChanged(new javax.swing.event.TableModelEvent(model));
            row = row + 1;
            table.changeSelection(row, 0, false, false);
            interTable(row, 0);
            resume();
        }

    }

    public void updateTvaListe() {

        //   Logger.getDefaultLogger().log(Logger.LOG_INFOS,"UPDATE de la liste de tva");

        modeltva.setData(null);

        table2.tableChanged(new javax.swing.event.TableModelEvent(modeltva));


    }


    boolean sw;

    private void updateValueInModel(Object obj, int row, int col) {

        model.setValueAt(row, col, obj);

        table.tableChanged(new javax.swing.event.TableModelEvent(model));

    }


    /**
     * Getter for property table.
     *
     * @return Value of property table.
     */

    public javax.swing.JTable getTable() {

        return table;

    }


    /**
     * Setter for property table.
     *
     * @param table New value of property table.
     */

    public void setTable(javax.swing.JTable table) {

        this.table = table;

    }


    /**
     * Getter for property sommeGlobal.
     *
     * @return Value of property sommeGlobal.
     */

    public double getSommeGlobal() {
        sommeGlobal = 0;
        try {
            double tmp = new Double(frame.getGrp_TField_montanteur().getText()).doubleValue();
        } catch (NumberFormatException nn) {
            sommeGlobal = 0;

        }
        return sommeGlobal;


    }


    /**
     * Setter for property sommeGlobal.
     *
     * @param sommeGlobal New value of property sommeGlobal.
     */

    public void setSommeGlobal(double sommeGlobal) {

        this.sommeGlobal = sommeGlobal;

    }


    ActionListener action = new ActionListener() {

        public void actionPerformed(ActionEvent evt) {

        }

    };

    private float getTvaPrct(int cleunik, TvaFrame frame) {

        Object[] tmpachat = (Object[]) frame.getAchathash().get(new Integer(cleunik));

        float tva = 0.0f;

        try {

            tva = Float.parseFloat(tmpachat[5].toString());

            Logger.getDefaultLogger().log(Logger.LOG_INFOS, "[OOOOOOOOOOOOOO] valeur tva " + tva + "   Object " + tmpachat[5].toString());

        }

        catch (NumberFormatException nn) {

            nn.printStackTrace();


        }

        return tva;

    }


    /**
     * Getter for property tvaf.
     *
     * @return Value of property tvaf.
     */

    public srcastra.astra.gui.components.tva.TvaFrame getTvaf() {

        return tvaf;

    }


    /**
     * Setter for property tvaf.
     *
     * @param tvaf New value of property tvaf.
     */

    public void setTvaf(srcastra.astra.gui.components.tva.TvaFrame tvaf) {

        this.tvaf = tvaf;

    }


    /**
     * Getter for property mouse.
     *
     * @return Value of property mouse.
     */

    public java.awt.event.MouseAdapter getMouse() {

        return mouse;

    }


    /**
     * Setter for property mouse.
     *
     * @param mouse New value of property mouse.
     */

    public void setMouse(java.awt.event.MouseAdapter mouse) {

        this.mouse = mouse;

    }


    /**
     * Getter for property table2.
     *
     * @return Value of property table2.
     */

    public javax.swing.JTable getTable2() {

        return table2;

    }


    /**
     * Setter for property table2.
     *
     * @param table2 New value of property table2.
     */

    public void setTable2(javax.swing.JTable table2) {

        this.table2 = table2;

    }


    /**
     * Getter for property modeltva.
     *
     * @return Value of property modeltva.
     */

    public srcastra.astra.gui.modules.compta.achat.TvaTableModel getModeltva() {

        return modeltva;

    }


    /**
     * Setter for property modeltva.
     *
     * @param modeltva New value of property modeltva.
     */

    public void setModeltva(srcastra.astra.gui.modules.compta.achat.TvaTableModel modeltva) {

        this.modeltva = modeltva;

    }


    /**
     * Getter for property model.
     *
     * @return Value of property model.
     */

    public srcastra.astra.gui.modules.compta.achat.AchatTableModel getModel() {

        return model;

    }


    /**
     * Setter for property model.
     *
     * @param model New value of property model.
     */

    public void setModel(srcastra.astra.gui.modules.compta.achat.AchatTableModel model) {

        this.model = model;

    }


    /**
     * Getter for property value.
     *
     * @return Value of property value.
     */

    public double getValue() {

        return value;

    }


    /**
     * Setter for property value.
     *
     * @param value New value of property value.
     */

    public void setValue(double value) {

        this.value = value;

    }


    /**
     * Getter for property tva.
     *
     * @return Value of property tva.
     */

    public double getTva() {

        return tva;

    }


    /**
     * Setter for property tva.
     *
     * @param tva New value of property tva.
     */

    public void setTva(double tva) {

        this.tva = tva;

    }


    /**
     * Getter for property frame.
     *
     * @return Value of property frame.
     */

    public srcastra.astra.gui.modules.compta.achat.Achat getFrame() {

        return frame;

    }


    /**
     * Setter for property frame.
     *
     * @param frame New value of property frame.
     */

    public void setFrame(srcastra.astra.gui.modules.compta.achat.Achat frame) {

        this.frame = frame;

    }


    /**
     * Getter for property valeur.
     *
     * @return Value of property valeur.
     */

    public java.util.Hashtable getValeur() {

        return valeur;

    }


    /**
     * Setter for property valeur.
     *
     * @param valeur New value of property valeur.
     */

    public void setValeur(java.util.Hashtable valeur) {

        this.valeur = valeur;

    }


    /**
     * Getter for property jota_categorie.
     *
     * @return Value of property jota_categorie.
     */

    public int getJota_categorie() {

        return jota_categorie;

    }


    /**
     * Setter for property jota_categorie.
     *
     * @param jota_categorie New value of property jota_categorie.
     */

    public void setJota_categorie(int jota_categorie) {

        this.jota_categorie = jota_categorie;

    }



}

