package ecobike.service.menucommands;

import ecobike.dao.impl.BikeDaoImpl;
import ecobike.model.AbstractBike;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
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

    private String path = "src/main/resources/ecobike.txt";

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public List<AbstractBike> execute(List<AbstractBike> list) {
        BikeDaoImpl bikeDao = new BikeDaoImpl();
        bikeDao.getAll(path, list);
        Scanner scanner = new Scanner(System.in);
        list = list.stream().sorted().collect(Collectors.toList());
        System.out.println("Enter value how much product show on page");
        int skip = 0;
        String input = scanner.nextLine();

        while (checkForCommandInput(input)) {
            System.out.println("Enter numbet please, try again");
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
