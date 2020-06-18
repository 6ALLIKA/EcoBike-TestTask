package ecobike.service.searchcommands;

import ecobike.model.AbstractBike;
import java.util.function.Predicate;

public class SearchByBrand implements SearchCommand {

    @Override
    public Predicate<AbstractBike> search(String parameter) {
        return b -> b.getBrand().equalsIgnoreCase(parameter);
    }
}
