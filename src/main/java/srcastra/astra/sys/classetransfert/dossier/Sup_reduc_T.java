/*







 * Sup_reduc_T.java







 *







 * Created on 7 octobre 2002, 11:10







 */















package srcastra.astra.sys.classetransfert.dossier;







import srcastra.astra.gui.test.*;







import srcastra.astra.sys.classetransfert.Grpdecision_T;







import srcastra.astra.sys.classetransfert.dossier.produit_T;



import srcastra.astra.sys.compta.*;















/**







 *







 * @author  Thomas







 */







public class Sup_reduc_T implements java.io.Serializable,Cloneable,srcastra.astra.sys.classetransfert.dossier.InterfaceProduit{







    







    /** Creates a new instance of Sup_reduc_T */







    public Sup_reduc_T() {
        qua=1;
        pax=1;
        prct=100;  
        this.parent= parent;
        setLonvalue(System.currentTimeMillis());
    }







     public Object clone()throws CloneNotSupportedException{







       return super.clone(); 







    }







    /** Getter for property aclibelle.







     * @return Value of property aclibelle.







     */







    public int getAclibelle() {







        return aclibelle;







    }







    







    /** Setter for property aclibelle.







     * @param aclibelle New value of property aclibelle.







     */







    public void setAclibelle(int aclibelle) {







        this.aclibelle = aclibelle;







    }







    







    /** Getter for property freeLibelle.







     * @return Value of property freeLibelle.







     */







    public java.lang.String getFreeLibelle() {







        return freeLibelle;







    }







    







    /** Setter for property freeLibelle.







     * @param freeLibelle New value of property freeLibelle.







     */







    public void setFreeLibelle(java.lang.String freeLibelle) {







        this.freeLibelle = freeLibelle;







    }







    







    /** Getter for property modifyreccord.







     * @return Value of property modifyreccord.







     */







    public boolean isModifyreccord() {







        return modifyreccord;







    }







    







    /** Setter for property modifyreccord.







     * @param modifyreccord New value of property modifyreccord.







     */







    public void setModifyreccord(boolean modifyreccord) {







        this.modifyreccord = modifyreccord;







    }







    







    /** Getter for property newreccord.







     * @return Value of property newreccord.







     */







    public boolean isNewreccord() {







        return newreccord;







    }







    







    /** Setter for property newreccord.







     * @param newreccord New value of property newreccord.







     */







    public void setNewreccord(boolean newreccord) {







        this.newreccord = newreccord;







    }







    







    /** Getter for property prix.







     * @return Value of property prix.







     */







  //  public float getPrix() {







    //    return new Double(prix).floatValue();







    //}







     public double getPrix() {







        return prix;







    }







    







    /** Setter for property prix.







     * @param prix New value of property prix.







     */







    public void setPrix(double prix) {







        this.prix = prix;







    }







    







    /** Getter for property reduc.







     * @return Value of property reduc.







     */







    public int getReduc() {







        return reduc;







    }







    







    /** Setter for property reduc.







     * @param reduc New value of property reduc.







     */







    public void setReduc(int reduc) {







        this.reduc = reduc;







    }







    







    /** Getter for property sup.







     * @return Value of property sup.







     */







    public int getSup() {







        return sup;







    }







    







    /** Setter for property sup.







     * @param sup New value of property sup.







     */







    public void setSup(int sup) {







        this.sup = sup;







    }







    







    /** Getter for property sup_reduc_cleunik.







     * @return Value of property sup_reduc_cleunik.







     */







    public long getSup_reduc_cleunik() {







        return sup_reduc_cleunik;







    }







    







    /** Setter for property sup_reduc_cleunik.







     * @param sup_reduc_cleunik New value of property sup_reduc_cleunik.







     */







    public void setSup_reduc_cleunik(long sup_reduc_cleunik) {







        this.sup_reduc_cleunik = sup_reduc_cleunik;







    }







   







    /** Getter for property produitaffichage.







     * @return Value of property produitaffichage.







     */







    public ProduitAffichage getProduitaffichage() {







        return produitaffichage;







    }







    







    /** Setter for property produitaffichage.







     * @param produitaffichage New value of property produitaffichage.







     */







    public void setProduitaffichage(ProduitAffichage produitaffichage) {







        this.produitaffichage = produitaffichage;







    }







    







    /** Getter for property valeur_tot.







     * @return Value of property valeur_tot.







     */







    public double getValeur_tot() {







        return valeur_tot;







    }







    







    /** Setter for property valeur_tot.







     * @param valeur_tot New value of property valeur_tot.







     */







    public void setValeur_tot(double valeur_tot) {







        this.valeur_tot = valeur_tot;







    }







    







    public void addSup_Reduc(Sup_reduc_T supreduc) {







    }







    







