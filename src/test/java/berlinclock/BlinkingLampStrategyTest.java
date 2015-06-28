package berlinclock;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BlinkingLampStrategyTest {

    private static BlinkingLampStrategy strategy;

    @BeforeClass
    public static void  setupStrategy() {
        strategy = new BlinkingLampStrategy();
    }

    @Test
    public void shouldEncodeEvenSecond() {
        verifyBlinkingLamp(LocalTime.of(0, 0, 0), Collections.singletonList(Lamp.YELLOW));
    }

    @Test
    public void shouldEncodeOddSecond() {
        verifyBlinkingLamp(LocalTime.of(12, 5, 13), Collections.singletonList(Lamp.OFF));
    }

    private void verifyBlinkingLamp(LocalTime time, List<Lamp> expectedBlinkingLamp) {
        List<Lamp> blinkingLamp = strategy.encode(time);
        assertThat(blinkingLamp, equalTo(expectedBlinkingLamp));
    }
}
