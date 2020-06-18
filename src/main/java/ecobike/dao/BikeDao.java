package ecobike.dao;

import ecobike.model.AbstractBike;
import java.util.List;

public interface BikeDao {

    List<String> addAll(String path, List<AbstractBike> unsavedProducts);

    List<AbstractBike> getAll(String path, List<AbstractBike> catalog);
}
