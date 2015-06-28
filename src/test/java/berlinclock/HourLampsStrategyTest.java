package berlinclock;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HourLampsStrategyTest {

    private static HourLampsStrategy fiveHourLampsStrategy;
    private static HourLampsStrategy oneHourLampsStrategy;

    @BeforeClass
    public static void setupStrategy() {
        fiveHourLampsStrategy = new HourLampsStrategy(5);
        oneHourLampsStrategy = new HourLampsStrategy(1);
    }

    @Test
    public void shouldEncodeAllFiveHourLampsAsOff() {
        verifyHoursLamps(LocalTime.of(0, 0, 0), fiveHourLampsStrategy, Arrays.asList(Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF));
    }

    @Test
    public void shouldEncodeAllFiveHourLampsAsRed() {
        verifyHoursLamps(LocalTime.of(20, 0, 0), fiveHourLampsStrategy, Arrays.asList(Lamp.RED, Lamp.RED, Lamp.RED, Lamp.RED));
    }

    @Test
    public void shouldEncodeSomeFiveHourLampsAsRed() {
        verifyHoursLamps(LocalTime.of(15, 23, 6), fiveHourLampsStrategy, Arrays.asList(Lamp.RED, Lamp.RED, Lamp.RED, Lamp.OFF));
    }

    @Test
    public void shouldEncodeAllOneHourLampsAsOff() {
        verifyHoursLamps(LocalTime.of(10, 23, 46), oneHourLampsStrategy, Arrays.asList(Lamp.OFF, Lamp.OFF, Lamp.OFF, Lamp.OFF));
    }

    @Test
    public void shouldEncodeAllOneHourLampsAsRed() {
        verifyHoursLamps(LocalTime.of(14, 13, 47), oneHourLampsStrategy, Arrays.asList(Lamp.RED, Lamp.RED, Lamp.RED, Lamp.RED));
    }

    @Test
    public void shouldEncodeAllOneHourLampsAsRedAbove() {
        verifyHoursLamps(LocalTime.of(16, 23, 46), oneHourLampsStrategy, Arrays.asList(Lamp.RED, Lamp.OFF, Lamp.OFF, Lamp.OFF));
    }

    @Test
    public void shouldEncodeSomeOneHourLampsAsRedBelow() {
        verifyHoursLamps(LocalTime.of(13, 12, 9), oneHourLampsStrategy, Arrays.asList(Lamp.RED, Lamp.RED, Lamp.RED, Lamp.OFF));
    }

    private void verifyHoursLamps(LocalTime time, EncodeStrategy strategy, List<Lamp> expectedLamps) {
        List<Lamp> redLamps = strategy.encode(time);
        assertThat(redLamps, equalTo(expectedLamps));
    }
}
