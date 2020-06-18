package ecobike.service;

import ecobike.dao.impl.BikeDaoImpl;
import ecobike.model.AbstractBike;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SearchToolByParametersTest {
    private SearchToolByParameters searchToolByParameters = new SearchToolByParameters();
    private BikeDaoImpl bikeDao = new BikeDaoImpl();

    @SneakyThrows
    @Test
    public void check_execute_TRUE() {
        List<AbstractBike> expected = new ArrayList<>();
        bikeDao.getAll("src/main/resources/ecobike.txt", expected);

        List<AbstractBike> actual = new ArrayList<>();
        System.setIn(new BufferedInputStream(new FileInputStream("src/test/resources"
                + "/searchByFilters-test.txt")));

        assertEquals(expected, searchToolByParameters.execute(actual));
    }
}