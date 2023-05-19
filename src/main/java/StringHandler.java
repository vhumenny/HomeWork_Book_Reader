import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringHandler {

    private final Map<String, Long> popularWordsMap = new HashMap<>();
    private Long distinctWordsCounter;
    private static final Pattern PATTERN = Pattern.compile("\\w+");


    public List<String> parse(List<String> lines) {
        return lines
                .stream()
                .flatMap(str -> PATTERN.matcher(str).results())
                .map(MatchResult::group)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    public String collectResultToString() {
        String popularWords = popularWordsMap
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .collect(Collectors.joining("\n"));
        return popularWords + "\ntotal amount of distinct words: " + distinctWordsCounter;
    }

    public long countDistinctWords(List<String> bookStrings) {
        distinctWordsCounter = bookStrings.stream()
                .filter(s -> s.length() > 2)
                .distinct()
                .count();
        return distinctWordsCounter;
    }

    public void collectPopularWords(List<String> bookStrings) {
        long amountOfWords = 10L;
        bookStrings.stream()
                .filter(s -> s.length() > 2)
                .collect(Collectors.groupingByConcurrent(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(amountOfWords)
                .forEach(entry -> popularWordsMap.put(entry.getKey(), entry.getValue()));
    }
}
