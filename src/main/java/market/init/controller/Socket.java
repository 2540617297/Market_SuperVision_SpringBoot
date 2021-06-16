package market.init.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import javax.sound.midi.Soundbank;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat/{userId}")
public class Socket {
    //session为与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private static int onlineCount = 0;
    private static ConcurrentHashMap<String, Socket> webSocketSet = new ConcurrentHashMap<String, Socket>();
    private Session WebSocketsession;
    private String userId = "";

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数
     * @throws Exception
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String param) throws Exception {
        this.session = session;
        userId = param;
        webSocketSet.put(param, this);
        addOnlineCount();
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        System.out.println("Open");
    }

    /**
     * 连接关闭调用的方法
     * @throws Exception
     */
    @OnClose
    public void onClose() throws Exception {

        if (!userId.equals("")) {
            webSocketSet.remove(userId); //从set中删除
            subOnlineCount();     //在线数减1
            System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
        }
        System.out.println("Close");
    }

    /**
     * 收到消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     * @throws Exception
     */
    @SuppressWarnings("unused")
    public void onMessage(String message, Session session) throws Exception {

        sendToUser(message);
    }

    /**
     * 发送消息给用户
     * @param message
     */
    @OnMessage
    public void sendToUser(String message) {
        System.out.println("++++++++"+message);
        String sendUserno = message.split("[|]")[1];
        try {
          if (webSocketSet.get(sendUserno) != null) {
            webSocketSet.get(sendUserno).sendMessage(message);
          } else {
            System.out.println("当前用户不在线");
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

    private String getNowTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        return time;
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 发送消息方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);   //向客户端发送数据
    }

    public static synchronized int getOnlineCount() {
    return onlineCount;
  }


    public static synchronized void addOnlineCount() {
    Socket.onlineCount++;
  }


    public static synchronized void subOnlineCount() {
    Socket.onlineCount--;
  }
}
