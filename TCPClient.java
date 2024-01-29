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

public class TCPClient implements Runnable {

	public static void main(String argv[]) throws Exception {
		String sentence;
		String modifiedSentence;

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

		Socket clientSocket = new Socket("localhost", 6789); //127.0.0.1

		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		System.out.println ("The TCP client is on. Please enter your input:");
		String test = "";
		//Loops until exit message breaks
		while (true)
		{
			System.out.println("Please enter input");
		sentence = inFromUser.readLine();
		test = sentence;
		//If closes socket and breaks loop on exit
		if (test.equalsIgnoreCase("exit"))
		{
		System.out.println("Closing");
		outToServer.writeBytes(sentence + '\n');
		Thread.sleep(2000);
		clientSocket.close();
		System.out.println("Closed");
		break;
		}
		outToServer.writeBytes(sentence + '\n');

		modifiedSentence = inFromServer.readLine();

		System.out.println("FROM SERVER: " + modifiedSentence);
		}
	

	}
}

