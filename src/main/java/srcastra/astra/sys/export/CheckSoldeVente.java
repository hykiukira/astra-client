/*
 * test12.java
 *
 * Created on 27 janvier 2004, 11:38
 */

package srcastra.astra.sys.export;
import java.sql.*;
import java.util.*;
/**
 *
 * @author  Administrateur
 */
public class CheckSoldeVente extends ExportMain implements ComptaExport{
    
    /** Creates a new instance of test12 */
    Hashtable dossierG;
    Hashtable dossierB;
    Hashtable dossierTMP;
    ArrayList cubicArray;
    String clientkey; 
    Hashtable clientKeyH=new Hashtable();
    //                          0           1           2               3               4               5           6                   7                   8                   9               10              11              12                      13                  14                  15              16              17              18              19                  20              21              22                   23                 24                  25              26              27              28                  29                      30                      31                      32                  33                      34              35              36                  37                  38              39          40              41              42              43              44                  45              46                  47              48              49          50              51                  52          53              54      
    String ventes="SELECT d.dr_numdos, c.csnom, d.dr_date_depart, h.`hecleunik` , h.`heperiode` , h.`henotcpt` , h.`heclottva` , h.`heclotperiode` , h.`heclotexercice` , h.`hetransact` , h.`jxcleunik` , h.`henumpiece` , h.`hedatecreation` , h.`hedatemouv` , h.`ce_cleunik_cent` , h.`tva_cleunik` , h.`ce_cleunik2` , cp.`ce_num` , h.`hevaleur` , t.tvic_cubic , h.`hevaleurbase` , h.`hevaleurtva` , h.`decleunik` , h.`hedatedevise` , h.`hevaleurdevise` , h.`helibelle` , h.`drcleunik` , h.`lignecleunik` , h.`sn_cleunik` , h.`ctprocleunik` , h.`typeintervenantcleunik` , h.`intervenantcleunik` , h.`cate_cleunik` , h.`hedossiercourant` , h.`hetypeligne` , h.`urcleunik` , h.`hetypepayement` , h.`helibellecompta2` , h.`pax` , h.`quantite` , h.`pourcent` , h.`hevaleuru` , h.`gn_cleunik` , h.`typegrpdec` , h.`exle_cleunik` , h.`he_echeance` , h.`he_reception` , h.`he_dc` , h.`he_exported` , e.eeabrev, j.jota_categorie, d.dr_date_depart, d.fournisseur, p.pede_numper,j.jota_abrev FROM  `historique2` h LEFT  JOIN dossier d ON ( h.drcleunik = d.dr_cleunik )  LEFT  JOIN clients c ON h.intervenantcleunik = c.cscleunik LEFT JOIN tvacubic t ON (h.tva_cleunik =t.tvic_cleunik), entite e, journcompta j, user u,periode p,compte cp  WHERE h.urcleunik = u.urcleunik AND u.eecleunik = e.eecleunik AND h.jxcleunik = j.jota_cleunik AND (h.hetypeligne =  'B' OR h.hetypeligne =  'D' OR h.hetypeligne =  'TVAV' OR h.hetypeligne =  'NC' OR h.hetypeligne =  'NCB' OR h.hetypeligne =  'NCT' OR h.hetypeligne =  'NCO' OR h.hetypeligne =  'NCOT' OR h.hetypeligne =  'NCOB')"+ 
    "AND h.henotcpt = 1  AND h.heperiode = p.pede_cleunik AND h.typeintervenantcleunik = 1 AND t.lmcleunik=? AND he_exported =0 AND h.drcleunik=? AND h.jxcleunik=? AND h.henumpiece=? AND cp.ce_cleunik=h.ce_cleunik2 ORDER BY h.hecleunik";
    String dossier="SELECT DISTINCT drcleunik,henumpiece,jxcleunik FROM historique2 WHERE henotcpt=1 AND (hetypeligne='B' OR hetypeligne='NCB' OR hetypeligne='NCOB')  AND he_exported =0 order by henumpiece";
    Connection con;
    int lmcleunik;
    ArrayList divArray;
    ArrayList comptArray;
    String historiquekey;
    GroupFacture groupefacture;
    ArrayList erreur;
    public CheckSoldeVente(int lmcleunik, Connection con, boolean bydossier, int pedecleunik,ArrayList erreur) throws SQLException{
        super(bydossier,pedecleunik); 
        this.erreur=erreur;
        this.lmcleunik=lmcleunik;
         this.con=con;
         clientkey="";
         historiquekey="";
         groupefacture=new GroupFacture(1);
       //  execute();
        
    }
    public static void main(String[] args){
       // new Vente(); 
        
    }
    public ArrayList execute() throws SQLException{
       // PreparedStatement pstmt=con.prepareStatement("LOCK TABLES historique2 WRITE");
       // pstmt.execute();
        //ResultSet result=pstmt.executeQuery();
        srcastra.astra.sys.utils.My_Logger.getLogger().info("CHECKING SALES");                       
        PreparedStatement  pstmt=con.prepareStatement(dossier);
       // pstmt.setInt(1,pedecleunik);
        ResultSet result=pstmt.executeQuery();
        result.beforeFirst();
        while(result.next()){
            if(dossierTMP==null)
                dossierTMP=new Hashtable();
            if(divArray==null){
              divArray=new ArrayList();   
            }
            DivideVente div=new DivideVente();
            div.setCledossier(result.getLong(1));
            div.setHenumpiece(result.getLong(2));
            div.setJxcleunik(result.getInt(3));
            divArray.add(div);
            String keys=""+div.getJxcleunik()+div.getHenumpiece();
            dossierTMP.put(keys,div);
        }
        //pstmt=con.prepareStatement("UNLOCK TABLES");
        //pstmt.execute();
        if(dossierTMP==null)
            return null;
        if(divArray!=null){
        for(int i=0;i<divArray.size();i++){
            if(comptArray==null)
                comptArray=new ArrayList();
                comptArray.add(getComptaDossier(lmcleunik,(DivideVente)divArray.get(i)));
            
        }              
      /*  clientkey="";
        if(clientKeyH!=null){
          for(Enumeration enu=clientKeyH.keys();enu.hasMoreElements();){
             clientkey=clientkey+clientKeyH.get(enu.nextElement())+",";
          }
        }
       if(clientkey.length()>0){
            clientkey=clientkey.substring(0,clientkey.length()-1);
            clientkey="IN ("+clientkey+") ";      
       }
       
        selectClient(lmcleunik, con, clientkey);
        for(int i=0;i<comptArray.size();i++){
            ArrayList tmp=(ArrayList)comptArray.get(i);
            prepareClient(tmp,  clientHash,false); 
        }*/
     //  System.out.println("check des vente");
       for(int i=0;i<comptArray.size();i++){
           ArrayList tmp=(ArrayList)comptArray.get(i);
           cubicFormat(tmp,null);
        }         
        }
         return cubicArray;     
    }
    private ArrayList getComptaDossier(int lmcleunik,DivideVente div) throws SQLException{
       
      // String historiquekey="";
       ArrayList dossierA=null;
       PreparedStatement pstmt=con.prepareStatement(ventes);
       pstmt.setInt(1,lmcleunik);
       pstmt.setLong(2, div.getCledossier());
       pstmt.setLong(3, div.getJxcleunik());
       pstmt.setLong(4,div.getHenumpiece());
       ResultSet result=pstmt.executeQuery();
       result.beforeFirst();
       while(result.next()){
           if(dossierA==null)
                dossierA=new ArrayList();
                Object[] tab=new Object[55];
                    for(int i=0;i<tab.length;i++){     
                        if(i==12 || i==13){           
                            tab[i]=new srcastra.astra.sys.classetransfert.utils.Date(result.getString(i+1)).toString3();                                                                  
                        }
                        else{
                            tab[i]=result.getObject(i+1);      
                        }
                    }
                if(tab[31]!=null){
                    clientkey=clientkey+tab[31].toString()+",";    
                    clientKeyH.put(tab[31].toString(), tab[31].toString());
                }
                    historiquekey=historiquekey+tab[3].toString()+",";
                    dossierA.add(tab);     
       }
      // printContrepartie(dossierA);
       return dossierA;
    }
  
