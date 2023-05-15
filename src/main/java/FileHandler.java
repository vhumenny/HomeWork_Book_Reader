import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Files.newBufferedWriter;

public class FileHandler {

    private static final String OUTPUT_FILE = "_statistic.txt";
    private String fileLocation;

    public void writeFile(String resultString) {
        try (var writer = newBufferedWriter(Path.of(fileLocation + OUTPUT_FILE))) {
            writer.write(resultString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> readFile(String fileLocation) {
        try (var reader = newBufferedReader(Path.of((fileLocation)))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Book doesn't exist!");
        }
    }
}
