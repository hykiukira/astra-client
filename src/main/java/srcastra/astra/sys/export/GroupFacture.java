/*
 * GroupFacture.java
 *
 * Created on 22 avril 2004, 16:17
 */

package srcastra.astra.sys.export;
import java.util.*;
import srcastra.astra.sys.compta.*;
/**
 *
 * @author  Administrateur
 */
public class GroupFacture {
    
    /** Creates a new instance of GroupFacture */
    ChecContrePartie checkpartie;
    Hashtable compte;
    ArrayList contrepartie;
    public GroupFacture(int type) {
      if(type==1){
            checkpartie=new CheckVente();          
      }
      else {
          checkpartie=new CheckAchat();     
      }
     
      //contrepartie=new ArrayList();
    }
    public void splitIntoAccoung(ArrayList facture){
         // System.out.println("[********GENERATE FACTURE*********]\n\n\n");
          //System.out.println("\nArray des facture avant transform size : "+facture.size());
         compte=new Hashtable();
          if(compteContrepartie(facture)>18){
              split(facture);
              reduceHashtable(facture);              
          }     
    }
  private int compteContrepartie(ArrayList facture){
          int k=1;
          if(facture !=null){
              if(facture.size()<20)
                  return 0;
          for(int i=0;i<facture.size();i++){
              Object[] tmp=(Object[])facture.get(i);
              if(tmp!=null){
                  if(checkpartie.isContrePartie(tmp)){
                    k++;
                  }                  
              }
          }
        }
          //System.out.println("Nombre de contrepartie = "+k);
          return k;
  }  
  private void split(ArrayList facture){
      int k=1;
      ArrayList indexToRemove=new ArrayList();
      for(int i=0;i<facture.size();i++){
              Object[] tmp=(Object[])facture.get(i);
              if(tmp!=null){
                  if(checkpartie.isContrePartie(tmp)){
                    k++;
                    checkAccount(tmp);
                    indexToRemove.add(tmp);
                  }                  
              }
          }
       for(int i=0;i<indexToRemove.size();i++){
            Object rm=indexToRemove.get(i);
            facture.remove(rm);
       }       
     //  System.out.println("nombre de passage dans split "+k);
  }  
  private void checkAccount(Object[] tmp){
      ArrayList tmpA;
      if(compte.get(tmp[17])==null){
          tmpA=new ArrayList();
          tmpA.add(tmp);
          compte.put(tmp[17], tmpA);
      }
      else{
          tmpA=(ArrayList)compte.get(tmp[17]);
          tmpA.add(tmp);          
      }
  }
  private void reduceHashtable(ArrayList facture){
      int j=1;
     
      for(Enumeration enu=compte.keys();enu.hasMoreElements();){
          //System.out.println("Array N°"+j);
          ArrayList array=(ArrayList)compte.get(enu.nextElement());
          sumArray(array);
      }
      for(Enumeration enu=compte.keys();enu.hasMoreElements();){
          ArrayList array=(ArrayList)compte.get(enu.nextElement());
          for(int i=0;i<array.size();i++){
            facture.add(array.get(i));   
          }
      }
        //System.out.println("\nArray des facture après transform size : "+facture.size());
  }
  private void sumArray(ArrayList array){
     double value=0;
    // System.out.println("\nArray des compte avant transform size : "+array.size());
      for(int i=0;i<array.size();i++){
          
        Object[] tmp=(Object[])array.get(i); 
        double valuecp=getValue(tmp[20]);
        value=value+valuecp;
        value=MathRound.roundThisToDouble(value);
     //   System.out.println("cp "+i+" value "+valuecp);
       // System.out.println("new sum "+i+" value "+value);
      }
      int p=array.size()-1;
      Object[] tmp=(Object[])array.get(0);
      tmp[20]=new Double(value);
      array.clear();
      array.add(tmp);
      //System.out.println("\nArray des compte après transformsize : "+array.size());
  }
   private double getValue(Object obj){
       double dou=new Double(obj.toString()).doubleValue();
       dou=MathRound.roundThisToDouble(dou);
       return dou; 
    }
   private Object addValue(Object obj,Object obj2){
       double dou=new Double(obj.toString()).doubleValue();
       double dou2=new Double(obj2.toString()).doubleValue();
       dou=MathRound.roundThisToDouble(dou+dou2);
       return new Double(dou);  
    }
}
