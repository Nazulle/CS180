import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.ArrayList;
/**
 * Profile Server
 *
 * This class consists of a server used to make Profiles and accounts for a social media app.
 *	
 * @author Saketh Ayyalasomayajula(sayyala@purdue.edu), Minwoo Jung(jung361@purdue.edu)
 * @version April 20th, 2021
 */

 public class ProfileServer implements Runnable {
    Socket csocket;
    // initilization
    static ArrayList<Profile> profiles = new ArrayList<>();
    static Authentication profilesList = new Authentication(profiles);

    public ProfileServer(Socket csocket) {
        this.csocket = csocket;
    }


    public static void main(String[] args) throws IOException {

        boolean serverChek = true;
        int port = 4242;
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("A Client connected");
            System.out.println(profilesList);
            new Thread(new ProfileServer(socket)).start();
        }


    }
    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
            PrintWriter writer = new PrintWriter(csocket.getOutputStream());

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
            } while (true);
            reader.close();
            writer.close();
            csocket.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
