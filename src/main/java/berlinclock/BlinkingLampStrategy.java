package berlinclock;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BlinkingLampStrategy implements EncodeStrategy {
    @Override
    public List<Lamp> encode(LocalTime time) {
        return Stream.of(time.getSecond()).map(seconds -> seconds % 2 == 0 ? Lamp.YELLOW : Lamp.OFF).collect(Collectors.toList());
    }
}
