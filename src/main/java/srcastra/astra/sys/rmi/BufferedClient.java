/*















 * ThreadedClient.java















 *















 * Created on 10 juillet 2002, 9:52















 */


package srcastra.astra.sys.rmi;


import java.rmi.*;


import java.util.*;


import srcastra.astra.sys.configuration.AbstractRequete;


import srcastra.astra.sys.rmi.utils.*;


import srcastra.astra.sys.compress.*;


import srcastra.astra.sys.rmi.Exception.*;


import srcastra.astra.sys.classetransfert.*;


import srcastra.astra.sys.classetransfert.signaletique.*;


import java.util.Collections;


import srcastra.astra.gui.sys.comparator.*;


import srcastra.astra.gui.modules.aidedesicion.*;


import srcastra.astra.gui.components.actions.actionToolBar.*;


/**
 * BufferedClient is meaned to be used by the client as an interface
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * It is constructed with a valid astrainterface as argument.
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * All calls made to BufferedClient are first checked for a valid
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * local copy of the results. If not, the result are fetched and
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * buffered
 *
 * @author David
 * @version 0.1 Quantum Warp Gate  - Al is back
 */


public class BufferedClient implements srcastra.astra.sys.rmi.astrainterface {


    private srcastra.astra.sys.rmi.astrainterface subInterface;


    private HashServeurBuffer buffer;


    private AbstractBuffer dectree;

    //    private ClientRemoteBufferWatcher watcher;


    /**
     * Rethrow the cause of the remote exception if the cause exists and
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * <p/>
     * extends RemoteException else rethrow the remoteException itself
     */


    private void retrowRemoteException(RemoteException re) throws RemoteException {


        Throwable t = re.getCause();


        if ((t == null) || (!(new java.rmi.RemoteException()).getClass().isAssignableFrom(t.getClass())))


            throw re;


        else


            throw (RemoteException) t;


    }


    /**
     * Creates new ThreadedClient
     */


    public BufferedClient(srcastra.astra.sys.rmi.astrainterface sub) {


        subInterface = sub;


        this.init_buffers();


        treeTable = new Hashtable();


        treeTable.put(new Integer(AbstractRequete.IMPRESSION), "vide");


        treeTable.put(new Integer(AbstractRequete.MEMO), "vide");


        treeTable.put(new Integer(AbstractRequete.SUP_RECUC), "vide");


        treeTable.put(new Integer(AbstractRequete.TVA_REGIME), "vide");


        treeTable.put(new Integer(AbstractRequete.TVA_TYPE), "vide");


        treeTable.put(new Integer(AbstractRequete.FACTURE), "vide");


        treeTable.put(new Integer(AbstractRequete.TYPE_PAYEMENT), "vide");


        treeTable.put(new Integer(AbstractRequete.DECSRIPTIF_LOG), "vide");

        //treeTable.put(new Integer(AbstractRequete.TYPE_PAYEMENT),"vide");


    }


    public srcastra.astra.sys.rmi.astrainterface getInterface() {


        return subInterface;


    }


