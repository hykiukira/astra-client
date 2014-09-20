/*

 * Somme.java

 *

 * Created on 2 septembre 2003, 14:00

 */



package srcastra.astra.sys.compta;

import java.sql.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.sys.rmi.utils.*;

import srcastra.astra.sys.classetransfert.configuration.*;

import srcastra.astra.sys.classetransfert.dossier.*;

import java.util.*;

import srcastra.astra.sys.Transaction;

import srcastra.astra.sys.rmi.Exception.ServeurSqlFailure;

/**

 *

 * @author  thomas

 */

public class Somme {

    

    /** Creates a new instance of Somme */

    public Somme(int type,int joer_cleunik,double value,astraimplement serveur,int typeliquidité,Poolconnection tmpool) throws SQLException{

       

      

        

    }

    private static void  fillCaisse(Poolconnection tmpool,int categorie,long sode_cleunik)throws SQLException,ServeurSqlFailure{

        String check="SELECT * FROM caisse WHERE sode_cleunik =? AND case_categorie=?";

        PreparedStatement pstmt=tmpool.getConuser().prepareStatement(check);

        pstmt.setLong(1,sode_cleunik);

        pstmt.setInt(2,categorie);

        ResultSet result=pstmt.executeQuery();

        while (result.next()){

               return;

        }

         String insert="INSERT INTO caisse (sode_cleunik , case_categorie, case_solde) VALUES (?, ?, ?)";

         pstmt=tmpool.getConuser().prepareStatement(insert);

         pstmt.setLong(1,sode_cleunik);

         pstmt.setInt(2,categorie);

         pstmt.setDouble(3,0);

         pstmt.execute();

        

    }

    public static void SommeCaise(long sode_cleunik,Poolconnection tmpool,Payement_T pay) throws SQLException,ServeurSqlFailure{

        double solde=0;

        int categorie=0;

        boolean sw=false;

        categorie=pay.getCategorie();

        PreparedStatement pstmt;

        ResultSet result;

        fillCaisse(tmpool,srcastra.astra.sys.classetransfert.configuration.TypePayement.CHEQUE,sode_cleunik);

        fillCaisse(tmpool,srcastra.astra.sys.classetransfert.configuration.TypePayement.CASH,sode_cleunik);

        if(categorie==0){

            String checkCategorie="SELECT tynt_categorie FROM typepaiement WHERE tynt_cleunik=?";

            pstmt=tmpool.getConuser().prepareStatement(checkCategorie);

            pstmt.setInt(1,pay.getM_typepayement());

            result=pstmt.executeQuery();

            result.beforeFirst();

            while(result.next()){

                 categorie=result.getInt(1);

            }

        }

        String check="SELECT * FROM caisse WHERE sode_cleunik =? AND case_categorie=?";

        pstmt=tmpool.getConuser().prepareStatement(check);

        pstmt.setLong(1,sode_cleunik);

        pstmt.setInt(2,categorie);

        result=pstmt.executeQuery();

        while (result.next()){

                solde=result.getDouble(3);         

                sw=true;

        }

        

        if(!sw){

            String insert="INSERT INTO caisse (sode_cleunik , case_categorie, case_solde) VALUES (?, ?, ?)";

            pstmt=tmpool.getConuser().prepareStatement(insert);

            pstmt.setLong(1,sode_cleunik);

            pstmt.setInt(2,categorie);

            pstmt.setDouble(3,pay.getPrix());

            pstmt.execute();

            

        }else{          

                updateCaisse(categorie,solde+pay.getPrix(),sode_cleunik,tmpool,pay);

          

        }

        

    }

private static void testValideTransfert(int categorie,double value,long sode_cleunik,Poolconnection tmpool,String requete)throws SQLException,ServeurSqlFailure{

            boolean check=false;

            //PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete); 

            

            PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete); 

            pstmt.setLong(1,sode_cleunik);

            pstmt.setInt(2,categorie);

            pstmt.setDouble(3,value);

            ResultSet result=pstmt.executeQuery();

            result.beforeFirst();

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n[**********************************]VERIFY SOLDE");

            System.out.println("sodecleunik "+sode_cleunik+" categorie "+categorie+" value"+value);

            while(result.next()){

                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n[**********************************]VERIFY SOLDE");

                System.out.println("categorie    "+result.getInt(2)+" solde   "+result.getDouble(3));

                check=true;                  

            }

