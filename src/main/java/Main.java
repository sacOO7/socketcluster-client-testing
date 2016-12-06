/**
 * Created by sachin on 5/12/16.
 */
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import io.github.sac.BasicListener;
import io.github.sac.Socket;

import java.util.List;
import java.util.Map;

/**
 * Created by sachin on 8/11/16.
 */

public class Main {

    public static String url="ws://localhost:8000/socketcluster/";

    public static void main(String arg[]) {

        Socket socket = new Socket(url);

        socket.setListener(new BasicListener() {
            public void onConnected(Socket socket, Map<String, List<String>> headers) {
                System.out.println("Connected to server");
            }

            public void onDisconnected(Socket socket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) {

            }

            public void onConnectError(Socket socket, WebSocketException exception) {

            }

            public void onAuthentication(Socket socket, Boolean status) {

            }

            public void onSetAuthToken(String token, Socket socket) {

            }
        });

        socket.connect();

    }
}
