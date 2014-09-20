/*



 * SearchInDecision.java



 *



 * Created on 6 février 2003, 11:56



 */


package srcastra.astra.gui.modules.aidedesicion;


/**
 * @author Thomas
 */


public class SearchInDecision implements java.util.Comparator {


    /**
     * Creates a new instance of SearchInDecision
     */


    int m_sortCol;


    public SearchInDecision(int col) {


        this.m_sortCol = col;


    }


    public int compare(Object obj, Object obj1) {


        System.out.println("[COMPARE] column = " + m_sortCol);


        Object[] tmpObj1 = (Object[]) obj;


        Object[] tempObj2 = (Object[]) obj1;


        Object comp1 = tempObj2[0];


        int compare;


        String cp1 = null;


        String cp2 = null;


        int entier1;


        int entier2;


        int comparaison = 0;


        switch (m_sortCol)


        {


            case 1:


                entier1 = ((Integer) tmpObj1[0]).intValue();


                entier2 = ((Integer) comp1).intValue();


                if (entier1 < entier2) comparaison = -1;


                else if (entier1 > entier2) comparaison = 1;


                else if (entier1 == entier2) comparaison = 0;


                break;


            case 0:


                cp1 = tmpObj1[m_sortCol].toString();


                cp2 = comp1.toString();


                comparaison = (cp1.toUpperCase()).compareTo(cp2.toUpperCase());


                break;


        }

        //  System.out.println("[COMPARE] Objet comparé 1 = " + cp1 + " Objet comparé 2 = " + cp2 + " Nbre de comparaison retourné = " + comparaison);


        return comparaison;


    }


}



