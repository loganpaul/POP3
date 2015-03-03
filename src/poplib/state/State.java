package poplib.state;

public interface State {

    public void run();

    public StateException getError();
}
