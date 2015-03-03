package popclient.exception;

import poplib.command.Command;
import poplib.state.StateException;

public class ConnectionException extends StateException {

    /**
     *
     */
    private static final long serialVersionUID = -4493380404757653042L;

    public ConnectionException() {
        super();
    }

    public ConnectionException(Command command) {
        super(command);
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Command command) {
        super(message, command);
    }

    public ConnectionException(Throwable throwable) {
        super(throwable);
    }

    public ConnectionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