    public Object ChargeObject(int urlmcleunik, int urcleunik, int objcleunik, int cas, int comboConstante) throws RemoteException {


        try {


            return subInterface.ChargeObject(urlmcleunik, urcleunik, objcleunik, cas, comboConstante);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public Object ChargeObjectPopup(int urlmcleunik, int urcleunik, int objcleunik, int cas, int comboConstante) throws RemoteException {


        try {

            /*  String signature= QueryKeyGen.ChargeObjectPopup(urlmcleunik, urcleunik, objcleunik, cas, comboConstante);















            synchronized (buffer) {















                if (isSignatureOk(signature+" objcleunik: "+objcleunik,signature,urcleunik)) {















                    System.out.println("Signature auth complete. Man, we are on the good way");















                    return buffer.getObjectValue(signature+" objcleunik: "+objcleunik);















                }















                else {















                    Object tmp = subInterface.ChargeObjectPopup(urlmcleunik, urcleunik, objcleunik, cas, comboConstante);















                    setSignature(signature+" objcleunik: "+objcleunik,tmp);















                    return tmp;















                }















            }*/


            Object tmp = subInterface.ChargeObjectPopup(urlmcleunik, urcleunik, objcleunik, cas, comboConstante);


            return tmp;


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public Object chargeObjetCombo(int objectCleunik, int urcleunik, int urlmcleunik, int comboConstante) throws RemoteException {


        try {


            return subInterface.chargeObjetCombo(objectCleunik, urcleunik, urlmcleunik, comboConstante);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public boolean deletefournisseur(int focleunik) throws RemoteException {


        try {

            //buffer.invalidate();


            return subInterface.deletefournisseur(focleunik);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return false;
        }


    }


    public Gestionerreur_T insertObjectPopup(Object objdp, int urcleunik, int TypObject, int cas) throws RemoteException {

        //buffer.invalidate();


        try {


            return subInterface.insertObjectPopup(objdp, urcleunik, TypObject, cas);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public Gestionerreur_T insertdocument(Document_T doc, int urcleunik) throws RemoteException {

        //buffer.invalidate();


        try {


            return subInterface.insertdocument(doc, urcleunik);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public Gestionerreur_T insertfourncontact(FournContact_T fourcon, int urcleunik) throws RemoteException, ServeurSqlFailure {

        //buffer.invalidate();


        try {


            return subInterface.insertfourncontact(fourcon, urcleunik);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public Gestionerreur_T insertfournisseur(Fournisseur_T fourn, int urcleunik) throws RemoteException {

        //buffer.invalidate();


        try {


            return subInterface.insertfournisseur(fourn, urcleunik);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public Gestionerreur_T modifyGrpDecision(Grpdecision_T grpd, int urcleunik) throws RemoteException {

        //buffer.invalidate();


        try {


            return subInterface.modifyGrpDecision(grpd, urcleunik);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public Gestionerreur_T modifyObjectPopup(Object objdp, int urcleunik, int TypObject, int cas) throws RemoteException {

        //buffer.invalidate();


        try {


            return subInterface.modifyObjectPopup(objdp, urcleunik, TypObject, cas);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public Gestionerreur_T modifydocument(Document_T doc, int urcleunik) throws RemoteException {

        //buffer.invalidate();


        try {


            return subInterface.modifydocument(doc, urcleunik);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public Gestionerreur_T modifyfourncontact(FournContact_T fourcon, int urcleunik) throws RemoteException {

        //buffer.invalidate();


        try {


            return subInterface.modifyfourncontact(fourcon, urcleunik);


        }


        catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se)


        {

            //  retrowRemoteException(e);return null;


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


        return null;


    }


    public Gestionerreur_T modifyfournisseur(Fournisseur_T fourn, int urcleunik) throws RemoteException {

        //buffer.invalidate();


        try {


            return subInterface.modifyfournisseur(fourn, urcleunik);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public void remoterollback(int urcleunik) throws RemoteException {

        //buffer.invalidate();


        try {


            subInterface.remoterollback(urcleunik);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return;
        }


    }


    public java.util.ArrayList renvComboTest(char typedecombo, int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas) throws RemoteException {


        try {


            return subInterface.renvComboTest(typedecombo, urcleunik, urlmcleunik, plettre, cxcode, cas);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public java.util.ArrayList renvIntitule(int urlmcleunik, int urcleunik, int cas, int typedesignalitique, int caecleunik) throws RemoteException {


        try {


            return subInterface.renvIntitule(urlmcleunik, urcleunik, cas, typedesignalitique, caecleunik);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public java.util.ArrayList renvLangueSystem(int urcleunik, int urlmcleunik, int tmpint) throws RemoteException {


        try {


            return subInterface.renvLangueSystem(urcleunik, urlmcleunik, tmpint);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, int typedesignalitique) throws RemoteException {


        try {


            String signature = QueryKeyGen.renvSignalitiques(urlmcleunik, urcleunik, typedesignalitique, typedesignalitique);


            synchronized (buffer) {


                if (isSignatureOk(signature, urcleunik)) {


                    System.out.println("Signature auth complete. Man, we are on the good way");


                    return buffer.getValue(signature);


                } else {


                    CompressArray tmp = subInterface.renvSignalitiques(urlmcleunik, urcleunik, cas, typedesignalitique);


                    setSignature(signature, tmp);


                    return tmp;


                }


            }


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, int typedesignalitique, String serveurSigne, boolean correctBuffer) throws RemoteException {


        try {


            String signature = QueryKeyGen.renvSignalitiques(urlmcleunik, urcleunik, cas, typedesignalitique);


            synchronized (buffer) {


                if (isSignatureOk(signature, serveurSigne, urcleunik)) {


                    System.out.println("OkOKOKOKOK this is the goog signature");


                    System.out.println("Signature auth complete. Man, we are on the good way");


                    correctBuffer = true;


                    return buffer.getValue(signature);


                } else {


                    System.out.println("NONONONONthis is not the goog signature"+typedesignalitique);


                    CompressArray tmp = subInterface.renvSignalitiques(urlmcleunik, urcleunik, cas, typedesignalitique);


                    Collections.sort(tmp, new ListeSelectorComparaTor(1, true));


                    setSignature(signature, tmp);


                    return tmp;


                }


            }


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public CompressArray renvSignalitiques(int urlmcleunik, int urcleunik, int cas, int typedesignalitique, boolean x) {


        System.out.println("Signature auth complete. Man, we are on the good way");


        String signature = QueryKeyGen.renvSignalitiques(urlmcleunik, urcleunik, cas, typedesignalitique);


        return buffer.getValue(signature);


    }


    public java.util.ArrayList renvcombo(char typedecombo, int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas) throws RemoteException {


        try {


            String signature = QueryKeyGen.renvcombo(typedecombo, urcleunik, urlmcleunik, plettre, cxcode, cas);


            synchronized (buffer) {

                //  if (isSignatureOk(signature, urcleunik)) {

                //    System.out.println("Signature auth complete. Man, we are on the good way");

                //  return buffer.getValue(signature);

                //}

                /*if (!buffer.isValid(signature))















                   buffer.setValue (signature,subInterface.renvcombo (typedecombo, urcleunik, urlmcleunik,plettre, cxcode, cas));















               return (java.util.ArrayList)(buffer.getObjectValue(signature));*/

                //subInterface.isBufferOk(signature,0,System.currentTimeMillis(),urcleunik);

                // else {


                java.util.ArrayList tmp = subInterface.renvcombo(typedecombo, urcleunik, urlmcleunik, plettre, cxcode, cas);


                setSignature(signature, tmp);


                return tmp;

                //}


            }


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public java.util.ArrayList renvcombo(char typedecombo, int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, String serveurSigne, boolean check) throws RemoteException {


        try {


            String signature = QueryKeyGen.renvcombo(typedecombo, urcleunik, urlmcleunik, plettre, cxcode, cas);


            synchronized (buffer) {


                if (isSignatureOk(signature, serveurSigne, urcleunik)) {


                    System.out.println("Signature auth complete. Man, we are on the good way");


                    return buffer.getValue(signature);


                }

                /*if (!buffer.isValid(signature))















                   buffer.setValue (signature,subInterface.renvcombo (typedecombo, urcleunik, urlmcleunik,plettre, cxcode, cas));















               return (java.util.ArrayList)(buffer.getObjectValue(signature));*/

                //subInterface.isBufferOk(signature,0,System.currentTimeMillis(),urcleunik);


                else {


                    java.util.ArrayList tmp = subInterface.renvcombo(typedecombo, urcleunik, urlmcleunik, plettre, cxcode, cas);


                    setSignature(signature, tmp);


                    return tmp;


                }


            }


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public java.util.ArrayList renvcombo(char typedecombo, int urcleunik, int urlmcleunik, char plettre, String cxcode, int cas, boolean x) throws RemoteException {


        String signature = QueryKeyGen.renvcombo(typedecombo, urcleunik, urlmcleunik, plettre, cxcode, cas);


        return buffer.getValue(signature);


    }


    public java.util.ArrayList renvcombofourncontact(int urcleunik, char plettre, int cas, int frcleunik) throws RemoteException {


        try {


            return subInterface.renvcombofourncontact(urcleunik, plettre, cas, frcleunik);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public Loginusers_T[] returnusers(int numentite) throws RemoteException {


        try {


            return subInterface.returnusers(numentite);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public Loginusers_T userautorisation(Loginusers_T user, Userinfo_T infodistante) throws RemoteException {


        try {


            Loginusers_T tmpgestion = subInterface.userautorisation(user, infodistante);


            return tmpgestion;


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public boolean userlogof(int urcleunik) throws RemoteException {


        try {


            srcastra.astra.sys.Configuration.getConfiguration().tryWriteObject(buffer, "Cache", "HashServerBuffer");


            return subInterface.userlogof(urcleunik);

            //return true;


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return false;
        }


    }


    private void init_buffers() {


        HashServeurBuffer tmp = new HashServeurBuffer();


        this.buffer = (HashServeurBuffer) srcastra.astra.sys.Configuration.getConfiguration().tryReadObject(tmp, "Cache", "HashServerBuffer");

        // System.out.println(this.buffer.toString());

        //        watcher = new ClientRemoteBufferWatcher(buffer);


    }

    //    public void registerBufferNotifier (int urcleunik, RemoteBufferWatcher watcher) throws RemoteException

    //    {

    //        System.out.println ("No notifier beyhond notifier allowed ");

    //        return;

    //    }


    public boolean isBufferOk(String reference, long timestamp, long currenttime, int urcleunik) {


        return false; // <-- crazy hehe


    }


    private boolean isSignatureOk(String signature, String Serveursignature, int urcleunik) throws RemoteException {


        Long timestamp = (Long) buffer.getObjectValue(signature + ".timestamp");


        Object tmpObj = buffer.getValue(signature);


        Object dummyObject = new String("Dummy variable");


        CompressArray array = null;


        if (tmpObj == null) {


            return false;


        } else {


            if (tmpObj.equals(dummyObject))


                System.out.println("[*************UUUUUUUU]Object vide");


            else {


                array = (CompressArray) tmpObj;


                System.out.println("[*********SIGNATURE DE LA LISTE:" + array.getSignature());


            }

            // long timestamp=buffer.returnTime();


            if (timestamp == null)


                return false;


            return subInterface.isBufferOk(Serveursignature, array.getSignature(), System.currentTimeMillis(), urcleunik);


        }


    }


    private boolean isSignatureOk(String signature, int urcleunik) throws RemoteException {


        return isSignatureOk(signature, signature, urcleunik);


    }

    /* private boolean isSignatureOk(String signature,String signatureserveur int urcleunik) throws RemoteException{















        return isSignatureOk(signature,signatureserveur,urcleunik);















    }*/


    private void setSignature(String signature, Object buf) {


        Long timestamp = new Long(System.currentTimeMillis());


        buffer.setValue(signature, buf);


        buffer.setValue(signature + ".timestamp", timestamp);


    }


    public CompressArray renvClient(int cas, int[] listeParam, int urcleunik, int lmcleunik) throws RemoteException, ServeurSqlFailure {


        try {


            String signature = QueryKeyGen.renvClient(cas, listeParam, urcleunik, lmcleunik);


            synchronized (buffer) {

                //  if (isSignatureOk(signature, urcleunik)) {

                //  System.out.println("Signature auth complete. Man, we are on the good way");

                // return buffer.getValue(signature);

                //}

                // else {


                CompressArray tmp = subInterface.renvClient(cas, listeParam, urcleunik, lmcleunik);


                setSignature(signature, tmp);


                return tmp;

                //}


            }


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public Object ChargeClient(int cas, int[] listeParam, int urcleunik, int lmcleunik) throws RemoteException, ServeurSqlFailure {


        try {


            return subInterface.ChargeClient(cas, listeParam, urcleunik, lmcleunik);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public srcastra.astra.sys.document.Genere_Doc edition(int key, int objcleunik, int urcleunik) throws RemoteException {


        try {


            return subInterface.edition(key, objcleunik, urcleunik);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return null;
        }


    }


    public int insertClient(int cas, int urcleunik, int[] param, Object objcli) throws RemoteException, ServeurSqlFailure {


        try {


            return subInterface.insertClient(cas, urcleunik, param, objcli);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return 0;
        }


    }


    public void ModifyClient(int cas, int urcleunik, int[] param, Object objcli) throws RemoteException, ServeurSqlFailure {


        try {


            subInterface.ModifyClient(cas, urcleunik, param, objcli);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return;
        }


    }


    public void deleteClient(int cas, int urcleunik, int[] param) throws RemoteException, ServeurSqlFailure {


        try {


            subInterface.deleteClient(cas, urcleunik, param);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return;
        }


    }


    public void deleteSignaletique(String table, String nomcleunik, int urcleunik, int checkCode, int cleunik, int typeObjet) throws RemoteException, ServeurSqlFailure {


        try {


            subInterface.deleteSignaletique(table, nomcleunik, urcleunik, checkCode, cleunik, typeObjet);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);
            return;
        }


    }


    public FournisseurRmiInterface renvFournisseurRmiObject(int urCleunik) throws RemoteException {


        try {


            return subInterface.renvFournisseurRmiObject(urCleunik);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);


            return null;


        }


    }


    public DossierRmiInterface renvDossierRmiObject(int urCleunik) throws RemoteException {


        try {


            return subInterface.renvDossierRmiObject(urCleunik);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);


            return null;


        }


    }


    public Gestionerreur_T modifyFournisseurMemo(String memo, int frcleunik, int urcleunik) throws RemoteException {


        Gestionerreur_T tmperreur = null;


        try {


            tmperreur = subInterface.modifyFournisseurMemo(memo, frcleunik, urcleunik);


            return tmperreur;


        }


        catch (RemoteException re)


        {
            retrowRemoteException(re);


            return tmperreur;
        }

        //return null;


    }


    public srcastra.astra.sys.classetransfert.FournMemo_T renvFournisseurMemo(int frcleunik, int urcleunik) throws RemoteException {


        try {


            return subInterface.renvFournisseurMemo(frcleunik, urcleunik);


        }


        catch (RemoteException re)


        {
            retrowRemoteException(re);


            return null;
        }


    }


    public Object chargeAllclient(int cas, int[] listeParam, int urcleunik, int lmcleunik, String param, int param2) throws RemoteException, ServeurSqlFailure {


        try {


            return subInterface.chargeAllclient(cas, listeParam, urcleunik, lmcleunik, param, param2);


        }


        catch (RemoteException re)


        {
            retrowRemoteException(re);


            return null;
        }


    }


    public CompressArray renvClient(int cas, int[] listeParam, int urcleunik, int lmcleunik, String name) throws RemoteException, ServeurSqlFailure {


        CompressArray tmp = subInterface.renvClient(cas, listeParam, urcleunik, lmcleunik, name);


        return tmp;


    }


    public Object workWithDecision(Object obj, int urcleunik, int typeAction, java.util.ArrayList data, int id2, int my_type, long timestamp, int typeDecision) throws RemoteException, ServeurSqlFailure {


        if (typeAction == ActionToolBar.ACT_READ) {


            if (treeTable.get(new Integer(typeDecision)) == null || treeTable.get(new Integer(typeDecision)).equals("vide")) {


                treeTable.put(new Integer(typeDecision), subInterface.workWithDecision(obj, urcleunik, typeAction, data, id2, my_type, timestamp, typeDecision));


                ((DecBuffer) treeTable.get(new Integer(typeDecision))).setGraphicBuffer(true);


                ((DecBuffer) treeTable.get(new Integer(typeDecision))).generateTable();


            }


            return treeTable.get(new Integer(typeDecision));


        } else


            return subInterface.workWithDecision(obj, urcleunik, typeAction, data, id2, my_type, timestamp, typeDecision);


    }

    /* public Object workWithDecisionMemo(Object obj, int urcleunik, int typeAction, java.util.ArrayList data, int id2, int my_type, long timestamp,int typeDecision) throws RemoteException, ServeurSqlFailure {















     if(typeAction==ActionToolBar.ACT_READ){















            if(dectree==null)















                return subInterface.workWithDecisionMemo(obj,urcleunik,typeAction,data,id2,my_type,typeDecision);















            else















                return dectree;           















    }















        else 















            return subInterface.workWithDecisionMemo(obj,urcleunik,typeAction,data,id2,my_type,timestamp);















    }*/


    public srcastra.astra.sys.rmi.groupe_dec.GrpGroupDecRmiInterface renvGrpDecRmiObject(int urCleunik) throws RemoteException {


        try {


            return subInterface.renvGrpDecRmiObject(urCleunik);


        }


        catch (RemoteException e)


        {
            retrowRemoteException(e);


            return null;


        }


    }


    public ConfigRmiInterface renvConfigRmiObject(int urCleunik) throws RemoteException {


        return subInterface.renvConfigRmiObject(urCleunik);


    }


    public java.util.ArrayList getTvaListe(int urcleunik, int lmcleunik) throws RemoteException, ServeurSqlFailure {

        // if(tvaListe==null)


        tvaListe = subInterface.getTvaListe(urcleunik, lmcleunik);


        return tvaListe;


    }


    public ParamComptableInterface renvParamCompta(int urCleunik) throws RemoteException {


        if (comptaInterface == null)


            comptaInterface = subInterface.renvParamCompta(urCleunik);


        return comptaInterface;


    }


    public GlobalRmiInterface renvEntiteRmiObject(int urCleunik) throws RemoteException {


        if (entitermi == null)


            entitermi = subInterface.renvEntiteRmiObject(urCleunik);


        return entitermi;


    }


    public GlobalRmiInterface renvUserRmiObject(int urCleunik) throws RemoteException {


        if (userrmi == null)


            userrmi = subInterface.renvUserRmiObject(urCleunik);


        return userrmi;


    }


    public GlobalRmiInterface renvTypePayementRmiObject(int urCleunik) throws RemoteException {


        if (typepayement == null)


            typepayement = subInterface.renvTypePayementRmiObject(urCleunik);


        return typepayement;


    }


    public GlobalRmiInterface renvCaisseLibelleRmiObject(int urCleunik) throws RemoteException {


        if (caisseLibelle == null)


            caisseLibelle = subInterface.renvCaisseLibelleRmiObject(urCleunik);


        return caisseLibelle;


    }


    public GlobalRmiInterface renvPeriodeRmiObject(int urCleunik) throws RemoteException {


        if (periodeCompta == null)


            periodeCompta = subInterface.renvPeriodeRmiObject(urCleunik);


        return periodeCompta;


    }


    public CompteRmiInterface renvCompte2RmiObject(int urCleunik) throws RemoteException {


        if (compte2 == null)


            compte2 = subInterface.renvCompte2RmiObject(urCleunik);


        return compte2;


    }


    public GlobalRmiInterface renvCompteRmiObject(int urCleunik) throws RemoteException {


        if (compte == null)


            compte = subInterface.renvCompteRmiObject(urCleunik);


        return compte;


    }


    public ListRmiInterface renvListRmiObject(int urCleunik) throws RemoteException {

        if (list == null)

            list = subInterface.renvListRmiObject(urCleunik);

        return list;

    }


    public void importClient(int urcleunik, ArrayList client) throws RemoteException, ServeurSqlFailure, srcastra.astra.sys.importastra.EntityNotFoundException {

        subInterface.importClient(urcleunik, client);

    }


    public ArrayList getTransacState(int societe) throws RemoteException, ServeurSqlFailure {

        return subInterface.getTransacState(societe);

    }


    public void remoteCommit(int urcleunik) throws RemoteException {

        subInterface.remoteCommit(urcleunik);

    }


    public String getVersion() throws RemoteException {

        return subInterface.getVersion();

    }

    public srcastra.astra.sys.btn.business.Btn getBtnServeur(int urCleunik) throws RemoteException {
        if (btnServeur == null) {
            btnServeur = subInterface.getBtnServeur(urCleunik);
        }
        return btnServeur;
    }


    ArrayList tvaListe;

    Hashtable treeTable;

    ParamComptableInterface comptaInterface;

    GlobalRmiInterface userrmi;

    GlobalRmiInterface entitermi;

    GlobalRmiInterface typepayement;

    GlobalRmiInterface caisseLibelle;

    GlobalRmiInterface periodeCompta;

    CompteRmiInterface compte2;

    GlobalRmiInterface compte;

    ListRmiInterface list;
    srcastra.astra.sys.btn.business.Btn btnServeur;

}  















 