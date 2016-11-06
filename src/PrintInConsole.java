import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Created by employee on 11/4/16.
 */
public class PrintInConsole {
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    private static final String GREEN_TEXT_START_TOKEN = (char) 27 + "[32m";
    private static final String GREEN_TEXT_END_TOKEN = (char) 27 + "[0m";
    private static final String RED_TEXT_START_TOKEN = (char) 27 + "[31m";
    private static final String RED_TEXT_END_TOKEN = (char) 27 + "[0m";


    public static String printCalendarInWeb(int[] weekends, int dayStartWithThisDate, int[][] a, int day) {
        return printCalendarHeader(weekends, day) +
                printCalendarArray(a, dayStartWithThisDate, weekends);
    }

    private static String printCalendarArray(int[][] a, int day, int[] weekends) {
        StringBuilder printerCalendarArray = new StringBuilder();
        for (int i = 0; i < MAX_WEEKS_IN_MONTH; i++) {
            for (int j = 0; j < DAYS_IN_WEEK; j++) {
                if (a[i][j] == 0) {
                    printerCalendarArray.append("    ");
                    continue;
                }
                if (a[i][j] == day)
                    printerCalendarArray.append(String.format(GREEN_TEXT_START_TOKEN + "%4d" + GREEN_TEXT_END_TOKEN, a[i][j]));
                else if (weekends[j] == 1)
                    printerCalendarArray.append(String.format(RED_TEXT_START_TOKEN + "%4d" + RED_TEXT_END_TOKEN, a[i][j]));
                else {
                    printerCalendarArray.append(String.format("%4d", a[i][j]));
                }
            }
            printerCalendarArray.append("\n");
        }
        return printerCalendarArray.toString();
    }

    private static String printCalendarHeader(int[] weekends, int dayStartWithThisDate) {
        int j = 0;
        StringBuilder printerCalendarHeader = new StringBuilder();
        for (int i = dayStartWithThisDate; i < DAYS_IN_WEEK + dayStartWithThisDate; i++) {
            if (weekends[j] == 1) {
                printerCalendarHeader.append(String.format(RED_TEXT_START_TOKEN + "%4s" + RED_TEXT_END_TOKEN, WeekFields.of(Locale.UK)
                        .getFirstDayOfWeek()
                        .plus(i)
                        .getDisplayName(TextStyle.SHORT, Locale.UK)
                        .toUpperCase()));
            } else {
                printerCalendarHeader.append(String.format("%4s", WeekFields.of(Locale.UK)
                        .getFirstDayOfWeek()
                        .plus(i)
                        .getDisplayName(TextStyle.SHORT, Locale.UK)
                        .toUpperCase()));
            }
            j++;
        }
        printerCalendarHeader.append("\n");
        return printerCalendarHeader.toString();
    }
}
