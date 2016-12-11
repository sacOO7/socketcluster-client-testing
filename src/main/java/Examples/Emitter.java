package Examples;

import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import io.github.sac.Ack;
import io.github.sac.BasicListener;
import io.github.sac.Socket;
import org.json.JSONException;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by sachin on 10/12/16.
 */
public class Emitter {
    public static String url="ws://localhost:8000/socketcluster/";

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
                socket.setAuthToken(token);
            }
        });

        socket.connect();

        socket.emit("chat","Hi");

        socket.emit("chat", "Hi", new Ack() {
            @Override
            public void call(String name, Object error, Object data) {
                System.out.println("Event ::"+name+" Error ::"+error+" data ::"+data);
            }
        });


    }
}
