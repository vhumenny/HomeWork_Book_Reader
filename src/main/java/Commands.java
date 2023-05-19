public enum Commands {
    PARSE("parse"),
    WRITE_STATISTIC("write statistic"),
    PRINT_STATISTIC("print statistic"),
    READ_CATALOG("read catalog"),
    ADD_BOOK("add book"),
    DELETE_BOOK("delete book"),
    EXIT("exit"),
    NOT_FOUND("Not found");

    private final String title;

    Commands(String title) {
        this.title = title;
    }

    public static Commands fromTitle(String title) {
        for (Commands command : values()) {
            if (command.title.equals(title)) {
                return command;
            }
        }
        return NOT_FOUND;
    }

    public String getTitle() {
        return title;
    }
}
