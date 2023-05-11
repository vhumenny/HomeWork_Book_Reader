import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Files.newBufferedWriter;

public class AppRunner {
    private static final String userDirectory = System.getProperty("user.dir") + FileSystems.getDefault().getSeparator();
    private static final String outputFile = "output.txt";
    private static final Pattern pattern = Pattern.compile("\\w+");

    public void start() {
        String bookName = getBookName();
        try (var reader = newBufferedReader(Path.of((userDirectory + bookName)));
             var writer = newBufferedWriter(Path.of(outputFile))) {
            List<String> bookStrings = new ArrayList<>();
            String lineToRead = reader.readLine().toLowerCase();

            while (lineToRead != null) {
                Matcher matcher = pattern.matcher(lineToRead);
                while (matcher.find()) {
                    bookStrings.add(matcher.group());
                }
                lineToRead = reader.readLine();
            }
            new WordsStorage().addPopularWords(bookStrings);


        } catch (IOException e) {
            throw new RuntimeException(e);

        }

    }

    private static String getBookName() {
        System.out.println("Please enter book name: ");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.next();
        File file = new File(userDirectory + bookName);
        while (!file.exists()) {
            if (!file.exists()) {
                System.out.println("please enter correct file name! (example - " + "book.txt");
            }
            bookName = scanner.next();
            file = new File(userDirectory + bookName);
        }
        return bookName;
    }

}
