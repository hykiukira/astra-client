/*
 * ExportMain.java
 *
 * Created on 27 janvier 2004, 14:33
 */

package srcastra.astra.sys.export;

import srcastra.astra.sys.compta.MathRound;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @author Administrateur
 */
public class ExportMain {

    /**
     * Creates a new instance of ExportMain
     */
    ArrayList client;
    ArrayList fournisseur;
    static Hashtable clientHash;
    static Hashtable fournHash;
    boolean bydossier;
    static Hashtable clienntToWrite;
    static Hashtable fournToWrite;
    int pedecleunik;
    static long exported;

    public static void setExported(long exportedt) {
        exported = exportedt;
    }

    public ExportMain(boolean bydossier, int pedecleunik) {
        this.bydossier = bydossier;
        this.pedecleunik = pedecleunik;

    }

    protected void setExportedS(String historiquekey, Connection con) throws SQLException {
        if (!historiquekey.equals("")) {
            String setComptabiliser = "UPDATE historique2 SET he_exported =? WHERE hecleunik " + historiquekey;
            System.out.println("ligne" + setComptabiliser);
            PreparedStatement pstmt = con.prepareStatement(setComptabiliser);
            pstmt.setLong(1, exported);
            pstmt.execute();
        }
    }

    protected String date(String dat) {
        dat = dat.substring(0, 19);
        //    System.out.println("date   "+dat);
        srcastra.astra.sys.classetransfert.utils.Date tmpdate = new srcastra.astra.sys.classetransfert.utils.Date(dat);
        return tmpdate.toString3();
    }

    protected void selectClient(int lmcleunik, Connection con, String clientkey) throws SQLException {
        if (clientkey == null)
            return;
        // clientHash=new Hashtable();
        System.out.println("clientKey:"+clientkey);
        String client = "SELECT c.cscleunik,c.csgecleunik,c.eecleunik,c.csdatetimecrea,c.csdatetimemodi,c.csreference,c.tscleunik,c.csnom,c.csadresse,c.cxcleunik,c.pyscleunik,c.csnom2,c.cstelephonep,c.cstelephones,c.csfax,c.csgsm,c.csmailprincip,c.csmailsecond,c.cstvatype,c.cstvanum,c.cstvaregime,c.csdatenaiss,c.cscodemailing,c.lecleunik,c.csbanque,c.cscartecredit,c.csanalytique,c.cscodecotisateur,c.csdatecotisation,c.csmontcotisation,c.csbloque,c.csdelaipaiem,c.snumerosessioncrea,c.snumerosessionmodif,tp.pystraduction,tc.txtraduction,cp.cxcode,tri.tetraduction,trl.leabreviation,ti.tsintitule,clients_groupe.csgenom,c.csdatecotisation,c.cstvatype2,c.ce_cleunik2     FROM clients c left join  clients_groupe on c.csgecleunik=clients_groupe.csgecleunik,traductioncodpostaux tc,traductionpays tp,codepostaux cp,traductionintitule tri,langue trl, traductionlangues trl2,traductiontitrepers ti WHERE tc.cxcleunik=c.cxcleunik AND c.cxcleunik=cp.cxcleunik AND tp.pyscleunik=c.pyscleunik AND trl.lecleunik=c.lecleunik AND trl2.lmcleunik=? AND tri.tecleunik=c.cstvaregime AND tri.caecleunik=1 AND tri.lmcleunik=? AND tc.lmcleunik=? AND tp.lmcleunik=? AND c.tscleunik=ti.tscleunik AND ti.lmcleunik=? AND c.cscleunik " + clientkey;
        PreparedStatement pstmt = con.prepareStatement(client);
        pstmt.setInt(1, lmcleunik);
        pstmt.setInt(2, lmcleunik);
        pstmt.setInt(3, lmcleunik);
        pstmt.setInt(4, lmcleunik);
        pstmt.setInt(5, lmcleunik);
        ResultSet result = pstmt.executeQuery();
        while (result.next()) {
            if (clientHash == null) clientHash = new Hashtable();
            Object[] tab = new Object[44];
            for (int i = 0; i < tab.length; i++) {
                tab[i] = result.getObject(i + 1);
            }
            clientHash.put(new Integer(tab[0].toString()), tab);
        }
    }

