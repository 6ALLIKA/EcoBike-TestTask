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

class SearchByColorTest {
    private static SearchByColor predicate;
    private static List<AbstractBike> list = new ArrayList<>();
    private String rose = "rose";
    private String flame = "flame";
    private String marine = "marine";

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
        predicate = context.getBean(SearchByColor.class);
    }


    @Test
    void search_marine_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getColor().equalsIgnoreCase(marine);
        String parameter = "maRIne";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_flame_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getColor().equalsIgnoreCase(flame);
        String parameter = "FlAmE";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_rose_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getColor().equalsIgnoreCase(rose);
        String parameter = "ROSe";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }
}
