package ecobike;

import ecobike.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        ConsoleHandler consoleHandler = context.getBean(ConsoleHandler.class);
        consoleHandler.start();
    }
}