    protected void selectFournisseur(int lmcleunik, Connection con, String fournisseurkey) throws SQLException {
        if (fournisseurkey == null)
            return;
        // System.out.println("fournisseur keys "+fournisseurkey);
        String fournisseur = "SELECT f.frcleunik, f.frreference1, f.frreference2, f.frnom1, f.frnom2, f.fradresse, f.frtvanum, f.frtvatype, f.frtvaregime, f.frnumbanque1, f.frnumbanque2, f.frnumbanque3, f.frtelephone1, f.frfax, f.frmail, f.frmodecccf, f.frdelaipaienbrjour, f.frdomiciliation, f.frmemo, f.frdatetimecrea, f.frdatetimemodif, f.snumerosessioncrea, f.snumerosessionmodif, f.decleunik, f.cxcleunik, f.aecleunik, f.lecleunik, f.pyscleunik, f.frfournprod, f.frNcompte, c.cxcode, tc.txtraduction, tt.Traduction,rn.Traduction FROM fournisseur f LEFT  JOIN typetva_traduction tt ON ( tt.typtva_cleunik = f.frtvatype AND tt.lmcleunik = ?  ) LEFT JOIN regtva_traduction rn ON(rn.regtva_cleunik=f.frtvaregime AND rn.lmcleunik=?), codepostaux c, traductioncodpostaux tc WHERE f.cxcleunik = c.cxcleunik AND c.cxcleunik = tc.cxcleunik AND tc.lmcleunik = ? AND frcleunik " + fournisseurkey;
        PreparedStatement pstmt = con.prepareStatement(fournisseur);
        pstmt.setInt(1, lmcleunik);
        pstmt.setInt(2, lmcleunik);
        pstmt.setInt(3, lmcleunik);
        ResultSet result = pstmt.executeQuery();
        while (result.next()) {
            if (fournHash == null) fournHash = new Hashtable();
            Object[] tab = new Object[34];
            for (int i = 0; i < tab.length; i++) {
                tab[i] = result.getObject(i + 1);  
            }
            
                if(tab[2]==null)
                    tab[2]=tab[1];
            
            fournHash.put(new Integer(tab[0].toString()), tab);
        }
    }

    protected String prepareString(Object toprepare, int size) {
        if (toprepare == null)
            return "";
        String tmp = "                                                     ";
        int sizeSt = toprepare.toString().length();
        if (sizeSt < size) {
            int tmpsiz = (size) - sizeSt;
            toprepare = toprepare.toString() + tmp.substring(0, tmpsiz);
        } else
            toprepare = toprepare.toString().substring(0, size);
        return toprepare.toString();
        //return (toprepare.toString().length()<size+1)?toprepare.toString():toprepare.toString().substring(0,size);
    }

    protected Long getAbsolute(Object value) {
        double dou = new Double(value.toString()).doubleValue();
        //   dou=dou*100d;
        dou = Math.abs(dou);
        dou = MathRound.roundThisToDouble0(dou);
        long ret = new Double(dou).longValue();
        return new Long(ret);
    }

    protected Object changeValueOD(Object obj) {
        double dou = new Double(obj.toString()).doubleValue();

        String dc = "";
        if (dou < 0)
            dc = "C";
        else
            dc = "D";
        //  dou=dou*100;
        dou = Math.abs(dou);
        dou = MathRound.roundThisToDouble0(dou);
        long ret = new Double(dou).longValue();
        String retv = dc + new Long(ret).toString();
        return retv;
    }

    protected Object changeValue(Object obj) {
        double dou = new Double(obj.toString()).doubleValue();
        dou = MathRound.roundThisToDouble0(dou);
        long ret = new Double(dou).longValue();
        return new Long(ret);
    }

    protected Object changeSign(Object obj) {
        double dou = new Double(obj.toString()).doubleValue();
        dou = MathRound.roundThisToDouble0(dou);
        long ret = new Double(dou).longValue();
        ret = ret * -1l;
        return new Long(ret);
    }

