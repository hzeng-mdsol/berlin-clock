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
        verifyBerlinTime("00:00:00", "Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO");
    }

    @Test
    public void shouldPrintBerlinTimeForOneSecondAfterMidnight() {
        verifyBerlinTime("00:00:01", "O\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO");
    }

    @Test
    public void throwExceptionWhenIncorrectTime() {
        thrown.expect(DateTimeException.class);
        thrown.expectMessage("Invalid value for HourOfDay (valid values 0 - 23): 25");

        berlinClock.getTime("25:00:00");
    }

    @Test
    public void shouldPrintBerlinTimeForFiveAM() {
        verifyBerlinTime("05:00:00", "Y\nROOO\nOOOO\nOOOOOOOOOOO\nOOOO");
    }

    @Test
    public void shouldPrintBerlinTimeForTwoPM() {
        verifyBerlinTime("14:00:00", "Y\nRROO\nRRRR\nOOOOOOOOOOO\nOOOO");
    }

    @Test
    public void shouldPrintBerlinTimeForMidday() {
        verifyBerlinTime("12:00:00", "Y\nRROO\nRROO\nOOOOOOOOOOO\nOOOO");
    }

    @Test
    public void shouldPrintBerlinTimeForThreeMinutesAfterSixPM() {
        verifyBerlinTime("18:03:01", "O\nRRRO\nRRRO\nOOOOOOOOOOO\nYYYO");
    }

    @Test
    public void shouldPrintBerlinTimeForAMinuteToMidnight() {
        verifyBerlinTime("23:59:59", "O\nRRRR\nRRRO\nYYRYYRYYRYY\nYYYY");
    }

    private void verifyBerlinTime(String time, String expectedTime) {
        String berlinClockTime = berlinClock.getTime(time);
        assertThat(berlinClockTime, equalTo(expectedTime));
    }
}
