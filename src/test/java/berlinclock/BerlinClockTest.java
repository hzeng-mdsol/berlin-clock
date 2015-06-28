package berlinclock;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.DateTimeException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BerlinClockTest {

    private static BerlinClock berlinClock;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @BeforeClass
    public static void setupBerlinClock() {
        berlinClock = new BerlinClock();
    }

    @Test
    public void shouldPrintBerlinTimeForMidnight() {
        verifyBerlinTime("00:00:00", "YOOOOOOOOOOOOOOOOOOOOOOO");
    }

    @Test
    public void shouldPrintBerlinTimeForOneSecondAfterMidnight() {
        verifyBerlinTime("00:00:01", "OOOOOOOOOOOOOOOOOOOOOOOO");
    }

    @Test
    public void throwExceptionWhenIncorrectTime() {
        thrown.expect(DateTimeException.class);
        thrown.expectMessage("Invalid value for HourOfDay (valid values 0 - 23): 25");

        berlinClock.getTime("25:00:00");
    }

    @Test
    public void shouldPrintBerlinTimeForFiveAM() {
        verifyBerlinTime("05:00:00", "YROOOOOOOOOOOOOOOOOOOOOO");
    }

    @Test
    public void shouldPrintBerlinTimeForTwoPM() {
        verifyBerlinTime("14:00:00", "YRROORRRROOOOOOOOOOOOOOO");
    }

    private void verifyBerlinTime(String time, String expectedTime) {
        String berlinClockTime = berlinClock.getTime(time);
        assertThat(berlinClockTime, equalTo(expectedTime));
    }
}