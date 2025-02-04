package edu.zut.cs.software.Tcp.Server;

import edu.zut.cs.software.ActiveMQ.topic.ActiveMq_Topic_Consumer;
import edu.zut.cs.software.ActiveMQ.topic.ActiveMq_Topic_Consumer_Demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpServerThread_Send extends Thread {

    private Socket socket;
    private String activeMQ_Name;
    private BufferedReader reader;
    private PrintWriter writer;

    public TcpServerThread_Send(Socket socket) {
        super();
        this.socket = socket;
        start();
    }
    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(),true);

            activeMQ_Name = reader.readLine();
            System.out.println(activeMQ_Name);

            System.out.println("TcpServerThread_Send客户端信息接收："+ reader.readLine());

            /*
            ActiverMq_Consumer_Demo activerMq_consumer_demo = new ActiverMq_Consumer_Demo(socket);
            activerMq_consumer_demo.init();
            new Thread(new ActiveMq_Consumer().new Consumer(activerMq_consumer_demo, activeMQ_Name)).start();
            */
            ActiveMq_Topic_Consumer_Demo activeMq_topic_consumer_demo = new ActiveMq_Topic_Consumer_Demo(socket);
            activeMq_topic_consumer_demo.init();
            ActiveMq_Topic_Consumer activeMq_topic_consumer = new ActiveMq_Topic_Consumer();
            new Thread(activeMq_topic_consumer.new Consumer(activeMq_topic_consumer_demo, activeMQ_Name)).start();

            /*
            Integer Number = Operation.getNumber();
            while(true) {
                sleep(1000);
                Integer number = Operation.getNumber();
                if(number != Number) {
                    String txtDate = null;
                    txtDate = Operation.getAll();
                    //sleep(5000);
                    writer.println(txtDate);
                    System.out.println("TcpServerThread_Send_OK!");
                    Number = number;
                }
                else {
                    System.out.println("客户端未发来信息，服务端无信息传输！");
                }
            }
        */
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