    private ArrayList cubicFormat(ArrayList array,Hashtable client)throws SQLException{
      
        boolean longv=false;
        if(cubicArray==null)
            cubicArray=new ArrayList();
        if(array!=null){ 
            groupefacture.splitIntoAccoung(array);
            Object[] vente=new Object[135];
            for(int m=0;m<vente.length;m++){
                vente[m]="";
            }
           
           int indexcp=13;
           int indextva=67;
           
           for(int j=0;j<array.size();j++){
                 Object[] tmptab=(Object[])array.get(j); 
                 
               if(j==0){      
                   //System.out.println("dossier numero "+tmptab[0]);
                   String type="F"; 
                   vente[10]=changeValue(tmptab[18]);
                   if(tmptab[34].toString().equals("NCB") || tmptab[34].toString().equals("NCOB")){
                       type="N";             
                    
                   }
                   vente[0]=prepareString(tmptab[49].toString(),2);
                   vente[1]=new Integer(1);                 
                   vente[2]=prepareString(tmptab[54],3);
                   vente[3]=type;
                   vente[4]= generePeriode(tmptab[12],tmptab[53].toString());
                   vente[5]=prepareString(tmptab[13],8);
                   vente[6]=prepareString(tmptab[13],8);
                   vente[7]=prepareString(tmptab[11],6);
                   vente[8]=prepareString(tmptab[0],8);
                   if(tmptab[0]!=null)
                       vente[9]=prepareString(tmptab[0].toString()+tmptab[12].toString(),40);                          
                   else 
                       vente[9]=prepareString(" "+tmptab[12].toString(),40);                          
               }
           else{ 
                 if(tmptab[34].toString().equals("NC") || tmptab[34].toString().equals("NCO") || tmptab[34].toString().equals("D")){
                    //System.out.println("index vente "+indexcp);
                    if(indexcp<135){
                        vente[indexcp]=prepareString(tmptab[17],8);
                        vente[indexcp+1]=prepareString(tmptab[25],20);
                        vente[indexcp+2]=changeValue(tmptab[20]);
                    if(indexcp==64){
                        indexcp=123;
                        longv=true;
                    }
                    else
                        if(longv==false)
                            indexcp=indexcp+3;    
                        else
                            indexcp=indexcp+4;    
                    }
                   // indexcp=indexcp+3;             
                }
                 else if(tmptab[34].toString().equals("NCT")|| tmptab[34].toString().equals("NCOT") || tmptab[34].toString().equals("TVAV")){
                   // System.out.println("indextva "+indextva);
                    vente[indextva]=prepareString(tmptab[19],5);
                    vente[indextva+1]=changeValue(tmptab[18]);
                    vente[indextva+2]=changeValue(tmptab[20]);
                    indextva=indextva+3;     
                    CheckCubicReccord. checkTva(con,erreur, CheckCubicReccord.VENTE, tmptab);
                }
           }         
        }//  System.out.println("check des vente");
           CheckCubicReccord.checkReccordVente(vente,CheckCubicReccord.VENTE,erreur);
           cubicArray.add(vente); 
    }
   /*  for(int k=0;k<venteArray.size();k++){
      Object[] obj=(Object[])venteArray.get(k);
      String toaffiche="VENTE ";
      for(int j=0;j<obj.length;j++){
          toaffiche=toaffiche+" "+j+"//*"+obj[j];
      }     
      System.out.println(toaffiche);
     }
      */  
    return cubicArray;
    }
   
    
   
    
}
