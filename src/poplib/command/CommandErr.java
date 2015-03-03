package poplib.command;

public class CommandErr extends Command {

    public static final String COMMAND_NAME = "ERR";

    private String comment;

    public CommandErr() {
        this("");
    }

    public CommandErr(String comment) {
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

    public CommandErr setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
