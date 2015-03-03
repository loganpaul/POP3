package poplib.service.impl;

import poplib.command.Command;
import poplib.factory.CommandFactory;
import poplib.service.DeliveryService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class DeliveryServiceImpl implements DeliveryService {

    protected CommandFactory commandFactory = new CommandFactory();
    private BufferedReader reader;
    private OutputStreamWriter writer;

    public DeliveryServiceImpl(Socket socket) throws IOException {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new OutputStreamWriter(socket.getOutputStream());
	}

	@Override
	public void send(Command command) throws IOException {
		
		System.out.println("    Send: " + command);
		writer.write(command + "\n");
		writer.write(".\n");
		writer.flush();
	}

	@Override
	public Command receive() throws IOException {
		String line = "";
		String response = "";
		
		line = reader.readLine();
		
		while(!".".equals(line)) {
			response += line + "\n";
			line = reader.readLine();
		}

		Command command = commandFactory.parse(response);
		System.out.println("Received: " + command);
		return command;
	}
}
