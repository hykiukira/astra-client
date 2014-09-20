/*
 * CheckInterface.java
 *
 * Created on 2 août 2002, 11:03
 */

package srcastra.astra.sys.rmi.utils;

/**
 *
 * @author  thomas
 */
public interface CheckInterface {
     public static final int CHECK_FOR_CLIENT=1;
     public static final int CHECK_FOR_FOURNISSEUR=2;
     public static final int CHECK_FOR_FOURN_AND_CLIENT=3;   
     public static final int CHECK_WRONG=4; 
     public static final int CHECK_WRONG_BOTH_CLI_FOURN=5; 
     public static final int CHECK_FOR_NOTHING=5; 
     public static final int CHECK_FOR_TRANS_REMISE=6; 
     public static final int CHECK_FOR_LOGEMENT_REMISE=7; 
     
}
