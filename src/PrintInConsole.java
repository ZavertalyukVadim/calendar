import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

/**
 * Created by employee on 11/4/16.
 */
class PrintInConsole {
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    private static final String GREEN_TEXT_START_TOKEN = (char) 27 + "[36m";
    private static final String EXT_END_TOKEN = (char) 27 + "[0m";
    private static final String RED_TEXT_START_TOKEN = (char) 27 + "[31m";

    static String printCalendarInConsole(List<Integer> weekends, int monthStartWithThisDate, int[][] massiveWithCalendar, int nowDay) {
        return printCalendarHeader(weekends, monthStartWithThisDate) +
                printCalendarArray(massiveWithCalendar, nowDay, weekends, monthStartWithThisDate);
    }

    public static String printCalendarArray(int[][] massiveWithCalendar, int currentDay, List<Integer> weekends, int monthStartWithThisDate) {
        StringBuilder printerCalendarArray = new StringBuilder();
        for (int i = 0; i < MAX_WEEKS_IN_MONTH; i++) {
            for (int j = 0; j < DAYS_IN_WEEK; j++) {
                if (massiveWithCalendar[i][j] == 0) {
                    printerCalendarArray.append(String.format("%4s", ""));
                    continue;
                }
                if (massiveWithCalendar[i][j] == currentDay)
                    printerCalendarArray.append(String.format(GREEN_TEXT_START_TOKEN + "%4d" + EXT_END_TOKEN, massiveWithCalendar[i][j]));
                else if (weekends.contains(j + monthStartWithThisDate))
                    printerCalendarArray.append(String.format(RED_TEXT_START_TOKEN + "%4d" + EXT_END_TOKEN, massiveWithCalendar[i][j]));
                else {
                    printerCalendarArray.append(String.format("%4d", massiveWithCalendar[i][j]));
                }
            }
            printerCalendarArray.append("\n");
        }
        return printerCalendarArray.toString();
    }

    public static String printCalendarHeader(List<Integer> weekends, int firstDaySelectedMonth) {
        StringBuilder printerCalendarHeader = new StringBuilder();
        for (int i = firstDaySelectedMonth; i < DAYS_IN_WEEK + firstDaySelectedMonth; i++) {
            if (weekends.contains(i)) {
                printerCalendarHeader.append(String.format(RED_TEXT_START_TOKEN + "%4s" + EXT_END_TOKEN, getTypeOfInputCalendarHeader(i)));
            } else {
                printerCalendarHeader.append(String.format("%4s", getTypeOfInputCalendarHeader(i)));

            }
        }
        printerCalendarHeader.append("\n");
        return printerCalendarHeader.toString();
    }

    private static String getTypeOfInputCalendarHeader(int i) {
        return WeekFields.of(Locale.UK)
                .getFirstDayOfWeek()
                .plus(i)
                .getDisplayName(TextStyle.SHORT, Locale.UK)
                .toUpperCase();
    }
}
