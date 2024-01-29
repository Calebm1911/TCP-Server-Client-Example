/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcp_example;

/**
 *
 * @author 12107
 */
import java.io.*;
import java.net.*;

// Server class
 class Server {
	public static int count = 0;
    public static void main(String[] args)
    {
        ServerSocket server = null;
        try {
  
            // server is listening on port 1234
            server = new ServerSocket(6789);
            server.setReuseAddress(true);
  
            // running infinite loop for getting
            // client request
            while (true) {
				System.out.println("Server is running.");
                // socket object to receive incoming client
                // requests
                Socket client = server.accept();
  
                // Displaying that new client is connected
                // to server
                System.out.println("New client connected"
                                   + client.getInetAddress()
                                         .getHostAddress());
  
                // create a new thread object
                ClientHandler clientSock
                    = new ClientHandler(client);
  
                // This thread will handle the client
                // separately
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
  
    // ClientHandler class
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        // Constructor
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }
  
        public void run()
        {
            PrintWriter out = null;
            BufferedReader in = null;
            try {
                    
                  // get the outputstream of client
                out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
				System.out.println(clientSocket.getOutputStream().toString());
				
                  // get the inputstream of client
                in = new BufferedReader(
                    new InputStreamReader(
                        clientSocket.getInputStream()));
				System.out.println(clientSocket.getInputStream().toString());
                String line;
                while ((line = in.readLine()) != null) {
					count += 1;
                    // writing the received message from
                    // client
                    try {
                        if (line.equalsIgnoreCase("exit"))
                    {
                        count -= 1;
                        //Thread.sleep(2000);
                        
                        clientSocket.close();
                        System.out.println("Closed");
                    }
                    } catch (SocketException e) {
                        // TODO Auto-generated catch block
                        System.out.println("Socket Closed");
                    }
                
                   
                    System.out.println("Sent from: " + clientSocket.getInetAddress() + "On port: " + clientSocket.getPort() + ": " + line);
                    out.println(line + ": Message Number " + count);
                }
            }
            catch (IOException e) {
            }
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                }
                catch (IOException e) {
                }
            }
        }
    }
}