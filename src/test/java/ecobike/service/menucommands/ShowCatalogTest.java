package ecobike.service.menucommands;

import ecobike.model.AbstractBike;
import ecobike.model.ElectricBike;
import ecobike.model.FoldingBike;
import ecobike.model.Speedelec;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ShowCatalogTest {
    private ShowCatalog showCatalog = new ShowCatalog();

    @SneakyThrows
    @Test
    public void check_execute_TRUE() {
        Speedelec speedelec = new Speedelec();
        speedelec.setBrand("E-Scooter");
        speedelec.setMaxSpeed(60);
        speedelec.setWeight(15300);
        speedelec.setLights(false);
        speedelec.setBatteryCapacity(14800);
        speedelec.setColor("marine");
        speedelec.setPrice(new BigDecimal(309));

        ElectricBike electricBike = new ElectricBike();
        electricBike.setBrand("Lankeleisi");
        electricBike.setMaxSpeed(50);
        electricBike.setWeight(21600);
        electricBike.setLights(false);
        electricBike.setBatteryCapacity(30000);
        electricBike.setColor("flame");
        electricBike.setPrice(new BigDecimal(859));

        FoldingBike foldingBike = new FoldingBike();
        foldingBike.setBrand("Benetti");
        foldingBike.setWheelSize(24);
        foldingBike.setGearsCount(27);
        foldingBike.setWeight(11400);
        foldingBike.setLights(false);
        foldingBike.setColor("rose");
        foldingBike.setPrice(new BigDecimal(1009));

        List<AbstractBike> expected = new ArrayList<>();

        expected.add(speedelec);
        expected.add(electricBike);
        expected.add(foldingBike);

        List<AbstractBike> actual = new ArrayList<>();
        System.setIn(new BufferedInputStream(new FileInputStream("src/test/resources"
                + "/showCatalog-test.txt")));
        showCatalog.setPath("src/test/resources/ecobikeFIXED.txt");
        assertEquals(expected.stream().sorted().collect(Collectors.toList()),
                showCatalog.execute(actual));
    }
}
