package seedu.hustler.logic.parser.anomaly;

import seedu.hustler.Hustler;
import seedu.hustler.logic.CommandLineException;

/**
 * Detects anomalies in done command inputted by user.
 */
public class DoneAnomaly extends DetectAnomaly {

    private static final String MESSAGE_INVALID_COMMAND_FORMAT = "Done format should be: '/done <integer>'!";
    private static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid!";
    private static final String MESSAGE_TASK_COMPLETED = "This task has already been completed.\n\tYou cannot mark it as done again!";
    /**
     * Detects anomalies in done command input.
     *
     * @param userInput input for which anomaly is detected
     * @throws CommandLineException if the user input does not conform the expected format
     */
    public void detect(String[] userInput) throws CommandLineException {
        if (userInput.length == 1 || userInput[1].isBlank()) {
            throw new CommandLineException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        String[] parsedInput = userInput[1].split(" ");
        if (parsedInput.length > 1) {
            throw new CommandLineException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        int doneIndex;
        try {
            doneIndex = Integer.parseInt(parsedInput[0]);
        } catch (NumberFormatException e) {
            throw new CommandLineException(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        doneIndex--;
        if (doneIndex >= Hustler.list.size() || doneIndex < 0) {
            throw new CommandLineException(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        if (Hustler.list.get(doneIndex).isCompleted()) {
            throw new CommandLineException(MESSAGE_TASK_COMPLETED);
        }
    }
}
