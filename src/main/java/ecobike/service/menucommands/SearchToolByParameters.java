package ecobike.service.menucommands;

import ecobike.dao.impl.BikeDaoImpl;
import ecobike.model.AbstractBike;
import ecobike.service.SearchCommandService;
import ecobike.service.searchcommands.SearchCommand;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchToolByParameters implements Command {
    /**
     * This class is responsible to configure filter parameters, their implementations lays in 
     * service.searchcommands
     * Also u can choose to show first or all results
     */

    private static final String ERROR_MESSAGE = "Something goes wrong, try again";
    private static final String NUMERIC_PATTERN = "[0-9]+";
    private Map<Integer, String> searchCommands = new HashMap<>();
    private Map<Integer, String> detailsForSearchCommands = new HashMap<>();
    private List<Predicate<AbstractBike>> allPredicates = new ArrayList<>();
    private final SearchCommandService searchCommandService;
    private final BikeDaoImpl bikeDao;
    private final ShowCatalog showCatalog;

    @Autowired
    public SearchToolByParameters(SearchCommandService searchCommandService,
                                  BikeDaoImpl bikeDao, ShowCatalog showCatalog) {
        this.searchCommandService = searchCommandService;
        this.bikeDao = bikeDao;
        this.showCatalog = showCatalog;
    }

    @Override
    public List<AbstractBike> execute(List<AbstractBike> list) {
        bikeDao.getAll(showCatalog.getPath(), list);
        fillSearchBasicCommands();
        fillDetailsForSearchCommands();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please choose what parametr you want add to filter");
            for (Map.Entry<Integer, String> entry : searchCommands.entrySet()) {
                System.out.println(entry.getKey() + "   " + entry.getValue());
            }
            String userInput = scanner.nextLine();
            while (checkForCommandInput(userInput)) {
                System.out.println("Wrong command, try again");
                userInput = scanner.nextLine();
            }
            int commandInput = Integer.parseInt(userInput);
            SearchCommand command = searchCommandService.getCommand(commandInput);
            if (commandInput == 1) {
                System.out.println(detailsForSearchCommands.get(commandInput));
                String input = scanner.nextLine();

                while (input.isEmpty() || !((input.equalsIgnoreCase("E-BIKE")
                        || input.equalsIgnoreCase("FOLDING BIKE")
                        || input.equalsIgnoreCase("SPEEDELEC")))) {

                    System.out.println(ERROR_MESSAGE);
                    System.out.println(detailsForSearchCommands.get(commandInput));
                    input = scanner.nextLine();
                }

                if (input.equalsIgnoreCase("FOLDING BIKE")) {
                    fillSearchCommandsForClassicModel();
                } else {
                    fillSearchCommandsForElectroModel();
                }

                allPredicates.add(command.search(input));
                searchCommands.remove(commandInput);

            } else if (commandInput == 2) {

                System.out.println(detailsForSearchCommands.get(commandInput));
                String input = scanner.nextLine();
                while (input.isEmpty()) {
                    System.out.println(ERROR_MESSAGE);
                    System.out.println(detailsForSearchCommands.get(commandInput));
                    input = scanner.nextLine();
                }
                allPredicates.add(command.search(input));
                searchCommands.remove(commandInput);

            } else if (commandInput == 5) {

                System.out.println(detailsForSearchCommands.get(5));
                String input = scanner.nextLine();

                while (input.isEmpty() || !(input.equalsIgnoreCase("true")
                        || input.equalsIgnoreCase("false"))) {

                    System.out.println(ERROR_MESSAGE);
                    System.out.println(detailsForSearchCommands.get(5));
                    input = scanner.nextLine();
                }

                allPredicates.add(command.search(input.toLowerCase()));
                searchCommands.remove(commandInput);

            } else if (commandInput == 4) {

                System.out.println(detailsForSearchCommands.get(4));
                String input = scanner.nextLine();
                while (input.isEmpty() || !input.matches("[a-zA-Z ]*")) {
                    System.out.println(ERROR_MESSAGE);
                    System.out.println(detailsForSearchCommands.get(4));
                    input = scanner.nextLine();
                }

                allPredicates.add(command.search(input.toLowerCase()));
                searchCommands.remove(commandInput);

            } else if (commandInput == 11) {
                break;
            } else {

                System.out.println(detailsForSearchCommands.get(4));
                String input = scanner.nextLine();
                String[] split = input.split(" +");

                while (!((split.length == 1 && !input.isEmpty() && input.matches(NUMERIC_PATTERN))

                        || ((split.length == 2 && split[1].matches(NUMERIC_PATTERN))
                        && (split[0].equalsIgnoreCase("less")
                        || split[0].equalsIgnoreCase("more")))

                        || ((split.length == 3 && split[1].matches(NUMERIC_PATTERN)
                        && split[2].matches(NUMERIC_PATTERN))
                        && split[0].equalsIgnoreCase("between")))) {

                    System.out.println(ERROR_MESSAGE);
                    System.out.println(detailsForSearchCommands.get(4));
                    input = scanner.nextLine();
                }

                allPredicates.add(command.search(input));
                searchCommands.remove(commandInput);
            }
        }

        makeChoiceHowMuchShowResults(scanner, list);

        return list;
    }

    private void fillSearchBasicCommands() {
        this.searchCommands.put(1, "Search by type");
        this.searchCommands.put(2, "Search by brand");
        this.searchCommands.put(3, "Search by weight");
        this.searchCommands.put(4, "Search by color");
        this.searchCommands.put(5, "Search by availability of lights");
        this.searchCommands.put(6, "Search by price");
        this.searchCommands.put(11, "Close filter and show result(s)");
    }

    private void fillSearchCommandsForClassicModel() {
        this.searchCommands.put(7, "Search by quantity of gears");
        this.searchCommands.put(8, "Search by size of wheels");
    }

    private void fillSearchCommandsForElectroModel() {
        this.searchCommands.put(9, "Search by max speed");
        this.searchCommands.put(10, "Search by battery capacity");
    }

    private void fillDetailsForSearchCommands() {
        this.detailsForSearchCommands.put(1, "Search by type" + "\n"
                + "Enter name of type below" + "\n"
                + "Available next types" + "\n"
                + "'E-BIKE', 'FOLDING BIKE', 'SPEEDELEC'");
        this.detailsForSearchCommands.put(2, "Search by brand" + "\n"
                + "Enter name of brands below");
        this.detailsForSearchCommands.put(3, "Search by color" + "\n"
                + "Enter color below");
        this.detailsForSearchCommands.put(4, "You can search by type in next "
                + "order and needs positive number" + "\n"
                + "'between number number'" + "\n"
                + "'less number'" + "\n"
                + "'more number'" + "\n"
                + "or enter single 'number' for exact results");
        this.detailsForSearchCommands.put(5, "You can search by availability of lights" + "\n"
                + "Enter true/false");

    }

    private boolean checkForCommandInput(String input) {
        return input.isEmpty() || !(input.matches("[0-9]+")
                && (searchCommands.containsKey(Integer.parseInt(input))));
    }

    private void makeChoiceHowMuchShowResults(Scanner scanner, List<AbstractBike> list) {
        System.out.println("Please select next option what u want to see from searching" + "\n"
                + "Type 1 if you want see first single result" + "\n"
                + "Type 2 if you want see all results");
        String choice = scanner.nextLine();

        while (choice.isEmpty() || !(choice.matches("[0-9]+")
                && (Integer.parseInt(choice) == 1 || Integer.parseInt(choice) == 2))) {
            System.out.println("Wrong command, try again");
            choice = scanner.nextLine();
        }

        Predicate<AbstractBike> compositePredicate =
                allPredicates.stream().reduce(w -> true, Predicate::and);
        if (Integer.parseInt(choice) == 1) {
            list.stream()
                    .sorted()
                    .filter(compositePredicate).findFirst()
                    .map(AbstractBike::toProductLook)
                    .ifPresent(System.out::println);
        } else {
            list.stream()
                    .sorted()
                    .filter(compositePredicate)
                    .map(AbstractBike::toProductLook)
                    .forEach(System.out::println);
        }
        allPredicates.clear();
    }
}
