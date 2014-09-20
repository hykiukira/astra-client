/*















 * astrainterface.java















 *















 * Created on 7 mars 2002, 11:54















 */















package srcastra.astra.sys.rmi;















import java.rmi.*;















import srcastra.astra.sys.classetransfert.*;















import srcastra.astra.sys.classetransfert.signaletique.*;















import srcastra.astra.sys.compress.CompressArray;















import srcastra.astra.sys.rmi.utils.*;
import java.util.*;














import srcastra.astra.sys.rmi.Exception.*;































//import srcastra.astra.sys.rmi.socketfactory.*;















/** Remote interface.















 *















 * @author thom















 * @version 1.0















 */















public interface astrainterface extends java.rmi.Remote , srcastra.astra.sys.manipuleclient.ClientConstante {















    Loginusers_T[] returnusers(int numentite) throws RemoteException;















    Loginusers_T userautorisation(Loginusers_T user,Userinfo_T infodistante) throws RemoteException;    















    boolean userlogof(int urcleunik) throws RemoteException;















    Gestionerreur_T modifyfournisseur(Fournisseur_T fourn,int urcleunik) throws RemoteException ;















    Gestionerreur_T modifyFournisseurMemo(String memo, int frcleunik, int urcleunik) throws RemoteException;















    Gestionerreur_T modifydocument(Document_T doc,int urcleunik) throws RemoteException;















    Gestionerreur_T modifyfourncontact(FournContact_T fourcon,int urcleunik) throws RemoteException,ServeurSqlFailure;















    boolean deletefournisseur(int focleunik) throws RemoteException;















    java.util.ArrayList renvcombo(char typedecombo,int urcleunik,int urlmcleunik,char plettre,String cxcode,int cas) throws RemoteException;















    Gestionerreur_T insertdocument(Document_T doc,int urcleunik) throws RemoteException;















    //Gestionerreur_T insertgroupedecision(Grpdecision_T grpd,java.sql.Connection usercon,String numsess) throws RemoteException;















    public Gestionerreur_T insertfourncontact(FournContact_T fourcon,int urcleunik) throws RemoteException,ServeurSqlFailure;















    Gestionerreur_T insertfournisseur(Fournisseur_T fourn,int urcleunik) throws RemoteException;















    java.util.ArrayList renvcombofourncontact(int urcleunik,char plettre,int cas,int frcleunik) throws RemoteException;















    java.util.ArrayList renvComboTest(char typedecombo,int urcleunik,int urlmcleunik,char plettre,String cxcode,int cas) throws RemoteException;















    Gestionerreur_T insertObjectPopup(Object objdp,int urcleunik,int TypObject,int cas) throws RemoteException;















    //CodePost_T ChargeCodePostaux(int urlmcleunik,int urcleunik,int cxcleunik,int cas)throws RemoteException;















    //oid ValidModifCodePost(int urcleunik,CodePost_T cpt) throws RemoteException;















    //void modifyCodePostaux (int urcleunik,CodePost_T cdpt) throws RemoteException;















    Object ChargeObject(int urlmcleunik,int urcleunik,int objcleunik,int cas,int comboConstante)throws RemoteException;















    Object chargeObjetCombo(int objectCleunik,int urcleunik,int urlmcleunik,int comboConstante) throws RemoteException; 















    Object ChargeObjectPopup(int urlmcleunik,int urcleunik,int objcleunik,int cas,int comboConstante)throws RemoteException;















    Gestionerreur_T modifyGrpDecision(Grpdecision_T grpd,int urcleunik) throws RemoteException;















    CompressArray renvSignalitiques(int urlmcleunik,int urcleunik,int cas,int typedesignalitique) throws RemoteException;















    //Gestionerreur_T InsertObjectCombo(Object objectTransfert,int urcleunik,int urlmcleunik,int comboConstante,int urights) throws RemoteException;















    //Gestionerreur_T insertObject(Object objdp,int urcleunik,int TypObject,int cas) throws RemoteException;















    void remoterollback(int urcleunik) throws RemoteException;















    Gestionerreur_T modifyObjectPopup(Object objdp,int urcleunik,int TypObject,int cas) throws RemoteException;















    java.util.ArrayList renvLangueSystem(int urcleunik,int urlmcleunik,int tmpint) throws RemoteException;















    java.util.ArrayList renvIntitule(int  urlmcleunik,int urcleunik,int cas,int typedesignalitique,int caecleunik) throws RemoteException; 















    //void registerBufferNotifier (int urcleunik, RemoteBufferWatcher watcher) throws RemoteException;















    boolean isBufferOk (String reference, long timestamp, long currenttime, int urcleunik) throws RemoteException;  



