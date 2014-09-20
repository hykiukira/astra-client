/*
 * DbfManager2.java
 *
 * Created on 18 septembre 2003, 14:41
 */
package srcastra.astra.sys.export;
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
            //System.out.println("writed");
            afficheDbf(array);
        }catch(xBaseJException xn){
            xn.printStackTrace();
        }
        catch(IOException in){
            in.printStackTrace();
        }
    }
    public DbfManager2(boolean sw) {
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
        // classDB.
        classDB.close();
        return field;
    }
    public ArrayList readFromFile(String file,char type )throws xBaseJException,IOException{
        ArrayList array=new ArrayList();
        DBF classDB=new DBF(file,type);
        for (int i = 1; i <= classDB.getRecordCount(); i++) {
            classDB.read();
            int col=classDB.getFieldCount();
            String tmpstring="";
            Object[] tab=new Object[col];
            String affiche="";
            for(int j=0;j<col;j++){
                Field field=classDB.getField(j+1);
                tab[j]=field.get();
                affiche=affiche+" "+tab[j].toString();
            }
            System.out.println(i+"affice in reader "+affiche);
            array.add(tab);
            //     i++;
        }
        classDB.close();
        return array;
    }
    private Object[] readClientReccord(DBF file)throws xBaseJException,IOException{
        //`CDE_LOC` , `C_NOCLIENT` , `C_NOM1` , `C_NOM2` , `C_ADRESSE` , `C_CPOS` , `C_LOCA` , `C_TELEB` , `C_TELEP` ,
        //`C_TELEFAX` , `C_TITRE`  `C_DATENAI` , `C_REFTVA` , `C_NOTVA` , `C_REGIME` , `C_LANG` ,`C_CMAILING` ,
        //`C_DTCOTIS` , `C_COTISAT` ,  `C_PAYS`
        // INSERT INTO `v_client` ( `CDE_LOC` , `C_NOCLIENT` , `C_NOM1` , `C_NOM2` , `C_ADRESSE` , `C_CPOS` , `C_LOCA` ,
        //`C_TELEB` , `C_TELEP` , `C_TELEFAX` , `C_TITRE` ,  `C_DATENAI` , `C_REFTVA` , `C_NOTVA` , `C_REGIME` , `C_LANG` ,`C_CMAILING` ,
        //`C_DTCOTIS` , `C_COTISAT` ,  `C_PAYS` ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        Object[] tab=new Object[20];
        tab[0]=file.getField("CDE_LOC").get().trim();
        tab[1]=file.getField("C_NOCLIENT").get().trim();
        tab[2]=file.getField("C_NOM1").get().trim();
        tab[3]=file.getField("C_NOM2").get().trim();
        tab[4]=file.getField("C_ADRESSE").get().trim();
        tab[5]=file.getField("C_CPOS").get().trim();
        tab[6]=file.getField("C_LOCA").get().trim();
        tab[7]=file.getField("C_TELEB").get().trim();
        tab[8]=file.getField("C_TELEP").get().trim();
        tab[9]=file.getField("C_TELEFAX").get().trim();
        tab[10]=file.getField("C_TITRE").get().trim();
        tab[11]=file.getField("C_DATENAI").get().trim();
        tab[12]=file.getField("C_REFTVA").get().trim();
        tab[13]=file.getField("C_NOTVA").get().trim();
        tab[14]=file.getField("C_REGIME").get().trim();
        tab[15]=file.getField("C_LANG").get().trim();
        tab[16]=file.getField("C_CMAILING").get().trim();
        tab[17]=file.getField("C_DTCOTIS").get().trim();
        tab[18]=file.getField("C_COTISAT").get().trim();
        tab[19]=file.getField("C_PAYS").get().trim();
        return tab;
    }
    public ArrayList readClientFromFile(String file,char type )throws xBaseJException,IOException{
        ArrayList array=new ArrayList();
        DBF classDB=new DBF(file);
        System.out.println("taille du fichier"+classDB.getRecordCount());
        for (int i = 1; i <= classDB.getRecordCount(); i++) {
            //v.`C_NOCLIENT` ,v.`CDE_LOC`, v.`C_NOM1` , v.`C_NOM2` , v.`C_ADRESSE` , v.C_LOCA , v.`C_TELEB` , v.`C_TELEP` ,
            //v.`C_TELEFAX` , v.`C_TITRE` , ' ' , v.`C_DATENAI`  , v.C_REFTVA, v.`C_NOTVA` , v.`C_REGIME` , v.`C_LANG` , v.`C_CMAILING`
            //, v.`C_DTCOTIS` , v.`C_COTISAT` , v.`C_PAYS`
            classDB.read();
            Object[] tab=readClientReccord(classDB);
            String affiche="";
            for(int j=0;j<tab.length;j++){
                affiche=affiche+" "+tab[j].toString();
            }
            System.out.println(i+" "+affiche);
            array.add(tab);
/*        int col=classDB.getFieldCount();
String tmpstring="";
Object[] tab=new Object[col];
String affiche="";
if(i==831)
System.out.println("tamere");
for(int j=0;j<col;j++){
if(j!=12){
//  System.out.println(j);
Field field=classDB.getField(j+1);
tab[j]=field.get();
//  System.out.println(i+"tmp"+tab[j].toString());
affiche=affiche+" "+tab[j].toString();
}
else{
xBaseJ.MemoField field=classDB.getField(j+1);
tab[j]=field.get();
affiche=affiche+" "+tab[j].toString();
}
}
System.out.println(i+"affice in reader "+affiche);
array.add(tab);
 */
            //  i++;
        }
        classDB.close();
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
                        int num=0;
                        if(data[i].toString().equals(""))
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
                aDB.close();
            }
    }
    public static int isEmpty(String file){
        try{
            DBF adb=new DBF(file);
            return adb.getRecordCount();
        }catch(IOException in){
            in.printStackTrace();
        }
        catch(xBaseJException xn){
            xn.printStackTrace();
        }
        return 0;
    }
    public static void EmptyFile(String file){
        try{
            DBF classDB=new DBF(file);
            classDB.close();
            classDB=new DBF(file);
            System.out.println("reccord count :"+classDB.getRecordCount());
            if(classDB.getRecordCount()>0){
                classDB.startTop();
                for (int i = 1; i <= classDB.getRecordCount(); i++)
                {                             classDB.read();
                                              classDB.delete();
                }
                classDB.pack();
            }
            classDB.close();
        }catch(IOException in){
            in.printStackTrace();
        }
        catch(xBaseJException xn){
            xn.printStackTrace();
        }
        catch(CloneNotSupportedException cn){
            cn.printStackTrace();
        }
    }
    public void writeToDBF2(String file,ArrayList data2)throws xBaseJException,IOException{
        DBF aDB;
        if(data2!=null){
            aDB=new DBF(file);
            for(int j=0;j<data2.size();j++){
                //aDB.readPrev();
                //if(j!=data2.size()-2)
                //  aDB.gotoRecord(aDB.getRecordCount());
                //aDB.readPrev();
                //aDB.readPrev();
                //aDB.readPrev();
                // System.out.println("Numero du reccord"+aDB.getCurrentRecordNumber());
                int col= aDB.getFieldCount();
                String tmpstring="";
                Object[] data=(Object[])data2.get(j);
                for(int i=0;i<col;i++){
                    //  System.out.println(i+" "+data[i].toString());
                    Field field= aDB.getField(i+1);
                    if(field instanceof CharField){
                        int m=field.length();
                        String value="                                                                                  ";
                        if(data[i].toString().equals("")){
                            value=value.substring(0, m);
                        }else{
                            value=data[i].toString();
                        }
                        //System.out.println("value "+i+"   "+value);
                        
                        if(field.getName().equals("JTOTDOC"))
                            if(value.charAt(0)!='C' && value.charAt(0)!='D')
                            value=" "+value;
                        
                        
                        
                        ((CharField)field ).put(value);
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
            // aDB.gotoRecord(aDB.getRecordCount()+1);
            // aDB.delete();
        }
        // aDB.readPrev();
    }
    public void writeToDBFHash(String file,Hashtable data2,int type)throws xBaseJException,IOException,MyXbaseException{
        Field field=null;
        String value="";
        ArrayList retval=new ArrayList();
        retval.add(file);
        try{
            DBF aDB;
            if(data2!=null){
                aDB=new DBF(file);
                for(Enumeration enu=data2.keys();enu.hasMoreElements();){
                    Object[] data=(Object[])data2.get(enu.nextElement());
                    String affiche="";
                    for(int j=0;j<data.length;j++){
                        affiche=affiche+"/"+data[j];
                    }
                }
                for(Enumeration enu=data2.keys();enu.hasMoreElements();){
                    int col= aDB.getFieldCount();
                    String tmpstring="";
                    Object[] data=(Object[])data2.get(enu.nextElement());
                    for(int i=0;i<col;i++){
                        field= aDB.getField(i+1);
                        value="";
                        if(type==CLIENT){
                            value=getRightField(field.name(), data);
                        }
                        else if(type==FOURNISSEUR){
                            value=getRightFourField(field.name(), data);
                        }else{
                            value=data[i].toString();
                        }
                        retval.add(file+" Field "+field.getName()+" VALUE "+value);
                        if(field instanceof CharField){
                            if(type!=CLIENT && type!=FOURNISSEUR){
                                int m=field.length();
                                value="                                                                                  ";
                                if(data[i].toString().equals("")){
                                    value=value.substring(0, m);
                                }else{
                                    value=data[i].toString().substring(0,m);
                                }
                            }
                            ((CharField)field ).put(value);
                        }
                        else  if(field instanceof DateField){
                            ((DateField)field ).put(value);
                        }
                        else  if(field instanceof NumField){
                            ((NumField)field ).put(value);
                        }
                        else  if(field instanceof FloatField){
                            ((FloatField)field ).put(value);
                        }
                        else  if(field instanceof LogicalField){
                            ((LogicalField)field ).put(value);
                        }
                        else  if(field instanceof MemoField){
                            ((MemoField)field ).put(value);
                        }
                    }
                    aDB.write();
                }
            }
        }catch(xBaseJException xn){
            MyXbaseException myXbase=new MyXbaseException(retval);
            myXbase.setParent(xn);
            throw  myXbase;
        }
        catch(IOException in){
            throw in;
        }
        // aDB.readPrev();
    }
   
    private String getRightField(String fieldName,Object[] data){
        if(fieldName.equals("CDE_LOC")){
            return data[0].toString();
        }
        else if(fieldName.equals("IS_TRANSF")){
            return data[1].toString();
        }
        else if(fieldName.equals("C_NUMERO")){
            return data[2].toString();
        }
        else if(fieldName.equals("C_ADRESSE")){
            return data[4].toString();
        }
        else if(fieldName.equals("C_REGIME")){
            return data[6].toString();
        }
        else if(fieldName.equals("C_REFTVA")){
            return data[7].toString();
        }
        else if(fieldName.equals("C_OPT4")){
            return data[9].toString();
        }
        else if(fieldName.equals("C_OPT2")){
            return data[11].toString();
        }
        else if(fieldName.equals("C_OPT1")){
            return data[12].toString();
        }
        else if(fieldName.equals("C_NUMTVA")){
            return data[13].toString();
        }
        else if(fieldName.equals("C_NUMTEL")){
            return data[14].toString();
        }
        else if(fieldName.equals("C_NUMFAX")){
            return data[15].toString();
        }
        else if(fieldName.equals("C_NOM1")){
            return data[16].toString();
        }
        else if(fieldName.equals("C_NOM2")){
            return data[18].toString();
        }
        else if(fieldName.equals("C_LOCALITE")){
            return data[19].toString();
        }
        else if(fieldName.equals("C_LAN")){
            return data[20].toString();
        }
        else if(fieldName.equals("C_TYPE")){
            return data[24].toString();
        }
        else if(fieldName.equals("C_DELESCPT")){
            return data[32].toString();
        }
        else if(fieldName.equals("C_IMPUT")){
            return data[126].toString();
        }
        return "";
    }
    private String getRightFourField(String fieldName,Object[] data){
        //SELECT f.frcleunik, f.frreference1, f.frreference2, f.frnom1, f.frnom2, 
        //f.fradresse, f.frtvanum, f.frtvatype, f.frtvaregime, f.frnumbanque1, 
        //f.frnumbanque2, f.frnumbanque3, f.frtelephone1, f.frfax, f.frmail, 
        //f.frmodecccf, f.frdelaipaienbrjour, f.frdomiciliation, f.frmemo, f.frdatetimecrea, 
        //f.frdatetimemodif, f.snumerosessioncrea, f.snumerosessionmodif, f.decleunik, f.cxcleunik, 
        //f.aecleunik, f.lecleunik, f.pyscleunik, f.frfournprod, f.frNcompte, c.cxcode, tc.txtraduction, 
        //tt.Traduction,rn.Traduction FROM fournisseur f LEFT  JOIN typetva_traduction tt ON ( tt.typtva_cleunik = f.frtvatype AND tt.lmcleunik = ?  ) LEFT JOIN regtva_traduction rn ON(rn.regtva_cleunik=f.frtvaregime AND rn.lmcleunik=?), codepostaux c, traductioncodpostaux tc WHERE f.cxcleunik = c.cxcleunik AND c.cxcleunik = tc.cxcleunik AND tc.lmcleunik = ? AND frcleunik "+fournisseurkey;
        srcastra.astra.sys.utils.My_Logger.getLogger().info("GETTING FOURNISSEUR FIELD "+fieldName);
        if(fieldName.equals("CDE_LOC")){
            return data[0].toString();
        }
        else if(fieldName.equals("IS_TRANSF")){
            return data[1].toString();
        }
        else if(fieldName.equals("F_NUMERO")){
            return data[2].toString();
        }
        else if(fieldName.equals("F_NOM1")){
            return data[3].toString();
        }
        else if(fieldName.equals("F_NOM2")){
            return data[4].toString();
        }
        else if(fieldName.equals("F_ADRESSE")){
            return data[5].toString();
        }
        else if(fieldName.equals("F_LOCALITE")){
            return data[6].toString();
        }
        else if(fieldName.equals("F_RETVA")){
            return data[7].toString();
        }
        else if(fieldName.equals("F_NUMTVA")){
            return data[8].toString();
        }
        else if(fieldName.equals("F_REGIME")){
            return data[9].toString();
        }
        else if(fieldName.equals("F_NUMBQE")){
            return data[10].toString();
        }
        else if(fieldName.equals("F_NUMTEL")){
            return data[11].toString();
        }
        else if(fieldName.equals("F_NUMFAX")){
            return data[12].toString();
        }
        else if(fieldName.equals("F_IMPUT")){
            return data[111].toString();
        }
        else if(fieldName.equals("F_DEV")){
           return "EUR";
        } 
        return "";
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
            //System.out.println("Reccord N° "+i+" :"+toprint);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new DbfManager2();
    }
    public static final int CLIENT=1;
    public static final int FOURNISSEUR=2;
}
