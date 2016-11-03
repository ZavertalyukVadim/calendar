import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Вадимка on 16.07.2016.
 */
public class Calendar {
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    private static Print print = new Print();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int[][] a = new int[6][7];
        LocalDate specificDate = getDate(args);
        System.out.println("С какого дня начинается неделя (1- пн, 7 - ВС) = ");
        int weekStartWithThisDayInt = Integer.parseInt(input.readLine());
        weekStartWithThisDayInt--;
        int[] weekends = {0, 1, 1, 0, 1, 0, 0};

        int firstDayWeekIndex = weekIndexOfFirstDay(specificDate);

        //узнаем количество дней в заданом месяце
        int monthLength = specificDate.lengthOfMonth();

        //формируем массив
        fillInCalendarArray(a, firstDayWeekIndex, monthLength);


        // /выводим введенное дату,время
        System.out.println("Дата с указанием года, месяца и дня : " + specificDate);
        print.printCalendarHeader(weekends, weekStartWithThisDayInt);
        print.printCalendarArray(a, specificDate.getDayOfMonth(), weekends);
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

    //формирование массива с днями месяца
    private static void fillInCalendarArray(int[][] a, int dayOfWeek, int dayInMonth) {
        int number = 1;
        for (int i = dayOfWeek - 2; i < DAYS_IN_WEEK; i++) {
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
        return specificDate.withDayOfMonth(1).getDayOfWeek().getValue();
    }

}