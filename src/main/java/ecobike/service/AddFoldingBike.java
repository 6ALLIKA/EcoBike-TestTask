package ecobike.service;

import ecobike.model.AbstractBike;
import ecobike.model.FoldingBike;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class AddFoldingBike implements Command {
    private final String ERROR_MESSAGE = "Something goes wrong, try again";

    @Override
    public List<AbstractBike> execute(List<AbstractBike> list) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lets add new FOLDING BIKE");
        System.out.println("Enter values by following types");

        System.out.println("Type name of brand");
        String input = scanner.nextLine();
        FoldingBike product = new FoldingBike();
        while (input.isEmpty()) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type name of brand");
            input = scanner.nextLine();
        }
        product.setBrand(input.replace(" +", " ").trim());

        System.out.println("Type size of the wheels (in inch)");
        input = scanner.nextLine();
        while (checkForNumericalInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type size of the wheels (in inch)");
            input = scanner.nextLine();
        }
        product.setWheelSize(Integer.parseInt(input));

        System.out.println("Type number of gears");
        input = scanner.nextLine();
        while (checkForNumericalInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type number of gears");
            input = scanner.nextLine();
        }
        product.setGearsQuantity(Integer.parseInt(input));

        System.out.println("Type weight of the bike (in grams)");
        input = scanner.nextLine();
        while (checkForNumericalInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type weight of the bike (in grams)");
            input = scanner.nextLine();
        }
        product.setWeight(Integer.parseInt(input));

        System.out.println("Type availability of lights at front and back (TRUE/FALSE)");
        input = scanner.nextLine();
        while (checkForBooleanInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type availability of lights at front and back (TRUE/FALSE)");
            input = scanner.nextLine();
        }
        product.setLights(Boolean.parseBoolean(input.toLowerCase()));

        System.out.println("Type color");
        input = scanner.nextLine();
        while (checkForAlphabeticInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type color");
            input = scanner.nextLine();
        }
        product.setColor(input.replace(" +", " ").trim());

        System.out.println("Type price");
        input = scanner.nextLine();
        while (checkForNumericalInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type price");
            input = scanner.nextLine();
        }
        product.setPrice(new BigDecimal(input));
        list.add(product);

        return list;
    }

    private boolean checkForAlphabeticInput(String input) {
        return input.isEmpty() || !input.matches("[a-zA-Z ]*");
    }

    private boolean checkForNumericalInput(String input) {
        return input.isEmpty() || !input.matches("[0-9]+");
    }

    private boolean checkForBooleanInput(String input) {
        return input.isEmpty()
                || !(input.equalsIgnoreCase("true")
                || input.equalsIgnoreCase("false"));
    }
}
