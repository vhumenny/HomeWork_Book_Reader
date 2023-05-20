public enum Commands {
    WRITE_STATISTIC("write"),
    PRINT_STATISTIC("print"),
    READ_CATALOG("read"),
    ADD_BOOK("add"),
    DELETE_BOOK("delete"),
    EXIT("exit"),
    NOT_FOUND("Not found");

    private final String title;

    Commands(String title) {
        this.title = title;
    }

    public static Commands fromTitle(String title) {
        for (Commands command : values()) {
            if (command.title.equalsIgnoreCase(title)) {
                return command;
            }
        }
        return NOT_FOUND;
    }
}