            if(!check){ 

                Transaction.rollback(tmpool.getConuser());

                ServeurSqlFailure sqe=new ServeurSqlFailure("La caisse à été modifier impossible de valider l'opération");

                sqe.setErrorcode(5200);

                throw sqe;  

            }   

} 

private static void updateCaisse(int categorie,double value,long sode_cleunik,Poolconnection tmpool,Payement_T pay)throws SQLException,ServeurSqlFailure{

     String transfert="UPDATE caisse SET case_solde=? WHERE sode_cleunik =? AND case_categorie=?";

     String insert="UPDATE caisse SET case_solde=? WHERE sode_cleunik =? AND case_categorie=?";

     String check="SELECT * FROM caisse WHERE sode_cleunik =? AND case_categorie=? AND case_solde=?"; 

     System.out.println("soldecheque dans updatecaisse "+pay.getCheque());

     System.out.println("soldecash dans updatecaisse"+pay.getCash());

   /*  if(pay.isTransfert()){

        testValideTransfert(TypePayement.CASH,pay.getCash(),sode_cleunik,tmpool,check);

        testValideTransfert(TypePayement.CHEQUE,pay.getCheque(),sode_cleunik,tmpool,check);    

     }*/

     if(categorie==TypePayement.CASH)

         pay.setCash(value);

     else if(categorie==TypePayement.CHEQUE)

         pay.setCheque(value);        

     PreparedStatement pstmt=tmpool.getConuser().prepareStatement(insert);

            pstmt.setDouble(1,value);

            pstmt.setLong(2,sode_cleunik);

            pstmt.setInt(3,categorie);

            pstmt.execute(); 

}

public static void getSoldes(astraimplement serveur,int eecleunik,int urcleunik,Poolconnection tmpool,CaisseData data,int periode,int excleunik)throws SQLException,java.rmi.RemoteException,srcastra.astra.sys.rmi.Exception.ServeurSqlFailure{

    if(serveur.renvConfigRmiObject(tmpool.getUrcleunik()).selectConfig(tmpool.getUrcleunik()).getCaisseparutilisateur()==1){

        if(urcleunik==0){

           //getSoldesByEntite(serveur,eecleunik,urcleunik,tmpool,data,periode,excleunik); 

        }

        else{

        //   getSoldeUser(serveur,eecleunik,urcleunik,tmpool,data,periode,excleunik);  

        }

    }

    else{

       //getSoldesByEntite(serveur,eecleunik,urcleunik,tmpool,data,periode,excleunik);

    }

}

public static void getSoldesByEntite(astraimplement serveur, int eecleunik, int urcleunik, Poolconnection tmpool, CaisseData data, int periode, int excleunik, int centralcle, int numper, Periode_T periodet)throws SQLException{

 //String caisseuser="SELECT h.hedatemouv, d.dr_numdos, h.heperiode, h.helibelle, h.hevaleur, t.tynt_categorie FROM historique2 h LEFT  JOIN dossier d ON h.drcleunik = d.dr_cleunik, typepaiement t,user u,entite e WHERE h.ce_cleunik2 =?  AND h.heperiode = ? AND h.urcleunik=u.urcleunik AND u.eecleunik=e.eecleunik AND e.eecleunik=? AND h.hetypepayement = t.tynt_cleunik ORDER BY h.hecleunik";

 //String requete="SELECT sum( c.case_solde ) , c.case_categorie FROM caisse c, solde s, userentitecompte ue, user u, entite e WHERE u.eecleunik = e.eecleunik AND ue.ureecleunik = u.urcleunik AND ue.uste_cat = 2 AND s.sode_divcleunik = ue.ce_cleunik AND c.sode_cleunik = s.sode_cleunik AND e.eecleunik = ? GROUP  BY c.case_categorie";

 //String requete="SELECT sum( c.case_solde ) , c.case_categorie FROM caisse c, solde s, userentitecompte ue, user u, entite e, periode p WHERE ue.ureecleunik = e.eecleunik  AND ue.uste_cat = 1 AND s.sode_divcleunik = ue.ce_cleunik AND c.sode_cleunik = s.sode_cleunik AND s.pede_cleunik = p.pede_cleunik AND p.pede_numper <= ? AND e.eecleunik = ? GROUP BY c.case_categorie";

 String requete="SELECT sum( c.case_solde ) , c.case_categorie FROM caisse c, solde s, userentitecompte ue, user u, entite e, periode p ,exercicecomptable ex WHERE u.eecleunik = e.eecleunik AND ue.ureecleunik = u.urcleunik AND ue.uste_cat = 1 AND s.sode_divcleunik = ue.ce_cleunik AND  c.sode_cleunik = s.sode_cleunik AND s.pede_cleunik = p.pede_cleunik AND p.pede_numper <=? AND e.eecleunik = ? AND p.exle_cleunik=ex.exle_cleunik AND ex.exle_courant ='O' GROUP BY c.case_categorie";

 String requete2="SELECT h.hedatemouv, d.dr_numdos, p.pede_numper, h.helibelle, h.hevaleur, t.tynt_categorie,LEFT(t.tynt_libelle1,2) FROM historique2 h LEFT  JOIN dossier d ON h.drcleunik = d.dr_cleunik , typepaiement t,user u,entite e,periode p WHERE  h.ce_cleunik_cent  = ? AND h.heperiode = p.pede_cleunik AND p.pede_cleunik=? AND h.urcleunik =u.urcleunik AND u.eecleunik=e.eecleunik AND e.eecleunik=? AND h.hetypepayement = t.tynt_cleunik AND h.urcleunik=u.urcleunik AND (h.hetypeligne='BP' OR h.hetypeligne='P') ORDER BY h.hecleunik";                

 getReccordsEntite(serveur, data, requete, requete2, tmpool, urcleunik, centralcle, periode, eecleunik, numper,1, periodet);

 //getReccordsEntite(serveur,solde,data,requete,caisseuser,tmpool);  

     //  getReccords(serveur,eecleunik,tmpool,data,periode,excleunik,requete,caissentite); 

}

