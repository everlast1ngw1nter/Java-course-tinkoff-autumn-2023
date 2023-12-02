package edu.hw8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Client {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<Void> callable = () -> {
            try (var socket = new Socket(InetAddress.getByName("localhost"), 53)) {
                var writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                writer.write("личности");
                writer.flush();
                socket.shutdownOutput();
                var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        };

        try (var executor = Executors.newFixedThreadPool(8)) {
            var tasks = Stream.generate(() -> callable)
                    .limit(64)
                    .toList();
            var futures = executor.invokeAll(tasks);
            for (var future : futures) {
                future.get();
            }
        }
    }
}
