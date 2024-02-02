package edu.hw8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Task1Client {

    private Task1Client() {
    }

    private static final int PORT = 18080;

    public static String getCausticResponse(String msg) {
        try (var socket = new Socket(InetAddress.getByName("localhost"), PORT)) {
            var writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(msg);
            writer.flush();
            socket.shutdownOutput();
            var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
