package seedu.hustler.task;

import java.time.LocalDateTime;
import seedu.hustler.task.variables.Difficulty;
import seedu.hustler.task.variables.Tag;
import static seedu.hustler.logic.parser.DateTimeParser.convertDateTime;
import static seedu.hustler.logic.parser.DateTimeParser.toDateTimeString;

/**
 * A class that inherits from the abstract class Task. This task
 * type is a Deadline which specifies the date and time before
 * which the Task should be completed.
 */
public class Deadline extends Task {
    /**
     * LocalDateTime to store the deadline's date and time.
     */
    protected LocalDateTime by;

    /**
     * Initializes description, default difficulty and by.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Initializes description, user input difficulty and by.
     */
    public Deadline(String description, LocalDateTime by, String difficulty, String tag, LocalDateTime now) {
        super(description, difficulty, tag, now);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, Difficulty difficulty, Tag tag, LocalDateTime now) {
        super(description, difficulty, tag, now);
        this.by = by;
    }

    /**
     * Overrides the toString method in Task to display task type and date time.
     *
     * @return a string with the target info.
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + toDateTimeString(getDateTime()) + ")";
    }

    /**
     * Overrides the toSaveFormat function to include task type and date time.
     *
     * @return a string with pipe separated info.
     */
    public String toSaveFormat() {
        return "D|" + super.toSaveFormat() + "|" + convertDateTime(by) + super.toSaveInputDateTime();
    }

    @Override
    public LocalDateTime getDateTime() {
        return this.by;
    }

    @Override
    public void setDateTime(LocalDateTime localDateTime) {
        this.by = localDateTime;
    }

    /**
     * Checks equality with another Deadline object.
     */
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline
                && description.equals(((Deadline) other).description) // check various attributes
                && getDateTime().equals(((Deadline) other).getDateTime()));
    }
}
