package berlinclock;

import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CalculateTimeChecker {

    public static void check(LocalTime time, CalculateTimeStrategy strategy, List<Lamp> expectedLamps) {
        List<Lamp> lamps = strategy.calculate(time);
        assertThat(lamps, equalTo(expectedLamps));
    }
}
