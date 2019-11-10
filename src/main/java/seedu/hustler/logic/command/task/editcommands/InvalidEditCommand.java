package seedu.hustler.logic.command.task.editcommands;

import seedu.hustler.logic.command.Command;
import seedu.hustler.ui.Ui;

/**
 * Represents invalid edit command.
 */
public class InvalidEditCommand extends Edit {
    
    /**
     * Initializes index of superclass.
     */
    public InvalidEditCommand() {
        super(-1); 
    }

    /**
     * Print error for InvalidCommand.
     */
    public void execute() {
        Ui ui = new Ui();
        ui.showMessage("Please enter a valid edit keyword: eg. /description or /difficulty");
    }
}
