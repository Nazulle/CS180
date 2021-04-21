import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.ArrayList;
/**
 * Profile Server
 *
 * This class consists of a server used to make Profiles and accounts for a social media app.
 *	
 * @author Saketh Ayyalasomayajula, sayyala@purdue.edu
 * @version April 20th, 2021
 */

 public class ProfileServer {
     // this is the list of all the users that the server will have
     private ArrayList<Profile> userList;
    public static void main(String[] args) throws IOException {
        // initilization
        boolean serverChek = true;
        int port = 4242;
        ServerSocket serverSocket = new ServerSocket(port);

        do {
            Socket socket = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream()); 
    
            // authentication class code

            Thread t = new Thread();

        } while (serverChek);
    }
 }
