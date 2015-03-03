package popclient.state;

import java.util.NoSuchElementException;
import java.util.Scanner;

import poplib.command.Command;
import poplib.command.CommandRetr;
import poplib.command.CommandStat;
import poplib.service.DeliveryService;
import poplib.state.AbstractState;

public class AuthenticatedState extends AbstractState {

	private Scanner scanner;
	private Command commandToSend;
	private boolean quit;

	public AuthenticatedState(DeliveryService deliveryService) {
		super(deliveryService);
		quit = false;
		scanner = new Scanner(System.in);
	}

	@Override
	public void run() {
		System.out.println(" --- Menu --- ");

		commandToSend = null;
		quit = false;

		while (null == commandToSend && !quit) {
			try {
				System.out.println("Choose a command");
				System.out.println("1 - STAT");
				System.out.println("2 - RETR");
				System.out.println("0 - QUIT");
				int response = scanner.nextInt();

				if (1 == response) {
					commandToSend = new CommandStat();
				} else if (2 == response) {
					System.out.println("What is the message id to retrieve ?");
					if (scanner.hasNextInt()) {
						response = scanner.nextInt();
						commandToSend = new CommandRetr(response);
					}
				} else if (0 == response) {
					quit = true;
				}
			} catch (NoSuchElementException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	public Command getCommandToSend() {
		return commandToSend;
	}
	
    public boolean quit() {
        return quit;
    }
}
