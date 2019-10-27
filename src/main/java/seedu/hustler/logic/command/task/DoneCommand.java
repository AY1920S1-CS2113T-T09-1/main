package seedu.hustler.logic.command.task;

import seedu.hustler.Hustler;
import seedu.hustler.logic.command.Command;
import seedu.hustler.data.CommandLog;
import seedu.hustler.logic.parser.ParserForCommand;
import seedu.hustler.ui.Ui;
import seedu.hustler.schedule.Scheduler;

/**
 * Command to do task in list.
 */
public class DoneCommand extends Command {
    /**
     * User input that contains index of task to do.
     */
    private String[] userInput;
   
    /**
    * Initializes userInput.
    *
    * @param userInput input that contains task id to do.
    */
    public DoneCommand(String[] userInput) {
        this.userInput = userInput;
    }

    /**
     * Does task at index taskIndex inside.
     */
    public void execute() {
        if (this.userInput.length == 1) {
            Ui ui = new Ui();
            ui.empty_description_error();
            CommandLog.deleteLatestLoggedCommand();
            return;
        }
        ParserForCommand doneParser = new ParserForCommand("done");
        int taskIndex = doneParser.parseIndex(this.userInput[1]).intValue();
        Hustler.list.doTask(taskIndex);
        Scheduler.remove(Hustler.list.get(taskIndex));
    }
}
