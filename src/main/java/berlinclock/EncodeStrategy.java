package berlinclock;

import java.time.LocalTime;
import java.util.List;

@FunctionalInterface
public interface EncodeStrategy {
    List<Lamp> encode(LocalTime time);
}
