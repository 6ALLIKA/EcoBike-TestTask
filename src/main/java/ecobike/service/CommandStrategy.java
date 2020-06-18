package ecobike.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandStrategy {
    /**
     * This class response to retrieve needed implementation for command by key
     */

    private Map<Integer, Command> map = new ConcurrentHashMap<>();

    public CommandStrategy() {
        map.put(1, new ShowCatalog());
        map.put(2, new AddFoldingBike());
        map.put(3, new AddSpeedelec());
        map.put(4, new AddElectricBike());
        map.put(5, new SearchToolByParameters());
        map.put(6, new WriteToFile());
        map.put(7, new StopProgramm());
    }

    public Command getCommand(int command) {
        return map.get(command);
    }
}
