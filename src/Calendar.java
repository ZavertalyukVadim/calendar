import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;

/**
 * Created by Вадимка on 16.07.2016.
 */
public class Calendar {
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    private static Print print = new Print();

    public static void main(String[] args) throws IOException {
        int[][] a = new int[6][7];
        LocalDate specificDate = getDate(args);
        //Выбираем Локацию
        Locale locale = Locale.US;
        int firstDayWeekIndex = weekIndexOfFirstDay(specificDate);
//CANADA
        //узнаем количество дней в заданом месяце
        int monthLength = specificDate.lengthOfMonth();
        //формируем массив
        if (locale.equals(Locale.ENGLISH)||locale.equals(Locale.CANADA)) {
            fillInCalendarArray(a, firstDayWeekIndex + 1, monthLength);
        } else {
            fillInCalendarArray(a, firstDayWeekIndex, monthLength);
        }

        // /выводим введенное дату,время
        System.out.println("Дата с указанием года, месяца и дня : " + specificDate);
        print.printCalendarHeader(locale);
        print.printCalendarArray(a, specificDate.getDayOfMonth(), locale);
    }

    static LocalDate getDate(String[] args) {
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

    //формирование массива с днями месяца
    static void fillInCalendarArray(int[][] a, int dayOfWeek, int dayInMonth) {
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
    static int weekIndexOfFirstDay(LocalDate specificDate) {
        Integer firstDayWeekIndex = specificDate.withDayOfMonth(1).getDayOfWeek().getValue();
        return firstDayWeekIndex;
    }

}