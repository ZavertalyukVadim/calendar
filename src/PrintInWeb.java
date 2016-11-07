import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

class PrintInWeb {
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    private static final String WEEKEND_TEXT_START_TOKEN_IN_WEB = "<td class=\"weekend\">";
    private static final String TEXT_END_TOKEN_IN_WEB = "</td>";
    private static final String CURRENT_DAY_TEXT_START_TOKEN_IN_WEB = "<td class=\"currentDay\">";
    private static final String OPEN_TAG_TABLE_COLUMN = "<td>";
    private static final String CLOSE_TAG_TABLE_COLUMN = "</td>";
    private static final String OPEN_TAG_TABLE_ROW = "<tr>";
    private static final String CLOSE_TAG_TABLE_ROW = "</tr>";

    static String printCalendarInWeb(List<Integer> weekends, int monthStartWithThisDate, int[][] massiveWithCalendar, int nowDay) {
        return printHeaderHTML() +
                printCalendarHeader(weekends, monthStartWithThisDate) +
                printCalendarArray(massiveWithCalendar, nowDay, weekends, monthStartWithThisDate) +
                printDownHTML();
    }


    private static String printCalendarArray(int[][] massiveWithCalendar, int day, List<Integer> weekends, int monthStartWithThisDate) {
        StringBuilder printerCalendarArray = new StringBuilder();
        for (int i = 0; i < MAX_WEEKS_IN_MONTH; i++) {
            printerCalendarArray.append(OPEN_TAG_TABLE_ROW);
            for (int j = 0; j < DAYS_IN_WEEK; j++) {
                if (massiveWithCalendar[i][j] == 0) {
                    printerCalendarArray.append(OPEN_TAG_TABLE_COLUMN + "" + CLOSE_TAG_TABLE_COLUMN);
                    continue;
                }
                if (massiveWithCalendar[i][j] == day)
                    printerCalendarArray.append(String.format(CURRENT_DAY_TEXT_START_TOKEN_IN_WEB + "%4d" + TEXT_END_TOKEN_IN_WEB, massiveWithCalendar[i][j]));
                else if (weekends.contains(j + monthStartWithThisDate)) {
                    printerCalendarArray.append(String.format(WEEKEND_TEXT_START_TOKEN_IN_WEB + "%4d" + TEXT_END_TOKEN_IN_WEB, massiveWithCalendar[i][j]));
                } else {
                    printerCalendarArray.append(String.format(OPEN_TAG_TABLE_COLUMN + "%4d" + CLOSE_TAG_TABLE_COLUMN, massiveWithCalendar[i][j]));
                }
            }
            printerCalendarArray.append("\n" + CLOSE_TAG_TABLE_ROW);
        }
        return printerCalendarArray.toString();
    }

    private static String printCalendarHeader(List<Integer> weekends, int dayStartWithThisDate) {
        StringBuilder printerCalendarHeader = new StringBuilder();
        printerCalendarHeader.append(OPEN_TAG_TABLE_ROW);
        for (int i = dayStartWithThisDate; i < DAYS_IN_WEEK + dayStartWithThisDate; i++) {
            if (weekends.contains(i)) {
                printerCalendarHeader.append(String.format(WEEKEND_TEXT_START_TOKEN_IN_WEB + "%4s" + TEXT_END_TOKEN_IN_WEB, getTypeOfInputCalendarHeader(i)));
            } else {
                printerCalendarHeader.append(String.format(OPEN_TAG_TABLE_COLUMN + "%4s" + CLOSE_TAG_TABLE_COLUMN, getTypeOfInputCalendarHeader(i)));
            }
        }
        printerCalendarHeader.append(CLOSE_TAG_TABLE_ROW);
        return printerCalendarHeader.toString();
    }

    private static String printHeaderHTML() {

        return "<Html>\n" +
                "<head>\n" +
                "<style>\n" +
                "       td.weekend{\n" +
                "           color: red;\n" +
                "       }\n" +
                "       td.currentDay{\n" +
                "           color: green;\n" +
                "       }\n" +
                "       td{\n" +
                "           padding:50px;\n" +
                "       }\n" +
                "   </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>";
    }

    private static String printDownHTML() {

        return "</table>\n" +
                "</body>\n" +
                "</Html>";
    }

    private static String getTypeOfInputCalendarHeader(int i) {
        return WeekFields.of(Locale.UK)
                .getFirstDayOfWeek()
                .plus(i)
                .getDisplayName(TextStyle.SHORT, Locale.UK)
                .toUpperCase();
    }
}