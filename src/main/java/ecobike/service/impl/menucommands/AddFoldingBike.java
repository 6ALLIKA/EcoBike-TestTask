package ecobike.service.impl.menucommands;

import ecobike.model.AbstractBike;
import ecobike.model.FoldingBike;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class AddFoldingBike extends BikeCreator implements Command {

    @Override
    public List<AbstractBike> execute(List<AbstractBike> list) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lets add new FOLDING BIKE");
        System.out.println("Enter values by following types");

        FoldingBike product = new FoldingBike();
        String input = "";

        super.makeCommonPart(product, input, scanner);
        super.makeUniquePartForClassicBike(product, input, scanner);

        list.add(product);

        return list;
    }
}
