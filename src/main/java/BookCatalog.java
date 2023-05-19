import entity.Book;
import fileHandlers.JsonFileHandler;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BookCatalog {

    private Map<String, Book> bookMap;
    private JsonFileHandler fileHandler;

    public BookCatalog() {
        fileHandler = new JsonFileHandler();
        this.bookMap = fileHandler.readFile();

    }

    public Book addBook(List<String> strings) {
        Book book = createBook(strings);
        bookMap.put(book.getBookName(), book);
        return book;
    }

    private Book createBook(List<String> strings) {
        String bookName = strings.get(0);
        String author = strings.get(2).replaceAll("by", "").stripLeading();
        strings.subList(0, 4).clear();
        return new Book(bookName, author, strings);
    }

    public Book removeBook(String bookName) {
        return bookMap.remove(bookName);
    }


}
