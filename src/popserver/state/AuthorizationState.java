package popserver.state;

import poplib.command.Command;
import poplib.command.CommandApop;
import poplib.command.CommandErr;
import poplib.command.CommandOk;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;
import popserver.exception.AuthorizationException;
import popserver.service.MailboxService;
import popserver.service.impl.MailboxServiceImpl;

import java.io.IOException;

public class AuthorizationState extends AbstractState {

    MailboxService mailboxService = new MailboxServiceImpl();

    public AuthorizationState(DeliveryService deliveryService) {
        super(deliveryService);
    }

    @Override
    public void run() {
        System.out.println(" --- Authorization State --- ");
        try {
            String timestamp = mailboxService.getTimestamp();
            Command command = new CommandOk("server ready" + timestamp);
            deliveryService.send(command);
            command = deliveryService.receive();
            if(command instanceof CommandApop) {
                CommandApop commandApop = (CommandApop) command;
                if(mailboxService.checkAuthentication((CommandApop) command, timestamp)) {
                    System.out.println("User authenticated.");
                    command = new CommandOk("maildrop has " + mailboxService.getMailCount() + " message(s) (" + mailboxService.getMailSize() + ")");
                } else {
                    command = new CommandErr("permission denied for " + commandApop.getMailbox() + " maildrop.");
                    setError(new AuthorizationException(command));
                }
            }
            deliveryService.send(command);
        } catch(IOException e) {
            setError(new AuthorizationException(e));
            getError().printStackTrace();
        }
    }
}
