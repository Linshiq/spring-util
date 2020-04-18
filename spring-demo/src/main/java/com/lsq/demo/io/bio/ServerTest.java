package com.lsq.demo.io.bio;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

public class ServerTest {
    private static final int port =8080;


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        test2();
    }

    /**
     * 有交互的BIO
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static void test1() throws IOException, ClassNotFoundException{
        ServerSocket serverSocket = new ServerSocket(port);
        while(true){
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

    }

    /**
     * 无交互的BIO
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static void test2() throws IOException, ClassNotFoundException{
        ServerSocket serverSocket = new ServerSocket(port);
        while(true){
            Socket clientSocket = serverSocket.accept();
            ObjectInputStream str = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("响应消息：" + str.readObject());
        }

    }
}
