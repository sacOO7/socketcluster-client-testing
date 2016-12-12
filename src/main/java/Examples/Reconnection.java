package Examples;

import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import io.github.sac.BasicListener;
import io.github.sac.ReconnectStrategy;
import io.github.sac.Socket;
import org.json.JSONException;

import java.util.List;
import java.util.Map;

/**
 * Created by sachin on 10/12/16.
 */
public class Reconnection {

    public static void main(String arg[]) throws JSONException, InterruptedException {

        String url = "ws://localhost:8000/socketcluster/";
        Socket socket = new Socket(url);

        socket.setListener(new BasicListener() {
            public void onConnected(Socket socket, Map<String, List<String>> headers) {
                System.out.println("Connected to server");
            }

            public void onDisconnected(Socket socket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) {
                System.out.println("Disconnected from end-point");

            }

            public void onConnectError(Socket socket, WebSocketException exception) {
                System.out.println("Connect error found" + exception);

            }

            public void onAuthentication(Socket socket, Boolean status) {
                if (status) {
                    System.out.println("socket is authenticated");
                } else {
                    System.out.println("Authentication is required (optional)");
                }

            }

            public void onSetAuthToken(String token, Socket socket) {
                System.out.println("Got auth token :: " + token);
                socket.setAuthToken(token);
            }
        });

//        socket.setReconnection(null);
        socket.setReconnection(new ReconnectStrategy().setDelay(3000).setMaxAttempts(7));
        socket.connect();

    }
}