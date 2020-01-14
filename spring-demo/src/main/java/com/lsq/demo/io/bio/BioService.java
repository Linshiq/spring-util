package com.lsq.demo.io.bio;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

/**
 * BIO : 阻塞IO
 * 单线程IO沟通，且必须呼叫方必须有响应后调用方才允许有操作
 *
 * 1、可以使用cmd命令，然后输入 telnet localhost 8888 （8888为端口） 进行交互
 * 2、可以启动多个，但下面这个因为是单线程，所以，第2个会被阻塞
 */
public class BioService {

    public static void main(String[] args) {
        // 定义监听端口
        try(ServerSocket serverSocket = new ServerSocket(8888)){
            System.out.println("BioService has start,listening on port:"+serverSocket.getLocalSocketAddress());

            while (true){
                Socket clientSocket = serverSocket.accept();
                try(Scanner input = new Scanner(clientSocket.getInputStream())){
                    // 针对每个Socket,不断在交互
                    while(true){
                        // 若交互送来的是out则退出
                        String request = input.nextLine();
                        if ("out".equals(request)){
                            break;
                        }
                        System.out.println(String.format("From %s : %s",clientSocket.getRemoteSocketAddress(),request));
                        String response = "this is to you infomation" + UUID.randomUUID() +"\n";
                        clientSocket.getOutputStream().write(response.getBytes());
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        } catch (Exception e){
          e.printStackTrace();
        }
    }
}
