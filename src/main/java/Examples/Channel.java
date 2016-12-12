package Examples;

import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import io.github.sac.*;
import io.github.sac.Emitter;
import org.json.JSONException;

import java.util.List;
import java.util.Map;

/**
 * Created by sachin on 10/12/16.
 */
public class Channel {
    public static String url="ws://localhost:8000/socketcluster/";

    public static void main(String arg[]) throws JSONException, InterruptedException {

        Socket socket = new Socket(url);

        socket.setListener(new BasicListener() {
            public void onConnected(Socket socket, Map<String, List<String>> headers) {
                System.out.println("Connected to server");

                /**
                 *  Code to subscribe and publish message
                 */

                socket.createChannel("room").subscribe();
                socket.getChannelByName("room").publish("Hi everyone present in this room");
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
        socket.disableLogging();


//        socket.getChannelByName("room").onMessage(new Emitter.Listener() {
//            @Override
//            public void call(String name, Object data) {
//
//            }
//        });

        socket.onSubscribe("room", new Emitter.Listener() {
            @Override
            public void call(String name, Object data) {
                System.out.printf("Got message for Channel"+name+" Message is ::"+data);
            }
        });



    }
}
