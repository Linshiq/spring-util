package com.lsq.demo.io.bio;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * /**
 *  * BIO : 阻塞IO
 *  * 单线程IO沟通，且必须呼叫方必须有响应后调用方才允许有操作
 *  *
 *  * 1、可以使用cmd命令，然后输入 telnet localhost 8888 （8888为端口） 进行交互
 *  * 2、可以启动多个
 *    3、以下是多线程方式启动
 *  */

public class MoreThreahBioService {

    public static void main(String[] args) {
        // 使用线程池方式启动BIO，这样最多有3（因为设置的是3）交互同时在请求，多余的则在队列中进行排队
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 定义监听端口
        try(ServerSocket serverSocket = new ServerSocket(8888)){
            System.out.println("BioService has start,listening on port:"+serverSocket.getLocalSocketAddress());
            while (true){
                Socket clientSocket = serverSocket.accept();
                // 利用多線程的方式啟動
                executorService.submit(new ClientHandle(clientSocket));
                System.out.println("Connetion from"+clientSocket.getRemoteSocketAddress());
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 定义个线程来处理
     */
    public static class ClientHandle implements Runnable{

        public Socket clientSocket;

        public ClientHandle(Socket clientSocket){
            this.clientSocket = clientSocket;
        }

        @Override
        public void run(){
            try(Scanner input = new Scanner(clientSocket.getInputStream())){
                // 针对每个Socket,不断在交互
                while(true){
                    // 若交互送来的是out则退出
                    String request = input.nextLine();
                    if ("out".equals(request)){
                        break;
                    }
                    System.out.println(String.format("From %s : %s",clientSocket.getRemoteSocketAddress(),request));
                    String response = "这是响应给你的信息\n";
                    clientSocket.getOutputStream().write(response.getBytes());
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
