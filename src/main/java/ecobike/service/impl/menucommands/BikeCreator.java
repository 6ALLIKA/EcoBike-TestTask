package ecobike.service.impl.menucommands;

import ecobike.model.AbstractBike;
import java.math.BigDecimal;
import java.util.Scanner;

public abstract class BikeCreator {
    private static final String ERROR_MESSAGE = "Something goes wrong, try again";

    public AbstractBike makeCommonPart(AbstractBike product, String input, Scanner scanner) {
        System.out.println("Type name of brand");
        input = scanner.nextLine();
        while (input.isEmpty()) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type name of brand");
            input = scanner.nextLine();
        }
        product.setBrand(input.replace(" +", " ").trim());

        System.out.println("Type weight of the bike (in grams)");
        input = scanner.nextLine();
        while (validateNumericalInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type weight of the bike (in grams)");
            input = scanner.nextLine();
        }
        product.setWeight(Integer.parseInt(input));

        System.out.println("Type availability of lights at front and back (TRUE/FALSE)");
        input = scanner.nextLine();
        while (validateBooleanInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type availability of lights at front and back (TRUE/FALSE)");
            input = scanner.nextLine();
        }
        product.setLights(Boolean.parseBoolean(input.toLowerCase()));

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
        return product;
    }

    public AbstractBike makeUniquePartForClassicBike(AbstractBike product,
                                                     String input, Scanner scanner) {

        System.out.println("Type size of the wheels (in inch)");
        input = scanner.nextLine();
        while (validateNumericalInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type size of the wheels (in inch)");
            input = scanner.nextLine();
        }
        product.setWheelSize(Integer.parseInt(input));

        System.out.println("Type number of gears");
        input = scanner.nextLine();
        while (validateNumericalInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type number of gears");
            input = scanner.nextLine();
        }
        product.setGearsCount(Integer.parseInt(input));

        return product;
    }

    public AbstractBike makeUniquePartForElectricBikeModel(AbstractBike product,
                                                     String input, Scanner scanner) {

        System.out.println("Type maximum speed (in km/h)");
        input = scanner.nextLine();
        while (validateNumericalInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type maximum speed (in km/h)");
            input = scanner.nextLine();
        }
        product.setMaxSpeed(Integer.parseInt(input));

        System.out.println("Type battery capacity (in mAh)");
        input = scanner.nextLine();
        while (validateNumericalInput(input)) {
            System.out.println(ERROR_MESSAGE);
            System.out.println("Type battery capacity (in mAh)");
            input = scanner.nextLine();
        }
        product.setBatteryCapacity(Integer.parseInt(input));

        return product;
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