    protected String generePeriode(Object dates, String periode) {
        
        System.out.println("ANNee:"+dates.toString());
        
        
        String annee = dates.toString().substring(4, 8);
        
        
        
        String retval = "";
        if (periode.length() > 1) {
            retval = periode.toString() + annee;
        } else {
            retval = "0" + periode.toString() + annee;
        }
        return retval;
    }

    protected ArrayList prepareClient(ArrayList array, Hashtable client, boolean caisse) {
        ArrayList clienta = null;
        boolean G = false;
        if (clienntToWrite == null)
            clienntToWrite = new Hashtable();
//        System.out.println("\n\n\n[*****************]client size"+client.size()+" array.size "+array.size());
        if (client != null && client.size() > 0 && array != null && array.size() > 0) {
            //   1               2           3           4               5               6               7        8          9           10          11         12       13              14          15      16          17          18              19          20          21              22              23          24          25          26              27              28                  29                  30              31          32              33                  34                      35              36          37          38              39                  40          41                      42              43              44
//SELECT c.cscleunik,c.csgecleunik,c.eecleunik,c.csdatetimecrea,c.csdatetimemodi,c.csreference,c.tscleunik,c.csnom,c.csadresse,c.cxcleunik,c.pyscleunik,c.csnom2,c.cstelephonep,c.cstelephones,c.csfax,c.csgsm,c.csmailprincip,c.csmailsecond,c.cstvatype,c.cstvanum,c.cstvaregime,c.csdatenaiss,c.cscodemailing,c.lecleunik,c.csbanque,c.cscartecredit,c.csanalytique,c.cscodecotisateur,c.csdatecotisation,c.csmontcotisation,c.csbloque,c.csdelaipaiem,c.snumerosessioncrea,c.snumerosessionmodif,tp.pystraduction,tc.txtraduction,cp.cxcode,tri.tetraduction,trl.leabreviation,ti.tsintitule,clients_groupe.csgenom,c.csdatecotisation,c.cstvatype2,c.ce_cleunik2     FROM clients c left join  clients_groupe on c.csgecleunik=clients_groupe.csgecleunik,traductioncodpostaux tc,traductionpays tp,codepostaux cp,traductionintitule tri,langue trl, traductionlangues trl2,traductiontitrepers ti WHERE tc.cxcleunik=c.cxcleunik AND c.cxcleunik=cp.cxcleunik AND tp.pyscleunik=c.pyscleunik AND trl.lecleunik=c.lecleunik AND trl2.lmcleunik=? AND tri.tecleunik=c.cstvaregime AND tri.caecleunik=1 AND tri.lmcleunik=? AND tc.lmcleunik=? AND tp.lmcleunik=? AND c.tscleunik=ti.tscleunik AND ti.lmcleunik=? AND c.cscleunik "+clientkey;
            //       1           2           3               4               5               6               7               8                   9                       10              11              12                  13                  14              15                  16                  17              18              19              20                  21              22                  23              24                  25                  26          27                  28              29              30                      31                              32                      33                  34              35                  36              37                      38             39           40          41              42              43                  44              45                  46                  47            48            49              50              51              52              53              54
//SELECT d.dr_numdos, c.csnom, d.dr_date_depart, h.`hecleunik` , h.`heperiode` , h.`henotcpt` , h.`heclottva` , h.`heclotperiode` , h.`heclotexercice` , h.`hetransact` , h.`jxcleunik` , h.`henumpiece` , h.`hedatecreation` , h.`hedatemouv` , h.`ce_cleunik_cent` , h.`tva_cleunik` , h.`ce_cleunik2` , h.`ce_cleunik` , h.`hevaleur` , h.`hecodetva` , h.`hevaleurbase` , h.`hevaleurtva` , h.`decleunik` , h.`hedatedevise` , h.`hevaleurdevise` , h.`helibelle` , h.`drcleunik` , h.`lignecleunik` , h.`sn_cleunik` , h.`ctprocleunik` , h.`typeintervenantcleunik` , h.`intervenantcleunik` , h.`cate_cleunik` , h.`hedossiercourant` , h.`hetypeligne` , h.`urcleunik` , h.`hetypepayement` , h.`helibellecompta2` , h.`pax` , h.`quantite` , h.`pourcent` , h.`hevaleuru` , h.`gn_cleunik` , h.`typegrpdec` , h.`exle_cleunik` , h.`he_echeance` , h.`he_reception` , h.`he_dc` , h.`he_exported` , e.eeabrev, j.jota_categorie, d.dr_date_depart, d.fournisseur, p.pede_numper FROM  `historique2` h LEFT  JOIN dossier d ON ( h.drcleunik = d.dr_cleunik )  LEFT  JOIN clients c ON h.intervenantcleunik = c.cscleunik, entite e, journcompta j, user u,periode p  WHERE h.urcleunik = u.urcleunik AND u.eecleunik = e.eecleunik AND h.jxcleunik = j.jota_cleunik AND h.hetypeligne =  'D' AND h.henotcpt = 1  AND h.heperiode = p.pede_cleunik AND h.typeintervenantcleunik = 1";
            for (int i = 0; i < array.size(); i++) {
                if (clienta == null)
                    clienta = new ArrayList();
                Object[] tmp = (Object[]) array.get(i);
                if (new Integer(tmp[30].toString()).intValue() == 1) {
                    Object[] clientdet = (Object[]) client.get(new Integer(tmp[31].toString()));
                    if (tmp != null && clientdet != null) {
                        Object[] clienttab = new Object[128];
                        for (int m = 0; m < clienttab.length; m++) {
                            clienttab[m] = "";
                        }
                        System.out.println("NUMDOSSIER"+tmp[49].toString());
                        
                        
                        clienttab[0] = prepareString(tmp[49].toString(), 2);
                        clienttab[1] = new Integer(1);
                        //  System.out.println("string "+tmp[0].toString().substring(0,3));
                        if (caisse) {
                         
                            
                           // System.out.println("tmp0:"+tmp[0].toString());
                           // System.out.println("tmp57:"+tmp[57].toString());  
                           // System.out.println(""+"K"+clienttab[0].toString()+"   tmp[0]"+tmp[0].toString()+"tmp[57]"+tmp[57].toString());
                           //  System.out.println("clienttab"+clienttab[0].toString());
                           // System.out.println("typeligne "+tmp[34].toString());
                            if (tmp[57] != null) {
                                 
                                 
                                 if (bydossier && !tmp[57].toString().substring(0, 3).equals("K" + clienttab[0].toString())) {
                                    System.out.println("client " + "K" + clienttab[0].toString());
                                    clienttab[2] = prepareString(clientdet[5], 8);
                                    G = true;
                                    System.out.println("by dossier caisse client G");
                                } else {
                                    if (!bydossier) {
                                        clienttab[2] = prepareString(clientdet[5], 8);
                                        System.out.println("clienttab[2]:"+clienttab[2].toString());
                                        System.out.println("NOT by dossier caisse client");
                                    } else {
                                        //clienttab[2]=prepareString(tmp[0],8);
                                        clienttab[2] = prepareString(tmp[57].toString().substring(1, tmp[57].toString().length()), 8);
                                        
                                        System.out.println("by dossier caisse client");
                                    }
                                }

                            }

                        } else {
                            if (!bydossier) {
                                clienttab[2] = prepareString(clientdet[5], 8);
                                System.out.println("NOT by dossier");
                            } else {
                                clienttab[2] = prepareString(tmp[0], 8);
                                System.out.println("BY dossier");
                            }


                        }
                        clienttab[4] = prepareString(clientdet[8], 30);
                        clienttab[6] = prepareString(clientdet[37], 1);
                        clienttab[7] = prepareString(clientdet[18], 2);
                        if (tmp[51] != null)
                            clienttab[9] = date(tmp[51].toString());
                        else
                            clienttab[9] = "";
                        clienttab[11] = "OK";
                        clienttab[12] = new Integer(1);
                        clienttab[13] = prepareString(clientdet[19], 12);
                        clienttab[14] = prepareString(clientdet[12], 14);
                        clienttab[15] = prepareString(clientdet[14], 14);
                        clienttab[16] = prepareString(clientdet[7], 30);
                        String datas = "";
                        String datas2 = "";
                        if (tmp[51] != null) {
                            datas = date(tmp[51].toString());
                        }
                        if (tmp[52] != null) {
                            datas2 = tmp[52].toString();
                        }
                        clienttab[18] = prepareString(datas2 + " " + datas, 30);
                        clienttab[19] = prepareString(clientdet[36].toString() + " " + clientdet[35].toString(), 30);
                        clienttab[20] = "N";
                        clienttab[24] = new Integer(0);
                        clienttab[32] = new Integer(0);
                        clienttab[126] = "T";
                        
                        System.out.println(caisse);
                        System.out.println(G);
                        
                        if (caisse == true && G == true) {
                            G = false;
                            //   System.out.println("g=true");
                        } else {
                            clienntToWrite.put(clienttab[2], clienttab);
                            //   System.out.println("g=false");
                        }
                        /*   if(tmp[0]==null){
                                clienntToWrite.put(clientdet[5], clienttab);
                           }else{
                               if(bydossier)
                                   clienntToWrite.put(tmp[0], clienttab);
                               else
                                   clienntToWrite.put(clientdet[5], clienttab);
                               clienta.add(clienttab);
                           }*/
                    }
                }
            }
         /*   for(Enumeration enu=clienntToWrite.keys();enu.hasMoreElements();){
              Object[] obj=(Object[])clienntToWrite.get(enu.nextElement());
              String toaffiche="";
              for(int j=0;j<obj.length;j++){
                  toaffiche=toaffiche+" "+j+"//*"+obj[j];
              }
              System.out.println("client"+toaffiche);
          }*/
            System.out.println(clienta.size());
            return clienta;
        }
        return null;
    }

