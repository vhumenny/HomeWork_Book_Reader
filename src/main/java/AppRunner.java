import bookCatalog.BookCatalog;
import bookCatalog.ConsolePrinter;
import bookCatalog.StringHandler;
import bookCatalog.entity.Book;
import bookCatalog.fileHandlers.TxtFileHandler;

import java.nio.file.FileSystems;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AppRunner {
    private final StringHandler stringHandler = new StringHandler();
    private final TxtFileHandler txtFileHandler = new TxtFileHandler();
    private final ConsolePrinter consolePrinter = new ConsolePrinter();
    private final CommandParser commandParser = new CommandParser();
    private final BookCatalog catalog = new BookCatalog();
    private static final String USER_DIRECTORY = System.getProperty("user.dir")
                                                 + FileSystems.getDefault().getSeparator();

    public void start() {
        consolePrinter.printConsoleCommands();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        Commands command = commandParser.parseCommand(scanner.next());
        while (!Objects.equals(command, Commands.EXIT)) {
            executeCommand(command, scanner);
            consolePrinter.printConsoleCommands();
            command = commandParser.parseCommand(scanner.next());
        }
        catalog.saveCatalog();
        scanner.close();
    }

    private void executeCommand(Commands command, Scanner scanner) {
        switch (command) {
            case ADD_BOOK -> addBook(catalog, scanner);
            case DELETE_BOOK -> {
                String bookName = getBookName(scanner);
                catalog.removeBook(bookName);
            }
            case READ_CATALOG -> System.out.println(catalog.getBookMap());
            case PRINT_STATISTIC -> {
                String result = getResult(scanner);
                consolePrinter.printStatisticToConsole(result);
            }
            case WRITE_STATISTIC -> {
                String result = getResult(scanner);
                txtFileHandler.writeFile(result);
            }
            case NOT_FOUND -> System.out.println("You entered incorrect command. Please try again!");
        }
    }

    private String getResult(Scanner scanner) {
        String bookName = getBookName(scanner);
        Book book = catalog.getBook(bookName);
        return stringHandler.collectStatisticToString(book.getTextLines());
    }

    private void addBook(BookCatalog catalog, Scanner scanner) {
        String fileLocation = getBookLocation(scanner);
        List<String> stringList = txtFileHandler.readFile(fileLocation);
        Book book = stringHandler.createBook(stringList);
        catalog.addBook(book);
    }

    public String getBookLocation(Scanner scanner) {
        System.out.println("Please enter file name:");
        String fileName = scanner.next();
        return USER_DIRECTORY + fileName + ".txt";
    }

    public String getBookName(Scanner scanner) {
        System.out.println("Please enter book name:");
        return scanner.next();
    }
}
