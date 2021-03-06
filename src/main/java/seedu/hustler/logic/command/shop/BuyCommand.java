package seedu.hustler.logic.command.shop;

import seedu.hustler.Hustler;
import seedu.hustler.game.achievement.Achievements;
import seedu.hustler.logic.command.Command;
import seedu.hustler.logic.CommandLineException;
import seedu.hustler.logic.parser.anomaly.BuyAnomaly;
import seedu.hustler.ui.Ui;

/**
 * Command to purchase an item in the shop with the given index.
 */
public class BuyCommand extends Command {

    /**
     * The given user input containing the index of the item to purchase.
     */
    private String[] userInput;

    /**
     * The anomaly class to check if the user input is valid.
     */
    private BuyAnomaly anomaly = new BuyAnomaly();

    /**
     * Constructs a buyCommand with the index of the item.
     * @param userInput the given user input.
     */
    public BuyCommand(String[] userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute() {
        Ui ui = new Ui();
        try {
            anomaly.detect(userInput);
            int index = Integer.parseInt(userInput[1]) - 1;
            if (Hustler.shopList.buy(index, Achievements.totalPoints).isPresent()) {
                Achievements.totalPoints -= Hustler.shopList.getItem(index).getCost();
                ui.showPurchasedSuccess();
            } else if (Hustler.shopList.getItem(index).isPurchased()) {
                ui.showAlreadyPurchased();
            } else {
                ui.showPurchasedFailure();
            }
        } catch (CommandLineException e) {
            ui.showMessage(e.getMessage());
        }
    }
}
