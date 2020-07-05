package ecobike.service.impl.menucommands;

import ecobike.model.AbstractBike;
import ecobike.model.ElectricBike;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class AddElectricBike extends BikeCreator implements Command {

    @Override
    public List<AbstractBike> execute(List<AbstractBike> list, String pathToFile) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lets add new E-BIKE");
        System.out.println("Enter values by following types");

        AbstractBike product = new ElectricBike();
        String input = "";

        super.makeCommonPart(product, input, scanner);
        super.makeUniquePartForElectricBikeModel(product, input, scanner);

        list.add(product);

        return list;
    }
}
