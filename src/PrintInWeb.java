import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

class PrintInWeb {
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    private static final String RED_TEXT_START_TOKEN_IN_WEB = "<font style ='color:red;'>";
    private static final String RED_TEXT_END_TOKEN_IN_WEB = "</font>";
    private static final String GREEN_TEXT_START_TOKEN_IN_WEB = "<font style ='color:green;'>";
    private static final String GREEN_TEXT_END_TOKEN_IN_WEB = "</font>";

    public static String printCalendarInWeb(int[] weekends, int dayStartWithThisDate, int[][] a, int day) {
        return printHeaderHTML() +
                printCalendarHeader(weekends, day) +
                printCalendarArray(a, dayStartWithThisDate, weekends) +
                printDownHTML();
    }


    private static String printCalendarArray(int[][] a, int day, int[] weekends) {
        StringBuilder printerCalendarArray = new StringBuilder();
        for (int i = 0; i < MAX_WEEKS_IN_MONTH; i++) {
            printerCalendarArray.append("<tr>");
            for (int j = 0; j < DAYS_IN_WEEK; j++) {
                if (a[i][j] == 0) {
                    printerCalendarArray.append("<td>" + "    " + "</td>");
                    continue;
                }
                if (a[i][j] == day)
                    printerCalendarArray.append(String.format("<td>" + GREEN_TEXT_START_TOKEN_IN_WEB + "%4d" + GREEN_TEXT_END_TOKEN_IN_WEB + "</td>", a[i][j]));
                else if (weekends[j] == 1)
                    printerCalendarArray.append(String.format("<td>" + RED_TEXT_START_TOKEN_IN_WEB + "%4d" + RED_TEXT_END_TOKEN_IN_WEB + "</td>", a[i][j]));
                else {
                    printerCalendarArray.append(String.format("<td>" + "%4d" + "</td>", a[i][j]));
                }
            }
            printerCalendarArray.append("\n" + "</tr>");
        }
        return printerCalendarArray.toString();
    }

    private static String printCalendarHeader(int[] weekends, int dayStartWithThisDate) {
        int j = 0;
        StringBuilder printerCalendarHeader = new StringBuilder();
        printerCalendarHeader.append("<tr>");
        for (int i = dayStartWithThisDate; i < DAYS_IN_WEEK + dayStartWithThisDate; i++) {
            if (weekends[j] == 1) {
                printerCalendarHeader.append(String.format("<td>" + RED_TEXT_START_TOKEN_IN_WEB + "%4s" + RED_TEXT_END_TOKEN_IN_WEB + "</td>", WeekFields.of(Locale.UK)
                        .getFirstDayOfWeek()
                        .plus(i)
                        .getDisplayName(TextStyle.SHORT, Locale.UK)
                        .toUpperCase()));
            } else {
                printerCalendarHeader.append(String.format("<td>" + "%4s" + "</td>", WeekFields.of(Locale.UK)
                        .getFirstDayOfWeek()
                        .plus(i)
                        .getDisplayName(TextStyle.SHORT, Locale.UK)
                        .toUpperCase()));
            }
            j++;
        }
        printerCalendarHeader.append("</tr>");
        return printerCalendarHeader.toString();
    }

    private static String printHeaderHTML() {

        return "<Html>\n" +
                "<head>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>";
    }

    private static String printDownHTML() {

        return "</table>\n" +
                "</body>\n" +
                "</Html>";
    }
}