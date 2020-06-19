package ecobike.service.menucommands;

import ecobike.dao.impl.BikeDaoImpl;
import ecobike.model.AbstractBike;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class SearchToolByParametersTest {
    @Autowired
    private SearchToolByParameters searchToolByParameters;
    @Autowired
    private BikeDaoImpl bikeDao;

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