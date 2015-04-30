package ru.ifmo.ctddev.muratov.helloudp;

import info.kgeorgiy.java.advanced.hello.HelloServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * HelloUDP was created by amir on 27.04.15.
 */
public class HelloUDPServer implements HelloServer {

    final int BUFFER_SIZE = 2048;
    final int TIME_OUT = 50;
    Thread[] t;
    DatagramSocket socketToReceive;

    @Override
    public void start(int port, int numberOfThreads) {
        t = new Thread[numberOfThreads];
        //System.out.println("Server started.");
        try {
            socketToReceive = new DatagramSocket(port);
            socketToReceive.setSoTimeout(TIME_OUT);
            for (int i = 0; i < numberOfThreads; i++) {
                t[i] = new Thread(new Answer(socketToReceive));
                t[i].start();
            }
        } catch (IOException e) {
            System.out.println("Can't create socket");
        }
    }

    @Override
    public void close() {
        for (Thread thread : t) {
            thread.interrupt();
        }
        for (Thread thread : t) {
            try {
                thread.join();
            } catch (InterruptedException ignore) {
            }
        }
        if (socketToReceive != null) {
            socketToReceive.close();
        }
    }

    private class Answer implements Runnable {

        DatagramSocket socketToReceive;

        public Answer(DatagramSocket socket1) {
            this.socketToReceive = socket1;
        }

        @Override
        public void run() {
            DatagramPacket packet = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
            try (DatagramSocket socketToSend = new DatagramSocket()) {
                while (!Thread.interrupted()) {
                    try {
                        socketToReceive.receive(packet);
                    } catch (SocketTimeoutException e) {
                        continue;
                    }
                    String s = "Hello, ";
                    byte[] answer = new byte[packet.getLength() + s.length()];
                    for (int i = 0; i < s.length(); i++) {
                        answer[i] = (byte) s.charAt(i);
                    }
                    for (int i = 0; i < packet.getLength(); i++) {
                        answer[s.length() + i] = packet.getData()[i];
                    }

                    packet.setData(answer);
                    packet.setLength(answer.length);
                    socketToSend.send(packet);
                }
            } catch (IOException ignored) {
            }
        }
    }

    public static void main(String[] arg) {
        if (arg == null || arg.length != 2 || Arrays.stream(arg).anyMatch(Predicate.isEqual(null))) {
            System.out.print("Usage HelloUDPServer <port number> <number of threads>");
            return;
        }
        int port = Integer.valueOf(arg[0]);
        int numberOfThreads = Integer.valueOf(arg[1]);
        new HelloUDPServer().start(port, numberOfThreads);
    }
}
