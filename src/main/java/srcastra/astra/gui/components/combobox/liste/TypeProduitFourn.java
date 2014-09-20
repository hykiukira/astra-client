/*

 * TypeProduitFourn.java

 *

 * Created on 22 avril 2003, 16:13

 */


package srcastra.astra.gui.components.combobox.liste;

import srcastra.astra.sys.rmi.astrainterface;

import srcastra.astra.sys.classetransfert.Loginusers_T;

import java.util.*;

/**
 * @author Thomas
 */

public class TypeProduitFourn extends TypeProduitListeTableModel {


    /**
     * Creates a new instance of TypeProduitFourn
     */

    public TypeProduitFourn(astrainterface serveur, Loginusers_T login) {

        super(serveur, login);

        loadata();

    }


    public ArrayList loadata() {

        if (vector == null) {

            if (m_vector == null) {

                m_vector = new ArrayList();

                m_vector.add(new Object[]{new Integer(8), loadNames("as"), loadNames("as_full"), new Integer(1)});

                m_vector.add(new Object[]{new Integer(1), loadNames("av"), loadNames("av_full"), new Integer(2)});

                m_vector.add(new Object[]{new Integer(5), loadNames("ba"), loadNames("ba_full"), new Integer(3)});

                m_vector.add(new Object[]{new Integer(2), loadNames("bro"), loadNames("bro_full"), new Integer(4)});

                m_vector.add(new Object[]{new Integer(3), loadNames("ho"), loadNames("ho_full"), new Integer(5)});

                m_vector.add(new Object[]{new Integer(4), loadNames("tax"), loadNames("tax_full"), new Integer(6)});

                m_vector.add(new Object[]{new Integer(7), loadNames("tr"), loadNames("tr_full"), new Integer(7)});

                m_vector.add(new Object[]{new Integer(6), loadNames("vo"), loadNames("vo_full"), new Integer(8)});

            }

            vector = m_vector;

            vector.add(new Object[]{new Integer(9), loadNames("au"), loadNames("au_full"), new Integer(9)});

            vector.add(new Object[]{new Integer(10), loadNames("nav"), loadNames("nav_full"), new Integer(10)});

            vector.add(new Object[]{new Integer(11), loadNames("loc"), loadNames("loc_full"), new Integer(11)});

            vector.add(new Object[]{new Integer(12), loadNames("org"), loadNames("org_full"), new Integer(12)});

            vector.add(new Object[]{new Integer(13), loadNames("vis"), loadNames("vis_full"), new Integer(13)});

            Collections.sort(m_vector, new srcastra.astra.gui.sys.comparator.ListeSelectorComparaTor2(1, true));


        }

        return vector;

    }

    ArrayList vector;

}