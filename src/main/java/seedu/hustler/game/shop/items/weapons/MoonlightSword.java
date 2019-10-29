package seedu.hustler.game.shop.items.weapons;

/**
 * The highest tiered Weapon in the shop.
 */
public class MoonlightSword extends Weapon {
    /**
     * The damage increment of the Moonlight Sword.
     */
    private static final int DMG_INCR = 10;

    /**
     * The cost of the Moonlight Sword.
     */
    private static final int COST = 20;

    /**
     * Constructs a new Moonlight Sword with its default variables.
     */
    public MoonlightSword() {
        super(COST, false, DMG_INCR);
    }

    @Override
    public String toString() {
        return "Moonlight Sword, " + super.toString();
    }
}
