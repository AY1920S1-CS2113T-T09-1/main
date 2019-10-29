package seedu.hustler.game.avatar;

import seedu.hustler.game.shop.items.ShopItem;
import seedu.hustler.game.shop.items.armors.Armor;
import seedu.hustler.game.shop.items.weapons.Weapon;
import java.util.Optional;

/**
 * The avatar which contains information and statistics which is dependent
 * on the productivity of the user.
 */
public class Avatar implements Convertible {

    /**
     * The name of the avatar.
     */
    private String name;

    /**
     * Level of the avatar.
     */
    private Level level;

    /**
     * Stats of the avatar.
     */
    private Stats stats;

    /**
     * The equipped weapon of the avatar, if any.
     */
    private Optional<Weapon> weapon;

    /**
     * The equipped armor of the avatar, if any.
     */
    private Optional<Armor> armor;

    /**
     * Default initialization of the level and stat.
     */
    public Avatar() {
        this.name = "Avatar";
        this.level = new Level();
        this.stats = new Stats();
        this.weapon = Optional.empty();
        this.armor = Optional.empty();
    }

    /**
     * Constructs the avatar instance with the name, level and stat with the equipment
     * of the avatar, if any.
     * @param level the level of the avatar.
     * @param stats the statistics of the avatar.
     * @param weapon the weapon equipped by the avatar, if any.
     * @param armor the armor equipped by the avatar, if any.
     */
    public Avatar(String name, Level level, Stats stats, Optional<Weapon> weapon, Optional<Armor> armor) {
        this.name = name;
        this.level = level;
        this.stats = stats;
        this.weapon = weapon;
        this.armor = armor;
    }

    /**
     * Sets the name for the avatar.
     * @param preferredName the new name to update to the avatar.
     * @return the avatar with the updated name.
     */
    public Avatar setName(String preferredName) {
        this.name = preferredName;
        return this;
    }

    /**
     * Gets the name for the avatar.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the integer level of the avatar.
     * @return the level of the avatar in integer.
     */
    public int getLevelInt() {
        return this.level.getLevel();
    }

    /**
     * Increases avatar xp by 1. Avatar levels up which also
     * raises the stats if the xp reaches level up threshold.
     * @return the level of the avatar.
     */
    public Level gainXp() {
        this.level = level.increaseXp();
        if (this.level.canLevel()) {
            this.level = level.levelUp();
            this.stats = stats.upStats(this.level.getLevel());
        }
        return this.level;
    }

    /**
     * Equips the shop item to the User's avatar.
     * @param equipment the equipment to be equipped.
     * @return the avatar with the updated items.
     */
    public Avatar equip(ShopItem equipment) {
        if(equipment.getType().equals("Weapon")) {
            this.weapon = Optional.of((Weapon) equipment);
        } else if (equipment.getType().equals("Armor")) {
            this.armor = Optional.of((Armor) equipment);
        }
        return this;
    }

    @Override
    public String toString() {
        String equipment = (weapon.isEmpty() && armor.isEmpty()) ? "" : ("\t--- Equipped ---"
            + (weapon.map(x -> "\n[ " + x.toString() + "]").orElse(""))
                + (armor.map(x -> "\n[ " + x.toString() + "]").orElse("")));
        return this.name + ", " + this.level.toString() + "\n"
            + this.stats.getStats(weapon, armor) + "\n" + equipment;
    }

    @Override
    public String toTxt() {
        return "Name " + this.name + "\n"
            + "Level " + this.level.toTxt() + "\n"
            + "Stats " + this.stats.toTxt() + "\n"
            + "Weapon " + this.weapon.map(Weapon::toString) + "\n"
            + "Armor " + this.armor.map(Armor::toString);
    }
}
