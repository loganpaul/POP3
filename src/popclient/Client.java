package popclient;

import java.io.IOException;
import java.net.UnknownHostException;

import popclient.factory.ClientStateFactory;
import poplib.service.DeliveryService;
import poplib.state.State;

public class Client {

	DeliveryService deliveryService;
	ClientStateFactory clientStateFactory;
	
	public Client() {
		
	}
	
	public State init() throws UnknownHostException, IOException {
		clientStateFactory = new ClientStateFactory();
		return clientStateFactory.nextState(null);
	}
	
	public void exec() {
		State state = null;
		
		try {
			state = init();
			
			while(null != state) {
				state.run();
				state = clientStateFactory.nextState(state);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("--- Terminated ---");
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.exec();
	}
}
