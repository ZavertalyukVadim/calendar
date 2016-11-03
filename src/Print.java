import java.util.Locale;

public class Print{//Вывод календаря
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
    static void printCalendarArray(int[][] a, int day, java.util.Locale locale) {
        for (int i = 0; i < MAX_WEEKS_IN_MONTH; i++) {
            for (int j = 0; j < DAYS_IN_WEEK; j++) {
                if (a[i][j] == 0) {
                    System.out.print("    ");
                    continue;
                }
                if (a[i][j] == day)
                    System.out.printf(GREEN_TEXT_START_TOKEN + "%4d" + GREEN_TEXT_END_TOKEN, a[i][j]);
                else if (locale.equals(Locale.ENGLISH)){
                    if(j == SATURDAY_INDEX_FOR_ENGLAND || j == SUNDAY_INDEX_FOR_ENGLAND)
                    System.out.printf(RED_TEXT_START_TOKEN + "%4d" + RED_TEXT_END_TOKEN, a[i][j]);
                    else {
                        System.out.printf("%4d", a[i][j]);
                    }
                }
                else if (locale!=locale.ENGLISH){
                    if(j == SATURDAY_INDEX || j == SUNDAY_INDEX)
                    System.out.printf(RED_TEXT_START_TOKEN + "%4d" + RED_TEXT_END_TOKEN, a[i][j]);
                    else {
                        System.out.printf("%4d", a[i][j]);
                    }
                }
            }
            System.out.println();
        }
    }}