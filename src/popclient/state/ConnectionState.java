package popclient.state;

import popclient.exception.ConnectionException;
import poplib.Protocol;
import poplib.command.Command;
import poplib.command.CommandOk;
import poplib.service.DeliveryService;
import poplib.service.impl.DeliveryServiceImpl;
import poplib.state.AbstractState;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectionState extends AbstractState {

    public int tryCount;

    public ConnectionState() {
        super(null);
        tryCount = 0;
    }

    public void run() {
        System.out.println("Demande de connexion n°" + (tryCount + 1));
        Socket socket;
        try {
            socket = new Socket(InetAddress.getByName(Protocol.HOST_ADDRESS), Protocol.LISTENING_PORT);

            deliveryService = new DeliveryServiceImpl(socket);

            Command command = deliveryService.receive();
            System.out.println("Command received: " + command);

            if(command instanceof CommandOk) {
                CommandOk commandOk = (CommandOk) command;
                System.out.println(commandOk.getComment());
            } else {
                setError(new ConnectionException("Bad server response", command));
            }
        } catch(IOException e) {
            setError(new ConnectionException(e));
        }
    }

    public ConnectionState reset() {
        tryCount++;
        if(tryCount > 2) {
            return null;
        } else {
            return this;
        }
    }

    public DeliveryService getDeliveryService() {
        return deliveryService;
    }
}
