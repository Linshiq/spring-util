package com.lsq.demo.io.bio;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientTest {
    private static final int port = 8080;
    private static final String address = "localhost";


    public static void main(String[] args) throws IOException, InterruptedException {
        // 此类型仅会建立一次通讯，服务端断了，这边发送的消息似乎送达不了服务端？
        Socket socket = new Socket(address, port);
        socket.setKeepAlive(true);
        ObjectOutputStream oos = null;
        int count = 0;

        while(true){
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("Hello world" + count);
            oos.flush();
            System.out.println(count);
            Thread.sleep(100);
            if (count++ == 100){
                break;
            }
        }
        System.out.println("loop end");
    }

}
