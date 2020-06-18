package ecobike.service.searchcommands;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SearchCommandStrategy {
    /**
     * This class response to retrieve needed implementation for command by key
     */

    private Map<Integer, SearchCommand> map = new ConcurrentHashMap<>();

    public SearchCommandStrategy() {
        map.put(1, new SearchByType());
        map.put(2, new SearchByBrand());
        map.put(3, new SearchByWeightOfTheBike());
        map.put(4, new SearchByColor());
        map.put(5, new SearchByAvailabilityOfLights());
        map.put(6, new SearchByPrice());
        map.put(7, new SearchByNumberOfGears());
        map.put(8, new SearchBySizeOfTheWheels());
        map.put(9, new SearchByMaxSpeed());
        map.put(10, new SearchByBatteryCapacity());
    }

    public SearchCommand getCommand(int command) {
        return map.get(command);
    }
}
