public class ConsolePrinter {

    public void printStatisticToConsole(String result) {
        System.out.println(result);
    }

    public void printConsoleCommands() {
        System.out.println("""
                Please choose the command:\s
                1) to parse new book enter - "parse"
                2) to write statistic in file enter - "write statistic"
                3) to print statistic in console enter "print statistic"
                4) to show all books in catalog enter "read catalog"
                5) to add book in catalog enter "add book"
                6) to delete book from catalog enter "delete book"
                7) to finish program enter "exit\"""");
    }
}
