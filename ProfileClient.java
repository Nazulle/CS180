import java.io.*;
import java.net.*;
import javax.swing.*;

/**
 * Calculator Client
 *
 * This class consists of a client that handles user inputs for basic equations through displaying GUIs.
 *	
 * @author Saketh Ayyalasomayajula, sayyala@purdue.edu
 * @version April 19th, 2021
 */

public class ProfileClient {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

        Socket socket = null;
        JOptionPane.showMessageDialog(null, "Welcome to the Social Media Profile App!", "Profile App", JOptionPane.INFORMATION_MESSAGE);

        try {
            socket = new Socket("localhost", 4242);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            //login code

            // create/ edit account

            // code for receiving profile

            // receiving userlist from server

            // sending/accepting friend requests

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
