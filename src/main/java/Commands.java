import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Commands {
    BOOKS("books"),
    QUIZ("quiz"),
    WRITE_STATISTIC("write"),
    PRINT_STATISTIC("print"),
    READ_CATALOG("read"),
    ADD_BOOK("add"),
    DELETE_BOOK("delete"),
    EXIT("exit"),
    NOT_FOUND("Not found");

    private final String title;
}
