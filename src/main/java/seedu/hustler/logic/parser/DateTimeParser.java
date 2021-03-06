package seedu.hustler.logic.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

//@@author jingkang97
/**
 * Handles LocalDateTime conversion to String and vice versa for each add command.
 */
public class DateTimeParser {
    /**
     * Date and time with format d/M/uuuu HHmm.
     */
    private static final String DATE_TIME_FORMATTER = "d/M/uuuu HHmm";

    /**
     * Date with format d/M/uuuu HHmm.
     */
    private static final String DATE_FORMATTER = "d/M/uuuu";

    /**
     * Convert type String to type LocalDateTime.
     * @param dateTime date and time of type String.
     * @return date and time of type LocalDateTime.
     */
    public static LocalDateTime getDateTime(String dateTime) throws DateTimeParseException {

        LocalDateTime localDateTime;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER);
        localDateTime = LocalDateTime.parse(dateTime,formatter);
        return localDateTime;
    }

    /**
     * Convert type String to type LocalDateTime.
     * @param date date of type String.
     * @return date of type LocalDateTime.
     */
    public static LocalDateTime getDate(String date) {

        LocalDateTime localDate;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
            localDate = LocalDateTime.parse(date,formatter);
            return localDate;
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format!!!");
            return null;
        }
    }

    /**
     * Obtains the Date without the Time in LocaLDateTime.
     * @param localDateTime the localDateTime of the task.
     * @return the String given in "DD/MM/YYYY" format.
     */
    public static String convertDateTimeToDate(LocalDateTime localDateTime) {
        int day = localDateTime.getDayOfMonth();
        int month = localDateTime.getMonthValue();
        int year = localDateTime.getYear();
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    /**
     * Obtains only the time in LocalDateTime.
     * @param localDateTime the localDateTime of the task.
     * @return the String given in "HHMM" format.
     */
    public static String getTimeOnly(LocalDateTime localDateTime) {
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        return String.format("%02d%02d", hour, minute);
    }

    /**
     * Convert type LocalDateTime to String.
     * @param localDateTime date and time of type LocalDateTime.
     * @return date and time of type String.
     */
    public static String convertDateTime(LocalDateTime localDateTime) {

        String dateTime;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER);
            dateTime = localDateTime.format(formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date and time format!!!");
            return null;
        }
    }

    /**
     * Convert type LocalDate to String.
     * @param localDate date of type LocalDateTime.
     * @return date of type String.
     */
    public static String convertDate(LocalDateTime localDate) {
        String date;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
            date = localDate.format(formatter);
            return date;
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format!!!");
            return null;
        }

    }

    /**
     * Makes use of the DateTimeFormatter and LocalDateTime class
     * to parse the user input date time and initializes the
     * dateBy member variable.
     */
    public static String toDateTimeString(LocalDateTime dateTime) {
        String suffix;
        switch (dateTime.getDayOfMonth() % 10) {
        case 1:
            suffix = "st";
            break;
        case 2:
            suffix = "nd";
            break;
        case 3:
            suffix = "rd";
            break;
        default:
            suffix = "th";
            break;
        }

        if (dateTime.getDayOfMonth() > 3 && dateTime.getDayOfMonth() < 21) {
            suffix = "th";
        }

        DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("d'"
                + suffix + "' 'of' MMMM uuuu',' h:mma", Locale.ENGLISH);

        return dateTime.format(printFormat);
    }
}
