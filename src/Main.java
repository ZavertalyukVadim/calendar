import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Вадимка on 16.07.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        int[][] a = new int[6][7];
        int year;
        int month;
        int day;

        LocalDate today = LocalDate.now();
        //Если приходит дата, она записывается в переменные, если нет, то в записывается текущая дата
        if (args.length>0) {
            year = Integer.parseInt(args[0]);
            month = Integer.parseInt(args[1]);//от 1 до 12
            day = Integer.parseInt(args[2]);//от 1 до 31
        }
        else {
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
        System.out.println("____________________________");
        System.out.print("|MON|TUE|WED|THU|FRI");
        System.out.print((char) 27 + "[31m|SAT" + (char) 27 + "[0m");
        System.out.println((char) 27 + "[31m|SUN" + (char) 27 + "[0m");
        System.out.println("____________________________");
        for(int i=0;i< 6;i++){
            for (int j = 0; j <7 ; j++) {
                    if(a[i][j]==0){
                        System.out.print("    ");
                        continue;
                    }
                    if (j == 5 || j == 6)
                        if (a[i][j] == day) System.out.printf((char) 27 + "[32m%4d" + (char) 27 + "[0m", a[i][j]);
                        else System.out.printf((char) 27 + "[31m%4d" + (char) 27 + "[0m", a[i][j]);
                    else if (a[i][j] == day) System.out.printf((char) 27 + "[32m%4d" + (char) 27 + "[0m", a[i][j]);
                    else System.out.printf("%4d", a[i][j]);

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
        int allDay = LocalDate.of(year,month,day).lengthOfMonth();
        return allDay;
    }

    private static int getDayOfWeek(LocalDate specificDate) {
        Integer dayOfWeek = specificDate.withDayOfMonth(1).getDayOfWeek().getValue();
        return dayOfWeek;
    }

}
