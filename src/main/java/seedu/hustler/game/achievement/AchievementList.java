package seedu.hustler.game.achievement;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static seedu.hustler.game.achievement.AddTask.addPoints;
import static seedu.hustler.game.achievement.ConsecutiveLogin.loginPoints;
import static seedu.hustler.game.achievement.ConsecutiveLogin.storedDate;
import static seedu.hustler.game.achievement.DoneTask.*;


public class AchievementList {

    /**
     * List of all unlocked and locked achievements.
     */
    public static ArrayList<Achievements> achievementList = new ArrayList<>();


    /**
     * When user first starts Hustler, Hustler will create a list of locked achievements and
     * store it inside achievements.txt.
     * @return list of locked achievements.
     */
    public static ArrayList<Achievements> firstStart(int loginCount) {

        if(loginCount == 1) {
            achievementList.add(new DoneTask("\uD83E\uDD49 Bronze"));
            achievementList.add(new DoneTask("\uD83E\uDD48 Silver"));
            achievementList.add(new DoneTask("\uD83E\uDD47 Gold"));
            achievementList.add(new AddTask("\uD83E\uDD49 Bronze"));
            achievementList.add(new AddTask("\uD83E\uDD48 Silver"));
            achievementList.add(new AddTask("\uD83E\uDD47 Gold"));
            achievementList.add(new ConsecutiveLogin("\uD83E\uDD49 Bronze"));
            achievementList.add(new ConsecutiveLogin("\uD83E\uDD48 Silver"));
            achievementList.add(new ConsecutiveLogin("\uD83E\uDD47 Gold"));
            achievementList.add(new FirstLogin());
            System.out.println("You have unlocked this achievement!\n" + new FirstLogin());
            FirstLogin.updatePoints();
            storedDate = LocalDateTime.now();
        }
        return achievementList;
    }


    public static ArrayList<Achievements> updateAddTask (String achievementLevel) {
        for(int i = 0; i < achievementList.size(); i += 1) {
            if(achievementList.get(i).getDescription().equals("Busybee") && achievementList.get(i).getAchievementLevel().equals(achievementLevel)) {
                achievementList.get(i).setLock(false);
                achievementList.get(i).setPoints(addPoints);
                System.out.println("You have unlocked this achievement!\n" + achievementList.get(i));
            }
        }

        return achievementList;
    }

    public static ArrayList<Achievements> updateDoneTask (String achievementLevel) {
        for(int i = 0; i < achievementList.size(); i += 1) {
            if(achievementList.get(i).getDescription().equals("Completionist") && achievementList.get(i).getAchievementLevel().equals(achievementLevel)) {
                achievementList.get(i).setLock(false);
                achievementList.get(i).setPoints(donePoints);
                System.out.println("You have unlocked this achievement!\n" + achievementList.get(i));
            }
        }
        return achievementList;
    }

    public static ArrayList<Achievements> updateConsecutiveLogin (String achievementLevel) {
        for(int i = 0; i < achievementList.size(); i += 1) {
            if(achievementList.get(i).getDescription().equals("Dedicated to the art") && achievementList.get(i).getAchievementLevel().equals(achievementLevel)) {
                achievementList.get(i).setLock(false);
                achievementList.get(i).setPoints(loginPoints);
                System.out.println("You have unlocked this achievement!\n" + achievementList.get(i));
            }
        }
        return achievementList;
    }

    public static void showList() {
        System.out.println("\uD83D\uDD13 ACHIEVEMENTS UNLOCKED \uD83D\uDD13");
        if(!achievementList.isEmpty()) {
            int l = 0;
            for(int i = 0; i < achievementList.size(); i ++) {
                if(!achievementList.get(i).checkLock()) {
                    l ++;
                    System.out.print(l + ". ");
                    System.out.println(achievementList.get(i));
                }
            }
            System.out.println("\uD83D\uDD12 LOCKED ACHIEVEMENTS \uD83D\uDD12");
            int j = 0;
            for(int i = 0; i < achievementList.size(); i ++) {
                if(achievementList.get(i).checkLock()) {
                    j ++;
                    System.out.print(j + ". ");
                    System.out.println(achievementList.get(i));
                }
            }
            System.out.println("Total Points = " + totalPoints + " \uD83D\uDCB0");
        }

    }

}
