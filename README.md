# TCP-Server-Client-Example
This project demonstrates a simple TCP server and client implementation in Java. The server listens on a specified port, handling multiple client connections concurrently. The client connects to the server, sends messages, and handles a special "exit" command to gracefully close the connection.
Project Structure
Server.java: The main class representing the TCP server. It listens for incoming connections and handles them using a separate thread for each client.

ClientHandler: A nested class within the Server class, responsible for handling each connected client in a separate thread. It receives messages from clients and sends a response back.

TCPClient.java: The main class representing the TCP client. It connects to the server, sends messages, and handles the "exit" command to gracefully close the connection.

# Usage
Compile and run the server, then compile and run the client.
Enter messages in the client terminal to send them to the server.
The server responds with a modified version of the received message.
The client recognizes the "exit" command to gracefully close the connection.
