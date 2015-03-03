package poplib.service;

import poplib.command.Command;

import java.io.IOException;

public interface DeliveryService {
    
    public void send(Command command) throws IOException;

    public Command receive() throws IOException;
}
