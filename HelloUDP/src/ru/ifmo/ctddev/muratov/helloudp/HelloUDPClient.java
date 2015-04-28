package ru.ifmo.ctddev.muratov.helloudp;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * HelloUDP was created by amir on 27.04.15.
 */
public class HelloUDPClient {

    final int BUFFER_SIZE = 2048;

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

        @Override
        public void run() {
            for (int i = 0; i < numberOfQueries; i++) {
                String query = prefix + threadNumber + "_" + i;
                try (DatagramSocket socket = new DatagramSocket()) {
                    InetAddress address = InetAddress.getByName(name);
                    DatagramPacket packetToSend = new DatagramPacket(query.getBytes(), query.length(), address, port);
                    socket.send(packetToSend);
                    DatagramPacket packetToReceive = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE, address, port);
                    socket.receive(packetToReceive);

                    //
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < packetToReceive.getLength(); j++) {
                        sb.append((char) packetToReceive.getData()[j]);
                    }
                    System.out.println("Query: " + query);
                    System.out.println("ANSWER: " + sb.toString());
                    //

                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void run(String[] arg) {
        if (arg == null || arg.length != 5) {
            System.out.print("Usage HelloUDPClient <name of computer/IP> <port number> " +
                    "<number of threads> <prefix of query> <number of threads> <queries in each thread>");
            return;
        }
        String name = arg[0];
        int port = Integer.valueOf(arg[1]);
        String prefix = arg[2];
        int numberOfThreads = Integer.valueOf(arg[3]);
        int numberOfQueries = Integer.valueOf(arg[4]);
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            executor.submit(new SendQueries(name, port, prefix, i, numberOfQueries));
        }
        executor.shutdown();
    }


    public static void main(String[] arg) {
        new HelloUDPClient().run(arg);
    }
}
