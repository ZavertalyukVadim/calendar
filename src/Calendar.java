import java.io.*;
import java.time.LocalDate;
import java.time.Month;

public class Calendar {
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    PrintInConsole printInConsole = new PrintInConsole();
    PrintInWeb printInWeb = new PrintInWeb();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int[][] a = new int[6][7];
        LocalDate specificDate = getDate(args);
        System.out.println("С какого дня начинается неделя (1- пн, 7 - ВС) = ");
        int weekStartWithThisDay = Integer.parseInt(input.readLine());
        weekStartWithThisDay--;
        int[] weekends = {0, 0, 0, 0, 0, 1, 1};
        boolean console = true;
        boolean web = true;
//        int j=1;
//        System.out.println("Введите '1', когда хотите, что бы этот день был выходным, а когда рабочим - 0''");
//        for (int i = 0; i < weekends.length; i++) {
//            System.out.println(i+j+"-ы/ой день");
//            weekends[i]= Integer.parseInt(input.readLine());
//        }

        int firstDayWeekIndex = weekIndexOfFirstDay(specificDate);

        //узнаем количество дней в заданом месяце
        int monthLength = specificDate.lengthOfMonth();

        //формируем массив
        fillInCalendarArray(a, firstDayWeekIndex + weekStartWithThisDay, monthLength);


        ///выводим введенное дату,время
        System.out.println("Дата с указанием года, месяца и дня : " + specificDate);

        if (console && web) {
            try (PrintWriter printWriter = new PrintWriter("text3.html")) {
                printWriter.println(PrintInWeb.printCalendarInWeb(weekends, specificDate.getDayOfMonth(), a, weekStartWithThisDay));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(PrintInConsole.printCalendarInWeb(weekends, specificDate.getDayOfMonth(), a, weekStartWithThisDay));
        }
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
        return specificDate.withDayOfMonth(1).getDayOfWeek().getValue();
    }

}