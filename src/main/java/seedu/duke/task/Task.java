package seedu.duke.task;

import java.time.LocalDateTime;

/**
 * An abstract class that acts as a template for ToDo, Deadline and Event.
 */
public abstract class Task {
    /**
     * String that contains the description of the task.
     */
    protected String description;

    /**
     * Boolean which states whether the task is done.
     */
    protected boolean isDone;

    /**
     * Boolean which states whether the task is recurring.
     */
    protected boolean isRecurring;

    /**
     * Integer to store how often the task is recurring in minutes.
     */
    protected int recurringMin;

    /**
     * String to store the description of how often the task is recurring.
     */
    protected String recurringString;

    /**
     * Initializes description and isDone as false.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isRecurring = false;
        this.recurringMin = 0;
        this.recurringString = "";
    }

    /**
     * Returns the status of the task.
     *
     * @return a symbol specifying whether a task has been completed or not.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks a class as complete.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as recurring.
     */
    public void markAsRecurring() {
        this.isRecurring = true;
    }

    /**
     * Sets how often the task is recurring in minutes.
     *
     * @param min minutes of how often the task is recurring.
     */
    public void setRecurringMin(int min) {
        this.recurringMin = min;
    }

    /**
     * Sets the description of how often the task is recurring as a string.
     *
     * @param description string of the description of how often the task will be recurred.
     */
    public void setRecurringString(String description) {
        this.recurringString = description;
    }

    /**
     * Returns the description of the task.
     *
     * @return string description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the description of the recurring task.
     *
     * @return string description.
     */
    public String getRecurringString() {
        return this.recurringString;
    }
    /**
     * Returns a string that displays all information
     * about the task in a user readable format.
     *
     * @return the status and description of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public String recurringString() {
        return (isRecurring ? " (Every " + getRecurringString() + ")" : "");
    }
    /**
     * Returns a disc savable csv format of the task info.
     *
     * @return a pipe separated string of the status and description.
     */
    public String toSaveFormat() {
        int isDone = this.isDone ? 1 : 0;
        return isDone + "|" + this.description;
    }

    /**
     * Returns a disc savable csv format of the task recurring info.
     *
     * @return a pipe separated string of the status and description.
     */
    public String saveRecurringString() {
        return "|" + (this.isRecurring ? 1 : 0) + "|" + this.recurringString + "|" + this.recurringMin;
    }

    /**
     * Checks whether an input task is equal to current object.
     *
     * @param temp input task
     * @return true or false to comparison.
     */
    public boolean equals(Task temp) {
        if (this.description == temp.description) {
            return true;
        }
        return false;
    }

    /**
     * Declares a method to obtain date and time of the task in sub-classes.
     */
    public abstract LocalDateTime getDateTime();

    /**
     * Declares a method to set date and time of the task in sub-classes.
     *
     * @param dateTime the date and time of the task of type LocalDateTime.
     */
    public abstract void setDateTime(LocalDateTime dateTime);

    /**
     * Declares a method to set date and time of the task in sub-classes.
     *
     * @param dateTime string of the date and time of the task.
     */
    public abstract void setDateTime(String dateTime);
}
