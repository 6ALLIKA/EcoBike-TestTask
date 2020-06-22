package ecobike.service;

import ecobike.service.impl.searchcommands.SearchCommand;

public interface SearchCommandService {
    SearchCommand getCommand(int command);
}
