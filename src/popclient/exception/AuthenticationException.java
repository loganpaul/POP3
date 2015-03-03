package popclient.exception;

import poplib.command.Command;
import poplib.state.StateException;

public class AuthenticationException extends StateException {

    /**
     *
     */
    private static final long serialVersionUID = 2995975554229057502L;

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(Command command) {
        super(command);
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Command command) {
        super(message, command);
    }

    public AuthenticationException(Throwable throwable) {
        super(throwable);
    }

    public AuthenticationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
