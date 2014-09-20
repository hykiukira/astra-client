/*
 * CheckCubicReccord.java
 *
 * Created on 11 mai 2004, 14:17
 */

package srcastra.astra.sys.export;
import java.util.*;
/**
 *
 * @author  Administrateur
 */
import java.sql.*;
public class CheckCubicReccord {
    
    /** Creates a new instance of CheckCubicReccord */
    public final static  int  VENTE=1;
    public final static  int  ACHAT=2;
    public final static  int  FINANCIER=3;
    public final static  int  OD=4;
    public CheckCubicReccord() {
        
    }
    public static void checkTva(Connection con,ArrayList array,int type,Object[] tab) throws SQLException{
        String requete="SELECT genField1 FROM tva WHERE tva_cleunik=?"; 
        PreparedStatement pstmt=con.prepareStatement(requete);
        String journal=tab[54].toString();
        String numpiece=tab[11].toString();
        int tva_cleunik=Integer.parseInt(tab[15].toString());
        pstmt.setInt(1, tva_cleunik);
        String code=" ";
        String err1="";
        String err2="";
        ResultSet result=pstmt.executeQuery();
        result.beforeFirst();
        while(result.next()){
          code=result.getString(1);             
        } 
        code=code.substring(0,1);
        if(code.equals("1")&& type==VENTE){
              err1="Erreur CHECKTVA VENTE: tva d'achat dans une facture de vente\nJournal : "+journal+"\nFacture : "+numpiece;
             

        }
        else if(code.equals("2")&& type==ACHAT){
              err2="Erreur CHECKTVA ACHAT: tva de vente dans une facture d'achat\nJournal : "+journal+"\nFacture : "+numpiece;
              array.add(err2);
        }
    }
    public static  void checkFinancier(Object[] main,Object[] maincp,int typef,ArrayList erreur){
        double maind=Double.parseDouble(main[18].toString());
        double cpd=Double.parseDouble(maincp[18].toString());
        String type="";
        if(typef==FINANCIER)
            type="FINANCIER";
        else if(typef==OD)
            type="OD";
        String err="";
     //   System.out.println("main "+maind);
      //  System.out.println("cp "+cpd);
        double total=maind+cpd;
      //  System.out.println("total "+total);
        if((maind+cpd)!=0){
            err="ERREUR SOLDE "+type+"\nJournal : "+main[54].toString()+"\nFacture : "+main[11].toString();  
            erreur.add(err);
        }
            
    }
    public static  ArrayList checkReccord(Object[] cubicrecords,int typef,ArrayList erreur){
        long tvamontant=0;
        long cp=0;
        long facture=0;         
        long cptva=0;
        String type="F";        
        if(cubicrecords!=null){
            if(cubicrecords[3]!=null){
                type=cubicrecords[3].toString();
            if(cubicrecords[10]!=null){
              facture=facture+Long.parseLong(cubicrecords[10].toString());   
            
            for(int i=15;i<64;i=i+3){
                if(cubicrecords[i]!=null){
                   
                    if(!cubicrecords[i].toString().equals("")){
                         long tmp=Long.parseLong(cubicrecords[i].toString());   
                         cp=cp+Long.parseLong(cubicrecords[i].toString());                                            
                         //System.out.println("i vente "+i+" cp "+tmp);
                    } 
                }
            }
            for(int i=68;i<93;i=i+3){
                if(cubicrecords[i]!=null){
                     if(!cubicrecords[i+1].toString().equals(""))    
                     {
                            long tmpbase=Long.parseLong(cubicrecords[i].toString());                       
                            long tmp=Long.parseLong(cubicrecords[i+1].toString());                   
                            tvamontant=tvamontant+tmp;     
                            cptva=cptva+tmpbase;
                        //    System.out.println("i vente "+(i+1)+" tva "+tmp);
                          //  System.out.println("i vente "+(i+1)+" base tva "+tmpbase);
                     }
                }
            }            
            // System.out.println("facture "+facture);
            // System.out.println("contrepartie "+cp);
            // System.out.println("tva "+tvamontant);
             //System.out.println("tvabase "+cptva);
         //   if(type.equals("N") && typef==VENTE){
          //      cp=cp*-1;
          //  }
            if(type.equals("F") && typef==ACHAT){
                cp=cp*-1;
            }
            long checkbase1=facture+(cp-tvamontant);
            long checkbase2=facture-cptva-tvamontant;
            if(checkbase1==0) ;
            else
            {
                //System.out.println("Test CheckBase 1  false\nNuméro de facture : "+cubicrecords[7].toString()+" journal : "+cubicrecords[2]+"\nMontant facture :  "+facture+"\nMontant contrepartie : "+cp+"\nMontant de la tva : "+tvamontant);
                erreur.add("Test CheckBase 1  false\nNuméro de facture : "+cubicrecords[7].toString()+" journal : "+cubicrecords[2]+"\nMontant facture :  "+facture+"\nMontant contrepartie : "+cp+"\nMontant de la tva : "+tvamontant);
            }
                if(checkbase2==0) ;
            else{
                //System.out.println("Test CheckBase 2  false\nNuméro de facture : "+cubicrecords[7].toString()+" journal : "+cubicrecords[2]+"\nMontant facture :  "+facture+"\nMontant contrepartie : "+cptva+"\nMontant de la tva : "+tvamontant);
                erreur.add("Test CheckBase 2  false\nNuméro de facture : "+cubicrecords[7].toString()+" journal : "+cubicrecords[2]+"\nMontant facture :  "+facture+"\nMontant contrepartie : "+cptva+"\nMontant de la tva : "+tvamontant);
            }
        }
        }
        }
        return null;
    }
     public static  ArrayList checkReccordVente(Object[] cubicrecords,int typef,ArrayList erreur){
        long tvamontant=0;
        long cp=0;
        long facture=0;
        long cptva=0;
        String type="F";
        if(cubicrecords!=null){
            if(cubicrecords[3]!=null){
                type=cubicrecords[3].toString();
            if(cubicrecords[10]!=null){
              facture=facture+Long.parseLong(cubicrecords[10].toString());

            for(int i=15;i<64;i=i+3){
                if(cubicrecords[i]!=null){

                    if(!cubicrecords[i].toString().equals("")){
                         long tmp=Long.parseLong(cubicrecords[i].toString());
                         cp=cp+Long.parseLong(cubicrecords[i].toString());
                         //System.out.println("i vente "+i+" cp "+tmp);
                    }
                }
            }
            for(int i=68;i<93;i=i+3){
                if(cubicrecords[i]!=null){
                     if(!cubicrecords[i+1].toString().equals(""))
                     {
                            long tmpbase=Long.parseLong(cubicrecords[i].toString());
                            long tmp=Long.parseLong(cubicrecords[i+1].toString());
                            tvamontant=tvamontant+tmp;
                            cptva=cptva+tmpbase;
                        //    System.out.println("i vente "+(i+1)+" tva "+tmp);
                          //  System.out.println("i vente "+(i+1)+" base tva "+tmpbase);
                     }
                }
            }
            // System.out.println("facture "+facture);
            // System.out.println("contrepartie "+cp);
            // System.out.println("tva "+tvamontant);
             //System.out.println("tvabase "+cptva);
         //   if(type.equals("N") && typef==VENTE){
          //      cp=cp*-1;
          //  }
            if(type.equals("F") && typef==ACHAT){
                cp=cp*-1;
            }
            long checkbase1=facture+(cp+tvamontant);
            long checkbase2=facture+(cptva+tvamontant);
            if(checkbase1==0) ;
            else
            {
                //System.out.println("Test CheckBase 1  false\nNuméro de facture : "+cubicrecords[7].toString()+" journal : "+cubicrecords[2]+"\nMontant facture :  "+facture+"\nMontant contrepartie : "+cp+"\nMontant de la tva : "+tvamontant);
                erreur.add("Test CheckBase 1  false\nNuméro de facture : "+cubicrecords[7].toString()+" journal : "+cubicrecords[2]+"\nMontant facture :  "+facture+"\nMontant contrepartie : "+cp+"\nMontant de la tva : "+tvamontant);
            }
                if(checkbase2==0) ;
            else{
                //System.out.println("Test CheckBase 2  false\nNuméro de facture : "+cubicrecords[7].toString()+" journal : "+cubicrecords[2]+"\nMontant facture :  "+facture+"\nMontant contrepartie : "+cptva+"\nMontant de la tva : "+tvamontant);
                erreur.add("Test CheckBase 2  false\nNuméro de facture : "+cubicrecords[7].toString()+" journal : "+cubicrecords[2]+"\nMontant facture :  "+facture+"\nMontant contrepartie : "+cptva+"\nMontant de la tva : "+tvamontant);
            }
        }
        }
        }
        return null;
    }

}
