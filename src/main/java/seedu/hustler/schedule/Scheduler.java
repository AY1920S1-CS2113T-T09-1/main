package seedu.hustler.schedule;

import seedu.hustler.Hustler;
import java.util.ArrayList;
import seedu.hustler.task.Task;
import seedu.hustler.ui.Ui;
import seedu.hustler.logic.CommandLineException;

/**
 * A class that holds all the
 * tasks to complete, time spent on each task,
 * and priority of each task, which is together called
 * an entry. Scheduler handles all these entries.
 */
public class Scheduler {
    private Ui ui = new Ui();

    /**
     * An ArrayList that stores entries
     * that are tasks to complete with the amount
     * of time spent on each task and their computed
     * priority.
     */
    public ArrayList<ScheduleEntry> schedule 
        = new ArrayList<>();

    /**
     * An object that uses top-k algorithm
     * based on priority to initialize recommended list.
     */
    private Recommender recommender;

    /**
     * An ArrayList of entries extracted based on priorty
     * from schedule.
     */
    public ArrayList<ScheduleEntry> recommended 
        = new ArrayList<>();

    /**
     * Add an entry to the schedule based on the task
     * supplied as input, only if the task is not done.
     *
     * @param task task to be added as entry
     */
    public void add(Task task) {
        if (task.isCompleted()) {
            return;
        }
        schedule.add(new ScheduleEntry(task, 0));
    }

    public static Scheduler getInstance() {
        return (new Scheduler()); 
    }
    
    /**
     * Adds task as an entry and also configuring a preset
     * amount of time spent on the task.
     *
     * @param task task to be added as entry
     * @param timeSpent amount of time spent on each task
     */
    public void add(Task task, long timeSpent) {
        if (task.isCompleted()) {
            return;
        }
        schedule.add(new ScheduleEntry(task, timeSpent));
    }
    
    /**
     * Returns an entry based on supplied index.
     *
     * @param index index of the wanted entry
     * @return entry that contains the task, time spent and priority of the task
     */
    public ScheduleEntry getEntry(int index) {
        return schedule.get(index);
    }
    
    /**
     * Adds seconds to the time spent on a task in the schedule.
     *
     * @param index index of the entry to update
     * @param seconds seconds to be added to the time spent on the task
     */
    public void updateEntry(int index, long seconds) {
        schedule.get(index).updateTimeSpent(seconds);
    }
    
    /**
     * Returns the list of entries.
     *
     * @return list of entries
     */
    public ArrayList<ScheduleEntry> getList() {
        return schedule;
    }
    
    /**
     * Updates the schedule with a latest task from
     * the original TaskList.
     */
    public void update() {
        add(Hustler.list.getLastTask());
    }

    /**
     * Removes an entry based on the task supplied.
     *
     * @param task task whose matching entry needs to be removed
     */
    public void remove(Task task) {
        schedule.removeIf(n -> (n.getTask() == task));
        recommended.removeIf(n -> (n.getTask() == task));
    }

    /**
     * Returns the size of the current list of entries.
     *
     * @return size of the list of entries
     */
    public int size() {
        return schedule.size();
    }

    /**
     * Displays the whole schedule which includes incomplete tasks,
     * and time spent on each of the tasks.
     */
    public void displayEntries() {
        String output = "These are the amount of hours you have spent on all your tasks:\n\t";
        for (ScheduleEntry entry : schedule) {
            output += entry.getTask().toString() 
                + " hours spent: " + (entry.getTimeSpent() / 3600.0) + "\n\t";
        }
        if (size() == 0) {
            output = "Tasks completed. Please add more.";
        }
        ui.showMessage(output);
    }

    /**
     * Returns the time spent on each task.
     *
     * @param task the task for which time spent is required
     * @return amount of time spent on the task
     */
    public long getTimeSpent(Task task) {
        if (task.isCompleted()) {
            return -1; 
        }
        for (ScheduleEntry entry : schedule) {
            if (entry.getTask() == task) {
                return entry.getTimeSpent();
            }
        }
        return -1;
    }

    public void recommend(int seconds) {
        recommender = new Recommender(schedule);
        recommended = recommender.recommend(seconds);
    }

    public void displayRecommendedSchedule() {
        if (recommended.size() == 0) {
            ui.showMessage("There are no tasks to complete. "
                    + "Please add more tasks."); 
            return;
        }

        String output = "This is your recommended schedule for the next "
            + "few hours. Change it to your liking:\n\t";
        for (int i = 0; i < recommended.size(); i++) {
            long hours = recommended.get(i).getTimeAlloc() / 3600;
            long minutes = (recommended.get(i).getTimeAlloc() / 60) % 60;
            long seconds = recommended.get(i).getTimeAlloc() % 60;
            output += (i + 1) + ". " + recommended.get(i).getTask().toString() 
                + " time alloted: " + hours + ":" + minutes + ":" + seconds + "\n\t";
        }
        ui.showMessage(output);
    }

    public void addToRecommended(Task task) throws CommandLineException {
        if (task.isCompleted()) {
            throw new CommandLineException("Task has already been completed");
        }
        if (recommended.stream().anyMatch(n -> (n.getTask() == task))) {
            throw new CommandLineException("Task is already present");
        }
        schedule.stream().parallel()
            .filter(n -> (n.getTask() == task))
            .findAny()
            .ifPresent(recommended::add);
    }    

    public void confirm() {
        for (ScheduleEntry entry : recommended) {
            entry.updateTimeSpent(entry.getTimeAlloc());
        }
        recommended.clear();
        displayEntries();
    }

    public void removeFromRecommended(int index) {
        recommended.remove(index); 
    }

    /**
     * Updates the allocated time to a particular entry in the recommended
     * list.
     *
     * @param index index of entry in recommended
     * @param timeInSeconds time allocated in seconds
     */
    public void updateAllocTime(int index, long timeInSeconds) {
        recommended.get(index).setTimeAlloc(timeInSeconds);
    }
}
