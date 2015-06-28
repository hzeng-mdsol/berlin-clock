package berlinclock;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Collections;

public class BlinkingLampStrategyTest {
    private static BlinkingLampStrategy blinkingLampStrategy;

    @BeforeClass
    public static void  setupStrategy() {
        blinkingLampStrategy = new BlinkingLampStrategy();
    }

    @Test
    public void shouldCalculateBlinkingLampWithEvenSecond() {
        CalculateTimeChecker.check(LocalTime.of(0, 0, 0), blinkingLampStrategy, Collections.singletonList(Lamp.YELLOW));
    }

    @Test
    public void shouldCalculateBlinkingLampWithOddSecond() {
        CalculateTimeChecker.check(LocalTime.of(12, 5, 13), blinkingLampStrategy, Collections.singletonList(Lamp.OFF));
    }
}
