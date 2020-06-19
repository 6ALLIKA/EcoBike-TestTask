package ecobike.service.menucommands;

import ecobike.model.AbstractBike;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class StopProgramm implements Command {
    /**
     * This implementation has checks that if user have unsaved products, program will ask to
     * save them or close the program
     */

    @Override
    public List<AbstractBike> execute(List<AbstractBike> list) {
        if (!list.isEmpty()) {
            System.out.println("You have unsaved changes, do you want to save them?");
            System.out.println("Type y/n (yes/no)");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            while (!(choice.equalsIgnoreCase("yes")
                    || choice.equalsIgnoreCase("no")
                    || choice.equalsIgnoreCase("y")
                    || choice.equalsIgnoreCase("n"))) {
                System.out.println("You made a mistake, try again");
                System.out.println("Type y/n (yes/no)");
                choice = scanner.nextLine();
            }
            if (choice.equalsIgnoreCase("yes")
                    || choice.equalsIgnoreCase("y")) {
                WriteToFile writeToFile = new WriteToFile();
                writeToFile.execute(list);
            } else {
                scanner.close();
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
        return list;
    }
}
