import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Вадимка on 16.07.2016.
 */
public class Main {
    public static final int DAYS_IN_WEEK = 7;
    public static final int MAX_WEEKS_IN_MONTH = 6;
    public static final int SATURDAY_INDEX = 5;
    public static final int SUNDAY_INDEX = 6;
    public static final String RED_TEXT_START_TOKEN = (char) 27 + "[31m" ;
    public static final String RED_TEXT_END_TOKEN = (char) 27 + "[0m";
    public static final String GREEN_TEXT_START_TOKEN = (char) 27 + "[32m" ;
    public static final String GREEN_TEXT_END_TOKEN = (char) 27 + "[0m";

    public static void main(String[] args) throws IOException {

        int[][] a = new int[6][7];

        LocalDate specificDate = getDate(args);

        //с какого дня начинаеться месяц
        int firstDayWeekIndex = weekIndexOfFirstDay(specificDate);

        //узнаем количество дней в заданом месяце
        int monthLength = specificDate.lengthOfMonth();
        fillInCalendarArray(a, firstDayWeekIndex, monthLength);

        // /выводим введенное дату,время
        System.out.println("Дата с указанием года, месяца и дня : " + specificDate);
        printCalendarArray(a, specificDate.getDayOfMonth());
    }

    private static LocalDate getDate(String[] args) {
        LocalDate today = LocalDate.now();
        if (args.length > 0) {
            try {
                int year = Integer.parseInt(args[0]);
                int month = Integer.parseInt(args[1]);
                int day = Integer.parseInt(args[2]);
                return LocalDate.of(year, Month.of(month), day);
            } catch (Exception e) {
                System.out.println("Pas specific date in arguments using following format: YYYY MM DD");
                System.exit(1);
            }
        }
        return today;
    }


    //Вывод календаря
    private static void printCalendarArray(int[][] a, int day) {
        printCalendarHeader();
        for (int i = 0; i < MAX_WEEKS_IN_MONTH; i++) {
            for (int j = 0; j < DAYS_IN_WEEK; j++) {
                if (a[i][j] == 0) {
                    System.out.print("    ");
                    continue;
                }
                if (a[i][j] == day)
                    System.out.printf(GREEN_TEXT_START_TOKEN + "%4d" + GREEN_TEXT_END_TOKEN, a[i][j]);
                else if (j == SATURDAY_INDEX || j == SUNDAY_INDEX)
                    System.out.printf(RED_TEXT_START_TOKEN + "%4d" + RED_TEXT_END_TOKEN, a[i][j]);
                else
                    System.out.printf("%4d", a[i][j]);
            }
            System.out.println();
        }
    }

    private static void printCalendarHeader() {
        System.out.println("____________________________");
        System.out.print("|MON|TUE|WED|THU|FRI|");
        System.out.print(RED_TEXT_START_TOKEN + "SAT" + RED_TEXT_END_TOKEN);
        System.out.print("|");
        System.out.print(RED_TEXT_START_TOKEN + "SUN" + RED_TEXT_END_TOKEN);
        System.out.println();
        System.out.println("____________________________");
    }

    //формирование массива с днями месяца
    private static void fillInCalendarArray(int[][] a, int dayOfWeek, int dayInMonth) {
        int number = 1;
        for (int i = dayOfWeek - 1; i < DAYS_IN_WEEK; i++) {
            a[0][i] = number;
            number++;
        }
        for (int i = 1; i < MAX_WEEKS_IN_MONTH; i++) {
            for (int j = 0; j < DAYS_IN_WEEK; j++) {
                a[i][j] = number;
                number++;
                if (number == dayInMonth + 1)
                    return;
            }
        }
    }

    //Узнаем день, с которого начинается месяц
    private static int weekIndexOfFirstDay(LocalDate specificDate) {
        Integer firstDayWeekIndex = specificDate.withDayOfMonth(1).getDayOfWeek().getValue();
        return firstDayWeekIndex;
    }

}