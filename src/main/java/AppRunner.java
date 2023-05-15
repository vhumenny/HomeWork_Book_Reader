import java.nio.file.FileSystems;
import java.util.List;
import java.util.Scanner;

public class AppRunner {
    private final PopularWordsStorage popularWordsStorage = new PopularWordsStorage();
    private static final String USER_DIRECTORY = System.getProperty("user.dir")
                                                 + FileSystems.getDefault().getSeparator();


    public void start() {
        FileHandler fileHandler = new FileHandler();
        System.out.println("Please enter book name:");
        Scanner scanner = new Scanner(System.in);
        String fileLocation = USER_DIRECTORY + scanner.next();
        scanner.close();
        List<String> stringList = fileHandler.readFile(fileLocation);
        List<String> parsedStrings = popularWordsStorage.parse(stringList);
        popularWordsStorage.countDistinctWords(parsedStrings);
        popularWordsStorage.collectPopularWords(parsedStrings);

        String result = popularWordsStorage.collectResultToString();
        fileHandler.writeFile(result);
        printStatisticToConsole(result);
    }


    public void printStatisticToConsole(String result) {
        System.out.println(result);
    }
}
