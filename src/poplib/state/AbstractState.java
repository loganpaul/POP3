package poplib.state;

import poplib.service.DeliveryService;

public abstract class AbstractState implements State {

    protected DeliveryService deliveryService;
    private StateException error;

    public AbstractState(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    public StateException getError() {
        return error;
    }

    public void setError(StateException error) {
        this.error = error;
    }
}
