import entity.Book;
import fileHandlers.TxtFileHandler;

import java.nio.file.FileSystems;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AppRunner {
    private final StringHandler stringHandler = new StringHandler();
    private final TxtFileHandler txtFileHandler = new TxtFileHandler();
    private final ConsolePrinter consolePrinter = new ConsolePrinter();
    private static final String USER_DIRECTORY = System.getProperty("user.dir")
                                                 + FileSystems.getDefault().getSeparator();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        consolePrinter.printConsoleCommands();
        Commands command = Commands.fromTitle(scanner.next());
        receiveCommand(command);
    }

    private void receiveCommand(Commands command) {
        BookCatalog catalog = new BookCatalog();
        while (!Objects.equals(command, Commands.EXIT)) {
            switch (command) {
                case ADD_BOOK -> addBook(catalog);
                case DELETE_BOOK -> {
                    String bookName = getBookLocation();
                    catalog.removeBook(bookName);
                }
                case READ_CATALOG -> System.out.println(catalog.getBookMap());
                case EXIT -> {
                    return;
                }
                case NOT_FOUND -> System.out.println("You entered incorrect command. Please try again!");
            }
            consolePrinter.printConsoleCommands();
            command = Commands.fromTitle(scanner.next());
        }
        catalog.saveCatalog();
    }

    private void addBook(BookCatalog catalog) {
        String bookName = getBookLocation();
        List<String> stringList = txtFileHandler.readFile(bookName);
        Book book = stringHandler.createBook(stringList);
        catalog.addBook(book);
        List<String> parsedStrings = stringHandler.parse(book.getTextLines());
        consolePrinter.printStatisticTips();

        Commands command = Commands.fromTitle(scanner.next());
        String result = stringHandler.collectResultToString(parsedStrings);
        switch (command) {
            case PRINT_STATISTIC -> consolePrinter.printStatisticToConsole(result);
            case WRITE_STATISTIC -> txtFileHandler.writeFile(result);
        }
    }

    private String getBookLocation() {
        System.out.println("Please enter book name:");
        String name = scanner.next();
        if (!name.endsWith(".txt")) return name;
        else return USER_DIRECTORY + name;
    }
}
