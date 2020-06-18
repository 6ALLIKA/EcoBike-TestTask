package ecobike.service.searchcommands;

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

class SearchByPriceTest {
    private SearchByPrice predicate = new SearchByPrice();
    private static List<AbstractBike> list = new ArrayList<>();
    private int price = 800;
    private int price1 = 500;
    private int price2 = 1000;

    @BeforeAll
    static void start() {
        FoldingBike foldingBike =new FoldingBike();
        foldingBike.setBrand("Benetti");
        foldingBike.setWheelSize(24);
        foldingBike.setGearsQuantity(27);
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
    }


    @Test
    void search_whenLess_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getPrice().intValue() < price;
        String parameter = "less 800";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_whenMore_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getPrice().intValue() > price;
        String parameter = "more 800";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_whenBetween_TRUE() {
        Predicate<AbstractBike> expected = b -> (Math.min(price1, price2)) < b.getPrice().intValue()
                && b.getPrice().intValue() < (Math.max(price1, price2));
        String parameter = "between 500 1000";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_whenExact_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getPrice().intValue() == price;
        String parameter = "800";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }
}
