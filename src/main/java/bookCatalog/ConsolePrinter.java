package bookCatalog;

public class ConsolePrinter {

    public void printStatisticToConsole(String result) {
        System.out.println(result);
    }

    public void printConsoleCommands() {
        System.out.println("""
                Please choose the command:\s
                1) to show all books in catalog enter "read"
                2) to add book in catalog enter "add"
                3) to delete book from catalog enter "delete"
                4) to write statistic to txt file enter "write"
                5) to print statistic to console enter "print"
                6) to finish program enter "exit\"""");
    }
}
