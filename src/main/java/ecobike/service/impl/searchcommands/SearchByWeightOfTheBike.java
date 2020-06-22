package ecobike.service.impl.searchcommands;

import ecobike.model.AbstractBike;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;

@Component
public class SearchByWeightOfTheBike implements SearchCommand {

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

    private Predicate<AbstractBike> showLessByParameter(int weight) {
        return b -> b.getWeight() < weight;
    }

    private Predicate<AbstractBike> showMoreByParameter(int weight) {
        return b -> b.getWeight() > weight;
    }

    private Predicate<AbstractBike> showBetweenByParameters(int weight1, int weight2) {
        return b -> (Math.min(weight1, weight2)) < b.getWeight()
                && b.getWeight() < (Math.max(weight1, weight2));
    }

    private Predicate<AbstractBike> showExactByParameter(int weight) {
        return b -> b.getWeight() == weight;
    }
}
