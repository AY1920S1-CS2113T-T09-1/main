package seedu.hustler.logic.command.task;

import seedu.hustler.Hustler;
import seedu.hustler.command.Command;
import seedu.hustler.task.Reminders;

/**
 * Command that executes reminders.
 */
public class RemindCommand extends Command {
    /**
     * Executes remind pipeline.
     */
    public void execute() {
        Reminders.runAll(Hustler.list);
        Reminders.displayReminders();
    } 
}
