package fileHandlers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Files.newBufferedWriter;

public class TxtFileHandler {

    private static final String OUTPUT_FILE = "_statistic.txt";
    private String fileLocation;

    public void writeFile(String resultString) {
        try (var writer = newBufferedWriter(Path.of(fileLocation + OUTPUT_FILE))) {
            writer.write(resultString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readFile(String location) {
        fileLocation = location;
        try (var reader = newBufferedReader(Path.of((fileLocation)))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Book doesn't exist!" + e);
        }
    }
}
