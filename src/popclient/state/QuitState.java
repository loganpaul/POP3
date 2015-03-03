package popclient.state;

import java.io.IOException;

import poplib.command.Command;
import poplib.command.CommandQuit;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;
import poplib.state.StateException;

public class QuitState extends AbstractState {

	public QuitState(DeliveryService deliveryService) {
		super(deliveryService);
	}

	@Override
	public void run() {
		System.out.println(" --- Quit --- ");

		try {
			CommandQuit command = new CommandQuit();
			deliveryService.send(command);
			Command response = deliveryService.receive();
		} catch (IOException e) {
			setError(new StateException(e));
			getError().printStackTrace();
		}
	}
}
