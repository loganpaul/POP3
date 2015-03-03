package poplib.state;

import poplib.command.Command;

public class StateException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -4329934170154908966L;

    private Command command;

    public StateException() {
    }

    public StateException(Command command) {
        super(command.toString());
        this.command = command;
    }

    public StateException(String message) {
        super(message);
    }

    public StateException(String message, Command command) {
        super(message + " - Command: " + command);
        this.command = command;
    }

    public StateException(Throwable throwable) {
        super(throwable);
    }

    public StateException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
