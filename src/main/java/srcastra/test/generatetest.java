/*
* generatetest.java
*
* Created on 2 septembre 2003, 12:24
*/
package srcastra.test;

/**
 * @author thomas
 */
public class generatetest {

    /**
     * Creates a new instance of generatetest
     */
    GenereHistorique historique;

    public generatetest() {
        historique = new GenereHistorique();
//historique.generaterecord(20000);
        historique.countTable();
//historique.sumValue();
        System.out.println("operation terminée");
    }

    public static void main(String[] args) {
        new generatetest();
    }
}
