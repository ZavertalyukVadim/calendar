import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Created by Вадимка on 16.07.2016.
 */
public class Calendar {
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    private static final int SATURDAY_INDEX = 5;
    private static final int SUNDAY_INDEX = 6;
    private static final int SATURDAY_INDEX_FOR_ENGLAND = 6;
    private static final int SUNDAY_INDEX_FOR_ENGLAND = 0;
    private static final String RED_TEXT_START_TOKEN = (char) 27 + "[31m";
    private static final String RED_TEXT_END_TOKEN = (char) 27 + "[0m";
    private static final String GREEN_TEXT_START_TOKEN = (char) 27 + "[32m";
    private static final String GREEN_TEXT_END_TOKEN = (char) 27 + "[0m";

    public static void main(String[] args) throws IOException {
        int[][] a = new int[6][7];
        LocalDate specificDate = getDate(args);
        //Выбираем Локацию
        Locale locale =Locale.ENGLISH;
        boolean flag;
        int firstDayWeekIndex = weekIndexOfFirstDay(specificDate);

        //узнаем количество дней в заданом месяце
        int monthLength = specificDate.lengthOfMonth();
        if(locale.equals(Locale.ENGLISH)){
            flag=true;
            fillInCalendarArray(a, firstDayWeekIndex, monthLength);
        }
        else{
            flag=false;
            fillInCalendarArray(a, firstDayWeekIndex, monthLength);
        }



        // /выводим введенное дату,время
        System.out.println("Дата с указанием года, месяца и дня : " + specificDate);
        printCalendarHeader(locale);
                printCalendarArray(a, specificDate.getDayOfMonth(),locale);
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


    //Вывод календаря
    static void printCalendarArray(int[][] a, int day, Locale locale) {
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
                }
//                else if (locale!=locale.ENGLISH){
//                    if(j == SATURDAY_INDEX || j == SUNDAY_INDEX)
//                    System.out.printf(RED_TEXT_START_TOKEN + "%4d" + RED_TEXT_END_TOKEN, a[i][j]);
//                }

                else
                    System.out.printf("%4d", a[i][j]);
            }
            System.out.println();
        }
    }

    public static void printCalendarHeader(Locale locale) {
        for (int i = 0; i < 7; i++) {
            if(locale.equals(Locale.ENGLISH)){
                if (i==0||i==6){
                    System.out.print(String.format(RED_TEXT_START_TOKEN + "%4s" + RED_TEXT_END_TOKEN,WeekFields.of(locale)
                            .getFirstDayOfWeek()
                            .plus(i)
                            .getDisplayName(TextStyle.SHORT,locale)
                            .toUpperCase()));
                }
                else {
                    System.out.print(String.format("%4s" ,WeekFields.of(locale)
                            .getFirstDayOfWeek()
                            .plus(i)
                            .getDisplayName(TextStyle.SHORT,locale)
                            .toUpperCase()));
                }
            }
            if(locale!=locale.ENGLISH){
                if (i==5||i==6){
                    System.out.print(String.format(RED_TEXT_START_TOKEN + "%4s" + RED_TEXT_END_TOKEN,WeekFields.of(locale)
                            .getFirstDayOfWeek()
                            .plus(i)
                            .getDisplayName(TextStyle.SHORT,locale)
                            .toUpperCase()));
                }
                else {
                    System.out.print(String.format("%4s" ,WeekFields.of(locale)
                            .getFirstDayOfWeek()
                            .plus(i)
                            .getDisplayName(TextStyle.SHORT,locale)
                            .toUpperCase()));
                }
            }
        }
        System.out.println();
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