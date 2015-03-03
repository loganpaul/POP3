package popserver.state;

import poplib.command.Command;
import poplib.command.CommandErr;
import poplib.command.CommandOk;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;
import poplib.state.StateException;
import popserver.service.MailboxService;

import java.io.IOException;

public class UpdateState extends AbstractState {

    private MailboxService mailboxService;

    public UpdateState(DeliveryService deliveryService, MailboxService mailboxService) {
        super(deliveryService);

        this.mailboxService = mailboxService;
    }

    @Override
    public void run() {
        System.out.println(" --- Update State --- ");
        String response = mailboxService.update();
        Command command = null;

        if(null == response) {
            command = new CommandOk();
        } else {
            command = new CommandErr(response);
        }

        try {
            deliveryService.send(command);
        } catch(IOException e) {
            setError(new StateException(e));
            getError().printStackTrace();
        }
    }
}
