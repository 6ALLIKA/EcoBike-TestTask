package ecobike.service.searchcommands;

import ecobike.model.AbstractBike;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;

@Component
public class SearchByMaxSpeed implements SearchCommand {

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

    private Predicate<AbstractBike> less(int speed) {
        return b -> b.getMaxSpeed() < speed;
    }

    private Predicate<AbstractBike> more(int speed) {
        return b -> b.getMaxSpeed() > speed;
    }

    private Predicate<AbstractBike> between(int speed1, int speed2) {
        return b -> (Math.min(speed1, speed2)) < b.getMaxSpeed()
                && b.getMaxSpeed() < (Math.max(speed1, speed2));
    }

    private Predicate<AbstractBike> exact(int speed) {
        return b -> b.getMaxSpeed() == speed;
    }
}
