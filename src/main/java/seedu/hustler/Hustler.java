package seedu.hustler;

import java.io.IOException;

import seedu.hustler.game.avatar.Avatar;
import seedu.hustler.command.Command;
import java.util.Scanner;

import seedu.hustler.data.AvatarStorage;
import seedu.hustler.data.Schedule;
import seedu.hustler.logic.CommandLineException;
import seedu.hustler.task.Reminders;
import seedu.hustler.ui.Ui;
import seedu.hustler.data.Storage;
import seedu.hustler.data.Folder;
import seedu.hustler.task.TaskList;
import seedu.hustler.parser.CommandParser;

/**
 * A personal assitant that takes in user input and gives and performs
 * an operation that can help the user
 * in his day to day needs. Has a tasklist with multiple features.
 */
public class Hustler {
    /**
     * TaskList instance that  stores all the tasks added by the
     * user and from storage.
     */
    public static TaskList list;

    /**
     * Storage instance that stores and loads tasks to and from
     * disk.
     */
    private static Storage storage = new Storage("data/duke.txt");
    /**
     * UI instance that is used to take input from console
     * and display errors and responses. Handles user interaction.
     */
    private static Ui ui = new Ui(new Scanner(System.in));
    /**
     * Parser instance that makes sense of user input and
     * performs some operation on list.
     */
    private static CommandParser parser = new CommandParser();

    /**
     * Avatar instance that keeps track of the User's progress.
     */
    public static Avatar avatar;

    /**
     * Runs Duke which commences the user to machine
     * feedback loop until the user enters "bye".
     * Loads existing tasklist and avatar, and performs operations
     * like list, find, delete and add on the tasklist. Adds
     * the Tasks in the TreeMap.
     * Saves the list to disk for next duke session inside
     * data/duke.txt.
     * @see Storage
     * @see TaskList
     * @see Parser
     * @see Ui
     * @see Schedule
     */
    public static void run() throws IOException {
        ui.show_opening_string();
        Folder.checkDirectory();
        list = new TaskList(storage.load());

        // Display reminders at the start
        Reminders.runAll(list);
        Reminders.displayReminders();
        System.out.println();
        avatar = AvatarStorage.load();
        AvatarStorage.save(avatar);

        // Taking the the first raw input
        String rawInput = ui.take_input();

        // Taking input and printing till user input is bye
        while (!rawInput.equals("bye")) {
            try {
                Command command = parser.parse(rawInput);
                command.execute();
                try {
                    storage.save(list.return_list());
                } catch (IOException e) {
                    ui.show_save_error();
                }
                rawInput = ui.take_input();

                System.out.println();
            } catch (CommandLineException e) {
                e.getErrorMsg();
                rawInput = ui.take_input();
            }
        }
        ui.show_bye_message();
    }

    /**
     * Main function run by java.
     *
     * @param args the command line parameters
     */
    public static void main(final String[] args) throws IOException {
        Hustler.run();
    }
}