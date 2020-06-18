package ecobike.service.searchcommands;

import ecobike.model.AbstractBike;
import ecobike.model.ElectricBike;
import ecobike.model.Speedelec;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SearchByBatteryCapacityTest {
    private SearchByBatteryCapacity predicate = new SearchByBatteryCapacity();
    private static List<AbstractBike> list = new ArrayList<>();
    private int battery = 16000;
    private int battery1 = 11000;
    private int battery2 = 40000;

    @BeforeAll
    static void start() {
        ElectricBike electricBike = new ElectricBike();
        electricBike.setBrand("Lankeleisi");
        electricBike.setMaxSpeed(50);
        electricBike.setWeight(21600);
        electricBike.setLights(false);
        electricBike.setBatteryCapacity(30000);
        electricBike.setColor("flame");
        electricBike.setPrice(new BigDecimal(859));

        Speedelec speedelec = new Speedelec();
        speedelec.setBrand("E-Scooter");
        speedelec.setMaxSpeed(60);
        speedelec.setWeight(15300);
        speedelec.setLights(false);
        speedelec.setBatteryCapacity(14800);
        speedelec.setColor("marine");
        speedelec.setPrice(new BigDecimal(309));

        ElectricBike electricBike2 = new ElectricBike();
        electricBike2.setBrand("Lankeleisi");
        electricBike2.setMaxSpeed(100);
        electricBike2.setWeight(21600);
        electricBike2.setLights(false);
        electricBike2.setBatteryCapacity(20000);
        electricBike2.setColor("flame");
        electricBike2.setPrice(new BigDecimal(859));

        Speedelec speedelec2 = new Speedelec();
        speedelec2.setBrand("E-Scooter");
        speedelec2.setMaxSpeed(40);
        speedelec2.setWeight(15300);
        speedelec2.setLights(false);
        speedelec2.setBatteryCapacity(10000);
        speedelec2.setColor("marine");
        speedelec2.setPrice(new BigDecimal(309));

        list.add(speedelec);
        list.add(electricBike);
        list.add(speedelec2);
        list.add(electricBike2);
    }


    @Test
    void search_whenLess_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getWeight() < battery;
        String parameter = "less 16000";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_whenMore_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getBatteryCapacity() > battery;
        String parameter = "more 16000";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_whenBetween_TRUE() {
        Predicate<AbstractBike> expected = b -> (Math.min(battery1, battery2)) < b.getBatteryCapacity()
                && b.getBatteryCapacity() < (Math.max(battery1, battery2));
        String parameter = "between 11000 40000";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_whenExact_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getBatteryCapacity() == battery;
        String parameter = "16000";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }
}