    public void deleteSup_Reduc(Sup_reduc_T supreduc) {







    }







    







    public long getAt_cleunik() {







        return getSup_reduc_cleunik();







    }







    







    public int getFrcleunik() {







         return 0;







    }







    







    public int getFrgtcleunik() {







         return 0;







    }







    







    public String getFrnom() {







         return null;







    }







    







    public Grpdecision_T getGroupdec() {







         return grpdec;







    }







    







    public String getGroupe_produit_nom() {







         return null;







    }







    







    public int getNumber_int_array() {







         return 0;







    }







    







    public int getTypeDeProduitCleunik() {







         return parent.getTypeDeProduitCleunik();







    }







    







    public String getTypeDeProduitNom() {







         return null;







    }







    







    public void modifySupReduc(Sup_reduc_T supreduc) {







       







    }







    







    public void setAt_cleunik(long at_cleunik) {







        this.sup_reduc_cleunik=at_cleunik;







    }







    







    public void setFrcleunik(int frcleunik) {







        







    }







    







    public void setFrgtcleunik(int frgtcleunik) {







         







    }







    







    public void setFrnom(String frnom) {







    }







    







    public void setGroupdec(Grpdecision_T groupdec) {







        this.grpdec=groupdec;







    }







    







    public void setGroupe_produit_nom(String grpnom) {







    }







    







    public void setNumber_int_array(int number_int_array) {







    }







    







    public void setTypeDeProduitCleunik(int produitcleunik) {







    }







    







    public void setTypeDeProduitNom(String produitnom) {







    }







    







    public double updateFieldsFromTable(int qua, int pax, float prct,float valeur_tot) {







        this.qua=qua;







        this.pax=pax;







        this.prct=prct;







        this.valeur_tot=valeur_tot;







        return  calculMontantTotal();







    }







    







    /** Getter for property parent.







     * @return Value of property parent.







     */ 







    public srcastra.astra.sys.classetransfert.dossier.produit_T getParent() {







        return parent;







    }







    







    /** Setter for property parent.







     * @param parent New value of property parent.







     */







    public void setParent(srcastra.astra.sys.classetransfert.dossier.produit_T parent) {







        this.parent = parent;







    }







    







    /** Getter for property soumis_tva.







     * @return Value of property soumis_tva.







     */







    public int getSoumis_tva() {







        return soumis_tva;







    }







    







    /** Setter for property soumis_tva.







     * @param soumis_tva New value of property soumis_tva.







     */







    public void setSoumis_tva(int soumis_tva) {







        this.soumis_tva = soumis_tva;







    }







     public double calculMontantTotal(){



          return TvaGrpDec.calcul(this);



     /*     if(getGroupdec().getGntvainclusvente()==0)







          {







          /*  montant_tva=((valeur_tot)/100)*parent.getGroupdec().getValeurGenFloat1(); 







            montant_tva=srcastra.astra.sys.compta.MathRound.roundThisToFloat(montant_tva);







            valeur_tot_tva_inc=valeur_tot+montant_tva;







            valeur_tot_tva_inc=srcastra.astra.sys.compta.MathRound.roundThisToFloat(valeur_tot_tva_inc);           







            return valeur_tot_tva_inc;







             srcastra.astra.sys.compta.TvaReturnValue tmp= srcastra.astra.sys.compta.tvaCalcul.TvaReturnValue(getGroupdec().getValeurGenFloat1(),valeur_tot);







             montant_tva=tmp.montant_Tva;







             valeur_tot_tva_inc=tmp.montant_TvaCompris;







             return tmp.montant_TvaCompris;







          }







          else{







            valeur_tot_tva_inc=valeur_tot; 







            valeur_tot_tva_inc=srcastra.astra.sys.compta.MathRound.roundThisToDouble(valeur_tot_tva_inc);







            return valeur_tot_tva_inc;







          }*/







        }







    







     /** Getter for property valeur_tot_tva_inc.







      * @return Value of property valeur_tot_tva_inc.







      */







     public double getValeur_tot_tva_inc() {







         return valeur_tot_tva_inc;







     }







     







     /** Setter for property valeur_tot_tva_inc.







      * @param valeur_tot_tva_inc New value of property valeur_tot_tva_inc.







      */







     public void setValeur_tot_tva_inc(double valeur_tot_tva_inc) {







         this.valeur_tot_tva_inc = valeur_tot_tva_inc;







     }







     







     /** Getter for property montant_tva.







      * @return Value of property montant_tva.







      */







     public double getMontant_tva() {







         return montant_tva;







     }







     







     /** Setter for property montant_tva.







      * @param montant_tva New value of property montant_tva.







      */







     public void setMontant_tva(double montant_tva) {







         this.montant_tva = montant_tva; 







     }







     







