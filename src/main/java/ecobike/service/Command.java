package ecobike.service;

import ecobike.model.AbstractBike;
import java.util.List;

public interface Command {
    List<AbstractBike> execute(List<AbstractBike> list);
}
