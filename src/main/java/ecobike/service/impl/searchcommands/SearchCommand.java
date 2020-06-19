package ecobike.service.impl.searchcommands;

import ecobike.model.AbstractBike;
import java.util.function.Predicate;

public interface SearchCommand {
    Predicate<AbstractBike> search(String parameter);
}
