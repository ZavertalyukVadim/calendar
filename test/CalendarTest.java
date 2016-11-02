import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.Locale;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by employee on 11/2/16.
 */

public class CalendarTest {
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    private static final int SATURDAY_INDEX = 5;
    private static final int SUNDAY_INDEX = 6;
    private static final String FORMAT = "%4s";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final String RED_TEXT_START_TOKEN = (char) 27 + "[31m";
    private static final String RED_TEXT_END_TOKEN = (char) 27 + "[0m";
    private static final String GREEN_TEXT_START_TOKEN = (char) 27 + "[32m";
    private static final String GREEN_TEXT_END_TOKEN = (char) 27 + "[0m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String HELLO = "hello";
    private static String str1 = " MON  TUE WED";
    private static String str2 = " THU FRI";
    Calendar calendar = new Calendar();
    String[] strings = {"2016", "11", "2"};

//    @Before
//    public void setUpStreams() {
//        System.setOut(new PrintStream(outContent));
//        System.setErr(new PrintStream(errContent));
//
//
//    }
//
//    @After
//    public void cleanUpStreams() {
//        System.setOut(null);
//        System.setErr(null);
//    }

    @Test
    public void ColorOfWeek() throws IOException {
        StringBuilder expected = new StringBuilder();
        Formatter formatter = new Formatter(expected, Locale.US);

        int[][] a = new int[6][7];
        int day =1;
        int dayNow=9;

        for (int i = 0; i < MAX_WEEKS_IN_MONTH; i++) {
            for (int j = 0; j < DAYS_IN_WEEK; j++,day++) {
                a[i][j]=day;
            }
        }

//        Calendar.printCalendarArray(a,dayNow);

        for (int i = 0; i < MAX_WEEKS_IN_MONTH; i++) {
            for (int j = 0; j < DAYS_IN_WEEK; j++,day++) {
//                expected.append("   ");
                if (a[i][j] == dayNow) {
                    formatter.format("  ");
                    continue;
                }
                formatter.format("%4d",a[i][j]);

//                    expected.append(GREEN_TEXT_START_TOKEN + "%4d" + GREEN_TEXT_END_TOKEN, a[i][j]);
            }
            formatter.format("\n");
        }



        for (int i = 0; i < MAX_WEEKS_IN_MONTH; i++) {
            for (int j = 0; j < DAYS_IN_WEEK; j++,day++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }



        assertThat(formatter.toString(), equalTo(outContent.toString()));
    }


    @Test
    public void CalendarHeader() {
        StringBuilder expected = new StringBuilder();
        expected.append(" MON  TUE WED THU FRI " + RED_TEXT_START_TOKEN + "SAT" + RED_TEXT_END_TOKEN + " " + RED_TEXT_START_TOKEN + "SUN" + RED_TEXT_END_TOKEN + "\n");

//        Calendar.printCalendarHeader();

        assertThat(expected.toString(), equalTo(outContent.toString()));
    }

    @Test
    public void err() {
        System.out.print("hello again");
        assertEquals("hello again", outContent.toString());
    }

    @Test
    public void checkFormat() {
        assertThat(String.format(FORMAT, ""), is("    "));
    }
}