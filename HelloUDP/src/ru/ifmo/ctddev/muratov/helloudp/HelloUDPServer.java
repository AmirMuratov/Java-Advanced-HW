package ru.ifmo.ctddev.muratov.helloudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.*;

/**
 * HelloUDP was created by amir on 27.04.15.
 */
public class HelloUDPServer {

    final int BUFFER_SIZE = 2048;

    private class Answer implements Runnable {

        byte[] query;
        DatagramSocket socketToSend;
        InetAddress addressToSend;
        int portToSend;
        int length;

        public Answer(InetAddress address, int port, byte[] query, int length, DatagramSocket socket) {
            addressToSend = address;
            portToSend = port;
            this.query = query;
            this.socketToSend = socket;
            this.length = length;
        }

        @Override
        public void run() {
            String s = "Hello, ";
            byte[] answer = new byte[length + s.length()];
            for (int i = 0; i < s.length(); i++) {
                answer[i] = (byte) s.charAt(i);
            }
            for (int i = 0; i < length; i++) {
                answer[s.length() + i] = query[i];
            }

            //
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append((char) query[i]);
            }
            System.out.println("LEN: " + length);
            System.out.println("GET: " + sb.toString());
            //

            DatagramPacket packet = new DatagramPacket(answer, answer.length, addressToSend, portToSend);
            try {
                socketToSend.send(packet);
                System.out.println("Answer was sent");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void run(String[] arg) {
        if (arg == null || arg.length != 2) {
            System.out.print("Usage HelloUDPServer <port number> <number of threads>");
            return;
        }
        int port = Integer.valueOf(arg[0]);
        int numberOfThreads = Integer.valueOf(arg[1]);
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        try(DatagramSocket socket = new DatagramSocket(port)) {
            DatagramPacket packet = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
            System.out.println("Server started");
            while (true) {
                socket.receive(packet);
                System.out.println("Packet received");
                executor.submit(new Answer(packet.getAddress(), packet.getPort(), packet.getData().clone(), packet.getLength(), socket));
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }


    public static void main(String[] arg) {
        new HelloUDPServer().run(arg);
    }
}
