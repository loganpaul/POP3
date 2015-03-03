package poplib.factory;

import poplib.state.State;

public interface StateFactory {

    public State nextState(State state);
}
