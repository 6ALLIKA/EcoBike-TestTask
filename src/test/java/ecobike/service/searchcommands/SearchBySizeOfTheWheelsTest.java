package ecobike.service.searchcommands;

import ecobike.model.AbstractBike;
import ecobike.model.FoldingBike;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SearchBySizeOfTheWheelsTest {
    private SearchBySizeOfTheWheels predicate = new SearchBySizeOfTheWheels();
    private static List<AbstractBike> list = new ArrayList<>();
    private int size = 30;
    private int size1 = 10;
    private int size2 = 50;

    @BeforeAll
    static void start() {
        FoldingBike foldingBike1 = new FoldingBike();
        foldingBike1.setBrand("Benetti");
        foldingBike1.setWheelSize(24);
        foldingBike1.setGearsQuantity(27);
        foldingBike1.setWeight(11400);
        foldingBike1.setLights(false);
        foldingBike1.setColor("rose");
        foldingBike1.setPrice(new BigDecimal(1009));

        FoldingBike foldingBike2 = new FoldingBike();
        foldingBike2.setBrand("Benetti");
        foldingBike2.setWheelSize(40);
        foldingBike2.setGearsQuantity(27);
        foldingBike2.setWeight(11400);
        foldingBike2.setLights(false);
        foldingBike2.setColor("rose");
        foldingBike2.setPrice(new BigDecimal(1009));

        FoldingBike foldingBike3 = new FoldingBike();
        foldingBike3.setBrand("Benetti");
        foldingBike3.setWheelSize(20);
        foldingBike3.setGearsQuantity(27);
        foldingBike3.setWeight(11400);
        foldingBike3.setLights(false);
        foldingBike3.setColor("rose");
        foldingBike3.setPrice(new BigDecimal(1009));

        list.add(foldingBike1);
        list.add(foldingBike2);
        list.add(foldingBike3);
    }


    @Test
    void search_whenLess_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getWheelSize() < size;
        String parameter = "less 30";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_whenMore_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getWheelSize() > size;
        String parameter = "more 30";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_whenBetween_TRUE() {
        Predicate<AbstractBike> expected = b -> (Math.min(size1, size2)) < b.getWheelSize()
                && b.getWheelSize() < (Math.max(size1, size2));
        String parameter = "between 10 50";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }

    @Test
    void search_whenExact_TRUE() {
        Predicate<AbstractBike> expected = b -> b.getWheelSize() == size;
        String parameter = "30";
        assertEquals(list.stream().filter(expected).collect(Collectors.toList()),
                list.stream().filter(predicate.search(parameter)).collect(Collectors.toList()));
    }
}