    CompressArray renvClient(int cas,int[] listeParam,int urcleunik,int lmcleunik) throws RemoteException, ServeurSqlFailure;



    Object ChargeClient(int cas, int[] listeParam, int urcleunik, int lmcleunik) throws RemoteException, ServeurSqlFailure; 



    int insertClient(int cas, int urcleunik,int[] param,Object objcli) throws RemoteException, ServeurSqlFailure;



    srcastra.astra.sys.document.Genere_Doc edition(int key,int objcleunik,int urcleunik) throws RemoteException;



    void ModifyClient(int cas, int urcleunik,int[] param,Object objcli) throws RemoteException, ServeurSqlFailure;



    void deleteClient(int cas, int urcleunik,int[] param) throws RemoteException, ServeurSqlFailure;



    void deleteSignaletique(String table,String nomcleunik,int urcleunik,int checkCode,int cleunik,int typeObjet) throws RemoteException, ServeurSqlFailure;



    FournisseurRmiInterface renvFournisseurRmiObject(int urCleunik) throws RemoteException;



    DossierRmiInterface renvDossierRmiObject(int urCleunik) throws RemoteException;



    CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, int typedesignalitique,boolean x) throws RemoteException;



    java.util.ArrayList renvcombo(char typedecombo,int urcleunik,int urlmcleunik,char plettre,String cxcode,int cas,boolean x) throws RemoteException;



    public srcastra.astra.sys.classetransfert.FournMemo_T renvFournisseurMemo(int frcleunik, int urcleunik) throws RemoteException;



    Object chargeAllclient(int cas, int[] listeParam, int urcleunik, int lmcleunik,String param,int param2) throws RemoteException, ServeurSqlFailure;



    CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, int typedesignalitique,String serveurSigne,boolean correctBuffer) throws RemoteException;



    java.util.ArrayList renvcombo(char typedecombo, int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas,String serveurSigne,boolean check) throws RemoteException ;



    CompressArray renvClient(int cas, int[] listeParam, int urcleunik, int lmcleunik,String name) throws RemoteException, ServeurSqlFailure;



    public Object workWithDecision(Object obj, int urcleunik, int typeAction,java.util.ArrayList data,int id2,int my_type,long timestamp,int typeDecision) throws RemoteException, ServeurSqlFailure;



    public java.util.ArrayList getTvaListe(int urcleunik,int lmcleunik)throws RemoteException, ServeurSqlFailure;



   // public Object workWithDecisionMemo(Object obj, int urcleunik, int typeAction, java.util.ArrayList data, int id2, int my_type, long timestamp) throws RemoteException, ServeurSqlFailure;







    public srcastra.astra.sys.rmi.groupe_dec.GrpGroupDecRmiInterface renvGrpDecRmiObject(int urCleunik) throws RemoteException;



    public ConfigRmiInterface renvConfigRmiObject(int urCleunik) throws RemoteException;



    ParamComptableInterface renvParamCompta(int urCleunik) throws RemoteException;



    public GlobalRmiInterface renvUserRmiObject(int urCleunik) throws RemoteException;



    public GlobalRmiInterface renvEntiteRmiObject(int urCleunik) throws RemoteException;



    GlobalRmiInterface renvTypePayementRmiObject(int urCleunik) throws RemoteException;



    public GlobalRmiInterface renvCaisseLibelleRmiObject(int urCleunik) throws RemoteException;



    GlobalRmiInterface renvPeriodeRmiObject(int urCleunik) throws RemoteException;



    public CompteRmiInterface renvCompte2RmiObject(int urCleunik) throws RemoteException;



    public GlobalRmiInterface renvCompteRmiObject(int urCleunik) throws RemoteException;

    public ListRmiInterface renvListRmiObject(int urCleunik) throws RemoteException;

    void importClient(int urcleunik,ArrayList client) throws RemoteException,ServeurSqlFailure,srcastra.astra.sys.importastra.EntityNotFoundException;

    java.util.ArrayList getTransacState(int societe)throws RemoteException, ServeurSqlFailure;

    void remoteCommit(int urcleunik) throws RemoteException;    

    String getVersion() throws RemoteException;    
    srcastra.astra.sys.btn.business.Btn getBtnServeur(int urCleunik)throws RemoteException;

     public static final int COMBO_CODE_POST=1;

     public static final int COMBO_PAYS=2;

     public static final int COMBO_LANGUE=3;

     public static final int COMBO_DEVISE=4;

     public static final int COMBO_TVAREGIME=5;

     public static final int COMBO_FOURNISSEUR=6;

     public static final int COMBO_FOURNISSEURCOMP=7;

     public static final int COMBO_FOURNDOC=8;

     public static final int NOMBRELANGUESYS=4; 

     public static final int COMBO_FOURGRPDEC=9;

     public static final int COMBO_FOURNCONTACT=10;

     public static final int COMBO_FOURNPROD=11;

     public static final int COMBO_LOGEMENT=12;

     public static final int COMBO_TRANSPORT=13;

     public static final int COMBO_SITUATION=14; 

     public static final int COMBO_SIGNALITIQUE=15; 

     public static final int COMBO_CODE_SAISON_ETE=16;

     public static final int COMBO_CODE_SAISON_HIVER=17;

     public static final int COMBO_TVA_TYPE=18;

     public static final int GRP_PROD_DEF=19;

     public static final int COMBO_TITREPERSONNES=20;

     public static final int COMBO_CLIENT = 21;

     public static final int COMBO_CLIENT_ADRESSES = 22;

     public static final int COMBO_CLIENT_REMISES = 23;

     public static final int COMBO_CATEGORIE_PRODUIT = 24;

     public static final int COMBO_DESTINATION = 25;

     public static final int COMBO_COMPAGNIE = 26;

     public static final int COMBO_COMPTE = 27;

     public static final int COMBO_EMBARQDEBARQ = 28;

     public static final int COMBO_PRODUITGRPDEC=29;

     public static final int COMBO_NATIONALITE=30;

     public static final int COMBO_SUPREDUC_GRPDEC=31;

     public static final char COMBOTYPE_PAYS     = 'p';

     public static final char COMBOTYPE_DEVISE   = 'd';

     public static final char COMBOTYPE_LANGUE   = 'l';

     public static final char COMBOTYPE_LOCALITE = 'L';

     public static final char COMBOTYPE_CODEPOST = 'c';

     public static final char COMBOTYPE_TVAREGIME= 'r';

     public static final char COMBOTYPE_FOURN    = 'f';

     public static final char COMBOTYPE_FOURNPROD= 'g';

     public static final char COMBOTYPE_VALEURTVA= 't';

     public static final char COMBOTYPE_TITREPERS= 'a';

     public static final char COMBOTYPE_LOGEMENT = 'b';

     public static final char COMBOTYPE_TRANSPORT= 'e'; 

     public static final String COMBO_CODE_POST_CAS="signCodePostalCas";

     public static final String COMBO_PAYS_CAS1="signPaysCas1";

     public static final String COMBO_PAYS_CAS2="signPaysCas2";

     public static final String COMBO_PAYS_CAS="signPaysCas";

     public static final String COMBO_LANGUECAS1="signLangueCas1";

     public static final String COMBO_LANGUECAS2="signLangueCas2";

     public static final String COMBO_LANGUECAS="signLangueCas";

     public static final String COMBO_DEVISECAS1="signDeviseCas1";    

     public static final String COMBO_DEVISECAS="signDeviseCas";

     public static final String COMBO_DEVISECAS2="signDeviseCas2"; 

     public static final String COMBO_TITREPERSONNESCAS="signTitrePersCas";  

     public static final String COMBO_DESTINATIONCAS1 = "signDestinationCas1";

     public static final String COMBO_DESTINATIONCAS2 = "signDestinationCas2";

     public static final String COMBO_DESTINATIONCAS = "signDestinationCas";

     public static final String COMBO_COMPAGNIECAS1 = "signCompagnieCas1";

     public static final String COMBO_COMPAGNIECAS2 = "signCompagnieCas2";

     public static final String COMBO_TRANSPORTCAS="signTransportCas";

     public static final String COMBO_TVA_TYPECAS1="signTvaTypeCas1";

     public static final String COMBO_TVA_TYPECAS2="signTvaTypeCas2";

     public static final String COMBO_LOGEMENTCAS="signLogementCas";

    // public static final String COMBO_COMPTECAS1 ="signCompteCas1";

     public static final String COMBO_COMPTECAS ="signCompteCas";

    // public static final String COMBO_COMPTECAS2 ="signCompteCas2";

     public static final String COMBO_FOURNISSEURCAS1="signFournisseurCas1";

     public static final String COMBO_FOURNISSEURCAS2="signFournisseurCas2";

     public static final String COMBO_EMBARQDEBARQCAS1 = "signEmbarDebarqCas1";

     public static final String COMBO_EMBARQDEBARQCAS = "signEmbarDebarqCas";

     public static final String COMBO_NATIONALITE_CAS1="natCas1";

     public static final String COMBO_LANGUE_SYSCAS="langueSysCas";

     public static final int COMBO_LANGUE_SYS=33;



    











     // public static final String COMBOTYPE_TVAREGIMECAS1= "signTvaTypeCas1";















}















     















