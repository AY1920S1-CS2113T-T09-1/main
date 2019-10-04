package seedu.hustler.ui;

import java.util.Scanner;

/**
 * A class dedicated to performing interactions with the user.
 * Takes inputs and contains methods that output error messages.
 */
public class Ui {

    public static final String LINE = "\t_____________________________________";

    /**
     * The scanner object that is used to take input from console.
     */
    private Scanner input;

    /**
     * Initializes the input object.
     *
     * @param consoleScanner Scanner object with an input stream
     */
    public Ui(Scanner consoleScanner) {
        this.input = consoleScanner;
    }

    /**
     * Default constructor.
     */
    public Ui() {
    }

    /**
     * Takes a string single line input.
     * @return user string input
     */
    public String take_input() {
        String userInput = input.nextLine();
        return userInput;
    }

    /**
     * Prints an output message if list history was not saved.
     */
    public void show_save_error() {
        System.out.println("\t_____________________________________");
        System.out.println("\tCouldn't save file.");
        System.out.println("\t_____________________________________\n\n");
    }

    /**
     * Prints a bye message if user enters bye.
     */
    public void show_bye_message() {
        System.out.println("\t_____________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t_____________________________________");
    }

    /**
     * Prints an error message if user does not enter a valid command.
     */
    public void correct_command_error() {
        System.out.println("\t_____________________________________");
        System.out.println("\tPlease enter a valid command: todo, "
            + "deadline, event, doafter, list, bye, find, delete.");
        System.out.println("\t_____________________________________\n\n");
    }

    /**
     * Prints an error message if the format of event or deadline
     * commands is not correct.
     */
    public void wrong_description_error() {
        System.out.println("\t_____________________________________");
        System.out.println("\tDescription needs a '/' before by/at/after");
        System.out.println("\t_____________________________________\n\n");
    }

    /**
     * Prints an error message if user performs an operation on a nonexistant
     * task.
     */
    public void task_doesnt_exist_error() {
        System.out.println("\t_____________________________________");
        System.out.println("\tTask doesn't exist. Please choose another.");
        System.out.println("\t_____________________________________\n\n");
    }

    /**
     * Prints error message if an empty list is asked to be displayed.
     */
    public void show_empty_list_error() {
        System.out.println("\t_____________________________________");
        System.out.println("\tList is empty. Please type "
            + "another command apart from list.");
        System.out.println("\t_____________________________________\n\n");
    }

    /**
     * Prints an error message if a command like find, delete, todo, deadline,
     * event or others is entered without a following description.
     */
    public void empty_description_error() {
        System.out.println("\t_____________________________________");
        System.out.println("\tPlease enter a description after the command.");
        System.out.println("\t_____________________________________\n\n");
    }

    /**
     * Prints an error message when the format of the date/time
     * entered for event or deadline is not correct.
     */
    public void date_time_error() {
        System.out.println("\t_____________________________________");
        System.out.println("\tFormat of time is incorrect either in command "
            + "or save file. Saving event/deadline as mentioned without date "
            + "time parsing.");
        System.out.println("\t_____________________________________\n\n");
    }

    /**
     * Prints an error message when the format of date is wrong when showing schedule.
     */
    public void dateFormatError() {
        System.out.println("\t_____________________________________");
        System.out.println("\tFormat of date invalid. Please type it in in DD/MM/YYYY");
        System.out.println("\t_____________________________________");
    }

    /**
     * Prints an error message when the format of show command is wrong.
     */
    public void showFormatError() {
        System.out.println("\t_____________________________________");
        System.out.println("\tShow command invalid. Please only type \"show <DD/MM/YYYY>\"");
        System.out.println("\t_____________________________________");
    }

    /**
     * A hello message when duke is ran.
     */
    public void show_opening_string() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}