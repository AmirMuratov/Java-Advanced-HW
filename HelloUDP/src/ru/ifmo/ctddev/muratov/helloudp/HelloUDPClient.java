package ru.ifmo.ctddev.muratov.helloudp;

import info.kgeorgiy.java.advanced.hello.HelloClient;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * HelloUDP was created by amir on 27.04.15.
 */
public class HelloUDPClient implements HelloClient{

    final int BUFFER_SIZE = 4096;
    final int TIME_TO_WAIT = 50;

    @Override
    public void start(String name, int port, String prefix, int numberOfQueries, int numberOfThreads) {
        //System.out.println("Client started: " + name + " " + port + " " + prefix + " " + numberOfThreads + " " + numberOfQueries);
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            executor.submit(new SendQueries(name, port, prefix, i, numberOfQueries));

        }
        executor.shutdownNow();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException ignore) {
        }
        /*Thread[] tmp = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            tmp[i] = new Thread(new SendQueries(name, port, prefix, i, numberOfQueries));
        }
        for (int i = 0; i < numberOfThreads; i++) {
            tmp[i].start();
        }
        for (int i = 0; i < numberOfThreads; i++) {
            try {
                tmp[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }

    private class SendQueries implements Runnable {

        private String name;
        private int port;
        private String prefix;
        private int threadNumber;
        private int numberOfQueries;

        public SendQueries(String name, int port, String prefix, int threadNumber, int numberOfQueries) {
            this.name = name;
            this.port = port;
            this.prefix = prefix;
            this.threadNumber = threadNumber;
            this.numberOfQueries = numberOfQueries;
        }

        private String sendRequest(String request, DatagramSocket socket) throws IOException {
            InetAddress address = InetAddress.getByName(name);
            DatagramPacket packetToSend = new DatagramPacket(request.getBytes(), request.length(), address, port);
            socket.send(packetToSend);
            DatagramPacket packetToReceive = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
            String result;
            try {
                socket.receive(packetToReceive);
                result = new String(packetToReceive.getData(), 0, packetToReceive.getLength());
            } catch (SocketTimeoutException e) {
                result = null;
            }
            return result;
        }

        @Override
        public void run() {
            try (DatagramSocket socket = new DatagramSocket()) {
                socket.setSoTimeout(TIME_TO_WAIT);
                for (int i = 0; i < numberOfQueries; i++) {
                    String query = prefix + threadNumber + "_" + i;
                    String result = null;
                    while (result == null) {
                        result = sendRequest(query, socket);
                    }

                    //
                    //System.out.println("Query: " + query);
                    //System.out.println("=========================");
                    //System.out.println("ANSWER: " + result);
                    //
                }
            } catch (IOException e) {
                System.out.println("No internet connection");
            }
        }
    }

    public static void main(String[] arg) {
        if (arg == null || arg.length != 5 || Arrays.stream(arg).anyMatch(Predicate.isEqual(null))) {
            System.out.print("Usage HelloUDPClient <name of computer/IP> <port number> " +
                    "<number of threads> <prefix of query> <number of threads> <queries in each thread>");
            return;
        }
        String name = arg[0];
        int port = Integer.valueOf(arg[1]);
        String prefix = arg[2];
        int numberOfThreads = Integer.valueOf(arg[3]);
        int numberOfQueries = Integer.valueOf(arg[4]);
        new HelloUDPClient().start(name, port, prefix, numberOfThreads, numberOfQueries);
    }
}
