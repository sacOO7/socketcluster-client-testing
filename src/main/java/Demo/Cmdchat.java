package Demo;

import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import io.github.sac.BasicListener;
import io.github.sac.Emitter;
import io.github.sac.Socket;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by sachin on 12/12/16.
 */
public class Cmdchat {

    enum e{
        MESSAGE,
        USER
    }
    public static String url="ws://localhost:8000/socketcluster/";
    private static String user;

    public static void main(String arg[]) throws JSONException, InterruptedException {

        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter username ::");
        user=scanner.nextLine();

        Socket socket = new Socket(url);

        socket.setListener(new BasicListener() {
            public void onConnected(Socket socket, Map<String, List<String>> headers) {
                socket.createChannel("MyclassRoom").subscribe();
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
        socket.disableLogging();

        socket.onSubscribe("MyclassRoom", new Emitter.Listener() {
            @Override
            public void call(String name, Object data) {
                JSONObject object= (JSONObject) data;
                try {
                    String username=object.getString("user");
                    String message=object.getString("message");
                    if (e.MESSAGE == object.get("type")){
                        System.out.println(username+" :: "+message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        while (true){
            String message=scanner.nextLine();
            JSONObject object=new JSONObject();
            object.put("user",user);
            object.put("message",message);
            object.put("type",e.MESSAGE);

            socket.getChannelByName("MyclassRoom").publish(object);
        }

    }
}