public static void getSoldeUser(astraimplement serveur, CaisseData data, Poolconnection tmpool, int urcleunik, int periode, int centralcle, int numper, Periode_T periodet)throws SQLException{

 //String caisseuser="SELECT h.hedatemouv, d.dr_numdos, h.heperiode, h.helibelle, h.hevaleur,t.tynt_categorie  FROM historique2 h LEFT  JOIN dossier d ON h.drcleunik = d.dr_cleunik, journcompta j,typepaiement t  WHERE j.urcleunik = ? AND j.jota_categorie = ? AND h.jxcleunik = j.jota_cleunik AND h.heperiode = ? AND j.exle_cleunik=? AND h.hetypepayement=t.tynt_cleunik AND h.hetypeligne = 'P' ORDER BY h.hecleunik";    

 //String caisseuser="SELECT h.hedatemouv, d.dr_numdos,  p.pede_numper, h.helibelle, h.hevaleur, t.tynt_categorie FROM historique2 h LEFT  JOIN dossier d ON h.drcleunik = d.dr_cleunik, typepaiement t,periode p WHERE h.ce_cleunik2 = ? AND h.heperiode = p.pede_cleunik AND p.pede_cleunik=? AND h.urcleunik =? AND h.hetypepayement = t.tynt_cleunik AND(h.hetypeligne='BP' OR h.hetypeligne='P') ORDER BY h.hecleunik";

 String  somme="SELECT sum( c.case_solde ) , c.case_categorie FROM caisse c, solde s, userentitecompte ue, user u, entite e, periode p,exercicecomptable ex WHERE u.eecleunik = e.eecleunik AND ue.ureecleunik = u.urcleunik AND ue.uste_cat = 2 AND s.sode_divcleunik = ue.ce_cleunik AND c.sode_cleunik = s.sode_cleunik AND s.pede_cleunik = p.pede_cleunik AND p.pede_numper <= ? AND u.urcleunik = ? AND p.exle_cleunik=ex.exle_cleunik AND ex.exle_courant ='O' GROUP BY c.case_categorie";  

 String requete2="SELECT h.hedatemouv, d.dr_numdos,  p.pede_numper, h.helibelle, h.hevaleur, t.tynt_categorie,LEFT(t.tynt_libelle1,2) FROM historique2 h LEFT  JOIN dossier d ON h.drcleunik = d.dr_cleunik, typepaiement t,user u,periode p WHERE  h.ce_cleunik_cent  = ? AND  h.heperiode = p.pede_cleunik AND p.pede_cleunik=? AND h.urcleunik =u.urcleunik  AND h.hetypepayement = t.tynt_cleunik  AND u.urcleunik=? AND (h.hetypeligne='BP' OR h.hetypeligne='P') ORDER BY h.hecleunik";                  

 //String  sommePer="SELECT c.case_solde  , c.case_categorie FROM caisse c, solde s, userentitecompte ue, user u, entite e, periode p,exercicecomptable ex WHERE u.eecleunik = e.eecleunik AND ue.ureecleunik = u.urcleunik AND ue.uste_cat = 2 AND s.sode_divcleunik = ue.ce_cleunik AND c.sode_cleunik = s.sode_cleunik AND s.pede_cleunik = p.pede_cleunik AND p.pede_numper <= ? AND u.urcleunik = ? AND p.exle_cleunik=ex.exle_cleunik AND ex.exle_courant ='O' GROUP BY c.case_categorie";  

 //String requete="SELECT sum(c.case_solde),c.case_categorie FROM journcompta j, journcomptaper p, caisse c WHERE j.urcleunik = ? AND j.jota_categorie = ? AND p.jota_cleunik = j.jota_cleunik AND c.joer_cleunik = p.joer_cleunik GROUP BY c.case_categorie ORDER BY case_categorie";

 //String requete="SELECT sum(c.case_solde),c.case_categorie FROM  caisse c WHERE sode_cleunik=? GROUP BY c.case_categorie";

 //getReccords(serveur,data,somme,requete2,tmpool,urcleunik,periode,centralcle,numper);
getReccordsEntite(serveur, data, somme, requete2, tmpool, urcleunik, centralcle, periode, urcleunik, numper,2, periodet);
}

