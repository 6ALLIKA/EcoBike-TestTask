package ecobike.service.searchcommands;

import ecobike.model.AbstractBike;
import java.util.function.Predicate;

public class SearchByWeightOfTheBike implements SearchCommand {

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

    private Predicate<AbstractBike> less(int weight) {
        return b -> b.getWeight() < weight;
    }

    private Predicate<AbstractBike> more(int weight) {
        return b -> b.getWeight() > weight;
    }

    private Predicate<AbstractBike> between(int weight1, int weight2) {
        return b -> (Math.min(weight1, weight2)) < b.getWeight()
                && b.getWeight() < (Math.max(weight1, weight2));
    }

    private Predicate<AbstractBike> exact(int weight) {
        return b -> b.getWeight() == weight;
    }
}
