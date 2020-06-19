package ecobike.service;

import ecobike.service.menucommands.Command;

public interface MenuCommandService {

    Command getCommand(int command);
}
