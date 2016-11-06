import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Formatter;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by employee on 11/2/16.
 */

public class CalendarTest {
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS_IN_MONTH = 6;
    private static final String FORMAT = "%4s";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final String RED_TEXT_START_TOKEN = (char) 27 + "[31m";
    private static final String RED_TEXT_END_TOKEN = (char) 27 + "[0m";
    private static final String GREEN_TEXT_START_TOKEN = (char) 27 + "[32m";
    private static final String GREEN_TEXT_END_TOKEN = (char) 27 + "[0m";

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));


    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void ColorOfCurrentDay() throws IOException {
        StringBuilder expected = new StringBuilder();
        Formatter formatter = new Formatter(expected, Locale.US);
        int[][] a = new int[6][7];
        int weekStartWithThisDayInt = 0;
        int dayNow = 9;
        int[] weekends = {0, 0, 1, 0, 0, 0, 0};
        for (int i = 0; i < MAX_WEEKS_IN_MONTH; i++) {
            for (int j = 0; j < DAYS_IN_WEEK; j++) {
                if (a[i][j] == 0) {
                    formatter.format("    ");
                    continue;
                }
                if (a[i][j] == dayNow)
                    formatter.format(GREEN_TEXT_START_TOKEN + "%4d" + GREEN_TEXT_END_TOKEN, a[i][j]);
                else if (weekends[j] == 1)
                    formatter.format(RED_TEXT_START_TOKEN + "%4d" + RED_TEXT_END_TOKEN, a[i][j]);
                else {
                    formatter.format("%4d", a[i][j]);
                }
            }
            formatter.format("\n");
        }


        PrintInWeb.printCalendarInWeb(weekends, dayNow, a, weekStartWithThisDayInt);


//        assertThat(formatter.toString(), equalTo(outContent.toString()));
    }


    @Test
    public void CalendarHeader() {
        int[] weekends = {1, 1, 0, 0, 0, 0, 0};
        int dayNow = 9;
        int[][] a = new int[6][7];
        int weekStartWithThisDayInt = 0;
        StringBuilder expected = new StringBuilder();
        int j = 0;
        for (int i = weekStartWithThisDayInt; i < DAYS_IN_WEEK + weekStartWithThisDayInt; i++) {
            if (weekends[j] == 1) {
                expected.append(String.format(RED_TEXT_START_TOKEN + "%4s" + RED_TEXT_END_TOKEN, WeekFields.of(Locale.UK)
                        .getFirstDayOfWeek()
                        .plus(i)
                        .getDisplayName(TextStyle.SHORT, Locale.UK)
                        .toUpperCase()));
            } else {
                expected.append(String.format("%4s", WeekFields.of(Locale.UK)
                        .getFirstDayOfWeek()
                        .plus(i)
                        .getDisplayName(TextStyle.SHORT, Locale.UK)
                        .toUpperCase()));
            }
            j++;
        }
        expected.append("\n");

        PrintInWeb.printCalendarInWeb(weekends, dayNow, a, weekStartWithThisDayInt);

//        assertThat(expected.toString(), equalTo(outContent.toString()));
    }

    @Test
    public void checkFormat() {
        assertThat(String.format(FORMAT, ""), is("    "));
    }
}