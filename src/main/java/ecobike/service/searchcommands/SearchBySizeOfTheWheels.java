package ecobike.service.searchcommands;

import ecobike.model.AbstractBike;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;

@Component
public class SearchBySizeOfTheWheels implements SearchCommand {

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

    private Predicate<AbstractBike> less(int size) {
        return b -> b.getWheelSize() < size;
    }

    private Predicate<AbstractBike> more(int size) {
        return b -> b.getWheelSize() > size;
    }

    private Predicate<AbstractBike> between(int size1, int size2) {
        return b -> (Math.min(size1, size2)) < b.getWheelSize()
                && b.getWheelSize() < (Math.max(size1, size2));
    }

    private Predicate<AbstractBike> exact(int size) {
        return b -> b.getWheelSize() == size;
    }
}
