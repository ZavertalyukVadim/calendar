import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

class Print {
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    private static final int SATURDAY_INDEX = 5;
    private static final int SUNDAY_INDEX = 6;
    private static final int SATURDAY_INDEX_FOR_ENGLAND = 6;
    private static final int SUNDAY_INDEX_FOR_ENGLAND = 0;
    private static final String GREEN_TEXT_START_TOKEN = (char) 27 + "[32m";
    private static final String GREEN_TEXT_END_TOKEN = (char) 27 + "[0m";
    private static final String RED_TEXT_START_TOKEN = (char) 27 + "[31m";
    private static final String RED_TEXT_END_TOKEN = (char) 27 + "[0m";

    void printCalendarArray(int[][] a, int day, Locale locale,int[] weekends) {

        for (int i = 0; i < MAX_WEEKS_IN_MONTH; i++) {
            for (int j = 0; j < DAYS_IN_WEEK; j++) {
                if (a[i][j] == 0) {
                    System.out.print("    ");
                    continue;
                }
                if (a[i][j] == day)
                    System.out.printf(GREEN_TEXT_START_TOKEN + "%4d" + GREEN_TEXT_END_TOKEN, a[i][j]);
                else  if (weekends[j]==1)
                        System.out.printf(RED_TEXT_START_TOKEN + "%4d" + RED_TEXT_END_TOKEN, a[i][j]);
                    else {
                        System.out.printf("%4d", a[i][j]);
                    }
            }
            System.out.println();
        }
    }

    void printCalendarHeader(int[] weekends,Locale locale,int dayStartWithThisDate) {
        int j=0;
        for (int i = dayStartWithThisDate; i < DAYS_IN_WEEK+dayStartWithThisDate; i++) {
                if (weekends[j]==1) {
                    System.out.print(String.format(RED_TEXT_START_TOKEN + "%4s" + RED_TEXT_END_TOKEN,  WeekFields.of(locale)
                            .getFirstDayOfWeek()
                            .plus(i)
                            .getDisplayName(TextStyle.SHORT, locale)
                            .toUpperCase()));
                } else {
                    System.out.print(String.format("%4s", WeekFields.of(locale)
                            .getFirstDayOfWeek()
                            .plus(i)
                            .getDisplayName(TextStyle.SHORT, locale)
                            .toUpperCase()));
                }
                j++;
        }
        System.out.println();

//            if (locale.equals(Locale.ENGLISH)||locale.equals(Locale.CANADA)) {/////////////////
//                if (i == 0 || i == 6) {
//                    System.out.print(String.format(RED_TEXT_START_TOKEN + "%4s" + RED_TEXT_END_TOKEN, WeekFields.of(locale)
//                            .getFirstDayOfWeek()
//                            .plus(i)
//                            .getDisplayName(TextStyle.SHORT, locale)
//                            .toUpperCase()));
//                } else {
//                    System.out.print(String.format("%4s", WeekFields.of(locale)
//                            .getFirstDayOfWeek()
//                            .plus(i)
//                            .getDisplayName(TextStyle.SHORT, locale)
//                            .toUpperCase()));
//                }
//            }
//            if (locale != Locale.ENGLISH && locale != Locale.CANADA) {/////////////////////////
//                if (i == 5 || i == 6) {
//                    System.out.print(String.format(RED_TEXT_START_TOKEN + "%4s" + RED_TEXT_END_TOKEN, WeekFields.of(locale)
//                            .getFirstDayOfWeek()
//                            .plus(i)
//                            .getDisplayName(TextStyle.SHORT, locale)
//                            .toUpperCase()));
//                } else {
//                    System.out.print(String.format("%4s", WeekFields.of(locale)
//                            .getFirstDayOfWeek()
//                            .plus(i)
//                            .getDisplayName(TextStyle.SHORT, locale)
//                            .toUpperCase()));
//                }
//            }


    }


}