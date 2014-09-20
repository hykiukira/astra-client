/*
* Signaletique.java
 *
 * Created on 6 mai 2002, 18:58
 */

package srcastra.astra.gui.modules.signalitique;
/**
 * @author S�bastien
 * @version
 */
public interface Signaletique {

    
    /** Permet d'updater les champs graphique d'un panneau
     * @param intitule tableau de String comprenant les intitul�s
     * � remplacer dans le panneau
     * @param langueCleUnik cle unique de la langue dans laquelle
     * les intitul�s sont affich�s
     */

//fonctions    
    void updateAllFields(int langueCleUnik);
    void setFocusToCombo(boolean x);
    //void changeLangue(int langue);
    int setMode(int mode);

//constantes  
    public static final int INSERT_MODE=1;
    public static final int INSERT_DISPLAY_MODE=2;
    public static final int INSERT_MODIFY_MODE=3;
}

