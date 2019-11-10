package seedu.hustler.task;

import java.time.LocalDateTime;
import seedu.hustler.Hustler;
import seedu.hustler.task.variables.Difficulty;
import seedu.hustler.task.variables.Tag;

/**
 * A class that inherits from Event. This task type is
 * RecurringEvent which is a recurrent Event.
 */
public class RecurringEvent extends Event {
    /**
     * String description that stats the frequency of recurrence.
     */
    protected String frequency;

    /**
     * Integer to store the period in minutes of each cycle of the RecurringEvent.
     */
    protected int period;

    /**
     * Boolean to keep track of whether next cycle of the RecurringEvent has been added.
     */
    boolean hasRecurred;

    /**
     * Initializes RecurringEvent's attributes.
     */
    public RecurringEvent(String description, LocalDateTime at, String difficulty, String tag,
                             LocalDateTime now, String frequency, int period, boolean hasRecurred) {
        super(description, at, difficulty, tag, now);
        this.frequency = frequency;
        this.period = period;
        this.hasRecurred = hasRecurred;
    }

    /**
     * Initializes RecurringEvent's attributes.
     */
    public RecurringEvent(String description, LocalDateTime at, Difficulty difficulty, Tag tag,
                             LocalDateTime now, String frequency, int period) {
        super(description, at, difficulty, tag, now);
        this.frequency = frequency;
        this.period = period;
        this.hasRecurred = false;
    }

    @Override
    public String toString() {
        return super.toString() + " (Repeats every " + frequency + ")";
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + "|" + frequency + "|" + period + "|" + hasRecurred;
    }

    @Override
    public LocalDateTime getDateTime() {
        if (LocalDateTime.now().isAfter(this.at) && !hasRecurred) {
            addNextRecurrence(Hustler.list);
        }
        return this.at;
    }

    @Override
    public void markAsDone() {
        this.isDone = true;
        if (!hasRecurred) {
            addNextRecurrence(Hustler.list);
        }
    }

    /**
     * Adds the next recurrence of the RecurringEvent
     * to the task list based on its period.
     */
    public void addNextRecurrence(TaskList list) {
        LocalDateTime nextAt = this.at.plusMinutes(period);
        System.out.println("\tNext recurrence of this Event has been added!");
        list.add(new RecurringEvent(description, nextAt, difficulty, tag,
                LocalDateTime.now(), frequency, period));
        this.hasRecurred = true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecurringEvent // instanceof handles nulls
                && description.equals(((RecurringEvent) other).description) // check various attributes
                && getDateTime().equals(((RecurringEvent) other).getDateTime())
                && frequency.equals(((RecurringEvent) other).frequency)
                && (period == ((RecurringEvent) other).period));
    }
}
