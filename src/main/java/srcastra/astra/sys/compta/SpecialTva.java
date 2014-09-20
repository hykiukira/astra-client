/*
 * SpecialTva.java
 *
 * Created on 31 december 2002, 10:54
 */

package srcastra.astra.sys.compta;

/**
 *
 * @author  Thomas
 */
public class SpecialTva {
    
    /** Creates a new instance of SpecialTva */
    public SpecialTva() {
    }
    public static Object fromFloaToString(Object m_data,Float data){
        if(data instanceof Float)
        {
            float tmp=((Float)data).floatValue();
            if(tmp==1.26f)
              m_data="6/21";
            else if(tmp==1.68f)
              m_data="8/21";
            else if(tmp==2.73f)
              m_data="13/21";
            else if(tmp==3.78f)
              m_data="18/21";   
           else
              m_data=data;
        }
        else
        m_data=data;     
        return m_data;
    }
     public static float fromStringToFloat(String tmp){
         float valeur=0; 
         try{
           valeur=Float.parseFloat(tmp);
           return valeur;
           
       }catch(NumberFormatException nn){
          if(tmp.equals("6/21"))
              valeur=1.26f;
          else if(tmp.equals("8/21"))
               valeur=1.68f;
          else if(tmp.equals("13/21"))
               valeur=2.73f;
          else if(tmp.equals("18/21"))
               valeur=3.78f;
         return valeur;
       }
     }
   public static void fromFloaToString(java.util.ArrayList m_vector){   
        for(int i=0;i<m_vector.size();i++)
       {    Object[] tab=(Object[])m_vector.get(i);
            //float tmp=((Float)tab[1]).floatValue();;
            try{
            float tmp=Float.parseFloat(tab[1].toString());
            if(tmp==1.26f)
              tab[1]="6/21";
            else if(tmp==1.68f)
              tab[1]="8/21";
            else if(tmp==2.73f)
              tab[1]="13/21";
            else if(tmp==3.78f)
              tab[1]="18/21";  
            }
            catch(NumberFormatException ne){
                
            }
       }
   }
}
