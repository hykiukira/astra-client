/*
 * MY_bigDecimal.java
 *
 * Created on 7 juin 2004, 14:22
 */

package srcastra.astra.sys.rmi.utils;
import java.math.BigDecimal;
/**
 *
 * @author  Administrateur
 */
public class MY_bigDecimal implements java.io.Serializable{
    
    /** Creates a new instance of MY_bigDecimal */
    java.math.BigDecimal bigdec;
    String dc;
    public MY_bigDecimal(String val) {
       val=formatPrix(val);
       bigdec=new BigDecimal(val);
       bigdec=bigdec.setScale(2,BigDecimal.ROUND_HALF_EVEN);
    }
    public MY_bigDecimal(BigDecimal val) {
       bigdec=val;
    }
     public static String  formatPrix(String field){     
     int i=field.indexOf(".");
     if(i!=-1){
        String sub=field.substring(i+1);
        if(sub.length()==1){
            field=field+"0";        
        }           
     }
     return field;
    }
     public void inverse(){
       bigdec=bigdec.multiply(new BigDecimal("-1.00"));
         
     }
     public String toString(){
       return bigdec.toString();   
     
     }
     public MY_bigDecimal subtract(MY_bigDecimal sub) {
            BigDecimal retva=this.bigdec.subtract(sub.bigdec);
            retva=retva.setScale(2,BigDecimal.ROUND_HALF_EVEN);
            return new MY_bigDecimal(retva);
     
     }
    public MY_bigDecimal add(MY_bigDecimal add) {
            BigDecimal retva=this.bigdec.add(add.bigdec);
            retva=retva.setScale(2,BigDecimal.ROUND_HALF_EVEN);
            return new MY_bigDecimal(retva);
     
     }
     public MY_bigDecimal addWithoutScaling(MY_bigDecimal add) {
            BigDecimal retva=this.bigdec.add(add.bigdec);
            //retva=retva.setScale(2,BigDecimal.ROUND_HALF_EVEN);
            return new MY_bigDecimal(retva);
     
     }
    public void abs(){
          this.bigdec=bigdec.abs();
        
    }
     public MY_bigDecimal multiply(MY_bigDecimal multi) {
            BigDecimal retva=this.bigdec.multiply(multi.bigdec);
            retva=retva.setScale(2,BigDecimal.ROUND_HALF_EVEN);
            return new MY_bigDecimal(retva);
     
     }
      public MY_bigDecimal divide(MY_bigDecimal multi) {
            BigDecimal retva=this.bigdec.divide(multi.bigdec,2,BigDecimal.ROUND_HALF_EVEN);
            //retva=retva.setScale(2,BigDecimal.ROUND_HALF_EVEN);
            return new MY_bigDecimal(retva);
     
     }
     public MY_bigDecimal divide(MY_bigDecimal multi,int scale) {
            BigDecimal retva=this.bigdec.divide(multi.bigdec,scale,BigDecimal.ROUND_HALF_EVEN);
            //retva=retva.setScale(2,BigDecimal.ROUND_HALF_EVEN);
            return new MY_bigDecimal(retva);
     
     }
     /**
      * Getter for property bigdec.
      * @return Value of property bigdec.
      */
     public java.math.BigDecimal getBigdec() {
         return bigdec;
     }
     public static MY_bigDecimal calculCommission(BigDecimal com,BigDecimal montant){
        com.setScale(2,BigDecimal.ROUND_HALF_EVEN);
        BigDecimal decimalcom=com.divide(new BigDecimal("100.00"),4,BigDecimal.ROUND_HALF_EVEN);
        System.out.println("decimal com"+decimalcom.toString());
        BigDecimal comission=montant.multiply(decimalcom);
        System.out.println("montant com"+comission.toString());
        BigDecimal ExactCommission=comission.setScale(2,BigDecimal.ROUND_HALF_EVEN);
        System.out.println("montant com"+ExactCommission.toString());
        return new MY_bigDecimal(ExactCommission);
     
     }
     public double doubleValue(){
        return bigdec.doubleValue();
     }
     
     /**
      * Getter for property dc.
      * @return Value of property dc.
      */
     public java.lang.String getDc() {
         return dc;
     }
     
     /**
      * Setter for property dc.
      * @param dc New value of property dc.
      */
     public void setDc(java.lang.String dc) {
         this.dc = dc;
     }
     
     /**
      * Setter for property bigdec.
      * @param bigdec New value of property bigdec.
      */
    
     
}
