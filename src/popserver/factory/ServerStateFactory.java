package popserver.factory;

import poplib.factory.StateFactory;
import poplib.service.DeliveryService;
import poplib.state.State;
import poplib.state.StateException;
import popserver.service.MailboxService;
import popserver.service.impl.MailboxServiceImpl;
import popserver.state.AuthorizationState;
import popserver.state.TransactionState;
import popserver.state.UpdateState;

public class ServerStateFactory implements StateFactory {

    private DeliveryService deliveryService;
    private MailboxService mailboxService;

    public ServerStateFactory(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        this.mailboxService = new MailboxServiceImpl();
    }

    @Override
    public State nextState(State state) {
        State next = null;

        if(state == null) {
            return new AuthorizationState(deliveryService);
        } else if(state instanceof AuthorizationState) {
            StateException error = state.getError();
            if(null != error) {
                next = state;
            } else {
                next = new TransactionState(deliveryService, mailboxService);
            }
        } else if(state instanceof TransactionState) {
            StateException error = state.getError();
            TransactionState transactionState = (TransactionState) state;
            if(null != error) {
                next = null;
            } else if(transactionState.quit()) {
                next = new UpdateState(deliveryService, mailboxService);
            } else {
                next = state;
            }
        } else if(state instanceof UpdateState) {
            next = null;
        }

        return next;
    }

    public DeliveryService getDeliveryService() {
        return deliveryService;
    }

    public void setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }
}
