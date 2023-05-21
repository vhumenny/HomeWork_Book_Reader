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
    private final File fileLocation = new File(
            "C:\\Users\\Admin\\dev\\HomeWork\\src\\main\\resources\\bookCatalog.json");

    public Map<String, Book> readFile() {
        Map<String, Book> bookMap = new HashMap<>();
        try {
            List<Book> bookList = objectMapper.readValue(fileLocation, new TypeReference<>() {
            });
            bookList.forEach(book -> bookMap.put(book.getBookName(), book));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookMap;
    }

    public void writeFile(List<Book> bookList) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileLocation, bookList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
