package poplib.command;

public class CommandQuit extends Command {

    public static final String COMMAND_NAME = "QUIT";

    public CommandQuit() {
        super(COMMAND_NAME);
    }
}
