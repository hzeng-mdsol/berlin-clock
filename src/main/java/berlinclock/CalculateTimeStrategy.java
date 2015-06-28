package berlinclock;

import java.time.LocalTime;
import java.util.List;

@FunctionalInterface
public interface CalculateTimeStrategy {
    List<Lamp> calculate(LocalTime time);
}
