package seedu.hustler.logic.command.task;

import javafx.application.Platform;
import seedu.hustler.logic.command.Command;
import seedu.hustler.ui.Ui;

/**
 * Command the quits the entire Hustler program.
 */
public class ByeCommand extends Command {
    /**
     * Prints a good bye message and safely exits the program.
     */
    public void execute() {
        Ui ui = new Ui();
        ui.show_bye_message();
        Platform.exit();
    }
}
