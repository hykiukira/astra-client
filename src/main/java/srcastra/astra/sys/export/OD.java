/*

 * Caisse.java

 *

 * Created on 18 février 2004, 09:48

 */



package srcastra.astra.sys.export;

import java.sql.*;

import java.util.*;

/**

 *

 * @author  Administrateur

 */

public class OD extends ExportMain implements ComptaExport{

    

    /** Creates a new instance of Caisse */

    int lmcleunik;

    Connection con;

    ArrayList caisse;

    String clientkey="";

    String fournisseurkey="";

    String historiquekey=null;

    String od= 

    "SELECT d.dr_numdos, c.csnom, d.dr_date_depart, h.`hecleunik` , h.`heperiode` , h.`henotcpt` ,h.`heclottva` ,"+ 

    "h.`heclotperiode` , h.`heclotexercice` , h.`hetransact` , h.`jxcleunik` , h.`henumpiece` ,h.`hedatecreation` , "+

    "h.`hedatemouv` , h.`ce_cleunik_cent` , h.`tva_cleunik` , h.`ce_cleunik2` , cp.`ce_num` , h.`hevaleur` , t.tvic_cubic , "+

    "h.`hevaleurbase` , h.`hevaleurtva` , h.`decleunik` , h.`hedatedevise` , h.`hevaleurdevise` ,h.`helibelle` , h.`drcleunik` , "+

    "h.`lignecleunik` , h.`sn_cleunik` , h.`ctprocleunik` , h.`typeintervenantcleunik` , h.`intervenantcleunik` , h.`cate_cleunik` ,"+

    "h.`hedossiercourant` , h.`hetypeligne` , h.`urcleunik` , h.`hetypepayement` , h.`helibellecompta2` , h.`pax` , h.`quantite` , "+

    "h.`pourcent` , h.`hevaleuru` , h.`gn_cleunik` , h.`typegrpdec` , h.`exle_cleunik` , h.`he_echeance` , h.`he_reception` , h.`he_dc` ,"+

    "h.`he_exported` , e.eeabrev, j.jota_categorie, d.dr_date_depart, d.fournisseur, p.pede_numper,j.jota_abrev,c.csreference FROM  `historique2` h LEFT "+

    "JOIN dossier d ON ( h.drcleunik = d.dr_cleunik )  LEFT  JOIN clients c ON h.intervenantcleunik = c.cscleunik LEFT JOIN tvacubic t ON "+

    "(h.tva_cleunik =t.tvic_cleunik), entite e, journcompta j, user u,periode p,compte cp  WHERE h.urcleunik = u.urcleunik AND u.eecleunik = e.eecleunik "+

    "AND h.jxcleunik = j.jota_cleunik AND (h.hetypeligne =  'OBCC' OR h.hetypeligne =  'OCCC') AND h.henotcpt = 1  AND h.heperiode = p.pede_cleunik AND h.typeintervenantcleunik = 1 AND t.lmcleunik=?  AND he_exported =0 AND cp.ce_cleunik=h.ce_cleunik2 ORDER BY h.hecleunik FOR UPDATE";  

   public OD(int lmcleunik, Connection con, boolean bydossier,int pedecleunik) throws SQLException{

        super(bydossier,pedecleunik);

       

        this.lmcleunik=lmcleunik;

        this.con=con;

        historiquekey="";

    }

    private void organize() throws SQLException{

      int m=1;

      boolean od=false;

      for(int k=0;k<caisse.size();k++){

          od=true;

        Object[] obj=(Object[])caisse.get(k);

        String toaffiche="";

        if(obj[34].toString().equals("OBCC")){

            OBCC(obj);

        }

        else if(obj[34].toString().equals("OCCC")){

            OCCC(obj);

        }

        for(int j=0;j<obj.length;j++){

            toaffiche=toaffiche+" "+j+"//*"+obj[j];

        }     

        //System.out.println(m+" array"+toaffiche);

        m++;

        }

      //System.out.println("clientkey "+clientkey);

      if(od){

        selectClient(lmcleunik, con, clientkey);

        prepareClient(caisse, clientHash,true);

      }

    

    }

    private void OBCC(Object[] tab){
        if(bydossier){
            tab[57]="K"+tab[0].toString();   
        }else{
            tab[57]="K"+tab[55].toString();   
        }

    } 

    private void OCCC(Object[] tab){

             tab[57]="G"+tab[17].toString();

    }

    private void getArray() throws SQLException {

       //PreparedStatement pstmt=con.prepareStatement("LOCK TABLES historique2 h WRITE,dossier d READ,clients c READ,tvacubic t READ,entite e READ,journcompta j READ,user u READ,periode p READ,compte cp READ");

      // pstmt.execute();

       caisse=new ArrayList(); 

       PreparedStatement  pstmt=con.prepareStatement(od);

       pstmt.setInt(1,lmcleunik);

   //    pstmt.setInt(2,pedecleunik);

       ResultSet result=pstmt.executeQuery();

       result.beforeFirst();

           while(result.next()){               

            Object[] tab=new Object[58];

            for(int i=0;i<tab.length-2;i++){     

                if(i==12 || i==13){           

                    tab[i]=new srcastra.astra.sys.classetransfert.utils.Date(result.getString(i+1)).toString3();

                   // System.out.println("Result "+result.getString(i+1)+" tab "+tab[i].toString());

                }

                else{

                    tab[i]=result.getObject(i+1);      

                }

            }

             if(tab[31]!=null)

                clientkey=clientkey+tab[31].toString()+",";   

            historiquekey=historiquekey+tab[3].toString()+",";   

         caisse.add(tab);

     }

   // pstmt=con.prepareStatement("UNLOCK TABLES");

    // pstmt.execute();

     if(clientkey.length()>0){

        clientkey=clientkey.substring(0,clientkey.length()-1); 

        clientkey="IN ("+clientkey+") ";      

       }            

       if(historiquekey.length()>0){ 

        historiquekey=historiquekey.substring(0,historiquekey.length()-1);

        historiquekey="IN ("+historiquekey+") ";            

        setExportedS(historiquekey, con);

       }

   }

