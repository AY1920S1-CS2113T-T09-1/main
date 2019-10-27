package seedu.hustler;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.hustler.command.Command;
import seedu.hustler.data.*;
import seedu.hustler.game.achievement.AchievementList;
import seedu.hustler.game.achievement.AddTask;
import seedu.hustler.game.achievement.ConsecutiveLogin;
import seedu.hustler.game.avatar.Avatar;
import seedu.hustler.game.avatar.Inventory;
import seedu.hustler.game.shop.ShopList;
import seedu.hustler.logic.CommandLineException;
//import seedu.hustler.parser.CommandParser;
import seedu.hustler.task.Reminders;
import seedu.hustler.task.TaskList;

import seedu.hustler.ui.Ui;

import seedu.hustler.logic.parser.CommandParser;

import seedu.hustler.ui.timer.TimerManager;

import java.io.IOException;

import static seedu.hustler.game.achievement.ConsecutiveLogin.updateAchievementLevel;

/**
 * A personal assistant that takes in user input and gives and performs
 * an operation that can help the user
 * in his day to day needs. Has a task list with multiple features.
 */
public class Hustler extends Application {
    /**
     * TaskList instance that  stores all the tasks added by the
     * user and from storage.
     */
    public static TaskList list;

    /**
     * shopList stores and manages the items to purchased in Hustler.
     * Bought items will be added to the storage and is not able for purchase
     * in the future.
     */
    public static ShopList shopList = new ShopList();


    public static AchievementList listAchievements = new AchievementList();

    /**
     * Storage instance that stores and loads tasks to and from
     * disk.
     */
    private static TaskStorage taskStorage = new TaskStorage("data/hustler.txt");

    /**
     * UI instance that is used to take input from console
     * and display errors and responses. Handles user interaction.
     */
    private static Ui ui = new Ui();

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
     * TimerManager instance that starts the timer.
     */
    public static TimerManager timermanager = new TimerManager();

    /**
     * MemoryManager instance that starts the timer.
     */
    public static MemoryManager memorymanager = new MemoryManager();

    /**
     * CommandLog instance that records user tasks.
     */
    public static CommandLog commandlog = new CommandLog();

    /**
     * Inventory that stores all the bought items.
     */
    public static Inventory inventory = new Inventory();

    /**
     * Initializes the essential components needed to run Hustler.
     * Loads existing task list and avatar.
     * Displays reminders at the start of Hustler.
     */
    public static void initialize() throws IOException {
        ui.show_opening_string();
        Folder.checkDirectory();
        loadStorage();
        memorymanager.createBackup();

        Reminders.runAll(list);
        Reminders.displayReminders();
        System.out.println();
        avatar = AvatarStorage.load();
        AvatarStorage.save(avatar);
        shopList = ShopStorage.load();
        inventory = inventory.updateInventory();


    }

    /**
     * Runs Hustler until the users enters "bye".
     * Performs operations like list, find, delete and add on the task list.
     * Saves the list to disk for next Hustler session inside data/hustler.txt.
     *
     * @param rawInput full user's input inside text area of GUI.
     */
    public static void run(String rawInput) {
        try {
            Command command = parser.parse(rawInput);
            command.execute();
            saveStorage();
            System.out.println();
        } catch (CommandLineException e) {
            e.getErrorMsg();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(Stage stage) {
    }

    /**
     * Loads different data from the relevant files.
     */
    public static void loadStorage() throws IOException {
        list = new TaskList(taskStorage.load());
        avatar = AvatarStorage.load();
        listAchievements = AchievementStorage.loadAchievementsss();
        //Check if it's the first time the user logs in.
        AchievementList.firstStart(AchievementStorage.logon());

        //Loads information such as number of tasks done, added, points, etc.
        AchievementStorage.loadStatus();

        //Loads achievements into achievement list.
        //AchievementStorage.loadAchievements();

        //Counts number of consecutive login and updates accordingly.
        ConsecutiveLogin.updateCount();
        ConsecutiveLogin.updatePoints();
        AchievementList.updateConsecutiveLogin(updateAchievementLevel());
        //AchievementStorage.createBackup(achievementList);
    }

    public static void reloadBackup() throws IOException {
        list = new TaskList(taskStorage.reloadBackup());
        avatar = AvatarStorage.reloadBackup();
        AchievementStorage.reloadStatus();
        //AchievementStorage.reloadAchievements();
        AddTask.updateAchievementLevel();
    }

    /**
     * Saves the task list to storage area.
     */
    public static void saveStorage() {
        try {
            taskStorage.save(list.return_list());
            AvatarStorage.save(avatar);

            //AchievementStorage.saveAchievements();
            AchievementStorage.save();
            AchievementStorage.saveStatus();
        } catch (IOException e) {
            ui.show_save_error();
        }
    }
}