    protected ArrayList prepareFournisseur(ArrayList array, Hashtable client) {
        ArrayList clienta = null;
        if (fournToWrite == null)
            fournToWrite = new Hashtable();
//                 1               2               3           4           5       6           7           8           9               10                  11              12              13          14          15          16          17                  18                  19          20              21                  22                      23                  24              25          26          27          28              29          30          31          32                  33          34
//"SELECT f.frcleunik, f.frreference1, f.frreference2, f.frnom1, f.frnom2, f.fradresse, f.frtvanum, f.frtvatype, f.frtvaregime, f.frnumbanque1, f.frnumbanque2, f.frnumbanque3, f.frtelephone1, f.frfax, f.frmail, f.frmodecccf, f.frdelaipaienbrjour, f.frdomiciliation, f.frmemo, f.frdatetimecrea, f.frdatetimemodif, f.snumerosessioncrea, f.snumerosessionmodif, f.decleunik, f.cxcleunik, f.aecleunik, f.lecleunik, f.pyscleunik, f.frfournprod, f.frNcompte, c.cxcode, tc.txtraduction, tt.Traduction, rn.Traduction FROM fournisseur f LEFT  JOIN typetva_traduction tt ON ( tt.typtva_cleunik = f.frtvatype AND tt.lmcleunik = ?  ) LEFT JOIN regtva_traduction rn ON(rn.regtva_cleunik=f.frtvaregime AND rn.lmcleunik=?), codepostaux c, traductioncodpostaux tc WHERE f.cxcleunik = c.cxcleunik AND c.cxcleunik = tc.cxcleunik AND tc.lmcleunik = ? AND frcleunik"+clientkey;
        //       1           2           3               4               5               6               7               8                   9                       10              11              12                  13                  14              15                  16                  17              18              19              20                  21              22                  23              24                  25                  26          27                  28              29              30                      31                              32                      33                  34              35                  36              37                      38             39           40          41              42              43                  44              45                  46                  47            48            49              50              51              52              53              54
//SELECT d.dr_numdos, c.csnom, d.dr_date_depart, h.`hecleunik` , h.`heperiode` , h.`henotcpt` , h.`heclottva` , h.`heclotperiode` , h.`heclotexercice` , h.`hetransact` , h.`jxcleunik` , h.`henumpiece` , h.`hedatecreation` , h.`hedatemouv` , h.`ce_cleunik_cent` , h.`tva_cleunik` , h.`ce_cleunik2` , h.`ce_cleunik` , h.`hevaleur` , h.`hecodetva` , h.`hevaleurbase` , h.`hevaleurtva` , h.`decleunik` , h.`hedatedevise` , h.`hevaleurdevise` , h.`helibelle` , h.`drcleunik` , h.`lignecleunik` , h.`sn_cleunik` , h.`ctprocleunik` , h.`typeintervenantcleunik` , h.`intervenantcleunik` , h.`cate_cleunik` , h.`hedossiercourant` , h.`hetypeligne` , h.`urcleunik` , h.`hetypepayement` , h.`helibellecompta2` , h.`pax` , h.`quantite` , h.`pourcent` , h.`hevaleuru` , h.`gn_cleunik` , h.`typegrpdec` , h.`exle_cleunik` , h.`he_echeance` , h.`he_reception` , h.`he_dc` , h.`he_exported` , e.eeabrev, j.jota_categorie, d.dr_date_depart, d.fournisseur, p.pede_numper FROM  `historique2` h LEFT  JOIN dossier d ON ( h.drcleunik = d.dr_cleunik )  LEFT  JOIN clients c ON h.intervenantcleunik = c.cscleunik, entite e, journcompta j, user u,periode p  WHERE h.urcleunik = u.urcleunik AND u.eecleunik = e.eecleunik AND h.jxcleunik = j.jota_cleunik AND h.hetypeligne =  'D' AND h.henotcpt = 1  AND h.heperiode = p.pede_cleunik AND h.typeintervenantcleunik = 1";
        if (client != null && client.size() > 0) {
            //   System.out.println("fournisseur size"+client.size()+" facture size "+array.size());
            for (int i = 0; i < array.size(); i++) {
                if (clienta == null)
                    clienta = new ArrayList();
                Object[] tmp = (Object[]) array.get(i);
                if (new Integer(tmp[30].toString()).intValue() == 2) {
                    Object[] clientdet = (Object[]) client.get(new Integer(tmp[31].toString()));
                    if (clientdet != null) {
                        Object[] clienttab = new Object[112];
                        for (int m = 0; m < clienttab.length; m++) {
                            clienttab[m] = "";
                        }
                        clienttab[0] = prepareString("ZZ", 2);
                        clienttab[1] = new Integer(1);
                        String reference=clientdet[1].toString();
                        if(clientdet[2]!=null){
                            String tmpRef=clientdet[2].toString();
                            tmpRef=tmpRef.trim();
                            if(!tmpRef.equals("")){
                                reference=tmpRef;
                            }
                        }
                        clienttab[2] = prepareString(reference, 8);
                        clienttab[3] = prepareString(clientdet[3], 30);
                        clienttab[4] = prepareString(clientdet[4], 30);
                        clienttab[5] = prepareString(clientdet[5], 30);
                        clienttab[6] = prepareString(clientdet[30].toString() + clientdet[31].toString(), 30);
                        clienttab[7] = prepareString(clientdet[32], 2);
                        clienttab[8] = prepareString(clientdet[6], 12);
                        clienttab[9] = prepareString(clientdet[33], 1);
                        clienttab[10] = prepareString(clientdet[9], 14);
                        clienttab[11] = prepareString(clientdet[12], 14);
                        clienttab[12] = prepareString(clientdet[13], 14);
                        clienttab[111] = "T";
                        fournToWrite.put(clientdet[5], clienttab);
                        // clienta.add(clienttab);
                    }
                }
            }
            //  System.out.println("fin des fournisseur");
            /*for(Enumeration enu=fournToWrite.keys();enu.hasMoreElements();){
             Object[] obj=(Object[])fournToWrite.get(enu.nextElement());
             String toaffiche="";
             for(int j=0;j<obj.length;j++){
                 toaffiche=toaffiche+" "+j+"//*"+obj[j];
             }
             System.out.println("fournisseur"+toaffiche);
         }*/
            return clienta;
        }
        return null;
    }

    public Hashtable getFournisseur() {
        return fournToWrite;
    }

    public Hashtable getClient() {
        return clienntToWrite;
    }


}
