package popserver;


import poplib.factory.StateFactory;
import poplib.service.impl.DeliveryServiceImpl;
import popserver.factory.ServerStateFactory;

import java.io.IOException;
import java.net.Socket;

public class Session extends Thread {

    private Socket socket;
    private StateFactory stateFactory;
    private poplib.state.State state;

    public Session(Socket s) {
        System.out.println("Client connected");
        this.socket = s;
        try {
            this.stateFactory = new ServerStateFactory(new DeliveryServiceImpl(socket));
            this.state = stateFactory.nextState(null);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        while(state != null) {
            state.run();
            state = stateFactory.nextState(state);
        }
    }
}
