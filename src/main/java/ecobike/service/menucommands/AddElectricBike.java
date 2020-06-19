package ecobike.service.menucommands;

import ecobike.model.AbstractBike;
import ecobike.model.ElectricBike;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class AddElectricBike implements Command {
    private static final String ERROR_MESSAGE = "Something goes wrong, try again";

    @Override
    public List<AbstractBike> execute(List<AbstractBike> list) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lets add new E-BIKE");
        System.out.println("Enter values by following types");

        System.out.println("Type name of brand");
        String input = scanner.nextLine();
        ElectricBike product = new ElectricBike();
        while (input.isEmpty()) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type name of brand");
            input = scanner.nextLine();
        }
        product.setBrand(input.replace(" +", " "));

        System.out.println("Type weight of the bike (in grams)");
        input = scanner.nextLine();
        while (validateNumericalInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type weight of the bike (in grams)");
            input = scanner.nextLine();
        }
        product.setWeight(Integer.parseInt(input));

        System.out.println("Type maximum speed (in km/h)");
        input = scanner.nextLine();
        while (validateNumericalInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type maximum speed (in km/h)");
            input = scanner.nextLine();
        }
        product.setWheelSize(Integer.parseInt(input));

        System.out.println("Type availability of lights at front and back (TRUE/FALSE)");
        input = scanner.nextLine();
        while (validateBooleanInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type availability of lights at front and back (TRUE/FALSE)");
            input = scanner.nextLine();
        }
        product.setLights(Boolean.parseBoolean(input.toLowerCase()));

        System.out.println("Type battery capacity (in mAh)");
        input = scanner.nextLine();
        while (validateNumericalInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type battery capacity (in mAh)");
            input = scanner.nextLine();
        }
        product.setGearsCount(Integer.parseInt(input));

        System.out.println("Type color");
        input = scanner.nextLine();
        while (validateAlphabeticInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type color");
            input = scanner.nextLine();
        }
        product.setColor(input.replace(" +", " ").trim());

        System.out.println("Type price");
        input = scanner.nextLine();
        while (validateNumericalInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type price");
            input = scanner.nextLine();
        }
        product.setPrice(new BigDecimal(input));
        list.add(product);

        return list;
    }

    private boolean validateAlphabeticInput(String input) {
        return input.isEmpty() || !input.matches("[a-zA-Z ]*");
    }

    private boolean validateNumericalInput(String input) {
        return input.isEmpty() || !input.matches("[0-9]+");
    }

    private boolean validateBooleanInput(String input) {
        return input.isEmpty()
                || !(input.equalsIgnoreCase("true")
                || input.equalsIgnoreCase("false"));
    }
}
