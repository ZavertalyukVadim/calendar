import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Calendar {
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    private YearMonth month;
    private LocalDate today;
    private DayOfWeek dayOfWeek;
    private List<DayOfWeek> weekends;
    private Locale locale;

    public Calendar() {
        this(LocalDate.now());
    }

    public Calendar(LocalDate today) {
        this(YearMonth.now(), today);
    }

    public Calendar(YearMonth month, LocalDate today) {
        this.month = month;
        this.today = today;
        dayOfWeek=DayOfWeek.MONDAY;
        weekends.add(DayOfWeek.SATURDAY.minus(1));
        weekends.add(DayOfWeek.SUNDAY.minus(1));
        locale=Locale.getDefault();
    }


    public static void main(String[] args) throws IOException {
        int[][] massiveWithCalendar = new int[6][7];
        int counter;
        Calendar calendar = new Calendar();
        calendar.print();
//        LocalDate specificDate = getDate(args);
//
//        PrintInConsole printInConsole = new PrintInConsole();
//        PrintInWeb printInWeb = new PrintInWeb();
//
//        int firstDayOfYourCalendar = getFirstDayOfYourWeek().getValue();
//
//        List<DayOfWeek> weekends = getWeekends();
//
//        boolean web = true;
//
//        int monthLength = specificDate.lengthOfMonth();
//
//        if (firstDayOfYourCalendar == 0) {
//            counter = 0;
//        } else counter = 7 - firstDayOfYourCalendar;
//
//        int firstDayInMonth = specificDate.with(TemporalAdjusters.firstDayOfMonth()).getDayOfWeek().getValue();
//
//        fillInCalendarArray(massiveWithCalendar, firstDayInMonth + counter, monthLength);
//
//        System.out.println("Дата с указанием года, месяца и дня : " + specificDate);
//        if (web) {
////            try (PrintWriter printWriter = new PrintWriter("calendar.html")) {
////                printWriter.println(printInWeb.printCalendarInWeb(weekends, firstDayOfYourCalendar, massiveWithCalendar, specificDate.getDayOfMonth()));
////            } catch (FileNotFoundException e) {
////                e.printStackTrace();
////            }
//            System.out.println(printInConsole.printCalendarInConsole(weekends, firstDayOfYourCalendar, massiveWithCalendar, specificDate.getDayOfMonth()));
//        }
    }
    public String print(){
        return "";
    }

    private static DayOfWeek getFirstDayOfYourWeek() {
        return DayOfWeek.THURSDAY.minus(1);
    }

    private static List<DayOfWeek> getWeekends() {
        List<DayOfWeek> weekends = new ArrayList<>();
        weekends.add(DayOfWeek.SATURDAY.minus(1));
        weekends.add(DayOfWeek.SUNDAY.minus(1));
        weekends.add(DayOfWeek.MONDAY.minus(1));
        weekends.add(DayOfWeek.THURSDAY.minus(1));
        return weekends;
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

}