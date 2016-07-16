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
        int[][] a = new int[6][7];
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j <6 ; j++) {
//                a[i][j]=0;
//            }
//        }


        //задаем год,месяц,время
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        LocalDate today = LocalDate.now();
        System.out.println("Введите год, месяц и день, на которые хотите увидеть календарь, если хотите увидеть календарь текущего времени, просто, заполните поля '0'");
        System.out.println("Введите год (прим.2016): ");
        int year = Integer.parseInt(input.readLine());
        System.out.println("Введите месяц(от 1 до 12): ");
        int month = Integer.parseInt(input.readLine());
        System.out.println("Введите день(от 1 до 31 учитывая количество дней в каждом месяце): ");
        int day = Integer.parseInt(input.readLine());
        if(year == 0 && month==0&&day ==0){
            year=today.getYear();
            month=today.getMonthValue();
            day=today.getDayOfMonth();
        }

        LocalDate specificDate = LocalDate.of(year, Month.of(month), day);

        //с какого дня начинаеться месяц
        int dayOfWeek = getDayOfWeek(specificDate);

        //узнаем количество дней в заданом месяце
        int dayInMonth= howDayInMonth(year, month, day);
        enteringIntoAnArray(a, dayOfWeek, dayInMonth);

        // /выводим введенное дату,время
        System.out.println("Дата с указанием года, месяца и дня : " + specificDate);
        conclusion(a, day);
    }

    private static void conclusion(int[][] a, int day) {
        System.out.println("____________________________________________________________");
        System.out.print("|MONDAY| TUESDAY| WEDNESDAY|THURSDAY|FRIDAY");
        System.out.print((char) 27 + "[31m|SATURDAY" + (char) 27 + "[0m");
        System.out.println((char) 27 + "[31m|SUNDAY" + (char) 27 + "[0m");
        System.out.println("____________________________________________________________");
        for(int i=0;i< 6;i++){
            for (int j = 0; j <7 ; j++) {
                if(j==5 || j==6 )
                    if(a[i][j]==day)System.out.printf((char) 27 + "[32m%8d " + (char) 27 + "[0m",a[i][j]);
                    else System.out.printf((char) 27 + "[31m%8d " + (char) 27 + "[0m",a[i][j]);
                else    if(a[i][j]==day)System.out.printf((char) 27 + "[32m%8d " + (char) 27 + "[0m",a[i][j]);
                else    System.out.printf("%8d", a[i][j]);

            }
            System.out.println();
        }
    }

    private static void enteringIntoAnArray(int[][] a, int dayOfWeek, int dayInMonth) {
        int number=1;
        for (int i = dayOfWeek-1; i <7 ; i++) {
            a[0][i]=number;
            number++;
        }
        for (int i = 1; i <6 ; i++) {
            for ( int j = 0; j <7 ; j++) {
                a[i][j]=number;
                number++;
                if(number==dayInMonth+1)
                    return;
            }
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
