package srcastra;

import srcastra.astra.sys.classetransfert.Loginusers_T;

/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 10/10/11
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */
public class FakeUser extends Loginusers_T{
    public java.util.Locale getLangage()    {


        java.util.Locale tmplocale=new java.util.Locale("fr","");


        return tmplocale;


    }

}
