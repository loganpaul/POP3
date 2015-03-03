package poplib.command;

public class CommandOkStat extends CommandOk {

    private int mailCount;
    private int mailboxSize;

    public CommandOkStat(int mailCount, int mailboxSize) {
        this(mailCount, mailboxSize, "");
    }

    public CommandOkStat(int mailCount, int mailboxSize, String comment) {
        super(comment);
        this.mailCount = mailCount;
        this.mailboxSize = mailboxSize;
    }

    @Override
    public String toString() {
        return command + " " + mailCount + " " + mailboxSize + " " + comment;
    }

    public int getMailCount() {
        return mailCount;
    }

    public int getMailboxSize() {
        return mailboxSize;
    }
}

