package ecobike.service.impl.menucommands;

import ecobike.model.AbstractBike;
import ecobike.model.FoldingBike;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class AddFoldingBikeTest {
    private AddFoldingBike addFoldingBike = new AddFoldingBike();

    @SneakyThrows
    @Test
    public void create_foldingBike_TRUE() {
        FoldingBike foldingBike = new FoldingBike();
        foldingBike.setBrand("Benetti");
        foldingBike.setWheelSize(24);
        foldingBike.setGearsCount(27);
        foldingBike.setWeight(11400);
        foldingBike.setLights(false);
        foldingBike.setColor("rose");
        foldingBike.setPrice(new BigDecimal(1009));

        List<AbstractBike> expected = new ArrayList<>();
        expected.add(foldingBike);

        List<AbstractBike> actual = new ArrayList<>();
        System.setIn(new BufferedInputStream(new FileInputStream("src/test/resources/"
                + "createFOLDINGBIKE-test.txt")));
        addFoldingBike.execute(actual, "path");

        assertEquals(expected, actual);
    }
}
