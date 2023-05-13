import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordsStorage {

    private final Map<String, Long> popularWords = new HashMap<>();
    private Long distinctWordsCounter;

    public Map<String, Long> processWords(List<String> bookStrings) {
        getPopularWords(bookStrings);
        countDistinctWords(bookStrings);
        printStatisticToConsole();
        return popularWords;
    }

    private void printStatisticToConsole() {
        popularWords.forEach((key, value) -> System.out.println(key + " : " + value));
        System.out.println("total amount of distinct words:" + distinctWordsCounter);
    }

    private void countDistinctWords(List<String> bookStrings) {
        distinctWordsCounter = bookStrings.stream()
                .filter(s -> s.length() > 2)
                .distinct()
                .count();
    }

    private void getPopularWords(List<String> bookStrings) {
        bookStrings.stream()
                .filter(s -> s.length() > 2)
                .collect(Collectors.groupingByConcurrent(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)
                .forEach(entry -> popularWords.put(entry.getKey(), entry.getValue()));
    }

    public Long getDistinctWordsCounter() {
        return distinctWordsCounter;
    }
}
