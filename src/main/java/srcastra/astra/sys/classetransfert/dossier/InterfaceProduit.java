/*



 * InterfaceProduit.java



 *



 * Created on 4 octobre 2002, 12:45



 */







package srcastra.astra.sys.classetransfert.dossier;



import srcastra.astra.sys.classetransfert.Grpdecision_T;







/**



 *



 * @author  Thomas



 */



public interface InterfaceProduit extends InterfaceProduitConstante {



    void setFrgtcleunik(int frgtcleunik);



    int getFrgtcleunik();



    void setGroupe_produit_nom(String grpnom);



    String getGroupe_produit_nom();



    void setFrcleunik(int frcleunik);



    int getFrcleunik();



    void setFrnom(String frnom);       



    String getFrnom();



    int getTypeDeProduitCleunik();



    void setTypeDeProduitCleunik(int produitcleunik);



    String getTypeDeProduitNom();   



    void setTypeDeProduitNom(String produitnom);



    public Grpdecision_T getGroupdec();



    public void setGroupdec(Grpdecision_T groupdec);



    public void addSup_Reduc(Sup_reduc_T supreduc);



    public void modifySupReduc(Sup_reduc_T supreduc);



    public void deleteSup_Reduc(Sup_reduc_T supreduc);



    public long getAt_cleunik();



    public void setAt_cleunik(long at_cleunik);



    public int getNumber_int_array();



    public void setNumber_int_array(int number_int_array);



    public double updateFieldsFromTable(int qua,int pax,float prct,float valeur_tot);



    double calculMontantTotal();



    double getMontant_tva() ;



    double getValeur_tot_tva_inc();



    double getValeur_tot() ;



    boolean isIsnewreccord(); 



    void setIsnewreccord(boolean isnewreccord);



    boolean isModify();



    void setModify(boolean modify);



    boolean isDeleted();



    void setDeleted(boolean deleted);



    java.util.Hashtable getSup_reduc();



    public srcastra.astra.sys.classetransfert.Document_T getDoc() ;     



    public void setDoc(srcastra.astra.sys.classetransfert.Document_T doc); 



    public srcastra.astra.sys.classetransfert.Grpdecision_T getGroupdecBase();

    public void setLibelleCompta(java.lang.String libelleCompta);

    public void setStatut(int statut) ;

    

    public void setGroupdecBase(srcastra.astra.sys.classetransfert.Grpdecision_T groupdecBase);

    public void setMontant_tva(double montant_tva);

    public void setValeur_tot(double valeur_tot) ;

    public void setValeur_tot_tva_inc(double valeur_tot_tva_inc) ;

    public double getAt_val_vente() ;

    public void setAt_val_vente(double at_val_vente);

    public int getPax() ;

    public void setPax(int pax) ;        

    public int getQua()   ; 

    public void setQua(int qua) ;

    public float getPrct() ;

    public void setPrct(float prct) ;

    public void setParent(srcastra.astra.sys.classetransfert.dossier.produit_T parent);

    public void setReduc(int reduc);

    public void setSup(int sup);
   

   



      



}



