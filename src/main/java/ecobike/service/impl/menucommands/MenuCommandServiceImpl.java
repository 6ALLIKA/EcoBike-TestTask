package ecobike.service.impl.menucommands;

import ecobike.service.MenuCommandService;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuCommandServiceImpl implements MenuCommandService {
    /**
     * This class response to retrieve needed implementation for command by key
     */

    private final ShowCatalog showCatalog;
    private final AddFoldingBike addFoldingBike;
    private final AddSpeedelec addSpeedelec;
    private final AddElectricBike addElectricBike;
    private final SearchToolByParameters searchToolByParameters;
    private final WriteToFile writeToFile;
    private final StopProgramm stopProgramm;
    private Map<Integer, Command> map = new ConcurrentHashMap<>();

    @Autowired
    public MenuCommandServiceImpl(ShowCatalog showCatalog, AddFoldingBike addFoldingBike,
                                  AddSpeedelec addSpeedelec, AddElectricBike addElectricBike,
                                  SearchToolByParameters searchToolByParameters,
                                  WriteToFile writeToFile, StopProgramm stopProgramm) {

        this.showCatalog = showCatalog;
        this.addFoldingBike = addFoldingBike;
        this.addSpeedelec = addSpeedelec;
        this.addElectricBike = addElectricBike;
        this.searchToolByParameters = searchToolByParameters;
        this.writeToFile = writeToFile;
        this.stopProgramm = stopProgramm;

        map.put(1, showCatalog);
        map.put(2, addFoldingBike);
        map.put(3, addSpeedelec);
        map.put(4, addElectricBike);
        map.put(5, searchToolByParameters);
        map.put(6, writeToFile);
        map.put(7, stopProgramm);
    }

    public Command getCommand(int command) {
        return map.get(command);
    }
}
