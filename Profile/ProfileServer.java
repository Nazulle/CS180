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


    public static void main(String[] args) throws IOException {
        // initilization
        ArrayList<Profile> profiles = new ArrayList<>();
        Authentication profilesList = new Authentication(profiles);
        try {
            profilesList.createProfile("minoj", "1234");
            profilesList.createProfile("sayyala", "1234");
        } catch (OccupiedProfileException e) {
            e.printStackTrace();
        }

        boolean serverChek = true;
        int port = 4242;
        ServerSocket serverSocket = new ServerSocket(port);
        do {
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            do {
                // authentication class code
                try {
                    String function = reader.readLine().trim();
                    String username = reader.readLine().trim();
                    String password = reader.readLine().trim();
                    System.out.println(username + password);
                    if (function.equals("login")) {
                        Profile p = profilesList.login(username, password);
                        System.out.println(p);
                        if (p != null) {
                            writer.println("success");
                        } else {
                            writer.println("fail");
                        }
                        writer.flush();
                    }
                    if (function.equals("createAccount")) {
                        try {
                            Profile p = profilesList.createProfile(username, password);
                            if (p != null) {
                                writer.println("Create account Success");
                                System.out.println(profilesList);
                            }
                        } catch (OccupiedProfileException o) {
                            System.out.println("Occupied Profile");
                            writer.println("Occupied Profile");
                        }
                        writer.flush();

                    }
                } catch (NullPointerException n) {
                    System.out.println("There was an null error");
                    break;
                }
            } while (serverChek);
            reader.close();
            writer.close();
            socket.close();
        } while (true);


    }
}
