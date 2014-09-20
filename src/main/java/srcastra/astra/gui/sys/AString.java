/*
 * AString.java
 *
 * Created on 16 juillet 2002, 16:13
 */

package srcastra.astra.gui.sys;

/**
 Classe spécifique aux objets qui seront encodé dans la JList.
* Elle permet de stocker non seulement le label de l'objet qui sera affiché dans la JList, mais
* aussi un numéro de clé.
*/    
public class AString {
        
        private int cleunik;
        private String string;
        
        /** Crée une nouvelle instance de AString
         * @param cleunik clé relative à l'objet
         * @param string label de l'objet qui sera affiché dans la JList
         */        
        public AString (int cleunik, String string) {
            this.string = string;
            this.cleunik = cleunik;
        }
        
        /** Appelée automatiquement par la JList pour déterminer le label
         * @return le label de l'objet
         */        
        public String toString() {
            return string;
        }
        
        /** Retourne la clé de l'objet
         * @return clé de l'objet
         */        
        public int getCleunik() {
            return cleunik;
        }       
    }