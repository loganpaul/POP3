package poplib.command;

public class CommandRetr extends Command {

    public static final String COMMAND_NAME = "RETR";

    private int idMessage;

    public CommandRetr(int idMessage) {
        super(COMMAND_NAME);
        this.idMessage = idMessage;
    }

    @Override
    public String toString() {
        return super.toString() + " " + Integer.toString(idMessage);
    }

    public int getIdMessage() {
        return idMessage;
    }

    public CommandRetr setIdMessage(int idMessage) {
        this.idMessage = idMessage;
        return this;
    }
}
