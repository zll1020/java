package net.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-30
 * Time: 10:03
 */
public class BIOClient {

    private static Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8080);
        OutputStream outputStream = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String msg = scanner.nextLine();
        outputStream.write(msg.getBytes(charset));
        scanner.close();
        outputStream.close();
    }

}
