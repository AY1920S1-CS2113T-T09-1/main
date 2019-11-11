package seedu.hustler.logic.parser.anomaly;

import seedu.hustler.logic.CommandLineException;
import seedu.hustler.ui.Ui;
import seedu.hustler.Hustler;

/**
 * Detects timer anomalies in user input.
 */
public class TimerAnomaly extends DetectAnomaly {

    /**
     * Detects anomalies for input.
     *
     * @param userInput input for which anomaly is detected
     */
    public void detect(String[] userInput) throws CommandLineException {

        if (Hustler.timermanager.isRunning()) {
            throw new CommandLineException("Timer already running. Please use /stoptimer to stop the current timer.");
        }

        //detects if the /timer command is followed with any arguments.
        if (userInput.length == 1) {
            throw new CommandLineException("Timer format should be: 'timer <integer> <integer> <integer>'!");
        }

        String[] timeParts = userInput[1].split(" ");

        //detects whether the number of arguments are exactly three (hours, minutes and
        //seconds). For example, 'timer 1' and 'timer 1 2 3 4' are invalid inputs.
        if (timeParts.length != 3) {
            throw new CommandLineException("Timer format should be: 'timer <integer> <integer> <integer>'!");
        }

        //detects whether the arguments are integers. For example, 'timer
        //tacobell' is a invalid input.
        try {
            int hours = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);
            int seconds = Integer.parseInt(timeParts[2]);
        } catch (NumberFormatException e) {
            throw new CommandLineException("Timer commands should follow this format: 'timer <integer> <integer> <integer>'!");
        }
	
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        int seconds = Integer.parseInt(timeParts[2]);

        if (hours < 0 || minutes < 0 || seconds < 0) {
            throw new CommandLineException("Hours, minutes and seconds should be positive integers.");
        }

        //detects whether the relevant arguments are non-integers.
        // For example, 'timer winter cheese sofa' is a invalid input.
        for (int i = 0; i < 3; i += 1) {
            try {
                int timeparts = Integer.parseInt(timeParts[i]);
            } catch (NumberFormatException e) {
                throw new CommandLineException("Timer format should be: 'timer <integer> <integer> <integer>'!");
            }
        }

        //detects if the arguments are neagtive integers.
        for (int i = 0; i < 3; i += 1) {
            int timeparts = Integer.parseInt(timeParts[i]);
            if (timeparts < 0) {
                throw new CommandLineException("Timer only accepts positive integers as arguments!");
            }
        }

        //detects if the arguments cause overflow, i.e. minutes or seconds more than 60.
        for (int i = 1; i < 3; i += 1) {
            int timeparts = Integer.parseInt(timeParts[i]);
            if (timeparts >= 60) {
                throw new CommandLineException("Timer only accepts integers between 0 and 59 (inclusive) "
                        + "for the minutes and seconds!");
            }
        }
    }
}
