/* * CalculDate.java * * Created on 5 septembre 2002, 11:00 */package srcastra.astra.sys.classetransfert.utils;import java.util.*;/** * * @author  Thomas */public class CalculDate {        /** Creates a new instance of CalculDate */    public CalculDate(srcastra.astra.sys.classetransfert.utils.Date date1, srcastra.astra.sys.classetransfert.utils.Date date2) {            }    public static void main(String args[]){       checkTime(10,11,0);    }    public static int checkTime(int hour1,int hour2,int am_pm){        int retval=0;        SimpleTimeZone pdt =new SimpleTimeZone(3600000,"Europe/Paris");        Calendar c1=new GregorianCalendar(pdt);        System.out.println("time : "+(c1.get(c1.HOUR))+" "+c1.get(c1.MINUTE)+" "+c1.get(c1.SECOND)+" "+c1.get(c1.AM_PM));        int hour=c1.get(c1.HOUR);       // hour=hour+1;        int calam_pm=c1.get(c1.AM_PM);        System.out.println("heure 1 :"+hour1);        System.out.println("heure 2 :"+hour2);        System.out.println("am pm : "+am_pm);        if(hour1<=hour && hour<=hour2 && am_pm==calam_pm){            System.out.println("je lance le thread");            retval=1;        }        return retval;    }     public static String getTime(){        int retval=0;        SimpleTimeZone pdt =new SimpleTimeZone(3600000,"Europe/Paris");        Calendar c1=new GregorianCalendar(pdt);        String time="time : "+(c1.get(c1.HOUR))+" "+c1.get(c1.MINUTE)+" "+c1.get(c1.SECOND);        return time;    }    public static int[] renvDifferenceBetweenDate(srcastra.astra.sys.classetransfert.utils.Date date1, srcastra.astra.sys.classetransfert.utils.Date date2) {         int[] returnvalue;         if(!date1.isOpen() && !date1.isUnknow() && !date2.isOpen() && !date2.isUnknow()){        //  String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);         // if (ids.length == 0)           //     System.exit(0);             //   System.out.println("Current Time");       //  SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, ids[0]);         SimpleTimeZone pdt =new SimpleTimeZone(3600000,"Europe/Paris");         Calendar c1=new GregorianCalendar(pdt);         c1.set(date1.year,date1.month-1,date1.day);         // c1.setLenient(false);         //java.util.Date dateJ1 = c1.getTime();         System.out.println("\nla date1 "+date1.day+"  "+date1.month+"  "+date1.year);        // c1.set(date1.year,date1.month-1,date1.day);         System.out.println("lenient :"+c1.isLenient());         System.out.println("year "+c1.get(c1.YEAR)+"  "+c1.get(Calendar.MONTH)+" "+c1.get(Calendar.DATE)+"max day = "+c1.getActualMaximum(Calendar.DAY_OF_MONTH));         long t1=c1.getTimeInMillis();         System.out.println("\nla date2 "+date2.day+"  "+date2.month+"  "+date2.year);         Calendar c2=new GregorianCalendar(pdt);         c2.set(date2.year,date2.month-1,date2.day);        // c2.setLenient(false);         java.util.Date dateJ2 = c2.getTime();         long difftime=c2.getTimeInMillis()-c1.getTimeInMillis();         long difday=difftime/(24*60*60*1000);            System.out.println("Calcul 2 :"+difday);        // c2.set(date2.year,date2.month-1,date2.day);         System.out.println("year "+c2.get(Calendar.YEAR)+"  "+c2.get(Calendar.MONTH)+" "+c2.get(Calendar.DAY_OF_MONTH)+"max day = "+c2.getActualMaximum(Calendar.DAY_OF_MONTH));         long t2=c2.getTimeInMillis();         long diff=t2-t1;         long day=diff/(1000*3600*24);         System.out.println("nbr jour:"+day);         returnvalue=new int[2];         returnvalue[0]=(int)day;         returnvalue[1]=(int)day+1;        }        else{         returnvalue=new int[2];         returnvalue[0]=-1;         returnvalue[1]=-1;                    }         return returnvalue;    } public static int renvBiggerDate(srcastra.astra.sys.classetransfert.utils.Date date1, srcastra.astra.sys.classetransfert.utils.Date date2) {         int[] returnvalue;              // Calendar c1=new GregorianCalendar(date1.year,date1.month-1,date1.day);         SimpleTimeZone pdt =new SimpleTimeZone(3600000,"Europe/Paris");         Calendar c1=new GregorianCalendar(pdt);         System.out.println("\nla date1 "+date1.day+"  "+date1.month+"  "+date1.year);         c1.set(date1.year,date1.month+1,date1.day);         long t1=c1.getTimeInMillis();         System.out.println("\nla date2 "+date2.day+"  "+date2.month+"  "+date2.year);         c1.set(date2.year,date2.month+1,date2.day);         long t2=c1.getTimeInMillis();         if(t1==t2)             return 0;         else if(t1<t2)             return -1;         else if(t1>t2)                         return 1;          return 0;         //return returnvalue;     }  public static void calculPeriodeComptable(srcastra.astra.sys.classetransfert.utils.Date debut,srcastra.astra.sys.classetransfert.utils.Date fin,String annee,int periode1,int periode2){      Calendar c1=Calendar.getInstance();//      int tmpannee=Integer.parseInt(annee);      c1.set(tmpannee,periode1,01);      debut.setYear(c1.get(Calendar.YEAR));      debut.setMonth(c1.get(Calendar.MONTH)+1);      debut.setDay(c1.get(Calendar.DAY_OF_MONTH));      if(periode2>11)          tmpannee++;            c1.set(tmpannee,periode2,01);      int maxday=c1.getActualMaximum(c1.DAY_OF_MONTH);      c1.set(tmpannee,periode2,maxday);      System.out.println("maxday "+maxday);      fin.setYear(c1.get(Calendar.YEAR));      fin.setMonth(c1.get(Calendar.MONTH)+1);      fin.setDay(c1.get(Calendar.DAY_OF_MONTH));  }  public static srcastra.astra.sys.classetransfert.utils.Date renvDateEcheance(srcastra.astra.sys.classetransfert.utils.Date date,int nbj){       SimpleTimeZone pdt =new SimpleTimeZone(3600000,"Europe/Paris");       Calendar c1=new GregorianCalendar(pdt);      // Calendar c1=Calendar.getInstance();//       c1.set(date.year,date.month-1,date.day);       c1.add(c1.DATE,-nbj);       srcastra.astra.sys.classetransfert.utils.Date date2=new srcastra.astra.sys.classetransfert.utils.Date();       date2.setYear(c1.get(Calendar.YEAR));       date2.setMonth(c1.get(Calendar.MONTH)+1);       date2.setDay(c1.get(Calendar.DAY_OF_MONTH));       return date2;  }      public static void testCalendar(){      Calendar c1=Calendar.getInstance();//          for(int i=0;i<12;i++){          c1.set(4,i,1);          System.out.println(i+"max day"+c1.getActualMaximum(Calendar.DAY_OF_MONTH));      }      srcastra.astra.sys.classetransfert.utils.Date date=new srcastra.astra.sys.classetransfert.utils.Date(2004,1, 25);      srcastra.astra.sys.classetransfert.utils.Date date2=new srcastra.astra.sys.classetransfert.utils.Date(2004,2,27);      int [] tab=renvDifferenceBetweenDate(date,date2);      System.out.println("nuit "+tab[0]+"  jour"+tab[1]);  }   public static boolean  verifyUptodateDate(srcastra.astra.sys.classetransfert.utils.Date date){       Calendar c1=Calendar.getInstance();//        System.out.println("aujourdhui "+c1.get(c1.DAY_OF_MONTH)+"  "+c1.get(c1.MONTH)+"  "+c1.get(c1.YEAR));       System.out.println("\nla date "+date.day+"  "+date.month+"  "+date.year);       long t1=c1.getTimeInMillis();       c1.set(date.year,date.month-1,date.day);       long t2=c1.getTimeInMillis();       if(t2>=t1)           return true;       else            return false;  } public static srcastra.astra.sys.classetransfert.utils.Date getTodayDate(){      Calendar c1=Calendar.getInstance();//       srcastra.astra.sys.classetransfert.utils.Date date=new srcastra.astra.sys.classetransfert.utils.Date(c1.get(c1.YEAR),c1.get(c1.MONTH)+1,c1.get(c1.DAY_OF_MONTH));      return date; }}