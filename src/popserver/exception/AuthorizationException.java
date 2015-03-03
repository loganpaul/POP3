package popserver.exception;

import poplib.command.Command;
import poplib.state.StateException;

public class AuthorizationException extends StateException {

    /**
     *
     */
    private static final long serialVersionUID = 2995975554229057502L;

    public AuthorizationException() {
        super();
    }

    public AuthorizationException(Command command) {
        super(command);
    }

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Command command) {
        super(message, command);
    }

    public AuthorizationException(Throwable throwable) {
        super(throwable);
    }

    public AuthorizationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
