package ecobike.service.impl.menucommands;

import ecobike.dao.impl.BikeDaoImpl;
import ecobike.model.AbstractBike;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowCatalog implements Command {
    /**
     * This class is responsible get objects from file and show them at console
     * Also program asks you to input, how much objects to show
     * All object have toProductLook() method that have unique implementation based on
     * characteristics
     * Also them will be sorted
     */
    private final BikeDaoImpl bikeDao;

    @Autowired
    public ShowCatalog(BikeDaoImpl bikeDao) {
        this.bikeDao = bikeDao;
    }

    @Override
    public List<AbstractBike> execute(List<AbstractBike> list, String pathToFile) {
        bikeDao.getAll(pathToFile, list);
        Scanner scanner = new Scanner(System.in);
        list = list.stream().sorted().collect(Collectors.toList());
        System.out.println("Enter value how much product show on page");
        int skip = 0;
        String input = scanner.nextLine();

        while (checkForCommandInput(input)) {
            System.out.println("Enter please number, try again");
            input = scanner.nextLine();
        }

        int limit = Integer.parseInt(input);

        while (list.size() > skip) {
            list.stream()
                    .skip(skip)
                    .limit(limit)
                    .map(AbstractBike::toProductLook)
                    .forEach(System.out::println);
            skip += limit;
            if (list.size() > skip) {
                System.out.println("If u want back to menu write 'back'"
                        + " or press enter to continue");
                String check = scanner.nextLine();
                if (check.equals("back")) {
                    break;
                }
            }
        }
        return list;
    }

    private boolean checkForCommandInput(String input) {
        return input.isEmpty() || !(input.matches("[0-9]+"));
    }
}