     public boolean isIsnewreccord() {







         return newreccord;







     }







     







     public boolean isModify() {







         return modifyreccord;







     }







     







     public void setIsnewreccord(boolean isnewreccord) {







         newreccord=isnewreccord;







     }







     







     public void setModify(boolean modify) {







         modifyreccord=modify;







     }







     







     public boolean isDeleted() {







         return deleted;







     }







     







     public void setDeleted(boolean deleted) {
        if(isIsnewreccord()){
            parent.getSup_reduc().remove(new Long(this.getSup_reduc_cleunik()));
        }else{
            this.deleted=deleted;
        }







     }







     







     public java.util.Hashtable getSup_reduc() {







         return null;







     }







     







     /** Getter for property sup_tva.







      * @return Value of property sup_tva.







      */







     public float getSup_tva() {







         return sup_tva;







     }







     







     /** Setter for property sup_tva.







      * @param sup_tva New value of property sup_tva.







      */







     public void setSup_tva(float sup_tva) {







         this.sup_tva = sup_tva;







     }







     







     /** Getter for property sup_statutCleuUnik.







      * @return Value of property sup_statutCleuUnik.







      */







     public int getSup_statutCleuUnik() {







         return sup_statutCleuUnik; 







     }







     







     /** Setter for property sup_statutCleuUnik.







      * @param sup_statutCleuUnik New value of property sup_statutCleuUnik.







      */







     public void setSup_statutCleuUnik(int sup_statutCleuUnik) {







         this.sup_statutCleuUnik = sup_statutCleuUnik;







     }







     







     public srcastra.astra.sys.classetransfert.Document_T getDoc() {







         return null;







     }







     







     public void setDoc(srcastra.astra.sys.classetransfert.Document_T doc) {







     }







     







     public srcastra.astra.sys.classetransfert.Grpdecision_T getGroupdecBase() {







         return null;







     }







     







     public void setGroupdecBase(srcastra.astra.sys.classetransfert.Grpdecision_T groupdecBase) {







     }







     public double getAt_val_vente() {

         return prix;



     }     



     



     public void setAt_val_vente(double at_val_vente) {



         prix=at_val_vente;



     }     







     /** Getter for property pax.



      * @return Value of property pax.



      */



     public int getPax() {



         return pax;



     }



     



     /** Setter for property pax.



      * @param pax New value of property pax.



      */



     public void setPax(int pax) {



         this.pax = pax;



     }



     



     /** Getter for property qua.



      * @return Value of property qua.



      */



     public int getQua() {



         return qua;



     }



     



     /** Setter for property qua.



      * @param qua New value of property qua.



      */



     public void setQua(int qua) {



         this.qua = qua;



     }



     



     /** Getter for property prct.



      * @return Value of property prct.



      */



     public float getPrct() {



         return prct;



     }



     



     /** Setter for property prct.



      * @param prct New value of property prct.



      */



     public void setPrct(float prct) {



         this.prct = prct;



     }



     



     public void setLibelleCompta(java.lang.String libelleCompta) {



     }



     



     public void setStatut(int statut) {



     }

     /** Getter for property lonvalue.
      * @return Value of property lonvalue.
      *
      */
     public long getLonvalue() {
         return lonvalue;
     }     

     /** Setter for property lonvalue.
      * @param lonvalue New value of property lonvalue.
      *
      */
     public void setLonvalue(long lonvalue) {
         this.lonvalue = lonvalue;
     }     

    

     



     /** Getter for property grpdec.







      * @return Value of property grpdec.







      */







   







     /** Getter for property Grpdecision_T.







      * @return Value of property Grpdecision_T.







      */







 







     







    private long sup_reduc_cleunik;







    private double prix;







    private int sup;







    private int reduc;







    private String freeLibelle;







    private int aclibelle;







    private boolean newreccord;







    private boolean modifyreccord;   







    private boolean deleted;







    private transient srcastra.astra.sys.classetransfert.dossier.ProduitAffichage produitaffichage;







    public int qua;







    public int pax;







    public float prct;







    private double valeur_tot;







    private double montant_tva;







    private double valeur_tot_tva_inc; 







    private produit_T parent;







    private int soumis_tva;







    private float sup_tva;







    private int sup_statutCleuUnik;
    private long lonvalue;






    private  Grpdecision_T grpdec; 
    public final static int TAXLOCALE=-1;
    public final static int TAXDEST=-2;
    public final static int TAXCOMP=-3;
    public final static int TAXREM=-5;
    public final static int TAXFESS=-4;
    public final static int TAXVOIDFEES=-6;
    public final static int TAXREFUND=-7;
    public final static int TAXREFUNDTAXCOMP=-8;
     public final static int TAXLOCALEBACK=-9;






}







