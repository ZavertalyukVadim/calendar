import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;

/**
 * Created by Вадимка on 16.07.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        //задаем год,месяц,время
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        LocalDate today = LocalDate.now();
        System.out.println("Введите год, месяц и день, на которые хотите увидеть календарь, если хотите увидеть календарь текущего времени, просто, заполните поля '0'");
        System.out.println("Введите год: ");
        int year = Integer.parseInt(input.readLine());
        System.out.println("Введите месяц(от 1 до 12): ");
        int month = Integer.parseInt(input.readLine());
        System.out.println("Введите день: ");
        int day = Integer.parseInt(input.readLine());
        if(year == 0 && month==0&&day ==0){
            year=today.getYear();
            month=today.getMonthValue();
            day=today.getDayOfMonth();
        }


        //выводим введенное дату,время
        LocalDate specificDate = LocalDate.of(year, Month.of(month), day);
        System.out.println("Дата с указанием года, месяца и дня : " + specificDate);
//        System.out.println("Первый день этого месяца : " + today.with(TemporalAdjusters.firstDayOfMonth()));


        //с какого дня начинаеться месяц
        int dayOfWeek = getDayOfWeek(specificDate);
        System.out.println(dayOfWeek);
//        Calendar c2 = new GregorianCalendar(year, month-1, day);
//        System.out.println(c2.getTime());
//        c2.add(Calendar.DAY_OF_MONTH, 1);
//        System.out.println(c2.getTime());


        //узнаем количество дней в заданом месяце
        int DayInMonth= howDayInMonth(year, month, day);
        conclusion(DayInMonth);
    }

    private static void conclusion(int dayInMonth) {
        System.out.println("____________________________________________________________");
        System.out.println("|MONDAY| TUESDAY| WEDNESDAY|THURSDAY|FRIDAY|SATURDAY|SUNDAY|");
        System.out.println("____________________________________________________________");
        for(int i=1;i<= dayInMonth;i++){
            if(i==8 || i==15 || i==22 || i==29)
                System.out.println();
            System.out.printf("%8d", i);
        }
    }

    private static int howDayInMonth(int year, int month, int day) {
        Calendar myCalendar = (Calendar) Calendar.getInstance().clone();
        myCalendar.set(year, month,day);
        int max_date = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println(max_date);
        return max_date;
    }

    private static int getDayOfWeek(LocalDate specificDate) {
        DayOfWeek dayOfWeek = specificDate.withDayOfMonth(1).getDayOfWeek();
        int dayForStart=0;
        if (dayOfWeek.toString() == "MONDAY"){
            dayForStart=1;
        }
        if (dayOfWeek.toString() == "TUESDAY"){
            dayForStart=2;
        }
        if (dayOfWeek.toString() == "WEDNESDAY"){
            dayForStart=3;
        }
        if (dayOfWeek.toString() == "THURSDAY"){
            dayForStart=4;
        }
        if (dayOfWeek.toString() == "FRIDAY"){
            dayForStart=5;
        }
        if (dayOfWeek.toString() == "SATURDAY"){
            dayForStart=6;
        }
        if (dayOfWeek.toString() == "SUNDAY"){
            dayForStart=7;
        }
        return dayForStart;
    }

}
