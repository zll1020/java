package net.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-30
 * Time: 10:09
 */
public class BIOServer {

    private Charset charset = Charset.forName("UTF-8");

    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器启动成功");

        while (!serverSocket.isClosed()){
            Socket socket = serverSocket.accept();
            System.out.println("收到新连接 : " + socket.toString());

            threadPool.execute(()->{
                try {
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
                    String msg;
                    while ((msg = reader.readLine()) != null){
                        if (msg.length() == 0){
                            break;
                        }
                        System.out.println(msg);
                    }
                    System.out.println("收到数据,来自："+ socket.toString());

                    // 响应结果 200
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
                    outputStream.write("Content-Length: 11\r\n\r\n".getBytes());
                    outputStream.write("Hello World".getBytes());
                    outputStream.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        serverSocket.close();

    }
}
