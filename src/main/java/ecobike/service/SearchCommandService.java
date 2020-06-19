package ecobike.service;

import ecobike.service.searchcommands.SearchCommand;

public interface SearchCommandService {
    SearchCommand getCommand(int command);
}
