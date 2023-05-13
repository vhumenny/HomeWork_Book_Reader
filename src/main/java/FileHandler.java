import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Files.newBufferedWriter;

public class FileHandler {
    private static final Pattern PATTERN = Pattern.compile("\\w+");
    private static final String OUTPUT_FILE = "_statistic.txt";

    public void handleFile(String fileLocation, WordsStorage wordsStorage) {
        Map<String, Long> result = readFile(fileLocation, wordsStorage);
        writeFile(fileLocation, result, wordsStorage);
    }

    private void writeFile(String fileLocation, Map<String, Long> result, WordsStorage wordsStorage) {
        try (var writer = newBufferedWriter(Path.of(fileLocation + OUTPUT_FILE))) {
            for (Map.Entry<String, Long> element : result.entrySet()) {
                writer.write(element.getKey() + " : " + element.getValue());
                writer.newLine();
            }
            writer.write("Total amount of distinct words:" + wordsStorage.getDistinctWordsCounter());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Long> readFile(String fileLocation, WordsStorage wordsStorage) {
        try (var reader = newBufferedReader(Path.of((fileLocation)))) {
            List<String> bookStrings = new ArrayList<>();
            String lineToRead = reader.readLine();

            while (lineToRead != null) {
                Matcher matcher = PATTERN.matcher(lineToRead);
                while (matcher.find()) {
                    bookStrings.add(matcher.group().toLowerCase());
                }
                lineToRead = reader.readLine();
            }
            return wordsStorage.processWords(bookStrings);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
