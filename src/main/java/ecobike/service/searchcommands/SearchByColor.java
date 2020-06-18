package ecobike.service.searchcommands;

import ecobike.model.AbstractBike;
import java.util.function.Predicate;

public class SearchByColor implements SearchCommand {

    @Override
    public Predicate<AbstractBike> search(String parameter) {
        return b -> b.getColor().equalsIgnoreCase(parameter);
    }
}
