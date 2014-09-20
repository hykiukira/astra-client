/* Classe Agenda
 *
 *Crée le 22 aout 2003
 *
 *par Driart
 *
 *Cette classe permet d'automatiser certaines actions en fonction de la date
 */

package srcastra.astra.sys.rmi;
import srcastra.astra.sys.classetransfert.utils.*;
import srcastra.astra.sys.classetransfert.dossier.*;
import srcastra.astra.sys.classetransfert.dossier.avion.*; 
import java.sql.*;
import srcastra.astra.sys.rmi.astraimplement;
import srcastra.astra.sys.rmi.utils.Poolconnection;
import java.rmi.*;
import srcastra.astra.sys.Transaction;
import srcastra.astra.sys.Logger;
import java.util.ArrayList;
import java.util.Hashtable;
import srcastra.astra.sys.classetransfert.utils.Date;
import srcastra.astra.sys.classetransfert.clients.*;
import srcastra.astra.sys.classetransfert.Loginusers_T;
import srcastra.astra.sys.rmi.Exception.*; 
import srcastra.astra.sys.produit.*;
import srcastra.astra.sys.compta.*;
import srcastra.astra.sys.rmi.Recherche.*;
import srcastra.astra.sys.compress.CompressArray;
import srcastra.astra.sys.classetransfert.*;
import srcastra.astra.sys.compta.*;
import srcastra.astra.gui.modules.printing.classelangueuser.*;
import srcastra.astra.sys.printing.PrintingInfo;
import srcastra.astra.gui.modules.printing.produit.produitInfo.*;
import srcastra.astra.gui.modules.compta.achat.*;
import srcastra.astra.sys.rmi.groupe_dec.*;
import java.io.*;
import java.util.*;




public class AgendaClass {



   protected Calendar calendrier;
   
   protected Date           date;
   protected Timer          timer;
   protected boolean        mode = true;   // true = automatique, false = manuel
   String                   avertissement;
 
   //constructeur par defaut
   
   public AgendaClass(){
   
            this.calendrier = calendrier.getInstance();
            this.date = date;
            this.timer = timer;
            this.mode = mode;
            this.avertissement = avertissement;
   } 
   
   //les aautres constructeurs
   
   public AgendaClass(Date date_, Timer timer_, boolean mode_, String avertissement_){
   
            this.calendrier = calendrier.getInstance();
            this.date = date_;
            this.timer = timer_;
            this.mode = mode_;
            this.avertissement = avertissement_;
   }
   
   public AgendaClass(Timer timer_, boolean mode_, String avertissement_){
   
            this.calendrier = calendrier.getInstance();
            this.date = date;
            this.timer = timer_;
            this.mode = mode_;
            this.avertissement = avertissement_;
   }
   
   public AgendaClass(Date date_, boolean mode_){
   
            this.calendrier = calendrier.getInstance();
            this.date = date_;
            this.timer = timer;
            this.mode = mode_;
            this.avertissement = avertissement;
   }
   
 
   public static void main(String [] args){
     
       AgendaClass a = new AgendaClass(CalculDate.getTodayDate(), true);
       
       System.out.println(" calendrier: "+a.calendrier);
       System.out.println(" date: "+a.date);
       System.out.println(" timer: "+a.timer);
       System.out.println(" avertissement: "+a.avertissement);
     
  
   }
   
   // actions à executer
   //il faut determiner les actions a faire et donc en fonction de ça on appele le 1,2,3, 4 ou 5
   //la date d'execution est la date d'echeance d'une action
   //le timer est le compteur
   public void action1 (Date execution, boolean modus, Timer latence){
   
                  if (modus = false)
                      mode = false;
                  date = execution;
                  timer = latence;
                 
               //action code here
   
               //SELECT d.dr_date_echeance FROM dossier d WHERE d.dr_numdos = ?
                  
   System.out.println("Avertissement! La date d'echeance pour la vente numéro: ");
   
   }

   public void action2 (Date execution, boolean modus, Timer latence){
   
                  if (modus = false)
                      mode = false;
                  date = execution;
                  timer = latence;
                  
               //action code here
   
              //SELECT f.dateecheance FROM facture f WHERE f.cleunik = ?
   
   System.out.println("Avertissement! La date d'echeance pour l'achat numéro: ");
   }
   
   public void action3 (Date execution, boolean modus, Timer latence){
   
                  if (modus = false)
                      mode = false;
                  date = execution;
                  timer = latence;
                  
               //action code here
   
   
   
   } 

}//end class Agenda