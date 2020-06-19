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
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class SearchByWeightOfTheBikeTest {
    private static SearchByWeightOfTheBike predicate;
    private static List<AbstractBike> list = new ArrayList<>();
    private int weight = 20000;
    private int weight1 = 15000;
    private int weight2 = 25000;

    @BeforeAll
    static void start() {
        FoldingBike foldingBike =new FoldingBike();
        foldingBike.setBrand("Benetti");
        foldingBike.setWheelSize(24);
        foldingBike.setGearsCount(27);
        foldingBike.setWeight(11400);
        foldingBike.setLights(false);
        foldingBike.setColor("rose");
        foldingBike.setPrice(new BigDecimal(1009));

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

        list.add(speedelec);
        list.add(electricBike);
        list.add(foldingBike);

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        predicate = context.getBean(SearchByWeightOfTheBike.class);

    }


    @Test
    void search_whenLess_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getWeight() < weight;
        String parameter = "less 20000";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_whenMore_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getWeight() > weight;
        String parameter = "more 20000";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_whenBetween_TRUE() {
        Predicate<AbstractBike> expected = b -> (Math.min(weight1, weight2)) < b.getWeight() && b.getWeight() < (Math.max(weight1, weight2));
        String parameter = "between 15000 25000";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_whenExact_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getWeight() == weight;
        String parameter = "20000";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }
}
