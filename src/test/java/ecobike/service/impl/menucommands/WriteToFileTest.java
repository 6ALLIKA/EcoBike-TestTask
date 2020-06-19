package ecobike.service.impl.menucommands;

import ecobike.config.AppConfig;
import ecobike.model.AbstractBike;
import ecobike.model.ElectricBike;
import ecobike.model.FoldingBike;
import ecobike.model.Speedelec;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
class WriteToFileTest {
    @Autowired
    private WriteToFile writeToFile;

    @SneakyThrows
    @Test
    public void check_execute_TRUE() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        writeToFile = context.getBean(WriteToFile.class);

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

        List<AbstractBike> products = new ArrayList<>();
        products.add(speedelec);
        products.add(electricBike);
        products.add(foldingBike);
        System.setIn(new BufferedInputStream(new FileInputStream("src/test/resources"
                + "/WriteToFile-test.txt")));
        writeToFile.execute(products);

        List<String> expected = new ArrayList<>();
        expected.add("SPEEDELEC E-Scooter; 60; 15300; false; 14800; marine; 309");
        expected.add("E-BIKE Lankeleisi; 50; 21600; false; 30000; flame; 859");
        expected.add("FOLDING BIKE Benetti; 24; 27; 11400; false; rose; 1009");

        List<String> actual = Files.readAllLines(Paths.get("src/test/resources/ecobikeWRITTEN.txt"));

        assertEquals(expected, actual);
    }

}
