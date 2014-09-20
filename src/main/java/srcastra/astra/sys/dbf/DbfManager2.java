/*
 * DbfManager2.java
 *
 * Created on 18 septembre 2003, 14:41
 */

package srcastra.astra.sys.dbf;
import java.io.*;
import java.util.*;
import xBaseJ.*;

/**
 *
 * @author  thomas
 */
public class DbfManager2 {
    
    /** Creates a new instance of DbfManager2 */
    public DbfManager2() {
        try{
           ArrayList array=readFromFile("C:\\Documents and Settings\\thomas\\Bureau\\dbf\\TEMPORAS.DBF",DBF.READ_ONLY);
           Field[] field=getFieldType("C:\\Documents and Settings\\thomas\\Bureau\\dbf\\TEMPORAS.DBF",DBF.READ_ONLY);
         //  writeToDBF("C:\\Documents and Settings\\thomas\\Bureau\\dbf\\TEMPORAS.DBF",false,field,array);
           writeToDBF2("C:\\Documents and Settings\\thomas\\Bureau\\dbf\\TEMPORAS.DBF",array);
           System.out.println("writed");
           afficheDbf(array);
        }catch(xBaseJException xn){
          xn.printStackTrace();   
        }
        catch(IOException in){
          in.printStackTrace();   
        }
    }
 public Field[] getFieldType(String file,char type) throws xBaseJException,IOException{
      Field[] field;
      DBF classDB=new DBF(file,type); 
				classDB.read();
                                int col=classDB.getFieldCount();
                                String tmpstring="";
                                field=new Field[col];
                                for(int j=0;j<col;j++){
                                    field[j]=classDB.getField(j+1);
                                }
                                return field;  
 }
 public ArrayList readFromFile(String file,char type )throws xBaseJException,IOException{
      ArrayList array=new ArrayList();
      DBF classDB=new DBF(file,type);
            for (int i = 1; i <= classDB.getRecordCount(); i++)
			{
				classDB.read();
                                int col=classDB.getFieldCount();
                                String tmpstring="";
                                Object[] tab=new Object[col];
                                for(int j=0;j<col;j++){
                                    Field field=classDB.getField(j+1);
                                    tab[j]=field.get();
                                }
                                array.add(tab);
				
			}
      return array;
     
 }
 public void writeToDBF(String file,boolean destroy,Field[] field,ArrayList data2)throws xBaseJException,IOException{
     DBF aDB;
     if(!destroy){
        aDB=new DBF(file);
        aDB.startBottom();
     }
     else
         aDB=new DBF(file,destroy);
     if(field!=null)        
           ;// aDB.addField(field); 
     if(data2!=null)
        for(int j=0;j<data2.size();j++){
            Object[] data=(Object[])data2.get(j);
         for(int i=0;i<field.length;i++){
             if(field[i] instanceof CharField){
                ((CharField)field[i] ).put(data[i].toString()); 
             }
             else  if(field[i] instanceof DateField){
                ((DateField)field[i] ).put(data[i].toString()); 
             }
             else  if(field[i] instanceof NumField){
                ((NumField)field[i] ).put(data[i].toString()); 
             }
             else  if(field[i] instanceof FloatField){
                ((FloatField)field[i] ).put(data[i].toString()); 
             }
              else  if(field[i] instanceof LogicalField){
                ((LogicalField)field[i] ).put(data[i].toString()); 
             }
              else  if(field[i] instanceof MemoField){
                ((MemoField)field[i] ).put(data[i].toString()); 
             }
         } 
        aDB.write();
     }
 }
 public void writeToDBF2(String file,ArrayList data2)throws xBaseJException,IOException{
     DBF aDB;
    
        aDB=new DBF(file);
        for(int j=0;j<data2.size();j++){
            
            //aDB.readPrev();
            //if(j!=data2.size()-2)
            aDB.gotoRecord(aDB.getRecordCount());
            //aDB.readPrev();
            //aDB.readPrev();
            //aDB.readPrev(); 
            System.out.println("Numero du reccord"+aDB.getCurrentRecordNumber());
            int col= aDB.getFieldCount();
            String tmpstring="";
            Object[] data=(Object[])data2.get(j);
             for(int i=0;i<col;i++){
                Field field= aDB.getField(i+1);
                if(field instanceof CharField){
                    ((CharField)field ).put(data[i].toString()); 
                 }
                 else  if(field instanceof DateField){
                    ((DateField)field ).put(data[i].toString()); 
                 }
                 else  if(field instanceof NumField){
                    ((NumField)field ).put(data[i].toString()); 
                 }
                 else  if(field instanceof FloatField){
                    ((FloatField)field ).put(data[i].toString()); 
                 }
                  else  if(field instanceof LogicalField){
                    ((LogicalField)field ).put(data[i].toString()); 
                 }
                  else  if(field instanceof MemoField){
                    ((MemoField)field ).put(data[i].toString()); 
                 }
              }
           
                aDB.write();
             }
         aDB.gotoRecord(aDB.getRecordCount()+1);
         aDB.delete();
           // aDB.readPrev();
   
 }
 private void afficheDbf(ArrayList array){
        for(int i=0;i<array.size();i++){
            String toprint="";
            Object[] tmp=(Object[])array.get(i);
             for(int j=0;j<tmp.length;j++){
                 String aff="";
                 if(tmp[j]!=null)
                     aff=tmp[j].toString();
                 toprint=toprint+"    "+aff;
             }
            System.out.println("Reccord N° "+i+" :"+toprint);            
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new DbfManager2();
    }
    
}
