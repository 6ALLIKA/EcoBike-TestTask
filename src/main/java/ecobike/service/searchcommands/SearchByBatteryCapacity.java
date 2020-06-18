package ecobike.service.searchcommands;

import ecobike.model.AbstractBike;
import java.util.function.Predicate;

public class SearchByBatteryCapacity implements SearchCommand {

    @Override
    public Predicate<AbstractBike> search(String parameter) {
        if (parameter.charAt(0) == 'l') {
            return less(Integer.parseInt(parameter.split(" +")[1]));
        }
        if (parameter.charAt(0) == 'm') {
            return more(Integer.parseInt(parameter.split(" +")[1]));
        }
        if (parameter.charAt(0) == 'b') {
            String[] data = parameter.split(" +");
            return between(Integer.parseInt(data[1]),
                    Integer.parseInt(data[2]));
        }
        return exact(Integer.parseInt(parameter));
    }

    private Predicate<AbstractBike> less(int capacity) {
        return b -> b.getBatteryCapacity() < capacity;
    }

    private Predicate<AbstractBike> more(int capacity) {
        return b -> b.getBatteryCapacity() > capacity;
    }

    private Predicate<AbstractBike> between(int capacity1, int capacity2) {
        return b -> (Math.min(capacity1, capacity2)) < b.getBatteryCapacity()
                && b.getBatteryCapacity() < (Math.max(capacity1, capacity2));
    }

    private Predicate<AbstractBike> exact(int capacity) {
        return b -> b.getBatteryCapacity() == capacity;
    }
}
