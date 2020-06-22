package ecobike.service.impl.menucommands;

import ecobike.model.AbstractBike;
import ecobike.model.ElectricBike;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class AddElectricBikeTest {
    private AddElectricBike addElectricBike = new AddElectricBike();

    @SneakyThrows
    @Test
    public void create_eBike_TRUE() {
        ElectricBike electricBike = new ElectricBike();
        electricBike.setBrand("Lankeleisi");
        electricBike.setMaxSpeed(50);
        electricBike.setWeight(21600);
        electricBike.setLights(false);
        electricBike.setBatteryCapacity(30000);
        electricBike.setColor("flame");
        electricBike.setPrice(new BigDecimal(859));

        List<AbstractBike> expected = new ArrayList<>();
        expected.add(electricBike);

        List<AbstractBike> actual = new ArrayList<>();
        System.setIn(new BufferedInputStream(new FileInputStream("src/test/resources"
                + "/createEBIKE-test.txt")));
        addElectricBike.execute(actual);

        assertEquals(expected, actual);
    }
}
