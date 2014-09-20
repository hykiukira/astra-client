/*
 * ReformatCode.java
 *
 * Created on 26 octobre 2004, 20:43
 */

package srcastra.astra.sys.utils;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Administrateur
 */
public class ReformatCode {

    /**
     * Creates a new instance of ReformatCode
     */
    JFileChooser fileChooser;
    FilenameFilter filter = new FilenameFilter() {
        public boolean accept(File dir, String name) {
            return name.endsWith(".java");
        }
    };

    public ReformatCode() {
        File file = init("/home/thom/IdeaProjects/Astra");
        if (file.isDirectory()) {
            System.out.println("This is a directory");
            File[] children = file.listFiles(filter);
            for (int i = 0; i < children.length; i++) {
                File child = children[i];
                System.out.println(child.getAbsolutePath());
            }
        } else {
            backupFile(file);
        }
    }

    private List copyFile(File file, File backup) {
        BufferedWriter bw = null;
        BufferedReader br = null;
        try {
            bw = new BufferedWriter(new FileWriter(backup));
            br = new BufferedReader(new FileReader(file));
            String str = null;
            List list = new ArrayList();
            while ((str = br.readLine()) != null) {
                //  str=str.trim();
                if (!str.equals("")) {
                    System.out.println("*" + str + "*");
                    str = str + "\n";
                    list.add(str);
                    if (bw != null) {
                        bw.write(str);
                    }

                }

            }
            return list;
        } catch (IOException in) {
            in.printStackTrace();
        } finally {
            try {
                br.close();
                bw.close();
            } catch (IOException in) {
                in.printStackTrace();
            }
        }
        return null;
    }

    private void overide(File file, List list) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            if (list != null) {
                for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                    Object o = iterator.next();
                    if (o != null) {
                        String line = o.toString().trim();
                        line = line + "\n";
                        bw.write(line);

                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }

    }

    private void backupFile(File file) {
        if (file != null) {
            String path = file.getParentFile().getAbsolutePath();
            String name = file.getName();
            System.out.println("path :" + path);
            File newFile = new File(path + File.separator + name + "backup");
            System.out.println("Backup File " + newFile.getAbsolutePath());
            List list = copyFile(file, newFile);
            overide(file, list);
        }

    }

    private File init(String path) {
        fileChooser = new JFileChooser(path);
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        if (file == null || !file.exists()) {
            System.exit(0);
        }
        return file;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ReformatCode();
        // TODO code application logic here
    }

}
