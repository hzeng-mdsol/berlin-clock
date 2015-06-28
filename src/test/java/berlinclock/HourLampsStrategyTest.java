package berlinclock;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalTime;
import java.util.Arrays;

public class HourLampsStrategyTest {
    private static HourLampsStrategy fiveHourLampsStrategy;
    private static HourLampsStrategy oneHourLampsStrategy;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @BeforeClass
    public static void setupStrategies() {
        fiveHourLampsStrategy = new HourLampsStrategy(5);
        oneHourLampsStrategy = new HourLampsStrategy(1);
    }

    @Test
    public void shouldCalculateAllFiveHourLampsAsOff() {
        CalculateTimeChecker.check(LocalTime.of(0, 0, 0), fiveHourLampsStrategy, Arrays.asList(Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF));
    }

    @Test
    public void shouldCalculateAllFiveHourLampsAsRed() {
        CalculateTimeChecker.check(LocalTime.of(20, 0, 0), fiveHourLampsStrategy, Arrays.asList(Lamp.RED, Lamp.RED, Lamp.RED, Lamp.RED));
    }

    @Test
    public void shouldCalculateSomeFiveHourLampsAsRed() {
        CalculateTimeChecker.check(LocalTime.of(15, 23, 6), fiveHourLampsStrategy, Arrays.asList(Lamp.RED, Lamp.RED, Lamp.RED, Lamp.OFF));
    }

    @Test
    public void shouldCalculateAllOneHourLampsAsOff() {
        CalculateTimeChecker.check(LocalTime.of(10, 23, 46), oneHourLampsStrategy, Arrays.asList(Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF));
    }

    @Test
    public void shouldCalculateAllOneHourLampsAsRed() {
        CalculateTimeChecker.check(LocalTime.of(14, 13, 47), oneHourLampsStrategy, Arrays.asList(Lamp.RED, Lamp.RED, Lamp.RED, Lamp.RED));
    }

    @Test
    public void shouldCalculateAllOneHourLampsAsRedAbove() {
        CalculateTimeChecker.check(LocalTime.of(16, 23, 46), oneHourLampsStrategy, Arrays.asList(Lamp.RED, Lamp.OFF, Lamp.OFF, Lamp.OFF));
    }

    @Test
    public void shouldCalculateSomeOneHourLampsAsRedBelow() {
        CalculateTimeChecker.check(LocalTime.of(13, 12, 9), oneHourLampsStrategy, Arrays.asList(Lamp.RED, Lamp.RED, Lamp.RED, Lamp.OFF));
    }

    @Test
    public void shouldThrowExceptionWithUnexpectedUnit() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Unexpected unit '3' for hour lamps.");

        new HourLampsStrategy(3);
    }
}
