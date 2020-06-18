package ecobike.service.searchcommands;

import ecobike.model.AbstractBike;
import java.util.function.Predicate;

public class SearchByAvailabilityOfLights implements SearchCommand {

    @Override
    public Predicate<AbstractBike> search(String parameter) {
        return Boolean.parseBoolean(parameter) ? isTrue() : isFalse();
    }

    private Predicate<AbstractBike> isTrue() {
        return AbstractBike::isLights;
    }

    private Predicate<AbstractBike> isFalse() {
        return b -> !b.isLights();
    }
}
