package ecobike.service;

import ecobike.model.AbstractBike;
import ecobike.model.Speedelec;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class AddSpeedelecTest {
    private AddSpeedelec addSpeedelec = new AddSpeedelec();

    @SneakyThrows
    @Test
    public void create_eBike_TRUE() {
        Speedelec speedelec = new Speedelec();
        speedelec.setBrand("E-Scooter");
        speedelec.setMaxSpeed(60);
        speedelec.setWeight(15300);
        speedelec.setLights(false);
        speedelec.setBatteryCapacity(14800);
        speedelec.setColor("marine");
        speedelec.setPrice(new BigDecimal(309));

        List<AbstractBike> expected = new ArrayList<>();
        expected.add(speedelec);

        List<AbstractBike> actual = new ArrayList<>();
        System.setIn(new BufferedInputStream(new FileInputStream("src/test/resources"
                + "/createSpeedelec-test.txt")));
        addSpeedelec.execute(actual);

        assertEquals(expected, actual);
    }
}
