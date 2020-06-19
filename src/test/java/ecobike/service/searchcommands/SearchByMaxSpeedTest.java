package ecobike.service.searchcommands;

import ecobike.config.AppConfig;
import ecobike.model.AbstractBike;
import ecobike.model.ElectricBike;
import ecobike.model.Speedelec;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class SearchByMaxSpeedTest {
    private static SearchByMaxSpeed predicate;
    private static List<AbstractBike> list = new ArrayList<>();
    private int speed = 40;
    private int speed1 = 50;
    private int speed2 = 150;

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
        electricBike2.setBatteryCapacity(30000);
        electricBike2.setColor("flame");
        electricBike2.setPrice(new BigDecimal(859));

        Speedelec speedelec2 = new Speedelec();
        speedelec2.setBrand("E-Scooter");
        speedelec2.setMaxSpeed(40);
        speedelec2.setWeight(15300);
        speedelec2.setLights(false);
        speedelec2.setBatteryCapacity(14800);
        speedelec2.setColor("marine");
        speedelec2.setPrice(new BigDecimal(309));

        list.add(speedelec);
        list.add(electricBike);
        list.add(speedelec2);
        list.add(electricBike2);

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        predicate = context.getBean(SearchByMaxSpeed.class);
    }


    @Test
    void search_whenLess_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getWeight() < speed;
        String parameter = "less 40";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_whenMore_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getMaxSpeed() > speed;
        String parameter = "more 40";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_whenBetween_TRUE() {
        Predicate<AbstractBike> expected = b -> (Math.min(speed1, speed2)) < b.getMaxSpeed()
                && b.getMaxSpeed() < (Math.max(speed1, speed2));
        String parameter = "between 50 150";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_whenExact_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getMaxSpeed() == speed;
        String parameter = "40";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }
}
