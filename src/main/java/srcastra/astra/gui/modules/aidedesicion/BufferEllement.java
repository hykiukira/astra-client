/*

 * getBufferEllement.java

 *

 * Created on 4 avril 2003, 14:57

 */


package srcastra.astra.gui.modules.aidedesicion;

import srcastra.astra.gui.modules.aidedesicion.*;

import java.util.*;

/**
 * @author Thomas
 */

public class BufferEllement {


    /**
     * Creates a new instance of getBufferEllement
     */

    public BufferEllement() {

    }

    public static Object getBufferElement(AbstractBuffer descTree, int type, int id, int lmcleunik) {

        DecBuffer buff = new DecBuffer();

        buff = (DecBuffer) descTree.getData3(type, id, buff);

        if (buff != null) {

            ArrayList data = buff.getData();

            if (data != null) {

                for (int i = 0; i < data.size(); i++) {

                    Object[] tmp = (Object[]) data.get(i);

                    if (lmcleunik == ((Integer) tmp[2]).intValue())

                        return tmp[3];

                }

            }

        }

        return null;

    }

    public static ArrayList getBufferArray(AbstractBuffer descTree, int type, int id, int lmcleunik) {

        DecBuffer buff = new DecBuffer();

        buff = (DecBuffer) descTree.getData3(type, id, buff);

        if (buff != null) {

            ArrayList data = buff.getData();

            return data;

        }

        return null;

    }

}

