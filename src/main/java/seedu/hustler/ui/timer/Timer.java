package seedu.hustler.ui.timer;

import seedu.hustler.ui.timer.statusTypes.threadStatus;
import seedu.hustler.Hustler;

/**
 * The timer device operated by timerManager.
 */
public class Timer implements Runnable {
    /**
     * The current status of the timer, an attribute that is
     * affected by the types of commands used by the user.
     */
    protected static threadStatus threadstatus = threadStatus.DEFAULT;

    /**
     * An array of 3 integers representing the hours, minutes
     * and seconds (indexes 2, 1, and 0 respectively of timeArray)
     * of the timer.
     */
    protected int[] timeArray = new int[3];

    /**
     * Default constructor that initialises the timer to 0hrs
     * 0minutes and 0seconds.
     */
    protected Timer() {
        timeArray = new int[3];
        threadstatus = threadStatus.DEFAULT;
    }
    /**
     * Overloaded constructor that directly sets the hours, minutes
     * and seconds of the timer according to the user's discretion.
     *
     * @param hours hours of the timer.
     * @param minutes minutes of the timer.
     * @param seconds seconds of the timer.
     */
    protected Timer(String hours, String minutes, String seconds) {
        timeArray[0] = Integer.parseInt(seconds);
        timeArray[1] = Integer.parseInt(minutes);
        timeArray[2] = Integer.parseInt(hours);
    }

    /**
     * Starts running the timer, counting down until
     * the time is up or if the timer is ended
     * prematurely by the user. Timer can be paused
     * and resumed.
     */
    public void run() {
        TimerUI.printThreadStart(threadstatus, timeArray);
        try {
            while (!isFinished()) {
                Thread.sleep(1000);
                timeArray = TimerLogic.decrement(timeArray);
            }
        } catch (Exception e) {
        }
        TimerUI.printThreadInterrupt(threadstatus);
        if (isFinished()) {
            Hustler.scheduler.confirm();
        }
    }

    /**
     * Returns the time currently stored by the timer.
     * This method is an accessor for other methods.
     * @return the entire array containing information
     *     about the timer's hours, minutes and seconds.
     */
    protected int[] getTime() {
        return timeArray;
    }

    /**
     * Returns a boolean value telling the timer if
     * the countdown is completed.
     * @return true if timer if completed, false otherwise.
     */
    private boolean isFinished() {
        return (timeArray[0] == 0 && timeArray[1] == 0 && timeArray[2] == 0);
    }
}
