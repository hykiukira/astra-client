/*

 * LoadTvaFrame.java

 *

 * Created on 7 mai 2003, 8:58

 */


package srcastra.astra.gui.components.tva;

import java.io.*;

import java.net.*;

import srcastra.astra.sys.*;

import srcastra.astra.sys.rmi.*;

import srcastra.astra.sys.classetransfert.*;

import java.rmi.*;

import java.util.*;

import srcastra.astra.gui.modules.*;

/**
 * @author Thomas
 */

public class LoadTvaFrame {


    /**
     * Creates a new instance of LoadTvaFrame
     */

    private File directory;

    MainScreenModule parent;

    boolean loaded;

    public LoadTvaFrame(int type, MainScreenModule parent) {

        directory = getDefaultAstraDir();

        this.parent = parent;

        loaded = true;


    }

    public void loadTva(astrainterface serveur, Loginusers_T user) {

        ArrayList tmp = new ArrayList();

        tmp.add(tryReadObject(mainEfr, "astra/tree", this.AEFR));

        tmp.add(tryReadObject(mainNfr, "astra/tree", this.ANFR));

        tmp.add(tryReadObject(mainIfr, "astra/tree", this.AIFR));

        tmp.add(tryReadObject(mainVEfr, "astra/tree", this.VEFR));

        tmp.add(tryReadObject(mainVNfr, "astra/tree", this.VNFR));

        tmp.add(tryReadObject(mainVIfr, "astra/tree", this.VIFR));

        tmp.add(tryReadObject(mainEnl, "astra/tree", this.AENL));

        tmp.add(tryReadObject(mainNnl, "astra/tree", this.ANNL));

        tmp.add(tryReadObject(mainInl, "astra/tree", this.AINL));

        tmp.add(tryReadObject(mainVEnl, "astra/tree", this.VENL));

        tmp.add(tryReadObject(mainVNnl, "astra/tree", this.VNNL));

        tmp.add(tryReadObject(mainVInl, "astra/tree", this.VINL));

        for (int i = 0; i < tmp.size(); i++)

        {

            if (tmp.get(i) == null)

                loaded = false;

        }

        if (loaded)

            return;

        try {

            LoadTree loadtree = new LoadTree();

            ArrayList tvaliste = serveur.getTvaListe(parent.getCurrentUser().getUrcleunik(), 1);

            parent.getCurrentUser().setUrlmcleunik(1);

            parent.getCurrentUser().setUrlmabrev("fr");

            mainNfr = new TvaFactory(parent).getTreeAchatNat();

            mainIfr = new TvaFactory(parent).getTreeAchatIntra();

            mainEfr = new TvaFactory(parent).getTreeAchatExtra();

            mainVNfr = new TvaFactory(parent).getTreeVenteNat();

            mainVIfr = new TvaFactory(parent).getTreeVenteInt();

            mainVEfr = new TvaFactory(parent).getTreeVenteExt();

            loadtree.loadTree(mainNfr, tvaliste);

            loadtree.loadTree(mainIfr, tvaliste);

            loadtree.loadTree(mainEfr, tvaliste);

            loadtree.loadTree(mainVNfr, tvaliste);

            loadtree.loadTree(mainVIfr, tvaliste);

            loadtree.loadTree(mainVEfr, tvaliste);

            tryWriteObject(mainEfr, "astra/tree", this.AEFR);

            tryWriteObject(mainNfr, "astra/tree", this.ANFR);

            tryWriteObject(mainIfr, "astra/tree", this.AIFR);

            tryWriteObject(mainVEfr, "astra/tree", this.VEFR);

            tryWriteObject(mainVNfr, "astra/tree", this.VNFR);

            tryWriteObject(mainVIfr, "astra/tree", this.VIFR);

            tvaliste = serveur.getTvaListe(parent.getCurrentUser().getUrcleunik(), 2);

            parent.getCurrentUser().setUrlmcleunik(2);

            parent.getCurrentUser().setUrlmabrev("nl");

            mainNnl = new TvaFactory(parent).getTreeAchatNat();

            mainInl = new TvaFactory(parent).getTreeAchatIntra();

            mainEnl = new TvaFactory(parent).getTreeAchatExtra();

            mainVNnl = new TvaFactory(parent).getTreeVenteNat();

            mainVInl = new TvaFactory(parent).getTreeVenteInt();

            mainVEnl = new TvaFactory(parent).getTreeVenteExt();

            loadtree.loadTree(mainNnl, tvaliste);

            loadtree.loadTree(mainInl, tvaliste);

            loadtree.loadTree(mainEnl, tvaliste);

            loadtree.loadTree(mainVNnl, tvaliste);

            loadtree.loadTree(mainVInl, tvaliste);

            loadtree.loadTree(mainVEnl, tvaliste);

            tryWriteObject(mainEnl, "astra/tree", this.AENL);

            tryWriteObject(mainNnl, "astra/tree", this.ANNL);

            tryWriteObject(mainInl, "astra/tree", this.AINL);

            tryWriteObject(mainVEnl, "astra/tree", this.VENL);

            tryWriteObject(mainVNnl, "astra/tree", this.VNNL);

            tryWriteObject(mainVInl, "astra/tree", this.VINL);

        } catch (srcastra.astra.sys.rmi.Exception.ServeurSqlFailure se) {


        } catch (java.rmi.RemoteException re) {


        }

    }

