/* * clientastra.java * *  Created on 8 mars 2002, 14:39 *//* * client.java * * Created on 13 f�vrier 2002, 17:25 */package srcastra.astra.sys.rmi;import java.rmi.*;import java.rmi.registry.*;import java.io.*;import java.sql.*;//import srcastra.serveur.*;import java.util.*;import srcastra.astra.sys.classetransfert.* ;      public class clientastra      {        static public void main(String args[])         {                String                result="";          Connection con;          String message="";          System.out.println("houhou");          try {			System.out.println("je suis dedans");                                repertoire = (Registry)LocateRegistry.getRegistry("192.168.1.52");           // si le registry est sur une autre machine (p.ex. le serveur : utilisez plut�t l'adresse IP	   //    		repertoire = (Registry)LocateRegistry.getRegistry("123.456.789.012");           	      		un_serveur = (astrainterface)repertoire.lookup("astraimplement");          			 Loginusers_T[] logintab=un_serveur.returnusers(1);                                // System.out.println("resultat : "+ result);                                //con=un_serveur.connectdb("thomas2","vidaloca2",message);                                //System.out.println(con+"resultat"+message);                                //message=un_serveur.connectdb2("   ","   ");                                 //System.out.println("resultat"+message);                                 //try{                                 //getmetada();                                 //}catch(SQLException d)                                // {                                  //  System.out.println(d);                                                                       //}                                                    }         catch (RemoteException e)               			{ e.printStackTrace();  System.out.println("erreur"+e);}         catch (NotBoundException e)               			{ e.printStackTrace();  System.out.println("erreur"+e);}                   }     private static Registry 		repertoire;     private  static astrainterface	un_serveur;     //private Loginusers_T[] logintab;      }     