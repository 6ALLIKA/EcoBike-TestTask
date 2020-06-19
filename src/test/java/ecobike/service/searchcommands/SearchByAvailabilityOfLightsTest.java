package ecobike.service.searchcommands;

import ecobike.config.AppConfig;
import ecobike.model.AbstractBike;
import ecobike.model.ElectricBike;
import ecobike.model.FoldingBike;
import ecobike.model.Speedelec;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class SearchByAvailabilityOfLightsTest {
    private static SearchByAvailabilityOfLights predicate;
    private static List<AbstractBike> list = new ArrayList<>();

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

        FoldingBike foldingBike1 = new FoldingBike();
        foldingBike1.setBrand("Benetti");
        foldingBike1.setWheelSize(24);
        foldingBike1.setGearsCount(27);
        foldingBike1.setWeight(11400);
        foldingBike1.setLights(true);
        foldingBike1.setColor("rose");
        foldingBike1.setPrice(new BigDecimal(1009));

        FoldingBike foldingBike2 = new FoldingBike();
        foldingBike2.setBrand("Benetti");
        foldingBike2.setWheelSize(40);
        foldingBike2.setGearsCount(20);
        foldingBike2.setWeight(11400);
        foldingBike2.setLights(true);
        foldingBike2.setColor("rose");
        foldingBike2.setPrice(new BigDecimal(1009));

        FoldingBike foldingBike3 = new FoldingBike();
        foldingBike3.setBrand("Benetti");
        foldingBike3.setWheelSize(20);
        foldingBike3.setGearsCount(40);
        foldingBike3.setWeight(11400);
        foldingBike3.setLights(true);
        foldingBike3.setColor("rose");
        foldingBike3.setPrice(new BigDecimal(1009));

        list.add(foldingBike1);
        list.add(foldingBike2);
        list.add(foldingBike3);
        list.add(speedelec);
        list.add(electricBike);
        list.add(speedelec2);
        list.add(electricBike2);

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        predicate = context.getBean(SearchByAvailabilityOfLights.class);
    }


    @Test
    void search_isFalse_TRUE() {
        Predicate<AbstractBike> expected = b -> !b.isLights();
        String parameter = "false";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_isTrue_TRUE() {
        Predicate<AbstractBike> expected = AbstractBike::isLights;
        String parameter = "true";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

}