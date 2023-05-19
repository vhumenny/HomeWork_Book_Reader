package fileHandlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Book;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class JsonFileHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String fileLocation = "C:\\Users\\Admin\\dev\\HomeWork\\src\\main\\resources\\bookCatalog.json";

    public Map<String, Book> readFile() {
        Map<String, Book> bookMap = new HashMap<>();
        try {
            List<Book> bookList = objectMapper.readValue(new File(fileLocation), new TypeReference<>(){});
            bookList.forEach(book -> bookMap.put(book.getBookName(), book));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookMap;
    }

    public void writeFile(Map<String, Book> bookMap) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileLocation), bookMap.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
