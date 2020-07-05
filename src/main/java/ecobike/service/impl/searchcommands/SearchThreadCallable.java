package ecobike.service.impl.searchcommands;

import ecobike.model.AbstractBike;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

public class SearchThreadCallable implements Callable<Predicate<AbstractBike>> {
    private final SearchCommand searchCommand;
    private final String parameter;

    public SearchThreadCallable(SearchCommand searchCommand, String parameter) {
        this.searchCommand = searchCommand;
        this.parameter = parameter;
    }

    @Override
    public Predicate<AbstractBike> call() throws Exception {
        return searchCommand.search(parameter);
    }
}
