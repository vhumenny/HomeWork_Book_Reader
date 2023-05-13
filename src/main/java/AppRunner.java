import java.io.File;
import java.nio.file.FileSystems;
import java.util.Scanner;

public class AppRunner {
    private static final String USER_DIRECTORY = System.getProperty("user.dir") + FileSystems.getDefault().getSeparator();

    public void start() {
        System.out.println("Please enter book name: ");
        String bookName = new Scanner(System.in).next();
        File file = new File(USER_DIRECTORY + bookName);
        while (!file.exists()) {
            System.out.println("Such book doesn't exist! " +
                               "Please enter correct file name! (example - " + "book.txt) or enter exit.");
            bookName = new Scanner(System.in).next();
            file = new File(USER_DIRECTORY + bookName);
            if (bookName.equals("exit")) return;
        }
        String fileLocation = USER_DIRECTORY + bookName;
        new FileHandler().handleFile(fileLocation, new WordsStorage());
    }
}