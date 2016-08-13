import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Вадимка on 16.07.2016.
 */
public class Main {
    public static final int DAYS_IN_WEEK = 7;
    public static final int MAX_WEEKS_IN_MONTH = 6;
    public static final String GREEN_TEXT_CURRENT_TOKEN = (char) 27 + "[32m%4d" + (char) 27 + "[0m";
    public static final String RED_TEXT_WEEKEND_TOKEN = (char) 27 + "[31m%4d" + (char) 27 + "[0m";
    public static final String RED_TEXT_SATURDAY_TOKEN = (char) 27 + "[31m|SAT" + (char) 27 + "[0m";
    public static final String RED_TEXT_SUNDAY_TOKEN = (char) 27 + "[31m|SUN" + (char) 27 + "[0m";

    public static void main(String[] args) throws IOException {

        int[][] a = new int[6][7];

        LocalDate specificDate = getDate(args);

        //с какого дня начинаеться месяц
        int firstDayWeekIndex = weekIndexOfFirstDay(specificDate);

        //узнаем количество дней в заданом месяце
        int dayInMonth = howDayInMonth(specificDate.getYear(), specificDate.getMonthValue(), specificDate.getDayOfMonth());
        fillInCalendarArray(a, firstDayWeekIndex, dayInMonth);

        // /выводим введенное дату,время
        System.out.println("Дата с указанием года, месяца и дня : " + specificDate);
        printCalendarArray(a, specificDate.getDayOfMonth());
    }

    private static LocalDate getDate(String[] args) {
        LocalDate today = LocalDate.now();
        int year;
        int month;
        int day;
        //Если приходит дата, она записывается в переменные, если нет, то в записывается текущая дата
        if (args.length > 0) {
            year = Integer.parseInt(args[0]);
            month = Integer.parseInt(args[1]);
            day = Integer.parseInt(args[2]);
            return LocalDate.of(year, Month.of(month), day);
        } else {
            return today;
        }
    }

    //Вывод календаря
    private static void printCalendarArray(int[][] a, int day) {
        System.out.println("____________________________");
        System.out.print("|MON|TUE|WED|THU|FRI");
        System.out.print(RED_TEXT_SATURDAY_TOKEN);
        System.out.println(RED_TEXT_SUNDAY_TOKEN);
        System.out.println("____________________________");
        for (int i = 0; i < MAX_WEEKS_IN_MONTH; i++) {
            for (int j = 0; j < DAYS_IN_WEEK; j++) {
                if (a[i][j] == 0) {
                    System.out.print("    ");
                    continue;
                }
                if (j == 5 || j == 6)
                    if (a[i][j] == day) System.out.printf(GREEN_TEXT_CURRENT_TOKEN, a[i][j]);
                    else System.out.printf(RED_TEXT_WEEKEND_TOKEN, a[i][j]);
                else if (a[i][j] == day) System.out.printf(GREEN_TEXT_CURRENT_TOKEN, a[i][j]);
                else System.out.printf("%4d", a[i][j]);

            }
            System.out.println();
        }
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

    //Узнаем количество дней в месяце
    private static int howDayInMonth(int year, int month, int day) {
        int allDay = LocalDate.of(year, month, day).lengthOfMonth();
        return allDay;
    }

    //Узнаем день, с которого начинается месяц
    private static int weekIndexOfFirstDay(LocalDate specificDate) {
        Integer firstDayWeekIndex = specificDate.withDayOfMonth(1).getDayOfWeek().getValue();
        return firstDayWeekIndex;
    }

}
