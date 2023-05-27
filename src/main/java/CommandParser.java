public class CommandParser {

    public Commands parseCommand(String title) {
        for (Commands command : Commands.values()) {
            if (command.getTitle().equalsIgnoreCase(title)) {
                return command;
            }
        }
        return Commands.NOT_FOUND;
    }
}
