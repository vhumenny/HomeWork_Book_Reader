import entity.Book;
import fileHandlers.TxtFileHandler;

import java.nio.file.FileSystems;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AppRunner {
    private final StringHandler stringHandler = new StringHandler();
    private final TxtFileHandler txtFileHandler = new TxtFileHandler();
    private static final String USER_DIRECTORY = System.getProperty("user.dir")
                                                 + FileSystems.getDefault().getSeparator();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        BookCatalog catalog = new BookCatalog();
        receiveCommand();

        List<String> stringList = txtFileHandler.readFile(bookLocation);

        Book book = catalog.addBook(stringList);

        List<String> parsedStrings = stringHandler.parse(book.getTextLines());
        stringHandler.countDistinctWords(parsedStrings);
        stringHandler.collectPopularWords(parsedStrings);


        String result = stringHandler.collectResultToString();
        txtFileHandler.writeFile(result);
        printStatisticToConsole(result);

        catalog.getFileHandler().writeFile(catalog.getBookMap());
    }

    private void receiveCommand() {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        consolePrinter.printConsoleCommands();
        Commands command = Commands.fromTitle(scanner.next());
        List<String> stringList = null;
        while (!Objects.equals(command, Commands.EXIT)) {
            consolePrinter.printConsoleCommands();
            switch (command) {
                case ADD_BOOK -> ;
                case DELETE_BOOK -> ;
                case PARSE -> stringList = txtFileHandler.readFile(bookLocation);
                case WRITE_STATISTIC -> ;
                case PRINT_STATISTIC -> ;
                case EXIT -> ;
                case NOT_FOUND -> System.out.println("You entered incorrect command. Please try again!");
            }
            System.out.println("Please enter command:");
            command = Commands.fromTitle(scanner.next());
        }

    }

    private static String getBookLocation() {
        System.out.println("Please enter book name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        if (name.equals("exit")) return name;
        else return USER_DIRECTORY + name;
    }

    public void printStatisticToConsole(String result) {
        System.out.println(result);
    }
}
