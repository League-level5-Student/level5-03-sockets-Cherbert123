package _01_Intro_To_Sockets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerGreeter extends Thread {
	// 1. Create an object of the ServerSocket class
	ServerSocket servSocket;

	public ServerGreeter() throws IOException {
		// 2. Initialize the ServerSocket object. In the parameters,
		// you must define the port at which the server will listen for connections.
		servSocket = new ServerSocket(8080);
		servSocket.setSoTimeout(60000);
		// *OPTIONAL* you can set a time limit for the server to wait by using the
		// ServerSocket's setSoTimeout(int timeInMilliSeconds) method
	}

	public void run() {
		// 3. Create a boolean variable and initialize it to true.
		boolean running = true;
		// 4. Make a while loop that continues looping as long as the boolean created in
		// the previous step is true.
		while (running) {
			try {
				// 8. Let the user know that the server is waiting for a client to connect.
				System.out.println("Connecting");
				// 9. Create an object of the Socket class and initialize it to
				// serverSocket.accept();
				// Change serverSocket to match the ServerSocket member variable you created in
				// step 1.
				// The program will wait her until either a client connects or the timeout
				// expires.
				Socket socket = servSocket.accept();
				// 10. Let the user know that the client has connected.
				System.out.println("Connected");
				// 11. Create a DataInputStream object. When initializing it, use the Socket
				// object you created in step 9 to call the getInputStream() method.
				DataInputStream inStream = (DataInputStream) socket.getInputStream();
				// 12. Print the message from the DataInputStream object using the readUTF()
				// method
				inStream.readUTF();
				// 13. Create a DataOutputStream object. When initializing it, use the Server
				// object you created in step 9 to call the getOutputStream() method.
				DataOutputStream outStream = (DataOutputStream) socket.getOutputStream();
				// 14. Use the DataOutputStream object to send a message to the client using the
				// writeUTF(String message) method.
				outStream.writeUTF("Hi");
				// 15. Close the client server
				socket.close();
				// 6. If the program catches a SockeTimeoutException, let the user know about it
				// and set loop's boolean variable to false.

				// 7. If the program catches a IOException, let the user know about it and set
				// the loop's boolean variable to false.

			} catch (IOException e) {
				System.out.println("IOException, Send Help");
				running = false;
			}

		}

	}

	public static void main(String[] args) {
		// 16. In a new thread, create an object of the ServerGreeter class and start
		// the thread. Don't forget the try-catch.
		Thread run = new Thread(() -> {
			try {
				ServerGreeter serv = new ServerGreeter();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		run.start();
	}
}
