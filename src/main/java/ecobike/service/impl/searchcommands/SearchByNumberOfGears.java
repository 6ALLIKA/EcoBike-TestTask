package ecobike.service.impl.searchcommands;

import ecobike.model.AbstractBike;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;

@Component
public class SearchByNumberOfGears implements SearchCommand {

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

    private Predicate<AbstractBike> showLessByParameter(int gears) {
        return b -> b.getGearsCount() < gears;
    }

    private Predicate<AbstractBike> showMoreByParameter(int gears) {
        return b -> b.getGearsCount() > gears;
    }

    private Predicate<AbstractBike> showBetweenByParameters(int gears1, int gears2) {
        return b -> (Math.min(gears1, gears2)) < b.getGearsCount()
                && b.getGearsCount() < (Math.max(gears1, gears2));
    }

    private Predicate<AbstractBike> showExactByParameter(int gears) {
        return b -> b.getGearsCount() == gears;
    }
}
