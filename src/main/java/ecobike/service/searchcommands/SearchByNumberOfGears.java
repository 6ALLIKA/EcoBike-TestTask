package ecobike.service.searchcommands;

import ecobike.model.AbstractBike;
import java.util.function.Predicate;

public class SearchByNumberOfGears implements SearchCommand {

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

    private Predicate<AbstractBike> less(int gears) {
        return b -> b.getGearsQuantity() < gears;
    }

    private Predicate<AbstractBike> more(int gears) {
        return b -> b.getGearsQuantity() > gears;
    }

    private Predicate<AbstractBike> between(int gears1, int gears2) {
        return b -> (Math.min(gears1, gears2)) < b.getGearsQuantity()
                && b.getGearsQuantity() < (Math.max(gears1, gears2));
    }

    private Predicate<AbstractBike> exact(int gears) {
        return b -> b.getGearsQuantity() == gears;
    }
}
