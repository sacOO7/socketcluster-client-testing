/**
 * Created by sachin on 5/12/16.
 */


import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import io.github.sac.BasicListener;
import io.github.sac.Emitter;
import io.github.sac.Socket;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.List;
import java.util.Map;

/**
 * Created by sachin on 8/11/16.
 */

public class Main {

//    public void nativeKeyPressed(NativeKeyEvent e) {
//        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
//
//        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
//            try {
//                GlobalScreen.unregisterNativeHook();
//            } catch (NativeHookException e1) {
//                e1.printStackTrace();
//            }
//        }
//    }
//
//    public void nativeKeyReleased(NativeKeyEvent e) {
//        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
//    }
//
//    public void nativeKeyTyped(NativeKeyEvent e) {
//        System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
//    }

//    public static String url="ws://localhost:8000/socketcluster/";
//
//    public static void main(String arg[]) throws JSONException, InterruptedException {
//
//        Socket socket = new Socket(url);
//
//        socket.setListener(new BasicListener() {
//            public void onConnected(Socket socket, Map<String, List<String>> headers) {
//                System.out.println("Connected to server");
//            }
//
//            public void onDisconnected(Socket socket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) {
//                System.out.println("Disconnected from end-point");
//
//            }
//
//            public void onConnectError(Socket socket, WebSocketException exception) {
//                System.out.println("Got connect error "+ exception);
//
//            }
//
//            public void onAuthentication(Socket socket, Boolean status) {
//                if (status) {
//                    System.out.println("socket is authenticated");
//                } else {
//                    System.out.println("Authentication is required (optional)");
//                }
//
//            }
//
//            public void onSetAuthToken(String token, Socket socket) {
//                socket.setAuthToken(token);
//            }
//        });
//
//        socket.connect();

//        socket.createChannel("Myclassroom").subscribe();
//        JSONObject object=new JSONObject();
//        object.put("message","sachin");
//        object.put("data","Hi gandu");
//        socket.getChannelByName("Myclassroom").publish(object);
//
//        socket.getChannelByName("Myclassroom").onMessage(new Emitter.Listener() {
//            @Override
//            public void call(String name, Object data) {
//                System.out.println("Got data "+data.toString());
//            }
//        });


//        System.out.print("Hello");
//        Thread.sleep(1000);
//        System.out.print("\b\b\b\b");
//        socket.emit("chat","Hi");

//        socket.emit("chat", "Hi", new Ack() {
//            @Override
//            public void call(String eventName, Object error, Object data) {
//                System.out.println("Got message for :"+eventName+" error is :"+error+" data is :"+data);
//            }
//        });
//
////        socket.on("yell", new Emitter.Listener() {
////            @Override
////            public void call(String eventName, Object data) {
////                System.out.println("Got message for :"+eventName+" data is :"+data);
////            }
////        });
//
//        socket.on("yell", new Emitter.AckListener() {
//            @Override
//            public void call(String eventName, Object data, Ack ack) {
//                System.out.println("Got message for :"+eventName+" data is :"+data);
//                //sending ack back
//
//                ack.call(eventName,"This is error","This is data");
//            }
//        });
//
//        Socket.Channel channel = socket.createChannel("yell");
////
//        channel.subscribe(new Ack() {
//            @Override
//            public void call(String channelName, Object error, Object data) {
//                if (error==null){
//                    System.out.println("Subscribed to channel "+channelName+" successfully");
//                }
//            }
//        });
//
//        channel.publish("Hi sachin", new Ack() {
//            @Override
//            public void call(String channelName, Object error, Object data) {
//                if (error==null){
//                    System.out.println("Published message to channel "+channelName+" successfully");
//                }
//            }
//        });
//
//        channel.onMessage(new Emitter.Listener() {
//            @Override
//            public void call(String channelName, Object data) {
//
//                System.out.println("Got message for channel "+channelName+" data is "+data);
//            }
//        });
//        try {
//            GlobalScreen.registerNativeHook();
//        }
//        catch (NativeHookException ex) {
//            System.err.println("There was a problem registering the native hook.");
//            System.err.println(ex.getMessage());
//
//            System.exit(1);
//        }
//
//        GlobalScreen.addNativeKeyListener(new Main());
//    }

}
