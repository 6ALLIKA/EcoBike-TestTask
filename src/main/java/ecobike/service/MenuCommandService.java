package ecobike.service;

import ecobike.service.impl.menucommands.Command;

public interface MenuCommandService {

    Command getCommand(int command);
}
