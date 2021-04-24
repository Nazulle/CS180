import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public static String username = "";
    public static String password = "";
    static Socket socket = null;

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

        JOptionPane.showMessageDialog(null, "Welcome to the Social Media Profile App!", "Profile App", JOptionPane.INFORMATION_MESSAGE);
        try {
            socket = new Socket("localhost", 4242);

            //login code
            LoginGUI g = new LoginGUI();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    g.run();

                }
            });


            // create/ edit account

            // code for receiving profile

            // receiving userlist from server

            // sending/accepting friend requests

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendLoginInfo() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        if (socket.isConnected()) {
            //System.out.println(username);
            writer.println("login");
            writer.println(username);
            writer.println(password);
            writer.flush();
            String result = reader.readLine();
            JOptionPane.showMessageDialog(null, result, "Profile App", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Not connected", "Profile App", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void createAccount() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        if (socket.isConnected()) {
            //System.out.println(username);
            writer.println("createAccount");
            writer.println(username);
            writer.println(password);
            writer.flush();
            String result = reader.readLine();
            JOptionPane.showMessageDialog(null, result, "Profile App", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Not connected", "Profile App", JOptionPane.INFORMATION_MESSAGE);

    }



}

