package berlinclock;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class BlinkingLampStrategy implements CalculateTimeStrategy {
    @Override
    public List<Lamp> calculate(LocalTime time) {
        return Collections.singletonList(time.getSecond() % 2 == 0? Lamp.YELLOW : Lamp.OFF);
    }
}