    private ArrayList prepareFinancierTab(){

         

        ArrayList venteArray=new ArrayList();    

        if(caisse!=null){

        for(int i=0;i<caisse.size();i++){

           int indexcp=13;

           int indextva=67;

                Object[] vente=new Object[135];

                for(int m=0;m<vente.length;m++){

                    vente[m]="";

                }

               //                               1           2           3               4               5               6               7               8                   9                   10                  11              12              13                  14                  15                  16                  17              18              19              20              21              22                  23                  24                  25                  26              27              28              29              30                  31                              32                      33                  34                  35                  36              37                  38                  39          40              41              42              43              44              45              46                      47              48          49               50         51              52                  53              54              55   

               // String financier="SELECT d.dr_numdos, f.frnom1, d.dr_date_depart, h.`hecleunik` , h.`heperiode` , h.`henotcpt` , h.`heclottva` , h.`heclotperiode` , h.`heclotexercice` , h.`hetransact` , h.`jxcleunik` , h.`henumpiece` , h.`hedatecreation` , h.`hedatemouv` , h.`ce_cleunik_cent` , h.`tva_cleunik` , h.`ce_cleunik2` , h.`ce_cleunik` , h.`hevaleur` , h.`hecodetva` , h.`hevaleurbase` , h.`hevaleurtva` , h.`decleunik` , h.`hedatedevise` , h.`hevaleurdevise` , h.`helibelle` , h.`drcleunik` , h.`lignecleunik` , h.`sn_cleunik` , h.`ctprocleunik` , h.`typeintervenantcleunik` , h.`intervenantcleunik` , h.`cate_cleunik` , h.`hedossiercourant` , h.`hetypeligne` , h.`urcleunik` , h.`hetypepayement` , h.`helibellecompta2` , h.`pax` , h.`quantite` , h.`pourcent` , h.`hevaleuru` , h.`gn_cleunik` , h.`typegrpdec` , h.`exle_cleunik` , h.`he_echeance` , h.`he_reception` , h.`he_dc` , h.`he_exported` , e.eeabrev, j.jota_categorie, d.dr_date_depart, d.fournisseur, p.pede_numper,j.jota_abrev FROM  `historique2` h LEFT  JOIN dossier d ON ( h.drcleunik = d.dr_cleunik )  LEFT  JOIN fournisseur f ON h.intervenantcleunik = f.frcleunik, entite e, journcompta j, user u,periode p  WHERE h.urcleunik = u.urcleunik AND u.eecleunik = e.eecleunik AND h.jxcleunik = j.jota_cleunik AND (h.hetypeligne =  'BP' OR h.hetypeligne =  'P' OR h.hetypeligne =  'CPC') AND h.henotcpt = 1  AND h.heperiode = p.pede_cleunik AND h.typeintervenantcleunik = 1";    

                 Object[] tmptab=(Object[])caisse.get(i); 

              

                  // System.out.println("the date 2***************************************$"+((srcastra.astra.sys.classetransfert.utils.Date)tmptab[13]).toString2());

                  // String date=((srcastra.astra.sys.classetransfert.utils.Date)tmptab[13]).toString3();

                  // System.out.println("the date "+date);

                  // System.out.println("the date 2***************************************$"+((srcastra.astra.sys.classetransfert.utils.Date)tmptab[13]).toString2());

                   vente[0]=prepareString(tmptab[49],2);

                   vente[1]=new Integer(1);                 

                   vente[2]=prepareString(tmptab[54],3);

                   vente[3]=prepareString(tmptab[11],6);

                   vente[4]= generePeriode(tmptab[13],tmptab[53].toString());

                   vente[5]=prepareString(tmptab[13],8);

                   vente[6]=prepareString(tmptab[57],9);

                //   vente[7]=prepareString(tmptab[11],6);//rem

                   vente[7]=prepareString(tmptab[0],8);

                   try{

                    vente[11]=changeValueOD(tmptab[18]);  

                   }catch(NumberFormatException nn){ 

                    vente[11]=tmptab[18];   

                   }

                   venteArray.add(vente);

        }     

          

    }

    /* for(int k=0;k<venteArray.size();k++){

      Object[] obj=(Object[])venteArray.get(k);

      String toaffiche="OD ";

      for(int j=0;j<obj.length;j++){

          toaffiche=toaffiche+" "+j+"//*"+obj[j];

      }     

      //System.out.println(toaffiche);

     }*/

        

    return venteArray;

    }

    public java.util.ArrayList execute() throws SQLException {

        System.out.println("Importation des OD");

        getArray();

        organize();        

        return prepareFinancierTab();

    }  

   

}

