package ecobike.service.impl.searchcommands;

import ecobike.model.AbstractBike;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;

@Component
public class SearchByBatteryCapacity implements SearchCommand {

    @Override
    public Predicate<AbstractBike> search(String parameter) {
        if (parameter.charAt(0) == 'l') {
            return showLessByParameter(Integer.parseInt(parameter.split(" +")[1]));
        }
        if (parameter.charAt(0) == 'm') {
            return showMoreByParameter(Integer.parseInt(parameter.split(" +")[1]));
        }
        if (parameter.charAt(0) == 'b') {
            String[] data = parameter.split(" +");
            return showBetweenByParameters(Integer.parseInt(data[1]),
                    Integer.parseInt(data[2]));
        }
        return showExactByParameter(Integer.parseInt(parameter));
    }

    private Predicate<AbstractBike> showLessByParameter(int capacity) {
        return b -> b.getBatteryCapacity() < capacity;
    }

    private Predicate<AbstractBike> showMoreByParameter(int capacity) {
        return b -> b.getBatteryCapacity() > capacity;
    }

    private Predicate<AbstractBike> showBetweenByParameters(int capacity1, int capacity2) {
        return b -> (Math.min(capacity1, capacity2)) < b.getBatteryCapacity()
                && b.getBatteryCapacity() < (Math.max(capacity1, capacity2));
    }

    private Predicate<AbstractBike> showExactByParameter(int capacity) {
        return b -> b.getBatteryCapacity() == capacity;
    }
}
