/* * ColumnData.java * * Created on 13 octobre 2002, 12:39 */package srcastra.astra.gui.test;/** * * @author  Administrateur */public class ColumnData implements java.io.Serializable{        /** Creates a new instance of ColumnData */    public ColumnData( String c_title,        int c_width,        int c_alignement){            this.c_title=c_title;            this.c_width=c_width;            this.c_alignement=c_alignement;            }    public String c_title;    public int c_width;    public int c_alignement;}