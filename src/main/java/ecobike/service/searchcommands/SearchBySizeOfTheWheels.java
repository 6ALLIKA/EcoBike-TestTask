package ecobike.service.searchcommands;

import ecobike.model.AbstractBike;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;

@Component
public class SearchBySizeOfTheWheels implements SearchCommand {

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

    private Predicate<AbstractBike> showLessByParameter(int size) {
        return b -> b.getWheelSize() < size;
    }

    private Predicate<AbstractBike> showMoreByParameter(int size) {
        return b -> b.getWheelSize() > size;
    }

    private Predicate<AbstractBike> showBetweenByParameters(int size1, int size2) {
        return b -> (Math.min(size1, size2)) < b.getWheelSize()
                && b.getWheelSize() < (Math.max(size1, size2));
    }

    private Predicate<AbstractBike> showExactByParameter(int size) {
        return b -> b.getWheelSize() == size;
    }
}
