package seedu.hustler.task.variables;

/**
 * The class that stores the difficulty of the task.
 */
public class Difficulty {

    /**
     * Enumerator that separates the level of the difficulty.
     */
    enum Level {
        L,
        M,
        H
    }

    /**
     * The level of the difficulty.
     */
    private Level level;

    /**
     * Constructs a difficulty level based on user input.
     * Will automatically set default difficulty as M (medium)
     * if user input cannot be parsed.
     * @param difficulty the level of the difficulty.
     */
    public Difficulty(String difficulty) {
        switch (difficulty.toLowerCase()) {
        case "l":
        case "low":
            this.level = Level.L;
            break;
        case "m":
        case "medium":
        default:
            this.level = Level.M;
            break;
        case "h":
        case "high":
            this.level = Level.H;
        }
    }

    @Override
    public String toString() {
        return "[" + this.level.toString() + "]";
    }

    /**
     * Converts the difficulty to string to be stored in a txt file.
     * @return
     */
    public String toSaveFormat() {
        return this.level.toString();
    }

    /**
     * Gets the level of the difficulty.
     * @return the level of the difficulty; low medium or high.
     */
    public Level getLevel() {
        return this.level;
    }
}
