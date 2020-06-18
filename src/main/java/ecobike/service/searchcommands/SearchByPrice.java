package ecobike.service.searchcommands;

import ecobike.model.AbstractBike;
import java.math.BigDecimal;
import java.util.function.Predicate;

public class SearchByPrice implements SearchCommand {

    @Override
    public Predicate<AbstractBike> search(String parameter) {
        if (parameter.charAt(0) == 'l') {
            return less(Integer.parseInt(parameter.split(" +")[1]));
        }
        if (parameter.charAt(0) == 'm') {
            return more(Integer.parseInt(parameter.split(" +")[1]));
        }
        if (parameter.charAt(0) == 'b') {
            String[] data = parameter.split(" +");
            return between(Integer.parseInt(data[1]),
                    Integer.parseInt(data[2]));
        }
        return exact(Integer.parseInt(parameter));
    }

    private Predicate<AbstractBike> less(int price) {
        return b -> b.getPrice().intValue() < price;
    }

    private Predicate<AbstractBike> more(int price) {
        return b -> b.getPrice().intValue() > price;
    }

    private Predicate<AbstractBike> between(int price1, int price2) {
        return b -> (Math.min(price1, price2)) < b.getPrice().intValue()
                && b.getPrice().intValue() < (Math.max(price1, price2));
    }

    private Predicate<AbstractBike> exact(int price) {
        return b -> b.getPrice().equals(new BigDecimal(price));
    }
}
