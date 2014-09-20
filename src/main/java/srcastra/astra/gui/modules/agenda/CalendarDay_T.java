/*
 * CalendarDay_T.java
 *
 * Created on 23 mars 2004, 11:20
 */

package srcastra.astra.gui.modules.agenda;

/**
 * @author Administrateur
 */
public class CalendarDay_T implements java.io.Serializable {

    /**
     * Creates a new instance of CalendarDay_T
     */
    public CalendarDay_T() {
    }

    /**
     * Getter for property day.
     *
     * @return Value of property day.
     */
    public int getDay() {
        return day;
    }

    /**
     * Setter for property day.
     *
     * @param day New value of property day.
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Getter for property task.
     *
     * @return Value of property task.
     */
    public boolean isTask() {
        return task;
    }

    /**
     * Setter for property task.
     *
     * @param task New value of property task.
     */
    public void setTask(boolean task) {
        this.task = task;
    }

    int day;
    boolean task;

}
