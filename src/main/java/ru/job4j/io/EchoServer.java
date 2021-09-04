package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        int indMsg = str.indexOf("msg=");
                        int indHttp = str.indexOf("HTTP/1.1");
                        if (indMsg != -1 && indHttp != -1) {
                            String msg = str.substring(indMsg + 4, indHttp - 1);
                            if (msg.equalsIgnoreCase("exit")) {
                                return;
                            } else if (msg.equalsIgnoreCase("hello")) {
                                out.write("Hello".getBytes());
                            } else {
                                out.write(msg.getBytes());
                            }
                        }
                    }
                    out.flush();
                }
            }
        } catch (Exception ex) {
            LOG.error("Exception in log example", ex);
        }
    }
}