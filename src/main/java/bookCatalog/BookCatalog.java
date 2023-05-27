package bookCatalog;

import bookCatalog.entity.Book;
import bookCatalog.fileHandlers.JsonFileHandler;
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
            System.out.println(book.getBookName() + " has already been added in catalog!");
        else {
            bookMap.put(book.getBookName(), book);
            System.out.println(book.getBookName() + " was added to catalog.");
        }
    }

    public void removeBook(String bookName) {
        if (bookMap.containsKey(bookName)) {
            bookMap.remove(bookName);
            System.out.println(bookName + " was removed from catalog.");
        } else System.out.println("There is no book " + bookName + " in catalog!");
    }

    public void saveCatalog() {
        fileHandler.writeFile(bookMap.values().stream().toList());
    }

    public Book getBook(String bookName) {
        if (bookMap.containsKey(bookName)) return bookMap.get(bookName);
        else throw new IllegalArgumentException("There is no book " + bookName + " in catalog!");
    }
}
