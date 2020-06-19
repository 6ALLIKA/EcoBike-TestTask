package ecobike;

import ecobike.model.AbstractBike;
import ecobike.service.MenuCommandService;
import ecobike.service.impl.menucommands.Command;
import ecobike.service.impl.menucommands.ShowCatalog;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service
public class ConsoleHandler {
    // common implementation without DB
    private List<AbstractBike> storageBikes = new ArrayList<>();
    private List<AbstractBike> catalogOfBikes = new ArrayList<>();

    private final MenuCommandService menuCommandService;

    public ConsoleHandler(MenuCommandService menuCommandService) {
        this.menuCommandService = menuCommandService;
    }

    public void start() {
        System.out.println("Please enter path for catalog file in next format, for example");
        System.out.println("C: Folder Catalog ecobike.txt");
        System.out.println("Or press Enter to start with default catalog");

        ShowCatalog showCatalog = new ShowCatalog();
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        while (!path.equals("")) { // here checking that path to file is valid and exists
            String pathInput = String.join(File.separator, path.split(" +"));
            File tmpFile = new File(pathInput);
            if (tmpFile.exists()) {
                showCatalog.setPath(pathInput);
                break;
            } else {
                System.out.println("Yours path is incorrect, please check it and try again");
                path = scanner.nextLine();
            }
        }

        while (true) {
            System.out.println("Please make your choice:");
            System.out.println("1 - Show the entire EcoBike catalog");
            System.out.println("2 – Add a new folding bike");
            System.out.println("3 – Add a new speedelec");
            System.out.println("4 – Add a new e-bike");
            System.out.println("5 – Find the items by filter");
            System.out.println("6 – Write to a file");
            System.out.println("7 – Stop the program");

            String input = scanner.nextLine();
            while (validateCommandInput(input)) {
                System.out.println("Wrong command, try again");
                input = scanner.nextLine();
            }

            int commandInput = Integer.parseInt(input);
            Command command = menuCommandService.getCommand(commandInput);
            if ((1 == commandInput) || (5 == commandInput)) {
                command.execute(catalogOfBikes);
            } else {
                command.execute(storageBikes);
            }
        }
    }

    private boolean validateCommandInput(String input) {
        return input.isEmpty() || !(input.matches("[0-9]+")
                && (Integer.parseInt(input) <= 7 && Integer.parseInt(input) >= 1));
    }
}
