package ecobike.service.impl.searchcommands;

import ecobike.service.SearchCommandService;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchCommandServiceImpl implements SearchCommandService {
    /**
     * This class response to retrieve needed implementation for command by key
     */
    private final SearchByType searchByType;
    private final SearchByBrand searchByBrand;
    private final SearchByWeightOfTheBike searchByWeightOfTheBike;
    private final SearchByColor searchByColor;
    private final SearchByAvailabilityOfLights searchByAvailabilityOfLights;
    private final SearchByPrice searchByPrice;
    private final SearchByNumberOfGears searchByNumberOfGears;
    private final SearchBySizeOfTheWheels searchBySizeOfTheWheels;
    private final SearchByMaxSpeed searchByMaxSpeed;
    private final SearchByBatteryCapacity searchByBatteryCapacity;

    private Map<Integer, SearchCommand> map = new ConcurrentHashMap<>();

    @Autowired
    public SearchCommandServiceImpl(SearchByType searchByType, SearchByBrand searchByBrand,
                                    SearchByWeightOfTheBike searchByWeightOfTheBike,
                                    SearchByColor searchByColor,
                                    SearchByAvailabilityOfLights searchByAvailabilityOfLights,
                                    SearchByPrice searchByPrice,
                                    SearchByNumberOfGears searchByNumberOfGears,
                                    SearchBySizeOfTheWheels searchBySizeOfTheWheels,
                                    SearchByMaxSpeed searchByMaxSpeed,
                                    SearchByBatteryCapacity searchByBatteryCapacity) {

        this.searchByType = searchByType;
        this.searchByBrand = searchByBrand;
        this.searchByWeightOfTheBike = searchByWeightOfTheBike;
        this.searchByColor = searchByColor;
        this.searchByAvailabilityOfLights = searchByAvailabilityOfLights;
        this.searchByPrice = searchByPrice;
        this.searchByNumberOfGears = searchByNumberOfGears;
        this.searchBySizeOfTheWheels = searchBySizeOfTheWheels;
        this.searchByMaxSpeed = searchByMaxSpeed;
        this.searchByBatteryCapacity = searchByBatteryCapacity;

        map.put(1, searchByType);
        map.put(2, searchByBrand);
        map.put(3, searchByBrand);
        map.put(4, searchByColor);
        map.put(5, searchByAvailabilityOfLights);
        map.put(6, searchByPrice);
        map.put(7, searchByNumberOfGears);
        map.put(8, searchBySizeOfTheWheels);
        map.put(9, searchByMaxSpeed);
        map.put(10, searchByBatteryCapacity);

    }

    public SearchCommand getCommand(int command) {
        return map.get(command);
    }
}
