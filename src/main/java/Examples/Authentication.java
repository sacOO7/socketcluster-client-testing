package Examples;

import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import io.github.sac.BasicListener;
import io.github.sac.Socket;
import org.json.JSONException;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sachin on 10/12/16.
 */
public class Authentication  {

    private static String url="ws://localhost:8000/socketcluster/";

    public static void main(String arg[]) throws JSONException, InterruptedException {

        Socket socket = new Socket(url);

        socket.setListener(new BasicListener() {
            public void onConnected(Socket socket, Map<String, List<String>> headers) {

                System.out.println("Connected to server");
            }

            public void onDisconnected(Socket socket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) {
                System.out.println("Disconnected from end-point");

            }

            public void onConnectError(Socket socket, WebSocketException exception) {
                System.out.println("Got connect error " + exception);

            }

            public void onAuthentication(Socket socket, Boolean status) {
                if (status) {
                    System.out.println("socket is authenticated");
                } else {
                    System.out.println("Authentication is required (optional)");
                }

            }

            public void onSetAuthToken(String token, Socket socket) {
                System.out.println("Got auth token :: "+ token );
                socket.setAuthToken(token);
            }
        });

        socket.connect();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                socket.disconnect();
            }
        },2000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                socket.connect();
            }
        },4000);
    }
}
