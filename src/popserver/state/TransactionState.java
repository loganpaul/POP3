package popserver.state;

import poplib.command.*;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;
import poplib.state.StateException;
import popserver.service.MailboxService;

import java.io.IOException;

public class TransactionState extends AbstractState {

    private MailboxService mailboxService;

    private boolean quit;

    public TransactionState(DeliveryService deliveryService, MailboxService mailboxService) {
        super(deliveryService);

        this.mailboxService = mailboxService;
        quit = false;
    }

    @Override
    public void run() {
        System.out.println(" --- Transaction State --- ");
        try {
            Command command = deliveryService.receive();
            String response = null;
            String error = null;

            if(command instanceof CommandQuit) {
                quit = true;
            }

            if(command instanceof CommandQuit) {
                quit = true;
            } else if(command instanceof CommandStat) {
                response = mailboxService.statistics();
            } else if(command instanceof CommandRetr) {
            	try {
            		response = mailboxService.retrieve(((CommandRetr) command).getIdMessage());
            	} catch (IndexOutOfBoundsException e) {
            		error = "";
            	}
            }

			if (!quit) {
				Command commandToSent = null;
				if (null == response) {
					commandToSent = new CommandErr();
				} else {
					commandToSent = new CommandOk(response);
				}
				deliveryService.send(commandToSent);
			}
		} catch (IOException e) {
			setError(new StateException(e));
			getError().printStackTrace();
		}
	}

    public boolean quit() {
        return quit;
    }
}
