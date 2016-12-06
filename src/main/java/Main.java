/**
 * Created by sachin on 5/12/16.
 */


import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import io.github.sac.Ack;
import io.github.sac.BasicListener;
import io.github.sac.Emitter;
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
                System.out.println("Disconnected from end-point");

            }

            public void onConnectError(Socket socket, WebSocketException exception) {
                System.out.println("Got connect error "+ exception);

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

//        socket.emit("chat","Hi");

        socket.emit("chat", "Hi", new Ack() {
            @Override
            public void call(String eventName, Object error, Object data) {
                System.out.println("Got message for :"+eventName+" error is :"+error+" data is :"+data);
            }
        });

//        socket.on("yell", new Emitter.Listener() {
//            @Override
//            public void call(String eventName, Object data) {
//                System.out.println("Got message for :"+eventName+" data is :"+data);
//            }
//        });

        socket.on("yell", new Emitter.AckListener() {
            @Override
            public void call(String eventName, Object data, Ack ack) {
                System.out.println("Got message for :"+eventName+" data is :"+data);
                //sending ack back

                ack.call(eventName,"This is error","This is data");
            }
        });

        Socket.Channel channel = socket.createChannel("yell");
//
        channel.subscribe(new Ack() {
            @Override
            public void call(String channelName, Object error, Object data) {
                if (error==null){
                    System.out.println("Subscribed to channel "+channelName+" successfully");
                }
            }
        });

        channel.publish("Hi sachin", new Ack() {
            @Override
            public void call(String channelName, Object error, Object data) {
                if (error==null){
                    System.out.println("Published message to channel "+channelName+" successfully");
                }
            }
        });

        channel.onMessage(new Emitter.Listener() {
            @Override
            public void call(String channelName, Object data) {

                System.out.println("Got message for channel "+channelName+" data is "+data);
            }
        });

    }
}