    public Object tryReadObject(Object defaultObject, String directoryName, String fileName)

    {

        File dir = new File(directory, directoryName);

        if (!dir.exists())

        {

            return defaultObject;

        }

        File fichier = new File(dir, fileName);

        if (!fichier.exists())

            return defaultObject;

        if (!fichier.isFile())

            return defaultObject;

        Logger.getDefaultLogger().setMinLogLevel(2);

        Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "Reading file");

        try

        {

            ObjectInputStream fstream = new ObjectInputStream(new FileInputStream(fichier));

            return fstream.readObject();

        }

        catch (Exception e)

        {

            e.printStackTrace();

            return defaultObject;

        }

    }

    public void tryWriteObject(Object o, String directoryName, String fileName)

    {

        File dir = new File(directory, directoryName);

        if (!dir.exists())

        {

            forceDirectory(directoryName);

            dir = new File(directory, directoryName);

            if (!dir.exists())

                return;

        }

        File fichier = new File(dir, fileName);

        if (!fichier.exists())

            try

            {

                fichier.createNewFile();

            } catch (Exception e) {
                e.printStackTrace();
            }

        if (!fichier.isFile())

            return;

        Logger.getDefaultLogger().setMinLogLevel(2);

        Logger.getDefaultLogger().log(Logger.LOG_DEBUG, "Writing file");

        try

        {

            ObjectOutputStream fstream = new ObjectOutputStream(new FileOutputStream(fichier));

            fstream.writeObject(o);

            fstream.close();

        }

        catch (Exception e)

        {

            e.printStackTrace();

        }

    }

    protected void forceDirectory(String name) {

        File tmp = new File(directory, name);

        if ((tmp.exists()) && (!tmp.isDirectory()))

        {

            tmp.delete();

            tmp = new File(directory, name);

        }

        if (!tmp.exists())

            try {

                javax.swing.filechooser.FileSystemView.getFileSystemView().createNewFolder(tmp);
            }

            catch (Exception e) {
                e.printStackTrace();
            }

    }

    private static File getDefaultAstraDir()

    {

        System.out.println(javax.swing.filechooser.FileSystemView.getFileSystemView().getDefaultDirectory());

        return javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory();

    }


    /**
     * Getter for property mainEfr.
     *
     * @return Value of property mainEfr.
     */

    public srcastra.astra.gui.components.tva.TvaComponent getMainEfr() {

        return mainEfr;

    }


    /**
     * Setter for property mainEfr.
     *
     * @param mainEfr New value of property mainEfr.
     */

    public void setMainEfr(srcastra.astra.gui.components.tva.TvaComponent mainEfr) {

        this.mainEfr = mainEfr;

    }


    /**
     * Getter for property mainEnl.
     *
     * @return Value of property mainEnl.
     */

    public srcastra.astra.gui.components.tva.TvaComponent getMainEnl() {

        return mainEnl;

    }


    /**
     * Setter for property mainEnl.
     *
     * @param mainEnl New value of property mainEnl.
     */

    public void setMainEnl(srcastra.astra.gui.components.tva.TvaComponent mainEnl) {

        this.mainEnl = mainEnl;

    }


    /**
     * Getter for property mainIfr.
     *
     * @return Value of property mainIfr.
     */

    public srcastra.astra.gui.components.tva.TvaComponent getMainIfr() {

        return mainIfr;

    }


    /**
     * Setter for property mainIfr.
     *
     * @param mainIfr New value of property mainIfr.
     */

    public void setMainIfr(srcastra.astra.gui.components.tva.TvaComponent mainIfr) {

        this.mainIfr = mainIfr;

    }


    /**
     * Getter for property mainInl.
     *
     * @return Value of property mainInl.
     */

    public srcastra.astra.gui.components.tva.TvaComponent getMainInl() {

        return mainInl;

    }


    /**
     * Setter for property mainInl.
     *
     * @param mainInl New value of property mainInl.
     */

    public void setMainInl(srcastra.astra.gui.components.tva.TvaComponent mainInl) {

        this.mainInl = mainInl;

    }


    /**
     * Getter for property mainNfr.
     *
     * @return Value of property mainNfr.
     */

    public srcastra.astra.gui.components.tva.TvaComponent getMainNfr() {

        return mainNfr;

    }


    /**
     * Setter for property mainNfr.
     *
     * @param mainNfr New value of property mainNfr.
     */

    public void setMainNfr(srcastra.astra.gui.components.tva.TvaComponent mainNfr) {

        this.mainNfr = mainNfr;

    }


    /**
     * Getter for property mainNnl.
     *
     * @return Value of property mainNnl.
     */

    public srcastra.astra.gui.components.tva.TvaComponent getMainNnl() {

        return mainNnl;

    }


    /**
     * Setter for property mainNnl.
     *
     * @param mainNnl New value of property mainNnl.
     */

    public void setMainNnl(srcastra.astra.gui.components.tva.TvaComponent mainNnl) {

        this.mainNnl = mainNnl;

    }


    /**
     * Getter for property mainVEfr.
     *
     * @return Value of property mainVEfr.
     */

    public srcastra.astra.gui.components.tva.TvaComponent getMainVEfr() {

        return mainVEfr;

    }


    /**
     * Setter for property mainVEfr.
     *
     * @param mainVEfr New value of property mainVEfr.
     */

    public void setMainVEfr(srcastra.astra.gui.components.tva.TvaComponent mainVEfr) {

        this.mainVEfr = mainVEfr;

    }


    /**
     * Getter for property mainVEnl.
     *
     * @return Value of property mainVEnl.
     */

    public srcastra.astra.gui.components.tva.TvaComponent getMainVEnl() {

        return mainVEnl;

    }


    /**
     * Setter for property mainVEnl.
     *
     * @param mainVEnl New value of property mainVEnl.
     */

    public void setMainVEnl(srcastra.astra.gui.components.tva.TvaComponent mainVEnl) {

        this.mainVEnl = mainVEnl;

    }


    /**
     * Getter for property mainVIfr.
     *
     * @return Value of property mainVIfr.
     */

    public srcastra.astra.gui.components.tva.TvaComponent getMainVIfr() {

        return mainVIfr;

    }


    /**
     * Setter for property mainVIfr.
     *
     * @param mainVIfr New value of property mainVIfr.
     */

    public void setMainVIfr(srcastra.astra.gui.components.tva.TvaComponent mainVIfr) {

        this.mainVIfr = mainVIfr;

    }


    /**
     * Getter for property mainVInl.
     *
     * @return Value of property mainVInl.
     */

    public srcastra.astra.gui.components.tva.TvaComponent getMainVInl() {

        return mainVInl;

    }


    /**
     * Setter for property mainVInl.
     *
     * @param mainVInl New value of property mainVInl.
     */

    public void setMainVInl(srcastra.astra.gui.components.tva.TvaComponent mainVInl) {

        this.mainVInl = mainVInl;

    }


    /**
     * Getter for property mainVNfr.
     *
     * @return Value of property mainVNfr.
     */

    public srcastra.astra.gui.components.tva.TvaComponent getMainVNfr() {

        return mainVNfr;

    }


    /**
     * Setter for property mainVNfr.
     *
     * @param mainVNfr New value of property mainVNfr.
     */

    public void setMainVNfr(srcastra.astra.gui.components.tva.TvaComponent mainVNfr) {

        this.mainVNfr = mainVNfr;

    }


    /**
     * Getter for property mainVNnl.
     *
     * @return Value of property mainVNnl.
     */

    public srcastra.astra.gui.components.tva.TvaComponent getMainVNnl() {

        return mainVNnl;

    }


    /**
     * Setter for property mainVNnl.
     *
     * @param mainVNnl New value of property mainVNnl.
     */

    public void setMainVNnl(srcastra.astra.gui.components.tva.TvaComponent mainVNnl) {

        this.mainVNnl = mainVNnl;

    }


    /**
     * Getter for property mainE.
     *
     * @return Value of property mainE.
     */


    TvaComponent mainEfr;

    TvaComponent mainNfr;

    TvaComponent mainIfr;

    TvaComponent mainEnl;

    TvaComponent mainNnl;

    TvaComponent mainInl;

    TvaComponent mainVEfr;

    TvaComponent mainVNfr;

    TvaComponent mainVIfr;

    TvaComponent mainVEnl;

    TvaComponent mainVNnl;

    TvaComponent mainVInl;

    public static final String AEFR = "aefr";

    public static final String AIFR = "aifr";

    public static final String ANFR = "anfr";

    public static final String AENL = "aenl";

    public static final String AINL = "ainl";

    public static final String ANNL = "annl";

    public static final String VEFR = "vefr";

    public static final String VIFR = "vifr";

    public static final String VNFR = "vnfr";

    public static final String VENL = "venl";

    public static final String VINL = "vinl";

    public static final String VNNL = "vnnl";


}

