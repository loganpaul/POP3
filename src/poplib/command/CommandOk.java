package poplib.command;

public class CommandOk extends Command {

    public static final String COMMAND_NAME = "+OK";

    protected String comment;

    public CommandOk() {
        super(COMMAND_NAME);
    }

    public CommandOk(String comment) {
        super(COMMAND_NAME);
        this.comment = comment;
    }

    @Override
    public String toString() {
        return super.toString() + " " + comment;
    }

    public String getComment() {
        return comment;
    }

    public CommandOk setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
