package bookCatalog;

import bookCatalog.entity.Book;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringHandler {
    private static final Pattern PATTERN = Pattern.compile("\\w+");

    public List<String> parseBookText(List<String> lines) {
        return lines
                .stream()
                .flatMap(str -> PATTERN.matcher(str).results())
                .map(MatchResult::group)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    public String collectStatisticToString(List<String> bookStrings) {
        List<String> parsedStrings = parseBookText(bookStrings);
        long distinctWordsCounter = countDistinctWords(parsedStrings);
        Map<String, Long> popularWordsMap = collectPopularWords(parsedStrings);

        String popularWords = popularWordsMap
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .collect(Collectors.joining("\n"));
        return popularWords + "\ntotal amount of distinct words: " + distinctWordsCounter;
    }

    public long countDistinctWords(List<String> bookStrings) {
        return bookStrings.stream()
                .filter(s -> s.length() > 2)
                .distinct()
                .count();
    }

    public Map<String, Long> collectPopularWords(List<String> bookStrings) {
        long amountOfWords = 10L;
        return bookStrings.stream()
                .filter(s -> s.length() > 2)
                .collect(Collectors.groupingByConcurrent(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(amountOfWords)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Book createBook(List<String> strings) {
        String bookName = strings.get(0);
        String author = strings.get(2).replaceAll("by", "").stripLeading();
        strings.subList(0, 4).clear();
        return new Book(bookName, author, strings);
    }
}
