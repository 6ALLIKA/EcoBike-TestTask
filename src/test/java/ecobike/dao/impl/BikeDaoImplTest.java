package ecobike.dao.impl;

import ecobike.model.AbstractBike;
import ecobike.model.ElectricBike;
import ecobike.model.FoldingBike;
import ecobike.model.Speedelec;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class BikeDaoImplTest {
    BikeDaoImpl bikeDao = new BikeDaoImpl();

    @Test
    void createClassicBike() {
        String data = "FOLDING BIKE Benetti; 24; 27; 11400; false; rose; 1009";

        FoldingBike foldingBike = new FoldingBike();
        foldingBike.setBrand("Benetti");
        foldingBike.setWheelSize(24);
        foldingBike.setGearsCount(27);
        foldingBike.setWeight(11400);
        foldingBike.setLights(false);
        foldingBike.setColor("rose");
        foldingBike.setPrice(new BigDecimal(1009));

        assertEquals(foldingBike, bikeDao.createFoldingBike(data));
    }

    @Test
    void createEBike() {
        String data = "E-BIKE Lankeleisi; 50; 21600; false; 30000; flame; 859";

        ElectricBike electricBike = new ElectricBike();
        electricBike.setBrand("Lankeleisi");
        electricBike.setMaxSpeed(50);
        electricBike.setWeight(21600);
        electricBike.setLights(false);
        electricBike.setBatteryCapacity(30000);
        electricBike.setColor("flame");
        electricBike.setPrice(new BigDecimal(859));

        assertEquals(electricBike, bikeDao.createEBike(data));
    }

    @Test
    void createSpeedElec() {
        String data = "SPEEDELEC E-Scooter; 60; 15300; false; 14800; marine; 309";

        Speedelec speedelec = new Speedelec();
        speedelec.setBrand("E-Scooter");
        speedelec.setMaxSpeed(60);
        speedelec.setWeight(15300);
        speedelec.setLights(false);
        speedelec.setBatteryCapacity(14800);
        speedelec.setColor("marine");
        speedelec.setPrice(new BigDecimal(309));

        assertEquals(speedelec, bikeDao.createSpeedElec(data));
    }

    @Test
    public void convertToData_TRUE() {
        List<String> expected = new ArrayList<>();
        expected.add("SPEEDELEC E-Scooter; 60; 15300; false; 14800; marine; 309");
        expected.add("E-BIKE Lankeleisi; 50; 21600; false; 30000; flame; 859");
        expected.add("FOLDING BIKE Benetti; 24; 27; 11400; false; rose; 1009");

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

        List<AbstractBike> list = new ArrayList<>();
        list.add(speedelec);
        list.add(electricBike);
        list.add(foldingBike);

        assertEquals(expected, bikeDao.convertToData(list));
    }
}