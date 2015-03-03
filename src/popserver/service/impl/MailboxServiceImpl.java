package popserver.service.impl;

import poplib.command.CommandApop;
import popserver.service.MailboxService;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MailboxServiceImpl implements MailboxService{

	@Override
	public String statistics() {
		return "3 480";
	}

	@Override
	public String retrieve(int id) {
		if(id > 2) {
			throw new IndexOutOfBoundsException();
		}
		return id + " 120\n" +
				"from: pierre.binauld@gmail.com\r\n" +
				"to: thomas.richard@gmail.com\r\n" +
				"Object: IGP POP3\r\n" +
				"\r\n" +
				"Contenu du message";
	}

	@Override
	public String update() {
		return null;
	}

    @Override
    public String getTimestamp() {
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch(UnknownHostException e) {
            hostname = "localhost";
        }
        return "<" + Thread.currentThread().getId() + "." +
                System.currentTimeMillis() + "@" +
                hostname + ">";
    }

    @Override
    public boolean checkAuthentication(CommandApop command, String timestamp) {
        return true;
    }

    @Override
    public int getMailCount() {
        return 3;
    }

    @Override
    public int getMailSize() {
        return 480;
    }
}
