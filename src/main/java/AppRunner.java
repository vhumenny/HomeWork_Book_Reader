import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Files.newBufferedWriter;

public class AppRunner {
    private static final String userDirectory = System.getProperty("user.dir") + FileSystems.getDefault().getSeparator();
    private static final Pattern pattern = Pattern.compile("\\w+");

    public void start() {
        String bookName = getBookName();
        if (bookName.equals("exit")) return;
        try (var reader = newBufferedReader(Path.of((userDirectory + bookName)));
             var writer = newBufferedWriter(Path.of(userDirectory + bookName + "_statistic.txt"))) {
            List<String> bookStrings = new ArrayList<>();
            String lineToRead = reader.readLine().toLowerCase();

            while (lineToRead != null) {
                Matcher matcher = pattern.matcher(lineToRead);
                while (matcher.find()) {
                    bookStrings.add(matcher.group());
                }
                lineToRead = reader.readLine();
            }
            WordsStorage wordsStorage = new WordsStorage();
            Map<String, Long> result = wordsStorage.processBookWords(bookStrings);
            for (Map.Entry<String, Long> element : result.entrySet()) {
                writer.write(element.getKey() + " : " + element.getValue());
                writer.newLine();
            }
            writer.write("total amount of distinct words:" + wordsStorage.getDistinctWordsCounter());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getBookName() {
        System.out.println("Please enter book name: ");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.next();
        File file = new File(userDirectory + bookName);
        if (!file.exists()) {
            System.out.println("Such book doesn't exist! " +
                               "Please enter correct file name! (example - " + "book.txt)");
        }
        return bookName;
    }
}