package ecobike.service.searchcommands;

import ecobike.model.AbstractBike;
import java.math.BigDecimal;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;

@Component
public class SearchByPrice implements SearchCommand {

    @Override
    public Predicate<AbstractBike> search(String parameter) {
        if (parameter.charAt(0) == 'l') {
            return showLessByParameter(Integer.parseInt(parameter.split(" +")[1]));
        }
        if (parameter.charAt(0) == 'm') {
            return showMoreByParameter(Integer.parseInt(parameter.split(" +")[1]));
        }
        if (parameter.charAt(0) == 'b') {
            String[] data = parameter.split(" +");
            return showBetweenByParameters(Integer.parseInt(data[1]),
                    Integer.parseInt(data[2]));
        }
        return showExactByParameter(Integer.parseInt(parameter));
    }

    private Predicate<AbstractBike> showLessByParameter(int price) {
        return b -> b.getPrice().intValue() < price;
    }

    private Predicate<AbstractBike> showMoreByParameter(int price) {
        return b -> b.getPrice().intValue() > price;
    }

    private Predicate<AbstractBike> showBetweenByParameters(int price1, int price2) {
        return b -> (Math.min(price1, price2)) < b.getPrice().intValue()
                && b.getPrice().intValue() < (Math.max(price1, price2));
    }

    private Predicate<AbstractBike> showExactByParameter(int price) {
        return b -> b.getPrice().equals(new BigDecimal(price));
    }
}
