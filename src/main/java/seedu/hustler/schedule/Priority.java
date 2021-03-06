package seedu.hustler.schedule;

import java.time.Duration;
import seedu.hustler.task.variables.Difficulty;
import seedu.hustler.schedule.time.TimeRemaining;
import seedu.hustler.task.Task;
import seedu.hustler.schedule.time.Timing;
import seedu.hustler.task.variables.Difficulty;

/**
 * A class that assigns a priority score to
 * given task.
 */
public class Priority {

    /**
     * The priority score of a task.
     */
    private double priorityScore;
    
    /**
     * The object that calculates time left for a
     * task.
     */
    private Timing time;
    
    /**
     * Initializes time.
     *
     * @param time the supplied object to initialize with
     */
    public Priority(Timing time) {
        this.time = time;
    }
    
    /**
     * Updates priorityScore and returns it.
     *
     * @param task the task for which priority is computed
     * @param timeSpent time spent on the task
     * @return priority score of the task
     */
    public double getPriorityScore(Task task, long timeSpent) {
        this.updatePriortyScore(task, timeSpent);
        return this.priorityScore;
    }
    
    /**
     * Takes in a task and computes a priority score
     * for the task based on its difficulty, amount of
     * hours available and time spent.
     *
     * @param task the task for which priority is computed
     * @param timeSpent time spent on the task
     */
    public void updatePriortyScore(Task task, long timeSpent) {
        if (task.getDifficulty().toString().equals("[HIGH]")) {
            this.priorityScore = 3;
        } else if (task.getDifficulty().toString().equals("[MED]")) {
            this.priorityScore = 2;
        } else {
            this.priorityScore = 1;
        }

        Duration timeRemaining = time.secondsLeft(task);
        long secondsRemaining = timeRemaining.getSeconds();

        long timeForScoring = secondsRemaining + timeSpent; 

        double daysForScoring = timeForScoring / 86400.0;
        this.priorityScore /= daysForScoring;
    }
}
