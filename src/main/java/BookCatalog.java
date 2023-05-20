import entity.Book;
import fileHandlers.JsonFileHandler;
import lombok.Data;

import java.util.Map;

@Data
public class BookCatalog {

    private Map<String, Book> bookMap;
    private JsonFileHandler fileHandler;

    public BookCatalog() {
        fileHandler = new JsonFileHandler();
        this.bookMap = fileHandler.readFile();
    }

    public void addBook(Book book) {
        if (bookMap.containsKey(book.getBookName()))
            throw new IllegalArgumentException(book.getBookName() + " has already been added in catalog!");
        else bookMap.put(book.getBookName(), book);
    }

    public void removeBook(String bookName) {
        if (bookMap.containsKey(bookName)) bookMap.remove(bookName);
        else throw new IllegalArgumentException("There is no book " + bookName + " in catalog!");
    }

    public void saveCatalog() {
        fileHandler.writeFile(bookMap);
    }
}
