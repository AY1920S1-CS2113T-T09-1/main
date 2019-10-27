package seedu.hustler.logic.command.task;

import seedu.hustler.Hustler;
import seedu.hustler.logic.command.Command;

/**
 * Command that sorts the task list.
 */
public class SortCommand extends Command {
    /**
     * User input that contains the way to sort the tasks.
     */
    private String sortType;

    /**
     * Initializes the sortType.
     *
     * @param sortType type of sort.
     */
    public SortCommand(String sortType) {
        this.sortType = sortType;
    }

    /**
     * Sorts the task list.
     */
    public void execute() {
        Hustler.list.sortTask(sortType);
    }
}