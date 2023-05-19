package fileHandlers;

import java.util.List;

public interface FileHandler {

    List<String> readFile(String location);

    void writeFile(String resultString);
}
