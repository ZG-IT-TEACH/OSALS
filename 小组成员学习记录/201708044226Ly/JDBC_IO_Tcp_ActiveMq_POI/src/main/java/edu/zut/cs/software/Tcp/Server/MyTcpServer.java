package edu.zut.cs.software.Tcp.Server;
//服务端程序

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyTcpServer {
    private BufferedReader reader;
    private PrintWriter writer;
    private ServerSocket server;
    private Socket socket;
    private TcpServerThread_Listen tcpServerThreadListen;
    private TcpServerThread_Send tcpServerThread_send;
    private TcpServerChatThread_Listen tcpServerChatThread_listen;
    private static Integer number = 99;
    private String sign = null;
    void getserver() {
        try {
            server = new ServerSocket(8866);
            System.out.println("服务器套接字已经创建成功");
            while(true) {
                System.out.println("等待客户机的连接");
                socket = server.accept();	//阻塞的

                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                sign = reader.readLine();
                number  = Integer.parseInt(sign);

                /*
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                String date = simpleDateFormat.format(calendar.getTime());

                System.out.println("客户端信息接收："+ reader.readLine());
                */

                if(number == 1) {
                    tcpServerThreadListen = new TcpServerThread_Listen(socket);
                }
                else if(number == 8) {
                    tcpServerChatThread_listen = new TcpServerChatThread_Listen(socket);
                }
                else {
                    /*谨记此处为何null异常
                    String queueName = reader.readLine();
                    System.out.println(queueName);
                    */
                    tcpServerThread_send = new TcpServerThread_Send(socket);
                }
                //number++;
                //reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //writer = new PrintWriter(socket.getOutputStream(),true);
                //getClientMessage();
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*private void getClientMessage(){
        try{
            while(true) {
                System.out.println("客户端信息接收："+ reader.readLine());
                writer.println(reader.readLine());
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        try{
            if(reader != null) {
                reader.close();
            }
            if(writer != null) {
                writer.close();
            }
            if(socket != null) {
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    */
    public static void main(String[] args) {
        MyTcpServer tcpserv = new MyTcpServer();
        tcpserv.getserver();
    }
}


