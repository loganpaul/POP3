package popserver;

import poplib.Protocol;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private int sessionCount;
    private boolean run;

    public Server(int sessionCount) {
        this.sessionCount = sessionCount;
    }

    public static void main(String[] args) {
        new Server(5).run();
    }

    public void run() {
        ServerSocket s;
        run = true;
        try {
            s = new ServerSocket(Protocol.LISTENING_PORT, sessionCount);
            while(run) {
                new Session(s.accept()).start();
            }
            s.close();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
