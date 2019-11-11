package seedu.hustler.game.achievement;

//@@author jingkang97

/**
 * Achievement which can be attained after certain number of tasks are added.
 * There is 3 achievement level which depends on the number of tasks added.
 */
public class AddTask extends Achievements {
    /**
     * Number of tasks added.
     */
    public static int numberOfTasks;

    /**
     * Keeps track of final points which is dependent
     * on number of tasks completed and stores it.
     */
    public static int addPoints;

    /**
     * Achievement level - Bronze, Silver, Gold.
     */
    public static String addAchievementLevel;

    /**
     * Description of achievement.
     */
    private String description;

    /**
     * Information regarding how to attain achievement.
     */
    private String information;

    /**
     * Achievement level - Bronze, Silver, Gold.
     */
    private String achievementLevel;

    /**
     * Points earned.
     */
    private int points;

    /**
     * Achievement status.
     */
    private Boolean locked;

    /**
     * Points earn from bronze level of achievement.
     */
    private static final int BRONZE_POINT = 5;

    /**
     * Points earn from silver level achievement.
     */
    private static final int SILVER_POINT = 10;

    /**
     * Points from gold level achievement.
     */
    private static final int GOLD_POINT = 15;

    /**
     * Initialise this achievement.
     * @param achievementLevel Achievement level of the achievement.
     */
    public AddTask(String achievementLevel) {
        this.description = "Busybee";
        switch (achievementLevel) {
        case "Bronze": {
            this.information = "(User adds 5 tasks)";
        } break;
        case "Silver": {
            this.information = "(User adds 10 tasks)";
        } break;
        case "Gold" : {
            this.information = "(User adds 15 tasks)";
        } break;
        default:
            break;
        }
        this.achievementLevel = achievementLevel;
        this.points = 0;
        this.locked = true;
    }

    private String progress() {
        double nextLevel = numberOfTasks;
        double goal;
        double percentage = 0;

        switch (achievementLevel) {
        case "Bronze": {
            if (numberOfTasks >= 5) {
                nextLevel = 5;
            }
            goal = 5;
            percentage = nextLevel / goal * 100;
        } break;
        case "Silver": {
            if (numberOfTasks >= 10) {
                nextLevel = 10;
            }
            goal = 10;
            percentage = nextLevel / goal * 100;
        } break;
        case "Gold": {
            if (numberOfTasks >= 15) {
                nextLevel = 15;
            }
            goal = 15;
            percentage = nextLevel / goal * 100;
        } break;
        default:
            break;
        }
        return " Progress: [" + (int)percentage + "%]";
    }

    /**
     * Increase the number of tasks added.
     * @return number of tasks added.
     */
    public static int increment() {
        numberOfTasks++;
        return numberOfTasks;
    }

    /**
     * Update the achievement level if user meets the condition.
     * Condition base on number of tasks added.
     * @return Achievement level.
     */
    public static String updateAchievementLevel() {
        if (numberOfTasks == 5) {
            addAchievementLevel = "Bronze";
        } else if (numberOfTasks == 10) {
            addAchievementLevel = "Silver";
        } else if (numberOfTasks == 15) {
            addAchievementLevel = "Gold";
        } else {
            addAchievementLevel = null;
        }
        return addAchievementLevel;
    }

    /**
     * Update points accordingly.
     * @return points attained from number of tasks done.
     */
    public static int updatePoints() {
        if (numberOfTasks == 5) {
            addPoints = BRONZE_POINT;
            totalPoints += addPoints;
        } else if (numberOfTasks == 10) {
            addPoints = SILVER_POINT;
            totalPoints += addPoints;
        } else if (numberOfTasks == 15) {
            addPoints = GOLD_POINT;
            totalPoints += addPoints;
        } else {
            addPoints = 0;
        }
        return addPoints;
    }

    /**
     * Gets achievement level of the achievement.
     * @return achievement level.
     */
    @Override
    public String getAchievementLevel() {
        return this.achievementLevel;
    }

    /**
     * Retrieve description of achievement.
     * @return description.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Retrieve information regarding the achievement.
     * @return information.
     */
    @Override
    public String getInformation() {
        return this.information;
    }

    /**
     * Retrieve current points from achievement.
     * @return points.
     */
    @Override
    public int getPoints() {
        return points;
    }

    /**
     * Update points gained from unlocking achievement.
     * @param points updated points.
     * @return points.
     */
    @Override
    public int setPoints(int points) {
        this.points = points;
        return points;
    }

    /**
     * Checks whether achievement have been unlocked.
     * @return true or false.
     */
    @Override
    public Boolean checkLock() {
        return locked;
    }

    /**
     * Unlocks achievement.
     * @param lock Lock condition.
     * @return true or false.
     */
    @Override
    public Boolean setLock(Boolean lock) {
        locked = lock;
        return locked;
    }

    /**
     * The format in which the achievement will be printed out.
     * @return string format of the achievement.
     */
    @Override
    public String toString() {
        return super.toString() + " " + points + " " + description + " " + achievementLevel + " "
            + this.information + progress();
    }

    /**
     * Format in which achievement will be stored.
     * @return string format of achievement.
     */
    @Override
    public String toTxt() {
        return locked + "|" + points + "|" + achievementLevel + "|" + description + "|" + information;
    }
}
