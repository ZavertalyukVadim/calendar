import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Вадимка on 16.07.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        LocalDate today = LocalDate.now();
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
        // Получае текущую дату
//        LocalDate today = LocalDate.now();
//        System.out.println("Текущая дата : " + today);
//        LocalDate ld = date

        LocalDate specificDate = LocalDate.of(year, Month.of(month), day);
        System.out.println("Дата с указанием года, месяца и дня : " + specificDate);
//        System.out.println("Первый день этого месяца : " + today.with(TemporalAdjusters.firstDayOfMonth()));
//        Calendar c = new GregorianCalendar();
//        Calendar c2 = new GregorianCalendar(year, month-1, day);
//        System.out.println(c2.getTime());
//        c2.add(Calendar.DAY_OF_MONTH, 1);
//        System.out.println(c2.getTime());

    }
}
