package ecobike.service.impl.menucommands;

import ecobike.model.AbstractBike;
import ecobike.model.Speedelec;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class AddSpeedelec extends BikeCreator implements Command {
    private static final String ERROR_MESSAGE = "Something goes wrong, try again";

    @Override
    public List<AbstractBike> execute(List<AbstractBike> list) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lets add new SPEEDELEC");
        System.out.println("Enter values by following types");

        AbstractBike product = new Speedelec();
        String input = "";

        super.makeCommonPart(product, input, scanner);
        super.makeUniquePartForElectricBikeModel(product, input, scanner);

        list.add(product);

        return list;
    }
}
