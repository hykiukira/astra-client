/*
 * AString.java
 *
 * Created on 16 juillet 2002, 16:13
 */

package srcastra.astra.gui.sys;

/**
 Classe sp�cifique aux objets qui seront encod� dans la JList.
* Elle permet de stocker non seulement le label de l'objet qui sera affich� dans la JList, mais
* aussi un num�ro de cl�.
*/    
public class AString {
        
        private int cleunik;
        private String string;
        
        /** Cr�e une nouvelle instance de AString
         * @param cleunik cl� relative � l'objet
         * @param string label de l'objet qui sera affich� dans la JList
         */        
        public AString (int cleunik, String string) {
            this.string = string;
            this.cleunik = cleunik;
        }
        
        /** Appel�e automatiquement par la JList pour d�terminer le label
         * @return le label de l'objet
         */        
        public String toString() {
            return string;
        }
        
        /** Retourne la cl� de l'objet
         * @return cl� de l'objet
         */        
        public int getCleunik() {
            return cleunik;
        }       
    }