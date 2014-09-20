/*
 * ReformatCode.java
 *
 * Created on 26 octobre 2004, 20:43
 */

package srcastra.astra.sys.rmi;
import java.io.*;
import java.util.*;
/**
 *
 * @author  Administrateur
 */
public class ReformatCode {
    
    /** Creates a new instance of ReformatCode */
    public ReformatCode() {
        BufferedReader br=null;
        BufferedWriter bw=null;
        try{
            //C:\Documents and Settings\Administrateur\Mes documents\perforinco\srcnetbeans\main\main\srcastra\astra\gui\modules\dossier\productSpecification\gestionnairepaneaux\SupReducPanelEdition.java
            String fileName="C:\\Documents and Settings\\Administrateur\\Mes documents\\perforinco\\srcnetbeans\\main\\main\\srcastra\\astra\\sys\\export\\";
         //   String fileName="C:\\Documents and Settings\\Administrateur\\Mes documents\\perforinco\\srcnetbeans\\main\\main\\srcastra\\astra\\gui\\modules\\dossier\\productSpecification\\gestionnairepaneaux\\";
            System.out.println(System.getProperty("user.dir"));
            File file=new File(fileName+"DbfManager2.java");
            File newFile=new File(fileName+"DbfManager2.javabackup");
            bw=new BufferedWriter(new FileWriter(newFile));
            br=new BufferedReader(new FileReader(file));
            String str=null;
            List list=new ArrayList();
            while((str=br.readLine())!=null){
                str=str.trim();
                if(!str.equals("")){
                    System.out.println("*"+str+"*");
                    list.add("*"+str+"*");
                    str=str+"\n";
                    if(bw!=null){
                        bw.write(str);
                    }
                }
            }
        }
        catch(IOException in){
            in.printStackTrace();
        }
        finally{
         try{
            br.close();
            bw.close();
        }catch(IOException in){
            in.printStackTrace();
        }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ReformatCode();
        // TODO code application logic here
    }
    
}