private static void getReccordsEntite(astraimplement serveur, CaisseData data, String requete, String caisse, Poolconnection tmpool, int urcleunik, int central, int periode, int eecleunik, int numper, int categorie, Periode_T periodet)throws SQLException{
 CaisseSolde.getSolde( numper, tmpool,data,categorie,eecleunik,false, periodet);
 PreparedStatement pstmt=tmpool.getConuser().prepareStatement(caisse);
 pstmt.setInt(1,central);
 pstmt.setInt(2,periode);
 pstmt.setInt(3,eecleunik);
 ResultSet result=pstmt.executeQuery();
 ResultSetMetaData meta=result.getMetaData();
 ArrayList array=null;
 while(result.next()){
 int size=meta.getColumnCount();
 Object[] data2=new Object[size];
  for(int i=0;i<size;i++){ 
     if(i==0)
         data2[i]=new srcastra.astra.sys.classetransfert.utils.Date(result.getString(i+1));

     else

     data2[i]=result.getObject(i+1);                
            }
            if(array==null)
                array=new ArrayList();
            array.add(data2);
        }
        data.setCaisse(array);   
}


private static void getReccords(astraimplement serveur,CaisseData data,String requete,String caisse,Poolconnection tmpool,int urcleunik,int periode,int centralcle,int numper)throws SQLException{

 PreparedStatement pstmt=tmpool.getConuser().prepareStatement(requete);

 pstmt.setInt(1,numper);

 pstmt.setInt(2,urcleunik);

 ResultSet result=pstmt.executeQuery();

        while(result.next()){

            if(result.getInt(2)==TypePayement.CARD){

                data.setSoldecc(result.getDouble(1));               

            }

            else if(result.getInt(2)==TypePayement.CASH){

                data.setSoldecash(result.getDouble(1));               

            }

            else if(result.getInt(2)==TypePayement.CHEQUE){

                data.setSoldecheque(result.getDouble(1));              

            }           

        }

        pstmt=tmpool.getConuser().prepareStatement(caisse);

        pstmt.setInt(1,centralcle);

        pstmt.setInt(2,periode);

        pstmt.setInt(3,urcleunik);

        result=pstmt.executeQuery();

        ResultSetMetaData meta=result.getMetaData();

        ArrayList array=null;

        while(result.next()){

            int size=meta.getColumnCount();

            Object[] data2=new Object[size];

            for(int i=0;i<size;i++){ 

                if(i==0)

                    data2[i]=new srcastra.astra.sys.classetransfert.utils.Date(result.getString(i+1));

                else

                    data2[i]=result.getObject(i+1);                

            }

            if(array==null)

                array=new ArrayList();

            array.add(data2);

        }

        data.setCaisse(array);   

    

}

public final static int CAISSE=1;

public final static int NONCAISSE=2;

}

